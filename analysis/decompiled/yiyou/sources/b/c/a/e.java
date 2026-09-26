package b.c.a;

import com.android.umanalytics.utils.ShellUtils;
import d.a0;
import java.io.IOException;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: Printer.java */
/* JADX INFO: loaded from: classes.dex */
class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f1740a = System.getProperty("line.separator");

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final String f1741b = f1740a + f1740a;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final String[] f1742c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final String[] f1743d;

    static {
        String str = f1740a;
        f1742c = new String[]{str, "Omitted response body"};
        f1743d = new String[]{str, "Omitted request body"};
    }

    static void a(d.e eVar, long j, boolean z, int i, String str, String str2, List<String> list, String str3, String str4) {
        String str5 = f1740a + "Body:" + f1740a + b(str2);
        String strA = eVar.a(false);
        String[] strArr = {"URL: " + str4, ShellUtils.COMMAND_LINE_END};
        String[] strArrA = a(str, j, i, z, eVar.d(), list, str3);
        if (eVar.e() == null) {
            a.a(eVar.f(), strA, "┌────── Response ───────────────────────────────────────────────────────────────────────", eVar.g());
        }
        a(eVar.f(), strA, strArr, eVar.e(), true, eVar.g());
        a(eVar.f(), strA, strArrA, eVar.e(), true, eVar.g());
        if (eVar.d() == b.BASIC || eVar.d() == b.BODY) {
            a(eVar.f(), strA, str5.split(f1740a), eVar.e(), true, eVar.g());
        }
        if (eVar.e() == null) {
            a.a(eVar.f(), strA, "└───────────────────────────────────────────────────────────────────────────────────────", eVar.g());
        }
    }

    static void b(d.e eVar, a0 a0Var) {
        String str = f1740a + "Body:" + f1740a + a(a0Var);
        String strA = eVar.a(true);
        if (eVar.e() == null) {
            a.a(eVar.f(), strA, "┌────── Request ────────────────────────────────────────────────────────────────────────", eVar.g());
        }
        a(eVar.f(), strA, new String[]{"URL: " + a0Var.g()}, eVar.e(), false, eVar.g());
        a(eVar.f(), strA, a(a0Var, eVar.d()), eVar.e(), true, eVar.g());
        if (eVar.d() == b.BASIC || eVar.d() == b.BODY) {
            a(eVar.f(), strA, str.split(f1740a), eVar.e(), true, eVar.g());
        }
        if (eVar.e() == null) {
            a.a(eVar.f(), strA, "└───────────────────────────────────────────────────────────────────────────────────────", eVar.g());
        }
    }

    private static boolean c(String str) {
        return f.a(str) || ShellUtils.COMMAND_LINE_END.equals(str) || "\t".equals(str) || f.a(str.trim());
    }

    static String b(String str) {
        try {
            if (str.startsWith("{")) {
                str = new JSONObject(str).toString(3);
            } else if (str.startsWith("[")) {
                str = new JSONArray(str).toString(3);
            }
        } catch (JSONException unused) {
        }
        return str;
    }

    static void a(d.e eVar, a0 a0Var) {
        String strA = eVar.a(true);
        if (eVar.e() == null) {
            a.a(eVar.f(), strA, "┌────── Request ────────────────────────────────────────────────────────────────────────", eVar.g());
        }
        a(eVar.f(), strA, new String[]{"URL: " + a0Var.g()}, eVar.e(), false, eVar.g());
        a(eVar.f(), strA, a(a0Var, eVar.d()), eVar.e(), true, eVar.g());
        if (eVar.d() == b.BASIC || eVar.d() == b.BODY) {
            a(eVar.f(), strA, f1743d, eVar.e(), true, eVar.g());
        }
        if (eVar.e() == null) {
            a.a(eVar.f(), strA, "└───────────────────────────────────────────────────────────────────────────────────────", eVar.g());
        }
    }

    static void a(d.e eVar, long j, boolean z, int i, String str, List<String> list, String str2) {
        String strA = eVar.a(false);
        if (eVar.e() == null) {
            a.a(eVar.f(), strA, "┌────── Response ───────────────────────────────────────────────────────────────────────", eVar.g());
        }
        a(eVar.f(), strA, a(str, j, i, z, eVar.d(), list, str2), eVar.e(), true, eVar.g());
        a(eVar.f(), strA, f1742c, eVar.e(), true, eVar.g());
        if (eVar.e() == null) {
            a.a(eVar.f(), strA, "└───────────────────────────────────────────────────────────────────────────────────────", eVar.g());
        }
    }

    private static String[] a(a0 a0Var, b bVar) {
        String string = a0Var.c().toString();
        boolean z = bVar == b.HEADERS || bVar == b.BASIC;
        StringBuilder sb = new StringBuilder();
        sb.append("Method: @");
        sb.append(a0Var.e());
        sb.append(f1741b);
        String str = "";
        if (!c(string) && z) {
            str = "Headers:" + f1740a + a(string);
        }
        sb.append(str);
        return sb.toString().split(f1740a);
    }

    private static String[] a(String str, long j, int i, boolean z, b bVar, List<String> list, String str2) {
        String str3;
        boolean z2 = bVar == b.HEADERS || bVar == b.BASIC;
        String strA = a(list);
        StringBuilder sb = new StringBuilder();
        String str4 = "";
        if (f.a(strA)) {
            str3 = "";
        } else {
            str3 = strA + " - ";
        }
        sb.append(str3);
        sb.append("is success : ");
        sb.append(z);
        sb.append(" - ");
        sb.append("Received in: ");
        sb.append(j);
        sb.append("ms");
        sb.append(f1741b);
        sb.append("Status Code: ");
        sb.append(i);
        sb.append(" / ");
        sb.append(str2);
        sb.append(f1741b);
        if (!c(str) && z2) {
            str4 = "Headers:" + f1740a + a(str);
        }
        sb.append(str4);
        return sb.toString().split(f1740a);
    }

    private static String a(List<String> list) {
        StringBuilder sb = new StringBuilder();
        for (String str : list) {
            sb.append("/");
            sb.append(str);
        }
        return sb.toString();
    }

    private static String a(String str) {
        String str2;
        String[] strArrSplit = str.split(f1740a);
        StringBuilder sb = new StringBuilder();
        int i = 0;
        if (strArrSplit.length > 1) {
            while (i < strArrSplit.length) {
                if (i == 0) {
                    str2 = "┌ ";
                } else {
                    str2 = i == strArrSplit.length - 1 ? "└ " : "├ ";
                }
                sb.append(str2);
                sb.append(strArrSplit[i]);
                sb.append(ShellUtils.COMMAND_LINE_END);
                i++;
            }
        } else {
            int length = strArrSplit.length;
            while (i < length) {
                String str3 = strArrSplit[i];
                sb.append("─ ");
                sb.append(str3);
                sb.append(ShellUtils.COMMAND_LINE_END);
                i++;
            }
        }
        return sb.toString();
    }

    private static void a(int i, String str, String[] strArr, c cVar, boolean z, boolean z2) {
        for (String str2 : strArr) {
            int length = str2.length();
            int i2 = z ? 110 : length;
            int i3 = 0;
            while (i3 <= length / i2) {
                int i4 = i3 * i2;
                i3++;
                int length2 = i3 * i2;
                if (length2 > str2.length()) {
                    length2 = str2.length();
                }
                if (cVar == null) {
                    a.a(i, str, "│ " + str2.substring(i4, length2), z2);
                } else {
                    cVar.a(i, str, str2.substring(i4, length2));
                }
            }
        }
    }

    private static String a(a0 a0Var) {
        try {
            a0 a0VarA = a0Var.f().a();
            e.c cVar = new e.c();
            if (a0VarA.a() == null) {
                return "";
            }
            a0VarA.a().writeTo(cVar);
            return b(cVar.o());
        } catch (IOException e2) {
            return "{\"err\": \"" + e2.getMessage() + "\"}";
        }
    }
}
