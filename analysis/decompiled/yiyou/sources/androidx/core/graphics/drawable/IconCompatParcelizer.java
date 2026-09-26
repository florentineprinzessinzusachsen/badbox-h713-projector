package androidx.core.graphics.drawable;

import android.content.res.ColorStateList;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class IconCompatParcelizer {
    public static IconCompat read(androidx.versionedparcelable.a aVar) {
        IconCompat iconCompat = new IconCompat();
        iconCompat.f1113a = aVar.a(iconCompat.f1113a, 1);
        iconCompat.f1115c = aVar.a(iconCompat.f1115c, 2);
        iconCompat.f1116d = aVar.a(iconCompat.f1116d, 3);
        iconCompat.f1117e = aVar.a(iconCompat.f1117e, 4);
        iconCompat.f1118f = aVar.a(iconCompat.f1118f, 5);
        iconCompat.g = (ColorStateList) aVar.a(iconCompat.g, 6);
        iconCompat.i = aVar.a(iconCompat.i, 7);
        iconCompat.c();
        return iconCompat;
    }

    public static void write(IconCompat iconCompat, androidx.versionedparcelable.a aVar) {
        aVar.a(true, true);
        iconCompat.a(aVar.c());
        int i = iconCompat.f1113a;
        if (-1 != i) {
            aVar.b(i, 1);
        }
        byte[] bArr = iconCompat.f1115c;
        if (bArr != null) {
            aVar.b(bArr, 2);
        }
        Parcelable parcelable = iconCompat.f1116d;
        if (parcelable != null) {
            aVar.b(parcelable, 3);
        }
        int i2 = iconCompat.f1117e;
        if (i2 != 0) {
            aVar.b(i2, 4);
        }
        int i3 = iconCompat.f1118f;
        if (i3 != 0) {
            aVar.b(i3, 5);
        }
        ColorStateList colorStateList = iconCompat.g;
        if (colorStateList != null) {
            aVar.b(colorStateList, 6);
        }
        String str = iconCompat.i;
        if (str != null) {
            aVar.b(str, 7);
        }
    }
}
