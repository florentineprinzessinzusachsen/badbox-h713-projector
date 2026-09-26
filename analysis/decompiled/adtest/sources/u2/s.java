package u2;

import d0.l0;
import java.util.NoSuchElementException;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public abstract class s {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final a3.h f2363a = new a3.h(10, "NONE");

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final a3.h f2364b = new a3.h(10, "PENDING");

    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x007b, code lost:
    
        if (r6.z().equals(r5) == false) goto L40;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.io.Serializable a(r.i r4, u2.h r5, a2.c r6) throws java.lang.Throwable {
        /*
            boolean r0 = r6 instanceof u2.j
            if (r0 == 0) goto L13
            r0 = r6
            u2.j r0 = (u2.j) r0
            int r1 = r0.f2329i
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.f2329i = r1
            goto L18
        L13:
            u2.j r0 = new u2.j
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.f2328h
            int r1 = r0.f2329i
            r2 = 1
            if (r1 == 0) goto L31
            if (r1 != r2) goto L29
            j2.n r4 = r0.f2327g
            d0.l0.M(r6)     // Catch: java.lang.Throwable -> L27
            goto L4b
        L27:
            r5 = move-exception
            goto L4f
        L29:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L31:
            d0.l0.M(r6)
            j2.n r6 = new j2.n
            r6.<init>()
            u2.e r1 = new u2.e     // Catch: java.lang.Throwable -> L4d
            r1.<init>(r5, r6)     // Catch: java.lang.Throwable -> L4d
            r0.f2327g = r6     // Catch: java.lang.Throwable -> L4d
            r0.f2329i = r2     // Catch: java.lang.Throwable -> L4d
            java.lang.Object r4 = r4.b(r1, r0)     // Catch: java.lang.Throwable -> L4d
            z1.a r5 = z1.a.f2781d
            if (r4 != r5) goto L4b
            return r5
        L4b:
            r4 = 0
            return r4
        L4d:
            r5 = move-exception
            r4 = r6
        L4f:
            java.lang.Object r4 = r4.f1276d
            java.lang.Throwable r4 = (java.lang.Throwable) r4
            if (r4 == 0) goto L5b
            boolean r6 = r4.equals(r5)
            if (r6 != 0) goto L7e
        L5b:
            y1.h r6 = r0.f42e
            j2.i.b(r6)
            r2.t r0 = r2.t.f2027e
            y1.f r6 = r6.k(r0)
            r2.v0 r6 = (r2.v0) r6
            if (r6 == 0) goto L7f
            r2.d1 r6 = (r2.d1) r6
            boolean r0 = r6.J()
            if (r0 != 0) goto L73
            goto L7f
        L73:
            java.util.concurrent.CancellationException r6 = r6.z()
            boolean r6 = r6.equals(r5)
            if (r6 != 0) goto L7e
            goto L7f
        L7e:
            throw r5
        L7f:
            if (r4 != 0) goto L82
            return r5
        L82:
            boolean r6 = r5 instanceof java.util.concurrent.CancellationException
            if (r6 == 0) goto L8a
            l3.h.a(r4, r5)
            throw r4
        L8a:
            l3.h.a(r5, r4)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: u2.s.a(r.i, u2.h, a2.c):java.io.Serializable");
    }

    public static final g b(g gVar) {
        return ((gVar instanceof p) || (gVar instanceof f)) ? gVar : new f(gVar);
    }

    /* JADX WARN: Code duplicated, block: B:26:0x0063  */
    /* JADX WARN: Code duplicated, block: B:27:0x0064  */
    /* JADX WARN: Code duplicated, block: B:30:0x0070 A[Catch: all -> 0x0035, TryCatch #1 {all -> 0x0035, blocks: (B:13:0x002f, B:24:0x0053, B:28:0x0068, B:30:0x0070, B:32:0x0076, B:34:0x007c, B:37:0x008d, B:39:0x0095, B:40:0x009c, B:41:0x009e, B:42:0x009f, B:43:0x00a6, B:20:0x0048, B:23:0x004f), top: B:60:0x0021 }] */
    /* JADX WARN: Code duplicated, block: B:32:0x0076 A[Catch: all -> 0x0035, TryCatch #1 {all -> 0x0035, blocks: (B:13:0x002f, B:24:0x0053, B:28:0x0068, B:30:0x0070, B:32:0x0076, B:34:0x007c, B:37:0x008d, B:39:0x0095, B:40:0x009c, B:41:0x009e, B:42:0x009f, B:43:0x00a6, B:20:0x0048, B:23:0x004f), top: B:60:0x0021 }] */
    /* JADX WARN: Code duplicated, block: B:34:0x007c A[Catch: all -> 0x0035, TryCatch #1 {all -> 0x0035, blocks: (B:13:0x002f, B:24:0x0053, B:28:0x0068, B:30:0x0070, B:32:0x0076, B:34:0x007c, B:37:0x008d, B:39:0x0095, B:40:0x009c, B:41:0x009e, B:42:0x009f, B:43:0x00a6, B:20:0x0048, B:23:0x004f), top: B:60:0x0021 }] */
    /* JADX WARN: Code duplicated, block: B:37:0x008d A[Catch: all -> 0x0035, TryCatch #1 {all -> 0x0035, blocks: (B:13:0x002f, B:24:0x0053, B:28:0x0068, B:30:0x0070, B:32:0x0076, B:34:0x007c, B:37:0x008d, B:39:0x0095, B:40:0x009c, B:41:0x009e, B:42:0x009f, B:43:0x00a6, B:20:0x0048, B:23:0x004f), top: B:60:0x0021 }] */
    /* JADX WARN: Code duplicated, block: B:39:0x0095 A[Catch: all -> 0x0035, TryCatch #1 {all -> 0x0035, blocks: (B:13:0x002f, B:24:0x0053, B:28:0x0068, B:30:0x0070, B:32:0x0076, B:34:0x007c, B:37:0x008d, B:39:0x0095, B:40:0x009c, B:41:0x009e, B:42:0x009f, B:43:0x00a6, B:20:0x0048, B:23:0x004f), top: B:60:0x0021 }] */
    /* JADX WARN: Code duplicated, block: B:42:0x009f A[Catch: all -> 0x0035, TryCatch #1 {all -> 0x0035, blocks: (B:13:0x002f, B:24:0x0053, B:28:0x0068, B:30:0x0070, B:32:0x0076, B:34:0x007c, B:37:0x008d, B:39:0x0095, B:40:0x009c, B:41:0x009e, B:42:0x009f, B:43:0x00a6, B:20:0x0048, B:23:0x004f), top: B:60:0x0021 }] */
    /* JADX WARN: Code duplicated, block: B:44:0x00a7 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:45:0x00a9  */
    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x008a, code lost:
    
        if (r1.c(r11, r0) == r5) goto L36;
     */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:35:0x008a -> B:14:0x0032). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object c(u2.h r8, t2.u r9, boolean r10, a2.c r11) {
        /*
            Method dump skipped, instruction units count: 202
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: u2.s.c(u2.h, t2.u, boolean, a2.c):java.lang.Object");
    }

    /* JADX WARN: Code duplicated, block: B:33:0x006b  */
    /* JADX WARN: Code duplicated, block: B:7:0x0015  */
    public static final Object d(h0.o oVar, a2.c cVar) {
        n nVar;
        j2.n nVar2;
        v2.a e4;
        o0.e eVar;
        a3.h hVar = v2.c.f2527b;
        if (cVar instanceof n) {
            nVar = (n) cVar;
            int i4 = nVar.f2347j;
            if ((i4 & Integer.MIN_VALUE) != 0) {
                nVar.f2347j = i4 - Integer.MIN_VALUE;
            } else {
                nVar = new n(cVar);
            }
        } else {
            nVar = new n(cVar);
        }
        Object obj = nVar.f2346i;
        int i5 = nVar.f2347j;
        if (i5 == 0) {
            l0.M(obj);
            j2.n nVar3 = new j2.n();
            nVar3.f1276d = hVar;
            o0.e eVar2 = new o0.e(1, nVar3);
            try {
                nVar.f2344g = nVar3;
                nVar.f2345h = eVar2;
                nVar.f2347j = 1;
                Object objB = oVar.b(eVar2, nVar);
                Object obj2 = z1.a.f2781d;
                if (objB == obj2) {
                    return obj2;
                }
                nVar2 = nVar3;
            } catch (v2.a e5) {
                nVar2 = nVar3;
                e4 = e5;
                eVar = eVar2;
                if (e4.f2522d != eVar) {
                    throw e4;
                }
            }
        } else {
            if (i5 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            eVar = nVar.f2345h;
            nVar2 = nVar.f2344g;
            try {
                l0.M(obj);
            } catch (v2.a e6) {
                e4 = e6;
                if (e4.f2522d != eVar) {
                    throw e4;
                }
            }
        }
        Object obj3 = nVar2.f1276d;
        if (obj3 != hVar) {
            return obj3;
        }
        throw new NoSuchElementException("Expected at least one element");
    }
}
