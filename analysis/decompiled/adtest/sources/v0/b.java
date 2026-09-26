package v0;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class b extends s0.b0 {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final a f2420c = new a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Class f2421a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final n f2422b;

    public b(s0.n nVar, s0.b0 b0Var, Class cls) {
        this.f2422b = new n(nVar, b0Var, cls);
        this.f2421a = cls;
    }

    @Override // s0.b0
    public final Object b(a1.b bVar) throws IOException {
        if (bVar.f0() == 9) {
            bVar.b0();
            return null;
        }
        ArrayList arrayList = new ArrayList();
        bVar.b();
        while (bVar.S()) {
            arrayList.add(this.f2422b.f2469c.b(bVar));
        }
        bVar.A();
        int size = arrayList.size();
        Class cls = this.f2421a;
        if (!cls.isPrimitive()) {
            return arrayList.toArray((Object[]) Array.newInstance((Class<?>) cls, size));
        }
        Object objNewInstance = Array.newInstance((Class<?>) cls, size);
        for (int i4 = 0; i4 < size; i4++) {
            Array.set(objNewInstance, i4, arrayList.get(i4));
        }
        return objNewInstance;
    }

    @Override // s0.b0
    public final void c(a1.d dVar, Object obj) throws IOException {
        if (obj == null) {
            dVar.S();
            return;
        }
        dVar.c();
        int length = Array.getLength(obj);
        for (int i4 = 0; i4 < length; i4++) {
            this.f2422b.c(dVar, Array.get(obj, i4));
        }
        dVar.A();
    }
}
