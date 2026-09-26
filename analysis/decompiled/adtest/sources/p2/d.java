package p2;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class d implements Iterator {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final CharSequence f1752d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f1753e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f1754f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f1755g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f1756h;

    public d(CharSequence charSequence) {
        j2.i.e(charSequence, "string");
        this.f1752d = charSequence;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        int i4;
        int i5;
        int i6 = this.f1753e;
        if (i6 != 0) {
            return i6 == 1;
        }
        if (this.f1756h < 0) {
            this.f1753e = 2;
            return false;
        }
        CharSequence charSequence = this.f1752d;
        int length = charSequence.length();
        int length2 = charSequence.length();
        for (int i7 = this.f1754f; i7 < length2; i7++) {
            char cCharAt = charSequence.charAt(i7);
            if (cCharAt == '\n' || cCharAt == '\r') {
                i4 = (cCharAt == '\r' && (i5 = i7 + 1) < charSequence.length() && charSequence.charAt(i5) == '\n') ? 2 : 1;
                length = i7;
                this.f1753e = 1;
                this.f1756h = i4;
                this.f1755g = length;
                return true;
            }
        }
        i4 = -1;
        this.f1753e = 1;
        this.f1756h = i4;
        this.f1755g = length;
        return true;
    }

    @Override // java.util.Iterator
    public final Object next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        this.f1753e = 0;
        int i4 = this.f1755g;
        int i5 = this.f1754f;
        this.f1754f = this.f1756h + i4;
        return this.f1752d.subSequence(i5, i4).toString();
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
