package a1;

import a3.y;
import android.util.Log;
import d0.t;
import e3.w;
import h1.c0;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Pattern;
import javax.net.ssl.SSLSocket;
import l3.m;
import l3.o;
import o.f;
import p2.p;
import q3.h;
import v1.i;
import v1.l;
import y1.g;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class a implements m, a3.m, f, g, x.c {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static volatile a f9e;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f10d;

    public /* synthetic */ a(int i4) {
        this.f10d = i4;
    }

    public static final String g(h hVar, h[] hVarArr, int i4) {
        int i5;
        boolean z3;
        int i6;
        int i7;
        h hVar2 = n3.a.f1494b;
        int iA = hVar.a();
        int i8 = 0;
        while (i8 < iA) {
            int i9 = (i8 + iA) / 2;
            while (i9 > -1 && hVar.d(i9) != 10) {
                i9--;
            }
            int i10 = i9 + 1;
            int i11 = 1;
            while (true) {
                i5 = i10 + i11;
                if (hVar.d(i5) == 10) {
                    break;
                }
                i11++;
            }
            int i12 = i5 - i10;
            int i13 = i4;
            boolean z4 = false;
            int i14 = 0;
            int i15 = 0;
            while (true) {
                if (z4) {
                    i6 = 46;
                    z3 = false;
                } else {
                    byte bD = hVarArr[i13].d(i14);
                    byte[] bArr = b3.d.f343a;
                    int i16 = bD & 255;
                    z3 = z4;
                    i6 = i16;
                }
                byte bD2 = hVar.d(i10 + i15);
                byte[] bArr2 = b3.d.f343a;
                i7 = i6 - (bD2 & 255);
                if (i7 != 0) {
                    break;
                }
                i15++;
                i14++;
                if (i15 == i12) {
                    break;
                }
                if (hVarArr[i13].a() != i14) {
                    z4 = z3;
                } else {
                    if (i13 == hVarArr.length - 1) {
                        break;
                    }
                    i13++;
                    i14 = -1;
                    z4 = true;
                }
            }
            if (i7 >= 0) {
                if (i7 <= 0) {
                    int i17 = i12 - i15;
                    int iA2 = hVarArr[i13].a() - i14;
                    int length = hVarArr.length;
                    for (int i18 = i13 + 1; i18 < length; i18++) {
                        iA2 += hVarArr[i18].a();
                    }
                    if (iA2 >= i17) {
                        if (iA2 <= i17) {
                            return hVar.h(i10, i12 + i10).g(p2.a.f1738a);
                        }
                    }
                }
                i8 = i5 + 1;
            }
            iA = i9;
        }
        return null;
    }

    public static final void h(q3.c cVar) {
        w wVar = q3.c.f1810h;
        if (q3.c.f1811i == null) {
            q3.c.f1811i = new q3.c();
            q3.b bVar = new q3.b("Okio Watchdog");
            bVar.setDaemon(true);
            bVar.start();
        }
        long jNanoTime = System.nanoTime();
        long j4 = cVar.f1862c;
        boolean z3 = cVar.f1860a;
        if (j4 != 0 && z3) {
            cVar.f1818g = Math.min(j4, cVar.c() - jNanoTime) + jNanoTime;
        } else if (j4 != 0) {
            cVar.f1818g = jNanoTime + j4;
        } else {
            if (!z3) {
                throw new AssertionError();
            }
            cVar.f1818g = cVar.c();
        }
        w wVar2 = q3.c.f1810h;
        int i4 = wVar2.f825a + 1;
        wVar2.f825a = i4;
        q3.c[] cVarArr = (q3.c[]) wVar2.f826b;
        if (i4 == cVarArr.length) {
            q3.c[] cVarArr2 = new q3.c[i4 * 2];
            i.W(cVarArr, cVarArr2, 0, 0, 14);
            wVar2.f826b = cVarArr2;
        }
        wVar2.b(i4, cVar);
        if (cVar.f1817f == 1) {
            q3.c.f1813k.signal();
        }
    }

    public static ArrayList i(List list) {
        j2.i.e(list, "protocols");
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            if (((y) obj) != y.f271f) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = new ArrayList(l.u0(arrayList));
        int size = arrayList.size();
        int i4 = 0;
        while (i4 < size) {
            Object obj2 = arrayList.get(i4);
            i4++;
            arrayList2.add(((y) obj2).f279d);
        }
        return arrayList2;
    }

    public static q3.c j() throws InterruptedException {
        w wVar = q3.c.f1810h;
        q3.c cVar = ((q3.c[]) wVar.f826b)[1];
        if (cVar == null) {
            long jNanoTime = System.nanoTime();
            q3.c.f1813k.await(q3.c.f1814l, TimeUnit.MILLISECONDS);
            if (((q3.c[]) wVar.f826b)[1] != null || System.nanoTime() - jNanoTime < q3.c.f1815m) {
                return null;
            }
            return q3.c.f1811i;
        }
        long jNanoTime2 = cVar.f1818g - System.nanoTime();
        if (jNanoTime2 > 0) {
            q3.c.f1813k.await(jNanoTime2, TimeUnit.NANOSECONDS);
            return null;
        }
        wVar.d(cVar);
        cVar.f1816e = 2;
        return cVar;
    }

    public static byte[] k(List list) {
        j2.i.e(list, "protocols");
        q3.e eVar = new q3.e();
        ArrayList arrayListI = i(list);
        int size = arrayListI.size();
        int i4 = 0;
        while (i4 < size) {
            Object obj = arrayListI.get(i4);
            i4++;
            String str = (String) obj;
            eVar.X(str.length());
            eVar.c0(str);
        }
        return eVar.A(eVar.f1822e);
    }

    public static h l(String str) {
        if (str.length() % 2 != 0) {
            throw new IllegalArgumentException("Unexpected hex string: ".concat(str).toString());
        }
        int length = str.length() / 2;
        byte[] bArr = new byte[length];
        for (int i4 = 0; i4 < length; i4++) {
            int i5 = i4 * 2;
            bArr[i4] = (byte) (r3.b.a(str.charAt(i5 + 1)) + (r3.b.a(str.charAt(i5)) << 4));
        }
        return new h(bArr);
    }

    public static h m(String str) {
        j2.i.e(str, "<this>");
        byte[] bytes = str.getBytes(p2.a.f1738a);
        j2.i.d(bytes, "getBytes(...)");
        h hVar = new h(bytes);
        hVar.f1826f = str;
        return hVar;
    }

    public static void p(String str) {
        c0.f1036a.getClass();
        c0.D.e(c0.f1037b[25], str);
    }

    @Override // l3.m
    public boolean a(SSLSocket sSLSocket) {
        return p.z0(sSLSocket.getClass().getName(), "com.google.android.gms.org.conscrypt.", false);
    }

    @Override // o.f
    public void b() {
        switch (this.f10d) {
            case 10:
                break;
            default:
                Log.d("ProfileInstaller", "DIAGNOSTIC_PROFILE_IS_COMPRESSED");
                break;
        }
    }

    @Override // l3.m
    public o c(SSLSocket sSLSocket) {
        Class<?> cls = sSLSocket.getClass();
        Class<?> superclass = cls;
        while (!superclass.getSimpleName().equals("OpenSSLSocketImpl")) {
            superclass = superclass.getSuperclass();
            if (superclass == null) {
                throw new AssertionError("No OpenSSLSocketImpl superclass of socket of type " + cls);
            }
        }
        return new l3.f(superclass);
    }

    @Override // x.c
    public x.d d(x.b bVar) {
        return new y.h(bVar.f2662a, bVar.f2663b, bVar.f2664c, bVar.f2665d, bVar.f2666e);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v0, types: [v1.p] */
    /* JADX WARN: Type inference failed for: r2v1, types: [java.util.Collection, java.util.List] */
    /* JADX WARN: Type inference failed for: r2v6 */
    /* JADX WARN: Type inference failed for: r2v7, types: [java.util.ArrayList] */
    /* JADX WARN: Type inference failed for: r2v8, types: [java.util.ArrayList] */
    @Override // a3.m
    public List e(String str) throws UnknownHostException {
        j2.i.e(str, "hostname");
        ExecutorService executorService = n1.c.f1490b;
        ConcurrentHashMap concurrentHashMap = n1.c.f1492d;
        int length = str.length();
        ?? r4 = v1.p.f2517d;
        if (length != 0) {
            String string = "";
            if (str.length() != 0) {
                Pattern patternCompile = Pattern.compile("^https?://");
                j2.i.d(patternCompile, "compile(...)");
                String strReplaceFirst = patternCompile.matcher(str).replaceFirst("");
                j2.i.d(strReplaceFirst, "replaceFirst(...)");
                int iE0 = p2.i.E0(strReplaceFirst, '/', 0, 6);
                if (iE0 > 0) {
                    strReplaceFirst = strReplaceFirst.substring(0, iE0);
                    j2.i.d(strReplaceFirst, "substring(...)");
                }
                int iE1 = p2.i.E0(strReplaceFirst, ':', 0, 6);
                if (iE1 > 0) {
                    strReplaceFirst = strReplaceFirst.substring(0, iE1);
                    j2.i.d(strReplaceFirst, "substring(...)");
                }
                string = p2.i.S0(strReplaceFirst).toString();
            }
            n1.b bVar = (n1.b) concurrentHashMap.get(string);
            if (bVar == null || System.currentTimeMillis() - bVar.f1488b > n1.c.f1489a) {
                n1.a aVar = null;
                AtomicReference atomicReference = new AtomicReference(null);
                ArrayList arrayList = new ArrayList();
                for (String str2 : n1.c.f1491c) {
                    arrayList.add(n1.c.f1490b.submit(new t(string, str2, atomicReference, 5)));
                }
                long jCurrentTimeMillis = System.currentTimeMillis();
                while (System.currentTimeMillis() - jCurrentTimeMillis < 5000 && (aVar = (n1.a) atomicReference.get()) == null) {
                    try {
                        Thread.sleep(10L);
                    } catch (InterruptedException unused) {
                        Thread.currentThread().interrupt();
                    }
                }
                Iterator it = arrayList.iterator();
                j2.i.d(it, "iterator(...)");
                while (it.hasNext()) {
                    Object next = it.next();
                    j2.i.d(next, "next(...)");
                    Future future = (Future) next;
                    if (!future.isDone()) {
                        future.cancel(true);
                    }
                }
                if (aVar != null) {
                    r4 = aVar.f1485a;
                    concurrentHashMap.put(string, new n1.b(r4));
                } else {
                    try {
                        InetAddress[] allByName = InetAddress.getAllByName(string);
                        j2.i.b(allByName);
                        ArrayList arrayList2 = new ArrayList();
                        for (InetAddress inetAddress : allByName) {
                            String hostAddress = inetAddress.getHostAddress();
                            if (hostAddress != null) {
                                arrayList2.add(hostAddress);
                            }
                        }
                        if (!arrayList2.isEmpty()) {
                            concurrentHashMap.put(string, new n1.b(arrayList2));
                        }
                        r4 = arrayList2;
                    } catch (Exception unused2) {
                    }
                }
            } else {
                r4 = bVar.f1487a;
            }
        }
        if (!r4.isEmpty()) {
            ArrayList arrayList3 = new ArrayList();
            for (String str3 : r4) {
                try {
                    InetAddress[] allByName2 = InetAddress.getAllByName(str3);
                    j2.i.d(allByName2, "getAllByName(...)");
                    arrayList3.addAll(i.R(allByName2));
                } catch (Exception unused3) {
                    l3.h.c0("OkHttpDns: IP convert failed: " + str3);
                }
            }
            if (!arrayList3.isEmpty()) {
                l3.h.c0("OkHttpDns: Resolved " + str + " -> " + arrayList3);
                return arrayList3;
            }
        }
        try {
            InetAddress[] allByName3 = InetAddress.getAllByName(str);
            j2.i.d(allByName3, "getAllByName(...)");
            return i.d0(allByName3);
        } catch (NullPointerException e4) {
            UnknownHostException unknownHostException = new UnknownHostException("Broken system behaviour for dns lookup of ".concat(str));
            unknownHostException.initCause(e4);
            throw unknownHostException;
        }
    }

    @Override // o.f
    public void f(int i4, Object obj) {
        String str;
        switch (this.f10d) {
            case 10:
                break;
            default:
                switch (i4) {
                    case 1:
                        str = "RESULT_INSTALL_SUCCESS";
                        break;
                    case 2:
                        str = "RESULT_ALREADY_INSTALLED";
                        break;
                    case 3:
                        str = "RESULT_UNSUPPORTED_ART_VERSION";
                        break;
                    case 4:
                        str = "RESULT_NOT_WRITABLE";
                        break;
                    case 5:
                        str = "RESULT_DESIRED_FORMAT_UNSUPPORTED";
                        break;
                    case 6:
                        str = "RESULT_BASELINE_PROFILE_NOT_FOUND";
                        break;
                    case 7:
                        str = "RESULT_IO_EXCEPTION";
                        break;
                    case 8:
                        str = "RESULT_PARSE_EXCEPTION";
                        break;
                    case 9:
                    default:
                        str = "";
                        break;
                    case 10:
                        str = "RESULT_INSTALL_SKIP_FILE_SUCCESS";
                        break;
                    case 11:
                        str = "RESULT_DELETE_SKIP_FILE_SUCCESS";
                        break;
                }
                if (i4 == 6 || i4 == 7 || i4 == 8) {
                    Log.e("ProfileInstaller", str, (Throwable) obj);
                } else {
                    Log.d("ProfileInstaller", str);
                }
                break;
        }
    }

    private final void n() {
    }

    private final void o(int i4, Object obj) {
    }
}
