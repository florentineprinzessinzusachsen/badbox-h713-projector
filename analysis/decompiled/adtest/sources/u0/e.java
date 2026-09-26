package u0;

import d0.l0;
import java.lang.reflect.Modifier;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import s0.b0;
import s0.c0;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class e implements c0, Cloneable {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final e f2245f = new e();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final List f2246d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final List f2247e;

    public e() {
        List list = Collections.EMPTY_LIST;
        this.f2246d = list;
        this.f2247e = list;
    }

    @Override // s0.c0
    public final b0 a(s0.n nVar, z0.a aVar) {
        Class cls = aVar.f2778a;
        boolean zB = b(cls, true);
        boolean zB2 = b(cls, false);
        if (zB || zB2) {
            return new d(this, zB2, zB, nVar, aVar);
        }
        return null;
    }

    public final boolean b(Class cls, boolean z3) {
        if (!z3 && !Enum.class.isAssignableFrom(cls)) {
            l0 l0Var = x0.c.f2671a;
            if (!Modifier.isStatic(cls.getModifiers()) && (cls.isAnonymousClass() || cls.isLocalClass())) {
                return true;
            }
        }
        Iterator it = (z3 ? this.f2246d : this.f2247e).iterator();
        if (!it.hasNext()) {
            return false;
        }
        it.next().getClass();
        throw new ClassCastException();
    }

    public final Object clone() {
        try {
            return (e) super.clone();
        } catch (CloneNotSupportedException e4) {
            throw new AssertionError(e4);
        }
    }
}
