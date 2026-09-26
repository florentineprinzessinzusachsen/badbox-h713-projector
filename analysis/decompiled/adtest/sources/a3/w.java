package a3;

import d0.l0;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class w {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public h f221b;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final b3.e f224e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final boolean f225f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final boolean f226g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final b f227h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final boolean f228i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final boolean f229j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final b f230k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public m f231l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final b f232m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final SocketFactory f233n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public SSLSocketFactory f234o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public l1.a f235p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final List f236q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final List f237r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public HostnameVerifier f238s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final e f239t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public l0 f240u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public int f241v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public int f242w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public int f243x;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final l f220a = new l();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final ArrayList f222c = new ArrayList();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final ArrayList f223d = new ArrayList();

    public w() {
        TimeZone timeZone = b3.g.f348a;
        this.f224e = new b3.e(0);
        this.f225f = true;
        this.f226g = true;
        b bVar = b.f68d;
        this.f227h = bVar;
        this.f228i = true;
        this.f229j = true;
        this.f230k = b.f69e;
        this.f231l = m.f188a;
        this.f232m = bVar;
        SocketFactory socketFactory = SocketFactory.getDefault();
        j2.i.d(socketFactory, "getDefault(...)");
        this.f233n = socketFactory;
        this.f236q = x.C;
        this.f237r = x.B;
        this.f238s = o3.c.f1576a;
        this.f239t = e.f119c;
        this.f241v = 10000;
        this.f242w = 10000;
        this.f243x = 10000;
    }

    public final void a(long j4) {
        j2.i.e(TimeUnit.SECONDS, "unit");
        this.f241v = b3.g.b(j4);
    }

    public final void b(long j4) {
        j2.i.e(TimeUnit.SECONDS, "unit");
        this.f242w = b3.g.b(j4);
    }

    public final void c(SSLSocketFactory sSLSocketFactory, l1.a aVar) {
        if (sSLSocketFactory.equals(this.f234o)) {
            aVar.equals(this.f235p);
        }
        this.f234o = sSLSocketFactory;
        k3.e eVar = k3.e.f1300a;
        this.f240u = k3.e.f1300a.c(aVar);
        this.f235p = aVar;
    }

    public final void d(long j4) {
        j2.i.e(TimeUnit.SECONDS, "unit");
        this.f243x = b3.g.b(j4);
    }
}
