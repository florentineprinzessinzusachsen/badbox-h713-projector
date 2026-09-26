package e;

import java.io.Closeable;
import java.io.Flushable;

/* JADX INFO: compiled from: Sink.java */
/* JADX INFO: loaded from: classes.dex */
public interface r extends Closeable, Flushable {
    void a(c cVar, long j);

    @Override // java.io.Closeable, java.lang.AutoCloseable
    void close();

    void flush();

    t timeout();
}
