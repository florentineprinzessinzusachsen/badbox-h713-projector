package com.android.umanalytics.http;

import b.b.a.f;
import d.d0;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import retrofit2.Converter;
import retrofit2.Retrofit;

/* JADX INFO: loaded from: classes.dex */
public class ResponseConvertFactory extends Converter.Factory {
    private final f gson;

    private ResponseConvertFactory(f fVar) {
        if (fVar == null) {
            throw new NullPointerException("gson == null");
        }
        this.gson = fVar;
    }

    public static ResponseConvertFactory create() {
        return create(new f());
    }

    @Override // retrofit2.Converter.Factory
    public Converter<d0, ?> responseBodyConverter(Type type, Annotation[] annotationArr, Retrofit retrofit) {
        return new GsonResponseBodyConverter(this.gson, type);
    }

    public static ResponseConvertFactory create(f fVar) {
        return new ResponseConvertFactory(fVar);
    }
}
