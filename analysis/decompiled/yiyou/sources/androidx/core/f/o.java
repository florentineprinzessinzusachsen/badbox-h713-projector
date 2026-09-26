package androidx.core.f;

import android.view.View;
import android.view.ViewGroup;

/* JADX INFO: compiled from: NestedScrollingParentHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f1083a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f1084b;

    public o(ViewGroup viewGroup) {
    }

    public void a(View view, View view2, int i) {
        a(view, view2, i, 0);
    }

    public void a(View view, View view2, int i, int i2) {
        if (i2 == 1) {
            this.f1084b = i;
        } else {
            this.f1083a = i;
        }
    }

    public int a() {
        return this.f1083a | this.f1084b;
    }

    public void a(View view, int i) {
        if (i == 1) {
            this.f1084b = 0;
        } else {
            this.f1083a = 0;
        }
    }
}
