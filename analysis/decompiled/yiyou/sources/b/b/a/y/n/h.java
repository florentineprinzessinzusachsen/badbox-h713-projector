package b.b.a.y.n;

import b.b.a.v;
import b.b.a.w;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.ArrayList;

/* JADX INFO: compiled from: ObjectTypeAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public final class h extends v<Object> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final w f1650b = new a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final b.b.a.f f1651a;

    /* JADX INFO: compiled from: ObjectTypeAdapter.java */
    static class a implements w {
        a() {
        }

        @Override // b.b.a.w
        public <T> v<T> a(b.b.a.f fVar, b.b.a.z.a<T> aVar) {
            if (aVar.a() == Object.class) {
                return new h(fVar);
            }
            return null;
        }
    }

    /* JADX INFO: compiled from: ObjectTypeAdapter.java */
    static /* synthetic */ class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f1652a = new int[JsonToken.values().length];

        static {
            try {
                f1652a[JsonToken.BEGIN_ARRAY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1652a[JsonToken.BEGIN_OBJECT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1652a[JsonToken.STRING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f1652a[JsonToken.NUMBER.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f1652a[JsonToken.BOOLEAN.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f1652a[JsonToken.NULL.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    h(b.b.a.f fVar) {
        this.f1651a = fVar;
    }

    @Override // b.b.a.v
    /* JADX INFO: renamed from: a */
    public Object a2(JsonReader jsonReader) throws IOException {
        switch (b.f1652a[jsonReader.peek().ordinal()]) {
            case 1:
                ArrayList arrayList = new ArrayList();
                jsonReader.beginArray();
                while (jsonReader.hasNext()) {
                    arrayList.add(a2(jsonReader));
                }
                jsonReader.endArray();
                return arrayList;
            case 2:
                b.b.a.y.h hVar = new b.b.a.y.h();
                jsonReader.beginObject();
                while (jsonReader.hasNext()) {
                    hVar.put(jsonReader.nextName(), a2(jsonReader));
                }
                jsonReader.endObject();
                return hVar;
            case 3:
                return jsonReader.nextString();
            case 4:
                return Double.valueOf(jsonReader.nextDouble());
            case 5:
                return Boolean.valueOf(jsonReader.nextBoolean());
            case 6:
                jsonReader.nextNull();
                return null;
            default:
                throw new IllegalStateException();
        }
    }

    @Override // b.b.a.v
    public void a(JsonWriter jsonWriter, Object obj) throws IOException {
        if (obj == null) {
            jsonWriter.nullValue();
            return;
        }
        v vVarA = this.f1651a.a((Class) obj.getClass());
        if (vVarA instanceof h) {
            jsonWriter.beginObject();
            jsonWriter.endObject();
        } else {
            vVarA.a(jsonWriter, obj);
        }
    }
}
