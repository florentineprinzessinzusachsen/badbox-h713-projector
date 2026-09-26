package androidx.fragment.app;

import android.util.Log;
import java.io.PrintWriter;
import java.util.ArrayList;

/* JADX INFO: compiled from: BackStackRecord.java */
/* JADX INFO: loaded from: classes.dex */
final class a extends m implements h.a, i.k {
    final i s;
    boolean t;
    int u = -1;

    public a(i iVar) {
        this.s = iVar;
    }

    public void a(String str, PrintWriter printWriter) {
        a(str, printWriter, true);
    }

    @Override // androidx.fragment.app.m
    public m b(Fragment fragment) {
        i iVar = fragment.r;
        if (iVar == null || iVar == this.s) {
            super.b(fragment);
            return this;
        }
        throw new IllegalStateException("Cannot detach Fragment attached to a different FragmentManager. Fragment " + fragment.toString() + " is already attached to a FragmentManager.");
    }

    @Override // androidx.fragment.app.m
    public m c(Fragment fragment) {
        i iVar = fragment.r;
        if (iVar == null || iVar == this.s) {
            super.c(fragment);
            return this;
        }
        throw new IllegalStateException("Cannot hide Fragment attached to a different FragmentManager. Fragment " + fragment.toString() + " is already attached to a FragmentManager.");
    }

    @Override // androidx.fragment.app.m
    public m d(Fragment fragment) {
        i iVar = fragment.r;
        if (iVar == null || iVar == this.s) {
            super.d(fragment);
            return this;
        }
        throw new IllegalStateException("Cannot remove Fragment attached to a different FragmentManager. Fragment " + fragment.toString() + " is already attached to a FragmentManager.");
    }

    @Override // androidx.fragment.app.m
    public m e(Fragment fragment) {
        i iVar = fragment.r;
        if (iVar == null || iVar == this.s) {
            super.e(fragment);
            return this;
        }
        throw new IllegalStateException("Cannot show Fragment attached to a different FragmentManager. Fragment " + fragment.toString() + " is already attached to a FragmentManager.");
    }

    boolean f() {
        for (int i = 0; i < this.f1297a.size(); i++) {
            if (b(this.f1297a.get(i))) {
                return true;
            }
        }
        return false;
    }

