package h3;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.TimeZone;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class y implements q3.t {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f1183d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final q f1184e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final i3.a f1185f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public long f1186g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public long f1187h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final ArrayDeque f1188i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f1189j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final w f1190k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final v f1191l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final x f1192m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final x f1193n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public b f1194o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public IOException f1195p;

    public y(int i4, q qVar, boolean z3, boolean z4, a3.r rVar) {
        j2.i.e(qVar, "connection");
        this.f1183d = i4;
        this.f1184e = qVar;
        this.f1185f = new i3.a(i4);
        this.f1187h = qVar.f1148u.a();
        ArrayDeque arrayDeque = new ArrayDeque();
        this.f1188i = arrayDeque;
        this.f1190k = new w(this, qVar.f1147t.a(), z4);
        this.f1191l = new v(this, z3);
        this.f1192m = new x(this);
        this.f1193n = new x(this);
        if (rVar == null) {
            if (!i()) {
                throw new IllegalStateException("remotely-initiated streams should have headers");
            }
        } else {
            if (i()) {
                throw new IllegalStateException("locally-initiated streams shouldn't have headers yet");
            }
            arrayDeque.add(rVar);
        }
    }

    /* JADX WARN: Code duplicated, block: B:16:0x001c  */
    public final void a() {
        boolean z3;
        boolean zJ;
        TimeZone timeZone = b3.g.f348a;
        synchronized (this) {
            try {
                w wVar = this.f1190k;
                if (wVar.f1177e || !wVar.f1180h) {
                    z3 = false;
                } else {
                    v vVar = this.f1191l;
                    if (vVar.f1172d || vVar.f1174f) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                }
                zJ = j();
            } catch (Throwable th) {
                throw th;
            }
        }
        if (z3) {
            e(b.CANCEL, null);
        } else {
            if (zJ) {
                return;
            }
            this.f1184e.l(this.f1183d);
        }
    }

    public final void b() throws IOException {
        v vVar = this.f1191l;
        if (vVar.f1174f) {
            throw new IOException("stream closed");
        }
        if (vVar.f1172d) {
            throw new IOException("stream finished");
        }
        if (h() != null) {
            IOException iOException = this.f1195p;
            if (iOException != null) {
                throw iOException;
            }
            b bVarH = h();
            j2.i.b(bVarH);
            throw new e0(bVarH);
        }
    }

    @Override // q3.t
    public final q3.s c() {
        return this.f1191l;
    }

    @Override // q3.t
    public final q3.u d() {
        return this.f1190k;
    }

    public final void e(b bVar, IOException iOException) {
        if (f(bVar, iOException)) {
            this.f1184e.f1153z.J(this.f1183d, bVar);
        }
    }

    public final boolean f(b bVar, IOException iOException) {
        TimeZone timeZone = b3.g.f348a;
        synchronized (this) {
            if (h() != null) {
                return false;
            }
            this.f1194o = bVar;
            this.f1195p = iOException;
            notifyAll();
            if (this.f1190k.f1177e && this.f1191l.f1172d) {
                return false;
            }
            this.f1184e.l(this.f1183d);
            return true;
        }
    }

    public final void g(b bVar) {
        if (f(bVar, null)) {
            this.f1184e.K(this.f1183d, bVar);
        }
    }

    public final b h() {
        b bVar;
        synchronized (this) {
            bVar = this.f1194o;
        }
        return bVar;
    }

    public final boolean i() {
        boolean z3 = (this.f1183d & 1) == 1;
        this.f1184e.getClass();
        return true == z3;
    }

    public final boolean j() {
        synchronized (this) {
            try {
                if (h() != null) {
                    return false;
                }
                w wVar = this.f1190k;
                if (wVar.f1177e || wVar.f1180h) {
                    v vVar = this.f1191l;
                    if ((vVar.f1172d || vVar.f1174f) && this.f1189j) {
                        return false;
                    }
                }
                return true;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void k(a3.r rVar, boolean z3) {
        boolean zJ;
        j2.i.e(rVar, "headers");
        TimeZone timeZone = b3.g.f348a;
        synchronized (this) {
            try {
                if (this.f1189j && rVar.a(":status") == null && rVar.a(":method") == null) {
                    this.f1190k.getClass();
                } else {
                    this.f1189j = true;
                    this.f1188i.add(rVar);
                }
                if (z3) {
                    this.f1190k.f1177e = true;
                }
                zJ = j();
                notifyAll();
            } catch (Throwable th) {
                throw th;
            }
        }
        if (zJ) {
            return;
        }
        this.f1184e.l(this.f1183d);
    }

    public final void l(b bVar) {
        synchronized (this) {
            if (h() == null) {
                this.f1194o = bVar;
                notifyAll();
            }
        }
    }
}
