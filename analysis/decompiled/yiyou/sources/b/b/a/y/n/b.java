package b.b.a.y.n;

import b.b.a.v;
import b.b.a.w;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Iterator;

/* JADX INFO: compiled from: CollectionTypeAdapterFactory.java */
/* JADX INFO: loaded from: classes.dex */
public final class b implements w {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final b.b.a.y.c f1628a;

    public b(b.b.a.y.c cVar) {
        this.f1628a = cVar;
    }

    @Override // b.b.a.w
    public <T> v<T> a(b.b.a.f fVar, b.b.a.z.a<T> aVar) {
        Type typeB = aVar.b();
        Class<? super T> clsA = aVar.a();
        if (!Collection.class.isAssignableFrom(clsA)) {
            return null;
        }
        Type typeA = b.b.a.y.b.a(typeB, (Class<?>) clsA);
        return new a(fVar, typeA, fVar.a((b.b.a.z.a) b.b.a.z.a.a(typeA)), this.f1628a.a(aVar));
    }

    /* JADX INFO: compiled from: CollectionTypeAdapterFactory.java */
    private static final class a<E> extends v<Collection<E>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final v<E> f1629a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final b.b.a.y.i<? extends Collection<E>> f1630b;

        public a(b.b.a.f fVar, Type type, v<E> vVar, b.b.a.y.i<? extends Collection<E>> iVar) {
            this.f1629a = new m(fVar, vVar, type);
            this.f1630b = iVar;
        }

        @Override // b.b.a.v
        /* JADX INFO: renamed from: a */
        public Collection<E> a2(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            Collection<E> collectionA = this.f1630b.a();
            jsonReader.beginArray();
            while (jsonReader.hasNext()) {
                collectionA.add(this.f1629a.a2(jsonReader));
            }
            jsonReader.endArray();
            return collectionA;
        }

        @Override // b.b.a.v
        public void a(JsonWriter jsonWriter, Collection<E> collection) throws IOException {
            if (collection == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginArray();
            Iterator<E> it = collection.iterator();
            while (it.hasNext()) {
                this.f1629a.a(jsonWriter, it.next());
            }
            jsonWriter.endArray();
        }
    }
}
