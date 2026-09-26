package b.b.a.y;

import b.b.a.t;
import b.b.a.y.n.n;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.google.gson.stream.MalformedJsonException;
import java.io.EOFException;
import java.io.IOException;
import java.io.Writer;

/* JADX INFO: compiled from: Streams.java */
/* JADX INFO: loaded from: classes.dex */
public final class l {
    public static b.b.a.l a(JsonReader jsonReader) {
        boolean z;
        try {
            try {
                jsonReader.peek();
                z = false;
                try {
                    return n.X.a2(jsonReader);
                } catch (EOFException e2) {
                    e = e2;
                    if (z) {
                        return b.b.a.n.f1558a;
                    }
                    throw new t(e);
                }
            } catch (EOFException e3) {
                e = e3;
                z = true;
            }
        } catch (MalformedJsonException e4) {
            throw new t(e4);
        } catch (IOException e5) {
            throw new b.b.a.m(e5);
        } catch (NumberFormatException e6) {
            throw new t(e6);
        }
    }

    /* JADX INFO: compiled from: Streams.java */
    private static final class a extends Writer {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Appendable f1617a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final C0040a f1618b = new C0040a();

        /* JADX INFO: renamed from: b.b.a.y.l$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: Streams.java */
        static class C0040a implements CharSequence {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            char[] f1619a;

            C0040a() {
            }

            @Override // java.lang.CharSequence
            public char charAt(int i) {
                return this.f1619a[i];
            }

            @Override // java.lang.CharSequence
            public int length() {
                return this.f1619a.length;
            }

            @Override // java.lang.CharSequence
            public CharSequence subSequence(int i, int i2) {
                return new String(this.f1619a, i, i2 - i);
            }
        }

        a(Appendable appendable) {
            this.f1617a = appendable;
        }

        @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
        }

        @Override // java.io.Writer, java.io.Flushable
        public void flush() {
        }

        @Override // java.io.Writer
        public void write(char[] cArr, int i, int i2) throws IOException {
            C0040a c0040a = this.f1618b;
            c0040a.f1619a = cArr;
            this.f1617a.append(c0040a, i, i2 + i);
        }

        @Override // java.io.Writer
        public void write(int i) throws IOException {
            this.f1617a.append((char) i);
        }
    }

    public static void a(b.b.a.l lVar, JsonWriter jsonWriter) {
        n.X.a(jsonWriter, lVar);
    }

    public static Writer a(Appendable appendable) {
        return appendable instanceof Writer ? (Writer) appendable : new a(appendable);
    }
}
