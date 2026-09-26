package com.android.nfx;

import android.graphics.Rect;

/* JADX INFO: loaded from: classes.dex */
class d {
    public int a;
    public Rect b;
    public int c;
    public int d;
    public int e;

    public d(NfxAccessibilityService nfxAccessibilityService, int i, Rect rect) {
        Rect rect2 = new Rect(0, 0, 0, 0);
        this.b = rect2;
        this.a = i;
        rect2.left = rect.left;
        rect2.top = rect.top;
        rect2.right = rect.right;
        rect2.bottom = rect.bottom;
    }
}
