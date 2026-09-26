package c3;

import a3.a0;
import a3.d;
import a3.d0;
import com.speed.adv.AdService;
import com.speed.net.ApiResponse;
import e3.g;
import f1.j;
import f1.l;
import f1.n;
import f1.o;
import f1.s;
import j2.i;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.channels.FileChannel;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;
import l0.k;
import l1.f;
import l3.h;
import p.p;
import p.r;
import q3.t;
import q3.u;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class b implements t, d, w.b {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Object f372d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Object f373e;

    public b(String str) {
        this.f372d = str.concat(".lck");
    }

    @Override // w.b
    public w.a a(String str) {
        FileChannel fileChannel;
        FileChannel fileChannel2;
        i.e(str, "fileName");
        p pVar = (p) this.f373e;
        if (!str.equals(":memory:")) {
            str = pVar.f1685c.f1577a.getDatabasePath(str).getAbsolutePath();
            i.b(str);
        }
        boolean z3 = true;
        q.a aVar = new q.a(str, (pVar.f1683a || pVar.f1684b || str.equals(":memory:")) ? false : true);
        ReentrantLock reentrantLock = aVar.f1767a;
        reentrantLock.lock();
        b bVar = aVar.f1768b;
        if (bVar != null) {
            try {
                bVar.h();
            } catch (Throwable th) {
                th = th;
                z3 = false;
            }
        }
        try {
            try {
                if (pVar.f1684b) {
                    throw new IllegalStateException("Recursive database initialization detected. Did you try to use the database instance during initialization? Maybe in one of the callbacks?");
                }
                w.a aVarA = ((w.b) this.f372d).a(str);
                if (pVar.f1683a) {
                    if (pVar.f1685c.f1583g == r.f1711f) {
                        h.y(aVarA, "PRAGMA synchronous = NORMAL");
                    } else {
                        h.y(aVarA, "PRAGMA synchronous = FULL");
                    }
                    p.b(aVarA);
                    pVar.f1686d.d(aVarA);
                } else {
                    try {
                        pVar.f1684b = true;
                        p.a(pVar, aVarA);
                        pVar.f1684b = false;
                    } catch (Throwable th2) {
                        pVar.f1684b = false;
                        throw th2;
                    }
                }
                if (bVar != null && (fileChannel2 = (FileChannel) bVar.f373e) != null) {
                    try {
                        fileChannel2.close();
                        bVar.f373e = null;
                    } catch (Throwable th3) {
                        bVar.f373e = null;
                        throw th3;
                    }
                }
                reentrantLock.unlock();
                return aVarA;
            } catch (Throwable th4) {
                if (bVar != null && (fileChannel = (FileChannel) bVar.f373e) != null) {
                    try {
                        fileChannel.close();
                    } finally {
                        bVar.f373e = null;
                    }
                }
                throw th4;
            }
        } catch (Throwable th5) {
            th = th5;
        }
        th = th5;
        try {
            if (z3) {
                throw th;
            }
            throw new IllegalStateException("Unable to open database '" + str + "'. Was a proper path / name used in Room's database builder?", th);
        } catch (Throwable th6) {
            reentrantLock.unlock();
            throw th6;
        }
    }

    @Override // a3.d
    public void b(e3.p pVar, d0 d0Var) {
        AdService adService = (AdService) this.f373e;
        if (!d0Var.f118s) {
            f.f1370c.post(new f1.p(d0Var, adService, 1));
            return;
        }
        try {
            String strL = d0Var.f109j.l();
            if (strL.length() != 0 && !i.a(p2.i.S0(strL).toString(), "null")) {
                try {
                    try {
                        h.d0(d0Var, strL);
                        Object objB = f.f1369b.b(strL, (Type) this.f372d);
                        i.d(objB, "fromJson(...)");
                        f.f1370c.post(new l((ApiResponse) objB, strL, adService, 1));
                        return;
                    } catch (Exception e4) {
                        h.c0("直接解析为T失败: " + e4.getMessage() + ", , 响应数据: " + strL);
                        f.f1370c.post(new o(e4, adService, 2));
                        return;
                    }
                } catch (Exception unused) {
                    f.f1370c.post(new n(f.f1369b.b(strL, new s().f2779b), adService, 1));
                    return;
                }
            }
            f.f1370c.post(new j(adService, 1));
        } catch (Exception e5) {
            h.c0("解密或解析失败: " + e5.getMessage() + ", 响应数据: ");
            f.f1370c.post(new o(e5, adService, 3));
        }
    }

    @Override // q3.t
    public q3.s c() {
        return (e3.f) this.f372d;
    }

    @Override // q3.t
    public u d() {
        return (g) this.f373e;
    }

    @Override // a3.d
    public void e(e3.p pVar, IOException iOException) {
        f.f1370c.post(new f1.i(iOException, (AdService) this.f373e, 1));
    }

    public boolean f() {
        synchronized (this) {
            if (((AtomicBoolean) this.f373e).get()) {
                return false;
            }
            ((AtomicInteger) this.f372d).incrementAndGet();
            return true;
        }
    }

    public boolean g(k kVar) {
        boolean zContainsKey;
        synchronized (this.f373e) {
            zContainsKey = ((d0.i) this.f372d).f460a.containsKey(kVar);
        }
        return zContainsKey;
    }

    public void h() throws IOException {
        String str = (String) this.f372d;
        if (((FileChannel) this.f373e) != null) {
            return;
        }
        try {
            File file = new File(str);
            File parentFile = file.getParentFile();
            if (parentFile != null) {
                parentFile.mkdirs();
            }
            FileChannel channel = new FileOutputStream(file).getChannel();
            this.f373e = channel;
            if (channel != null) {
                channel.lock();
            }
        } catch (Throwable th) {
            FileChannel fileChannel = (FileChannel) this.f373e;
            if (fileChannel != null) {
                fileChannel.close();
            }
            this.f373e = null;
            throw new IllegalStateException("Unable to lock file: '" + str + "'.", th);
        }
    }

    public e0.l i(k kVar) {
        e0.l lVarC;
        i.e(kVar, "id");
        synchronized (this.f373e) {
            lVarC = ((d0.i) this.f372d).c(kVar);
        }
        return lVarC;
    }

    public void j(e0.l lVar, int i4) {
        i.e(lVar, "workSpecId");
        a3.l lVar2 = (a3.l) this.f373e;
        ((m0.j) lVar2.f184e).execute(new m0.k((e0.f) this.f372d, lVar, false, i4));
    }

    public e0.l k(k kVar) {
        e0.l lVarE;
        synchronized (this.f373e) {
            lVarE = ((d0.i) this.f372d).e(kVar);
        }
        return lVarE;
    }

    public void l() {
        synchronized (this) {
            ((AtomicInteger) this.f372d).decrementAndGet();
            if (((AtomicInteger) this.f372d).get() < 0) {
                throw new IllegalStateException("Unbalanced call to unblock() detected.");
            }
        }
    }

    public b(p.s sVar) {
        this.f372d = new AtomicInteger(0);
        this.f373e = new AtomicBoolean(false);
    }

    public b(a0 a0Var, d0 d0Var) {
        this.f372d = a0Var;
        this.f373e = d0Var;
    }

    public b(e0.f fVar, a3.l lVar) {
        i.e(fVar, "processor");
        i.e(lVar, "workTaskExecutor");
        this.f372d = fVar;
        this.f373e = lVar;
    }

    public b(p pVar, w.b bVar) {
        i.e(bVar, "actual");
        this.f373e = pVar;
        this.f372d = bVar;
    }

    public b(d0.i iVar) {
        this.f372d = iVar;
        this.f373e = new Object();
    }

    public b(e3.h hVar) {
        f3.g gVar = (f3.g) hVar.f752g;
        this.f372d = new e3.f(hVar, gVar.d().c(), -1L, true);
        this.f373e = new g(hVar, gVar.d().d(), -1L, true);
    }

    public b(Type type, AdService adService) {
        this.f372d = type;
        this.f373e = adService;
    }
}
