package retrofit2;

import d.b0;
import d.c0;
import d.d0;
import d.e;
import d.s;
import d.t;
import d.v;
import d.w;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.URI;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HEAD;
import retrofit2.http.HTTP;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.OPTIONS;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.QueryName;
import retrofit2.http.Url;

/* JADX INFO: loaded from: classes.dex */
final class ServiceMethod<R, T> {
    private final t baseUrl;
    private final CallAdapter<R, T> callAdapter;
    private final e.a callFactory;
    private final v contentType;
    private final boolean hasBody;
    private final s headers;
    private final String httpMethod;
    private final boolean isFormEncoded;
    private final boolean isMultipart;
    private final ParameterHandler<?>[] parameterHandlers;
    private final String relativeUrl;
    private final Converter<d0, R> responseConverter;
    static final Pattern PARAM_URL_REGEX = Pattern.compile("\\{([a-zA-Z][a-zA-Z0-9_-]*)\\}");
    static final String PARAM = "[a-zA-Z][a-zA-Z0-9_-]*";
    static final Pattern PARAM_NAME_REGEX = Pattern.compile(PARAM);

    static final class Builder<T, R> {
        CallAdapter<T, R> callAdapter;
        v contentType;
        boolean gotBody;
        boolean gotField;
        boolean gotPart;
        boolean gotPath;
        boolean gotQuery;
        boolean gotUrl;
        boolean hasBody;
        s headers;
        String httpMethod;
        boolean isFormEncoded;
        boolean isMultipart;
        final Method method;
        final Annotation[] methodAnnotations;
        final Annotation[][] parameterAnnotationsArray;
        ParameterHandler<?>[] parameterHandlers;
        final Type[] parameterTypes;
        String relativeUrl;
        Set<String> relativeUrlParamNames;
        Converter<d0, T> responseConverter;
        Type responseType;
        final Retrofit retrofit;

        Builder(Retrofit retrofit, Method method) {
            this.retrofit = retrofit;
            this.method = method;
            this.methodAnnotations = method.getAnnotations();
            this.parameterTypes = method.getGenericParameterTypes();
            this.parameterAnnotationsArray = method.getParameterAnnotations();
        }

        private CallAdapter<T, R> createCallAdapter() {
            Type genericReturnType = this.method.getGenericReturnType();
            if (Utils.hasUnresolvableType(genericReturnType)) {
                throw methodError("Method return type must not include a type variable or wildcard: %s", genericReturnType);
            }
            if (genericReturnType == Void.TYPE) {
                throw methodError("Service methods cannot return void.", new Object[0]);
            }
            try {
                return (CallAdapter<T, R>) this.retrofit.callAdapter(genericReturnType, this.method.getAnnotations());
            } catch (RuntimeException e2) {
                throw methodError(e2, "Unable to create call adapter for %s", genericReturnType);
            }
        }

        private Converter<d0, T> createResponseConverter() {
            try {
                return this.retrofit.responseBodyConverter(this.responseType, this.method.getAnnotations());
            } catch (RuntimeException e2) {
                throw methodError(e2, "Unable to create converter for %s", this.responseType);
            }
        }

        private RuntimeException methodError(String str, Object... objArr) {
            return methodError(null, str, objArr);
        }

        private RuntimeException parameterError(Throwable th, int i, String str, Object... objArr) {
            return methodError(th, str + " (parameter #" + (i + 1) + ")", objArr);
        }

        private s parseHeaders(String[] strArr) {
            s.a aVar = new s.a();
            for (String str : strArr) {
                int iIndexOf = str.indexOf(58);
                if (iIndexOf == -1 || iIndexOf == 0 || iIndexOf == str.length() - 1) {
                    throw methodError("@Headers value must be in the form \"Name: Value\". Found: \"%s\"", str);
                }
                String strSubstring = str.substring(0, iIndexOf);
                String strTrim = str.substring(iIndexOf + 1).trim();
                if ("Content-Type".equalsIgnoreCase(strSubstring)) {
                    v vVarB = v.b(strTrim);
                    if (vVarB == null) {
                        throw methodError("Malformed content type: %s", strTrim);
                    }
                    this.contentType = vVarB;
                } else {
                    aVar.a(strSubstring, strTrim);
                }
            }
            return aVar.a();
        }

