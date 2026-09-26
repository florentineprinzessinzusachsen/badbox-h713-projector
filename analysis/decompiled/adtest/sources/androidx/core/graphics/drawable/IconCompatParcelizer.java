package androidx.core.graphics.drawable;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.os.Parcel;
import android.os.Parcelable;
import c0.b;
import c0.c;
import java.nio.charset.Charset;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public class IconCompatParcelizer {
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static IconCompat read(b bVar) {
        IconCompat iconCompat = new IconCompat();
        int i4 = iconCompat.f292a;
        if (bVar.e(1)) {
            i4 = ((c) bVar).f362e.readInt();
        }
        iconCompat.f292a = i4;
        byte[] bArr = iconCompat.f294c;
        if (bVar.e(2)) {
            Parcel parcel = ((c) bVar).f362e;
            int i5 = parcel.readInt();
            if (i5 < 0) {
                bArr = null;
            } else {
                byte[] bArr2 = new byte[i5];
                parcel.readByteArray(bArr2);
                bArr = bArr2;
            }
        }
        iconCompat.f294c = bArr;
        iconCompat.f295d = bVar.f(iconCompat.f295d, 3);
        int i6 = iconCompat.f296e;
        if (bVar.e(4)) {
            i6 = ((c) bVar).f362e.readInt();
        }
        iconCompat.f296e = i6;
        int i7 = iconCompat.f297f;
        if (bVar.e(5)) {
            i7 = ((c) bVar).f362e.readInt();
        }
        iconCompat.f297f = i7;
        iconCompat.f298g = (ColorStateList) bVar.f(iconCompat.f298g, 6);
        String string = iconCompat.f300i;
        if (bVar.e(7)) {
            string = ((c) bVar).f362e.readString();
        }
        iconCompat.f300i = string;
        String string2 = iconCompat.f301j;
        if (bVar.e(8)) {
            string2 = ((c) bVar).f362e.readString();
        }
        iconCompat.f301j = string2;
        iconCompat.f299h = PorterDuff.Mode.valueOf(iconCompat.f300i);
        switch (iconCompat.f292a) {
            case -1:
                Parcelable parcelable = iconCompat.f295d;
                if (parcelable == null) {
                    throw new IllegalArgumentException("Invalid icon");
                }
                iconCompat.f293b = parcelable;
                return iconCompat;
            case 0:
            default:
                return iconCompat;
            case 1:
            case 5:
                Parcelable parcelable2 = iconCompat.f295d;
                if (parcelable2 != null) {
                    iconCompat.f293b = parcelable2;
                    return iconCompat;
                }
                byte[] bArr3 = iconCompat.f294c;
                iconCompat.f293b = bArr3;
                iconCompat.f292a = 3;
                iconCompat.f296e = 0;
                iconCompat.f297f = bArr3.length;
                return iconCompat;
            case 2:
            case 4:
            case 6:
                String str = new String(iconCompat.f294c, Charset.forName("UTF-16"));
                iconCompat.f293b = str;
                if (iconCompat.f292a == 2 && iconCompat.f301j == null) {
                    iconCompat.f301j = str.split(":", -1)[0];
                }
                return iconCompat;
            case 3:
                iconCompat.f293b = iconCompat.f294c;
                return iconCompat;
        }
    }

    public static void write(IconCompat iconCompat, b bVar) {
        bVar.getClass();
        iconCompat.f300i = iconCompat.f299h.name();
        switch (iconCompat.f292a) {
            case -1:
                iconCompat.f295d = (Parcelable) iconCompat.f293b;
                break;
            case 1:
            case 5:
                iconCompat.f295d = (Parcelable) iconCompat.f293b;
                break;
            case 2:
                iconCompat.f294c = ((String) iconCompat.f293b).getBytes(Charset.forName("UTF-16"));
                break;
            case 3:
                iconCompat.f294c = (byte[]) iconCompat.f293b;
                break;
            case 4:
            case 6:
                iconCompat.f294c = iconCompat.f293b.toString().getBytes(Charset.forName("UTF-16"));
                break;
        }
        int i4 = iconCompat.f292a;
        if (-1 != i4) {
            bVar.h(1);
            ((c) bVar).f362e.writeInt(i4);
        }
        byte[] bArr = iconCompat.f294c;
        if (bArr != null) {
            bVar.h(2);
            Parcel parcel = ((c) bVar).f362e;
            parcel.writeInt(bArr.length);
            parcel.writeByteArray(bArr);
        }
        Parcelable parcelable = iconCompat.f295d;
        if (parcelable != null) {
            bVar.h(3);
            ((c) bVar).f362e.writeParcelable(parcelable, 0);
        }
        int i5 = iconCompat.f296e;
        if (i5 != 0) {
            bVar.h(4);
            ((c) bVar).f362e.writeInt(i5);
        }
        int i6 = iconCompat.f297f;
        if (i6 != 0) {
            bVar.h(5);
            ((c) bVar).f362e.writeInt(i6);
        }
        ColorStateList colorStateList = iconCompat.f298g;
        if (colorStateList != null) {
            bVar.h(6);
            ((c) bVar).f362e.writeParcelable(colorStateList, 0);
        }
        String str = iconCompat.f300i;
        if (str != null) {
            bVar.h(7);
            ((c) bVar).f362e.writeString(str);
        }
        String str2 = iconCompat.f301j;
        if (str2 != null) {
            bVar.h(8);
            ((c) bVar).f362e.writeString(str2);
        }
    }
}
