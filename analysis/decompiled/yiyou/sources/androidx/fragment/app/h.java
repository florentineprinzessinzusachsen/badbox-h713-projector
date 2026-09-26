package androidx.fragment.app;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.List;

/* JADX INFO: compiled from: FragmentManager.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class h {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static final f f1244b = new f();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private f f1245a = null;

    /* JADX INFO: compiled from: FragmentManager.java */
    public interface a {
        int a();
    }

    /* JADX INFO: compiled from: FragmentManager.java */
    public static abstract class b {
        public abstract void a(h hVar, Fragment fragment);

        public abstract void a(h hVar, Fragment fragment, Context context);

        public abstract void a(h hVar, Fragment fragment, Bundle bundle);

        public abstract void a(h hVar, Fragment fragment, View view, Bundle bundle);

        public abstract void b(h hVar, Fragment fragment);

        public abstract void b(h hVar, Fragment fragment, Context context);

        public abstract void b(h hVar, Fragment fragment, Bundle bundle);

        public abstract void c(h hVar, Fragment fragment);

        public abstract void c(h hVar, Fragment fragment, Bundle bundle);

        public abstract void d(h hVar, Fragment fragment);

        public abstract void d(h hVar, Fragment fragment, Bundle bundle);

        public abstract void e(h hVar, Fragment fragment);

        public abstract void f(h hVar, Fragment fragment);

        public abstract void g(h hVar, Fragment fragment);
    }

    /* JADX INFO: compiled from: FragmentManager.java */
    public interface c {
        void a();
    }

    public abstract Fragment a(String str);

    public abstract a a(int i);

    public abstract m a();

    public abstract void a(int i, int i2);

    public void a(f fVar) {
        this.f1245a = fVar;
    }

    public abstract void a(String str, int i);

    public abstract void a(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr);

    public abstract boolean b();

    public abstract boolean b(int i, int i2);

    public abstract boolean b(String str, int i);

    public abstract int c();

    public f d() {
        if (this.f1245a == null) {
            this.f1245a = f1244b;
        }
        return this.f1245a;
    }

    public abstract List<Fragment> e();

    public abstract void f();

    public abstract boolean g();
}
