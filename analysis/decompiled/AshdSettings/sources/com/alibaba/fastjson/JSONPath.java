package com.alibaba.fastjson;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.JSONLexerBase;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.parser.deserializer.FieldDeserializer;
import com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.serializer.FieldSerializer;
import com.alibaba.fastjson.serializer.JavaBeanSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.util.IOUtils;
import com.alibaba.fastjson.util.TypeUtils;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes.dex */
public class JSONPath implements JSONAware {
    static final long LENGTH = -1580386065683472715L;
    static final long SIZE = 5614464919154503228L;
    private static ConcurrentMap<String, JSONPath> pathCache = new ConcurrentHashMap(128, 0.75f, 1);
    private boolean hasRefSegment;
    private ParserConfig parserConfig;
    private final String path;
    private Segment[] segments;
    private SerializeConfig serializeConfig;

    interface Filter {
        boolean apply(JSONPath jSONPath, Object obj, Object obj2, Object obj3);
    }

    enum Operator {
        EQ,
        NE,
        GT,
        GE,
        LT,
        LE,
        LIKE,
        NOT_LIKE,
        RLIKE,
        NOT_RLIKE,
        IN,
        NOT_IN,
        BETWEEN,
        NOT_BETWEEN,
        And,
        Or,
        REG_MATCH
    }

    interface Segment {
        Object eval(JSONPath jSONPath, Object obj, Object obj2);

        void extract(JSONPath jSONPath, DefaultJSONParser defaultJSONParser, Context context);
    }

    public JSONPath(String str) {
        this(str, SerializeConfig.getGlobalInstance(), ParserConfig.getGlobalInstance());
    }

    public JSONPath(String str, SerializeConfig serializeConfig, ParserConfig parserConfig) {
        if (str == null || str.length() == 0) {
            throw new JSONPathException("json-path can not be null or empty");
        }
        this.path = str;
        this.serializeConfig = serializeConfig;
        this.parserConfig = parserConfig;
    }

    protected void init() {
        if (this.segments != null) {
            return;
        }
        if ("*".equals(this.path)) {
            this.segments = new Segment[]{WildCardSegment.instance};
            return;
        }
        JSONPathParser jSONPathParser = new JSONPathParser(this.path);
        this.segments = jSONPathParser.explain();
        this.hasRefSegment = jSONPathParser.hasRefSegment;
    }

    public Object eval(Object obj) {
        if (obj == null) {
            return null;
        }
        init();
        Object objEval = obj;
        for (int i = 0; i < this.segments.length; i++) {
            objEval = this.segments[i].eval(this, obj, objEval);
        }
        return objEval;
    }

    public Object extract(DefaultJSONParser defaultJSONParser) {
        if (defaultJSONParser == null) {
            return null;
        }
        init();
        if (this.hasRefSegment) {
            return eval(defaultJSONParser.parse());
        }
        Context context = null;
        int i = 0;
        while (i < this.segments.length) {
            Segment segment = this.segments[i];
            boolean z = true;
            boolean z2 = i == this.segments.length - 1;
            if (context != null && context.object != null) {
                return segment.eval(this, null, context.object);
            }
            if (!z2) {
                Segment segment2 = this.segments[i + 1];
                if ((!(segment instanceof PropertySegment) || !((PropertySegment) segment).deep || (!(segment2 instanceof ArrayAccessSegment) && !(segment2 instanceof MultiIndexSegment) && !(segment2 instanceof MultiPropertySegment) && !(segment2 instanceof SizeSegment) && !(segment2 instanceof PropertySegment) && !(segment2 instanceof FilterSegment))) && ((!(segment2 instanceof ArrayAccessSegment) || ((ArrayAccessSegment) segment2).index >= 0) && !(segment2 instanceof FilterSegment))) {
                    z = false;
                }
            }
            Context context2 = new Context(context, z);
            segment.extract(this, defaultJSONParser, context2);
            i++;
            context = context2;
        }
        return context.object;
    }

    private static class Context {
        final boolean eval;
        Object object;
        final Context parent;

        public Context(Context context, boolean z) {
            this.parent = context;
            this.eval = z;
        }
    }

    public boolean contains(Object obj) {
        if (obj == null) {
            return false;
        }
        init();
        Object obj2 = obj;
        int i = 0;
        while (i < this.segments.length) {
            Object objEval = this.segments[i].eval(this, obj, obj2);
            if (objEval == null) {
                return false;
            }
            if (objEval == Collections.EMPTY_LIST && (obj2 instanceof List)) {
                return ((List) obj2).contains(objEval);
            }
            i++;
            obj2 = objEval;
        }
        return true;
    }

    public boolean containsValue(Object obj, Object obj2) {
        Object objEval = eval(obj);
        if (objEval == obj2) {
            return true;
        }
        if (objEval == null) {
            return false;
        }
        if (objEval instanceof Iterable) {
            Iterator it = ((Iterable) objEval).iterator();
            while (it.hasNext()) {
                if (eq(it.next(), obj2)) {
                    return true;
                }
            }
            return false;
        }
        return eq(objEval, obj2);
    }

    public int size(Object obj) {
        if (obj == null) {
            return -1;
        }
        init();
        Object objEval = obj;
        for (int i = 0; i < this.segments.length; i++) {
            objEval = this.segments[i].eval(this, obj, objEval);
        }
        return evalSize(objEval);
    }

    public Set<?> keySet(Object obj) {
        if (obj == null) {
            return null;
        }
        init();
        Object objEval = obj;
        for (int i = 0; i < this.segments.length; i++) {
            objEval = this.segments[i].eval(this, obj, objEval);
        }
        return evalKeySet(objEval);
    }

    public void arrayAdd(Object obj, Object... objArr) {
        if (objArr == null || objArr.length == 0 || obj == null) {
            return;
        }
        init();
        int i = 0;
        Object objEval = obj;
        Object obj2 = null;
        for (int i2 = 0; i2 < this.segments.length; i2++) {
            if (i2 == this.segments.length - 1) {
                obj2 = objEval;
            }
            objEval = this.segments[i2].eval(this, obj, objEval);
        }
        if (objEval == null) {
            throw new JSONPathException("value not found in path " + this.path);
        }
        if (objEval instanceof Collection) {
            Collection collection = (Collection) objEval;
            int length = objArr.length;
            while (i < length) {
                collection.add(objArr[i]);
                i++;
            }
            return;
        }
        Class<?> cls = objEval.getClass();
        if (cls.isArray()) {
            int length2 = Array.getLength(objEval);
            Object objNewInstance = Array.newInstance(cls.getComponentType(), objArr.length + length2);
            System.arraycopy(objEval, 0, objNewInstance, 0, length2);
            while (i < objArr.length) {
                Array.set(objNewInstance, length2 + i, objArr[i]);
                i++;
            }
            Segment segment = this.segments[this.segments.length - 1];
            if (segment instanceof PropertySegment) {
                ((PropertySegment) segment).setValue(this, obj2, objNewInstance);
                return;
            } else {
                if (segment instanceof ArrayAccessSegment) {
                    ((ArrayAccessSegment) segment).setValue(this, obj2, objNewInstance);
                    return;
                }
                throw new UnsupportedOperationException();
            }
        }
        throw new JSONException("unsupported array put operation. " + cls);
    }

    public boolean remove(Object obj) {
        boolean z = false;
        if (obj == null) {
            return false;
        }
        init();
        Object obj2 = null;
        Object objEval = obj;
        for (int i = 0; i < this.segments.length; i++) {
            if (i == this.segments.length - 1) {
                obj2 = objEval;
                break;
            }
            objEval = this.segments[i].eval(this, obj, objEval);
            if (objEval == null) {
                break;
            }
        }
        if (obj2 == null) {
            return false;
        }
        Segment segment = this.segments[this.segments.length - 1];
        if (segment instanceof PropertySegment) {
            PropertySegment propertySegment = (PropertySegment) segment;
            if ((obj2 instanceof Collection) && this.segments.length > 1) {
                Segment segment2 = this.segments[this.segments.length - 2];
                if ((segment2 instanceof RangeSegment) || (segment2 instanceof MultiIndexSegment)) {
                    Iterator it = ((Collection) obj2).iterator();
                    while (it.hasNext()) {
                        if (propertySegment.remove(this, it.next())) {
                            z = true;
                        }
                    }
                    return z;
                }
            }
            return propertySegment.remove(this, obj2);
        }
        if (segment instanceof ArrayAccessSegment) {
            return ((ArrayAccessSegment) segment).remove(this, obj2);
        }
        throw new UnsupportedOperationException();
    }

    public boolean set(Object obj, Object obj2) {
        return set(obj, obj2, true);
    }

    /* JADX WARN: Code duplicated, block: B:21:0x004f  */
    public boolean set(Object obj, Object obj2, boolean z) {
        Class<?> cls;
        JavaBeanDeserializer javaBeanDeserializer;
        if (obj == null) {
            return false;
        }
        init();
        Object obj3 = obj;
        int i = 0;
        Object obj4 = null;
        while (true) {
            if (i >= this.segments.length) {
                obj3 = obj4;
                break;
            }
            Segment segment = this.segments[i];
            Object objEval = segment.eval(this, obj, obj3);
            if (objEval == null) {
                Segment segment2 = i < this.segments.length - 1 ? this.segments[i + 1] : null;
                if (segment2 instanceof PropertySegment) {
                    if (segment instanceof PropertySegment) {
                        String str = ((PropertySegment) segment).propertyName;
                        JavaBeanDeserializer javaBeanDeserializer2 = getJavaBeanDeserializer(obj3.getClass());
                        if (javaBeanDeserializer2 != null) {
                            cls = javaBeanDeserializer2.getFieldDeserializer(str).fieldInfo.fieldClass;
                            javaBeanDeserializer = getJavaBeanDeserializer(cls);
                        } else {
                            cls = null;
                            javaBeanDeserializer = null;
                        }
                    } else {
                        cls = null;
                        javaBeanDeserializer = null;
                    }
                    if (javaBeanDeserializer != null) {
                        if (javaBeanDeserializer.beanInfo.defaultConstructor == null) {
                            return false;
                        }
                        objEval = javaBeanDeserializer.createInstance((DefaultJSONParser) null, cls);
                    } else {
                        objEval = new JSONObject();
                    }
                } else {
                    objEval = segment2 instanceof ArrayAccessSegment ? new JSONArray() : null;
                }
                if (objEval != null) {
                    if (segment instanceof PropertySegment) {
                        ((PropertySegment) segment).setValue(this, obj3, objEval);
                    } else {
                        if (!(segment instanceof ArrayAccessSegment)) {
                            break;
                        }
                        ((ArrayAccessSegment) segment).setValue(this, obj3, objEval);
                    }
                } else {
                    break;
                }
            }
            i++;
            obj4 = obj3;
            obj3 = objEval;
        }
        if (obj3 == null) {
            return false;
        }
        Segment segment3 = this.segments[this.segments.length - 1];
        if (segment3 instanceof PropertySegment) {
            ((PropertySegment) segment3).setValue(this, obj3, obj2);
            return true;
        }
        if (segment3 instanceof ArrayAccessSegment) {
            return ((ArrayAccessSegment) segment3).setValue(this, obj3, obj2);
        }
        throw new UnsupportedOperationException();
    }

    public static Object eval(Object obj, String str) {
        return compile(str).eval(obj);
    }

    public static int size(Object obj, String str) {
        JSONPath jSONPathCompile = compile(str);
        return jSONPathCompile.evalSize(jSONPathCompile.eval(obj));
    }

    public static Set<?> keySet(Object obj, String str) {
        JSONPath jSONPathCompile = compile(str);
        return jSONPathCompile.evalKeySet(jSONPathCompile.eval(obj));
    }

    public static boolean contains(Object obj, String str) {
        if (obj == null) {
            return false;
        }
        return compile(str).contains(obj);
    }

    public static boolean containsValue(Object obj, String str, Object obj2) {
        return compile(str).containsValue(obj, obj2);
    }

    public static void arrayAdd(Object obj, String str, Object... objArr) {
        compile(str).arrayAdd(obj, objArr);
    }

    public static boolean set(Object obj, String str, Object obj2) {
        return compile(str).set(obj, obj2);
    }

    public static boolean remove(Object obj, String str) {
        return compile(str).remove(obj);
    }

