package y2;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class e extends h {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final e f2755g;

    static {
        int i4 = k.f2763c;
        int i5 = k.f2764d;
        long j4 = k.f2765e;
        String str = k.f2761a;
        e eVar = new e();
        eVar.f2757f = new c(i4, i5, j4, str);
        f2755g = eVar;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        throw new UnsupportedOperationException("Dispatchers.Default cannot be closed");
    }

    @Override // r2.s
    public final String toString() {
        return "Dispatchers.Default";
    }
}
