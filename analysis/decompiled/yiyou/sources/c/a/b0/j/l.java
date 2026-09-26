package c.a.b0.j;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

/* JADX INFO: compiled from: HashMapSupplier.java */
/* JADX INFO: loaded from: classes.dex */
public enum l implements Callable<Map<Object, Object>> {
    INSTANCE;

    public static <K, V> Callable<Map<K, V>> a() {
        return INSTANCE;
    }

    @Override // java.util.concurrent.Callable
    public Map<Object, Object> call() {
        return new HashMap();
    }
}
