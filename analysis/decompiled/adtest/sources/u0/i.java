package u0;

import java.io.EOFException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import v0.b1;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public abstract class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Type[] f2254a = new Type[0];

    public static Type a(Type type) {
        if (type instanceof Class) {
            Class cls = (Class) type;
            return cls.isArray() ? new f(a(cls.getComponentType())) : cls;
        }
        if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            return new g(parameterizedType.getOwnerType(), (Class) parameterizedType.getRawType(), parameterizedType.getActualTypeArguments());
        }
        if (type instanceof GenericArrayType) {
            return new f(((GenericArrayType) type).getGenericComponentType());
        }
        if (!(type instanceof WildcardType)) {
            return type;
        }
        WildcardType wildcardType = (WildcardType) type;
        return new h(wildcardType.getUpperBounds(), wildcardType.getLowerBounds());
    }

    public static void b(Type type) {
        if ((type instanceof Class) && ((Class) type).isPrimitive()) {
            throw new IllegalArgumentException("Primitive type is not allowed");
        }
    }

    public static void c(String str) {
        if (str.length() <= 10000) {
            return;
        }
        throw new NumberFormatException("Number string too large: " + str.substring(0, 30) + "...");
    }

    public static boolean d(Type type, Type type2) {
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
            return Objects.equals(parameterizedType.getOwnerType(), parameterizedType2.getOwnerType()) && parameterizedType.getRawType().equals(parameterizedType2.getRawType()) && Arrays.equals(parameterizedType.getActualTypeArguments(), parameterizedType2.getActualTypeArguments());
        }
        if (type instanceof GenericArrayType) {
            if (type2 instanceof GenericArrayType) {
                return d(((GenericArrayType) type).getGenericComponentType(), ((GenericArrayType) type2).getGenericComponentType());
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
        return Objects.equals(typeVariable.getGenericDeclaration(), typeVariable2.getGenericDeclaration()) && typeVariable.getName().equals(typeVariable2.getName());
    }

    public static void e(List list) {
        Iterator it = list.iterator();
        if (it.hasNext()) {
            it.next().getClass();
            throw new ClassCastException();
        }
    }

    public static Type f(Type type, Class cls, Class cls2) {
        if (cls2 == cls) {
            return type;
        }
        if (cls2.isInterface()) {
            Class<?>[] interfaces = cls.getInterfaces();
            int length = interfaces.length;
            for (int i4 = 0; i4 < length; i4++) {
                Class<?> cls3 = interfaces[i4];
                if (cls3 == cls2) {
                    return cls.getGenericInterfaces()[i4];
                }
                if (cls2.isAssignableFrom(cls3)) {
                    return f(cls.getGenericInterfaces()[i4], interfaces[i4], cls2);
                }
            }
        }
        if (!cls.isInterface()) {
            while (cls != Object.class) {
                Class<?> superclass = cls.getSuperclass();
                if (superclass == cls2) {
                    return cls.getGenericSuperclass();
                }
                if (cls2.isAssignableFrom(superclass)) {
                    return f(cls.getGenericSuperclass(), superclass, cls2);
                }
                cls = superclass;
            }
        }
        return cls2;
    }

    public static Class g(Type type) {
        if (type instanceof Class) {
            return (Class) type;
        }
        if (type instanceof ParameterizedType) {
            return (Class) ((ParameterizedType) type).getRawType();
        }
        if (type instanceof GenericArrayType) {
            return Array.newInstance((Class<?>) g(((GenericArrayType) type).getGenericComponentType()), 0).getClass();
        }
        if (type instanceof TypeVariable) {
            return Object.class;
        }
        if (type instanceof WildcardType) {
            return g(((WildcardType) type).getUpperBounds()[0]);
        }
        throw new IllegalArgumentException("Expected a Class, ParameterizedType, or GenericArrayType, but <" + type + "> is of type " + (type == null ? "null" : type.getClass().getName()));
    }

    public static Type h(Type type, Class cls, Class cls2) {
        if (type instanceof WildcardType) {
            type = ((WildcardType) type).getUpperBounds()[0];
        }
        if (cls2.isAssignableFrom(cls)) {
            return k(type, cls, f(type, cls, cls2), new HashMap());
        }
        throw new IllegalArgumentException(cls + " is not the same as or a subtype of " + cls2);
    }

    public static s0.q i(a1.b bVar) {
        boolean z3;
        try {
            try {
                bVar.f0();
                z3 = false;
                try {
                    return (s0.q) b1.f2448z.b(bVar);
                } catch (EOFException e4) {
                    e = e4;
                    if (z3) {
                        return s0.s.f2134d;
                    }
                    throw new s0.r(e);
                }
            } catch (EOFException e5) {
                e = e5;
                z3 = true;
            }
        } catch (a1.e e6) {
            throw new s0.r(e6);
        } catch (IOException e7) {
            throw new s0.r(e7);
        } catch (NumberFormatException e8) {
            throw new s0.r(e8);
        }
    }

    public static BigDecimal j(String str) {
        c(str);
        BigDecimal bigDecimal = new BigDecimal(str);
        if (Math.abs(bigDecimal.scale()) < 10000) {
            return bigDecimal;
        }
        throw new NumberFormatException("Number has unsupported scale: ".concat(str));
    }

    /* JADX WARN: Code duplicated, block: B:29:0x0056  */
    /* JADX WARN: Code duplicated, block: B:41:0x0081  */
    /* JADX WARN: Code duplicated, block: B:43:0x0085  */
    /* JADX WARN: Code duplicated, block: B:46:0x0097  */
    /* JADX WARN: Code duplicated, block: B:47:0x009d  */
    /* JADX WARN: Code duplicated, block: B:49:0x00a2  */
    /* JADX WARN: Code duplicated, block: B:51:0x00b9  */
    /* JADX WARN: Code duplicated, block: B:53:0x00c7 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:54:0x00c9  */
    /* JADX WARN: Code duplicated, block: B:60:0x00e6  */
    /* JADX WARN: Code duplicated, block: B:62:0x00ea  */
    /* JADX WARN: Code duplicated, block: B:64:0x00f7  */
    /* JADX WARN: Code duplicated, block: B:66:0x0101  */
    /* JADX WARN: Code duplicated, block: B:68:0x0105  */
    /* JADX WARN: Code duplicated, block: B:69:0x010c  */
    /* JADX WARN: Code duplicated, block: B:71:0x011d  */
    /* JADX WARN: Code duplicated, block: B:73:0x0120  */
    /* JADX WARN: Code duplicated, block: B:77:0x012a  */
    /* JADX WARN: Code duplicated, block: B:79:0x012e  */
    /* JADX WARN: Code duplicated, block: B:80:0x0135  */
    /* JADX WARN: Code duplicated, block: B:99:0x00d3 A[SYNTHETIC] */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v12 */
    /* JADX WARN: Type inference failed for: r12v0, types: [java.lang.reflect.Type] */
    /* JADX WARN: Type inference failed for: r12v1, types: [java.lang.reflect.Type] */
    /* JADX WARN: Type inference failed for: r12v10, types: [java.lang.Object, java.lang.reflect.Type] */
    /* JADX WARN: Type inference failed for: r12v14 */
    /* JADX WARN: Type inference failed for: r12v15 */
    /* JADX WARN: Type inference failed for: r12v17, types: [java.lang.reflect.Type[]] */
    /* JADX WARN: Type inference failed for: r12v18 */
    /* JADX WARN: Type inference failed for: r12v2, types: [java.lang.reflect.WildcardType] */
    /* JADX WARN: Type inference failed for: r12v3, types: [u0.h] */
    /* JADX WARN: Type inference failed for: r12v4, types: [u0.h] */
    /* JADX WARN: Type inference failed for: r12v5, types: [java.lang.reflect.ParameterizedType] */
    /* JADX WARN: Type inference failed for: r12v6, types: [java.lang.reflect.GenericArrayType] */
    /* JADX WARN: Type inference failed for: r12v7 */
    /* JADX WARN: Type inference failed for: r12v9 */
    /* JADX WARN: Type inference failed for: r13v0, types: [java.util.HashMap] */
    /* JADX WARN: Type inference failed for: r2v3 */
    public static Type k(Type type, Class cls, Type type2, HashMap map) {
        Type[] lowerBounds;
        Type[] upperBounds;
        Type typeK;
        Type[] upperBounds2;
        Type typeK2;
        Type[] lowerBounds2;
        boolean zEquals;
        int length;
        Type[] typeArr;
        boolean z3;
        Type gVar;
        Type typeK3;
        Type genericComponentType;
        Type typeK4;
        TypeVariable typeVariable;
        TypeVariable typeVariable2 = null;
        do {
            int i4 = 0;
            if (!(type2 instanceof TypeVariable)) {
                if (!(type2 instanceof Class)) {
                    if (type2 instanceof GenericArrayType) {
                        if (type2 instanceof ParameterizedType) {
                            if (type2 instanceof WildcardType) {
                                break;
                            }
                            type2 = (WildcardType) type2;
                            lowerBounds = type2.getLowerBounds();
                            upperBounds = type2.getUpperBounds();
                            if (lowerBounds.length == 1) {
                                if (upperBounds.length == 1) {
                                    break;
                                }
                                typeK = k(type, cls, upperBounds[0], map);
                                if (typeK != upperBounds[0]) {
                                    break;
                                }
                                if (typeK instanceof WildcardType) {
                                    upperBounds2 = ((WildcardType) typeK).getUpperBounds();
                                } else {
                                    upperBounds2 = new Type[]{typeK};
                                }
                                type2 = new h(upperBounds2, f2254a);
                                break;
                            }
                            typeK2 = k(type, cls, lowerBounds[0], map);
                            if (typeK2 != lowerBounds[0]) {
                                break;
                            }
                            if (typeK2 instanceof WildcardType) {
                                lowerBounds2 = ((WildcardType) typeK2).getLowerBounds();
                            } else {
                                lowerBounds2 = new Type[]{typeK2};
                            }
                            type2 = new h(new Type[]{Object.class}, lowerBounds2);
                            break;
                        }
                        type2 = (ParameterizedType) type2;
                        Type ownerType = type2.getOwnerType();
                        Type typeK5 = k(type, cls, ownerType, map);
                        zEquals = Objects.equals(typeK5, ownerType);
                        Type[] actualTypeArguments = type2.getActualTypeArguments();
                        length = actualTypeArguments.length;
                        typeArr = actualTypeArguments;
                        z3 = false;
                        while (i4 < length) {
                            typeK3 = k(type, cls, typeArr[i4], map);
                            if (Objects.equals(typeK3, typeArr[i4])) {
                                if (!z3) {
                                    typeArr = (Type[]) typeArr.clone();
                                    z3 = true;
                                }
                                typeArr[i4] = typeK3;
                            }
                            i4++;
                        }
                        if (!zEquals) {
                        }
                        gVar = new g(typeK5, (Class) type2.getRawType(), typeArr);
                        type2 = gVar;
                        break;
                    }
                    type2 = (GenericArrayType) type2;
                    genericComponentType = type2.getGenericComponentType();
                    typeK4 = k(type, cls, genericComponentType, map);
                    if (Objects.equals(genericComponentType, typeK4)) {
                        gVar = new f(typeK4);
                        type2 = gVar;
                        break;
                    }
                    break;
                }
                Class cls2 = (Class) type2;
                if (!cls2.isArray()) {
                    if (type2 instanceof GenericArrayType) {
                        if (type2 instanceof ParameterizedType) {
                            if (type2 instanceof WildcardType) {
                                break;
                            }
                            type2 = (WildcardType) type2;
                            lowerBounds = type2.getLowerBounds();
                            upperBounds = type2.getUpperBounds();
                            if (lowerBounds.length == 1) {
                                if (upperBounds.length == 1) {
                                    break;
                                }
                                typeK = k(type, cls, upperBounds[0], map);
                                if (typeK != upperBounds[0]) {
                                    break;
                                }
                                if (typeK instanceof WildcardType) {
                                    upperBounds2 = ((WildcardType) typeK).getUpperBounds();
                                } else {
                                    upperBounds2 = new Type[]{typeK};
                                }
                                type2 = new h(upperBounds2, f2254a);
                                break;
                            }
                            typeK2 = k(type, cls, lowerBounds[0], map);
                            if (typeK2 != lowerBounds[0]) {
                                break;
                            }
                            if (typeK2 instanceof WildcardType) {
                                lowerBounds2 = ((WildcardType) typeK2).getLowerBounds();
                            } else {
                                lowerBounds2 = new Type[]{typeK2};
                            }
                            type2 = new h(new Type[]{Object.class}, lowerBounds2);
                            break;
                        }
                        type2 = (ParameterizedType) type2;
                        Type ownerType2 = type2.getOwnerType();
                        Type typeK6 = k(type, cls, ownerType2, map);
                        zEquals = Objects.equals(typeK6, ownerType2);
                        Type[] actualTypeArguments2 = type2.getActualTypeArguments();
                        length = actualTypeArguments2.length;
                        typeArr = actualTypeArguments2;
                        z3 = false;
                        while (i4 < length) {
                            typeK3 = k(type, cls, typeArr[i4], map);
                            if (Objects.equals(typeK3, typeArr[i4])) {
                                if (!z3) {
                                    typeArr = (Type[]) typeArr.clone();
                                    z3 = true;
                                }
                                typeArr[i4] = typeK3;
                            }
                            i4++;
                        }
                        if (!zEquals && !z3) {
                            break;
                        }
                        gVar = new g(typeK6, (Class) type2.getRawType(), typeArr);
                        type2 = gVar;
                        break;
                    }
                    type2 = (GenericArrayType) type2;
                    genericComponentType = type2.getGenericComponentType();
                    typeK4 = k(type, cls, genericComponentType, map);
                    if (Objects.equals(genericComponentType, typeK4)) {
                        break;
                    }
                    gVar = new f(typeK4);
                    type2 = gVar;
                    break;
                }
                Class<?> componentType = cls2.getComponentType();
                Type typeK7 = k(type, cls, componentType, map);
                if (!Objects.equals(componentType, typeK7)) {
                    gVar = new f(typeK7);
                    type2 = gVar;
                    break;
                }
                type2 = cls2;
                break;
            }
            typeVariable = (TypeVariable) type2;
            Type type3 = (Type) map.get(typeVariable);
            Class cls3 = Void.TYPE;
            if (type3 != null) {
                return type3 == cls3 ? type2 : type3;
            }
            map.put(typeVariable, cls3);
            if (typeVariable2 == null) {
                typeVariable2 = typeVariable;
            }
            GenericDeclaration genericDeclaration = typeVariable.getGenericDeclaration();
            Class cls4 = genericDeclaration instanceof Class ? (Class) genericDeclaration : null;
            if (cls4 == null) {
                type2 = typeVariable;
            } else {
                Type typeF = f(type, cls, cls4);
                if (typeF instanceof ParameterizedType) {
                    TypeVariable[] typeParameters = cls4.getTypeParameters();
                    int length2 = typeParameters.length;
                    while (true) {
                        if (i4 >= length2) {
                            throw new NoSuchElementException();
                        }
                        if (typeVariable.equals(typeParameters[i4])) {
                            type2 = ((ParameterizedType) typeF).getActualTypeArguments()[i4];
                            break;
                        }
                        i4++;
                    }
                } else {
                    type2 = typeVariable;
                }
            }
        } while (type2 != typeVariable);
        if (typeVariable2 != null) {
            map.put(typeVariable2, type2);
        }
        return type2;
    }

    public static String l(Type type) {
        return type instanceof Class ? ((Class) type).getName() : type.toString();
    }

    public static Class m(Class cls) {
        if (cls == Integer.TYPE) {
            return Integer.class;
        }
        if (cls == Float.TYPE) {
            return Float.class;
        }
        if (cls == Byte.TYPE) {
            return Byte.class;
        }
        if (cls == Double.TYPE) {
            return Double.class;
        }
        if (cls == Long.TYPE) {
            return Long.class;
        }
        if (cls == Character.TYPE) {
            return Character.class;
        }
        if (cls == Boolean.TYPE) {
            return Boolean.class;
        }
        if (cls == Short.TYPE) {
            return Short.class;
        }
        return cls == Void.TYPE ? Void.class : cls;
    }
}
