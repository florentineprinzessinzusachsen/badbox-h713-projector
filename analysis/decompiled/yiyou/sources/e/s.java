package e;

import java.io.Closeable;

/* JADX INFO: compiled from: Source.java */
/* JADX INFO: loaded from: classes.dex */
public interface s extends Closeable {
    @Override // java.io.Closeable, java.lang.AutoCloseable
    void close();

    long read(c cVar, long j);

    t timeout();
}
