package b3;

import a3.d0;
import a3.r;
import a3.t;
import a3.x;
import j2.i;
import java.io.InterruptedIOException;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import p2.p;
import q3.h;
import q3.u;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public abstract class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final TimeZone f348a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final String f349b;

    static {
        TimeZone timeZone = TimeZone.getTimeZone("GMT");
        i.b(timeZone);
        f348a = timeZone;
        String strL0 = p2.i.L0(x.class.getName(), "okhttp3.");
        if (p.u0(strL0, "Client")) {
            strL0 = strL0.substring(0, strL0.length() - 6);
            i.d(strL0, "substring(...)");
        }
        f349b = strL0;
    }

    public static final boolean a(t tVar, t tVar2) {
        i.e(tVar, "<this>");
        i.e(tVar2, "other");
        return i.a(tVar.f211d, tVar2.f211d) && tVar.f212e == tVar2.f212e && i.a(tVar.f208a, tVar2.f208a);
    }

    public static final int b(long j4) {
        TimeUnit timeUnit = TimeUnit.SECONDS;
        i.e(timeUnit, "unit");
        if (j4 < 0) {
            throw new IllegalStateException("timeout".concat(" < 0").toString());
        }
        long millis = timeUnit.toMillis(j4);
        if (millis > 2147483647L) {
            throw new IllegalArgumentException("timeout".concat(" too large").toString());
        }
        if (millis != 0 || j4 <= 0) {
            return (int) millis;
        }
        throw new IllegalArgumentException("timeout".concat(" too small").toString());
    }

    public static final void c(Socket socket) {
        i.e(socket, "<this>");
        try {
            socket.close();
        } catch (AssertionError e4) {
            throw e4;
        } catch (RuntimeException e5) {
            if (!i.a(e5.getMessage(), "bio == null")) {
                throw e5;
            }
        } catch (Exception unused) {
        }
    }

    public static final String d(String str, Object... objArr) {
        i.e(str, "format");
        Locale locale = Locale.US;
        Object[] objArrCopyOf = Arrays.copyOf(objArr, objArr.length);
        return String.format(locale, str, Arrays.copyOf(objArrCopyOf, objArrCopyOf.length));
    }

    public static final long e(d0 d0Var) {
        String strA = d0Var.f108i.a("Content-Length");
        if (strA == null) {
            return -1L;
        }
        byte[] bArr = d.f343a;
        try {
            return Long.parseLong(strA);
        } catch (NumberFormatException unused) {
            return -1L;
        }
    }

    public static final Charset f(q3.g gVar, Charset charset) {
        i.e(gVar, "<this>");
        i.e(charset, "default");
        int iM = gVar.m(d.f344b);
        if (iM == -1) {
            return charset;
        }
        if (iM == 0) {
            return p2.a.f1738a;
        }
        if (iM == 1) {
            return p2.a.f1739b;
        }
        if (iM == 2) {
            Charset charset2 = p2.a.f1738a;
            Charset charset3 = p2.a.f1741d;
            if (charset3 != null) {
                return charset3;
            }
            Charset charsetForName = Charset.forName("UTF-32LE");
            i.d(charsetForName, "forName(...)");
            p2.a.f1741d = charsetForName;
            return charsetForName;
        }
        if (iM == 3) {
            return p2.a.f1740c;
        }
        if (iM != 4) {
            throw new AssertionError();
        }
        Charset charset4 = p2.a.f1738a;
        Charset charset5 = p2.a.f1742e;
        if (charset5 != null) {
            return charset5;
        }
        Charset charsetForName2 = Charset.forName("UTF-32BE");
        i.d(charsetForName2, "forName(...)");
        p2.a.f1742e = charsetForName2;
        return charsetForName2;
    }

    public static final boolean g(u uVar, int i4) {
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        i.e(timeUnit, "timeUnit");
        long jNanoTime = System.nanoTime();
        long jC = uVar.f().e() ? uVar.f().c() - jNanoTime : Long.MAX_VALUE;
        uVar.f().d(Math.min(jC, timeUnit.toNanos(i4)) + jNanoTime);
        try {
            q3.e eVar = new q3.e();
            while (uVar.g(8192L, eVar) != -1) {
                eVar.skip(eVar.f1822e);
            }
            if (jC == Long.MAX_VALUE) {
                uVar.f().a();
                return true;
            }
            uVar.f().d(jNanoTime + jC);
            return true;
        } catch (InterruptedIOException unused) {
            if (jC == Long.MAX_VALUE) {
                uVar.f().a();
                return false;
            }
            uVar.f().d(jNanoTime + jC);
            return false;
        } catch (Throwable th) {
            if (jC == Long.MAX_VALUE) {
                uVar.f().a();
            } else {
                uVar.f().d(jNanoTime + jC);
            }
            throw th;
        }
    }

    public static final r h(List list) {
        ArrayList arrayList = new ArrayList(20);
        Iterator it = list.iterator();
        while (it.hasNext()) {
            h3.d dVar = (h3.d) it.next();
            h hVar = dVar.f1085a;
            h hVar2 = dVar.f1086b;
            String strJ = hVar.j();
            String strJ2 = hVar2.j();
            arrayList.add(strJ);
            arrayList.add(p2.i.S0(strJ2).toString());
        }
        return new r((String[]) arrayList.toArray(new String[0]));
    }

    public static final String i(t tVar, boolean z3) {
        int i4;
        i.e(tVar, "<this>");
        int i5 = tVar.f212e;
        String str = tVar.f211d;
        if (p2.i.B0(str, ":")) {
            str = "[" + str + ']';
        }
        if (!z3) {
            String str2 = tVar.f208a;
            i.e(str2, "scheme");
            if (str2.equals("http")) {
                i4 = 80;
            } else {
                i4 = str2.equals("https") ? 443 : -1;
            }
            if (i5 == i4) {
                return str;
            }
        }
        return str + ':' + i5;
    }

    public static final List j(List list) {
        i.e(list, "<this>");
        if (list.isEmpty()) {
            return v1.p.f2517d;
        }
        if (list.size() == 1) {
            List listSingletonList = Collections.singletonList(list.get(0));
            i.d(listSingletonList, "singletonList(...)");
            return listSingletonList;
        }
        Object[] array = list.toArray();
        i.d(array, "toArray(...)");
        List listUnmodifiableList = Collections.unmodifiableList(v1.i.R(array));
        i.d(listUnmodifiableList, "unmodifiableList(...)");
        return listUnmodifiableList;
    }

    public static final List k(Object[] objArr) {
        if (objArr == null || objArr.length == 0) {
            return v1.p.f2517d;
        }
        if (objArr.length == 1) {
            List listSingletonList = Collections.singletonList(objArr[0]);
            i.d(listSingletonList, "singletonList(...)");
            return listSingletonList;
        }
        List listUnmodifiableList = Collections.unmodifiableList(v1.i.R((Object[]) objArr.clone()));
        i.d(listUnmodifiableList, "unmodifiableList(...)");
        return listUnmodifiableList;
    }
}
