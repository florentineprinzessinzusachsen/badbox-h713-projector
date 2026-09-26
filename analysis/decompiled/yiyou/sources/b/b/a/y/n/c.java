package b.b.a.y.n;

import b.b.a.t;
import b.b.a.v;
import b.b.a.w;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* JADX INFO: compiled from: DateTypeAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public final class c extends v<Date> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final w f1631b = new a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final List<DateFormat> f1632a = new ArrayList();

    /* JADX INFO: compiled from: DateTypeAdapter.java */
    static class a implements w {
        a() {
        }

        @Override // b.b.a.w
        public <T> v<T> a(b.b.a.f fVar, b.b.a.z.a<T> aVar) {
            if (aVar.a() == Date.class) {
                return new c();
            }
            return null;
        }
    }

    public c() {
        this.f1632a.add(DateFormat.getDateTimeInstance(2, 2, Locale.US));
        if (!Locale.getDefault().equals(Locale.US)) {
            this.f1632a.add(DateFormat.getDateTimeInstance(2, 2));
        }
        if (b.b.a.y.e.c()) {
            this.f1632a.add(b.b.a.y.j.a(2, 2));
        }
    }

    @Override // b.b.a.v
    /* JADX INFO: renamed from: a, reason: avoid collision after fix types in other method */
    public Date a2(JsonReader jsonReader) throws IOException {
        if (jsonReader.peek() == JsonToken.NULL) {
            jsonReader.nextNull();
            return null;
        }
        return a(jsonReader.nextString());
    }

    private synchronized Date a(String str) {
        Iterator<DateFormat> it = this.f1632a.iterator();
        while (it.hasNext()) {
            try {
                return it.next().parse(str);
            } catch (ParseException unused) {
            }
        }
        try {
            return b.b.a.y.n.o.a.a(str, new ParsePosition(0));
        } catch (ParseException e2) {
            throw new t(str, e2);
        }
    }

    @Override // b.b.a.v
    public synchronized void a(JsonWriter jsonWriter, Date date) {
        try {
            if (date == null) {
                jsonWriter.nullValue();
            } else {
                jsonWriter.value(this.f1632a.get(0).format(date));
            }
        } catch (Throwable th) {
            throw th;
        }
    }
}
