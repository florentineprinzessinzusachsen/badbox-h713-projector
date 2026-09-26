package androidx.core.app;

import android.app.PendingIntent;
import android.os.Parcel;
import android.text.TextUtils;
import androidx.core.graphics.drawable.IconCompat;
import c0.b;
import c0.c;
import c0.d;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public class RemoteActionCompatParcelizer {
    public static RemoteActionCompat read(b bVar) {
        RemoteActionCompat remoteActionCompat = new RemoteActionCompat();
        d dVarG = remoteActionCompat.f285a;
        boolean z3 = true;
        if (bVar.e(1)) {
            dVarG = bVar.g();
        }
        remoteActionCompat.f285a = (IconCompat) dVarG;
        CharSequence charSequence = remoteActionCompat.f286b;
        if (bVar.e(2)) {
            charSequence = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(((c) bVar).f362e);
        }
        remoteActionCompat.f286b = charSequence;
        CharSequence charSequence2 = remoteActionCompat.f287c;
        if (bVar.e(3)) {
            charSequence2 = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(((c) bVar).f362e);
        }
        remoteActionCompat.f287c = charSequence2;
        remoteActionCompat.f288d = (PendingIntent) bVar.f(remoteActionCompat.f288d, 4);
        boolean z4 = remoteActionCompat.f289e;
        if (bVar.e(5)) {
            z4 = ((c) bVar).f362e.readInt() != 0;
        }
        remoteActionCompat.f289e = z4;
        boolean z5 = remoteActionCompat.f290f;
        if (!bVar.e(6)) {
            z3 = z5;
        } else if (((c) bVar).f362e.readInt() == 0) {
            z3 = false;
        }
        remoteActionCompat.f290f = z3;
        return remoteActionCompat;
    }

    public static void write(RemoteActionCompat remoteActionCompat, b bVar) {
        bVar.getClass();
        IconCompat iconCompat = remoteActionCompat.f285a;
        bVar.h(1);
        bVar.i(iconCompat);
        CharSequence charSequence = remoteActionCompat.f286b;
        bVar.h(2);
        Parcel parcel = ((c) bVar).f362e;
        TextUtils.writeToParcel(charSequence, parcel, 0);
        CharSequence charSequence2 = remoteActionCompat.f287c;
        bVar.h(3);
        TextUtils.writeToParcel(charSequence2, parcel, 0);
        PendingIntent pendingIntent = remoteActionCompat.f288d;
        bVar.h(4);
        parcel.writeParcelable(pendingIntent, 0);
        boolean z3 = remoteActionCompat.f289e;
        bVar.h(5);
        parcel.writeInt(z3 ? 1 : 0);
        boolean z4 = remoteActionCompat.f290f;
        bVar.h(6);
        parcel.writeInt(z4 ? 1 : 0);
    }
}
