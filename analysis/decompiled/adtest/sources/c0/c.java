package c0;

import android.os.Parcel;
import android.util.SparseIntArray;
import e.e;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class c extends b {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final SparseIntArray f361d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Parcel f362e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f363f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int f364g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final String f365h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f366i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f367j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f368k;

    public c(Parcel parcel) {
        this(parcel, parcel.dataPosition(), parcel.dataSize(), "", new e(), new e(), new e());
    }

    @Override // c0.b
    public final c a() {
        Parcel parcel = this.f362e;
        int iDataPosition = parcel.dataPosition();
        int i4 = this.f367j;
        if (i4 == this.f363f) {
            i4 = this.f364g;
        }
        return new c(parcel, iDataPosition, i4, this.f365h + "  ", this.f358a, this.f359b, this.f360c);
    }

    @Override // c0.b
    public final boolean e(int i4) {
        while (this.f367j < this.f364g) {
            int i5 = this.f368k;
            if (i5 == i4) {
                return true;
            }
            if (String.valueOf(i5).compareTo(String.valueOf(i4)) > 0) {
                return false;
            }
            int i6 = this.f367j;
            Parcel parcel = this.f362e;
            parcel.setDataPosition(i6);
            int i7 = parcel.readInt();
            this.f368k = parcel.readInt();
            this.f367j += i7;
        }
        return this.f368k == i4;
    }

    @Override // c0.b
    public final void h(int i4) {
        int i5 = this.f366i;
        SparseIntArray sparseIntArray = this.f361d;
        Parcel parcel = this.f362e;
        if (i5 >= 0) {
            int i6 = sparseIntArray.get(i5);
            int iDataPosition = parcel.dataPosition();
            parcel.setDataPosition(i6);
            parcel.writeInt(iDataPosition - i6);
            parcel.setDataPosition(iDataPosition);
        }
        this.f366i = i4;
        sparseIntArray.put(i4, parcel.dataPosition());
        parcel.writeInt(0);
        parcel.writeInt(i4);
    }

    public c(Parcel parcel, int i4, int i5, String str, e eVar, e eVar2, e eVar3) {
        super(eVar, eVar2, eVar3);
        this.f361d = new SparseIntArray();
        this.f366i = -1;
        this.f368k = -1;
        this.f362e = parcel;
        this.f363f = i4;
        this.f364g = i5;
        this.f367j = i4;
        this.f365h = str;
    }
}
