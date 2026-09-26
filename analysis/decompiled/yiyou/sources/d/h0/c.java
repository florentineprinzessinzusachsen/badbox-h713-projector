package d.h0;

import com.baidu.mobstat.Config;
import d.b0;
import d.d0;
import d.t;
import d.v;
import e.e;
import e.f;
import e.s;
import java.io.Closeable;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.IDN;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

/* JADX INFO: compiled from: Util.java */
/* JADX INFO: loaded from: classes.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final byte[] f4337a = new byte[0];

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final String[] f4338b = new String[0];

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final d0 f4339c = d0.create((v) null, f4337a);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final f f4340d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static final f f4341e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static final f f4342f;
    private static final f g;
    private static final f h;
    public static final Charset i;
    private static final Charset j;
    private static final Charset k;
    private static final Charset l;
    private static final Charset m;
    public static final TimeZone n;
    public static final Comparator<String> o;
    private static final Method p;
    private static final Pattern q;

    /* JADX INFO: compiled from: Util.java */
    class a implements Comparator<String> {
        a() {
        }

        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(String str, String str2) {
            return str.compareTo(str2);
        }
    }

    /* JADX INFO: compiled from: Util.java */
    class b implements ThreadFactory {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ String f4343a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ boolean f4344b;

        b(String str, boolean z) {
            this.f4343a = str;
            this.f4344b = z;
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable, this.f4343a);
            thread.setDaemon(this.f4344b);
            return thread;
        }
    }

    static {
        Method declaredMethod = null;
        b0.create((v) null, f4337a);
        f4340d = f.b("efbbbf");
        f4341e = f.b("feff");
        f4342f = f.b("fffe");
        g = f.b("0000ffff");
        h = f.b("ffff0000");
        i = Charset.forName("UTF-8");
        Charset.forName("ISO-8859-1");
        j = Charset.forName("UTF-16BE");
        k = Charset.forName("UTF-16LE");
        l = Charset.forName("UTF-32BE");
        m = Charset.forName("UTF-32LE");
        n = TimeZone.getTimeZone("GMT");
        o = new a();
        try {
            declaredMethod = Throwable.class.getDeclaredMethod("addSuppressed", Throwable.class);
        } catch (Exception unused) {
        }
        p = declaredMethod;
        q = Pattern.compile("([0-9a-fA-F]*:[0-9a-fA-F:.]*)|([\\d.]+)");
    }

    public static int a(char c2) {
        if (c2 >= '0' && c2 <= '9') {
            return c2 - '0';
        }
        char c3 = 'a';
        if (c2 < 'a' || c2 > 'f') {
            c3 = 'A';
            if (c2 < 'A' || c2 > 'F') {
                return -1;
            }
        }
        return (c2 - c3) + 10;
    }

    public static void a(Throwable th, Throwable th2) {
        Method method = p;
        if (method != null) {
            try {
                method.invoke(th, th2);
            } catch (IllegalAccessException | InvocationTargetException unused) {
            }
        }
    }

    public static boolean b(s sVar, int i2, TimeUnit timeUnit) {
        long jNanoTime = System.nanoTime();
        long jC = sVar.timeout().d() ? sVar.timeout().c() - jNanoTime : Long.MAX_VALUE;
        sVar.timeout().a(Math.min(jC, timeUnit.toNanos(i2)) + jNanoTime);
        try {
            e.c cVar = new e.c();
            while (sVar.read(cVar, 8192L) != -1) {
                cVar.a();
            }
            if (jC == Long.MAX_VALUE) {
                sVar.timeout().a();
            } else {
                sVar.timeout().a(jNanoTime + jC);
            }
            return true;
        } catch (InterruptedIOException unused) {
            if (jC == Long.MAX_VALUE) {
                sVar.timeout().a();
            } else {
                sVar.timeout().a(jNanoTime + jC);
            }
            return false;
        } catch (Throwable th) {
            if (jC == Long.MAX_VALUE) {
                sVar.timeout().a();
            } else {
                sVar.timeout().a(jNanoTime + jC);
            }
            throw th;
        }
    }

    public static int c(String str, int i2, int i3) {
        for (int i4 = i3 - 1; i4 >= i2; i4--) {
            char cCharAt = str.charAt(i4);
            if (cCharAt != '\t' && cCharAt != '\n' && cCharAt != '\f' && cCharAt != '\r' && cCharAt != ' ') {
                return i4 + 1;
            }
        }
        return i2;
    }

    public static String d(String str, int i2, int i3) {
        int iB = b(str, i2, i3);
        return str.substring(iB, c(str, iB, i3));
    }

    public static int c(String str) {
        int length = str.length();
        for (int i2 = 0; i2 < length; i2++) {
            char cCharAt = str.charAt(i2);
            if (cCharAt <= 31 || cCharAt >= 127) {
                return i2;
            }
        }
        return -1;
    }

    public static void a(long j2, long j3, long j4) {
        if ((j3 | j4) < 0 || j3 > j2 || j2 - j3 < j4) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    public static boolean a(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    public static boolean d(String str) {
        return q.matcher(str).matches();
    }

    public static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (RuntimeException e2) {
                throw e2;
            } catch (Exception unused) {
            }
        }
    }

    public static void a(Socket socket) {
        if (socket != null) {
            try {
                socket.close();
            } catch (AssertionError e2) {
                if (!a(e2)) {
                    throw e2;
                }
            } catch (RuntimeException e3) {
                throw e3;
            } catch (Exception unused) {
            }
        }
    }

    public static boolean a(s sVar, int i2, TimeUnit timeUnit) {
        try {
            return b(sVar, i2, timeUnit);
        } catch (IOException unused) {
            return false;
        }
    }

    public static <T> List<T> a(List<T> list) {
        return Collections.unmodifiableList(new ArrayList(list));
    }

    public static <K, V> Map<K, V> a(Map<K, V> map) {
        if (map.isEmpty()) {
            return Collections.emptyMap();
        }
        return Collections.unmodifiableMap(new LinkedHashMap(map));
    }

    public static boolean b(Comparator<String> comparator, String[] strArr, String[] strArr2) {
        if (strArr != null && strArr2 != null && strArr.length != 0 && strArr2.length != 0) {
            for (String str : strArr) {
                for (String str2 : strArr2) {
                    if (comparator.compare(str, str2) == 0) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static <T> List<T> a(T... tArr) {
        return Collections.unmodifiableList(Arrays.asList((Object[]) tArr.clone()));
    }

    public static ThreadFactory a(String str, boolean z) {
        return new b(str, z);
    }

    public static String[] a(Comparator<? super String> comparator, String[] strArr, String[] strArr2) {
        ArrayList arrayList = new ArrayList();
        for (String str : strArr) {
            for (String str2 : strArr2) {
                if (comparator.compare(str, str2) == 0) {
                    arrayList.add(str);
                    break;
                }
            }
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    public static int b(String str, int i2, int i3) {
        while (i2 < i3) {
            char cCharAt = str.charAt(i2);
            if (cCharAt != '\t' && cCharAt != '\n' && cCharAt != '\f' && cCharAt != '\r' && cCharAt != ' ') {
                return i2;
            }
            i2++;
        }
        return i3;
    }

    private static boolean b(String str) {
        for (int i2 = 0; i2 < str.length(); i2++) {
            char cCharAt = str.charAt(i2);
            if (cCharAt <= 31 || cCharAt >= 127 || " #%/:?@[\\]".indexOf(cCharAt) != -1) {
                return true;
            }
        }
        return false;
    }

    public static String a(t tVar, boolean z) {
        String strG;
        if (tVar.g().contains(Config.TRACE_TODAY_VISIT_SPLIT)) {
            strG = "[" + tVar.g() + "]";
        } else {
            strG = tVar.g();
        }
        if (!z && tVar.k() == t.c(tVar.o())) {
            return strG;
        }
        return strG + Config.TRACE_TODAY_VISIT_SPLIT + tVar.k();
    }

    public static boolean a(AssertionError assertionError) {
        return (assertionError.getCause() == null || assertionError.getMessage() == null || !assertionError.getMessage().contains("getsockname failed")) ? false : true;
    }

    public static int a(Comparator<String> comparator, String[] strArr, String str) {
        int length = strArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (comparator.compare(strArr[i2], str) == 0) {
                return i2;
            }
        }
        return -1;
    }

    public static String[] a(String[] strArr, String str) {
        String[] strArr2 = new String[strArr.length + 1];
        System.arraycopy(strArr, 0, strArr2, 0, strArr.length);
        strArr2[strArr2.length - 1] = str;
        return strArr2;
    }

    public static int a(String str, int i2, int i3, String str2) {
        while (i2 < i3) {
            if (str2.indexOf(str.charAt(i2)) != -1) {
                return i2;
            }
            i2++;
        }
        return i3;
    }

    public static int a(String str, int i2, int i3, char c2) {
        while (i2 < i3) {
            if (str.charAt(i2) == c2) {
                return i2;
            }
            i2++;
        }
        return i3;
    }

    public static String a(String str) {
        InetAddress inetAddressA;
        if (str.contains(Config.TRACE_TODAY_VISIT_SPLIT)) {
            if (str.startsWith("[") && str.endsWith("]")) {
                inetAddressA = a(str, 1, str.length() - 1);
            } else {
                inetAddressA = a(str, 0, str.length());
            }
            if (inetAddressA == null) {
                return null;
            }
            byte[] address = inetAddressA.getAddress();
            if (address.length == 16) {
                return a(address);
            }
            throw new AssertionError("Invalid IPv6 address: '" + str + "'");
        }
        try {
            String lowerCase = IDN.toASCII(str).toLowerCase(Locale.US);
            if (lowerCase.isEmpty() || b(lowerCase)) {
                return null;
            }
            return lowerCase;
        } catch (IllegalArgumentException unused) {
            return null;
        }
    }

    public static String a(String str, Object... objArr) {
        return String.format(Locale.US, str, objArr);
    }

    public static Charset a(e eVar, Charset charset) {
        if (eVar.a(0L, f4340d)) {
            eVar.skip(f4340d.f());
            return i;
        }
        if (eVar.a(0L, f4341e)) {
            eVar.skip(f4341e.f());
            return j;
        }
        if (eVar.a(0L, f4342f)) {
            eVar.skip(f4342f.f());
            return k;
        }
        if (eVar.a(0L, g)) {
            eVar.skip(g.f());
            return l;
        }
        if (!eVar.a(0L, h)) {
            return charset;
        }
        eVar.skip(h.f());
        return m;
    }

    public static int a(String str, long j2, TimeUnit timeUnit) {
        if (j2 < 0) {
            throw new IllegalArgumentException(str + " < 0");
        }
        if (timeUnit != null) {
            long millis = timeUnit.toMillis(j2);
            if (millis > 2147483647L) {
                throw new IllegalArgumentException(str + " too large.");
            }
            if (millis != 0 || j2 <= 0) {
                return (int) millis;
            }
            throw new IllegalArgumentException(str + " too small.");
        }
        throw new NullPointerException("unit == null");
    }

    public static AssertionError a(String str, Exception exc) {
        AssertionError assertionError = new AssertionError(str);
        try {
            assertionError.initCause(exc);
        } catch (IllegalStateException unused) {
        }
        return assertionError;
    }

    /* JADX WARN: Code duplicated, block: B:32:0x0050  */
    /* JADX WARN: Code duplicated, block: B:35:0x005b A[LOOP:1: B:31:0x004e->B:35:0x005b, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:62:0x0061 A[EDGE_INSN: B:62:0x0061->B:36:0x0061 BREAK  A[LOOP:1: B:31:0x004e->B:35:0x005b], SYNTHETIC] */
    private static InetAddress a(String str, int i2, int i3) {
        int i4;
        int i5;
        int iA;
        byte[] bArr = new byte[16];
        int i6 = 0;
        int i7 = -1;
        int i8 = -1;
        while (i2 < i3) {
            if (i6 == bArr.length) {
                return null;
            }
            int i9 = i2 + 2;
            if (i9 <= i3 && str.regionMatches(i2, "::", 0, 2)) {
                if (i7 != -1) {
                    return null;
                }
                i6 += 2;
                i7 = i6;
                if (i9 == i3) {
                    break;
                }
                i8 = i9;
                i2 = i8;
                i4 = 0;
                while (i2 < i3) {
                    iA = a(str.charAt(i2));
                    if (iA == -1) {
                        break;
                        break;
                    }
                    i4 = (i4 << 4) + iA;
                    i2++;
                }
                i5 = i2 - i8;
                if (i5 != 0) {
                }
                return null;
            }
            if (i6 != 0) {
                if (!str.regionMatches(i2, Config.TRACE_TODAY_VISIT_SPLIT, 0, 1)) {
                    if (!str.regionMatches(i2, ".", 0, 1) || !a(str, i8, i3, bArr, i6 - 2)) {
                        return null;
                    }
                    i6 += 2;
                    break;
                }
                i2++;
            }
            i8 = i2;
            i2 = i8;
            i4 = 0;
            while (i2 < i3) {
                iA = a(str.charAt(i2));
                if (iA == -1) {
                    break;
                }
                i4 = (i4 << 4) + iA;
                i2++;
            }
            i5 = i2 - i8;
            if (i5 != 0 || i5 > 4) {
                return null;
            }
            int i10 = i6 + 1;
            bArr[i6] = (byte) ((i4 >>> 8) & 255);
            i6 = i10 + 1;
            bArr[i10] = (byte) (i4 & 255);
        }
        if (i6 != bArr.length) {
            if (i7 == -1) {
                return null;
            }
            int i11 = i6 - i7;
            System.arraycopy(bArr, i7, bArr, bArr.length - i11, i11);
            Arrays.fill(bArr, i7, (bArr.length - i6) + i7, (byte) 0);
        }
        try {
            return InetAddress.getByAddress(bArr);
        } catch (UnknownHostException unused) {
            throw new AssertionError();
        }
    }

    private static boolean a(String str, int i2, int i3, byte[] bArr, int i4) {
        int i5 = i4;
        while (i2 < i3) {
            if (i5 == bArr.length) {
                return false;
            }
            if (i5 != i4) {
                if (str.charAt(i2) != '.') {
                    return false;
                }
                i2++;
            }
            int i6 = i2;
            int i7 = 0;
            while (i6 < i3) {
                char cCharAt = str.charAt(i6);
                if (cCharAt < '0' || cCharAt > '9') {
                    break;
                }
                if ((i7 == 0 && i2 != i6) || (i7 = ((i7 * 10) + cCharAt) - 48) > 255) {
                    return false;
                }
                i6++;
            }
            if (i6 - i2 == 0) {
                return false;
            }
            bArr[i5] = (byte) i7;
            i5++;
            i2 = i6;
        }
        return i5 == i4 + 4;
    }

    private static String a(byte[] bArr) {
        int i2 = 0;
        int i3 = 0;
        int i4 = -1;
        int i5 = 0;
        while (i3 < bArr.length) {
            int i6 = i3;
            while (i6 < 16 && bArr[i6] == 0 && bArr[i6 + 1] == 0) {
                i6 += 2;
            }
            int i7 = i6 - i3;
            if (i7 > i5 && i7 >= 4) {
                i4 = i3;
                i5 = i7;
            }
            i3 = i6 + 2;
        }
        e.c cVar = new e.c();
        while (i2 < bArr.length) {
            if (i2 == i4) {
                cVar.writeByte(58);
                i2 += i5;
                if (i2 == 16) {
                    cVar.writeByte(58);
                }
            } else {
                if (i2 > 0) {
                    cVar.writeByte(58);
                }
                cVar.d(((bArr[i2] & 255) << 8) | (bArr[i2 + 1] & 255));
                i2 += 2;
            }
        }
        return cVar.o();
    }

    public static X509TrustManager a() {
        try {
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init((KeyStore) null);
            TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
            if (trustManagers.length == 1 && (trustManagers[0] instanceof X509TrustManager)) {
                return (X509TrustManager) trustManagers[0];
            }
            throw new IllegalStateException("Unexpected default trust managers:" + Arrays.toString(trustManagers));
        } catch (GeneralSecurityException e2) {
            throw a("No System TLS", (Exception) e2);
        }
    }
}
