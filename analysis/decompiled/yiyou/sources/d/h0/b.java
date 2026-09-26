package d.h0;

/* JADX INFO: compiled from: NamedRunnable.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class b implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected final String f4336a;

    public b(String str, Object... objArr) {
        this.f4336a = c.a(str, objArr);
    }

    protected abstract void b();

    @Override // java.lang.Runnable
    public final void run() {
        String name = Thread.currentThread().getName();
        Thread.currentThread().setName(this.f4336a);
        try {
            b();
        } finally {
            Thread.currentThread().setName(name);
        }
    }
}
