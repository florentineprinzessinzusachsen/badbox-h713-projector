package r2;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public abstract class z {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final a0 f2053a;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v6, types: [s2.d] */
    /* JADX WARN: Type inference failed for: r0v7, types: [r2.y] */
    /* JADX WARN: Type inference failed for: r0v8, types: [r2.a0] */
    /* JADX WARN: Type inference failed for: r0v9, types: [r2.y] */
    static {
        String property;
        ?? r4;
        int i4 = w2.t.f2651a;
        try {
            property = System.getProperty("kotlinx.coroutines.main.delay");
        } catch (SecurityException unused) {
            property = null;
        }
        if (property != null ? Boolean.parseBoolean(property) : false) {
            y2.e eVar = e0.f1974a;
            r4 = w2.n.f2645a;
            s2.d dVar = r4.f2148h;
            if (!(r4 != 0)) {
                r4 = y.f2050m;
            }
        } else {
            r4 = y.f2050m;
        }
        f2053a = r4;
    }
}
