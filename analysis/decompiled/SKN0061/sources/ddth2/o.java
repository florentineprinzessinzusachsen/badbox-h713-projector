package ddth2;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public final class o {
    public static final Object a = new Object();

    public static class a implements Runnable {
        public final /* synthetic */ p a;

        public a(p pVar) {
            this.a = pVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.a.e();
        }
    }

    public static class b implements Runnable {
        public final /* synthetic */ p a;

        public b(p pVar) {
            this.a = pVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.a.d();
        }
    }

    public static class c implements Runnable {
        public final /* synthetic */ p a;

        public c(p pVar) {
            this.a = pVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.a.g();
        }
    }

    public static class d implements Runnable {
        public final /* synthetic */ p a;

        public d(p pVar) {
            this.a = pVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.a.f();
        }
    }

    public static class e implements Runnable {
        public final /* synthetic */ p a;

        public e(p pVar) {
            this.a = pVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.a.e();
        }
    }

    public static class f implements Runnable {
        public final /* synthetic */ p a;

        public f(p pVar) {
            this.a = pVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.a.d();
        }
    }

    public static class g implements Runnable {
        public final /* synthetic */ p a;

        public g(p pVar) {
            this.a = pVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.a.g();
        }
    }

    public static class h implements Runnable {
        public final /* synthetic */ p a;

        public h(p pVar) {
            this.a = pVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.a.f();
        }
    }

    public static class i implements Runnable {
        public final /* synthetic */ SdkInitCallback a;

        public i(SdkInitCallback sdkInitCallback) {
            this.a = sdkInitCallback;
        }

        @Override // java.lang.Runnable
        public void run() {
            SdkInitCallback sdkInitCallback = this.a;
            if (sdkInitCallback != null) {
                sdkInitCallback.onStartSuccess();
            }
        }
    }

    public static void a(Context context) {
        Context applicationContext = context.getApplicationContext();
        synchronized (a) {
            p pVarA = p.a(applicationContext);
            boolean zM69c = pVarA.m69c();
            pVarA.j();
            if (!zM69c) {
                pVarA.k();
                pVarA.h();
            }
            pVarA.a(new a(pVarA), new b(pVarA), new c(pVarA), new d(pVarA), null);
            pVarA.l();
        }
    }

    public static void a(Context context, SdkInitCallback sdkInitCallback) {
        Context applicationContext = context.getApplicationContext();
        synchronized (a) {
            p pVarA = p.a(applicationContext);
            boolean zM69c = pVarA.m69c();
            pVarA.j();
            if (!zM69c) {
                pVarA.k();
                pVarA.h();
            }
            pVarA.a(new e(pVarA), new f(pVarA), new g(pVarA), new h(pVarA), new i(sdkInitCallback));
            pVarA.l();
        }
    }

    public static void b(Context context) {
        Context applicationContext = context.getApplicationContext();
        synchronized (a) {
            p pVarA = p.a(applicationContext);
            if (pVarA.b()) {
                pVarA.i();
                pVarA.m();
            }
        }
    }
}
