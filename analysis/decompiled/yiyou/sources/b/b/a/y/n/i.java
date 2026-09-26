package b.b.a.y.n;

import b.b.a.t;
import b.b.a.v;
import b.b.a.w;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: ReflectiveTypeAdapterFactory.java */
/* JADX INFO: loaded from: classes.dex */
public final class i implements w {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final b.b.a.y.c f1653a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final b.b.a.e f1654b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final b.b.a.y.d f1655c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final d f1656d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final b.b.a.y.o.b f1657e = b.b.a.y.o.b.a();

    /* JADX INFO: compiled from: ReflectiveTypeAdapterFactory.java */
    static abstract class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final String f1663a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final boolean f1664b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final boolean f1665c;

        protected c(String str, boolean z, boolean z2) {
            this.f1663a = str;
            this.f1664b = z;
            this.f1665c = z2;
        }

        abstract void a(JsonReader jsonReader, Object obj);

        abstract void a(JsonWriter jsonWriter, Object obj);

        abstract boolean a(Object obj);
    }

    public i(b.b.a.y.c cVar, b.b.a.e eVar, b.b.a.y.d dVar, d dVar2) {
        this.f1653a = cVar;
        this.f1654b = eVar;
        this.f1655c = dVar;
        this.f1656d = dVar2;
    }

    public boolean a(Field field, boolean z) {
        return a(field, z, this.f1655c);
    }

    static boolean a(Field field, boolean z, b.b.a.y.d dVar) {
        return (dVar.a(field.getType(), z) || dVar.a(field, z)) ? false : true;
    }

    private List<String> a(Field field) {
        b.b.a.x.c cVar = (b.b.a.x.c) field.getAnnotation(b.b.a.x.c.class);
        if (cVar == null) {
            return Collections.singletonList(this.f1654b.a(field));
        }
        String strValue = cVar.value();
        String[] strArrAlternate = cVar.alternate();
        if (strArrAlternate.length == 0) {
            return Collections.singletonList(strValue);
        }
        ArrayList arrayList = new ArrayList(strArrAlternate.length + 1);
        arrayList.add(strValue);
        for (String str : strArrAlternate) {
            arrayList.add(str);
        }
        return arrayList;
    }

    /* JADX INFO: compiled from: ReflectiveTypeAdapterFactory.java */
    class a extends c {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ Field f1658d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ boolean f1659e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        final /* synthetic */ v f1660f;
        final /* synthetic */ b.b.a.f g;
        final /* synthetic */ b.b.a.z.a h;
        final /* synthetic */ boolean i;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(i iVar, String str, boolean z, boolean z2, Field field, boolean z3, v vVar, b.b.a.f fVar, b.b.a.z.a aVar, boolean z4) {
            super(str, z, z2);
            this.f1658d = field;
            this.f1659e = z3;
            this.f1660f = vVar;
            this.g = fVar;
            this.h = aVar;
            this.i = z4;
        }

        @Override // b.b.a.y.n.i.c
        void a(JsonWriter jsonWriter, Object obj) throws IllegalAccessException {
            (this.f1659e ? this.f1660f : new m(this.g, this.f1660f, this.h.b())).a(jsonWriter, this.f1658d.get(obj));
        }

        @Override // b.b.a.y.n.i.c
        void a(JsonReader jsonReader, Object obj) throws IllegalAccessException {
            Object objA2 = this.f1660f.a2(jsonReader);
            if (objA2 == null && this.i) {
                return;
            }
            this.f1658d.set(obj, objA2);
        }

        @Override // b.b.a.y.n.i.c
        public boolean a(Object obj) {
            return this.f1664b && this.f1658d.get(obj) != obj;
        }
    }

    /* JADX INFO: compiled from: ReflectiveTypeAdapterFactory.java */
    public static final class b<T> extends v<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final b.b.a.y.i<T> f1661a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final Map<String, c> f1662b;

        b(b.b.a.y.i<T> iVar, Map<String, c> map) {
            this.f1661a = iVar;
            this.f1662b = map;
        }

        @Override // b.b.a.v
        /* JADX INFO: renamed from: a */
        public T a2(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            T tA = this.f1661a.a();
            try {
                jsonReader.beginObject();
                while (jsonReader.hasNext()) {
                    c cVar = this.f1662b.get(jsonReader.nextName());
                    if (cVar == null || !cVar.f1665c) {
                        jsonReader.skipValue();
                    } else {
                        cVar.a(jsonReader, tA);
                    }
                }
                jsonReader.endObject();
                return tA;
            } catch (IllegalAccessException e2) {
                throw new AssertionError(e2);
            } catch (IllegalStateException e3) {
                throw new t(e3);
            }
        }

        @Override // b.b.a.v
        public void a(JsonWriter jsonWriter, T t) throws IOException {
            if (t == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            try {
                for (c cVar : this.f1662b.values()) {
                    if (cVar.a(t)) {
                        jsonWriter.name(cVar.f1663a);
                        cVar.a(jsonWriter, t);
                    }
                }
                jsonWriter.endObject();
            } catch (IllegalAccessException e2) {
                throw new AssertionError(e2);
            }
        }
    }

    @Override // b.b.a.w
    public <T> v<T> a(b.b.a.f fVar, b.b.a.z.a<T> aVar) {
        Class<? super T> clsA = aVar.a();
        if (Object.class.isAssignableFrom(clsA)) {
            return new b(this.f1653a.a(aVar), a(fVar, (b.b.a.z.a<?>) aVar, (Class<?>) clsA));
        }
        return null;
    }

    private c a(b.b.a.f fVar, Field field, String str, b.b.a.z.a<?> aVar, boolean z, boolean z2) {
        boolean zA = b.b.a.y.k.a((Type) aVar.a());
        b.b.a.x.b bVar = (b.b.a.x.b) field.getAnnotation(b.b.a.x.b.class);
        v<?> vVarA = bVar != null ? this.f1656d.a(this.f1653a, fVar, aVar, bVar) : null;
        boolean z3 = vVarA != null;
        if (vVarA == null) {
            vVarA = fVar.a((b.b.a.z.a) aVar);
        }
        return new a(this, str, z, z2, field, z3, vVarA, fVar, aVar, zA);
    }

    private Map<String, c> a(b.b.a.f fVar, b.b.a.z.a<?> aVar, Class<?> cls) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        if (cls.isInterface()) {
            return linkedHashMap;
        }
        Type typeB = aVar.b();
        b.b.a.z.a<?> aVarA = aVar;
        Class<?> clsA = cls;
        while (clsA != Object.class) {
            Field[] declaredFields = clsA.getDeclaredFields();
            int length = declaredFields.length;
            boolean z = false;
            int i = 0;
            while (i < length) {
                Field field = declaredFields[i];
                boolean zA = a(field, true);
                boolean zA2 = a(field, z);
                if (zA || zA2) {
                    this.f1657e.a(field);
                    Type typeA = b.b.a.y.b.a(aVarA.b(), clsA, field.getGenericType());
                    List<String> listA = a(field);
                    int size = listA.size();
                    c cVar = null;
                    int i2 = 0;
                    while (i2 < size) {
                        String str = listA.get(i2);
                        boolean z2 = i2 != 0 ? false : zA;
                        c cVar2 = cVar;
                        int i3 = i2;
                        int i4 = size;
                        List<String> list = listA;
                        Field field2 = field;
                        cVar = cVar2 == null ? (c) linkedHashMap.put(str, a(fVar, field, str, b.b.a.z.a.a(typeA), z2, zA2)) : cVar2;
                        i2 = i3 + 1;
                        zA = z2;
                        listA = list;
                        size = i4;
                        field = field2;
                    }
                    c cVar3 = cVar;
                    if (cVar3 != null) {
                        throw new IllegalArgumentException(typeB + " declares multiple JSON fields named " + cVar3.f1663a);
                    }
                }
                i++;
                z = false;
            }
            aVarA = b.b.a.z.a.a(b.b.a.y.b.a(aVarA.b(), clsA, clsA.getGenericSuperclass()));
            clsA = aVarA.a();
        }
        return linkedHashMap;
    }
}
