package a;

import a3.g;
import a3.h0;
import a3.o;
import a3.t;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Trace;
import android.util.Log;
import com.speed.adv.AdService;
import com.speed.net.daemon.KeepAliveWorker;
import d0.a0;
import d0.c0;
import d0.j0;
import d0.l0;
import d0.n;
import e0.q;
import e0.y;
import i2.p;
import j2.d;
import j2.i;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import l3.h;
import org.json.JSONException;
import org.json.JSONObject;
import r2.v0;
import r2.w;
import r2.x;
import r3.b;
import t2.e;
import u0.l;
import v.j;
import w.c;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public abstract class a {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static long f0d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static Method f1e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static Method f2f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static Method f3g;

    public static final j A(w.a aVar, String str, boolean z3) {
        c cVarP = aVar.P("PRAGMA index_xinfo(`" + str + "`)");
        try {
            int iL = h.l(cVarP, "seqno");
            int iL2 = h.l(cVarP, "cid");
            int iL3 = h.l(cVarP, "name");
            int iL4 = h.l(cVarP, "desc");
            if (iL != -1 && iL2 != -1 && iL3 != -1 && iL4 != -1) {
                LinkedHashMap linkedHashMap = new LinkedHashMap();
                LinkedHashMap linkedHashMap2 = new LinkedHashMap();
                while (cVarP.F()) {
                    if (((int) cVarP.getLong(iL2)) >= 0) {
                        int i4 = (int) cVarP.getLong(iL);
                        String strN = cVarP.n(iL3);
                        String str2 = cVarP.getLong(iL4) > 0 ? "DESC" : "ASC";
                        linkedHashMap.put(Integer.valueOf(i4), strN);
                        linkedHashMap2.put(Integer.valueOf(i4), str2);
                    }
                }
                List listD0 = v1.j.D0(linkedHashMap.entrySet(), new l(1));
                ArrayList arrayList = new ArrayList(v1.l.u0(listD0));
                Iterator it = listD0.iterator();
                while (it.hasNext()) {
                    arrayList.add((String) ((Map.Entry) it.next()).getValue());
                }
                List listG0 = v1.j.G0(arrayList);
                List listD1 = v1.j.D0(linkedHashMap2.entrySet(), new l(2));
                ArrayList arrayList2 = new ArrayList(v1.l.u0(listD1));
                Iterator it2 = listD1.iterator();
                while (it2.hasNext()) {
                    arrayList2.add((String) ((Map.Entry) it2.next()).getValue());
                }
                j jVar = new j(str, z3, listG0, v1.j.G0(arrayList2));
                h.k(cVarP, null);
                return jVar;
            }
            h.k(cVarP, null);
            return null;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                h.k(cVarP, th);
                throw th2;
            }
        }
    }

    public static final void B(Object[] objArr, int i4, int i5) {
        i.e(objArr, "<this>");
        while (i4 < i5) {
            objArr[i4] = null;
            i4++;
        }
    }

    public static final Object C(p pVar) {
        Thread.interrupted();
        return x.s(y1.i.f2726d, new v.a(pVar, null));
    }

    public static void D(AdService adService) {
        TimeUnit timeUnit = TimeUnit.MINUTES;
        i.e(timeUnit, "repeatIntervalTimeUnit");
        c0 c0Var = new c0(KeepAliveWorker.class, 1);
        l0.p pVar = c0Var.f427b;
        long millis = timeUnit.toMillis(15L);
        pVar.getClass();
        String str = l0.p.f1330z;
        if (millis < 900000) {
            a0.e().h(str, "Interval duration lesser than minimum allowed value; Changed to 900000");
        }
        long j4 = millis < 900000 ? 900000L : millis;
        long j5 = millis < 900000 ? 900000L : millis;
        if (j4 < 900000) {
            a0.e().h(str, "Interval duration lesser than minimum allowed value; Changed to 900000");
        }
        pVar.f1338h = j4 >= 900000 ? j4 : 900000L;
        if (j5 < 300000) {
            a0.e().h(str, "Flex duration lesser than minimum allowed value; Changed to 300000");
        }
        if (j5 > pVar.f1338h) {
            a0.e().h(str, "Flex duration greater than interval duration; Changed to " + j4);
        }
        pVar.f1339i = l0.j(j5, 300000L, pVar.f1338h);
        new q(y.S(adService), "keep_alive_heartbeat", n.f484e, Collections.singletonList((j0) c0Var.a()), 0).J();
        Log.i("KeepAliveWorker", "WorkManager periodic heartbeat scheduled");
    }

    public static final void E(String str, long j4) {
        try {
            Class.forName("android.os.SystemProperties").getMethod("set", String.class, String.class).invoke(null, str, String.valueOf(j4));
        } catch (Exception unused) {
        }
    }

    public static final long F(String str, long j4) {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            Object objInvoke = cls.getMethod("getLong", String.class, Long.TYPE).invoke(cls, str, Long.valueOf(j4));
            i.c(objInvoke, "null cannot be cast to non-null type kotlin.Long");
            return ((Long) objInvoke).longValue();
        } catch (Exception unused) {
            return j4;
        }
    }

    public static String G(String str) {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            Object objInvoke = cls.getMethod("get", String.class, String.class).invoke(cls, str, "");
            i.c(objInvoke, "null cannot be cast to non-null type kotlin.String");
            return (String) objInvoke;
        } catch (Exception unused) {
            return "";
        }
    }

    public static final String H(int i4) {
        if (i4 == 0) {
            return "0";
        }
        char[] cArr = b.f2057a;
        int i5 = 0;
        char[] cArr2 = {cArr[(i4 >> 28) & 15], cArr[(i4 >> 24) & 15], cArr[(i4 >> 20) & 15], cArr[(i4 >> 16) & 15], cArr[(i4 >> 12) & 15], cArr[(i4 >> 8) & 15], cArr[(i4 >> 4) & 15], cArr[i4 & 15]};
        while (i5 < 8 && cArr2[i5] == '0') {
            i5++;
        }
        if (i5 < 0) {
            throw new IndexOutOfBoundsException(a1.c.d(i5, "startIndex: ", ", endIndex: 8, size: 8"));
        }
        if (i5 <= 8) {
            return new String(cArr2, i5, 8 - i5);
        }
        throw new IllegalArgumentException(a1.c.d(i5, "startIndex: ", " > endIndex: 8"));
    }

    public static String I(String str) {
        return str.length() <= 127 ? str : str.substring(0, 127);
    }

    public static e a(int i4, t2.a aVar, int i5) {
        int i6 = i5 & 2;
        t2.a aVar2 = t2.a.f2174d;
        if (i6 != 0) {
            aVar = aVar2;
        }
        if (i4 == -2) {
            if (aVar != aVar2) {
                return new t2.p(1, aVar);
            }
            t2.i.f2219c.getClass();
            return new e(t2.h.f2218b);
        }
        if (i4 == -1) {
            if (aVar == aVar2) {
                return new t2.p(1, t2.a.f2175e);
            }
            throw new IllegalArgumentException("CONFLATED capacity cannot be used with non-default onBufferOverflow");
        }
        if (i4 == 0) {
            return aVar == aVar2 ? new e(0) : new t2.p(1, aVar);
        }
        if (i4 != Integer.MAX_VALUE) {
            return aVar == aVar2 ? new e(i4) : new t2.p(i4, aVar);
        }
        return new e(Integer.MAX_VALUE);
    }

    public static final String b(String str, a3.a0 a0Var) {
        t tVar = (t) a0Var.f63c;
        String strB = tVar.b();
        if (p2.p.u0(str, "/")) {
            int length = str.length() - 1;
            if (length < 0) {
                length = 0;
            }
            str = p2.i.R0(length, str);
        }
        String strConcat = str.concat(strB);
        String strD = tVar.d();
        if (strD == null || strD.length() == 0) {
            return strConcat;
        }
        return strConcat + "?" + tVar.d();
    }

    public static final String c(Object[] objArr, int i4, int i5, v1.e eVar) {
        StringBuilder sb = new StringBuilder((i5 * 3) + 2);
        sb.append("[");
        for (int i6 = 0; i6 < i5; i6++) {
            if (i6 > 0) {
                sb.append(", ");
            }
            Object obj = objArr[i4 + i6];
            if (obj == eVar) {
                sb.append("(this Collection)");
            } else {
                sb.append(obj);
            }
        }
        sb.append("]");
        String string = sb.toString();
        i.d(string, "toString(...)");
        return string;
    }

    public static final void d(LinkedHashMap linkedHashMap) throws JSONException {
        if (G("ro.product.devicetype").length() > 0) {
            linkedHashMap.put("devicetype", G("ro.product.devicetype"));
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("hq_fota_version", G("ro.fota.version"));
            jSONObject.put("hq_git_version", G("ro.product.git_version"));
            jSONObject.put("hq_locale", G("ro.product.locale"));
            jSONObject.put("hq_build_product", G("ro.build.product"));
            jSONObject.put("hq_build_type", G("ro.build.type"));
            jSONObject.put("hq_board", G("ro.product.board"));
            jSONObject.put("hq_brand", G("ro.product.brand"));
            jSONObject.put("hq_device", G("ro.product.device"));
            jSONObject.put("hq_manufacturer", G("ro.product.manufacturer"));
            jSONObject.put("hq_model", G("ro.product.model"));
            linkedHashMap.put("extra_params", jSONObject.toString());
        }
    }

    public static final boolean e(int i4, int i5, int i6, byte[] bArr, byte[] bArr2) {
        i.e(bArr, "a");
        i.e(bArr2, "b");
        for (int i7 = 0; i7 < i6; i7++) {
            if (bArr[i7 + i4] != bArr2[i7 + i5]) {
                return false;
            }
        }
        return true;
    }

    public static final void f(long j4, long j5, long j6) {
        if ((j5 | j6) < 0 || j5 > j4 || j4 - j5 < j6) {
            throw new ArrayIndexOutOfBoundsException("size=" + j4 + " offset=" + j5 + " byteCount=" + j6);
        }
    }

    public static void g(int i4, int i5, int i6) {
        if (i4 >= 0 && i5 <= i6) {
            if (i4 > i5) {
                throw new IllegalArgumentException(a1.c.b(i4, i5, "fromIndex: ", " > toIndex: "));
            }
            return;
        }
        throw new IndexOutOfBoundsException("fromIndex: " + i4 + ", toIndex: " + i5 + ", size: " + i6);
    }

    public static final long h() {
        return Thread.currentThread().getId();
    }

    public static final boolean i() {
        h1.c0.f1036a.getClass();
        boolean zK = h1.c0.k();
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            Object objInvoke = cls.getMethod("getBoolean", String.class, Boolean.TYPE).invoke(cls, "persist.autorun.uplog", Boolean.valueOf(zK));
            i.c(objInvoke, "null cannot be cast to non-null type kotlin.Boolean");
            return ((Boolean) objInvoke).booleanValue();
        } catch (Exception unused) {
            return zK;
        }
    }

    public static a3.p j(SSLSession sSLSession) throws IOException {
        Object objK;
        String cipherSuite = sSLSession.getCipherSuite();
        if (cipherSuite == null) {
            throw new IllegalStateException("cipherSuite == null");
        }
        if (cipherSuite.equals("TLS_NULL_WITH_NULL_NULL") || cipherSuite.equals("SSL_NULL_WITH_NULL_NULL")) {
            throw new IOException("cipherSuite == ".concat(cipherSuite));
        }
        g gVarC = g.f125b.c(cipherSuite);
        String protocol = sSLSession.getProtocol();
        if (protocol == null) {
            throw new IllegalStateException("tlsVersion == null");
        }
        if ("NONE".equals(protocol)) {
            throw new IOException("tlsVersion == NONE");
        }
        h0.f150e.getClass();
        h0 h0VarD = a3.b.d(protocol);
        try {
            objK = b3.g.k(sSLSession.getPeerCertificates());
        } catch (SSLPeerUnverifiedException unused) {
            objK = v1.p.f2517d;
        }
        return new a3.p(h0VarD, gVarC, b3.g.k(sSLSession.getLocalCertificates()), new o(0, objK));
    }

    public static final String k() {
        TimeZone timeZone = TimeZone.getDefault();
        return timeZone.getDisplayName() + "-GMT" + String.format("%+03d:%02d", Arrays.copyOf(new Object[]{Integer.valueOf(timeZone.getRawOffset() / 3600000), Integer.valueOf(Math.abs((timeZone.getRawOffset() / 60000) % 60))}, 2)) + "-" + timeZone.getID();
    }

    public static g.l l(g.j jVar) {
        g.i iVar = new g.i();
        iVar.f938c = new g.o();
        g.l lVar = new g.l(iVar);
        iVar.f937b = lVar;
        iVar.f936a = jVar.getClass();
        try {
            Object objA = jVar.a(iVar);
            if (objA == null) {
                return lVar;
            }
            iVar.f936a = objA;
            return lVar;
        } catch (Exception e4) {
            lVar.f942e.i(e4);
            return lVar;
        }
    }

    public static final String m(Context context) {
        Object obj;
        Enumeration<InetAddress> inetAddresses;
        String hostAddress;
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            i.d(networkInterfaces, "getNetworkInterfaces(...)");
            ArrayList list = Collections.list(networkInterfaces);
            i.d(list, "list(...)");
            int size = list.size();
            int i4 = 0;
            int i5 = 0;
            loop0: while (true) {
                if (i5 >= size) {
                    obj = null;
                    break;
                }
                obj = list.get(i5);
                i5++;
                Enumeration<InetAddress> inetAddresses2 = ((NetworkInterface) obj).getInetAddresses();
                i.d(inetAddresses2, "getInetAddresses(...)");
                ArrayList list2 = Collections.list(inetAddresses2);
                i.d(list2, "list(...)");
                if (!list2.isEmpty()) {
                    int size2 = list2.size();
                    int i6 = 0;
                    while (i6 < size2) {
                        Object obj2 = list2.get(i6);
                        i6++;
                        InetAddress inetAddress = (InetAddress) obj2;
                        if (!inetAddress.isLoopbackAddress() && (inetAddress instanceof Inet4Address)) {
                            break loop0;
                        }
                    }
                }
            }
            NetworkInterface networkInterface = (NetworkInterface) obj;
            if (networkInterface == null || (inetAddresses = networkInterface.getInetAddresses()) == null) {
                return "Unknown";
            }
            ArrayList list3 = Collections.list(inetAddresses);
            i.d(list3, "list(...)");
            int size3 = list3.size();
            while (i4 < size3) {
                Object obj3 = list3.get(i4);
                i4++;
                InetAddress inetAddress2 = (InetAddress) obj3;
                if (!inetAddress2.isLoopbackAddress() && (inetAddress2 instanceof Inet4Address)) {
                    InetAddress inetAddress3 = (InetAddress) obj3;
                    return (inetAddress3 == null || (hostAddress = inetAddress3.getHostAddress()) == null) ? "Unknown" : hostAddress;
                }
            }
            throw new NoSuchElementException("Collection contains no element matching the predicate.");
        } catch (Exception unused) {
            return "Unknown";
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public static final Class n(n2.b bVar) {
        i.e(bVar, "<this>");
        Class clsA = ((d) bVar).a();
        if (clsA.isPrimitive()) {
            String name = clsA.getName();
            switch (name.hashCode()) {
                case -1325958191:
                    if (name.equals("double")) {
                        return Double.class;
                    }
                    break;
                case 104431:
                    if (name.equals("int")) {
                        return Integer.class;
                    }
                    break;
                case 3039496:
                    if (name.equals("byte")) {
                        return Byte.class;
                    }
                    break;
                case 3052374:
                    if (name.equals("char")) {
                        return Character.class;
                    }
                    break;
                case 3327612:
                    if (name.equals("long")) {
                        return Long.class;
                    }
                    break;
                case 3625364:
                    if (name.equals("void")) {
                        return Void.class;
                    }
                    break;
                case 64711720:
                    if (name.equals("boolean")) {
                        return Boolean.class;
                    }
                    break;
                case 97526364:
                    if (name.equals("float")) {
                        return Float.class;
                    }
                    break;
                case 109413500:
                    if (name.equals("short")) {
                        return Short.class;
                    }
                    break;
            }
        }
        return clsA;
    }

    public static final String o(Context context) {
        Object systemService = context.getSystemService("connectivity");
        i.c(systemService, "null cannot be cast to non-null type android.net.ConnectivityManager");
        ConnectivityManager connectivityManager = (ConnectivityManager) systemService;
        NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
        if (networkCapabilities == null || !networkCapabilities.hasTransport(1)) {
            return (networkCapabilities == null || !networkCapabilities.hasTransport(0)) ? "Unknown" : "Cellular";
        }
        return "Wi-Fi";
    }

    public static int p(Context context) {
        try {
            Object systemService = context.getApplicationContext().getSystemService("wifi");
            WifiManager wifiManager = systemService instanceof WifiManager ? (WifiManager) systemService : null;
            WifiInfo connectionInfo = wifiManager != null ? wifiManager.getConnectionInfo() : null;
            if (connectionInfo != null) {
                return WifiManager.calculateSignalLevel(connectionInfo.getRssi(), 5);
            }
            return -1;
        } catch (Exception unused) {
            return -1;
        }
    }

    public static String q(Context context) {
        try {
            Object systemService = context.getApplicationContext().getSystemService("wifi");
            WifiManager wifiManager = systemService instanceof WifiManager ? (WifiManager) systemService : null;
            WifiInfo connectionInfo = wifiManager != null ? wifiManager.getConnectionInfo() : null;
            String ssid = connectionInfo != null ? connectionInfo.getSSID() : null;
            if (ssid != null) {
                String strX0 = p2.p.x0(ssid, "\"", "");
                if (!strX0.equals("<unknown ssid>")) {
                    return strX0;
                }
            }
        } catch (Exception unused) {
        }
        return null;
    }

    public static void r(String str, Exception exc) {
        if (exc instanceof InvocationTargetException) {
            Throwable cause = exc.getCause();
            if (!(cause instanceof RuntimeException)) {
                throw new RuntimeException(cause);
            }
            throw ((RuntimeException) cause);
        }
        Log.v("Trace", "Unable to call " + str + " via reflection", exc);
    }

    public static boolean s() {
        if (Build.VERSION.SDK_INT >= 29) {
            return b0.a.c();
        }
        try {
            if (f1e == null) {
                f0d = Trace.class.getField("TRACE_TAG_APP").getLong(null);
                f1e = Trace.class.getMethod("isTagEnabled", Long.TYPE);
            }
            return ((Boolean) f1e.invoke(null, Long.valueOf(f0d))).booleanValue();
        } catch (Exception e4) {
            r("isTagEnabled", e4);
            return false;
        }
    }

    public static final boolean t(Context context) {
        Object systemService = context.getSystemService("connectivity");
        i.c(systemService, "null cannot be cast to non-null type android.net.ConnectivityManager");
        ConnectivityManager connectivityManager = (ConnectivityManager) systemService;
        Network[] allNetworks = connectivityManager.getAllNetworks();
        i.d(allNetworks, "getAllNetworks(...)");
        for (Network network : allNetworks) {
            NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(network);
            if (networkCapabilities != null && networkCapabilities.hasTransport(4)) {
                return true;
            }
        }
        return false;
    }

    public static g.l u(final y1.h hVar, final p pVar) {
        i.e(hVar, "context");
        final w wVar = w.f2033d;
        return l(new g.j(wVar, pVar) { // from class: d0.q

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public final /* synthetic */ r2.w f496b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            public final /* synthetic */ a2.i f497c;

            /* JADX WARN: Multi-variable type inference failed */
            {
                this.f497c = (a2.i) pVar;
            }

            /* JADX WARN: Type inference failed for: r2v1, types: [a2.i, i2.p] */
            @Override // g.j
            public final Object a(g.i iVar) {
                r2.t tVar = r2.t.f2027e;
                y1.h hVar2 = this.f495a;
                s sVar = new s(2, (v0) hVar2.k(tVar));
                g.o oVar = iVar.f938c;
                if (oVar != null) {
                    oVar.a(sVar, m.f478d);
                }
                return r2.x.p(r2.x.a(hVar2), null, this.f496b, new u((i2.p) this.f497c, iVar, (y1.c) null), 1);
            }
        });
    }

    public static u1.c v(i2.a aVar) {
        u1.l lVar = new u1.l();
        lVar.f2302d = aVar;
        lVar.f2303e = u1.j.f2300a;
        return lVar;
    }

    public static final void w(Context context) {
        Map mapSingletonMap;
        i.e(context, "context");
        File databasePath = context.getDatabasePath("androidx.work.workdb");
        i.d(databasePath, "getDatabasePath(...)");
        if (databasePath.exists()) {
            a0.e().a(e0.t.f682a, "Migrating WorkDatabase to the no-backup directory");
            File databasePath2 = context.getDatabasePath("androidx.work.workdb");
            i.d(databasePath2, "getDatabasePath(...)");
            File noBackupFilesDir = context.getNoBackupFilesDir();
            i.d(noBackupFilesDir, "getNoBackupFilesDir(...)");
            String[] strArr = e0.t.f683b;
            int iJ = v1.t.J(strArr.length);
            if (iJ < 16) {
                iJ = 16;
            }
            LinkedHashMap linkedHashMap = new LinkedHashMap(iJ);
            for (String str : strArr) {
                linkedHashMap.put(new File(databasePath2.getPath() + str), new File(noBackupFilesDir.getPath() + str));
            }
            if (linkedHashMap.isEmpty()) {
                mapSingletonMap = Collections.singletonMap(databasePath2, noBackupFilesDir);
                i.d(mapSingletonMap, "singletonMap(...)");
            } else {
                LinkedHashMap linkedHashMap2 = new LinkedHashMap(linkedHashMap);
                linkedHashMap2.put(databasePath2, noBackupFilesDir);
                mapSingletonMap = linkedHashMap2;
            }
            for (Map.Entry entry : mapSingletonMap.entrySet()) {
                File file = (File) entry.getKey();
                File file2 = (File) entry.getValue();
                if (file.exists()) {
                    if (file2.exists()) {
                        a0.e().h(e0.t.f682a, "Over-writing contents of " + file2);
                    }
                    a0.e().a(e0.t.f682a, file.renameTo(file2) ? "Migrated " + file + "to " + file2 : "Renaming " + file + " to " + file2 + " failed");
                }
            }
        }
    }

    public static final long x(m2.e eVar) {
        k2.a aVar = k2.d.f1291d;
        long j4 = eVar.f1455d;
        long j5 = eVar.f1456e;
        if (j4 > j5) {
            throw new IllegalArgumentException("Cannot get random in empty range: " + eVar);
        }
        if (j5 < Long.MAX_VALUE) {
            return k2.d.f1291d.d(j4, j5 + 1);
        }
        if (j4 <= Long.MIN_VALUE) {
            return k2.d.f1291d.c();
        }
        return k2.d.f1291d.d(j4 - 1, j5) + 1;
    }

    public static final boolean y(String str) {
        i.e(str, "method");
        return (str.equals("GET") || str.equals("HEAD")) ? false : true;
    }

    public static final List z(c cVar) {
        int iL = h.l(cVar, "id");
        int iL2 = h.l(cVar, "seq");
        int iL3 = h.l(cVar, "from");
        int iL4 = h.l(cVar, "to");
        w1.c cVar2 = new w1.c(10);
        while (cVar.F()) {
            cVar2.add(new v.g((int) cVar.getLong(iL), (int) cVar.getLong(iL2), cVar.n(iL3), cVar.n(iL4)));
        }
        return v1.j.C0(h.d(cVar2));
    }
}