    public static JSONPath compile(String str) {
        if (str == null) {
            throw new JSONPathException("jsonpath can not be null");
        }
        JSONPath jSONPath = pathCache.get(str);
        if (jSONPath != null) {
            return jSONPath;
        }
        JSONPath jSONPath2 = new JSONPath(str);
        if (pathCache.size() >= 1024) {
            return jSONPath2;
        }
        pathCache.putIfAbsent(str, jSONPath2);
        return pathCache.get(str);
    }

    public static Object read(String str, String str2) {
        return compile(str2).eval(JSON.parse(str));
    }

    public static Object extract(String str, String str2, ParserConfig parserConfig, int i, Feature... featureArr) {
        DefaultJSONParser defaultJSONParser = new DefaultJSONParser(str, parserConfig, i | Feature.OrderedField.mask);
        Object objExtract = compile(str2).extract(defaultJSONParser);
        defaultJSONParser.lexer.close();
        return objExtract;
    }

    public static Object extract(String str, String str2) {
        return extract(str, str2, ParserConfig.global, JSON.DEFAULT_PARSER_FEATURE, new Feature[0]);
    }

    public static Map<String, Object> paths(Object obj) {
        return paths(obj, SerializeConfig.globalInstance);
    }

    public static Map<String, Object> paths(Object obj, SerializeConfig serializeConfig) {
        IdentityHashMap identityHashMap = new IdentityHashMap();
        HashMap map = new HashMap();
        paths(identityHashMap, map, "/", obj, serializeConfig);
        return map;
    }

    private static void paths(Map<Object, String> map, Map<String, Object> map2, String str, Object obj, SerializeConfig serializeConfig) {
        StringBuilder sb;
        StringBuilder sb2;
        StringBuilder sb3;
        StringBuilder sb4;
        if (obj == null) {
            return;
        }
        int i = 0;
        if (map.put(obj, str) != null) {
            if (!((obj instanceof String) || (obj instanceof Number) || (obj instanceof Date) || (obj instanceof UUID))) {
                return;
            }
        }
        map2.put(str, obj);
        if (obj instanceof Map) {
            for (Map.Entry entry : ((Map) obj).entrySet()) {
                Object key = entry.getKey();
                if (key instanceof String) {
                    if (str.equals("/")) {
                        sb4 = new StringBuilder();
                    } else {
                        sb4 = new StringBuilder();
                        sb4.append(str);
                    }
                    sb4.append("/");
                    sb4.append(key);
                    paths(map, map2, sb4.toString(), entry.getValue(), serializeConfig);
                }
            }
            return;
        }
        if (obj instanceof Collection) {
            for (Object obj2 : (Collection) obj) {
                if (str.equals("/")) {
                    sb3 = new StringBuilder();
                } else {
                    sb3 = new StringBuilder();
                    sb3.append(str);
                }
                sb3.append("/");
                sb3.append(i);
                paths(map, map2, sb3.toString(), obj2, serializeConfig);
                i++;
            }
            return;
        }
        Class<?> cls = obj.getClass();
        if (cls.isArray()) {
            int length = Array.getLength(obj);
            while (i < length) {
                Object obj3 = Array.get(obj, i);
                if (str.equals("/")) {
                    sb2 = new StringBuilder();
                } else {
                    sb2 = new StringBuilder();
                    sb2.append(str);
                }
                sb2.append("/");
                sb2.append(i);
                paths(map, map2, sb2.toString(), obj3, serializeConfig);
                i++;
            }
            return;
        }
        if (ParserConfig.isPrimitive2(cls) || cls.isEnum()) {
            return;
        }
        ObjectSerializer objectWriter = serializeConfig.getObjectWriter(cls);
        if (objectWriter instanceof JavaBeanSerializer) {
            try {
                for (Map.Entry<String, Object> entry2 : ((JavaBeanSerializer) objectWriter).getFieldValuesMap(obj).entrySet()) {
                    String key2 = entry2.getKey();
                    if (key2 instanceof String) {
                        if (str.equals("/")) {
                            sb = new StringBuilder();
                            sb.append("/");
                            sb.append(key2);
                        } else {
                            sb = new StringBuilder();
                            sb.append(str);
                            sb.append("/");
                            sb.append(key2);
                        }
                        paths(map, map2, sb.toString(), entry2.getValue(), serializeConfig);
                    }
                }
            } catch (Exception e) {
                throw new JSONException("toJSON error", e);
            }
        }
    }

    public String getPath() {
        return this.path;
    }

    static class JSONPathParser {
        private char ch;
        private boolean hasRefSegment;
        private int level;
        private final String path;
        private int pos;

        static boolean isDigitFirst(char c) {
            return c == '-' || c == '+' || (c >= '0' && c <= '9');
        }

        public JSONPathParser(String str) {
            this.path = str;
            next();
        }

        void next() {
            String str = this.path;
            int i = this.pos;
            this.pos = i + 1;
            this.ch = str.charAt(i);
        }

        char getNextChar() {
            return this.path.charAt(this.pos);
        }

        boolean isEOF() {
            return this.pos >= this.path.length();
        }

        Segment readSegement() {
            boolean z = true;
            if (this.level == 0 && this.path.length() == 1) {
                if (isDigitFirst(this.ch)) {
                    return new ArrayAccessSegment(this.ch - '0');
                }
                if ((this.ch >= 'a' && this.ch <= 'z') || (this.ch >= 'A' && this.ch <= 'Z')) {
                    return new PropertySegment(Character.toString(this.ch), false);
                }
            }
            while (!isEOF()) {
                skipWhitespace();
                if (this.ch == '$') {
                    next();
                } else {
                    if (this.ch == '.' || this.ch == '/') {
                        char c = this.ch;
                        next();
                        if (c == '.' && this.ch == '.') {
                            next();
                            if (this.path.length() > this.pos + 3 && this.ch == '[' && this.path.charAt(this.pos) == '*' && this.path.charAt(this.pos + 1) == ']' && this.path.charAt(this.pos + 2) == '.') {
                                next();
                                next();
                                next();
                                next();
                            }
                        } else {
                            z = false;
                        }
                        if (this.ch == '*') {
                            if (!isEOF()) {
                                next();
                            }
                            return z ? WildCardSegment.instance_deep : WildCardSegment.instance;
                        }
                        if (isDigitFirst(this.ch)) {
                            return parseArrayAccess(false);
                        }
                        String name = readName();
                        if (this.ch == '(') {
                            next();
                            if (this.ch == ')') {
                                if (!isEOF()) {
                                    next();
                                }
                                if ("size".equals(name) || "length".equals(name)) {
                                    return SizeSegment.instance;
                                }
                                if ("max".equals(name)) {
                                    return MaxSegment.instance;
                                }
                                if ("min".equals(name)) {
                                    return MinSegment.instance;
                                }
                                if ("keySet".equals(name)) {
                                    return KeySetSegment.instance;
                                }
                                throw new JSONPathException("not support jsonpath : " + this.path);
                            }
                            throw new JSONPathException("not support jsonpath : " + this.path);
                        }
                        return new PropertySegment(name, z);
                    }
                    if (this.ch == '[') {
                        return parseArrayAccess(true);
                    }
                    if (this.level == 0) {
                        return new PropertySegment(readName(), false);
                    }
                    throw new JSONPathException("not support jsonpath : " + this.path);
                }
            }
            return null;
        }

        public final void skipWhitespace() {
            while (this.ch <= ' ') {
                if (this.ch != ' ' && this.ch != '\r' && this.ch != '\n' && this.ch != '\t' && this.ch != '\f' && this.ch != '\b') {
                    return;
                } else {
                    next();
                }
            }
        }

        Segment parseArrayAccess(boolean z) {
            Object arrayAccessFilter = parseArrayAccessFilter(z);
            if (arrayAccessFilter instanceof Segment) {
                return (Segment) arrayAccessFilter;
            }
            return new FilterSegment((Filter) arrayAccessFilter);
        }

