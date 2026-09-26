package androidx.lifecycle;

import java.io.Closeable;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: compiled from: ViewModel.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Map<String, Object> f1404a = new HashMap();

    final void a() {
        Map<String, Object> map = this.f1404a;
        if (map != null) {
            synchronized (map) {
                Iterator<Object> it = this.f1404a.values().iterator();
                while (it.hasNext()) {
                    a(it.next());
                }
            }
        }
        b();
    }

    protected void b() {
    }

    private static void a(Object obj) {
        if (obj instanceof Closeable) {
            try {
                ((Closeable) obj).close();
            } catch (IOException e2) {
                throw new RuntimeException(e2);
            }
        }
    }
}
