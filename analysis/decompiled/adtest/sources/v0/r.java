package v0;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f2478a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Field f2479b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final String f2480c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ Method f2481d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ s0.b0 f2482e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ s0.b0 f2483f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ boolean f2484g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ boolean f2485h;

    public r(String str, Field field, Method method, s0.b0 b0Var, s0.b0 b0Var2, boolean z3, boolean z4) {
        this.f2481d = method;
        this.f2482e = b0Var;
        this.f2483f = b0Var2;
        this.f2484g = z3;
        this.f2485h = z4;
        this.f2478a = str;
        this.f2479b = field;
        this.f2480c = field.getName();
    }

    public final void a(a1.d dVar, Object obj) throws IllegalAccessException {
        Object objInvoke;
        Method method = this.f2481d;
        if (method != null) {
            try {
                objInvoke = method.invoke(obj, null);
            } catch (InvocationTargetException e4) {
                throw new s0.r("Accessor " + x0.c.d(method, false) + " threw exception", e4.getCause());
            }
        } else {
            objInvoke = this.f2479b.get(obj);
        }
        if (objInvoke == obj) {
            return;
        }
        dVar.J(this.f2478a);
        this.f2482e.c(dVar, objInvoke);
    }
}
