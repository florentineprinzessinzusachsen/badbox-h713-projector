package u0;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class n extends AbstractSet {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f2263d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ Map f2264e;

    public /* synthetic */ n(Map map, int i4) {
        this.f2263d = i4;
        this.f2264e = map;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public void clear() {
        switch (this.f2263d) {
            case 0:
                ((p) this.f2264e).clear();
                break;
            case 1:
                ((p) this.f2264e).clear();
                break;
            default:
                super.clear();
                break;
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean contains(Object obj) {
        o oVarA;
        switch (this.f2263d) {
            case 0:
                if (!(obj instanceof Map.Entry)) {
                    return false;
                }
                p pVar = (p) this.f2264e;
                Map.Entry entry = (Map.Entry) obj;
                Object key = entry.getKey();
                o oVar = null;
                if (key != null) {
                    try {
                        oVarA = pVar.a(key, false);
                    } catch (ClassCastException unused) {
                        oVarA = null;
                    }
                    break;
                } else {
                    oVarA = null;
                }
                if (oVarA != null && Objects.equals(oVarA.f2272k, entry.getValue())) {
                    oVar = oVarA;
                }
                return oVar != null;
            case 1:
                return ((p) this.f2264e).containsKey(obj);
            default:
                return super.contains(obj);
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        switch (this.f2263d) {
            case 0:
                return new m((p) this.f2264e, 0);
            case 1:
                return new m((p) this.f2264e, 1);
            default:
                return new e.c((e.e) this.f2264e);
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean remove(Object obj) {
        o oVarA;
        switch (this.f2263d) {
            case 0:
                p pVar = (p) this.f2264e;
                if (!(obj instanceof Map.Entry)) {
                    return false;
                }
                Map.Entry entry = (Map.Entry) obj;
                Object key = entry.getKey();
                o oVar = null;
                if (key != null) {
                    try {
                        oVarA = pVar.a(key, false);
                    } catch (ClassCastException unused) {
                        oVarA = null;
                    }
                    break;
                } else {
                    oVarA = null;
                }
                if (oVarA != null && Objects.equals(oVarA.f2272k, entry.getValue())) {
                    oVar = oVarA;
                }
                if (oVar == null) {
                    return false;
                }
                pVar.c(oVar, true);
                return true;
            case 1:
                p pVar2 = (p) this.f2264e;
                o oVarA2 = null;
                if (obj != null) {
                    try {
                        oVarA2 = pVar2.a(obj, false);
                        break;
                    } catch (ClassCastException unused2) {
                    }
                }
                if (oVarA2 != null) {
                    pVar2.c(oVarA2, true);
                }
                return oVarA2 != null;
            default:
                return super.remove(obj);
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        switch (this.f2263d) {
            case 0:
                return ((p) this.f2264e).f2278g;
            case 1:
                return ((p) this.f2264e).f2278g;
            default:
                return ((e.e) this.f2264e).f566f;
        }
    }
}
