package d;

import com.android.umanalytics.utils.ShellUtils;
import com.baidu.mobstat.Config;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* JADX INFO: compiled from: Headers.java */
/* JADX INFO: loaded from: classes.dex */
public final class s {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String[] f4662a;

    s(a aVar) {
        List<String> list = aVar.f4663a;
        this.f4662a = (String[]) list.toArray(new String[list.size()]);
    }

    public String a(String str) {
        return a(this.f4662a, str);
    }

    public int b() {
        return this.f4662a.length / 2;
    }

    public boolean equals(Object obj) {
        return (obj instanceof s) && Arrays.equals(((s) obj).f4662a, this.f4662a);
    }

    public int hashCode() {
        return Arrays.hashCode(this.f4662a);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        int iB = b();
        for (int i = 0; i < iB; i++) {
            sb.append(a(i));
            sb.append(": ");
            sb.append(b(i));
            sb.append(ShellUtils.COMMAND_LINE_END);
        }
        return sb.toString();
    }

    /* JADX INFO: compiled from: Headers.java */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final List<String> f4663a = new ArrayList(20);

        private void d(String str, String str2) {
            if (str == null) {
                throw new NullPointerException("name == null");
            }
            if (str.isEmpty()) {
                throw new IllegalArgumentException("name is empty");
            }
            int length = str.length();
            for (int i = 0; i < length; i++) {
                char cCharAt = str.charAt(i);
                if (cCharAt <= ' ' || cCharAt >= 127) {
                    throw new IllegalArgumentException(d.h0.c.a("Unexpected char %#04x at %d in header name: %s", Integer.valueOf(cCharAt), Integer.valueOf(i), str));
                }
            }
            if (str2 == null) {
                throw new NullPointerException("value for name " + str + " == null");
            }
            int length2 = str2.length();
            for (int i2 = 0; i2 < length2; i2++) {
                char cCharAt2 = str2.charAt(i2);
                if ((cCharAt2 <= 31 && cCharAt2 != '\t') || cCharAt2 >= 127) {
                    throw new IllegalArgumentException(d.h0.c.a("Unexpected char %#04x at %d in %s value: %s", Integer.valueOf(cCharAt2), Integer.valueOf(i2), str, str2));
                }
            }
        }

        a a(String str) {
            int iIndexOf = str.indexOf(Config.TRACE_TODAY_VISIT_SPLIT, 1);
            if (iIndexOf != -1) {
                b(str.substring(0, iIndexOf), str.substring(iIndexOf + 1));
                return this;
            }
            if (str.startsWith(Config.TRACE_TODAY_VISIT_SPLIT)) {
                b("", str.substring(1));
                return this;
            }
            b("", str);
            return this;
        }

        a b(String str, String str2) {
            this.f4663a.add(str);
            this.f4663a.add(str2.trim());
            return this;
        }

        public a c(String str) {
            int i = 0;
            while (i < this.f4663a.size()) {
                if (str.equalsIgnoreCase(this.f4663a.get(i))) {
                    this.f4663a.remove(i);
                    this.f4663a.remove(i);
                    i -= 2;
                }
                i += 2;
            }
            return this;
        }

        public String b(String str) {
            for (int size = this.f4663a.size() - 2; size >= 0; size -= 2) {
                if (str.equalsIgnoreCase(this.f4663a.get(size))) {
                    return this.f4663a.get(size + 1);
                }
            }
            return null;
        }

        public a c(String str, String str2) {
            d(str, str2);
            c(str);
            b(str, str2);
            return this;
        }

        public a a(String str, String str2) {
            d(str, str2);
            b(str, str2);
            return this;
        }

        public s a() {
            return new s(this);
        }
    }

    public String a(int i) {
        return this.f4662a[i * 2];
    }

    public String b(int i) {
        return this.f4662a[(i * 2) + 1];
    }

    private s(String[] strArr) {
        this.f4662a = strArr;
    }

    public a a() {
        a aVar = new a();
        Collections.addAll(aVar.f4663a, this.f4662a);
        return aVar;
    }

    public List<String> b(String str) {
        int iB = b();
        ArrayList arrayList = null;
        for (int i = 0; i < iB; i++) {
            if (str.equalsIgnoreCase(a(i))) {
                if (arrayList == null) {
                    arrayList = new ArrayList(2);
                }
                arrayList.add(b(i));
            }
        }
        if (arrayList != null) {
            return Collections.unmodifiableList(arrayList);
        }
        return Collections.emptyList();
    }

    private static String a(String[] strArr, String str) {
        for (int length = strArr.length - 2; length >= 0; length -= 2) {
            if (str.equalsIgnoreCase(strArr[length])) {
                return strArr[length + 1];
            }
        }
        return null;
    }

    public static s a(String... strArr) {
        if (strArr != null) {
            if (strArr.length % 2 == 0) {
                String[] strArr2 = (String[]) strArr.clone();
                for (int i = 0; i < strArr2.length; i++) {
                    if (strArr2[i] != null) {
                        strArr2[i] = strArr2[i].trim();
                    } else {
                        throw new IllegalArgumentException("Headers cannot be null");
                    }
                }
                for (int i2 = 0; i2 < strArr2.length; i2 += 2) {
                    String str = strArr2[i2];
                    String str2 = strArr2[i2 + 1];
                    if (str.length() == 0 || str.indexOf(0) != -1 || str2.indexOf(0) != -1) {
                        throw new IllegalArgumentException("Unexpected header: " + str + ": " + str2);
                    }
                }
                return new s(strArr2);
            }
            throw new IllegalArgumentException("Expected alternating header names and values");
        }
        throw new NullPointerException("namesAndValues == null");
    }
}
