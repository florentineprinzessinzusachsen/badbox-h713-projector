package u0;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.util.Objects;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class h implements WildcardType, Serializable {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Type f2252d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Type f2253e;

    public h(Type[] typeArr, Type[] typeArr2) {
        if (typeArr2.length > 1) {
            throw new IllegalArgumentException("At most one lower bound is supported");
        }
        if (typeArr.length != 1) {
            throw new IllegalArgumentException("Exactly one upper bound must be specified");
        }
        if (typeArr2.length != 1) {
            Objects.requireNonNull(typeArr[0]);
            i.b(typeArr[0]);
            this.f2253e = null;
            this.f2252d = i.a(typeArr[0]);
            return;
        }
        Objects.requireNonNull(typeArr2[0]);
        i.b(typeArr2[0]);
        if (typeArr[0] != Object.class) {
            throw new IllegalArgumentException("When lower bound is specified, upper bound must be Object");
        }
        this.f2253e = i.a(typeArr2[0]);
        this.f2252d = Object.class;
    }

    public final boolean equals(Object obj) {
        return (obj instanceof WildcardType) && i.d(this, (WildcardType) obj);
    }

    @Override // java.lang.reflect.WildcardType
    public final Type[] getLowerBounds() {
        Type type = this.f2253e;
        return type != null ? new Type[]{type} : i.f2254a;
    }

    @Override // java.lang.reflect.WildcardType
    public final Type[] getUpperBounds() {
        return new Type[]{this.f2252d};
    }

    public final int hashCode() {
        Type type = this.f2253e;
        return (type != null ? type.hashCode() + 31 : 1) ^ (this.f2252d.hashCode() + 31);
    }

    public final String toString() {
        Type type = this.f2253e;
        if (type != null) {
            return "? super " + i.l(type);
        }
        Type type2 = this.f2252d;
        if (type2 == Object.class) {
            return "?";
        }
        return "? extends " + i.l(type2);
    }
}