    public void g() {
        if (this.r != null) {
            for (int i = 0; i < this.r.size(); i++) {
                this.r.get(i).run();
            }
            this.r = null;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("BackStackEntry{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        if (this.u >= 0) {
            sb.append(" #");
            sb.append(this.u);
        }
        if (this.j != null) {
            sb.append(" ");
            sb.append(this.j);
        }
        sb.append("}");
        return sb.toString();
    }

    public void a(String str, PrintWriter printWriter, boolean z) {
        String str2;
        if (z) {
            printWriter.print(str);
            printWriter.print("mName=");
            printWriter.print(this.j);
            printWriter.print(" mIndex=");
            printWriter.print(this.u);
            printWriter.print(" mCommitted=");
            printWriter.println(this.t);
            if (this.f1302f != 0) {
                printWriter.print(str);
                printWriter.print("mTransition=#");
                printWriter.print(Integer.toHexString(this.f1302f));
                printWriter.print(" mTransitionStyle=#");
                printWriter.println(Integer.toHexString(this.g));
            }
            if (this.f1298b != 0 || this.f1299c != 0) {
                printWriter.print(str);
                printWriter.print("mEnterAnim=#");
                printWriter.print(Integer.toHexString(this.f1298b));
                printWriter.print(" mExitAnim=#");
                printWriter.println(Integer.toHexString(this.f1299c));
            }
            if (this.f1300d != 0 || this.f1301e != 0) {
                printWriter.print(str);
                printWriter.print("mPopEnterAnim=#");
                printWriter.print(Integer.toHexString(this.f1300d));
                printWriter.print(" mPopExitAnim=#");
                printWriter.println(Integer.toHexString(this.f1301e));
            }
            if (this.k != 0 || this.l != null) {
                printWriter.print(str);
                printWriter.print("mBreadCrumbTitleRes=#");
                printWriter.print(Integer.toHexString(this.k));
                printWriter.print(" mBreadCrumbTitleText=");
                printWriter.println(this.l);
            }
            if (this.m != 0 || this.n != null) {
                printWriter.print(str);
                printWriter.print("mBreadCrumbShortTitleRes=#");
                printWriter.print(Integer.toHexString(this.m));
                printWriter.print(" mBreadCrumbShortTitleText=");
                printWriter.println(this.n);
            }
        }
        if (this.f1297a.isEmpty()) {
            return;
        }
        printWriter.print(str);
        printWriter.println("Operations:");
        int size = this.f1297a.size();
        for (int i = 0; i < size; i++) {
            m.a aVar = this.f1297a.get(i);
            switch (aVar.f1303a) {
                case 0:
                    str2 = "NULL";
                    break;
                case 1:
                    str2 = "ADD";
                    break;
                case 2:
                    str2 = "REPLACE";
                    break;
                case 3:
                    str2 = "REMOVE";
                    break;
                case 4:
                    str2 = "HIDE";
                    break;
                case 5:
                    str2 = "SHOW";
                    break;
                case 6:
                    str2 = "DETACH";
                    break;
                case 7:
                    str2 = "ATTACH";
                    break;
                case 8:
                    str2 = "SET_PRIMARY_NAV";
                    break;
                case 9:
                    str2 = "UNSET_PRIMARY_NAV";
                    break;
                case 10:
                    str2 = "OP_SET_MAX_LIFECYCLE";
                    break;
                default:
                    str2 = "cmd=" + aVar.f1303a;
                    break;
            }
            printWriter.print(str);
            printWriter.print("  Op #");
            printWriter.print(i);
            printWriter.print(": ");
            printWriter.print(str2);
            printWriter.print(" ");
            printWriter.println(aVar.f1304b);
            if (z) {
                if (aVar.f1305c != 0 || aVar.f1306d != 0) {
                    printWriter.print(str);
                    printWriter.print("enterAnim=#");
                    printWriter.print(Integer.toHexString(aVar.f1305c));
                    printWriter.print(" exitAnim=#");
                    printWriter.println(Integer.toHexString(aVar.f1306d));
                }
                if (aVar.f1307e != 0 || aVar.f1308f != 0) {
                    printWriter.print(str);
                    printWriter.print("popEnterAnim=#");
                    printWriter.print(Integer.toHexString(aVar.f1307e));
                    printWriter.print(" popExitAnim=#");
                    printWriter.println(Integer.toHexString(aVar.f1308f));
                }
            }
        }
    }

    @Override // androidx.fragment.app.m
    public int b() {
        return a(false);
    }

    @Override // androidx.fragment.app.m
    public int c() {
        return a(true);
    }

    void d() {
        int size = this.f1297a.size();
        for (int i = 0; i < size; i++) {
            m.a aVar = this.f1297a.get(i);
            Fragment fragment = aVar.f1304b;
            if (fragment != null) {
                fragment.a(this.f1302f, this.g);
            }
            switch (aVar.f1303a) {
                case 1:
                    fragment.a(aVar.f1305c);
                    this.s.a(fragment, false);
                    break;
                case 2:
                default:
                    throw new IllegalArgumentException("Unknown cmd: " + aVar.f1303a);
                case 3:
                    fragment.a(aVar.f1306d);
                    this.s.o(fragment);
                    break;
                case 4:
                    fragment.a(aVar.f1306d);
                    this.s.h(fragment);
                    break;
                case 5:
                    fragment.a(aVar.f1305c);
                    this.s.t(fragment);
                    break;
                case 6:
                    fragment.a(aVar.f1306d);
                    this.s.d(fragment);
                    break;
                case 7:
                    fragment.a(aVar.f1305c);
                    this.s.b(fragment);
                    break;
                case 8:
                    this.s.s(fragment);
                    break;
                case 9:
                    this.s.s(null);
                    break;
                case 10:
                    this.s.a(fragment, aVar.h);
                    break;
            }
            if (!this.q && aVar.f1303a != 1 && fragment != null) {
                this.s.l(fragment);
            }
        }
        if (this.q) {
            return;
        }
        i iVar = this.s;
        iVar.a(iVar.p, true);
    }

    public String e() {
        return this.j;
    }

    boolean b(int i) {
        int size = this.f1297a.size();
        for (int i2 = 0; i2 < size; i2++) {
            Fragment fragment = this.f1297a.get(i2).f1304b;
            int i3 = fragment != null ? fragment.w : 0;
            if (i3 != 0 && i3 == i) {
                return true;
            }
        }
        return false;
    }

    void b(boolean z) {
        for (int size = this.f1297a.size() - 1; size >= 0; size--) {
            m.a aVar = this.f1297a.get(size);
            Fragment fragment = aVar.f1304b;
            if (fragment != null) {
                fragment.a(i.f(this.f1302f), this.g);
            }
            switch (aVar.f1303a) {
                case 1:
                    fragment.a(aVar.f1308f);
                    this.s.o(fragment);
                    break;
                case 2:
                default:
                    throw new IllegalArgumentException("Unknown cmd: " + aVar.f1303a);
                case 3:
                    fragment.a(aVar.f1307e);
                    this.s.a(fragment, false);
                    break;
                case 4:
                    fragment.a(aVar.f1307e);
                    this.s.t(fragment);
                    break;
                case 5:
                    fragment.a(aVar.f1308f);
                    this.s.h(fragment);
                    break;
                case 6:
                    fragment.a(aVar.f1307e);
                    this.s.b(fragment);
                    break;
                case 7:
                    fragment.a(aVar.f1308f);
                    this.s.d(fragment);
                    break;
                case 8:
                    this.s.s(null);
                    break;
                case 9:
                    this.s.s(fragment);
                    break;
                case 10:
                    this.s.a(fragment, aVar.g);
                    break;
            }
            if (!this.q && aVar.f1303a != 3 && fragment != null) {
                this.s.l(fragment);
            }
        }
        if (this.q || !z) {
            return;
        }
        i iVar = this.s;
        iVar.a(iVar.p, true);
    }

    /* JADX WARN: Code duplicated, block: B:13:0x0027  */
    /* JADX WARN: Code duplicated, block: B:14:0x002d  */
    Fragment b(ArrayList<Fragment> arrayList, Fragment fragment) {
        for (int size = this.f1297a.size() - 1; size >= 0; size--) {
            m.a aVar = this.f1297a.get(size);
            int i = aVar.f1303a;
            if (i == 1) {
                arrayList.remove(aVar.f1304b);
            } else if (i != 3) {
                switch (i) {
                    case 6:
                        arrayList.add(aVar.f1304b);
                        break;
                    case 7:
                        arrayList.remove(aVar.f1304b);
                        break;
                    case 8:
                        fragment = null;
                        break;
                    case 9:
                        fragment = aVar.f1304b;
                        break;
                    case 10:
                        aVar.h = aVar.g;
                        break;
                }
            } else {
                arrayList.add(aVar.f1304b);
            }
        }
        return fragment;
    }

    private static boolean b(m.a aVar) {
        Fragment fragment = aVar.f1304b;
        return (fragment == null || !fragment.k || fragment.H == null || fragment.z || fragment.y || !fragment.O()) ? false : true;
    }

    @Override // androidx.fragment.app.h.a
    public int a() {
        return this.u;
    }

    @Override // androidx.fragment.app.m
    void a(int i, Fragment fragment, String str, int i2) {
        super.a(i, fragment, str, i2);
        fragment.r = this.s;
    }

    void a(int i) {
        if (this.h) {
            if (i.I) {
                Log.v("FragmentManager", "Bump nesting in " + this + " by " + i);
            }
            int size = this.f1297a.size();
            for (int i2 = 0; i2 < size; i2++) {
                m.a aVar = this.f1297a.get(i2);
                Fragment fragment = aVar.f1304b;
                if (fragment != null) {
                    fragment.q += i;
                    if (i.I) {
                        Log.v("FragmentManager", "Bump nesting of " + aVar.f1304b + " to " + aVar.f1304b.q);
                    }
                }
            }
        }
    }

    int a(boolean z) {
        if (!this.t) {
            if (i.I) {
                Log.v("FragmentManager", "Commit: " + this);
                PrintWriter printWriter = new PrintWriter(new androidx.core.e.b("FragmentManager"));
                a("  ", printWriter);
                printWriter.close();
            }
            this.t = true;
            if (this.h) {
                this.u = this.s.b(this);
            } else {
                this.u = -1;
            }
            this.s.a(this, z);
            return this.u;
        }
        throw new IllegalStateException("commit already called");
    }

    @Override // androidx.fragment.app.i.k
    public boolean a(ArrayList<a> arrayList, ArrayList<Boolean> arrayList2) {
        if (i.I) {
            Log.v("FragmentManager", "Run: " + this);
        }
        arrayList.add(this);
        arrayList2.add(false);
        if (!this.h) {
            return true;
        }
        this.s.a(this);
        return true;
    }

    boolean a(ArrayList<a> arrayList, int i, int i2) {
        if (i2 == i) {
            return false;
        }
        int size = this.f1297a.size();
        int i3 = -1;
        for (int i4 = 0; i4 < size; i4++) {
            Fragment fragment = this.f1297a.get(i4).f1304b;
            int i5 = fragment != null ? fragment.w : 0;
            if (i5 != 0 && i5 != i3) {
                for (int i6 = i; i6 < i2; i6++) {
                    a aVar = arrayList.get(i6);
                    int size2 = aVar.f1297a.size();
                    for (int i7 = 0; i7 < size2; i7++) {
                        Fragment fragment2 = aVar.f1297a.get(i7).f1304b;
                        if ((fragment2 != null ? fragment2.w : 0) == i5) {
                            return true;
                        }
                    }
                }
                i3 = i5;
            }
        }
        return false;
    }

    /* JADX WARN: Code duplicated, block: B:35:0x00b6  */
    Fragment a(ArrayList<Fragment> arrayList, Fragment fragment) {
        Fragment fragment2 = fragment;
        int i = 0;
        while (i < this.f1297a.size()) {
            m.a aVar = this.f1297a.get(i);
            int i2 = aVar.f1303a;
            if (i2 == 1) {
                arrayList.add(aVar.f1304b);
            } else if (i2 == 2) {
                Fragment fragment3 = aVar.f1304b;
                int i3 = fragment3.w;
                Fragment fragment4 = fragment2;
                int i4 = i;
                boolean z = false;
                for (int size = arrayList.size() - 1; size >= 0; size--) {
                    Fragment fragment5 = arrayList.get(size);
                    if (fragment5.w == i3) {
                        if (fragment5 == fragment3) {
                            z = true;
                        } else {
                            if (fragment5 == fragment4) {
                                this.f1297a.add(i4, new m.a(9, fragment5));
                                i4++;
                                fragment4 = null;
                            }
                            m.a aVar2 = new m.a(3, fragment5);
                            aVar2.f1305c = aVar.f1305c;
                            aVar2.f1307e = aVar.f1307e;
                            aVar2.f1306d = aVar.f1306d;
                            aVar2.f1308f = aVar.f1308f;
                            this.f1297a.add(i4, aVar2);
                            arrayList.remove(fragment5);
                            i4++;
                        }
                    }
                }
                if (z) {
                    this.f1297a.remove(i4);
                    i4--;
                } else {
                    aVar.f1303a = 1;
                    arrayList.add(fragment3);
                }
                i = i4;
                fragment2 = fragment4;
            } else if (i2 == 3 || i2 == 6) {
                arrayList.remove(aVar.f1304b);
                Fragment fragment6 = aVar.f1304b;
                if (fragment6 == fragment2) {
                    this.f1297a.add(i, new m.a(9, fragment6));
                    i++;
                    fragment2 = null;
                }
            } else if (i2 == 7) {
                arrayList.add(aVar.f1304b);
            } else if (i2 == 8) {
                this.f1297a.add(i, new m.a(9, fragment2));
                i++;
                fragment2 = aVar.f1304b;
            }
            i++;
        }
        return fragment2;
    }

    void a(Fragment.f fVar) {
        for (int i = 0; i < this.f1297a.size(); i++) {
            m.a aVar = this.f1297a.get(i);
            if (b(aVar)) {
                aVar.f1304b.a(fVar);
            }
        }
    }
}
