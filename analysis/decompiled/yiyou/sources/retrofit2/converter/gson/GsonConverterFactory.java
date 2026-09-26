package retrofit2.converter.gson;

import b.b.a.f;
import b.b.a.z.a;
import d.b0;
import d.d0;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import retrofit2.Converter;
import retrofit2.Retrofit;

/* JADX INFO: loaded from: classes.dex */
public final class GsonConverterFactory extends Converter.Factory {
    private final f gson;

    private GsonConverterFactory(f fVar) {
        this.gson = fVar;
    }

    public static GsonConverterFactory create() {
        return create(new f());
    }

    @Override // retrofit2.Converter.Factory
    public Converter<?, b0> requestBodyConverter(Type type, Annotation[] annotationArr, Annotation[] annotationArr2, Retrofit retrofit) {
        return new GsonRequestBodyConverter(this.gson, this.gson.a((a) a.a(type)));
    }

    @Override // retrofit2.Converter.Factory
    public Converter<d0, ?> responseBodyConverter(Type type, Annotation[] annotationArr, Retrofit retrofit) {
        return new GsonResponseBodyConverter(this.gson, this.gson.a((a) a.a(type)));
    }

    public static GsonConverterFactory create(f fVar) {
        if (fVar != null) {
            return new GsonConverterFactory(fVar);
        }
        throw new NullPointerException("gson == null");
    }
}
