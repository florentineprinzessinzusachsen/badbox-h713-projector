package d;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.List;

/* JADX INFO: compiled from: EventListener.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final p f4650a = new a();

    /* JADX INFO: compiled from: EventListener.java */
    class a extends p {
        a() {
        }
    }

    /* JADX INFO: compiled from: EventListener.java */
    class b implements c {
        b() {
        }

        @Override // d.p.c
        public p a(e eVar) {
            return p.this;
        }
    }

    /* JADX INFO: compiled from: EventListener.java */
    public interface c {
        p a(e eVar);
    }

    static c a(p pVar) {
        return pVar.new b();
    }

    public void a(e eVar) {
    }

    public void a(e eVar, long j) {
    }

    public void a(e eVar, a0 a0Var) {
    }

    public void a(e eVar, c0 c0Var) {
    }

    public void a(e eVar, i iVar) {
    }

    public void a(e eVar, r rVar) {
    }

    public void a(e eVar, IOException iOException) {
    }

    public void a(e eVar, String str) {
    }

    public void a(e eVar, String str, List<InetAddress> list) {
    }

    public void a(e eVar, InetSocketAddress inetSocketAddress, Proxy proxy) {
    }

    public void a(e eVar, InetSocketAddress inetSocketAddress, Proxy proxy, y yVar) {
    }

    public void a(e eVar, InetSocketAddress inetSocketAddress, Proxy proxy, y yVar, IOException iOException) {
    }

    public void b(e eVar) {
    }

    public void b(e eVar, long j) {
    }

    public void b(e eVar, i iVar) {
    }

    public void c(e eVar) {
    }

    public void d(e eVar) {
    }

    public void e(e eVar) {
    }

    public void f(e eVar) {
    }

    public void g(e eVar) {
    }
}
