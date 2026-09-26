package com.rk_itvui.settings.language;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import com.ashd.settings.R;
import com.rk_itvui.settings.BuildConfig;
import com.rk_itvui.settings.FullScreenPreferenceActivity;
import com.rk_itvui.settings.ScreenInformation;
import java.util.ArrayList;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public class LanguageInputmethod extends FullScreenPreferenceActivity implements AdapterView.OnItemClickListener {
    private KeyBoardSetting KeyBoard_local;
    private LanguageSetting language_local;
    ListView list;
    SimpleAdapter listItemAdapter;
    private Context mContext = this;
    private Handler mHandler = null;
    private int mId = -1;
    HashMap<String, Object> map_LanugageItem = new HashMap<>();
    HashMap<String, Object> map_InputMethodiItem = new HashMap<>();

    @Override // com.rk_itvui.settings.FullScreenPreferenceActivity, android.preference.PreferenceActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.language_inputmethod);
        addListView();
        if (BuildConfig.FLAVOR.contains(BuildConfig.FLAVOR)) {
            TextView textView = (TextView) findViewById(R.id.app_text);
            TextView textView2 = (TextView) findViewById(R.id.settings_line);
            textView.setCompoundDrawablesWithIntrinsicBounds(getDrawable(R.drawable.icon_ashd2), (Drawable) null, (Drawable) null, (Drawable) null);
            textView2.setBackgroundResource(R.drawable.settings_head_line_ashd2);
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        ScreenInformation.mScreenWidth = displayMetrics.widthPixels;
        ScreenInformation.mScreenHeight = displayMetrics.heightPixels;
        ScreenInformation.mDensityDpi = displayMetrics.densityDpi;
        ScreenInformation.mDpiRatio = ScreenInformation.mDefaultDpi / displayMetrics.densityDpi;
        if ("release".equals("sta")) {
            ((TextView) findViewById(R.id.tv_title)).setText(getString(R.string.input_metohd_select));
        }
    }

    public void addListView() {
        ArrayList arrayList = new ArrayList();
        this.KeyBoard_local = new KeyBoardSetting(this.mContext, this.mHandler, this.mId);
        this.language_local = new LanguageSetting(this.mContext, this.mHandler);
        if (!"release".equals("sta")) {
            this.map_LanugageItem.put("LanguageSettingIcon", Integer.valueOf(R.drawable.language_icon));
            this.map_LanugageItem.put("LanguageSettingItem", getString(R.string.language_seletor));
            this.map_LanugageItem.put("LanguageSettingStatus", this.language_local.getDefaultLanguageSetting());
            arrayList.add(this.map_LanugageItem);
        }
        this.map_InputMethodiItem.put("LanguageSettingIcon", Integer.valueOf(R.drawable.inputmethod_icon));
        this.map_InputMethodiItem.put("LanguageSettingItem", getString(R.string.input_method_selector));
        this.map_InputMethodiItem.put("LanguageSettingStatus", this.KeyBoard_local.getKeyBoardDefault());
        arrayList.add(this.map_InputMethodiItem);
        this.list = (ListView) findViewById(R.id.language_list);
        this.listItemAdapter = new SimpleAdapter(this, arrayList, R.layout.language_item, new String[]{"LanguageSettingIcon", "LanguageSettingItem", "LanguageSettingStatus"}, new int[]{R.id.LanguageSettingIcon, R.id.LanguageSettingItem, R.id.LanguageSettingStatus});
        this.list.setAdapter((ListAdapter) this.listItemAdapter);
        this.list.setOnItemClickListener(this);
        this.list.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.rk_itvui.settings.language.LanguageInputmethod.1
            @Override // android.view.View.OnFocusChangeListener
            public void onFocusChange(View view, boolean z) {
                if (z) {
                    return;
                }
                LanguageInputmethod.this.list.requestFocus();
            }
        });
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        switch (i) {
            case 0:
                Log.d("smj", "=====================================onclick0");
                if ("release".equals("sta")) {
                    startActivity(new Intent(this.mContext, (Class<?>) KeyBoardSettingAlterDialogActivity.class));
                } else {
                    startActivity(new Intent(this.mContext, (Class<?>) LanguageActivity.class));
                }
                break;
            case 1:
                Log.d("smj", "=====================================onclick1");
                startActivity(new Intent(this.mContext, (Class<?>) KeyBoardSettingAlterDialogActivity.class));
                break;
        }
    }

    @Override // android.app.Activity
    public void onResume() {
        super.onResume();
        this.map_InputMethodiItem.put("LanguageSettingStatus", this.KeyBoard_local.getKeyBoardDefault());
        this.listItemAdapter.notifyDataSetChanged();
        this.list.requestFocus();
        this.list.setSelection(0);
    }
}
