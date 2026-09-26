package t2;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public abstract class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final m f2198a = new m(-1, null, null, 0);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final int f2199b = w2.a.j("kotlinx.coroutines.bufferedChannel.segmentSize", 32, 12);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final int f2200c = w2.a.j("kotlinx.coroutines.bufferedChannel.expandBufferCompletionWaitIterations", 10000, 12);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final a3.h f2201d = new a3.h(10, "BUFFERED");

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final a3.h f2202e = new a3.h(10, "SHOULD_BUFFER");

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final a3.h f2203f = new a3.h(10, "S_RESUMING_BY_RCV");

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final a3.h f2204g = new a3.h(10, "RESUMING_BY_EB");

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final a3.h f2205h = new a3.h(10, "POISONED");

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final a3.h f2206i = new a3.h(10, "DONE_RCV");

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final a3.h f2207j = new a3.h(10, "INTERRUPTED_SEND");

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final a3.h f2208k = new a3.h(10, "INTERRUPTED_RCV");

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final a3.h f2209l = new a3.h(10, "CHANNEL_CLOSED");

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final a3.h f2210m = new a3.h(10, "SUSPEND");

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final a3.h f2211n = new a3.h(10, "SUSPEND_NO_WAITER");

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final a3.h f2212o = new a3.h(10, "FAILED");

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final a3.h f2213p = new a3.h(10, "NO_RECEIVE_RESULT");

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final a3.h f2214q = new a3.h(10, "CLOSE_HANDLER_CLOSED");

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final a3.h f2215r = new a3.h(10, "CLOSE_HANDLER_INVOKED");

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final a3.h f2216s = new a3.h(10, "NO_CLOSE_CAUSE");

    public static final boolean a(r2.g gVar, Object obj, i2.q qVar) {
        a3.h hVarN = gVar.n(obj, qVar);
        if (hVarN == null) {
            return false;
        }
        gVar.o(hVarN);
        return true;
    }
}
