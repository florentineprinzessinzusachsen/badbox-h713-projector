package com.alibaba.fastjson.parser;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import com.alibaba.fastjson.JSONPath;
import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;
import com.alibaba.fastjson.asm.ClassReader;
import com.alibaba.fastjson.asm.TypeCollector;
import com.alibaba.fastjson.parser.deserializer.ASMDeserializerFactory;
import com.alibaba.fastjson.parser.deserializer.ArrayListTypeFieldDeserializer;
import com.alibaba.fastjson.parser.deserializer.AutowiredObjectDeserializer;
import com.alibaba.fastjson.parser.deserializer.DefaultFieldDeserializer;
import com.alibaba.fastjson.parser.deserializer.EnumDeserializer;
import com.alibaba.fastjson.parser.deserializer.FieldDeserializer;
import com.alibaba.fastjson.parser.deserializer.JSONPDeserializer;
import com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer;
import com.alibaba.fastjson.parser.deserializer.JavaObjectDeserializer;
import com.alibaba.fastjson.parser.deserializer.Jdk8DateCodec;
import com.alibaba.fastjson.parser.deserializer.MapDeserializer;
import com.alibaba.fastjson.parser.deserializer.NumberDeserializer;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.parser.deserializer.OptionalCodec;
import com.alibaba.fastjson.parser.deserializer.PropertyProcessable;
import com.alibaba.fastjson.parser.deserializer.PropertyProcessableDeserializer;
import com.alibaba.fastjson.parser.deserializer.SqlDateDeserializer;
import com.alibaba.fastjson.parser.deserializer.StackTraceElementDeserializer;
import com.alibaba.fastjson.parser.deserializer.ThrowableDeserializer;
import com.alibaba.fastjson.parser.deserializer.TimeDeserializer;
import com.alibaba.fastjson.serializer.AtomicCodec;
import com.alibaba.fastjson.serializer.AwtCodec;
import com.alibaba.fastjson.serializer.BigDecimalCodec;
import com.alibaba.fastjson.serializer.BigIntegerCodec;
import com.alibaba.fastjson.serializer.BooleanCodec;
import com.alibaba.fastjson.serializer.CalendarCodec;
import com.alibaba.fastjson.serializer.CharArrayCodec;
import com.alibaba.fastjson.serializer.CharacterCodec;
import com.alibaba.fastjson.serializer.CollectionCodec;
import com.alibaba.fastjson.serializer.DateCodec;
import com.alibaba.fastjson.serializer.FloatCodec;
import com.alibaba.fastjson.serializer.IntegerCodec;
import com.alibaba.fastjson.serializer.JodaCodec;
import com.alibaba.fastjson.serializer.LongCodec;
import com.alibaba.fastjson.serializer.MiscCodec;
import com.alibaba.fastjson.serializer.ObjectArrayCodec;
import com.alibaba.fastjson.serializer.ReferenceCodec;
import com.alibaba.fastjson.serializer.StringCodec;
import com.alibaba.fastjson.util.ASMClassLoader;
import com.alibaba.fastjson.util.ASMUtils;
import com.alibaba.fastjson.util.FieldInfo;
import com.alibaba.fastjson.util.IOUtils;
import com.alibaba.fastjson.util.IdentityHashMap;
import com.alibaba.fastjson.util.JavaBeanInfo;
import com.alibaba.fastjson.util.ServiceLoader;
import com.alibaba.fastjson.util.TypeUtils;
import java.io.Closeable;
import java.io.File;
import java.io.InputStream;
import java.io.Serializable;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URL;
import java.nio.charset.Charset;
import java.security.AccessControlException;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Currency;
import java.util.EventListener;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TimeZone;
import java.util.TreeMap;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicLongArray;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Pattern;
import javax.sql.DataSource;
import javax.sql.RowSet;
import javax.xml.datatype.XMLGregorianCalendar;

/* JADX INFO: loaded from: classes.dex */
public class ParserConfig {
    public static final String AUTOTYPE_ACCEPT = "fastjson.parser.autoTypeAccept";
    private static final String[] AUTO_TYPE_ACCEPT_LIST;
    private static boolean awtError = false;
    public static ParserConfig global = null;
    private static boolean jdk8Error = false;
    private static boolean jodaError = false;
    private long[] acceptHashCodes;
    private boolean asmEnable;
    protected ASMDeserializerFactory asmFactory;
    private boolean autoTypeSupport;
    public boolean compatibleWithJavaBean;
    protected ClassLoader defaultClassLoader;
    private long[] denyHashCodes;
    private final IdentityHashMap<Type, ObjectDeserializer> deserializers;
    public final boolean fieldBased;
    private boolean jacksonCompatible;
    public PropertyNamingStrategy propertyNamingStrategy;
    public final SymbolTable symbolTable;
    public static final String DENY_PROPERTY = "fastjson.parser.deny";
    public static final String[] DENYS = splitItemsFormProperty(IOUtils.getStringProperty(DENY_PROPERTY));
    public static final String AUTOTYPE_SUPPORT_PROPERTY = "fastjson.parser.autoTypeSupport";
    public static final boolean AUTO_SUPPORT = "true".equals(IOUtils.getStringProperty(AUTOTYPE_SUPPORT_PROPERTY));

    static {
        String[] strArrSplitItemsFormProperty = splitItemsFormProperty(IOUtils.getStringProperty(AUTOTYPE_ACCEPT));
        if (strArrSplitItemsFormProperty == null) {
            strArrSplitItemsFormProperty = new String[0];
        }
        AUTO_TYPE_ACCEPT_LIST = strArrSplitItemsFormProperty;
        global = new ParserConfig();
    }

    public static ParserConfig getGlobalInstance() {
        return global;
    }

