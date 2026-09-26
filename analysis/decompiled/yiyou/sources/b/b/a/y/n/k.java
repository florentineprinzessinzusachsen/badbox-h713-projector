package b.b.a.y.n;

import b.b.a.t;
import b.b.a.v;
import b.b.a.w;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/* JADX INFO: compiled from: TimeTypeAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public final class k extends v<Time> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final w f1668b = new a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final DateFormat f1669a = new SimpleDateFormat("hh:mm:ss a");

    /* JADX INFO: compiled from: TimeTypeAdapter.java */
    static class a implements w {
        a() {
        }

        @Override // b.b.a.w
        public <T> v<T> a(b.b.a.f fVar, b.b.a.z.a<T> aVar) {
            if (aVar.a() == Time.class) {
                return new k();
            }
            return null;
        }
    }

    @Override // b.b.a.v
    /* JADX INFO: renamed from: a, reason: avoid collision after fix types in other method */
    public synchronized Time a2(JsonReader jsonReader) {
        if (jsonReader.peek() == JsonToken.NULL) {
            jsonReader.nextNull();
            return null;
        }
        try {
            return new Time(this.f1669a.parse(jsonReader.nextString()).getTime());
        } catch (ParseException e2) {
            throw new t(e2);
        }
    }

    @Override // b.b.a.v
    public synchronized void a(JsonWriter jsonWriter, Time time) {
        jsonWriter.value(time == null ? null : this.f1669a.format((Date) time));
    }
}
