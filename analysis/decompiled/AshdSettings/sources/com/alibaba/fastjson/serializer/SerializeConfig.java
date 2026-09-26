package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONAware;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONStreamAware;
import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;
import com.alibaba.fastjson.parser.deserializer.Jdk8DateCodec;
import com.alibaba.fastjson.parser.deserializer.OptionalCodec;
import com.alibaba.fastjson.support.springfox.SwaggerJsonSerializer;
import com.alibaba.fastjson.util.ASMUtils;
import com.alibaba.fastjson.util.FieldInfo;
import com.alibaba.fastjson.util.IdentityHashMap;
import com.alibaba.fastjson.util.ServiceLoader;
import com.alibaba.fastjson.util.TypeUtils;
import java.io.File;
import java.io.Serializable;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URL;
import java.nio.charset.Charset;
import java.sql.Clob;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Currency;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicLongArray;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Pattern;
import javax.xml.datatype.XMLGregorianCalendar;
import org.w3c.dom.Node;

/* JADX INFO: loaded from: classes.dex */
public class SerializeConfig {
    private static boolean awtError = false;
    public static final SerializeConfig globalInstance = new SerializeConfig();
    private static boolean guavaError = false;
    private static boolean jdk8Error = false;
    private static boolean jodaError = false;
    private static boolean jsonnullError = false;
    private static boolean oracleJdbcError = false;
    private static boolean springfoxError = false;
    private boolean asm;
    private ASMSerializerFactory asmFactory;
    private final boolean fieldBased;
    public PropertyNamingStrategy propertyNamingStrategy;
    private final IdentityHashMap<Type, ObjectSerializer> serializers;
    protected String typeKey;

    public String getTypeKey() {
        return this.typeKey;
    }

    public void setTypeKey(String str) {
        this.typeKey = str;
    }

    private final JavaBeanSerializer createASMSerializer(SerializeBeanInfo serializeBeanInfo) throws Exception {
        JavaBeanSerializer javaBeanSerializerCreateJavaBeanSerializer = this.asmFactory.createJavaBeanSerializer(serializeBeanInfo);
        for (int i = 0; i < javaBeanSerializerCreateJavaBeanSerializer.sortedGetters.length; i++) {
            Class<?> cls = javaBeanSerializerCreateJavaBeanSerializer.sortedGetters[i].fieldInfo.fieldClass;
            if (cls.isEnum() && !(getObjectWriter(cls) instanceof EnumSerializer)) {
                javaBeanSerializerCreateJavaBeanSerializer.writeDirect = false;
            }
        }
        return javaBeanSerializerCreateJavaBeanSerializer;
    }

    public final ObjectSerializer createJavaBeanSerializer(Class<?> cls) {
        SerializeBeanInfo serializeBeanInfoBuildBeanInfo = TypeUtils.buildBeanInfo(cls, null, this.propertyNamingStrategy, this.fieldBased);
        if (serializeBeanInfoBuildBeanInfo.fields.length == 0 && Iterable.class.isAssignableFrom(cls)) {
            return MiscCodec.instance;
        }
        return createJavaBeanSerializer(serializeBeanInfoBuildBeanInfo);
    }

    public ObjectSerializer createJavaBeanSerializer(SerializeBeanInfo serializeBeanInfo) {
        Method method;
        JSONType jSONType = serializeBeanInfo.jsonType;
        boolean z = false;
        boolean z2 = this.asm && !this.fieldBased;
        if (jSONType != null) {
            Class<?> clsSerializer = jSONType.serializer();
            if (clsSerializer != Void.class) {
                try {
                    Object objNewInstance = clsSerializer.newInstance();
                    if (objNewInstance instanceof ObjectSerializer) {
                        return (ObjectSerializer) objNewInstance;
                    }
                } catch (Throwable unused) {
                }
            }
            if (!jSONType.asm()) {
                z2 = false;
            }
            if (z2) {
                for (SerializerFeature serializerFeature : jSONType.serialzeFeatures()) {
                    if (SerializerFeature.WriteNonStringValueAsString == serializerFeature || SerializerFeature.WriteEnumUsingToString == serializerFeature || SerializerFeature.NotWriteDefaultValue == serializerFeature || SerializerFeature.BrowserCompatible == serializerFeature) {
                        z2 = false;
                        break;
                    }
                }
            }
            if (z2 && jSONType.serialzeFilters().length != 0) {
                z2 = false;
            }
        }
        Class<?> cls = serializeBeanInfo.beanType;
        if (!Modifier.isPublic(serializeBeanInfo.beanType.getModifiers())) {
            return new JavaBeanSerializer(serializeBeanInfo);
        }
        if ((z2 && this.asmFactory.classLoader.isExternalClass(cls)) || cls == Serializable.class || cls == Object.class) {
            z2 = false;
        }
        if (z2 && !ASMUtils.checkName(cls.getSimpleName())) {
            z2 = false;
        }
        if (z2 && serializeBeanInfo.beanType.isInterface()) {
            z2 = false;
        }
        if (z2) {
            FieldInfo[] fieldInfoArr = serializeBeanInfo.fields;
            int length = fieldInfoArr.length;
            boolean z3 = z2;
            int i = 0;
            while (true) {
                if (i >= length) {
                    z = z3;
                    break;
                }
                FieldInfo fieldInfo = fieldInfoArr[i];
                Field field = fieldInfo.field;
                if ((field != null && !field.getType().equals(fieldInfo.fieldClass)) || ((method = fieldInfo.method) != null && !method.getReturnType().equals(fieldInfo.fieldClass))) {
                    break;
                }
                JSONField annotation = fieldInfo.getAnnotation();
                if (annotation != null) {
                    String str = annotation.format();
                    if ((str.length() != 0 && (fieldInfo.fieldClass != String.class || !"trim".equals(str))) || !ASMUtils.checkName(annotation.name()) || annotation.jsonDirect() || annotation.serializeUsing() != Void.class || annotation.unwrapped()) {
                        break;
                    }
                    for (SerializerFeature serializerFeature2 : annotation.serialzeFeatures()) {
                        if (SerializerFeature.WriteNonStringValueAsString == serializerFeature2 || SerializerFeature.WriteEnumUsingToString == serializerFeature2 || SerializerFeature.NotWriteDefaultValue == serializerFeature2 || SerializerFeature.BrowserCompatible == serializerFeature2 || SerializerFeature.WriteClassName == serializerFeature2) {
                            z3 = false;
                            break;
                        }
                    }
                    if (TypeUtils.isAnnotationPresentOneToMany(method) || TypeUtils.isAnnotationPresentManyToMany(method)) {
                        break;
                    }
                }
                i++;
            }
        } else {
            z = z2;
        }
        if (z) {
            try {
                JavaBeanSerializer javaBeanSerializerCreateASMSerializer = createASMSerializer(serializeBeanInfo);
                if (javaBeanSerializerCreateASMSerializer != null) {
                    return javaBeanSerializerCreateASMSerializer;
                }
            } catch (ClassCastException | ClassFormatError | ClassNotFoundException unused2) {
            } catch (OutOfMemoryError e) {
                if (e.getMessage().indexOf("Metaspace") != -1) {
                    throw e;
                }
            } catch (Throwable th) {
                throw new JSONException("create asm serializer error, verson 1.2.53, class " + cls, th);
            }
        }
        return new JavaBeanSerializer(serializeBeanInfo);
    }

