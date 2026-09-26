package x0;

import d0.l0;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class b extends l0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Method f2667a = Class.class.getMethod("isRecord", null);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Method f2668b = Class.class.getMethod("getRecordComponents", null);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Method f2669c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Method f2670d;

    public b() throws ClassNotFoundException {
        Class<?> cls = Class.forName("java.lang.reflect.RecordComponent");
        this.f2669c = cls.getMethod("getName", null);
        this.f2670d = cls.getMethod("getType", null);
    }

    @Override // d0.l0
    public final boolean A(Class cls) {
        try {
            return ((Boolean) this.f2667a.invoke(cls, null)).booleanValue();
        } catch (ReflectiveOperationException e4) {
            throw new RuntimeException("Unexpected ReflectiveOperationException occurred (Gson 2.13.2). To support Java records, reflection is utilized to read out information about records. All these invocations happens after it is established that records exist in the JVM. This exception is unexpected behavior.", e4);
        }
    }

    @Override // d0.l0
    public final Method u(Class cls, Field field) {
        try {
            return cls.getMethod(field.getName(), null);
        } catch (ReflectiveOperationException e4) {
            throw new RuntimeException("Unexpected ReflectiveOperationException occurred (Gson 2.13.2). To support Java records, reflection is utilized to read out information about records. All these invocations happens after it is established that records exist in the JVM. This exception is unexpected behavior.", e4);
        }
    }

    @Override // d0.l0
    public final Constructor v(Class cls) {
        try {
            Object[] objArr = (Object[]) this.f2668b.invoke(cls, null);
            Class<?>[] clsArr = new Class[objArr.length];
            for (int i4 = 0; i4 < objArr.length; i4++) {
                clsArr[i4] = (Class) this.f2670d.invoke(objArr[i4], null);
            }
            return cls.getDeclaredConstructor(clsArr);
        } catch (ReflectiveOperationException e4) {
            throw new RuntimeException("Unexpected ReflectiveOperationException occurred (Gson 2.13.2). To support Java records, reflection is utilized to read out information about records. All these invocations happens after it is established that records exist in the JVM. This exception is unexpected behavior.", e4);
        }
    }

    @Override // d0.l0
    public final String[] x(Class cls) {
        try {
            Object[] objArr = (Object[]) this.f2668b.invoke(cls, null);
            String[] strArr = new String[objArr.length];
            for (int i4 = 0; i4 < objArr.length; i4++) {
                strArr[i4] = (String) this.f2669c.invoke(objArr[i4], null);
            }
            return strArr;
        } catch (ReflectiveOperationException e4) {
            throw new RuntimeException("Unexpected ReflectiveOperationException occurred (Gson 2.13.2). To support Java records, reflection is utilized to read out information about records. All these invocations happens after it is established that records exist in the JVM. This exception is unexpected behavior.", e4);
        }
    }
}
