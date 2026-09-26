package com.rk_itvui.settings.language;

import android.app.ActivityManagerNative;
import android.app.IActivityManager;
import android.app.backup.BackupManager;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.RemoteException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.android.internal.app.AlertActivity;
import com.ashd.settings.R;
import java.text.Collator;
import java.util.Arrays;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public class LanguageSettingAlterDialogActivity extends AlertActivity {
    private Loc[] mLocales;
    private String[] mSpecialLocaleCodes;
    private String[] mSpecialLocaleNames;
    private ArrayAdapter<Loc> mAdapter = null;
    private final int magin = -5;
    private AdapterView.OnItemClickListener mListItemClister = new AdapterView.OnItemClickListener() { // from class: com.rk_itvui.settings.language.LanguageSettingAlterDialogActivity.1
        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            try {
                IActivityManager iActivityManager = ActivityManagerNative.getDefault();
                Configuration configuration = iActivityManager.getConfiguration();
                configuration.locale = LanguageSettingAlterDialogActivity.this.mLocales[i].locale;
                configuration.userSetLocale = true;
                iActivityManager.updateConfiguration(configuration);
                BackupManager.dataChanged("com.android.providers.settings");
            } catch (RemoteException unused) {
            }
            LanguageSettingAlterDialogActivity.this.finish();
        }
    };

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        loadLanguage();
        ListView listView = (ListView) ((LayoutInflater) getSystemService("layout_inflater")).inflate(R.layout.listview, (ViewGroup) null);
        listView.setAdapter((ListAdapter) this.mAdapter);
        this.mAlert.setView(listView, -5, -5, -5, -5);
        setupAlert();
        listView.requestFocus();
        listView.setSelection(0);
        listView.setOnItemClickListener(this.mListItemClister);
    }

    private static class Loc implements Comparable {
        static Collator sCollator = Collator.getInstance();
        String label;
        Locale locale;

        public Loc(String str, Locale locale) {
            this.label = str;
            this.locale = locale;
        }

        public String toString() {
            return this.label;
        }

        @Override // java.lang.Comparable
        public int compareTo(Object obj) {
            return sCollator.compare(this.label, ((Loc) obj).label);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void loadLanguage() {
        int i;
        this.mSpecialLocaleCodes = getResources().getStringArray(R.array.special_locale_codes);
        this.mSpecialLocaleNames = getResources().getStringArray(R.array.special_locale_names);
        String[] locales = getAssets().getLocales();
        Arrays.sort(locales);
        Loc[] locArr = new Loc[locales.length];
        int i2 = 0;
        for (String str : locales) {
            if (str.length() == 5) {
                String strSubstring = str.substring(0, 2);
                Locale locale = new Locale(strSubstring, str.substring(3, 5));
                if (i2 == 0) {
                    i = i2 + 1;
                    locArr[i2] = new Loc(toTitleCase(locale.getDisplayLanguage(locale)), locale);
                } else {
                    int i3 = i2 - 1;
                    if (locArr[i3].locale.getLanguage().equals(strSubstring)) {
                        locArr[i3].label = toTitleCase(getDisplayName(locArr[i3].locale));
                        i = i2 + 1;
                        locArr[i2] = new Loc(toTitleCase(getDisplayName(locale)), locale);
                    } else {
                        locArr[i2] = new Loc(str.equals("zz_ZZ") ? "Pseudo..." : toTitleCase(locale.getDisplayLanguage(locale)), locale);
                        i2++;
                    }
                }
                i2 = i;
            }
        }
        this.mLocales = new Loc[i2];
        for (int i4 = 0; i4 < i2; i4++) {
            this.mLocales[i4] = locArr[i4];
        }
        Arrays.sort(this.mLocales);
        this.mAdapter = new ArrayAdapter<>((Context) this, R.layout.locale_picker_item, R.id.locale, (Object[]) this.mLocales);
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
}
