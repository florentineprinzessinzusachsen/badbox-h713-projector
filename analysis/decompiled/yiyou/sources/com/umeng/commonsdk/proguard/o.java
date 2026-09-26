package com.umeng.commonsdk.proguard;

import java.lang.reflect.InvocationTargetException;

/* JADX INFO: compiled from: TEnumHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class o {
    public static n a(Class<? extends n> cls, int i) {
        try {
            return (n) cls.getMethod("findByValue", Integer.TYPE).invoke(null, Integer.valueOf(i));
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
            return null;
        }
    }
}
