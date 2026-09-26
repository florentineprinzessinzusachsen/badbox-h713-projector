package u0;

import java.io.Serializable;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Objects;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class g implements ParameterizedType, Serializable {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Type f2249d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Type f2250e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Type[] f2251f;

    public g(Type type, Class cls, Type... typeArr) {
        Objects.requireNonNull(cls);
        if (type == null && !Modifier.isStatic(cls.getModifiers()) && cls.getDeclaringClass() != null) {
            throw new IllegalArgumentException("Must specify owner type for " + cls);
        }
        this.f2249d = type == null ? null : i.a(type);
        this.f2250e = i.a(cls);
        Type[] typeArr2 = (Type[]) typeArr.clone();
        this.f2251f = typeArr2;
        int length = typeArr2.length;
        for (int i4 = 0; i4 < length; i4++) {
            Objects.requireNonNull(this.f2251f[i4]);
            i.b(this.f2251f[i4]);
            Type[] typeArr3 = this.f2251f;
            typeArr3[i4] = i.a(typeArr3[i4]);
        }
    }

    public final boolean equals(Object obj) {
        return (obj instanceof ParameterizedType) && i.d(this, (ParameterizedType) obj);
    }

    @Override // java.lang.reflect.ParameterizedType
    public final Type[] getActualTypeArguments() {
        return (Type[]) this.f2251f.clone();
    }

    @Override // java.lang.reflect.ParameterizedType
    public final Type getOwnerType() {
        return this.f2249d;
    }

    @Override // java.lang.reflect.ParameterizedType
    public final Type getRawType() {
        return this.f2250e;
    }

    public final int hashCode() {
        int iHashCode = Arrays.hashCode(this.f2251f) ^ this.f2250e.hashCode();
        Type type = this.f2249d;
        return iHashCode ^ (type != null ? type.hashCode() : 0);
    }

    public final String toString() {
        Type[] typeArr = this.f2251f;
        int length = typeArr.length;
        Type type = this.f2250e;
        if (length == 0) {
            return i.l(type);
        }
        StringBuilder sb = new StringBuilder((length + 1) * 30);
        sb.append(i.l(type));
        sb.append("<");
        sb.append(i.l(typeArr[0]));
        for (int i4 = 1; i4 < length; i4++) {
            sb.append(", ");
            sb.append(i.l(typeArr[i4]));
        }
        sb.append(">");
        return sb.toString();
    }
}
