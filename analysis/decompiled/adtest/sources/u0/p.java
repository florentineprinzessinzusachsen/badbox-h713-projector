package u0;

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Comparator;
import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class p extends AbstractMap implements Serializable {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final l f2274l = new l(0);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final boolean f2276e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public o f2277f;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final o f2280i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public n f2281j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public n f2282k;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f2278g = 0;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f2279h = 0;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Comparator f2275d = f2274l;

    public p(boolean z3) {
        this.f2276e = z3;
        this.f2280i = new o(z3);
    }

    public final o a(Object obj, boolean z3) {
        int iCompareTo;
        o oVar;
        o oVar2 = this.f2277f;
        l lVar = f2274l;
        Comparator comparator = this.f2275d;
        if (oVar2 != null) {
            Comparable comparable = comparator == lVar ? (Comparable) obj : null;
            while (true) {
                Object obj2 = oVar2.f2270i;
                iCompareTo = comparable != null ? comparable.compareTo(obj2) : comparator.compare(obj, obj2);
                if (iCompareTo == 0) {
                    return oVar2;
                }
                o oVar3 = iCompareTo < 0 ? oVar2.f2266e : oVar2.f2267f;
                if (oVar3 == null) {
                    break;
                }
                oVar2 = oVar3;
            }
        } else {
            iCompareTo = 0;
        }
        o oVar4 = oVar2;
        if (!z3) {
            return null;
        }
        o oVar5 = this.f2280i;
        if (oVar4 != null) {
            oVar = new o(this.f2276e, oVar4, obj, oVar5, oVar5.f2269h);
            if (iCompareTo < 0) {
                oVar4.f2266e = oVar;
            } else {
                oVar4.f2267f = oVar;
            }
            b(oVar4, true);
        } else {
            if (comparator == lVar && !(obj instanceof Comparable)) {
                throw new ClassCastException(obj.getClass().getName().concat(" is not Comparable"));
            }
            oVar = new o(this.f2276e, oVar4, obj, oVar5, oVar5.f2269h);
            this.f2277f = oVar;
        }
        this.f2278g++;
        this.f2279h++;
        return oVar;
    }

    public final void b(o oVar, boolean z3) {
        while (oVar != null) {
            o oVar2 = oVar.f2266e;
            o oVar3 = oVar.f2267f;
            int i4 = oVar2 != null ? oVar2.f2273l : 0;
            int i5 = oVar3 != null ? oVar3.f2273l : 0;
            int i6 = i4 - i5;
            if (i6 == -2) {
                o oVar4 = oVar3.f2266e;
                o oVar5 = oVar3.f2267f;
                int i7 = (oVar4 != null ? oVar4.f2273l : 0) - (oVar5 != null ? oVar5.f2273l : 0);
                if (i7 == -1 || (i7 == 0 && !z3)) {
                    e(oVar);
                } else {
                    f(oVar3);
                    e(oVar);
                }
                if (z3) {
                    return;
                }
            } else if (i6 == 2) {
                o oVar6 = oVar2.f2266e;
                o oVar7 = oVar2.f2267f;
                int i8 = (oVar6 != null ? oVar6.f2273l : 0) - (oVar7 != null ? oVar7.f2273l : 0);
                if (i8 == 1 || (i8 == 0 && !z3)) {
                    f(oVar);
                } else {
                    e(oVar2);
                    f(oVar);
                }
                if (z3) {
                    return;
                }
            } else if (i6 == 0) {
                oVar.f2273l = i4 + 1;
                if (z3) {
                    return;
                }
            } else {
                oVar.f2273l = Math.max(i4, i5) + 1;
                if (!z3) {
                    return;
                }
            }
            oVar = oVar.f2265d;
        }
    }

    public final void c(o oVar, boolean z3) {
        o oVar2;
        o oVar3;
        int i4;
        if (z3) {
            o oVar4 = oVar.f2269h;
            oVar4.f2268g = oVar.f2268g;
            oVar.f2268g.f2269h = oVar4;
        }
        o oVar5 = oVar.f2266e;
        o oVar6 = oVar.f2267f;
        o oVar7 = oVar.f2265d;
        int i5 = 0;
        if (oVar5 == null || oVar6 == null) {
            if (oVar5 != null) {
                d(oVar, oVar5);
                oVar.f2266e = null;
            } else if (oVar6 != null) {
                d(oVar, oVar6);
                oVar.f2267f = null;
            } else {
                d(oVar, null);
            }
            b(oVar7, false);
            this.f2278g--;
            this.f2279h++;
            return;
        }
        if (oVar5.f2273l > oVar6.f2273l) {
            o oVar8 = oVar5.f2267f;
            while (true) {
                o oVar9 = oVar8;
                oVar3 = oVar5;
                oVar5 = oVar9;
                if (oVar5 == null) {
                    break;
                } else {
                    oVar8 = oVar5.f2267f;
                }
            }
        } else {
            o oVar10 = oVar6.f2266e;
            while (true) {
                oVar2 = oVar6;
                oVar6 = oVar10;
                if (oVar6 == null) {
                    break;
                } else {
                    oVar10 = oVar6.f2266e;
                }
            }
            oVar3 = oVar2;
        }
        c(oVar3, false);
        o oVar11 = oVar.f2266e;
        if (oVar11 != null) {
            i4 = oVar11.f2273l;
            oVar3.f2266e = oVar11;
            oVar11.f2265d = oVar3;
            oVar.f2266e = null;
        } else {
            i4 = 0;
        }
        o oVar12 = oVar.f2267f;
        if (oVar12 != null) {
            i5 = oVar12.f2273l;
            oVar3.f2267f = oVar12;
            oVar12.f2265d = oVar3;
            oVar.f2267f = null;
        }
        oVar3.f2273l = Math.max(i4, i5) + 1;
        d(oVar, oVar3);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final void clear() {
        this.f2277f = null;
        this.f2278g = 0;
        this.f2279h++;
        o oVar = this.f2280i;
        oVar.f2269h = oVar;
        oVar.f2268g = oVar;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean containsKey(Object obj) {
        o oVarA = null;
        if (obj != null) {
            try {
                oVarA = a(obj, false);
            } catch (ClassCastException unused) {
            }
        }
        return oVarA != null;
    }

    public final void d(o oVar, o oVar2) {
        o oVar3 = oVar.f2265d;
        oVar.f2265d = null;
        if (oVar2 != null) {
            oVar2.f2265d = oVar3;
        }
        if (oVar3 == null) {
            this.f2277f = oVar2;
        } else if (oVar3.f2266e == oVar) {
            oVar3.f2266e = oVar2;
        } else {
            oVar3.f2267f = oVar2;
        }
    }

    public final void e(o oVar) {
        o oVar2 = oVar.f2266e;
        o oVar3 = oVar.f2267f;
        o oVar4 = oVar3.f2266e;
        o oVar5 = oVar3.f2267f;
        oVar.f2267f = oVar4;
        if (oVar4 != null) {
            oVar4.f2265d = oVar;
        }
        d(oVar, oVar3);
        oVar3.f2266e = oVar;
        oVar.f2265d = oVar3;
        int iMax = Math.max(oVar2 != null ? oVar2.f2273l : 0, oVar4 != null ? oVar4.f2273l : 0) + 1;
        oVar.f2273l = iMax;
        oVar3.f2273l = Math.max(iMax, oVar5 != null ? oVar5.f2273l : 0) + 1;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Set entrySet() {
        n nVar = this.f2281j;
        if (nVar != null) {
            return nVar;
        }
        n nVar2 = new n(this, 0);
        this.f2281j = nVar2;
        return nVar2;
    }

    public final void f(o oVar) {
        o oVar2 = oVar.f2266e;
        o oVar3 = oVar.f2267f;
        o oVar4 = oVar2.f2266e;
        o oVar5 = oVar2.f2267f;
        oVar.f2266e = oVar5;
        if (oVar5 != null) {
            oVar5.f2265d = oVar;
        }
        d(oVar, oVar2);
        oVar2.f2267f = oVar;
        oVar.f2265d = oVar2;
        int iMax = Math.max(oVar3 != null ? oVar3.f2273l : 0, oVar5 != null ? oVar5.f2273l : 0) + 1;
        oVar.f2273l = iMax;
        oVar2.f2273l = Math.max(iMax, oVar4 != null ? oVar4.f2273l : 0) + 1;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Object get(Object obj) {
        o oVarA;
        if (obj != null) {
            try {
                oVarA = a(obj, false);
            } catch (ClassCastException unused) {
                oVarA = null;
            }
        } else {
            oVarA = null;
        }
        if (oVarA != null) {
            return oVarA.f2272k;
        }
        return null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Set keySet() {
        n nVar = this.f2282k;
        if (nVar != null) {
            return nVar;
        }
        n nVar2 = new n(this, 1);
        this.f2282k = nVar2;
        return nVar2;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Object put(Object obj, Object obj2) {
        if (obj == null) {
            throw new NullPointerException("key == null");
        }
        if (obj2 == null && !this.f2276e) {
            throw new NullPointerException("value == null");
        }
        o oVarA = a(obj, true);
        Object obj3 = oVarA.f2272k;
        oVarA.f2272k = obj2;
        return obj3;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Object remove(Object obj) {
        o oVarA;
        if (obj != null) {
            try {
                oVarA = a(obj, false);
            } catch (ClassCastException unused) {
                oVarA = null;
            }
        } else {
            oVarA = null;
        }
        if (oVarA != null) {
            c(oVarA, true);
        }
        if (oVarA != null) {
            return oVarA.f2272k;
        }
        return null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final int size() {
        return this.f2278g;
    }
}
