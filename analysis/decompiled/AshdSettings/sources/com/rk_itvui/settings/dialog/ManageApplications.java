package com.rk_itvui.settings.dialog;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.os.SystemProperties;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;
import com.ashd.settings.R;
import com.rk_itvui.settings.BuildConfig;
import com.rk_itvui.settings.FullScreenAlertActivity;
import com.rk_itvui.settings.Utils;
import java.util.ArrayList;
import java.util.Comparator;

/* JADX INFO: loaded from: classes.dex */
public class ManageApplications extends FullScreenAlertActivity implements AdapterView.OnItemClickListener, DialogInterface.OnCancelListener, TabHost.TabContentFactory, TabHost.OnTabChangeListener {
    public static final String APP_CHG = "chg";
    static final boolean DEBUG = false;
    public static final int FILTER_APPS_ALL = 0;
    public static final int FILTER_APPS_SDCARD = 2;
    public static final int FILTER_APPS_THIRD_PARTY = 1;
    private static final int INSTALLED_APP_DETAILS = 1;
    private static final int MENU_OPTIONS_BASE = 0;
    public static final int SHOW_BACKGROUND_PROCESSES = 7;
    public static final int SHOW_RUNNING_SERVICES = 6;
    public static final int SORT_ORDER_ALPHA = 4;
    public static final int SORT_ORDER_SIZE = 5;
    static final String TAB_ALL = "All";
    static final String TAB_DOWNLOADED = "Downloaded";
    static final String TAB_RUNNING = "Running";
    static final String TAB_SDCARD = "OnSdCard";
    static final String TAG = "ManageApplications";
    static final int VIEW_LIST = 1;
    static final int VIEW_NOTHING = 0;
    static final int VIEW_RUNNING = 2;
    private static final boolean mIsHideStorageInfo = SystemProperties.getBoolean("persist.sys.hideapp.capacity", false);
    private boolean mActivityResumed;
    private ApplicationsAdapter mApplicationsAdapter;
    private ApplicationsState mApplicationsState;
    LinearColorBar mColorBar;
    private CharSequence mComputingSizeStr;
    private boolean mCreatedRunning;
    private int mCurView;
    private String mCurrentPkgName;
    private StatFs mDataFileStats;
    TextView mFreeStorageText;
    private LayoutInflater mInflater;
    private CharSequence mInvalidSizeStr;
    private long mLastAppStorage;
    private long mLastFreeStorage;
    private long mLastUsedStorage;
    private View mListContainer;
    private ListView mListView;
    private View mLoadingContainer;
    private Object mNonConfigInstance;
    private boolean mResumedRunning;
    private View mRootView;
    private RunningProcessesView mRunningProcessesView;
    private StatFs mSDCardFileStats;
    TextView mStorageChartLabel;
    private TabHost mTabHost;
    TextView mUsedStorageText;
    private int mSortOrder = 4;
    private int mFilterApps = 1;
    private boolean mLastShowedInternalStorage = true;
    private int myTabCurrentIndex = 0;
    private boolean myTabHasFocus = true;
    final Runnable mRunningProcessesAvail = new Runnable() { // from class: com.rk_itvui.settings.dialog.ManageApplications.1
        @Override // java.lang.Runnable
        public void run() {
            ManageApplications.this.handleRunningProcessesAvail();
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public String getTagByIndex(int i) {
        switch (i) {
            case 0:
                return TAB_DOWNLOADED;
            case 1:
                return TAB_ALL;
            case 2:
                return TAB_SDCARD;
            case 3:
                return TAB_RUNNING;
            default:
                return TAB_DOWNLOADED;
        }
    }

    private void hideStorageInfo() {
        findViewById(R.id.storage_color_bar).setVisibility(4);
    }

    private void showDeviceInfO() {
        findViewById(R.id.storage_color_bar).setVisibility(0);
    }

    static class AppViewHolder {
        ImageView appIcon;
        TextView appName;
        TextView appSize;
        CheckBox checkBox;
        TextView disabled;
        ApplicationsState.AppEntry entry;

        AppViewHolder() {
        }

        void updateSizeText(ManageApplications manageApplications) {
            if (this.entry.sizeStr != null) {
                this.appSize.setText(this.entry.sizeStr);
            } else if (this.entry.size == -2) {
                this.appSize.setText(manageApplications.mInvalidSizeStr);
            }
        }
    }

    class ApplicationsAdapter extends BaseAdapter implements Filterable, ApplicationsState.Callbacks, AbsListView.RecyclerListener {
        private ArrayList<ApplicationsState.AppEntry> mBaseEntries;
        CharSequence mCurFilterPrefix;
        private ArrayList<ApplicationsState.AppEntry> mEntries;
        private boolean mResumed;
        private final ApplicationsState mState;
        private boolean mWaitingForData;
        private final ArrayList<View> mActive = new ArrayList<>();
        private int mLastFilterMode = -1;
        private int mLastSortMode = -1;
        private Filter mFilter = new Filter() { // from class: com.rk_itvui.settings.dialog.ManageApplications.ApplicationsAdapter.1
            @Override // android.widget.Filter
            protected Filter.FilterResults performFiltering(CharSequence charSequence) {
                ArrayList<ApplicationsState.AppEntry> arrayListApplyPrefixFilter = ApplicationsAdapter.this.applyPrefixFilter(charSequence, ApplicationsAdapter.this.mBaseEntries);
                Filter.FilterResults filterResults = new Filter.FilterResults();
                filterResults.values = arrayListApplyPrefixFilter;
                filterResults.count = arrayListApplyPrefixFilter.size();
                return filterResults;
            }

            @Override // android.widget.Filter
            protected void publishResults(CharSequence charSequence, Filter.FilterResults filterResults) {
                ApplicationsAdapter.this.mCurFilterPrefix = charSequence;
                ApplicationsAdapter.this.mEntries = (ArrayList) filterResults.values;
                ApplicationsAdapter.this.notifyDataSetChanged();
                ManageApplications.this.updateStorageUsage();
            }
        };

        @Override // com.rk_itvui.settings.dialog.ApplicationsState.Callbacks
        public void onPackageIconChanged() {
        }

        @Override // com.rk_itvui.settings.dialog.ApplicationsState.Callbacks
        public void onRunningStateChanged(boolean z) {
        }

        public ApplicationsAdapter(ApplicationsState applicationsState) {
            this.mState = applicationsState;
        }

        public void resume(int i, int i2) {
            if (!this.mResumed) {
                this.mResumed = true;
                this.mState.resume(this);
                this.mLastFilterMode = i;
                this.mLastSortMode = i2;
                rebuild(true);
                return;
            }
            rebuild(i, i2);
        }

        public void pause() {
            if (this.mResumed) {
                this.mResumed = false;
                this.mState.pause();
            }
        }

        public void rebuild(int i, int i2) {
            if (i == this.mLastFilterMode && i2 == this.mLastSortMode) {
                return;
            }
            this.mLastFilterMode = i;
            this.mLastSortMode = i2;
            rebuild(true);
        }

        public void rebuild(boolean z) {
            ApplicationsState.AppFilter appFilter;
            Comparator<ApplicationsState.AppEntry> comparator;
            switch (this.mLastFilterMode) {
                case 1:
                    appFilter = ApplicationsState.THIRD_PARTY_FILTER;
                    break;
                case 2:
                    appFilter = ApplicationsState.ON_SD_CARD_FILTER;
                    break;
                default:
                    appFilter = null;
                    break;
            }
            if (this.mLastSortMode == 5) {
                comparator = ApplicationsState.SIZE_COMPARATOR;
            } else {
                comparator = ApplicationsState.ALPHA_COMPARATOR;
            }
            ArrayList<ApplicationsState.AppEntry> arrayListRebuild = this.mState.rebuild(appFilter, comparator);
            if (arrayListRebuild != null || z) {
                this.mBaseEntries = arrayListRebuild;
                if (this.mBaseEntries != null) {
                    this.mEntries = applyPrefixFilter(this.mCurFilterPrefix, this.mBaseEntries);
                } else {
                    this.mEntries = null;
                }
                notifyDataSetChanged();
                ManageApplications.this.updateStorageUsage();
                if (arrayListRebuild != null) {
                    ManageApplications.this.mListContainer.setVisibility(0);
                    ManageApplications.this.mLoadingContainer.setVisibility(8);
                } else {
                    this.mWaitingForData = true;
                    ManageApplications.this.mListContainer.setVisibility(4);
                    ManageApplications.this.mLoadingContainer.setVisibility(0);
                }
            }
        }

        ArrayList<ApplicationsState.AppEntry> applyPrefixFilter(CharSequence charSequence, ArrayList<ApplicationsState.AppEntry> arrayList) {
            if (charSequence == null || charSequence.length() == 0) {
                return arrayList;
            }
            String strNormalize = ApplicationsState.normalize(charSequence.toString());
            String str = " " + strNormalize;
            ArrayList<ApplicationsState.AppEntry> arrayList2 = new ArrayList<>();
            for (int i = 0; i < arrayList.size(); i++) {
                ApplicationsState.AppEntry appEntry = arrayList.get(i);
                String normalizedLabel = appEntry.getNormalizedLabel();
                if (normalizedLabel.startsWith(strNormalize) || normalizedLabel.indexOf(str) != -1) {
                    arrayList2.add(appEntry);
                }
            }
            return arrayList2;
        }

        @Override // com.rk_itvui.settings.dialog.ApplicationsState.Callbacks
        public void onRebuildComplete(ArrayList<ApplicationsState.AppEntry> arrayList) {
            if (ManageApplications.this.mLoadingContainer.getVisibility() == 0) {
                ManageApplications.this.mLoadingContainer.startAnimation(AnimationUtils.loadAnimation(ManageApplications.this, android.R.anim.fade_out));
                ManageApplications.this.mListContainer.startAnimation(AnimationUtils.loadAnimation(ManageApplications.this, android.R.anim.fade_in));
            }
            ManageApplications.this.mListContainer.setVisibility(0);
            ManageApplications.this.mLoadingContainer.setVisibility(8);
            this.mWaitingForData = false;
            this.mBaseEntries = arrayList;
            this.mEntries = applyPrefixFilter(this.mCurFilterPrefix, this.mBaseEntries);
            notifyDataSetChanged();
            ManageApplications.this.updateStorageUsage();
        }

        @Override // com.rk_itvui.settings.dialog.ApplicationsState.Callbacks
        public void onPackageListChanged() {
            rebuild(false);
        }

        @Override // com.rk_itvui.settings.dialog.ApplicationsState.Callbacks
        public void onPackageSizeChanged(String str) {
            for (int i = 0; i < this.mActive.size(); i++) {
                AppViewHolder appViewHolder = (AppViewHolder) this.mActive.get(i).getTag();
                if (appViewHolder.entry.info.packageName.equals(str)) {
                    synchronized (appViewHolder.entry) {
                        appViewHolder.updateSizeText(ManageApplications.this);
                    }
                    if (appViewHolder.entry.info.packageName.equals(ManageApplications.this.mCurrentPkgName) && this.mLastSortMode == 5) {
                        rebuild(false);
                    }
                    ManageApplications.this.updateStorageUsage();
                    return;
                }
            }
        }

        @Override // com.rk_itvui.settings.dialog.ApplicationsState.Callbacks
        public void onAllSizesComputed() {
            if (this.mLastSortMode == 5) {
                rebuild(false);
            }
        }

        @Override // android.widget.Adapter
        public int getCount() {
            if (this.mEntries != null) {
                return this.mEntries.size();
            }
            return 0;
        }

        @Override // android.widget.Adapter
        public Object getItem(int i) {
            return this.mEntries.get(i);
        }

        public ApplicationsState.AppEntry getAppEntry(int i) {
            return this.mEntries.get(i);
        }

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return this.mEntries.get(i).id;
        }

        @Override // android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            AppViewHolder appViewHolder;
            if (view == null) {
                view = ManageApplications.this.mInflater.inflate(R.layout.manage_applications_item, (ViewGroup) null);
                appViewHolder = new AppViewHolder();
                appViewHolder.appName = (TextView) view.findViewById(R.id.app_name);
                appViewHolder.appIcon = (ImageView) view.findViewById(R.id.app_icon);
                appViewHolder.appSize = (TextView) view.findViewById(R.id.app_size);
                appViewHolder.disabled = (TextView) view.findViewById(R.id.app_disabled);
                appViewHolder.checkBox = (CheckBox) view.findViewById(R.id.app_on_sdcard);
                view.setTag(appViewHolder);
            } else {
                appViewHolder = (AppViewHolder) view.getTag();
            }
            ApplicationsState.AppEntry appEntry = this.mEntries.get(i);
            synchronized (appEntry) {
                appViewHolder.entry = appEntry;
                if (appEntry.label != null) {
                    appViewHolder.appName.setText(appEntry.label);
                }
                this.mState.ensureIcon(appEntry);
                if (appEntry.icon != null) {
                    appViewHolder.appIcon.setImageDrawable(appEntry.icon);
                }
                appViewHolder.updateSizeText(ManageApplications.this);
                appViewHolder.disabled.setVisibility(8);
                if (this.mLastFilterMode == 2) {
                    appViewHolder.checkBox.setVisibility(0);
                    appViewHolder.checkBox.setChecked((appEntry.info.flags & 262144) != 0);
                } else {
                    appViewHolder.checkBox.setVisibility(8);
                }
            }
            this.mActive.remove(view);
            this.mActive.add(view);
            return view;
        }

        @Override // android.widget.Filterable
        public Filter getFilter() {
            return this.mFilter;
        }

        @Override // android.widget.AbsListView.RecyclerListener
        public void onMovedToScrapHeap(View view) {
            this.mActive.remove(view);
        }
    }

