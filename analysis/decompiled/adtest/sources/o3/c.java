package o3;

import j2.i;
import java.security.cert.Certificate;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import p2.h;
import v1.p;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class c implements HostnameVerifier {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final c f1576a = new c();

    public static List a(X509Certificate x509Certificate, int i4) {
        Object obj;
        try {
            Collection<List<?>> subjectAlternativeNames = x509Certificate.getSubjectAlternativeNames();
            if (subjectAlternativeNames != null) {
                ArrayList arrayList = new ArrayList();
                for (List<?> list : subjectAlternativeNames) {
                    if (list != null && list.size() >= 2 && i.a(list.get(0), Integer.valueOf(i4)) && (obj = list.get(1)) != null) {
                        arrayList.add((String) obj);
                    }
                }
                return arrayList;
            }
        } catch (CertificateParsingException unused) {
        }
        return p.f2517d;
    }

    public static boolean b(String str) {
        int i4;
        int length = str.length();
        int length2 = str.length();
        if (length2 < 0) {
            throw new IllegalArgumentException(a1.c.d(length2, "endIndex < beginIndex: ", " < 0").toString());
        }
        if (length2 > str.length()) {
            throw new IllegalArgumentException(("endIndex > string.length: " + length2 + " > " + str.length()).toString());
        }
        long j4 = 0;
        int i5 = 0;
        while (i5 < length2) {
            char cCharAt = str.charAt(i5);
            if (cCharAt < 128) {
                j4++;
            } else {
                if (cCharAt < 2048) {
                    i4 = 2;
                } else if (cCharAt < 55296 || cCharAt > 57343) {
                    i4 = 3;
                } else {
                    int i6 = i5 + 1;
                    char cCharAt2 = i6 < length2 ? str.charAt(i6) : (char) 0;
                    if (cCharAt > 56319 || cCharAt2 < 56320 || cCharAt2 > 57343) {
                        j4++;
                        i5 = i6;
                    } else {
                        j4 += (long) 4;
                        i5 += 2;
                    }
                }
                j4 += (long) i4;
            }
            i5++;
        }
        return length == ((int) j4);
    }

    /* JADX WARN: Code duplicated, block: B:59:0x00fc  */
    public static boolean c(String str, X509Certificate x509Certificate) {
        boolean zA;
        int length;
        i.e(str, "host");
        h hVar = b3.c.f342a;
        h hVar2 = b3.c.f342a;
        hVar2.getClass();
        if (hVar2.f1761d.matcher(str).matches()) {
            String strB = b3.c.b(str);
            List listA = a(x509Certificate, 7);
            if (!listA.isEmpty()) {
                Iterator it = listA.iterator();
                while (it.hasNext()) {
                    if (i.a(strB, b3.c.b((String) it.next()))) {
                        return true;
                    }
                }
            }
            return false;
        }
        if (b(str)) {
            Locale locale = Locale.US;
            i.d(locale, "US");
            str = str.toLowerCase(locale);
            i.d(str, "toLowerCase(...)");
        }
        List<String> listA2 = a(x509Certificate, 2);
        if (!listA2.isEmpty()) {
            for (String lowerCase : listA2) {
                if (str.length() == 0 || p2.p.z0(str, ".", false) || p2.p.u0(str, "..") || lowerCase == null || lowerCase.length() == 0 || p2.p.z0(lowerCase, ".", false) || p2.p.u0(lowerCase, "..")) {
                    zA = false;
                } else {
                    String strConcat = !p2.p.u0(str, ".") ? str.concat(".") : str;
                    if (!p2.p.u0(lowerCase, ".")) {
                        lowerCase = lowerCase.concat(".");
                    }
                    if (b(lowerCase)) {
                        Locale locale2 = Locale.US;
                        i.d(locale2, "US");
                        lowerCase = lowerCase.toLowerCase(locale2);
                        i.d(lowerCase, "toLowerCase(...)");
                    }
                    if (!p2.i.B0(lowerCase, "*")) {
                        zA = i.a(strConcat, lowerCase);
                    } else if (!p2.p.z0(lowerCase, "*.", false) || p2.i.E0(lowerCase, '*', 1, 4) != -1 || strConcat.length() < lowerCase.length() || "*.".equals(lowerCase)) {
                        zA = false;
                    } else {
                        String strSubstring = lowerCase.substring(1);
                        i.d(strSubstring, "substring(...)");
                        if (p2.p.u0(strConcat, strSubstring) && ((length = strConcat.length() - strSubstring.length()) <= 0 || p2.i.I0(strConcat, '.', length - 1, 4) == -1)) {
                            zA = true;
                        } else {
                            zA = false;
                        }
                    }
                }
                if (zA) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override // javax.net.ssl.HostnameVerifier
    public final boolean verify(String str, SSLSession sSLSession) {
        i.e(str, "host");
        i.e(sSLSession, "session");
        if (b(str)) {
            try {
                Certificate certificate = sSLSession.getPeerCertificates()[0];
                i.c(certificate, "null cannot be cast to non-null type java.security.cert.X509Certificate");
                return c(str, (X509Certificate) certificate);
            } catch (SSLException unused) {
            }
        }
        return false;
    }
}
