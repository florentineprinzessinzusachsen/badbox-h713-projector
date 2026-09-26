package androidx.fragment.app;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

/* JADX INFO: compiled from: FragmentManagerState.java */
/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"BanParcelableUsage"})
final class j implements Parcelable {
    public static final Parcelable.Creator<j> CREATOR = new a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    ArrayList<l> f1281a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    ArrayList<String> f1282b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    b[] f1283c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    String f1284d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    int f1285e;

    /* JADX INFO: compiled from: FragmentManagerState.java */
    static class a implements Parcelable.Creator<j> {
        a() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public j createFromParcel(Parcel parcel) {
            return new j(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public j[] newArray(int i) {
            return new j[i];
        }
    }

    public j() {
        this.f1284d = null;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(this.f1281a);
        parcel.writeStringList(this.f1282b);
        parcel.writeTypedArray(this.f1283c, i);
        parcel.writeString(this.f1284d);
        parcel.writeInt(this.f1285e);
    }

    public j(Parcel parcel) {
        this.f1284d = null;
        this.f1281a = parcel.createTypedArrayList(l.CREATOR);
        this.f1282b = parcel.createStringArrayList();
        this.f1283c = (b[]) parcel.createTypedArray(b.CREATOR);
        this.f1284d = parcel.readString();
        this.f1285e = parcel.readInt();
    }
}
