package b.b.a;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.google.gson.stream.MalformedJsonException;
import java.io.EOFException;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicLongArray;

/* JADX INFO: compiled from: Gson.java */
/* JADX INFO: loaded from: classes.dex */
public final class f {
    private static final b.b.a.z.a<?> k = b.b.a.z.a.a(Object.class);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final ThreadLocal<Map<b.b.a.z.a<?>, C0037f<?>>> f1542a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Map<b.b.a.z.a<?>, v<?>> f1543b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final b.b.a.y.c f1544c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final b.b.a.y.n.d f1545d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    final List<w> f1546e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    final boolean f1547f;
    final boolean g;
    final boolean h;
    final boolean i;
    final boolean j;

    public f() {
        this(b.b.a.y.d.g, b.b.a.d.f1536a, Collections.emptyMap(), false, false, false, true, false, false, false, u.f1562a, null, 2, 2, Collections.emptyList(), Collections.emptyList(), Collections.emptyList());
    }

    private v<Number> a(boolean z) {
        return z ? b.b.a.y.n.n.v : new a(this);
    }

    private v<Number> b(boolean z) {
        return z ? b.b.a.y.n.n.u : new b(this);
    }

    public String toString() {
        return "{serializeNulls:" + this.f1547f + ",factories:" + this.f1546e + ",instanceCreators:" + this.f1544c + "}";
    }

    /* JADX INFO: compiled from: Gson.java */
    class a extends v<Number> {
        a(f fVar) {
        }

        @Override // b.b.a.v
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Number a2(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            return Double.valueOf(jsonReader.nextDouble());
        }

        @Override // b.b.a.v
        public void a(JsonWriter jsonWriter, Number number) throws IOException {
            if (number == null) {
                jsonWriter.nullValue();
            } else {
                f.a(number.doubleValue());
                jsonWriter.value(number);
            }
        }
    }

