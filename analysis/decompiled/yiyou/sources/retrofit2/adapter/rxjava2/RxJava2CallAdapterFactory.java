package retrofit2.adapter.rxjava2;

import c.a.b;
import c.a.f;
import c.a.h;
import c.a.l;
import c.a.t;
import c.a.u;
import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import retrofit2.CallAdapter;
import retrofit2.Response;
import retrofit2.Retrofit;

/* JADX INFO: loaded from: classes.dex */
public final class RxJava2CallAdapterFactory extends CallAdapter.Factory {
    private final boolean isAsync;
    private final t scheduler;

    private RxJava2CallAdapterFactory(t tVar, boolean z) {
        this.scheduler = tVar;
        this.isAsync = z;
    }

    public static RxJava2CallAdapterFactory create() {
        return new RxJava2CallAdapterFactory(null, false);
    }

    public static RxJava2CallAdapterFactory createAsync() {
        return new RxJava2CallAdapterFactory(null, true);
    }

    public static RxJava2CallAdapterFactory createWithScheduler(t tVar) {
        if (tVar != null) {
            return new RxJava2CallAdapterFactory(tVar, false);
        }
        throw new NullPointerException("scheduler == null");
    }

    @Override // retrofit2.CallAdapter.Factory
    public CallAdapter<?, ?> get(Type type, Annotation[] annotationArr, Retrofit retrofit) {
        Type parameterUpperBound;
        boolean z;
        boolean z2;
        String str;
        Class<?> rawType = CallAdapter.Factory.getRawType(type);
        if (rawType == b.class) {
            return new RxJava2CallAdapter(Void.class, this.scheduler, this.isAsync, false, true, false, false, false, true);
        }
        boolean z3 = rawType == f.class;
        boolean z4 = rawType == u.class;
        boolean z5 = rawType == h.class;
        if (rawType != l.class && !z3 && !z4 && !z5) {
            return null;
        }
        if (!(type instanceof ParameterizedType)) {
            if (z3) {
                str = "Flowable";
            } else if (z4) {
                str = "Single";
            } else {
                str = z5 ? "Maybe" : "Observable";
            }
            throw new IllegalStateException(str + " return type must be parameterized as " + str + "<Foo> or " + str + "<? extends Foo>");
        }
        Type parameterUpperBound2 = CallAdapter.Factory.getParameterUpperBound(0, (ParameterizedType) type);
        Class<?> rawType2 = CallAdapter.Factory.getRawType(parameterUpperBound2);
        if (rawType2 != Response.class) {
            if (rawType2 != Result.class) {
                parameterUpperBound = parameterUpperBound2;
                z = false;
                z2 = true;
            } else {
                if (!(parameterUpperBound2 instanceof ParameterizedType)) {
                    throw new IllegalStateException("Result must be parameterized as Result<Foo> or Result<? extends Foo>");
                }
                parameterUpperBound = CallAdapter.Factory.getParameterUpperBound(0, (ParameterizedType) parameterUpperBound2);
                z = true;
            }
            return new RxJava2CallAdapter(parameterUpperBound, this.scheduler, this.isAsync, z, z2, z3, z4, z5, false);
        }
        if (!(parameterUpperBound2 instanceof ParameterizedType)) {
            throw new IllegalStateException("Response must be parameterized as Response<Foo> or Response<? extends Foo>");
        }
        parameterUpperBound = CallAdapter.Factory.getParameterUpperBound(0, (ParameterizedType) parameterUpperBound2);
        z = false;
        z2 = false;
        return new RxJava2CallAdapter(parameterUpperBound, this.scheduler, this.isAsync, z, z2, z3, z4, z5, false);
    }
}