        private void parseHttpMethodAndPath(String str, String str2, boolean z) {
            String str3 = this.httpMethod;
            if (str3 != null) {
                throw methodError("Only one HTTP method is allowed. Found: %s and %s.", str3, str);
            }
            this.httpMethod = str;
            this.hasBody = z;
            if (str2.isEmpty()) {
                return;
            }
            int iIndexOf = str2.indexOf(63);
            if (iIndexOf != -1 && iIndexOf < str2.length() - 1) {
                String strSubstring = str2.substring(iIndexOf + 1);
                if (ServiceMethod.PARAM_URL_REGEX.matcher(strSubstring).find()) {
                    throw methodError("URL query string \"%s\" must not have replace block. For dynamic query parameters use @Query.", strSubstring);
                }
            }
            this.relativeUrl = str2;
            this.relativeUrlParamNames = ServiceMethod.parsePathParameters(str2);
        }

        private void parseMethodAnnotation(Annotation annotation) {
            if (annotation instanceof DELETE) {
                parseHttpMethodAndPath("DELETE", ((DELETE) annotation).value(), false);
                return;
            }
            if (annotation instanceof GET) {
                parseHttpMethodAndPath("GET", ((GET) annotation).value(), false);
                return;
            }
            if (annotation instanceof HEAD) {
                parseHttpMethodAndPath("HEAD", ((HEAD) annotation).value(), false);
                if (!Void.class.equals(this.responseType)) {
                    throw methodError("HEAD method must use Void as response type.", new Object[0]);
                }
                return;
            }
            if (annotation instanceof PATCH) {
                parseHttpMethodAndPath("PATCH", ((PATCH) annotation).value(), true);
                return;
            }
            if (annotation instanceof POST) {
                parseHttpMethodAndPath("POST", ((POST) annotation).value(), true);
                return;
            }
            if (annotation instanceof PUT) {
                parseHttpMethodAndPath("PUT", ((PUT) annotation).value(), true);
                return;
            }
            if (annotation instanceof OPTIONS) {
                parseHttpMethodAndPath("OPTIONS", ((OPTIONS) annotation).value(), false);
                return;
            }
            if (annotation instanceof HTTP) {
                HTTP http = (HTTP) annotation;
                parseHttpMethodAndPath(http.method(), http.path(), http.hasBody());
                return;
            }
            if (annotation instanceof Headers) {
                String[] strArrValue = ((Headers) annotation).value();
                if (strArrValue.length == 0) {
                    throw methodError("@Headers annotation is empty.", new Object[0]);
                }
                this.headers = parseHeaders(strArrValue);
                return;
            }
            if (annotation instanceof Multipart) {
                if (this.isFormEncoded) {
                    throw methodError("Only one encoding annotation is allowed.", new Object[0]);
                }
                this.isMultipart = true;
            } else if (annotation instanceof FormUrlEncoded) {
                if (this.isMultipart) {
                    throw methodError("Only one encoding annotation is allowed.", new Object[0]);
                }
                this.isFormEncoded = true;
            }
        }

        private ParameterHandler<?> parseParameter(int i, Type type, Annotation[] annotationArr) {
            ParameterHandler<?> parameterHandler = null;
            for (Annotation annotation : annotationArr) {
                ParameterHandler<?> parameterAnnotation = parseParameterAnnotation(i, type, annotationArr, annotation);
                if (parameterAnnotation != null) {
                    if (parameterHandler != null) {
                        throw parameterError(i, "Multiple Retrofit annotations found, only one allowed.", new Object[0]);
                    }
                    parameterHandler = parameterAnnotation;
                }
            }
            if (parameterHandler != null) {
                return parameterHandler;
            }
            throw parameterError(i, "No Retrofit annotation found.", new Object[0]);
        }