    public boolean isAsmEnable() {
        return this.asm;
    }

    public void setAsmEnable(boolean z) {
        if (ASMUtils.IS_ANDROID) {
            return;
        }
        this.asm = z;
    }

    public static SerializeConfig getGlobalInstance() {
        return globalInstance;
    }

    public SerializeConfig() {
        this(8192);
    }

    public SerializeConfig(boolean z) {
        this(8192, z);
    }

    public SerializeConfig(int i) {
        this(i, false);
    }

    public SerializeConfig(int i, boolean z) {
        this.asm = !ASMUtils.IS_ANDROID;
        this.typeKey = JSON.DEFAULT_TYPE_KEY;
        this.fieldBased = z;
        this.serializers = new IdentityHashMap<>(i);
        try {
            if (this.asm) {
                this.asmFactory = new ASMSerializerFactory();
            }
        } catch (Throwable unused) {
            this.asm = false;
        }
        initSerializers();
    }

    private void initSerializers() {
        put(Boolean.class, (ObjectSerializer) BooleanCodec.instance);
        put(Character.class, (ObjectSerializer) CharacterCodec.instance);
        put(Byte.class, (ObjectSerializer) IntegerCodec.instance);
        put(Short.class, (ObjectSerializer) IntegerCodec.instance);
        put(Integer.class, (ObjectSerializer) IntegerCodec.instance);
        put(Long.class, (ObjectSerializer) LongCodec.instance);
        put(Float.class, (ObjectSerializer) FloatCodec.instance);
        put(Double.class, (ObjectSerializer) DoubleSerializer.instance);
        put(BigDecimal.class, (ObjectSerializer) BigDecimalCodec.instance);
        put(BigInteger.class, (ObjectSerializer) BigIntegerCodec.instance);
        put(String.class, (ObjectSerializer) StringCodec.instance);
        put(byte[].class, (ObjectSerializer) PrimitiveArraySerializer.instance);
        put(short[].class, (ObjectSerializer) PrimitiveArraySerializer.instance);
        put(int[].class, (ObjectSerializer) PrimitiveArraySerializer.instance);
        put(long[].class, (ObjectSerializer) PrimitiveArraySerializer.instance);
        put(float[].class, (ObjectSerializer) PrimitiveArraySerializer.instance);
        put(double[].class, (ObjectSerializer) PrimitiveArraySerializer.instance);
        put(boolean[].class, (ObjectSerializer) PrimitiveArraySerializer.instance);
        put(char[].class, (ObjectSerializer) PrimitiveArraySerializer.instance);
        put(Object[].class, (ObjectSerializer) ObjectArrayCodec.instance);
        put(Class.class, (ObjectSerializer) MiscCodec.instance);
        put(SimpleDateFormat.class, (ObjectSerializer) MiscCodec.instance);
        put(Currency.class, (ObjectSerializer) new MiscCodec());
        put(TimeZone.class, (ObjectSerializer) MiscCodec.instance);
        put(InetAddress.class, (ObjectSerializer) MiscCodec.instance);
        put(Inet4Address.class, (ObjectSerializer) MiscCodec.instance);
        put(Inet6Address.class, (ObjectSerializer) MiscCodec.instance);
        put(InetSocketAddress.class, (ObjectSerializer) MiscCodec.instance);
        put(File.class, (ObjectSerializer) MiscCodec.instance);
        put(Appendable.class, (ObjectSerializer) AppendableSerializer.instance);
        put(StringBuffer.class, (ObjectSerializer) AppendableSerializer.instance);
        put(StringBuilder.class, (ObjectSerializer) AppendableSerializer.instance);
        put(Charset.class, (ObjectSerializer) ToStringSerializer.instance);
        put(Pattern.class, (ObjectSerializer) ToStringSerializer.instance);
        put(Locale.class, (ObjectSerializer) ToStringSerializer.instance);
        put(URI.class, (ObjectSerializer) ToStringSerializer.instance);
        put(URL.class, (ObjectSerializer) ToStringSerializer.instance);
        put(UUID.class, (ObjectSerializer) ToStringSerializer.instance);
        put(AtomicBoolean.class, (ObjectSerializer) AtomicCodec.instance);
        put(AtomicInteger.class, (ObjectSerializer) AtomicCodec.instance);
        put(AtomicLong.class, (ObjectSerializer) AtomicCodec.instance);
        put(AtomicReference.class, (ObjectSerializer) ReferenceCodec.instance);
        put(AtomicIntegerArray.class, (ObjectSerializer) AtomicCodec.instance);
        put(AtomicLongArray.class, (ObjectSerializer) AtomicCodec.instance);
        put(WeakReference.class, (ObjectSerializer) ReferenceCodec.instance);
        put(SoftReference.class, (ObjectSerializer) ReferenceCodec.instance);
        put(LinkedList.class, (ObjectSerializer) CollectionCodec.instance);
    }

