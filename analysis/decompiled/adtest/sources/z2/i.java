package z2;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public abstract class i {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final a3.h f2797b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final a3.h f2798c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final a3.h f2799d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final a3.h f2800e;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int f2796a = w2.a.j("kotlinx.coroutines.semaphore.maxSpinCycles", 100, 12);

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final int f2801f = w2.a.j("kotlinx.coroutines.semaphore.segmentSize", 16, 12);

    static {
        int i4 = 10;
        f2797b = new a3.h(i4, "PERMIT");
        f2798c = new a3.h(i4, "TAKEN");
        f2799d = new a3.h(i4, "BROKEN");
        f2800e = new a3.h(i4, "CANCELLED");
    }
}
