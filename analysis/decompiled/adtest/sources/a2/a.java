package a2;

import d0.l0;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public abstract class a implements y1.c, d, Serializable {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final y1.c f40d;

    public a(y1.c cVar) {
        this.f40d = cVar;
    }

    @Override // a2.d
    public d e() {
        y1.c cVar = this.f40d;
        if (cVar instanceof d) {
            return (d) cVar;
        }
        return null;
    }

    public y1.c i(Object obj, y1.c cVar) {
        throw new UnsupportedOperationException("create(Any?;Continuation) has not been overridden");
    }

    @Override // y1.c
    public final void j(Object obj) {
        y1.c cVar = this;
        while (true) {
            a aVar = (a) cVar;
            y1.c cVar2 = aVar.f40d;
            j2.i.b(cVar2);
            try {
                obj = aVar.l(obj);
                if (obj == z1.a.f2781d) {
                    return;
                }
            } catch (Throwable th) {
                obj = l0.l(th);
            }
            aVar.p();
            if (!(cVar2 instanceof a)) {
                cVar2.j(obj);
                return;
            }
            cVar = cVar2;
        }
    }

    public StackTraceElement k() {
        int iIntValue;
        String strC;
        Method method;
        Object objInvoke;
        Method method2;
        Object objInvoke2;
        e eVar = (e) getClass().getAnnotation(e.class);
        String str = null;
        if (eVar == null || eVar.v() < 1) {
            return null;
        }
        try {
            Field declaredField = getClass().getDeclaredField("label");
            declaredField.setAccessible(true);
            Object obj = declaredField.get(this);
            Integer num = obj instanceof Integer ? (Integer) obj : null;
            iIntValue = (num != null ? num.intValue() : 0) - 1;
        } catch (Exception unused) {
            iIntValue = -1;
        }
        int i4 = iIntValue >= 0 ? eVar.l()[iIntValue] : -1;
        f fVar = g.f49b;
        f fVar2 = g.f48a;
        if (fVar == null) {
            try {
                f fVar3 = new f(Class.class.getDeclaredMethod("getModule", null), getClass().getClassLoader().loadClass("java.lang.Module").getDeclaredMethod("getDescriptor", null), getClass().getClassLoader().loadClass("java.lang.module.ModuleDescriptor").getDeclaredMethod("name", null), 0);
                g.f49b = fVar3;
                fVar = fVar3;
            } catch (Exception unused2) {
                g.f49b = fVar2;
                fVar = fVar2;
            }
        }
        if (fVar != fVar2 && (method = (Method) fVar.f45e) != null && (objInvoke = method.invoke(getClass(), null)) != null && (method2 = (Method) fVar.f46f) != null && (objInvoke2 = method2.invoke(objInvoke, null)) != null) {
            Method method3 = (Method) fVar.f47g;
            Object objInvoke3 = method3 != null ? method3.invoke(objInvoke2, null) : null;
            if (objInvoke3 instanceof String) {
                str = (String) objInvoke3;
            }
        }
        if (str == null) {
            strC = eVar.c();
        } else {
            strC = str + '/' + eVar.c();
        }
        return new StackTraceElement(strC, eVar.m(), eVar.f(), i4);
    }

    public abstract Object l(Object obj);

    public String toString() {
        StringBuilder sb = new StringBuilder("Continuation at ");
        Object objK = k();
        if (objK == null) {
            objK = getClass().getName();
        }
        sb.append(objK);
        return sb.toString();
    }

    public void p() {
    }
}
