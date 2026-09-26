package com.rk_itvui.utils;

import android.content.pm.IPackageStatsObserver;
import android.content.pm.PackageManager;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.UndeclaredThrowableException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public abstract class ReflectionUtils {
    public static FieldFilter COPYABLE_FIELDS = new FieldFilter() { // from class: com.rk_itvui.utils.ReflectionUtils.4
        @Override // com.rk_itvui.utils.ReflectionUtils.FieldFilter
        public boolean matches(Field field) {
            return (Modifier.isStatic(field.getModifiers()) || Modifier.isFinal(field.getModifiers())) ? false : true;
        }
    };
    public static MethodFilter NON_BRIDGED_METHODS = new MethodFilter() { // from class: com.rk_itvui.utils.ReflectionUtils.5
        @Override // com.rk_itvui.utils.ReflectionUtils.MethodFilter
        public boolean matches(Method method) {
            return !method.isBridge();
        }
    };
    public static MethodFilter USER_DECLARED_METHODS = new MethodFilter() { // from class: com.rk_itvui.utils.ReflectionUtils.6
        @Override // com.rk_itvui.utils.ReflectionUtils.MethodFilter
        public boolean matches(Method method) {
            return (method.isBridge() || method.getDeclaringClass() == Object.class) ? false : true;
        }
    };

    public interface FieldCallback {
        void doWith(Field field) throws IllegalAccessException, IllegalArgumentException;
    }

    public interface FieldFilter {
        boolean matches(Field field);
    }

    public interface MethodCallback {
        void doWith(Method method) throws IllegalAccessException, IllegalArgumentException;
    }

    public interface MethodFilter {
        boolean matches(Method method);
    }

    public static Object invokeMethod(Method method, Object obj) {
        return invokeMethod(method, obj, new Object[0]);
    }

    public static Object invokeMethod(Method method, Object obj, Object... objArr) {
        try {
            return method.invoke(obj, objArr);
        } catch (Exception e) {
            handleReflectionException(e);
            throw new IllegalStateException("Should never get here");
        }
    }

    public static Object invokeJdbcMethod(Method method, Object obj) throws SQLException {
        return invokeJdbcMethod(method, obj, new Object[0]);
    }

    public static Object invokeJdbcMethod(Method method, Object obj, Object... objArr) throws SQLException {
        try {
            return method.invoke(obj, objArr);
        } catch (IllegalAccessException e) {
            handleReflectionException(e);
            throw new IllegalStateException("Should never get here");
        } catch (InvocationTargetException e2) {
            if (e2.getTargetException() instanceof SQLException) {
                throw ((SQLException) e2.getTargetException());
            }
            handleInvocationTargetException(e2);
            throw new IllegalStateException("Should never get here");
        }
    }

    public static void handleReflectionException(Exception exc) {
        if (exc instanceof NoSuchMethodException) {
            throw new IllegalStateException("Method not found: " + exc.getMessage());
        }
        if (exc instanceof IllegalAccessException) {
            throw new IllegalStateException("Could not access method: " + exc.getMessage());
        }
        if (exc instanceof InvocationTargetException) {
            handleInvocationTargetException((InvocationTargetException) exc);
        }
        if (exc instanceof RuntimeException) {
            throw ((RuntimeException) exc);
        }
        throw new UndeclaredThrowableException(exc);
    }

    public static void handleInvocationTargetException(InvocationTargetException invocationTargetException) {
        rethrowRuntimeException(invocationTargetException.getTargetException());
    }

    public static void rethrowRuntimeException(Throwable th) {
        if (th instanceof RuntimeException) {
            throw ((RuntimeException) th);
        }
        if (th instanceof Error) {
            throw ((Error) th);
        }
        throw new UndeclaredThrowableException(th);
    }

    public static void rethrowException(Throwable th) throws Exception {
        if (th instanceof Exception) {
            throw ((Exception) th);
        }
        if (th instanceof Error) {
            throw ((Error) th);
        }
        throw new UndeclaredThrowableException(th);
    }

    public static boolean declaresException(Method method, Class<?> cls) {
        for (Class<?> cls2 : method.getExceptionTypes()) {
            if (cls2.isAssignableFrom(cls)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isPublicStaticFinal(Field field) {
        int modifiers = field.getModifiers();
        return Modifier.isPublic(modifiers) && Modifier.isStatic(modifiers) && Modifier.isFinal(modifiers);
    }

    public static boolean isEqualsMethod(Method method) {
        if (method == null || !method.getName().equals("equals")) {
            return false;
        }
        Class<?>[] parameterTypes = method.getParameterTypes();
        return parameterTypes.length == 1 && parameterTypes[0] == Object.class;
    }

    public static boolean isHashCodeMethod(Method method) {
        return method != null && method.getName().equals("hashCode") && method.getParameterTypes().length == 0;
    }

    public static boolean isToStringMethod(Method method) {
        return method != null && method.getName().equals("toString") && method.getParameterTypes().length == 0;
    }

    public static boolean isObjectMethod(Method method) {
        try {
            Object.class.getDeclaredMethod(method.getName(), method.getParameterTypes());
            return true;
        } catch (NoSuchMethodException unused) {
            return false;
        } catch (SecurityException unused2) {
            return false;
        }
    }

    public static void makeAccessible(Field field) {
        if ((Modifier.isPublic(field.getModifiers()) && Modifier.isPublic(field.getDeclaringClass().getModifiers()) && !Modifier.isFinal(field.getModifiers())) || field.isAccessible()) {
            return;
        }
        field.setAccessible(true);
    }

    public static void makeAccessible(Method method) {
        if ((Modifier.isPublic(method.getModifiers()) && Modifier.isPublic(method.getDeclaringClass().getModifiers())) || method.isAccessible()) {
            return;
        }
        method.setAccessible(true);
    }

    public static void makeAccessible(Constructor<?> constructor) {
        if ((Modifier.isPublic(constructor.getModifiers()) && Modifier.isPublic(constructor.getDeclaringClass().getModifiers())) || constructor.isAccessible()) {
            return;
        }
        constructor.setAccessible(true);
    }

    public static void doWithMethods(Class<?> cls, MethodCallback methodCallback) throws IllegalArgumentException {
        doWithMethods(cls, methodCallback, null);
    }

    public static void doWithMethods(Class<?> cls, MethodCallback methodCallback, MethodFilter methodFilter) throws IllegalArgumentException {
        for (Method method : cls.getDeclaredMethods()) {
            if (methodFilter == null || methodFilter.matches(method)) {
                try {
                    methodCallback.doWith(method);
                } catch (IllegalAccessException e) {
                    throw new IllegalStateException("Shouldn't be illegal to access method '" + method.getName() + "': " + e);
                }
            }
        }
        if (cls.getSuperclass() != null) {
            doWithMethods(cls.getSuperclass(), methodCallback, methodFilter);
            return;
        }
        if (cls.isInterface()) {
            for (Class<?> cls2 : cls.getInterfaces()) {
                doWithMethods(cls2, methodCallback, methodFilter);
            }
        }
    }

    public static Method[] getAllDeclaredMethods(Class<?> cls) throws IllegalArgumentException {
        final ArrayList arrayList = new ArrayList(32);
        doWithMethods(cls, new MethodCallback() { // from class: com.rk_itvui.utils.ReflectionUtils.1
            @Override // com.rk_itvui.utils.ReflectionUtils.MethodCallback
            public void doWith(Method method) {
                arrayList.add(method);
            }
        });
        return (Method[]) arrayList.toArray(new Method[arrayList.size()]);
    }

    public static Method[] getUniqueDeclaredMethods(Class<?> cls) throws IllegalArgumentException {
        final ArrayList arrayList = new ArrayList(32);
        doWithMethods(cls, new MethodCallback() { // from class: com.rk_itvui.utils.ReflectionUtils.2
            @Override // com.rk_itvui.utils.ReflectionUtils.MethodCallback
            public void doWith(Method method) {
                Method method2;
                Iterator it = arrayList.iterator();
                while (true) {
                    if (it.hasNext()) {
                        method2 = (Method) it.next();
                        if (method.getName().equals(method2.getName()) && Arrays.equals(method.getParameterTypes(), method2.getParameterTypes())) {
                            if (method2.getReturnType() != method.getReturnType() && method2.getReturnType().isAssignableFrom(method.getReturnType())) {
                                break;
                            } else {
                                break;
                            }
                        }
                    }
                    method2 = null;
                    break;
                }
                if (method2 != null) {
                    arrayList.remove(method2);
                }
            }
        });
        return (Method[]) arrayList.toArray(new Method[arrayList.size()]);
    }

    public static void doWithFields(Class<?> cls, FieldCallback fieldCallback) throws IllegalArgumentException {
        doWithFields(cls, fieldCallback, null);
    }

    public static void doWithFields(Class<?> cls, FieldCallback fieldCallback, FieldFilter fieldFilter) throws IllegalArgumentException {
        do {
            for (Field field : cls.getDeclaredFields()) {
                if (fieldFilter == null || fieldFilter.matches(field)) {
                    try {
                        fieldCallback.doWith(field);
                    } catch (IllegalAccessException e) {
                        throw new IllegalStateException("Shouldn't be illegal to access field '" + field.getName() + "': " + e);
                    }
                }
            }
            cls = cls.getSuperclass();
            if (cls == null) {
                return;
            }
        } while (cls != Object.class);
    }

    public static void shallowCopyFieldState(final Object obj, final Object obj2) throws IllegalArgumentException {
        if (obj == null) {
            throw new IllegalArgumentException("Source for field copy cannot be null");
        }
        if (obj2 == null) {
            throw new IllegalArgumentException("Destination for field copy cannot be null");
        }
        if (!obj.getClass().isAssignableFrom(obj2.getClass())) {
            throw new IllegalArgumentException("Destination class [" + obj2.getClass().getName() + "] must be same or subclass as source class [" + obj.getClass().getName() + "]");
        }
        doWithFields(obj.getClass(), new FieldCallback() { // from class: com.rk_itvui.utils.ReflectionUtils.3
            @Override // com.rk_itvui.utils.ReflectionUtils.FieldCallback
            public void doWith(Field field) throws IllegalAccessException, IllegalArgumentException {
                ReflectionUtils.makeAccessible(field);
                field.set(obj2, field.get(obj));
            }
        }, COPYABLE_FIELDS);
    }

    public Object getFieldValue(Object obj, String str) {
        try {
            return obj.getClass().getField(str).get(obj);
        } catch (Exception e) {
            handleReflectionException(e);
            return null;
        }
    }

    public static Object getStaticFieldValue(String str, String str2) {
        try {
            Class<?> cls = Class.forName(str);
            return cls.getField(str2).get(cls);
        } catch (Exception unused) {
            return null;
        }
    }

    public static Object getPrivateFieldValue(Object obj, String str) {
        try {
            Field declaredField = obj.getClass().getDeclaredField(str);
            declaredField.setAccessible(true);
            return declaredField.get(obj);
        } catch (Exception e) {
            handleReflectionException(e);
            return null;
        }
    }

    public static Method getMethod(String str, String str2, Class<?>... clsArr) {
        try {
            return Class.forName(str).getMethod(str2, clsArr);
        } catch (Exception unused) {
            return null;
        }
    }

    public static Object invokeMethod(Object obj, String str, Object... objArr) {
        Class<?>[] clsArr;
        Class<?> cls = obj.getClass();
        if (objArr != null) {
            try {
                clsArr = new Class[objArr.length];
                for (int i = 0; i < objArr.length; i++) {
                    clsArr[i] = objArr[i].getClass();
                }
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        } else {
            clsArr = null;
        }
        return cls.getMethod(str, clsArr).invoke(obj, objArr);
    }

    public static Object invokeMethod(Object obj, String str, Class<?>[] clsArr, Object... objArr) {
        try {
            return obj.getClass().getMethod(str, clsArr).invoke(obj, objArr);
        } catch (Exception unused) {
            return null;
        }
    }

    public static Object invokeMethod(Class<?> cls, String str, Object... objArr) {
        try {
            return invokeMethod(cls.newInstance(), str, objArr);
        } catch (Exception unused) {
            return null;
        }
    }

    public static Object invokeMethod(String str, String str2, Object... objArr) {
        try {
            return invokeMethod(Class.forName(str).newInstance(), str2, objArr);
        } catch (Exception unused) {
            return null;
        }
    }

    public static Object invokeStaticMethod(Class<?> cls, String str, Object... objArr) {
        Class<?>[] clsArr;
        if (objArr != null) {
            try {
                clsArr = new Class[objArr.length];
                for (int i = 0; i < objArr.length; i++) {
                    clsArr[i] = objArr[i].getClass();
                }
            } catch (Exception unused) {
                return null;
            }
        } else {
            clsArr = null;
        }
        return cls.getMethod(str, clsArr).invoke(null, objArr);
    }

    public static Object invokeStaticMethod(String str, String str2, Object... objArr) {
        try {
            return invokeStaticMethod(Class.forName(str), str2, objArr);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Object invokeStaticMethod(String str, String str2, Class<?>[] clsArr, Object... objArr) {
        try {
            return invokeStaticMethod(Class.forName(str), str2, clsArr, objArr);
        } catch (Exception unused) {
            return null;
        }
    }

    public static Object invokeStaticMethod(Class<?> cls, String str, Class<?>[] clsArr, Object... objArr) {
        try {
            return cls.getMethod(str, clsArr).invoke(null, objArr);
        } catch (Exception unused) {
            return null;
        }
    }

    public static void setField(Object obj, String str, Object obj2) {
        try {
            obj.getClass().getField(str).set(obj, obj2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void getCacheSize(PackageManager packageManager, String str, IPackageStatsObserver iPackageStatsObserver) {
        try {
            packageManager.getClass().getDeclaredMethod("getPackageSizeInfo", String.class, Integer.TYPE, IPackageStatsObserver.class).invoke(packageManager, str, 0, iPackageStatsObserver);
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