    public ParserConfig() {
        this(false);
    }

    public ParserConfig(boolean z) {
        this(null, null, z);
    }

    public ParserConfig(ClassLoader classLoader) {
        this(null, classLoader, false);
    }

    public ParserConfig(ASMDeserializerFactory aSMDeserializerFactory) {
        this(aSMDeserializerFactory, null, false);
    }

    private ParserConfig(ASMDeserializerFactory aSMDeserializerFactory, ClassLoader classLoader, boolean z) {
        this.deserializers = new IdentityHashMap<>();
        this.asmEnable = !ASMUtils.IS_ANDROID;
        this.symbolTable = new SymbolTable(4096);
        this.autoTypeSupport = AUTO_SUPPORT;
        this.jacksonCompatible = false;
        this.compatibleWithJavaBean = TypeUtils.compatibleWithJavaBean;
        this.denyHashCodes = new long[]{-8720046426850100497L, -8165637398350707645L, -8109300701639721088L, -8083514888460375884L, -7966123100503199569L, -7921218830998286408L, -7768608037458185275L, -7766605818834748097L, -6835437086156813536L, -6179589609550493385L, -5194641081268104286L, -4837536971810737970L, -4082057040235125754L, -3935185854875733362L, -2753427844400776271L, -2364987994247679115L, -2262244760619952081L, -1872417015366588117L, -1589194880214235129L, -254670111376247151L, -190281065685395680L, 33238344207745342L, 313864100207897507L, 1073634739308289776L, 1203232727967308606L, 1459860845934817624L, 1502845958873959152L, 3547627781654598988L, 3730752432285826863L, 3794316665763266033L, 4147696707147271408L, 4904007817188630457L, 5347909877633654828L, 5450448828334921485L, 5688200883751798389L, 5751393439502795295L, 5944107969236155580L, 6742705432718011780L, 7017492163108594270L, 7179336928365889465L, 7442624256860549330L, 8389032537095247355L, 8409640769019589119L, 8838294710098435315L};
        long[] jArr = new long[AUTO_TYPE_ACCEPT_LIST.length + 1];
        for (int i = 0; i < AUTO_TYPE_ACCEPT_LIST.length; i++) {
            jArr[i] = TypeUtils.fnv1a_64(AUTO_TYPE_ACCEPT_LIST[i]);
        }
        jArr[jArr.length - 1] = -6293031534589903644L;
        Arrays.sort(jArr);
        this.acceptHashCodes = jArr;
        this.fieldBased = z;
        if (aSMDeserializerFactory == null && !ASMUtils.IS_ANDROID) {
            try {
                if (classLoader == null) {
                    aSMDeserializerFactory = new ASMDeserializerFactory(new ASMClassLoader());
                } else {
                    aSMDeserializerFactory = new ASMDeserializerFactory(classLoader);
                }
            } catch (ExceptionInInitializerError | NoClassDefFoundError | AccessControlException unused) {
            }
        }
        this.asmFactory = aSMDeserializerFactory;
        if (aSMDeserializerFactory == null) {
            this.asmEnable = false;
        }
        initDeserializers();
        addItemsToDeny(DENYS);
        addItemsToAccept(AUTO_TYPE_ACCEPT_LIST);
    }