        /* JADX WARN: Code duplicated, block: B:300:0x03ed A[LOOP:9: B:298:0x03e9->B:300:0x03ed, LOOP_END] */
        /* JADX WARN: Code duplicated, block: B:305:0x03f9  */
        /* JADX WARN: Code duplicated, block: B:307:0x03ff  */
        /* JADX WARN: Code duplicated, block: B:309:0x0404  */
        Object parseArrayAccessFilter(boolean z) {
            boolean z2;
            Filter nullSegement;
            String[] strArr;
            String str;
            String str2;
            Filter matchSegement;
            Operator operator;
            Filter valueSegment;
            Filter valueSegment2;
            Filter notNullSegement;
            Filter doubleOpSegement;
            int i;
            if (z) {
                accept('[');
            }
            if (this.ch == '?') {
                next();
                accept('(');
                z2 = true;
            } else {
                z2 = false;
            }
            if (z2 || IOUtils.firstIdentifier(this.ch) || this.ch == '\\' || this.ch == '@') {
                if (this.ch == '@') {
                    next();
                    accept('.');
                }
                String name = readName();
                skipWhitespace();
                if (z2 && this.ch == ')') {
                    next();
                    Filter notNullSegement2 = new NotNullSegement(name);
                    while (this.ch == ' ') {
                        next();
                    }
                    if (this.ch == '&' || this.ch == '|') {
                        notNullSegement2 = filterRest(notNullSegement2);
                    }
                    if (z) {
                        accept(']');
                    }
                    return notNullSegement2;
                }
                if (z && this.ch == ']') {
                    next();
                    Filter notNullSegement3 = new NotNullSegement(name);
                    while (this.ch == ' ') {
                        next();
                    }
                    if (this.ch == '&' || this.ch == '|') {
                        notNullSegement3 = filterRest(notNullSegement3);
                    }
                    accept(')');
                    if (z2) {
                        accept(')');
                    }
                    if (z) {
                        accept(']');
                    }
                    return notNullSegement3;
                }
                Operator op = readOp();
                skipWhitespace();
                if (op == Operator.BETWEEN || op == Operator.NOT_BETWEEN) {
                    boolean z3 = op == Operator.NOT_BETWEEN;
                    Object value = readValue();
                    if (!"and".equalsIgnoreCase(readName())) {
                        throw new JSONPathException(this.path);
                    }
                    Object value2 = readValue();
                    if (value == null || value2 == null) {
                        throw new JSONPathException(this.path);
                    }
                    if (JSONPath.isInt(value.getClass()) && JSONPath.isInt(value2.getClass())) {
                        return new IntBetweenSegement(name, TypeUtils.longExtractValue((Number) value), TypeUtils.longExtractValue((Number) value2), z3);
                    }
                    throw new JSONPathException(this.path);
                }
                if (op == Operator.IN || op == Operator.NOT_IN) {
                    boolean z4 = op == Operator.NOT_IN;
                    accept('(');
                    JSONArray jSONArray = new JSONArray();
                    jSONArray.add(readValue());
                    while (true) {
                        skipWhitespace();
                        if (this.ch != ',') {
                            break;
                        }
                        next();
                        jSONArray.add(readValue());
                    }
                    boolean z5 = true;
                    boolean z6 = true;
                    boolean z7 = true;
                    for (Object obj : jSONArray) {
                        if (obj != null) {
                            Class<?> cls = obj.getClass();
                            if (z5 && cls != Byte.class && cls != Short.class && cls != Integer.class && cls != Long.class) {
                                z5 = false;
                                z7 = false;
                            }
                            if (z6 && cls != String.class) {
                                z6 = false;
                            }
                        } else if (z5) {
                            z5 = false;
                        }
                    }
                    if (jSONArray.size() == 1 && jSONArray.get(0) == null) {
                        if (z4) {
                            nullSegement = new NotNullSegement(name);
                        } else {
                            nullSegement = new NullSegement(name);
                        }
                        while (this.ch == ' ') {
                            next();
                        }
                        if (this.ch == '&' || this.ch == '|') {
                            nullSegement = filterRest(nullSegement);
                        }
                        accept(')');
                        if (z2) {
                            accept(')');
                        }
                        if (z) {
                            accept(']');
                        }
                        return nullSegement;
                    }
                    if (z5) {
                        if (jSONArray.size() == 1) {
                            Filter intOpSegement = new IntOpSegement(name, TypeUtils.longExtractValue((Number) jSONArray.get(0)), z4 ? Operator.NE : Operator.EQ);
                            while (this.ch == ' ') {
                                next();
                            }
                            if (this.ch == '&' || this.ch == '|') {
                                intOpSegement = filterRest(intOpSegement);
                            }
                            accept(')');
                            if (z2) {
                                accept(')');
                            }
                            if (z) {
                                accept(']');
                            }
                            return intOpSegement;
                        }
                        long[] jArr = new long[jSONArray.size()];
                        for (int i2 = 0; i2 < jArr.length; i2++) {
                            jArr[i2] = TypeUtils.longExtractValue((Number) jSONArray.get(i2));
                        }
                        Filter intInSegement = new IntInSegement(name, jArr, z4);
                        while (this.ch == ' ') {
                            next();
                        }
                        if (this.ch == '&' || this.ch == '|') {
                            intInSegement = filterRest(intInSegement);
                        }
                        accept(')');
                        if (z2) {
                            accept(')');
                        }
                        if (z) {
                            accept(']');
                        }
                        return intInSegement;
                    }
                    if (!z6) {
                        if (z7) {
                            Long[] lArr = new Long[jSONArray.size()];
                            for (int i3 = 0; i3 < lArr.length; i3++) {
                                Number number = (Number) jSONArray.get(i3);
                                if (number != null) {
                                    lArr[i3] = Long.valueOf(TypeUtils.longExtractValue(number));
                                }
                            }
                            Filter intObjInSegement = new IntObjInSegement(name, lArr, z4);
                            while (this.ch == ' ') {
                                next();
                            }
                            if (this.ch == '&' || this.ch == '|') {
                                intObjInSegement = filterRest(intObjInSegement);
                            }
                            accept(')');
                            if (z2) {
                                accept(')');
                            }
                            if (z) {
                                accept(']');
                            }
                            return intObjInSegement;
                        }
                        throw new UnsupportedOperationException();
                    }
                    if (jSONArray.size() == 1) {
                        Filter stringOpSegement = new StringOpSegement(name, (String) jSONArray.get(0), z4 ? Operator.NE : Operator.EQ);
                        while (this.ch == ' ') {
                            next();
                        }
                        if (this.ch == '&' || this.ch == '|') {
                            stringOpSegement = filterRest(stringOpSegement);
                        }
                        accept(')');
                        if (z2) {
                            accept(')');
                        }
                        if (z) {
                            accept(']');
                        }
                        return stringOpSegement;
                    }
                    String[] strArr2 = new String[jSONArray.size()];
                    jSONArray.toArray(strArr2);
                    Filter stringInSegement = new StringInSegement(name, strArr2, z4);
                    while (this.ch == ' ') {
                        next();
                    }
                    if (this.ch == '&' || this.ch == '|') {
                        stringInSegement = filterRest(stringInSegement);
                    }
                    accept(')');
                    if (z2) {
                        accept(')');
                    }
                    if (z) {
                        accept(']');
                    }
                    return stringInSegement;
                }
                int i4 = 2;
                String str3 = null;
                if (this.ch == '\'' || this.ch == '\"') {
                    String string = readString();
                    if (op == Operator.RLIKE) {
                        matchSegement = new RlikeSegement(name, string, false);
                    } else if (op == Operator.NOT_RLIKE) {
                        matchSegement = new RlikeSegement(name, string, true);
                    } else {
                        if (op == Operator.LIKE || op == Operator.NOT_LIKE) {
                            while (string.indexOf("%%") != -1) {
                                string = string.replaceAll("%%", "%");
                            }
                            boolean z8 = op == Operator.NOT_LIKE;
                            int iIndexOf = string.indexOf(37);
                            if (iIndexOf == -1) {
                                if (op == Operator.LIKE) {
                                    operator = Operator.EQ;
                                } else {
                                    operator = Operator.NE;
                                }
                                matchSegement = new StringOpSegement(name, string, operator);
                            } else {
                                String[] strArrSplit = string.split("%");
                                if (iIndexOf == 0) {
                                    if (string.charAt(string.length() - 1) == '%') {
                                        String[] strArr3 = new String[strArrSplit.length - 1];
                                        System.arraycopy(strArrSplit, 1, strArr3, 0, strArr3.length);
                                        strArr = strArr3;
                                        str = null;
                                    } else {
                                        String str4 = strArrSplit[strArrSplit.length - 1];
                                        if (strArrSplit.length > 2) {
                                            String[] strArr4 = new String[strArrSplit.length - 2];
                                            System.arraycopy(strArrSplit, 1, strArr4, 0, strArr4.length);
                                            strArr = strArr4;
                                            str = str4;
                                        } else {
                                            str = str4;
                                            strArr = null;
                                        }
                                    }
                                } else {
                                    if (string.charAt(string.length() - 1) == '%') {
                                        if (strArrSplit.length == 1) {
                                            str2 = strArrSplit[0];
                                        } else {
                                            strArr = strArrSplit;
                                            str = null;
                                        }
                                    } else if (strArrSplit.length == 1) {
                                        str2 = strArrSplit[0];
                                    } else if (strArrSplit.length == 2) {
                                        str2 = strArrSplit[0];
                                        str = strArrSplit[1];
                                        strArr = null;
                                        str3 = str2;
                                    } else {
                                        String str5 = strArrSplit[0];
                                        String str6 = strArrSplit[strArrSplit.length - 1];
                                        String[] strArr5 = new String[strArrSplit.length - 2];
                                        System.arraycopy(strArrSplit, 1, strArr5, 0, strArr5.length);
                                        strArr = strArr5;
                                        str3 = str5;
                                        str = str6;
                                    }
                                    str = null;
                                    strArr = null;
                                    str3 = str2;
                                }
                                matchSegement = new MatchSegement(name, str3, str, strArr, z8);
                            }
                            while (this.ch == ' ') {
                                next();
                            }
                            if (this.ch != '&' || this.ch == '|') {
                                matchSegement = filterRest(matchSegement);
                            }
                            if (z2) {
                                accept(')');
                            }
                            if (z) {
                                accept(']');
                            }
                            return matchSegement;
                        }
                        matchSegement = new StringOpSegement(name, string, op);
                    }
                    while (this.ch == ' ') {
                        next();
                    }
                    if (this.ch != '&') {
                        matchSegement = filterRest(matchSegement);
                    } else {
                        matchSegement = filterRest(matchSegement);
                    }
                    if (z2) {
                        accept(')');
                    }
                    if (z) {
                        accept(']');
                    }
                    return matchSegement;
                }
                if (isDigitFirst(this.ch)) {
                    long longValue = readLongValue();
                    double doubleValue = this.ch == '.' ? readDoubleValue(longValue) : 0.0d;
                    if (doubleValue == 0.0d) {
                        doubleOpSegement = new IntOpSegement(name, longValue, op);
                    } else {
                        doubleOpSegement = new DoubleOpSegement(name, doubleValue, op);
                    }
                    while (this.ch == ' ') {
                        next();
                    }
                    if (this.ch == '&' || this.ch == '|') {
                        doubleOpSegement = filterRest(doubleOpSegement);
                    }
                    if (z2) {
                        accept(')');
                    }
                    if (z) {
                        accept(']');
                    }
                    return doubleOpSegement;
                }
                if (this.ch == '$') {
                    RefOpSegement refOpSegement = new RefOpSegement(name, readSegement(), op);
                    this.hasRefSegment = true;
                    while (this.ch == ' ') {
                        next();
                    }
                    if (z2) {
                        accept(')');
                    }
                    if (z) {
                        accept(']');
                    }
                    return refOpSegement;
                }
                if (this.ch == '/') {
                    StringBuilder sb = new StringBuilder();
                    while (true) {
                        next();
                        if (this.ch == '/') {
                            break;
                        }
                        if (this.ch == '\\') {
                            next();
                            sb.append(this.ch);
                        } else {
                            sb.append(this.ch);
                        }
                    }
                    next();
                    if (this.ch == 'i') {
                        next();
                    } else {
                        i4 = 0;
                    }
                    RegMatchSegement regMatchSegement = new RegMatchSegement(name, Pattern.compile(sb.toString(), i4), op);
                    if (z2) {
                        accept(')');
                    }
                    if (z) {
                        accept(']');
                    }
                    return regMatchSegement;
                }
                if (this.ch == 'n') {
                    if ("null".equals(readName())) {
                        if (op == Operator.EQ) {
                            notNullSegement = new NullSegement(name);
                        } else {
                            notNullSegement = op == Operator.NE ? new NotNullSegement(name) : null;
                        }
                        if (notNullSegement != null) {
                            while (this.ch == ' ') {
                                next();
                            }
                            if (this.ch == '&' || this.ch == '|') {
                                notNullSegement = filterRest(notNullSegement);
                            }
                        }
                        if (z2) {
                            accept(')');
                        }
                        accept(']');
                        if (notNullSegement != null) {
                            return notNullSegement;
                        }
                        throw new UnsupportedOperationException();
                    }
                } else if (this.ch == 't') {
                    if ("true".equals(readName())) {
                        if (op == Operator.EQ) {
                            valueSegment2 = new ValueSegment(name, Boolean.TRUE, true);
                        } else {
                            valueSegment2 = op == Operator.NE ? new ValueSegment(name, Boolean.TRUE, false) : null;
                        }
                        if (valueSegment2 != null) {
                            while (this.ch == ' ') {
                                next();
                            }
                            if (this.ch == '&' || this.ch == '|') {
                                valueSegment2 = filterRest(valueSegment2);
                            }
                        }
                        if (z2) {
                            accept(')');
                        }
                        accept(']');
                        if (valueSegment2 != null) {
                            return valueSegment2;
                        }
                        throw new UnsupportedOperationException();
                    }
                } else if (this.ch == 'f' && "false".equals(readName())) {
                    if (op == Operator.EQ) {
                        valueSegment = new ValueSegment(name, Boolean.FALSE, true);
                    } else {
                        valueSegment = op == Operator.NE ? new ValueSegment(name, Boolean.FALSE, false) : null;
                    }
                    if (valueSegment != null) {
                        while (this.ch == ' ') {
                            next();
                        }
                        if (this.ch == '&' || this.ch == '|') {
                            valueSegment = filterRest(valueSegment);
                        }
                    }
                    if (z2) {
                        accept(')');
                    }
                    accept(']');
                    if (valueSegment != null) {
                        return valueSegment;
                    }
                    throw new UnsupportedOperationException();
                }
                throw new UnsupportedOperationException();
            }
            int i5 = this.pos - 1;
            while (this.ch != ']' && this.ch != '/' && !isEOF() && (this.ch != '.' || z2 || z2)) {
                if (this.ch == '\\') {
                    next();
                }
                next();
            }
            if (z || this.ch == '/' || this.ch == '.') {
                i = this.pos - 1;
            } else {
                i = this.pos;
            }
            String strSubstring = this.path.substring(i5, i);
            if (strSubstring.indexOf("\\.") != -1) {
                String strReplaceAll = strSubstring.replaceAll("\\\\\\.", "\\.");
                if (strReplaceAll.indexOf("\\-") != -1) {
                    strReplaceAll = strReplaceAll.replaceAll("\\\\-", "-");
                }
                if (z2) {
                    accept(')');
                }
                return new PropertySegment(strReplaceAll, false);
            }
            Segment segmentBuildArraySegement = buildArraySegement(strSubstring);
            if (z && !isEOF()) {
                accept(']');
            }
            return segmentBuildArraySegement;
        }

        Filter filterRest(Filter filter) {
            boolean z = this.ch == '&';
            if ((this.ch != '&' || getNextChar() != '&') && (this.ch != '|' || getNextChar() != '|')) {
                return filter;
            }
            next();
            next();
            while (this.ch == ' ') {
                next();
            }
            return new FilterGroup(filter, (Filter) parseArrayAccessFilter(false), z);
        }

        protected long readLongValue() {
            int i = this.pos - 1;
            if (this.ch == '+' || this.ch == '-') {
                next();
            }
            while (this.ch >= '0' && this.ch <= '9') {
                next();
            }
            return Long.parseLong(this.path.substring(i, this.pos - 1));
        }

        protected double readDoubleValue(long j) {
            int i = this.pos - 1;
            next();
            while (this.ch >= '0' && this.ch <= '9') {
                next();
            }
            return Double.parseDouble(this.path.substring(i, this.pos - 1)) + j;
        }

        protected Object readValue() {
            skipWhitespace();
            if (isDigitFirst(this.ch)) {
                return Long.valueOf(readLongValue());
            }
            if (this.ch == '\"' || this.ch == '\'') {
                return readString();
            }
            if (this.ch == 'n') {
                if ("null".equals(readName())) {
                    return null;
                }
                throw new JSONPathException(this.path);
            }
            throw new UnsupportedOperationException();
        }

