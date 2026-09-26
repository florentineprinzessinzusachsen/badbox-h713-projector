package a3;

import d0.l0;
import java.net.ProxySelector;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class x {
    public static final List B = b3.g.k(new y[]{y.f274i, y.f272g});
    public static final List C = b3.g.k(new j[]{j.f163e, j.f164f});
    public final h A;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final l f244a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final List f245b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final List f246c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final b3.e f247d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final boolean f248e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final boolean f249f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final b f250g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final boolean f251h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final boolean f252i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final b f253j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final m f254k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final ProxySelector f255l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final b f256m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final SocketFactory f257n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final SSLSocketFactory f258o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final X509TrustManager f259p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final List f260q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final List f261r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final HostnameVerifier f262s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final e f263t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final l0 f264u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final int f265v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final int f266w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final int f267x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final h f268y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public final d3.e f269z;

    public x(w wVar) throws NoSuchAlgorithmException, KeyStoreException {
        this.f244a = wVar.f220a;
        this.f245b = b3.g.j(wVar.f222c);
        this.f246c = b3.g.j(wVar.f223d);
        this.f247d = wVar.f224e;
        this.f248e = wVar.f225f;
        this.f249f = wVar.f226g;
        this.f250g = wVar.f227h;
        this.f251h = wVar.f228i;
        this.f252i = wVar.f229j;
        this.f253j = wVar.f230k;
        this.f254k = wVar.f231l;
        ProxySelector proxySelector = ProxySelector.getDefault();
        this.f255l = proxySelector == null ? m3.a.f1458a : proxySelector;
        this.f256m = wVar.f232m;
        this.f257n = wVar.f233n;
        List list = wVar.f236q;
        this.f260q = list;
        this.f261r = wVar.f237r;
        this.f262s = wVar.f238s;
        this.f265v = wVar.f241v;
        this.f266w = wVar.f242w;
        this.f267x = wVar.f243x;
        this.f268y = new h(4, (byte) 0);
        this.f269z = d3.e.f542l;
        h hVar = wVar.f221b;
        if (hVar == null) {
            hVar = new h(0, (byte) 0);
            wVar.f221b = hVar;
        }
        this.A = hVar;
        if (list != null && list.isEmpty()) {
            this.f258o = null;
            this.f264u = null;
            this.f259p = null;
            this.f263t = e.f119c;
            break;
        }
        Iterator it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                this.f258o = null;
                this.f264u = null;
                this.f259p = null;
                this.f263t = e.f119c;
                break;
            }
            if (((j) it.next()).f165a) {
                SSLSocketFactory sSLSocketFactory = wVar.f234o;
                if (sSLSocketFactory != null) {
                    this.f258o = sSLSocketFactory;
                    l0 l0Var = wVar.f240u;
                    j2.i.b(l0Var);
                    this.f264u = l0Var;
                    l1.a aVar = wVar.f235p;
                    j2.i.b(aVar);
                    this.f259p = aVar;
                    e eVar = wVar.f239t;
                    eVar.getClass();
                    this.f263t = j2.i.a(eVar.f121b, l0Var) ? eVar : new e(eVar.f120a, l0Var);
                    break;
                }
                k3.e eVar2 = k3.e.f1300a;
                k3.e.f1300a.getClass();
                TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
                trustManagerFactory.init((KeyStore) null);
                TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
                j2.i.b(trustManagers);
                if (trustManagers.length == 1) {
                    TrustManager trustManager = trustManagers[0];
                    if (trustManager instanceof X509TrustManager) {
                        X509TrustManager x509TrustManager = (X509TrustManager) trustManager;
                        this.f259p = x509TrustManager;
                        k3.e eVar3 = k3.e.f1300a;
                        eVar3.getClass();
                        try {
                            SSLContext sSLContextL = eVar3.l();
                            sSLContextL.init(null, new TrustManager[]{x509TrustManager}, null);
                            SSLSocketFactory socketFactory = sSLContextL.getSocketFactory();
                            j2.i.d(socketFactory, "getSocketFactory(...)");
                            this.f258o = socketFactory;
                            l0 l0VarC = k3.e.f1300a.c(x509TrustManager);
                            this.f264u = l0VarC;
                            e eVar4 = wVar.f239t;
                            eVar4.getClass();
                            this.f263t = j2.i.a(eVar4.f121b, l0VarC) ? eVar4 : new e(eVar4.f120a, l0VarC);
                            break;
                        } catch (GeneralSecurityException e4) {
                            throw new AssertionError("No System TLS: " + e4, e4);
                        }
                    }
                }
                String string = Arrays.toString(trustManagers);
                j2.i.d(string, "toString(...)");
                throw new IllegalStateException("Unexpected default trust managers: ".concat(string).toString());
            }
        }
        X509TrustManager x509TrustManager2 = this.f259p;
        l0 l0Var2 = this.f264u;
        SSLSocketFactory sSLSocketFactory2 = this.f258o;
        List list2 = this.f246c;
        List list3 = this.f245b;
        j2.i.c(list3, "null cannot be cast to non-null type kotlin.collections.List<okhttp3.Interceptor?>");
        if (list3.contains(null)) {
            throw new IllegalStateException(("Null interceptor: " + list3).toString());
        }
        j2.i.c(list2, "null cannot be cast to non-null type kotlin.collections.List<okhttp3.Interceptor?>");
        if (list2.contains(null)) {
            throw new IllegalStateException(("Null network interceptor: " + list2).toString());
        }
        List list4 = this.f260q;
        if (list4 == null || !list4.isEmpty()) {
            Iterator it2 = list4.iterator();
            while (it2.hasNext()) {
                if (((j) it2.next()).f165a) {
                    if (sSLSocketFactory2 == null) {
                        throw new IllegalStateException("sslSocketFactory == null");
                    }
                    if (l0Var2 == null) {
                        throw new IllegalStateException("certificateChainCleaner == null");
                    }
                    if (x509TrustManager2 == null) {
                        throw new IllegalStateException("x509TrustManager == null");
                    }
                    return;
                }
            }
        }
        if (sSLSocketFactory2 != null) {
            throw new IllegalStateException("Check failed.");
        }
        if (l0Var2 != null) {
            throw new IllegalStateException("Check failed.");
        }
        if (x509TrustManager2 != null) {
            throw new IllegalStateException("Check failed.");
        }
        if (!j2.i.a(this.f263t, e.f119c)) {
            throw new IllegalStateException("Check failed.");
        }
    }
}
