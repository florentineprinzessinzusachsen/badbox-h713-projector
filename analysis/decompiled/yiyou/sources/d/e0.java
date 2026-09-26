package d;

import java.net.InetSocketAddress;
import java.net.Proxy;

/* JADX INFO: compiled from: Route.java */
/* JADX INFO: loaded from: classes.dex */
public final class e0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final a f4312a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final Proxy f4313b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final InetSocketAddress f4314c;

    public e0(a aVar, Proxy proxy, InetSocketAddress inetSocketAddress) {
        if (aVar == null) {
            throw new NullPointerException("address == null");
        }
        if (proxy == null) {
            throw new NullPointerException("proxy == null");
        }
        if (inetSocketAddress == null) {
            throw new NullPointerException("inetSocketAddress == null");
        }
        this.f4312a = aVar;
        this.f4313b = proxy;
        this.f4314c = inetSocketAddress;
    }

    public a a() {
        return this.f4312a;
    }

    public Proxy b() {
        return this.f4313b;
    }

    public boolean c() {
        return this.f4312a.i != null && this.f4313b.type() == Proxy.Type.HTTP;
    }

    public InetSocketAddress d() {
        return this.f4314c;
    }

    public boolean equals(Object obj) {
        if (obj instanceof e0) {
            e0 e0Var = (e0) obj;
            if (e0Var.f4312a.equals(this.f4312a) && e0Var.f4313b.equals(this.f4313b) && e0Var.f4314c.equals(this.f4314c)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return ((((527 + this.f4312a.hashCode()) * 31) + this.f4313b.hashCode()) * 31) + this.f4314c.hashCode();
    }

    public String toString() {
        return "Route{" + this.f4314c + "}";
    }
}
