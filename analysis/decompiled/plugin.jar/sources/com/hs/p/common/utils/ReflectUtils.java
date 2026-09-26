package com.hs.p.common.utils;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: /Users/ruben/projector-dump/downloads/plugin.jar */
public class ReflectUtils {
    public static Object getField(Class<?> cls, Object obj, String str) throws Exception {
        Field declaredField = cls.getDeclaredField(str);
        boolean zIsAccessible = declaredField.isAccessible();
        try {
            declaredField.setAccessible(true);
            return declaredField.get(obj);
        } finally {
            resetAccessible(declaredField, true, zIsAccessible);
        }
    }

    private static Class[] getParameterTypes(Object... objArr) {
        if (objArr == null) {
            return null;
        }
        Class[] clsArr = new Class[objArr.length];
        for (int i = 0; i < objArr.length; i++) {
            clsArr[i] = objArr[i].getClass();
        }
        return clsArr;
    }

    public static Object invoke(Class<?> cls, Object obj, String str, Class[] clsArr, Object... objArr) throws Exception {
        Method declaredMethod = cls.getDeclaredMethod(str, clsArr);
        boolean zIsAccessible = declaredMethod.isAccessible();
        try {
            declaredMethod.setAccessible(true);
            return declaredMethod.invoke(obj, objArr);
        } finally {
            resetAccessible(declaredMethod, true, zIsAccessible);
        }
    }

    private static void resetAccessible(AccessibleObject accessibleObject, boolean z, boolean z2) {
    }

    public static void setField(Class<?> cls, Object obj, String str, Object obj2) throws Exception {
        Field declaredField = cls.getDeclaredField(str);
        boolean zIsAccessible = declaredField.isAccessible();
        try {
            declaredField.setAccessible(true);
            declaredField.set(obj, obj2);
        } finally {
            resetAccessible(declaredField, true, zIsAccessible);
        }
    }

    public static Object getField(String str, Object obj, String str2) throws Exception {
        return getField(Class.forName(str), obj, str2);
    }

    public static Object invoke(Class<?> cls, Object obj, String str, Object... objArr) throws Exception {
        return invoke(cls, obj, str, getParameterTypes(objArr), objArr);
    }

    public static void setField(String str, Object obj, String str2, Object obj2) throws Exception {
        setField(Class.forName(str), obj, str2, obj2);
    }

    public static Object invoke(String str, Object obj, String str2, Class[] clsArr, Object... objArr) throws Exception {
        return invoke(Class.forName(str), obj, str2, clsArr, objArr);
    }

    public static Object invoke(String str, Object obj, String str2, Object... objArr) throws Exception {
        return invoke(Class.forName(str), obj, str2, objArr);
    }
}