    private void initDeserializers() {
        this.deserializers.put(SimpleDateFormat.class, MiscCodec.instance);
        this.deserializers.put(Timestamp.class, SqlDateDeserializer.instance_timestamp);
        this.deserializers.put(Date.class, SqlDateDeserializer.instance);
        this.deserializers.put(Time.class, TimeDeserializer.instance);
        this.deserializers.put(java.util.Date.class, DateCodec.instance);
        this.deserializers.put(Calendar.class, CalendarCodec.instance);
        this.deserializers.put(XMLGregorianCalendar.class, CalendarCodec.instance);
        this.deserializers.put(JSONObject.class, MapDeserializer.instance);
        this.deserializers.put(JSONArray.class, CollectionCodec.instance);
        this.deserializers.put(Map.class, MapDeserializer.instance);
        this.deserializers.put(HashMap.class, MapDeserializer.instance);
        this.deserializers.put(LinkedHashMap.class, MapDeserializer.instance);
        this.deserializers.put(TreeMap.class, MapDeserializer.instance);
        this.deserializers.put(ConcurrentMap.class, MapDeserializer.instance);
        this.deserializers.put(ConcurrentHashMap.class, MapDeserializer.instance);
        this.deserializers.put(Collection.class, CollectionCodec.instance);
        this.deserializers.put(List.class, CollectionCodec.instance);
        this.deserializers.put(ArrayList.class, CollectionCodec.instance);
        this.deserializers.put(Object.class, JavaObjectDeserializer.instance);
        this.deserializers.put(String.class, StringCodec.instance);
        this.deserializers.put(StringBuffer.class, StringCodec.instance);
        this.deserializers.put(StringBuilder.class, StringCodec.instance);
        this.deserializers.put(Character.TYPE, CharacterCodec.instance);
        this.deserializers.put(Character.class, CharacterCodec.instance);
        this.deserializers.put(Byte.TYPE, NumberDeserializer.instance);
        this.deserializers.put(Byte.class, NumberDeserializer.instance);
        this.deserializers.put(Short.TYPE, NumberDeserializer.instance);
        this.deserializers.put(Short.class, NumberDeserializer.instance);
        this.deserializers.put(Integer.TYPE, IntegerCodec.instance);
        this.deserializers.put(Integer.class, IntegerCodec.instance);
        this.deserializers.put(Long.TYPE, LongCodec.instance);
        this.deserializers.put(Long.class, LongCodec.instance);
        this.deserializers.put(BigInteger.class, BigIntegerCodec.instance);
        this.deserializers.put(BigDecimal.class, BigDecimalCodec.instance);
        this.deserializers.put(Float.TYPE, FloatCodec.instance);
        this.deserializers.put(Float.class, FloatCodec.instance);
        this.deserializers.put(Double.TYPE, NumberDeserializer.instance);
        this.deserializers.put(Double.class, NumberDeserializer.instance);
        this.deserializers.put(Boolean.TYPE, BooleanCodec.instance);
        this.deserializers.put(Boolean.class, BooleanCodec.instance);
        this.deserializers.put(Class.class, MiscCodec.instance);
        this.deserializers.put(char[].class, new CharArrayCodec());
        this.deserializers.put(AtomicBoolean.class, BooleanCodec.instance);
        this.deserializers.put(AtomicInteger.class, IntegerCodec.instance);
        this.deserializers.put(AtomicLong.class, LongCodec.instance);
        this.deserializers.put(AtomicReference.class, ReferenceCodec.instance);
        this.deserializers.put(WeakReference.class, ReferenceCodec.instance);
        this.deserializers.put(SoftReference.class, ReferenceCodec.instance);
        this.deserializers.put(UUID.class, MiscCodec.instance);
        this.deserializers.put(TimeZone.class, MiscCodec.instance);
        this.deserializers.put(Locale.class, MiscCodec.instance);
        this.deserializers.put(Currency.class, MiscCodec.instance);
        this.deserializers.put(Inet4Address.class, MiscCodec.instance);
        this.deserializers.put(Inet6Address.class, MiscCodec.instance);
        this.deserializers.put(InetSocketAddress.class, MiscCodec.instance);
        this.deserializers.put(File.class, MiscCodec.instance);
        this.deserializers.put(URI.class, MiscCodec.instance);
        this.deserializers.put(URL.class, MiscCodec.instance);
        this.deserializers.put(Pattern.class, MiscCodec.instance);
        this.deserializers.put(Charset.class, MiscCodec.instance);
        this.deserializers.put(JSONPath.class, MiscCodec.instance);
        this.deserializers.put(Number.class, NumberDeserializer.instance);
        this.deserializers.put(AtomicIntegerArray.class, AtomicCodec.instance);
        this.deserializers.put(AtomicLongArray.class, AtomicCodec.instance);
        this.deserializers.put(StackTraceElement.class, StackTraceElementDeserializer.instance);
        this.deserializers.put(Serializable.class, JavaObjectDeserializer.instance);
        this.deserializers.put(Cloneable.class, JavaObjectDeserializer.instance);
        this.deserializers.put(Comparable.class, JavaObjectDeserializer.instance);
        this.deserializers.put(Closeable.class, JavaObjectDeserializer.instance);
        this.deserializers.put(JSONPObject.class, new JSONPDeserializer());
    }

    private static String[] splitItemsFormProperty(String str) {
        if (str == null || str.length() <= 0) {
            return null;
        }
        return str.split(",");
    }

    public void configFromPropety(Properties properties) {
        addItemsToDeny(splitItemsFormProperty(properties.getProperty(DENY_PROPERTY)));
        addItemsToAccept(splitItemsFormProperty(properties.getProperty(AUTOTYPE_ACCEPT)));
        String property = properties.getProperty(AUTOTYPE_SUPPORT_PROPERTY);
        if ("true".equals(property)) {
            this.autoTypeSupport = true;
        } else if ("false".equals(property)) {
            this.autoTypeSupport = false;
        }
    }

    private void addItemsToDeny(String[] strArr) {
        if (strArr == null) {
            return;
        }
        for (String str : strArr) {
            addDeny(str);
        }
    }

    private void addItemsToAccept(String[] strArr) {
        if (strArr == null) {
            return;
        }
        for (String str : strArr) {
            addAccept(str);
        }
    }

    public boolean isAutoTypeSupport() {
        return this.autoTypeSupport;
    }

    public void setAutoTypeSupport(boolean z) {
        this.autoTypeSupport = z;
    }

    public boolean isAsmEnable() {
        return this.asmEnable;
    }

    public void setAsmEnable(boolean z) {
        this.asmEnable = z;
    }

    public IdentityHashMap<Type, ObjectDeserializer> getDerializers() {
        return this.deserializers;
    }

    public IdentityHashMap<Type, ObjectDeserializer> getDeserializers() {
        return this.deserializers;
    }

