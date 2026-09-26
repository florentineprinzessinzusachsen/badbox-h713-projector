package b.b.a.y.o;

import b.b.a.y.e;
import java.lang.reflect.AccessibleObject;

/* JADX INFO: compiled from: ReflectionAccessor.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final b f1702a;

    static {
        f1702a = e.b() < 9 ? new a() : new c();
    }

    public static b a() {
        return f1702a;
    }

    public abstract void a(AccessibleObject accessibleObject);
}
