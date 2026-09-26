package b.b.a.y.n;

import b.b.a.o;
import b.b.a.q;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import java.io.Reader;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: compiled from: JsonTreeReader.java */
/* JADX INFO: loaded from: classes.dex */
public final class e extends JsonReader {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static final Object f1634e;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Object[] f1635a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f1636b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private String[] f1637c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int[] f1638d;

    /* JADX INFO: compiled from: JsonTreeReader.java */
    static class a extends Reader {
        a() {
        }

        @Override // java.io.Reader, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            throw new AssertionError();
        }

        @Override // java.io.Reader
        public int read(char[] cArr, int i, int i2) {
            throw new AssertionError();
        }
    }

    static {
        new a();
        f1634e = new Object();
    }

    private void a(JsonToken jsonToken) {
        if (peek() == jsonToken) {
            return;
        }
        throw new IllegalStateException("Expected " + jsonToken + " but was " + peek() + locationString());
    }

    private Object b() {
        return this.f1635a[this.f1636b - 1];
    }

    private String locationString() {
        return " at path " + getPath();
    }

    private Object m() {
        Object[] objArr = this.f1635a;
        int i = this.f1636b - 1;
        this.f1636b = i;
        Object obj = objArr[i];
        objArr[this.f1636b] = null;
        return obj;
    }

    @Override // com.google.gson.stream.JsonReader
    public void beginArray() {
        a(JsonToken.BEGIN_ARRAY);
        a(((b.b.a.i) b()).iterator());
        this.f1638d[this.f1636b - 1] = 0;
    }

    @Override // com.google.gson.stream.JsonReader
    public void beginObject() {
        a(JsonToken.BEGIN_OBJECT);
        a(((o) b()).h().iterator());
    }

    @Override // com.google.gson.stream.JsonReader, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.f1635a = new Object[]{f1634e};
        this.f1636b = 1;
    }

    @Override // com.google.gson.stream.JsonReader
    public void endArray() {
        a(JsonToken.END_ARRAY);
        m();
        m();
        int i = this.f1636b;
        if (i > 0) {
            int[] iArr = this.f1638d;
            int i2 = i - 1;
            iArr[i2] = iArr[i2] + 1;
        }
    }

    @Override // com.google.gson.stream.JsonReader
    public void endObject() {
        a(JsonToken.END_OBJECT);
        m();
        m();
        int i = this.f1636b;
        if (i > 0) {
            int[] iArr = this.f1638d;
            int i2 = i - 1;
            iArr[i2] = iArr[i2] + 1;
        }
    }

    @Override // com.google.gson.stream.JsonReader
    public String getPath() {
        StringBuilder sb = new StringBuilder();
        sb.append('$');
        int i = 0;
        while (i < this.f1636b) {
            Object[] objArr = this.f1635a;
            if (objArr[i] instanceof b.b.a.i) {
                i++;
                if (objArr[i] instanceof Iterator) {
                    sb.append('[');
                    sb.append(this.f1638d[i]);
                    sb.append(']');
                }
            } else if (objArr[i] instanceof o) {
                i++;
                if (objArr[i] instanceof Iterator) {
                    sb.append('.');
                    String[] strArr = this.f1637c;
                    if (strArr[i] != null) {
                        sb.append(strArr[i]);
                    }
                }
            }
            i++;
        }
        return sb.toString();
    }

    @Override // com.google.gson.stream.JsonReader
    public boolean hasNext() {
        JsonToken jsonTokenPeek = peek();
        return (jsonTokenPeek == JsonToken.END_OBJECT || jsonTokenPeek == JsonToken.END_ARRAY) ? false : true;
    }

    @Override // com.google.gson.stream.JsonReader
    public boolean nextBoolean() {
        a(JsonToken.BOOLEAN);
        boolean zH = ((q) m()).h();
        int i = this.f1636b;
        if (i > 0) {
            int[] iArr = this.f1638d;
            int i2 = i - 1;
            iArr[i2] = iArr[i2] + 1;
        }
        return zH;
    }

    @Override // com.google.gson.stream.JsonReader
    public double nextDouble() {
        JsonToken jsonTokenPeek = peek();
        if (jsonTokenPeek != JsonToken.NUMBER && jsonTokenPeek != JsonToken.STRING) {
            throw new IllegalStateException("Expected " + JsonToken.NUMBER + " but was " + jsonTokenPeek + locationString());
        }
        double dJ = ((q) b()).j();
        if (!isLenient() && (Double.isNaN(dJ) || Double.isInfinite(dJ))) {
            throw new NumberFormatException("JSON forbids NaN and infinities: " + dJ);
        }
        m();
        int i = this.f1636b;
        if (i > 0) {
            int[] iArr = this.f1638d;
            int i2 = i - 1;
            iArr[i2] = iArr[i2] + 1;
        }
        return dJ;
    }

    @Override // com.google.gson.stream.JsonReader
    public int nextInt() {
        JsonToken jsonTokenPeek = peek();
        if (jsonTokenPeek != JsonToken.NUMBER && jsonTokenPeek != JsonToken.STRING) {
            throw new IllegalStateException("Expected " + JsonToken.NUMBER + " but was " + jsonTokenPeek + locationString());
        }
        int iK = ((q) b()).k();
        m();
        int i = this.f1636b;
        if (i > 0) {
            int[] iArr = this.f1638d;
            int i2 = i - 1;
            iArr[i2] = iArr[i2] + 1;
        }
        return iK;
    }

    @Override // com.google.gson.stream.JsonReader
    public long nextLong() {
        JsonToken jsonTokenPeek = peek();
        if (jsonTokenPeek != JsonToken.NUMBER && jsonTokenPeek != JsonToken.STRING) {
            throw new IllegalStateException("Expected " + JsonToken.NUMBER + " but was " + jsonTokenPeek + locationString());
        }
        long jL = ((q) b()).l();
        m();
        int i = this.f1636b;
        if (i > 0) {
            int[] iArr = this.f1638d;
            int i2 = i - 1;
            iArr[i2] = iArr[i2] + 1;
        }
        return jL;
    }

    @Override // com.google.gson.stream.JsonReader
    public String nextName() {
        a(JsonToken.NAME);
        Map.Entry entry = (Map.Entry) ((Iterator) b()).next();
        String str = (String) entry.getKey();
        this.f1637c[this.f1636b - 1] = str;
        a(entry.getValue());
        return str;
    }

    @Override // com.google.gson.stream.JsonReader
    public void nextNull() {
        a(JsonToken.NULL);
        m();
        int i = this.f1636b;
        if (i > 0) {
            int[] iArr = this.f1638d;
            int i2 = i - 1;
            iArr[i2] = iArr[i2] + 1;
        }
    }

    @Override // com.google.gson.stream.JsonReader
    public String nextString() {
        JsonToken jsonTokenPeek = peek();
        if (jsonTokenPeek == JsonToken.STRING || jsonTokenPeek == JsonToken.NUMBER) {
            String strN = ((q) m()).n();
            int i = this.f1636b;
            if (i > 0) {
                int[] iArr = this.f1638d;
                int i2 = i - 1;
                iArr[i2] = iArr[i2] + 1;
            }
            return strN;
        }
        throw new IllegalStateException("Expected " + JsonToken.STRING + " but was " + jsonTokenPeek + locationString());
    }

    @Override // com.google.gson.stream.JsonReader
    public JsonToken peek() {
        if (this.f1636b == 0) {
            return JsonToken.END_DOCUMENT;
        }
        Object objB = b();
        if (objB instanceof Iterator) {
            boolean z = this.f1635a[this.f1636b - 2] instanceof o;
            Iterator it = (Iterator) objB;
            if (!it.hasNext()) {
                return z ? JsonToken.END_OBJECT : JsonToken.END_ARRAY;
            }
            if (z) {
                return JsonToken.NAME;
            }
            a(it.next());
            return peek();
        }
        if (objB instanceof o) {
            return JsonToken.BEGIN_OBJECT;
        }
        if (objB instanceof b.b.a.i) {
            return JsonToken.BEGIN_ARRAY;
        }
        if (!(objB instanceof q)) {
            if (objB instanceof b.b.a.n) {
                return JsonToken.NULL;
            }
            if (objB == f1634e) {
                throw new IllegalStateException("JsonReader is closed");
            }
            throw new AssertionError();
        }
        q qVar = (q) objB;
        if (qVar.q()) {
            return JsonToken.STRING;
        }
        if (qVar.o()) {
            return JsonToken.BOOLEAN;
        }
        if (qVar.p()) {
            return JsonToken.NUMBER;
        }
        throw new AssertionError();
    }

    @Override // com.google.gson.stream.JsonReader
    public void skipValue() {
        if (peek() == JsonToken.NAME) {
            nextName();
            this.f1637c[this.f1636b - 2] = "null";
        } else {
            m();
            int i = this.f1636b;
            if (i > 0) {
                this.f1637c[i - 1] = "null";
            }
        }
        int i2 = this.f1636b;
        if (i2 > 0) {
            int[] iArr = this.f1638d;
            int i3 = i2 - 1;
            iArr[i3] = iArr[i3] + 1;
        }
    }

    @Override // com.google.gson.stream.JsonReader
    public String toString() {
        return e.class.getSimpleName();
    }

    public void a() {
        a(JsonToken.NAME);
        Map.Entry entry = (Map.Entry) ((Iterator) b()).next();
        a(entry.getValue());
        a(new q((String) entry.getKey()));
    }

    private void a(Object obj) {
        int i = this.f1636b;
        Object[] objArr = this.f1635a;
        if (i == objArr.length) {
            Object[] objArr2 = new Object[i * 2];
            int[] iArr = new int[i * 2];
            String[] strArr = new String[i * 2];
            System.arraycopy(objArr, 0, objArr2, 0, i);
            System.arraycopy(this.f1638d, 0, iArr, 0, this.f1636b);
            System.arraycopy(this.f1637c, 0, strArr, 0, this.f1636b);
            this.f1635a = objArr2;
            this.f1638d = iArr;
            this.f1637c = strArr;
        }
        Object[] objArr3 = this.f1635a;
        int i2 = this.f1636b;
        this.f1636b = i2 + 1;
        objArr3[i2] = obj;
    }
}
