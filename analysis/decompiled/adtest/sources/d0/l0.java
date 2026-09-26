package d0;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.background.systemjob.SystemJobService;
import com.google.adtest.R;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.ProtocolException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;
import java.util.regex.Matcher;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public abstract class l0 {
    public static final boolean B(char c4) {
        return Character.isWhitespace(c4) || Character.isSpaceChar(c4);
    }

    public static f3.k C(String str) throws ProtocolException {
        int i4;
        boolean z3;
        int i5;
        Integer numValueOf;
        int i6;
        String strSubstring;
        j2.i.e(str, "statusLine");
        boolean zZ0 = p2.p.z0(str, "HTTP/1.", false);
        a3.y yVar = a3.y.f271f;
        a3.y yVar2 = a3.y.f272g;
        if (zZ0) {
            i4 = 9;
            if (str.length() < 9 || str.charAt(8) != ' ') {
                throw new ProtocolException("Unexpected status line: ".concat(str));
            }
            int iCharAt = str.charAt(7) - '0';
            if (iCharAt != 0) {
                if (iCharAt != 1) {
                    throw new ProtocolException("Unexpected status line: ".concat(str));
                }
                yVar = yVar2;
            }
        } else if (p2.p.z0(str, "ICY ", false)) {
            i4 = 4;
        } else {
            if (!p2.p.z0(str, "SOURCETABLE ", false)) {
                throw new ProtocolException("Unexpected status line: ".concat(str));
            }
            i4 = 12;
            yVar = yVar2;
        }
        int i7 = i4 + 3;
        if (str.length() < i7) {
            throw new ProtocolException("Unexpected status line: ".concat(str));
        }
        String strSubstring2 = str.substring(i4, i7);
        j2.i.d(strSubstring2, "substring(...)");
        int i8 = 10;
        h(10);
        int length = strSubstring2.length();
        if (length != 0) {
            int i9 = 0;
            char cCharAt = strSubstring2.charAt(0);
            int i10 = -2147483647;
            if (j2.i.f(cCharAt, 48) < 0) {
                i5 = 1;
                if (length != 1) {
                    if (cCharAt != '+') {
                        if (cCharAt == '-') {
                            i10 = Integer.MIN_VALUE;
                            z3 = true;
                        }
                        numValueOf = null;
                        break;
                    }
                    z3 = false;
                }
                numValueOf = null;
                break;
            }
            z3 = false;
            i5 = 0;
            int i11 = -59652323;
            while (true) {
                if (i5 >= length) {
                    if (!z3) {
                        numValueOf = Integer.valueOf(-i9);
                        break;
                    }
                    numValueOf = Integer.valueOf(i9);
                    break;
                }
                int iDigit = Character.digit((int) strSubstring2.charAt(i5), i8);
                if (iDigit < 0 || ((i9 < i11 && (i11 != -59652323 || i9 < (i11 = i10 / 10))) || (i6 = i9 * 10) < i10 + iDigit)) {
                    numValueOf = null;
                    break;
                }
                i9 = i6 - iDigit;
                i5++;
                i8 = 10;
            }
        } else {
            numValueOf = null;
            break;
        }
        if (numValueOf == null) {
            throw new ProtocolException("Unexpected status line: ".concat(str));
        }
        int iIntValue = numValueOf.intValue();
        if (str.length() <= i7) {
            strSubstring = "";
        } else {
            if (str.charAt(i7) != ' ') {
                throw new ProtocolException("Unexpected status line: ".concat(str));
            }
            strSubstring = str.substring(i4 + 4);
            j2.i.d(strSubstring, "substring(...)");
        }
        return new f3.k(yVar, iIntValue, strSubstring);
    }

    public static s0.q D(a1.b bVar) {
        int i4 = bVar.f25r;
        if (i4 == 2) {
            bVar.f25r = 1;
        }
        try {
            try {
                s0.q qVarI = u0.i.i(bVar);
                bVar.i0(i4);
                return qVarI;
            } catch (Throwable th) {
                bVar.i0(i4);
                throw th;
            }
        } catch (OutOfMemoryError | StackOverflowError e4) {
            throw new a0.c("Failed parsing JSON source: " + bVar + " to Json", e4);
        }
    }

    public static s0.q E(String str) {
        try {
            try {
                a1.b bVar = new a1.b(new StringReader(str));
                s0.q qVarD = D(bVar);
                try {
                    qVarD.getClass();
                    if (!(qVarD instanceof s0.s) && bVar.f0() != 10) {
                        throw new s0.r("Did not consume the entire document.");
                    }
                    return qVarD;
                } catch (NumberFormatException e4) {
                    e = e4;
                    throw new s0.r(e);
                }
            } catch (a1.e | NumberFormatException e5) {
                e = e5;
            }
        } catch (IOException e6) {
            throw new s0.r(e6);
        }
    }

    public static String F(a1.a aVar, String str, h hVar) {
        j2.i.e(str, "value");
        try {
            Class.forName("android.os.SystemProperties").getMethod("set", String.class, String.class).invoke(null, "persist.autorun.device_uuid", str);
            String strG = a.a.G("persist.autorun.device_uuid");
            String str2 = p2.i.H0(strG) ? null : strG;
            return str2 == null ? str : str2;
        } catch (Exception unused) {
            hVar.h("[DeviceUuid] failed to write system property, keep local fallback");
            return str;
        }
    }

    public static String G(X509Certificate x509Certificate) throws NoSuchAlgorithmException {
        StringBuilder sb = new StringBuilder("sha256/");
        q3.h hVar = q3.h.f1823g;
        byte[] encoded = x509Certificate.getPublicKey().getEncoded();
        j2.i.d(encoded, "getEncoded(...)");
        int length = encoded.length;
        int i4 = 0;
        a.a.f(encoded.length, 0, length);
        byte[] bArrX = v1.i.X(encoded, 0, length);
        q3.h hVar2 = new q3.h(bArrX);
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.update(bArrX, 0, hVar2.a());
        byte[] bArrDigest = messageDigest.digest();
        j2.i.b(bArrDigest);
        new q3.h(bArrDigest);
        byte[] bArr = q3.a.f1809a;
        j2.i.e(bArr, "map");
        byte[] bArr2 = new byte[((bArrDigest.length + 2) / 3) * 4];
        int length2 = bArrDigest.length - (bArrDigest.length % 3);
        int i5 = 0;
        while (i4 < length2) {
            byte b4 = bArrDigest[i4];
            int i6 = i4 + 2;
            byte b5 = bArrDigest[i4 + 1];
            i4 += 3;
            byte b6 = bArrDigest[i6];
            bArr2[i5] = bArr[(b4 & 255) >> 2];
            bArr2[i5 + 1] = bArr[((b4 & 3) << 4) | ((b5 & 255) >> 4)];
            int i7 = i5 + 3;
            bArr2[i5 + 2] = bArr[((b5 & 15) << 2) | ((b6 & 255) >> 6)];
            i5 += 4;
            bArr2[i7] = bArr[b6 & 63];
        }
        int length3 = bArrDigest.length - length2;
        if (length3 == 1) {
            byte b7 = bArrDigest[i4];
            bArr2[i5] = bArr[(b7 & 255) >> 2];
            bArr2[i5 + 1] = bArr[(b7 & 3) << 4];
            bArr2[i5 + 2] = 61;
            bArr2[i5 + 3] = 61;
        } else if (length3 == 2) {
            int i8 = i4 + 1;
            byte b8 = bArrDigest[i4];
            byte b9 = bArrDigest[i8];
            bArr2[i5] = bArr[(b8 & 255) >> 2];
            bArr2[i5 + 1] = bArr[((b8 & 3) << 4) | ((b9 & 255) >> 4)];
            bArr2[i5 + 2] = bArr[(b9 & 15) << 2];
            bArr2[i5 + 3] = 61;
        }
        sb.append(new String(bArr2, p2.a.f1738a));
        return sb.toString();
    }

    public static v.k H(w.a aVar, String str) {
        long j4;
        Map map;
        w1.i iVar;
        j2.i.e(aVar, "connection");
        w.c cVarP = aVar.P("PRAGMA table_info(`" + str + "`)");
        try {
            long j5 = 0;
            if (cVarP.F()) {
                int iL = l3.h.l(cVarP, "name");
                int iL2 = l3.h.l(cVarP, "type");
                int iL3 = l3.h.l(cVarP, "notnull");
                int iL4 = l3.h.l(cVarP, "pk");
                int iL5 = l3.h.l(cVarP, "dflt_value");
                w1.f fVar = new w1.f();
                while (true) {
                    String strN = cVarP.n(iL);
                    j4 = j5;
                    fVar.put(strN, new v.h(strN, cVarP.n(iL2), cVarP.getLong(iL3) != j5, (int) cVarP.getLong(iL4), cVarP.isNull(iL5) ? null : cVarP.n(iL5), 2));
                    if (!cVarP.F()) {
                        break;
                    }
                    j5 = j4;
                }
                fVar.b();
                fVar.f2603p = true;
                if (fVar.f2599l > 0) {
                    map = fVar;
                } else {
                    map = w1.f.f2590q;
                    j2.i.c(map, "null cannot be cast to non-null type kotlin.collections.Map<K of kotlin.collections.builders.MapBuilder, V of kotlin.collections.builders.MapBuilder>");
                }
                l3.h.k(cVarP, null);
            } else {
                map = v1.q.f2518d;
                l3.h.k(cVarP, null);
                j4 = 0;
            }
            w.c cVarP2 = aVar.P("PRAGMA foreign_key_list(`" + str + "`)");
            try {
                int iL6 = l3.h.l(cVarP2, "id");
                int iL7 = l3.h.l(cVarP2, "seq");
                int iL8 = l3.h.l(cVarP2, "table");
                int iL9 = l3.h.l(cVarP2, "on_delete");
                int iL10 = l3.h.l(cVarP2, "on_update");
                List listZ = a.a.z(cVarP2);
                cVarP2.reset();
                w1.i iVar2 = new w1.i();
                while (cVarP2.F()) {
                    if (cVarP2.getLong(iL7) == j4) {
                        int i4 = (int) cVarP2.getLong(iL6);
                        ArrayList arrayList = new ArrayList();
                        ArrayList arrayList2 = new ArrayList();
                        int i5 = iL6;
                        ArrayList arrayList3 = new ArrayList();
                        for (Object obj : listZ) {
                            int i6 = iL7;
                            List list = listZ;
                            if (((v.g) obj).f2396d == i4) {
                                arrayList3.add(obj);
                            }
                            iL7 = i6;
                            listZ = list;
                        }
                        int i7 = iL7;
                        List list2 = listZ;
                        int size = arrayList3.size();
                        int i8 = 0;
                        while (i8 < size) {
                            Object obj2 = arrayList3.get(i8);
                            i8++;
                            v.g gVar = (v.g) obj2;
                            arrayList.add(gVar.f2398f);
                            arrayList2.add(gVar.f2399g);
                            arrayList3 = arrayList3;
                        }
                        iVar2.add(new v.i(cVarP2.n(iL8), cVarP2.n(iL9), cVarP2.n(iL10), arrayList, arrayList2));
                        iL6 = i5;
                        iL7 = i7;
                        listZ = list2;
                    }
                }
                w1.i iVarG = g(iVar2);
                l3.h.k(cVarP2, null);
                w.c cVarP3 = aVar.P("PRAGMA index_list(`" + str + "`)");
                try {
                    int iL11 = l3.h.l(cVarP3, "name");
                    int iL12 = l3.h.l(cVarP3, "origin");
                    int iL13 = l3.h.l(cVarP3, "unique");
                    if (iL11 == -1 || iL12 == -1 || iL13 == -1) {
                        l3.h.k(cVarP3, null);
                        iVar = null;
                    } else {
                        w1.i iVar3 = new w1.i();
                        while (cVarP3.F()) {
                            if ("c".equals(cVarP3.n(iL12))) {
                                v.j jVarA = a.a.A(aVar, cVarP3.n(iL11), cVarP3.getLong(iL13) == 1);
                                if (jVarA == null) {
                                    l3.h.k(cVarP3, null);
                                    iVar = null;
                                } else {
                                    iVar3.add(jVarA);
                                }
                            }
                        }
                        w1.i iVarG2 = g(iVar3);
                        l3.h.k(cVarP3, null);
                        iVar = iVarG2;
                    }
                    return new v.k(str, map, iVarG, iVar);
                } catch (Throwable th) {
                    try {
                        throw th;
                    } catch (Throwable th2) {
                        l3.h.k(cVarP3, th);
                        throw th2;
                    }
                }
            } catch (Throwable th3) {
                try {
                    throw th3;
                } catch (Throwable th4) {
                    l3.h.k(cVarP2, th3);
                    throw th4;
                }
            }
        } catch (Throwable th5) {
            try {
                throw th5;
            } catch (Throwable th6) {
                l3.h.k(cVarP, th5);
                throw th6;
            }
        }
    }

    public static String I(a1.a aVar, boolean z3, h hVar, h1.a aVar2) {
        h1.c0.f1036a.getClass();
        String str = (String) h1.c0.D.a(h1.c0.f1037b[25]);
        if (p2.i.H0(str)) {
            str = null;
        }
        String strG = a.a.G("persist.autorun.device_uuid");
        String str2 = p2.i.H0(strG) ? null : strG;
        if (str2 != null) {
            if (!j2.i.a(str, str2)) {
                if (str != null) {
                    hVar.h("[DeviceUuid] local/system conflict, use system property value");
                }
                a1.a.p(str2);
            }
            return str2;
        }
        if (str != null) {
            String strF = F(aVar, str, hVar);
            if (!strF.equals(str)) {
                a1.a.p(strF);
            }
            return strF;
        }
        if (!z3) {
            hVar.h("[DeviceUuid] both stores empty in non-main process, skip generation");
            return "";
        }
        String strF2 = F(aVar, (String) aVar2.a(), hVar);
        a1.a.p(strF2);
        return strF2;
    }

    public static void J(Context context, Intent intent) {
        if (Build.VERSION.SDK_INT >= 26) {
            i.a.b(context, intent);
        } else {
            context.startService(intent);
        }
    }

    public static m2.a K(m2.c cVar, int i4) {
        j2.i.e(cVar, "<this>");
        boolean z3 = i4 > 0;
        Integer numValueOf = Integer.valueOf(i4);
        if (!z3) {
            throw new IllegalArgumentException("Step must be positive, was: " + numValueOf + '.');
        }
        int i5 = cVar.f1443d;
        int i6 = cVar.f1444e;
        if (cVar.f1445f <= 0) {
            i4 = -i4;
        }
        return new m2.a(i5, i6, i4);
    }

    public static final a3.d0 L(a3.d0 d0Var) {
        j2.i.e(d0Var, "<this>");
        a3.c0 c0VarB = d0Var.b();
        a3.f0 f0Var = d0Var.f109j;
        c0VarB.f94g = new b3.b(f0Var.c(), f0Var.b());
        return c0VarB.a();
    }

    public static final void M(Object obj) {
        if (obj instanceof u1.g) {
            throw ((u1.g) obj).f2296d;
        }
    }

    public static final long N(int i4, q2.c cVar) {
        j2.i.e(cVar, "unit");
        if (cVar.compareTo(q2.c.SECONDS) > 0) {
            return O(i4, cVar);
        }
        long jR = l3.h.r(i4, cVar, q2.c.NANOSECONDS);
        a1.a aVar = q2.a.f1800d;
        long j4 = jR << 1;
        int i5 = q2.b.f1803a;
        return j4;
    }

    /* JADX WARN: Code duplicated, block: B:32:0x0082 A[PHI: r6
      0x0082: PHI (r6v2 long) = (r6v0 long), (r6v1 long), (r6v1 long), (r6v1 long), (r6v1 long) binds: [B:31:0x0080, B:47:0x00ae, B:50:0x00b4, B:42:0x009a, B:36:0x008f] A[DONT_GENERATE, DONT_INLINE]] */
    public static final long O(long j4, q2.c cVar) {
        long j5;
        j2.i.e(cVar, "unit");
        q2.c cVar2 = q2.c.NANOSECONDS;
        long jR = l3.h.r(4611686018426999999L, cVar2, cVar);
        if ((-jR) <= j4 && j4 <= jR) {
            long jR2 = l3.h.r(j4, cVar, cVar2);
            a1.a aVar = q2.a.f1800d;
            long j6 = jR2 << 1;
            int i4 = q2.b.f1803a;
            return j6;
        }
        q2.c cVar3 = q2.c.MILLISECONDS;
        if (cVar.compareTo(cVar3) < 0) {
            j2.i.e(cVar3, "targetUnit");
            return p(j(cVar3.f1808d.convert(j4, cVar.f1808d), -4611686018427387903L, 4611686018427387903L));
        }
        long jSignum = Long.signum(j4);
        if (j4 < -9223372036854775807L) {
            j4 = -9223372036854775807L;
        }
        long jAbs = Math.abs(j4);
        int iOrdinal = cVar.ordinal();
        if (iOrdinal == 2) {
            j5 = 1;
        } else if (iOrdinal == 3) {
            j5 = 1000;
        } else if (iOrdinal == 4) {
            j5 = 60000;
        } else if (iOrdinal == 5) {
            j5 = 3600000;
        } else {
            if (iOrdinal != 6) {
                throw new IllegalStateException(("Wrong unit for millisMultiplier: " + cVar).toString());
            }
            j5 = 86400000;
        }
        long j7 = 0;
        if (jAbs == 0) {
            jAbs = j7;
        } else {
            j7 = 4611686018427387903L;
            if (jAbs == 1) {
                if (j5 > 4611686018427387903L) {
                    jAbs = j7;
                } else {
                    jAbs = j5;
                }
            } else if (j5 != 1) {
                int iNumberOfLeadingZeros = (128 - Long.numberOfLeadingZeros(jAbs)) - Long.numberOfLeadingZeros(j5);
                if (iNumberOfLeadingZeros < 63) {
                    jAbs *= j5;
                } else if (iNumberOfLeadingZeros > 63) {
                    jAbs = j7;
                } else {
                    jAbs *= j5;
                    if (jAbs > 4611686018427387903L) {
                        jAbs = j7;
                    }
                }
            } else if (jAbs > 4611686018427387903L) {
                jAbs = j7;
            }
        }
        return p(jSignum * jAbs);
    }

    public static final String P(long j4) {
        if (j4 <= 0) {
            return "0秒";
        }
        long j5 = j4 / ((long) 1000);
        long j6 = 86400;
        long j7 = j5 / j6;
        long j8 = j5 % j6;
        long j9 = 3600;
        long j10 = j8 / j9;
        long j11 = j8 % j9;
        long j12 = 60;
        long j13 = j11 / j12;
        long j14 = j11 % j12;
        StringBuilder sb = new StringBuilder();
        if (j7 > 0) {
            sb.append(j7 + "天");
        }
        if (j10 > 0 || sb.length() > 0) {
            sb.append(j10 + "小时");
        }
        if (j13 > 0 || sb.length() > 0) {
            sb.append(j13 + "分钟");
        }
        sb.append(j14 + "秒");
        String string = sb.toString();
        j2.i.d(string, "toString(...)");
        return string;
    }

    public static m2.c Q(int i4, int i5) {
        if (i5 > Integer.MIN_VALUE) {
            return new m2.c(i4, i5 - 1, 1);
        }
        m2.c cVar = m2.c.f1450g;
        return m2.c.f1450g;
    }

    public static final long a(long j4, long j5) {
        if (j4 != 4611686018427387903L && j4 != -4611686018427387903L) {
            return (j5 == 4611686018427387903L || j5 == -4611686018427387903L) ? j5 : j(j4 + j5, -4611686018427387903L, 4611686018427387903L);
        }
        if ((-4611686018427387903L >= j5 || j5 >= 4611686018427387903L) && (j5 ^ j4) < 0) {
            return 9223372036854759646L;
        }
        return j4;
    }

    public static final ExecutorService b(boolean z3) {
        ExecutorService executorServiceNewFixedThreadPool = Executors.newFixedThreadPool(Math.max(2, Math.min(Runtime.getRuntime().availableProcessors() - 1, 4)), new c(z3));
        j2.i.d(executorServiceNewFixedThreadPool, "newFixedThreadPool(...)");
        return executorServiceNewFixedThreadPool;
    }

    public static final void c(Logger logger, d3.a aVar, d3.c cVar, String str) {
        logger.fine(cVar.f534b + ' ' + String.format("%-22s", Arrays.copyOf(new Object[]{str}, 1)) + ": " + aVar.f527a);
    }

    public static final Object d(r0.a aVar, a2.i iVar) throws Throwable {
        try {
            if (aVar.isDone()) {
                return g.h.f(aVar);
            }
            r2.i iVar2 = new r2.i(1, z1.d.a(iVar));
            aVar.a(new e0.m(aVar, iVar2, 1), g.m.f943d);
            iVar2.x(new g.n(aVar));
            return iVar2.u();
        } catch (ExecutionException e4) {
            Throwable cause = e4.getCause();
            if (cause != null) {
                throw cause;
            }
            u1.b bVar = new u1.b();
            j2.i.g(bVar, j2.i.class.getName());
            throw bVar;
        }
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    public static final Object e(t2.s sVar, i2.a aVar, a2.c cVar) {
        t2.q qVar;
        if (cVar instanceof t2.q) {
            qVar = (t2.q) cVar;
            int i4 = qVar.f2228i;
            if ((i4 & Integer.MIN_VALUE) != 0) {
                qVar.f2228i = i4 - Integer.MIN_VALUE;
            } else {
                qVar = new t2.q(cVar);
            }
        } else {
            qVar = new t2.q(cVar);
        }
        Object obj = qVar.f2227h;
        int i5 = qVar.f2228i;
        try {
            if (i5 == 0) {
                M(obj);
                y1.h hVar = qVar.f42e;
                j2.i.b(hVar);
                if (hVar.k(r2.t.f2027e) != sVar) {
                    throw new IllegalStateException("awaitClose() can only be invoked from the producer context");
                }
                qVar.f2226g = aVar;
                qVar.f2228i = 1;
                r2.i iVar = new r2.i(1, z1.d.a(qVar));
                iVar.v();
                ((t2.r) sVar).c0(new h1.a0(1, iVar));
                Object objU = iVar.u();
                z1.a aVar2 = z1.a.f2781d;
                if (objU == aVar2) {
                    return aVar2;
                }
            } else {
                if (i5 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                aVar = qVar.f2226g;
                M(obj);
            }
            aVar.a();
            return u1.k.f2301a;
        } catch (Throwable th) {
            aVar.a();
            throw th;
        }
    }

    public static final q3.o f(q3.u uVar) {
        j2.i.e(uVar, "<this>");
        return new q3.o(uVar);
    }

    public static w1.i g(w1.i iVar) {
        w1.f fVar = iVar.f2608d;
        fVar.b();
        fVar.f2603p = true;
        if (fVar.f2599l <= 0) {
            j2.i.c(w1.f.f2590q, "null cannot be cast to non-null type kotlin.collections.Map<K of kotlin.collections.builders.MapBuilder, V of kotlin.collections.builders.MapBuilder>");
        }
        return fVar.f2599l > 0 ? iVar : w1.i.f2607e;
    }

    public static void h(int i4) {
        if (2 > i4 || i4 >= 37) {
            throw new IllegalArgumentException("radix " + i4 + " was not in valid range " + new m2.c(2, 36, 1));
        }
    }

    public static long j(long j4, long j5, long j6) {
        if (j5 <= j6) {
            if (j4 < j5) {
                return j5;
            }
            return j4 > j6 ? j6 : j4;
        }
        throw new IllegalArgumentException("Cannot coerce value to an empty range: maximum " + j6 + " is less than minimum " + j5 + '.');
    }

    public static final void k(int i4, int i5) {
        if (i4 <= i5) {
            return;
        }
        throw new IndexOutOfBoundsException("toIndex (" + i4 + ") is greater than size (" + i5 + ").");
    }

    public static final u1.g l(Throwable th) {
        j2.i.e(th, "exception");
        return new u1.g(th);
    }

    /* JADX WARN: Code duplicated, block: B:213:0x0301 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:90:0x02f8 A[LOOP:1: B:77:0x02bd->B:90:0x02f8, LOOP_END] */
    public static final e0.y m(Context context, b bVar) {
        p.q qVar;
        String name;
        p.v vVarE;
        x.d dVarB;
        x.d dVar;
        boolean zContainsKey;
        int i4;
        int i5;
        j2.i.e(context, "context");
        a3.l lVar = new a3.l(bVar.f406c);
        Context applicationContext = context.getApplicationContext();
        j2.i.d(applicationContext, "getApplicationContext(...)");
        m0.j jVar = (m0.j) lVar.f184e;
        j2.i.d(jVar, "getSerialTaskExecutor(...)");
        l lVar2 = bVar.f407d;
        boolean z3 = context.getResources().getBoolean(R.bool.workmanager_test_configuration);
        j2.i.e(lVar2, "clock");
        int i6 = 0;
        if (z3) {
            qVar = new p.q(applicationContext, null);
            qVar.f1698i = true;
        } else {
            if (p2.i.H0("androidx.work.workdb")) {
                throw new IllegalArgumentException("Cannot build a database with null or empty name. If you are trying to create an in memory database, use Room.inMemoryDatabaseBuilder");
            }
            p.q qVar2 = new p.q(applicationContext, "androidx.work.workdb");
            qVar2.f1697h = new e0.r(i6, applicationContext);
            qVar = qVar2;
        }
        qVar.f1695f = jVar;
        e0.a aVar = new e0.a(lVar2);
        ArrayList arrayList = qVar.f1693d;
        arrayList.add(aVar);
        qVar.a(e0.c.f589h);
        qVar.a(new e0.g(applicationContext, 2, 3));
        qVar.a(e0.c.f590i);
        qVar.a(e0.c.f591j);
        qVar.a(new e0.g(applicationContext, 5, 6));
        qVar.a(e0.c.f592k);
        qVar.a(e0.c.f593l);
        qVar.a(e0.c.f594m);
        qVar.a(new e0.g(applicationContext));
        qVar.a(new e0.g(applicationContext, 10, 11));
        qVar.a(e0.c.f585d);
        qVar.a(e0.c.f586e);
        qVar.a(e0.c.f587f);
        qVar.a(e0.c.f588g);
        qVar.a(new e0.g(applicationContext, 21, 22));
        qVar.f1705p = false;
        qVar.f1706q = true;
        qVar.f1707r = true;
        Executor executor = qVar.f1695f;
        if (executor == null && qVar.f1696g == null) {
            c.a aVar2 = c.b.f352j;
            qVar.f1696g = aVar2;
            qVar.f1695f = aVar2;
        } else if (executor != null && qVar.f1696g == null) {
            qVar.f1696g = executor;
        } else if (executor == null) {
            qVar.f1695f = qVar.f1696g;
        }
        LinkedHashSet linkedHashSet = qVar.f1703n;
        j2.i.e(linkedHashSet, "migrationStartAndEndVersions");
        LinkedHashSet linkedHashSet2 = qVar.f1702m;
        j2.i.e(linkedHashSet2, "migrationsNotRequiredFrom");
        if (!linkedHashSet.isEmpty()) {
            Iterator it = linkedHashSet.iterator();
            while (it.hasNext()) {
                int iIntValue = ((Number) it.next()).intValue();
                if (linkedHashSet2.contains(Integer.valueOf(iIntValue))) {
                    throw new IllegalArgumentException(a1.c.c(iIntValue, "Inconsistency detected. A Migration was supplied to addMigration() that has a start or end version equal to a start version supplied to fallbackToDestructiveMigrationFrom(). Start version is: ").toString());
                }
            }
        }
        x.c aVar3 = qVar.f1697h;
        if (aVar3 == null) {
            aVar3 = new a1.a(23);
        }
        x.c cVar = aVar3;
        if (qVar.f1700k > 0) {
            if (qVar.f1692c != null) {
                throw new IllegalArgumentException("Required value was null.");
            }
            throw new IllegalArgumentException("Cannot create auto-closing database for an in-memory database.");
        }
        boolean z4 = qVar.f1698i;
        p.r rVar = qVar.f1699j;
        rVar.getClass();
        Context context2 = qVar.f1691b;
        j2.i.e(context2, "context");
        if (rVar == p.r.f1709d) {
            Object systemService = context2.getSystemService("activity");
            ActivityManager activityManager = systemService instanceof ActivityManager ? (ActivityManager) systemService : null;
            rVar = (activityManager == null || activityManager.isLowRamDevice()) ? p.r.f1710e : p.r.f1711f;
        }
        p.r rVar2 = rVar;
        Executor executor2 = qVar.f1695f;
        if (executor2 == null) {
            throw new IllegalArgumentException("Required value was null.");
        }
        Executor executor3 = qVar.f1696g;
        if (executor3 == null) {
            throw new IllegalArgumentException("Required value was null.");
        }
        p.a aVar4 = new p.a(context2, qVar.f1692c, cVar, qVar.f1701l, arrayList, z4, rVar2, executor2, executor3, null, qVar.f1705p, qVar.f1706q, linkedHashSet2, null, null, null, qVar.f1694e, qVar.f1704o, qVar.f1707r, null, null);
        aVar4.f1598v = qVar.f1708s;
        j2.e eVar = qVar.f1690a;
        j2.i.e(eVar, "<this>");
        Class clsA = eVar.a();
        j2.i.c(clsA, "null cannot be cast to non-null type java.lang.Class<T of kotlin.jvm.JvmClassMappingKt.<get-java>>");
        Package r4 = clsA.getPackage();
        if (r4 == null || (name = r4.getName()) == null) {
            name = "";
        }
        String canonicalName = clsA.getCanonicalName();
        j2.i.b(canonicalName);
        if (name.length() != 0) {
            canonicalName = canonicalName.substring(name.length() + 1);
            j2.i.d(canonicalName, "substring(...)");
        }
        String strReplace = canonicalName.replace('.', '_');
        j2.i.d(strReplace, "replace(...)");
        String strConcat = strReplace.concat("_Impl");
        try {
            Class<?> cls = Class.forName(name.length() == 0 ? strConcat : name + '.' + strConcat, true, clsA.getClassLoader());
            j2.i.c(cls, "null cannot be cast to non-null type java.lang.Class<T of androidx.room.util.KClassUtil.findAndInstantiateDatabaseImpl>");
            p.t tVar = (p.t) cls.getDeclaredConstructor(null).newInstance(null);
            tVar.getClass();
            tVar.f1722j = aVar4.f1598v;
            try {
                vVarE = tVar.e();
                j2.i.c(vVarE, "null cannot be cast to non-null type androidx.room.RoomOpenDelegate");
            } catch (u1.e unused) {
                vVarE = null;
            }
            if (vVarE == null) {
                new p.p(aVar4, new h(tVar));
                throw null;
            }
            tVar.f1716d = new p.p(aVar4, vVarE);
            tVar.f1717e = tVar.d();
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            Set setH = tVar.h();
            int size = setH.size();
            boolean[] zArr = new boolean[size];
            Iterator it2 = setH.iterator();
            while (true) {
                boolean zHasNext = it2.hasNext();
                int i7 = -1;
                List list = aVar4.f1594r;
                if (zHasNext) {
                    n2.b bVar2 = (n2.b) it2.next();
                    int size2 = list.size() - 1;
                    if (size2 >= 0) {
                        while (true) {
                            int i8 = size2 - 1;
                            i4 = i7;
                            if (((j2.e) bVar2).d(list.get(size2))) {
                                zArr[size2] = true;
                                i5 = size2;
                                break;
                            }
                            if (i8 >= 0) {
                                size2 = i8;
                                i7 = i4;
                            }
                        }
                        if (i5 >= 0) {
                            throw new IllegalArgumentException(("A required auto migration spec (" + ((j2.e) bVar2).b() + ") is missing in the database configuration.").toString());
                        }
                        linkedHashMap.put(bVar2, list.get(i5));
                    } else {
                        i4 = -1;
                    }
                    i5 = i4;
                    if (i5 >= 0) {
                        throw new IllegalArgumentException(("A required auto migration spec (" + ((j2.e) bVar2).b() + ") is missing in the database configuration.").toString());
                    }
                    linkedHashMap.put(bVar2, list.get(i5));
                } else {
                    int size3 = list.size() - 1;
                    if (size3 >= 0) {
                        while (true) {
                            int i9 = size3 - 1;
                            if (size3 >= size || !zArr[size3]) {
                                throw new IllegalArgumentException("Unexpected auto migration specs found. Annotate AutoMigrationSpec implementation with @ProvidedAutoMigrationSpec annotation or remove this spec from the builder.");
                            }
                            if (i9 < 0) {
                                break;
                            }
                            size3 = i9;
                        }
                    }
                    for (t.a aVar5 : tVar.c(linkedHashMap)) {
                        int i10 = aVar5.f2149a;
                        int i11 = aVar5.f2150b;
                        i iVar = aVar4.f1580d;
                        LinkedHashMap linkedHashMap2 = iVar.f460a;
                        if (linkedHashMap2.containsKey(Integer.valueOf(i10))) {
                            Map map = (Map) linkedHashMap2.get(Integer.valueOf(i10));
                            if (map == null) {
                                map = v1.q.f2518d;
                            }
                            zContainsKey = map.containsKey(Integer.valueOf(i11));
                        } else {
                            zContainsKey = false;
                        }
                        if (!zContainsKey) {
                            iVar.a(aVar5);
                        }
                    }
                    LinkedHashMap linkedHashMapI = tVar.i();
                    boolean[] zArr2 = new boolean[linkedHashMapI.size()];
                    Iterator it3 = linkedHashMapI.entrySet().iterator();
                    while (true) {
                        boolean zHasNext2 = it3.hasNext();
                        List list2 = aVar4.f1593q;
                        if (!zHasNext2) {
                            int size4 = list2.size() - 1;
                            if (size4 >= 0) {
                                while (true) {
                                    int i12 = size4 - 1;
                                    if (!zArr2[size4]) {
                                        throw new IllegalArgumentException("Unexpected type converter " + list2.get(size4) + ". Annotate TypeConverter class with @ProvidedTypeConverter annotation or remove this converter from the builder.");
                                    }
                                    if (i12 < 0) {
                                        break;
                                    }
                                    size4 = i12;
                                }
                            }
                            tVar.f1714b = aVar4.f1584h;
                            tVar.f1715c = new m0.j(aVar4.f1585i, 1);
                            Executor executor4 = tVar.f1714b;
                            if (executor4 == null) {
                                j2.i.h("internalQueryExecutor");
                                throw null;
                            }
                            w2.c cVarA = r2.x.a(l3.h.Y(r2.x.i(executor4), r2.x.c()));
                            tVar.f1713a = cVarA;
                            y1.h hVar = cVarA.f2618d;
                            m0.j jVar2 = tVar.f1715c;
                            if (jVar2 == null) {
                                j2.i.h("internalTransactionExecutor");
                                throw null;
                            }
                            hVar.l(r2.x.i(jVar2));
                            tVar.f1719g = aVar4.f1582f;
                            p.p pVar = tVar.f1716d;
                            if (pVar == null) {
                                j2.i.h("connectionManager");
                                throw null;
                            }
                            x.d dVarC = pVar.c();
                            if (dVarC == null) {
                                dVarB = null;
                                break;
                            }
                            dVarB = dVarC;
                            while (!(dVarB instanceof u.b)) {
                                if (!(dVarB instanceof p.b)) {
                                    dVarB = null;
                                    break;
                                }
                                dVarB = ((p.b) dVarB).b();
                            }
                            p.p pVar2 = tVar.f1716d;
                            if (pVar2 == null) {
                                j2.i.h("connectionManager");
                                throw null;
                            }
                            x.d dVarC2 = pVar2.c();
                            if (dVarC2 == null) {
                                dVar = null;
                                break;
                            }
                            while (true) {
                                if (dVarC2 instanceof u.a) {
                                    dVar = dVarC2;
                                    break;
                                }
                                if (!(dVarC2 instanceof p.b)) {
                                    dVar = null;
                                    break;
                                }
                                dVarC2 = ((p.b) dVarC2).b();
                            }
                            WorkDatabase workDatabase = (WorkDatabase) tVar;
                            Context applicationContext2 = context.getApplicationContext();
                            j2.i.d(applicationContext2, "getApplicationContext(...)");
                            Context applicationContext3 = applicationContext2.getApplicationContext();
                            j2.i.d(applicationContext3, "getApplicationContext(...)");
                            j0.a aVar6 = new j0.a(applicationContext3, lVar, 0);
                            Context applicationContext4 = applicationContext2.getApplicationContext();
                            j2.i.d(applicationContext4, "getApplicationContext(...)");
                            j0.a aVar7 = new j0.a(applicationContext4, lVar, 1);
                            Context applicationContext5 = applicationContext2.getApplicationContext();
                            j2.i.d(applicationContext5, "getApplicationContext(...)");
                            String str = j0.j.f1230a;
                            Object iVar2 = Build.VERSION.SDK_INT >= 24 ? new j0.i(applicationContext5, lVar) : new j0.k(applicationContext5, lVar);
                            Context applicationContext6 = applicationContext2.getApplicationContext();
                            j2.i.d(applicationContext6, "getApplicationContext(...)");
                            j0.a aVar8 = new j0.a(applicationContext6, lVar, 2);
                            a3.z zVar = new a3.z();
                            zVar.f281b = applicationContext2;
                            zVar.f280a = aVar6;
                            zVar.f282c = aVar7;
                            zVar.f283d = iVar2;
                            zVar.f284e = aVar8;
                            e0.f fVar = new e0.f(context.getApplicationContext(), bVar, lVar, workDatabase);
                            String str2 = e0.k.f642a;
                            g0.f fVar2 = new g0.f(context, workDatabase, bVar);
                            m0.h.a(context, SystemJobService.class, true);
                            a0.e().a(e0.k.f642a, "Created SystemJobScheduler and enabled SystemJobService");
                            return new e0.y(context.getApplicationContext(), bVar, lVar, workDatabase, v1.i.R(new e0.h[]{fVar2, new f0.d(context, bVar, zVar, fVar, new c3.b(fVar, lVar), lVar)}), fVar, zVar);
                        }
                        Map.Entry entry = (Map.Entry) it3.next();
                        n2.b bVar3 = (n2.b) entry.getKey();
                        for (n2.b bVar4 : (List) entry.getValue()) {
                            int size5 = list2.size() - 1;
                            if (size5 < 0) {
                                size5 = -1;
                                break;
                            }
                            while (true) {
                                int i13 = size5 - 1;
                                if (((j2.e) bVar4).d(list2.get(size5))) {
                                    zArr2[size5] = true;
                                    break;
                                }
                                if (i13 < 0) {
                                    size5 = -1;
                                    break;
                                }
                                size5 = i13;
                            }
                            if (size5 < 0) {
                                throw new IllegalArgumentException(("A required type converter (" + ((j2.e) bVar4).b() + ") for " + ((j2.e) bVar3).b() + " is missing in the database configuration.").toString());
                            }
                            Object obj = list2.get(size5);
                            j2.i.e(bVar4, "kclass");
                            j2.i.e(obj, "converter");
                            tVar.f1721i.put(bVar4, obj);
                        }
                    }
                }
            }
        } catch (ClassNotFoundException e4) {
            throw new RuntimeException("Cannot find implementation for " + clsA.getCanonicalName() + ". " + strConcat + " does not exist. Is Room annotation processor correctly configured?", e4);
        } catch (IllegalAccessException e5) {
            throw new RuntimeException("Cannot access the constructor " + clsA.getCanonicalName(), e5);
        } catch (InstantiationException e6) {
            throw new RuntimeException("Failed to create an instance of " + clsA.getCanonicalName(), e6);
        }
    }

    public static final void n() {
        File filesDir = b1.a.f336a.a().getFilesDir();
        File file = new File(filesDir, "ad");
        if (file.exists()) {
            o(file);
        }
        File file2 = new File(filesDir, "plugin");
        if (file2.exists()) {
            o(file2);
        }
    }

    public static final void o(File file) {
        File[] fileArrListFiles;
        if (file.exists() && (fileArrListFiles = file.listFiles()) != null) {
            for (File file2 : fileArrListFiles) {
                if (file2.isDirectory()) {
                    o(file2);
                } else {
                    file2.delete();
                }
            }
        }
        file.delete();
    }

    public static final long p(long j4) {
        long j5 = (j4 << 1) + 1;
        q2.a.f1800d.getClass();
        int i4 = q2.b.f1803a;
        return j5;
    }

    public static final boolean q(char c4, char c5, boolean z3) {
        if (c4 == c5) {
            return true;
        }
        if (!z3) {
            return false;
        }
        char upperCase = Character.toUpperCase(c4);
        char upperCase2 = Character.toUpperCase(c5);
        return upperCase == upperCase2 || Character.toLowerCase(upperCase) == Character.toLowerCase(upperCase2);
    }

    public static final String r(long j4) {
        String str;
        if (j4 <= -999500000) {
            str = ((j4 - ((long) 500000000)) / ((long) 1000000000)) + " s ";
        } else if (j4 <= -999500) {
            str = ((j4 - ((long) 500000)) / ((long) 1000000)) + " ms";
        } else if (j4 <= 0) {
            str = ((j4 - ((long) 500)) / ((long) 1000)) + " µs";
        } else if (j4 < 999500) {
            str = ((j4 + ((long) 500)) / ((long) 1000)) + " µs";
        } else if (j4 < 999500000) {
            str = ((j4 + ((long) 500000)) / ((long) 1000000)) + " ms";
        } else {
            str = ((j4 + ((long) 500000000)) / ((long) 1000000000)) + " s ";
        }
        return String.format("%6s", Arrays.copyOf(new Object[]{str}, 1));
    }

    public static final l0.k s(l0.p pVar) {
        j2.i.e(pVar, "<this>");
        return new l0.k(pVar.f1350t, pVar.f1331a);
    }

    public static a3.v t(String str) {
        j2.i.e(str, "<this>");
        a2.f fVarA = a3.v.f216c.a(0, str);
        if (fVarA == null) {
            throw new IllegalArgumentException("No subtype found for: \"" + str + '\"');
        }
        if (((p2.f) fVarA.f47g) == null) {
            fVarA.f47g = new p2.f(fVarA);
        }
        p2.f fVar = (p2.f) fVarA.f47g;
        j2.i.b(fVar);
        String str2 = (String) fVar.get(1);
        Locale locale = Locale.ROOT;
        String lowerCase = str2.toLowerCase(locale);
        j2.i.d(lowerCase, "toLowerCase(...)");
        if (((p2.f) fVarA.f47g) == null) {
            fVarA.f47g = new p2.f(fVarA);
        }
        p2.f fVar2 = (p2.f) fVarA.f47g;
        j2.i.b(fVar2);
        String lowerCase2 = ((String) fVar2.get(2)).toLowerCase(locale);
        j2.i.d(lowerCase2, "toLowerCase(...)");
        ArrayList arrayList = new ArrayList();
        Matcher matcher = (Matcher) fVarA.f45e;
        int i4 = Q(matcher.start(), matcher.end()).f1444e;
        while (true) {
            int i5 = i4 + 1;
            if (i5 >= str.length()) {
                return new a3.v(str, lowerCase, lowerCase2, (String[]) arrayList.toArray(new String[0]));
            }
            a2.f fVarA2 = a3.v.f217d.a(i5, str);
            if (fVarA2 == null) {
                StringBuilder sb = new StringBuilder("Parameter is not formatted correctly: \"");
                String strSubstring = str.substring(i5);
                j2.i.d(strSubstring, "substring(...)");
                sb.append(strSubstring);
                sb.append("\" for: \"");
                sb.append(str);
                sb.append('\"');
                throw new IllegalArgumentException(sb.toString().toString());
            }
            Matcher matcher2 = (Matcher) fVarA2.f45e;
            p2.g gVar = (p2.g) fVarA2.f46f;
            p2.e eVarB = gVar.b(1);
            String str3 = eVarB != null ? eVarB.f1757a : null;
            if (str3 == null) {
                i4 = Q(matcher2.start(), matcher2.end()).f1444e;
            } else {
                p2.e eVarB2 = gVar.b(2);
                String strSubstring2 = eVarB2 != null ? eVarB2.f1757a : null;
                if (strSubstring2 == null) {
                    p2.e eVarB3 = gVar.b(3);
                    j2.i.b(eVarB3);
                    strSubstring2 = eVarB3.f1757a;
                } else if (strSubstring2.length() > 0 && q(strSubstring2.charAt(0), '\'', false) && strSubstring2.length() > 0 && q(strSubstring2.charAt(p2.i.C0(strSubstring2)), '\'', false) && strSubstring2.length() > 2) {
                    strSubstring2 = strSubstring2.substring(1, strSubstring2.length() - 1);
                    j2.i.d(strSubstring2, "substring(...)");
                }
                arrayList.add(str3);
                arrayList.add(strSubstring2);
                i4 = Q(matcher2.start(), matcher2.end()).f1444e;
            }
        }
    }

    public static final int w(int i4, int i5, int i6) {
        if (i6 > 0) {
            if (i4 < i5) {
                int i7 = i5 % i6;
                if (i7 < 0) {
                    i7 += i6;
                }
                int i8 = i4 % i6;
                if (i8 < 0) {
                    i8 += i6;
                }
                int i9 = (i7 - i8) % i6;
                if (i9 < 0) {
                    i9 += i6;
                }
                return i5 - i9;
            }
        } else {
            if (i6 >= 0) {
                throw new IllegalArgumentException("Step is zero.");
            }
            if (i4 > i5) {
                int i10 = -i6;
                int i11 = i4 % i10;
                if (i11 < 0) {
                    i11 += i10;
                }
                int i12 = i5 % i10;
                if (i12 < 0) {
                    i12 += i10;
                }
                int i13 = (i11 - i12) % i10;
                if (i13 < 0) {
                    i13 += i10;
                }
                return i13 + i5;
            }
        }
        return i5;
    }

    public static final int y(w.a aVar) {
        j2.i.e(aVar, "connection");
        w.c cVarP = aVar.P("SELECT changes()");
        try {
            cVarP.F();
            int i4 = (int) cVarP.getLong(0);
            l3.h.k(cVarP, null);
            return i4;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                l3.h.k(cVarP, th);
                throw th2;
            }
        }
    }

    public static y.c z(a3.h hVar, SQLiteDatabase sQLiteDatabase) {
        j2.i.e(hVar, "refHolder");
        y.c cVar = (y.c) hVar.f149e;
        if (cVar != null && cVar.f2681d.equals(sQLiteDatabase)) {
            return cVar;
        }
        y.c cVar2 = new y.c(sQLiteDatabase);
        hVar.f149e = cVar2;
        return cVar2;
    }

    public abstract boolean A(Class cls);

    public abstract List i(List list, String str);

    public abstract Method u(Class cls, Field field);

    public abstract Constructor v(Class cls);

    public abstract String[] x(Class cls);
}
