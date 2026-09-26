package b.b.a.y;

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

/* JADX INFO: compiled from: LinkedTreeMap.java */
/* JADX INFO: loaded from: classes.dex */
public final class h<K, V> extends AbstractMap<K, V> implements Serializable {
    private static final Comparator<Comparable> h = new a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    Comparator<? super K> f1598a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    e<K, V> f1599b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    int f1600c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    int f1601d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    final e<K, V> f1602e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private h<K, V>.b f1603f;
    private h<K, V>.c g;

    /* JADX INFO: compiled from: LinkedTreeMap.java */
    static class a implements Comparator<Comparable> {
        a() {
        }

        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(Comparable comparable, Comparable comparable2) {
            return comparable.compareTo(comparable2);
        }
    }

    /* JADX INFO: compiled from: LinkedTreeMap.java */
    class b extends AbstractSet<Map.Entry<K, V>> {

        /* JADX INFO: compiled from: LinkedTreeMap.java */
        class a extends h<K, V>.d<Map.Entry<K, V>> {
            a(b bVar) {
                super();
            }

            @Override // java.util.Iterator
            public Map.Entry<K, V> next() {
                return a();
            }
        }

        b() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            h.this.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            return (obj instanceof Map.Entry) && h.this.a((Map.Entry<?, ?>) obj) != null;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<Map.Entry<K, V>> iterator() {
            return new a(this);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            e<K, V> eVarA;
            if (!(obj instanceof Map.Entry) || (eVarA = h.this.a((Map.Entry<?, ?>) obj)) == null) {
                return false;
            }
            h.this.a((e) eVarA, true);
            return true;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return h.this.f1600c;
        }
    }

    /* JADX INFO: compiled from: LinkedTreeMap.java */
    final class c extends AbstractSet<K> {

        /* JADX INFO: compiled from: LinkedTreeMap.java */
        class a extends h<K, V>.d<K> {
            a(c cVar) {
                super();
            }

            @Override // java.util.Iterator
            public K next() {
                return a().f1615f;
            }
        }

        c() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            h.this.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            return h.this.containsKey(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<K> iterator() {
            return new a(this);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            return h.this.b(obj) != null;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return h.this.f1600c;
        }
    }

    /* JADX INFO: compiled from: LinkedTreeMap.java */
    private abstract class d<T> implements Iterator<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        e<K, V> f1606a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        e<K, V> f1607b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f1608c;

        d() {
            h hVar = h.this;
            this.f1606a = hVar.f1602e.f1613d;
            this.f1607b = null;
            this.f1608c = hVar.f1601d;
        }

        final e<K, V> a() {
            e<K, V> eVar = this.f1606a;
            h hVar = h.this;
            if (eVar == hVar.f1602e) {
                throw new NoSuchElementException();
            }
            if (hVar.f1601d != this.f1608c) {
                throw new ConcurrentModificationException();
            }
            this.f1606a = eVar.f1613d;
            this.f1607b = eVar;
            return eVar;
        }

        @Override // java.util.Iterator
        public final boolean hasNext() {
            return this.f1606a != h.this.f1602e;
        }

        @Override // java.util.Iterator
        public final void remove() {
            e<K, V> eVar = this.f1607b;
            if (eVar == null) {
                throw new IllegalStateException();
            }
            h.this.a((e) eVar, true);
            this.f1607b = null;
            this.f1608c = h.this.f1601d;
        }
    }

    public h() {
        this(h);
    }

    e<K, V> a(K k, boolean z) {
        int iCompareTo;
        e<K, V> eVar;
        Comparator<? super K> comparator = this.f1598a;
        e<K, V> eVar2 = this.f1599b;
        if (eVar2 != null) {
            Comparable comparable = comparator == h ? (Comparable) k : null;
            while (true) {
                iCompareTo = comparable != null ? comparable.compareTo(eVar2.f1615f) : comparator.compare(k, eVar2.f1615f);
                if (iCompareTo == 0) {
                    return eVar2;
                }
                e<K, V> eVar3 = iCompareTo < 0 ? eVar2.f1611b : eVar2.f1612c;
                if (eVar3 == null) {
                    break;
                }
                eVar2 = eVar3;
            }
        } else {
            iCompareTo = 0;
        }
        if (!z) {
            return null;
        }
        e<K, V> eVar4 = this.f1602e;
        if (eVar2 != null) {
            eVar = new e<>(eVar2, k, eVar4, eVar4.f1614e);
            if (iCompareTo < 0) {
                eVar2.f1611b = eVar;
            } else {
                eVar2.f1612c = eVar;
            }
            b(eVar2, true);
        } else {
            if (comparator == h && !(k instanceof Comparable)) {
                throw new ClassCastException(k.getClass().getName() + " is not Comparable");
            }
            eVar = new e<>(eVar2, k, eVar4, eVar4.f1614e);
            this.f1599b = eVar;
        }
        this.f1600c++;
        this.f1601d++;
        return eVar;
    }

