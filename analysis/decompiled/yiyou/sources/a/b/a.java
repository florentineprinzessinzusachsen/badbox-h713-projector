package a.b;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/* JADX INFO: compiled from: ArrayMap.java */
/* JADX INFO: loaded from: classes.dex */
public class a<K, V> extends g<K, V> implements Map<K, V> {
    f<K, V> h;

    /* JADX INFO: renamed from: a.b.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ArrayMap.java */
    class C0002a extends f<K, V> {
        C0002a() {
        }

        @Override // a.b.f
        protected Object a(int i, int i2) {
            return a.this.f62b[(i << 1) + i2];
        }

        @Override // a.b.f
        protected int b(Object obj) {
            return a.this.b(obj);
        }

        @Override // a.b.f
        protected int c() {
            return a.this.f63c;
        }

        @Override // a.b.f
        protected int a(Object obj) {
            return a.this.a(obj);
        }

        @Override // a.b.f
        protected Map<K, V> b() {
            return a.this;
        }

        @Override // a.b.f
        protected void a(K k, V v) {
            a.this.put(k, v);
        }

        @Override // a.b.f
        protected V a(int i, V v) {
            return a.this.a(i, v);
        }

        @Override // a.b.f
        protected void a(int i) {
            a.this.c(i);
        }

        @Override // a.b.f
        protected void a() {
            a.this.clear();
        }
    }

    public a() {
    }

    private f<K, V> b() {
        if (this.h == null) {
            this.h = new C0002a();
        }
        return this.h;
    }

    public boolean a(Collection<?> collection) {
        return f.c(this, collection);
    }

    @Override // java.util.Map
    public Set<Map.Entry<K, V>> entrySet() {
        return b().d();
    }

    @Override // java.util.Map
    public Set<K> keySet() {
        return b().e();
    }

    @Override // java.util.Map
    public void putAll(Map<? extends K, ? extends V> map) {
        a(this.f63c + map.size());
        for (Map.Entry<? extends K, ? extends V> entry : map.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override // java.util.Map
    public Collection<V> values() {
        return b().f();
    }

    public a(int i) {
        super(i);
    }
}
