package d.h0.f;

import d.a0;
import d.c0;
import d.u;
import d.x;

/* JADX INFO: compiled from: ConnectInterceptor.java */
/* JADX INFO: loaded from: classes.dex */
public final class a implements u {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final x f4382a;

    public a(x xVar) {
        this.f4382a = xVar;
    }

    @Override // d.u
    public c0 intercept(u.a aVar) {
        d.h0.g.g gVar = (d.h0.g.g) aVar;
        a0 a0VarRequest = gVar.request();
        g gVarH = gVar.h();
        return gVar.a(a0VarRequest, gVarH, gVarH.a(this.f4382a, aVar, !a0VarRequest.e().equals("GET")), gVarH.c());
    }
}
