package com.blankj.utilcode.util;

import b.b.a.f;
import b.b.a.g;
import b.b.a.z.a;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class GsonUtils {
    private static final f GSON = createGson(true);
    private static final f GSON_NO_NULLS = createGson(false);

    private GsonUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    private static f createGson(boolean z) {
        g gVar = new g();
        if (z) {
            gVar.b();
        }
        return gVar.a();
    }

    public static <T> T fromJson(String str, Class<T> cls) {
        return (T) GSON.a(str, (Class) cls);
    }

    public static Type getArrayType(Type type) {
        return a.b(type).b();
    }

    public static f getGson() {
        return getGson(true);
    }

    public static Type getListType(Type type) {
        return a.a(List.class, type).b();
    }

    public static Type getMapType(Type type, Type type2) {
        return a.a(Map.class, type, type2).b();
    }

    public static Type getSetType(Type type) {
        return a.a(Set.class, type).b();
    }

    public static Type getType(Type type, Type... typeArr) {
        return a.a(type, typeArr).b();
    }

    public static String toJson(Object obj) {
        return toJson(obj, true);
    }

    public static <T> T fromJson(String str, Type type) {
        return (T) GSON.a(str, type);
    }

    public static f getGson(boolean z) {
        return z ? GSON_NO_NULLS : GSON;
    }

    public static String toJson(Object obj, boolean z) {
        return (z ? GSON : GSON_NO_NULLS).a(obj);
    }

    public static <T> T fromJson(Reader reader, Class<T> cls) {
        return (T) GSON.a(reader, (Class) cls);
    }

    public static String toJson(Object obj, Type type) {
        return toJson(obj, type, true);
    }

    public static <T> T fromJson(Reader reader, Type type) {
        return (T) GSON.a(reader, type);
    }

    public static String toJson(Object obj, Type type, boolean z) {
        return (z ? GSON : GSON_NO_NULLS).a(obj, type);
    }
}
