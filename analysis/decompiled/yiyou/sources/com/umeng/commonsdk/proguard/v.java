package com.umeng.commonsdk.proguard;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: FieldMetaData.java */
/* JADX INFO: loaded from: classes.dex */
public class v implements Serializable {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static Map<Class<? extends j>, Map<? extends q, v>> f4002d = new HashMap();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f4003a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final byte f4004b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final w f4005c;

    public v(String str, byte b2, w wVar) {
        this.f4003a = str;
        this.f4004b = b2;
        this.f4005c = wVar;
    }

    public static void a(Class<? extends j> cls, Map<? extends q, v> map) {
        f4002d.put(cls, map);
    }

    public static Map<? extends q, v> a(Class<? extends j> cls) {
        if (!f4002d.containsKey(cls)) {
            try {
                cls.newInstance();
            } catch (IllegalAccessException e2) {
                throw new RuntimeException("IllegalAccessException for TBase class: " + cls.getName() + ", message: " + e2.getMessage());
            } catch (InstantiationException e3) {
                throw new RuntimeException("InstantiationException for TBase class: " + cls.getName() + ", message: " + e3.getMessage());
            }
        }
        return f4002d.get(cls);
    }
}
