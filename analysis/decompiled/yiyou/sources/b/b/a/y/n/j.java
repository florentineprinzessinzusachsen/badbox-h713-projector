package b.b.a.y.n;

import b.b.a.t;
import b.b.a.v;
import b.b.a.w;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/* JADX INFO: compiled from: SqlDateTypeAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public final class j extends v<Date> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final w f1666b = new a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final DateFormat f1667a = new SimpleDateFormat("MMM d, yyyy");

    /* JADX INFO: compiled from: SqlDateTypeAdapter.java */
    static class a implements w {
        a() {
        }

        @Override // b.b.a.w
        public <T> v<T> a(b.b.a.f fVar, b.b.a.z.a<T> aVar) {
            if (aVar.a() == Date.class) {
                return new j();
            }
            return null;
        }
    }

    @Override // b.b.a.v
    /* JADX INFO: renamed from: a, reason: avoid collision after fix types in other method */
    public synchronized Date a2(JsonReader jsonReader) {
        if (jsonReader.peek() == JsonToken.NULL) {
            jsonReader.nextNull();
            return null;
        }
        try {
            return new Date(this.f1667a.parse(jsonReader.nextString()).getTime());
        } catch (ParseException e2) {
            throw new t(e2);
        }
    }

    @Override // b.b.a.v
    public synchronized void a(JsonWriter jsonWriter, Date date) {
        jsonWriter.value(date == null ? null : this.f1667a.format((java.util.Date) date));
    }
}