    e<K, V> b(Object obj) {
        e<K, V> eVarA = a(obj);
        if (eVarA != null) {
            a((e) eVarA, true);
        }
        return eVarA;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public void clear() {
        this.f1599b = null;
        this.f1600c = 0;
        this.f1601d++;
        e<K, V> eVar = this.f1602e;
        eVar.f1614e = eVar;
        eVar.f1613d = eVar;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsKey(Object obj) {
        return a(obj) != null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<Map.Entry<K, V>> entrySet() {
        h<K, V>.b bVar = this.f1603f;
        if (bVar != null) {
            return bVar;
        }
        h<K, V>.b bVar2 = new b();
        this.f1603f = bVar2;
        return bVar2;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V get(Object obj) {
        e<K, V> eVarA = a(obj);
        if (eVarA != null) {
            return eVarA.g;
        }
        return null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<K> keySet() {
        h<K, V>.c cVar = this.g;
        if (cVar != null) {
            return cVar;
        }
        h<K, V>.c cVar2 = new c();
        this.g = cVar2;
        return cVar2;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V put(K k, V v) {
        if (k == null) {
            throw new NullPointerException("key == null");
        }
        e<K, V> eVarA = a((Object) k, true);
        V v2 = eVarA.g;
        eVarA.g = v;
        return v2;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V remove(Object obj) {
        e<K, V> eVarB = b(obj);
        if (eVarB != null) {
            return eVarB.g;
        }
        return null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public int size() {
        return this.f1600c;
    }

    public h(Comparator<? super K> comparator) {
        this.f1600c = 0;
        this.f1601d = 0;
        this.f1602e = new e<>();
        this.f1598a = comparator == null ? h : comparator;
    }

    /* JADX INFO: compiled from: LinkedTreeMap.java */
    static final class e<K, V> implements Map.Entry<K, V> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        e<K, V> f1610a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        e<K, V> f1611b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        e<K, V> f1612c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        e<K, V> f1613d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        e<K, V> f1614e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        final K f1615f;
        V g;
        int h;

        e() {
            this.f1615f = null;
            this.f1614e = this;
            this.f1613d = this;
        }

        public e<K, V> a() {
            e<K, V> eVar = this;
            for (e<K, V> eVar2 = this.f1611b; eVar2 != null; eVar2 = eVar2.f1611b) {
                eVar = eVar2;
            }
            return eVar;
        }

        public e<K, V> b() {
            e<K, V> eVar = this;
            for (e<K, V> eVar2 = this.f1612c; eVar2 != null; eVar2 = eVar2.f1612c) {
                eVar = eVar2;
            }
            return eVar;
        }

        @Override // java.util.Map.Entry
        public boolean equals(Object obj) {
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            K k = this.f1615f;
            if (k == null) {
                if (entry.getKey() != null) {
                    return false;
                }
            } else if (!k.equals(entry.getKey())) {
                return false;
            }
            V v = this.g;
            if (v == null) {
                if (entry.getValue() != null) {
                    return false;
                }
            } else if (!v.equals(entry.getValue())) {
                return false;
            }
            return true;
        }

        @Override // java.util.Map.Entry
        public K getKey() {
            return this.f1615f;
        }

        @Override // java.util.Map.Entry
        public V getValue() {
            return this.g;
        }

        @Override // java.util.Map.Entry
        public int hashCode() {
            K k = this.f1615f;
            int iHashCode = k == null ? 0 : k.hashCode();
            V v = this.g;
            return iHashCode ^ (v != null ? v.hashCode() : 0);
        }

        @Override // java.util.Map.Entry
        public V setValue(V v) {
            V v2 = this.g;
            this.g = v;
            return v2;
        }

        public String toString() {
            return this.f1615f + "=" + this.g;
        }

        e(e<K, V> eVar, K k, e<K, V> eVar2, e<K, V> eVar3) {
            this.f1610a = eVar;
            this.f1615f = k;
            this.h = 1;
            this.f1613d = eVar2;
            this.f1614e = eVar3;
            eVar3.f1613d = this;
            eVar2.f1614e = this;
        }
    }

    private void b(e<K, V> eVar, boolean z) {
        while (eVar != null) {
            e<K, V> eVar2 = eVar.f1611b;
            e<K, V> eVar3 = eVar.f1612c;
            int i = eVar2 != null ? eVar2.h : 0;
            int i2 = eVar3 != null ? eVar3.h : 0;
            int i3 = i - i2;
            if (i3 == -2) {
                e<K, V> eVar4 = eVar3.f1611b;
                e<K, V> eVar5 = eVar3.f1612c;
                int i4 = (eVar4 != null ? eVar4.h : 0) - (eVar5 != null ? eVar5.h : 0);
                if (i4 != -1 && (i4 != 0 || z)) {
                    b((e) eVar3);
                    a((e) eVar);
                } else {
                    a((e) eVar);
                }
                if (z) {
                    return;
                }
            } else if (i3 == 2) {
                e<K, V> eVar6 = eVar2.f1611b;
                e<K, V> eVar7 = eVar2.f1612c;
                int i5 = (eVar6 != null ? eVar6.h : 0) - (eVar7 != null ? eVar7.h : 0);
                if (i5 != 1 && (i5 != 0 || z)) {
                    a((e) eVar2);
                    b((e) eVar);
                } else {
                    b((e) eVar);
                }
                if (z) {
                    return;
                }
            } else if (i3 == 0) {
                eVar.h = i + 1;
                if (z) {
                    return;
                }
            } else {
                eVar.h = Math.max(i, i2) + 1;
                if (!z) {
                    return;
                }
            }
            eVar = eVar.f1610a;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    e<K, V> a(Object obj) {
        if (obj == 0) {
            return null;
        }
        try {
            return a(obj, false);
        } catch (ClassCastException unused) {
            return null;
        }
    }

    e<K, V> a(Map.Entry<?, ?> entry) {
        e<K, V> eVarA = a(entry.getKey());
        if (eVarA != null && a(eVarA.g, entry.getValue())) {
            return eVarA;
        }
        return null;
    }

    private boolean a(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    void a(e<K, V> eVar, boolean z) {
        int i;
        if (z) {
            e<K, V> eVar2 = eVar.f1614e;
            eVar2.f1613d = eVar.f1613d;
            eVar.f1613d.f1614e = eVar2;
        }
        e<K, V> eVar3 = eVar.f1611b;
        e<K, V> eVar4 = eVar.f1612c;
        e<K, V> eVar5 = eVar.f1610a;
        int i2 = 0;
        if (eVar3 != null && eVar4 != null) {
            e<K, V> eVarB = eVar3.h > eVar4.h ? eVar3.b() : eVar4.a();
            a((e) eVarB, false);
            e<K, V> eVar6 = eVar.f1611b;
            if (eVar6 != null) {
                i = eVar6.h;
                eVarB.f1611b = eVar6;
                eVar6.f1610a = eVarB;
                eVar.f1611b = null;
            } else {
                i = 0;
            }
            e<K, V> eVar7 = eVar.f1612c;
            if (eVar7 != null) {
                i2 = eVar7.h;
                eVarB.f1612c = eVar7;
                eVar7.f1610a = eVarB;
                eVar.f1612c = null;
            }
            eVarB.h = Math.max(i, i2) + 1;
            a((e) eVar, (e) eVarB);
            return;
        }
        if (eVar3 != null) {
            a((e) eVar, (e) eVar3);
            eVar.f1611b = null;
        } else if (eVar4 != null) {
            a((e) eVar, (e) eVar4);
            eVar.f1612c = null;
        } else {
            a((e) eVar, (e) null);
        }
        b(eVar5, false);
        this.f1600c--;
        this.f1601d++;
    }

    private void b(e<K, V> eVar) {
        e<K, V> eVar2 = eVar.f1611b;
        e<K, V> eVar3 = eVar.f1612c;
        e<K, V> eVar4 = eVar2.f1611b;
        e<K, V> eVar5 = eVar2.f1612c;
        eVar.f1611b = eVar5;
        if (eVar5 != null) {
            eVar5.f1610a = eVar;
        }
        a((e) eVar, (e) eVar2);
        eVar2.f1612c = eVar;
        eVar.f1610a = eVar2;
        eVar.h = Math.max(eVar3 != null ? eVar3.h : 0, eVar5 != null ? eVar5.h : 0) + 1;
        eVar2.h = Math.max(eVar.h, eVar4 != null ? eVar4.h : 0) + 1;
    }

    private void a(e<K, V> eVar, e<K, V> eVar2) {
        e<K, V> eVar3 = eVar.f1610a;
        eVar.f1610a = null;
        if (eVar2 != null) {
            eVar2.f1610a = eVar3;
        }
        if (eVar3 != null) {
            if (eVar3.f1611b == eVar) {
                eVar3.f1611b = eVar2;
                return;
            } else {
                eVar3.f1612c = eVar2;
                return;
            }
        }
        this.f1599b = eVar2;
    }

    private void a(e<K, V> eVar) {
        e<K, V> eVar2 = eVar.f1611b;
        e<K, V> eVar3 = eVar.f1612c;
        e<K, V> eVar4 = eVar3.f1611b;
        e<K, V> eVar5 = eVar3.f1612c;
        eVar.f1612c = eVar4;
        if (eVar4 != null) {
            eVar4.f1610a = eVar;
        }
        a((e) eVar, (e) eVar3);
        eVar3.f1611b = eVar;
        eVar.f1610a = eVar3;
        eVar.h = Math.max(eVar2 != null ? eVar2.h : 0, eVar4 != null ? eVar4.h : 0) + 1;
        eVar3.h = Math.max(eVar.h, eVar5 != null ? eVar5.h : 0) + 1;
    }
}
