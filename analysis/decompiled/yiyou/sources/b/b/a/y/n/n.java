package b.b.a.y.n;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Calendar;
import java.util.Currency;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;

/* JADX INFO: compiled from: TypeAdapters.java */
/* JADX INFO: loaded from: classes.dex */
public final class n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final b.b.a.v<Class> f1679a = new k().a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final b.b.a.w f1680b = a(Class.class, f1679a);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final b.b.a.v<BitSet> f1681c = new v().a();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final b.b.a.w f1682d = a(BitSet.class, f1681c);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final b.b.a.v<Boolean> f1683e = new c0();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final b.b.a.v<Boolean> f1684f = new d0();
    public static final b.b.a.w g = a(Boolean.TYPE, Boolean.class, f1683e);
    public static final b.b.a.v<Number> h = new e0();
    public static final b.b.a.w i = a(Byte.TYPE, Byte.class, h);
    public static final b.b.a.v<Number> j = new f0();
    public static final b.b.a.w k = a(Short.TYPE, Short.class, j);
    public static final b.b.a.v<Number> l = new g0();
    public static final b.b.a.w m = a(Integer.TYPE, Integer.class, l);
    public static final b.b.a.v<AtomicInteger> n = new h0().a();
    public static final b.b.a.w o = a(AtomicInteger.class, n);
    public static final b.b.a.v<AtomicBoolean> p = new i0().a();
    public static final b.b.a.w q = a(AtomicBoolean.class, p);
    public static final b.b.a.v<AtomicIntegerArray> r = new a().a();
    public static final b.b.a.w s = a(AtomicIntegerArray.class, r);
    public static final b.b.a.v<Number> t = new b();
    public static final b.b.a.v<Number> u = new c();
    public static final b.b.a.v<Number> v = new d();
    public static final b.b.a.v<Number> w = new e();
    public static final b.b.a.w x = a(Number.class, w);
    public static final b.b.a.v<Character> y = new f();
    public static final b.b.a.w z = a(Character.TYPE, Character.class, y);
    public static final b.b.a.v<String> A = new g();
    public static final b.b.a.v<BigDecimal> B = new h();
    public static final b.b.a.v<BigInteger> C = new i();
    public static final b.b.a.w D = a(String.class, A);
    public static final b.b.a.v<StringBuilder> E = new j();
    public static final b.b.a.w F = a(StringBuilder.class, E);
    public static final b.b.a.v<StringBuffer> G = new l();
    public static final b.b.a.w H = a(StringBuffer.class, G);
    public static final b.b.a.v<URL> I = new m();
    public static final b.b.a.w J = a(URL.class, I);
    public static final b.b.a.v<URI> K = new C0042n();
    public static final b.b.a.w L = a(URI.class, K);
    public static final b.b.a.v<InetAddress> M = new o();
    public static final b.b.a.w N = b(InetAddress.class, M);
    public static final b.b.a.v<UUID> O = new p();
    public static final b.b.a.w P = a(UUID.class, O);
    public static final b.b.a.v<Currency> Q = new q().a();
    public static final b.b.a.w R = a(Currency.class, Q);
    public static final b.b.a.w S = new r();
    public static final b.b.a.v<Calendar> T = new s();
    public static final b.b.a.w U = b(Calendar.class, GregorianCalendar.class, T);
    public static final b.b.a.v<Locale> V = new t();
    public static final b.b.a.w W = a(Locale.class, V);
    public static final b.b.a.v<b.b.a.l> X = new u();
    public static final b.b.a.w Y = b(b.b.a.l.class, X);
    public static final b.b.a.w Z = new w();

    /* JADX INFO: compiled from: TypeAdapters.java */
    static class a0 implements b.b.a.w {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Class f1685a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ b.b.a.v f1686b;

        /* JADX INFO: Add missing generic type declarations: [T1] */
        /* JADX INFO: compiled from: TypeAdapters.java */
        class a<T1> extends b.b.a.v<T1> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ Class f1687a;

            a(Class cls) {
                this.f1687a = cls;
            }

            @Override // b.b.a.v
            public void a(JsonWriter jsonWriter, T1 t1) {
                a0.this.f1686b.a(jsonWriter, t1);
            }

            @Override // b.b.a.v
            /* JADX INFO: renamed from: a */
            public T1 a2(JsonReader jsonReader) {
                T1 t1 = (T1) a0.this.f1686b.a2(jsonReader);
                if (t1 == null || this.f1687a.isInstance(t1)) {
                    return t1;
                }
                throw new b.b.a.t("Expected a " + this.f1687a.getName() + " but was " + t1.getClass().getName());
            }
        }

        a0(Class cls, b.b.a.v vVar) {
            this.f1685a = cls;
            this.f1686b = vVar;
        }

        @Override // b.b.a.w
        public <T2> b.b.a.v<T2> a(b.b.a.f fVar, b.b.a.z.a<T2> aVar) {
            Class<? super T2> clsA = aVar.a();
            if (this.f1685a.isAssignableFrom(clsA)) {
                return new a(clsA);
            }
            return null;
        }

        public String toString() {
            return "Factory[typeHierarchy=" + this.f1685a.getName() + ",adapter=" + this.f1686b + "]";
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    static /* synthetic */ class b0 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f1689a = new int[JsonToken.values().length];

        static {
            try {
                f1689a[JsonToken.NUMBER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1689a[JsonToken.BOOLEAN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1689a[JsonToken.STRING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f1689a[JsonToken.NULL.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f1689a[JsonToken.BEGIN_ARRAY.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f1689a[JsonToken.BEGIN_OBJECT.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f1689a[JsonToken.END_DOCUMENT.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f1689a[JsonToken.NAME.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f1689a[JsonToken.END_OBJECT.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f1689a[JsonToken.END_ARRAY.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    static class k extends b.b.a.v<Class> {
        k() {
        }

        @Override // b.b.a.v
        /* JADX INFO: renamed from: a */
        public /* bridge */ /* synthetic */ Class a2(JsonReader jsonReader) {
            a2(jsonReader);
            throw null;
        }

        @Override // b.b.a.v
        public /* bridge */ /* synthetic */ void a(JsonWriter jsonWriter, Class cls) {
            a2(jsonWriter, cls);
            throw null;
        }

        /* JADX INFO: renamed from: a, reason: avoid collision after fix types in other method */
        public void a2(JsonWriter jsonWriter, Class cls) {
            throw new UnsupportedOperationException("Attempted to serialize java.lang.Class: " + cls.getName() + ". Forgot to register a type adapter?");
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // b.b.a.v
        /* JADX INFO: renamed from: a */
        public Class a2(JsonReader jsonReader) {
            throw new UnsupportedOperationException("Attempted to deserialize a java.lang.Class. Forgot to register a type adapter?");
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    static class r implements b.b.a.w {
        r() {
        }

        @Override // b.b.a.w
        public <T> b.b.a.v<T> a(b.b.a.f fVar, b.b.a.z.a<T> aVar) {
            if (aVar.a() != Timestamp.class) {
                return null;
            }
            return new a(this, fVar.a((Class) Date.class));
        }

        /* JADX INFO: compiled from: TypeAdapters.java */
        class a extends b.b.a.v<Timestamp> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ b.b.a.v f1692a;

            a(r rVar, b.b.a.v vVar) {
                this.f1692a = vVar;
            }

            @Override // b.b.a.v
            /* JADX INFO: renamed from: a, reason: avoid collision after fix types in other method */
            public Timestamp a2(JsonReader jsonReader) {
                Date date = (Date) this.f1692a.a2(jsonReader);
                if (date != null) {
                    return new Timestamp(date.getTime());
                }
                return null;
            }

            @Override // b.b.a.v
            public void a(JsonWriter jsonWriter, Timestamp timestamp) {
                this.f1692a.a(jsonWriter, timestamp);
            }
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    static class w implements b.b.a.w {
        w() {
        }

        @Override // b.b.a.w
        public <T> b.b.a.v<T> a(b.b.a.f fVar, b.b.a.z.a<T> aVar) {
            Class<? super T> clsA = aVar.a();
            if (!Enum.class.isAssignableFrom(clsA) || clsA == Enum.class) {
                return null;
            }
            if (!clsA.isEnum()) {
                clsA = clsA.getSuperclass();
            }
            return new j0(clsA);
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    static class x implements b.b.a.w {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Class f1693a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ b.b.a.v f1694b;

        x(Class cls, b.b.a.v vVar) {
            this.f1693a = cls;
            this.f1694b = vVar;
        }

        @Override // b.b.a.w
        public <T> b.b.a.v<T> a(b.b.a.f fVar, b.b.a.z.a<T> aVar) {
            if (aVar.a() == this.f1693a) {
                return this.f1694b;
            }
            return null;
        }

        public String toString() {
            return "Factory[type=" + this.f1693a.getName() + ",adapter=" + this.f1694b + "]";
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    static class y implements b.b.a.w {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Class f1695a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ Class f1696b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ b.b.a.v f1697c;

        y(Class cls, Class cls2, b.b.a.v vVar) {
            this.f1695a = cls;
            this.f1696b = cls2;
            this.f1697c = vVar;
        }

        @Override // b.b.a.w
        public <T> b.b.a.v<T> a(b.b.a.f fVar, b.b.a.z.a<T> aVar) {
            Class<? super T> clsA = aVar.a();
            if (clsA == this.f1695a || clsA == this.f1696b) {
                return this.f1697c;
            }
            return null;
        }

        public String toString() {
            return "Factory[type=" + this.f1696b.getName() + "+" + this.f1695a.getName() + ",adapter=" + this.f1697c + "]";
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    static class z implements b.b.a.w {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Class f1698a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ Class f1699b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ b.b.a.v f1700c;

        z(Class cls, Class cls2, b.b.a.v vVar) {
            this.f1698a = cls;
            this.f1699b = cls2;
            this.f1700c = vVar;
        }

        @Override // b.b.a.w
        public <T> b.b.a.v<T> a(b.b.a.f fVar, b.b.a.z.a<T> aVar) {
            Class<? super T> clsA = aVar.a();
            if (clsA == this.f1698a || clsA == this.f1699b) {
                return this.f1700c;
            }
            return null;
        }

        public String toString() {
            return "Factory[type=" + this.f1698a.getName() + "+" + this.f1699b.getName() + ",adapter=" + this.f1700c + "]";
        }
    }

    public static <TT> b.b.a.w a(Class<TT> cls, b.b.a.v<TT> vVar) {
        return new x(cls, vVar);
    }

    public static <TT> b.b.a.w b(Class<TT> cls, Class<? extends TT> cls2, b.b.a.v<? super TT> vVar) {
        return new z(cls, cls2, vVar);
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    static class a extends b.b.a.v<AtomicIntegerArray> {
        a() {
        }

        @Override // b.b.a.v
        /* JADX INFO: renamed from: a, reason: avoid collision after fix types in other method */
        public AtomicIntegerArray a2(JsonReader jsonReader) throws IOException {
            ArrayList arrayList = new ArrayList();
            jsonReader.beginArray();
            while (jsonReader.hasNext()) {
                try {
                    arrayList.add(Integer.valueOf(jsonReader.nextInt()));
                } catch (NumberFormatException e2) {
                    throw new b.b.a.t(e2);
                }
            }
            jsonReader.endArray();
            int size = arrayList.size();
            AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(size);
            for (int i = 0; i < size; i++) {
                atomicIntegerArray.set(i, ((Integer) arrayList.get(i)).intValue());
            }
            return atomicIntegerArray;
        }

        @Override // b.b.a.v
        public void a(JsonWriter jsonWriter, AtomicIntegerArray atomicIntegerArray) throws IOException {
            jsonWriter.beginArray();
            int length = atomicIntegerArray.length();
            for (int i = 0; i < length; i++) {
                jsonWriter.value(atomicIntegerArray.get(i));
            }
            jsonWriter.endArray();
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    static class b extends b.b.a.v<Number> {
        b() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // b.b.a.v
        /* JADX INFO: renamed from: a */
        public Number a2(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            try {
                return Long.valueOf(jsonReader.nextLong());
            } catch (NumberFormatException e2) {
                throw new b.b.a.t(e2);
            }
        }

        @Override // b.b.a.v
        public void a(JsonWriter jsonWriter, Number number) throws IOException {
            jsonWriter.value(number);
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    static class c extends b.b.a.v<Number> {
        c() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // b.b.a.v
        /* JADX INFO: renamed from: a */
        public Number a2(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            return Float.valueOf((float) jsonReader.nextDouble());
        }

        @Override // b.b.a.v
        public void a(JsonWriter jsonWriter, Number number) throws IOException {
            jsonWriter.value(number);
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    static class c0 extends b.b.a.v<Boolean> {
        c0() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // b.b.a.v
        /* JADX INFO: renamed from: a */
        public Boolean a2(JsonReader jsonReader) throws IOException {
            JsonToken jsonTokenPeek = jsonReader.peek();
            if (jsonTokenPeek == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            if (jsonTokenPeek == JsonToken.STRING) {
                return Boolean.valueOf(Boolean.parseBoolean(jsonReader.nextString()));
            }
            return Boolean.valueOf(jsonReader.nextBoolean());
        }

        @Override // b.b.a.v
        public void a(JsonWriter jsonWriter, Boolean bool) throws IOException {
            jsonWriter.value(bool);
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    static class d extends b.b.a.v<Number> {
        d() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // b.b.a.v
        /* JADX INFO: renamed from: a */
        public Number a2(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            return Double.valueOf(jsonReader.nextDouble());
        }

        @Override // b.b.a.v
        public void a(JsonWriter jsonWriter, Number number) throws IOException {
            jsonWriter.value(number);
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    static class d0 extends b.b.a.v<Boolean> {
        d0() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // b.b.a.v
        /* JADX INFO: renamed from: a */
        public Boolean a2(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            return Boolean.valueOf(jsonReader.nextString());
        }

        @Override // b.b.a.v
        public void a(JsonWriter jsonWriter, Boolean bool) throws IOException {
            jsonWriter.value(bool == null ? "null" : bool.toString());
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    static class e extends b.b.a.v<Number> {
        e() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // b.b.a.v
        /* JADX INFO: renamed from: a */
        public Number a2(JsonReader jsonReader) throws IOException {
            JsonToken jsonTokenPeek = jsonReader.peek();
            int i = b0.f1689a[jsonTokenPeek.ordinal()];
            if (i == 1 || i == 3) {
                return new b.b.a.y.g(jsonReader.nextString());
            }
            if (i == 4) {
                jsonReader.nextNull();
                return null;
            }
            throw new b.b.a.t("Expecting number, got: " + jsonTokenPeek);
        }

        @Override // b.b.a.v
        public void a(JsonWriter jsonWriter, Number number) throws IOException {
            jsonWriter.value(number);
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    static class e0 extends b.b.a.v<Number> {
        e0() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // b.b.a.v
        /* JADX INFO: renamed from: a */
        public Number a2(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            try {
                return Byte.valueOf((byte) jsonReader.nextInt());
            } catch (NumberFormatException e2) {
                throw new b.b.a.t(e2);
            }
        }

        @Override // b.b.a.v
        public void a(JsonWriter jsonWriter, Number number) throws IOException {
            jsonWriter.value(number);
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    static class f extends b.b.a.v<Character> {
        f() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // b.b.a.v
        /* JADX INFO: renamed from: a */
        public Character a2(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            String strNextString = jsonReader.nextString();
            if (strNextString.length() == 1) {
                return Character.valueOf(strNextString.charAt(0));
            }
            throw new b.b.a.t("Expecting character, got: " + strNextString);
        }

        @Override // b.b.a.v
        public void a(JsonWriter jsonWriter, Character ch) throws IOException {
            jsonWriter.value(ch == null ? null : String.valueOf(ch));
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    static class f0 extends b.b.a.v<Number> {
        f0() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // b.b.a.v
        /* JADX INFO: renamed from: a */
        public Number a2(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            try {
                return Short.valueOf((short) jsonReader.nextInt());
            } catch (NumberFormatException e2) {
                throw new b.b.a.t(e2);
            }
        }

        @Override // b.b.a.v
        public void a(JsonWriter jsonWriter, Number number) throws IOException {
            jsonWriter.value(number);
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    static class g extends b.b.a.v<String> {
        g() {
        }

        @Override // b.b.a.v
        /* JADX INFO: renamed from: a, reason: avoid collision after fix types in other method */
        public String a2(JsonReader jsonReader) throws IOException {
            JsonToken jsonTokenPeek = jsonReader.peek();
            if (jsonTokenPeek == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            if (jsonTokenPeek == JsonToken.BOOLEAN) {
                return Boolean.toString(jsonReader.nextBoolean());
            }
            return jsonReader.nextString();
        }

        @Override // b.b.a.v
        public void a(JsonWriter jsonWriter, String str) throws IOException {
            jsonWriter.value(str);
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    static class g0 extends b.b.a.v<Number> {
        g0() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // b.b.a.v
        /* JADX INFO: renamed from: a */
        public Number a2(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            try {
                return Integer.valueOf(jsonReader.nextInt());
            } catch (NumberFormatException e2) {
                throw new b.b.a.t(e2);
            }
        }

        @Override // b.b.a.v
        public void a(JsonWriter jsonWriter, Number number) throws IOException {
            jsonWriter.value(number);
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    static class h extends b.b.a.v<BigDecimal> {
        h() {
        }

        @Override // b.b.a.v
        /* JADX INFO: renamed from: a, reason: avoid collision after fix types in other method */
        public BigDecimal a2(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            try {
                return new BigDecimal(jsonReader.nextString());
            } catch (NumberFormatException e2) {
                throw new b.b.a.t(e2);
            }
        }

        @Override // b.b.a.v
        public void a(JsonWriter jsonWriter, BigDecimal bigDecimal) throws IOException {
            jsonWriter.value(bigDecimal);
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    static class h0 extends b.b.a.v<AtomicInteger> {
        h0() {
        }

        @Override // b.b.a.v
        /* JADX INFO: renamed from: a, reason: avoid collision after fix types in other method */
        public AtomicInteger a2(JsonReader jsonReader) {
            try {
                return new AtomicInteger(jsonReader.nextInt());
            } catch (NumberFormatException e2) {
                throw new b.b.a.t(e2);
            }
        }

        @Override // b.b.a.v
        public void a(JsonWriter jsonWriter, AtomicInteger atomicInteger) throws IOException {
            jsonWriter.value(atomicInteger.get());
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    static class i extends b.b.a.v<BigInteger> {
        i() {
        }

        @Override // b.b.a.v
        /* JADX INFO: renamed from: a, reason: avoid collision after fix types in other method */
        public BigInteger a2(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            try {
                return new BigInteger(jsonReader.nextString());
            } catch (NumberFormatException e2) {
                throw new b.b.a.t(e2);
            }
        }

        @Override // b.b.a.v
        public void a(JsonWriter jsonWriter, BigInteger bigInteger) throws IOException {
            jsonWriter.value(bigInteger);
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    static class i0 extends b.b.a.v<AtomicBoolean> {
        i0() {
        }

        @Override // b.b.a.v
        /* JADX INFO: renamed from: a, reason: avoid collision after fix types in other method */
        public AtomicBoolean a2(JsonReader jsonReader) {
            return new AtomicBoolean(jsonReader.nextBoolean());
        }

        @Override // b.b.a.v
        public void a(JsonWriter jsonWriter, AtomicBoolean atomicBoolean) throws IOException {
            jsonWriter.value(atomicBoolean.get());
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    static class j extends b.b.a.v<StringBuilder> {
        j() {
        }

        @Override // b.b.a.v
        /* JADX INFO: renamed from: a, reason: avoid collision after fix types in other method */
        public StringBuilder a2(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            return new StringBuilder(jsonReader.nextString());
        }

        @Override // b.b.a.v
        public void a(JsonWriter jsonWriter, StringBuilder sb) throws IOException {
            jsonWriter.value(sb == null ? null : sb.toString());
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    private static final class j0<T extends Enum<T>> extends b.b.a.v<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Map<String, T> f1690a = new HashMap();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final Map<T, String> f1691b = new HashMap();

        public j0(Class<T> cls) {
            try {
                for (T t : cls.getEnumConstants()) {
                    String strName = t.name();
                    b.b.a.x.c cVar = (b.b.a.x.c) cls.getField(strName).getAnnotation(b.b.a.x.c.class);
                    if (cVar != null) {
                        strName = cVar.value();
                        for (String str : cVar.alternate()) {
                            this.f1690a.put(str, t);
                        }
                    }
                    this.f1690a.put(strName, t);
                    this.f1691b.put(t, strName);
                }
            } catch (NoSuchFieldException e2) {
                throw new AssertionError(e2);
            }
        }

        @Override // b.b.a.v
        /* JADX INFO: renamed from: a */
        public T a2(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            return this.f1690a.get(jsonReader.nextString());
        }

        @Override // b.b.a.v
        public void a(JsonWriter jsonWriter, T t) throws IOException {
            jsonWriter.value(t == null ? null : this.f1691b.get(t));
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    static class l extends b.b.a.v<StringBuffer> {
        l() {
        }

        @Override // b.b.a.v
        /* JADX INFO: renamed from: a, reason: avoid collision after fix types in other method */
        public StringBuffer a2(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            return new StringBuffer(jsonReader.nextString());
        }

        @Override // b.b.a.v
        public void a(JsonWriter jsonWriter, StringBuffer stringBuffer) throws IOException {
            jsonWriter.value(stringBuffer == null ? null : stringBuffer.toString());
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    static class m extends b.b.a.v<URL> {
        m() {
        }

        @Override // b.b.a.v
        /* JADX INFO: renamed from: a, reason: avoid collision after fix types in other method */
        public URL a2(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            String strNextString = jsonReader.nextString();
            if ("null".equals(strNextString)) {
                return null;
            }
            return new URL(strNextString);
        }

        @Override // b.b.a.v
        public void a(JsonWriter jsonWriter, URL url) throws IOException {
            jsonWriter.value(url == null ? null : url.toExternalForm());
        }
    }

    /* JADX INFO: renamed from: b.b.a.y.n.n$n, reason: collision with other inner class name */
    /* JADX INFO: compiled from: TypeAdapters.java */
    static class C0042n extends b.b.a.v<URI> {
        C0042n() {
        }

        @Override // b.b.a.v
        /* JADX INFO: renamed from: a, reason: avoid collision after fix types in other method */
        public URI a2(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            try {
                String strNextString = jsonReader.nextString();
                if ("null".equals(strNextString)) {
                    return null;
                }
                return new URI(strNextString);
            } catch (URISyntaxException e2) {
                throw new b.b.a.m(e2);
            }
        }

        @Override // b.b.a.v
        public void a(JsonWriter jsonWriter, URI uri) throws IOException {
            jsonWriter.value(uri == null ? null : uri.toASCIIString());
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    static class o extends b.b.a.v<InetAddress> {
        o() {
        }

        @Override // b.b.a.v
        /* JADX INFO: renamed from: a, reason: avoid collision after fix types in other method */
        public InetAddress a2(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            return InetAddress.getByName(jsonReader.nextString());
        }

        @Override // b.b.a.v
        public void a(JsonWriter jsonWriter, InetAddress inetAddress) throws IOException {
            jsonWriter.value(inetAddress == null ? null : inetAddress.getHostAddress());
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    static class p extends b.b.a.v<UUID> {
        p() {
        }

        @Override // b.b.a.v
        /* JADX INFO: renamed from: a, reason: avoid collision after fix types in other method */
        public UUID a2(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            return UUID.fromString(jsonReader.nextString());
        }

        @Override // b.b.a.v
        public void a(JsonWriter jsonWriter, UUID uuid) throws IOException {
            jsonWriter.value(uuid == null ? null : uuid.toString());
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    static class q extends b.b.a.v<Currency> {
        q() {
        }

        @Override // b.b.a.v
        /* JADX INFO: renamed from: a, reason: avoid collision after fix types in other method */
        public Currency a2(JsonReader jsonReader) {
            return Currency.getInstance(jsonReader.nextString());
        }

        @Override // b.b.a.v
        public void a(JsonWriter jsonWriter, Currency currency) throws IOException {
            jsonWriter.value(currency.getCurrencyCode());
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    static class s extends b.b.a.v<Calendar> {
        s() {
        }

        @Override // b.b.a.v
        /* JADX INFO: renamed from: a, reason: avoid collision after fix types in other method */
        public Calendar a2(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            int i = 0;
            int i2 = 0;
            int i3 = 0;
            int i4 = 0;
            int i5 = 0;
            int i6 = 0;
            while (jsonReader.peek() != JsonToken.END_OBJECT) {
                String strNextName = jsonReader.nextName();
                int iNextInt = jsonReader.nextInt();
                if ("year".equals(strNextName)) {
                    i = iNextInt;
                } else if ("month".equals(strNextName)) {
                    i2 = iNextInt;
                } else if ("dayOfMonth".equals(strNextName)) {
                    i3 = iNextInt;
                } else if ("hourOfDay".equals(strNextName)) {
                    i4 = iNextInt;
                } else if ("minute".equals(strNextName)) {
                    i5 = iNextInt;
                } else if ("second".equals(strNextName)) {
                    i6 = iNextInt;
                }
            }
            jsonReader.endObject();
            return new GregorianCalendar(i, i2, i3, i4, i5, i6);
        }

        @Override // b.b.a.v
        public void a(JsonWriter jsonWriter, Calendar calendar) throws IOException {
            if (calendar == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name("year");
            jsonWriter.value(calendar.get(1));
            jsonWriter.name("month");
            jsonWriter.value(calendar.get(2));
            jsonWriter.name("dayOfMonth");
            jsonWriter.value(calendar.get(5));
            jsonWriter.name("hourOfDay");
            jsonWriter.value(calendar.get(11));
            jsonWriter.name("minute");
            jsonWriter.value(calendar.get(12));
            jsonWriter.name("second");
            jsonWriter.value(calendar.get(13));
            jsonWriter.endObject();
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    static class t extends b.b.a.v<Locale> {
        t() {
        }

        @Override // b.b.a.v
        /* JADX INFO: renamed from: a, reason: avoid collision after fix types in other method */
        public Locale a2(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            StringTokenizer stringTokenizer = new StringTokenizer(jsonReader.nextString(), "_");
            String strNextToken = stringTokenizer.hasMoreElements() ? stringTokenizer.nextToken() : null;
            String strNextToken2 = stringTokenizer.hasMoreElements() ? stringTokenizer.nextToken() : null;
            String strNextToken3 = stringTokenizer.hasMoreElements() ? stringTokenizer.nextToken() : null;
            if (strNextToken2 == null && strNextToken3 == null) {
                return new Locale(strNextToken);
            }
            if (strNextToken3 == null) {
                return new Locale(strNextToken, strNextToken2);
            }
            return new Locale(strNextToken, strNextToken2, strNextToken3);
        }

        @Override // b.b.a.v
        public void a(JsonWriter jsonWriter, Locale locale) throws IOException {
            jsonWriter.value(locale == null ? null : locale.toString());
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    static class u extends b.b.a.v<b.b.a.l> {
        u() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // b.b.a.v
        /* JADX INFO: renamed from: a */
        public b.b.a.l a2(JsonReader jsonReader) throws IOException {
            switch (b0.f1689a[jsonReader.peek().ordinal()]) {
                case 1:
                    return new b.b.a.q(new b.b.a.y.g(jsonReader.nextString()));
                case 2:
                    return new b.b.a.q(Boolean.valueOf(jsonReader.nextBoolean()));
                case 3:
                    return new b.b.a.q(jsonReader.nextString());
                case 4:
                    jsonReader.nextNull();
                    return b.b.a.n.f1558a;
                case 5:
                    b.b.a.i iVar = new b.b.a.i();
                    jsonReader.beginArray();
                    while (jsonReader.hasNext()) {
                        iVar.a(a2(jsonReader));
                    }
                    jsonReader.endArray();
                    return iVar;
                case 6:
                    b.b.a.o oVar = new b.b.a.o();
                    jsonReader.beginObject();
                    while (jsonReader.hasNext()) {
                        oVar.a(jsonReader.nextName(), a2(jsonReader));
                    }
                    jsonReader.endObject();
                    return oVar;
                default:
                    throw new IllegalArgumentException();
            }
        }

        @Override // b.b.a.v
        public void a(JsonWriter jsonWriter, b.b.a.l lVar) throws IOException {
            if (lVar != null && !lVar.e()) {
                if (lVar.g()) {
                    b.b.a.q qVarC = lVar.c();
                    if (qVarC.p()) {
                        jsonWriter.value(qVarC.m());
                        return;
                    } else if (qVarC.o()) {
                        jsonWriter.value(qVarC.h());
                        return;
                    } else {
                        jsonWriter.value(qVarC.n());
                        return;
                    }
                }
                if (lVar.d()) {
                    jsonWriter.beginArray();
                    Iterator<b.b.a.l> it = lVar.a().iterator();
                    while (it.hasNext()) {
                        a(jsonWriter, it.next());
                    }
                    jsonWriter.endArray();
                    return;
                }
                if (lVar.f()) {
                    jsonWriter.beginObject();
                    for (Map.Entry<String, b.b.a.l> entry : lVar.b().h()) {
                        jsonWriter.name(entry.getKey());
                        a(jsonWriter, entry.getValue());
                    }
                    jsonWriter.endObject();
                    return;
                }
                throw new IllegalArgumentException("Couldn't write " + lVar.getClass());
            }
            jsonWriter.nullValue();
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    static class v extends b.b.a.v<BitSet> {
        v() {
        }

        /* JADX WARN: Code duplicated, block: B:15:0x002e  */
        @Override // b.b.a.v
        /* JADX INFO: renamed from: a, reason: avoid collision after fix types in other method */
        public BitSet a2(JsonReader jsonReader) throws IOException {
            BitSet bitSet = new BitSet();
            jsonReader.beginArray();
            JsonToken jsonTokenPeek = jsonReader.peek();
            int i = 0;
            while (jsonTokenPeek != JsonToken.END_ARRAY) {
                int i2 = b0.f1689a[jsonTokenPeek.ordinal()];
                boolean zNextBoolean = true;
                if (i2 != 1) {
                    if (i2 == 2) {
                        zNextBoolean = jsonReader.nextBoolean();
                    } else if (i2 == 3) {
                        String strNextString = jsonReader.nextString();
                        try {
                            if (Integer.parseInt(strNextString) == 0) {
                                zNextBoolean = false;
                            }
                        } catch (NumberFormatException unused) {
                            throw new b.b.a.t("Error: Expecting: bitset number value (1, 0), Found: " + strNextString);
                        }
                    } else {
                        throw new b.b.a.t("Invalid bitset value type: " + jsonTokenPeek);
                    }
                } else if (jsonReader.nextInt() == 0) {
                    zNextBoolean = false;
                }
                if (zNextBoolean) {
                    bitSet.set(i);
                }
                i++;
                jsonTokenPeek = jsonReader.peek();
            }
            jsonReader.endArray();
            return bitSet;
        }

        @Override // b.b.a.v
        public void a(JsonWriter jsonWriter, BitSet bitSet) throws IOException {
            jsonWriter.beginArray();
            int length = bitSet.length();
            for (int i = 0; i < length; i++) {
                jsonWriter.value(bitSet.get(i) ? 1L : 0L);
            }
            jsonWriter.endArray();
        }
    }

    public static <TT> b.b.a.w a(Class<TT> cls, Class<TT> cls2, b.b.a.v<? super TT> vVar) {
        return new y(cls, cls2, vVar);
    }

    public static <T1> b.b.a.w b(Class<T1> cls, b.b.a.v<T1> vVar) {
        return new a0(cls, vVar);
    }
}
