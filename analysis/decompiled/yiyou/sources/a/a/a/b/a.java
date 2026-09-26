package a.a.a.b;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: FastSafeIterableMap.java */
/* JADX INFO: loaded from: classes.dex */
public class a<K, V> extends b<K, V> {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private HashMap<K, b.c<K, V>> f7e = new HashMap<>();

    @Override // a.a.a.b.b
    protected b.c<K, V> a(K k) {
        return this.f7e.get(k);
    }

    @Override // a.a.a.b.b
    public V b(K k, V v) {
        b.c<K, V> cVarA = a(k);
        if (cVarA != null) {
            return cVarA.f13b;
        }
        this.f7e.put(k, a(k, v));
        return null;
    }

    public boolean contains(K k) {
        return this.f7e.containsKey(k);
    }

    @Override // a.a.a.b.b
    public V remove(K k) {
        V v = (V) super.remove(k);
        this.f7e.remove(k);
        return v;
    }

    public Map.Entry<K, V> b(K k) {
        if (contains(k)) {
            return this.f7e.get(k).f15d;
        }
        return null;
    }
}