        private ParameterHandler<?> parseParameterAnnotation(int i, Type type, Annotation[] annotationArr, Annotation annotation) {
            if (annotation instanceof Url) {
                if (this.gotUrl) {
                    throw parameterError(i, "Multiple @Url method annotations found.", new Object[0]);
                }
                if (this.gotPath) {
                    throw parameterError(i, "@Path parameters may not be used with @Url.", new Object[0]);
                }
                if (this.gotQuery) {
                    throw parameterError(i, "A @Url parameter must not come after a @Query", new Object[0]);
                }
                if (this.relativeUrl != null) {
                    throw parameterError(i, "@Url cannot be used with @%s URL", this.httpMethod);
                }
                this.gotUrl = true;
                if (type == t.class || type == String.class || type == URI.class || ((type instanceof Class) && "android.net.Uri".equals(((Class) type).getName()))) {
                    return new ParameterHandler.RelativeUrl();
                }
                throw parameterError(i, "@Url must be okhttp3.HttpUrl, String, java.net.URI, or android.net.Uri type.", new Object[0]);
            }
            if (annotation instanceof Path) {
                if (this.gotQuery) {
                    throw parameterError(i, "A @Path parameter must not come after a @Query.", new Object[0]);
                }
                if (this.gotUrl) {
                    throw parameterError(i, "@Path parameters may not be used with @Url.", new Object[0]);
                }
                if (this.relativeUrl == null) {
                    throw parameterError(i, "@Path can only be used with relative url on @%s", this.httpMethod);
                }
                this.gotPath = true;
                Path path = (Path) annotation;
                String strValue = path.value();
                validatePathName(i, strValue);
                return new ParameterHandler.Path(strValue, this.retrofit.stringConverter(type, annotationArr), path.encoded());
            }
            if (annotation instanceof Query) {
                Query query = (Query) annotation;
                String strValue2 = query.value();
                boolean zEncoded = query.encoded();
                Class<?> rawType = Utils.getRawType(type);
                this.gotQuery = true;
                if (!Iterable.class.isAssignableFrom(rawType)) {
                    return rawType.isArray() ? new ParameterHandler.Query(strValue2, this.retrofit.stringConverter(ServiceMethod.boxIfPrimitive(rawType.getComponentType()), annotationArr), zEncoded).array() : new ParameterHandler.Query(strValue2, this.retrofit.stringConverter(type, annotationArr), zEncoded);
                }
                if (type instanceof ParameterizedType) {
                    return new ParameterHandler.Query(strValue2, this.retrofit.stringConverter(Utils.getParameterUpperBound(0, (ParameterizedType) type), annotationArr), zEncoded).iterable();
                }
                throw parameterError(i, rawType.getSimpleName() + " must include generic type (e.g., " + rawType.getSimpleName() + "<String>)", new Object[0]);
            }
            if (annotation instanceof QueryName) {
                boolean zEncoded2 = ((QueryName) annotation).encoded();
                Class<?> rawType2 = Utils.getRawType(type);
                this.gotQuery = true;
                if (!Iterable.class.isAssignableFrom(rawType2)) {
                    return rawType2.isArray() ? new ParameterHandler.QueryName(this.retrofit.stringConverter(ServiceMethod.boxIfPrimitive(rawType2.getComponentType()), annotationArr), zEncoded2).array() : new ParameterHandler.QueryName(this.retrofit.stringConverter(type, annotationArr), zEncoded2);
                }
                if (type instanceof ParameterizedType) {
                    return new ParameterHandler.QueryName(this.retrofit.stringConverter(Utils.getParameterUpperBound(0, (ParameterizedType) type), annotationArr), zEncoded2).iterable();
                }
                throw parameterError(i, rawType2.getSimpleName() + " must include generic type (e.g., " + rawType2.getSimpleName() + "<String>)", new Object[0]);
            }
            if (annotation instanceof QueryMap) {
                Class<?> rawType3 = Utils.getRawType(type);
                if (!Map.class.isAssignableFrom(rawType3)) {
                    throw parameterError(i, "@QueryMap parameter type must be Map.", new Object[0]);
                }
                Type supertype = Utils.getSupertype(type, rawType3, Map.class);
                if (!(supertype instanceof ParameterizedType)) {
                    throw parameterError(i, "Map must include generic types (e.g., Map<String, String>)", new Object[0]);
                }
                ParameterizedType parameterizedType = (ParameterizedType) supertype;
                Type parameterUpperBound = Utils.getParameterUpperBound(0, parameterizedType);
                if (String.class == parameterUpperBound) {
                    return new ParameterHandler.QueryMap(this.retrofit.stringConverter(Utils.getParameterUpperBound(1, parameterizedType), annotationArr), ((QueryMap) annotation).encoded());
                }
                throw parameterError(i, "@QueryMap keys must be of type String: " + parameterUpperBound, new Object[0]);
            }
            if (annotation instanceof Header) {
                String strValue3 = ((Header) annotation).value();
                Class<?> rawType4 = Utils.getRawType(type);
                if (!Iterable.class.isAssignableFrom(rawType4)) {
                    return rawType4.isArray() ? new ParameterHandler.Header(strValue3, this.retrofit.stringConverter(ServiceMethod.boxIfPrimitive(rawType4.getComponentType()), annotationArr)).array() : new ParameterHandler.Header(strValue3, this.retrofit.stringConverter(type, annotationArr));
                }
                if (type instanceof ParameterizedType) {
                    return new ParameterHandler.Header(strValue3, this.retrofit.stringConverter(Utils.getParameterUpperBound(0, (ParameterizedType) type), annotationArr)).iterable();
                }
                throw parameterError(i, rawType4.getSimpleName() + " must include generic type (e.g., " + rawType4.getSimpleName() + "<String>)", new Object[0]);
            }
            if (annotation instanceof HeaderMap) {
                Class<?> rawType5 = Utils.getRawType(type);
                if (!Map.class.isAssignableFrom(rawType5)) {
                    throw parameterError(i, "@HeaderMap parameter type must be Map.", new Object[0]);
                }
                Type supertype2 = Utils.getSupertype(type, rawType5, Map.class);
                if (!(supertype2 instanceof ParameterizedType)) {
                    throw parameterError(i, "Map must include generic types (e.g., Map<String, String>)", new Object[0]);
                }
                ParameterizedType parameterizedType2 = (ParameterizedType) supertype2;
                Type parameterUpperBound2 = Utils.getParameterUpperBound(0, parameterizedType2);
                if (String.class == parameterUpperBound2) {
                    return new ParameterHandler.HeaderMap(this.retrofit.stringConverter(Utils.getParameterUpperBound(1, parameterizedType2), annotationArr));
                }
                throw parameterError(i, "@HeaderMap keys must be of type String: " + parameterUpperBound2, new Object[0]);
            }
            if (annotation instanceof Field) {
                if (!this.isFormEncoded) {
                    throw parameterError(i, "@Field parameters can only be used with form encoding.", new Object[0]);
                }
                Field field = (Field) annotation;
                String strValue4 = field.value();
                boolean zEncoded3 = field.encoded();
                this.gotField = true;
                Class<?> rawType6 = Utils.getRawType(type);
                if (!Iterable.class.isAssignableFrom(rawType6)) {
                    return rawType6.isArray() ? new ParameterHandler.Field(strValue4, this.retrofit.stringConverter(ServiceMethod.boxIfPrimitive(rawType6.getComponentType()), annotationArr), zEncoded3).array() : new ParameterHandler.Field(strValue4, this.retrofit.stringConverter(type, annotationArr), zEncoded3);
                }
                if (type instanceof ParameterizedType) {
                    return new ParameterHandler.Field(strValue4, this.retrofit.stringConverter(Utils.getParameterUpperBound(0, (ParameterizedType) type), annotationArr), zEncoded3).iterable();
                }
                throw parameterError(i, rawType6.getSimpleName() + " must include generic type (e.g., " + rawType6.getSimpleName() + "<String>)", new Object[0]);
            }
            if (annotation instanceof FieldMap) {
                if (!this.isFormEncoded) {
                    throw parameterError(i, "@FieldMap parameters can only be used with form encoding.", new Object[0]);
                }
                Class<?> rawType7 = Utils.getRawType(type);
                if (!Map.class.isAssignableFrom(rawType7)) {
                    throw parameterError(i, "@FieldMap parameter type must be Map.", new Object[0]);
                }
                Type supertype3 = Utils.getSupertype(type, rawType7, Map.class);
                if (!(supertype3 instanceof ParameterizedType)) {
                    throw parameterError(i, "Map must include generic types (e.g., Map<String, String>)", new Object[0]);
                }
                ParameterizedType parameterizedType3 = (ParameterizedType) supertype3;
                Type parameterUpperBound3 = Utils.getParameterUpperBound(0, parameterizedType3);
                if (String.class == parameterUpperBound3) {
                    Converter<T, String> converterStringConverter = this.retrofit.stringConverter(Utils.getParameterUpperBound(1, parameterizedType3), annotationArr);
                    this.gotField = true;
                    return new ParameterHandler.FieldMap(converterStringConverter, ((FieldMap) annotation).encoded());
                }
                throw parameterError(i, "@FieldMap keys must be of type String: " + parameterUpperBound3, new Object[0]);
            }
            if (!(annotation instanceof Part)) {
                if (!(annotation instanceof PartMap)) {
                    if (!(annotation instanceof Body)) {
                        return null;
                    }
                    if (this.isFormEncoded || this.isMultipart) {
                        throw parameterError(i, "@Body parameters cannot be used with form or multi-part encoding.", new Object[0]);
                    }
                    if (this.gotBody) {
                        throw parameterError(i, "Multiple @Body method annotations found.", new Object[0]);
                    }
                    try {
                        Converter<T, b0> converterRequestBodyConverter = this.retrofit.requestBodyConverter(type, annotationArr, this.methodAnnotations);
                        this.gotBody = true;
                        return new ParameterHandler.Body(converterRequestBodyConverter);
                    } catch (RuntimeException e2) {
                        throw parameterError(e2, i, "Unable to create @Body converter for %s", type);
                    }
                }
                if (!this.isMultipart) {
                    throw parameterError(i, "@PartMap parameters can only be used with multipart encoding.", new Object[0]);
                }
                this.gotPart = true;
                Class<?> rawType8 = Utils.getRawType(type);
                if (!Map.class.isAssignableFrom(rawType8)) {
                    throw parameterError(i, "@PartMap parameter type must be Map.", new Object[0]);
                }
                Type supertype4 = Utils.getSupertype(type, rawType8, Map.class);
                if (!(supertype4 instanceof ParameterizedType)) {
                    throw parameterError(i, "Map must include generic types (e.g., Map<String, String>)", new Object[0]);
                }
                ParameterizedType parameterizedType4 = (ParameterizedType) supertype4;
                Type parameterUpperBound4 = Utils.getParameterUpperBound(0, parameterizedType4);
                if (String.class == parameterUpperBound4) {
                    Type parameterUpperBound5 = Utils.getParameterUpperBound(1, parameterizedType4);
                    if (w.b.class.isAssignableFrom(Utils.getRawType(parameterUpperBound5))) {
                        throw parameterError(i, "@PartMap values cannot be MultipartBody.Part. Use @Part List<Part> or a different value type instead.", new Object[0]);
                    }
                    return new ParameterHandler.PartMap(this.retrofit.requestBodyConverter(parameterUpperBound5, annotationArr, this.methodAnnotations), ((PartMap) annotation).encoding());
                }
                throw parameterError(i, "@PartMap keys must be of type String: " + parameterUpperBound4, new Object[0]);
            }
            if (!this.isMultipart) {
                throw parameterError(i, "@Part parameters can only be used with multipart encoding.", new Object[0]);
            }
            Part part = (Part) annotation;
            this.gotPart = true;
            String strValue5 = part.value();
            Class<?> rawType9 = Utils.getRawType(type);
            if (strValue5.isEmpty()) {
                if (!Iterable.class.isAssignableFrom(rawType9)) {
                    if (rawType9.isArray()) {
                        if (w.b.class.isAssignableFrom(rawType9.getComponentType())) {
                            return ParameterHandler.RawPart.INSTANCE.array();
                        }
                        throw parameterError(i, "@Part annotation must supply a name or use MultipartBody.Part parameter type.", new Object[0]);
                    }
                    if (w.b.class.isAssignableFrom(rawType9)) {
                        return ParameterHandler.RawPart.INSTANCE;
                    }
                    throw parameterError(i, "@Part annotation must supply a name or use MultipartBody.Part parameter type.", new Object[0]);
                }
                if (type instanceof ParameterizedType) {
                    if (w.b.class.isAssignableFrom(Utils.getRawType(Utils.getParameterUpperBound(0, (ParameterizedType) type)))) {
                        return ParameterHandler.RawPart.INSTANCE.iterable();
                    }
                    throw parameterError(i, "@Part annotation must supply a name or use MultipartBody.Part parameter type.", new Object[0]);
                }
                throw parameterError(i, rawType9.getSimpleName() + " must include generic type (e.g., " + rawType9.getSimpleName() + "<String>)", new Object[0]);
            }
            s sVarA = s.a("Content-Disposition", "form-data; name=\"" + strValue5 + "\"", "Content-Transfer-Encoding", part.encoding());
            if (!Iterable.class.isAssignableFrom(rawType9)) {
                if (!rawType9.isArray()) {
                    if (w.b.class.isAssignableFrom(rawType9)) {
                        throw parameterError(i, "@Part parameters using the MultipartBody.Part must not include a part name in the annotation.", new Object[0]);
                    }
                    return new ParameterHandler.Part(sVarA, this.retrofit.requestBodyConverter(type, annotationArr, this.methodAnnotations));
                }
                Class<?> clsBoxIfPrimitive = ServiceMethod.boxIfPrimitive(rawType9.getComponentType());
                if (w.b.class.isAssignableFrom(clsBoxIfPrimitive)) {
                    throw parameterError(i, "@Part parameters using the MultipartBody.Part must not include a part name in the annotation.", new Object[0]);
                }
                return new ParameterHandler.Part(sVarA, this.retrofit.requestBodyConverter(clsBoxIfPrimitive, annotationArr, this.methodAnnotations)).array();
            }
            if (type instanceof ParameterizedType) {
                Type parameterUpperBound6 = Utils.getParameterUpperBound(0, (ParameterizedType) type);
                if (w.b.class.isAssignableFrom(Utils.getRawType(parameterUpperBound6))) {
                    throw parameterError(i, "@Part parameters using the MultipartBody.Part must not include a part name in the annotation.", new Object[0]);
                }
                return new ParameterHandler.Part(sVarA, this.retrofit.requestBodyConverter(parameterUpperBound6, annotationArr, this.methodAnnotations)).iterable();
            }
            throw parameterError(i, rawType9.getSimpleName() + " must include generic type (e.g., " + rawType9.getSimpleName() + "<String>)", new Object[0]);
        }

