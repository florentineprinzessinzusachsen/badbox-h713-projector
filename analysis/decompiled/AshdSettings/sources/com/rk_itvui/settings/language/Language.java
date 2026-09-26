package com.rk_itvui.settings.language;

import java.text.Collator;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public class Language implements Comparable<Language> {
    static Collator sCollator = Collator.getInstance();
    private int iconRes;
    private String label;
    private Locale locale;

    public Language(String str, Locale locale) {
        this.label = str;
        this.locale = locale;
    }

    public String toString() {
        return this.label;
    }

    @Override // java.lang.Comparable
    public int compareTo(Language language) {
        return sCollator.compare(this.label, language.label);
    }

    public int getIconRes() {
        return this.iconRes;
    }

    public void setIconRes(int i) {
        this.iconRes = i;
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String str) {
        this.label = str;
    }

    public Locale getLocale() {
        return this.locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }
}
