package a.a.a.b;

import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: SafeIterableMap.java */
/* JADX INFO: loaded from: classes.dex */
public class b<K, V> implements Iterable<Map.Entry<K, V>> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    c<K, V> f8a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private c<K, V> f9b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private WeakHashMap<f<K, V>, Boolean> f10c = new WeakHashMap<>();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f11d = 0;

    /* JADX INFO: compiled from: SafeIterableMap.java */
    static class a<K, V> extends e<K, V> {
        a(c<K, V> cVar, c<K, V> cVar2) {
            super(cVar, cVar2);
        }

        @Override // a.a.a.b.b.e
        c<K, V> b(c<K, V> cVar) {
            return cVar.f15d;
        }

        @Override // a.a.a.b.b.e
        c<K, V> c(c<K, V> cVar) {
            return cVar.f14c;
        }
    }

    /* JADX INFO: renamed from: a.a.a.b.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: SafeIterableMap.java */
    private static class C0001b<K, V> extends e<K, V> {
        C0001b(c<K, V> cVar, c<K, V> cVar2) {
            super(cVar, cVar2);
        }

        @Override // a.a.a.b.b.e
        c<K, V> b(c<K, V> cVar) {
            return cVar.f14c;
        }

        @Override // a.a.a.b.b.e
        c<K, V> c(c<K, V> cVar) {
            return cVar.f15d;
        }
    }

    /* JADX INFO: compiled from: SafeIterableMap.java */
    static class c<K, V> implements Map.Entry<K, V> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final K f12a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final V f13b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        c<K, V> f14c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        c<K, V> f15d;

        c(K k, V v) {
            this.f12a = k;
            this.f13b = v;
        }

        @Override // java.util.Map.Entry
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof c)) {
                return false;
            }
            c cVar = (c) obj;
            return this.f12a.equals(cVar.f12a) && this.f13b.equals(cVar.f13b);
        }

        @Override // java.util.Map.Entry
        public K getKey() {
            return this.f12a;
        }

        @Override // java.util.Map.Entry
        public V getValue() {
            return this.f13b;
        }

        @Override // java.util.Map.Entry
        public int hashCode() {
            return this.f12a.hashCode() ^ this.f13b.hashCode();
        }

        @Override // java.util.Map.Entry
        public V setValue(V v) {
            throw new UnsupportedOperationException("An entry modification is not supported");
        }

        public String toString() {
            return this.f12a + "=" + this.f13b;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: SafeIterableMap.java */
    public class d implements Iterator<Map.Entry<K, V>>, f<K, V> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private c<K, V> f16a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private boolean f17b = true;

        d() {
        }

        @Override // a.a.a.b.b.f
        public void a(c<K, V> cVar) {
            c<K, V> cVar2 = this.f16a;
            if (cVar == cVar2) {
                this.f16a = cVar2.f15d;
                this.f17b = this.f16a == null;
            }
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            if (this.f17b) {
                return b.this.f8a != null;
            }
            c<K, V> cVar = this.f16a;
            return (cVar == null || cVar.f14c == null) ? false : true;
        }

        @Override // java.util.Iterator
        public Map.Entry<K, V> next() {
            if (this.f17b) {
                this.f17b = false;
                this.f16a = b.this.f8a;
            } else {
                c<K, V> cVar = this.f16a;
                this.f16a = cVar != null ? cVar.f14c : null;
            }
            return this.f16a;
        }
    }

    /* JADX INFO: compiled from: SafeIterableMap.java */
    private static abstract class e<K, V> implements Iterator<Map.Entry<K, V>>, f<K, V> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        c<K, V> f19a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        c<K, V> f20b;

        e(c<K, V> cVar, c<K, V> cVar2) {
            this.f19a = cVar2;
            this.f20b = cVar;
        }

        @Override // a.a.a.b.b.f
        public void a(c<K, V> cVar) {
            if (this.f19a == cVar && cVar == this.f20b) {
                this.f20b = null;
                this.f19a = null;
            }
            c<K, V> cVar2 = this.f19a;
            if (cVar2 == cVar) {
                this.f19a = b(cVar2);
            }
            if (this.f20b == cVar) {
                this.f20b = a();
            }
        }

        abstract c<K, V> b(c<K, V> cVar);

        abstract c<K, V> c(c<K, V> cVar);

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.f20b != null;
        }

        @Override // java.util.Iterator
        public Map.Entry<K, V> next() {
            c<K, V> cVar = this.f20b;
            this.f20b = a();
            return cVar;
        }

        private c<K, V> a() {
            c<K, V> cVar = this.f20b;
            c<K, V> cVar2 = this.f19a;
            if (cVar == cVar2 || cVar2 == null) {
                return null;
            }
            return c(cVar);
        }
    }

    /* JADX INFO: compiled from: SafeIterableMap.java */
    interface f<K, V> {
        void a(c<K, V> cVar);
    }

    protected c<K, V> a(K k) {
        c<K, V> cVar = this.f8a;
        while (cVar != null && !cVar.f12a.equals(k)) {
            cVar = cVar.f14c;
        }
        return cVar;
    }

    public V b(K k, V v) {
        c<K, V> cVarA = a(k);
        if (cVarA != null) {
            return cVarA.f13b;
        }
        a(k, v);
        return null;
    }

    public Map.Entry<K, V> c() {
        return this.f9b;
    }

    public Iterator<Map.Entry<K, V>> descendingIterator() {
        C0001b c0001b = new C0001b(this.f9b, this.f8a);
        this.f10c.put(c0001b, false);
        return c0001b;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof b)) {
            return false;
        }
        b bVar = (b) obj;
        if (size() != bVar.size()) {
            return false;
        }
        Iterator<Map.Entry<K, V>> it = iterator();
        Iterator<Map.Entry<K, V>> it2 = bVar.iterator();
        while (it.hasNext() && it2.hasNext()) {
            Map.Entry<K, V> next = it.next();
            Map.Entry<K, V> next2 = it2.next();
            if ((next == null && next2 != null) || (next != null && !next.equals(next2))) {
                return false;
            }
        }
        return (it.hasNext() || it2.hasNext()) ? false : true;
    }

    public int hashCode() {
        Iterator<Map.Entry<K, V>> it = iterator();
        int iHashCode = 0;
        while (it.hasNext()) {
            iHashCode += it.next().hashCode();
        }
        return iHashCode;
    }

    @Override // java.lang.Iterable
    public Iterator<Map.Entry<K, V>> iterator() {
        a aVar = new a(this.f8a, this.f9b);
        this.f10c.put(aVar, false);
        return aVar;
    }

    public V remove(K k) {
        c<K, V> cVarA = a(k);
        if (cVarA == null) {
            return null;
        }
        this.f11d--;
        if (!this.f10c.isEmpty()) {
            Iterator<f<K, V>> it = this.f10c.keySet().iterator();
            while (it.hasNext()) {
                it.next().a(cVarA);
            }
        }
        c<K, V> cVar = cVarA.f15d;
        if (cVar != null) {
            cVar.f14c = cVarA.f14c;
        } else {
            this.f8a = cVarA.f14c;
        }
        c<K, V> cVar2 = cVarA.f14c;
        if (cVar2 != null) {
            cVar2.f15d = cVarA.f15d;
        } else {
            this.f9b = cVarA.f15d;
        }
        cVarA.f14c = null;
        cVarA.f15d = null;
        return cVarA.f13b;
    }

    public int size() {
        return this.f11d;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Iterator<Map.Entry<K, V>> it = iterator();
        while (it.hasNext()) {
            sb.append(it.next().toString());
            if (it.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    protected c<K, V> a(K k, V v) {
        c<K, V> cVar = new c<>(k, v);
        this.f11d++;
        c<K, V> cVar2 = this.f9b;
        if (cVar2 == null) {
            this.f8a = cVar;
            this.f9b = this.f8a;
            return cVar;
        }
        cVar2.f14c = cVar;
        cVar.f15d = cVar2;
        this.f9b = cVar;
        return cVar;
    }

    public b<K, V>.d b() {
        b<K, V>.d dVar = new d();
        this.f10c.put(dVar, false);
        return dVar;
    }

    public Map.Entry<K, V> a() {
        return this.f8a;
    }
}
