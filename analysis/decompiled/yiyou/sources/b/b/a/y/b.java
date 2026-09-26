package b.b.a.y;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Properties;

/* JADX INFO: compiled from: $Gson$Types.java */
/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final Type[] f1566a = new Type[0];

    /* JADX INFO: compiled from: $Gson$Types.java */
    private static final class a implements GenericArrayType, Serializable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Type f1567a;

        public a(Type type) {
            this.f1567a = b.b(type);
        }

        public boolean equals(Object obj) {
            return (obj instanceof GenericArrayType) && b.a((Type) this, (Type) obj);
        }

        @Override // java.lang.reflect.GenericArrayType
        public Type getGenericComponentType() {
            return this.f1567a;
        }

        public int hashCode() {
            return this.f1567a.hashCode();
        }

        public String toString() {
            return b.h(this.f1567a) + "[]";
        }
    }

    /* JADX INFO: renamed from: b.b.a.y.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: $Gson$Types.java */
    private static final class C0038b implements ParameterizedType, Serializable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Type f1568a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final Type f1569b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final Type[] f1570c;

        public C0038b(Type type, Type type2, Type... typeArr) {
            if (type2 instanceof Class) {
                Class cls = (Class) type2;
                boolean z = true;
                boolean z2 = Modifier.isStatic(cls.getModifiers()) || cls.getEnclosingClass() == null;
                if (type == null && !z2) {
                    z = false;
                }
                b.b.a.y.a.a(z);
            }
            this.f1568a = type == null ? null : b.b(type);
            this.f1569b = b.b(type2);
            this.f1570c = (Type[]) typeArr.clone();
            int length = this.f1570c.length;
            for (int i = 0; i < length; i++) {
                b.b.a.y.a.a(this.f1570c[i]);
                b.c(this.f1570c[i]);
                Type[] typeArr2 = this.f1570c;
                typeArr2[i] = b.b(typeArr2[i]);
            }
        }

        public boolean equals(Object obj) {
            return (obj instanceof ParameterizedType) && b.a((Type) this, (Type) obj);
        }

        @Override // java.lang.reflect.ParameterizedType
        public Type[] getActualTypeArguments() {
            return (Type[]) this.f1570c.clone();
        }

        @Override // java.lang.reflect.ParameterizedType
        public Type getOwnerType() {
            return this.f1568a;
        }

        @Override // java.lang.reflect.ParameterizedType
        public Type getRawType() {
            return this.f1569b;
        }

        public int hashCode() {
            return (Arrays.hashCode(this.f1570c) ^ this.f1569b.hashCode()) ^ b.a((Object) this.f1568a);
        }

        public String toString() {
            int length = this.f1570c.length;
            if (length == 0) {
                return b.h(this.f1569b);
            }
            StringBuilder sb = new StringBuilder((length + 1) * 30);
            sb.append(b.h(this.f1569b));
            sb.append("<");
            sb.append(b.h(this.f1570c[0]));
            for (int i = 1; i < length; i++) {
                sb.append(", ");
                sb.append(b.h(this.f1570c[i]));
            }
            sb.append(">");
            return sb.toString();
        }
    }

    /* JADX INFO: compiled from: $Gson$Types.java */
    private static final class c implements WildcardType, Serializable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Type f1571a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final Type f1572b;

        public c(Type[] typeArr, Type[] typeArr2) {
            b.b.a.y.a.a(typeArr2.length <= 1);
            b.b.a.y.a.a(typeArr.length == 1);
            if (typeArr2.length != 1) {
                b.b.a.y.a.a(typeArr[0]);
                b.c(typeArr[0]);
                this.f1572b = null;
                this.f1571a = b.b(typeArr[0]);
                return;
            }
            b.b.a.y.a.a(typeArr2[0]);
            b.c(typeArr2[0]);
            b.b.a.y.a.a(typeArr[0] == Object.class);
            this.f1572b = b.b(typeArr2[0]);
            this.f1571a = Object.class;
        }

        public boolean equals(Object obj) {
            return (obj instanceof WildcardType) && b.a((Type) this, (Type) obj);
        }

        @Override // java.lang.reflect.WildcardType
        public Type[] getLowerBounds() {
            Type type = this.f1572b;
            return type != null ? new Type[]{type} : b.f1566a;
        }

        @Override // java.lang.reflect.WildcardType
        public Type[] getUpperBounds() {
            return new Type[]{this.f1571a};
        }

        public int hashCode() {
            Type type = this.f1572b;
            return (type != null ? type.hashCode() + 31 : 1) ^ (this.f1571a.hashCode() + 31);
        }

        public String toString() {
            if (this.f1572b != null) {
                return "? super " + b.h(this.f1572b);
            }
            if (this.f1571a == Object.class) {
                return "?";
            }
            return "? extends " + b.h(this.f1571a);
        }
    }

    public static ParameterizedType a(Type type, Type type2, Type... typeArr) {
        return new C0038b(type, type2, typeArr);
    }

    public static Type b(Type type) {
        if (type instanceof Class) {
            Class cls = (Class) type;
            return cls.isArray() ? new a(b(cls.getComponentType())) : cls;
        }
        if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            return new C0038b(parameterizedType.getOwnerType(), parameterizedType.getRawType(), parameterizedType.getActualTypeArguments());
        }
        if (type instanceof GenericArrayType) {
            return new a(((GenericArrayType) type).getGenericComponentType());
        }
        if (!(type instanceof WildcardType)) {
            return type;
        }
        WildcardType wildcardType = (WildcardType) type;
        return new c(wildcardType.getUpperBounds(), wildcardType.getLowerBounds());
    }

    static void c(Type type) {
        b.b.a.y.a.a(((type instanceof Class) && ((Class) type).isPrimitive()) ? false : true);
    }

    public static Type d(Type type) {
        return type instanceof GenericArrayType ? ((GenericArrayType) type).getGenericComponentType() : ((Class) type).getComponentType();
    }

    public static Class<?> e(Type type) {
        if (type instanceof Class) {
            return (Class) type;
        }
        if (type instanceof ParameterizedType) {
            Type rawType = ((ParameterizedType) type).getRawType();
            b.b.a.y.a.a(rawType instanceof Class);
            return (Class) rawType;
        }
        if (type instanceof GenericArrayType) {
            return Array.newInstance(e(((GenericArrayType) type).getGenericComponentType()), 0).getClass();
        }
        if (type instanceof TypeVariable) {
            return Object.class;
        }
        if (type instanceof WildcardType) {
            return e(((WildcardType) type).getUpperBounds()[0]);
        }
        throw new IllegalArgumentException("Expected a Class, ParameterizedType, or GenericArrayType, but <" + type + "> is of type " + (type == null ? "null" : type.getClass().getName()));
    }

    public static WildcardType f(Type type) {
        return new c(type instanceof WildcardType ? ((WildcardType) type).getUpperBounds() : new Type[]{type}, f1566a);
    }

    public static WildcardType g(Type type) {
        return new c(new Type[]{Object.class}, type instanceof WildcardType ? ((WildcardType) type).getLowerBounds() : new Type[]{type});
    }

    public static String h(Type type) {
        return type instanceof Class ? ((Class) type).getName() : type.toString();
    }

    public static GenericArrayType a(Type type) {
        return new a(type);
    }

    static boolean a(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    public static boolean a(Type type, Type type2) {
        if (type == type2) {
            return true;
        }
        if (type instanceof Class) {
            return type.equals(type2);
        }
        if (type instanceof ParameterizedType) {
            if (!(type2 instanceof ParameterizedType)) {
                return false;
            }
            ParameterizedType parameterizedType = (ParameterizedType) type;
            ParameterizedType parameterizedType2 = (ParameterizedType) type2;
            return a((Object) parameterizedType.getOwnerType(), (Object) parameterizedType2.getOwnerType()) && parameterizedType.getRawType().equals(parameterizedType2.getRawType()) && Arrays.equals(parameterizedType.getActualTypeArguments(), parameterizedType2.getActualTypeArguments());
        }
        if (type instanceof GenericArrayType) {
            if (type2 instanceof GenericArrayType) {
                return a(((GenericArrayType) type).getGenericComponentType(), ((GenericArrayType) type2).getGenericComponentType());
            }
            return false;
        }
        if (type instanceof WildcardType) {
            if (!(type2 instanceof WildcardType)) {
                return false;
            }
            WildcardType wildcardType = (WildcardType) type;
            WildcardType wildcardType2 = (WildcardType) type2;
            return Arrays.equals(wildcardType.getUpperBounds(), wildcardType2.getUpperBounds()) && Arrays.equals(wildcardType.getLowerBounds(), wildcardType2.getLowerBounds());
        }
        if (!(type instanceof TypeVariable) || !(type2 instanceof TypeVariable)) {
            return false;
        }
        TypeVariable typeVariable = (TypeVariable) type;
        TypeVariable typeVariable2 = (TypeVariable) type2;
        return typeVariable.getGenericDeclaration() == typeVariable2.getGenericDeclaration() && typeVariable.getName().equals(typeVariable2.getName());
    }

    static Type b(Type type, Class<?> cls, Class<?> cls2) {
        if (type instanceof WildcardType) {
            type = ((WildcardType) type).getUpperBounds()[0];
        }
        b.b.a.y.a.a(cls2.isAssignableFrom(cls));
        return a(type, cls, a(type, cls, cls2));
    }

    public static Type[] b(Type type, Class<?> cls) {
        if (type == Properties.class) {
            return new Type[]{String.class, String.class};
        }
        Type typeB = b(type, cls, Map.class);
        return typeB instanceof ParameterizedType ? ((ParameterizedType) typeB).getActualTypeArguments() : new Type[]{Object.class, Object.class};
    }

    static int a(Object obj) {
        if (obj != null) {
            return obj.hashCode();
        }
        return 0;
    }

    static Type a(Type type, Class<?> cls, Class<?> cls2) {
        if (cls2 == cls) {
            return type;
        }
        if (cls2.isInterface()) {
            Class<?>[] interfaces = cls.getInterfaces();
            int length = interfaces.length;
            for (int i = 0; i < length; i++) {
                if (interfaces[i] == cls2) {
                    return cls.getGenericInterfaces()[i];
                }
                if (cls2.isAssignableFrom(interfaces[i])) {
                    return a(cls.getGenericInterfaces()[i], interfaces[i], cls2);
                }
            }
        }
        if (!cls.isInterface()) {
            while (cls != Object.class) {
                Class<? super Object> superclass = cls.getSuperclass();
                if (superclass == cls2) {
                    return cls.getGenericSuperclass();
                }
                if (cls2.isAssignableFrom(superclass)) {
                    return a(cls.getGenericSuperclass(), (Class<?>) superclass, cls2);
                }
                cls = superclass;
            }
        }
        return cls2;
    }

    public static Type a(Type type, Class<?> cls) {
        Type typeB = b(type, cls, Collection.class);
        if (typeB instanceof WildcardType) {
            typeB = ((WildcardType) typeB).getUpperBounds()[0];
        }
        if (typeB instanceof ParameterizedType) {
            return ((ParameterizedType) typeB).getActualTypeArguments()[0];
        }
        return Object.class;
    }

    public static Type a(Type type, Class<?> cls, Type type2) {
        return a(type, cls, type2, new HashSet());
    }

    private static Type a(Type type, Class<?> cls, Type type2, Collection<TypeVariable> collection) {
        Type type3;
        WildcardType wildcardType;
        while (type2 instanceof TypeVariable) {
            TypeVariable typeVariable = (TypeVariable) type2;
            if (collection.contains(typeVariable)) {
                return type2;
            }
            collection.add(typeVariable);
            type2 = a(type, cls, (TypeVariable<?>) typeVariable);
            if (type2 == typeVariable) {
                return type2;
            }
        }
        if (type2 instanceof Class) {
            Class cls2 = (Class) type2;
            if (cls2.isArray()) {
                Class<?> componentType = cls2.getComponentType();
                Type typeA = a(type, cls, componentType, collection);
                return componentType == typeA ? cls2 : a(typeA);
            }
        }
        if (type2 instanceof GenericArrayType) {
            GenericArrayType genericArrayType = (GenericArrayType) type2;
            Type genericComponentType = genericArrayType.getGenericComponentType();
            Type typeA2 = a(type, cls, genericComponentType, collection);
            return genericComponentType == typeA2 ? genericArrayType : a(typeA2);
        }
        if (type2 instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type2;
            Type ownerType = parameterizedType.getOwnerType();
            Type typeA3 = a(type, cls, ownerType, collection);
            boolean z = typeA3 != ownerType;
            Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
            int length = actualTypeArguments.length;
            for (int i = 0; i < length; i++) {
                Type typeA4 = a(type, cls, actualTypeArguments[i], collection);
                if (typeA4 != actualTypeArguments[i]) {
                    if (!z) {
                        actualTypeArguments = (Type[]) actualTypeArguments.clone();
                        z = true;
                    }
                    actualTypeArguments[i] = typeA4;
                }
            }
            return z ? a(typeA3, parameterizedType.getRawType(), actualTypeArguments) : parameterizedType;
        }
        if (type2 instanceof WildcardType) {
            wildcardType = (WildcardType) type2;
            Type[] lowerBounds = wildcardType.getLowerBounds();
            Type[] upperBounds = wildcardType.getUpperBounds();
            if (lowerBounds.length == 1) {
                Type typeA5 = a(type, cls, lowerBounds[0], collection);
                if (typeA5 != lowerBounds[0]) {
                    type3 = type2;
                    type3 = wildcardType;
                    return g(typeA5);
                }
            } else if (upperBounds.length == 1) {
                type3 = type2;
                type3 = wildcardType;
                Type typeA6 = a(type, cls, upperBounds[0], collection);
                type3 = wildcardType;
                if (typeA6 != upperBounds[0]) {
                    return f(typeA6);
                }
            }
        }
        type3 = type2;
        type3 = wildcardType;
        type3 = type2;
        type3 = wildcardType;
        type3 = type2;
        return type3;
    }

    static Type a(Type type, Class<?> cls, TypeVariable<?> typeVariable) {
        Class<?> clsA = a(typeVariable);
        if (clsA == null) {
            return typeVariable;
        }
        Type typeA = a(type, cls, clsA);
        if (!(typeA instanceof ParameterizedType)) {
            return typeVariable;
        }
        return ((ParameterizedType) typeA).getActualTypeArguments()[a((Object[]) clsA.getTypeParameters(), (Object) typeVariable)];
    }

    private static int a(Object[] objArr, Object obj) {
        int length = objArr.length;
        for (int i = 0; i < length; i++) {
            if (obj.equals(objArr[i])) {
                return i;
            }
        }
        throw new NoSuchElementException();
    }

    private static Class<?> a(TypeVariable<?> typeVariable) {
        GenericDeclaration genericDeclaration = typeVariable.getGenericDeclaration();
        if (genericDeclaration instanceof Class) {
            return (Class) genericDeclaration;
        }
        return null;
    }
}
