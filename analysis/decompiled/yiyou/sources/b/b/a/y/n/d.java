package b.b.a.y.n;

import b.b.a.s;
import b.b.a.v;
import b.b.a.w;

/* JADX INFO: compiled from: JsonAdapterAnnotationTypeAdapterFactory.java */
/* JADX INFO: loaded from: classes.dex */
public final class d implements w {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final b.b.a.y.c f1633a;

    public d(b.b.a.y.c cVar) {
        this.f1633a = cVar;
    }

    @Override // b.b.a.w
    public <T> v<T> a(b.b.a.f fVar, b.b.a.z.a<T> aVar) {
        b.b.a.x.b bVar = (b.b.a.x.b) aVar.a().getAnnotation(b.b.a.x.b.class);
        if (bVar == null) {
            return null;
        }
        return (v<T>) a(this.f1633a, fVar, aVar, bVar);
    }

    v<?> a(b.b.a.y.c cVar, b.b.a.f fVar, b.b.a.z.a<?> aVar, b.b.a.x.b bVar) {
        v<?> lVar;
        Object objA = cVar.a(b.b.a.z.a.a((Class) bVar.value())).a();
        if (objA instanceof v) {
            lVar = (v) objA;
        } else if (objA instanceof w) {
            lVar = ((w) objA).a(fVar, aVar);
        } else {
            boolean z = objA instanceof s;
            if (!z && !(objA instanceof b.b.a.k)) {
                throw new IllegalArgumentException("Invalid attempt to bind an instance of " + objA.getClass().getName() + " as a @JsonAdapter for " + aVar.toString() + ". @JsonAdapter value must be a TypeAdapter, TypeAdapterFactory, JsonSerializer or JsonDeserializer.");
            }
            lVar = new l<>(z ? (s) objA : null, objA instanceof b.b.a.k ? (b.b.a.k) objA : null, fVar, aVar, null);
        }
        return (lVar == null || !bVar.nullSafe()) ? lVar : lVar.a();
    }
}
