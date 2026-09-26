package f3;

import a3.d0;
import a3.r;
import a3.t;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;
import v1.p;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public abstract class h {
    static {
        q3.h hVar = q3.h.f1823g;
        a1.a.m("\"\\");
        a1.a.m("\t ,=");
    }

    public static final boolean a(d0 d0Var) {
        if (j2.i.a(d0Var.f103d.f62b, "HEAD")) {
            return false;
        }
        int i4 = d0Var.f106g;
        if (((i4 < 100 || i4 >= 200) && i4 != 204 && i4 != 304) || b3.g.e(d0Var) != -1) {
            return true;
        }
        String strA = d0Var.f108i.a("Transfer-Encoding");
        if (strA == null) {
            strA = null;
        }
        return "chunked".equalsIgnoreCase(strA);
    }

    /* JADX WARN: Code duplicated, block: B:34:0x00a3  */
    public static final void b(a3.b bVar, t tVar, r rVar) {
        List listUnmodifiableList;
        List listUnmodifiableList2;
        p pVar;
        a3.k kVar;
        int i4;
        a3.k kVar2;
        j2.i.e(bVar, "<this>");
        j2.i.e(tVar, "url");
        j2.i.e(rVar, "headers");
        if (bVar == a3.b.f69e) {
            return;
        }
        Pattern pattern = a3.k.f169k;
        int size = rVar.size();
        int i5 = 0;
        ArrayList arrayList = null;
        for (int i6 = 0; i6 < size; i6++) {
            if ("Set-Cookie".equalsIgnoreCase(rVar.b(i6))) {
                if (arrayList == null) {
                    arrayList = new ArrayList(2);
                }
                arrayList.add(rVar.d(i6));
            }
        }
        if (arrayList != null) {
            listUnmodifiableList = Collections.unmodifiableList(arrayList);
            j2.i.d(listUnmodifiableList, "unmodifiableList(...)");
        } else {
            listUnmodifiableList = null;
        }
        p pVar2 = p.f2517d;
        List list = listUnmodifiableList == null ? pVar2 : listUnmodifiableList;
        int size2 = list.size();
        int i7 = 0;
        ArrayList arrayList2 = null;
        while (i7 < size2) {
            String str = (String) list.get(i7);
            j2.i.e(str, "setCookie");
            long jCurrentTimeMillis = System.currentTimeMillis();
            byte[] bArr = b3.d.f343a;
            char c4 = ';';
            int iD = b3.d.d(str, ';', i5, str.length());
            char c5 = '=';
            int iD2 = b3.d.d(str, '=', i5, iD);
            if (iD2 == iD) {
                pVar = pVar2;
                kVar = null;
                i4 = 0;
            } else {
                String strN = b3.d.n(str, i5, iD2);
                if (strN.length() != 0 && b3.d.f(strN) == -1) {
                    String strN2 = b3.d.n(str, iD2 + 1, iD);
                    if (b3.d.f(strN2) == -1) {
                        int i8 = iD + 1;
                        int length = str.length();
                        long j4 = 253402300799999L;
                        long jV = 253402300799999L;
                        String str2 = null;
                        String str3 = null;
                        boolean z3 = false;
                        long j5 = -1;
                        boolean z4 = false;
                        boolean z5 = true;
                        String str4 = null;
                        boolean z6 = false;
                        while (true) {
                            if (i8 >= length) {
                                pVar = pVar2;
                                if (j5 == Long.MIN_VALUE) {
                                    j4 = Long.MIN_VALUE;
                                } else if (j5 != -1) {
                                    long j6 = jCurrentTimeMillis + (j5 <= 9223372036854775L ? j5 * ((long) 1000) : Long.MAX_VALUE);
                                    if (j6 >= jCurrentTimeMillis && j6 <= 253402300799999L) {
                                        j4 = j6;
                                    }
                                } else {
                                    j4 = jV;
                                }
                                String str5 = tVar.f211d;
                                if (str2 != null) {
                                    if (!j2.i.a(str5, str2)) {
                                        if (p2.p.u0(str5, str2) && str5.charAt((str5.length() - str2.length()) - 1) == '.') {
                                            p2.h hVar = b3.c.f342a;
                                            p2.h hVar2 = b3.c.f342a;
                                            hVar2.getClass();
                                            if (!hVar2.f1761d.matcher(str5).matches()) {
                                            }
                                        }
                                        i4 = 0;
                                        kVar2 = null;
                                    }
                                    kVar = kVar2;
                                    break;
                                }
                                str2 = str5;
                                if (str5.length() == str2.length() || n3.a.f1496d.a(str2) != null) {
                                    String strSubstring = "/";
                                    i4 = 0;
                                    if (str3 == null || !p2.p.z0(str3, "/", false)) {
                                        String strB = tVar.b();
                                        int iI0 = p2.i.I0(strB, '/', 0, 6);
                                        if (iI0 != 0) {
                                            strSubstring = strB.substring(0, iI0);
                                            j2.i.d(strSubstring, "substring(...)");
                                        }
                                        str3 = strSubstring;
                                    }
                                    kVar2 = new a3.k(strN, strN2, j4, str2, str3, z6, z3, z4, z5, str4);
                                } else {
                                    i4 = 0;
                                    kVar2 = null;
                                }
                                kVar = kVar2;
                                break;
                            }
                            p pVar3 = pVar2;
                            int iD3 = b3.d.d(str, c4, i8, length);
                            int iD4 = b3.d.d(str, c5, i8, iD3);
                            String strN3 = b3.d.n(str, i8, iD4);
                            String strN4 = iD4 < iD3 ? b3.d.n(str, iD4 + 1, iD3) : "";
                            if (strN3.equalsIgnoreCase("expires")) {
                                try {
                                    jV = l3.h.V(strN4.length(), strN4);
                                    z4 = true;
                                } catch (NumberFormatException | IllegalArgumentException unused) {
                                }
                            } else if (strN3.equalsIgnoreCase("max-age")) {
                                try {
                                    long j7 = Long.parseLong(strN4);
                                    j5 = j7 <= 0 ? Long.MIN_VALUE : j7;
                                } catch (NumberFormatException e4) {
                                    Pattern patternCompile = Pattern.compile("-?\\d+");
                                    j2.i.d(patternCompile, "compile(...)");
                                    if (!patternCompile.matcher(strN4).matches()) {
                                        throw e4;
                                    }
                                    j5 = p2.p.z0(strN4, "-", false) ? Long.MIN_VALUE : Long.MAX_VALUE;
                                }
                                z4 = true;
                            } else if (strN3.equalsIgnoreCase("domain")) {
                                if (p2.p.u0(strN4, ".")) {
                                    throw new IllegalArgumentException("Failed requirement.");
                                }
                                String strB2 = b3.c.b(p2.i.L0(strN4, "."));
                                if (strB2 == null) {
                                    throw new IllegalArgumentException();
                                }
                                str2 = strB2;
                                z5 = false;
                            } else if (strN3.equalsIgnoreCase("path")) {
                                str3 = strN4;
                            } else if (strN3.equalsIgnoreCase("secure")) {
                                z6 = true;
                            } else if (strN3.equalsIgnoreCase("httponly")) {
                                z3 = true;
                            } else if (strN3.equalsIgnoreCase("samesite")) {
                                str4 = strN4;
                            }
                            i8 = iD3 + 1;
                            pVar2 = pVar3;
                            c4 = ';';
                            c5 = '=';
                        }
                    } else {
                        pVar = pVar2;
                        kVar = null;
                        i4 = 0;
                    }
                } else {
                    pVar = pVar2;
                    kVar = null;
                    i4 = 0;
                }
            }
            if (kVar != null) {
                if (arrayList2 == null) {
                    arrayList2 = new ArrayList();
                }
                arrayList2.add(kVar);
            }
            i7++;
            i5 = i4;
            pVar2 = pVar;
        }
        p pVar4 = pVar2;
        if (arrayList2 != null) {
            listUnmodifiableList2 = Collections.unmodifiableList(arrayList2);
            j2.i.d(listUnmodifiableList2, "unmodifiableList(...)");
        } else {
            listUnmodifiableList2 = null;
        }
        (listUnmodifiableList2 == null ? pVar4 : listUnmodifiableList2).isEmpty();
    }
}
