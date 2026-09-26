package m2;

import d0.l0;
import java.util.Iterator;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public class a implements Iterable {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f1443d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f1444e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f1445f;

    public a(int i4, int i5, int i6) {
        if (i6 == 0) {
            throw new IllegalArgumentException("Step must be non-zero.");
        }
        if (i6 == Integer.MIN_VALUE) {
            throw new IllegalArgumentException("Step must be greater than Int.MIN_VALUE to avoid overflow on negation.");
        }
        this.f1443d = i4;
        this.f1444e = l0.w(i4, i5, i6);
        this.f1445f = i6;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof a)) {
            return false;
        }
        if (isEmpty() && ((a) obj).isEmpty()) {
            return true;
        }
        a aVar = (a) obj;
        return this.f1443d == aVar.f1443d && this.f1444e == aVar.f1444e && this.f1445f == aVar.f1445f;
    }

    public int hashCode() {
        if (isEmpty()) {
            return -1;
        }
        return (((this.f1443d * 31) + this.f1444e) * 31) + this.f1445f;
    }

    public boolean isEmpty() {
        int i4 = this.f1445f;
        int i5 = this.f1444e;
        int i6 = this.f1443d;
        if (i4 > 0) {
            return i6 > i5;
        }
        return i6 < i5;
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        return new b(this.f1443d, this.f1444e, this.f1445f);
    }

    public String toString() {
        StringBuilder sb;
        int i4 = this.f1444e;
        int i5 = this.f1443d;
        int i6 = this.f1445f;
        if (i6 > 0) {
            sb = new StringBuilder();
            sb.append(i5);
            sb.append("..");
            sb.append(i4);
            sb.append(" step ");
            sb.append(i6);
        } else {
            sb = new StringBuilder();
            sb.append(i5);
            sb.append(" downTo ");
            sb.append(i4);
            sb.append(" step ");
            sb.append(-i6);
        }
        return sb.toString();
    }
}
