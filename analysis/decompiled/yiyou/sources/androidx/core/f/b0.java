package androidx.core.f;

import android.os.Build;
import android.view.WindowInsets;

/* JADX INFO: compiled from: WindowInsetsCompat.java */
/* JADX INFO: loaded from: classes.dex */
public class b0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Object f1058a;

    private b0(Object obj) {
        this.f1058a = obj;
    }

    public int a() {
        if (Build.VERSION.SDK_INT >= 20) {
            return ((WindowInsets) this.f1058a).getSystemWindowInsetBottom();
        }
        return 0;
    }

    public int b() {
        if (Build.VERSION.SDK_INT >= 20) {
            return ((WindowInsets) this.f1058a).getSystemWindowInsetLeft();
        }
        return 0;
    }

    public int c() {
        if (Build.VERSION.SDK_INT >= 20) {
            return ((WindowInsets) this.f1058a).getSystemWindowInsetRight();
        }
        return 0;
    }

    public int d() {
        if (Build.VERSION.SDK_INT >= 20) {
            return ((WindowInsets) this.f1058a).getSystemWindowInsetTop();
        }
        return 0;
    }

    public boolean e() {
        if (Build.VERSION.SDK_INT >= 21) {
            return ((WindowInsets) this.f1058a).isConsumed();
        }
        return false;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || b0.class != obj.getClass()) {
            return false;
        }
        Object obj2 = this.f1058a;
        Object obj3 = ((b0) obj).f1058a;
        if (obj2 == null) {
            return obj3 == null;
        }
        return obj2.equals(obj3);
    }

    public int hashCode() {
        Object obj = this.f1058a;
        if (obj == null) {
            return 0;
        }
        return obj.hashCode();
    }

    public b0 a(int i, int i2, int i3, int i4) {
        if (Build.VERSION.SDK_INT >= 20) {
            return new b0(((WindowInsets) this.f1058a).replaceSystemWindowInsets(i, i2, i3, i4));
        }
        return null;
    }

    static b0 a(Object obj) {
        if (obj == null) {
            return null;
        }
        return new b0(obj);
    }

    static Object a(b0 b0Var) {
        if (b0Var == null) {
            return null;
        }
        return b0Var.f1058a;
    }
}
