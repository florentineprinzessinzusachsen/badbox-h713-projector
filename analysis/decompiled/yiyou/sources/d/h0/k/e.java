package d.h0.k;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: compiled from: OptionalMethod.java */
/* JADX INFO: loaded from: classes.dex */
class e<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Class<?> f4603a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f4604b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Class[] f4605c;

    e(Class<?> cls, String str, Class... clsArr) {
        this.f4603a = cls;
        this.f4604b = str;
        this.f4605c = clsArr;
    }

    public boolean a(T t) {
        return a(t.getClass()) != null;
    }

    public Object b(T t, Object... objArr) {
        Method methodA = a(t.getClass());
        if (methodA == null) {
            return null;
        }
        try {
            return methodA.invoke(t, objArr);
        } catch (IllegalAccessException unused) {
            return null;
        }
    }

    public Object c(T t, Object... objArr) {
        try {
            return b(t, objArr);
        } catch (InvocationTargetException e2) {
            Throwable targetException = e2.getTargetException();
            if (targetException instanceof RuntimeException) {
                throw ((RuntimeException) targetException);
            }
            AssertionError assertionError = new AssertionError("Unexpected exception");
            assertionError.initCause(targetException);
            throw assertionError;
        }
    }

    public Object d(T t, Object... objArr) {
        try {
            return a(t, objArr);
        } catch (InvocationTargetException e2) {
            Throwable targetException = e2.getTargetException();
            if (targetException instanceof RuntimeException) {
                throw ((RuntimeException) targetException);
            }
            AssertionError assertionError = new AssertionError("Unexpected exception");
            assertionError.initCause(targetException);
            throw assertionError;
        }
    }

    public Object a(T t, Object... objArr) {
        Method methodA = a(t.getClass());
        if (methodA == null) {
            throw new AssertionError("Method " + this.f4604b + " not supported for object " + t);
        }
        try {
            return methodA.invoke(t, objArr);
        } catch (IllegalAccessException e2) {
            AssertionError assertionError = new AssertionError("Unexpectedly could not call: " + methodA);
            assertionError.initCause(e2);
            throw assertionError;
        }
    }

    private Method a(Class<?> cls) {
        Class<?> cls2;
        String str = this.f4604b;
        if (str == null) {
            return null;
        }
        Method methodA = a(cls, str, this.f4605c);
        if (methodA == null || (cls2 = this.f4603a) == null || cls2.isAssignableFrom(methodA.getReturnType())) {
            return methodA;
        }
        return null;
    }

    private static Method a(Class<?> cls, String str, Class[] clsArr) {
        try {
            Method method = cls.getMethod(str, clsArr);
            try {
                if ((method.getModifiers() & 1) != 0) {
                    return method;
                }
                return null;
            } catch (NoSuchMethodException unused) {
                return method;
            }
        } catch (NoSuchMethodException unused2) {
        }
    }
}
