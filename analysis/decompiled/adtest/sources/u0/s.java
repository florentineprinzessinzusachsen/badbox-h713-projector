package u0;

import java.io.Writer;
import java.util.Objects;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class s extends Writer {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final StringBuilder f2285d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final r f2286e = new r();

    public s(StringBuilder sb) {
        this.f2285d = sb;
    }

    @Override // java.io.Writer, java.lang.Appendable
    public final Writer append(CharSequence charSequence) {
        this.f2285d.append(charSequence);
        return this;
    }

    @Override // java.io.Writer
    public final void write(int i4) {
        this.f2285d.append((char) i4);
    }

    @Override // java.io.Writer, java.lang.Appendable
    public final Appendable append(CharSequence charSequence) {
        this.f2285d.append(charSequence);
        return this;
    }

    @Override // java.io.Writer
    public final void write(String str, int i4, int i5) {
        Objects.requireNonNull(str);
        this.f2285d.append((CharSequence) str, i4, i5 + i4);
    }

    @Override // java.io.Writer, java.lang.Appendable
    public final Writer append(CharSequence charSequence, int i4, int i5) {
        this.f2285d.append(charSequence, i4, i5);
        return this;
    }

    @Override // java.io.Writer, java.lang.Appendable
    public final Appendable append(CharSequence charSequence, int i4, int i5) {
        this.f2285d.append(charSequence, i4, i5);
        return this;
    }

    @Override // java.io.Writer
    public final void write(char[] cArr, int i4, int i5) {
        r rVar = this.f2286e;
        rVar.f2283d = cArr;
        rVar.f2284e = null;
        this.f2285d.append((CharSequence) rVar, i4, i5 + i4);
    }

    @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
    }

    @Override // java.io.Writer, java.io.Flushable
    public final void flush() {
    }
}
