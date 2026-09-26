package b.b.a.y;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

/* JADX INFO: compiled from: ConstructorConstructor.java */
/* JADX INFO: loaded from: classes.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Map<Type, b.b.a.h<?>> f1573a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final b.b.a.y.o.b f1574b = b.b.a.y.o.b.a();

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: compiled from: ConstructorConstructor.java */
    class a<T> implements b.b.a.y.i<T> {
        a(c cVar) {
        }

        @Override // b.b.a.y.i
        public T a() {
            return (T) new ConcurrentHashMap();
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: compiled from: ConstructorConstructor.java */
    class b<T> implements b.b.a.y.i<T> {
        b(c cVar) {
        }

        @Override // b.b.a.y.i
        public T a() {
            return (T) new TreeMap();
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: renamed from: b.b.a.y.c$c, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ConstructorConstructor.java */
    class C0039c<T> implements b.b.a.y.i<T> {
        C0039c(c cVar) {
        }

        @Override // b.b.a.y.i
        public T a() {
            return (T) new LinkedHashMap();
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: compiled from: ConstructorConstructor.java */
    class d<T> implements b.b.a.y.i<T> {
        d(c cVar) {
        }

        @Override // b.b.a.y.i
        public T a() {
            return (T) new b.b.a.y.h();
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: compiled from: ConstructorConstructor.java */
    class e<T> implements b.b.a.y.i<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final b.b.a.y.m f1575a = b.b.a.y.m.a();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ Class f1576b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ Type f1577c;

        e(c cVar, Class cls, Type type) {
            this.f1576b = cls;
            this.f1577c = type;
        }

        @Override // b.b.a.y.i
        public T a() {
            try {
                return (T) this.f1575a.a(this.f1576b);
            } catch (Exception e2) {
                throw new RuntimeException("Unable to invoke no-args constructor for " + this.f1577c + ". Registering an InstanceCreator with Gson for this type may fix this problem.", e2);
            }
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: compiled from: ConstructorConstructor.java */
    class f<T> implements b.b.a.y.i<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ b.b.a.h f1578a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ Type f1579b;

        f(c cVar, b.b.a.h hVar, Type type) {
            this.f1578a = hVar;
            this.f1579b = type;
        }

        @Override // b.b.a.y.i
        public T a() {
            return (T) this.f1578a.a(this.f1579b);
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: compiled from: ConstructorConstructor.java */
    class g<T> implements b.b.a.y.i<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ b.b.a.h f1580a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ Type f1581b;

        g(c cVar, b.b.a.h hVar, Type type) {
            this.f1580a = hVar;
            this.f1581b = type;
        }

        @Override // b.b.a.y.i
        public T a() {
            return (T) this.f1580a.a(this.f1581b);
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: compiled from: ConstructorConstructor.java */
    class h<T> implements b.b.a.y.i<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Constructor f1582a;

        h(c cVar, Constructor constructor) {
            this.f1582a = constructor;
        }

        @Override // b.b.a.y.i
        public T a() {
            try {
                return (T) this.f1582a.newInstance(null);
            } catch (IllegalAccessException e2) {
                throw new AssertionError(e2);
            } catch (InstantiationException e3) {
                throw new RuntimeException("Failed to invoke " + this.f1582a + " with no args", e3);
            } catch (InvocationTargetException e4) {
                throw new RuntimeException("Failed to invoke " + this.f1582a + " with no args", e4.getTargetException());
            }
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: compiled from: ConstructorConstructor.java */
    class i<T> implements b.b.a.y.i<T> {
        i(c cVar) {
        }

        @Override // b.b.a.y.i
        public T a() {
            return (T) new TreeSet();
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: compiled from: ConstructorConstructor.java */
    class j<T> implements b.b.a.y.i<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Type f1583a;

        j(c cVar, Type type) {
            this.f1583a = type;
        }

        @Override // b.b.a.y.i
        public T a() {
            Type type = this.f1583a;
            if (!(type instanceof ParameterizedType)) {
                throw new b.b.a.m("Invalid EnumSet type: " + this.f1583a.toString());
            }
            Type type2 = ((ParameterizedType) type).getActualTypeArguments()[0];
            if (type2 instanceof Class) {
                return (T) EnumSet.noneOf((Class) type2);
            }
            throw new b.b.a.m("Invalid EnumSet type: " + this.f1583a.toString());
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: compiled from: ConstructorConstructor.java */
    class k<T> implements b.b.a.y.i<T> {
        k(c cVar) {
        }

        @Override // b.b.a.y.i
        public T a() {
            return (T) new LinkedHashSet();
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: compiled from: ConstructorConstructor.java */
    class l<T> implements b.b.a.y.i<T> {
        l(c cVar) {
        }

        @Override // b.b.a.y.i
        public T a() {
            return (T) new ArrayDeque();
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: compiled from: ConstructorConstructor.java */
    class m<T> implements b.b.a.y.i<T> {
        m(c cVar) {
        }

        @Override // b.b.a.y.i
        public T a() {
            return (T) new ArrayList();
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: compiled from: ConstructorConstructor.java */
    class n<T> implements b.b.a.y.i<T> {
        n(c cVar) {
        }

        @Override // b.b.a.y.i
        public T a() {
            return (T) new ConcurrentSkipListMap();
        }
    }

    public c(Map<Type, b.b.a.h<?>> map) {
        this.f1573a = map;
    }

    private <T> b.b.a.y.i<T> b(Type type, Class<? super T> cls) {
        return new e(this, cls, type);
    }

    public <T> b.b.a.y.i<T> a(b.b.a.z.a<T> aVar) {
        Type typeB = aVar.b();
        Class<? super T> clsA = aVar.a();
        b.b.a.h<?> hVar = this.f1573a.get(typeB);
        if (hVar != null) {
            return new f(this, hVar, typeB);
        }
        b.b.a.h<?> hVar2 = this.f1573a.get(clsA);
        if (hVar2 != null) {
            return new g(this, hVar2, typeB);
        }
        b.b.a.y.i<T> iVarA = a(clsA);
        if (iVarA != null) {
            return iVarA;
        }
        b.b.a.y.i<T> iVarA2 = a(typeB, clsA);
        return iVarA2 != null ? iVarA2 : b(typeB, clsA);
    }

    public String toString() {
        return this.f1573a.toString();
    }

    private <T> b.b.a.y.i<T> a(Class<? super T> cls) {
        try {
            Constructor<? super T> declaredConstructor = cls.getDeclaredConstructor(new Class[0]);
            if (!declaredConstructor.isAccessible()) {
                this.f1574b.a(declaredConstructor);
            }
            return new h(this, declaredConstructor);
        } catch (NoSuchMethodException unused) {
            return null;
        }
    }

    private <T> b.b.a.y.i<T> a(Type type, Class<? super T> cls) {
        if (Collection.class.isAssignableFrom(cls)) {
            if (SortedSet.class.isAssignableFrom(cls)) {
                return new i(this);
            }
            if (EnumSet.class.isAssignableFrom(cls)) {
                return new j(this, type);
            }
            if (Set.class.isAssignableFrom(cls)) {
                return new k(this);
            }
            if (Queue.class.isAssignableFrom(cls)) {
                return new l(this);
            }
            return new m(this);
        }
        if (!Map.class.isAssignableFrom(cls)) {
            return null;
        }
        if (ConcurrentNavigableMap.class.isAssignableFrom(cls)) {
            return new n(this);
        }
        if (ConcurrentMap.class.isAssignableFrom(cls)) {
            return new a(this);
        }
        if (SortedMap.class.isAssignableFrom(cls)) {
            return new b(this);
        }
        if ((type instanceof ParameterizedType) && !String.class.isAssignableFrom(b.b.a.z.a.a(((ParameterizedType) type).getActualTypeArguments()[0]).a())) {
            return new C0039c(this);
        }
        return new d(this);
    }
}
