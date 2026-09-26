package d.h0.f;

import com.baidu.mobstat.Config;
import d.e0;
import d.p;
import d.t;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

/* JADX INFO: compiled from: RouteSelector.java */
/* JADX INFO: loaded from: classes.dex */
public final class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final d.a f4395a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final d f4396b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final d.e f4397c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final p f4398d;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private int f4400f;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private List<Proxy> f4399e = Collections.emptyList();
    private List<InetSocketAddress> g = Collections.emptyList();
    private final List<e0> h = new ArrayList();

    /* JADX INFO: compiled from: RouteSelector.java */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final List<e0> f4401a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private int f4402b = 0;

        a(List<e0> list) {
            this.f4401a = list;
        }

        public List<e0> a() {
            return new ArrayList(this.f4401a);
        }

        public boolean b() {
            return this.f4402b < this.f4401a.size();
        }

        public e0 c() {
            if (!b()) {
                throw new NoSuchElementException();
            }
            List<e0> list = this.f4401a;
            int i = this.f4402b;
            this.f4402b = i + 1;
            return list.get(i);
        }
    }

    public f(d.a aVar, d dVar, d.e eVar, p pVar) {
        this.f4395a = aVar;
        this.f4396b = dVar;
        this.f4397c = eVar;
        this.f4398d = pVar;
        a(aVar.k(), aVar.f());
    }

    private boolean c() {
        return this.f4400f < this.f4399e.size();
    }

    private Proxy d() throws SocketException, UnknownHostException {
        if (c()) {
            List<Proxy> list = this.f4399e;
            int i = this.f4400f;
            this.f4400f = i + 1;
            Proxy proxy = list.get(i);
            a(proxy);
            return proxy;
        }
        throw new SocketException("No route to " + this.f4395a.k().g() + "; exhausted proxy configurations: " + this.f4399e);
    }

    public boolean a() {
        return c() || !this.h.isEmpty();
    }

    public a b() throws SocketException, UnknownHostException {
        if (!a()) {
            throw new NoSuchElementException();
        }
        ArrayList arrayList = new ArrayList();
        while (c()) {
            Proxy proxyD = d();
            int size = this.g.size();
            for (int i = 0; i < size; i++) {
                e0 e0Var = new e0(this.f4395a, proxyD, this.g.get(i));
                if (this.f4396b.c(e0Var)) {
                    this.h.add(e0Var);
                } else {
                    arrayList.add(e0Var);
                }
            }
            if (!arrayList.isEmpty()) {
                break;
            }
        }
        if (arrayList.isEmpty()) {
            arrayList.addAll(this.h);
            this.h.clear();
        }
        return new a(arrayList);
    }

    public void a(e0 e0Var, IOException iOException) {
        if (e0Var.b().type() != Proxy.Type.DIRECT && this.f4395a.h() != null) {
            this.f4395a.h().connectFailed(this.f4395a.k().p(), e0Var.b().address(), iOException);
        }
        this.f4396b.b(e0Var);
    }

    private void a(t tVar, Proxy proxy) {
        if (proxy != null) {
            this.f4399e = Collections.singletonList(proxy);
        } else {
            List<Proxy> listSelect = this.f4395a.h().select(tVar.p());
            this.f4399e = (listSelect == null || listSelect.isEmpty()) ? d.h0.c.a(Proxy.NO_PROXY) : d.h0.c.a(listSelect);
        }
        this.f4400f = 0;
    }

    private void a(Proxy proxy) throws SocketException, UnknownHostException {
        String strG;
        int iK;
        this.g = new ArrayList();
        if (proxy.type() != Proxy.Type.DIRECT && proxy.type() != Proxy.Type.SOCKS) {
            SocketAddress socketAddressAddress = proxy.address();
            if (socketAddressAddress instanceof InetSocketAddress) {
                InetSocketAddress inetSocketAddress = (InetSocketAddress) socketAddressAddress;
                strG = a(inetSocketAddress);
                iK = inetSocketAddress.getPort();
            } else {
                throw new IllegalArgumentException("Proxy.address() is not an InetSocketAddress: " + socketAddressAddress.getClass());
            }
        } else {
            strG = this.f4395a.k().g();
            iK = this.f4395a.k().k();
        }
        if (iK >= 1 && iK <= 65535) {
            if (proxy.type() == Proxy.Type.SOCKS) {
                this.g.add(InetSocketAddress.createUnresolved(strG, iK));
                return;
            }
            this.f4398d.a(this.f4397c, strG);
            List<InetAddress> listA = this.f4395a.c().a(strG);
            if (!listA.isEmpty()) {
                this.f4398d.a(this.f4397c, strG, listA);
                int size = listA.size();
                for (int i = 0; i < size; i++) {
                    this.g.add(new InetSocketAddress(listA.get(i), iK));
                }
                return;
            }
            throw new UnknownHostException(this.f4395a.c() + " returned no addresses for " + strG);
        }
        throw new SocketException("No route to " + strG + Config.TRACE_TODAY_VISIT_SPLIT + iK + "; port is out of range");
    }

    static String a(InetSocketAddress inetSocketAddress) {
        InetAddress address = inetSocketAddress.getAddress();
        if (address == null) {
            return inetSocketAddress.getHostName();
        }
        return address.getHostAddress();
    }
}
