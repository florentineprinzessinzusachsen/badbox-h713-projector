package b2;

import a1.c;
import java.io.Serializable;
import java.util.RandomAccess;
import v1.d;
import v1.i;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class b extends d implements a, RandomAccess, Serializable {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Enum[] f338d;

    public b(Enum[] enumArr) {
        this.f338d = enumArr;
    }

    @Override // v1.a
    public final int a() {
        return this.f338d.length;
    }

    @Override // v1.a, java.util.Collection, java.util.List
    public final boolean contains(Object obj) {
        if (!(obj instanceof Enum)) {
            return false;
        }
        Enum r4 = (Enum) obj;
        return ((Enum) i.b0(this.f338d, r4.ordinal())) == r4;
    }

    @Override // java.util.List
    public final Object get(int i4) {
        Enum[] enumArr = this.f338d;
        int length = enumArr.length;
        if (i4 < 0 || i4 >= length) {
            throw new IndexOutOfBoundsException(c.b(i4, length, "index: ", ", size: "));
        }
        return enumArr[i4];
    }

    @Override // v1.d, java.util.List
    public final int indexOf(Object obj) {
        if (!(obj instanceof Enum)) {
            return -1;
        }
        Enum r4 = (Enum) obj;
        int iOrdinal = r4.ordinal();
        if (((Enum) i.b0(this.f338d, iOrdinal)) == r4) {
            return iOrdinal;
        }
        return -1;
    }

    @Override // v1.d, java.util.List
    public final int lastIndexOf(Object obj) {
        if (!(obj instanceof Enum)) {
            return -1;
        }
        Enum r4 = (Enum) obj;
        int iOrdinal = r4.ordinal();
        if (((Enum) i.b0(this.f338d, iOrdinal)) == r4) {
            return iOrdinal;
        }
        return -1;
    }
}
