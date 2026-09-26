package retrofit2;

import d.b0;
import d.d0;
import d.e;
import d.t;
import d.x;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public final class Retrofit {
    final t baseUrl;
    final List<CallAdapter.Factory> callAdapterFactories;
    final e.a callFactory;
    final Executor callbackExecutor;
    final List<Converter.Factory> converterFactories;
    private final Map<Method, ServiceMethod<?, ?>> serviceMethodCache = new ConcurrentHashMap();
    final boolean validateEagerly;

    Retrofit(e.a aVar, t tVar, List<Converter.Factory> list, List<CallAdapter.Factory> list2, Executor executor, boolean z) {
        this.callFactory = aVar;
        this.baseUrl = tVar;
        this.converterFactories = list;
        this.callAdapterFactories = list2;
        this.callbackExecutor = executor;
        this.validateEagerly = z;
    }

    private void eagerlyValidateMethods(Class<?> cls) {
        Platform platform = Platform.get();
        for (Method method : cls.getDeclaredMethods()) {
            if (!platform.isDefaultMethod(method)) {
                loadServiceMethod(method);
            }
        }
    }

    public t baseUrl() {
        return this.baseUrl;
    }

    public CallAdapter<?, ?> callAdapter(Type type, Annotation[] annotationArr) {
        return nextCallAdapter(null, type, annotationArr);
    }

    public List<CallAdapter.Factory> callAdapterFactories() {
        return this.callAdapterFactories;
    }

    public e.a callFactory() {
        return this.callFactory;
    }

    public Executor callbackExecutor() {
        return this.callbackExecutor;
    }

    public List<Converter.Factory> converterFactories() {
        return this.converterFactories;
    }

    public <T> T create(final Class<T> cls) {
        Utils.validateServiceInterface(cls);
        if (this.validateEagerly) {
            eagerlyValidateMethods(cls);
        }
        return (T) Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, new InvocationHandler() { // from class: retrofit2.Retrofit.1
            private final Platform platform = Platform.get();

            @Override // java.lang.reflect.InvocationHandler
            public Object invoke(Object obj, Method method, Object[] objArr) {
                if (method.getDeclaringClass() == Object.class) {
                    return method.invoke(this, objArr);
                }
                if (this.platform.isDefaultMethod(method)) {
                    return this.platform.invokeDefaultMethod(method, cls, obj, objArr);
                }
                ServiceMethod<?, ?> serviceMethodLoadServiceMethod = Retrofit.this.loadServiceMethod(method);
                return serviceMethodLoadServiceMethod.adapt(new OkHttpCall(serviceMethodLoadServiceMethod, objArr));
            }
        });
    }

    ServiceMethod<?, ?> loadServiceMethod(Method method) {
        ServiceMethod serviceMethodBuild;
        ServiceMethod<?, ?> serviceMethod = this.serviceMethodCache.get(method);
        if (serviceMethod != null) {
            return serviceMethod;
        }
        synchronized (this.serviceMethodCache) {
            serviceMethodBuild = this.serviceMethodCache.get(method);
            if (serviceMethodBuild == null) {
                serviceMethodBuild = new ServiceMethod.Builder(this, method).build();
                this.serviceMethodCache.put(method, serviceMethodBuild);
            }
        }
        return serviceMethodBuild;
    }

    public Builder newBuilder() {
        return new Builder(this);
    }

    public CallAdapter<?, ?> nextCallAdapter(CallAdapter.Factory factory, Type type, Annotation[] annotationArr) {
        Utils.checkNotNull(type, "returnType == null");
        Utils.checkNotNull(annotationArr, "annotations == null");
        int iIndexOf = this.callAdapterFactories.indexOf(factory) + 1;
        int size = this.callAdapterFactories.size();
        for (int i = iIndexOf; i < size; i++) {
            CallAdapter<?, ?> callAdapter = this.callAdapterFactories.get(i).get(type, annotationArr, this);
            if (callAdapter != null) {
                return callAdapter;
            }
        }
        StringBuilder sb = new StringBuilder("Could not locate call adapter for ");
        sb.append(type);
        sb.append(".\n");
        if (factory != null) {
            sb.append("  Skipped:");
            for (int i2 = 0; i2 < iIndexOf; i2++) {
                sb.append("\n   * ");
                sb.append(this.callAdapterFactories.get(i2).getClass().getName());
            }
            sb.append('\n');
        }
        sb.append("  Tried:");
        int size2 = this.callAdapterFactories.size();
        while (iIndexOf < size2) {
            sb.append("\n   * ");
            sb.append(this.callAdapterFactories.get(iIndexOf).getClass().getName());
            iIndexOf++;
        }
        throw new IllegalArgumentException(sb.toString());
    }

    public <T> Converter<T, b0> nextRequestBodyConverter(Converter.Factory factory, Type type, Annotation[] annotationArr, Annotation[] annotationArr2) {
        Utils.checkNotNull(type, "type == null");
        Utils.checkNotNull(annotationArr, "parameterAnnotations == null");
        Utils.checkNotNull(annotationArr2, "methodAnnotations == null");
        int iIndexOf = this.converterFactories.indexOf(factory) + 1;
        int size = this.converterFactories.size();
        for (int i = iIndexOf; i < size; i++) {
            Converter<T, b0> converter = (Converter<T, b0>) this.converterFactories.get(i).requestBodyConverter(type, annotationArr, annotationArr2, this);
            if (converter != null) {
                return converter;
            }
        }
        StringBuilder sb = new StringBuilder("Could not locate RequestBody converter for ");
        sb.append(type);
        sb.append(".\n");
        if (factory != null) {
            sb.append("  Skipped:");
            for (int i2 = 0; i2 < iIndexOf; i2++) {
                sb.append("\n   * ");
                sb.append(this.converterFactories.get(i2).getClass().getName());
            }
            sb.append('\n');
        }
        sb.append("  Tried:");
        int size2 = this.converterFactories.size();
        while (iIndexOf < size2) {
            sb.append("\n   * ");
            sb.append(this.converterFactories.get(iIndexOf).getClass().getName());
            iIndexOf++;
        }
        throw new IllegalArgumentException(sb.toString());
    }

    public <T> Converter<d0, T> nextResponseBodyConverter(Converter.Factory factory, Type type, Annotation[] annotationArr) {
        Utils.checkNotNull(type, "type == null");
        Utils.checkNotNull(annotationArr, "annotations == null");
        int iIndexOf = this.converterFactories.indexOf(factory) + 1;
        int size = this.converterFactories.size();
        for (int i = iIndexOf; i < size; i++) {
            Converter<d0, T> converter = (Converter<d0, T>) this.converterFactories.get(i).responseBodyConverter(type, annotationArr, this);
            if (converter != null) {
                return converter;
            }
        }
        StringBuilder sb = new StringBuilder("Could not locate ResponseBody converter for ");
        sb.append(type);
        sb.append(".\n");
        if (factory != null) {
            sb.append("  Skipped:");
            for (int i2 = 0; i2 < iIndexOf; i2++) {
                sb.append("\n   * ");
                sb.append(this.converterFactories.get(i2).getClass().getName());
            }
            sb.append('\n');
        }
        sb.append("  Tried:");
        int size2 = this.converterFactories.size();
        while (iIndexOf < size2) {
            sb.append("\n   * ");
            sb.append(this.converterFactories.get(iIndexOf).getClass().getName());
            iIndexOf++;
        }
        throw new IllegalArgumentException(sb.toString());
    }

    public <T> Converter<T, b0> requestBodyConverter(Type type, Annotation[] annotationArr, Annotation[] annotationArr2) {
        return nextRequestBodyConverter(null, type, annotationArr, annotationArr2);
    }

    public <T> Converter<d0, T> responseBodyConverter(Type type, Annotation[] annotationArr) {
        return nextResponseBodyConverter(null, type, annotationArr);
    }

    public <T> Converter<T, String> stringConverter(Type type, Annotation[] annotationArr) {
        Utils.checkNotNull(type, "type == null");
        Utils.checkNotNull(annotationArr, "annotations == null");
        int size = this.converterFactories.size();
        for (int i = 0; i < size; i++) {
            Converter<T, String> converter = (Converter<T, String>) this.converterFactories.get(i).stringConverter(type, annotationArr, this);
            if (converter != null) {
                return converter;
            }
        }
        return BuiltInConverters.ToStringConverter.INSTANCE;
    }

    public static final class Builder {
        private t baseUrl;
        private final List<CallAdapter.Factory> callAdapterFactories;
        private e.a callFactory;
        private Executor callbackExecutor;
        private final List<Converter.Factory> converterFactories;
        private final Platform platform;
        private boolean validateEagerly;

        Builder(Platform platform) {
            this.converterFactories = new ArrayList();
            this.callAdapterFactories = new ArrayList();
            this.platform = platform;
        }

        public Builder addCallAdapterFactory(CallAdapter.Factory factory) {
            this.callAdapterFactories.add((CallAdapter.Factory) Utils.checkNotNull(factory, "factory == null"));
            return this;
        }

        public Builder addConverterFactory(Converter.Factory factory) {
            this.converterFactories.add((Converter.Factory) Utils.checkNotNull(factory, "factory == null"));
            return this;
        }

        public Builder baseUrl(String str) {
            Utils.checkNotNull(str, "baseUrl == null");
            t tVarE = t.e(str);
            if (tVarE != null) {
                return baseUrl(tVarE);
            }
            throw new IllegalArgumentException("Illegal URL: " + str);
        }

        public Retrofit build() {
            if (this.baseUrl == null) {
                throw new IllegalStateException("Base URL required.");
            }
            e.a xVar = this.callFactory;
            if (xVar == null) {
                xVar = new x();
            }
            e.a aVar = xVar;
            Executor executorDefaultCallbackExecutor = this.callbackExecutor;
            if (executorDefaultCallbackExecutor == null) {
                executorDefaultCallbackExecutor = this.platform.defaultCallbackExecutor();
            }
            Executor executor = executorDefaultCallbackExecutor;
            ArrayList arrayList = new ArrayList(this.callAdapterFactories);
            arrayList.add(this.platform.defaultCallAdapterFactory(executor));
            ArrayList arrayList2 = new ArrayList(this.converterFactories.size() + 1);
            arrayList2.add(new BuiltInConverters());
            arrayList2.addAll(this.converterFactories);
            return new Retrofit(aVar, this.baseUrl, Collections.unmodifiableList(arrayList2), Collections.unmodifiableList(arrayList), executor, this.validateEagerly);
        }

        public List<CallAdapter.Factory> callAdapterFactories() {
            return this.callAdapterFactories;
        }

        public Builder callFactory(e.a aVar) {
            this.callFactory = (e.a) Utils.checkNotNull(aVar, "factory == null");
            return this;
        }

        public Builder callbackExecutor(Executor executor) {
            this.callbackExecutor = (Executor) Utils.checkNotNull(executor, "executor == null");
            return this;
        }

        public Builder client(x xVar) {
            return callFactory((e.a) Utils.checkNotNull(xVar, "client == null"));
        }

        public List<Converter.Factory> converterFactories() {
            return this.converterFactories;
        }

        public Builder validateEagerly(boolean z) {
            this.validateEagerly = z;
            return this;
        }

        public Builder() {
            this(Platform.get());
        }

        public Builder baseUrl(t tVar) {
            Utils.checkNotNull(tVar, "baseUrl == null");
            List<String> listJ = tVar.j();
            if ("".equals(listJ.get(listJ.size() - 1))) {
                this.baseUrl = tVar;
                return this;
            }
            throw new IllegalArgumentException("baseUrl must end in /: " + tVar);
        }

        Builder(Retrofit retrofit) {
            this.converterFactories = new ArrayList();
            this.callAdapterFactories = new ArrayList();
            this.platform = Platform.get();
            this.callFactory = retrofit.callFactory;
            this.baseUrl = retrofit.baseUrl;
            this.converterFactories.addAll(retrofit.converterFactories);
            this.converterFactories.remove(0);
            this.callAdapterFactories.addAll(retrofit.callAdapterFactories);
            List<CallAdapter.Factory> list = this.callAdapterFactories;
            list.remove(list.size() - 1);
            this.callbackExecutor = retrofit.callbackExecutor;
            this.validateEagerly = retrofit.validateEagerly;
        }
    }
}
