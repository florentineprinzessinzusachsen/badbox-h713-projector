package androidx.versionedparcelable;

import android.os.Parcel;
import android.os.Parcelable;
import c0.a;
import c0.c;
import c0.d;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public class ParcelImpl implements Parcelable {
    public static final Parcelable.Creator<ParcelImpl> CREATOR = new a();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final d f306d;

    public ParcelImpl(Parcel parcel) {
        this.f306d = new c(parcel).g();
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i4) {
        new c(parcel).i(this.f306d);
    }
}
