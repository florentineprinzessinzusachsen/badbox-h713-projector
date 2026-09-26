package com.rk_itvui.settings.bluetooth;

import android.text.InputFilter;
import android.text.Spanned;

/* JADX INFO: loaded from: classes.dex */
class Utf8ByteLengthFilter implements InputFilter {
    private final int mMaxBytes;

    Utf8ByteLengthFilter(int i) {
        this.mMaxBytes = i;
    }

    @Override // android.text.InputFilter
    public CharSequence filter(CharSequence charSequence, int i, int i2, Spanned spanned, int i3, int i4) {
        int i5 = i;
        int i6 = 0;
        while (true) {
            int i7 = 1;
            if (i5 >= i2) {
                break;
            }
            char cCharAt = charSequence.charAt(i5);
            if (cCharAt >= 128) {
                i7 = cCharAt < 2048 ? 2 : 3;
            }
            i6 += i7;
            i5++;
        }
        int length = spanned.length();
        int i8 = 0;
        for (int i9 = 0; i9 < length; i9++) {
            if (i9 < i3 || i9 >= i4) {
                char cCharAt2 = spanned.charAt(i9);
                i8 += cCharAt2 < 128 ? 1 : cCharAt2 < 2048 ? 2 : 3;
            }
        }
        int i10 = this.mMaxBytes - i8;
        if (i10 <= 0) {
            return "";
        }
        if (i10 >= i6) {
            return null;
        }
        int i11 = i10;
        for (int i12 = i; i12 < i2; i12++) {
            char cCharAt3 = charSequence.charAt(i12);
            i11 -= cCharAt3 < 128 ? 1 : cCharAt3 < 2048 ? 2 : 3;
            if (i11 < 0) {
                return charSequence.subSequence(i, i12);
            }
        }
        return null;
    }
}
