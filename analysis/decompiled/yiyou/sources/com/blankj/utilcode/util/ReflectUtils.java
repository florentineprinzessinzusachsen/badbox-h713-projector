package com.blankj.utilcode.util;

import com.umeng.commonsdk.proguard.e;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class ReflectUtils {
    private final Object object;
    private final Class<?> type;

    private static class NULL {
        private NULL() {
        }
    }

    public static class ReflectException extends RuntimeException {
        private static final long serialVersionUID = 858774075258496016L;

        public ReflectException(String str) {
            super(str);
        }

        public ReflectException(String str, Throwable th) {
            super(str, th);
        }

        public ReflectException(Throwable th) {
            super(th);
        }
    }

    private ReflectUtils(Class<?> cls) {
        this(cls, cls);
    }

    private <T extends AccessibleObject> T accessible(T t) {
        if (t == null) {
            return null;
        }
        if (t instanceof Member) {
            Member member = (Member) t;
            if (Modifier.isPublic(member.getModifiers()) && Modifier.isPublic(member.getDeclaringClass().getModifiers())) {
                return t;
            }
        }
        if (!t.isAccessible()) {
            t.setAccessible(true);
        }
        return t;
    }

    private Method exactMethod(String str, Class<?>[] clsArr) throws NoSuchMethodException {
        Class<?> clsType = type();
        try {
            return clsType.getMethod(str, clsArr);
        } catch (NoSuchMethodException unused) {
            do {
                try {
                    return clsType.getDeclaredMethod(str, clsArr);
                } catch (NoSuchMethodException unused2) {
                    clsType = clsType.getSuperclass();
                }
            } while (clsType != null);
            throw new NoSuchMethodException();
        }
    }

    private static Class<?> forName(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e2) {
            throw new ReflectException(e2);
        }
    }

    private Field getAccessibleField(String str) {
        Class<?> clsType = type();
        try {
            return (Field) accessible(clsType.getField(str));
        } catch (NoSuchFieldException e2) {
            do {
                try {
                    return (Field) accessible(clsType.getDeclaredField(str));
                } catch (NoSuchFieldException unused) {
                    clsType = clsType.getSuperclass();
                    if (clsType == null) {
                        throw new ReflectException(e2);
                    }
                }
            } while (clsType == null);
            throw new ReflectException(e2);
        }
    }

    private Class<?>[] getArgsType(Object... objArr) {
        if (objArr == null) {
            return new Class[0];
        }
        Class<?>[] clsArr = new Class[objArr.length];
        for (int i = 0; i < objArr.length; i++) {
            Object obj = objArr[i];
            clsArr[i] = obj == null ? NULL.class : obj.getClass();
        }
        return clsArr;
    }

    private Field getField(String str) throws IllegalAccessException {
        Field accessibleField = getAccessibleField(str);
        if ((accessibleField.getModifiers() & 16) == 16) {
            try {
                Field declaredField = Field.class.getDeclaredField("modifiers");
                declaredField.setAccessible(true);
                declaredField.setInt(accessibleField, accessibleField.getModifiers() & (-17));
            } catch (NoSuchFieldException unused) {
            }
        }
        return accessibleField;
    }

    private boolean isSimilarSignature(Method method, String str, Class<?>[] clsArr) {
        return method.getName().equals(str) && match(method.getParameterTypes(), clsArr);
    }

    private boolean match(Class<?>[] clsArr, Class<?>[] clsArr2) {
        if (clsArr.length != clsArr2.length) {
            return false;
        }
        for (int i = 0; i < clsArr2.length; i++) {
            if (clsArr2[i] != NULL.class && !wrapper(clsArr[i]).isAssignableFrom(wrapper(clsArr2[i]))) {
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String property(String str) {
        int length = str.length();
        if (length == 0) {
            return "";
        }
        if (length == 1) {
            return str.toLowerCase();
        }
        return str.substring(0, 1).toLowerCase() + str.substring(1);
    }

    public static ReflectUtils reflect(String str) {
        return reflect(forName(str));
    }

    private Method similarMethod(String str, Class<?>[] clsArr) throws NoSuchMethodException {
        Class<?> clsType = type();
        ArrayList arrayList = new ArrayList();
        for (Method method : clsType.getMethods()) {
            if (isSimilarSignature(method, str, clsArr)) {
                arrayList.add(method);
            }
        }
        if (!arrayList.isEmpty()) {
            sortMethods(arrayList);
            return arrayList.get(0);
        }
        do {
            for (Method method2 : clsType.getDeclaredMethods()) {
                if (isSimilarSignature(method2, str, clsArr)) {
                    arrayList.add(method2);
                }
            }
            if (!arrayList.isEmpty()) {
                sortMethods(arrayList);
                return arrayList.get(0);
            }
            clsType = clsType.getSuperclass();
        } while (clsType != null);
        throw new NoSuchMethodException("No similar method " + str + " with params " + Arrays.toString(clsArr) + " could be found on type " + type() + ".");
    }

    private void sortConstructors(List<Constructor<?>> list) {
        Collections.sort(list, new Comparator<Constructor<?>>() { // from class: com.blankj.utilcode.util.ReflectUtils.1
            @Override // java.util.Comparator
            public int compare(Constructor<?> constructor, Constructor<?> constructor2) {
                Class<?>[] parameterTypes = constructor.getParameterTypes();
                Class<?>[] parameterTypes2 = constructor2.getParameterTypes();
                int length = parameterTypes.length;
                for (int i = 0; i < length; i++) {
                    if (!parameterTypes[i].equals(parameterTypes2[i])) {
                        return ReflectUtils.this.wrapper(parameterTypes[i]).isAssignableFrom(ReflectUtils.this.wrapper(parameterTypes2[i])) ? 1 : -1;
                    }
                }
                return 0;
            }
        });
    }

    private void sortMethods(List<Method> list) {
        Collections.sort(list, new Comparator<Method>() { // from class: com.blankj.utilcode.util.ReflectUtils.2
            @Override // java.util.Comparator
            public int compare(Method method, Method method2) {
                Class<?>[] parameterTypes = method.getParameterTypes();
                Class<?>[] parameterTypes2 = method2.getParameterTypes();
                int length = parameterTypes.length;
                for (int i = 0; i < length; i++) {
                    if (!parameterTypes[i].equals(parameterTypes2[i])) {
                        return ReflectUtils.this.wrapper(parameterTypes[i]).isAssignableFrom(ReflectUtils.this.wrapper(parameterTypes2[i])) ? 1 : -1;
                    }
                }
                return 0;
            }
        });
    }

    private Class<?> type() {
        return this.type;
    }

    private Object unwrap(Object obj) {
        return obj instanceof ReflectUtils ? ((ReflectUtils) obj).get() : obj;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Class<?> wrapper(Class<?> cls) {
        if (cls == null) {
            return null;
        }
        if (!cls.isPrimitive()) {
            return cls;
        }
        if (Boolean.TYPE == cls) {
            return Boolean.class;
        }
        if (Integer.TYPE == cls) {
            return Integer.class;
        }
        if (Long.TYPE == cls) {
            return Long.class;
        }
        if (Short.TYPE == cls) {
            return Short.class;
        }
        if (Byte.TYPE == cls) {
            return Byte.class;
        }
        if (Double.TYPE == cls) {
            return Double.class;
        }
        if (Float.TYPE == cls) {
            return Float.class;
        }
        if (Character.TYPE == cls) {
            return Character.class;
        }
        return Void.TYPE == cls ? Void.class : cls;
    }

    public boolean equals(Object obj) {
        return (obj instanceof ReflectUtils) && this.object.equals(((ReflectUtils) obj).get());
    }

    public ReflectUtils field(String str) {
        try {
            Field field = getField(str);
            return new ReflectUtils(field.getType(), field.get(this.object));
        } catch (IllegalAccessException e2) {
            throw new ReflectException(e2);
        }
    }

    public <T> T get() {
        return (T) this.object;
    }

    public int hashCode() {
        return this.object.hashCode();
    }

    public ReflectUtils method(String str) {
        return method(str, new Object[0]);
    }

    public ReflectUtils newInstance() {
        return newInstance(new Object[0]);
    }

    public <P> P proxy(Class<P> cls) {
        final boolean z = this.object instanceof Map;
        return (P) Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, new InvocationHandler() { // from class: com.blankj.utilcode.util.ReflectUtils.3
            @Override // java.lang.reflect.InvocationHandler
            public Object invoke(Object obj, Method method, Object[] objArr) {
                String name = method.getName();
                try {
                    return ReflectUtils.reflect(ReflectUtils.this.object).method(name, objArr).get();
                } catch (ReflectException e2) {
                    if (z) {
                        Map map = (Map) ReflectUtils.this.object;
                        int length = objArr == null ? 0 : objArr.length;
                        if (length == 0 && name.startsWith("get")) {
                            return map.get(ReflectUtils.property(name.substring(3)));
                        }
                        if (length == 0 && name.startsWith(e.ac)) {
                            return map.get(ReflectUtils.property(name.substring(2)));
                        }
                        if (length == 1 && name.startsWith("set")) {
                            map.put(ReflectUtils.property(name.substring(3)), objArr[0]);
                            return null;
                        }
                    }
                    throw e2;
                }
            }
        });
    }

    public String toString() {
        return this.object.toString();
    }

    private ReflectUtils(Class<?> cls, Object obj) {
        this.type = cls;
        this.object = obj;
    }

    public static ReflectUtils reflect(String str, ClassLoader classLoader) {
        return reflect(forName(str, classLoader));
    }

    public ReflectUtils method(String str, Object... objArr) {
        Class<?>[] argsType = getArgsType(objArr);
        try {
            try {
                return method(exactMethod(str, argsType), this.object, objArr);
            } catch (NoSuchMethodException e2) {
                throw new ReflectException(e2);
            }
        } catch (NoSuchMethodException unused) {
            return method(similarMethod(str, argsType), this.object, objArr);
        }
    }

    public ReflectUtils newInstance(Object... objArr) {
        Class<?>[] argsType = getArgsType(objArr);
        try {
            return newInstance(type().getDeclaredConstructor(argsType), objArr);
        } catch (NoSuchMethodException e2) {
            ArrayList arrayList = new ArrayList();
            for (Constructor<?> constructor : type().getDeclaredConstructors()) {
                if (match(constructor.getParameterTypes(), argsType)) {
                    arrayList.add(constructor);
                }
            }
            if (arrayList.isEmpty()) {
                throw new ReflectException(e2);
            }
            sortConstructors(arrayList);
            return newInstance(arrayList.get(0), objArr);
        }
    }

    private static Class<?> forName(String str, ClassLoader classLoader) {
        try {
            return Class.forName(str, true, classLoader);
        } catch (ClassNotFoundException e2) {
            throw new ReflectException(e2);
        }
    }

    public static ReflectUtils reflect(Class<?> cls) {
        return new ReflectUtils(cls);
    }

    public static ReflectUtils reflect(Object obj) {
        return new ReflectUtils(obj == null ? Object.class : obj.getClass(), obj);
    }

    public ReflectUtils field(String str, Object obj) {
        try {
            getField(str).set(this.object, unwrap(obj));
            return this;
        } catch (Exception e2) {
            throw new ReflectException(e2);
        }
    }

    private ReflectUtils method(Method method, Object obj, Object... objArr) {
        try {
            accessible(method);
            if (method.getReturnType() == Void.TYPE) {
                method.invoke(obj, objArr);
                return reflect(obj);
            }
            return reflect(method.invoke(obj, objArr));
        } catch (Exception e2) {
            throw new ReflectException(e2);
        }
    }

    private ReflectUtils newInstance(Constructor<?> constructor, Object... objArr) {
        try {
            return new ReflectUtils(constructor.getDeclaringClass(), ((Constructor) accessible(constructor)).newInstance(objArr));
        } catch (Exception e2) {
            throw new ReflectException(e2);
        }
    }
}
