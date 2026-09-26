package a.b;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

/* JADX INFO: compiled from: LruCache.java */
/* JADX INFO: loaded from: classes.dex */
public class e<K, V> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final LinkedHashMap<K, V> f37a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f38b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f39c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f40d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f41e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private int f42f;
    private int g;
    private int h;

    public e(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        }
        this.f39c = i;
        this.f37a = new LinkedHashMap<>(0, 0.75f, true);
    }

    protected V a(K k) {
        return null;
    }

    public final V a(K k, V v) {
        V vPut;
        if (k == null || v == null) {
            throw new NullPointerException("key == null || value == null");
        }
        synchronized (this) {
            this.f40d++;
            this.f38b += c(k, v);
            vPut = this.f37a.put(k, v);
            if (vPut != null) {
                this.f38b -= c(k, vPut);
            }
        }
        if (vPut != null) {
            a(false, k, vPut, v);
        }
        a(this.f39c);
        return vPut;
    }

    protected void a(boolean z, K k, V v, V v2) {
    }

    protected int b(K k, V v) {
        return 1;
    }

    public final V b(K k) {
        V vPut;
        if (k == null) {
            throw new NullPointerException("key == null");
        }
        synchronized (this) {
            V v = this.f37a.get(k);
            if (v != null) {
                this.g++;
                return v;
            }
            this.h++;
            V vA = a(k);
            if (vA == null) {
                return null;
            }
            synchronized (this) {
                this.f41e++;
                vPut = this.f37a.put(k, vA);
                if (vPut != null) {
                    this.f37a.put(k, vPut);
                } else {
                    this.f38b += c(k, vA);
                }
            }
            if (vPut != null) {
                a(false, k, vA, vPut);
                return vPut;
            }
            a(this.f39c);
            return vA;
        }
    }

    public final V c(K k) {
        V vRemove;
        if (k == null) {
            throw new NullPointerException("key == null");
        }
        synchronized (this) {
            vRemove = this.f37a.remove(k);
            if (vRemove != null) {
                this.f38b -= c(k, vRemove);
            }
        }
        if (vRemove != null) {
            a(false, k, vRemove, null);
        }
        return vRemove;
    }

    public final synchronized String toString() {
        int i;
        i = this.g + this.h;
        return String.format(Locale.US, "LruCache[maxSize=%d,hits=%d,misses=%d,hitRate=%d%%]", Integer.valueOf(this.f39c), Integer.valueOf(this.g), Integer.valueOf(this.h), Integer.valueOf(i != 0 ? (this.g * 100) / i : 0));
    }

    private int c(K k, V v) {
        int iB = b(k, v);
        if (iB >= 0) {
            return iB;
        }
        throw new IllegalStateException("Negative size: " + k + "=" + v);
    }

    public void a(int i) {
        K key;
        V value;
        while (true) {
            synchronized (this) {
                if (this.f38b < 0 || (this.f37a.isEmpty() && this.f38b != 0)) {
                    break;
                }
                if (this.f38b > i && !this.f37a.isEmpty()) {
                    Map.Entry<K, V> next = this.f37a.entrySet().iterator().next();
                    key = next.getKey();
                    value = next.getValue();
                    this.f37a.remove(key);
                    this.f38b -= c(key, value);
                    this.f42f++;
                }
                return;
            }
            a(true, key, value, null);
        }
        throw new IllegalStateException(getClass().getName() + ".sizeOf() is reporting inconsistent results!");
    }

    public final synchronized int b() {
        return this.f38b;
    }

    public final void a() {
        a(-1);
    }
}