    public void addFilter(Class<?> cls, SerializeFilter serializeFilter) {
        Object objectWriter = getObjectWriter(cls);
        if (objectWriter instanceof SerializeFilterable) {
            SerializeFilterable serializeFilterable = (SerializeFilterable) objectWriter;
            if (this != globalInstance && serializeFilterable == MapSerializer.instance) {
                MapSerializer mapSerializer = new MapSerializer();
                put((Type) cls, (ObjectSerializer) mapSerializer);
                mapSerializer.addFilter(serializeFilter);
                return;
            }
            serializeFilterable.addFilter(serializeFilter);
        }
    }

    public void config(Class<?> cls, SerializerFeature serializerFeature, boolean z) {
        ObjectSerializer objectWriter = getObjectWriter(cls, false);
        if (objectWriter == null) {
            SerializeBeanInfo serializeBeanInfoBuildBeanInfo = TypeUtils.buildBeanInfo(cls, null, this.propertyNamingStrategy);
            if (z) {
                serializeBeanInfoBuildBeanInfo.features = serializerFeature.mask | serializeBeanInfoBuildBeanInfo.features;
            } else {
                serializeBeanInfoBuildBeanInfo.features = (~serializerFeature.mask) & serializeBeanInfoBuildBeanInfo.features;
            }
            put((Type) cls, createJavaBeanSerializer(serializeBeanInfoBuildBeanInfo));
            return;
        }
        if (objectWriter instanceof JavaBeanSerializer) {
            SerializeBeanInfo serializeBeanInfo = ((JavaBeanSerializer) objectWriter).beanInfo;
            int i = serializeBeanInfo.features;
            if (z) {
                serializeBeanInfo.features = serializerFeature.mask | serializeBeanInfo.features;
            } else {
                serializeBeanInfo.features = (~serializerFeature.mask) & serializeBeanInfo.features;
            }
            if (i == serializeBeanInfo.features || objectWriter.getClass() == JavaBeanSerializer.class) {
                return;
            }
            put((Type) cls, createJavaBeanSerializer(serializeBeanInfo));
        }
    }

    public ObjectSerializer getObjectWriter(Class<?> cls) {
        return getObjectWriter(cls, true);
    }

