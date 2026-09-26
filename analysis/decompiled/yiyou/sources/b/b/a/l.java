package b.b.a;

import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.StringWriter;

/* JADX INFO: compiled from: JsonElement.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class l {
    public i a() {
        if (d()) {
            return (i) this;
        }
        throw new IllegalStateException("Not a JSON Array: " + this);
    }

    public o b() {
        if (f()) {
            return (o) this;
        }
        throw new IllegalStateException("Not a JSON Object: " + this);
    }

    public q c() {
        if (g()) {
            return (q) this;
        }
        throw new IllegalStateException("Not a JSON Primitive: " + this);
    }

    public boolean d() {
        return this instanceof i;
    }

    public boolean e() {
        return this instanceof n;
    }

    public boolean f() {
        return this instanceof o;
    }

    public boolean g() {
        return this instanceof q;
    }

    public String toString() {
        try {
            StringWriter stringWriter = new StringWriter();
            JsonWriter jsonWriter = new JsonWriter(stringWriter);
            jsonWriter.setLenient(true);
            b.b.a.y.l.a(this, jsonWriter);
            return stringWriter.toString();
        } catch (IOException e2) {
            throw new AssertionError(e2);
        }
    }
}
