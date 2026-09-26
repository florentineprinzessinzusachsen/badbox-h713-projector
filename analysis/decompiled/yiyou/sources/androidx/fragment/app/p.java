package androidx.fragment.app;

import android.annotation.SuppressLint;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.f.t;
import androidx.core.f.v;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: FragmentTransitionImpl.java */
/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"UnknownNullness"})
public abstract class p {

    /* JADX INFO: compiled from: FragmentTransitionImpl.java */
    class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ int f1347a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ ArrayList f1348b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ ArrayList f1349c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ ArrayList f1350d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ ArrayList f1351e;

        a(p pVar, int i, ArrayList arrayList, ArrayList arrayList2, ArrayList arrayList3, ArrayList arrayList4) {
            this.f1347a = i;
            this.f1348b = arrayList;
            this.f1349c = arrayList2;
            this.f1350d = arrayList3;
            this.f1351e = arrayList4;
        }

        @Override // java.lang.Runnable
        public void run() {
            for (int i = 0; i < this.f1347a; i++) {
                t.a((View) this.f1348b.get(i), (String) this.f1349c.get(i));
                t.a((View) this.f1350d.get(i), (String) this.f1351e.get(i));
            }
        }
    }

    /* JADX INFO: compiled from: FragmentTransitionImpl.java */
    class b implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ ArrayList f1352a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ Map f1353b;

        b(p pVar, ArrayList arrayList, Map map) {
            this.f1352a = arrayList;
            this.f1353b = map;
        }

        @Override // java.lang.Runnable
        public void run() {
            int size = this.f1352a.size();
            for (int i = 0; i < size; i++) {
                View view = (View) this.f1352a.get(i);
                String strM = t.m(view);
                if (strM != null) {
                    t.a(view, p.a((Map<String, String>) this.f1353b, strM));
                }
            }
        }
    }

    /* JADX INFO: compiled from: FragmentTransitionImpl.java */
    class c implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ ArrayList f1354a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ Map f1355b;

        c(p pVar, ArrayList arrayList, Map map) {
            this.f1354a = arrayList;
            this.f1355b = map;
        }

        @Override // java.lang.Runnable
        public void run() {
            int size = this.f1354a.size();
            for (int i = 0; i < size; i++) {
                View view = (View) this.f1354a.get(i);
                t.a(view, (String) this.f1355b.get(t.m(view)));
            }
        }
    }

    public abstract Object a(Object obj, Object obj2, Object obj3);

    protected void a(View view, Rect rect) {
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        rect.set(iArr[0], iArr[1], iArr[0] + view.getWidth(), iArr[1] + view.getHeight());
    }

    public abstract void a(ViewGroup viewGroup, Object obj);

    public abstract void a(Object obj, Rect rect);

    public abstract void a(Object obj, View view);

    public abstract void a(Object obj, View view, ArrayList<View> arrayList);

    public abstract void a(Object obj, Object obj2, ArrayList<View> arrayList, Object obj3, ArrayList<View> arrayList2, Object obj4, ArrayList<View> arrayList3);

    public abstract void a(Object obj, ArrayList<View> arrayList);

    public abstract void a(Object obj, ArrayList<View> arrayList, ArrayList<View> arrayList2);

    public abstract boolean a(Object obj);

    public abstract Object b(Object obj);

    public abstract Object b(Object obj, Object obj2, Object obj3);

    public abstract void b(Object obj, View view);

    public abstract void b(Object obj, View view, ArrayList<View> arrayList);

    public abstract void b(Object obj, ArrayList<View> arrayList, ArrayList<View> arrayList2);

    public abstract Object c(Object obj);

    public abstract void c(Object obj, View view);

    ArrayList<String> a(ArrayList<View> arrayList) {
        ArrayList<String> arrayList2 = new ArrayList<>();
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            View view = arrayList.get(i);
            arrayList2.add(t.m(view));
            t.a(view, (String) null);
        }
        return arrayList2;
    }

    void a(View view, ArrayList<View> arrayList, ArrayList<View> arrayList2, ArrayList<String> arrayList3, Map<String, String> map) {
        int size = arrayList2.size();
        ArrayList arrayList4 = new ArrayList();
        for (int i = 0; i < size; i++) {
            View view2 = arrayList.get(i);
            String strM = t.m(view2);
            arrayList4.add(strM);
            if (strM != null) {
                t.a(view2, (String) null);
                String str = map.get(strM);
                for (int i2 = 0; i2 < size; i2++) {
                    if (str.equals(arrayList3.get(i2))) {
                        t.a(arrayList2.get(i2), strM);
                        break;
                    }
                }
            }
        }
        androidx.core.f.q.a(view, new a(this, size, arrayList2, arrayList3, arrayList, arrayList4));
    }

    void a(ArrayList<View> arrayList, View view) {
        if (view.getVisibility() == 0) {
            if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                if (v.a(viewGroup)) {
                    arrayList.add(viewGroup);
                    return;
                }
                int childCount = viewGroup.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    a(arrayList, viewGroup.getChildAt(i));
                }
                return;
            }
            arrayList.add(view);
        }
    }

    void a(Map<String, View> map, View view) {
        if (view.getVisibility() == 0) {
            String strM = t.m(view);
            if (strM != null) {
                map.put(strM, view);
            }
            if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                int childCount = viewGroup.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    a(map, viewGroup.getChildAt(i));
                }
            }
        }
    }

    void a(View view, ArrayList<View> arrayList, Map<String, String> map) {
        androidx.core.f.q.a(view, new b(this, arrayList, map));
    }

    void a(ViewGroup viewGroup, ArrayList<View> arrayList, Map<String, String> map) {
        androidx.core.f.q.a(viewGroup, new c(this, arrayList, map));
    }

    protected static void a(List<View> list, View view) {
        int size = list.size();
        if (a(list, view, size)) {
            return;
        }
        list.add(view);
        for (int i = size; i < list.size(); i++) {
            View view2 = list.get(i);
            if (view2 instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view2;
                int childCount = viewGroup.getChildCount();
                for (int i2 = 0; i2 < childCount; i2++) {
                    View childAt = viewGroup.getChildAt(i2);
                    if (!a(list, childAt, size)) {
                        list.add(childAt);
                    }
                }
            }
        }
    }

    private static boolean a(List<View> list, View view, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            if (list.get(i2) == view) {
                return true;
            }
        }
        return false;
    }

    protected static boolean a(List list) {
        return list == null || list.isEmpty();
    }

    static String a(Map<String, String> map, String str) {
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (str.equals(entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }
}
