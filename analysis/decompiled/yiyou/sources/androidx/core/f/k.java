package androidx.core.f;

import android.view.View;
import android.view.ViewParent;

/* JADX INFO: compiled from: NestedScrollingChildHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private ViewParent f1078a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private ViewParent f1079b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final View f1080c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private boolean f1081d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int[] f1082e;

    public k(View view) {
        this.f1080c = view;
    }

    private ViewParent c(int i) {
        if (i == 0) {
            return this.f1078a;
        }
        if (i != 1) {
            return null;
        }
        return this.f1079b;
    }

    public void a(boolean z) {
        if (this.f1081d) {
            t.v(this.f1080c);
        }
        this.f1081d = z;
    }

    public void b(int i) {
        ViewParent viewParentC = c(i);
        if (viewParentC != null) {
            w.a(viewParentC, this.f1080c, i);
            a(i, (ViewParent) null);
        }
    }

    private boolean b(int i, int i2, int i3, int i4, int[] iArr, int i5, int[] iArr2) {
        ViewParent viewParentC;
        int i6;
        int i7;
        int[] iArr3;
        if (!a() || (viewParentC = c(i5)) == null) {
            return false;
        }
        if (i == 0 && i2 == 0 && i3 == 0 && i4 == 0) {
            if (iArr != null) {
                iArr[0] = 0;
                iArr[1] = 0;
            }
            return false;
        }
        if (iArr != null) {
            this.f1080c.getLocationInWindow(iArr);
            i6 = iArr[0];
            i7 = iArr[1];
        } else {
            i6 = 0;
            i7 = 0;
        }
        if (iArr2 == null) {
            int[] iArrB = b();
            iArrB[0] = 0;
            iArrB[1] = 0;
            iArr3 = iArrB;
        } else {
            iArr3 = iArr2;
        }
        w.a(viewParentC, this.f1080c, i, i2, i3, i4, i5, iArr3);
        if (iArr != null) {
            this.f1080c.getLocationInWindow(iArr);
            iArr[0] = iArr[0] - i6;
            iArr[1] = iArr[1] - i7;
        }
        return true;
    }

    public boolean a() {
        return this.f1081d;
    }

    public boolean a(int i) {
        return c(i) != null;
    }

    public boolean a(int i, int i2) {
        if (a(i2)) {
            return true;
        }
        if (!a()) {
            return false;
        }
        View view = this.f1080c;
        for (ViewParent parent = this.f1080c.getParent(); parent != null; parent = parent.getParent()) {
            if (w.b(parent, view, this.f1080c, i, i2)) {
                a(i2, parent);
                w.a(parent, view, this.f1080c, i, i2);
                return true;
            }
            if (parent instanceof View) {
                view = (View) parent;
            }
        }
        return false;
    }

    public boolean a(int i, int i2, int i3, int i4, int[] iArr) {
        return b(i, i2, i3, i4, iArr, 0, null);
    }

    public void a(int i, int i2, int i3, int i4, int[] iArr, int i5, int[] iArr2) {
        b(i, i2, i3, i4, iArr, i5, iArr2);
    }

    private int[] b() {
        if (this.f1082e == null) {
            this.f1082e = new int[2];
        }
        return this.f1082e;
    }

    public boolean a(int i, int i2, int[] iArr, int[] iArr2, int i3) {
        ViewParent viewParentC;
        int i4;
        int i5;
        if (!a() || (viewParentC = c(i3)) == null) {
            return false;
        }
        if (i == 0 && i2 == 0) {
            if (iArr2 != null) {
                iArr2[0] = 0;
                iArr2[1] = 0;
            }
            return false;
        }
        if (iArr2 != null) {
            this.f1080c.getLocationInWindow(iArr2);
            i4 = iArr2[0];
            i5 = iArr2[1];
        } else {
            i4 = 0;
            i5 = 0;
        }
        if (iArr == null) {
            iArr = b();
        }
        iArr[0] = 0;
        iArr[1] = 0;
        w.a(viewParentC, this.f1080c, i, i2, iArr, i3);
        if (iArr2 != null) {
            this.f1080c.getLocationInWindow(iArr2);
            iArr2[0] = iArr2[0] - i4;
            iArr2[1] = iArr2[1] - i5;
        }
        return (iArr[0] == 0 && iArr[1] == 0) ? false : true;
    }

    public boolean a(float f2, float f3, boolean z) {
        ViewParent viewParentC;
        if (!a() || (viewParentC = c(0)) == null) {
            return false;
        }
        return w.a(viewParentC, this.f1080c, f2, f3, z);
    }

    public boolean a(float f2, float f3) {
        ViewParent viewParentC;
        if (!a() || (viewParentC = c(0)) == null) {
            return false;
        }
        return w.a(viewParentC, this.f1080c, f2, f3);
    }

    private void a(int i, ViewParent viewParent) {
        if (i == 0) {
            this.f1078a = viewParent;
        } else {
            if (i != 1) {
                return;
            }
            this.f1079b = viewParent;
        }
    }
}
