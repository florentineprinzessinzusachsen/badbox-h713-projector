package u0;

import java.io.Serializable;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import java.util.Objects;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class f implements GenericArrayType, Serializable {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Type f2248d;

    public f(Type type) {
        Objects.requireNonNull(type);
        this.f2248d = i.a(type);
    }

    public final boolean equals(Object obj) {
        return (obj instanceof GenericArrayType) && i.d(this, (GenericArrayType) obj);
    }

    @Override // java.lang.reflect.GenericArrayType
    public final Type getGenericComponentType() {
        return this.f2248d;
    }

    public final int hashCode() {
        return this.f2248d.hashCode();
    }

    public final String toString() {
        return i.l(this.f2248d) + "[]";
    }
}
