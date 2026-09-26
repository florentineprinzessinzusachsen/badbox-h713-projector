package com.android.nfx;

import java.util.Comparator;

/* JADX INFO: loaded from: classes.dex */
class b implements Comparator {
    b(NfxAccessibilityService nfxAccessibilityService) {
    }

    @Override // java.util.Comparator
    public int compare(Object obj, Object obj2) {
        return Integer.compare(Math.abs(((d) obj).e), Math.abs(((d) obj2).e));
    }
}
