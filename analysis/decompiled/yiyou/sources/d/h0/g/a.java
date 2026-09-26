package d.h0.g;

import d.a0;
import d.b0;
import d.c0;
import d.l;
import d.m;
import d.s;
import d.u;
import d.v;
import java.util.List;

/* JADX INFO: compiled from: BridgeInterceptor.java */
/* JADX INFO: loaded from: classes.dex */
public final class a implements u {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final m f4410a;

    public a(m mVar) {
        this.f4410a = mVar;
    }

    private String a(List<l> list) {
        StringBuilder sb = new StringBuilder();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                sb.append("; ");
            }
            l lVar = list.get(i);
            sb.append(lVar.a());
            sb.append('=');
            sb.append(lVar.b());
        }
        return sb.toString();
    }

    @Override // d.u
    public c0 intercept(u.a aVar) {
        a0 a0VarRequest = aVar.request();
        a0.a aVarF = a0VarRequest.f();
        b0 b0VarA = a0VarRequest.a();
        if (b0VarA != null) {
            v vVarContentType = b0VarA.contentType();
            if (vVarContentType != null) {
                aVarF.b("Content-Type", vVarContentType.toString());
            }
            long jContentLength = b0VarA.contentLength();
            if (jContentLength != -1) {
                aVarF.b("Content-Length", Long.toString(jContentLength));
                aVarF.a("Transfer-Encoding");
            } else {
                aVarF.b("Transfer-Encoding", "chunked");
                aVarF.a("Content-Length");
            }
        }
        boolean z = false;
        if (a0VarRequest.a("Host") == null) {
            aVarF.b("Host", d.h0.c.a(a0VarRequest.g(), false));
        }
        if (a0VarRequest.a("Connection") == null) {
            aVarF.b("Connection", "Keep-Alive");
        }
        if (a0VarRequest.a("Accept-Encoding") == null && a0VarRequest.a("Range") == null) {
            z = true;
            aVarF.b("Accept-Encoding", "gzip");
        }
        List<l> listA = this.f4410a.a(a0VarRequest.g());
        if (!listA.isEmpty()) {
            aVarF.b("Cookie", a(listA));
        }
        if (a0VarRequest.a("User-Agent") == null) {
            aVarF.b("User-Agent", d.h0.d.a());
        }
        c0 c0VarA = aVar.a(aVarF.a());
        e.a(this.f4410a, a0VarRequest.g(), c0VarA.o());
        c0.a aVarS = c0VarA.s();
        aVarS.a(a0VarRequest);
        if (z && "gzip".equalsIgnoreCase(c0VarA.a("Content-Encoding")) && e.b(c0VarA)) {
            e.j jVar = new e.j(c0VarA.a().source());
            s.a aVarA = c0VarA.o().a();
            aVarA.c("Content-Encoding");
            aVarA.c("Content-Length");
            aVarS.a(aVarA.a());
            aVarS.a(new h(c0VarA.a("Content-Type"), -1L, e.l.a(jVar)));
        }
        return aVarS.a();
    }
}