        protected Operator readOp() {
            Operator operator;
            if (this.ch == '=') {
                next();
                if (this.ch == '~') {
                    next();
                    operator = Operator.REG_MATCH;
                } else if (this.ch == '=') {
                    next();
                    operator = Operator.EQ;
                } else {
                    operator = Operator.EQ;
                }
            } else if (this.ch == '!') {
                next();
                accept('=');
                operator = Operator.NE;
            } else if (this.ch == '<') {
                next();
                if (this.ch == '=') {
                    next();
                    operator = Operator.LE;
                } else {
                    operator = Operator.LT;
                }
            } else if (this.ch == '>') {
                next();
                if (this.ch == '=') {
                    next();
                    operator = Operator.GE;
                } else {
                    operator = Operator.GT;
                }
            } else {
                operator = null;
            }
            if (operator != null) {
                return operator;
            }
            String name = readName();
            if ("not".equalsIgnoreCase(name)) {
                skipWhitespace();
                String name2 = readName();
                if ("like".equalsIgnoreCase(name2)) {
                    return Operator.NOT_LIKE;
                }
                if ("rlike".equalsIgnoreCase(name2)) {
                    return Operator.NOT_RLIKE;
                }
                if ("in".equalsIgnoreCase(name2)) {
                    return Operator.NOT_IN;
                }
                if ("between".equalsIgnoreCase(name2)) {
                    return Operator.NOT_BETWEEN;
                }
                throw new UnsupportedOperationException();
            }
            if ("nin".equalsIgnoreCase(name)) {
                return Operator.NOT_IN;
            }
            if ("like".equalsIgnoreCase(name)) {
                return Operator.LIKE;
            }
            if ("rlike".equalsIgnoreCase(name)) {
                return Operator.RLIKE;
            }
            if ("in".equalsIgnoreCase(name)) {
                return Operator.IN;
            }
            if ("between".equalsIgnoreCase(name)) {
                return Operator.BETWEEN;
            }
            throw new UnsupportedOperationException();
        }

        String readName() {
            skipWhitespace();
            if (this.ch != '\\' && !Character.isJavaIdentifierStart(this.ch)) {
                throw new JSONPathException("illeal jsonpath syntax. " + this.path);
            }
            StringBuilder sb = new StringBuilder();
            while (!isEOF()) {
                if (this.ch == '\\') {
                    next();
                    sb.append(this.ch);
                    if (isEOF()) {
                        return sb.toString();
                    }
                    next();
                } else {
                    if (!Character.isJavaIdentifierPart(this.ch)) {
                        break;
                    }
                    sb.append(this.ch);
                    next();
                }
            }
            if (isEOF() && Character.isJavaIdentifierPart(this.ch)) {
                sb.append(this.ch);
            }
            return sb.toString();
        }

        String readString() {
            char c = this.ch;
            next();
            int i = this.pos - 1;
            while (this.ch != c && !isEOF()) {
                next();
            }
            String strSubstring = this.path.substring(i, isEOF() ? this.pos : this.pos - 1);
            accept(c);
            return strSubstring;
        }

        void accept(char c) {
            if (this.ch != c) {
                throw new JSONPathException("expect '" + c + ", but '" + this.ch + "'");
            }
            if (isEOF()) {
                return;
            }
            next();
        }

        public Segment[] explain() {
            if (this.path == null || this.path.length() == 0) {
                throw new IllegalArgumentException();
            }
            Segment[] segmentArr = new Segment[8];
            while (true) {
                Segment segement = readSegement();
                if (segement == null) {
                    break;
                }
                if (segement instanceof PropertySegment) {
                    PropertySegment propertySegment = (PropertySegment) segement;
                    if (propertySegment.deep || !propertySegment.propertyName.equals("*")) {
                    }
                }
                if (this.level == segmentArr.length) {
                    Segment[] segmentArr2 = new Segment[(this.level * 3) / 2];
                    System.arraycopy(segmentArr, 0, segmentArr2, 0, this.level);
                    segmentArr = segmentArr2;
                }
                int i = this.level;
                this.level = i + 1;
                segmentArr[i] = segement;
            }
            if (this.level == segmentArr.length) {
                return segmentArr;
            }
            Segment[] segmentArr3 = new Segment[this.level];
            System.arraycopy(segmentArr, 0, segmentArr3, 0, this.level);
            return segmentArr3;
        }

        Segment buildArraySegement(String str) {
            int length = str.length();
            int i = 0;
            char cCharAt = str.charAt(0);
            int i2 = length - 1;
            char cCharAt2 = str.charAt(i2);
            int iIndexOf = str.indexOf(44);
            if (str.length() > 2 && cCharAt == '\'' && cCharAt2 == '\'') {
                if (iIndexOf == -1) {
                    return new PropertySegment(str.substring(1, i2), false);
                }
                String[] strArrSplit = str.split(",");
                String[] strArr = new String[strArrSplit.length];
                while (i < strArrSplit.length) {
                    String str2 = strArrSplit[i];
                    strArr[i] = str2.substring(1, str2.length() - 1);
                    i++;
                }
                return new MultiPropertySegment(strArr);
            }
            int iIndexOf2 = str.indexOf(58);
            if (iIndexOf == -1 && iIndexOf2 == -1) {
                if (TypeUtils.isNumber(str)) {
                    try {
                        return new ArrayAccessSegment(Integer.parseInt(str));
                    } catch (NumberFormatException unused) {
                        return new PropertySegment(str, false);
                    }
                }
                if (str.charAt(0) == '\"' && str.charAt(str.length() - 1) == '\"') {
                    str = str.substring(1, str.length() - 1);
                }
                return new PropertySegment(str, false);
            }
            if (iIndexOf != -1) {
                String[] strArrSplit2 = str.split(",");
                int[] iArr = new int[strArrSplit2.length];
                while (i < strArrSplit2.length) {
                    iArr[i] = Integer.parseInt(strArrSplit2[i]);
                    i++;
                }
                return new MultiIndexSegment(iArr);
            }
            if (iIndexOf2 != -1) {
                String[] strArrSplit3 = str.split(":");
                int[] iArr2 = new int[strArrSplit3.length];
                for (int i3 = 0; i3 < strArrSplit3.length; i3++) {
                    String str3 = strArrSplit3[i3];
                    if (str3.length() != 0) {
                        iArr2[i3] = Integer.parseInt(str3);
                    } else if (i3 == 0) {
                        iArr2[i3] = 0;
                    } else {
                        throw new UnsupportedOperationException();
                    }
                }
                int i4 = iArr2[0];
                int i5 = iArr2.length > 1 ? iArr2[1] : -1;
                int i6 = iArr2.length == 3 ? iArr2[2] : 1;
                if (i5 < 0 || i5 >= i4) {
                    if (i6 <= 0) {
                        throw new UnsupportedOperationException("step must greater than zero : " + i6);
                    }
                    return new RangeSegment(i4, i5, i6);
                }
                throw new UnsupportedOperationException("end must greater than or equals start. start " + i4 + ",  end " + i5);
            }
            throw new UnsupportedOperationException();
        }
    }

    static class SizeSegment implements Segment {
        public static final SizeSegment instance = new SizeSegment();

        SizeSegment() {
        }

        @Override // com.alibaba.fastjson.JSONPath.Segment
        public Integer eval(JSONPath jSONPath, Object obj, Object obj2) {
            return Integer.valueOf(jSONPath.evalSize(obj2));
        }

        @Override // com.alibaba.fastjson.JSONPath.Segment
        public void extract(JSONPath jSONPath, DefaultJSONParser defaultJSONParser, Context context) {
            throw new UnsupportedOperationException();
        }
    }

    static class MaxSegment implements Segment {
        public static final MaxSegment instance = new MaxSegment();

        MaxSegment() {
        }

        @Override // com.alibaba.fastjson.JSONPath.Segment
        public Object eval(JSONPath jSONPath, Object obj, Object obj2) {
            if (obj instanceof Collection) {
                Object obj3 = null;
                for (Object obj4 : (Collection) obj) {
                    if (obj4 != null && (obj3 == null || JSONPath.compare(obj3, obj4) < 0)) {
                        obj3 = obj4;
                    }
                }
                return obj3;
            }
            throw new UnsupportedOperationException();
        }

        @Override // com.alibaba.fastjson.JSONPath.Segment
        public void extract(JSONPath jSONPath, DefaultJSONParser defaultJSONParser, Context context) {
            throw new UnsupportedOperationException();
        }
    }

    static class MinSegment implements Segment {
        public static final MinSegment instance = new MinSegment();

        MinSegment() {
        }

        @Override // com.alibaba.fastjson.JSONPath.Segment
        public Object eval(JSONPath jSONPath, Object obj, Object obj2) {
            if (obj instanceof Collection) {
                Object obj3 = null;
                for (Object obj4 : (Collection) obj) {
                    if (obj4 != null && (obj3 == null || JSONPath.compare(obj3, obj4) > 0)) {
                        obj3 = obj4;
                    }
                }
                return obj3;
            }
            throw new UnsupportedOperationException();
        }

        @Override // com.alibaba.fastjson.JSONPath.Segment
        public void extract(JSONPath jSONPath, DefaultJSONParser defaultJSONParser, Context context) {
            throw new UnsupportedOperationException();
        }
    }

    /* JADX WARN: Code duplicated, block: B:71:0x0162 A[PHI: r3
      0x0162: PHI (r3v17 java.lang.Object) = 
      (r3v0 java.lang.Object)
      (r3v0 java.lang.Object)
      (r3v0 java.lang.Object)
      (r3v0 java.lang.Object)
      (r3v12 java.lang.Object)
      (r3v0 java.lang.Object)
      (r3v0 java.lang.Object)
     binds: [B:61:0x012c, B:69:0x0152, B:58:0x011b, B:47:0x00e4, B:28:0x0087, B:33:0x009d, B:18:0x0053] A[DONT_GENERATE, DONT_INLINE]] */
    static int compare(Object obj, Object obj2) {
        Object d;
        Object f;
        if (obj.getClass() == obj2.getClass()) {
            return ((Comparable) obj).compareTo(obj2);
        }
        Class<?> cls = obj.getClass();
        Class<?> cls2 = obj2.getClass();
        if (cls == BigDecimal.class) {
            if (cls2 == Integer.class) {
                f = new BigDecimal(((Integer) obj2).intValue());
            } else if (cls2 == Long.class) {
                f = new BigDecimal(((Long) obj2).longValue());
            } else if (cls2 == Float.class) {
                f = new BigDecimal(((Float) obj2).floatValue());
            } else if (cls2 == Double.class) {
                f = new BigDecimal(((Double) obj2).doubleValue());
            } else {
                f = obj2;
            }
        } else if (cls == Long.class) {
            if (cls2 == Integer.class) {
                f = new Long(((Integer) obj2).intValue());
            } else {
                if (cls2 == BigDecimal.class) {
                    d = new BigDecimal(((Long) obj).longValue());
                } else if (cls2 == Float.class) {
                    d = new Float(((Long) obj).longValue());
                } else {
                    if (cls2 == Double.class) {
                        d = new Double(((Long) obj).longValue());
                    }
                    f = obj2;
                }
                obj = d;
                f = obj2;
            }
        } else if (cls == Integer.class) {
            if (cls2 == Long.class) {
                d = new Long(((Integer) obj).intValue());
            } else if (cls2 == BigDecimal.class) {
                d = new BigDecimal(((Integer) obj).intValue());
            } else if (cls2 == Float.class) {
                d = new Float(((Integer) obj).intValue());
            } else {
                if (cls2 == Double.class) {
                    d = new Double(((Integer) obj).intValue());
                }
                f = obj2;
            }
            obj = d;
            f = obj2;
        } else if (cls == Double.class) {
            if (cls2 == Integer.class) {
                f = new Double(((Integer) obj2).intValue());
            } else if (cls2 == Long.class) {
                f = new Double(((Long) obj2).longValue());
            } else if (cls2 == Float.class) {
                f = new Double(((Float) obj2).floatValue());
            } else {
                f = obj2;
            }
        } else if (cls != Float.class) {
            f = obj2;
        } else if (cls2 == Integer.class) {
            f = new Float(((Integer) obj2).intValue());
        } else if (cls2 == Long.class) {
            f = new Float(((Long) obj2).longValue());
        } else {
            if (cls2 == Double.class) {
                d = new Double(((Float) obj).floatValue());
                obj = d;
            }
            f = obj2;
        }
        return ((Comparable) obj).compareTo(f);
    }

    static class KeySetSegment implements Segment {
        public static final KeySetSegment instance = new KeySetSegment();

        KeySetSegment() {
        }