        private void validatePathName(int i, String str) {
            if (!ServiceMethod.PARAM_NAME_REGEX.matcher(str).matches()) {
                throw parameterError(i, "@Path parameter name must match %s. Found: %s", ServiceMethod.PARAM_URL_REGEX.pattern(), str);
            }
            if (!this.relativeUrlParamNames.contains(str)) {
                throw parameterError(i, "URL \"%s\" does not contain \"{%s}\".", this.relativeUrl, str);
            }
        }

        public ServiceMethod build() {
            this.callAdapter = createCallAdapter();
            this.responseType = this.callAdapter.responseType();
            Type type = this.responseType;
            if (type == Response.class || type == c0.class) {
                throw methodError("'" + Utils.getRawType(this.responseType).getName() + "' is not a valid response body type. Did you mean ResponseBody?", new Object[0]);
            }
            this.responseConverter = createResponseConverter();
            for (Annotation annotation : this.methodAnnotations) {
                parseMethodAnnotation(annotation);
            }
            if (this.httpMethod == null) {
                throw methodError("HTTP method annotation is required (e.g., @GET, @POST, etc.).", new Object[0]);
            }
            if (!this.hasBody) {
                if (this.isMultipart) {
                    throw methodError("Multipart can only be specified on HTTP methods with request body (e.g., @POST).", new Object[0]);
                }
                if (this.isFormEncoded) {
                    throw methodError("FormUrlEncoded can only be specified on HTTP methods with request body (e.g., @POST).", new Object[0]);
                }
            }
            int length = this.parameterAnnotationsArray.length;
            this.parameterHandlers = new ParameterHandler[length];
            for (int i = 0; i < length; i++) {
                Type type2 = this.parameterTypes[i];
                if (Utils.hasUnresolvableType(type2)) {
                    throw parameterError(i, "Parameter type must not include a type variable or wildcard: %s", type2);
                }
                Annotation[] annotationArr = this.parameterAnnotationsArray[i];
                if (annotationArr == null) {
                    throw parameterError(i, "No Retrofit annotation found.", new Object[0]);
                }
                this.parameterHandlers[i] = parseParameter(i, type2, annotationArr);
            }
            if (this.relativeUrl == null && !this.gotUrl) {
                throw methodError("Missing either @%s URL or @Url parameter.", this.httpMethod);
            }
            if (!this.isFormEncoded && !this.isMultipart && !this.hasBody && this.gotBody) {
                throw methodError("Non-body HTTP method cannot contain @Body.", new Object[0]);
            }
            if (this.isFormEncoded && !this.gotField) {
                throw methodError("Form-encoded method must contain at least one @Field.", new Object[0]);
            }
            if (!this.isMultipart || this.gotPart) {
                return new ServiceMethod(this);
            }
            throw methodError("Multipart method must contain at least one @Part.", new Object[0]);
        }

