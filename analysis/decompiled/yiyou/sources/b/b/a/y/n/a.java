package b.b.a.y.n;

import b.b.a.v;
import b.b.a.w;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import java.util.ArrayList;

/* JADX INFO: compiled from: ArrayTypeAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public final class a<E> extends v<Object> {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final w f1625c = new C0041a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Class<E> f1626a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final v<E> f1627b;

    /* JADX INFO: renamed from: b.b.a.y.n.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ArrayTypeAdapter.java */
    static class C0041a implements w {
        C0041a() {
        }

        @Override // b.b.a.w
        public <T> v<T> a(b.b.a.f fVar, b.b.a.z.a<T> aVar) {
            Type typeB = aVar.b();
            if (!(typeB instanceof GenericArrayType) && (!(typeB instanceof Class) || !((Class) typeB).isArray())) {
                return null;
            }
            Type typeD = b.b.a.y.b.d(typeB);
            return new a(fVar, fVar.a((b.b.a.z.a) b.b.a.z.a.a(typeD)), b.b.a.y.b.e(typeD));
        }
    }

    public a(b.b.a.f fVar, v<E> vVar, Class<E> cls) {
        this.f1627b = new m(fVar, vVar, cls);
        this.f1626a = cls;
    }

    @Override // b.b.a.v
    /* JADX INFO: renamed from: a */
    public Object a2(JsonReader jsonReader) throws IOException {
        if (jsonReader.peek() == JsonToken.NULL) {
            jsonReader.nextNull();
            return null;
        }
        ArrayList arrayList = new ArrayList();
        jsonReader.beginArray();
        while (jsonReader.hasNext()) {
            arrayList.add(this.f1627b.a2(jsonReader));
        }
        jsonReader.endArray();
        int size = arrayList.size();
        Object objNewInstance = Array.newInstance((Class<?>) this.f1626a, size);
        for (int i = 0; i < size; i++) {
            Array.set(objNewInstance, i, arrayList.get(i));
        }
        return objNewInstance;
    }

    @Override // b.b.a.v
    public void a(JsonWriter jsonWriter, Object obj) throws IOException {
        if (obj == null) {
            jsonWriter.nullValue();
            return;
        }
        jsonWriter.beginArray();
        int length = Array.getLength(obj);
        for (int i = 0; i < length; i++) {
            this.f1627b.a(jsonWriter, (E) Array.get(obj, i));
        }
        jsonWriter.endArray();
    }
}
