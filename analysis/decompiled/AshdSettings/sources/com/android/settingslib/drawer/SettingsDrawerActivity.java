package com.android.settingslib.drawer;

import android.R;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.res.TypedArray;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.UserHandle;
import android.os.UserManager;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.text.TextUtils;
import android.util.ArraySet;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toolbar;
import com.android.settingslib.applications.InterestingConfigChanges;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class SettingsDrawerActivity extends Activity {
    protected static final boolean DEBUG_TIMING = false;
    public static final String EXTRA_SHOW_MENU = "show_drawer_menu";
    private static InterestingConfigChanges sConfigTracker;
    private static List<DashboardCategory> sDashboardCategories;
    private static HashMap<Pair<String, String>, Tile> sTileCache;
    private FrameLayout mContentHeaderContainer;
    private SettingsDrawerAdapter mDrawerAdapter;
    private DrawerLayout mDrawerLayout;
    private boolean mShowingMenu;
    private UserManager mUserManager;
    private static final String TAG = "SettingsDrawerActivity";
    private static final boolean DEBUG = Log.isLoggable(TAG, 3);
    private static ArraySet<ComponentName> sTileBlacklist = new ArraySet<>();
    private final PackageReceiver mPackageReceiver = new PackageReceiver();
    private final List<CategoryListener> mCategoryListeners = new ArrayList();

    public interface CategoryListener {
        void onCategoriesChanged();
    }

    @Override // android.app.Activity
    protected void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        System.currentTimeMillis();
        TypedArray typedArrayObtainStyledAttributes = getTheme().obtainStyledAttributes(R.styleable.Theme);
        if (!typedArrayObtainStyledAttributes.getBoolean(38, false)) {
            getWindow().addFlags(Integer.MIN_VALUE);
            getWindow().addFlags(67108864);
            requestWindowFeature(1);
        }
        super.setContentView(com.android.settingslib.R.layout.settings_with_drawer);
        this.mContentHeaderContainer = (FrameLayout) findViewById(com.android.settingslib.R.id.content_header_container);
        if (this.mContentHeaderContainer == null) {
            super.setContentView(com.android.settingslib.R.layout.settings_with_drawer_mid);
            this.mContentHeaderContainer = (FrameLayout) findViewById(com.android.settingslib.R.id.content_header_container);
            Log.e(TAG, "is box ??? mContentHeaderContainer is null!!!");
        }
        this.mDrawerLayout = (DrawerLayout) findViewById(com.android.settingslib.R.id.drawer_layout);
        if (this.mDrawerLayout == null) {
            return;
        }
        Toolbar toolbar = (Toolbar) findViewById(com.android.settingslib.R.id.action_bar);
        if (typedArrayObtainStyledAttributes.getBoolean(38, false)) {
            toolbar.setVisibility(8);
            this.mDrawerLayout.setDrawerLockMode(1);
            this.mDrawerLayout = null;
            return;
        }
        getDashboardCategories();
        setActionBar(toolbar);
        this.mDrawerAdapter = new SettingsDrawerAdapter(this);
        ListView listView = (ListView) findViewById(com.android.settingslib.R.id.left_drawer);
        listView.setAdapter((ListAdapter) this.mDrawerAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.android.settingslib.drawer.SettingsDrawerActivity.1
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                SettingsDrawerActivity.this.onTileClicked(SettingsDrawerActivity.this.mDrawerAdapter.getTile(i));
            }
        });
        this.mUserManager = UserManager.get(this);
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (this.mShowingMenu && this.mDrawerLayout != null && menuItem.getItemId() == 16908332 && this.mDrawerAdapter.getCount() != 0) {
            openDrawer();
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        if (this.mDrawerLayout != null) {
            IntentFilter intentFilter = new IntentFilter("android.intent.action.PACKAGE_ADDED");
            intentFilter.addAction("android.intent.action.PACKAGE_REMOVED");
            intentFilter.addAction("android.intent.action.PACKAGE_CHANGED");
            intentFilter.addAction("android.intent.action.PACKAGE_REPLACED");
            intentFilter.addDataScheme("package");
            registerReceiver(this.mPackageReceiver, intentFilter);
            new CategoriesUpdater().execute(new Void[0]);
        }
        Intent intent = getIntent();
        if (intent != null) {
            if (intent.hasExtra(EXTRA_SHOW_MENU)) {
                if (intent.getBooleanExtra(EXTRA_SHOW_MENU, false)) {
                    showMenuIcon();
                }
            } else if (isTopLevelTile(intent)) {
                showMenuIcon();
            }
        }
    }

    @Override // android.app.Activity
    protected void onPause() {
        if (this.mDrawerLayout != null) {
            unregisterReceiver(this.mPackageReceiver);
        }
        super.onPause();
    }

    private boolean isTopLevelTile(Intent intent) {
        ComponentName component = intent.getComponent();
        if (component == null) {
            return false;
        }
        Iterator<DashboardCategory> it = getDashboardCategories().iterator();
        while (it.hasNext()) {
            for (Tile tile : it.next().tiles) {
                if (TextUtils.equals(tile.intent.getComponent().getClassName(), component.getClassName())) {
                    if (!DEBUG) {
                        return true;
                    }
                    Log.d(TAG, "intent is for top level tile: " + ((Object) tile.title));
                    return true;
                }
            }
        }
        if (DEBUG) {
            Log.d(TAG, "Intent is not for top level settings " + intent);
        }
        return false;
    }

    public void addCategoryListener(CategoryListener categoryListener) {
        this.mCategoryListeners.add(categoryListener);
    }

    public void remCategoryListener(CategoryListener categoryListener) {
        this.mCategoryListeners.remove(categoryListener);
    }

    public void setIsDrawerPresent(boolean z) {
        if (z) {
            this.mDrawerLayout = (DrawerLayout) findViewById(com.android.settingslib.R.id.drawer_layout);
            updateDrawer();
        } else if (this.mDrawerLayout != null) {
            this.mDrawerLayout.setDrawerLockMode(1);
            this.mDrawerLayout = null;
        }
    }

    public void openDrawer() {
        if (this.mDrawerLayout != null) {
            this.mDrawerLayout.openDrawer(GravityCompat.START);
        }
    }

    public void closeDrawer() {
        if (this.mDrawerLayout != null) {
            this.mDrawerLayout.closeDrawers();
        }
    }

    public void setContentHeaderView(View view) {
        this.mContentHeaderContainer.removeAllViews();
        if (view != null) {
            this.mContentHeaderContainer.addView(view);
        }
    }

    @Override // android.app.Activity
    public void setContentView(@LayoutRes int i) {
        ViewGroup viewGroup = (ViewGroup) findViewById(com.android.settingslib.R.id.content_frame);
        if (viewGroup != null) {
            viewGroup.removeAllViews();
        }
        LayoutInflater.from(this).inflate(i, viewGroup);
    }

    @Override // android.app.Activity
    public void setContentView(View view) {
        ((ViewGroup) findViewById(com.android.settingslib.R.id.content_frame)).addView(view);
    }

    @Override // android.app.Activity
    public void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        ((ViewGroup) findViewById(com.android.settingslib.R.id.content_frame)).addView(view, layoutParams);
    }

    public void updateDrawer() {
        if (this.mDrawerLayout == null) {
            return;
        }
        this.mDrawerAdapter.updateCategories();
        if (this.mDrawerAdapter.getCount() != 0) {
            this.mDrawerLayout.setDrawerLockMode(0);
        } else {
            this.mDrawerLayout.setDrawerLockMode(1);
        }
    }

    public void showMenuIcon() {
        this.mShowingMenu = true;
        getActionBar().setHomeAsUpIndicator(com.android.settingslib.R.drawable.ic_menu);
        getActionBar().setHomeActionContentDescription(com.android.settingslib.R.string.content_description_menu_button);
        getActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public List<DashboardCategory> getDashboardCategories() {
        if (sDashboardCategories == null) {
            sTileCache = new HashMap<>();
            sConfigTracker = new InterestingConfigChanges();
            sConfigTracker.applyNewConfig(getResources());
            sDashboardCategories = TileUtils.getCategories(this, sTileCache);
        }
        return sDashboardCategories;
    }

    protected void onCategoriesChanged() {
        updateDrawer();
        int size = this.mCategoryListeners.size();
        for (int i = 0; i < size; i++) {
            this.mCategoryListeners.get(i).onCategoriesChanged();
        }
    }

    public boolean openTile(Tile tile) {
        closeDrawer();
        if (tile == null) {
            startActivity(new Intent("android.settings.SETTINGS").addFlags(32768));
            return true;
        }
        try {
            updateUserHandlesIfNeeded(tile);
            int size = tile.userHandle.size();
            if (size > 1) {
                ProfileSelectDialog.show(getFragmentManager(), tile);
                return false;
            }
            if (size == 1) {
                tile.intent.putExtra(EXTRA_SHOW_MENU, true);
                tile.intent.addFlags(32768);
                startActivityAsUser(tile.intent, tile.userHandle.get(0));
            } else {
                tile.intent.putExtra(EXTRA_SHOW_MENU, true);
                tile.intent.addFlags(32768);
                startActivity(tile.intent);
            }
            return true;
        } catch (ActivityNotFoundException e) {
            Log.w(TAG, "Couldn't find tile " + tile.intent, e);
        }
    }

    private void updateUserHandlesIfNeeded(Tile tile) {
        ArrayList<UserHandle> arrayList = tile.userHandle;
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            if (this.mUserManager.getUserInfo(arrayList.get(size).getIdentifier()) == null) {
                if (DEBUG) {
                    Log.d(TAG, "Delete the user: " + arrayList.get(size).getIdentifier());
                }
                arrayList.remove(size);
            }
        }
    }

    protected void onTileClicked(Tile tile) {
        if (openTile(tile)) {
            finish();
        }
    }

    public HashMap<Pair<String, String>, Tile> getTileCache() {
        if (sTileCache == null) {
            getDashboardCategories();
        }
        return sTileCache;
    }

    public void onProfileTileOpen() {
        finish();
    }

    public void setTileEnabled(ComponentName componentName, boolean z) {
        PackageManager packageManager = getPackageManager();
        int componentEnabledSetting = packageManager.getComponentEnabledSetting(componentName);
        if ((componentEnabledSetting == 1) != z || componentEnabledSetting == 0) {
            if (z) {
                sTileBlacklist.remove(componentName);
            } else {
                sTileBlacklist.add(componentName);
            }
            packageManager.setComponentEnabledSetting(componentName, z ? 1 : 2, 1);
            new CategoriesUpdater().execute(new Void[0]);
        }
    }

    private class CategoriesUpdater extends AsyncTask<Void, Void, List<DashboardCategory>> {
        private CategoriesUpdater() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public List<DashboardCategory> doInBackground(Void... voidArr) {
            if (SettingsDrawerActivity.sConfigTracker.applyNewConfig(SettingsDrawerActivity.this.getResources())) {
                SettingsDrawerActivity.sTileCache.clear();
            }
            return TileUtils.getCategories(SettingsDrawerActivity.this, SettingsDrawerActivity.sTileCache);
        }

        @Override // android.os.AsyncTask
        protected void onPreExecute() {
            if (SettingsDrawerActivity.sConfigTracker == null || SettingsDrawerActivity.sTileCache == null) {
                SettingsDrawerActivity.this.getDashboardCategories();
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(List<DashboardCategory> list) {
            for (int i = 0; i < list.size(); i++) {
                DashboardCategory dashboardCategory = list.get(i);
                int i2 = 0;
                while (i2 < dashboardCategory.tiles.size()) {
                    if (SettingsDrawerActivity.sTileBlacklist.contains(dashboardCategory.tiles.get(i2).intent.getComponent())) {
                        dashboardCategory.tiles.remove(i2);
                        i2--;
                    }
                    i2++;
                }
            }
            List unused = SettingsDrawerActivity.sDashboardCategories = list;
            SettingsDrawerActivity.this.onCategoriesChanged();
        }
    }

    private class PackageReceiver extends BroadcastReceiver {
        private PackageReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            new CategoriesUpdater().execute(new Void[0]);
        }
    }
}
