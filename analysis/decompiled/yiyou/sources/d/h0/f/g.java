package d.h0.f;

import d.e0;
import d.h0.i.n;
import d.j;
import d.p;
import d.u;
import d.x;
import java.io.IOException;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.net.Socket;
import java.util.List;

/* JADX INFO: compiled from: StreamAllocation.java */
/* JADX INFO: loaded from: classes.dex */
public final class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final d.a f4403a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private f.a f4404b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private e0 f4405c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final j f4406d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final d.e f4407e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final p f4408f;
    private final Object g;
    private final f h;
    private int i;
    private c j;
    private boolean k;
    private boolean l;
    private boolean m;
    private d.h0.g.c n;

    /* JADX INFO: compiled from: StreamAllocation.java */
    public static final class a extends WeakReference<g> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Object f4409a;

        a(g gVar, Object obj) {
            super(gVar);
            this.f4409a = obj;
        }
    }

    public g(j jVar, d.a aVar, d.e eVar, p pVar, Object obj) {
        this.f4406d = jVar;
        this.f4403a = aVar;
        this.f4407e = eVar;
        this.f4408f = pVar;
        this.h = new f(aVar, i(), eVar, pVar);
        this.g = obj;
    }

    private Socket h() {
        c cVar = this.j;
        if (cVar == null || !cVar.k) {
            return null;
        }
        return a(false, false, true);
    }

    private d i() {
        return d.h0.a.f4335a.a(this.f4406d);
    }

    public d.h0.g.c a(x xVar, u.a aVar, boolean z) {
        try {
            d.h0.g.c cVarA = a(aVar.b(), aVar.c(), aVar.a(), xVar.p(), xVar.v(), z).a(xVar, aVar, this);
            synchronized (this.f4406d) {
                this.n = cVarA;
            }
            return cVarA;
        } catch (IOException e2) {
            throw new e(e2);
        }
    }

    public d.h0.g.c b() {
        d.h0.g.c cVar;
        synchronized (this.f4406d) {
            cVar = this.n;
        }
        return cVar;
    }

    public synchronized c c() {
        return this.j;
    }

    public boolean d() {
        f.a aVar;
        return this.f4405c != null || ((aVar = this.f4404b) != null && aVar.b()) || this.h.a();
    }

    public void e() {
        c cVar;
        Socket socketA;
        synchronized (this.f4406d) {
            cVar = this.j;
            socketA = a(true, false, false);
            if (this.j != null) {
                cVar = null;
            }
        }
        d.h0.c.a(socketA);
        if (cVar != null) {
            this.f4408f.b(this.f4407e, cVar);
        }
    }

    public void f() {
        c cVar;
        Socket socketA;
        synchronized (this.f4406d) {
            cVar = this.j;
            socketA = a(false, true, false);
            if (this.j != null) {
                cVar = null;
            }
        }
        d.h0.c.a(socketA);
        if (cVar != null) {
            this.f4408f.b(this.f4407e, cVar);
            this.f4408f.a(this.f4407e);
        }
    }

    public e0 g() {
        return this.f4405c;
    }

    public String toString() {
        c cVarC = c();
        return cVarC != null ? cVarC.toString() : this.f4403a.toString();
    }

    private void b(c cVar) {
        int size = cVar.n.size();
        for (int i = 0; i < size; i++) {
            if (cVar.n.get(i).get() == this) {
                cVar.n.remove(i);
                return;
            }
        }
        throw new IllegalStateException();
    }

    private c a(int i, int i2, int i3, int i4, boolean z, boolean z2) throws Throwable {
        while (true) {
            c cVarA = a(i, i2, i3, i4, z);
            synchronized (this.f4406d) {
                if (cVarA.l == 0) {
                    return cVarA;
                }
                if (cVarA.a(z2)) {
                    return cVarA;
                }
                e();
            }
        }
    }

    private c a(int i, int i2, int i3, int i4, boolean z) throws Throwable {
        Socket socketH;
        Socket socketA;
        c cVar;
        c cVar2;
        e0 e0VarC;
        boolean z2;
        boolean z3;
        f.a aVar;
        synchronized (this.f4406d) {
            if (!this.l) {
                if (this.n == null) {
                    if (!this.m) {
                        c cVar3 = this.j;
                        socketH = h();
                        socketA = null;
                        if (this.j != null) {
                            cVar2 = this.j;
                            cVar = null;
                        } else {
                            cVar = cVar3;
                            cVar2 = null;
                        }
                        if (!this.k) {
                            cVar = null;
                        }
                        if (cVar2 == null) {
                            d.h0.a.f4335a.a(this.f4406d, this.f4403a, this, null);
                            if (this.j != null) {
                                cVar2 = this.j;
                                e0VarC = null;
                                z2 = true;
                            } else {
                                e0VarC = this.f4405c;
                            }
                        } else {
                            e0VarC = null;
                        }
                        z2 = false;
                    } else {
                        throw new IOException("Canceled");
                    }
                } else {
                    throw new IllegalStateException("codec != null");
                }
            } else {
                throw new IllegalStateException("released");
            }
        }
        d.h0.c.a(socketH);
        if (cVar != null) {
            this.f4408f.b(this.f4407e, cVar);
        }
        if (z2) {
            this.f4408f.a(this.f4407e, cVar2);
        }
        if (cVar2 != null) {
            return cVar2;
        }
        if (e0VarC != null || ((aVar = this.f4404b) != null && aVar.b())) {
            z3 = false;
        } else {
            this.f4404b = this.h.b();
            z3 = true;
        }
        synchronized (this.f4406d) {
            if (this.m) {
                throw new IOException("Canceled");
            }
            if (z3) {
                List<e0> listA = this.f4404b.a();
                int size = listA.size();
                for (int i5 = 0; i5 < size; i5++) {
                    e0 e0Var = listA.get(i5);
                    d.h0.a.f4335a.a(this.f4406d, this.f4403a, this, e0Var);
                    if (this.j != null) {
                        cVar2 = this.j;
                        this.f4405c = e0Var;
                        z2 = true;
                        break;
                    }
                }
            }
            if (!z2) {
                if (e0VarC == null) {
                    e0VarC = this.f4404b.c();
                }
                this.f4405c = e0VarC;
                this.i = 0;
                cVar2 = new c(this.f4406d, e0VarC);
                a(cVar2, false);
            }
        }
        if (z2) {
            this.f4408f.a(this.f4407e, cVar2);
            return cVar2;
        }
        cVar2.a(i, i2, i3, i4, z, this.f4407e, this.f4408f);
        i().a(cVar2.e());
        synchronized (this.f4406d) {
            this.k = true;
            d.h0.a.f4335a.b(this.f4406d, cVar2);
            if (cVar2.d()) {
                socketA = d.h0.a.f4335a.a(this.f4406d, this.f4403a, this);
                cVar2 = this.j;
            }
        }
        d.h0.c.a(socketA);
        this.f4408f.a(this.f4407e, cVar2);
        return cVar2;
    }

    public void a(boolean z, d.h0.g.c cVar, long j, IOException iOException) {
        c cVar2;
        Socket socketA;
        boolean z2;
        this.f4408f.b(this.f4407e, j);
        synchronized (this.f4406d) {
            if (cVar != null) {
                if (cVar == this.n) {
                    if (!z) {
                        this.j.l++;
                    }
                    cVar2 = this.j;
                    socketA = a(z, false, true);
                    if (this.j != null) {
                        cVar2 = null;
                    }
                    z2 = this.l;
                }
            }
            throw new IllegalStateException("expected " + this.n + " but was " + cVar);
        }
        d.h0.c.a(socketA);
        if (cVar2 != null) {
            this.f4408f.b(this.f4407e, cVar2);
        }
        if (iOException != null) {
            this.f4408f.a(this.f4407e, iOException);
        } else if (z2) {
            this.f4408f.a(this.f4407e);
        }
    }

    /* JADX WARN: Code duplicated, block: B:23:0x004a  */
    private Socket a(boolean z, boolean z2, boolean z3) {
        Socket socketF;
        if (z3) {
            this.n = null;
        }
        if (z2) {
            this.l = true;
        }
        c cVar = this.j;
        if (cVar != null) {
            if (z) {
                cVar.k = true;
            }
            if (this.n == null && (this.l || this.j.k)) {
                b(this.j);
                if (this.j.n.isEmpty()) {
                    this.j.o = System.nanoTime();
                    if (d.h0.a.f4335a.a(this.f4406d, this.j)) {
                        socketF = this.j.f();
                    } else {
                        socketF = null;
                    }
                } else {
                    socketF = null;
                }
                this.j = null;
                return socketF;
            }
        }
        return null;
    }

    public void a() {
        d.h0.g.c cVar;
        c cVar2;
        synchronized (this.f4406d) {
            this.m = true;
            cVar = this.n;
            cVar2 = this.j;
        }
        if (cVar != null) {
            cVar.cancel();
        } else if (cVar2 != null) {
            cVar2.b();
        }
    }

    /* JADX WARN: Code duplicated, block: B:28:0x004c  */
    public void a(IOException iOException) {
        boolean z;
        c cVar;
        Socket socketA;
        synchronized (this.f4406d) {
            if (iOException instanceof n) {
                d.h0.i.b bVar = ((n) iOException).f4581a;
                if (bVar == d.h0.i.b.REFUSED_STREAM) {
                    this.i++;
                    if (this.i > 1) {
                        this.f4405c = null;
                        z = true;
                    } else {
                        z = false;
                    }
                } else if (bVar != d.h0.i.b.CANCEL) {
                    this.f4405c = null;
                    z = true;
                } else {
                    z = false;
                }
            } else if (this.j == null || (this.j.d() && !(iOException instanceof d.h0.i.a))) {
                z = false;
            } else {
                if (this.j.l == 0) {
                    if (this.f4405c != null && iOException != null) {
                        this.h.a(this.f4405c, iOException);
                    }
                    this.f4405c = null;
                }
                z = true;
            }
            cVar = this.j;
            socketA = a(z, false, true);
            if (this.j != null || !this.k) {
                cVar = null;
            }
        }
        d.h0.c.a(socketA);
        if (cVar != null) {
            this.f4408f.b(this.f4407e, cVar);
        }
    }

    public void a(c cVar, boolean z) {
        if (this.j == null) {
            this.j = cVar;
            this.k = z;
            cVar.n.add(new a(this, this.g));
            return;
        }
        throw new IllegalStateException();
    }

    public Socket a(c cVar) {
        if (this.n == null && this.j.n.size() == 1) {
            Reference<g> reference = this.j.n.get(0);
            Socket socketA = a(true, false, false);
            this.j = cVar;
            cVar.n.add(reference);
            return socketA;
        }
        throw new IllegalStateException();
    }
}