        @Override // com.alibaba.fastjson.JSONPath.Segment
        public Object eval(JSONPath jSONPath, Object obj, Object obj2) {
            return jSONPath.evalKeySet(obj2);
        }

        @Override // com.alibaba.fastjson.JSONPath.Segment
        public void extract(JSONPath jSONPath, DefaultJSONParser defaultJSONParser, Context context) {
            throw new UnsupportedOperationException();
        }
    }

    static class PropertySegment implements Segment {
        private final boolean deep;
        private final String propertyName;
        private final long propertyNameHash;

        public PropertySegment(String str, boolean z) {
            this.propertyName = str;
            this.propertyNameHash = TypeUtils.fnv1a_64(str);
            this.deep = z;
        }

        @Override // com.alibaba.fastjson.JSONPath.Segment
        public Object eval(JSONPath jSONPath, Object obj, Object obj2) {
            if (this.deep) {
                ArrayList arrayList = new ArrayList();
                jSONPath.deepScan(obj2, this.propertyName, arrayList);
                return arrayList;
            }
            return jSONPath.getPropertyValue(obj2, this.propertyName, this.propertyNameHash);
        }

        @Override // com.alibaba.fastjson.JSONPath.Segment
        public void extract(JSONPath jSONPath, DefaultJSONParser defaultJSONParser, Context context) {
            Object objIntegerValue;
            Object objIntegerValue2;
            JSONArray jSONArray;
            Object objIntegerValue3;
            JSONLexerBase jSONLexerBase = (JSONLexerBase) defaultJSONParser.lexer;
            if (this.deep && context.object == null) {
                context.object = new JSONArray();
            }
            if (jSONLexerBase.token() == 14) {
                if ("*".equals(this.propertyName)) {
                    return;
                }
                jSONLexerBase.nextToken();
                if (this.deep) {
                    jSONArray = (JSONArray) context.object;
                } else {
                    jSONArray = new JSONArray();
                }
                while (true) {
                    int i = jSONLexerBase.token();
                    if (i != 12) {
                        if (i == 14) {
                            if (this.deep) {
                                extract(jSONPath, defaultJSONParser, context);
                            } else {
                                jSONLexerBase.skipObject();
                            }
                        } else {
                            switch (i) {
                                case 2:
                                case 3:
                                case 4:
                                case 5:
                                case 6:
                                case 7:
                                case 8:
                                    jSONLexerBase.nextToken();
                                    break;
                            }
                        }
                    } else if (this.deep) {
                        extract(jSONPath, defaultJSONParser, context);
                    } else {
                        int iSeekObjectToField = jSONLexerBase.seekObjectToField(this.propertyNameHash, this.deep);
                        if (iSeekObjectToField == 3) {
                            int i2 = jSONLexerBase.token();
                            if (i2 == 2) {
                                objIntegerValue3 = jSONLexerBase.integerValue();
                                jSONLexerBase.nextToken();
                            } else if (i2 == 4) {
                                objIntegerValue3 = jSONLexerBase.stringVal();
                                jSONLexerBase.nextToken();
                            } else {
                                objIntegerValue3 = defaultJSONParser.parse();
                            }
                            jSONArray.add(objIntegerValue3);
                            if (jSONLexerBase.token() == 13) {
                                jSONLexerBase.nextToken();
                            } else {
                                jSONLexerBase.skipObject();
                            }
                        } else if (iSeekObjectToField == -1) {
                            continue;
                        } else {
                            if (this.deep) {
                                throw new UnsupportedOperationException(jSONLexerBase.info());
                            }
                            jSONLexerBase.skipObject();
                        }
                    }
                    if (jSONLexerBase.token() == 15) {
                        jSONLexerBase.nextToken();
                        if (this.deep || jSONArray.size() <= 0) {
                            return;
                        }
                        context.object = jSONArray;
                        return;
                    }
                    if (jSONLexerBase.token() == 16) {
                        jSONLexerBase.nextToken();
                    } else {
                        throw new JSONException("illegal json : " + jSONLexerBase.info());
                    }
                }
            } else {
                if (!this.deep) {
                    if (jSONLexerBase.seekObjectToField(this.propertyNameHash, this.deep) == 3 && context.eval) {
                        switch (jSONLexerBase.token()) {
                            case 2:
                                objIntegerValue2 = jSONLexerBase.integerValue();
                                jSONLexerBase.nextToken(16);
                                break;
                            case 3:
                                objIntegerValue2 = jSONLexerBase.decimalValue();
                                jSONLexerBase.nextToken(16);
                                break;
                            case 4:
                                objIntegerValue2 = jSONLexerBase.stringVal();
                                jSONLexerBase.nextToken(16);
                                break;
                            default:
                                objIntegerValue2 = defaultJSONParser.parse();
                                break;
                        }
                        if (context.eval) {
                            context.object = objIntegerValue2;
                            return;
                        }
                        return;
                    }
                    return;
                }
                while (true) {
                    int iSeekObjectToField2 = jSONLexerBase.seekObjectToField(this.propertyNameHash, this.deep);
                    if (iSeekObjectToField2 == -1) {
                        return;
                    }
                    if (iSeekObjectToField2 == 3) {
                        if (context.eval) {
                            switch (jSONLexerBase.token()) {
                                case 2:
                                    objIntegerValue = jSONLexerBase.integerValue();
                                    jSONLexerBase.nextToken(16);
                                    break;
                                case 3:
                                    objIntegerValue = jSONLexerBase.decimalValue();
                                    jSONLexerBase.nextToken(16);
                                    break;
                                case 4:
                                    objIntegerValue = jSONLexerBase.stringVal();
                                    jSONLexerBase.nextToken(16);
                                    break;
                                default:
                                    objIntegerValue = defaultJSONParser.parse();
                                    break;
                            }
                            if (context.eval) {
                                if (context.object instanceof List) {
                                    List list = (List) context.object;
                                    if (list.size() == 0 && (objIntegerValue instanceof List)) {
                                        context.object = objIntegerValue;
                                    } else {
                                        list.add(objIntegerValue);
                                    }
                                } else {
                                    context.object = objIntegerValue;
                                }
                            }
                        }
                    } else if (iSeekObjectToField2 == 1 || iSeekObjectToField2 == 2) {
                        extract(jSONPath, defaultJSONParser, context);
                    }
                }
            }
        }

        public void setValue(JSONPath jSONPath, Object obj, Object obj2) {
            if (this.deep) {
                jSONPath.deepSet(obj, this.propertyName, this.propertyNameHash, obj2);
            } else {
                jSONPath.setPropertyValue(obj, this.propertyName, this.propertyNameHash, obj2);
            }
        }

        public boolean remove(JSONPath jSONPath, Object obj) {
            return jSONPath.removePropertyValue(obj, this.propertyName);
        }
    }

    static class MultiPropertySegment implements Segment {
        private final String[] propertyNames;
        private final long[] propertyNamesHash;

        public MultiPropertySegment(String[] strArr) {
            this.propertyNames = strArr;
            this.propertyNamesHash = new long[strArr.length];
            for (int i = 0; i < this.propertyNamesHash.length; i++) {
                this.propertyNamesHash[i] = TypeUtils.fnv1a_64(strArr[i]);
            }
        }

        @Override // com.alibaba.fastjson.JSONPath.Segment
        public Object eval(JSONPath jSONPath, Object obj, Object obj2) {
            ArrayList arrayList = new ArrayList(this.propertyNames.length);
            for (int i = 0; i < this.propertyNames.length; i++) {
                arrayList.add(jSONPath.getPropertyValue(obj2, this.propertyNames[i], this.propertyNamesHash[i]));
            }
            return arrayList;
        }

        @Override // com.alibaba.fastjson.JSONPath.Segment
        public void extract(JSONPath jSONPath, DefaultJSONParser defaultJSONParser, Context context) {
            JSONArray jSONArray;
            Object objIntegerValue;
            JSONLexerBase jSONLexerBase = (JSONLexerBase) defaultJSONParser.lexer;
            if (context.object == null) {
                jSONArray = new JSONArray();
                context.object = jSONArray;
            } else {
                jSONArray = (JSONArray) context.object;
            }
            for (int size = jSONArray.size(); size < this.propertyNamesHash.length; size++) {
                jSONArray.add(null);
            }
            do {
                int iSeekObjectToField = jSONLexerBase.seekObjectToField(this.propertyNamesHash);
                if (jSONLexerBase.matchStat != 3) {
                    return;
                }
                switch (jSONLexerBase.token()) {
                    case 2:
                        objIntegerValue = jSONLexerBase.integerValue();
                        jSONLexerBase.nextToken(16);
                        break;
                    case 3:
                        objIntegerValue = jSONLexerBase.decimalValue();
                        jSONLexerBase.nextToken(16);
                        break;
                    case 4:
                        objIntegerValue = jSONLexerBase.stringVal();
                        jSONLexerBase.nextToken(16);
                        break;
                    default:
                        objIntegerValue = defaultJSONParser.parse();
                        break;
                }
                jSONArray.set(iSeekObjectToField, objIntegerValue);
            } while (jSONLexerBase.token() == 16);
        }
    }

    static class WildCardSegment implements Segment {
        public static final WildCardSegment instance = new WildCardSegment(false);
        public static final WildCardSegment instance_deep = new WildCardSegment(true);
        private boolean deep;

        private WildCardSegment(boolean z) {
            this.deep = z;
        }

        @Override // com.alibaba.fastjson.JSONPath.Segment
        public Object eval(JSONPath jSONPath, Object obj, Object obj2) {
            if (!this.deep) {
                return jSONPath.getPropertyValues(obj2);
            }
            ArrayList arrayList = new ArrayList();
            jSONPath.deepGetPropertyValues(obj2, arrayList);
            return arrayList;
        }

        @Override // com.alibaba.fastjson.JSONPath.Segment
        public void extract(JSONPath jSONPath, DefaultJSONParser defaultJSONParser, Context context) {
            if (context.eval) {
                Object obj = defaultJSONParser.parse();
                if (this.deep) {
                    ArrayList arrayList = new ArrayList();
                    jSONPath.deepGetPropertyValues(obj, arrayList);
                    context.object = arrayList;
                    return;
                } else {
                    if (obj instanceof JSONObject) {
                        Collection<Object> collectionValues = ((JSONObject) obj).values();
                        JSONArray jSONArray = new JSONArray(collectionValues.size());
                        Iterator<Object> it = collectionValues.iterator();
                        while (it.hasNext()) {
                            jSONArray.add(it.next());
                        }
                        context.object = jSONArray;
                        return;
                    }
                    if (obj instanceof JSONArray) {
                        context.object = obj;
                        return;
                    }
                }
            }
            throw new JSONException("TODO");
        }
    }

    static class ArrayAccessSegment implements Segment {
        private final int index;

        public ArrayAccessSegment(int i) {
            this.index = i;
        }

        @Override // com.alibaba.fastjson.JSONPath.Segment
        public Object eval(JSONPath jSONPath, Object obj, Object obj2) {
            return jSONPath.getArrayItem(obj2, this.index);
        }

        public boolean setValue(JSONPath jSONPath, Object obj, Object obj2) {
            return jSONPath.setArrayItem(jSONPath, obj, this.index, obj2);
        }

        public boolean remove(JSONPath jSONPath, Object obj) {
            return jSONPath.removeArrayItem(jSONPath, obj, this.index);
        }

        @Override // com.alibaba.fastjson.JSONPath.Segment
        public void extract(JSONPath jSONPath, DefaultJSONParser defaultJSONParser, Context context) {
            if (((JSONLexerBase) defaultJSONParser.lexer).seekArrayToItem(this.index) && context.eval) {
                context.object = defaultJSONParser.parse();
            }
        }
    }

    static class MultiIndexSegment implements Segment {
        private final int[] indexes;

        public MultiIndexSegment(int[] iArr) {
            this.indexes = iArr;
        }

        @Override // com.alibaba.fastjson.JSONPath.Segment
        public Object eval(JSONPath jSONPath, Object obj, Object obj2) {
            JSONArray jSONArray = new JSONArray(this.indexes.length);
            for (int i = 0; i < this.indexes.length; i++) {
                jSONArray.add(jSONPath.getArrayItem(obj2, this.indexes[i]));
            }
            return jSONArray;
        }

