package androidx.fragment.app;

import android.view.View;
import androidx.core.f.t;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

/* JADX INFO: compiled from: FragmentTransaction.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class m {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    int f1298b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    int f1299c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    int f1300d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    int f1301e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    int f1302f;
    int g;
    boolean h;
    String j;
    int k;
    CharSequence l;
    int m;
    CharSequence n;
    ArrayList<String> o;
    ArrayList<String> p;
    ArrayList<Runnable> r;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    ArrayList<a> f1297a = new ArrayList<>();
    boolean i = true;
    boolean q = false;

    /* JADX INFO: compiled from: FragmentTransaction.java */
    static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        int f1303a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        Fragment f1304b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f1305c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        int f1306d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        int f1307e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        int f1308f;
        androidx.lifecycle.e.b g;
        androidx.lifecycle.e.b h;

        a() {
        }

        a(int i, Fragment fragment) {
            this.f1303a = i;
            this.f1304b = fragment;
            androidx.lifecycle.e.b bVar = androidx.lifecycle.e.b.RESUMED;
            this.g = bVar;
            this.h = bVar;
        }
    }

    void a(a aVar) {
        this.f1297a.add(aVar);
        aVar.f1305c = this.f1298b;
        aVar.f1306d = this.f1299c;
        aVar.f1307e = this.f1300d;
        aVar.f1308f = this.f1301e;
    }

    public abstract int b();

    public m b(int i, Fragment fragment, String str) {
        if (i == 0) {
            throw new IllegalArgumentException("Must use non-zero containerViewId");
        }
        a(i, fragment, str, 2);
        return this;
    }

    public abstract int c();

    public m c(Fragment fragment) {
        a(new a(4, fragment));
        return this;
    }

    public m d(Fragment fragment) {
        a(new a(3, fragment));
        return this;
    }

    public m e(Fragment fragment) {
        a(new a(5, fragment));
        return this;
    }

    public m b(Fragment fragment) {
        a(new a(6, fragment));
        return this;
    }

    public m a(int i, Fragment fragment, String str) {
        a(i, fragment, str, 1);
        return this;
    }

    void a(int i, Fragment fragment, String str, int i2) {
        Class<?> cls = fragment.getClass();
        int modifiers = cls.getModifiers();
        if (!cls.isAnonymousClass() && Modifier.isPublic(modifiers) && (!cls.isMemberClass() || Modifier.isStatic(modifiers))) {
            if (str != null) {
                String str2 = fragment.x;
                if (str2 != null && !str.equals(str2)) {
                    throw new IllegalStateException("Can't change tag of fragment " + fragment + ": was " + fragment.x + " now " + str);
                }
                fragment.x = str;
            }
            if (i != 0) {
                if (i != -1) {
                    int i3 = fragment.v;
                    if (i3 != 0 && i3 != i) {
                        throw new IllegalStateException("Can't change container ID of fragment " + fragment + ": was " + fragment.v + " now " + i);
                    }
                    fragment.v = i;
                    fragment.w = i;
                } else {
                    throw new IllegalArgumentException("Can't add fragment " + fragment + " with tag " + str + " to container view with no id");
                }
            }
            a(new a(i2, fragment));
            return;
        }
        throw new IllegalStateException("Fragment " + cls.getCanonicalName() + " must be a public static class to be  properly recreated from instance state.");
    }

    public m a(Fragment fragment) {
        a(new a(7, fragment));
        return this;
    }

    public m a(int i, int i2, int i3, int i4) {
        this.f1298b = i;
        this.f1299c = i2;
        this.f1300d = i3;
        this.f1301e = i4;
        return this;
    }

    public m a(View view, String str) {
        if (n.b()) {
            String strM = t.m(view);
            if (strM != null) {
                if (this.o == null) {
                    this.o = new ArrayList<>();
                    this.p = new ArrayList<>();
                } else if (!this.p.contains(str)) {
                    if (this.o.contains(strM)) {
                        throw new IllegalArgumentException("A shared element with the source name '" + strM + "' has already been added to the transaction.");
                    }
                } else {
                    throw new IllegalArgumentException("A shared element with the target name '" + str + "' has already been added to the transaction.");
                }
                this.o.add(strM);
                this.p.add(str);
            } else {
                throw new IllegalArgumentException("Unique transitionNames are required for all sharedElements");
            }
        }
        return this;
    }

    public m a(String str) {
        if (this.i) {
            this.h = true;
            this.j = str;
            return this;
        }
        throw new IllegalStateException("This FragmentTransaction is not allowed to be added to the back stack.");
    }
}
