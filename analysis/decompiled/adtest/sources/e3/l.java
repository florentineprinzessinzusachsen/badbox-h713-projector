package e3;

import java.io.IOException;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class l implements i {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final s f756d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final d3.e f757e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final long f758f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public long f759g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final CopyOnWriteArrayList f760h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final LinkedBlockingDeque f761i;

    public l(s sVar, d3.e eVar) {
        j2.i.e(eVar, "taskRunner");
        this.f756d = sVar;
        this.f757e = eVar;
        this.f758f = TimeUnit.MILLISECONDS.toNanos(250L);
        this.f759g = Long.MIN_VALUE;
        this.f760h = new CopyOnWriteArrayList();
        this.f761i = new LinkedBlockingDeque();
    }

    public final void a() {
        CopyOnWriteArrayList copyOnWriteArrayList = this.f760h;
        Iterator it = copyOnWriteArrayList.iterator();
        j2.i.d(it, "iterator(...)");
        while (it.hasNext()) {
            v vVar = (v) it.next();
            vVar.cancel();
            v vVarA = vVar.a();
            if (vVarA != null) {
                this.f756d.f820p.addLast(vVarA);
            }
        }
        copyOnWriteArrayList.clear();
    }

    public final u b() {
        v jVar;
        s sVar = this.f756d;
        if (sVar.a(null)) {
            try {
                jVar = sVar.b();
            } catch (Throwable th) {
                jVar = new j(th);
            }
            if (jVar.b()) {
                return new u(jVar, (Throwable) null, 6);
            }
            if (jVar instanceof j) {
                return ((j) jVar).f753a;
            }
            this.f760h.add(jVar);
            this.f757e.d().d(new k(b3.g.f349b + " connect " + sVar.f813i.f58h.f(), jVar, this), 0L);
        }
        return null;
    }

    @Override // e3.i
    public final q c() throws IOException {
        u uVarB;
        long j4;
        u uVar;
        IOException iOException = null;
        while (true) {
            try {
                if (this.f760h.isEmpty() && !this.f756d.a(null)) {
                    a();
                    j2.i.b(iOException);
                    throw iOException;
                }
                if (this.f756d.f815k.f782s) {
                    throw new IOException("Canceled");
                }
                a3.h hVar = this.f757e.f543a;
                long jNanoTime = System.nanoTime();
                long j5 = this.f759g - jNanoTime;
                if (this.f760h.isEmpty() || j5 <= 0) {
                    uVarB = b();
                    j4 = this.f758f;
                    this.f759g = jNanoTime + j4;
                } else {
                    j4 = j5;
                    uVarB = null;
                }
                if (uVarB == null) {
                    TimeUnit timeUnit = TimeUnit.NANOSECONDS;
                    CopyOnWriteArrayList copyOnWriteArrayList = this.f760h;
                    if (copyOnWriteArrayList.isEmpty() || (uVar = (u) this.f761i.poll(j4, timeUnit)) == null) {
                        uVarB = null;
                    } else {
                        copyOnWriteArrayList.remove(uVar.f822a);
                        uVarB = uVar;
                    }
                    if (uVarB == null) {
                    }
                }
                boolean z3 = false;
                if (uVarB.f823b == null && uVarB.f824c == null) {
                    a();
                    if (!uVarB.f822a.b()) {
                        uVarB = uVarB.f822a.e();
                    }
                    if (uVarB.f823b == null && uVarB.f824c == null) {
                        z3 = true;
                    }
                    if (z3) {
                        q qVarF = uVarB.f822a.f();
                        a();
                        return qVarF;
                    }
                }
                Throwable th = uVarB.f824c;
                if (th != null) {
                    if (!(th instanceof IOException)) {
                        throw th;
                    }
                    if (iOException == null) {
                        iOException = (IOException) th;
                    } else {
                        l3.h.a(iOException, th);
                    }
                }
                v vVar = uVarB.f823b;
                if (vVar != null) {
                    this.f756d.f820p.addFirst(vVar);
                }
            } catch (Throwable th2) {
                a();
                throw th2;
            }
        }
    }

    @Override // e3.i
    public final s d() {
        return this.f756d;
    }
}
