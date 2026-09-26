package androidx.versionedparcelable;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.SparseIntArray;
import java.lang.reflect.Method;

/* JADX INFO: compiled from: VersionedParcelParcel.java */
/* JADX INFO: loaded from: classes.dex */
class b extends a {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final SparseIntArray f1477d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final Parcel f1478e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final int f1479f;
    private final int g;
    private final String h;
    private int i;
    private int j;
    private int k;

    b(Parcel parcel) {
        this(parcel, parcel.dataPosition(), parcel.dataSize(), "", new a.b.a(), new a.b.a(), new a.b.a());
    }

    @Override // androidx.versionedparcelable.a
    public boolean a(int i) {
        while (this.j < this.g) {
            int i2 = this.k;
            if (i2 == i) {
                return true;
            }
            if (String.valueOf(i2).compareTo(String.valueOf(i)) > 0) {
                return false;
            }
            this.f1478e.setDataPosition(this.j);
            int i3 = this.f1478e.readInt();
            this.k = this.f1478e.readInt();
            this.j += i3;
        }
        return this.k == i;
    }

    @Override // androidx.versionedparcelable.a
    public void b(int i) {
        a();
        this.i = i;
        this.f1477d.put(i, this.f1478e.dataPosition());
        c(0);
        c(i);
    }

    @Override // androidx.versionedparcelable.a
    public void c(int i) {
        this.f1478e.writeInt(i);
    }

    @Override // androidx.versionedparcelable.a
    public boolean d() {
        return this.f1478e.readInt() != 0;
    }

    @Override // androidx.versionedparcelable.a
    public byte[] e() {
        int i = this.f1478e.readInt();
        if (i < 0) {
            return null;
        }
        byte[] bArr = new byte[i];
        this.f1478e.readByteArray(bArr);
        return bArr;
    }

    @Override // androidx.versionedparcelable.a
    protected CharSequence f() {
        return (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(this.f1478e);
    }

    @Override // androidx.versionedparcelable.a
    public int g() {
        return this.f1478e.readInt();
    }

    @Override // androidx.versionedparcelable.a
    public <T extends Parcelable> T h() {
        return (T) this.f1478e.readParcelable(b.class.getClassLoader());
    }

    @Override // androidx.versionedparcelable.a
    public String i() {
        return this.f1478e.readString();
    }

    private b(Parcel parcel, int i, int i2, String str, a.b.a<String, Method> aVar, a.b.a<String, Method> aVar2, a.b.a<String, Class> aVar3) {
        super(aVar, aVar2, aVar3);
        this.f1477d = new SparseIntArray();
        this.i = -1;
        this.j = 0;
        this.k = -1;
        this.f1478e = parcel;
        this.f1479f = i;
        this.g = i2;
        this.j = this.f1479f;
        this.h = str;
    }

    @Override // androidx.versionedparcelable.a
    protected a b() {
        Parcel parcel = this.f1478e;
        int iDataPosition = parcel.dataPosition();
        int i = this.j;
        if (i == this.f1479f) {
            i = this.g;
        }
        return new b(parcel, iDataPosition, i, this.h + "  ", this.f1474a, this.f1475b, this.f1476c);
    }

    @Override // androidx.versionedparcelable.a
    public void a() {
        int i = this.i;
        if (i >= 0) {
            int i2 = this.f1477d.get(i);
            int iDataPosition = this.f1478e.dataPosition();
            this.f1478e.setDataPosition(i2);
            this.f1478e.writeInt(iDataPosition - i2);
            this.f1478e.setDataPosition(iDataPosition);
        }
    }

    @Override // androidx.versionedparcelable.a
    public void a(byte[] bArr) {
        if (bArr != null) {
            this.f1478e.writeInt(bArr.length);
            this.f1478e.writeByteArray(bArr);
        } else {
            this.f1478e.writeInt(-1);
        }
    }

    @Override // androidx.versionedparcelable.a
    public void a(String str) {
        this.f1478e.writeString(str);
    }

    @Override // androidx.versionedparcelable.a
    public void a(Parcelable parcelable) {
        this.f1478e.writeParcelable(parcelable, 0);
    }

    @Override // androidx.versionedparcelable.a
    public void a(boolean z) {
        this.f1478e.writeInt(z ? 1 : 0);
    }

    @Override // androidx.versionedparcelable.a
    protected void a(CharSequence charSequence) {
        TextUtils.writeToParcel(charSequence, this.f1478e, 0);
    }
}
