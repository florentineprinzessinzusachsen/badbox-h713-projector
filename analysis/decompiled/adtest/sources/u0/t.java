package u0;

import java.lang.reflect.Method;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class t extends x {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Method f2287b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Object f2288c;

    public t(Method method, Object obj) {
        this.f2287b = method;
        this.f2288c = obj;
    }

    @Override // u0.x
    public final Object a(Class cls) {
        String strA = c.a(cls);
        if (strA == null) {
            return this.f2287b.invoke(this.f2288c, cls);
        }
        throw new AssertionError("UnsafeAllocator is used for non-instantiable type: ".concat(strA));
    }
}
