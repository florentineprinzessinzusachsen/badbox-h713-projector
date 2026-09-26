package v0;

import java.lang.reflect.Field;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class t extends s {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final u0.q f2490b;

    public t(u0.q qVar, u uVar) {
        super(uVar);
        this.f2490b = qVar;
    }

    @Override // v0.s
    public final Object d() {
        return this.f2490b.a();
    }

    @Override // v0.s
    public final void f(Object obj, a1.b bVar, r rVar) throws IllegalAccessException {
        Field field = rVar.f2479b;
        Object objB = rVar.f2483f.b(bVar);
        if (objB == null && rVar.f2484g) {
            return;
        }
        if (!rVar.f2485h) {
            field.set(obj, objB);
            return;
        }
        throw new s0.r("Cannot set value of 'static final' " + x0.c.d(field, false));
    }

    @Override // v0.s
    public final Object e(Object obj) {
        return obj;
    }
}
