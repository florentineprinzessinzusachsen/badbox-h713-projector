package com.rk_itvui.settings.picture;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.ashd.settings.R;
import com.rk_itvui.settings.FullScreenActivity;
import com.rk_itvui.settings.ScreenInformation;
import com.rk_itvui.settings.developer.SettingItem;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class DisplaySettings extends FullScreenActivity implements ListController.CallBackListener {
    private static final String SLEEP_TIMEOUT_INDEX = "sleep.timeout.index";
    ListAdapter mAdapter;
    private String TAG = "DevelopmentSettings";
    private ListView mListView = null;
    private Map<String, ArrayList<SettingItem>> mMap = new HashMap();
    PictureModeController mCurrentController = new PictureModeController();

    @Override // com.rk_itvui.settings.picture.ListController.CallBackListener
    public void updateListController(ListController listController) {
    }

    @Override // com.rk_itvui.settings.picture.ListController.CallBackListener
    public void updateListPosition(int i) {
    }

    @Override // com.rk_itvui.settings.picture.ListController.CallBackListener
    public void updateTvView(boolean z, String str, String str2, String str3) {
    }

    @SuppressLint({"NewApi"})
    private void getScreenSize() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        ScreenInformation.mScreenWidth = displayMetrics.widthPixels;
        ScreenInformation.mScreenHeight = displayMetrics.heightPixels;
        ScreenInformation.mDensityDpi = displayMetrics.densityDpi;
        ScreenInformation.mDpiRatio = ScreenInformation.mDefaultDpi / displayMetrics.densityDpi;
    }

    private void createSpace() {
        ((TextView) findViewById(R.id.bottom_space)).setLayoutParams(new LinearLayout.LayoutParams(-1, (int) (ScreenInformation.mScreenWidth / 20.0f)));
    }

    @Override // com.rk_itvui.settings.FullScreenActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.display_mode_setting);
        this.mCurrentController = new PictureModeController();
        this.mCurrentController.init(this, this.mAdapter, null);
        this.mCurrentController.setCallBackListener(this);
        this.mAdapter = new ListAdapter(this, this.mCurrentController.defaultInitSettings());
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(this.mCurrentController);
        listView.setOnItemSelectedListener(this.mCurrentController);
        listView.setAdapter((android.widget.ListAdapter) this.mAdapter);
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (this.mCurrentController.onKeyDown(i, keyEvent)) {
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (this.mCurrentController.onKeyUp(i, keyEvent)) {
            return true;
        }
        return super.onKeyUp(i, keyEvent);
    }

    @Override // android.app.Activity
    protected void onPause() {
        this.mCurrentController.onPause();
        super.onPause();
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        this.mCurrentController.onResume();
    }
}
