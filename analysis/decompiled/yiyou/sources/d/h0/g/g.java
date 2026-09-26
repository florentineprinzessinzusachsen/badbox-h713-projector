package d.h0.g;

import d.a0;
import d.c0;
import d.p;
import d.u;
import java.util.List;

/* JADX INFO: compiled from: RealInterceptorChain.java */
/* JADX INFO: loaded from: classes.dex */
public final class g implements u.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final List<u> f4416a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final d.h0.f.g f4417b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final c f4418c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final d.h0.f.c f4419d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final int f4420e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final a0 f4421f;
    private final d.e g;
    private final p h;
    private final int i;
    private final int j;
    private final int k;
    private int l;

    public g(List<u> list, d.h0.f.g gVar, c cVar, d.h0.f.c cVar2, int i, a0 a0Var, d.e eVar, p pVar, int i2, int i3, int i4) {
        this.f4416a = list;
        this.f4419d = cVar2;
        this.f4417b = gVar;
        this.f4418c = cVar;
        this.f4420e = i;
        this.f4421f = a0Var;
        this.g = eVar;
        this.h = pVar;
        this.i = i2;
        this.j = i3;
        this.k = i4;
    }

    @Override // d.u.a
    public int a() {
        return this.k;
    }

    @Override // d.u.a
    public int b() {
        return this.i;
    }

    @Override // d.u.a
    public int c() {
        return this.j;
    }

    @Override // d.u.a
    public d.i d() {
        return this.f4419d;
    }

    public d.e e() {
        return this.g;
    }

    public p f() {
        return this.h;
    }

    public c g() {
        return this.f4418c;
    }

    public d.h0.f.g h() {
        return this.f4417b;
    }

    @Override // d.u.a
    public a0 request() {
        return this.f4421f;
    }

    @Override // d.u.a
    public c0 a(a0 a0Var) {
        return a(a0Var, this.f4417b, this.f4418c, this.f4419d);
    }

    public c0 a(a0 a0Var, d.h0.f.g gVar, c cVar, d.h0.f.c cVar2) {
        if (this.f4420e < this.f4416a.size()) {
            this.l++;
            if (this.f4418c != null && !this.f4419d.a(a0Var.g())) {
                throw new IllegalStateException("network interceptor " + this.f4416a.get(this.f4420e - 1) + " must retain the same host and port");
            }
            if (this.f4418c != null && this.l > 1) {
                throw new IllegalStateException("network interceptor " + this.f4416a.get(this.f4420e - 1) + " must call proceed() exactly once");
            }
            g gVar2 = new g(this.f4416a, gVar, cVar, cVar2, this.f4420e + 1, a0Var, this.g, this.h, this.i, this.j, this.k);
            u uVar = this.f4416a.get(this.f4420e);
            c0 c0VarIntercept = uVar.intercept(gVar2);
            if (cVar != null && this.f4420e + 1 < this.f4416a.size() && gVar2.l != 1) {
                throw new IllegalStateException("network interceptor " + uVar + " must call proceed() exactly once");
            }
            if (c0VarIntercept != null) {
                if (c0VarIntercept.a() != null) {
                    return c0VarIntercept;
                }
                throw new IllegalStateException("interceptor " + uVar + " returned a response with no body");
            }
            throw new NullPointerException("interceptor " + uVar + " returned null");
        }
        throw new AssertionError();
    }
}
