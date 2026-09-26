package s0;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import v0.b1;
import v0.r0;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final u0.e f2119a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f2120b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final h f2121c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final HashMap f2122d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final ArrayList f2123e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final ArrayList f2124f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int f2125g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final int f2126h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final boolean f2127i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public i f2128j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final boolean f2129k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final z f2130l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final z f2131m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final ArrayDeque f2132n;

    public o() {
        this.f2119a = u0.e.f2245f;
        this.f2120b = 1;
        this.f2121c = h.f2085d;
        this.f2122d = new HashMap();
        this.f2123e = new ArrayList();
        this.f2124f = new ArrayList();
        i iVar = n.f2096t;
        this.f2125g = 2;
        this.f2126h = 2;
        this.f2127i = true;
        this.f2128j = n.f2096t;
        this.f2129k = true;
        this.f2130l = n.f2098v;
        this.f2131m = n.f2099w;
        this.f2132n = new ArrayDeque();
    }

    public final n a() {
        r0 r0Var;
        r0 r0Var2;
        ArrayList arrayList = this.f2123e;
        int size = arrayList.size();
        ArrayList arrayList2 = this.f2124f;
        ArrayList arrayList3 = new ArrayList(arrayList2.size() + size + 3);
        arrayList3.addAll(arrayList);
        Collections.reverse(arrayList3);
        ArrayList arrayList4 = new ArrayList(arrayList2);
        Collections.reverse(arrayList4);
        arrayList3.addAll(arrayList4);
        boolean z3 = y0.f.f2716a;
        int i4 = this.f2125g;
        int i5 = this.f2126h;
        if (i4 != 2 || i5 != 2) {
            v0.h hVar = new v0.h(v0.g.f2454b, i4, i5);
            r0 r0Var3 = b1.f2423a;
            r0 r0Var4 = new r0(Date.class, hVar, 0);
            if (z3) {
                y0.e eVar = y0.f.f2718c;
                eVar.getClass();
                r0Var = new r0(eVar.f2455a, new v0.h(eVar, i4, i5), 0);
                y0.e eVar2 = y0.f.f2717b;
                eVar2.getClass();
                r0Var2 = new r0(eVar2.f2455a, new v0.h(eVar2, i4, i5), 0);
            } else {
                r0Var = null;
                r0Var2 = null;
            }
            arrayList3.add(r0Var4);
            if (z3) {
                arrayList3.add(r0Var);
                arrayList3.add(r0Var2);
            }
        }
        return new n(this.f2119a, this.f2121c, new HashMap(this.f2122d), this.f2127i, this.f2128j, this.f2129k, this.f2120b, this.f2125g, this.f2126h, new ArrayList(arrayList), new ArrayList(arrayList2), arrayList3, this.f2130l, this.f2131m, new ArrayList(this.f2132n));
    }

    public o(n nVar) {
        this.f2119a = u0.e.f2245f;
        this.f2120b = 1;
        this.f2121c = h.f2085d;
        HashMap map = new HashMap();
        this.f2122d = map;
        ArrayList arrayList = new ArrayList();
        this.f2123e = arrayList;
        ArrayList arrayList2 = new ArrayList();
        this.f2124f = arrayList2;
        i iVar = n.f2096t;
        this.f2125g = 2;
        this.f2126h = 2;
        this.f2127i = true;
        this.f2128j = n.f2096t;
        this.f2129k = true;
        this.f2130l = n.f2098v;
        this.f2131m = n.f2099w;
        ArrayDeque arrayDeque = new ArrayDeque();
        this.f2132n = arrayDeque;
        this.f2119a = nVar.f2105f;
        this.f2121c = nVar.f2106g;
        map.putAll(nVar.f2107h);
        this.f2127i = nVar.f2108i;
        this.f2128j = nVar.f2109j;
        this.f2120b = nVar.f2118s;
        this.f2125g = nVar.f2111l;
        this.f2126h = nVar.f2112m;
        arrayList.addAll(nVar.f2113n);
        arrayList2.addAll(nVar.f2114o);
        this.f2129k = nVar.f2110k;
        this.f2130l = nVar.f2115p;
        this.f2131m = nVar.f2116q;
        arrayDeque.addAll(nVar.f2117r);
    }
}
