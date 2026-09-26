package com.rk_itvui.settings.model;

/* JADX INFO: loaded from: classes.dex */
public class ListItem {
    private String cls;
    private String detail;
    private int iconRes;
    private int itemId;
    private String title;

    public ListItem() {
    }

    public ListItem(int i, String str, String str2) {
        this.iconRes = i;
        this.title = str;
        this.detail = str2;
    }

    public int getItemId() {
        return this.itemId;
    }

    public void setItemId(int i) {
        this.itemId = i;
    }

    public int getIconRes() {
        return this.iconRes;
    }

    public void setIconRes(int i) {
        this.iconRes = i;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getDetail() {
        return this.detail;
    }

    public void setDetail(String str) {
        this.detail = str;
    }

    public String getCls() {
        return this.cls;
    }

    public void setCls(String str) {
        this.cls = str;
    }
}