        @Override // com.alibaba.fastjson.JSONPath.Segment
        public void extract(JSONPath jSONPath, DefaultJSONParser defaultJSONParser, Context context) {
            if (context.eval) {
                Object obj = defaultJSONParser.parse();
                if (obj instanceof List) {
                    int[] iArr = new int[this.indexes.length];
                    System.arraycopy(this.indexes, 0, iArr, 0, iArr.length);
                    List list = (List) obj;
                    if (iArr[0] >= 0) {
                        for (int size = list.size() - 1; size >= 0; size--) {
                            if (Arrays.binarySearch(iArr, size) < 0) {
                                list.remove(size);
                            }
                        }
                        context.object = list;
                        return;
                    }
                }
            }
            throw new UnsupportedOperationException();
        }
    }

    static class RangeSegment implements Segment {
        private final int end;
        private final int start;
        private final int step;

        public RangeSegment(int i, int i2, int i3) {
            this.start = i;
            this.end = i2;
            this.step = i3;
        }

        @Override // com.alibaba.fastjson.JSONPath.Segment
        public Object eval(JSONPath jSONPath, Object obj, Object obj2) {
            int iIntValue = SizeSegment.instance.eval(jSONPath, obj, obj2).intValue();
            int i = this.start >= 0 ? this.start : this.start + iIntValue;
            int i2 = this.end >= 0 ? this.end : this.end + iIntValue;
            int i3 = ((i2 - i) / this.step) + 1;
            if (i3 == -1) {
                return null;
            }
            ArrayList arrayList = new ArrayList(i3);
            while (i <= i2 && i < iIntValue) {
                arrayList.add(jSONPath.getArrayItem(obj2, i));
                i += this.step;
            }
            return arrayList;
        }

        @Override // com.alibaba.fastjson.JSONPath.Segment
        public void extract(JSONPath jSONPath, DefaultJSONParser defaultJSONParser, Context context) {
            throw new UnsupportedOperationException();
        }
    }

    static class NotNullSegement implements Filter {
        private final String propertyName;
        private final long propertyNameHash;

        public NotNullSegement(String str) {
            this.propertyName = str;
            this.propertyNameHash = TypeUtils.fnv1a_64(str);
        }

        @Override // com.alibaba.fastjson.JSONPath.Filter
        public boolean apply(JSONPath jSONPath, Object obj, Object obj2, Object obj3) {
            return jSONPath.getPropertyValue(obj3, this.propertyName, this.propertyNameHash) != null;
        }
    }

    static class NullSegement implements Filter {
        private final String propertyName;
        private final long propertyNameHash;

        public NullSegement(String str) {
            this.propertyName = str;
            this.propertyNameHash = TypeUtils.fnv1a_64(str);
        }

        @Override // com.alibaba.fastjson.JSONPath.Filter
        public boolean apply(JSONPath jSONPath, Object obj, Object obj2, Object obj3) {
            return jSONPath.getPropertyValue(obj3, this.propertyName, this.propertyNameHash) == null;
        }
    }

    static class ValueSegment implements Filter {
        private boolean eq;
        private final String propertyName;
        private final long propertyNameHash;
        private final Object value;

        public ValueSegment(String str, Object obj, boolean z) {
            this.eq = true;
            if (obj == null) {
                throw new IllegalArgumentException("value is null");
            }
            this.propertyName = str;
            this.propertyNameHash = TypeUtils.fnv1a_64(str);
            this.value = obj;
            this.eq = z;
        }

        @Override // com.alibaba.fastjson.JSONPath.Filter
        public boolean apply(JSONPath jSONPath, Object obj, Object obj2, Object obj3) {
            boolean zEquals = this.value.equals(jSONPath.getPropertyValue(obj3, this.propertyName, this.propertyNameHash));
            return !this.eq ? !zEquals : zEquals;
        }
    }

    static class IntInSegement implements Filter {
        private final boolean not;
        private final String propertyName;
        private final long propertyNameHash;
        private final long[] values;

        public IntInSegement(String str, long[] jArr, boolean z) {
            this.propertyName = str;
            this.propertyNameHash = TypeUtils.fnv1a_64(str);
            this.values = jArr;
            this.not = z;
        }

        @Override // com.alibaba.fastjson.JSONPath.Filter
        public boolean apply(JSONPath jSONPath, Object obj, Object obj2, Object obj3) {
            Object propertyValue = jSONPath.getPropertyValue(obj3, this.propertyName, this.propertyNameHash);
            if (propertyValue == null) {
                return false;
            }
            if (propertyValue instanceof Number) {
                long jLongExtractValue = TypeUtils.longExtractValue((Number) propertyValue);
                for (long j : this.values) {
                    if (j == jLongExtractValue) {
                        return !this.not;
                    }
                }
            }
            return this.not;
        }
    }

    static class IntBetweenSegement implements Filter {
        private final long endValue;
        private final boolean not;
        private final String propertyName;
        private final long propertyNameHash;
        private final long startValue;

        public IntBetweenSegement(String str, long j, long j2, boolean z) {
            this.propertyName = str;
            this.propertyNameHash = TypeUtils.fnv1a_64(str);
            this.startValue = j;
            this.endValue = j2;
            this.not = z;
        }

        @Override // com.alibaba.fastjson.JSONPath.Filter
        public boolean apply(JSONPath jSONPath, Object obj, Object obj2, Object obj3) {
            Object propertyValue = jSONPath.getPropertyValue(obj3, this.propertyName, this.propertyNameHash);
            if (propertyValue == null) {
                return false;
            }
            if (propertyValue instanceof Number) {
                long jLongExtractValue = TypeUtils.longExtractValue((Number) propertyValue);
                if (jLongExtractValue >= this.startValue && jLongExtractValue <= this.endValue) {
                    return !this.not;
                }
            }
            return this.not;
        }
    }

    static class IntObjInSegement implements Filter {
        private final boolean not;
        private final String propertyName;
        private final long propertyNameHash;
        private final Long[] values;

        public IntObjInSegement(String str, Long[] lArr, boolean z) {
            this.propertyName = str;
            this.propertyNameHash = TypeUtils.fnv1a_64(str);
            this.values = lArr;
            this.not = z;
        }

        @Override // com.alibaba.fastjson.JSONPath.Filter
        public boolean apply(JSONPath jSONPath, Object obj, Object obj2, Object obj3) {
            Object propertyValue = jSONPath.getPropertyValue(obj3, this.propertyName, this.propertyNameHash);
            int i = 0;
            if (propertyValue == null) {
                Long[] lArr = this.values;
                int length = lArr.length;
                while (i < length) {
                    if (lArr[i] == null) {
                        return !this.not;
                    }
                    i++;
                }
                return this.not;
            }
            if (propertyValue instanceof Number) {
                long jLongExtractValue = TypeUtils.longExtractValue((Number) propertyValue);
                Long[] lArr2 = this.values;
                int length2 = lArr2.length;
                while (i < length2) {
                    Long l = lArr2[i];
                    if (l != null && l.longValue() == jLongExtractValue) {
                        return !this.not;
                    }
                    i++;
                }
            }
            return this.not;
        }
    }

    static class StringInSegement implements Filter {
        private final boolean not;
        private final String propertyName;
        private final long propertyNameHash;
        private final String[] values;

        public StringInSegement(String str, String[] strArr, boolean z) {
            this.propertyName = str;
            this.propertyNameHash = TypeUtils.fnv1a_64(str);
            this.values = strArr;
            this.not = z;
        }

        @Override // com.alibaba.fastjson.JSONPath.Filter
        public boolean apply(JSONPath jSONPath, Object obj, Object obj2, Object obj3) {
            Object propertyValue = jSONPath.getPropertyValue(obj3, this.propertyName, this.propertyNameHash);
            for (String str : this.values) {
                if (str == propertyValue) {
                    return !this.not;
                }
                if (str != null && str.equals(propertyValue)) {
                    return !this.not;
                }
            }
            return this.not;
        }
    }

    static class IntOpSegement implements Filter {
        private final Operator op;
        private final String propertyName;
        private final long propertyNameHash;
        private final long value;
        private BigDecimal valueDecimal;
        private Double valueDouble;
        private Float valueFloat;

        public IntOpSegement(String str, long j, Operator operator) {
            this.propertyName = str;
            this.propertyNameHash = TypeUtils.fnv1a_64(str);
            this.value = j;
            this.op = operator;
        }

        @Override // com.alibaba.fastjson.JSONPath.Filter
        public boolean apply(JSONPath jSONPath, Object obj, Object obj2, Object obj3) {
            Object propertyValue = jSONPath.getPropertyValue(obj3, this.propertyName, this.propertyNameHash);
            if (propertyValue == null || !(propertyValue instanceof Number)) {
                return false;
            }
            if (propertyValue instanceof BigDecimal) {
                if (this.valueDecimal == null) {
                    this.valueDecimal = BigDecimal.valueOf(this.value);
                }
                int iCompareTo = this.valueDecimal.compareTo((BigDecimal) propertyValue);
                switch (this.op) {
                    case EQ:
                        return iCompareTo == 0;
                    case NE:
                        return iCompareTo != 0;
                    case GE:
                        return iCompareTo <= 0;
                    case GT:
                        return iCompareTo < 0;
                    case LE:
                        return iCompareTo >= 0;
                    case LT:
                        return iCompareTo > 0;
                    default:
                        return false;
                }
            }
            if (propertyValue instanceof Float) {
                if (this.valueFloat == null) {
                    this.valueFloat = Float.valueOf(this.value);
                }
                int iCompareTo2 = this.valueFloat.compareTo((Float) propertyValue);
                switch (this.op) {
                    case EQ:
                        return iCompareTo2 == 0;
                    case NE:
                        return iCompareTo2 != 0;
                    case GE:
                        return iCompareTo2 <= 0;
                    case GT:
                        return iCompareTo2 < 0;
                    case LE:
                        return iCompareTo2 >= 0;
                    case LT:
                        return iCompareTo2 > 0;
                    default:
                        return false;
                }
            }
            if (propertyValue instanceof Double) {
                if (this.valueDouble == null) {
                    this.valueDouble = Double.valueOf(this.value);
                }
                int iCompareTo3 = this.valueDouble.compareTo((Double) propertyValue);
                switch (this.op) {
                    case EQ:
                        return iCompareTo3 == 0;
                    case NE:
                        return iCompareTo3 != 0;
                    case GE:
                        return iCompareTo3 <= 0;
                    case GT:
                        return iCompareTo3 < 0;
                    case LE:
                        return iCompareTo3 >= 0;
                    case LT:
                        return iCompareTo3 > 0;
                    default:
                        return false;
                }
            }
            long jLongExtractValue = TypeUtils.longExtractValue((Number) propertyValue);
            switch (this.op) {
                case EQ:
                    return jLongExtractValue == this.value;
                case NE:
                    return jLongExtractValue != this.value;
                case GE:
                    return jLongExtractValue >= this.value;
                case GT:
                    return jLongExtractValue > this.value;
                case LE:
                    return jLongExtractValue <= this.value;
                case LT:
                    return jLongExtractValue < this.value;
                default:
                    return false;
            }
        }
    }

    static class DoubleOpSegement implements Filter {
        private final Operator op;
        private final String propertyName;
        private final long propertyNameHash;
        private final double value;

        public DoubleOpSegement(String str, double d, Operator operator) {
            this.propertyName = str;
            this.value = d;
            this.op = operator;
            this.propertyNameHash = TypeUtils.fnv1a_64(str);
        }

        @Override // com.alibaba.fastjson.JSONPath.Filter
        public boolean apply(JSONPath jSONPath, Object obj, Object obj2, Object obj3) {
            Object propertyValue = jSONPath.getPropertyValue(obj3, this.propertyName, this.propertyNameHash);
            if (propertyValue == null || !(propertyValue instanceof Number)) {
                return false;
            }
            double dDoubleValue = ((Number) propertyValue).doubleValue();
            switch (this.op) {
                case EQ:
                    return dDoubleValue == this.value;
                case NE:
                    return dDoubleValue != this.value;
                case GE:
                    return dDoubleValue >= this.value;
                case GT:
                    return dDoubleValue > this.value;
                case LE:
                    return dDoubleValue <= this.value;
                case LT:
                    return dDoubleValue < this.value;
                default:
                    return false;
            }
        }
    }

    static class RefOpSegement implements Filter {
        private final Operator op;
        private final String propertyName;
        private final long propertyNameHash;
        private final Segment refSgement;

        public RefOpSegement(String str, Segment segment, Operator operator) {
            this.propertyName = str;
            this.refSgement = segment;
            this.op = operator;
            this.propertyNameHash = TypeUtils.fnv1a_64(str);
        }

