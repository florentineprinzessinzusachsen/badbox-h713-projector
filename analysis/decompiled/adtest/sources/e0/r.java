package e0;

import android.content.Context;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class r implements x.c, u0.q {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f679d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ Object f680e;

    public /* synthetic */ r(int i4, Object obj) {
        this.f679d = i4;
        this.f680e = obj;
    }

    @Override // u0.q
    public Object a() {
        int i4 = this.f679d;
        Object obj = this.f680e;
        switch (i4) {
            case 1:
                Constructor constructor = (Constructor) obj;
                try {
                    return constructor.newInstance(null);
                } catch (IllegalAccessException e4) {
                    d0.l0 l0Var = x0.c.f2671a;
                    throw new RuntimeException("Unexpected IllegalAccessException occurred (Gson 2.13.2). Certain ReflectionAccessFilter features require Java >= 9 to work correctly. If you are not using ReflectionAccessFilter, report this to the Gson maintainers.", e4);
                } catch (InstantiationException e5) {
                    throw new RuntimeException("Failed to invoke constructor '" + x0.c.b(constructor) + "' with no args", e5);
                } catch (InvocationTargetException e6) {
                    throw new RuntimeException("Failed to invoke constructor '" + x0.c.b(constructor) + "' with no args", e6.getCause());
                }
            default:
                Class cls = (Class) obj;
                try {
                    return u0.x.f2292a.a(cls);
                } catch (Exception e7) {
                    throw new RuntimeException("Unable to create instance of " + cls + ". Registering an InstanceCreator or a TypeAdapter for this type, or adding a no-args constructor may fix this problem.", e7);
                }
        }
    }

    @Override // x.c
    public x.d d(x.b bVar) {
        Context context = (Context) this.f680e;
        String str = bVar.f2663b;
        e3.w wVar = bVar.f2664c;
        j2.i.e(wVar, "callback");
        if (str == null || str.length() == 0) {
            throw new IllegalArgumentException("Must set a non-null database name to a configuration that uses the no backup directory.");
        }
        return new y.h(context, str, wVar, true, true);
    }
}
