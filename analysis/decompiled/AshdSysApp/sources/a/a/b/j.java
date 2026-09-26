package a.a.b;

/* JADX INFO: loaded from: classes.dex */
public abstract class j implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Thread f10a;
    public int b = 0;
    public int c = 0;
    public int d = 0;
    public Object e;

    public abstract void a();

    public void b() {
    }

    public void c() {
    }

    public boolean d() {
        int i = this.d;
        if (i != 3 && i != 2 && i != 1) {
            return false;
        }
        this.b = 0;
        this.d = 0;
        return true;
    }

    public void e() {
        if (this.d != 1) {
            this.d = 1;
            this.f10a = new Thread(this);
            this.f10a.start();
        }
        b();
    }

    public void f() {
        if (this.d != 0) {
            c();
            this.d = 3;
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        a();
        f();
    }
}