    public ObjectDeserializer getDeserializer(Type type) {
        ObjectDeserializer objectDeserializer = this.deserializers.get(type);
        if (objectDeserializer != null) {
            return objectDeserializer;
        }
        if (type instanceof Class) {
            return getDeserializer((Class) type, type);
        }
        if (type instanceof ParameterizedType) {
            Type rawType = ((ParameterizedType) type).getRawType();
            if (rawType instanceof Class) {
                return getDeserializer((Class) rawType, type);
            }
            return getDeserializer(rawType);
        }
        if (type instanceof WildcardType) {
            Type[] upperBounds = ((WildcardType) type).getUpperBounds();
            if (upperBounds.length == 1) {
                return getDeserializer(upperBounds[0]);
            }
        }
        return JavaObjectDeserializer.instance;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ObjectDeserializer getDeserializer(Class<?> cls, Type type) {
        ObjectDeserializer objectDeserializer;
        ObjectDeserializer objectDeserializerCreateJavaBeanDeserializer;
        String str;
        Class<?> clsMappingTo;
        Type type2 = type;
        ObjectDeserializer objectDeserializer2 = this.deserializers.get(type2);
        if (objectDeserializer2 != null) {
            return objectDeserializer2;
        }
        if (type2 == null) {
            type2 = cls;
        }
        ObjectDeserializer objectDeserializer3 = this.deserializers.get(type2);
        if (objectDeserializer3 != null) {
            return objectDeserializer3;
        }
        JSONType jSONType = (JSONType) TypeUtils.getAnnotation(cls, JSONType.class);
        if (jSONType != null && (clsMappingTo = jSONType.mappingTo()) != Void.class) {
            return getDeserializer(clsMappingTo, clsMappingTo);
        }
        if ((type2 instanceof WildcardType) || (type2 instanceof TypeVariable) || (type2 instanceof ParameterizedType)) {
            objectDeserializer = objectDeserializer3;
            objectDeserializer = this.deserializers.get(cls);
        }
        if (objectDeserializer != null) {
            return objectDeserializer;
        }
        String strReplace = cls.getName().replace('$', '.');
        Object obj = objectDeserializer;
        if (strReplace.startsWith("java.awt.") && AwtCodec.support(cls) && !awtError) {
            obj = objectDeserializer;
            obj = objectDeserializer;
            str = "java.awt.Color";
            String[] strArr = {"java.awt.Point", "java.awt.Font", "java.awt.Rectangle", "java.awt.Color"};
            try {
                int length = strArr.length;
                for (int i = 0; i < length; i++) {
                    str = strArr[i];
                    if (str.equals(strReplace)) {
                        IdentityHashMap<Type, ObjectDeserializer> identityHashMap = this.deserializers;
                        Class<?> cls2 = Class.forName(str);
                        AwtCodec awtCodec = AwtCodec.instance;
                        identityHashMap.put(cls2, awtCodec);
                        return awtCodec;
                    }
                }
            } catch (Throwable unused) {
                awtError = true;
            }
            obj = AwtCodec.instance;
        }
        obj = objectDeserializer;
        obj = objectDeserializer;
        obj = objectDeserializer;
        if (!jdk8Error) {
            try {
                try {
                    if (strReplace.startsWith("java.time.")) {
                        for (String str2 : new String[]{"java.time.LocalDateTime", "java.time.LocalDate", "java.time.LocalTime", "java.time.ZonedDateTime", "java.time.OffsetDateTime", "java.time.OffsetTime", "java.time.ZoneOffset", "java.time.ZoneRegion", "java.time.ZoneId", "java.time.Period", "java.time.Duration", "java.time.Instant"}) {
                            if (str2.equals(strReplace)) {
                                IdentityHashMap<Type, ObjectDeserializer> identityHashMap2 = this.deserializers;
                                Class<?> cls3 = Class.forName(str2);
                                Jdk8DateCodec jdk8DateCodec = Jdk8DateCodec.instance;
                                identityHashMap2.put(cls3, jdk8DateCodec);
                                return jdk8DateCodec;
                            }
                        }
                    } else if (strReplace.startsWith("java.util.Optional")) {
                        for (String str3 : new String[]{"java.util.Optional", "java.util.OptionalDouble", "java.util.OptionalInt", "java.util.OptionalLong"}) {
                            if (str3.equals(strReplace)) {
                                IdentityHashMap<Type, ObjectDeserializer> identityHashMap3 = this.deserializers;
                                Class<?> cls4 = Class.forName(str3);
                                OptionalCodec optionalCodec = OptionalCodec.instance;
                                identityHashMap3.put(cls4, optionalCodec);
                                return optionalCodec;
                            }
                        }
                    }
                } catch (Throwable unused2) {
                    obj = str;
                    jdk8Error = true;
                }
            } catch (Throwable unused3) {
            }
        }
        if (!jodaError) {
            try {
                if (strReplace.startsWith("org.joda.time.")) {
                    for (String str4 : new String[]{"org.joda.time.DateTime", "org.joda.time.LocalDate", "org.joda.time.LocalDateTime", "org.joda.time.LocalTime", "org.joda.time.Instant", "org.joda.time.Period", "org.joda.time.Duration", "org.joda.time.DateTimeZone", "org.joda.time.format.DateTimeFormatter"}) {
                        if (str4.equals(strReplace)) {
                            IdentityHashMap<Type, ObjectDeserializer> identityHashMap4 = this.deserializers;
                            Class<?> cls5 = Class.forName(str4);
                            JodaCodec jodaCodec = JodaCodec.instance;
                            try {
                                identityHashMap4.put(cls5, jodaCodec);
                                return jodaCodec;
                            } catch (Throwable unused4) {
                                obj = jodaCodec;
                            }
                        }
                        jodaError = true;
                    }
                }
            } catch (Throwable unused5) {
            }
        }
        Object obj2 = obj;
        if (strReplace.equals("java.nio.file.Path")) {
            IdentityHashMap<Type, ObjectDeserializer> identityHashMap5 = this.deserializers;
            MiscCodec miscCodec = MiscCodec.instance;
            identityHashMap5.put(cls, miscCodec);
            obj2 = miscCodec;
        }
        ObjectDeserializer objectDeserializer4 = obj2;
        if (cls == Map.Entry.class) {
            IdentityHashMap<Type, ObjectDeserializer> identityHashMap6 = this.deserializers;
            MiscCodec miscCodec2 = MiscCodec.instance;
            identityHashMap6.put(cls, miscCodec2);
            objectDeserializer4 = miscCodec2;
        }
        try {
            for (AutowiredObjectDeserializer autowiredObjectDeserializer : ServiceLoader.load(AutowiredObjectDeserializer.class, Thread.currentThread().getContextClassLoader())) {
                Iterator<Type> it = autowiredObjectDeserializer.getAutowiredFor().iterator();
                while (it.hasNext()) {
                    this.deserializers.put(it.next(), autowiredObjectDeserializer);
                }
            }
        } catch (Exception unused6) {
        }
        if (objectDeserializer4 == 0) {
            objectDeserializer4 = this.deserializers.get(type2);
        }
        if (objectDeserializer4 != 0) {
            return objectDeserializer4;
        }
        if (cls.isEnum()) {
            if (this.jacksonCompatible) {
                for (Method method : cls.getMethods()) {
                    if (TypeUtils.isJacksonCreator(method)) {
                        ObjectDeserializer objectDeserializerCreateJavaBeanDeserializer2 = createJavaBeanDeserializer(cls, type2);
                        putDeserializer(type2, objectDeserializerCreateJavaBeanDeserializer2);
                        return objectDeserializerCreateJavaBeanDeserializer2;
                    }
                }
            }
            JSONType jSONType2 = (JSONType) cls.getAnnotation(JSONType.class);
            if (jSONType2 != null) {
                try {
                    ObjectDeserializer objectDeserializer5 = (ObjectDeserializer) jSONType2.deserializer().newInstance();
                    this.deserializers.put(cls, objectDeserializer5);
                    return objectDeserializer5;
                } catch (Throwable unused7) {
                }
            }
            objectDeserializerCreateJavaBeanDeserializer = new EnumDeserializer(cls);
        } else if (cls.isArray()) {
            objectDeserializerCreateJavaBeanDeserializer = ObjectArrayCodec.instance;
        } else if (cls == Set.class || cls == HashSet.class || cls == Collection.class || cls == List.class || cls == ArrayList.class || Collection.class.isAssignableFrom(cls)) {
            objectDeserializerCreateJavaBeanDeserializer = CollectionCodec.instance;
        } else if (Map.class.isAssignableFrom(cls)) {
            objectDeserializerCreateJavaBeanDeserializer = MapDeserializer.instance;
        } else if (Throwable.class.isAssignableFrom(cls)) {
            objectDeserializerCreateJavaBeanDeserializer = new ThrowableDeserializer(this, cls);
        } else if (PropertyProcessable.class.isAssignableFrom(cls)) {
            objectDeserializerCreateJavaBeanDeserializer = new PropertyProcessableDeserializer(cls);
        } else if (cls == InetAddress.class) {
            objectDeserializerCreateJavaBeanDeserializer = MiscCodec.instance;
        } else {
            objectDeserializerCreateJavaBeanDeserializer = createJavaBeanDeserializer(cls, type2);
        }
        putDeserializer(type2, objectDeserializerCreateJavaBeanDeserializer);
        return objectDeserializerCreateJavaBeanDeserializer;
    }

    public void initJavaBeanDeserializers(Class<?>... clsArr) {
        if (clsArr == null) {
            return;
        }
        for (Class<?> cls : clsArr) {
            if (cls != null) {
                putDeserializer(cls, createJavaBeanDeserializer(cls, cls));
            }
        }
    }

    public ObjectDeserializer createJavaBeanDeserializer(Class<?> cls, Type type) {
        JSONField annotation;
        boolean zCheckName = this.asmEnable & (!this.fieldBased);
        if (zCheckName) {
            JSONType jSONType = (JSONType) TypeUtils.getAnnotation(cls, JSONType.class);
            if (jSONType != null) {
                Class<?> clsDeserializer = jSONType.deserializer();
                if (clsDeserializer != Void.class) {
                    try {
                        Object objNewInstance = clsDeserializer.newInstance();
                        if (objNewInstance instanceof ObjectDeserializer) {
                            return (ObjectDeserializer) objNewInstance;
                        }
                    } catch (Throwable unused) {
                    }
                }
                zCheckName = jSONType.asm();
            }
            if (zCheckName) {
                Class<?> builderClass = JavaBeanInfo.getBuilderClass(cls, jSONType);
                if (builderClass == null) {
                    builderClass = cls;
                }
                do {
                    if (!Modifier.isPublic(builderClass.getModifiers())) {
                        zCheckName = false;
                        break;
                    }
                    builderClass = builderClass.getSuperclass();
                    if (builderClass == Object.class) {
                        break;
                    }
                } while (builderClass != null);
            }
        }
        if (cls.getTypeParameters().length != 0) {
            zCheckName = false;
        }
        if (zCheckName && this.asmFactory != null && this.asmFactory.classLoader.isExternalClass(cls)) {
            zCheckName = false;
        }
        if (zCheckName) {
            zCheckName = ASMUtils.checkName(cls.getSimpleName());
        }
        if (zCheckName) {
            if (cls.isInterface()) {
                zCheckName = false;
            }
            JavaBeanInfo javaBeanInfoBuild = JavaBeanInfo.build(cls, type, this.propertyNamingStrategy, false, TypeUtils.compatibleWithJavaBean, this.jacksonCompatible);
            if (zCheckName && javaBeanInfoBuild.fields.length > 200) {
                zCheckName = false;
            }
            Constructor<?> constructor = javaBeanInfoBuild.defaultConstructor;
            if (zCheckName && constructor == null && !cls.isInterface()) {
                zCheckName = false;
            }
            for (FieldInfo fieldInfo : javaBeanInfoBuild.fields) {
                if (!fieldInfo.getOnly) {
                    Class<?> cls2 = fieldInfo.fieldClass;
                    if (Modifier.isPublic(cls2.getModifiers()) && ((!cls2.isMemberClass() || Modifier.isStatic(cls2.getModifiers())) && ((fieldInfo.getMember() == null || ASMUtils.checkName(fieldInfo.getMember().getName())) && (((annotation = fieldInfo.getAnnotation()) == null || (ASMUtils.checkName(annotation.name()) && annotation.format().length() == 0 && annotation.deserializeUsing() == Void.class && !annotation.unwrapped())) && ((fieldInfo.method == null || fieldInfo.method.getParameterTypes().length <= 1) && (!cls2.isEnum() || (getDeserializer(cls2) instanceof EnumDeserializer))))))) {
                    }
                }
                zCheckName = false;
                break;
            }
        }
        if (zCheckName && cls.isMemberClass() && !Modifier.isStatic(cls.getModifiers())) {
            zCheckName = false;
        }
        if (!zCheckName) {
            return new JavaBeanDeserializer(this, cls, type);
        }
        JavaBeanInfo javaBeanInfoBuild2 = JavaBeanInfo.build(cls, type, this.propertyNamingStrategy);
        try {
            return this.asmFactory.createJavaBeanDeserializer(this, javaBeanInfoBuild2);
        } catch (JSONException unused2) {
            return new JavaBeanDeserializer(this, javaBeanInfoBuild2);
        } catch (NoSuchMethodException unused3) {
            return new JavaBeanDeserializer(this, cls, type);
        } catch (Exception e) {
            throw new JSONException("create asm deserializer error, " + cls.getName(), e);
        }
    }

    public FieldDeserializer createFieldDeserializer(ParserConfig parserConfig, JavaBeanInfo javaBeanInfo, FieldInfo fieldInfo) {
        Class<?> clsDeserializeUsing;
        Class<?> cls = javaBeanInfo.clazz;
        Class<?> cls2 = fieldInfo.fieldClass;
        JSONField annotation = fieldInfo.getAnnotation();
        Class<?> cls3 = null;
        if (annotation != null && (clsDeserializeUsing = annotation.deserializeUsing()) != Void.class) {
            cls3 = clsDeserializeUsing;
        }
        if (cls3 == null && (cls2 == List.class || cls2 == ArrayList.class)) {
            return new ArrayListTypeFieldDeserializer(parserConfig, cls, fieldInfo);
        }
        return new DefaultFieldDeserializer(parserConfig, cls, fieldInfo);
    }

    public void putDeserializer(Type type, ObjectDeserializer objectDeserializer) {
        this.deserializers.put(type, objectDeserializer);
    }

    public ObjectDeserializer getDeserializer(FieldInfo fieldInfo) {
        return getDeserializer(fieldInfo.fieldClass, fieldInfo.fieldType);
    }

    public boolean isPrimitive(Class<?> cls) {
        return isPrimitive2(cls);
    }

    public static boolean isPrimitive2(Class<?> cls) {
        return cls.isPrimitive() || cls == Boolean.class || cls == Character.class || cls == Byte.class || cls == Short.class || cls == Integer.class || cls == Long.class || cls == Float.class || cls == Double.class || cls == BigInteger.class || cls == BigDecimal.class || cls == String.class || cls == java.util.Date.class || cls == Date.class || cls == Time.class || cls == Timestamp.class || cls.isEnum();
    }

    public static void parserAllFieldToCache(Class<?> cls, Map<String, Field> map) {
        for (Field field : cls.getDeclaredFields()) {
            String name = field.getName();
            if (!map.containsKey(name)) {
                map.put(name, field);
            }
        }
        if (cls.getSuperclass() == null || cls.getSuperclass() == Object.class) {
            return;
        }
        parserAllFieldToCache(cls.getSuperclass(), map);
    }

    public static Field getFieldFromCache(String str, Map<String, Field> map) {
        Field field = map.get(str);
        if (field == null) {
            field = map.get("_" + str);
        }
        if (field == null) {
            field = map.get("m_" + str);
        }
        if (field != null) {
            return field;
        }
        char cCharAt = str.charAt(0);
        if (cCharAt >= 'a' && cCharAt <= 'z') {
            char[] charArray = str.toCharArray();
            charArray[0] = (char) (charArray[0] - ' ');
            field = map.get(new String(charArray));
        }
        if (str.length() <= 2) {
            return field;
        }
        char cCharAt2 = str.charAt(1);
        if (str.length() <= 2 || cCharAt < 'a' || cCharAt > 'z' || cCharAt2 < 'A' || cCharAt2 > 'Z') {
            return field;
        }
        for (Map.Entry<String, Field> entry : map.entrySet()) {
            if (str.equalsIgnoreCase(entry.getKey())) {
                return entry.getValue();
            }
        }
        return field;
    }

    public ClassLoader getDefaultClassLoader() {
        return this.defaultClassLoader;
    }

    public void setDefaultClassLoader(ClassLoader classLoader) {
        this.defaultClassLoader = classLoader;
    }

    public void addDeny(String str) {
        if (str == null || str.length() == 0) {
            return;
        }
        long jFnv1a_64 = TypeUtils.fnv1a_64(str);
        if (Arrays.binarySearch(this.denyHashCodes, jFnv1a_64) >= 0) {
            return;
        }
        long[] jArr = new long[this.denyHashCodes.length + 1];
        jArr[jArr.length - 1] = jFnv1a_64;
        System.arraycopy(this.denyHashCodes, 0, jArr, 0, this.denyHashCodes.length);
        Arrays.sort(jArr);
        this.denyHashCodes = jArr;
    }

    public void addAccept(String str) {
        if (str == null || str.length() == 0) {
            return;
        }
        long jFnv1a_64 = TypeUtils.fnv1a_64(str);
        if (Arrays.binarySearch(this.acceptHashCodes, jFnv1a_64) >= 0) {
            return;
        }
        long[] jArr = new long[this.acceptHashCodes.length + 1];
        jArr[jArr.length - 1] = jFnv1a_64;
        System.arraycopy(this.acceptHashCodes, 0, jArr, 0, this.acceptHashCodes.length);
        Arrays.sort(jArr);
        this.acceptHashCodes = jArr;
    }

    public Class<?> checkAutoType(Class cls) {
        return this.deserializers.get(cls) != null ? cls : checkAutoType(cls.getName(), null, JSON.DEFAULT_PARSER_FEATURE);
    }

    public Class<?> checkAutoType(String str, Class<?> cls) {
        return checkAutoType(str, cls, JSON.DEFAULT_PARSER_FEATURE);
    }

    /* JADX WARN: Code duplicated, block: B:118:0x0237  */
    /* JADX WARN: Code duplicated, block: B:124:0x0243  */
    /* JADX WARN: Code duplicated, block: B:126:0x0246 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:130:0x024e A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:131:0x0250  */
    /* JADX WARN: Code duplicated, block: B:134:0x0259 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:135:0x025b  */
    /* JADX WARN: Code duplicated, block: B:137:0x025f  */
    /* JADX WARN: Code duplicated, block: B:139:0x0267  */
    /* JADX WARN: Code duplicated, block: B:159:0x02e3  */
    /* JADX WARN: Code duplicated, block: B:161:0x02fa A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:162:0x02fc  */
    /* JADX WARN: Instruction removed from duplicated block: B:159:0x02e3, please report this as an issue */
    public Class<?> checkAutoType(String str, Class<?> cls, int i) throws Throwable {
        Class<?> clsLoadClass;
        long j;
        boolean z;
        boolean z2;
        InputStream resourceAsStream;
        Throwable th;
        boolean zHasJsonType;
        boolean z3;
        if (str == null) {
            return null;
        }
        if (str.length() >= 192 || str.length() < 3) {
            throw new JSONException("autoType is not support. " + str);
        }
        boolean z4 = (cls == null || cls == Object.class || cls == Serializable.class || cls == Cloneable.class || cls == Closeable.class || cls == EventListener.class || cls == Iterable.class || cls == Collection.class) ? false : true;
        String strReplace = str.replace('$', '.');
        long jCharAt = (((long) strReplace.charAt(0)) ^ (-3750763034362895579L)) * 1099511628211L;
        if (jCharAt == -5808493101479473382L) {
            throw new JSONException("autoType is not support. " + str);
        }
        if ((((long) strReplace.charAt(strReplace.length() - 1)) ^ jCharAt) * 1099511628211L == 655701488918567152L) {
            throw new JSONException("autoType is not support. " + str);
        }
        long jCharAt2 = (((((((long) strReplace.charAt(0)) ^ (-3750763034362895579L)) * 1099511628211L) ^ ((long) strReplace.charAt(1))) * 1099511628211L) ^ ((long) strReplace.charAt(2))) * 1099511628211L;
        if (this.autoTypeSupport || z4) {
            long jCharAt3 = jCharAt2;
            int i2 = 3;
            clsLoadClass = null;
            while (i2 < strReplace.length()) {
                long j2 = jCharAt2;
                jCharAt3 = (((long) strReplace.charAt(i2)) ^ jCharAt3) * 1099511628211L;
                if (Arrays.binarySearch(this.acceptHashCodes, jCharAt3) >= 0 && (clsLoadClass = TypeUtils.loadClass(str, this.defaultClassLoader, true)) != null) {
                    return clsLoadClass;
                }
                if (Arrays.binarySearch(this.denyHashCodes, jCharAt3) >= 0 && TypeUtils.getClassFromMapping(str) == null) {
                    throw new JSONException("autoType is not support. " + str);
                }
                i2++;
                jCharAt2 = j2;
            }
            j = jCharAt2;
        } else {
            j = jCharAt2;
            clsLoadClass = null;
        }
        if (clsLoadClass == null) {
            clsLoadClass = TypeUtils.getClassFromMapping(str);
        }
        if (clsLoadClass == null) {
            clsLoadClass = this.deserializers.findClass(str);
        }
        if (clsLoadClass != null) {
            if (cls == null || clsLoadClass == HashMap.class || cls.isAssignableFrom(clsLoadClass)) {
                return clsLoadClass;
            }
            throw new JSONException("type not match. " + str + " -> " + cls.getName());
        }
        if (!this.autoTypeSupport) {
            int i3 = 3;
            while (i3 < strReplace.length()) {
                long jCharAt4 = (j ^ ((long) strReplace.charAt(i3))) * 1099511628211L;
                if (Arrays.binarySearch(this.denyHashCodes, jCharAt4) >= 0) {
                    throw new JSONException("autoType is not support. " + str);
                }
                if (Arrays.binarySearch(this.acceptHashCodes, jCharAt4) >= 0) {
                    if (clsLoadClass == null) {
                        clsLoadClass = TypeUtils.loadClass(str, this.defaultClassLoader, true);
                    }
                    if (cls == null || !cls.isAssignableFrom(clsLoadClass)) {
                        return clsLoadClass;
                    }
                    throw new JSONException("type not match. " + str + " -> " + cls.getName());
                }
                i3++;
                j = jCharAt4;
            }
        }
        try {
            try {
                String str2 = str.replace('.', '/') + ".class";
                if (this.defaultClassLoader != null) {
                    try {
                        resourceAsStream = this.defaultClassLoader.getResourceAsStream(str2);
                    } catch (Exception unused) {
                        resourceAsStream = null;
                        z = true;
                        z2 = false;
                        IOUtils.close(resourceAsStream);
                        zHasJsonType = z2;
                        int i4 = Feature.SupportAutoType.mask;
                        if (!this.autoTypeSupport) {
                            z3 = z;
                        } else {
                            z3 = z;
                        }
                        if (clsLoadClass == null) {
                            if (z3) {
                                z2 = z;
                            } else {
                                z2 = z;
                            }
                            clsLoadClass = TypeUtils.loadClass(str, this.defaultClassLoader, z2);
                        }
                        if (clsLoadClass != null) {
                            if (zHasJsonType) {
                                TypeUtils.addMapping(str, clsLoadClass);
                                return clsLoadClass;
                            }
                            if (ClassLoader.class.isAssignableFrom(clsLoadClass)) {
                            }
                            throw new JSONException("autoType is not support. " + str);
                        }
                        if (!z3) {
                            if (clsLoadClass != null) {
                                TypeUtils.addMapping(str, clsLoadClass);
                            }
                            return clsLoadClass;
                        }
                        throw new JSONException("autoType is not support. " + str);
                    }
                } else {
                    resourceAsStream = ParserConfig.class.getClassLoader().getResourceAsStream(str2);
                }
                if (resourceAsStream != null) {
                    try {
                        try {
                            z = true;
                            try {
                                ClassReader classReader = new ClassReader(resourceAsStream, true);
                                z2 = false;
                                try {
                                    TypeCollector typeCollector = new TypeCollector("<clinit>", new Class[0]);
                                    classReader.accept(typeCollector);
                                    zHasJsonType = typeCollector.hasJsonType();
                                } catch (Exception unused2) {
                                    IOUtils.close(resourceAsStream);
                                    zHasJsonType = z2;
                                }
                            } catch (Exception unused3) {
                                z2 = false;
                                IOUtils.close(resourceAsStream);
                                zHasJsonType = z2;
                                int i5 = Feature.SupportAutoType.mask;
                                if (!this.autoTypeSupport) {
                                    z3 = z;
                                } else {
                                    z3 = z;
                                }
                                if (clsLoadClass == null) {
                                    if (z3) {
                                        z2 = z;
                                    } else {
                                        z2 = z;
                                    }
                                    clsLoadClass = TypeUtils.loadClass(str, this.defaultClassLoader, z2);
                                }
                                if (clsLoadClass != null) {
                                    if (zHasJsonType) {
                                        TypeUtils.addMapping(str, clsLoadClass);
                                        return clsLoadClass;
                                    }
                                    if (ClassLoader.class.isAssignableFrom(clsLoadClass)) {
                                    }
                                    throw new JSONException("autoType is not support. " + str);
                                }
                                if (!z3) {
                                    if (clsLoadClass != null) {
                                        TypeUtils.addMapping(str, clsLoadClass);
                                    }
                                    return clsLoadClass;
                                }
                                throw new JSONException("autoType is not support. " + str);
                            }
                        } catch (Exception unused4) {
                            z = true;
                            z2 = false;
                            IOUtils.close(resourceAsStream);
                            zHasJsonType = z2;
                            int i6 = Feature.SupportAutoType.mask;
                            if (!this.autoTypeSupport) {
                                z3 = z;
                            } else {
                                z3 = z;
                            }
                            if (clsLoadClass == null) {
                                if (z3) {
                                    z2 = z;
                                } else {
                                    z2 = z;
                                }
                                clsLoadClass = TypeUtils.loadClass(str, this.defaultClassLoader, z2);
                            }
                            if (clsLoadClass != null) {
                                if (zHasJsonType) {
                                    TypeUtils.addMapping(str, clsLoadClass);
                                    return clsLoadClass;
                                }
                                if (ClassLoader.class.isAssignableFrom(clsLoadClass)) {
                                }
                                throw new JSONException("autoType is not support. " + str);
                            }
                            if (!z3) {
                                if (clsLoadClass != null) {
                                    TypeUtils.addMapping(str, clsLoadClass);
                                }
                                return clsLoadClass;
                            }
                            throw new JSONException("autoType is not support. " + str);
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        IOUtils.close(resourceAsStream);
                        throw th;
                    }
                } else {
                    z = true;
                    z2 = false;
                    zHasJsonType = false;
                }
                IOUtils.close(resourceAsStream);
            } catch (Throwable th3) {
                th = th3;
                resourceAsStream = null;
            }
        } catch (Exception unused5) {
            z = true;
            z2 = false;
            resourceAsStream = null;
        }
        int i7 = Feature.SupportAutoType.mask;
        if (!this.autoTypeSupport && (i & i7) == 0 && (i7 & JSON.DEFAULT_PARSER_FEATURE) == 0) {
            z3 = z2;
        } else {
            z3 = z;
        }
        if (clsLoadClass == null && (z3 || zHasJsonType || z4)) {
            if (z3 || zHasJsonType) {
                z2 = z;
            }
            clsLoadClass = TypeUtils.loadClass(str, this.defaultClassLoader, z2);
        }
        if (clsLoadClass != null) {
            if (zHasJsonType) {
                TypeUtils.addMapping(str, clsLoadClass);
                return clsLoadClass;
            }
            if (!ClassLoader.class.isAssignableFrom(clsLoadClass) || DataSource.class.isAssignableFrom(clsLoadClass) || RowSet.class.isAssignableFrom(clsLoadClass)) {
                throw new JSONException("autoType is not support. " + str);
            }
            if (cls != null) {
                if (cls.isAssignableFrom(clsLoadClass)) {
                    TypeUtils.addMapping(str, clsLoadClass);
                    return clsLoadClass;
                }
                throw new JSONException("type not match. " + str + " -> " + cls.getName());
            }
            if (JavaBeanInfo.build(clsLoadClass, clsLoadClass, this.propertyNamingStrategy).creatorConstructor != null && z3) {
                throw new JSONException("autoType is not support. " + str);
            }
        }
        if (!z3) {
            if (clsLoadClass != null) {
                TypeUtils.addMapping(str, clsLoadClass);
            }
            return clsLoadClass;
        }
        throw new JSONException("autoType is not support. " + str);
    }

    public void clearDeserializers() {
        this.deserializers.clear();
        initDeserializers();
    }

    public boolean isJacksonCompatible() {
        return this.jacksonCompatible;
    }

    public void setJacksonCompatible(boolean z) {
        this.jacksonCompatible = z;
    }
}
