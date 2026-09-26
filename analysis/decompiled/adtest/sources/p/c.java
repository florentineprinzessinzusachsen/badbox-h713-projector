package p;

import android.os.IBinder;
import android.os.Parcel;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class c implements d {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public IBinder f1608c;

    @Override // android.os.IInterface
    public final IBinder asBinder() {
        return this.f1608c;
    }

    @Override // p.d
    public final void b(String[] strArr) {
        Parcel parcelObtain = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken(d.f1614a);
            parcelObtain.writeStringArray(strArr);
            this.f1608c.transact(1, parcelObtain, null, 1);
        } finally {
            parcelObtain.recycle();
        }
    }
}
