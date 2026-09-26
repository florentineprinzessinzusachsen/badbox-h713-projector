package a.a.a.a;

import java.util.concurrent.Executor;

/* JADX INFO: compiled from: ArchTaskExecutor.java */
/* JADX INFO: loaded from: classes.dex */
public class a extends c {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static volatile a f0c;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private c f2b = new a.a.a.a.b();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private c f1a = this.f2b;

    /* JADX INFO: renamed from: a.a.a.a.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ArchTaskExecutor.java */
    static class ExecutorC0000a implements Executor {
        ExecutorC0000a() {
        }

        @Override // java.util.concurrent.Executor
        public void execute(Runnable runnable) {
            a.b().b(runnable);
        }
    }

    /* JADX INFO: compiled from: ArchTaskExecutor.java */
    static class b implements Executor {
        b() {
        }

        @Override // java.util.concurrent.Executor
        public void execute(Runnable runnable) {
            a.b().a(runnable);
        }
    }

    static {
        new ExecutorC0000a();
        new b();
    }

    private a() {
    }

    public static a b() {
        if (f0c != null) {
            return f0c;
        }
        synchronized (a.class) {
            if (f0c == null) {
                f0c = new a();
            }
        }
        return f0c;
    }

    @Override // a.a.a.a.c
    public void a(Runnable runnable) {
        this.f1a.a(runnable);
    }

    @Override // a.a.a.a.c
    public boolean a() {
        return this.f1a.a();
    }

    @Override // a.a.a.a.c
    public void b(Runnable runnable) {
        this.f1a.b(runnable);
    }
}
