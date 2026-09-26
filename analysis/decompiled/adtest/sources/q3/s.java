package q3;

import java.io.Closeable;
import java.io.Flushable;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public interface s extends Closeable, Flushable {
    void R(long j4, e eVar);

    @Override // java.io.Closeable, java.lang.AutoCloseable
    void close();

    w f();

    void flush();
}
