package com.rk_itvui.settings.dialog;

import android.app.ActivityManager;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.SystemClock;
import android.os.SystemProperties;
import android.text.format.DateUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.ashd.settings.R;
import com.rk_itvui.settings.Utils;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public class RunningProcessesView extends FrameLayout implements AdapterView.OnItemClickListener, AbsListView.RecyclerListener, RunningState.OnRefreshUiListener {
    static final long PAGE_SIZE = 4096;
    private static final boolean mIsHideStorageInfo = SystemProperties.getBoolean("persist.sys.hide.capacity", false);
    long SECONDARY_SERVER_MEM;
    final HashMap<View, ActiveItem> mActiveItems;
    ServiceListAdapter mAdapter;
    ActivityManager mAm;
    TextView mBackgroundProcessText;
    byte[] mBuffer;
    StringBuilder mBuilder;
    LinearColorBar mColorBar;
    Dialog mCurDialog;
    RunningState.BaseItem mCurSelected;
    Runnable mDataAvail;
    TextView mForegroundProcessText;
    long mLastAvailMemory;
    long mLastBackgroundProcessMemory;
    long mLastForegroundProcessMemory;
    int mLastNumBackgroundProcesses;
    int mLastNumForegroundProcesses;
    int mLastNumServiceProcesses;
    long mLastServiceProcessMemory;
    ListView mListView;
    RunningState mState;

    public Object doRetainNonConfigurationInstance() {
        return null;
    }

    public static class ActiveItem {
        long mFirstRunTime;
        ViewHolder mHolder;
        RunningState.BaseItem mItem;
        View mRootView;
        ActivityManager.RunningServiceInfo mService;
        boolean mSetBackground;

        void updateTime(Context context, StringBuilder sb) {
            TextView textView;
            if (this.mItem instanceof RunningState.ServiceItem) {
                textView = this.mHolder.size;
            } else {
                String str = this.mItem.mSizeStr != null ? this.mItem.mSizeStr : "";
                if (!str.equals(this.mItem.mCurSizeStr)) {
                    this.mItem.mCurSizeStr = str;
                    this.mHolder.size.setText(str);
                }
                if (this.mItem.mBackground) {
                    if (!this.mSetBackground) {
                        this.mSetBackground = true;
                        this.mHolder.uptime.setText("");
                    }
                } else if (this.mItem instanceof RunningState.MergedItem) {
                    textView = this.mHolder.uptime;
                }
                textView = null;
            }
            if (textView != null) {
                boolean z = false;
                this.mSetBackground = false;
                if (this.mFirstRunTime >= 0) {
                    textView.setText(DateUtils.formatElapsedTime(sb, (SystemClock.elapsedRealtime() - this.mFirstRunTime) / 1000));
                    return;
                }
                if ((this.mItem instanceof RunningState.MergedItem) && ((RunningState.MergedItem) this.mItem).mServices.size() > 0) {
                    z = true;
                }
                if (z) {
                    textView.setText(context.getResources().getText(R.string.service_restarting));
                } else {
                    textView.setText("");
                }
            }
        }
    }

    public static class ViewHolder {
        public TextView description;
        public ImageView icon;
        public TextView name;
        public View rootView;
        public TextView size;
        public TextView uptime;

        public ViewHolder(View view) {
            this.rootView = view;
            this.icon = (ImageView) view.findViewById(R.id.icon);
            this.name = (TextView) view.findViewById(R.id.name);
            this.description = (TextView) view.findViewById(R.id.description);
            this.size = (TextView) view.findViewById(R.id.size);
            this.uptime = (TextView) view.findViewById(R.id.uptime);
            view.setTag(this);
        }

        public ActiveItem bind(RunningState runningState, RunningState.BaseItem baseItem, StringBuilder sb) {
            ActiveItem activeItem;
            synchronized (runningState.mLock) {
                PackageManager packageManager = this.rootView.getContext().getPackageManager();
                if (baseItem.mPackageInfo == null && (baseItem instanceof RunningState.MergedItem)) {
                    ((RunningState.MergedItem) baseItem).mProcess.ensureLabel(packageManager);
                    baseItem.mPackageInfo = ((RunningState.MergedItem) baseItem).mProcess.mPackageInfo;
                    baseItem.mDisplayLabel = ((RunningState.MergedItem) baseItem).mProcess.mDisplayLabel;
                }
                this.name.setText(baseItem.mDisplayLabel);
                activeItem = new ActiveItem();
                activeItem.mRootView = this.rootView;
                activeItem.mItem = baseItem;
                activeItem.mHolder = this;
                activeItem.mFirstRunTime = baseItem.mActiveSince;
                if (baseItem.mBackground) {
                    this.description.setText(this.rootView.getContext().getText(R.string.cached));
                } else {
                    this.description.setText(baseItem.mDescription);
                }
                baseItem.mCurSizeStr = null;
                if (baseItem.mPackageInfo != null) {
                    this.icon.setImageDrawable(baseItem.mPackageInfo.loadIcon(packageManager));
                }
                this.icon.setVisibility(0);
                activeItem.updateTime(this.rootView.getContext(), sb);
            }
            return activeItem;
        }
    }

    static class TimeTicker extends TextView {
        public TimeTicker(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }
    }

    class ServiceListAdapter extends BaseAdapter {
        final LayoutInflater mInflater;
        ArrayList<RunningState.MergedItem> mItems;
        boolean mShowBackground;
        final RunningState mState;

        @Override // android.widget.BaseAdapter, android.widget.ListAdapter
        public boolean areAllItemsEnabled() {
            return false;
        }

        @Override // android.widget.BaseAdapter, android.widget.Adapter
        public boolean hasStableIds() {
            return true;
        }

        ServiceListAdapter(RunningState runningState) {
            this.mState = runningState;
            this.mInflater = (LayoutInflater) RunningProcessesView.this.getContext().getSystemService("layout_inflater");
            refreshItems();
        }

        void setShowBackground(boolean z) {
            if (this.mShowBackground != z) {
                this.mShowBackground = z;
                this.mState.setWatchingBackgroundItems(z);
                refreshItems();
                notifyDataSetChanged();
                RunningProcessesView.this.mColorBar.setShowingGreen(this.mShowBackground);
            }
        }

        boolean getShowBackground() {
            return this.mShowBackground;
        }

        void refreshItems() {
            ArrayList<RunningState.MergedItem> currentMergedItems;
            if (this.mShowBackground) {
                currentMergedItems = this.mState.getCurrentBackgroundItems();
            } else {
                currentMergedItems = this.mState.getCurrentMergedItems();
            }
            if (this.mItems != currentMergedItems) {
                this.mItems = currentMergedItems;
            }
            if (this.mItems == null) {
                this.mItems = new ArrayList<>();
            }
        }

        @Override // android.widget.Adapter
        public int getCount() {
            return this.mItems.size();
        }

        @Override // android.widget.BaseAdapter, android.widget.Adapter
        public boolean isEmpty() {
            return this.mState.hasData() && this.mItems.size() == 0;
        }

        @Override // android.widget.Adapter
        public Object getItem(int i) {
            return this.mItems.get(i);
        }

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return this.mItems.get(i).hashCode();
        }

        @Override // android.widget.BaseAdapter, android.widget.ListAdapter
        public boolean isEnabled(int i) {
            return !this.mItems.get(i).mIsProcess;
        }

        @Override // android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = newView(viewGroup);
            }
            bindView(view, i);
            return view;
        }

        public View newView(ViewGroup viewGroup) {
            View viewInflate = this.mInflater.inflate(R.layout.running_processes_item, viewGroup, false);
            new ViewHolder(viewInflate);
            return viewInflate;
        }

        public void bindView(View view, int i) {
            synchronized (this.mState.mLock) {
                if (i >= this.mItems.size()) {
                    return;
                }
                RunningProcessesView.this.mActiveItems.put(view, ((ViewHolder) view.getTag()).bind(this.mState, this.mItems.get(i), RunningProcessesView.this.mBuilder));
            }
        }
    }

    private boolean matchText(byte[] bArr, int i, String str) {
        int length = str.length();
        if (i + length >= bArr.length) {
            return false;
        }
        for (int i2 = 0; i2 < length; i2++) {
            if (bArr[i + i2] != str.charAt(i2)) {
                return false;
            }
        }
        return true;
    }

    private long extractMemValue(byte[] bArr, int i) {
        while (i < bArr.length && bArr[i] != 10) {
            if (bArr[i] >= 48 && bArr[i] <= 57) {
                int i2 = i + 1;
                while (i2 < bArr.length && bArr[i2] >= 48 && bArr[i2] <= 57) {
                    i2++;
                }
                return ((long) Integer.parseInt(new String(bArr, 0, i, i2 - i))) * 1024;
            }
            i++;
        }
        return 0L;
    }

    private long readAvailMem() {
        try {
            FileInputStream fileInputStream = new FileInputStream("/proc/meminfo");
            int i = fileInputStream.read(this.mBuffer);
            fileInputStream.close();
            int length = this.mBuffer.length;
            int i2 = 0;
            long jExtractMemValue = 0;
            long jExtractMemValue2 = 0;
            while (i2 < i && (jExtractMemValue == 0 || jExtractMemValue2 == 0)) {
                if (matchText(this.mBuffer, i2, "MemFree")) {
                    i2 += 7;
                    jExtractMemValue = extractMemValue(this.mBuffer, i2);
                } else if (matchText(this.mBuffer, i2, "Cached")) {
                    i2 += 6;
                    jExtractMemValue2 = extractMemValue(this.mBuffer, i2);
                }
                while (i2 < length && this.mBuffer[i2] != 10) {
                    i2++;
                }
                i2++;
            }
            return jExtractMemValue + jExtractMemValue2;
        } catch (FileNotFoundException | IOException unused) {
            return 0L;
        }
    }

    void refreshUi(boolean z) {
        if (z) {
            ServiceListAdapter serviceListAdapter = (ServiceListAdapter) this.mListView.getAdapter();
            serviceListAdapter.refreshItems();
            serviceListAdapter.notifyDataSetChanged();
        }
        if (this.mDataAvail != null) {
            this.mDataAvail.run();
            this.mDataAvail = null;
        }
        long availMem = readAvailMem() - this.SECONDARY_SERVER_MEM;
        if (availMem < 0) {
            availMem = 0;
        }
        synchronized (this.mState.mLock) {
            if (this.mLastNumBackgroundProcesses != this.mState.mNumBackgroundProcesses || this.mLastBackgroundProcessMemory != this.mState.mBackgroundProcessMemory || this.mLastAvailMemory != availMem) {
                this.mLastNumBackgroundProcesses = this.mState.mNumBackgroundProcesses;
                this.mLastBackgroundProcessMemory = this.mState.mBackgroundProcessMemory;
                this.mLastAvailMemory = availMem;
                ActivityManager activityManager = (ActivityManager) getContext().getSystemService("activity");
                ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
                activityManager.getMemoryInfo(memoryInfo);
                this.mBackgroundProcessText.setText(getResources().getString(R.string.service_background_processes, Utils.formatSize(memoryInfo.availMem, "RunningProcessesView refreshUi availMem")));
            }
            if (this.mLastNumForegroundProcesses != this.mState.mNumForegroundProcesses || this.mLastForegroundProcessMemory != this.mState.mForegroundProcessMemory || this.mLastNumServiceProcesses != this.mState.mNumServiceProcesses || this.mLastServiceProcessMemory != this.mState.mServiceProcessMemory) {
                this.mLastNumForegroundProcesses = this.mState.mNumForegroundProcesses;
                this.mLastForegroundProcessMemory = this.mState.mForegroundProcessMemory;
                this.mLastNumServiceProcesses = this.mState.mNumServiceProcesses;
                this.mLastServiceProcessMemory = this.mState.mServiceProcessMemory;
                ActivityManager activityManager2 = (ActivityManager) getContext().getSystemService("activity");
                ActivityManager.MemoryInfo memoryInfo2 = new ActivityManager.MemoryInfo();
                activityManager2.getMemoryInfo(memoryInfo2);
                this.mForegroundProcessText.setText(getResources().getString(R.string.service_foreground_processes, Utils.formatSize(memoryInfo2.totalMem - memoryInfo2.availMem, "RunningprocessesView refreshUi mForegroundProcessText")));
            }
            float f = availMem + this.mLastBackgroundProcessMemory + this.mLastForegroundProcessMemory + this.mLastServiceProcessMemory;
            this.mColorBar.setRatios(this.mLastForegroundProcessMemory / f, this.mLastServiceProcessMemory / f, this.mLastBackgroundProcessMemory / f);
        }
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        RunningState.MergedItem mergedItem = (RunningState.MergedItem) ((ListView) adapterView).getAdapter().getItem(i);
        this.mCurSelected = mergedItem;
        Intent intent = new Intent();
        intent.putExtra("uid", mergedItem.mProcess.mUid);
        intent.putExtra("process", mergedItem.mProcess.mProcessName);
        intent.putExtra("background", this.mAdapter.mShowBackground);
        intent.setClass(getContext(), RunningServiceDetails.class);
        getContext().startActivity(intent);
    }

    @Override // android.widget.AbsListView.RecyclerListener
    public void onMovedToScrapHeap(View view) {
        this.mActiveItems.remove(view);
    }

    public RunningProcessesView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mActiveItems = new HashMap<>();
        this.mBuilder = new StringBuilder(128);
        this.mLastNumBackgroundProcesses = -1;
        this.mLastNumForegroundProcesses = -1;
        this.mLastNumServiceProcesses = -1;
        this.mLastBackgroundProcessMemory = -1L;
        this.mLastForegroundProcessMemory = -1L;
        this.mLastServiceProcessMemory = -1L;
        this.mLastAvailMemory = -1L;
        this.mBuffer = new byte[1024];
    }

    public void doCreate(Bundle bundle, Object obj) {
        this.mAm = (ActivityManager) getContext().getSystemService("activity");
        this.mState = RunningState.getInstance(getContext());
        ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.running_processes_view, this);
        this.mListView = (ListView) findViewById(android.R.id.list);
        View viewFindViewById = findViewById(android.R.id.empty);
        if (viewFindViewById != null) {
            this.mListView.setEmptyView(viewFindViewById);
        }
        this.mListView.setOnItemClickListener(this);
        this.mListView.setRecyclerListener(this);
        this.mAdapter = new ServiceListAdapter(this.mState);
        this.mListView.setAdapter((ListAdapter) this.mAdapter);
        this.mColorBar = (LinearColorBar) findViewById(R.id.color_bar);
        if (mIsHideStorageInfo) {
            this.mColorBar.setVisibility(4);
        } else {
            this.mColorBar.setVisibility(0);
        }
        this.mBackgroundProcessText = (TextView) findViewById(R.id.backgroundText);
        this.mBackgroundProcessText.setOnClickListener(new View.OnClickListener() { // from class: com.rk_itvui.settings.dialog.RunningProcessesView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                RunningProcessesView.this.mAdapter.setShowBackground(true);
            }
        });
        this.mForegroundProcessText = (TextView) findViewById(R.id.foregroundText);
        this.mForegroundProcessText.setOnClickListener(new View.OnClickListener() { // from class: com.rk_itvui.settings.dialog.RunningProcessesView.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                RunningProcessesView.this.mAdapter.setShowBackground(false);
            }
        });
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        this.mAm.getMemoryInfo(memoryInfo);
        this.SECONDARY_SERVER_MEM = memoryInfo.secondaryServerThreshold;
    }

    public void doPause() {
        this.mState.pause();
        this.mDataAvail = null;
    }

    public boolean doResume(Runnable runnable) {
        this.mState.resume(this);
        if (this.mState.hasData()) {
            refreshUi(true);
            return true;
        }
        this.mDataAvail = runnable;
        return false;
    }

    void updateTimes() {
        Iterator<ActiveItem> it = this.mActiveItems.values().iterator();
        while (it.hasNext()) {
            ActiveItem next = it.next();
            if (next.mRootView.getWindowToken() == null) {
                it.remove();
            } else {
                next.updateTime(getContext(), this.mBuilder);
            }
        }
    }

    @Override // com.rk_itvui.settings.dialog.RunningState.OnRefreshUiListener
    public void onRefreshUi(int i) {
        switch (i) {
            case 0:
                updateTimes();
                break;
            case 1:
                refreshUi(false);
                updateTimes();
                break;
            case 2:
                refreshUi(true);
                updateTimes();
                break;
        }
    }
}
