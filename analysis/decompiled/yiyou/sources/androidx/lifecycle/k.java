package androidx.lifecycle;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: Lifecycling.java */
/* JADX INFO: loaded from: classes.dex */
public class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static Map<Class, Integer> f1401a = new HashMap();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static Map<Class, List<Constructor<? extends c>>> f1402b = new HashMap();

    static f a(Object obj) {
        boolean z = obj instanceof f;
        boolean z2 = obj instanceof b;
        if (z && z2) {
            return new FullLifecycleObserverAdapter((b) obj, (f) obj);
        }
        if (z2) {
            return new FullLifecycleObserverAdapter((b) obj, null);
        }
        if (z) {
            return (f) obj;
        }
        Class<?> cls = obj.getClass();
        if (b(cls) != 2) {
            return new ReflectiveGenericLifecycleObserver(obj);
        }
        List<Constructor<? extends c>> list = f1402b.get(cls);
        if (list.size() == 1) {
            return new SingleGeneratedAdapterObserver(a(list.get(0), obj));
        }
        c[] cVarArr = new c[list.size()];
        for (int i = 0; i < list.size(); i++) {
            cVarArr[i] = a(list.get(i), obj);
        }
        return new CompositeGeneratedAdaptersObserver(cVarArr);
    }

    private static int b(Class<?> cls) {
        Integer num = f1401a.get(cls);
        if (num != null) {
            return num.intValue();
        }
        int iD = d(cls);
        f1401a.put(cls, Integer.valueOf(iD));
        return iD;
    }

    private static boolean c(Class<?> cls) {
        return cls != null && g.class.isAssignableFrom(cls);
    }

    private static int d(Class<?> cls) {
        if (cls.getCanonicalName() == null) {
            return 1;
        }
        Constructor<? extends c> constructorA = a(cls);
        if (constructorA != null) {
            f1402b.put(cls, Collections.singletonList(constructorA));
            return 2;
        }
        if (a.f1378c.b(cls)) {
            return 1;
        }
        Class<? super Object> superclass = cls.getSuperclass();
        ArrayList arrayList = null;
        if (c(superclass)) {
            if (b(superclass) == 1) {
                return 1;
            }
            arrayList = new ArrayList(f1402b.get(superclass));
        }
        for (Class<?> cls2 : cls.getInterfaces()) {
            if (c(cls2)) {
                if (b(cls2) == 1) {
                    return 1;
                }
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.addAll(f1402b.get(cls2));
            }
        }
        if (arrayList == null) {
            return 1;
        }
        f1402b.put(cls, arrayList);
        return 2;
    }

    private static c a(Constructor<? extends c> constructor, Object obj) {
        try {
            return constructor.newInstance(obj);
        } catch (IllegalAccessException e2) {
            throw new RuntimeException(e2);
        } catch (InstantiationException e3) {
            throw new RuntimeException(e3);
        } catch (InvocationTargetException e4) {
            throw new RuntimeException(e4);
        }
    }

    private static Constructor<? extends c> a(Class<?> cls) {
        try {
            Package r0 = cls.getPackage();
            String canonicalName = cls.getCanonicalName();
            String name = r0 != null ? r0.getName() : "";
            if (!name.isEmpty()) {
                canonicalName = canonicalName.substring(name.length() + 1);
            }
            String strA = a(canonicalName);
            if (!name.isEmpty()) {
                strA = name + "." + strA;
            }
            Constructor declaredConstructor = Class.forName(strA).getDeclaredConstructor(cls);
            if (!declaredConstructor.isAccessible()) {
                declaredConstructor.setAccessible(true);
            }
            return declaredConstructor;
        } catch (ClassNotFoundException unused) {
            return null;
        } catch (NoSuchMethodException e2) {
            throw new RuntimeException(e2);
        }
    }

    public static String a(String str) {
        return str.replace(".", "_") + "_LifecycleAdapter";
    }
}
