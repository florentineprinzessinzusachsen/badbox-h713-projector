package u0;

import d0.l0;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Map f2236a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f2237b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final List f2238c;

    public c(Map map, boolean z3, List list) {
        this.f2236a = map;
        this.f2237b = z3;
        this.f2238c = list;
    }

    public static String a(Class cls) {
        int modifiers = cls.getModifiers();
        if (Modifier.isInterface(modifiers)) {
            return "Interfaces can't be instantiated! Register an InstanceCreator or a TypeAdapter for this type. Interface name: ".concat(cls.getName());
        }
        if (!Modifier.isAbstract(modifiers)) {
            return null;
        }
        return "Abstract classes can't be instantiated! Adjust the R8 configuration or register an InstanceCreator or a TypeAdapter for this type. Class name: " + cls.getName() + "\nSee " + "https://github.com/google/gson/blob/main/Troubleshooting.md#".concat("r8-abstract-class");
    }

    /* JADX WARN: Code duplicated, block: B:56:0x00ff  */
    /* JADX WARN: Code duplicated, block: B:58:0x0107  */
    /* JADX WARN: Code duplicated, block: B:59:0x010d  */
    /* JADX WARN: Code duplicated, block: B:61:0x0115  */
    /* JADX WARN: Code duplicated, block: B:62:0x011b  */
    /* JADX WARN: Code duplicated, block: B:64:0x0123  */
    /* JADX WARN: Code duplicated, block: B:65:0x012a  */
    /* JADX WARN: Code duplicated, block: B:67:0x0132  */
    public final q b(z0.a aVar, boolean z3) {
        q qVar;
        final String str;
        q rVar;
        final Type type = aVar.f2779b;
        Class cls = aVar.f2778a;
        Map map = this.f2236a;
        if (map.get(type) != null) {
            throw new ClassCastException();
        }
        if (map.get(cls) != null) {
            throw new ClassCastException();
        }
        final int i4 = 0;
        final int i5 = 1;
        b3.e eVar = null;
        if (EnumSet.class.isAssignableFrom(cls)) {
            qVar = new q() { // from class: u0.b
                @Override // u0.q
                public final Object a() {
                    switch (i4) {
                        case 0:
                            Type type2 = type;
                            if (!(type2 instanceof ParameterizedType)) {
                                throw new s0.r("Invalid EnumSet type: " + type2.toString());
                            }
                            Type type3 = ((ParameterizedType) type2).getActualTypeArguments()[0];
                            if (type3 instanceof Class) {
                                return EnumSet.noneOf((Class) type3);
                            }
                            throw new s0.r("Invalid EnumSet type: " + type2.toString());
                        default:
                            Type type4 = type;
                            if (!(type4 instanceof ParameterizedType)) {
                                throw new s0.r("Invalid EnumMap type: " + type4.toString());
                            }
                            Type type5 = ((ParameterizedType) type4).getActualTypeArguments()[0];
                            if (type5 instanceof Class) {
                                return new EnumMap((Class) type5);
                            }
                            throw new s0.r("Invalid EnumMap type: " + type4.toString());
                    }
                }
            };
        } else {
            qVar = cls == EnumMap.class ? new q() { // from class: u0.b
                @Override // u0.q
                public final Object a() {
                    switch (i5) {
                        case 0:
                            Type type2 = type;
                            if (!(type2 instanceof ParameterizedType)) {
                                throw new s0.r("Invalid EnumSet type: " + type2.toString());
                            }
                            Type type3 = ((ParameterizedType) type2).getActualTypeArguments()[0];
                            if (type3 instanceof Class) {
                                return EnumSet.noneOf((Class) type3);
                            }
                            throw new s0.r("Invalid EnumSet type: " + type2.toString());
                        default:
                            Type type4 = type;
                            if (!(type4 instanceof ParameterizedType)) {
                                throw new s0.r("Invalid EnumMap type: " + type4.toString());
                            }
                            Type type5 = ((ParameterizedType) type4).getActualTypeArguments()[0];
                            if (type5 instanceof Class) {
                                return new EnumMap((Class) type5);
                            }
                            throw new s0.r("Invalid EnumMap type: " + type4.toString());
                    }
                }
            } : null;
        }
        if (qVar != null) {
            return qVar;
        }
        i.e(this.f2238c);
        if (Modifier.isAbstract(cls.getModifiers())) {
            rVar = null;
        } else {
            try {
                Constructor declaredConstructor = cls.getDeclaredConstructor(null);
                l0 l0Var = x0.c.f2671a;
                try {
                    declaredConstructor.setAccessible(true);
                    str = null;
                } catch (Exception e4) {
                    str = "Failed making constructor '" + x0.c.b(declaredConstructor) + "' accessible; either increase its visibility or write a custom InstanceCreator or TypeAdapter for its declaring type: " + e4.getMessage() + x0.c.e(e4);
                }
                rVar = str != null ? new q() { // from class: u0.a
                    @Override // u0.q
                    public final Object a() {
                        switch (i5) {
                            case 0:
                                throw new s0.r(str);
                            case 1:
                                throw new s0.r(str);
                            case 2:
                                throw new s0.r(str);
                            default:
                                throw new s0.r(str);
                        }
                    }
                } : new e0.r(i5, declaredConstructor);
            } catch (NoSuchMethodException unused) {
                rVar = null;
            }
        }
        if (rVar != null) {
            return rVar;
        }
        final int i6 = 3;
        final int i7 = 2;
        if (Collection.class.isAssignableFrom(cls)) {
            if (cls.isAssignableFrom(ArrayList.class)) {
                eVar = new b3.e(6);
            } else if (cls.isAssignableFrom(LinkedHashSet.class)) {
                eVar = new b3.e(7);
            } else if (cls.isAssignableFrom(TreeSet.class)) {
                eVar = new b3.e(8);
            } else if (cls.isAssignableFrom(ArrayDeque.class)) {
                eVar = new b3.e(9);
            }
        } else if (Map.class.isAssignableFrom(cls)) {
            if (cls.isAssignableFrom(p.class)) {
                if (type instanceof ParameterizedType) {
                    Type[] actualTypeArguments = ((ParameterizedType) type).getActualTypeArguments();
                    if (actualTypeArguments.length == 0 || i.g(actualTypeArguments[0]) != String.class) {
                        if (cls.isAssignableFrom(LinkedHashMap.class)) {
                            eVar = new b3.e(2);
                        } else if (cls.isAssignableFrom(TreeMap.class)) {
                            eVar = new b3.e(3);
                        } else if (cls.isAssignableFrom(ConcurrentHashMap.class)) {
                            eVar = new b3.e(4);
                        } else if (cls.isAssignableFrom(ConcurrentSkipListMap.class)) {
                            eVar = new b3.e(5);
                        }
                    }
                }
                eVar = new b3.e(1);
            } else if (cls.isAssignableFrom(LinkedHashMap.class)) {
                eVar = new b3.e(2);
            } else if (cls.isAssignableFrom(TreeMap.class)) {
                eVar = new b3.e(3);
            } else if (cls.isAssignableFrom(ConcurrentHashMap.class)) {
                eVar = new b3.e(4);
            } else if (cls.isAssignableFrom(ConcurrentSkipListMap.class)) {
                eVar = new b3.e(5);
            }
        }
        if (eVar != null) {
            return eVar;
        }
        final String strA = a(cls);
        if (strA != null) {
            return new q() { // from class: u0.a
                @Override // u0.q
                public final Object a() {
                    switch (i4) {
                        case 0:
                            throw new s0.r(strA);
                        case 1:
                            throw new s0.r(strA);
                        case 2:
                            throw new s0.r(strA);
                        default:
                            throw new s0.r(strA);
                    }
                }
            };
        }
        if (!z3) {
            final String str2 = "Unable to create instance of " + cls + "; Register an InstanceCreator or a TypeAdapter for this type.";
            return new q() { // from class: u0.a
                @Override // u0.q
                public final Object a() {
                    switch (i7) {
                        case 0:
                            throw new s0.r(str2);
                        case 1:
                            throw new s0.r(str2);
                        case 2:
                            throw new s0.r(str2);
                        default:
                            throw new s0.r(str2);
                    }
                }
            };
        }
        if (this.f2237b) {
            return new e0.r(i7, cls);
        }
        final String str3 = "Unable to create instance of " + cls + "; usage of JDK Unsafe is disabled. Registering an InstanceCreator or a TypeAdapter for this type, adding a no-args constructor, or enabling usage of JDK Unsafe may fix this problem.";
        if (cls.getDeclaredConstructors().length == 0) {
            str3 = str3 + " Or adjust your R8 configuration to keep the no-args constructor of the class.";
        }
        return new q() { // from class: u0.a
            @Override // u0.q
            public final Object a() {
                switch (i6) {
                    case 0:
                        throw new s0.r(str3);
                    case 1:
                        throw new s0.r(str3);
                    case 2:
                        throw new s0.r(str3);
                    default:
                        throw new s0.r(str3);
                }
            }
        };
    }

    public final String toString() {
        return this.f2236a.toString();
    }
}
