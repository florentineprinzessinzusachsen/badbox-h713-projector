package l3;

import a3.d0;
import a3.r;
import a3.s;
import a3.v;
import a3.z;
import android.content.ContentProviderClient;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.TypedArray;
import android.database.SQLException;
import android.drm.DrmManagerClient;
import android.media.MediaDrm;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Trace;
import android.util.Base64;
import android.util.Log;
import d0.a0;
import d0.b0;
import d0.i0;
import d0.k0;
import d0.l0;
import h1.c0;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.net.NetworkInterface;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import org.json.JSONException;
import p.t;
import p.w;
import r2.x;
import s0.q;
import s0.u;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public abstract class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static boolean f1382a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static boolean f1383b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static boolean f1384c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static boolean f1385d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static boolean f1386e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static SSLSocketFactory f1387f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static c1.a f1388g;

    /* JADX WARN: Code duplicated, block: B:14:0x0034  */
    public static d0.j A(byte[] bArr) {
        boolean z3;
        j2.i.e(bArr, "bytes");
        if (bArr.length > 10240) {
            throw new IllegalStateException("Data cannot occupy more than 10240 bytes when serialized");
        }
        if (bArr.length == 0) {
            return d0.j.f464b;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
            byte[] bArr2 = new byte[2];
            byteArrayInputStream.read(bArr2);
            byte b4 = (byte) (-21267);
            int i4 = 0;
            if (bArr2[0] == ((byte) 16777132)) {
                z3 = true;
                if (bArr2[1] != b4) {
                    z3 = false;
                }
            } else {
                z3 = false;
            }
            byteArrayInputStream.reset();
            if (z3) {
                ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
                try {
                    int i5 = objectInputStream.readInt();
                    while (i4 < i5) {
                        linkedHashMap.put(objectInputStream.readUTF(), objectInputStream.readObject());
                        i4++;
                    }
                    objectInputStream.close();
                } catch (Throwable th) {
                    try {
                        throw th;
                    } catch (Throwable th2) {
                        j(objectInputStream, th);
                        throw th2;
                    }
                }
            } else {
                DataInputStream dataInputStream = new DataInputStream(byteArrayInputStream);
                try {
                    short s3 = dataInputStream.readShort();
                    if (s3 != -21521) {
                        throw new IllegalStateException(a1.c.c(s3, "Magic number doesn't match: ").toString());
                    }
                    short s4 = dataInputStream.readShort();
                    if (s4 != 1) {
                        throw new IllegalStateException(a1.c.c(s4, "Unsupported version number: ").toString());
                    }
                    int i6 = dataInputStream.readInt();
                    while (i4 < i6) {
                        linkedHashMap.put(dataInputStream.readUTF(), B(dataInputStream, dataInputStream.readByte()));
                        i4++;
                    }
                    dataInputStream.close();
                } catch (Throwable th3) {
                    try {
                        throw th3;
                    } catch (Throwable th4) {
                        j(dataInputStream, th3);
                        throw th4;
                    }
                }
            }
        } catch (IOException e4) {
            a0.e().d(d0.k.f466a, "Error in Data#fromByteArray: ", e4);
        } catch (ClassNotFoundException e5) {
            a0.e().d(d0.k.f466a, "Error in Data#fromByteArray: ", e5);
        }
        return new d0.j(linkedHashMap);
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [java.io.Serializable, java.lang.Double[]] */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.io.Serializable, java.lang.Float[]] */
    /* JADX WARN: Type inference failed for: r0v4, types: [java.io.Serializable, java.lang.Long[]] */
    /* JADX WARN: Type inference failed for: r0v5, types: [java.io.Serializable, java.lang.Integer[]] */
    /* JADX WARN: Type inference failed for: r0v6, types: [java.io.Serializable, java.lang.Byte[]] */
    /* JADX WARN: Type inference failed for: r0v7, types: [java.io.Serializable, java.lang.Boolean[]] */
    /* JADX WARN: Type inference failed for: r1v14, types: [java.io.Serializable, java.lang.String[]] */
    public static final Serializable B(DataInputStream dataInputStream, byte b4) throws IOException {
        if (b4 == 0) {
            return null;
        }
        if (b4 == 1) {
            return Boolean.valueOf(dataInputStream.readBoolean());
        }
        if (b4 == 2) {
            return Byte.valueOf(dataInputStream.readByte());
        }
        if (b4 == 3) {
            return Integer.valueOf(dataInputStream.readInt());
        }
        if (b4 == 4) {
            return Long.valueOf(dataInputStream.readLong());
        }
        if (b4 == 5) {
            return Float.valueOf(dataInputStream.readFloat());
        }
        if (b4 == 6) {
            return Double.valueOf(dataInputStream.readDouble());
        }
        if (b4 == 7) {
            return dataInputStream.readUTF();
        }
        int i4 = 0;
        if (b4 == 8) {
            int i5 = dataInputStream.readInt();
            ?? r4 = new Boolean[i5];
            while (i4 < i5) {
                r4[i4] = Boolean.valueOf(dataInputStream.readBoolean());
                i4++;
            }
            return r4;
        }
        if (b4 == 9) {
            int i6 = dataInputStream.readInt();
            ?? r5 = new Byte[i6];
            while (i4 < i6) {
                r5[i4] = Byte.valueOf(dataInputStream.readByte());
                i4++;
            }
            return r5;
        }
        if (b4 == 10) {
            int i7 = dataInputStream.readInt();
            ?? r6 = new Integer[i7];
            while (i4 < i7) {
                r6[i4] = Integer.valueOf(dataInputStream.readInt());
                i4++;
            }
            return r6;
        }
        if (b4 == 11) {
            int i8 = dataInputStream.readInt();
            ?? r7 = new Long[i8];
            while (i4 < i8) {
                r7[i4] = Long.valueOf(dataInputStream.readLong());
                i4++;
            }
            return r7;
        }
        if (b4 == 12) {
            int i9 = dataInputStream.readInt();
            ?? r8 = new Float[i9];
            while (i4 < i9) {
                r8[i4] = Float.valueOf(dataInputStream.readFloat());
                i4++;
            }
            return r8;
        }
        if (b4 == 13) {
            int i10 = dataInputStream.readInt();
            ?? r9 = new Double[i10];
            while (i4 < i10) {
                r9[i4] = Double.valueOf(dataInputStream.readDouble());
                i4++;
            }
            return r9;
        }
        if (b4 != 14) {
            throw new IllegalStateException(a1.c.c(b4, "Unsupported type "));
        }
        int i11 = dataInputStream.readInt();
        ?? r10 = new String[i11];
        while (i4 < i11) {
            String utf = dataInputStream.readUTF();
            if (j2.i.a(utf, "androidx.work.Data-95ed6082-b8e9-46e8-a73f-ff56f00f5d9d")) {
                utf = null;
            }
            r10[i4] = utf;
            i4++;
        }
        return r10;
    }

    public static y1.f C(y1.f fVar, y1.g gVar) {
        j2.i.e(gVar, "key");
        if (j2.i.a(fVar.getKey(), gVar)) {
            return fVar;
        }
        return null;
    }

    public static final int D(w.c cVar, String str) {
        j2.i.e(cVar, "stmt");
        int iL = l(cVar, str);
        if (iL >= 0) {
            return iL;
        }
        int columnCount = cVar.getColumnCount();
        ArrayList arrayList = new ArrayList(columnCount);
        for (int i4 = 0; i4 < columnCount; i4++) {
            arrayList.add(cVar.getColumnName(i4));
        }
        throw new IllegalArgumentException("Column '" + str + "' does not exist. Available columns: [" + v1.j.y0(arrayList, null, null, null, null, 63) + ']');
    }

    public static final y1.h E(t tVar, a2.c cVar) {
        if (!tVar.j()) {
            w2.c cVar2 = tVar.f1713a;
            if (cVar2 != null) {
                return cVar2.f2618d;
            }
            j2.i.h("coroutineScope");
            throw null;
        }
        if (cVar.g().k(w.f1728d) != null) {
            throw new ClassCastException();
        }
        w2.c cVar3 = tVar.f1713a;
        if (cVar3 != null) {
            return cVar3.f2618d;
        }
        j2.i.h("coroutineScope");
        throw null;
    }

    public static final String F(a3.a0 a0Var) {
        j2.i.e(a0Var, "<this>");
        String str = ((a3.t) a0Var.f63c).f211d;
        c0.f1036a.getClass();
        a3.a0 a0Var2 = c0.f1042g;
        n2.c[] cVarArr = c0.f1037b;
        String str2 = (String) a0Var2.a(cVarArr[0]);
        j2.i.e(str2, "<this>");
        s sVar = new s();
        sVar.c(null, str2);
        if (j2.i.a(str, sVar.a().f211d)) {
            return "主域名";
        }
        String str3 = (String) c0.f1043h.a(cVarArr[1]);
        j2.i.e(str3, "<this>");
        s sVar2 = new s();
        sVar2.c(null, str3);
        if (j2.i.a(str, sVar2.a().f211d)) {
            return "备用域名";
        }
        String strI = c0.i();
        j2.i.e(strI, "<this>");
        s sVar3 = new s();
        sVar3.c(null, strI);
        return j2.i.a(str, sVar3.a().f211d) ? "随机域名" : "未知域名";
    }

    /* JADX WARN: Code duplicated, block: B:11:0x0036  */
    /* JADX WARN: Code duplicated, block: B:26:0x007b  */
    public static final String G() {
        String strH;
        String strH2;
        String upperCase;
        byte[] hardwareAddress;
        try {
            File file = new File("/sys/class/net/eth0/address");
            if (file.exists()) {
                String upperCase2 = p2.i.S0(T(file)).toString().toUpperCase(Locale.ROOT);
                j2.i.d(upperCase2, "toUpperCase(...)");
                if (upperCase2.length() >= 17) {
                    strH = p2.i.R0(17, upperCase2);
                } else {
                    strH = null;
                }
            } else {
                strH = null;
            }
        } catch (Exception e4) {
            e4.printStackTrace();
        }
        if (!Q(strH)) {
            strH = H("eth0");
        }
        if (Q(strH)) {
            return strH;
        }
        try {
            File file2 = new File("/sys/class/net/wlan0/address");
            if (file2.exists()) {
                String upperCase3 = p2.i.S0(T(file2)).toString().toUpperCase(Locale.ROOT);
                j2.i.d(upperCase3, "toUpperCase(...)");
                if (upperCase3.length() >= 17) {
                    strH2 = p2.i.R0(17, upperCase3);
                } else {
                    strH2 = null;
                }
            } else {
                strH2 = null;
            }
        } catch (Exception e5) {
            e5.printStackTrace();
        }
        if (!Q(strH2)) {
            strH2 = H("wlan0");
        }
        if (Q(strH2)) {
            return strH2;
        }
        try {
            Iterator it = Collections.list(NetworkInterface.getNetworkInterfaces()).iterator();
            j2.i.d(it, "iterator(...)");
            while (it.hasNext()) {
                NetworkInterface networkInterface = (NetworkInterface) it.next();
                if (p2.p.v0(networkInterface.getName(), "wlan0") && (hardwareAddress = networkInterface.getHardwareAddress()) != null) {
                    String strC0 = v1.i.c0(hardwareAddress, new d0.h(2));
                    if (Q(strC0)) {
                        return strC0;
                    }
                }
            }
        } catch (Exception e6) {
            e6.printStackTrace();
        }
        try {
            Object systemService = b1.a.f336a.a().getSystemService("wifi");
            j2.i.c(systemService, "null cannot be cast to non-null type android.net.wifi.WifiManager");
            String macAddress = ((WifiManager) systemService).getConnectionInfo().getMacAddress();
            if (macAddress != null) {
                Locale locale = Locale.US;
                j2.i.d(locale, "US");
                upperCase = macAddress.toUpperCase(locale);
                j2.i.d(upperCase, "toUpperCase(...)");
            } else {
                upperCase = null;
            }
            if (Q(upperCase)) {
                return upperCase;
            }
            return null;
        } catch (Exception e7) {
            e7.printStackTrace();
        }
    }

    public static final String H(String str) {
        byte[] hardwareAddress;
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            if (networkInterfaces != null) {
                Iterator it = Collections.list(networkInterfaces).iterator();
                j2.i.d(it, "iterator(...)");
                while (it.hasNext()) {
                    NetworkInterface networkInterface = (NetworkInterface) it.next();
                    if (p2.p.v0(networkInterface.getName(), str) && (hardwareAddress = networkInterface.getHardwareAddress()) != null) {
                        return v1.i.c0(hardwareAddress, new d0.h(3));
                    }
                }
            }
            return null;
        } catch (Exception e4) {
            e4.printStackTrace();
            return null;
        }
    }

    public static final void I(String str) {
        j2.i.e(str, "name");
        if (str.length() <= 0) {
            throw new IllegalArgumentException("name is empty");
        }
        int length = str.length();
        for (int i4 = 0; i4 < length; i4++) {
            char cCharAt = str.charAt(i4);
            if ('!' > cCharAt || cCharAt >= 127) {
                StringBuilder sb = new StringBuilder("Unexpected char 0x");
                l0.h(16);
                String string = Integer.toString(cCharAt, 16);
                j2.i.d(string, "toString(...)");
                if (string.length() < 2) {
                    string = "0".concat(string);
                }
                sb.append(string);
                sb.append(" at ");
                sb.append(i4);
                sb.append(" in header name: ");
                sb.append(str);
                throw new IllegalArgumentException(sb.toString().toString());
            }
        }
    }

    public static final void J(String str, String str2) {
        j2.i.e(str, "value");
        int length = str.length();
        for (int i4 = 0; i4 < length; i4++) {
            char cCharAt = str.charAt(i4);
            if (cCharAt != '\t' && (' ' > cCharAt || cCharAt >= 127)) {
                StringBuilder sb = new StringBuilder("Unexpected char 0x");
                l0.h(16);
                String string = Integer.toString(cCharAt, 16);
                j2.i.d(string, "toString(...)");
                if (string.length() < 2) {
                    string = "0".concat(string);
                }
                sb.append(string);
                sb.append(" at ");
                sb.append(i4);
                sb.append(" in ");
                sb.append(str2);
                sb.append(" value");
                sb.append(b3.d.j(str2) ? "" : ": ".concat(str));
                throw new IllegalArgumentException(sb.toString().toString());
            }
        }
    }

    public static final int K(e.f fVar, Object obj, int i4) {
        int i5 = fVar.f572f;
        if (i5 == 0) {
            return -1;
        }
        try {
            int iA = f.a.a(fVar.f570d, i5, i4);
            if (iA < 0 || j2.i.a(obj, fVar.f571e[iA])) {
                return iA;
            }
            int i6 = iA + 1;
            while (i6 < i5 && fVar.f570d[i6] == i4) {
                if (j2.i.a(obj, fVar.f571e[i6])) {
                    return i6;
                }
                i6++;
            }
            for (int i7 = iA - 1; i7 >= 0 && fVar.f570d[i7] == i4; i7--) {
                if (j2.i.a(obj, fVar.f571e[i7])) {
                    return i7;
                }
            }
            return ~i6;
        } catch (IndexOutOfBoundsException unused) {
            throw new ConcurrentModificationException();
        }
    }

    public static final d0.a L(int i4) {
        if (i4 == 0) {
            return d0.a.f398d;
        }
        if (i4 == 1) {
            return d0.a.f399e;
        }
        throw new IllegalArgumentException(a1.c.d(i4, "Could not convert ", " to BackoffPolicy"));
    }

    public static final b0 M(int i4) {
        if (i4 == 0) {
            return b0.f417d;
        }
        if (i4 == 1) {
            return b0.f418e;
        }
        if (i4 == 2) {
            return b0.f419f;
        }
        if (i4 == 3) {
            return b0.f420g;
        }
        if (i4 == 4) {
            return b0.f421h;
        }
        if (Build.VERSION.SDK_INT < 30 || i4 != 5) {
            throw new IllegalArgumentException(a1.c.d(i4, "Could not convert ", " to NetworkType"));
        }
        return b0.f422i;
    }

    public static final i0 N(int i4) {
        if (i4 == 0) {
            return i0.f461d;
        }
        if (i4 == 1) {
            return i0.f462e;
        }
        throw new IllegalArgumentException(a1.c.d(i4, "Could not convert ", " to OutOfQuotaPolicy"));
    }

    public static final k0 O(int i4) {
        if (i4 == 0) {
            return k0.f467d;
        }
        if (i4 == 1) {
            return k0.f468e;
        }
        if (i4 == 2) {
            return k0.f469f;
        }
        if (i4 == 3) {
            return k0.f470g;
        }
        if (i4 == 4) {
            return k0.f471h;
        }
        if (i4 == 5) {
            return k0.f472i;
        }
        throw new IllegalArgumentException(a1.c.d(i4, "Could not convert ", " to State"));
    }

    public static boolean P(String str) {
        return ("Connection".equalsIgnoreCase(str) || "Keep-Alive".equalsIgnoreCase(str) || "Proxy-Authenticate".equalsIgnoreCase(str) || "Proxy-Authorization".equalsIgnoreCase(str) || "TE".equalsIgnoreCase(str) || "Trailers".equalsIgnoreCase(str) || "Transfer-Encoding".equalsIgnoreCase(str) || "Upgrade".equalsIgnoreCase(str)) ? false : true;
    }

    public static final boolean Q(String str) {
        if (str != null && str.length() != 0) {
            String[] strArr = {"02:00:00:00:00:00", "00:00:00:00:00:00", "FF:FF:FF:FF:FF:FF"};
            if (!(strArr.length > 0 ? v1.i.R(strArr) : v1.p.f2517d).contains(str)) {
                Pattern patternCompile = Pattern.compile("([0-9A-Fa-f]{2}[:-]){5}([0-9A-Fa-f]{2})");
                j2.i.d(patternCompile, "compile(...)");
                j2.i.e(str, "input");
                if (patternCompile.matcher(str).matches()) {
                    return true;
                }
            }
        }
        return false;
    }

    public static final d0.l R(final d0.l lVar, final String str, final Executor executor, final i2.a aVar) {
        j2.i.e(lVar, "tracer");
        j2.i.e(str, "label");
        j2.i.e(executor, "executor");
        final n.h hVar = new n.h();
        a.a.l(new g.j() { // from class: d0.g0
            @Override // g.j
            public final Object a(final g.i iVar) {
                final l lVar2 = lVar;
                final String str2 = str;
                final i2.a aVar2 = aVar;
                final n.h hVar2 = hVar;
                executor.execute(new Runnable() { // from class: d0.h0
                    @Override // java.lang.Runnable
                    public final void run() {
                        String str3 = str2;
                        i2.a aVar3 = aVar2;
                        n.h hVar3 = hVar2;
                        g.i iVar2 = iVar;
                        lVar2.getClass();
                        boolean zS = a.a.s();
                        if (zS) {
                            try {
                                j2.i.e(str3, "label");
                                Trace.beginSection(a.a.I(str3));
                            } catch (Throwable th) {
                                if (zS) {
                                    Trace.endSection();
                                }
                                throw th;
                            }
                        }
                        try {
                            aVar3.a();
                            f0 f0Var = l.f476c;
                            hVar3.a(f0Var);
                            iVar2.a(f0Var);
                        } catch (Throwable th2) {
                            hVar3.a(new e0(th2));
                            iVar2.b(th2);
                        }
                        if (zS) {
                            Trace.endSection();
                        }
                    }
                });
                return u1.k.f2301a;
            }
        });
        return new d0.l();
    }

    public static List S(Object obj) {
        List listSingletonList = Collections.singletonList(obj);
        j2.i.d(listSingletonList, "singletonList(...)");
        return listSingletonList;
    }

    public static final String T(File file) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        try {
            char[] cArr = new char[1024];
            while (true) {
                int i4 = bufferedReader.read(cArr);
                if (i4 == -1) {
                    bufferedReader.close();
                    String string = sb.toString();
                    j2.i.d(string, "toString(...)");
                    return string;
                }
                sb.append(cArr, 0, i4);
            }
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                j(bufferedReader, th);
                throw th2;
            }
        }
    }

    public static y1.h U(y1.f fVar, y1.g gVar) {
        j2.i.e(gVar, "key");
        return j2.i.a(fVar.getKey(), gVar) ? y1.i.f2726d : fVar;
    }

    /* JADX WARN: Code duplicated, block: B:18:0x00a2  */
    public static long V(int i4, String str) {
        int iT = t(str, 0, i4, false);
        Matcher matcher = a3.k.f172n.matcher(str);
        int i5 = -1;
        int i6 = -1;
        int i7 = -1;
        int iF0 = -1;
        int i8 = -1;
        int i9 = -1;
        while (iT < i4) {
            int iT2 = t(str, iT + 1, i4, true);
            matcher.region(iT, iT2);
            if (i6 == -1 && matcher.usePattern(a3.k.f172n).matches()) {
                String strGroup = matcher.group(1);
                j2.i.d(strGroup, "group(...)");
                i6 = Integer.parseInt(strGroup);
                String strGroup2 = matcher.group(2);
                j2.i.d(strGroup2, "group(...)");
                i8 = Integer.parseInt(strGroup2);
                String strGroup3 = matcher.group(3);
                j2.i.d(strGroup3, "group(...)");
                i9 = Integer.parseInt(strGroup3);
            } else if (i7 == -1 && matcher.usePattern(a3.k.f171m).matches()) {
                String strGroup4 = matcher.group(1);
                j2.i.d(strGroup4, "group(...)");
                i7 = Integer.parseInt(strGroup4);
            } else if (iF0 == -1) {
                Pattern pattern = a3.k.f170l;
                if (matcher.usePattern(pattern).matches()) {
                    String strGroup5 = matcher.group(1);
                    j2.i.d(strGroup5, "group(...)");
                    Locale locale = Locale.US;
                    j2.i.d(locale, "US");
                    String lowerCase = strGroup5.toLowerCase(locale);
                    j2.i.d(lowerCase, "toLowerCase(...)");
                    String strPattern = pattern.pattern();
                    j2.i.d(strPattern, "pattern(...)");
                    iF0 = p2.i.F0(strPattern, lowerCase, 0, 6) / 4;
                } else if (i5 != -1 && matcher.usePattern(a3.k.f169k).matches()) {
                    String strGroup6 = matcher.group(1);
                    j2.i.d(strGroup6, "group(...)");
                    i5 = Integer.parseInt(strGroup6);
                }
            } else if (i5 != -1) {
            }
            iT = t(str, iT2 + 1, i4, false);
        }
        if (70 <= i5 && i5 < 100) {
            i5 += 1900;
        }
        if (i5 >= 0 && i5 < 70) {
            i5 += 2000;
        }
        if (i5 < 1601) {
            throw new IllegalArgumentException("Failed requirement.");
        }
        if (iF0 == -1) {
            throw new IllegalArgumentException("Failed requirement.");
        }
        if (1 > i7 || i7 >= 32) {
            throw new IllegalArgumentException("Failed requirement.");
        }
        if (i6 < 0 || i6 >= 24) {
            throw new IllegalArgumentException("Failed requirement.");
        }
        if (i8 < 0 || i8 >= 60) {
            throw new IllegalArgumentException("Failed requirement.");
        }
        if (i9 < 0 || i9 >= 60) {
            throw new IllegalArgumentException("Failed requirement.");
        }
        GregorianCalendar gregorianCalendar = new GregorianCalendar(b3.g.f348a);
        gregorianCalendar.setLenient(false);
        gregorianCalendar.set(1, i5);
        gregorianCalendar.set(2, iF0 - 1);
        gregorianCalendar.set(5, i7);
        gregorianCalendar.set(11, i6);
        gregorianCalendar.set(12, i8);
        gregorianCalendar.set(13, i9);
        gregorianCalendar.set(14, 0);
        return gregorianCalendar.getTimeInMillis();
    }

    public static final Object W(t tVar, boolean z3, boolean z4, i2.l lVar) {
        j2.i.e(tVar, "db");
        tVar.a();
        if (!tVar.j() || tVar.k() || tVar.f1720h.get() == null) {
            return a.a.C(new v.c(lVar, tVar, null, z3, z4));
        }
        throw new IllegalStateException("Cannot access database on a different coroutine context inherited from a suspending transaction.");
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    public static final Object X(t tVar, boolean z3, d0.h hVar, a2.c cVar) {
        v.e eVar;
        if (cVar instanceof v.e) {
            eVar = (v.e) cVar;
            int i4 = eVar.f2389k;
            if ((i4 & Integer.MIN_VALUE) != 0) {
                eVar.f2389k = i4 - Integer.MIN_VALUE;
            } else {
                eVar = new v.e(cVar);
            }
        } else {
            eVar = new v.e(cVar);
        }
        Object objE = eVar.f2388j;
        int i5 = eVar.f2389k;
        Object obj = z1.a.f2781d;
        if (i5 == 0) {
            l0.M(objE);
            if (tVar.j() && tVar.m() && tVar.k()) {
                v.f fVar = new v.f(hVar, tVar, null, z3);
                eVar.f2389k = 1;
                Object objQ = tVar.q(z3, fVar, eVar);
                if (objQ != obj) {
                    return objQ;
                }
            } else {
                eVar.f2385g = tVar;
                eVar.f2386h = hVar;
                eVar.f2387i = z3;
                eVar.f2389k = 2;
                objE = E(tVar, eVar);
                if (objE != obj) {
                }
            }
        }
        if (i5 == 1) {
            l0.M(objE);
            return objE;
        }
        if (i5 != 2) {
            if (i5 != 3) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            l0.M(objE);
            return objE;
        }
        z3 = eVar.f2387i;
        hVar = eVar.f2386h;
        tVar = eVar.f2385g;
        l0.M(objE);
        v.d dVar = new v.d(hVar, tVar, null, z3);
        eVar.f2385g = null;
        eVar.f2386h = null;
        eVar.f2389k = 3;
        Object objW = x.w((y1.h) objE, dVar, eVar);
        return objW == obj ? obj : objW;
    }

    public static y1.h Y(y1.f fVar, y1.h hVar) {
        j2.i.e(hVar, "context");
        return hVar == y1.i.f2726d ? fVar : (y1.h) hVar.K(fVar, new r1.b(9));
    }

    public static String Z(q qVar) {
        s0.o oVar = new s0.o();
        s0.i iVar = s0.i.f2088e;
        Objects.requireNonNull(iVar);
        oVar.f2128j = iVar;
        s0.n nVarA = oVar.a();
        if (qVar instanceof s0.t) {
            s0.t tVar = new s0.t();
            Iterator it = ((u0.n) ((s0.t) qVar).f2135d.entrySet()).iterator();
            while (((u0.m) it).hasNext()) {
                u0.o oVarB = ((u0.m) it).b();
                String str = (String) oVarB.getKey();
                q qVar2 = (q) oVarB.getValue();
                j2.i.b(qVar2);
                tVar.f2135d.put(str, q0(qVar2));
            }
            String strF = nVarA.f(tVar);
            j2.i.b(strF);
            return strF;
        }
        if (!(qVar instanceof s0.p)) {
            String strF2 = nVarA.f(qVar);
            j2.i.d(strF2, "toJson(...)");
            return strF2;
        }
        s0.p pVar = new s0.p();
        ArrayList arrayList = ((s0.p) qVar).f2133d;
        int size = arrayList.size();
        int i4 = 0;
        while (i4 < size) {
            Object obj = arrayList.get(i4);
            i4++;
            q qVar3 = (q) obj;
            j2.i.b(qVar3);
            pVar.f2133d.add(q0(qVar3));
        }
        String strF3 = nVarA.f(pVar);
        j2.i.b(strF3);
        return strF3;
    }

    public static void a(Throwable th, Throwable th2) {
        j2.i.e(th, "<this>");
        j2.i.e(th2, "exception");
        if (th != th2) {
            Integer num = d2.a.f526a;
            if (num == null || num.intValue() >= 19) {
                th.addSuppressed(th2);
                return;
            }
            Method method = c2.a.f370a;
            if (method != null) {
                method.invoke(th, th2);
            }
        }
    }

    public static final void a0(String str) {
        j2.i.e(str, "<this>");
        if (f1382a) {
            if (f1385d) {
                System.out.println((Object) str);
            }
        } else if (l0("persist.log.autorun.enable") || l0("persist.sys.log.autorun.enable")) {
            System.out.println((Object) str);
        }
    }

    public static final String b(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return String.valueOf(Build.VERSION.SDK_INT >= 28 ? packageInfo.getLongVersionCode() : packageInfo.versionCode);
        } catch (PackageManager.NameNotFoundException e4) {
            e4.printStackTrace();
            return "0";
        }
    }

    public static final void b0(String str) {
        j2.i.e(str, "<this>");
        if (f1382a) {
            if (f1383b) {
                System.out.println((Object) str);
            }
        } else if (l0("persist.log.encrypted.enable")) {
            System.out.println((Object) str);
        }
    }

    public static void c(StringBuilder sb, Object obj, i2.l lVar) {
        if (lVar != null) {
            sb.append((CharSequence) lVar.h(obj));
            return;
        }
        if (obj == null ? true : obj instanceof CharSequence) {
            sb.append((CharSequence) obj);
        } else if (obj instanceof Character) {
            sb.append(((Character) obj).charValue());
        } else {
            sb.append((CharSequence) obj.toString());
        }
    }

    public static final void c0(String str) {
        j2.i.e(str, "<this>");
        if (f1382a) {
            if (f1384c) {
                System.out.println((Object) str);
            }
        } else if (l0("persist.log.netreq.enable") || l0("persist.sys.log.netreq.enable")) {
            System.out.println((Object) str);
        }
    }

    public static w1.c d(w1.c cVar) {
        cVar.f();
        cVar.f2581f = true;
        return cVar.f2580e > 0 ? cVar : w1.c.f2578g;
    }

    /* JADX WARN: Code duplicated, block: B:10:0x0045  */
    public static final void d0(d0 d0Var, String str) {
        a3.a0 a0Var = d0Var.f103d;
        if (!p2.i.B0(((a3.t) a0Var.f63c).f215h, "device/dau") || l0("persist.log.dau.enable")) {
            if (str != null) {
                try {
                    q qVarE = l0.E(str);
                    s0.o oVar = new s0.o(new s0.n());
                    s0.i iVar = s0.i.f2088e;
                    Objects.requireNonNull(iVar);
                    oVar.f2128j = iVar;
                    str = oVar.a().f(qVarE);
                    if (str == null) {
                        str = "空响应";
                    }
                } catch (Exception unused) {
                }
            } else {
                str = "空响应";
            }
            try {
                StringBuilder sb = new StringBuilder("📡 网络请求完成\n");
                sb.append("URL      : " + ((a3.t) a0Var.f63c) + "   [" + F(a0Var) + "]");
                sb.append('\n');
                String str2 = a0Var.f62b;
                StringBuilder sb2 = new StringBuilder("Method   : ");
                sb2.append(str2);
                sb.append(sb2.toString());
                sb.append("\nResponse :\n");
                sb.append(str);
                sb.append("\n────────────────────────────End\n");
                c0(sb.toString());
            } catch (Exception e4) {
                a1.c.f("响应打印异常：", e4.getMessage());
            }
        }
    }

    public static void e(long j4, q3.e eVar, int i4, ArrayList arrayList, int i5, int i6, ArrayList arrayList2) {
        int i7;
        int i8;
        ArrayList arrayList3;
        long j5;
        int i9;
        int i10 = i4;
        ArrayList arrayList4 = arrayList;
        ArrayList arrayList5 = arrayList2;
        if (i5 >= i6) {
            throw new IllegalArgumentException("Failed requirement.");
        }
        for (int i11 = i5; i11 < i6; i11++) {
            if (((q3.h) arrayList4.get(i11)).a() < i10) {
                throw new IllegalArgumentException("Failed requirement.");
            }
        }
        q3.h hVar = (q3.h) arrayList.get(i5);
        q3.h hVar2 = (q3.h) arrayList4.get(i6 - 1);
        if (i10 == hVar.a()) {
            int iIntValue = ((Number) arrayList5.get(i5)).intValue();
            int i12 = i5 + 1;
            q3.h hVar3 = (q3.h) arrayList4.get(i12);
            i7 = i12;
            i8 = iIntValue;
            hVar = hVar3;
        } else {
            i7 = i5;
            i8 = -1;
        }
        if (hVar.d(i10) == hVar2.d(i10)) {
            int iMin = Math.min(hVar.a(), hVar2.a());
            int i13 = 0;
            for (int i14 = i10; i14 < iMin && hVar.d(i14) == hVar2.d(i14); i14++) {
                i13++;
            }
            long j6 = 4;
            long j7 = (eVar.f1822e / j6) + j4 + ((long) 2) + ((long) i13) + 1;
            eVar.a0(-i13);
            eVar.a0(i8);
            int i15 = i10 + i13;
            while (i10 < i15) {
                eVar.a0(hVar.d(i10) & 255);
                i10++;
            }
            if (i7 + 1 == i6) {
                if (i15 != ((q3.h) arrayList4.get(i7)).a()) {
                    throw new IllegalStateException("Check failed.");
                }
                eVar.a0(((Number) arrayList5.get(i7)).intValue());
                return;
            } else {
                q3.e eVar2 = new q3.e();
                eVar.a0(((int) ((eVar2.f1822e / j6) + j7)) * (-1));
                e(j7, eVar2, i15, arrayList4, i7, i6, arrayList5);
                eVar.W(eVar2);
                return;
            }
        }
        int i16 = 1;
        for (int i17 = i7 + 1; i17 < i6; i17++) {
            if (((q3.h) arrayList4.get(i17 - 1)).d(i10) != ((q3.h) arrayList4.get(i17)).d(i10)) {
                i16++;
            }
        }
        long j8 = 4;
        long j9 = (eVar.f1822e / j8) + j4 + ((long) 2) + ((long) (i16 * 2));
        eVar.a0(i16);
        eVar.a0(i8);
        for (int i18 = i7; i18 < i6; i18++) {
            int iD = ((q3.h) arrayList4.get(i18)).d(i10);
            if (i18 == i7 || iD != ((q3.h) arrayList4.get(i18 - 1)).d(i10)) {
                eVar.a0(iD & 255);
            }
        }
        q3.e eVar3 = new q3.e();
        int i19 = i7;
        while (i19 < i6) {
            byte bD = ((q3.h) arrayList4.get(i19)).d(i10);
            int i20 = i19 + 1;
            int i21 = i20;
            while (true) {
                if (i21 >= i6) {
                    i21 = i6;
                    break;
                } else if (bD != ((q3.h) arrayList4.get(i21)).d(i10)) {
                    break;
                } else {
                    i21++;
                }
            }
            if (i20 == i21 && i10 + 1 == ((q3.h) arrayList4.get(i19)).a()) {
                eVar.a0(((Number) arrayList5.get(i19)).intValue());
                arrayList3 = arrayList5;
                j5 = j9;
                i9 = i21;
            } else {
                eVar.a0(((int) ((eVar3.f1822e / j8) + j9)) * (-1));
                arrayList3 = arrayList5;
                j5 = j9;
                i9 = i21;
                e(j5, eVar3, i10 + 1, arrayList, i19, i9, arrayList3);
                arrayList4 = arrayList;
            }
            j9 = j5;
            i19 = i9;
            arrayList5 = arrayList3;
        }
        eVar.W(eVar3);
    }

    public static final void e0(a3.a0 a0Var) {
        a3.b0 b0Var;
        Charset charsetForName;
        String string;
        String str = a0Var.f62b;
        a3.t tVar = (a3.t) a0Var.f63c;
        try {
            if (!p2.i.B0(tVar.f215h, "device/dau") || l0("persist.log.dau.enable")) {
                StringBuilder sb = new StringBuilder();
                sb.append("🚀 开始网络请求");
                sb.append('\n');
                sb.append("URL    : " + tVar + "   [" + F(a0Var) + "]");
                sb.append('\n');
                StringBuilder sb2 = new StringBuilder("Method : ");
                sb2.append(str);
                sb.append(sb2.toString());
                sb.append('\n');
                if (j2.i.a(str, "GET")) {
                    List list = tVar.f213f;
                    if (list == null) {
                        string = null;
                    } else {
                        StringBuilder sb3 = new StringBuilder();
                        a3.b.b(list, sb3);
                        string = sb3.toString();
                    }
                    if (string != null && string.length() != 0) {
                        sb.append("Params :");
                        sb.append('\n');
                        Iterator it = p2.i.O0(string, new String[]{"&"}, 6).iterator();
                        while (it.hasNext()) {
                            List listO0 = p2.i.O0((String) it.next(), new String[]{"="}, 2);
                            ArrayList arrayList = new ArrayList(v1.l.u0(listO0));
                            Iterator it2 = listO0.iterator();
                            while (it2.hasNext()) {
                                arrayList.add(URLDecoder.decode((String) it2.next(), "UTF-8"));
                            }
                            String str2 = (String) arrayList.get(0);
                            String strZ = (String) arrayList.get(1);
                            j2.i.b(strZ);
                            try {
                                strZ = Z(l0.E(strZ));
                            } catch (Exception unused) {
                            }
                            sb.append("  " + str2 + " = " + strZ);
                            sb.append('\n');
                        }
                    }
                }
                if (j2.i.a(str, "POST") && (b0Var = (a3.b0) a0Var.f65e) != null) {
                    q3.e eVar = new q3.e();
                    b0Var.c(eVar);
                    v vVarB = b0Var.b();
                    if (vVarB == null || (charsetForName = vVarB.a(Charset.forName("UTF-8"))) == null) {
                        charsetForName = Charset.forName("UTF-8");
                    }
                    j2.i.b(charsetForName);
                    String strO = eVar.O(charsetForName);
                    if (j2.i.a(((r) a0Var.f64d).a("X-Encrypted"), "AES-GCM")) {
                        strO = u(strO);
                    }
                    sb.append("Body   :");
                    sb.append('\n');
                    try {
                        strO = Z(l0.E(strO));
                    } catch (Exception unused2) {
                    }
                    sb.append(strO);
                    sb.append('\n');
                }
                sb.append("────────────────────────────End");
                sb.append('\n');
                c0(sb.toString());
            }
        } catch (Exception e4) {
            a1.c.f("请求打印异常：", e4.getMessage());
        }
    }

    public static final LinkedHashSet f(byte[] bArr) throws IOException {
        j2.i.e(bArr, "bytes");
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        if (bArr.length == 0) {
            return linkedHashSet;
        }
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        try {
            try {
                ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
                try {
                    int i4 = objectInputStream.readInt();
                    for (int i5 = 0; i5 < i4; i5++) {
                        Uri uri = Uri.parse(objectInputStream.readUTF());
                        boolean z3 = objectInputStream.readBoolean();
                        j2.i.b(uri);
                        linkedHashSet.add(new d0.d(uri, z3));
                    }
                    objectInputStream.close();
                    byteArrayInputStream.close();
                    return linkedHashSet;
                } catch (Throwable th) {
                    try {
                        throw th;
                    } catch (Throwable th2) {
                        j(objectInputStream, th);
                        throw th2;
                    }
                }
            } catch (IOException e4) {
                e4.printStackTrace();
            }
        } catch (Throwable th3) {
            try {
                throw th3;
            } catch (Throwable th4) {
                j(byteArrayInputStream, th3);
                throw th4;
            }
        }
    }

    public static void h0(i2.p pVar) throws JSONException {
        o1.a aVar = e1.a.f703a;
        o1.a aVar2 = o1.a.f1558d;
        String str = aVar == aVar2 ? "" : "/v2";
        c0.f1036a.getClass();
        String str2 = c0.d().get(0) + "appapi/ad/sdk" + str;
        a3.x xVar = l1.f.f1368a;
        o1.a aVar3 = e1.a.f703a;
        u1.f fVar = new u1.f("channel", c0.c());
        d1.g.f520a.getClass();
        u1.f fVar2 = new u1.f("sdk_version", d1.g.b());
        String strG = G();
        int i4 = 5;
        u1.f[] fVarArr = {fVar, fVar2, new u1.f("mac", strG != null ? strG : ""), new u1.f("os_sdk_version", String.valueOf(Build.VERSION.SDK_INT)), new u1.f("os_platform_version", "Android " + Build.VERSION.RELEASE), new u1.f("plugin_path", d1.g.a())};
        LinkedHashMap linkedHashMap = new LinkedHashMap(v1.t.J(6));
        v1.t.L(linkedHashMap, fVarArr);
        if (d1.g.b().compareTo((String) d1.g.f525f.a(d1.g.f521b[4])) > 0) {
            linkedHashMap.put("upgrade_time", Long.valueOf(System.currentTimeMillis()));
        }
        a.a.d(linkedHashMap);
        Type type = new t1.p().f2779b;
        j2.i.d(type, "getType(...)");
        a3.x xVar2 = l1.f.f1368a;
        String strA = aVar3 == aVar2 ? l1.f.a(str2, linkedHashMap) : l1.f.a(str2, v1.q.f2518d);
        z zVar = new z();
        zVar.d(strA);
        if (aVar3 == o1.a.f1559e) {
            String strE = l1.f.f1369b.e(linkedHashMap);
            j2.i.b(strE);
            String strX = x(strE);
            int i5 = a3.b0.f70d;
            p2.h hVar = v.f216c;
            zVar.c("POST", s(l0.t("application/json; charset=utf-8"), strX));
            zVar.a("Content-Type", "application/json; charset=utf-8");
            zVar.a("X-Encrypted", "AES-GCM");
        }
        a3.a0 a0Var = new a3.a0(zVar);
        xVar2.getClass();
        new e3.p(xVar2, a0Var).e(new a2.f(aVar3, type, pVar, i4));
    }

    public static void i0(boolean z3) {
        p1.a.f1737b = null;
        c0.f1036a.getClass();
        a3.a0 a0Var = c0.f1045j;
        n2.c[] cVarArr = c0.f1037b;
        a0Var.e(cVarArr[3], "");
        p1.a.f1736a.set(0);
        if (z3) {
            c0.f1044i.e(cVarArr[2], "https://random.vivosoc.cc/");
            c0.f1055t.e(cVarArr[14], 0L);
            c0.f1056u.e(cVarArr[15], 0L);
            c0.f1059x.e(cVarArr[18], Boolean.FALSE);
        }
    }

    public static final void j(Closeable closeable, Throwable th) throws IOException {
        if (closeable != null) {
            if (th == null) {
                closeable.close();
                return;
            }
            try {
                closeable.close();
            } catch (Throwable th2) {
                a(th, th2);
            }
        }
    }

    public static String j0(Throwable th) {
        j2.i.e(th, "<this>");
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        th.printStackTrace(printWriter);
        printWriter.flush();
        String string = stringWriter.toString();
        j2.i.d(string, "toString(...)");
        return string;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final void k(w.c cVar, Throwable th) {
        if (cVar != 0) {
            if (th != null) {
                try {
                    a1.c.g(cVar);
                    return;
                } catch (Throwable th2) {
                    a(th, th2);
                    return;
                }
            }
            if (cVar instanceof AutoCloseable) {
                cVar.close();
                return;
            }
            if (cVar instanceof ExecutorService) {
                e0.x.f((ExecutorService) cVar);
                return;
            }
            if (cVar instanceof TypedArray) {
                ((TypedArray) cVar).recycle();
                return;
            }
            if (cVar instanceof MediaMetadataRetriever) {
                ((MediaMetadataRetriever) cVar).release();
                return;
            }
            if (cVar instanceof MediaDrm) {
                ((MediaDrm) cVar).release();
            } else if (cVar instanceof DrmManagerClient) {
                ((DrmManagerClient) cVar).release();
            } else {
                if (!(cVar instanceof ContentProviderClient)) {
                    throw new IllegalArgumentException();
                }
                ((ContentProviderClient) cVar).release();
            }
        }
    }

    public static final int k0(k0 k0Var) {
        j2.i.e(k0Var, "state");
        int iOrdinal = k0Var.ordinal();
        if (iOrdinal == 0) {
            return 0;
        }
        int i4 = 1;
        if (iOrdinal != 1) {
            i4 = 2;
            if (iOrdinal != 2) {
                i4 = 3;
                if (iOrdinal != 3) {
                    i4 = 4;
                    if (iOrdinal != 4) {
                        if (iOrdinal == 5) {
                            return 5;
                        }
                        throw new a0.c();
                    }
                }
            }
        }
        return i4;
    }

    public static final int l(w.c cVar, String str) {
        j2.i.e(cVar, "<this>");
        int iM = m(cVar, str);
        if (iM >= 0) {
            return iM;
        }
        int iM2 = m(cVar, "`" + str + '`');
        if (iM2 >= 0) {
            return iM2;
        }
        if (Build.VERSION.SDK_INT > 25 || str.length() == 0) {
            return -1;
        }
        int columnCount = cVar.getColumnCount();
        String strConcat = ".".concat(str);
        String str2 = "." + str + '`';
        for (int i4 = 0; i4 < columnCount; i4++) {
            String columnName = cVar.getColumnName(i4);
            if (columnName.length() >= str.length() + 2 && (p2.p.u0(columnName, strConcat) || (columnName.charAt(0) == '`' && p2.p.u0(columnName, str2)))) {
                return i4;
            }
        }
        return -1;
    }

    public static boolean l0(String str) {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            Object objInvoke = cls.getMethod("getBoolean", String.class, Boolean.TYPE).invoke(cls, str, false);
            j2.i.c(objInvoke, "null cannot be cast to non-null type kotlin.Boolean");
            return ((Boolean) objInvoke).booleanValue();
        } catch (Exception unused) {
            return false;
        }
    }

    public static final int m(w.c cVar, String str) {
        j2.i.e(cVar, "<this>");
        j2.i.e(str, "name");
        int columnCount = cVar.getColumnCount();
        for (int i4 = 0; i4 < columnCount; i4++) {
            if (str.equals(cVar.getColumnName(i4))) {
                return i4;
            }
        }
        return -1;
    }

    public static final void m0(int i4, String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("Error code: " + i4);
        if (str != null) {
            sb.append(", message: ".concat(str));
        }
        throw new SQLException(sb.toString());
    }

    public static final void n(a3.q qVar, String str, String str2) {
        j2.i.e(str2, "value");
        ArrayList arrayList = qVar.f197a;
        arrayList.add(str);
        arrayList.add(p2.i.S0(str2).toString());
    }

    public static byte[] n0(d0.j jVar) {
        j2.i.e(jVar, "data");
        HashMap map = jVar.f465a;
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            try {
                dataOutputStream.writeShort(-21521);
                dataOutputStream.writeShort(1);
                dataOutputStream.writeInt(map.size());
                for (Map.Entry entry : map.entrySet()) {
                    o0(dataOutputStream, (String) entry.getKey(), entry.getValue());
                }
                dataOutputStream.flush();
                if (dataOutputStream.size() > 10240) {
                    throw new IllegalStateException("Data cannot occupy more than 10240 bytes when serialized");
                }
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                dataOutputStream.close();
                j2.i.b(byteArray);
                return byteArray;
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    j(dataOutputStream, th);
                    throw th2;
                }
            }
        } catch (IOException e4) {
            a0.e().d(d0.k.f466a, "Error in Data#toByteArray: ", e4);
            return new byte[0];
        }
    }

    public static int o(Comparable comparable, Comparable comparable2) {
        if (comparable == comparable2) {
            return 0;
        }
        if (comparable == null) {
            return -1;
        }
        if (comparable2 == null) {
            return 1;
        }
        return comparable.compareTo(comparable2);
    }

    public static final void o0(DataOutputStream dataOutputStream, String str, Object obj) throws IOException {
        int i4;
        if (obj == null) {
            dataOutputStream.writeByte(0);
        } else if (obj instanceof Boolean) {
            dataOutputStream.writeByte(1);
            dataOutputStream.writeBoolean(((Boolean) obj).booleanValue());
        } else if (obj instanceof Byte) {
            dataOutputStream.writeByte(2);
            dataOutputStream.writeByte(((Number) obj).byteValue());
        } else if (obj instanceof Integer) {
            dataOutputStream.writeByte(3);
            dataOutputStream.writeInt(((Number) obj).intValue());
        } else if (obj instanceof Long) {
            dataOutputStream.writeByte(4);
            dataOutputStream.writeLong(((Number) obj).longValue());
        } else if (obj instanceof Float) {
            dataOutputStream.writeByte(5);
            dataOutputStream.writeFloat(((Number) obj).floatValue());
        } else if (obj instanceof Double) {
            dataOutputStream.writeByte(6);
            dataOutputStream.writeDouble(((Number) obj).doubleValue());
        } else if (obj instanceof String) {
            dataOutputStream.writeByte(7);
            dataOutputStream.writeUTF((String) obj);
        } else {
            if (!(obj instanceof Object[])) {
                throw new IllegalArgumentException("Unsupported value type " + j2.o.a(obj.getClass()).c());
            }
            Object[] objArr = (Object[]) obj;
            j2.e eVarA = j2.o.a(objArr.getClass());
            if (eVarA.equals(j2.o.a(Boolean[].class))) {
                i4 = 8;
            } else if (eVarA.equals(j2.o.a(Byte[].class))) {
                i4 = 9;
            } else if (eVarA.equals(j2.o.a(Integer[].class))) {
                i4 = 10;
            } else if (eVarA.equals(j2.o.a(Long[].class))) {
                i4 = 11;
            } else if (eVarA.equals(j2.o.a(Float[].class))) {
                i4 = 12;
            } else if (eVarA.equals(j2.o.a(Double[].class))) {
                i4 = 13;
            } else {
                if (!eVarA.equals(j2.o.a(String[].class))) {
                    throw new IllegalArgumentException("Unsupported value type " + j2.o.a(objArr.getClass()).b());
                }
                i4 = 14;
            }
            dataOutputStream.writeByte(i4);
            dataOutputStream.writeInt(objArr.length);
            for (Object obj2 : objArr) {
                if (i4 == 8) {
                    Boolean bool = obj2 instanceof Boolean ? (Boolean) obj2 : null;
                    dataOutputStream.writeBoolean(bool != null ? bool.booleanValue() : false);
                } else if (i4 == 9) {
                    Byte b4 = obj2 instanceof Byte ? (Byte) obj2 : null;
                    dataOutputStream.writeByte(b4 != null ? b4.byteValue() : (byte) 0);
                } else if (i4 == 10) {
                    Integer num = obj2 instanceof Integer ? (Integer) obj2 : null;
                    dataOutputStream.writeInt(num != null ? num.intValue() : 0);
                } else if (i4 == 11) {
                    Long l4 = obj2 instanceof Long ? (Long) obj2 : null;
                    dataOutputStream.writeLong(l4 != null ? l4.longValue() : 0L);
                } else if (i4 == 12) {
                    Float f2 = obj2 instanceof Float ? (Float) obj2 : null;
                    dataOutputStream.writeFloat(f2 != null ? f2.floatValue() : 0.0f);
                } else if (i4 == 13) {
                    Double d4 = obj2 instanceof Double ? (Double) obj2 : null;
                    dataOutputStream.writeDouble(d4 != null ? d4.doubleValue() : 0.0d);
                } else if (i4 == 14) {
                    String str2 = obj2 instanceof String ? (String) obj2 : null;
                    if (str2 == null) {
                        str2 = "androidx.work.Data-95ed6082-b8e9-46e8-a73f-ff56f00f5d9d";
                    }
                    dataOutputStream.writeUTF(str2);
                }
            }
        }
        dataOutputStream.writeUTF(str);
    }

    public static void p() {
        try {
            Class<?> cls = Class.forName("com.android.okhttp.OkHttpClient");
            Object objNewInstance = cls.newInstance();
            try {
                cls.getMethod("setSslSocketFactory", SSLSocketFactory.class).invoke(objNewInstance, f1387f);
            } catch (Exception unused) {
            }
            cls.getMethod("setHostnameVerifier", HostnameVerifier.class).invoke(objNewInstance, f1388g);
        } catch (Exception unused2) {
        }
    }

    public static final m0.f p0(byte[] bArr) throws IOException {
        j2.i.e(bArr, "bytes");
        if (Build.VERSION.SDK_INT < 28 || bArr.length == 0) {
            return new m0.f(null);
        }
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            try {
                int i4 = objectInputStream.readInt();
                int[] iArr = new int[i4];
                for (int i5 = 0; i5 < i4; i5++) {
                    iArr[i5] = objectInputStream.readInt();
                }
                int i6 = objectInputStream.readInt();
                int[] iArr2 = new int[i6];
                for (int i7 = 0; i7 < i6; i7++) {
                    iArr2[i7] = objectInputStream.readInt();
                }
                m0.f fVarC = m0.g.c(iArr2, iArr);
                objectInputStream.close();
                byteArrayInputStream.close();
                return fVarC;
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    j(objectInputStream, th);
                    throw th2;
                }
            }
        } catch (Throwable th3) {
            try {
                throw th3;
            } catch (Throwable th4) {
                j(byteArrayInputStream, th3);
                throw th4;
            }
        }
    }

    public static void q(HttpsURLConnection httpsURLConnection) {
        try {
            Log.d("SSLUtils", "Configuring SSL ignore for connection: " + httpsURLConnection.getURL());
            TrustManager[] trustManagerArr = {new n1.d(0)};
            SSLContext sSLContext = SSLContext.getInstance("TLS");
            sSLContext.init(null, trustManagerArr, new SecureRandom());
            httpsURLConnection.setSSLSocketFactory(sSLContext.getSocketFactory());
            httpsURLConnection.setHostnameVerifier(new c1.a(2));
            Log.d("SSLUtils", "SSL ignore configured successfully for connection");
        } catch (Exception e4) {
            Log.e("SSLUtils", "Failed to configure SSL ignore for connection", e4);
            e4.printStackTrace();
        }
    }

    public static final q q0(q qVar) {
        if (!(qVar instanceof u) || !(qVar.a().f2136d instanceof String)) {
            return qVar;
        }
        String strB = qVar.b();
        j2.i.b(strB);
        if ((!p2.p.z0(strB, "{", false) || !p2.p.u0(strB, "}")) && (!p2.p.z0(strB, "[", false) || !p2.p.u0(strB, "]"))) {
            return qVar;
        }
        try {
            return l0.E(strB);
        } catch (Exception unused) {
            return qVar;
        }
    }

    public static final long r(long j4, q2.c cVar, q2.c cVar2) {
        j2.i.e(cVar, "sourceUnit");
        j2.i.e(cVar2, "targetUnit");
        return cVar2.f1808d.convert(j4, cVar.f1808d);
    }

    public static a3.b0 s(v vVar, String str) {
        Charset charset = p2.a.f1738a;
        p2.h hVar = v.f216c;
        Charset charsetA = vVar.a(null);
        if (charsetA == null) {
            String str2 = vVar + "; charset=utf-8";
            j2.i.e(str2, "<this>");
            try {
                vVar = l0.t(str2);
            } catch (IllegalArgumentException unused) {
                vVar = null;
            }
        } else {
            charset = charsetA;
        }
        byte[] bytes = str.getBytes(charset);
        j2.i.d(bytes, "getBytes(...)");
        int length = bytes.length;
        b3.d.a(bytes.length, 0, length);
        return new a3.b0(vVar, length, bytes);
    }

    public static int t(String str, int i4, int i5, boolean z3) {
        while (i4 < i5) {
            char cCharAt = str.charAt(i4);
            if (((cCharAt < ' ' && cCharAt != '\t') || cCharAt >= 127 || ('0' <= cCharAt && cCharAt < ':') || (('a' <= cCharAt && cCharAt < '{') || (('A' <= cCharAt && cCharAt < '[') || cCharAt == ':'))) == (!z3)) {
                return i4;
            }
            i4++;
        }
        return i5;
    }

    public static String u(String str) {
        j2.i.e(str, "<this>");
        if (str.length() <= 0) {
            throw new IllegalArgumentException("密文不能为空");
        }
        if ("LvEZr95h9LZgbJ2gNKhV3bgUnhx4ZKDg".length() <= 0) {
            throw new IllegalArgumentException("密钥不能为空");
        }
        try {
            byte[] bArrDecode = Base64.decode(str, 0);
            Charset charset = p2.a.f1738a;
            byte[] bytes = "LvEZr95h9LZgbJ2gNKhV3bgUnhx4ZKDg".getBytes(charset);
            j2.i.d(bytes, "getBytes(...)");
            SecretKeySpec secretKeySpec = new SecretKeySpec(bytes, "AES");
            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
            if (bArrDecode.length < 12) {
                throw new IllegalArgumentException("密文长度不足");
            }
            byte[] bArrX = v1.i.X(bArrDecode, 0, 12);
            byte[] bArrX2 = v1.i.X(bArrDecode, 12, bArrDecode.length);
            cipher.init(2, secretKeySpec, new GCMParameterSpec(128, bArrX));
            byte[] bArrDoFinal = cipher.doFinal(bArrX2);
            j2.i.b(bArrDoFinal);
            String str2 = new String(bArrDoFinal, charset);
            b0("解密成功，明文: ".concat(str2));
            return str2;
        } catch (Exception e4) {
            b0("解密失败: " + e4.getMessage());
            throw new IllegalArgumentException("AES 解密失败: " + e4.getMessage(), e4);
        }
    }

    public static final boolean v(String str, String str2) {
        j2.i.e(str, "current");
        if (str.equals(str2)) {
            return true;
        }
        if (str.length() != 0) {
            int i4 = 0;
            int i5 = 0;
            int i6 = 0;
            while (i4 < str.length()) {
                char cCharAt = str.charAt(i4);
                int i7 = i6 + 1;
                if (i6 != 0 || cCharAt == '(') {
                    if (cCharAt == '(') {
                        i5++;
                    } else if (cCharAt == ')' && (i5 = i5 - 1) == 0 && i6 != str.length() - 1) {
                    }
                    i4++;
                    i6 = i7;
                }
            }
            if (i5 == 0) {
                String strSubstring = str.substring(1, str.length() - 1);
                j2.i.d(strSubstring, "substring(...)");
                return j2.i.a(p2.i.S0(strSubstring).toString(), str2);
            }
        }
        return false;
    }

    public static void w() {
        if (f1386e) {
            return;
        }
        try {
            Log.i("SSLUtils", "Enabling SSL certificate ignore for all HTTPS connections");
            TrustManager[] trustManagerArr = {new n1.d(1)};
            SSLContext sSLContext = SSLContext.getInstance("TLS");
            sSLContext.init(null, trustManagerArr, new SecureRandom());
            SSLSocketFactory socketFactory = sSLContext.getSocketFactory();
            f1387f = socketFactory;
            HttpsURLConnection.setDefaultSSLSocketFactory(socketFactory);
            Log.i("SSLUtils", "Set default SSLSocketFactory for HttpsURLConnection");
            c1.a aVar = new c1.a(3);
            f1388g = aVar;
            HttpsURLConnection.setDefaultHostnameVerifier(aVar);
            Log.i("SSLUtils", "Set default HostnameVerifier for HttpsURLConnection");
            f1386e = true;
            try {
                p();
                try {
                    Class.forName("com.android.okhttp.internal.Internal");
                } catch (Exception unused) {
                }
                Class.forName("com.android.okhttp.internal.huc.HttpsURLConnectionImpl");
            } catch (Exception unused2) {
            }
            Log.i("SSLUtils", "SSL certificate ignore enabled successfully");
        } catch (Exception e4) {
            Log.e("SSLUtils", "Failed to enable SSL ignore", e4);
            e4.printStackTrace();
        }
    }

    public static String x(String str) {
        if (str.length() <= 0) {
            throw new IllegalArgumentException("明文不能为空");
        }
        if ("LvEZr95h9LZgbJ2gNKhV3bgUnhx4ZKDg".length() <= 0) {
            throw new IllegalArgumentException("密钥不能为空");
        }
        b0("开始加密明文: ".concat(str));
        try {
            Charset charset = p2.a.f1738a;
            byte[] bytes = "LvEZr95h9LZgbJ2gNKhV3bgUnhx4ZKDg".getBytes(charset);
            j2.i.d(bytes, "getBytes(...)");
            SecretKeySpec secretKeySpec = new SecretKeySpec(bytes, "AES");
            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
            byte[] bArr = new byte[12];
            new SecureRandom().nextBytes(bArr);
            cipher.init(1, secretKeySpec, new GCMParameterSpec(128, bArr));
            byte[] bytes2 = str.getBytes(charset);
            j2.i.d(bytes2, "getBytes(...)");
            byte[] bArrDoFinal = cipher.doFinal(bytes2);
            j2.i.b(bArrDoFinal);
            int length = bArrDoFinal.length;
            byte[] bArrCopyOf = Arrays.copyOf(bArr, 12 + length);
            System.arraycopy(bArrDoFinal, 0, bArrCopyOf, 12, length);
            j2.i.b(bArrCopyOf);
            String strEncodeToString = Base64.encodeToString(bArrCopyOf, 0);
            b0("加密成功，Base64 编码: " + strEncodeToString);
            j2.i.b(strEncodeToString);
            return strEncodeToString;
        } catch (Exception e4) {
            b0("加密失败: " + e4.getMessage());
            throw new IllegalArgumentException("AES 加密失败: " + e4.getMessage(), e4);
        }
    }

    public static final void y(w.a aVar, String str) {
        j2.i.e(aVar, "<this>");
        j2.i.e(str, "sql");
        w.c cVarP = aVar.P(str);
        try {
            cVarP.F();
            k(cVarP, null);
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                k(cVarP, th);
                throw th2;
            }
        }
    }

    public static final String z(Collection collection) {
        j2.i.e(collection, "collection");
        if (collection.isEmpty()) {
            return " }";
        }
        return p2.j.r0(v1.j.y0(collection, ",\n", "\n", "\n", null, 56)) + "},";
    }

    public abstract void f0(g.g gVar, g.g gVar2);

    public abstract boolean g(g.h hVar, g.d dVar, g.d dVar2);

    public abstract void g0(g.g gVar, Thread thread);

    public abstract boolean h(g.h hVar, Object obj, Object obj2);

    public abstract boolean i(g.h hVar, g.g gVar, g.g gVar2);
}
