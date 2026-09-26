package com.rk_itvui.settings.language;

import android.app.ActivityManagerNative;
import android.app.IActivityManager;
import android.app.backup.BackupManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.RemoteException;
import android.os.SystemProperties;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.ashd.settings.R;
import com.rk_itvui.settings.BaseActivity;
import com.rk_itvui.settings.WindowHelper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public class LanguageActivity extends BaseActivity implements AdapterView.OnItemClickListener {
    public static final String LANG_SET = "com.ashd.setting.language";
    public static final String LANG_SHOW_ALL = "com.ashd.setting.lang.showall";
    public static final String LANG_SHOW_CN = "com.ashd.setting.lang.showcn";
    static Object[][] mIconResourceIDs = {new Object[]{"zh_CN", Integer.valueOf(R.drawable.lang_zh_rcn)}, new Object[]{"zh_HK", Integer.valueOf(R.drawable.lang_zh_rhk)}, new Object[]{"ja_JP", Integer.valueOf(R.drawable.lang_ja_rjp)}, new Object[]{"en_CA", Integer.valueOf(R.drawable.lang_en_rca)}, new Object[]{"en_NZ", Integer.valueOf(R.drawable.lang_en_rnz)}, new Object[]{"ko_KR", Integer.valueOf(R.drawable.lang_ko_rkr)}, new Object[]{"en_US", Integer.valueOf(R.drawable.lang_en_rus)}};
    private String currentSelect;
    private ArrayList<String> mDisplayNames;
    private ListView mLangListView;
    private ArrayList<Language> mLocales;
    private String[] mSpecialLocaleCodes;
    private String[] mSpecialLocaleNames;
    private String[] mDisplayLocaleNames = {"zh-CN", "ar-EG", "ar-IL", "zh-TW", "en-US", "pt-PT", "vi-VN", "ms-MS", "ja-JP", "ko-KR", "de-DE", "ru-RU", "in-ID", "cs-CZ", "da-DK", "it-IT", "tl-PH", "af-ZA", "nl-NL", "pl-PL", "fr-FR", "fi-FI", "tl-TL", "el-GR", "nb-NO", "ms-MY", "th-TH"};
    int showAll = 0;
    private int selectIndex = 0;

    @Override // com.rk_itvui.settings.BaseActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.setting_language);
        this.currentSelect = SystemProperties.get(LANG_SET, (String) null);
        this.showAll = SystemProperties.getInt(LANG_SHOW_ALL, 0);
        this.showAll = 1;
        if (this.currentSelect == null) {
            try {
                Locale locale = ActivityManagerNative.getDefault().getConfiguration().locale;
                if (locale != null) {
                    this.currentSelect = locale.getLanguage() + "-" + locale.getCountry();
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        Log.e("LanguageActivity", "currentSelect::" + this.currentSelect);
        this.mLangListView = (ListView) findViewById(R.id.list_view);
        this.mLangListView.requestFocus();
        this.mLangListView.setOnItemClickListener(this);
        this.mDisplayNames = new ArrayList<>(Arrays.asList(this.mDisplayLocaleNames));
        buildLangListItem();
        this.mLangListView.setAdapter((ListAdapter) new LanguageArrayAdapter(this, this.mLocales));
        this.mLangListView.setSelection(this.selectIndex);
        ViewGroup.LayoutParams layoutParams = this.mLangListView.getLayoutParams();
        layoutParams.width = (int) (((double) WindowHelper.getWinWidth(this)) * 0.6d);
        layoutParams.height = (int) (((double) WindowHelper.getWinHeight(this)) * 0.8d);
        this.mLangListView.setLayoutParams(layoutParams);
    }

    private void add(List<String> list, String str) {
        if (list == null || list.contains(str)) {
            return;
        }
        list.add(str);
    }

    private void buildLangListItem() {
        int i;
        Log.e("LanguageActivity", "buildLangListItem");
        this.mSpecialLocaleCodes = getResources().getStringArray(R.array.lang_speciale_codes);
        this.mSpecialLocaleNames = getResources().getStringArray(R.array.lang_special_names);
        ArrayList arrayList = new ArrayList(Arrays.asList(getAssets().getLocales()));
        add(arrayList, "vi-VN");
        add(arrayList, "ja-JP");
        add(arrayList, "ko-KR");
        add(arrayList, "de-DE");
        add(arrayList, "ru-RU");
        add(arrayList, "in-ID");
        add(arrayList, "cs-CZ");
        add(arrayList, "da-DK");
        add(arrayList, "it-IT");
        add(arrayList, "nl-NL");
        add(arrayList, "pl-PL");
        add(arrayList, "fi-FI");
        add(arrayList, "el-GR");
        add(arrayList, "nb-NO");
        add(arrayList, "ms-MY");
        add(arrayList, "ar-EG");
        add(arrayList, "ar-IL");
        add(arrayList, "th-TH");
        add(arrayList, "tl-PH");
        add(arrayList, "fr-FR");
        Object[] array = arrayList.toArray();
        String[] strArr = new String[array.length];
        for (int i2 = 0; i2 < array.length; i2++) {
            strArr[i2] = array[i2].toString();
        }
        Arrays.sort(strArr);
        Language[] languageArr = new Language[strArr.length];
        int i3 = 0;
        int i4 = 0;
        for (String str : strArr) {
            int length = str.length();
            Log.e("LanguageActivity", "len=" + length + "::" + str);
            if (length == 5) {
                String strSubstring = str.substring(0, 2);
                Locale locale = new Locale(strSubstring, str.substring(3, 5));
                if (!str.equals("iw-IL")) {
                    if (i3 == 0) {
                        i = i3 + 1;
                        languageArr[i3] = new Language(toTitleCase(locale.getDisplayLanguage(locale)), locale);
                    } else {
                        int i5 = i3 - 1;
                        if (languageArr[i5].getLocale().getLanguage().equals(strSubstring)) {
                            languageArr[i5].setLabel(toTitleCase(getDisplayName(languageArr[i5].getLocale())));
                            i = i3 + 1;
                            languageArr[i3] = new Language(toTitleCase(getDisplayName(locale)), locale);
                        } else {
                            languageArr[i3] = new Language(str.equals("zz_ZZ") ? "Pseudo..." : toTitleCase(locale.getDisplayLanguage(locale)), locale);
                            i = i3 + 1;
                        }
                    }
                    setIconRes(languageArr[i - 1]);
                    Log.e("LanguageActivityAdd", i4 + "::" + str);
                    i4++;
                    i3 = i;
                }
            }
        }
        Language[] languageArr2 = new Language[i3];
        System.arraycopy(languageArr, 0, languageArr2, 0, i3);
        Arrays.sort(languageArr2);
        this.mLocales = new ArrayList<>(Arrays.asList(languageArr2));
        if (this.showAll == 0) {
            Iterator<Language> it = this.mLocales.iterator();
            while (it.hasNext()) {
                Language next = it.next();
                String country = next.getLocale().getCountry();
                String language = next.getLocale().getLanguage();
                Log.e("LanguageActivity", "getCountry=" + country + "::getLanguage=" + language);
                if (this.mDisplayNames.contains(language + "-" + country)) {
                    Log.e("LanguageActivity", "Save getCountry=" + country + "::getLanguage=" + language);
                } else {
                    it.remove();
                }
            }
        }
        if ("release".equals("rsd")) {
            int i6 = 0;
            while (i6 < this.mLocales.size()) {
                if (this.mLocales.get(i6).getLabel().contains("中文")) {
                    i6++;
                } else {
                    this.mLocales.remove(i6);
                }
            }
        }
        this.selectIndex = 0;
        for (int i7 = 0; i7 < this.mLocales.size(); i7++) {
            if ((this.mLocales.get(i7).getLocale().getLanguage() + "-" + this.mLocales.get(i7).getLocale().getCountry()).equals(this.currentSelect)) {
                this.selectIndex = i7;
                Log.e("LanguageActivity", "selectIndex=" + this.selectIndex);
            }
        }
    }

    private static String toTitleCase(String str) {
        if (str.length() == 0) {
            return str;
        }
        return Character.toUpperCase(str.charAt(0)) + str.substring(1);
    }

    private String getDisplayName(Locale locale) {
        String string = locale.toString();
        for (int i = 0; i < this.mSpecialLocaleCodes.length; i++) {
            if (this.mSpecialLocaleCodes[i].equals(string)) {
                return this.mSpecialLocaleNames[i];
            }
        }
        return locale.getDisplayName(locale);
    }

    private void setIconRes(Language language) {
        String string = language.getLocale().toString();
        for (Object[] objArr : mIconResourceIDs) {
            if (objArr[0].equals(string)) {
                language.setIconRes(((Integer) objArr[1]).intValue());
                return;
            }
        }
        language.setIconRes(R.drawable.lang_default);
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        try {
            Log.e("LanguageActivity", "position=" + i);
            IActivityManager iActivityManager = ActivityManagerNative.getDefault();
            Configuration configuration = iActivityManager.getConfiguration();
            Language language = this.mLocales.get(i);
            configuration.locale = language.getLocale();
            String str = language.getLocale().getLanguage() + "-" + language.getLocale().getCountry();
            Log.e("LanguageActivity", "lang=" + str);
            SystemProperties.set(LANG_SET, str);
            configuration.userSetLocale = true;
            iActivityManager.updatePersistentConfiguration(configuration);
            BackupManager.dataChanged("com.android.providers.settings");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        finish();
    }
}
