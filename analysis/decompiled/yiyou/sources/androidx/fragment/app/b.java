package androidx.fragment.app;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import java.util.ArrayList;

/* JADX INFO: compiled from: BackStackState.java */
/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"BanParcelableUsage"})
final class b implements Parcelable {
    public static final Parcelable.Creator<b> CREATOR = new a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final int[] f1230a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final ArrayList<String> f1231b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final int[] f1232c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final int[] f1233d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    final int f1234e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    final int f1235f;
    final String g;
    final int h;
    final int i;
    final CharSequence j;
    final int k;
    final CharSequence l;
    final ArrayList<String> m;
    final ArrayList<String> n;
    final boolean o;

    /* JADX INFO: compiled from: BackStackState.java */
    static class a implements Parcelable.Creator<b> {
        a() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public b createFromParcel(Parcel parcel) {
            return new b(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public b[] newArray(int i) {
            return new b[i];
        }
    }

    public b(androidx.fragment.app.a aVar) {
        int size = aVar.f1297a.size();
        this.f1230a = new int[size * 5];
        if (!aVar.h) {
            throw new IllegalStateException("Not on back stack");
        }
        this.f1231b = new ArrayList<>(size);
        this.f1232c = new int[size];
        this.f1233d = new int[size];
        int i = 0;
        int i2 = 0;
        while (i < size) {
            m.a aVar2 = aVar.f1297a.get(i);
            int i3 = i2 + 1;
            this.f1230a[i2] = aVar2.f1303a;
            ArrayList<String> arrayList = this.f1231b;
            Fragment fragment = aVar2.f1304b;
            arrayList.add(fragment != null ? fragment.f1207e : null);
            int[] iArr = this.f1230a;
            int i4 = i3 + 1;
            iArr[i3] = aVar2.f1305c;
            int i5 = i4 + 1;
            iArr[i4] = aVar2.f1306d;
            int i6 = i5 + 1;
            iArr[i5] = aVar2.f1307e;
            iArr[i6] = aVar2.f1308f;
            this.f1232c[i] = aVar2.g.ordinal();
            this.f1233d[i] = aVar2.h.ordinal();
            i++;
            i2 = i6 + 1;
        }
        this.f1234e = aVar.f1302f;
        this.f1235f = aVar.g;
        this.g = aVar.j;
        this.h = aVar.u;
        this.i = aVar.k;
        this.j = aVar.l;
        this.k = aVar.m;
        this.l = aVar.n;
        this.m = aVar.o;
        this.n = aVar.p;
        this.o = aVar.q;
    }

    public androidx.fragment.app.a a(i iVar) {
        androidx.fragment.app.a aVar = new androidx.fragment.app.a(iVar);
        int i = 0;
        int i2 = 0;
        while (i < this.f1230a.length) {
            m.a aVar2 = new m.a();
            int i3 = i + 1;
            aVar2.f1303a = this.f1230a[i];
            if (i.I) {
                Log.v("FragmentManager", "Instantiate " + aVar + " op #" + i2 + " base fragment #" + this.f1230a[i3]);
            }
            String str = this.f1231b.get(i2);
            if (str != null) {
                aVar2.f1304b = iVar.g.get(str);
            } else {
                aVar2.f1304b = null;
            }
            aVar2.g = androidx.lifecycle.e.b.values()[this.f1232c[i2]];
            aVar2.h = androidx.lifecycle.e.b.values()[this.f1233d[i2]];
            int[] iArr = this.f1230a;
            int i4 = i3 + 1;
            aVar2.f1305c = iArr[i3];
            int i5 = i4 + 1;
            aVar2.f1306d = iArr[i4];
            int i6 = i5 + 1;
            aVar2.f1307e = iArr[i5];
            aVar2.f1308f = iArr[i6];
            aVar.f1298b = aVar2.f1305c;
            aVar.f1299c = aVar2.f1306d;
            aVar.f1300d = aVar2.f1307e;
            aVar.f1301e = aVar2.f1308f;
            aVar.a(aVar2);
            i2++;
            i = i6 + 1;
        }
        aVar.f1302f = this.f1234e;
        aVar.g = this.f1235f;
        aVar.j = this.g;
        aVar.u = this.h;
        aVar.h = true;
        aVar.k = this.i;
        aVar.l = this.j;
        aVar.m = this.k;
        aVar.n = this.l;
        aVar.o = this.m;
        aVar.p = this.n;
        aVar.q = this.o;
        aVar.a(1);
        return aVar;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeIntArray(this.f1230a);
        parcel.writeStringList(this.f1231b);
        parcel.writeIntArray(this.f1232c);
        parcel.writeIntArray(this.f1233d);
        parcel.writeInt(this.f1234e);
        parcel.writeInt(this.f1235f);
        parcel.writeString(this.g);
        parcel.writeInt(this.h);
        parcel.writeInt(this.i);
        TextUtils.writeToParcel(this.j, parcel, 0);
        parcel.writeInt(this.k);
        TextUtils.writeToParcel(this.l, parcel, 0);
        parcel.writeStringList(this.m);
        parcel.writeStringList(this.n);
        parcel.writeInt(this.o ? 1 : 0);
    }

    public b(Parcel parcel) {
        this.f1230a = parcel.createIntArray();
        this.f1231b = parcel.createStringArrayList();
        this.f1232c = parcel.createIntArray();
        this.f1233d = parcel.createIntArray();
        this.f1234e = parcel.readInt();
        this.f1235f = parcel.readInt();
        this.g = parcel.readString();
        this.h = parcel.readInt();
        this.i = parcel.readInt();
        this.j = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.k = parcel.readInt();
        this.l = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.m = parcel.createStringArrayList();
        this.n = parcel.createStringArrayList();
        this.o = parcel.readInt() != 0;
    }
}