        @Override // com.alibaba.fastjson.JSONPath.Filter
        public boolean apply(JSONPath jSONPath, Object obj, Object obj2, Object obj3) {
            Object propertyValue = jSONPath.getPropertyValue(obj3, this.propertyName, this.propertyNameHash);
            if (propertyValue == null || !(propertyValue instanceof Number)) {
                return false;
            }
            Object objEval = this.refSgement.eval(jSONPath, obj, obj);
            if ((objEval instanceof Integer) || (objEval instanceof Long) || (objEval instanceof Short) || (objEval instanceof Byte)) {
                long jLongExtractValue = TypeUtils.longExtractValue((Number) objEval);
                if ((propertyValue instanceof Integer) || (propertyValue instanceof Long) || (propertyValue instanceof Short) || (propertyValue instanceof Byte)) {
                    long jLongExtractValue2 = TypeUtils.longExtractValue((Number) propertyValue);
                    switch (this.op) {
                        case EQ:
                            return jLongExtractValue2 == jLongExtractValue;
                        case NE:
                            return jLongExtractValue2 != jLongExtractValue;
                        case GE:
                            return jLongExtractValue2 >= jLongExtractValue;
                        case GT:
                            return jLongExtractValue2 > jLongExtractValue;
                        case LE:
                            return jLongExtractValue2 <= jLongExtractValue;
                        case LT:
                            return jLongExtractValue2 < jLongExtractValue;
                    }
                }
                if (propertyValue instanceof BigDecimal) {
                    int iCompareTo = BigDecimal.valueOf(jLongExtractValue).compareTo((BigDecimal) propertyValue);
                    switch (this.op) {
                        case EQ:
                            return iCompareTo == 0;
                        case NE:
                            return iCompareTo != 0;
                        case GE:
                            return iCompareTo <= 0;
                        case GT:
                            return iCompareTo < 0;
                        case LE:
                            return iCompareTo >= 0;
                        case LT:
                            return iCompareTo > 0;
                        default:
                            return false;
                    }
                }
            }
            throw new UnsupportedOperationException();
        }
    }

    static class MatchSegement implements Filter {
        private final String[] containsValues;
        private final String endsWithValue;
        private final int minLength;
        private final boolean not;
        private final String propertyName;
        private final long propertyNameHash;
        private final String startsWithValue;

        public MatchSegement(String str, String str2, String str3, String[] strArr, boolean z) {
            this.propertyName = str;
            this.propertyNameHash = TypeUtils.fnv1a_64(str);
            this.startsWithValue = str2;
            this.endsWithValue = str3;
            this.containsValues = strArr;
            this.not = z;
            int length = str2 != null ? str2.length() + 0 : 0;
            length = str3 != null ? length + str3.length() : length;
            if (strArr != null) {
                for (String str4 : strArr) {
                    length += str4.length();
                }
            }
            this.minLength = length;
        }

        @Override // com.alibaba.fastjson.JSONPath.Filter
        public boolean apply(JSONPath jSONPath, Object obj, Object obj2, Object obj3) {
            int length;
            Object propertyValue = jSONPath.getPropertyValue(obj3, this.propertyName, this.propertyNameHash);
            if (propertyValue == null) {
                return false;
            }
            String string = propertyValue.toString();
            if (string.length() < this.minLength) {
                return this.not;
            }
            if (this.startsWithValue == null) {
                length = 0;
            } else {
                if (!string.startsWith(this.startsWithValue)) {
                    return this.not;
                }
                length = this.startsWithValue.length() + 0;
            }
            if (this.containsValues != null) {
                for (String str : this.containsValues) {
                    int iIndexOf = string.indexOf(str, length);
                    if (iIndexOf == -1) {
                        return this.not;
                    }
                    length = iIndexOf + str.length();
                }
            }
            if (this.endsWithValue != null && !string.endsWith(this.endsWithValue)) {
                return this.not;
            }
            return !this.not;
        }
    }

    static class RlikeSegement implements Filter {
        private final boolean not;
        private final Pattern pattern;
        private final String propertyName;
        private final long propertyNameHash;

        public RlikeSegement(String str, String str2, boolean z) {
            this.propertyName = str;
            this.propertyNameHash = TypeUtils.fnv1a_64(str);
            this.pattern = Pattern.compile(str2);
            this.not = z;
        }

        @Override // com.alibaba.fastjson.JSONPath.Filter
        public boolean apply(JSONPath jSONPath, Object obj, Object obj2, Object obj3) {
            Object propertyValue = jSONPath.getPropertyValue(obj3, this.propertyName, this.propertyNameHash);
            if (propertyValue == null) {
                return false;
            }
            boolean zMatches = this.pattern.matcher(propertyValue.toString()).matches();
            return this.not ? !zMatches : zMatches;
        }
    }

    static class StringOpSegement implements Filter {
        private final Operator op;
        private final String propertyName;
        private final long propertyNameHash;
        private final String value;

        public StringOpSegement(String str, String str2, Operator operator) {
            this.propertyName = str;
            this.propertyNameHash = TypeUtils.fnv1a_64(str);
            this.value = str2;
            this.op = operator;
        }

        @Override // com.alibaba.fastjson.JSONPath.Filter
        public boolean apply(JSONPath jSONPath, Object obj, Object obj2, Object obj3) {
            Object propertyValue = jSONPath.getPropertyValue(obj3, this.propertyName, this.propertyNameHash);
            if (this.op == Operator.EQ) {
                return this.value.equals(propertyValue);
            }
            if (this.op == Operator.NE) {
                return !this.value.equals(propertyValue);
            }
            if (propertyValue == null) {
                return false;
            }
            int iCompareTo = this.value.compareTo(propertyValue.toString());
            if (this.op == Operator.GE) {
                return iCompareTo <= 0;
            }
            if (this.op == Operator.GT) {
                return iCompareTo < 0;
            }
            if (this.op == Operator.LE) {
                return iCompareTo >= 0;
            }
            return this.op == Operator.LT && iCompareTo > 0;
        }
    }

    static class RegMatchSegement implements Filter {
        private final Operator op;
        private final Pattern pattern;
        private final String propertyName;
        private final long propertyNameHash;

        public RegMatchSegement(String str, Pattern pattern, Operator operator) {
            this.propertyName = str;
            this.propertyNameHash = TypeUtils.fnv1a_64(str);
            this.pattern = pattern;
            this.op = operator;
        }

        @Override // com.alibaba.fastjson.JSONPath.Filter
        public boolean apply(JSONPath jSONPath, Object obj, Object obj2, Object obj3) {
            Object propertyValue = jSONPath.getPropertyValue(obj3, this.propertyName, this.propertyNameHash);
            if (propertyValue == null) {
                return false;
            }
            return this.pattern.matcher(propertyValue.toString()).matches();
        }
    }

    public static class FilterSegment implements Segment {
        private final Filter filter;

        public FilterSegment(Filter filter) {
            this.filter = filter;
        }

        @Override // com.alibaba.fastjson.JSONPath.Segment
        public Object eval(JSONPath jSONPath, Object obj, Object obj2) {
            if (obj2 == null) {
                return null;
            }
            JSONArray jSONArray = new JSONArray();
            if (obj2 instanceof Iterable) {
                for (Object obj3 : (Iterable) obj2) {
                    if (this.filter.apply(jSONPath, obj, obj2, obj3)) {
                        jSONArray.add(obj3);
                    }
                }
                return jSONArray;
            }
            if (this.filter.apply(jSONPath, obj, obj2, obj2)) {
                return obj2;
            }
            return null;
        }

        @Override // com.alibaba.fastjson.JSONPath.Segment
        public void extract(JSONPath jSONPath, DefaultJSONParser defaultJSONParser, Context context) {
            throw new UnsupportedOperationException();
        }
    }

    static class FilterGroup implements Filter {
        private boolean and;
        private List<Filter> fitlers = new ArrayList(2);

        public FilterGroup(Filter filter, Filter filter2, boolean z) {
            this.fitlers.add(filter);
            this.fitlers.add(filter2);
            this.and = z;
        }

        @Override // com.alibaba.fastjson.JSONPath.Filter
        public boolean apply(JSONPath jSONPath, Object obj, Object obj2, Object obj3) {
            if (this.and) {
                Iterator<Filter> it = this.fitlers.iterator();
                while (it.hasNext()) {
                    if (!it.next().apply(jSONPath, obj, obj2, obj3)) {
                        return false;
                    }
                }
                return true;
            }
            Iterator<Filter> it2 = this.fitlers.iterator();
            while (it2.hasNext()) {
                if (it2.next().apply(jSONPath, obj, obj2, obj3)) {
                    return true;
                }
            }
            return false;
        }
    }