    /* JADX WARN: Code duplicated, block: B:189:0x036e A[Catch: ClassNotFoundException -> 0x0385, TryCatch #4 {ClassNotFoundException -> 0x0385, blocks: (B:187:0x035c, B:189:0x036e, B:191:0x0376), top: B:272:0x035c }] */
    /* JADX WARN: Code duplicated, block: B:195:0x0382 A[LOOP:7: B:188:0x036c->B:195:0x0382, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:208:0x03a6  */
    /* JADX WARN: Code duplicated, block: B:212:0x03cc A[Catch: ClassNotFoundException -> 0x03e3, TryCatch #2 {ClassNotFoundException -> 0x03e3, blocks: (B:210:0x03ae, B:212:0x03cc, B:214:0x03d4), top: B:268:0x03ae }] */
    /* JADX WARN: Code duplicated, block: B:218:0x03e0 A[LOOP:8: B:211:0x03ca->B:218:0x03e0, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:222:0x03ec  */
    /* JADX WARN: Code duplicated, block: B:228:0x0402  */
    /* JADX WARN: Code duplicated, block: B:230:0x040e  */
    /* JADX WARN: Code duplicated, block: B:232:0x0414  */
    /* JADX WARN: Code duplicated, block: B:234:0x0418  */
    /* JADX WARN: Code duplicated, block: B:235:0x041b  */
    /* JADX WARN: Code duplicated, block: B:237:0x041f  */
    /* JADX WARN: Code duplicated, block: B:240:0x042e A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:242:0x0431  */
    /* JADX WARN: Code duplicated, block: B:246:0x0438  */
    /* JADX WARN: Code duplicated, block: B:249:0x0442  */
    /* JADX WARN: Code duplicated, block: B:253:0x0457  */
    /* JADX WARN: Code duplicated, block: B:312:0x0376 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:314:0x03d4 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:315:0x0436 A[EDGE_INSN: B:315:0x0436->B:245:0x0436 BREAK  A[LOOP:9: B:236:0x041d->B:243:0x0432], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:316:0x0435 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:318:0x0432 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:327:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r19v0, types: [com.alibaba.fastjson.serializer.SerializeConfig] */
    /* JADX WARN: Type inference failed for: r2v10 */
    /* JADX WARN: Type inference failed for: r2v11, types: [com.alibaba.fastjson.serializer.ObjectSerializer] */
    /* JADX WARN: Type inference failed for: r2v12, types: [com.alibaba.fastjson.serializer.MiscCodec, com.alibaba.fastjson.serializer.ObjectSerializer] */
    /* JADX WARN: Type inference failed for: r2v13, types: [com.alibaba.fastjson.serializer.CalendarCodec, com.alibaba.fastjson.serializer.ObjectSerializer] */
    /* JADX WARN: Type inference failed for: r2v14 */
    /* JADX WARN: Type inference failed for: r2v15 */
    /* JADX WARN: Type inference failed for: r2v16 */
    /* JADX WARN: Type inference failed for: r2v17 */
    /* JADX WARN: Type inference failed for: r2v18 */
    /* JADX WARN: Type inference failed for: r2v19 */
    /* JADX WARN: Type inference failed for: r2v20 */
    /* JADX WARN: Type inference failed for: r2v21, types: [com.alibaba.fastjson.serializer.ObjectSerializer] */
    /* JADX WARN: Type inference failed for: r2v26 */
    /* JADX WARN: Type inference failed for: r2v27 */
    /* JADX WARN: Type inference failed for: r2v28 */
    /* JADX WARN: Type inference failed for: r2v29 */
    /* JADX WARN: Type inference failed for: r2v30 */
    /* JADX WARN: Type inference failed for: r2v31 */
    /* JADX WARN: Type inference failed for: r2v32 */
    /* JADX WARN: Type inference failed for: r2v33 */
    /* JADX WARN: Type inference failed for: r2v34 */
    /* JADX WARN: Type inference failed for: r2v35 */
    /* JADX WARN: Type inference failed for: r2v36 */
    /* JADX WARN: Type inference failed for: r2v37 */
    /* JADX WARN: Type inference failed for: r2v38 */
    /* JADX WARN: Type inference failed for: r2v39 */
    /* JADX WARN: Type inference failed for: r2v40, types: [com.alibaba.fastjson.serializer.MiscCodec, com.alibaba.fastjson.serializer.ObjectSerializer] */
    /* JADX WARN: Type inference failed for: r2v41, types: [com.alibaba.fastjson.serializer.MiscCodec, com.alibaba.fastjson.serializer.ObjectSerializer] */
    /* JADX WARN: Type inference failed for: r2v42, types: [com.alibaba.fastjson.serializer.ObjectSerializer, com.alibaba.fastjson.serializer.ToStringSerializer] */
    /* JADX WARN: Type inference failed for: r2v43, types: [com.alibaba.fastjson.serializer.ClobSeriliazer, com.alibaba.fastjson.serializer.ObjectSerializer] */
    /* JADX WARN: Type inference failed for: r2v44, types: [com.alibaba.fastjson.serializer.EnumerationSerializer, com.alibaba.fastjson.serializer.ObjectSerializer] */
    /* JADX WARN: Type inference failed for: r2v45, types: [com.alibaba.fastjson.serializer.ObjectSerializer, com.alibaba.fastjson.serializer.ToStringSerializer] */
    /* JADX WARN: Type inference failed for: r2v46, types: [com.alibaba.fastjson.serializer.AppendableSerializer, com.alibaba.fastjson.serializer.ObjectSerializer] */
    /* JADX WARN: Type inference failed for: r2v49 */
    /* JADX WARN: Type inference failed for: r2v51 */
    /* JADX WARN: Type inference failed for: r2v55, types: [com.alibaba.fastjson.serializer.EnumSerializer, com.alibaba.fastjson.serializer.ObjectSerializer] */
    /* JADX WARN: Type inference failed for: r2v57, types: [com.alibaba.fastjson.serializer.ObjectSerializer] */
    /* JADX WARN: Type inference failed for: r2v61, types: [com.alibaba.fastjson.serializer.EnumSerializer, com.alibaba.fastjson.serializer.ObjectSerializer] */
    /* JADX WARN: Type inference failed for: r2v63, types: [com.alibaba.fastjson.serializer.ObjectSerializer] */
    /* JADX WARN: Type inference failed for: r2v64, types: [com.alibaba.fastjson.serializer.MiscCodec, com.alibaba.fastjson.serializer.ObjectSerializer] */
    /* JADX WARN: Type inference failed for: r2v65, types: [com.alibaba.fastjson.serializer.JSONSerializableSerializer, com.alibaba.fastjson.serializer.ObjectSerializer] */
    /* JADX WARN: Type inference failed for: r2v66, types: [com.alibaba.fastjson.serializer.JSONAwareSerializer, com.alibaba.fastjson.serializer.ObjectSerializer] */
    /* JADX WARN: Type inference failed for: r2v67, types: [com.alibaba.fastjson.serializer.DateCodec, com.alibaba.fastjson.serializer.ObjectSerializer] */
    /* JADX WARN: Type inference failed for: r2v68, types: [com.alibaba.fastjson.serializer.CollectionCodec, com.alibaba.fastjson.serializer.ObjectSerializer] */
    /* JADX WARN: Type inference failed for: r2v69, types: [com.alibaba.fastjson.serializer.ListSerializer, com.alibaba.fastjson.serializer.ObjectSerializer] */
    /* JADX WARN: Type inference failed for: r2v70, types: [com.alibaba.fastjson.serializer.ObjectSerializer] */
    /* JADX WARN: Type inference failed for: r2v73, types: [com.alibaba.fastjson.serializer.MapSerializer, com.alibaba.fastjson.serializer.ObjectSerializer] */
    /* JADX WARN: Type inference failed for: r2v76, types: [com.alibaba.fastjson.serializer.ObjectSerializer] */
    /* JADX WARN: Type inference failed for: r2v80 */
    /* JADX WARN: Type inference failed for: r2v81 */
    /* JADX WARN: Type inference failed for: r2v82 */
    /* JADX WARN: Type inference failed for: r2v83 */
    /* JADX WARN: Type inference failed for: r2v84 */
    /* JADX WARN: Type inference failed for: r2v85 */
    /* JADX WARN: Type inference failed for: r2v86 */
    /* JADX WARN: Type inference failed for: r2v87 */
    /* JADX WARN: Type inference failed for: r8v13, types: [int] */
    private ObjectSerializer getObjectWriter(Class<?> cls, boolean z) {
        ?? CreateJavaBeanSerializer;
        Class<?>[] interfaces;
        int length;
        Class<?> cls2;
        Class<?> cls3;
        int i;
        JodaCodec jodaCodec;
        MiscCodec miscCodec;
        int i2;
        GuavaCodec guavaCodec;
        ClassLoader classLoader;
        ObjectSerializer objectSerializer = this.serializers.get(cls);
        ObjectSerializer objectSerializer2 = objectSerializer;
        if (objectSerializer == null) {
            try {
                for (Object obj : ServiceLoader.load(AutowiredObjectSerializer.class, Thread.currentThread().getContextClassLoader())) {
                    if (obj instanceof AutowiredObjectSerializer) {
                        AutowiredObjectSerializer autowiredObjectSerializer = (AutowiredObjectSerializer) obj;
                        Iterator<Type> it = autowiredObjectSerializer.getAutowiredFor().iterator();
                        while (it.hasNext()) {
                            put(it.next(), autowiredObjectSerializer);
                        }
                    }
                }
            } catch (ClassCastException unused) {
            }
            objectSerializer2 = this.serializers.get(cls);
        }
        if (objectSerializer2 == 0 && (classLoader = JSON.class.getClassLoader()) != Thread.currentThread().getContextClassLoader()) {
            try {
                for (Object obj2 : ServiceLoader.load(AutowiredObjectSerializer.class, classLoader)) {
                    if (obj2 instanceof AutowiredObjectSerializer) {
                        AutowiredObjectSerializer autowiredObjectSerializer2 = (AutowiredObjectSerializer) obj2;
                        Iterator<Type> it2 = autowiredObjectSerializer2.getAutowiredFor().iterator();
                        while (it2.hasNext()) {
                            put(it2.next(), autowiredObjectSerializer2);
                        }
                    }
                }
            } catch (ClassCastException unused2) {
            }
            objectSerializer2 = this.serializers.get(cls);
        }
        if (objectSerializer2 != 0) {
            return objectSerializer2;
        }
        String name = cls.getName();
        if (Map.class.isAssignableFrom(cls)) {
            CreateJavaBeanSerializer = MapSerializer.instance;
            put(cls, CreateJavaBeanSerializer);
        } else if (List.class.isAssignableFrom(cls)) {
            CreateJavaBeanSerializer = ListSerializer.instance;
            put(cls, CreateJavaBeanSerializer);
        } else if (Collection.class.isAssignableFrom(cls)) {
            CreateJavaBeanSerializer = CollectionCodec.instance;
            put(cls, CreateJavaBeanSerializer);
        } else if (Date.class.isAssignableFrom(cls)) {
            CreateJavaBeanSerializer = DateCodec.instance;
            put(cls, CreateJavaBeanSerializer);
        } else if (JSONAware.class.isAssignableFrom(cls)) {
            CreateJavaBeanSerializer = JSONAwareSerializer.instance;
            put(cls, CreateJavaBeanSerializer);
        } else if (JSONSerializable.class.isAssignableFrom(cls)) {
            CreateJavaBeanSerializer = JSONSerializableSerializer.instance;
            put(cls, CreateJavaBeanSerializer);
        } else if (JSONStreamAware.class.isAssignableFrom(cls)) {
            CreateJavaBeanSerializer = MiscCodec.instance;
            put(cls, CreateJavaBeanSerializer);
        } else if (cls.isEnum()) {
            JSONType jSONType = (JSONType) TypeUtils.getAnnotation(cls, JSONType.class);
            if (jSONType != null && jSONType.serializeEnumAsJavaBean()) {
                CreateJavaBeanSerializer = createJavaBeanSerializer(cls);
                put(cls, CreateJavaBeanSerializer);
            } else {
                CreateJavaBeanSerializer = EnumSerializer.instance;
                put(cls, CreateJavaBeanSerializer);
            }
        } else {
            Class<? super Object> superclass = cls.getSuperclass();
            if (superclass != null && superclass.isEnum()) {
                JSONType jSONType2 = (JSONType) TypeUtils.getAnnotation(superclass, JSONType.class);
                if (jSONType2 != null && jSONType2.serializeEnumAsJavaBean()) {
                    CreateJavaBeanSerializer = createJavaBeanSerializer(cls);
                    put(cls, CreateJavaBeanSerializer);
                } else {
                    CreateJavaBeanSerializer = EnumSerializer.instance;
                    put(cls, CreateJavaBeanSerializer);
                }
            } else if (cls.isArray()) {
                Class<?> componentType = cls.getComponentType();
                ArraySerializer arraySerializer = new ArraySerializer(componentType, getObjectWriter(componentType));
                put(cls, arraySerializer);
                CreateJavaBeanSerializer = arraySerializer;
            } else {
                Class<?> cls4 = null;
                if (Throwable.class.isAssignableFrom(cls)) {
                    SerializeBeanInfo serializeBeanInfoBuildBeanInfo = TypeUtils.buildBeanInfo(cls, null, this.propertyNamingStrategy);
                    serializeBeanInfoBuildBeanInfo.features |= SerializerFeature.WriteClassName.mask;
                    JavaBeanSerializer javaBeanSerializer = new JavaBeanSerializer(serializeBeanInfoBuildBeanInfo);
                    put(cls, javaBeanSerializer);
                    CreateJavaBeanSerializer = javaBeanSerializer;
                } else if (TimeZone.class.isAssignableFrom(cls) || Map.Entry.class.isAssignableFrom(cls)) {
                    CreateJavaBeanSerializer = MiscCodec.instance;
                    put(cls, CreateJavaBeanSerializer);
                } else if (Appendable.class.isAssignableFrom(cls)) {
                    CreateJavaBeanSerializer = AppendableSerializer.instance;
                    put(cls, CreateJavaBeanSerializer);
                } else if (Charset.class.isAssignableFrom(cls)) {
                    CreateJavaBeanSerializer = ToStringSerializer.instance;
                    put(cls, CreateJavaBeanSerializer);
                } else if (Enumeration.class.isAssignableFrom(cls)) {
                    CreateJavaBeanSerializer = EnumerationSerializer.instance;
                    put(cls, CreateJavaBeanSerializer);
                } else if (Calendar.class.isAssignableFrom(cls) || XMLGregorianCalendar.class.isAssignableFrom(cls)) {
                    CreateJavaBeanSerializer = CalendarCodec.instance;
                    put(cls, CreateJavaBeanSerializer);
                } else if (Clob.class.isAssignableFrom(cls)) {
                    CreateJavaBeanSerializer = ClobSeriliazer.instance;
                    put(cls, CreateJavaBeanSerializer);
                } else if (TypeUtils.isPath(cls)) {
                    CreateJavaBeanSerializer = ToStringSerializer.instance;
                    put(cls, CreateJavaBeanSerializer);
                } else if (Iterator.class.isAssignableFrom(cls)) {
                    CreateJavaBeanSerializer = MiscCodec.instance;
                    put(cls, CreateJavaBeanSerializer);
                } else if (Node.class.isAssignableFrom(cls)) {
                    CreateJavaBeanSerializer = MiscCodec.instance;
                    put(cls, CreateJavaBeanSerializer);
                } else {
                    int i3 = 0;
                    if (name.startsWith("java.awt.") && AwtCodec.support(cls) && !awtError) {
                        try {
                            for (String str : new String[]{"java.awt.Color", "java.awt.Font", "java.awt.Point", "java.awt.Rectangle"}) {
                                if (str.equals(name)) {
                                    Class<?> cls5 = Class.forName(str);
                                    AwtCodec awtCodec = AwtCodec.instance;
                                    try {
                                        put(cls5, awtCodec);
                                        return awtCodec;
                                    } catch (Throwable unused3) {
                                        objectSerializer2 = awtCodec;
                                    }
                                }
                                awtError = true;
                            }
                        } catch (Throwable unused4) {
                        }
                    }
                    if (!jdk8Error && (name.startsWith("java.time.") || name.startsWith("java.util.Optional") || name.equals("java.util.concurrent.atomic.LongAdder") || name.equals("java.util.concurrent.atomic.DoubleAdder"))) {
                        try {
                            String[] strArr = {"java.time.LocalDateTime", "java.time.LocalDate", "java.time.LocalTime", "java.time.ZonedDateTime", "java.time.OffsetDateTime", "java.time.OffsetTime", "java.time.ZoneOffset", "java.time.ZoneRegion", "java.time.Period", "java.time.Duration", "java.time.Instant"};
                            ?? length2 = strArr.length;
                            for (String str2 : strArr) {
                                try {
                                    if (str2.equals(name)) {
                                        Class<?> cls6 = Class.forName(str2);
                                        Jdk8DateCodec jdk8DateCodec = Jdk8DateCodec.instance;
                                        put(cls6, jdk8DateCodec);
                                        return jdk8DateCodec;
                                    }
                                } catch (Throwable unused5) {
                                    objectSerializer2 = length2;
                                    jdk8Error = true;
                                }
                            }
                            for (String str3 : new String[]{"java.util.Optional", "java.util.OptionalDouble", "java.util.OptionalInt", "java.util.OptionalLong"}) {
                                if (str3.equals(name)) {
                                    Class<?> cls7 = Class.forName(str3);
                                    OptionalCodec optionalCodec = OptionalCodec.instance;
                                    put(cls7, optionalCodec);
                                    return optionalCodec;
                                }
                            }
                            for (String str4 : new String[]{"java.util.concurrent.atomic.LongAdder", "java.util.concurrent.atomic.DoubleAdder"}) {
                                if (str4.equals(name)) {
                                    Class<?> cls8 = Class.forName(str4);
                                    AdderSerializer adderSerializer = AdderSerializer.instance;
                                    put(cls8, adderSerializer);
                                    return adderSerializer;
                                }
                            }
                        } catch (Throwable unused6) {
                        }
                    }
                    if (!oracleJdbcError && name.startsWith("oracle.sql.")) {
                        try {
                            for (String str5 : new String[]{"oracle.sql.DATE", "oracle.sql.TIMESTAMP"}) {
                                if (str5.equals(name)) {
                                    Class<?> cls9 = Class.forName(str5);
                                    DateCodec dateCodec = DateCodec.instance;
                                    try {
                                        put(cls9, dateCodec);
                                        return dateCodec;
                                    } catch (Throwable unused7) {
                                        objectSerializer2 = dateCodec;
                                    }
                                }
                                oracleJdbcError = true;
                            }
                        } catch (Throwable unused8) {
                        }
                    }
                    ?? r2 = objectSerializer2;
                    if (!springfoxError && name.equals("springfox.documentation.spring.web.json.Json")) {
                        try {
                            r2 = objectSerializer2;
                            Class<?> cls10 = Class.forName("springfox.documentation.spring.web.json.Json");
                            SwaggerJsonSerializer swaggerJsonSerializer = SwaggerJsonSerializer.instance;
                            try {
                                put(cls10, swaggerJsonSerializer);
                                return swaggerJsonSerializer;
                            } catch (ClassNotFoundException unused9) {
                                objectSerializer2 = swaggerJsonSerializer;
                                springfoxError = true;
                                r2 = objectSerializer2;
                                r2 = objectSerializer2;
                                if (!guavaError) {
                                    try {
                                        for (String str6 : new String[]{"com.google.common.collect.HashMultimap", "com.google.common.collect.LinkedListMultimap", "com.google.common.collect.LinkedHashMultimap", "com.google.common.collect.ArrayListMultimap", "com.google.common.collect.TreeMultimap"}) {
                                            if (str6.equals(name)) {
                                                Class<?> cls11 = Class.forName(str6);
                                                guavaCodec = GuavaCodec.instance;
                                                try {
                                                    put(cls11, guavaCodec);
                                                    return guavaCodec;
                                                } catch (ClassNotFoundException unused10) {
                                                    r2 = guavaCodec;
                                                }
                                            }
                                            guavaError = true;
                                        }
                                    } catch (ClassNotFoundException unused11) {
                                    }
                                }
                                CreateJavaBeanSerializer = r2;
                                if (!jsonnullError) {
                                    try {
                                        CreateJavaBeanSerializer = r2;
                                        Class<?> cls12 = Class.forName("net.sf.json.JSONNull");
                                        miscCodec = MiscCodec.instance;
                                        try {
                                            put(cls12, miscCodec);
                                            return miscCodec;
                                        } catch (ClassNotFoundException unused12) {
                                            r2 = miscCodec;
                                            jsonnullError = true;
                                            CreateJavaBeanSerializer = r2;
                                            CreateJavaBeanSerializer = r2;
                                            if (!jodaError) {
                                                try {
                                                    for (String str7 : new String[]{"org.joda.time.LocalDate", "org.joda.time.LocalDateTime", "org.joda.time.LocalTime", "org.joda.time.Instant", "org.joda.time.DateTime", "org.joda.time.Period", "org.joda.time.Duration", "org.joda.time.DateTimeZone", "org.joda.time.UTCDateTimeZone", "org.joda.time.tz.CachedDateTimeZone", "org.joda.time.tz.FixedDateTimeZone"}) {
                                                        if (str7.equals(name)) {
                                                            Class<?> cls13 = Class.forName(str7);
                                                            jodaCodec = JodaCodec.instance;
                                                            try {
                                                                put(cls13, jodaCodec);
                                                                return jodaCodec;
                                                            } catch (ClassNotFoundException unused13) {
                                                                CreateJavaBeanSerializer = jodaCodec;
                                                            }
                                                        }
                                                        jodaError = true;
                                                    }
                                                } catch (ClassNotFoundException unused14) {
                                                }
                                            }
                                            interfaces = cls.getInterfaces();
                                            if (interfaces.length != 1) {
                                            }
                                            if (TypeUtils.isProxy(cls)) {
                                                ObjectSerializer objectWriter = getObjectWriter(cls.getSuperclass());
                                                put(cls, objectWriter);
                                                return objectWriter;
                                            }
                                            if (Proxy.isProxyClass(cls)) {
                                                if (interfaces.length == 2) {
                                                    cls4 = interfaces[1];
                                                } else {
                                                    length = interfaces.length;
                                                    cls2 = null;
                                                    while (true) {
                                                        if (i3 >= length) {
                                                            cls4 = cls2;
                                                            break;
                                                        }
                                                        cls3 = interfaces[i3];
                                                        if (!cls3.getName().startsWith("org.springframework.aop.")) {
                                                            if (cls2 != null) {
                                                                break;
                                                            }
                                                            cls2 = cls3;
                                                        }
                                                        i3++;
                                                    }
                                                }
                                                if (cls4 != null) {
                                                    ObjectSerializer objectWriter2 = getObjectWriter(cls4);
                                                    put(cls, objectWriter2);
                                                    return objectWriter2;
                                                }
                                            }
                                            if (z) {
                                                CreateJavaBeanSerializer = createJavaBeanSerializer(cls);
                                                put(cls, CreateJavaBeanSerializer);
                                            }
                                            if (CreateJavaBeanSerializer == 0) {
                                                return this.serializers.get(cls);
                                            }
                                            return CreateJavaBeanSerializer;
                                        }
                                    } catch (ClassNotFoundException unused15) {
                                    }
                                }
                                CreateJavaBeanSerializer = r2;
                                if (!jodaError) {
                                    while (i < r8) {
                                        if (str7.equals(name)) {
                                            Class<?> cls14 = Class.forName(str7);
                                            jodaCodec = JodaCodec.instance;
                                            put(cls14, jodaCodec);
                                            return jodaCodec;
                                        }
                                        jodaError = true;
                                    }
                                }
                                interfaces = cls.getInterfaces();
                                if (interfaces.length != 1) {
                                }
                                if (TypeUtils.isProxy(cls)) {
                                    ObjectSerializer objectWriter3 = getObjectWriter(cls.getSuperclass());
                                    put(cls, objectWriter3);
                                    return objectWriter3;
                                }
                                if (Proxy.isProxyClass(cls)) {
                                    if (interfaces.length == 2) {
                                        cls4 = interfaces[1];
                                    } else {
                                        length = interfaces.length;
                                        cls2 = null;
                                        while (true) {
                                            if (i3 >= length) {
                                                cls4 = cls2;
                                                break;
                                            }
                                            cls3 = interfaces[i3];
                                            if (!cls3.getName().startsWith("org.springframework.aop.")) {
                                                if (cls2 != null) {
                                                    break;
                                                    break;
                                                }
                                                cls2 = cls3;
                                            }
                                            i3++;
                                        }
                                    }
                                    if (cls4 != null) {
                                        ObjectSerializer objectWriter4 = getObjectWriter(cls4);
                                        put(cls, objectWriter4);
                                        return objectWriter4;
                                    }
                                }
                                if (z) {
                                    CreateJavaBeanSerializer = createJavaBeanSerializer(cls);
                                    put(cls, CreateJavaBeanSerializer);
                                }
                                if (CreateJavaBeanSerializer == 0) {
                                    return this.serializers.get(cls);
                                }
                                return CreateJavaBeanSerializer;
                            }
                        } catch (ClassNotFoundException unused16) {
                        }
                    }
                    r2 = objectSerializer2;
                    if (!guavaError && name.startsWith("com.google.common.collect.")) {
                        while (i2 < r8) {
                            if (str6.equals(name)) {
                                Class<?> cls15 = Class.forName(str6);
                                guavaCodec = GuavaCodec.instance;
                                put(cls15, guavaCodec);
                                return guavaCodec;
                            }
                            guavaError = true;
                        }
                    }
                    CreateJavaBeanSerializer = r2;
                    if (!jsonnullError && name.equals("net.sf.json.JSONNull")) {
                        CreateJavaBeanSerializer = r2;
                        Class<?> cls16 = Class.forName("net.sf.json.JSONNull");
                        miscCodec = MiscCodec.instance;
                        put(cls16, miscCodec);
                        return miscCodec;
                    }
                    CreateJavaBeanSerializer = r2;
                    if (!jodaError && name.startsWith("org.joda.")) {
                        while (i < r8) {
                            if (str7.equals(name)) {
                                Class<?> cls17 = Class.forName(str7);
                                jodaCodec = JodaCodec.instance;
                                put(cls17, jodaCodec);
                                return jodaCodec;
                            }
                            jodaError = true;
                        }
                    }
                    interfaces = cls.getInterfaces();
                    if (interfaces.length != 1 && interfaces[0].isAnnotation()) {
                        put(cls, AnnotationSerializer.instance);
                        return AnnotationSerializer.instance;
                    }
                    if (TypeUtils.isProxy(cls)) {
                        ObjectSerializer objectWriter5 = getObjectWriter(cls.getSuperclass());
                        put(cls, objectWriter5);
                        return objectWriter5;
                    }
                    if (Proxy.isProxyClass(cls)) {
                        if (interfaces.length == 2) {
                            cls4 = interfaces[1];
                        } else {
                            length = interfaces.length;
                            cls2 = null;
                            while (true) {
                                if (i3 >= length) {
                                    cls4 = cls2;
                                    break;
                                }
                                cls3 = interfaces[i3];
                                if (!cls3.getName().startsWith("org.springframework.aop.")) {
                                    if (cls2 != null) {
                                        break;
                                        break;
                                    }
                                    cls2 = cls3;
                                }
                                i3++;
                            }
                        }
                        if (cls4 != null) {
                            ObjectSerializer objectWriter6 = getObjectWriter(cls4);
                            put(cls, objectWriter6);
                            return objectWriter6;
                        }
                    }
                    if (z) {
                        CreateJavaBeanSerializer = createJavaBeanSerializer(cls);
                        put(cls, CreateJavaBeanSerializer);
                    }
                }
            }
        }
        if (CreateJavaBeanSerializer == 0) {
            return this.serializers.get(cls);
        }
        return CreateJavaBeanSerializer;
    }

    public final ObjectSerializer get(Type type) {
        return this.serializers.get(type);
    }

    public boolean put(Object obj, Object obj2) {
        return put((Type) obj, (ObjectSerializer) obj2);
    }

    public boolean put(Type type, ObjectSerializer objectSerializer) {
        return this.serializers.put(type, objectSerializer);
    }

    public void configEnumAsJavaBean(Class<? extends Enum>... clsArr) {
        for (Class<? extends Enum> cls : clsArr) {
            put((Type) cls, createJavaBeanSerializer(cls));
        }
    }

    public void setPropertyNamingStrategy(PropertyNamingStrategy propertyNamingStrategy) {
        this.propertyNamingStrategy = propertyNamingStrategy;
    }

    public void clearSerializers() {
        this.serializers.clear();
        initSerializers();
    }
}
