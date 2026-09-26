package y;

import android.database.sqlite.SQLiteProgram;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public class i implements x.e {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final SQLiteProgram f2707d;

    public i(SQLiteProgram sQLiteProgram) {
        j2.i.e(sQLiteProgram, "delegate");
        this.f2707d = sQLiteProgram;
    }

    @Override // x.e
    public final void E(int i4, double d4) {
        this.f2707d.bindDouble(i4, d4);
    }

    @Override // x.e
    public final void a(int i4, long j4) {
        this.f2707d.bindLong(i4, j4);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        this.f2707d.close();
    }

    @Override // x.e
    public final void d(int i4, byte[] bArr) {
        this.f2707d.bindBlob(i4, bArr);
    }

    @Override // x.e
    public final void e(int i4) {
        this.f2707d.bindNull(i4);
    }

    @Override // x.e
    public final void u(int i4, String str) {
        j2.i.e(str, "value");
        this.f2707d.bindString(i4, str);
    }
}
