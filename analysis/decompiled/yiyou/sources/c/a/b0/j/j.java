package c.a.b0.j;

import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: ExceptionHelper.java */
/* JADX INFO: loaded from: classes.dex */
public final class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Throwable f3091a = new a();

    /* JADX INFO: compiled from: ExceptionHelper.java */
    static final class a extends Throwable {
        a() {
            super("No further exceptions");
        }

        @Override // java.lang.Throwable
        public Throwable fillInStackTrace() {
            return this;
        }
    }

    public static RuntimeException a(Throwable th) {
        if (th instanceof Error) {
            throw ((Error) th);
        }
        return th instanceof RuntimeException ? (RuntimeException) th : new RuntimeException(th);
    }

    public static <T> boolean a(AtomicReference<Throwable> atomicReference, Throwable th) {
        Throwable th2;
        do {
            th2 = atomicReference.get();
            if (th2 == f3091a) {
                return false;
            }
        } while (!atomicReference.compareAndSet(th2, th2 == null ? th : new c.a.z.a(th2, th)));
        return true;
    }

    public static <T> Throwable a(AtomicReference<Throwable> atomicReference) {
        Throwable th = atomicReference.get();
        Throwable th2 = f3091a;
        return th != th2 ? atomicReference.getAndSet(th2) : th;
    }
}
