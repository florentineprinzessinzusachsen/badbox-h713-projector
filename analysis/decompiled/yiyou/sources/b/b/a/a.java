package b.b.a;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* JADX INFO: compiled from: DefaultDateTypeAdapter.java */
/* JADX INFO: loaded from: classes.dex */
final class a extends v<Date> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Class<? extends Date> f1534a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final List<DateFormat> f1535b = new ArrayList();

    a(Class<? extends Date> cls, String str) {
        a(cls);
        this.f1534a = cls;
        this.f1535b.add(new SimpleDateFormat(str, Locale.US));
        if (Locale.getDefault().equals(Locale.US)) {
            return;
        }
        this.f1535b.add(new SimpleDateFormat(str));
    }

    public String toString() {
        DateFormat dateFormat = this.f1535b.get(0);
        if (dateFormat instanceof SimpleDateFormat) {
            return "DefaultDateTypeAdapter(" + ((SimpleDateFormat) dateFormat).toPattern() + ')';
        }
        return "DefaultDateTypeAdapter(" + dateFormat.getClass().getSimpleName() + ')';
    }

    private static Class<? extends Date> a(Class<? extends Date> cls) {
        if (cls == Date.class || cls == java.sql.Date.class || cls == Timestamp.class) {
            return cls;
        }
        throw new IllegalArgumentException("Date type must be one of " + Date.class + ", " + Timestamp.class + ", or " + java.sql.Date.class + " but was " + cls);
    }

    @Override // b.b.a.v
    public void a(JsonWriter jsonWriter, Date date) throws IOException {
        if (date == null) {
            jsonWriter.nullValue();
            return;
        }
        synchronized (this.f1535b) {
            jsonWriter.value(this.f1535b.get(0).format(date));
        }
    }

    public a(Class<? extends Date> cls, int i, int i2) {
        a(cls);
        this.f1534a = cls;
        this.f1535b.add(DateFormat.getDateTimeInstance(i, i2, Locale.US));
        if (!Locale.getDefault().equals(Locale.US)) {
            this.f1535b.add(DateFormat.getDateTimeInstance(i, i2));
        }
        if (b.b.a.y.e.c()) {
            this.f1535b.add(b.b.a.y.j.a(i, i2));
        }
    }

    @Override // b.b.a.v
    /* JADX INFO: renamed from: a, reason: avoid collision after fix types in other method */
    public Date a2(JsonReader jsonReader) throws IOException {
        if (jsonReader.peek() == JsonToken.NULL) {
            jsonReader.nextNull();
            return null;
        }
        Date dateA = a(jsonReader.nextString());
        Class<? extends Date> cls = this.f1534a;
        if (cls == Date.class) {
            return dateA;
        }
        if (cls == Timestamp.class) {
            return new Timestamp(dateA.getTime());
        }
        if (cls == java.sql.Date.class) {
            return new java.sql.Date(dateA.getTime());
        }
        throw new AssertionError();
    }

    private Date a(String str) {
        synchronized (this.f1535b) {
            Iterator<DateFormat> it = this.f1535b.iterator();
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
    }
}
