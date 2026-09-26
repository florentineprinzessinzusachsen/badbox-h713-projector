package b.b.a.y.n;

import b.b.a.o;
import b.b.a.q;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: JsonTreeWriter.java */
/* JADX INFO: loaded from: classes.dex */
public final class f extends JsonWriter {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final Writer f1639d = new a();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static final q f1640e = new q("closed");

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final List<b.b.a.l> f1641a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f1642b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private b.b.a.l f1643c;

    /* JADX INFO: compiled from: JsonTreeWriter.java */
    static class a extends Writer {
        a() {
        }

        @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            throw new AssertionError();
        }

        @Override // java.io.Writer, java.io.Flushable
        public void flush() {
            throw new AssertionError();
        }

        @Override // java.io.Writer
        public void write(char[] cArr, int i, int i2) {
            throw new AssertionError();
        }
    }

    public f() {
        super(f1639d);
        this.f1641a = new ArrayList();
        this.f1643c = b.b.a.n.f1558a;
    }

    private b.b.a.l peek() {
        List<b.b.a.l> list = this.f1641a;
        return list.get(list.size() - 1);
    }

    public b.b.a.l a() {
        if (this.f1641a.isEmpty()) {
            return this.f1643c;
        }
        throw new IllegalStateException("Expected one JSON element but was " + this.f1641a);
    }

    @Override // com.google.gson.stream.JsonWriter
    public JsonWriter beginArray() {
        b.b.a.i iVar = new b.b.a.i();
        a(iVar);
        this.f1641a.add(iVar);
        return this;
    }

    @Override // com.google.gson.stream.JsonWriter
    public JsonWriter beginObject() {
        o oVar = new o();
        a(oVar);
        this.f1641a.add(oVar);
        return this;
    }

    @Override // com.google.gson.stream.JsonWriter, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (!this.f1641a.isEmpty()) {
            throw new IOException("Incomplete document");
        }
        this.f1641a.add(f1640e);
    }

    @Override // com.google.gson.stream.JsonWriter
    public JsonWriter endArray() {
        if (this.f1641a.isEmpty() || this.f1642b != null) {
            throw new IllegalStateException();
        }
        if (!(peek() instanceof b.b.a.i)) {
            throw new IllegalStateException();
        }
        List<b.b.a.l> list = this.f1641a;
        list.remove(list.size() - 1);
        return this;
    }

    @Override // com.google.gson.stream.JsonWriter
    public JsonWriter endObject() {
        if (this.f1641a.isEmpty() || this.f1642b != null) {
            throw new IllegalStateException();
        }
        if (!(peek() instanceof o)) {
            throw new IllegalStateException();
        }
        List<b.b.a.l> list = this.f1641a;
        list.remove(list.size() - 1);
        return this;
    }

    @Override // com.google.gson.stream.JsonWriter, java.io.Flushable
    public void flush() {
    }

    @Override // com.google.gson.stream.JsonWriter
    public JsonWriter name(String str) {
        if (this.f1641a.isEmpty() || this.f1642b != null) {
            throw new IllegalStateException();
        }
        if (!(peek() instanceof o)) {
            throw new IllegalStateException();
        }
        this.f1642b = str;
        return this;
    }

    @Override // com.google.gson.stream.JsonWriter
    public JsonWriter nullValue() {
        a(b.b.a.n.f1558a);
        return this;
    }

    @Override // com.google.gson.stream.JsonWriter
    public JsonWriter value(String str) {
        if (str == null) {
            nullValue();
            return this;
        }
        a(new q(str));
        return this;
    }

    @Override // com.google.gson.stream.JsonWriter
    public JsonWriter value(boolean z) {
        a(new q(Boolean.valueOf(z)));
        return this;
    }

    private void a(b.b.a.l lVar) {
        if (this.f1642b != null) {
            if (!lVar.e() || getSerializeNulls()) {
                ((o) peek()).a(this.f1642b, lVar);
            }
            this.f1642b = null;
            return;
        }
        if (this.f1641a.isEmpty()) {
            this.f1643c = lVar;
            return;
        }
        b.b.a.l lVarPeek = peek();
        if (lVarPeek instanceof b.b.a.i) {
            ((b.b.a.i) lVarPeek).a(lVar);
            return;
        }
        throw new IllegalStateException();
    }

    @Override // com.google.gson.stream.JsonWriter
    public JsonWriter value(Boolean bool) {
        if (bool == null) {
            nullValue();
            return this;
        }
        a(new q(bool));
        return this;
    }

    @Override // com.google.gson.stream.JsonWriter
    public JsonWriter value(double d2) {
        if (!isLenient() && (Double.isNaN(d2) || Double.isInfinite(d2))) {
            throw new IllegalArgumentException("JSON forbids NaN and infinities: " + d2);
        }
        a(new q(Double.valueOf(d2)));
        return this;
    }

    @Override // com.google.gson.stream.JsonWriter
    public JsonWriter value(long j) {
        a(new q(Long.valueOf(j)));
        return this;
    }

    @Override // com.google.gson.stream.JsonWriter
    public JsonWriter value(Number number) {
        if (number == null) {
            nullValue();
            return this;
        }
        if (!isLenient()) {
            double dDoubleValue = number.doubleValue();
            if (Double.isNaN(dDoubleValue) || Double.isInfinite(dDoubleValue)) {
                throw new IllegalArgumentException("JSON forbids NaN and infinities: " + number);
            }
        }
        a(new q(number));
        return this;
    }
}
