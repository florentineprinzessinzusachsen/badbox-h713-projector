package b.b.a.z;

import b.b.a.y.b;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/* JADX INFO: compiled from: TypeToken.java */
/* JADX INFO: loaded from: classes.dex */
public class a<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final Class<? super T> f1706a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final Type f1707b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final int f1708c;

    protected a() {
        this.f1707b = b((Class<?>) a.class);
        this.f1706a = (Class<? super T>) b.e(this.f1707b);
        this.f1708c = this.f1707b.hashCode();
    }

    static Type b(Class<?> cls) {
        Type genericSuperclass = cls.getGenericSuperclass();
        if (genericSuperclass instanceof Class) {
            throw new RuntimeException("Missing type parameter.");
        }
        return b.b(((ParameterizedType) genericSuperclass).getActualTypeArguments()[0]);
    }

    public final Class<? super T> a() {
        return this.f1706a;
    }

    public final boolean equals(Object obj) {
        return (obj instanceof a) && b.a(this.f1707b, ((a) obj).f1707b);
    }

    public final int hashCode() {
        return this.f1708c;
    }

    public final String toString() {
        return b.h(this.f1707b);
    }

    public static a<?> a(Type type) {
        return new a<>(type);
    }

    public static <T> a<T> a(Class<T> cls) {
        return new a<>(cls);
    }

    public static a<?> a(Type type, Type... typeArr) {
        return new a<>(b.a((Type) null, type, typeArr));
    }

    a(Type type) {
        b.b.a.y.a.a(type);
        this.f1707b = b.b(type);
        this.f1706a = (Class<? super T>) b.e(this.f1707b);
        this.f1708c = this.f1707b.hashCode();
    }

    public final Type b() {
        return this.f1707b;
    }

    public static a<?> b(Type type) {
        return new a<>(b.a(type));
    }
}
