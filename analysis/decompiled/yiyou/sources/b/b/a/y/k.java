package b.b.a.y;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: Primitives.java */
/* JADX INFO: loaded from: classes.dex */
public final class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final Map<Class<?>, Class<?>> f1616a;

    static {
        HashMap map = new HashMap(16);
        HashMap map2 = new HashMap(16);
        a(map, map2, Boolean.TYPE, Boolean.class);
        a(map, map2, Byte.TYPE, Byte.class);
        a(map, map2, Character.TYPE, Character.class);
        a(map, map2, Double.TYPE, Double.class);
        a(map, map2, Float.TYPE, Float.class);
        a(map, map2, Integer.TYPE, Integer.class);
        a(map, map2, Long.TYPE, Long.class);
        a(map, map2, Short.TYPE, Short.class);
        a(map, map2, Void.TYPE, Void.class);
        f1616a = Collections.unmodifiableMap(map);
        Collections.unmodifiableMap(map2);
    }

    private static void a(Map<Class<?>, Class<?>> map, Map<Class<?>, Class<?>> map2, Class<?> cls, Class<?> cls2) {
        map.put(cls, cls2);
        map2.put(cls2, cls);
    }

    public static boolean a(Type type) {
        return f1616a.containsKey(type);
    }

    public static <T> Class<T> a(Class<T> cls) {
        Map<Class<?>, Class<?>> map = f1616a;
        a.a(cls);
        Class<T> cls2 = (Class) map.get(cls);
        return cls2 == null ? cls : cls2;
    }
}
