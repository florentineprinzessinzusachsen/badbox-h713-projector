package d.h0.i;

import java.io.IOException;

/* JADX INFO: compiled from: StreamResetException.java */
/* JADX INFO: loaded from: classes.dex */
public final class n extends IOException {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final b f4581a;

    public n(b bVar) {
        super("stream was reset: " + bVar);
        this.f4581a = bVar;
    }
}
