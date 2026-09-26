package com.blankj.utilcode.util;

import b.b.a.f;
import java.lang.reflect.Type;

/* JADX INFO: loaded from: classes.dex */
public final class CloneUtils {
    private CloneUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static <T> T deepClone(T t, Type type) {
        try {
            f fVar = new f();
            return (T) fVar.a(fVar.a(t), type);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }
}
