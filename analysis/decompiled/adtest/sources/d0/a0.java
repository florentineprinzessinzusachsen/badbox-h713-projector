package d0;

import android.util.Log;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class a0 {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Object f401b = new Object();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static volatile a0 f402c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f403a;

    public a0(int i4) {
        this.f403a = i4;
    }

    public static a0 e() {
        a0 a0Var;
        synchronized (f401b) {
            try {
                if (f402c == null) {
                    f402c = new a0(3);
                }
                a0Var = f402c;
            } catch (Throwable th) {
                throw th;
            }
        }
        return a0Var;
    }

    public static String g(String str) {
        int length = str.length();
        StringBuilder sb = new StringBuilder(23);
        sb.append("WM-");
        if (length >= 20) {
            sb.append(str.substring(0, 20));
        } else {
            sb.append(str);
        }
        return sb.toString();
    }

    public final void a(String str, String str2) {
        if (this.f403a <= 3) {
            Log.d(str, str2);
        }
    }

    public final void b(String str, String str2, Throwable th) {
        if (this.f403a <= 3) {
            Log.d(str, str2, th);
        }
    }

    public final void c(String str, String str2) {
        if (this.f403a <= 6) {
            Log.e(str, str2);
        }
    }

    public final void d(String str, String str2, Throwable th) {
        if (this.f403a <= 6) {
            Log.e(str, str2, th);
        }
    }

    public final void f(String str, String str2) {
        if (this.f403a <= 4) {
            Log.i(str, str2);
        }
    }

    public final void h(String str, String str2) {
        if (this.f403a <= 5) {
            Log.w(str, str2);
        }
    }
}
