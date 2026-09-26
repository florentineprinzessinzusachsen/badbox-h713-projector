package retrofit2;

import d.b0;
import d.d0;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import retrofit2.http.Streaming;

/* JADX INFO: loaded from: classes.dex */
final class BuiltInConverters extends Converter.Factory {

    static final class BufferingResponseBodyConverter implements Converter<d0, d0> {
        static final BufferingResponseBodyConverter INSTANCE = new BufferingResponseBodyConverter();

        BufferingResponseBodyConverter() {
        }

        @Override // retrofit2.Converter
        public d0 convert(d0 d0Var) {
            try {
                return Utils.buffer(d0Var);
            } finally {
                d0Var.close();
            }
        }
    }

    static final class RequestBodyConverter implements Converter<b0, b0> {
        static final RequestBodyConverter INSTANCE = new RequestBodyConverter();

        RequestBodyConverter() {
        }

        @Override // retrofit2.Converter
        public b0 convert(b0 b0Var) {
            return b0Var;
        }
    }

    static final class StreamingResponseBodyConverter implements Converter<d0, d0> {
        static final StreamingResponseBodyConverter INSTANCE = new StreamingResponseBodyConverter();

        StreamingResponseBodyConverter() {
        }

        @Override // retrofit2.Converter
        public d0 convert(d0 d0Var) {
            return d0Var;
        }
    }

    static final class ToStringConverter implements Converter<Object, String> {
        static final ToStringConverter INSTANCE = new ToStringConverter();

        ToStringConverter() {
        }

        @Override // retrofit2.Converter
        public String convert(Object obj) {
            return obj.toString();
        }
    }

    static final class VoidResponseBodyConverter implements Converter<d0, Void> {
        static final VoidResponseBodyConverter INSTANCE = new VoidResponseBodyConverter();

        VoidResponseBodyConverter() {
        }

        @Override // retrofit2.Converter
        public Void convert(d0 d0Var) {
            d0Var.close();
            return null;
        }
    }

    BuiltInConverters() {
    }

    @Override // retrofit2.Converter.Factory
    public Converter<?, b0> requestBodyConverter(Type type, Annotation[] annotationArr, Annotation[] annotationArr2, Retrofit retrofit) {
        if (b0.class.isAssignableFrom(Utils.getRawType(type))) {
            return RequestBodyConverter.INSTANCE;
        }
        return null;
    }

    @Override // retrofit2.Converter.Factory
    public Converter<d0, ?> responseBodyConverter(Type type, Annotation[] annotationArr, Retrofit retrofit) {
        if (type == d0.class) {
            return Utils.isAnnotationPresent(annotationArr, Streaming.class) ? StreamingResponseBodyConverter.INSTANCE : BufferingResponseBodyConverter.INSTANCE;
        }
        if (type == Void.class) {
            return VoidResponseBodyConverter.INSTANCE;
        }
        return null;
    }
}
