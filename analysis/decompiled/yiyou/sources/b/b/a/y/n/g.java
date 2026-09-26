package b.b.a.y.n;

import b.b.a.q;
import b.b.a.t;
import b.b.a.v;
import b.b.a.w;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;

/* JADX INFO: compiled from: MapTypeAdapterFactory.java */
/* JADX INFO: loaded from: classes.dex */
public final class g implements w {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final b.b.a.y.c f1644a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final boolean f1645b;

    public g(b.b.a.y.c cVar, boolean z) {
        this.f1644a = cVar;
        this.f1645b = z;
    }

    @Override // b.b.a.w
    public <T> v<T> a(b.b.a.f fVar, b.b.a.z.a<T> aVar) {
        Type typeB = aVar.b();
        if (!Map.class.isAssignableFrom(aVar.a())) {
            return null;
        }
        Type[] typeArrB = b.b.a.y.b.b(typeB, b.b.a.y.b.e(typeB));
        return new a(fVar, typeArrB[0], a(fVar, typeArrB[0]), typeArrB[1], fVar.a((b.b.a.z.a) b.b.a.z.a.a(typeArrB[1])), this.f1644a.a(aVar));
    }

    /* JADX INFO: compiled from: MapTypeAdapterFactory.java */
    private final class a<K, V> extends v<Map<K, V>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final v<K> f1646a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final v<V> f1647b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final b.b.a.y.i<? extends Map<K, V>> f1648c;

        public a(b.b.a.f fVar, Type type, v<K> vVar, Type type2, v<V> vVar2, b.b.a.y.i<? extends Map<K, V>> iVar) {
            this.f1646a = new m(fVar, vVar, type);
            this.f1647b = new m(fVar, vVar2, type2);
            this.f1648c = iVar;
        }

        @Override // b.b.a.v
        /* JADX INFO: renamed from: a */
        public Map<K, V> a2(JsonReader jsonReader) throws IOException {
            JsonToken jsonTokenPeek = jsonReader.peek();
            if (jsonTokenPeek == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            Map<K, V> mapA = this.f1648c.a();
            if (jsonTokenPeek == JsonToken.BEGIN_ARRAY) {
                jsonReader.beginArray();
                while (jsonReader.hasNext()) {
                    jsonReader.beginArray();
                    K kA2 = this.f1646a.a2(jsonReader);
                    if (mapA.put(kA2, this.f1647b.a2(jsonReader)) == null) {
                        jsonReader.endArray();
                    } else {
                        throw new t("duplicate key: " + kA2);
                    }
                }
                jsonReader.endArray();
            } else {
                jsonReader.beginObject();
                while (jsonReader.hasNext()) {
                    b.b.a.y.f.INSTANCE.promoteNameToValue(jsonReader);
                    K kA3 = this.f1646a.a2(jsonReader);
                    if (mapA.put(kA3, this.f1647b.a2(jsonReader)) != null) {
                        throw new t("duplicate key: " + kA3);
                    }
                }
                jsonReader.endObject();
            }
            return mapA;
        }

        @Override // b.b.a.v
        public void a(JsonWriter jsonWriter, Map<K, V> map) throws IOException {
            if (map == null) {
                jsonWriter.nullValue();
                return;
            }
            if (!g.this.f1645b) {
                jsonWriter.beginObject();
                for (Map.Entry<K, V> entry : map.entrySet()) {
                    jsonWriter.name(String.valueOf(entry.getKey()));
                    this.f1647b.a(jsonWriter, entry.getValue());
                }
                jsonWriter.endObject();
                return;
            }
            ArrayList arrayList = new ArrayList(map.size());
            ArrayList arrayList2 = new ArrayList(map.size());
            int i = 0;
            boolean z = false;
            for (Map.Entry<K, V> entry2 : map.entrySet()) {
                b.b.a.l lVarA = this.f1646a.a(entry2.getKey());
                arrayList.add(lVarA);
                arrayList2.add(entry2.getValue());
                z |= lVarA.d() || lVarA.f();
            }
            if (z) {
                jsonWriter.beginArray();
                int size = arrayList.size();
                while (i < size) {
                    jsonWriter.beginArray();
                    b.b.a.y.l.a((b.b.a.l) arrayList.get(i), jsonWriter);
                    this.f1647b.a(jsonWriter, (V) arrayList2.get(i));
                    jsonWriter.endArray();
                    i++;
                }
                jsonWriter.endArray();
                return;
            }
            jsonWriter.beginObject();
            int size2 = arrayList.size();
            while (i < size2) {
                jsonWriter.name(a((b.b.a.l) arrayList.get(i)));
                this.f1647b.a(jsonWriter, (V) arrayList2.get(i));
                i++;
            }
            jsonWriter.endObject();
        }

        private String a(b.b.a.l lVar) {
            if (lVar.g()) {
                q qVarC = lVar.c();
                if (qVarC.p()) {
                    return String.valueOf(qVarC.m());
                }
                if (qVarC.o()) {
                    return Boolean.toString(qVarC.h());
                }
                if (qVarC.q()) {
                    return qVarC.n();
                }
                throw new AssertionError();
            }
            if (lVar.e()) {
                return "null";
            }
            throw new AssertionError();
        }
    }

    private v<?> a(b.b.a.f fVar, Type type) {
        if (type != Boolean.TYPE && type != Boolean.class) {
            return fVar.a((b.b.a.z.a) b.b.a.z.a.a(type));
        }
        return n.f1684f;
    }
}
