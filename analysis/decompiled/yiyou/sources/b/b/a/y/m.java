package b.b.a.y;

import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/* JADX INFO: compiled from: UnsafeAllocator.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class m {

    /* JADX INFO: compiled from: UnsafeAllocator.java */
    static class a extends m {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Method f1620a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ Object f1621b;

        a(Method method, Object obj) {
            this.f1620a = method;
            this.f1621b = obj;
        }

        @Override // b.b.a.y.m
        public <T> T a(Class<T> cls) {
            m.b(cls);
            return (T) this.f1620a.invoke(this.f1621b, cls);
        }
    }

    /* JADX INFO: compiled from: UnsafeAllocator.java */
    static class b extends m {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Method f1622a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ int f1623b;

        b(Method method, int i) {
            this.f1622a = method;
            this.f1623b = i;
        }

        @Override // b.b.a.y.m
        public <T> T a(Class<T> cls) {
            m.b(cls);
            return (T) this.f1622a.invoke(null, cls, Integer.valueOf(this.f1623b));
        }
    }

    /* JADX INFO: compiled from: UnsafeAllocator.java */
    static class c extends m {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Method f1624a;

        c(Method method) {
            this.f1624a = method;
        }

        @Override // b.b.a.y.m
        public <T> T a(Class<T> cls) {
            m.b(cls);
            return (T) this.f1624a.invoke(null, cls, Object.class);
        }
    }

    /* JADX INFO: compiled from: UnsafeAllocator.java */
    static class d extends m {
        d() {
        }

        @Override // b.b.a.y.m
        public <T> T a(Class<T> cls) {
            throw new UnsupportedOperationException("Cannot allocate " + cls);
        }
    }

    public static m a() {
        try {
            Class<?> cls = Class.forName("sun.misc.Unsafe");
            Field declaredField = cls.getDeclaredField("theUnsafe");
            declaredField.setAccessible(true);
            return new a(cls.getMethod("allocateInstance", Class.class), declaredField.get(null));
        } catch (Exception unused) {
            try {
                try {
                    Method declaredMethod = ObjectStreamClass.class.getDeclaredMethod("getConstructorId", Class.class);
                    declaredMethod.setAccessible(true);
                    int iIntValue = ((Integer) declaredMethod.invoke(null, Object.class)).intValue();
                    Method declaredMethod2 = ObjectStreamClass.class.getDeclaredMethod("newInstance", Class.class, Integer.TYPE);
                    declaredMethod2.setAccessible(true);
                    return new b(declaredMethod2, iIntValue);
                } catch (Exception unused2) {
                    return new d();
                }
            } catch (Exception unused3) {
                Method declaredMethod3 = ObjectInputStream.class.getDeclaredMethod("newInstance", Class.class, Class.class);
                declaredMethod3.setAccessible(true);
                return new c(declaredMethod3);
            }
        }
    }

    static void b(Class<?> cls) {
        int modifiers = cls.getModifiers();
        if (Modifier.isInterface(modifiers)) {
            throw new UnsupportedOperationException("Interface can't be instantiated! Interface name: " + cls.getName());
        }
        if (Modifier.isAbstract(modifiers)) {
            throw new UnsupportedOperationException("Abstract class can't be instantiated! Class name: " + cls.getName());
        }
    }

    public abstract <T> T a(Class<T> cls);
}
