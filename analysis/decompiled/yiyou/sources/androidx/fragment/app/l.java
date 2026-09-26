package androidx.fragment.app;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

/* JADX INFO: compiled from: FragmentState.java */
/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"BanParcelableUsage"})
final class l implements Parcelable {
    public static final Parcelable.Creator<l> CREATOR = new a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final String f1291a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final String f1292b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final boolean f1293c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final int f1294d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    final int f1295e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    final String f1296f;
    final boolean g;
    final boolean h;
    final boolean i;
    final Bundle j;
    final boolean k;
    final int l;
    Bundle m;
    Fragment n;

    /* JADX INFO: compiled from: FragmentState.java */
    static class a implements Parcelable.Creator<l> {
        a() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public l createFromParcel(Parcel parcel) {
            return new l(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public l[] newArray(int i) {
            return new l[i];
        }
    }

    l(Fragment fragment) {
        this.f1291a = fragment.getClass().getName();
        this.f1292b = fragment.f1207e;
        this.f1293c = fragment.m;
        this.f1294d = fragment.v;
        this.f1295e = fragment.w;
        this.f1296f = fragment.x;
        this.g = fragment.A;
        this.h = fragment.l;
        this.i = fragment.z;
        this.j = fragment.f1208f;
        this.k = fragment.y;
        this.l = fragment.R.ordinal();
    }

    public Fragment a(ClassLoader classLoader, f fVar) {
        if (this.n == null) {
            Bundle bundle = this.j;
            if (bundle != null) {
                bundle.setClassLoader(classLoader);
            }
            this.n = fVar.a(classLoader, this.f1291a);
            this.n.m(this.j);
            Bundle bundle2 = this.m;
            if (bundle2 != null) {
                bundle2.setClassLoader(classLoader);
                this.n.f1204b = this.m;
            } else {
                this.n.f1204b = new Bundle();
            }
            Fragment fragment = this.n;
            fragment.f1207e = this.f1292b;
            fragment.m = this.f1293c;
            fragment.o = true;
            fragment.v = this.f1294d;
            fragment.w = this.f1295e;
            fragment.x = this.f1296f;
            fragment.A = this.g;
            fragment.l = this.h;
            fragment.z = this.i;
            fragment.y = this.k;
            fragment.R = androidx.lifecycle.e.b.values()[this.l];
            if (i.I) {
                Log.v("FragmentManager", "Instantiated fragment " + this.n);
            }
        }
        return this.n;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("FragmentState{");
        sb.append(this.f1291a);
        sb.append(" (");
        sb.append(this.f1292b);
        sb.append(")}:");
        if (this.f1293c) {
            sb.append(" fromLayout");
        }
        if (this.f1295e != 0) {
            sb.append(" id=0x");
            sb.append(Integer.toHexString(this.f1295e));
        }
        String str = this.f1296f;
        if (str != null && !str.isEmpty()) {
            sb.append(" tag=");
            sb.append(this.f1296f);
        }
        if (this.g) {
            sb.append(" retainInstance");
        }
        if (this.h) {
            sb.append(" removing");
        }
        if (this.i) {
            sb.append(" detached");
        }
        if (this.k) {
            sb.append(" hidden");
        }
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f1291a);
        parcel.writeString(this.f1292b);
        parcel.writeInt(this.f1293c ? 1 : 0);
        parcel.writeInt(this.f1294d);
        parcel.writeInt(this.f1295e);
        parcel.writeString(this.f1296f);
        parcel.writeInt(this.g ? 1 : 0);
        parcel.writeInt(this.h ? 1 : 0);
        parcel.writeInt(this.i ? 1 : 0);
        parcel.writeBundle(this.j);
        parcel.writeInt(this.k ? 1 : 0);
        parcel.writeBundle(this.m);
        parcel.writeInt(this.l);
    }

    l(Parcel parcel) {
        this.f1291a = parcel.readString();
        this.f1292b = parcel.readString();
        this.f1293c = parcel.readInt() != 0;
        this.f1294d = parcel.readInt();
        this.f1295e = parcel.readInt();
        this.f1296f = parcel.readString();
        this.g = parcel.readInt() != 0;
        this.h = parcel.readInt() != 0;
        this.i = parcel.readInt() != 0;
        this.j = parcel.readBundle();
        this.k = parcel.readInt() != 0;
        this.m = parcel.readBundle();
        this.l = parcel.readInt();
    }
}
