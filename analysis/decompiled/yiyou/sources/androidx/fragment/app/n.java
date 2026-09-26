package androidx.fragment.app;

import android.graphics.Rect;
import android.os.Build;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.f.t;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: FragmentTransition.java */
/* JADX INFO: loaded from: classes.dex */
class n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final int[] f1309a = {0, 3, 0, 1, 5, 4, 7, 6, 9, 8, 10};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final p f1310b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final p f1311c;

    /* JADX INFO: compiled from: FragmentTransition.java */
    static class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ ArrayList f1312a;

        a(ArrayList arrayList) {
            this.f1312a = arrayList;
        }

        @Override // java.lang.Runnable
        public void run() {
            n.a((ArrayList<View>) this.f1312a, 4);
        }
    }

    /* JADX INFO: compiled from: FragmentTransition.java */
    static class b implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Object f1313a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ p f1314b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ View f1315c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ Fragment f1316d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ ArrayList f1317e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        final /* synthetic */ ArrayList f1318f;
        final /* synthetic */ ArrayList g;
        final /* synthetic */ Object h;

        b(Object obj, p pVar, View view, Fragment fragment, ArrayList arrayList, ArrayList arrayList2, ArrayList arrayList3, Object obj2) {
            this.f1313a = obj;
            this.f1314b = pVar;
            this.f1315c = view;
            this.f1316d = fragment;
            this.f1317e = arrayList;
            this.f1318f = arrayList2;
            this.g = arrayList3;
            this.h = obj2;
        }

        @Override // java.lang.Runnable
        public void run() {
            Object obj = this.f1313a;
            if (obj != null) {
                this.f1314b.b(obj, this.f1315c);
                this.f1318f.addAll(n.a(this.f1314b, this.f1313a, this.f1316d, (ArrayList<View>) this.f1317e, this.f1315c));
            }
            if (this.g != null) {
                if (this.h != null) {
                    ArrayList<View> arrayList = new ArrayList<>();
                    arrayList.add(this.f1315c);
                    this.f1314b.a(this.h, this.g, arrayList);
                }
                this.g.clear();
                this.g.add(this.f1315c);
            }
        }
    }

    /* JADX INFO: compiled from: FragmentTransition.java */
    static class c implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Fragment f1319a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ Fragment f1320b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ boolean f1321c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ a.b.a f1322d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ View f1323e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        final /* synthetic */ p f1324f;
        final /* synthetic */ Rect g;

        c(Fragment fragment, Fragment fragment2, boolean z, a.b.a aVar, View view, p pVar, Rect rect) {
            this.f1319a = fragment;
            this.f1320b = fragment2;
            this.f1321c = z;
            this.f1322d = aVar;
            this.f1323e = view;
            this.f1324f = pVar;
            this.g = rect;
        }

        @Override // java.lang.Runnable
        public void run() {
            n.a(this.f1319a, this.f1320b, this.f1321c, (a.b.a<String, View>) this.f1322d, false);
            View view = this.f1323e;
            if (view != null) {
                this.f1324f.a(view, this.g);
            }
        }
    }

    /* JADX INFO: compiled from: FragmentTransition.java */
    static class d implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ p f1325a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ a.b.a f1326b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ Object f1327c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ e f1328d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ ArrayList f1329e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        final /* synthetic */ View f1330f;
        final /* synthetic */ Fragment g;
        final /* synthetic */ Fragment h;
        final /* synthetic */ boolean i;
        final /* synthetic */ ArrayList j;
        final /* synthetic */ Object k;
        final /* synthetic */ Rect l;

        d(p pVar, a.b.a aVar, Object obj, e eVar, ArrayList arrayList, View view, Fragment fragment, Fragment fragment2, boolean z, ArrayList arrayList2, Object obj2, Rect rect) {
            this.f1325a = pVar;
            this.f1326b = aVar;
            this.f1327c = obj;
            this.f1328d = eVar;
            this.f1329e = arrayList;
            this.f1330f = view;
            this.g = fragment;
            this.h = fragment2;
            this.i = z;
            this.j = arrayList2;
            this.k = obj2;
            this.l = rect;
        }

        @Override // java.lang.Runnable
        public void run() {
            a.b.a<String, View> aVarA = n.a(this.f1325a, (a.b.a<String, String>) this.f1326b, this.f1327c, this.f1328d);
            if (aVarA != null) {
                this.f1329e.addAll(aVarA.values());
                this.f1329e.add(this.f1330f);
            }
            n.a(this.g, this.h, this.i, aVarA, false);
            Object obj = this.f1327c;
            if (obj != null) {
                this.f1325a.b(obj, this.j, this.f1329e);
                View viewA = n.a(aVarA, this.f1328d, this.k, this.i);
                if (viewA != null) {
                    this.f1325a.a(viewA, this.l);
                }
            }
        }
    }

    /* JADX INFO: compiled from: FragmentTransition.java */
    static class e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Fragment f1331a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f1332b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public androidx.fragment.app.a f1333c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public Fragment f1334d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public boolean f1335e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public androidx.fragment.app.a f1336f;

        e() {
        }
    }

    static {
        f1310b = Build.VERSION.SDK_INT >= 21 ? new o() : null;
        f1311c = a();
    }

    private static p a() {
        try {
            return (p) Class.forName("androidx.transition.FragmentTransitionSupport").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            return null;
        }
    }

    private static void b(i iVar, int i, e eVar, View view, a.b.a<String, String> aVar) {
        Fragment fragment;
        Fragment fragment2;
        p pVarA;
        Object obj;
        ViewGroup viewGroup = iVar.r.c() ? (ViewGroup) iVar.r.a(i) : null;
        if (viewGroup == null || (pVarA = a((fragment2 = eVar.f1334d), (fragment = eVar.f1331a))) == null) {
            return;
        }
        boolean z = eVar.f1332b;
        boolean z2 = eVar.f1335e;
        ArrayList<View> arrayList = new ArrayList<>();
        ArrayList<View> arrayList2 = new ArrayList<>();
        Object objA = a(pVarA, fragment, z);
        Object objB = b(pVarA, fragment2, z2);
        Object objB2 = b(pVarA, viewGroup, view, aVar, eVar, arrayList2, arrayList, objA, objB);
        if (objA == null && objB2 == null) {
            obj = objB;
            if (obj == null) {
                return;
            }
        } else {
            obj = objB;
        }
        ArrayList<View> arrayListA = a(pVarA, obj, fragment2, arrayList2, view);
        ArrayList<View> arrayListA2 = a(pVarA, objA, fragment, arrayList, view);
        a(arrayListA2, 4);
        Object objA2 = a(pVarA, objA, obj, objB2, fragment, z);
        if (objA2 != null) {
            a(pVarA, obj, fragment2, arrayListA);
            ArrayList<String> arrayListA3 = pVarA.a(arrayList);
            pVarA.a(objA2, objA, arrayListA2, obj, arrayListA, objB2, arrayList);
            pVarA.a(viewGroup, objA2);
            pVarA.a(viewGroup, arrayList2, arrayList, arrayListA3, aVar);
            a(arrayListA2, 0);
            pVarA.b(objB2, arrayList2, arrayList);
        }
    }

    static void a(i iVar, ArrayList<androidx.fragment.app.a> arrayList, ArrayList<Boolean> arrayList2, int i, int i2, boolean z) {
        if (iVar.p < 1) {
            return;
        }
        SparseArray sparseArray = new SparseArray();
        for (int i3 = i; i3 < i2; i3++) {
            androidx.fragment.app.a aVar = arrayList.get(i3);
            if (arrayList2.get(i3).booleanValue()) {
                b(aVar, (SparseArray<e>) sparseArray, z);
            } else {
                a(aVar, (SparseArray<e>) sparseArray, z);
            }
        }
        if (sparseArray.size() != 0) {
            View view = new View(iVar.q.f());
            int size = sparseArray.size();
            for (int i4 = 0; i4 < size; i4++) {
                int iKeyAt = sparseArray.keyAt(i4);
                a.b.a<String, String> aVarA = a(iKeyAt, arrayList, arrayList2, i, i2);
                e eVar = (e) sparseArray.valueAt(i4);
                if (z) {
                    b(iVar, iKeyAt, eVar, view, aVarA);
                } else {
                    a(iVar, iKeyAt, eVar, view, aVarA);
                }
            }
        }
    }

    private static a.b.a<String, String> a(int i, ArrayList<androidx.fragment.app.a> arrayList, ArrayList<Boolean> arrayList2, int i2, int i3) {
        ArrayList<String> arrayList3;
        ArrayList<String> arrayList4;
        a.b.a<String, String> aVar = new a.b.a<>();
        for (int i4 = i3 - 1; i4 >= i2; i4--) {
            androidx.fragment.app.a aVar2 = arrayList.get(i4);
            if (aVar2.b(i)) {
                boolean zBooleanValue = arrayList2.get(i4).booleanValue();
                ArrayList<String> arrayList5 = aVar2.o;
                if (arrayList5 != null) {
                    int size = arrayList5.size();
                    if (zBooleanValue) {
                        arrayList3 = aVar2.o;
                        arrayList4 = aVar2.p;
                    } else {
                        ArrayList<String> arrayList6 = aVar2.o;
                        arrayList3 = aVar2.p;
                        arrayList4 = arrayList6;
                    }
                    for (int i5 = 0; i5 < size; i5++) {
                        String str = arrayList4.get(i5);
                        String str2 = arrayList3.get(i5);
                        String strRemove = aVar.remove(str2);
                        if (strRemove != null) {
                            aVar.put(str, strRemove);
                        } else {
                            aVar.put(str, str2);
                        }
                    }
                }
            }
        }
        return aVar;
    }

    private static Object b(p pVar, Fragment fragment, boolean z) {
        Object objP;
        if (fragment == null) {
            return null;
        }
        if (z) {
            objP = fragment.B();
        } else {
            objP = fragment.p();
        }
        return pVar.b(objP);
    }

    private static Object b(p pVar, ViewGroup viewGroup, View view, a.b.a<String, String> aVar, e eVar, ArrayList<View> arrayList, ArrayList<View> arrayList2, Object obj, Object obj2) {
        Object obj3;
        View view2;
        Rect rect;
        Fragment fragment = eVar.f1331a;
        Fragment fragment2 = eVar.f1334d;
        if (fragment != null) {
            fragment.o0().setVisibility(0);
        }
        if (fragment == null || fragment2 == null) {
            return null;
        }
        boolean z = eVar.f1332b;
        Object objA = aVar.isEmpty() ? null : a(pVar, fragment, fragment2, z);
        a.b.a<String, View> aVarB = b(pVar, aVar, objA, eVar);
        a.b.a<String, View> aVarA = a(pVar, aVar, objA, eVar);
        if (aVar.isEmpty()) {
            if (aVarB != null) {
                aVarB.clear();
            }
            if (aVarA != null) {
                aVarA.clear();
            }
            obj3 = null;
        } else {
            a(arrayList, aVarB, aVar.keySet());
            a(arrayList2, aVarA, aVar.values());
            obj3 = objA;
        }
        if (obj == null && obj2 == null && obj3 == null) {
            return null;
        }
        a(fragment, fragment2, z, aVarB, true);
        if (obj3 != null) {
            arrayList2.add(view);
            pVar.b(obj3, view, arrayList);
            a(pVar, obj3, obj2, aVarB, eVar.f1335e, eVar.f1336f);
            Rect rect2 = new Rect();
            View viewA = a(aVarA, eVar, obj, z);
            if (viewA != null) {
                pVar.a(obj, rect2);
            }
            rect = rect2;
            view2 = viewA;
        } else {
            view2 = null;
            rect = null;
        }
        androidx.core.f.q.a(viewGroup, new c(fragment, fragment2, z, aVarA, view2, pVar, rect));
        return obj3;
    }

    private static void a(p pVar, Object obj, Fragment fragment, ArrayList<View> arrayList) {
        if (fragment != null && obj != null && fragment.k && fragment.y && fragment.N) {
            fragment.g(true);
            pVar.a(obj, fragment.H(), arrayList);
            androidx.core.f.q.a(fragment.G, new a(arrayList));
        }
    }

    private static void a(i iVar, int i, e eVar, View view, a.b.a<String, String> aVar) {
        Fragment fragment;
        Fragment fragment2;
        p pVarA;
        Object obj;
        ViewGroup viewGroup = iVar.r.c() ? (ViewGroup) iVar.r.a(i) : null;
        if (viewGroup == null || (pVarA = a((fragment2 = eVar.f1334d), (fragment = eVar.f1331a))) == null) {
            return;
        }
        boolean z = eVar.f1332b;
        boolean z2 = eVar.f1335e;
        Object objA = a(pVarA, fragment, z);
        Object objB = b(pVarA, fragment2, z2);
        ArrayList arrayList = new ArrayList();
        ArrayList<View> arrayList2 = new ArrayList<>();
        Object objA2 = a(pVarA, viewGroup, view, aVar, eVar, (ArrayList<View>) arrayList, arrayList2, objA, objB);
        if (objA == null && objA2 == null) {
            obj = objB;
            if (obj == null) {
                return;
            }
        } else {
            obj = objB;
        }
        ArrayList<View> arrayListA = a(pVarA, obj, fragment2, (ArrayList<View>) arrayList, view);
        Object obj2 = (arrayListA == null || arrayListA.isEmpty()) ? null : obj;
        pVarA.a(objA, view);
        Object objA3 = a(pVarA, objA, obj2, objA2, fragment, eVar.f1332b);
        if (objA3 != null) {
            ArrayList<View> arrayList3 = new ArrayList<>();
            pVarA.a(objA3, objA, arrayList3, obj2, arrayListA, objA2, arrayList2);
            a(pVarA, viewGroup, fragment, view, arrayList2, objA, arrayList3, obj2, arrayListA);
            pVarA.a((View) viewGroup, arrayList2, (Map<String, String>) aVar);
            pVarA.a(viewGroup, objA3);
            pVarA.a(viewGroup, arrayList2, (Map<String, String>) aVar);
        }
    }

    private static a.b.a<String, View> b(p pVar, a.b.a<String, String> aVar, Object obj, e eVar) {
        androidx.core.app.m mVarQ;
        ArrayList<String> arrayList;
        if (!aVar.isEmpty() && obj != null) {
            Fragment fragment = eVar.f1334d;
            a.b.a<String, View> aVar2 = new a.b.a<>();
            pVar.a((Map<String, View>) aVar2, fragment.o0());
            androidx.fragment.app.a aVar3 = eVar.f1336f;
            if (eVar.f1335e) {
                mVarQ = fragment.o();
                arrayList = aVar3.p;
            } else {
                mVarQ = fragment.q();
                arrayList = aVar3.o;
            }
            aVar2.a((Collection<?>) arrayList);
            if (mVarQ != null) {
                mVarQ.a(arrayList, aVar2);
                for (int size = arrayList.size() - 1; size >= 0; size--) {
                    String str = arrayList.get(size);
                    View view = aVar2.get(str);
                    if (view == null) {
                        aVar.remove(str);
                    } else if (!str.equals(t.m(view))) {
                        aVar.put(t.m(view), aVar.remove(str));
                    }
                }
            } else {
                aVar.a((Collection<?>) aVar2.keySet());
            }
            return aVar2;
        }
        aVar.clear();
        return null;
    }

    private static void a(p pVar, ViewGroup viewGroup, Fragment fragment, View view, ArrayList<View> arrayList, Object obj, ArrayList<View> arrayList2, Object obj2, ArrayList<View> arrayList3) {
        androidx.core.f.q.a(viewGroup, new b(obj, pVar, view, fragment, arrayList, arrayList2, arrayList3, obj2));
    }

    private static p a(Fragment fragment, Fragment fragment2) {
        ArrayList arrayList = new ArrayList();
        if (fragment != null) {
            Object objP = fragment.p();
            if (objP != null) {
                arrayList.add(objP);
            }
            Object objB = fragment.B();
            if (objB != null) {
                arrayList.add(objB);
            }
            Object objD = fragment.D();
            if (objD != null) {
                arrayList.add(objD);
            }
        }
        if (fragment2 != null) {
            Object objN = fragment2.n();
            if (objN != null) {
                arrayList.add(objN);
            }
            Object objY = fragment2.y();
            if (objY != null) {
                arrayList.add(objY);
            }
            Object objC = fragment2.C();
            if (objC != null) {
                arrayList.add(objC);
            }
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        p pVar = f1310b;
        if (pVar != null && a(pVar, arrayList)) {
            return f1310b;
        }
        p pVar2 = f1311c;
        if (pVar2 != null && a(pVar2, arrayList)) {
            return f1311c;
        }
        if (f1310b == null && f1311c == null) {
            return null;
        }
        throw new IllegalArgumentException("Invalid Transition types");
    }

    public static void b(androidx.fragment.app.a aVar, SparseArray<e> sparseArray, boolean z) {
        if (aVar.s.r.c()) {
            for (int size = aVar.f1297a.size() - 1; size >= 0; size--) {
                a(aVar, aVar.f1297a.get(size), sparseArray, true, z);
            }
        }
    }

    static boolean b() {
        return (f1310b == null && f1311c == null) ? false : true;
    }

    private static boolean a(p pVar, List<Object> list) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (!pVar.a(list.get(i))) {
                return false;
            }
        }
        return true;
    }

    private static Object a(p pVar, Fragment fragment, Fragment fragment2, boolean z) {
        Object objC;
        if (fragment == null || fragment2 == null) {
            return null;
        }
        if (z) {
            objC = fragment2.D();
        } else {
            objC = fragment.C();
        }
        return pVar.c(pVar.b(objC));
    }

    private static Object a(p pVar, Fragment fragment, boolean z) {
        Object objN;
        if (fragment == null) {
            return null;
        }
        if (z) {
            objN = fragment.y();
        } else {
            objN = fragment.n();
        }
        return pVar.b(objN);
    }

    private static void a(ArrayList<View> arrayList, a.b.a<String, View> aVar, Collection<String> collection) {
        for (int size = aVar.size() - 1; size >= 0; size--) {
            View viewD = aVar.d(size);
            if (collection.contains(t.m(viewD))) {
                arrayList.add(viewD);
            }
        }
    }

    private static Object a(p pVar, ViewGroup viewGroup, View view, a.b.a<String, String> aVar, e eVar, ArrayList<View> arrayList, ArrayList<View> arrayList2, Object obj, Object obj2) {
        Object obj3;
        Rect rect;
        Fragment fragment = eVar.f1331a;
        Fragment fragment2 = eVar.f1334d;
        if (fragment == null || fragment2 == null) {
            return null;
        }
        boolean z = eVar.f1332b;
        Object objA = aVar.isEmpty() ? null : a(pVar, fragment, fragment2, z);
        a.b.a<String, View> aVarB = b(pVar, aVar, objA, eVar);
        if (aVar.isEmpty()) {
            obj3 = null;
        } else {
            arrayList.addAll(aVarB.values());
            obj3 = objA;
        }
        if (obj == null && obj2 == null && obj3 == null) {
            return null;
        }
        a(fragment, fragment2, z, aVarB, true);
        if (obj3 != null) {
            rect = new Rect();
            pVar.b(obj3, view, arrayList);
            a(pVar, obj3, obj2, aVarB, eVar.f1335e, eVar.f1336f);
            if (obj != null) {
                pVar.a(obj, rect);
            }
        } else {
            rect = null;
        }
        androidx.core.f.q.a(viewGroup, new d(pVar, aVar, obj3, eVar, arrayList2, view, fragment, fragment2, z, arrayList, obj, rect));
        return obj3;
    }

    static a.b.a<String, View> a(p pVar, a.b.a<String, String> aVar, Object obj, e eVar) {
        androidx.core.app.m mVarO;
        ArrayList<String> arrayList;
        String strA;
        Fragment fragment = eVar.f1331a;
        View viewH = fragment.H();
        if (!aVar.isEmpty() && obj != null && viewH != null) {
            a.b.a<String, View> aVar2 = new a.b.a<>();
            pVar.a((Map<String, View>) aVar2, viewH);
            androidx.fragment.app.a aVar3 = eVar.f1333c;
            if (eVar.f1332b) {
                mVarO = fragment.q();
                arrayList = aVar3.o;
            } else {
                mVarO = fragment.o();
                arrayList = aVar3.p;
            }
            if (arrayList != null) {
                aVar2.a((Collection<?>) arrayList);
                aVar2.a((Collection<?>) aVar.values());
            }
            if (mVarO != null) {
                mVarO.a(arrayList, aVar2);
                for (int size = arrayList.size() - 1; size >= 0; size--) {
                    String str = arrayList.get(size);
                    View view = aVar2.get(str);
                    if (view == null) {
                        String strA2 = a(aVar, str);
                        if (strA2 != null) {
                            aVar.remove(strA2);
                        }
                    } else if (!str.equals(t.m(view)) && (strA = a(aVar, str)) != null) {
                        aVar.put(strA, t.m(view));
                    }
                }
            } else {
                a(aVar, aVar2);
            }
            return aVar2;
        }
        aVar.clear();
        return null;
    }

    private static String a(a.b.a<String, String> aVar, String str) {
        int size = aVar.size();
        for (int i = 0; i < size; i++) {
            if (str.equals(aVar.d(i))) {
                return aVar.b(i);
            }
        }
        return null;
    }

    static View a(a.b.a<String, View> aVar, e eVar, Object obj, boolean z) {
        ArrayList<String> arrayList;
        String str;
        androidx.fragment.app.a aVar2 = eVar.f1333c;
        if (obj == null || aVar == null || (arrayList = aVar2.o) == null || arrayList.isEmpty()) {
            return null;
        }
        if (z) {
            str = aVar2.o.get(0);
        } else {
            str = aVar2.p.get(0);
        }
        return aVar.get(str);
    }

    private static void a(p pVar, Object obj, Object obj2, a.b.a<String, View> aVar, boolean z, androidx.fragment.app.a aVar2) {
        String str;
        ArrayList<String> arrayList = aVar2.o;
        if (arrayList == null || arrayList.isEmpty()) {
            return;
        }
        if (z) {
            str = aVar2.p.get(0);
        } else {
            str = aVar2.o.get(0);
        }
        View view = aVar.get(str);
        pVar.c(obj, view);
        if (obj2 != null) {
            pVar.c(obj2, view);
        }
    }

    private static void a(a.b.a<String, String> aVar, a.b.a<String, View> aVar2) {
        for (int size = aVar.size() - 1; size >= 0; size--) {
            if (!aVar2.containsKey(aVar.d(size))) {
                aVar.c(size);
            }
        }
    }

    static void a(Fragment fragment, Fragment fragment2, boolean z, a.b.a<String, View> aVar, boolean z2) {
        androidx.core.app.m mVarO;
        if (z) {
            mVarO = fragment2.o();
        } else {
            mVarO = fragment.o();
        }
        if (mVarO != null) {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            int size = aVar == null ? 0 : aVar.size();
            for (int i = 0; i < size; i++) {
                arrayList2.add(aVar.b(i));
                arrayList.add(aVar.d(i));
            }
            if (z2) {
                mVarO.b(arrayList2, arrayList, null);
            } else {
                mVarO.a(arrayList2, arrayList, null);
            }
        }
    }

    static ArrayList<View> a(p pVar, Object obj, Fragment fragment, ArrayList<View> arrayList, View view) {
        if (obj == null) {
            return null;
        }
        ArrayList<View> arrayList2 = new ArrayList<>();
        View viewH = fragment.H();
        if (viewH != null) {
            pVar.a(arrayList2, viewH);
        }
        if (arrayList != null) {
            arrayList2.removeAll(arrayList);
        }
        if (arrayList2.isEmpty()) {
            return arrayList2;
        }
        arrayList2.add(view);
        pVar.a(obj, arrayList2);
        return arrayList2;
    }

    static void a(ArrayList<View> arrayList, int i) {
        if (arrayList == null) {
            return;
        }
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            arrayList.get(size).setVisibility(i);
        }
    }

    private static Object a(p pVar, Object obj, Object obj2, Object obj3, Fragment fragment, boolean z) {
        boolean zG;
        if (obj == null || obj2 == null || fragment == null) {
            zG = true;
        } else if (z) {
            zG = fragment.h();
        } else {
            zG = fragment.g();
        }
        if (zG) {
            return pVar.b(obj2, obj, obj3);
        }
        return pVar.a(obj2, obj, obj3);
    }

    public static void a(androidx.fragment.app.a aVar, SparseArray<e> sparseArray, boolean z) {
        int size = aVar.f1297a.size();
        for (int i = 0; i < size; i++) {
            a(aVar, aVar.f1297a.get(i), sparseArray, false, z);
        }
    }

    /* JADX WARN: Code duplicated, block: B:56:0x0076  */
    /* JADX WARN: Code duplicated, block: B:57:0x0078  */
    /* JADX WARN: Code duplicated, block: B:64:0x0087 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:65:0x0089  */
    /* JADX WARN: Code duplicated, block: B:66:0x008c  */
    /* JADX WARN: Code duplicated, block: B:70:0x0094  */
    /* JADX WARN: Code duplicated, block: B:71:0x0096  */
    private static void a(androidx.fragment.app.a aVar, m.a aVar2, SparseArray<e> sparseArray, boolean z, boolean z2) {
        int i;
        boolean z3;
        boolean z4;
        boolean z5;
        boolean z6;
        View view;
        boolean z7;
        Fragment fragment = aVar2.f1304b;
        if (fragment == null || (i = fragment.w) == 0) {
            return;
        }
        int i2 = z ? f1309a[aVar2.f1303a] : aVar2.f1303a;
        boolean z8 = false;
        if (i2 == 1) {
            if (z2) {
                z3 = fragment.M;
            } else if (!fragment.k || fragment.y) {
                z3 = false;
            } else {
                z3 = true;
            }
            z8 = z3;
            z4 = true;
            z6 = false;
            z5 = false;
        } else if (i2 == 3) {
            if (z2 ? !fragment.k || fragment.y : fragment.k || (view = fragment.H) == null || view.getVisibility() != 0 || fragment.O < 0.0f) {
                z7 = false;
            } else {
                z7 = true;
            }
            z5 = z7;
            z4 = false;
            z6 = true;
        } else if (i2 != 4) {
            if (i2 != 5) {
                if (i2 != 6) {
                    if (i2 != 7) {
                        z4 = false;
                    } else {
                        if (z2) {
                            z3 = fragment.M;
                        } else {
                            if (fragment.k) {
                            }
                            z3 = false;
                        }
                        z8 = z3;
                        z4 = true;
                    }
                }
                if (z2) {
                    z7 = false;
                } else {
                    z7 = false;
                }
                z5 = z7;
                z4 = false;
                z6 = true;
            } else {
                if (z2) {
                    if (fragment.N && !fragment.y && fragment.k) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                } else {
                    z3 = fragment.y;
                }
                z8 = z3;
                z4 = true;
            }
            z6 = false;
            z5 = false;
        } else {
            if (!z2 ? !(!fragment.k || fragment.y) : fragment.N && fragment.k && fragment.y) {
                z7 = false;
            } else {
                z7 = true;
            }
            z5 = z7;
            z4 = false;
            z6 = true;
        }
        e eVarA = sparseArray.get(i);
        if (z8) {
            eVarA = a(eVarA, sparseArray, i);
            eVarA.f1331a = fragment;
            eVarA.f1332b = z;
            eVarA.f1333c = aVar;
        }
        e eVarA2 = eVarA;
        if (!z2 && z4) {
            if (eVarA2 != null && eVarA2.f1334d == fragment) {
                eVarA2.f1334d = null;
            }
            i iVar = aVar.s;
            if (fragment.f1203a < 1 && iVar.p >= 1 && !aVar.q) {
                iVar.j(fragment);
                iVar.a(fragment, 1, 0, 0, false);
            }
        }
        if (z5 && (eVarA2 == null || eVarA2.f1334d == null)) {
            eVarA2 = a(eVarA2, sparseArray, i);
            eVarA2.f1334d = fragment;
            eVarA2.f1335e = z;
            eVarA2.f1336f = aVar;
        }
        if (z2 || !z6 || eVarA2 == null || eVarA2.f1331a != fragment) {
            return;
        }
        eVarA2.f1331a = null;
    }

    private static e a(e eVar, SparseArray<e> sparseArray, int i) {
        if (eVar != null) {
            return eVar;
        }
        e eVar2 = new e();
        sparseArray.put(i, eVar2);
        return eVar2;
    }
}
