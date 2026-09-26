package c.a.b0.j;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: compiled from: VolatileSizeArrayList.java */
/* JADX INFO: loaded from: classes.dex */
public final class s<T> extends AtomicInteger implements List<T>, RandomAccess {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final ArrayList<T> f3108a = new ArrayList<>();

    @Override // java.util.List, java.util.Collection
    public boolean add(T t) {
        boolean zAdd = this.f3108a.add(t);
        lazySet(this.f3108a.size());
        return zAdd;
    }

    @Override // java.util.List, java.util.Collection
    public boolean addAll(Collection<? extends T> collection) {
        boolean zAddAll = this.f3108a.addAll(collection);
        lazySet(this.f3108a.size());
        return zAddAll;
    }

    @Override // java.util.List, java.util.Collection
    public void clear() {
        this.f3108a.clear();
        lazySet(0);
    }

    @Override // java.util.List, java.util.Collection
    public boolean contains(Object obj) {
        return this.f3108a.contains(obj);
    }

    @Override // java.util.List, java.util.Collection
    public boolean containsAll(Collection<?> collection) {
        return this.f3108a.containsAll(collection);
    }

    @Override // java.util.List, java.util.Collection
    public boolean equals(Object obj) {
        return obj instanceof s ? this.f3108a.equals(((s) obj).f3108a) : this.f3108a.equals(obj);
    }

    @Override // java.util.List
    public T get(int i) {
        return this.f3108a.get(i);
    }

    @Override // java.util.List, java.util.Collection
    public int hashCode() {
        return this.f3108a.hashCode();
    }

    @Override // java.util.List
    public int indexOf(Object obj) {
        return this.f3108a.indexOf(obj);
    }

    @Override // java.util.List, java.util.Collection
    public boolean isEmpty() {
        return get() == 0;
    }

    @Override // java.util.List, java.util.Collection, java.lang.Iterable
    public Iterator<T> iterator() {
        return this.f3108a.iterator();
    }

    @Override // java.util.List
    public int lastIndexOf(Object obj) {
        return this.f3108a.lastIndexOf(obj);
    }

    @Override // java.util.List
    public ListIterator<T> listIterator() {
        return this.f3108a.listIterator();
    }

    @Override // java.util.List, java.util.Collection
    public boolean remove(Object obj) {
        boolean zRemove = this.f3108a.remove(obj);
        lazySet(this.f3108a.size());
        return zRemove;
    }

    @Override // java.util.List, java.util.Collection
    public boolean removeAll(Collection<?> collection) {
        boolean zRemoveAll = this.f3108a.removeAll(collection);
        lazySet(this.f3108a.size());
        return zRemoveAll;
    }

    @Override // java.util.List, java.util.Collection
    public boolean retainAll(Collection<?> collection) {
        boolean zRetainAll = this.f3108a.retainAll(collection);
        lazySet(this.f3108a.size());
        return zRetainAll;
    }

    @Override // java.util.List
    public T set(int i, T t) {
        return this.f3108a.set(i, t);
    }

    @Override // java.util.List, java.util.Collection
    public int size() {
        return get();
    }

    @Override // java.util.List
    public List<T> subList(int i, int i2) {
        return this.f3108a.subList(i, i2);
    }

    @Override // java.util.List, java.util.Collection
    public Object[] toArray() {
        return this.f3108a.toArray();
    }

    @Override // java.util.concurrent.atomic.AtomicInteger
    public String toString() {
        return this.f3108a.toString();
    }

    @Override // java.util.List
    public ListIterator<T> listIterator(int i) {
        return this.f3108a.listIterator(i);
    }

    @Override // java.util.List, java.util.Collection
    public <E> E[] toArray(E[] eArr) {
        return (E[]) this.f3108a.toArray(eArr);
    }

    @Override // java.util.List
    public void add(int i, T t) {
        this.f3108a.add(i, t);
        lazySet(this.f3108a.size());
    }

    @Override // java.util.List
    public boolean addAll(int i, Collection<? extends T> collection) {
        boolean zAddAll = this.f3108a.addAll(i, collection);
        lazySet(this.f3108a.size());
        return zAddAll;
    }

    @Override // java.util.List
    public T remove(int i) {
        T tRemove = this.f3108a.remove(i);
        lazySet(this.f3108a.size());
        return tRemove;
    }
}
