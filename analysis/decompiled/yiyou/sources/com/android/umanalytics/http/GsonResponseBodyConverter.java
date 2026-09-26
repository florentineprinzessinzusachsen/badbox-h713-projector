package com.android.umanalytics.http;

import b.b.a.f;
import d.d0;
import java.lang.reflect.Type;
import retrofit2.Converter;

/* JADX INFO: loaded from: classes.dex */
class GsonResponseBodyConverter<T> implements Converter<d0, T> {
    private final f gson;
    private final Type type;

    GsonResponseBodyConverter(f fVar, Type type) {
        this.gson = fVar;
        this.type = type;
    }

    @Override // retrofit2.Converter
    public T convert(d0 d0Var) {
        String strString = d0Var.string();
        HttpResult httpResult = (HttpResult) this.gson.a(strString, (Class) HttpResult.class);
        if (httpResult.getCode() == 0) {
            return (T) this.gson.a(strString, this.type);
        }
        throw new ApiException(httpResult.getCode(), httpResult.getMsg());
    }
}
