package androidx.lifecycle;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: ClassesInfoCache.java */
/* JADX INFO: loaded from: classes.dex */
class a {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    static a f1378c = new a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Map<Class, C0030a> f1379a = new HashMap();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Map<Class, Boolean> f1380b = new HashMap();

    /* JADX INFO: compiled from: ClassesInfoCache.java */
    static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final int f1383a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final Method f1384b;

        b(int i, Method method) {
            this.f1383a = i;
            this.f1384b = method;
            this.f1384b.setAccessible(true);
        }

        void a(h hVar, e.a aVar, Object obj) {
            try {
                int i = this.f1383a;
                if (i == 0) {
                    this.f1384b.invoke(obj, new Object[0]);
                } else if (i == 1) {
                    this.f1384b.invoke(obj, hVar);
                } else {
                    if (i != 2) {
                        return;
                    }
                    this.f1384b.invoke(obj, hVar, aVar);
                }
            } catch (IllegalAccessException e2) {
                throw new RuntimeException(e2);
            } catch (InvocationTargetException e3) {
                throw new RuntimeException("Failed to call observer method", e3.getCause());
            }
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || b.class != obj.getClass()) {
                return false;
            }
            b bVar = (b) obj;
            return this.f1383a == bVar.f1383a && this.f1384b.getName().equals(bVar.f1384b.getName());
        }

        public int hashCode() {
            return (this.f1383a * 31) + this.f1384b.getName().hashCode();
        }
    }

    a() {
    }

    private Method[] c(Class cls) {
        try {
            return cls.getDeclaredMethods();
        } catch (NoClassDefFoundError e2) {
            throw new IllegalArgumentException("The observer class has some methods that use newer APIs which are not available in the current OS version. Lifecycles cannot access even other methods so you should make sure that your observer classes only access framework classes that are available in your min API level OR use lifecycle:compiler annotation processor.", e2);
        }
    }

    C0030a a(Class cls) {
        C0030a c0030a = this.f1379a.get(cls);
        return c0030a != null ? c0030a : a(cls, null);
    }

    boolean b(Class cls) {
        Boolean bool = this.f1380b.get(cls);
        if (bool != null) {
            return bool.booleanValue();
        }
        Method[] methodArrC = c(cls);
        for (Method method : methodArrC) {
            if (((o) method.getAnnotation(o.class)) != null) {
                a(cls, methodArrC);
                return true;
            }
        }
        this.f1380b.put(cls, false);
        return false;
    }

    /* JADX INFO: renamed from: androidx.lifecycle.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ClassesInfoCache.java */
    static class C0030a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final Map<e.a, List<b>> f1381a = new HashMap();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final Map<b, e.a> f1382b;

        C0030a(Map<b, e.a> map) {
            this.f1382b = map;
            for (Map.Entry<b, e.a> entry : map.entrySet()) {
                e.a value = entry.getValue();
                List<b> arrayList = this.f1381a.get(value);
                if (arrayList == null) {
                    arrayList = new ArrayList<>();
                    this.f1381a.put(value, arrayList);
                }
                arrayList.add(entry.getKey());
            }
        }

        void a(h hVar, e.a aVar, Object obj) {
            a(this.f1381a.get(aVar), hVar, aVar, obj);
            a(this.f1381a.get(e.a.ON_ANY), hVar, aVar, obj);
        }

        private static void a(List<b> list, h hVar, e.a aVar, Object obj) {
            if (list != null) {
                for (int size = list.size() - 1; size >= 0; size--) {
                    list.get(size).a(hVar, aVar, obj);
                }
            }
        }
    }

    private void a(Map<b, e.a> map, b bVar, e.a aVar, Class cls) {
        e.a aVar2 = map.get(bVar);
        if (aVar2 == null || aVar == aVar2) {
            if (aVar2 == null) {
                map.put(bVar, aVar);
                return;
            }
            return;
        }
        throw new IllegalArgumentException("Method " + bVar.f1384b.getName() + " in " + cls.getName() + " already declared with different @OnLifecycleEvent value: previous value " + aVar2 + ", new value " + aVar);
    }

    private C0030a a(Class cls, Method[] methodArr) {
        int i;
        C0030a c0030aA;
        Class superclass = cls.getSuperclass();
        HashMap map = new HashMap();
        if (superclass != null && (c0030aA = a(superclass)) != null) {
            map.putAll(c0030aA.f1382b);
        }
        for (Class<?> cls2 : cls.getInterfaces()) {
            for (Map.Entry<b, e.a> entry : a(cls2).f1382b.entrySet()) {
                a(map, entry.getKey(), entry.getValue(), cls);
            }
        }
        if (methodArr == null) {
            methodArr = c(cls);
        }
        boolean z = false;
        for (Method method : methodArr) {
            o oVar = (o) method.getAnnotation(o.class);
            if (oVar != null) {
                Class<?>[] parameterTypes = method.getParameterTypes();
                if (parameterTypes.length <= 0) {
                    i = 0;
                } else {
                    if (!parameterTypes[0].isAssignableFrom(h.class)) {
                        throw new IllegalArgumentException("invalid parameter type. Must be one and instanceof LifecycleOwner");
                    }
                    i = 1;
                }
                e.a aVarValue = oVar.value();
                if (parameterTypes.length > 1) {
                    if (parameterTypes[1].isAssignableFrom(e.a.class)) {
                        if (aVarValue != e.a.ON_ANY) {
                            throw new IllegalArgumentException("Second arg is supported only for ON_ANY value");
                        }
                        i = 2;
                    } else {
                        throw new IllegalArgumentException("invalid parameter type. second arg must be an event");
                    }
                }
                if (parameterTypes.length <= 2) {
                    a(map, new b(i, method), aVarValue, cls);
                    z = true;
                } else {
                    throw new IllegalArgumentException("cannot have more than 2 params");
                }
            }
        }
        C0030a c0030a = new C0030a(map);
        this.f1379a.put(cls, c0030a);
        this.f1380b.put(cls, Boolean.valueOf(z));
        return c0030a;
    }
}
