package c.a.b0.b;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: Functions.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final c.a.a0.n<Object, Object> f1757a = new v();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Runnable f1758b = new q();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final c.a.a0.a f1759c = new n();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    static final c.a.a0.f<Object> f1760d = new o();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final c.a.a0.f<Throwable> f1761e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    static final c.a.a0.p<Object> f1762f;
    static final c.a.a0.p<Object> g;
    static final Callable<Object> h;
    static final Comparator<Object> i;

    /* JADX INFO: renamed from: c.a.b0.b.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: Functions.java */
    static final class C0044a<T> implements c.a.a0.f<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.a0.a f1763a;

        C0044a(c.a.a0.a aVar) {
            this.f1763a = aVar;
        }

        @Override // c.a.a0.f
        public void a(T t) {
            this.f1763a.run();
        }
    }

    /* JADX INFO: compiled from: Functions.java */
    static final class a0 implements Comparator<Object> {
        a0() {
        }

        @Override // java.util.Comparator
        public int compare(Object obj, Object obj2) {
            return ((Comparable) obj).compareTo(obj2);
        }
    }

    /* JADX INFO: compiled from: Functions.java */
    static final class b<T1, T2, R> implements c.a.a0.n<Object[], R> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.a0.c<? super T1, ? super T2, ? extends R> f1764a;

        b(c.a.a0.c<? super T1, ? super T2, ? extends R> cVar) {
            this.f1764a = cVar;
        }

        @Override // c.a.a0.n
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public R apply(Object[] objArr) {
            if (objArr.length == 2) {
                return this.f1764a.a(objArr[0], objArr[1]);
            }
            throw new IllegalArgumentException("Array of size 2 expected but got " + objArr.length);
        }
    }

    /* JADX INFO: compiled from: Functions.java */
    static final class b0<T> implements c.a.a0.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.a0.f<? super c.a.k<T>> f1765a;

        b0(c.a.a0.f<? super c.a.k<T>> fVar) {
            this.f1765a = fVar;
        }

        @Override // c.a.a0.a
        public void run() {
            this.f1765a.a(c.a.k.f());
        }
    }

    /* JADX INFO: compiled from: Functions.java */
    static final class c<T1, T2, T3, R> implements c.a.a0.n<Object[], R> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.a0.g<T1, T2, T3, R> f1766a;

        c(c.a.a0.g<T1, T2, T3, R> gVar) {
            this.f1766a = gVar;
        }

        @Override // c.a.a0.n
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public R apply(Object[] objArr) {
            if (objArr.length == 3) {
                return this.f1766a.a((T1) objArr[0], (T2) objArr[1], (T3) objArr[2]);
            }
            throw new IllegalArgumentException("Array of size 3 expected but got " + objArr.length);
        }
    }

    /* JADX INFO: compiled from: Functions.java */
    static final class c0<T> implements c.a.a0.f<Throwable> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.a0.f<? super c.a.k<T>> f1767a;

        c0(c.a.a0.f<? super c.a.k<T>> fVar) {
            this.f1767a = fVar;
        }

        @Override // c.a.a0.f
        public void a(Throwable th) {
            this.f1767a.a(c.a.k.a(th));
        }
    }

    /* JADX INFO: compiled from: Functions.java */
    static final class d<T1, T2, T3, T4, R> implements c.a.a0.n<Object[], R> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.a0.h<T1, T2, T3, T4, R> f1768a;

        d(c.a.a0.h<T1, T2, T3, T4, R> hVar) {
            this.f1768a = hVar;
        }

        @Override // c.a.a0.n
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public R apply(Object[] objArr) {
            if (objArr.length != 4) {
                throw new IllegalArgumentException("Array of size 4 expected but got " + objArr.length);
            }
            return this.f1768a.a((T1) objArr[0], (T2) objArr[1], (T3) objArr[2], (T4) objArr[3]);
        }
    }

    /* JADX INFO: compiled from: Functions.java */
    static final class d0<T> implements c.a.a0.f<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.a0.f<? super c.a.k<T>> f1769a;

        d0(c.a.a0.f<? super c.a.k<T>> fVar) {
            this.f1769a = fVar;
        }

        @Override // c.a.a0.f
        public void a(T t) {
            this.f1769a.a(c.a.k.a(t));
        }
    }

    /* JADX INFO: compiled from: Functions.java */
    static final class e<T1, T2, T3, T4, T5, R> implements c.a.a0.n<Object[], R> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final c.a.a0.i<T1, T2, T3, T4, T5, R> f1770a;

        e(c.a.a0.i<T1, T2, T3, T4, T5, R> iVar) {
            this.f1770a = iVar;
        }

        @Override // c.a.a0.n
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public R apply(Object[] objArr) {
            if (objArr.length != 5) {
                throw new IllegalArgumentException("Array of size 5 expected but got " + objArr.length);
            }
            return this.f1770a.a((T1) objArr[0], (T2) objArr[1], (T3) objArr[2], (T4) objArr[3], (T5) objArr[4]);
        }
    }

    /* JADX INFO: compiled from: Functions.java */
    static final class e0 implements Callable<Object> {
        e0() {
        }

        @Override // java.util.concurrent.Callable
        public Object call() {
            return null;
        }
    }

    /* JADX INFO: compiled from: Functions.java */
    static final class f<T1, T2, T3, T4, T5, T6, R> implements c.a.a0.n<Object[], R> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.a0.j<T1, T2, T3, T4, T5, T6, R> f1771a;

        f(c.a.a0.j<T1, T2, T3, T4, T5, T6, R> jVar) {
            this.f1771a = jVar;
        }

        @Override // c.a.a0.n
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public R apply(Object[] objArr) {
            if (objArr.length != 6) {
                throw new IllegalArgumentException("Array of size 6 expected but got " + objArr.length);
            }
            return this.f1771a.a((T1) objArr[0], (T2) objArr[1], (T3) objArr[2], (T4) objArr[3], (T5) objArr[4], (T6) objArr[5]);
        }
    }

    /* JADX INFO: compiled from: Functions.java */
    static final class f0 implements c.a.a0.f<Throwable> {
        f0() {
        }

        @Override // c.a.a0.f
        public void a(Throwable th) {
            c.a.e0.a.b(new c.a.z.d(th));
        }
    }

    /* JADX INFO: compiled from: Functions.java */
    static final class g<T1, T2, T3, T4, T5, T6, T7, R> implements c.a.a0.n<Object[], R> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.a0.k<T1, T2, T3, T4, T5, T6, T7, R> f1772a;

        g(c.a.a0.k<T1, T2, T3, T4, T5, T6, T7, R> kVar) {
            this.f1772a = kVar;
        }

        @Override // c.a.a0.n
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public R apply(Object[] objArr) {
            if (objArr.length != 7) {
                throw new IllegalArgumentException("Array of size 7 expected but got " + objArr.length);
            }
            return this.f1772a.a((T1) objArr[0], (T2) objArr[1], (T3) objArr[2], (T4) objArr[3], (T5) objArr[4], (T6) objArr[5], (T7) objArr[6]);
        }
    }

    /* JADX INFO: compiled from: Functions.java */
    static final class g0<T> implements c.a.a0.n<T, c.a.f0.c<T>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final TimeUnit f1773a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final c.a.t f1774b;

        g0(TimeUnit timeUnit, c.a.t tVar) {
            this.f1773a = timeUnit;
            this.f1774b = tVar;
        }

        @Override // c.a.a0.n
        public c.a.f0.c<T> apply(T t) {
            return new c.a.f0.c<>(t, this.f1774b.a(this.f1773a), this.f1773a);
        }
    }

    /* JADX INFO: compiled from: Functions.java */
    static final class h<T1, T2, T3, T4, T5, T6, T7, T8, R> implements c.a.a0.n<Object[], R> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.a0.l<T1, T2, T3, T4, T5, T6, T7, T8, R> f1775a;

        h(c.a.a0.l<T1, T2, T3, T4, T5, T6, T7, T8, R> lVar) {
            this.f1775a = lVar;
        }

        @Override // c.a.a0.n
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public R apply(Object[] objArr) {
            if (objArr.length != 8) {
                throw new IllegalArgumentException("Array of size 8 expected but got " + objArr.length);
            }
            return this.f1775a.a((T1) objArr[0], (T2) objArr[1], (T3) objArr[2], (T4) objArr[3], (T5) objArr[4], (T6) objArr[5], (T7) objArr[6], (T8) objArr[7]);
        }
    }

    /* JADX INFO: compiled from: Functions.java */
    static final class h0<K, T> implements c.a.a0.b<Map<K, T>, T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final c.a.a0.n<? super T, ? extends K> f1776a;

        h0(c.a.a0.n<? super T, ? extends K> nVar) {
            this.f1776a = nVar;
        }

        @Override // c.a.a0.b
        public void a(Map<K, T> map, T t) {
            map.put(this.f1776a.apply(t), t);
        }
    }

    /* JADX INFO: compiled from: Functions.java */
    static final class i<T1, T2, T3, T4, T5, T6, T7, T8, T9, R> implements c.a.a0.n<Object[], R> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.a0.m<T1, T2, T3, T4, T5, T6, T7, T8, T9, R> f1777a;

        i(c.a.a0.m<T1, T2, T3, T4, T5, T6, T7, T8, T9, R> mVar) {
            this.f1777a = mVar;
        }

        @Override // c.a.a0.n
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public R apply(Object[] objArr) {
            if (objArr.length != 9) {
                throw new IllegalArgumentException("Array of size 9 expected but got " + objArr.length);
            }
            return this.f1777a.a((T1) objArr[0], (T2) objArr[1], (T3) objArr[2], (T4) objArr[3], (T5) objArr[4], (T6) objArr[5], (T7) objArr[6], (T8) objArr[7], (T9) objArr[8]);
        }
    }

    /* JADX INFO: compiled from: Functions.java */
    static final class i0<K, V, T> implements c.a.a0.b<Map<K, V>, T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final c.a.a0.n<? super T, ? extends V> f1778a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final c.a.a0.n<? super T, ? extends K> f1779b;

        i0(c.a.a0.n<? super T, ? extends V> nVar, c.a.a0.n<? super T, ? extends K> nVar2) {
            this.f1778a = nVar;
            this.f1779b = nVar2;
        }

        @Override // c.a.a0.b
        public void a(Map<K, V> map, T t) {
            map.put(this.f1779b.apply(t), this.f1778a.apply(t));
        }
    }

    /* JADX INFO: compiled from: Functions.java */
    static final class j<T> implements Callable<List<T>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final int f1780a;

        j(int i) {
            this.f1780a = i;
        }

        @Override // java.util.concurrent.Callable
        public List<T> call() {
            return new ArrayList(this.f1780a);
        }
    }

    /* JADX INFO: compiled from: Functions.java */
    static final class j0<K, V, T> implements c.a.a0.b<Map<K, Collection<V>>, T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final c.a.a0.n<? super K, ? extends Collection<? super V>> f1781a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final c.a.a0.n<? super T, ? extends V> f1782b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final c.a.a0.n<? super T, ? extends K> f1783c;

        j0(c.a.a0.n<? super K, ? extends Collection<? super V>> nVar, c.a.a0.n<? super T, ? extends V> nVar2, c.a.a0.n<? super T, ? extends K> nVar3) {
            this.f1781a = nVar;
            this.f1782b = nVar2;
            this.f1783c = nVar3;
        }

        @Override // c.a.a0.b
        public void a(Map<K, Collection<V>> map, T t) {
            K kApply = this.f1783c.apply(t);
            Collection<? super V> collectionApply = (Collection) map.get(kApply);
            if (collectionApply == null) {
                collectionApply = this.f1781a.apply(kApply);
                map.put(kApply, collectionApply);
            }
            collectionApply.add(this.f1782b.apply(t));
        }
    }

    /* JADX INFO: compiled from: Functions.java */
    static final class k<T> implements c.a.a0.p<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.a0.e f1784a;

        k(c.a.a0.e eVar) {
            this.f1784a = eVar;
        }

        @Override // c.a.a0.p
        public boolean a(T t) {
            return !this.f1784a.a();
        }
    }

    /* JADX INFO: compiled from: Functions.java */
    static final class k0 implements c.a.a0.p<Object> {
        k0() {
        }

        @Override // c.a.a0.p
        public boolean a(Object obj) {
            return true;
        }
    }

    /* JADX INFO: compiled from: Functions.java */
    static final class l<T, U> implements c.a.a0.n<T, U> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final Class<U> f1785a;

        l(Class<U> cls) {
            this.f1785a = cls;
        }

        @Override // c.a.a0.n
        public U apply(T t) {
            return this.f1785a.cast(t);
        }
    }

    /* JADX INFO: compiled from: Functions.java */
    static final class m<T, U> implements c.a.a0.p<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final Class<U> f1786a;

        m(Class<U> cls) {
            this.f1786a = cls;
        }

        @Override // c.a.a0.p
        public boolean a(T t) {
            return this.f1786a.isInstance(t);
        }
    }

    /* JADX INFO: compiled from: Functions.java */
    static final class n implements c.a.a0.a {
        n() {
        }

        @Override // c.a.a0.a
        public void run() {
        }

        public String toString() {
            return "EmptyAction";
        }
    }

    /* JADX INFO: compiled from: Functions.java */
    static final class o implements c.a.a0.f<Object> {
        o() {
        }

        @Override // c.a.a0.f
        public void a(Object obj) {
        }

        public String toString() {
            return "EmptyConsumer";
        }
    }

    /* JADX INFO: compiled from: Functions.java */
    static final class p implements c.a.a0.o {
        p() {
        }
    }

    /* JADX INFO: compiled from: Functions.java */
    static final class q implements Runnable {
        q() {
        }

        @Override // java.lang.Runnable
        public void run() {
        }

        public String toString() {
            return "EmptyRunnable";
        }
    }

    /* JADX INFO: compiled from: Functions.java */
    static final class r<T> implements c.a.a0.p<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final T f1787a;

        r(T t) {
            this.f1787a = t;
        }

        @Override // c.a.a0.p
        public boolean a(T t) {
            return c.a.b0.b.b.a(t, this.f1787a);
        }
    }

    /* JADX INFO: compiled from: Functions.java */
    static final class s implements c.a.a0.f<Throwable> {
        s() {
        }

        @Override // c.a.a0.f
        public void a(Throwable th) {
            c.a.e0.a.b(th);
        }
    }

    /* JADX INFO: compiled from: Functions.java */
    static final class t implements c.a.a0.p<Object> {
        t() {
        }

        @Override // c.a.a0.p
        public boolean a(Object obj) {
            return false;
        }
    }

    /* JADX INFO: compiled from: Functions.java */
    enum u implements Callable<Set<Object>> {
        INSTANCE;

        @Override // java.util.concurrent.Callable
        public Set<Object> call() {
            return new HashSet();
        }
    }

    /* JADX INFO: compiled from: Functions.java */
    static final class v implements c.a.a0.n<Object, Object> {
        v() {
        }

        @Override // c.a.a0.n
        public Object apply(Object obj) {
            return obj;
        }

        public String toString() {
            return "IdentityFunction";
        }
    }

    /* JADX INFO: compiled from: Functions.java */
    static final class w<T, U> implements Callable<U>, c.a.a0.n<T, U> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final U f1790a;

        w(U u) {
            this.f1790a = u;
        }

        @Override // c.a.a0.n
        public U apply(T t) {
            return this.f1790a;
        }

        @Override // java.util.concurrent.Callable
        public U call() {
            return this.f1790a;
        }
    }

    /* JADX INFO: compiled from: Functions.java */
    static final class x<T> implements c.a.a0.n<List<T>, List<T>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final Comparator<? super T> f1791a;

        x(Comparator<? super T> comparator) {
            this.f1791a = comparator;
        }

        public List<T> a(List<T> list) {
            Collections.sort(list, this.f1791a);
            return list;
        }

        @Override // c.a.a0.n
        public /* bridge */ /* synthetic */ Object apply(Object obj) {
            List<T> list = (List) obj;
            a(list);
            return list;
        }
    }

    /* JADX INFO: compiled from: Functions.java */
    static final class y implements c.a.a0.f<f.a.c> {
        y() {
        }

        @Override // c.a.a0.f
        public void a(f.a.c cVar) {
            cVar.c(Long.MAX_VALUE);
        }
    }

    /* JADX INFO: compiled from: Functions.java */
    enum z implements Comparator<Object> {
        INSTANCE;

        @Override // java.util.Comparator
        public int compare(Object obj, Object obj2) {
            return ((Comparable) obj).compareTo(obj2);
        }
    }

    static {
        new s();
        f1761e = new f0();
        new p();
        f1762f = new k0();
        g = new t();
        h = new e0();
        i = new a0();
        new y();
    }

    public static <T1, T2, R> c.a.a0.n<Object[], R> a(c.a.a0.c<? super T1, ? super T2, ? extends R> cVar) {
        c.a.b0.b.b.a(cVar, "f is null");
        return new b(cVar);
    }

    public static <T> c.a.a0.p<T> b() {
        return (c.a.a0.p<T>) f1762f;
    }

    public static <T, U> c.a.a0.n<T, U> c(U u2) {
        return new w(u2);
    }

    public static <T> c.a.a0.f<T> d() {
        return (c.a.a0.f<T>) f1760d;
    }

    public static <T> c.a.a0.n<T, T> e() {
        return (c.a.a0.n<T, T>) f1757a;
    }

    public static <T> Comparator<T> f() {
        return z.INSTANCE;
    }

    public static <T> Comparator<T> g() {
        return (Comparator<T>) i;
    }

    public static <T> Callable<T> h() {
        return (Callable<T>) h;
    }

    public static <T> Callable<T> b(T t2) {
        return new w(t2);
    }

    public static <T> Callable<Set<T>> c() {
        return u.INSTANCE;
    }

    public static <T1, T2, T3, R> c.a.a0.n<Object[], R> a(c.a.a0.g<T1, T2, T3, R> gVar) {
        c.a.b0.b.b.a(gVar, "f is null");
        return new c(gVar);
    }

    public static <T> c.a.a0.f<Throwable> b(c.a.a0.f<? super c.a.k<T>> fVar) {
        return new c0(fVar);
    }

    public static <T> c.a.a0.f<T> c(c.a.a0.f<? super c.a.k<T>> fVar) {
        return new d0(fVar);
    }

    public static <T, U> c.a.a0.p<T> b(Class<U> cls) {
        return new m(cls);
    }

    public static <T1, T2, T3, T4, R> c.a.a0.n<Object[], R> a(c.a.a0.h<T1, T2, T3, T4, R> hVar) {
        c.a.b0.b.b.a(hVar, "f is null");
        return new d(hVar);
    }

    public static <T1, T2, T3, T4, T5, R> c.a.a0.n<Object[], R> a(c.a.a0.i<T1, T2, T3, T4, T5, R> iVar) {
        c.a.b0.b.b.a(iVar, "f is null");
        return new e(iVar);
    }

    public static <T1, T2, T3, T4, T5, T6, R> c.a.a0.n<Object[], R> a(c.a.a0.j<T1, T2, T3, T4, T5, T6, R> jVar) {
        c.a.b0.b.b.a(jVar, "f is null");
        return new f(jVar);
    }

    public static <T1, T2, T3, T4, T5, T6, T7, R> c.a.a0.n<Object[], R> a(c.a.a0.k<T1, T2, T3, T4, T5, T6, T7, R> kVar) {
        c.a.b0.b.b.a(kVar, "f is null");
        return new g(kVar);
    }

    public static <T1, T2, T3, T4, T5, T6, T7, T8, R> c.a.a0.n<Object[], R> a(c.a.a0.l<T1, T2, T3, T4, T5, T6, T7, T8, R> lVar) {
        c.a.b0.b.b.a(lVar, "f is null");
        return new h(lVar);
    }

    public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, R> c.a.a0.n<Object[], R> a(c.a.a0.m<T1, T2, T3, T4, T5, T6, T7, T8, T9, R> mVar) {
        c.a.b0.b.b.a(mVar, "f is null");
        return new i(mVar);
    }

    public static <T> c.a.a0.p<T> a() {
        return (c.a.a0.p<T>) g;
    }

    public static <T, U> c.a.a0.n<T, U> a(Class<U> cls) {
        return new l(cls);
    }

    public static <T> Callable<List<T>> a(int i2) {
        return new j(i2);
    }

    public static <T> c.a.a0.p<T> a(T t2) {
        return new r(t2);
    }

    public static <T> c.a.a0.a a(c.a.a0.f<? super c.a.k<T>> fVar) {
        return new b0(fVar);
    }

    public static <T> c.a.a0.f<T> a(c.a.a0.a aVar) {
        return new C0044a(aVar);
    }

    public static <T> c.a.a0.p<T> a(c.a.a0.e eVar) {
        return new k(eVar);
    }

    public static <T> c.a.a0.n<T, c.a.f0.c<T>> a(TimeUnit timeUnit, c.a.t tVar) {
        return new g0(timeUnit, tVar);
    }

    public static <T, K> c.a.a0.b<Map<K, T>, T> a(c.a.a0.n<? super T, ? extends K> nVar) {
        return new h0(nVar);
    }

    public static <T, K, V> c.a.a0.b<Map<K, V>, T> a(c.a.a0.n<? super T, ? extends K> nVar, c.a.a0.n<? super T, ? extends V> nVar2) {
        return new i0(nVar2, nVar);
    }

    public static <T, K, V> c.a.a0.b<Map<K, Collection<V>>, T> a(c.a.a0.n<? super T, ? extends K> nVar, c.a.a0.n<? super T, ? extends V> nVar2, c.a.a0.n<? super K, ? extends Collection<? super V>> nVar3) {
        return new j0(nVar3, nVar2, nVar);
    }

    public static <T> c.a.a0.n<List<T>, List<T>> a(Comparator<? super T> comparator) {
        return new x(comparator);
    }
}
