package com.blankj.utilcode.util;

import android.util.Log;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class ApiUtils {
    private static final String TAG = "ApiUtils";
    private Map<Class, BaseApi> mApiMap;
    private Map<Class, Class> mInjectApiImplMap;

    @Target({ElementType.TYPE})
    @Retention(RetentionPolicy.CLASS)
    public @interface Api {
        boolean isMock() default false;
    }

    public static abstract class BaseApi {
    }

    private static class LazyHolder {
        private static final ApiUtils INSTANCE = new ApiUtils();

        private LazyHolder() {
        }
    }

    public static <T extends BaseApi> T getApi(Class<T> cls) {
        if (cls != null) {
            return (T) getInstance().getApiInner(cls);
        }
        throw new NullPointerException("Argument 'apiClass' of type Class<T> (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    private <Result> Result getApiInner(Class cls) {
        BaseApi baseApi = (Result) this.mApiMap.get(cls);
        if (baseApi == null) {
            synchronized (this) {
                baseApi = this.mApiMap.get(cls);
                if (baseApi == null) {
                    Class cls2 = this.mInjectApiImplMap.get(cls);
                    if (cls2 == null) {
                        Log.e(TAG, "The <" + cls + "> doesn't implement.");
                        return null;
                    }
                    try {
                        BaseApi baseApi2 = (BaseApi) cls2.newInstance();
                        this.mApiMap.put(cls, baseApi2);
                        baseApi = (Result) baseApi2;
                    } catch (Exception unused) {
                        Log.e(TAG, "The <" + cls2 + "> has no parameterless constructor.");
                        return null;
                    }
                }
            }
        }
        return (Result) baseApi;
    }

    private static ApiUtils getInstance() {
        return LazyHolder.INSTANCE;
    }

    private void init() {
    }

    private void registerImpl(Class cls) {
        this.mInjectApiImplMap.put(cls.getSuperclass(), cls);
    }

    public static String toString_() {
        return getInstance().toString();
    }

    public String toString() {
        return "ApiUtils: " + this.mInjectApiImplMap;
    }

    private ApiUtils() {
        this.mApiMap = new ConcurrentHashMap();
        this.mInjectApiImplMap = new HashMap();
        init();
    }
}
