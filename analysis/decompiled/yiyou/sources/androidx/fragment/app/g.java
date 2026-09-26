package androidx.fragment.app;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import java.io.FileDescriptor;
import java.io.PrintWriter;

/* JADX INFO: compiled from: FragmentHostCallback.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class g<E> extends d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Activity f1239a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Context f1240b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Handler f1241c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final int f1242d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    final i f1243e;

    g(c cVar) {
        this(cVar, cVar, new Handler(), 0);
    }

    @Override // androidx.fragment.app.d
    public View a(int i) {
        return null;
    }

    void a(Fragment fragment) {
    }

    public void a(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
    }

    public boolean b(Fragment fragment) {
        return true;
    }

    @Override // androidx.fragment.app.d
    public boolean c() {
        return true;
    }

    Activity e() {
        return this.f1239a;
    }

    Context f() {
        return this.f1240b;
    }

    Handler g() {
        return this.f1241c;
    }

    public abstract E h();

    public LayoutInflater i() {
        return LayoutInflater.from(this.f1240b);
    }

    public int j() {
        return this.f1242d;
    }

    public boolean k() {
        return true;
    }

    public void l() {
    }

    g(Activity activity, Context context, Handler handler, int i) {
        this.f1243e = new i();
        this.f1239a = activity;
        androidx.core.e.e.a(context, "context == null");
        this.f1240b = context;
        androidx.core.e.e.a(handler, "handler == null");
        this.f1241c = handler;
        this.f1242d = i;
    }
}