    protected Object getArrayItem(Object obj, int i) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof List) {
            List list = (List) obj;
            if (i >= 0) {
                if (i < list.size()) {
                    return list.get(i);
                }
                return null;
            }
            if (Math.abs(i) <= list.size()) {
                return list.get(list.size() + i);
            }
            return null;
        }
        if (obj.getClass().isArray()) {
            int length = Array.getLength(obj);
            if (i >= 0) {
                if (i < length) {
                    return Array.get(obj, i);
                }
                return null;
            }
            if (Math.abs(i) <= length) {
                return Array.get(obj, length + i);
            }
            return null;
        }
        if (obj instanceof Map) {
            Map map = (Map) obj;
            Object obj2 = map.get(Integer.valueOf(i));
            return obj2 == null ? map.get(Integer.toString(i)) : obj2;
        }
        if (obj instanceof Collection) {
            int i2 = 0;
            for (Object obj3 : (Collection) obj) {
                if (i2 == i) {
                    return obj3;
                }
                i2++;
            }
            return null;
        }
        throw new UnsupportedOperationException();
    }

    public boolean setArrayItem(JSONPath jSONPath, Object obj, int i, Object obj2) {
        if (obj instanceof List) {
            List list = (List) obj;
            if (i >= 0) {
                list.set(i, obj2);
            } else {
                list.set(list.size() + i, obj2);
            }
            return true;
        }
        Class<?> cls = obj.getClass();
        if (cls.isArray()) {
            int length = Array.getLength(obj);
            if (i >= 0) {
                if (i < length) {
                    Array.set(obj, i, obj2);
                }
            } else if (Math.abs(i) <= length) {
                Array.set(obj, length + i, obj2);
            }
            return true;
        }
        throw new JSONPathException("unsupported set operation." + cls);
    }

    public boolean removeArrayItem(JSONPath jSONPath, Object obj, int i) {
        if (obj instanceof List) {
            List list = (List) obj;
            if (i >= 0) {
                if (i >= list.size()) {
                    return false;
                }
                list.remove(i);
                return true;
            }
            int size = list.size() + i;
            if (size < 0) {
                return false;
            }
            list.remove(size);
            return true;
        }
        throw new JSONPathException("unsupported set operation." + obj.getClass());
    }

    protected Collection<Object> getPropertyValues(Object obj) {
        JavaBeanSerializer javaBeanSerializer = getJavaBeanSerializer(obj.getClass());
        if (javaBeanSerializer != null) {
            try {
                return javaBeanSerializer.getFieldValues(obj);
            } catch (Exception e) {
                throw new JSONPathException("jsonpath error, path " + this.path, e);
            }
        }
        if (obj instanceof Map) {
            return ((Map) obj).values();
        }
        throw new UnsupportedOperationException();
    }

    protected void deepGetPropertyValues(Object obj, List<Object> list) {
        Collection fieldValues;
        Class<?> cls = obj.getClass();
        JavaBeanSerializer javaBeanSerializer = getJavaBeanSerializer(cls);
        if (javaBeanSerializer != null) {
            try {
                fieldValues = javaBeanSerializer.getFieldValues(obj);
            } catch (Exception e) {
                throw new JSONPathException("jsonpath error, path " + this.path, e);
            }
        } else if (obj instanceof Map) {
            fieldValues = ((Map) obj).values();
        } else {
            fieldValues = obj instanceof Collection ? (Collection) obj : null;
        }
        if (fieldValues != null) {
            for (Object obj2 : fieldValues) {
                if (obj2 == null || ParserConfig.isPrimitive2(obj2.getClass())) {
                    list.add(obj2);
                } else {
                    deepGetPropertyValues(obj2, list);
                }
            }
            return;
        }
        throw new UnsupportedOperationException(cls.getName());
    }

    static boolean eq(Object obj, Object obj2) {
        if (obj == obj2) {
            return true;
        }
        if (obj == null || obj2 == null) {
            return false;
        }
        if (obj.getClass() == obj2.getClass()) {
            return obj.equals(obj2);
        }
        if (obj instanceof Number) {
            if (obj2 instanceof Number) {
                return eqNotNull((Number) obj, (Number) obj2);
            }
            return false;
        }
        return obj.equals(obj2);
    }

    static boolean eqNotNull(Number number, Number number2) {
        Class<?> cls = number.getClass();
        boolean zIsInt = isInt(cls);
        Class<?> cls2 = number2.getClass();
        boolean zIsInt2 = isInt(cls2);
        if (number instanceof BigDecimal) {
            BigDecimal bigDecimal = (BigDecimal) number;
            if (zIsInt2) {
                return bigDecimal.equals(BigDecimal.valueOf(TypeUtils.longExtractValue(number2)));
            }
        }
        if (zIsInt) {
            if (zIsInt2) {
                return number.longValue() == number2.longValue();
            }
            if (number2 instanceof BigInteger) {
                return BigInteger.valueOf(number.longValue()).equals((BigInteger) number);
            }
        }
        if (zIsInt2 && (number instanceof BigInteger)) {
            return ((BigInteger) number).equals(BigInteger.valueOf(TypeUtils.longExtractValue(number2)));
        }
        boolean zIsDouble = isDouble(cls);
        boolean zIsDouble2 = isDouble(cls2);
        return ((zIsDouble && zIsDouble2) || ((zIsDouble && zIsInt2) || (zIsDouble2 && zIsInt))) && number.doubleValue() == number2.doubleValue();
    }

    protected static boolean isDouble(Class<?> cls) {
        return cls == Float.class || cls == Double.class;
    }

    protected static boolean isInt(Class<?> cls) {
        return cls == Byte.class || cls == Short.class || cls == Integer.class || cls == Long.class;
    }

    protected Object getPropertyValue(Object obj, String str, long j) {
        JSONArray jSONArray = null;
        if (obj == null) {
            return null;
        }
        if (obj instanceof Map) {
            Map map = (Map) obj;
            Object obj2 = map.get(str);
            if (obj2 == null) {
                return (SIZE == j || LENGTH == j) ? Integer.valueOf(map.size()) : obj2;
            }
            return obj2;
        }
        JavaBeanSerializer javaBeanSerializer = getJavaBeanSerializer(obj.getClass());
        if (javaBeanSerializer != null) {
            try {
                return javaBeanSerializer.getFieldValue(obj, str, j, false);
            } catch (Exception e) {
                throw new JSONPathException("jsonpath error, path " + this.path + ", segement " + str, e);
            }
        }
        int i = 0;
        if (obj instanceof List) {
            List list = (List) obj;
            if (SIZE == j || LENGTH == j) {
                return Integer.valueOf(list.size());
            }
            while (i < list.size()) {
                Object obj3 = list.get(i);
                if (obj3 == list) {
                    if (jSONArray == null) {
                        jSONArray = new JSONArray(list.size());
                    }
                    jSONArray.add(obj3);
                } else {
                    Object propertyValue = getPropertyValue(obj3, str, j);
                    if (propertyValue instanceof Collection) {
                        Collection collection = (Collection) propertyValue;
                        if (jSONArray == null) {
                            jSONArray = new JSONArray(list.size());
                        }
                        jSONArray.addAll(collection);
                    } else if (propertyValue != null) {
                        if (jSONArray == null) {
                            jSONArray = new JSONArray(list.size());
                        }
                        jSONArray.add(propertyValue);
                    }
                }
                i++;
            }
            return jSONArray == null ? Collections.emptyList() : jSONArray;
        }
        if (obj instanceof Object[]) {
            Object[] objArr = (Object[]) obj;
            if (SIZE == j || LENGTH == j) {
                return Integer.valueOf(objArr.length);
            }
            JSONArray jSONArray2 = new JSONArray(objArr.length);
            while (i < objArr.length) {
                Object[] objArr2 = objArr[i];
                if (objArr2 == objArr) {
                    jSONArray2.add(objArr2);
                } else {
                    Object propertyValue2 = getPropertyValue(objArr2, str, j);
                    if (propertyValue2 instanceof Collection) {
                        jSONArray2.addAll((Collection) propertyValue2);
                    } else if (propertyValue2 != null) {
                        jSONArray2.add(propertyValue2);
                    }
                }
                i++;
            }
            return jSONArray2;
        }
        if (obj instanceof Enum) {
            Enum r1 = (Enum) obj;
            if (-4270347329889690746L == j) {
                return r1.name();
            }
            if (-1014497654951707614L == j) {
                return Integer.valueOf(r1.ordinal());
            }
        }
        if (obj instanceof Calendar) {
            Calendar calendar = (Calendar) obj;
            if (8963398325558730460L == j) {
                return Integer.valueOf(calendar.get(1));
            }
            if (-811277319855450459L == j) {
                return Integer.valueOf(calendar.get(2));
            }
            if (-3851359326990528739L == j) {
                return Integer.valueOf(calendar.get(5));
            }
            if (4647432019745535567L == j) {
                return Integer.valueOf(calendar.get(11));
            }
            if (6607618197526598121L == j) {
                return Integer.valueOf(calendar.get(12));
            }
            if (-6586085717218287427L == j) {
                return Integer.valueOf(calendar.get(13));
            }
        }
        return null;
    }

    protected void deepScan(Object obj, String str, List<Object> list) {
        if (obj == null) {
            return;
        }
        if (obj instanceof Map) {
            for (Map.Entry entry : ((Map) obj).entrySet()) {
                Object value = entry.getValue();
                if (str.equals(entry.getKey())) {
                    if (value instanceof Collection) {
                        list.addAll((Collection) value);
                    } else {
                        list.add(value);
                    }
                } else if (value != null && !ParserConfig.isPrimitive2(value.getClass())) {
                    deepScan(value, str, list);
                }
            }
            return;
        }
        if (obj instanceof Collection) {
            for (Object obj2 : (Collection) obj) {
                if (!ParserConfig.isPrimitive2(obj2.getClass())) {
                    deepScan(obj2, str, list);
                }
            }
            return;
        }
        JavaBeanSerializer javaBeanSerializer = getJavaBeanSerializer(obj.getClass());
        if (javaBeanSerializer != null) {
            try {
                FieldSerializer fieldSerializer = javaBeanSerializer.getFieldSerializer(str);
                if (fieldSerializer != null) {
                    try {
                        try {
                            list.add(fieldSerializer.getPropertyValueDirect(obj));
                            return;
                        } catch (InvocationTargetException e) {
                            throw new JSONException("getFieldValue error." + str, e);
                        }
                    } catch (IllegalAccessException e2) {
                        throw new JSONException("getFieldValue error." + str, e2);
                    }
                }
                Iterator<Object> it = javaBeanSerializer.getFieldValues(obj).iterator();
                while (it.hasNext()) {
                    deepScan(it.next(), str, list);
                }
                return;
            } catch (Exception e3) {
                throw new JSONPathException("jsonpath error, path " + this.path + ", segement " + str, e3);
            }
        }
        if (obj instanceof List) {
            List list2 = (List) obj;
            for (int i = 0; i < list2.size(); i++) {
                deepScan(list2.get(i), str, list);
            }
        }
    }

    protected void deepSet(Object obj, String str, long j, Object obj2) {
        if (obj == null) {
            return;
        }
        if (obj instanceof Map) {
            Map map = (Map) obj;
            if (map.containsKey(str)) {
                map.get(str);
                map.put(str, obj2);
                return;
            } else {
                Iterator it = map.values().iterator();
                while (it.hasNext()) {
                    deepSet(it.next(), str, j, obj2);
                }
                return;
            }
        }
        Class<?> cls = obj.getClass();
        JavaBeanDeserializer javaBeanDeserializer = getJavaBeanDeserializer(cls);
        if (javaBeanDeserializer != null) {
            try {
                FieldDeserializer fieldDeserializer = javaBeanDeserializer.getFieldDeserializer(str);
                if (fieldDeserializer != null) {
                    fieldDeserializer.setValue(obj, obj2);
                    return;
                }
                Iterator<Object> it2 = getJavaBeanSerializer(cls).getObjectFieldValues(obj).iterator();
                while (it2.hasNext()) {
                    deepSet(it2.next(), str, j, obj2);
                }
                return;
            } catch (Exception e) {
                throw new JSONPathException("jsonpath error, path " + this.path + ", segement " + str, e);
            }
        }
        if (obj instanceof List) {
            List list = (List) obj;
            for (int i = 0; i < list.size(); i++) {
                deepSet(list.get(i), str, j, obj2);
            }
        }
    }

    protected boolean setPropertyValue(Object obj, String str, long j, Object obj2) {
        if (obj instanceof Map) {
            ((Map) obj).put(str, obj2);
            return true;
        }
        if (obj instanceof List) {
            for (Object obj3 : (List) obj) {
                if (obj3 != null) {
                    setPropertyValue(obj3, str, j, obj2);
                }
            }
            return true;
        }
        ObjectDeserializer deserializer = this.parserConfig.getDeserializer(obj.getClass());
        JavaBeanDeserializer javaBeanDeserializer = deserializer instanceof JavaBeanDeserializer ? (JavaBeanDeserializer) deserializer : null;
        if (javaBeanDeserializer != null) {
            FieldDeserializer fieldDeserializer = javaBeanDeserializer.getFieldDeserializer(j);
            if (fieldDeserializer == null) {
                return false;
            }
            fieldDeserializer.setValue(obj, obj2);
            return true;
        }
        throw new UnsupportedOperationException();
    }

    protected boolean removePropertyValue(Object obj, String str) {
        if (obj instanceof Map) {
            return ((Map) obj).remove(str) != null;
        }
        ObjectDeserializer deserializer = this.parserConfig.getDeserializer(obj.getClass());
        JavaBeanDeserializer javaBeanDeserializer = deserializer instanceof JavaBeanDeserializer ? (JavaBeanDeserializer) deserializer : null;
        if (javaBeanDeserializer != null) {
            FieldDeserializer fieldDeserializer = javaBeanDeserializer.getFieldDeserializer(str);
            if (fieldDeserializer == null) {
                return false;
            }
            fieldDeserializer.setValue(obj, (String) null);
            return true;
        }
        throw new UnsupportedOperationException();
    }

    protected JavaBeanSerializer getJavaBeanSerializer(Class<?> cls) {
        ObjectSerializer objectWriter = this.serializeConfig.getObjectWriter(cls);
        if (objectWriter instanceof JavaBeanSerializer) {
            return (JavaBeanSerializer) objectWriter;
        }
        return null;
    }

    protected JavaBeanDeserializer getJavaBeanDeserializer(Class<?> cls) {
        ObjectDeserializer deserializer = this.parserConfig.getDeserializer(cls);
        if (deserializer instanceof JavaBeanDeserializer) {
            return (JavaBeanDeserializer) deserializer;
        }
        return null;
    }

    int evalSize(Object obj) {
        if (obj == null) {
            return -1;
        }
        if (obj instanceof Collection) {
            return ((Collection) obj).size();
        }
        if (obj instanceof Object[]) {
            return ((Object[]) obj).length;
        }
        if (obj.getClass().isArray()) {
            return Array.getLength(obj);
        }
        if (obj instanceof Map) {
            int i = 0;
            Iterator it = ((Map) obj).values().iterator();
            while (it.hasNext()) {
                if (it.next() != null) {
                    i++;
                }
            }
            return i;
        }
        JavaBeanSerializer javaBeanSerializer = getJavaBeanSerializer(obj.getClass());
        if (javaBeanSerializer == null) {
            return -1;
        }
        try {
            return javaBeanSerializer.getSize(obj);
        } catch (Exception e) {
            throw new JSONPathException("evalSize error : " + this.path, e);
        }
    }

    Set<?> evalKeySet(Object obj) {
        JavaBeanSerializer javaBeanSerializer;
        if (obj == null) {
            return null;
        }
        if (obj instanceof Map) {
            return ((Map) obj).keySet();
        }
        if ((obj instanceof Collection) || (obj instanceof Object[]) || obj.getClass().isArray() || (javaBeanSerializer = getJavaBeanSerializer(obj.getClass())) == null) {
            return null;
        }
        try {
            return javaBeanSerializer.getFieldNames(obj);
        } catch (Exception e) {
            throw new JSONPathException("evalKeySet error : " + this.path, e);
        }
    }

    @Override // com.alibaba.fastjson.JSONAware
    public String toJSONString() {
        return JSON.toJSONString(this.path);
    }
}
