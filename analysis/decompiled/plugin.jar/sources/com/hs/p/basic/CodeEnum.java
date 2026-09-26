package com.hs.p.basic;

/* JADX INFO: loaded from: /Users/ruben/projector-dump/downloads/plugin.jar */
public enum CodeEnum {
    OK(0, "OK"),
    FAIL(1, "unknown failed"),
    EXCEPTION(2, "remote server error"),
    ILLEGAL_PARAMETER(3, "illegal parameter");

    private int mCode;
    private String mMessage;

    CodeEnum(int i, String str) {
        this.mCode = i;
        this.mMessage = str;
    }

    public int code() {
        return this.mCode;
    }

    public boolean codeEquals(int i) {
        return this.mCode == i;
    }

    public String message() {
        return this.mMessage;
    }
}