    /* JADX INFO: compiled from: Gson.java */
    class b extends v<Number> {
        b(f fVar) {
        }

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
            if (number == null) {
                jsonWriter.nullValue();
            } else {
                f.a(number.floatValue());
                jsonWriter.value(number);
            }
        }
    }

    /* JADX INFO: compiled from: Gson.java */
    static class c extends v<Number> {
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
            return Long.valueOf(jsonReader.nextLong());
        }

        @Override // b.b.a.v
        public void a(JsonWriter jsonWriter, Number number) throws IOException {
            if (number == null) {
                jsonWriter.nullValue();
            } else {
                jsonWriter.value(number.toString());
            }
        }
    }

    /* JADX INFO: compiled from: Gson.java */
    static class d extends v<AtomicLong> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ v f1548a;

        d(v vVar) {
            this.f1548a = vVar;
        }

        @Override // b.b.a.v
        public void a(JsonWriter jsonWriter, AtomicLong atomicLong) {
            this.f1548a.a(jsonWriter, Long.valueOf(atomicLong.get()));
        }

        @Override // b.b.a.v
        /* JADX INFO: renamed from: a, reason: avoid collision after fix types in other method */
        public AtomicLong a2(JsonReader jsonReader) {
            return new AtomicLong(((Number) this.f1548a.a2(jsonReader)).longValue());
        }
    }

    /* JADX INFO: compiled from: Gson.java */
    static class e extends v<AtomicLongArray> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ v f1549a;

        e(v vVar) {
            this.f1549a = vVar;
        }

        @Override // b.b.a.v
        public void a(JsonWriter jsonWriter, AtomicLongArray atomicLongArray) throws IOException {
            jsonWriter.beginArray();
            int length = atomicLongArray.length();
            for (int i = 0; i < length; i++) {
                this.f1549a.a(jsonWriter, Long.valueOf(atomicLongArray.get(i)));
            }
            jsonWriter.endArray();
        }

        @Override // b.b.a.v
        /* JADX INFO: renamed from: a, reason: avoid collision after fix types in other method */
        public AtomicLongArray a2(JsonReader jsonReader) throws IOException {
            ArrayList arrayList = new ArrayList();
            jsonReader.beginArray();
            while (jsonReader.hasNext()) {
                arrayList.add(Long.valueOf(((Number) this.f1549a.a2(jsonReader)).longValue()));
            }
            jsonReader.endArray();
            int size = arrayList.size();
            AtomicLongArray atomicLongArray = new AtomicLongArray(size);
            for (int i = 0; i < size; i++) {
                atomicLongArray.set(i, ((Long) arrayList.get(i)).longValue());
            }
            return atomicLongArray;
        }
    }

    /* JADX INFO: renamed from: b.b.a.f$f, reason: collision with other inner class name */
    /* JADX INFO: compiled from: Gson.java */
    static class C0037f<T> extends v<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private v<T> f1550a;

        C0037f() {
        }

        public void a(v<T> vVar) {
            if (this.f1550a != null) {
                throw new AssertionError();
            }
            this.f1550a = vVar;
        }

        @Override // b.b.a.v
        /* JADX INFO: renamed from: a */
        public T a2(JsonReader jsonReader) {
            v<T> vVar = this.f1550a;
            if (vVar != null) {
                return vVar.a2(jsonReader);
            }
            throw new IllegalStateException();
        }

        @Override // b.b.a.v
        public void a(JsonWriter jsonWriter, T t) {
            v<T> vVar = this.f1550a;
            if (vVar != null) {
                vVar.a(jsonWriter, t);
                return;
            }
            throw new IllegalStateException();
        }
    }

    static void a(double d2) {
        if (Double.isNaN(d2) || Double.isInfinite(d2)) {
            throw new IllegalArgumentException(d2 + " is not a valid double value as per JSON specification. To override this behavior, use GsonBuilder.serializeSpecialFloatingPointValues() method.");
        }
    }

    private static v<AtomicLongArray> b(v<Number> vVar) {
        return new e(vVar).a();
    }

    private static v<Number> a(u uVar) {
        if (uVar == u.f1562a) {
            return b.b.a.y.n.n.t;
        }
        return new c();
    }

    f(b.b.a.y.d dVar, b.b.a.e eVar, Map<Type, h<?>> map, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, u uVar, String str, int i, int i2, List<w> list, List<w> list2, List<w> list3) {
        this.f1542a = new ThreadLocal<>();
        this.f1543b = new ConcurrentHashMap();
        this.f1544c = new b.b.a.y.c(map);
        this.f1547f = z;
        this.g = z3;
        this.h = z4;
        this.i = z5;
        this.j = z6;
        ArrayList arrayList = new ArrayList();
        arrayList.add(b.b.a.y.n.n.Y);
        arrayList.add(b.b.a.y.n.h.f1650b);
        arrayList.add(dVar);
        arrayList.addAll(list3);
        arrayList.add(b.b.a.y.n.n.D);
        arrayList.add(b.b.a.y.n.n.m);
        arrayList.add(b.b.a.y.n.n.g);
        arrayList.add(b.b.a.y.n.n.i);
        arrayList.add(b.b.a.y.n.n.k);
        v<Number> vVarA = a(uVar);
        arrayList.add(b.b.a.y.n.n.a(Long.TYPE, Long.class, vVarA));
        arrayList.add(b.b.a.y.n.n.a(Double.TYPE, Double.class, a(z7)));
        arrayList.add(b.b.a.y.n.n.a(Float.TYPE, Float.class, b(z7)));
        arrayList.add(b.b.a.y.n.n.x);
        arrayList.add(b.b.a.y.n.n.o);
        arrayList.add(b.b.a.y.n.n.q);
        arrayList.add(b.b.a.y.n.n.a(AtomicLong.class, a(vVarA)));
        arrayList.add(b.b.a.y.n.n.a(AtomicLongArray.class, b(vVarA)));
        arrayList.add(b.b.a.y.n.n.s);
        arrayList.add(b.b.a.y.n.n.z);
        arrayList.add(b.b.a.y.n.n.F);
        arrayList.add(b.b.a.y.n.n.H);
        arrayList.add(b.b.a.y.n.n.a(BigDecimal.class, b.b.a.y.n.n.B));
        arrayList.add(b.b.a.y.n.n.a(BigInteger.class, b.b.a.y.n.n.C));
        arrayList.add(b.b.a.y.n.n.J);
        arrayList.add(b.b.a.y.n.n.L);
        arrayList.add(b.b.a.y.n.n.P);
        arrayList.add(b.b.a.y.n.n.R);
        arrayList.add(b.b.a.y.n.n.W);
        arrayList.add(b.b.a.y.n.n.N);
        arrayList.add(b.b.a.y.n.n.f1682d);
        arrayList.add(b.b.a.y.n.c.f1631b);
        arrayList.add(b.b.a.y.n.n.U);
        arrayList.add(b.b.a.y.n.k.f1668b);
        arrayList.add(b.b.a.y.n.j.f1666b);
        arrayList.add(b.b.a.y.n.n.S);
        arrayList.add(b.b.a.y.n.a.f1625c);
        arrayList.add(b.b.a.y.n.n.f1680b);
        arrayList.add(new b.b.a.y.n.b(this.f1544c));
        arrayList.add(new b.b.a.y.n.g(this.f1544c, z2));
        this.f1545d = new b.b.a.y.n.d(this.f1544c);
        arrayList.add(this.f1545d);
        arrayList.add(b.b.a.y.n.n.Z);
        arrayList.add(new b.b.a.y.n.i(this.f1544c, eVar, dVar, this.f1545d));
        this.f1546e = Collections.unmodifiableList(arrayList);
    }

    private static v<AtomicLong> a(v<Number> vVar) {
        return new d(vVar).a();
    }

    public <T> v<T> a(b.b.a.z.a<T> aVar) {
        v<T> vVar = (v) this.f1543b.get(aVar == null ? k : aVar);
        if (vVar != null) {
            return vVar;
        }
        Map<b.b.a.z.a<?>, C0037f<?>> map = this.f1542a.get();
        boolean z = false;
        if (map == null) {
            map = new HashMap<>();
            this.f1542a.set(map);
            z = true;
        }
        C0037f<?> c0037f = map.get(aVar);
        if (c0037f != null) {
            return c0037f;
        }
        try {
            C0037f<?> c0037f2 = new C0037f<>();
            map.put(aVar, c0037f2);
            Iterator<w> it = this.f1546e.iterator();
            while (it.hasNext()) {
                v<T> vVarA = it.next().a(this, aVar);
                if (vVarA != null) {
                    c0037f2.a((v<?>) vVarA);
                    this.f1543b.put(aVar, vVarA);
                    map.remove(aVar);
                    if (z) {
                        this.f1542a.remove();
                    }
                    return vVarA;
                }
            }
            throw new IllegalArgumentException("GSON (2.8.5) cannot handle " + aVar);
        } catch (Throwable th) {
            map.remove(aVar);
            if (z) {
                this.f1542a.remove();
            }
            throw th;
        }
    }

    public <T> v<T> a(w wVar, b.b.a.z.a<T> aVar) {
        if (!this.f1546e.contains(wVar)) {
            wVar = this.f1545d;
        }
        boolean z = false;
        for (w wVar2 : this.f1546e) {
            if (z) {
                v<T> vVarA = wVar2.a(this, aVar);
                if (vVarA != null) {
                    return vVarA;
                }
            } else if (wVar2 == wVar) {
                z = true;
            }
        }
        throw new IllegalArgumentException("GSON cannot serialize " + aVar);
    }

    public <T> v<T> a(Class<T> cls) {
        return a((b.b.a.z.a) b.b.a.z.a.a((Class) cls));
    }

    public String a(Object obj) {
        if (obj == null) {
            return a((l) n.f1558a);
        }
        return a(obj, obj.getClass());
    }

    public String a(Object obj, Type type) {
        StringWriter stringWriter = new StringWriter();
        a(obj, type, stringWriter);
        return stringWriter.toString();
    }

    public void a(Object obj, Type type, Appendable appendable) {
        try {
            a(obj, type, a(b.b.a.y.l.a(appendable)));
        } catch (IOException e2) {
            throw new m(e2);
        }
    }

    public void a(Object obj, Type type, JsonWriter jsonWriter) {
        v vVarA = a((b.b.a.z.a) b.b.a.z.a.a(type));
        boolean zIsLenient = jsonWriter.isLenient();
        jsonWriter.setLenient(true);
        boolean zIsHtmlSafe = jsonWriter.isHtmlSafe();
        jsonWriter.setHtmlSafe(this.h);
        boolean serializeNulls = jsonWriter.getSerializeNulls();
        jsonWriter.setSerializeNulls(this.f1547f);
        try {
            try {
                vVarA.a(jsonWriter, obj);
                jsonWriter.setLenient(zIsLenient);
                jsonWriter.setHtmlSafe(zIsHtmlSafe);
                jsonWriter.setSerializeNulls(serializeNulls);
            } catch (IOException e2) {
                throw new m(e2);
            } catch (AssertionError e3) {
                throw new AssertionError("AssertionError (GSON 2.8.5): " + e3.getMessage(), e3);
            }
        } catch (Throwable th) {
            jsonWriter.setLenient(zIsLenient);
            jsonWriter.setHtmlSafe(zIsHtmlSafe);
            jsonWriter.setSerializeNulls(serializeNulls);
            throw th;
        }
    }

    public String a(l lVar) {
        StringWriter stringWriter = new StringWriter();
        a(lVar, stringWriter);
        return stringWriter.toString();
    }

    public void a(l lVar, Appendable appendable) {
        try {
            a(lVar, a(b.b.a.y.l.a(appendable)));
        } catch (IOException e2) {
            throw new m(e2);
        }
    }

    public JsonWriter a(Writer writer) throws IOException {
        if (this.g) {
            writer.write(")]}'\n");
        }
        JsonWriter jsonWriter = new JsonWriter(writer);
        if (this.i) {
            jsonWriter.setIndent("  ");
        }
        jsonWriter.setSerializeNulls(this.f1547f);
        return jsonWriter;
    }

    public JsonReader a(Reader reader) {
        JsonReader jsonReader = new JsonReader(reader);
        jsonReader.setLenient(this.j);
        return jsonReader;
    }

    public void a(l lVar, JsonWriter jsonWriter) {
        boolean zIsLenient = jsonWriter.isLenient();
        jsonWriter.setLenient(true);
        boolean zIsHtmlSafe = jsonWriter.isHtmlSafe();
        jsonWriter.setHtmlSafe(this.h);
        boolean serializeNulls = jsonWriter.getSerializeNulls();
        jsonWriter.setSerializeNulls(this.f1547f);
        try {
            try {
                b.b.a.y.l.a(lVar, jsonWriter);
                jsonWriter.setLenient(zIsLenient);
                jsonWriter.setHtmlSafe(zIsHtmlSafe);
                jsonWriter.setSerializeNulls(serializeNulls);
            } catch (IOException e2) {
                throw new m(e2);
            } catch (AssertionError e3) {
                throw new AssertionError("AssertionError (GSON 2.8.5): " + e3.getMessage(), e3);
            }
        } catch (Throwable th) {
            jsonWriter.setLenient(zIsLenient);
            jsonWriter.setHtmlSafe(zIsHtmlSafe);
            jsonWriter.setSerializeNulls(serializeNulls);
            throw th;
        }
    }

    public <T> T a(String str, Class<T> cls) {
        return (T) b.b.a.y.k.a((Class) cls).cast(a(str, (Type) cls));
    }

    public <T> T a(String str, Type type) {
        if (str == null) {
            return null;
        }
        return (T) a((Reader) new StringReader(str), type);
    }

    public <T> T a(Reader reader, Class<T> cls) {
        JsonReader jsonReaderA = a(reader);
        Object objA = a(jsonReaderA, (Type) cls);
        a(objA, jsonReaderA);
        return (T) b.b.a.y.k.a((Class) cls).cast(objA);
    }

    public <T> T a(Reader reader, Type type) {
        JsonReader jsonReaderA = a(reader);
        T t = (T) a(jsonReaderA, type);
        a(t, jsonReaderA);
        return t;
    }

    private static void a(Object obj, JsonReader jsonReader) {
        if (obj != null) {
            try {
                if (jsonReader.peek() == JsonToken.END_DOCUMENT) {
                } else {
                    throw new m("JSON document was not fully consumed.");
                }
            } catch (MalformedJsonException e2) {
                throw new t(e2);
            } catch (IOException e3) {
                throw new m(e3);
            }
        }
    }

    public <T> T a(JsonReader jsonReader, Type type) {
        boolean zIsLenient = jsonReader.isLenient();
        boolean z = true;
        jsonReader.setLenient(true);
        try {
            try {
                try {
                    jsonReader.peek();
                    z = false;
                    T tA2 = a((b.b.a.z.a) b.b.a.z.a.a(type)).a2(jsonReader);
                    jsonReader.setLenient(zIsLenient);
                    return tA2;
                } catch (IOException e2) {
                    throw new t(e2);
                } catch (IllegalStateException e3) {
                    throw new t(e3);
                }
            } catch (EOFException e4) {
                if (z) {
                    jsonReader.setLenient(zIsLenient);
                    return null;
                }
                throw new t(e4);
            } catch (AssertionError e5) {
                throw new AssertionError("AssertionError (GSON 2.8.5): " + e5.getMessage(), e5);
            }
        } catch (Throwable th) {
            jsonReader.setLenient(zIsLenient);
            throw th;
        }
    }
}
