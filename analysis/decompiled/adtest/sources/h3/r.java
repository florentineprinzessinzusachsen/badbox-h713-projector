package h3;

import d0.l0;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ProtocolException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class r implements f3.g {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final List f1154g = b3.g.k(new String[]{"connection", "host", "keep-alive", "proxy-connection", "te", "transfer-encoding", "encoding", "upgrade", ":method", ":path", ":scheme", ":authority"});

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final List f1155h = b3.g.k(new String[]{"connection", "host", "keep-alive", "proxy-connection", "te", "transfer-encoding", "encoding", "upgrade"});

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final e3.q f1156a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final f3.i f1157b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final q f1158c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public volatile y f1159d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final a3.y f1160e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public volatile boolean f1161f;

    public r(a3.x xVar, e3.q qVar, f3.i iVar, q qVar2) {
        j2.i.e(qVar2, "http2Connection");
        this.f1156a = qVar;
        this.f1157b = iVar;
        this.f1158c = qVar2;
        List list = xVar.f261r;
        a3.y yVar = a3.y.f275j;
        this.f1160e = list.contains(yVar) ? yVar : a3.y.f274i;
    }

    @Override // f3.g
    public final void a() {
        y yVar = this.f1159d;
        j2.i.b(yVar);
        yVar.f1191l.close();
    }

    @Override // f3.g
    public final boolean b() {
        boolean z3;
        y yVar = this.f1159d;
        if (yVar == null) {
            return false;
        }
        synchronized (yVar) {
            w wVar = yVar.f1190k;
            z3 = wVar.f1177e && wVar.f1179g.c();
        }
        return z3;
    }

    @Override // f3.g
    public final void c() {
        this.f1158c.flush();
    }

    @Override // f3.g
    public final void cancel() {
        this.f1161f = true;
        y yVar = this.f1159d;
        if (yVar != null) {
            yVar.g(b.CANCEL);
        }
    }

    @Override // f3.g
    public final q3.t d() {
        y yVar = this.f1159d;
        j2.i.b(yVar);
        return yVar;
    }

    @Override // f3.g
    public final q3.s e(a3.a0 a0Var, long j4) {
        y yVar = this.f1159d;
        j2.i.b(yVar);
        return yVar.f1191l;
    }

    @Override // f3.g
    public final long f(a3.d0 d0Var) {
        if (f3.h.a(d0Var)) {
            return b3.g.e(d0Var);
        }
        return 0L;
    }

    @Override // f3.g
    public final q3.u g(a3.d0 d0Var) {
        y yVar = this.f1159d;
        j2.i.b(yVar);
        return yVar.f1190k;
    }

    /* JADX WARN: Code duplicated, block: B:19:0x002c  */
    @Override // f3.g
    public final a3.c0 h(boolean z3) throws IOException {
        a3.r rVar;
        boolean z4;
        y yVar = this.f1159d;
        if (yVar == null) {
            throw new IOException("stream wasn't created");
        }
        synchronized (yVar) {
            while (true) {
                if (!yVar.f1188i.isEmpty() || yVar.h() != null) {
                    break;
                }
                if (!z3) {
                    yVar.f1184e.getClass();
                    v vVar = yVar.f1191l;
                    z4 = vVar.f1174f || vVar.f1172d;
                }
                if (z4) {
                    yVar.f1192m.h();
                }
                try {
                    try {
                        yVar.wait();
                        if (z4) {
                            yVar.f1192m.l();
                        }
                    } catch (InterruptedException unused) {
                        Thread.currentThread().interrupt();
                        throw new InterruptedIOException();
                    }
                } catch (Throwable th) {
                    if (z4) {
                        yVar.f1192m.l();
                    }
                    throw th;
                }
            }
            if (yVar.f1188i.isEmpty()) {
                IOException iOException = yVar.f1195p;
                if (iOException != null) {
                    throw iOException;
                }
                b bVarH = yVar.h();
                j2.i.b(bVarH);
                throw new e0(bVarH);
            }
            Object objRemoveFirst = yVar.f1188i.removeFirst();
            j2.i.d(objRemoveFirst, "removeFirst(...)");
            rVar = (a3.r) objRemoveFirst;
        }
        a3.y yVar2 = this.f1160e;
        j2.i.e(yVar2, "protocol");
        ArrayList arrayList = new ArrayList(20);
        int size = rVar.size();
        f3.k kVarC = null;
        for (int i4 = 0; i4 < size; i4++) {
            String strB = rVar.b(i4);
            String strD = rVar.d(i4);
            if (strB.equals(":status")) {
                kVarC = l0.C("HTTP/1.1 ".concat(strD));
            } else if (!f1155h.contains(strB)) {
                arrayList.add(strB);
                arrayList.add(p2.i.S0(strD).toString());
            }
        }
        if (kVarC == null) {
            throw new ProtocolException("Expected ':status' header not present");
        }
        a3.c0 c0Var = new a3.c0();
        c0Var.f89b = yVar2;
        c0Var.f90c = kVarC.f910b;
        c0Var.f91d = kVarC.f911c;
        c0Var.f93f = new a3.r((String[]) arrayList.toArray(new String[0])).c();
        if (z3 && c0Var.f90c == 100) {
            return null;
        }
        return c0Var;
    }

    @Override // f3.g
    public final f3.f i() {
        return this.f1156a;
    }

    @Override // f3.g
    public final void j(a3.a0 a0Var) throws IOException {
        int i4;
        y yVar;
        boolean z3;
        if (this.f1159d != null) {
            return;
        }
        boolean z4 = ((a3.b0) a0Var.f65e) != null;
        a3.r rVar = (a3.r) a0Var.f64d;
        ArrayList arrayList = new ArrayList(rVar.size() + 4);
        arrayList.add(new d(d.f1081f, a0Var.f62b));
        q3.h hVar = d.f1082g;
        a3.t tVar = (a3.t) a0Var.f63c;
        j2.i.e(tVar, "url");
        String strB = tVar.b();
        String strD = tVar.d();
        if (strD != null) {
            strB = strB + '?' + strD;
        }
        arrayList.add(new d(hVar, strB));
        String strA = ((a3.r) a0Var.f64d).a("Host");
        if (strA != null) {
            arrayList.add(new d(d.f1084i, strA));
        }
        arrayList.add(new d(d.f1083h, tVar.f208a));
        int size = rVar.size();
        for (int i5 = 0; i5 < size; i5++) {
            String strB2 = rVar.b(i5);
            Locale locale = Locale.US;
            j2.i.d(locale, "US");
            String lowerCase = strB2.toLowerCase(locale);
            j2.i.d(lowerCase, "toLowerCase(...)");
            if (!f1154g.contains(lowerCase) || (lowerCase.equals("te") && rVar.d(i5).equals("trailers"))) {
                arrayList.add(new d(lowerCase, rVar.d(i5)));
            }
        }
        q qVar = this.f1158c;
        qVar.getClass();
        boolean z5 = !z4;
        synchronized (qVar.f1153z) {
            synchronized (qVar) {
                try {
                    if (qVar.f1135h > 1073741823) {
                        qVar.A(b.REFUSED_STREAM);
                    }
                    if (qVar.f1136i) {
                        throw new a();
                    }
                    i4 = qVar.f1135h;
                    qVar.f1135h = i4 + 2;
                    yVar = new y(i4, qVar, z5, false, null);
                    z3 = !z4 || qVar.f1150w >= qVar.f1151x || yVar.f1186g >= yVar.f1187h;
                    if (yVar.j()) {
                        qVar.f1132e.put(Integer.valueOf(i4), yVar);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            qVar.f1153z.A(z5, i4, arrayList);
        }
        if (z3) {
            qVar.f1153z.flush();
        }
        this.f1159d = yVar;
        if (this.f1161f) {
            y yVar2 = this.f1159d;
            j2.i.b(yVar2);
            yVar2.g(b.CANCEL);
            throw new IOException("Canceled");
        }
        y yVar3 = this.f1159d;
        j2.i.b(yVar3);
        x xVar = yVar3.f1192m;
        long j4 = this.f1157b.f903g;
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        xVar.g(j4);
        y yVar4 = this.f1159d;
        j2.i.b(yVar4);
        yVar4.f1193n.g(this.f1157b.f904h);
    }
}
