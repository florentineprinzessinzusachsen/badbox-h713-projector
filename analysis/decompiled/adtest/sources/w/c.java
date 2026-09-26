package w;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public interface c extends AutoCloseable {
    boolean F();

    void a(int i4, long j4);

    void d(int i4, byte[] bArr);

    void e(int i4);

    byte[] getBlob(int i4);

    int getColumnCount();

    String getColumnName(int i4);

    long getLong(int i4);

    boolean isNull(int i4);

    String n(int i4);

    void o(int i4, String str);

    void reset();

    boolean v();
}
