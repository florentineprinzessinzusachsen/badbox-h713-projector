package a3;

import android.os.Handler;
import android.os.Looper;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.net.Socket;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class l implements d, q3.t {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f183d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Object f184e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Object f185f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final Object f186g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final Object f187h;

    public l() {
        this.f183d = 0;
        this.f185f = new ArrayDeque();
        this.f186g = new ArrayDeque();
        this.f187h = new ArrayDeque();
    }

    public static void g(l lVar, e3.m mVar, e3.p pVar, e3.m mVar2, int i4) {
        h hVar;
        if ((i4 & 1) != 0) {
            mVar = null;
        }
        if ((i4 & 2) != 0) {
            pVar = null;
        }
        if ((i4 & 4) != 0) {
            mVar2 = null;
        }
        lVar.getClass();
        TimeZone timeZone = b3.g.f348a;
        boolean zIsShutdown = ((ThreadPoolExecutor) lVar.a()).isShutdown();
        synchronized (lVar) {
            if (pVar != null) {
                try {
                    if (!((ArrayDeque) lVar.f187h).remove(pVar)) {
                        throw new IllegalStateException("Call wasn't in-flight!");
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            if (mVar2 != null) {
                mVar2.f763e.decrementAndGet();
                if (!((ArrayDeque) lVar.f186g).remove(mVar2)) {
                    throw new IllegalStateException("Call wasn't in-flight!");
                }
            }
            if (mVar != null) {
                ((ArrayDeque) lVar.f185f).add(mVar);
                e3.m mVarF = lVar.f(((t) mVar.f764f.f768e.f63c).f211d);
                if (mVarF != null) {
                    mVar.f763e = mVarF.f763e;
                }
            }
            if ((pVar != null || mVar2 != null) && (zIsShutdown || ((ArrayDeque) lVar.f186g).isEmpty())) {
                ((ArrayDeque) lVar.f187h).isEmpty();
            }
            int i5 = 1;
            if (zIsShutdown) {
                List listG0 = v1.j.G0((ArrayDeque) lVar.f185f);
                ((ArrayDeque) lVar.f185f).clear();
                hVar = new h(i5, listG0);
            } else {
                ArrayList arrayList = new ArrayList();
                Iterator it = ((ArrayDeque) lVar.f185f).iterator();
                j2.i.d(it, "iterator(...)");
                while (it.hasNext()) {
                    e3.m mVar3 = (e3.m) it.next();
                    if (((ArrayDeque) lVar.f186g).size() >= 64) {
                        break;
                    }
                    if (mVar3.f763e.get() < 5) {
                        it.remove();
                        mVar3.f763e.incrementAndGet();
                        arrayList.add(mVar3);
                        ((ArrayDeque) lVar.f186g).add(mVar3);
                    }
                }
                hVar = new h(i5, arrayList);
            }
        }
        int size = ((List) hVar.f149e).size();
        for (int i6 = 0; i6 < size; i6++) {
            e3.m mVar4 = (e3.m) ((List) hVar.f149e).get(i6);
            if (mVar4 != mVar) {
                e3.p pVar2 = mVar4.f764f;
            }
            if (zIsShutdown) {
                mVar4.getClass();
                InterruptedIOException interruptedIOException = new InterruptedIOException("executor rejected");
                interruptedIOException.initCause(null);
                e3.p pVar3 = mVar4.f764f;
                pVar3.j(interruptedIOException);
                mVar4.f762d.e(pVar3, interruptedIOException);
            } else {
                ExecutorService executorServiceA = lVar.a();
                mVar4.getClass();
                e3.p pVar4 = mVar4.f764f;
                j2.i.e(pVar4.f767d.f244a, "<this>");
                try {
                    try {
                        ((ThreadPoolExecutor) executorServiceA).execute(mVar4);
                    } catch (RejectedExecutionException e4) {
                        InterruptedIOException interruptedIOException2 = new InterruptedIOException("executor rejected");
                        interruptedIOException2.initCause(e4);
                        e3.p pVar5 = mVar4.f764f;
                        pVar5.j(interruptedIOException2);
                        mVar4.f762d.e(pVar5, interruptedIOException2);
                        l lVar2 = pVar4.f767d.f244a;
                        lVar2.getClass();
                        g(lVar2, null, null, mVar4, 3);
                    }
                } catch (Throwable th2) {
                    l lVar3 = pVar4.f767d.f244a;
                    lVar3.getClass();
                    g(lVar3, null, null, mVar4, 3);
                    throw th2;
                }
            }
        }
    }

    public synchronized ExecutorService a() {
        ThreadPoolExecutor threadPoolExecutor;
        try {
            if (((ThreadPoolExecutor) this.f184e) == null) {
                TimeUnit timeUnit = TimeUnit.SECONDS;
                SynchronousQueue synchronousQueue = new SynchronousQueue();
                String str = b3.g.f349b + " Dispatcher";
                j2.i.e(str, "name");
                this.f184e = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, timeUnit, synchronousQueue, new b3.f(str, false));
            }
            threadPoolExecutor = (ThreadPoolExecutor) this.f184e;
            j2.i.b(threadPoolExecutor);
        } catch (Throwable th) {
            throw th;
        }
        return threadPoolExecutor;
    }

    @Override // a3.d
    public void b(e3.p pVar, d0 d0Var) throws IOException {
        if (!d0Var.f118s) {
            if (q1.f.f1798b == pVar) {
                x xVar = q1.f.f1797a;
                q1.f.f1798b = null;
            }
            q1.f.f1799c.remove((String) this.f184e);
            ((q1.a) this.f185f).f(Boolean.FALSE, new IOException("Unexpected code " + d0Var));
            return;
        }
        f0 f0Var = d0Var.f109j;
        if (f0Var == null) {
            String str = (String) this.f184e;
            q1.a aVar = (q1.a) this.f185f;
            if (q1.f.f1798b == pVar) {
                x xVar2 = q1.f.f1797a;
                q1.f.f1798b = null;
            }
            q1.f.f1799c.remove(str);
            aVar.f(Boolean.FALSE, new IOException("Empty response body"));
            return;
        }
        InputStream inputStreamQ = f0Var.k().Q();
        FileOutputStream fileOutputStream = new FileOutputStream((File) this.f186g);
        byte[] bArr = new byte[8192];
        f0 f0Var2 = d0Var.f109j;
        long jB = f0Var2 != null ? f0Var2.b() : 0L;
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStreamQ);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
        int i4 = -1;
        long j4 = 0;
        while (true) {
            try {
                int i5 = bufferedInputStream.read(bArr);
                if (i5 == -1) {
                    bufferedOutputStream.flush();
                    bufferedInputStream.close();
                    bufferedOutputStream.close();
                    q1.f.f1799c.remove((String) this.f184e);
                    if (q1.f.f1798b == pVar) {
                        q1.f.f1798b = null;
                    }
                    ((q1.a) this.f185f).f(Boolean.TRUE, null);
                    return;
                }
                if (pVar.f782s) {
                    bufferedInputStream.close();
                    bufferedOutputStream.close();
                    ((File) this.f186g).delete();
                    q1.f.f1799c.remove((String) this.f184e);
                    if (q1.f.f1798b == pVar) {
                        q1.f.f1798b = null;
                        return;
                    }
                    return;
                }
                bufferedOutputStream.write(bArr, 0, i5);
                j4 += (long) i5;
                int i6 = jB > 0 ? (int) ((((long) 100) * j4) / jB) : 0;
                if (i6 > i4) {
                    ((d0.h) this.f187h).h(Integer.valueOf(i6));
                    i4 = i6;
                }
            } catch (IOException e4) {
                bufferedInputStream.close();
                bufferedOutputStream.close();
                ((File) this.f186g).delete();
                q1.f.f1799c.remove((String) this.f184e);
                if (q1.f.f1798b == pVar) {
                    q1.f.f1798b = null;
                }
                if (pVar.f782s) {
                    return;
                }
                ((q1.a) this.f185f).f(Boolean.FALSE, e4);
                return;
            }
        }
    }

    @Override // q3.t
    public q3.s c() {
        return (r3.c) this.f187h;
    }

    @Override // q3.t
    public q3.u d() {
        return (r3.d) this.f186g;
    }

    @Override // a3.d
    public void e(e3.p pVar, IOException iOException) {
        if (q1.f.f1798b == pVar) {
            x xVar = q1.f.f1797a;
            q1.f.f1798b = null;
        }
        q1.f.f1799c.remove((String) this.f184e);
        ((q1.a) this.f185f).f(Boolean.FALSE, iOException);
    }

    public e3.m f(String str) {
        Iterator it = ((ArrayDeque) this.f186g).iterator();
        j2.i.d(it, "iterator(...)");
        while (it.hasNext()) {
            e3.m mVar = (e3.m) it.next();
            if (j2.i.a(((t) mVar.f764f.f768e.f63c).f211d, str)) {
                return mVar;
            }
        }
        Iterator it2 = ((ArrayDeque) this.f185f).iterator();
        j2.i.d(it2, "iterator(...)");
        while (it2.hasNext()) {
            e3.m mVar2 = (e3.m) it2.next();
            if (j2.i.a(((t) mVar2.f764f.f768e.f63c).f211d, str)) {
                return mVar2;
            }
        }
        return null;
    }

    public String toString() {
        switch (this.f183d) {
            case 3:
                String string = ((Socket) this.f184e).toString();
                j2.i.d(string, "toString(...)");
                return string;
            default:
                return super.toString();
        }
    }

    public l(Socket socket) {
        this.f183d = 3;
        this.f184e = socket;
        this.f185f = new AtomicInteger();
        this.f186g = new r3.d(this);
        this.f187h = new r3.c(this);
    }

    public l(ExecutorService executorService) {
        this.f183d = 1;
        this.f186g = new Handler(Looper.getMainLooper());
        this.f187h = new n0.a(this);
        m0.j jVar = new m0.j(executorService, 0);
        this.f184e = jVar;
        this.f185f = r2.x.i(jVar);
    }

    public l(String str, q1.a aVar, File file, d0.h hVar) {
        this.f183d = 2;
        this.f184e = str;
        this.f185f = aVar;
        this.f186g = file;
        this.f187h = hVar;
    }
}