        private RuntimeException methodError(Throwable th, String str, Object... objArr) {
            return new IllegalArgumentException(String.format(str, objArr) + "\n    for method " + this.method.getDeclaringClass().getSimpleName() + "." + this.method.getName(), th);
        }

        private RuntimeException parameterError(int i, String str, Object... objArr) {
            return methodError(str + " (parameter #" + (i + 1) + ")", objArr);
        }
    }

    ServiceMethod(Builder<R, T> builder) {
        this.callFactory = builder.retrofit.callFactory();
        this.callAdapter = builder.callAdapter;
        this.baseUrl = builder.retrofit.baseUrl();
        this.responseConverter = builder.responseConverter;
        this.httpMethod = builder.httpMethod;
        this.relativeUrl = builder.relativeUrl;
        this.headers = builder.headers;
        this.contentType = builder.contentType;
        this.hasBody = builder.hasBody;
        this.isFormEncoded = builder.isFormEncoded;
        this.isMultipart = builder.isMultipart;
        this.parameterHandlers = builder.parameterHandlers;
    }

    static Class<?> boxIfPrimitive(Class<?> cls) {
        if (Boolean.TYPE == cls) {
            return Boolean.class;
        }
        if (Byte.TYPE == cls) {
            return Byte.class;
        }
        if (Character.TYPE == cls) {
            return Character.class;
        }
        if (Double.TYPE == cls) {
            return Double.class;
        }
        if (Float.TYPE == cls) {
            return Float.class;
        }
        if (Integer.TYPE == cls) {
            return Integer.class;
        }
        if (Long.TYPE == cls) {
            return Long.class;
        }
        return Short.TYPE == cls ? Short.class : cls;
    }

    static Set<String> parsePathParameters(String str) {
        Matcher matcher = PARAM_URL_REGEX.matcher(str);
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        while (matcher.find()) {
            linkedHashSet.add(matcher.group(1));
        }
        return linkedHashSet;
    }

    T adapt(Call<R> call) {
        return this.callAdapter.adapt(call);
    }

    e toCall(Object... objArr) {
        RequestBuilder requestBuilder = new RequestBuilder(this.httpMethod, this.baseUrl, this.relativeUrl, this.headers, this.contentType, this.hasBody, this.isFormEncoded, this.isMultipart);
        ParameterHandler<?>[] parameterHandlerArr = this.parameterHandlers;
        int length = objArr != null ? objArr.length : 0;
        if (length == parameterHandlerArr.length) {
            for (int i = 0; i < length; i++) {
                parameterHandlerArr[i].apply(requestBuilder, objArr[i]);
            }
            return this.callFactory.a(requestBuilder.build());
        }
        throw new IllegalArgumentException("Argument count (" + length + ") doesn't match expected count (" + parameterHandlerArr.length + ")");
    }

    R toResponse(d0 d0Var) {
        return this.responseConverter.convert(d0Var);
    }
}
