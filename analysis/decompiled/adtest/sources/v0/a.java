package v0;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public class a implements s0.c0 {
    @Override // s0.c0
    public final s0.b0 a(s0.n nVar, z0.a aVar) {
        Type type = aVar.f2779b;
        boolean z3 = type instanceof GenericArrayType;
        if (!z3 && (!(type instanceof Class) || !((Class) type).isArray())) {
            return null;
        }
        Type genericComponentType = z3 ? ((GenericArrayType) type).getGenericComponentType() : ((Class) type).getComponentType();
        return new b(nVar, nVar.c(new z0.a(genericComponentType)), u0.i.g(genericComponentType));
    }
}