    /* JADX WARN: Code duplicated, block: B:18:0x00c8  */
    @Override // com.rk_itvui.settings.FullScreenAlertActivity
    protected void onCreate(Bundle bundle) {
        String string;
        Log.d(TAG, "====================================================");
        super.onCreate(bundle);
        this.mApplicationsState = ApplicationsState.getInstance(getApplication());
        this.mApplicationsAdapter = new ApplicationsAdapter(this.mApplicationsState);
        Intent intent = getIntent();
        String action = intent.getAction();
        String str = TAB_DOWNLOADED;
        if (intent.getComponent().getClassName().equals("com.android.settings.RunningServices")) {
            str = TAB_RUNNING;
            Log.d(TAG, "onCreate action=" + intent.getComponent().getClassName());
        } else if (intent.getComponent().getClassName().equals("com.android.settings.applications.StorageUse") || action.equals("android.intent.action.MANAGE_PACKAGE_STORAGE")) {
            this.mSortOrder = 5;
            this.mFilterApps = 0;
            str = TAB_ALL;
            Log.d(TAG, "onCreate action=" + intent.getComponent().getClassName());
        } else if (action.equals("android.settings.MANAGE_ALL_APPLICATIONS_SETTINGS")) {
            str = TAB_ALL;
            Log.d(TAG, "onCreate action=Settings.ACTION_MANAGE_ALL_APPLICATIONS_SETTINGS");
        }
        if (bundle != null) {
            this.mSortOrder = bundle.getInt("sortOrder", this.mSortOrder);
            this.mFilterApps = bundle.getInt("filterApps", this.mFilterApps);
            string = bundle.getString("defaultTabTag");
            if (string == null) {
                string = str;
            }
        } else {
            string = str;
        }
        this.mNonConfigInstance = getLastNonConfigurationInstance();
        try {
            this.mDataFileStats = new StatFs("/data");
            this.mSDCardFileStats = new StatFs(Environment.getExternalStorageDirectory().toString());
        } catch (Exception unused) {
        }
        requestWindowFeature(4);
        requestWindowFeature(5);
        this.mInvalidSizeStr = getText(R.string.invalid_size_value);
        this.mComputingSizeStr = getText(R.string.computing_size);
        this.mInflater = (LayoutInflater) getSystemService("layout_inflater");
        this.mRootView = this.mInflater.inflate(R.layout.manage_applications, (ViewGroup) null);
        this.mLoadingContainer = this.mRootView.findViewById(R.id.loading_container);
        this.mListContainer = this.mRootView.findViewById(R.id.list_container);
        if (BuildConfig.FLAVOR.contains(BuildConfig.FLAVOR)) {
            TextView textView = (TextView) this.mRootView.findViewById(R.id.app_text);
            TextView textView2 = (TextView) this.mRootView.findViewById(R.id.settings_line);
            textView.setCompoundDrawablesWithIntrinsicBounds(getDrawable(R.drawable.icon_ashd2), (Drawable) null, (Drawable) null, (Drawable) null);
            textView2.setBackgroundResource(R.drawable.settings_head_line_ashd2);
        }
        ListView listView = (ListView) this.mListContainer.findViewById(android.R.id.list);
        View viewFindViewById = this.mListContainer.findViewById(android.R.id.empty);
        if (viewFindViewById != null) {
            listView.setEmptyView(viewFindViewById);
        }
        listView.setOnItemClickListener(this);
        listView.setSaveEnabled(true);
        listView.setItemsCanFocus(true);
        listView.setOnItemClickListener(this);
        listView.setTextFilterEnabled(true);
        listView.setSelection(2);
        this.mListView = listView;
        listView.setRecyclerListener(this.mApplicationsAdapter);
        this.mListView.setAdapter((ListAdapter) this.mApplicationsAdapter);
        this.mListView.setSelection(2);
        this.mColorBar = (LinearColorBar) this.mListContainer.findViewById(R.id.storage_color_bar);
        this.mStorageChartLabel = (TextView) this.mListContainer.findViewById(R.id.storageChartLabel);
        this.mUsedStorageText = (TextView) this.mListContainer.findViewById(R.id.usedStorageText);
        this.mFreeStorageText = (TextView) this.mListContainer.findViewById(R.id.freeStorageText);
        this.mRunningProcessesView = (RunningProcessesView) this.mRootView.findViewById(R.id.running_processes);
        LayoutInflater layoutInflater = (LayoutInflater) getSystemService("layout_inflater");
        View viewInflate = layoutInflater.inflate(R.layout.setting_tab_downloaded, (ViewGroup) null);
        View viewInflate2 = layoutInflater.inflate(R.layout.setting_tab_all, (ViewGroup) null);
        View viewInflate3 = layoutInflater.inflate(R.layout.setting_tab_running, (ViewGroup) null);
        if (this.mTabHost == null) {
            setContentView(R.layout.my_tab_content);
            Log.i(TAG, "setContentView");
        }
        this.mTabHost = (TabHost) findViewById(R.id.mytabhost);
        final TabHost tabHost = getTabHost();
        tabHost.setup();
        tabHost.setOnKeyListener(new View.OnKeyListener() { // from class: com.rk_itvui.settings.dialog.ManageApplications.2
            @Override // android.view.View.OnKeyListener
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (keyEvent.getAction() == 1) {
                    return false;
                }
                Log.i("test", "onKey");
                return false;
            }
        });
        TabWidget tabWidget = tabHost.getTabWidget();
        LinearLayout linearLayout = (LinearLayout) tabHost.getChildAt(0);
        linearLayout.removeViewAt(0);
        linearLayout.addView(tabWidget);
        tabHost.addTab(tabHost.newTabSpec(TAB_DOWNLOADED).setIndicator(viewInflate).setContent(this));
        tabHost.addTab(tabHost.newTabSpec(TAB_ALL).setIndicator(viewInflate2).setContent(this));
        tabHost.addTab(tabHost.newTabSpec(TAB_RUNNING).setIndicator(viewInflate3).setContent(this));
        for (int i = 0; i < 3; i++) {
            tabHost.getTabWidget().getChildAt(i).setBackgroundResource("release".equals("eon") ? R.drawable.tab_bg_selector_eon : R.drawable.tab_bg_selector);
        }
        tabHost.setCurrentTabByTag(string);
        tabHost.setOnTabChangedListener(this);
        hideSystemUI();
        this.mListView.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.rk_itvui.settings.dialog.ManageApplications.3
            @Override // android.view.View.OnFocusChangeListener
            public void onFocusChange(View view, boolean z) {
                if (z) {
                    Log.i(ManageApplications.TAG, "mListView获取焦点");
                    ManageApplications.this.myTabHasFocus = false;
                } else {
                    Log.i(ManageApplications.TAG, "mListView失去焦点");
                    ManageApplications.this.myTabHasFocus = true;
                    tabHost.setCurrentTabByTag(ManageApplications.this.getTagByIndex(ManageApplications.this.myTabCurrentIndex));
                }
            }
        });
        checkIsShowDeviceInfo();
    }

    public void onStart() {
        super.onStart();
    }

    public TabHost getTabHost() {
        return this.mTabHost;
    }

    protected void onResume() {
        super.onResume();
        this.mActivityResumed = true;
        showCurrentTab();
    }

    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt("sortOrder", this.mSortOrder);
        bundle.putInt("filterApps", this.mFilterApps);
        bundle.putString("defautTabTag", getTabHost().getCurrentTabTag());
    }

    public Object onRetainNonConfigurationInstance() {
        return this.mRunningProcessesView.doRetainNonConfigurationInstance();
    }

    protected void onPause() {
        super.onPause();
        this.mActivityResumed = false;
        this.mApplicationsAdapter.pause();
        if (this.mResumedRunning) {
            this.mRunningProcessesView.doPause();
            this.mResumedRunning = false;
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        if (i != 1 || this.mCurrentPkgName == null) {
            return;
        }
        this.mApplicationsState.requestSize(this.mCurrentPkgName, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void startApplicationDetailsActivity() {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse("package:" + this.mCurrentPkgName));
        intent.setClass(this, InstalledAppDetails.class);
        startActivity(intent);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 4, 1, R.string.sort_order_alpha).setIcon(android.R.drawable.ic_menu_sort_alphabetically);
        menu.add(0, 5, 2, R.string.sort_order_size).setIcon(android.R.drawable.ic_menu_sort_by_size);
        menu.add(0, 6, 3, R.string.show_running_services);
        menu.add(0, 7, 3, R.string.show_background_processes);
        return true;
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        if (this.mCurView == 2) {
            boolean showBackground = this.mRunningProcessesView.mAdapter.getShowBackground();
            menu.findItem(4).setVisible(false);
            menu.findItem(5).setVisible(false);
            menu.findItem(6).setVisible(showBackground);
            menu.findItem(7).setVisible(!showBackground);
        } else {
            menu.findItem(4).setVisible(this.mSortOrder != 4);
            menu.findItem(5).setVisible(this.mSortOrder != 5);
            menu.findItem(6).setVisible(false);
            menu.findItem(7).setVisible(false);
        }
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId == 4 || itemId == 5) {
            this.mSortOrder = itemId;
            if (this.mCurView != 2) {
                this.mApplicationsAdapter.rebuild(this.mFilterApps, this.mSortOrder);
            }
        } else if (itemId == 6) {
            this.mRunningProcessesView.mAdapter.setShowBackground(false);
        } else if (itemId == 7) {
            this.mRunningProcessesView.mAdapter.setShowBackground(true);
        }
        return true;
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (i == 84 && keyEvent.isTracking()) {
            if (this.mCurView == 2) {
                return true;
            }
            ((InputMethodManager) getSystemService("input_method")).showSoftInputUnchecked(0, null);
            return true;
        }
        return super.onKeyUp(i, keyEvent);
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        this.mCurrentPkgName = this.mApplicationsAdapter.getAppEntry(i).info.packageName;
        startApplicationDetailsActivity();
    }

    @Override // android.content.DialogInterface.OnCancelListener
    public void onCancel(DialogInterface dialogInterface) {
        finish();
    }

    @Override // android.widget.TabHost.TabContentFactory
    public View createTabContent(String str) {
        return this.mRootView;
    }

    /* JADX WARN: Code duplicated, block: B:40:0x00d8 A[LOOP:0: B:39:0x00d6->B:40:0x00d8, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:43:0x0109  */
    /* JADX WARN: Code duplicated, block: B:46:0x0114  */
    /* JADX WARN: Code duplicated, block: B:48:0x0140  */
    /* JADX WARN: Code duplicated, block: B:51:0x0164  */
    /* JADX WARN: Code duplicated, block: B:52:0x0183  */
    /* JADX WARN: Code duplicated, block: B:54:0x0191  */
    /* JADX WARN: Code duplicated, block: B:57:0x01a0  */
    /* JADX WARN: Code duplicated, block: B:74:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:76:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Instruction removed from duplicated block: B:46:0x0114, please report this as an issue */
    void updateStorageUsage() {
        CharSequence text;
        IllegalArgumentException illegalArgumentException;
        long blockCount;
        long availableBlocks;
        int count;
        int i;
        long j;
        long jSumCacheSizes;
        long j2;
        long j3;
        if (this.mCurView == 2) {
            return;
        }
        if (this.mFilterApps == 2) {
            if (this.mLastShowedInternalStorage) {
                this.mLastShowedInternalStorage = false;
            }
            text = getText(R.string.sd_card_storage);
            this.mSDCardFileStats.restat(Environment.getExternalStorageDirectory().toString());
            try {
                blockCount = ((long) this.mSDCardFileStats.getBlockCount()) * ((long) this.mSDCardFileStats.getBlockSize());
                try {
                    jSumCacheSizes = ((long) this.mSDCardFileStats.getAvailableBlocks()) * ((long) this.mSDCardFileStats.getBlockSize());
                    try {
                        Log.e("formatSize", "totalStorage=" + blockCount + ",freeStorage=" + jSumCacheSizes);
                    } catch (IllegalArgumentException unused) {
                    }
                } catch (IllegalArgumentException unused2) {
                    jSumCacheSizes = 0;
                }
            } catch (IllegalArgumentException unused3) {
                blockCount = 0;
            }
            j2 = 0;
        } else {
            if (!this.mLastShowedInternalStorage) {
                this.mLastShowedInternalStorage = true;
            }
            text = getText(R.string.internal_storage);
            this.mDataFileStats.restat("/data");
            try {
                blockCount = ((long) this.mDataFileStats.getBlockCount()) * ((long) this.mDataFileStats.getBlockSize());
                try {
                    availableBlocks = ((long) this.mDataFileStats.getAvailableBlocks()) * ((long) this.mDataFileStats.getBlockSize());
                    try {
                        Log.e("formatSize", "totalStorage1=" + blockCount + ",freeStorage2=" + availableBlocks);
                    } catch (IllegalArgumentException e) {
                        illegalArgumentException = e;
                        illegalArgumentException.printStackTrace();
                    }
                } catch (IllegalArgumentException e2) {
                    illegalArgumentException = e2;
                    availableBlocks = 0;
                    illegalArgumentException.printStackTrace();
                    count = this.mApplicationsAdapter.getCount();
                    j = 0;
                    for (i = 0; i < count; i++) {
                        ApplicationsState.AppEntry appEntry = this.mApplicationsAdapter.getAppEntry(i);
                        j += appEntry.codeSize + appEntry.dataSize;
                    }
                    jSumCacheSizes = availableBlocks + this.mApplicationsState.sumCacheSizes();
                    Log.e("formatSize", "freeStorage=" + jSumCacheSizes);
                    j2 = j;
                    if (text != null) {
                        this.mStorageChartLabel.setText(text);
                    }
                    if (blockCount > 0) {
                        j3 = blockCount - jSumCacheSizes;
                        float f = blockCount;
                        this.mColorBar.setRatios((j3 - j2) / f, j2 / f, jSumCacheSizes / f);
                        Log.e("formatSize", "usedStorage=" + j3);
                        if (this.mLastUsedStorage != j3) {
                            this.mLastUsedStorage = j3;
                            this.mUsedStorageText.setText(getResources().getString(R.string.service_foreground_processes, Utils.formatSize(j3, "ManageApplication updateStorageUsage mUsedStorageText")));
                        }
                        if (this.mLastFreeStorage != jSumCacheSizes) {
                            this.mLastFreeStorage = jSumCacheSizes;
                            this.mFreeStorageText.setText(getResources().getString(R.string.service_background_processes, Utils.formatSize(jSumCacheSizes, "ManageApplication updateStorageUsage mFreeStorageText")));
                            return;
                        }
                        return;
                    }
                    this.mColorBar.setRatios(0.0f, 0.0f, 0.0f);
                    if (this.mLastUsedStorage != -1) {
                        this.mLastUsedStorage = -1L;
                        this.mUsedStorageText.setText("");
                    }
                    if (this.mLastFreeStorage != -1) {
                        this.mLastFreeStorage = -1L;
                        this.mFreeStorageText.setText("");
                    }
                }
            } catch (IllegalArgumentException e3) {
                illegalArgumentException = e3;
                blockCount = 0;
            }
            count = this.mApplicationsAdapter.getCount();
            j = 0;
            while (i < count) {
                ApplicationsState.AppEntry appEntry2 = this.mApplicationsAdapter.getAppEntry(i);
                j += appEntry2.codeSize + appEntry2.dataSize;
            }
            jSumCacheSizes = availableBlocks + this.mApplicationsState.sumCacheSizes();
            Log.e("formatSize", "freeStorage=" + jSumCacheSizes);
            j2 = j;
        }
        if (text != null) {
            this.mStorageChartLabel.setText(text);
        }
        if (blockCount > 0) {
            j3 = blockCount - jSumCacheSizes;
            float f2 = blockCount;
            this.mColorBar.setRatios((j3 - j2) / f2, j2 / f2, jSumCacheSizes / f2);
            Log.e("formatSize", "usedStorage=" + j3);
            if (this.mLastUsedStorage != j3) {
                this.mLastUsedStorage = j3;
                this.mUsedStorageText.setText(getResources().getString(R.string.service_foreground_processes, Utils.formatSize(j3, "ManageApplication updateStorageUsage mUsedStorageText")));
            }
            if (this.mLastFreeStorage != jSumCacheSizes) {
                this.mLastFreeStorage = jSumCacheSizes;
                this.mFreeStorageText.setText(getResources().getString(R.string.service_background_processes, Utils.formatSize(jSumCacheSizes, "ManageApplication updateStorageUsage mFreeStorageText")));
                return;
            }
            return;
        }
        this.mColorBar.setRatios(0.0f, 0.0f, 0.0f);
        if (this.mLastUsedStorage != -1) {
            this.mLastUsedStorage = -1L;
            this.mUsedStorageText.setText("");
        }
        if (this.mLastFreeStorage != -1) {
            this.mLastFreeStorage = -1L;
            this.mFreeStorageText.setText("");
        }
    }

    private void selectView(int i) {
        boolean z = true;
        if (i == 1) {
            if (this.mResumedRunning) {
                this.mRunningProcessesView.doPause();
                this.mResumedRunning = false;
            }
            if (this.mCurView != i) {
                this.mRunningProcessesView.setVisibility(8);
                this.mListContainer.setVisibility(0);
                this.mLoadingContainer.setVisibility(8);
            }
            if (this.mActivityResumed) {
                this.mApplicationsAdapter.resume(this.mFilterApps, this.mSortOrder);
            }
        } else if (i == 2) {
            if (!this.mCreatedRunning) {
                this.mRunningProcessesView.doCreate(null, this.mNonConfigInstance);
                this.mCreatedRunning = true;
            }
            if (this.mActivityResumed && !this.mResumedRunning) {
                boolean zDoResume = this.mRunningProcessesView.doResume(this.mRunningProcessesAvail);
                this.mResumedRunning = true;
                z = zDoResume;
            }
            this.mApplicationsAdapter.pause();
            if (this.mCurView != i) {
                if (z) {
                    this.mRunningProcessesView.setVisibility(0);
                } else {
                    this.mLoadingContainer.setVisibility(0);
                }
                this.mListContainer.setVisibility(8);
            }
        }
        this.mCurView = i;
    }

    /* JADX WARN: Multi-variable type inference failed */
    void handleRunningProcessesAvail() {
        if (this.mCurView == 2) {
            this.mLoadingContainer.startAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_out));
            this.mRunningProcessesView.startAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_in));
            this.mRunningProcessesView.setVisibility(0);
            this.mLoadingContainer.setVisibility(8);
        }
    }

    public void showCurrentTab() {
        String currentTabTag = getTabHost().getCurrentTabTag();
        int i = 2;
        if (TAB_DOWNLOADED.equalsIgnoreCase(currentTabTag)) {
            i = 1;
        } else if (TAB_ALL.equalsIgnoreCase(currentTabTag)) {
            i = 0;
        } else if (!TAB_SDCARD.equalsIgnoreCase(currentTabTag)) {
            if (TAB_RUNNING.equalsIgnoreCase(currentTabTag)) {
                ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
                selectView(2);
                return;
            }
            return;
        }
        this.mFilterApps = i;
        selectView(1);
        updateStorageUsage();
        checkIsShowDeviceInfo();
    }

    @Override // android.widget.TabHost.OnTabChangeListener
    public void onTabChanged(String str) {
        showCurrentTab();
        Log.i(TAG, "tabId=" + str);
        checkIsShowDeviceInfo();
    }

    private void checkIsShowDeviceInfo() {
        if (mIsHideStorageInfo) {
            hideStorageInfo();
        } else {
            showDeviceInfO();
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        Log.i(TAG, "keyCode=" + i);
        if (this.myTabHasFocus) {
            switch (i) {
                case 21:
                    changeTab(true);
                    this.mTabHost.setCurrentTabByTag(getTagByIndex(this.myTabCurrentIndex));
                    break;
                case 22:
                    changeTab(false);
                    this.mTabHost.setCurrentTabByTag(getTagByIndex(this.myTabCurrentIndex));
                    break;
            }
        }
        return super.onKeyDown(i, keyEvent);
    }

    private void changeTab(boolean z) {
        if (z) {
            this.myTabCurrentIndex--;
            if (this.myTabCurrentIndex < 0) {
                this.myTabCurrentIndex = 0;
                return;
            }
            return;
        }
        this.myTabCurrentIndex++;
        if (this.myTabCurrentIndex > 3) {
            this.myTabCurrentIndex = 3;
        }
    }
}
