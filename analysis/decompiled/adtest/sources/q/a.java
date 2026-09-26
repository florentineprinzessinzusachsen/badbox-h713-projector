package q;

import c3.b;
import java.util.LinkedHashMap;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final a1.a f1765c = new a1.a(16);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final LinkedHashMap f1766d = new LinkedHashMap();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ReentrantLock f1767a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final b f1768b;

    public a(String str, boolean z3) {
        ReentrantLock reentrantLock;
        synchronized (f1765c) {
            try {
                LinkedHashMap linkedHashMap = f1766d;
                Object reentrantLock2 = linkedHashMap.get(str);
                if (reentrantLock2 == null) {
                    reentrantLock2 = new ReentrantLock();
                    linkedHashMap.put(str, reentrantLock2);
                }
                reentrantLock = (ReentrantLock) reentrantLock2;
            } catch (Throwable th) {
                throw th;
            }
        }
        this.f1767a = reentrantLock;
        this.f1768b = z3 ? new b(str) : null;
    }
}
