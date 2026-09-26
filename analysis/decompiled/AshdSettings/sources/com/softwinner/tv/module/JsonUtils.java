package com.softwinner.tv.module;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/* JADX INFO: loaded from: classes.dex */
public class JsonUtils {
    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private JsonUtils() {
    }

    public static String objToJson(Object obj) {
        if (gson != null) {
            return gson.toJson(obj);
        }
        return null;
    }

    public static <T> T jsonToObj(String str, Class<T> cls) {
        if (gson != null) {
            return (T) gson.fromJson(str, (Class) cls);
        }
        return null;
    }
}
