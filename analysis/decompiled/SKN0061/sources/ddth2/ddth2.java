package ddth2;

import android.content.Context;
import ddth2.hidden.C0001a;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public final class ddth2 {
    private static Method a(Class cls) throws NoSuchMethodException {
        Class superclass = cls;
        while (true) {
            int i = 0;
            if (superclass == null) {
                Method[] methods = cls.getMethods();
                int length = methods.length;
                while (i < length) {
                    Method method = methods[i];
                    if (method.getParameterTypes().length == 0 && MetricsSnapshot.class.getName().equals(method.getReturnType().getName())) {
                        method.setAccessible(true);
                        return method;
                    }
                    i++;
                }
                throw new NoSuchMethodException("MetricsSnapshot accessor not found in ".concat(cls.getName()));
            }
            Method[] declaredMethods = superclass.getDeclaredMethods();
            int length2 = declaredMethods.length;
            while (i < length2) {
                Method method2 = declaredMethods[i];
                if (method2.getParameterTypes().length == 0 && MetricsSnapshot.class.getName().equals(method2.getReturnType().getName())) {
                    method2.setAccessible(true);
                    return method2;
                }
                i++;
            }
            superclass = superclass.getSuperclass();
        }
    }

    public static MetricsSnapshot getRuntimeMetricsSnapshot(Context context) {
        p pVarA = p.a(context == null ? null : context.getApplicationContext());
        try {
            return (MetricsSnapshot) a(pVarA.getClass()).invoke(pVarA, null);
        } catch (Throwable th) {
            if (th instanceof RuntimeException) {
                throw ((RuntimeException) th);
            }
            if (th instanceof Error) {
                throw ((Error) th);
            }
            throw new RuntimeException(th);
        }
    }

    public static void initialize(Context context) {
        o.a(context);
        Context applicationContext = context == null ? null : context.getApplicationContext();
        if (applicationContext != null) {
            context = applicationContext;
        }
        C0001a.a(context);
    }

    public static void release(Context context) {
        C0001a.e();
        th = null;
        try {
            o.b(context);
        } catch (Throwable th) {
            if (th == null) {
                th = th;
            } else {
                th.addSuppressed(th);
            }
        }
        if (th == null) {
            return;
        }
        if (th instanceof RuntimeException) {
            throw ((RuntimeException) th);
        }
        if (!(th instanceof Error)) {
            throw new RuntimeException(th);
        }
        throw ((Error) th);
    }

    public static void initialize(Context context, SdkInitCallback sdkInitCallback) {
        o.a(context, sdkInitCallback);
        Context applicationContext = context == null ? null : context.getApplicationContext();
        if (applicationContext != null) {
            context = applicationContext;
        }
        C0001a.a(context);
    }
}
