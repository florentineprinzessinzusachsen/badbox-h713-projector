package v0;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Map;
import java.util.Properties;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class d implements s0.c0 {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f2452d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final u0.c f2453e;

    public /* synthetic */ d(u0.c cVar, int i4) {
        this.f2452d = i4;
        this.f2453e = cVar;
    }

    @Override // s0.c0
    public final s0.b0 a(s0.n nVar, z0.a aVar) {
        Type[] actualTypeArguments;
        int i4 = this.f2452d;
        u0.c cVar = this.f2453e;
        Type type = Object.class;
        switch (i4) {
            case 0:
                Type type2 = aVar.f2779b;
                Class cls = aVar.f2778a;
                if (!Collection.class.isAssignableFrom(cls)) {
                    return null;
                }
                Type typeH = u0.i.h(type2, cls, Collection.class);
                type = typeH instanceof ParameterizedType ? ((ParameterizedType) typeH).getActualTypeArguments()[0] : Object.class;
                return new c(new n(nVar, nVar.c(new z0.a(type)), type), cVar.b(aVar, false));
            default:
                Type type3 = aVar.f2779b;
                Class cls2 = aVar.f2778a;
                if (!Map.class.isAssignableFrom(cls2)) {
                    return null;
                }
                if (Properties.class.isAssignableFrom(cls2)) {
                    actualTypeArguments = new Type[]{String.class, String.class};
                } else {
                    Type typeH2 = u0.i.h(type3, cls2, Map.class);
                    actualTypeArguments = typeH2 instanceof ParameterizedType ? ((ParameterizedType) typeH2).getActualTypeArguments() : new Type[]{type, type};
                }
                Type type4 = actualTypeArguments[0];
                Type type5 = actualTypeArguments[1];
                return new n(this, new n(nVar, (type4 == Boolean.TYPE || type4 == Boolean.class) ? b1.f2425c : nVar.c(new z0.a(type4)), type4), new n(nVar, nVar.c(new z0.a(type5)), type5), cVar.b(aVar, false));
        }
    }
}
