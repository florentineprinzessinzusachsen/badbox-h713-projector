package a3;

import d0.l0;
import java.util.ArrayList;
import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class e {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final e f119c = new e(v1.j.I0(new ArrayList()), null);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Set f120a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final l0 f121b;

    public e(Set set, l0 l0Var) {
        this.f120a = set;
        this.f121b = l0Var;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof e)) {
            return false;
        }
        e eVar = (e) obj;
        return j2.i.a(eVar.f120a, this.f120a) && j2.i.a(eVar.f121b, this.f121b);
    }

    public final int hashCode() {
        int iHashCode = (this.f120a.hashCode() + 1517) * 41;
        l0 l0Var = this.f121b;
        return iHashCode + (l0Var != null ? l0Var.hashCode() : 0);
    }
}
