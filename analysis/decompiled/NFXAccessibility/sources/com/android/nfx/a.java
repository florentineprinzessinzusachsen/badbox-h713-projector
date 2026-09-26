package com.android.nfx;

import java.util.Comparator;

/* JADX INFO: loaded from: classes.dex */
class a implements Comparator {
    a(NfxAccessibilityService nfxAccessibilityService) {
    }

    @Override // java.util.Comparator
    public int compare(Object obj, Object obj2) {
        return Integer.compare(((d) obj2).d, ((d) obj).d);
    }
}
