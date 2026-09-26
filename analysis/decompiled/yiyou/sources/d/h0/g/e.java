package d.h0.g;

import d.a0;
import d.c0;
import d.l;
import d.m;
import d.s;
import d.t;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Pattern;

/* JADX INFO: compiled from: HttpHeaders.java */
/* JADX INFO: loaded from: classes.dex */
public final class e {
    static {
        Pattern.compile(" +([^ \"=]*)=(:?\"([^\"]*)\"|([^ \"=]*)) *(:?,|$)");
    }

    public static long a(c0 c0Var) {
        return a(c0Var.o());
    }

    public static boolean b(s sVar) {
        return c(sVar).contains("*");
    }

    public static boolean c(c0 c0Var) {
        return b(c0Var.o());
    }

    private static Set<String> d(c0 c0Var) {
        return c(c0Var.o());
    }

    public static s e(c0 c0Var) {
        return a(c0Var.r().w().c(), c0Var.o());
    }

    public static long a(s sVar) {
        return a(sVar.a("Content-Length"));
    }

    public static boolean b(c0 c0Var) {
        if (c0Var.w().e().equals("HEAD")) {
            return false;
        }
        int iM = c0Var.m();
        return (((iM >= 100 && iM < 200) || iM == 204 || iM == 304) && a(c0Var) == -1 && !"chunked".equalsIgnoreCase(c0Var.a("Transfer-Encoding"))) ? false : true;
    }

    public static Set<String> c(s sVar) {
        Set<String> setEmptySet = Collections.emptySet();
        int iB = sVar.b();
        Set<String> treeSet = setEmptySet;
        for (int i = 0; i < iB; i++) {
            if ("Vary".equalsIgnoreCase(sVar.a(i))) {
                String strB = sVar.b(i);
                if (treeSet.isEmpty()) {
                    treeSet = new TreeSet<>((Comparator<? super String>) String.CASE_INSENSITIVE_ORDER);
                }
                for (String str : strB.split(",")) {
                    treeSet.add(str.trim());
                }
            }
        }
        return treeSet;
    }

    private static long a(String str) {
        if (str == null) {
            return -1L;
        }
        try {
            return Long.parseLong(str);
        } catch (NumberFormatException unused) {
            return -1L;
        }
    }

    public static boolean a(c0 c0Var, s sVar, a0 a0Var) {
        for (String str : d(c0Var)) {
            if (!d.h0.c.a(sVar.b(str), a0Var.b(str))) {
                return false;
            }
        }
        return true;
    }

    public static s a(s sVar, s sVar2) {
        Set<String> setC = c(sVar2);
        if (setC.isEmpty()) {
            return new s.a().a();
        }
        s.a aVar = new s.a();
        int iB = sVar.b();
        for (int i = 0; i < iB; i++) {
            String strA = sVar.a(i);
            if (setC.contains(strA)) {
                aVar.a(strA, sVar.b(i));
            }
        }
        return aVar.a();
    }

    public static int b(String str, int i) {
        char cCharAt;
        while (i < str.length() && ((cCharAt = str.charAt(i)) == ' ' || cCharAt == '\t')) {
            i++;
        }
        return i;
    }

    public static void a(m mVar, t tVar, s sVar) {
        if (mVar == m.f4642a) {
            return;
        }
        List<l> listA = l.a(tVar, sVar);
        if (listA.isEmpty()) {
            return;
        }
        mVar.a(tVar, listA);
    }

    public static int a(String str, int i, String str2) {
        while (i < str.length() && str2.indexOf(str.charAt(i)) == -1) {
            i++;
        }
        return i;
    }

    public static int a(String str, int i) {
        try {
            long j = Long.parseLong(str);
            if (j > 2147483647L) {
                return Integer.MAX_VALUE;
            }
            if (j < 0) {
                return 0;
            }
            return (int) j;
        } catch (NumberFormatException unused) {
            return i;
        }
    }
}
