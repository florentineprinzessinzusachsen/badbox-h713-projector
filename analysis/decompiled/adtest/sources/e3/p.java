package e3;

import a3.a0;
import a3.d0;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.lang.ref.Reference;
import java.net.Socket;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class p implements Cloneable {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final a3.x f767d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final a0 f768e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final r f769f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final o f770g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final AtomicBoolean f771h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public Object f772i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public i f773j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public q f774k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public boolean f775l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public h f776m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f777n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public boolean f778o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f779p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f780q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public boolean f781r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public volatile boolean f782s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public volatile h f783t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final CopyOnWriteArrayList f784u;

    public p(a3.x xVar, a0 a0Var) {
        this.f767d = xVar;
        this.f768e = a0Var;
        this.f769f = (r) xVar.A.f149e;
        xVar.f247d.getClass();
        o oVar = new o(this);
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        oVar.g(0);
        this.f770g = oVar;
        this.f771h = new AtomicBoolean();
        this.f781r = true;
        this.f784u = new CopyOnWriteArrayList();
        new AtomicReference((b3.a) a0Var.f66f);
    }

    public static final String a(p pVar) {
        StringBuilder sb = new StringBuilder();
        sb.append(pVar.f782s ? "canceled " : "");
        sb.append("call");
        sb.append(" to ");
        sb.append(((a3.t) pVar.f768e.f63c).f());
        return sb.toString();
    }

    public final void b(q qVar) {
        j2.i.e(qVar, "connection");
        TimeZone timeZone = b3.g.f348a;
        if (this.f774k != null) {
            throw new IllegalStateException("Check failed.");
        }
        this.f774k = qVar;
        qVar.f799p.add(new n(this, this.f772i));
    }

    public final IOException c(IOException iOException) {
        IOException interruptedIOException;
        Socket socketK;
        TimeZone timeZone = b3.g.f348a;
        q qVar = this.f774k;
        if (qVar != null) {
            synchronized (qVar) {
                socketK = k();
            }
            if (this.f774k == null) {
                if (socketK != null) {
                    b3.g.c(socketK);
                }
            } else if (socketK != null) {
                throw new IllegalStateException("Check failed.");
            }
        }
        if (!this.f775l && this.f770g.i()) {
            interruptedIOException = new InterruptedIOException("timeout");
            if (iOException != null) {
                interruptedIOException.initCause(iOException);
            }
        } else {
            interruptedIOException = iOException;
        }
        if (iOException != null) {
            j2.i.b(interruptedIOException);
        }
        return interruptedIOException;
    }

    public final Object clone() {
        return new p(this.f767d, this.f768e);
    }

    public final void d() {
        if (this.f782s) {
            return;
        }
        this.f782s = true;
        h hVar = this.f783t;
        if (hVar != null) {
            ((f3.g) hVar.f752g).cancel();
        }
        Iterator it = this.f784u.iterator();
        j2.i.d(it, "iterator(...)");
        while (it.hasNext()) {
            ((v) it.next()).cancel();
        }
    }

    public final void e(a3.d dVar) {
        if (!this.f771h.compareAndSet(false, true)) {
            throw new IllegalStateException("Already Executed");
        }
        k3.e eVar = k3.e.f1300a;
        this.f772i = k3.e.f1300a.h();
        a3.l lVar = this.f767d.f244a;
        m mVar = new m(this, dVar);
        lVar.getClass();
        a3.l.g(lVar, mVar, null, null, 6);
    }

    public final d0 f() {
        if (!this.f771h.compareAndSet(false, true)) {
            throw new IllegalStateException("Already Executed");
        }
        this.f770g.h();
        k3.e eVar = k3.e.f1300a;
        this.f772i = k3.e.f1300a.h();
        try {
            a3.l lVar = this.f767d.f244a;
            synchronized (lVar) {
                ((ArrayDeque) lVar.f187h).add(this);
            }
            d0 d0VarH = h();
            a3.l lVar2 = this.f767d.f244a;
            lVar2.getClass();
            a3.l.g(lVar2, null, this, null, 5);
            return d0VarH;
        } catch (Throwable th) {
            a3.l lVar3 = this.f767d.f244a;
            lVar3.getClass();
            a3.l.g(lVar3, null, this, null, 5);
            throw th;
        }
    }

    public final void g(boolean z3) {
        h hVar;
        synchronized (this) {
            if (!this.f781r) {
                throw new IllegalStateException("released");
            }
        }
        if (z3 && (hVar = this.f783t) != null) {
            ((f3.g) hVar.f752g).cancel();
            ((p) hVar.f750e).i(hVar, true, true, true, true, null);
        }
        this.f776m = null;
    }

    /* JADX WARN: Code duplicated, block: B:16:0x0077  */
    public final d0 h() {
        ArrayList arrayList = new ArrayList();
        v1.j.v0(arrayList, this.f767d.f245b);
        arrayList.add(new f3.a(this.f767d));
        arrayList.add(new f3.a(this.f767d.f253j));
        arrayList.add(new c3.a(0));
        arrayList.add(a.f705a);
        v1.j.v0(arrayList, this.f767d.f246c);
        arrayList.add(f3.c.f894a);
        a0 a0Var = this.f768e;
        a3.x xVar = this.f767d;
        boolean z3 = false;
        try {
            try {
                d0 d0VarB = new f3.i(this, arrayList, 0, null, a0Var, xVar.f265v, xVar.f266w, xVar.f267x).b(a0Var);
                if (this.f782s) {
                    b3.d.b(d0VarB);
                    throw new IOException("Canceled");
                }
                j(null);
                return d0VarB;
            } catch (IOException e4) {
                z3 = true;
                IOException iOExceptionJ = j(e4);
                j2.i.c(iOExceptionJ, "null cannot be cast to non-null type kotlin.Throwable");
                throw iOExceptionJ;
            }
        } catch (Throwable th) {
            if (!z3) {
                j(null);
            }
            throw th;
        }
        if (!z3) {
            j(null);
        }
        throw th;
    }

    /* JADX WARN: Code duplicated, block: B:22:0x002c A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:23:0x002e A[Catch: all -> 0x0018, TryCatch #0 {all -> 0x0018, blocks: (B:8:0x0013, B:23:0x002e, B:25:0x0032, B:27:0x0036, B:29:0x003a, B:30:0x003c, B:32:0x0041, B:34:0x0045, B:36:0x0049, B:41:0x0052, B:14:0x001c, B:17:0x0022, B:20:0x0028), top: B:57:0x0013 }] */
    /* JADX WARN: Code duplicated, block: B:25:0x0032 A[Catch: all -> 0x0018, TryCatch #0 {all -> 0x0018, blocks: (B:8:0x0013, B:23:0x002e, B:25:0x0032, B:27:0x0036, B:29:0x003a, B:30:0x003c, B:32:0x0041, B:34:0x0045, B:36:0x0049, B:41:0x0052, B:14:0x001c, B:17:0x0022, B:20:0x0028), top: B:57:0x0013 }] */
    /* JADX WARN: Code duplicated, block: B:27:0x0036 A[Catch: all -> 0x0018, TryCatch #0 {all -> 0x0018, blocks: (B:8:0x0013, B:23:0x002e, B:25:0x0032, B:27:0x0036, B:29:0x003a, B:30:0x003c, B:32:0x0041, B:34:0x0045, B:36:0x0049, B:41:0x0052, B:14:0x001c, B:17:0x0022, B:20:0x0028), top: B:57:0x0013 }] */
    /* JADX WARN: Code duplicated, block: B:29:0x003a A[Catch: all -> 0x0018, TryCatch #0 {all -> 0x0018, blocks: (B:8:0x0013, B:23:0x002e, B:25:0x0032, B:27:0x0036, B:29:0x003a, B:30:0x003c, B:32:0x0041, B:34:0x0045, B:36:0x0049, B:41:0x0052, B:14:0x001c, B:17:0x0022, B:20:0x0028), top: B:57:0x0013 }] */
    /* JADX WARN: Code duplicated, block: B:39:0x004f  */
    public final IOException i(h hVar, boolean z3, boolean z4, boolean z5, boolean z6, IOException iOException) {
        boolean z7;
        boolean z8;
        boolean z9;
        j2.i.e(hVar, "exchange");
        if (hVar.equals(this.f783t)) {
            synchronized (this) {
                z7 = false;
                if (z3) {
                    try {
                        if (this.f777n) {
                            if (z3) {
                                this.f777n = false;
                            }
                            if (z4) {
                                this.f778o = false;
                            }
                            if (z6) {
                                this.f779p = false;
                            }
                            if (z5) {
                                this.f780q = false;
                            }
                            if (this.f777n) {
                                z9 = false;
                            } else {
                                z9 = false;
                            }
                            if (z9) {
                                z7 = true;
                            }
                            boolean z10 = z9;
                            z8 = z7;
                            z7 = z10;
                        } else if ((!z4 && this.f778o) || ((z6 && this.f779p) || (z5 && this.f780q))) {
                            if (z3) {
                                this.f777n = false;
                            }
                            if (z4) {
                                this.f778o = false;
                            }
                            if (z6) {
                                this.f779p = false;
                            }
                            if (z5) {
                                this.f780q = false;
                            }
                            if (this.f777n || this.f778o || this.f779p || this.f780q) {
                                z9 = false;
                            } else {
                                z9 = true;
                            }
                            if (z9 && !this.f781r) {
                                z7 = true;
                            }
                            boolean z11 = z9;
                            z8 = z7;
                            z7 = z11;
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                } else {
                    z8 = !z4 ? false : false;
                }
            }
            if (z7) {
                this.f783t = null;
                q qVar = this.f774k;
                if (qVar != null) {
                    qVar.e();
                }
            }
            if (z8) {
                return c(iOException);
            }
        }
        return iOException;
    }

    public final IOException j(IOException iOException) {
        boolean z3;
        synchronized (this) {
            z3 = false;
            if (this.f781r) {
                this.f781r = false;
                if (!this.f777n && !this.f778o && !this.f779p && !this.f780q) {
                    z3 = true;
                }
            }
        }
        return z3 ? c(iOException) : iOException;
    }

    public final Socket k() {
        q qVar = this.f774k;
        j2.i.b(qVar);
        TimeZone timeZone = b3.g.f348a;
        ArrayList arrayList = qVar.f799p;
        int size = arrayList.size();
        int i4 = 0;
        int i5 = 0;
        while (true) {
            if (i5 >= size) {
                i4 = -1;
                break;
            }
            Object obj = arrayList.get(i5);
            i5++;
            if (j2.i.a(((Reference) obj).get(), this)) {
                break;
            }
            i4++;
        }
        if (i4 == -1) {
            throw new IllegalStateException("Check failed.");
        }
        arrayList.remove(i4);
        this.f774k = null;
        if (!arrayList.isEmpty()) {
            return null;
        }
        qVar.f800q = System.nanoTime();
        r rVar = this.f769f;
        ConcurrentLinkedQueue concurrentLinkedQueue = rVar.f804d;
        d3.c cVar = rVar.f802b;
        TimeZone timeZone2 = b3.g.f348a;
        if (!qVar.f793j) {
            cVar.d(rVar.f803c, 0L);
            return null;
        }
        qVar.f793j = true;
        concurrentLinkedQueue.remove(qVar);
        if (concurrentLinkedQueue.isEmpty()) {
            cVar.a();
        }
        return qVar.f788e;
    }
}
