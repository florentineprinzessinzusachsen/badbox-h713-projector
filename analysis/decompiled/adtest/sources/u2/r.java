package u2;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class r extends v2.b implements p, g, h, v2.l {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final /* synthetic */ AtomicReferenceFieldUpdater f2361h = AtomicReferenceFieldUpdater.newUpdater(r.class, Object.class, "_state$volatile");
    private volatile /* synthetic */ Object _state$volatile;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f2362g;

    public r(Object obj) {
        this._state$volatile = obj;
    }

    @Override // v2.l
    public final g a(y1.h hVar, int i4, t2.a aVar) {
        return ((((i4 < 0 || i4 >= 2) && i4 != -2) || aVar != t2.a.f2175e) && !((i4 == 0 || i4 == -3) && aVar == t2.a.f2174d)) ? new v2.e(this, hVar, i4, aVar) : this;
    }

    /* JADX WARN: Code duplicated, block: B:77:0x013b A[Catch: all -> 0x003d, TryCatch #1 {all -> 0x003d, blocks: (B:14:0x0037, B:52:0x00cf, B:54:0x00d7, B:57:0x00de, B:58:0x00e4, B:60:0x00e7, B:70:0x0108, B:73:0x0118, B:74:0x0134, B:80:0x0144, B:77:0x013b, B:79:0x0141, B:62:0x00ed, B:66:0x00f4, B:21:0x0052, B:24:0x005d, B:51:0x00bf), top: B:91:0x0025 }] */
    /* JADX WARN: Code duplicated, block: B:7:0x0019  */
    /* JADX WARN: Code duplicated, block: B:95:0x0141 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:96:? A[LOOP:0: B:74:0x0134->B:96:?, LOOP_END, SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:72:0x0117 -> B:52:0x00cf). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    @Override // u2.g
    public final java.lang.Object b(u2.h r18, y1.c r19) {
        /*
            Method dump skipped, instruction units count: 340
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: u2.r.b(u2.h, y1.c):java.lang.Object");
    }

    @Override // u2.h
    public final Object c(Object obj, y1.c cVar) {
        if (obj == null) {
            obj = v2.c.f2527b;
        }
        g(null, obj);
        return u1.k.f2301a;
    }

    @Override // v2.b
    public final t d() {
        return new t();
    }

    @Override // v2.b
    public final t[] e() {
        return new t[2];
    }

    public final boolean g(Object obj, Object obj2) {
        int i4;
        t[] tVarArr;
        a3.h hVar;
        synchronized (this) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f2361h;
            Object obj3 = atomicReferenceFieldUpdater.get(this);
            if (obj != null && !j2.i.a(obj3, obj)) {
                return false;
            }
            if (j2.i.a(obj3, obj2)) {
                return true;
            }
            atomicReferenceFieldUpdater.set(this, obj2);
            int i5 = this.f2362g;
            if ((i5 & 1) != 0) {
                this.f2362g = i5 + 2;
                return true;
            }
            int i6 = i5 + 1;
            this.f2362g = i6;
            t[] tVarArr2 = this.f2523d;
            while (true) {
                t[] tVarArr3 = tVarArr2;
                if (tVarArr3 != null) {
                    for (t tVar : tVarArr3) {
                        if (tVar != null) {
                            AtomicReference atomicReference = tVar.f2365a;
                            while (true) {
                                Object obj4 = atomicReference.get();
                                if (obj4 == null || obj4 == (hVar = s.f2364b)) {
                                    break;
                                }
                                a3.h hVar2 = s.f2363a;
                                if (obj4 != hVar2) {
                                    do {
                                        if (atomicReference.compareAndSet(obj4, hVar2)) {
                                            ((r2.i) obj4).j(u1.k.f2301a);
                                            break;
                                        }
                                    } while (atomicReference.get() == obj4);
                                } else {
                                    do {
                                        if (atomicReference.compareAndSet(obj4, hVar)) {
                                            break;
                                        }
                                    } while (atomicReference.get() == obj4);
                                }
                            }
                        }
                    }
                }
                synchronized (this) {
                    i4 = this.f2362g;
                    if (i4 == i6) {
                        this.f2362g = i6 + 1;
                        return true;
                    }
                    tVarArr = this.f2523d;
                }
                tVarArr2 = tVarArr;
                i6 = i4;
            }
        }
    }
}
