package u0;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class r implements CharSequence {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public char[] f2283d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f2284e;

    @Override // java.lang.CharSequence
    public final char charAt(int i4) {
        return this.f2283d[i4];
    }

    @Override // java.lang.CharSequence
    public final int length() {
        return this.f2283d.length;
    }

    @Override // java.lang.CharSequence
    public final CharSequence subSequence(int i4, int i5) {
        return new String(this.f2283d, i4, i5 - i4);
    }

    @Override // java.lang.CharSequence
    public final String toString() {
        if (this.f2284e == null) {
            this.f2284e = new String(this.f2283d);
        }
        return this.f2284e;
    }
}
