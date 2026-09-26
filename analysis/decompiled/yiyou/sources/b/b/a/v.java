package b.b.a;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

/* JADX INFO: compiled from: TypeAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class v<T> {
    public final v<T> a() {
        return new a();
    }

    /* JADX INFO: renamed from: a */
    public abstract T a2(JsonReader jsonReader);

    public abstract void a(JsonWriter jsonWriter, T t);

    /* JADX INFO: compiled from: TypeAdapter.java */
    class a extends v<T> {
        a() {
        }

        @Override // b.b.a.v
        public void a(JsonWriter jsonWriter, T t) throws IOException {
            if (t == null) {
                jsonWriter.nullValue();
            } else {
                v.this.a(jsonWriter, t);
            }
        }

        @Override // b.b.a.v
        /* JADX INFO: renamed from: a */
        public T a2(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            return (T) v.this.a2(jsonReader);
        }
    }

    public final l a(T t) {
        try {
            b.b.a.y.n.f fVar = new b.b.a.y.n.f();
            a(fVar, t);
            return fVar.a();
        } catch (IOException e2) {
            throw new m(e2);
        }
    }
}
