package com.blankj.utilcode.util;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.h;
import androidx.fragment.app.m;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class FragmentUtils {
    private static final String ARGS_ID = "args_id";
    private static final String ARGS_IS_ADD_STACK = "args_is_add_stack";
    private static final String ARGS_IS_HIDE = "args_is_hide";
    private static final String ARGS_TAG = "args_tag";
    private static final int TYPE_ADD_FRAGMENT = 1;
    private static final int TYPE_HIDE_FRAGMENT = 4;
    private static final int TYPE_REMOVE_FRAGMENT = 32;
    private static final int TYPE_REMOVE_TO_FRAGMENT = 64;
    private static final int TYPE_REPLACE_FRAGMENT = 16;
    private static final int TYPE_SHOW_FRAGMENT = 2;
    private static final int TYPE_SHOW_HIDE_FRAGMENT = 8;

    private static class Args {
        final int id;
        final boolean isAddStack;
        final boolean isHide;
        final String tag;

        Args(int i, boolean z, boolean z2) {
            this(i, null, z, z2);
        }

        Args(int i, String str, boolean z, boolean z2) {
            this.id = i;
            this.tag = str;
            this.isHide = z;
            this.isAddStack = z2;
        }
    }

    public static class FragmentNode {
        final Fragment fragment;
        final List<FragmentNode> next;

        public FragmentNode(Fragment fragment, List<FragmentNode> list) {
            this.fragment = fragment;
            this.next = list;
        }

        public Fragment getFragment() {
            return this.fragment;
        }

        public List<FragmentNode> getNext() {
            return this.next;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.fragment.getClass().getSimpleName());
            sb.append("->");
            List<FragmentNode> list = this.next;
            sb.append((list == null || list.isEmpty()) ? "no child" : this.next.toString());
            return sb.toString();
        }
    }

    public interface OnBackClickListener {
        boolean onBackClick();
    }

    private FragmentUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static void add(h hVar, Fragment fragment, int i) {
        if (hVar == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (fragment == null) {
            throw new NullPointerException("Argument 'add' of type Fragment (#1 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        add(hVar, fragment, i, (String) null, false, false);
    }

    private static void addAnim(m mVar, int i, int i2, int i3, int i4) {
        mVar.a(i, i2, i3, i4);
    }

    private static void addSharedElement(m mVar, View... viewArr) {
        if (Build.VERSION.SDK_INT >= 21) {
            for (View view : viewArr) {
                mVar.a(view, view.getTransitionName());
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static boolean dispatchBackPress(Fragment fragment) {
        if (fragment != 0) {
            return fragment.Q() && fragment.S() && fragment.G() && (fragment instanceof OnBackClickListener) && ((OnBackClickListener) fragment).onBackClick();
        }
        throw new NullPointerException("Argument 'fragment' of type Fragment (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static Fragment findFragment(h hVar, Class<? extends Fragment> cls) {
        if (hVar != null) {
            return hVar.a(cls.getName());
        }
        throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static List<FragmentNode> getAllFragments(h hVar) {
        if (hVar != null) {
            return getAllFragments(hVar, new ArrayList());
        }
        throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static List<FragmentNode> getAllFragmentsInStack(h hVar) {
        if (hVar != null) {
            return getAllFragmentsInStack(hVar, new ArrayList());
        }
        throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    private static Args getArgs(Fragment fragment) {
        Bundle bundleK = fragment.k();
        if (bundleK == null) {
            bundleK = Bundle.EMPTY;
        }
        return new Args(bundleK.getInt(ARGS_ID, fragment.t()), bundleK.getBoolean(ARGS_IS_HIDE), bundleK.getBoolean(ARGS_IS_ADD_STACK));
    }

    public static List<Fragment> getFragments(h hVar) {
        if (hVar == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        List<Fragment> listE = hVar.e();
        return (listE == null || listE.isEmpty()) ? Collections.emptyList() : listE;
    }

    public static List<Fragment> getFragmentsInStack(h hVar) {
        Bundle bundleK;
        if (hVar == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        List<Fragment> fragments = getFragments(hVar);
        ArrayList arrayList = new ArrayList();
        for (Fragment fragment : fragments) {
            if (fragment != null && (bundleK = fragment.k()) != null && bundleK.getBoolean(ARGS_IS_ADD_STACK)) {
                arrayList.add(fragment);
            }
        }
        return arrayList;
    }

    public static String getSimpleName(Fragment fragment) {
        return fragment == null ? "null" : fragment.getClass().getSimpleName();
    }

    public static Fragment getTop(h hVar) {
        if (hVar != null) {
            return getTopIsInStack(hVar, null, false);
        }
        throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static Fragment getTopInStack(h hVar) {
        if (hVar != null) {
            return getTopIsInStack(hVar, null, true);
        }
        throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    private static Fragment getTopIsInStack(h hVar, Fragment fragment, boolean z) {
        if (hVar == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        List<Fragment> fragments = getFragments(hVar);
        for (int size = fragments.size() - 1; size >= 0; size--) {
            Fragment fragment2 = fragments.get(size);
            if (fragment2 != null) {
                if (!z) {
                    return getTopIsInStack(fragment2.l(), fragment, false);
                }
                Bundle bundleK = fragment2.k();
                if (bundleK != null && bundleK.getBoolean(ARGS_IS_ADD_STACK)) {
                    return getTopIsInStack(fragment2.l(), fragment, true);
                }
            }
        }
        return null;
    }

    public static Fragment getTopShow(h hVar) {
        if (hVar != null) {
            return getTopShowIsInStack(hVar, null, false);
        }
        throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static Fragment getTopShowInStack(h hVar) {
        if (hVar != null) {
            return getTopShowIsInStack(hVar, null, true);
        }
        throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    private static Fragment getTopShowIsInStack(h hVar, Fragment fragment, boolean z) {
        if (hVar == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        List<Fragment> fragments = getFragments(hVar);
        for (int size = fragments.size() - 1; size >= 0; size--) {
            Fragment fragment2 = fragments.get(size);
            if (fragment2 != null && fragment2.Q() && fragment2.S() && fragment2.G()) {
                if (!z) {
                    return getTopShowIsInStack(fragment2.l(), fragment2, false);
                }
                Bundle bundleK = fragment2.k();
                if (bundleK != null && bundleK.getBoolean(ARGS_IS_ADD_STACK)) {
                    return getTopShowIsInStack(fragment2.l(), fragment2, true);
                }
            }
        }
        return fragment;
    }

    public static void hide(Fragment fragment) {
        if (fragment == null) {
            throw new NullPointerException("Argument 'hide' of type Fragment (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        putArgs(fragment, true);
        operateNoAnim(fragment.r(), 4, null, fragment);
    }

    private static void operate(int i, h hVar, m mVar, Fragment fragment, Fragment... fragmentArr) {
        if (hVar == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#1 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (fragment != null && fragment.P()) {
            Log.e("FragmentUtils", fragment.getClass().getName() + " is isRemoving");
            return;
        }
        int i2 = 0;
        if (i == 1) {
            int length = fragmentArr.length;
            while (i2 < length) {
                Fragment fragment2 = fragmentArr[i2];
                Bundle bundleK = fragment2.k();
                if (bundleK == null) {
                    return;
                }
                String string = bundleK.getString(ARGS_TAG, fragment2.getClass().getName());
                Fragment fragmentA = hVar.a(string);
                if (fragmentA != null && fragmentA.J()) {
                    mVar.d(fragmentA);
                }
                mVar.a(bundleK.getInt(ARGS_ID), fragment2, string);
                if (bundleK.getBoolean(ARGS_IS_HIDE)) {
                    mVar.c(fragment2);
                }
                if (bundleK.getBoolean(ARGS_IS_ADD_STACK)) {
                    mVar.a(string);
                }
                i2++;
            }
        } else if (i == 2) {
            int length2 = fragmentArr.length;
            while (i2 < length2) {
                mVar.e(fragmentArr[i2]);
                i2++;
            }
        } else if (i == 4) {
            int length3 = fragmentArr.length;
            while (i2 < length3) {
                mVar.c(fragmentArr[i2]);
                i2++;
            }
        } else if (i == 8) {
            mVar.e(fragment);
            int length4 = fragmentArr.length;
            while (i2 < length4) {
                Fragment fragment3 = fragmentArr[i2];
                if (fragment3 != fragment) {
                    mVar.c(fragment3);
                }
                i2++;
            }
        } else if (i == 16) {
            Bundle bundleK2 = fragmentArr[0].k();
            if (bundleK2 == null) {
                return;
            }
            String string2 = bundleK2.getString(ARGS_TAG, fragmentArr[0].getClass().getName());
            mVar.b(bundleK2.getInt(ARGS_ID), fragmentArr[0], string2);
            if (bundleK2.getBoolean(ARGS_IS_ADD_STACK)) {
                mVar.a(string2);
            }
        } else if (i == 32) {
            int length5 = fragmentArr.length;
            while (i2 < length5) {
                Fragment fragment4 = fragmentArr[i2];
                if (fragment4 != fragment) {
                    mVar.d(fragment4);
                }
                i2++;
            }
        } else if (i == 64) {
            for (int length6 = fragmentArr.length - 1; length6 >= 0; length6--) {
                Fragment fragment5 = fragmentArr[length6];
                if (fragment5 == fragmentArr[0]) {
                    if (fragment == null) {
                        break;
                    }
                    mVar.d(fragment5);
                    break;
                }
                mVar.d(fragment5);
            }
        }
        mVar.c();
    }

    private static void operateNoAnim(h hVar, int i, Fragment fragment, Fragment... fragmentArr) {
        if (hVar == null) {
            return;
        }
        operate(i, hVar, hVar.a(), fragment, fragmentArr);
    }

    public static void pop(h hVar) {
        if (hVar == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        pop(hVar, true);
    }

    public static void popAll(h hVar) {
        if (hVar == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        popAll(hVar, true);
    }

    public static void popTo(h hVar, Class<? extends Fragment> cls, boolean z) {
        if (hVar == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        popTo(hVar, cls, z, true);
    }

    private static void putArgs(Fragment fragment, Args args) {
        Bundle bundleK = fragment.k();
        if (bundleK == null) {
            bundleK = new Bundle();
            fragment.m(bundleK);
        }
        bundleK.putInt(ARGS_ID, args.id);
        bundleK.putBoolean(ARGS_IS_HIDE, args.isHide);
        bundleK.putBoolean(ARGS_IS_ADD_STACK, args.isAddStack);
        bundleK.putString(ARGS_TAG, args.tag);
    }

    public static void remove(Fragment fragment) {
        if (fragment == null) {
            throw new NullPointerException("Argument 'remove' of type Fragment (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        operateNoAnim(fragment.r(), 32, null, fragment);
    }

    public static void removeAll(h hVar) {
        if (hVar == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        operateNoAnim(hVar, 32, null, (Fragment[]) getFragments(hVar).toArray(new Fragment[0]));
    }

    public static void removeTo(Fragment fragment, boolean z) {
        if (fragment == null) {
            throw new NullPointerException("Argument 'removeTo' of type Fragment (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        operateNoAnim(fragment.r(), 64, z ? fragment : null, fragment);
    }

    public static void replace(Fragment fragment, Fragment fragment2) {
        if (fragment == null) {
            throw new NullPointerException("Argument 'srcFragment' of type Fragment (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (fragment2 == null) {
            throw new NullPointerException("Argument 'destFragment' of type Fragment (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        replace(fragment, fragment2, (String) null, false);
    }

    public static void setBackground(Fragment fragment, Drawable drawable) {
        if (fragment == null) {
            throw new NullPointerException("Argument 'fragment' of type Fragment (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        View viewH = fragment.H();
        if (viewH == null) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 16) {
            viewH.setBackground(drawable);
        } else {
            viewH.setBackgroundDrawable(drawable);
        }
    }

    public static void setBackgroundColor(Fragment fragment, int i) {
        if (fragment == null) {
            throw new NullPointerException("Argument 'fragment' of type Fragment (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        View viewH = fragment.H();
        if (viewH != null) {
            viewH.setBackgroundColor(i);
        }
    }

    public static void setBackgroundResource(Fragment fragment, int i) {
        if (fragment == null) {
            throw new NullPointerException("Argument 'fragment' of type Fragment (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        View viewH = fragment.H();
        if (viewH != null) {
            viewH.setBackgroundResource(i);
        }
    }

    public static void show(Fragment fragment) {
        if (fragment == null) {
            throw new NullPointerException("Argument 'show' of type Fragment (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        putArgs(fragment, false);
        operateNoAnim(fragment.r(), 2, null, fragment);
    }

    public static void showHide(int i, List<Fragment> list) {
        if (list == null) {
            throw new NullPointerException("Argument 'fragments' of type List<Fragment> (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        showHide(list.get(i), list);
    }

    public static Fragment findFragment(h hVar, String str) {
        if (hVar == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (str != null) {
            return hVar.a(str);
        }
        throw new NullPointerException("Argument 'tag' of type String (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    private static List<FragmentNode> getAllFragments(h hVar, List<FragmentNode> list) {
        if (hVar != null) {
            List<Fragment> fragments = getFragments(hVar);
            for (int size = fragments.size() - 1; size >= 0; size--) {
                Fragment fragment = fragments.get(size);
                if (fragment != null) {
                    list.add(new FragmentNode(fragment, getAllFragments(fragment.l(), new ArrayList())));
                }
            }
            return list;
        }
        throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    private static List<FragmentNode> getAllFragmentsInStack(h hVar, List<FragmentNode> list) {
        Bundle bundleK;
        if (hVar != null) {
            List<Fragment> fragments = getFragments(hVar);
            for (int size = fragments.size() - 1; size >= 0; size--) {
                Fragment fragment = fragments.get(size);
                if (fragment != null && (bundleK = fragment.k()) != null && bundleK.getBoolean(ARGS_IS_ADD_STACK)) {
                    list.add(new FragmentNode(fragment, getAllFragmentsInStack(fragment.l(), new ArrayList())));
                }
            }
            return list;
        }
        throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void pop(h hVar, boolean z) {
        if (hVar == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (z) {
            hVar.g();
        } else {
            hVar.f();
        }
    }

    public static void popAll(h hVar, boolean z) {
        if (hVar != null) {
            if (hVar.c() > 0) {
                h.a aVarA = hVar.a(0);
                if (z) {
                    hVar.b(aVarA.a(), 1);
                    return;
                } else {
                    hVar.a(aVarA.a(), 1);
                    return;
                }
            }
            return;
        }
        throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void popTo(h hVar, Class<? extends Fragment> cls, boolean z, boolean z2) {
        if (hVar == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (z2) {
            hVar.b(cls.getName(), z ? 1 : 0);
        } else {
            hVar.a(cls.getName(), z ? 1 : 0);
        }
    }

    public static void showHide(Fragment fragment, List<Fragment> list) {
        if (fragment == null) {
            throw new NullPointerException("Argument 'show' of type Fragment (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (list != null) {
            Iterator<Fragment> it = list.iterator();
            while (true) {
                boolean z = false;
                if (it.hasNext()) {
                    Fragment next = it.next();
                    if (next != fragment) {
                        z = true;
                    }
                    putArgs(next, z);
                } else {
                    operateNoAnim(fragment.r(), 8, fragment, (Fragment[]) list.toArray(new Fragment[0]));
                    return;
                }
            }
        } else {
            throw new NullPointerException("Argument 'hide' of type List<Fragment> (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    public static void add(h hVar, Fragment fragment, int i, boolean z) {
        if (hVar == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (fragment != null) {
            add(hVar, fragment, i, (String) null, z, false);
            return;
        }
        throw new NullPointerException("Argument 'add' of type Fragment (#1 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void hide(h hVar) {
        if (hVar != null) {
            List<Fragment> fragments = getFragments(hVar);
            Iterator<Fragment> it = fragments.iterator();
            while (it.hasNext()) {
                putArgs(it.next(), true);
            }
            operateNoAnim(hVar, 4, null, (Fragment[]) fragments.toArray(new Fragment[0]));
            return;
        }
        throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void replace(Fragment fragment, Fragment fragment2, boolean z) {
        if (fragment == null) {
            throw new NullPointerException("Argument 'srcFragment' of type Fragment (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (fragment2 != null) {
            replace(fragment, fragment2, (String) null, z);
            return;
        }
        throw new NullPointerException("Argument 'destFragment' of type Fragment (#1 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void show(h hVar) {
        if (hVar != null) {
            List<Fragment> fragments = getFragments(hVar);
            Iterator<Fragment> it = fragments.iterator();
            while (it.hasNext()) {
                putArgs(it.next(), false);
            }
            operateNoAnim(hVar, 2, null, (Fragment[]) fragments.toArray(new Fragment[0]));
            return;
        }
        throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static boolean dispatchBackPress(h hVar) {
        if (hVar != null) {
            List<Fragment> fragments = getFragments(hVar);
            if (fragments != null && !fragments.isEmpty()) {
                for (int size = fragments.size() - 1; size >= 0; size--) {
                    Fragment fragment = fragments.get(size);
                    if (fragment != 0 && fragment.Q() && fragment.S() && fragment.G() && (fragment instanceof OnBackClickListener) && ((OnBackClickListener) fragment).onBackClick()) {
                        return true;
                    }
                }
            }
            return false;
        }
        throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void add(h hVar, Fragment fragment, int i, boolean z, boolean z2) {
        if (hVar == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (fragment != null) {
            add(hVar, fragment, i, (String) null, z, z2);
            return;
        }
        throw new NullPointerException("Argument 'add' of type Fragment (#1 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void replace(Fragment fragment, Fragment fragment2, int i, int i2) {
        if (fragment == null) {
            throw new NullPointerException("Argument 'srcFragment' of type Fragment (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (fragment2 != null) {
            replace(fragment, fragment2, (String) null, false, i, i2, 0, 0);
            return;
        }
        throw new NullPointerException("Argument 'destFragment' of type Fragment (#1 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    private static void putArgs(Fragment fragment, boolean z) {
        Bundle bundleK = fragment.k();
        if (bundleK == null) {
            bundleK = new Bundle();
            fragment.m(bundleK);
        }
        bundleK.putBoolean(ARGS_IS_HIDE, z);
    }

    public static void showHide(int i, Fragment... fragmentArr) {
        if (fragmentArr != null) {
            showHide(fragmentArr[i], fragmentArr);
            return;
        }
        throw new NullPointerException("Argument 'fragments' of type Fragment[] (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void add(h hVar, Fragment fragment, int i, int i2, int i3) {
        if (hVar == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (fragment != null) {
            add(hVar, fragment, i, null, false, i2, i3, 0, 0);
            return;
        }
        throw new NullPointerException("Argument 'add' of type Fragment (#1 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void replace(Fragment fragment, Fragment fragment2, boolean z, int i, int i2) {
        if (fragment == null) {
            throw new NullPointerException("Argument 'srcFragment' of type Fragment (#0 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (fragment2 != null) {
            replace(fragment, fragment2, (String) null, z, i, i2, 0, 0);
            return;
        }
        throw new NullPointerException("Argument 'destFragment' of type Fragment (#1 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void showHide(Fragment fragment, Fragment... fragmentArr) {
        if (fragment == null) {
            throw new NullPointerException("Argument 'show' of type Fragment (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (fragmentArr != null) {
            int length = fragmentArr.length;
            for (int i = 0; i < length; i++) {
                Fragment fragment2 = fragmentArr[i];
                putArgs(fragment2, fragment2 != fragment);
            }
            operateNoAnim(fragment.r(), 8, fragment, fragmentArr);
            return;
        }
        throw new NullPointerException("Argument 'hide' of type Fragment[] (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void add(h hVar, Fragment fragment, int i, boolean z, int i2, int i3) {
        if (hVar == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (fragment != null) {
            add(hVar, fragment, i, null, z, i2, i3, 0, 0);
            return;
        }
        throw new NullPointerException("Argument 'add' of type Fragment (#1 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void replace(Fragment fragment, Fragment fragment2, int i, int i2, int i3, int i4) {
        if (fragment == null) {
            throw new NullPointerException("Argument 'srcFragment' of type Fragment (#0 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (fragment2 != null) {
            replace(fragment, fragment2, (String) null, false, i, i2, i3, i4);
            return;
        }
        throw new NullPointerException("Argument 'destFragment' of type Fragment (#1 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void showHide(Fragment fragment, Fragment fragment2) {
        if (fragment == null) {
            throw new NullPointerException("Argument 'show' of type Fragment (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (fragment2 != null) {
            putArgs(fragment, false);
            putArgs(fragment2, true);
            operateNoAnim(fragment.r(), 8, fragment, fragment2);
            return;
        }
        throw new NullPointerException("Argument 'hide' of type Fragment (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void add(h hVar, Fragment fragment, int i, int i2, int i3, int i4, int i5) {
        if (hVar == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 7, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (fragment != null) {
            add(hVar, fragment, i, null, false, i2, i3, i4, i5);
            return;
        }
        throw new NullPointerException("Argument 'add' of type Fragment (#1 out of 7, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void replace(Fragment fragment, Fragment fragment2, boolean z, int i, int i2, int i3, int i4) {
        if (fragment == null) {
            throw new NullPointerException("Argument 'srcFragment' of type Fragment (#0 out of 7, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (fragment2 != null) {
            replace(fragment, fragment2, (String) null, z, i, i2, i3, i4);
            return;
        }
        throw new NullPointerException("Argument 'destFragment' of type Fragment (#1 out of 7, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void add(h hVar, Fragment fragment, int i, boolean z, int i2, int i3, int i4, int i5) {
        if (hVar == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 8, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (fragment != null) {
            add(hVar, fragment, i, null, z, i2, i3, i4, i5);
            return;
        }
        throw new NullPointerException("Argument 'add' of type Fragment (#1 out of 8, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void replace(Fragment fragment, Fragment fragment2, View... viewArr) {
        if (fragment == null) {
            throw new NullPointerException("Argument 'srcFragment' of type Fragment (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (fragment2 != null) {
            replace(fragment, fragment2, (String) null, false, viewArr);
            return;
        }
        throw new NullPointerException("Argument 'destFragment' of type Fragment (#1 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void add(h hVar, Fragment fragment, int i, View... viewArr) {
        if (hVar == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (fragment == null) {
            throw new NullPointerException("Argument 'add' of type Fragment (#1 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (viewArr != null) {
            add(hVar, fragment, i, (String) null, false, viewArr);
            return;
        }
        throw new NullPointerException("Argument 'sharedElements' of type View[] (#3 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void replace(Fragment fragment, Fragment fragment2, boolean z, View... viewArr) {
        if (fragment == null) {
            throw new NullPointerException("Argument 'srcFragment' of type Fragment (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (fragment2 != null) {
            replace(fragment, fragment2, (String) null, z, viewArr);
            return;
        }
        throw new NullPointerException("Argument 'destFragment' of type Fragment (#1 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void replace(h hVar, Fragment fragment, int i) {
        if (hVar == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (fragment != null) {
            replace(hVar, fragment, i, (String) null, false);
            return;
        }
        throw new NullPointerException("Argument 'fragment' of type Fragment (#1 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void add(h hVar, Fragment fragment, int i, boolean z, View... viewArr) {
        if (hVar == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (fragment == null) {
            throw new NullPointerException("Argument 'add' of type Fragment (#1 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (viewArr != null) {
            add(hVar, fragment, i, (String) null, z, viewArr);
            return;
        }
        throw new NullPointerException("Argument 'sharedElements' of type View[] (#4 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void replace(h hVar, Fragment fragment, int i, boolean z) {
        if (hVar == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (fragment != null) {
            replace(hVar, fragment, i, (String) null, z);
            return;
        }
        throw new NullPointerException("Argument 'fragment' of type Fragment (#1 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void add(h hVar, List<Fragment> list, int i, int i2) {
        if (hVar == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (list != null) {
            add(hVar, (Fragment[]) list.toArray(new Fragment[0]), i, (String[]) null, i2);
            return;
        }
        throw new NullPointerException("Argument 'adds' of type List<Fragment> (#1 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void replace(h hVar, Fragment fragment, int i, int i2, int i3) {
        if (hVar == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (fragment != null) {
            replace(hVar, fragment, i, null, false, i2, i3, 0, 0);
            return;
        }
        throw new NullPointerException("Argument 'fragment' of type Fragment (#1 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void add(h hVar, Fragment[] fragmentArr, int i, int i2) {
        if (hVar == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (fragmentArr != null) {
            add(hVar, fragmentArr, i, (String[]) null, i2);
            return;
        }
        throw new NullPointerException("Argument 'adds' of type Fragment[] (#1 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void replace(h hVar, Fragment fragment, int i, boolean z, int i2, int i3) {
        if (hVar == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (fragment != null) {
            replace(hVar, fragment, i, null, z, i2, i3, 0, 0);
            return;
        }
        throw new NullPointerException("Argument 'fragment' of type Fragment (#1 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void add(h hVar, Fragment fragment, int i, String str) {
        if (hVar == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (fragment != null) {
            add(hVar, fragment, i, str, false, false);
            return;
        }
        throw new NullPointerException("Argument 'add' of type Fragment (#1 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void replace(h hVar, Fragment fragment, int i, int i2, int i3, int i4, int i5) {
        if (hVar == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 7, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (fragment != null) {
            replace(hVar, fragment, i, null, false, i2, i3, i4, i5);
            return;
        }
        throw new NullPointerException("Argument 'fragment' of type Fragment (#1 out of 7, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void add(h hVar, Fragment fragment, int i, String str, boolean z) {
        if (hVar == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (fragment != null) {
            add(hVar, fragment, i, str, z, false);
            return;
        }
        throw new NullPointerException("Argument 'add' of type Fragment (#1 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void replace(h hVar, Fragment fragment, int i, boolean z, int i2, int i3, int i4, int i5) {
        if (hVar == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 8, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (fragment != null) {
            replace(hVar, fragment, i, null, z, i2, i3, i4, i5);
            return;
        }
        throw new NullPointerException("Argument 'fragment' of type Fragment (#1 out of 8, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void add(h hVar, Fragment fragment, int i, String str, boolean z, boolean z2) {
        if (hVar == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (fragment != null) {
            putArgs(fragment, new Args(i, str, z, z2));
            operateNoAnim(hVar, 1, null, fragment);
            return;
        }
        throw new NullPointerException("Argument 'add' of type Fragment (#1 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void replace(h hVar, Fragment fragment, int i, View... viewArr) {
        if (hVar == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (fragment != null) {
            replace(hVar, fragment, i, (String) null, false, viewArr);
            return;
        }
        throw new NullPointerException("Argument 'fragment' of type Fragment (#1 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void add(h hVar, Fragment fragment, int i, String str, int i2, int i3) {
        if (hVar == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (fragment != null) {
            add(hVar, fragment, i, str, false, i2, i3, 0, 0);
            return;
        }
        throw new NullPointerException("Argument 'add' of type Fragment (#1 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void replace(h hVar, Fragment fragment, int i, boolean z, View... viewArr) {
        if (hVar == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (fragment != null) {
            replace(hVar, fragment, i, (String) null, z, viewArr);
            return;
        }
        throw new NullPointerException("Argument 'fragment' of type Fragment (#1 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void add(h hVar, Fragment fragment, int i, String str, boolean z, int i2, int i3) {
        if (hVar == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 7, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (fragment != null) {
            add(hVar, fragment, i, str, z, i2, i3, 0, 0);
            return;
        }
        throw new NullPointerException("Argument 'add' of type Fragment (#1 out of 7, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void replace(Fragment fragment, Fragment fragment2, String str) {
        if (fragment == null) {
            throw new NullPointerException("Argument 'srcFragment' of type Fragment (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (fragment2 != null) {
            replace(fragment, fragment2, str, false);
            return;
        }
        throw new NullPointerException("Argument 'destFragment' of type Fragment (#1 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void add(h hVar, Fragment fragment, int i, String str, int i2, int i3, int i4, int i5) {
        if (hVar == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 8, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (fragment != null) {
            add(hVar, fragment, i, str, false, i2, i3, i4, i5);
            return;
        }
        throw new NullPointerException("Argument 'add' of type Fragment (#1 out of 8, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void replace(Fragment fragment, Fragment fragment2, String str, boolean z) {
        if (fragment == null) {
            throw new NullPointerException("Argument 'srcFragment' of type Fragment (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (fragment2 != null) {
            h hVarR = fragment.r();
            if (hVarR == null) {
                return;
            }
            replace(hVarR, fragment2, getArgs(fragment).id, str, z);
            return;
        }
        throw new NullPointerException("Argument 'destFragment' of type Fragment (#1 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void add(h hVar, Fragment fragment, int i, String str, boolean z, int i2, int i3, int i4, int i5) {
        if (hVar == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 9, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (fragment != null) {
            m mVarA = hVar.a();
            putArgs(fragment, new Args(i, str, false, z));
            addAnim(mVarA, i2, i3, i4, i5);
            operate(1, hVar, mVarA, null, fragment);
            return;
        }
        throw new NullPointerException("Argument 'add' of type Fragment (#1 out of 9, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void replace(Fragment fragment, Fragment fragment2, String str, int i, int i2) {
        if (fragment == null) {
            throw new NullPointerException("Argument 'srcFragment' of type Fragment (#0 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (fragment2 != null) {
            replace(fragment, fragment2, str, false, i, i2, 0, 0);
            return;
        }
        throw new NullPointerException("Argument 'destFragment' of type Fragment (#1 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void replace(Fragment fragment, Fragment fragment2, String str, boolean z, int i, int i2) {
        if (fragment == null) {
            throw new NullPointerException("Argument 'srcFragment' of type Fragment (#0 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (fragment2 != null) {
            replace(fragment, fragment2, str, z, i, i2, 0, 0);
            return;
        }
        throw new NullPointerException("Argument 'destFragment' of type Fragment (#1 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void add(h hVar, Fragment fragment, int i, String str, View... viewArr) {
        if (hVar == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (fragment == null) {
            throw new NullPointerException("Argument 'add' of type Fragment (#1 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (viewArr != null) {
            add(hVar, fragment, i, str, false, viewArr);
            return;
        }
        throw new NullPointerException("Argument 'sharedElements' of type View[] (#4 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void replace(Fragment fragment, Fragment fragment2, String str, int i, int i2, int i3, int i4) {
        if (fragment == null) {
            throw new NullPointerException("Argument 'srcFragment' of type Fragment (#0 out of 7, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (fragment2 != null) {
            replace(fragment, fragment2, str, false, i, i2, i3, i4);
            return;
        }
        throw new NullPointerException("Argument 'destFragment' of type Fragment (#1 out of 7, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void add(h hVar, Fragment fragment, int i, String str, boolean z, View... viewArr) {
        if (hVar == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (fragment == null) {
            throw new NullPointerException("Argument 'add' of type Fragment (#1 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (viewArr != null) {
            m mVarA = hVar.a();
            putArgs(fragment, new Args(i, str, false, z));
            addSharedElement(mVarA, viewArr);
            operate(1, hVar, mVarA, null, fragment);
            return;
        }
        throw new NullPointerException("Argument 'sharedElements' of type View[] (#5 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void replace(Fragment fragment, Fragment fragment2, String str, boolean z, int i, int i2, int i3, int i4) {
        if (fragment == null) {
            throw new NullPointerException("Argument 'srcFragment' of type Fragment (#0 out of 8, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (fragment2 != null) {
            h hVarR = fragment.r();
            if (hVarR == null) {
                return;
            }
            replace(hVarR, fragment2, getArgs(fragment).id, str, z, i, i2, i3, i4);
            return;
        }
        throw new NullPointerException("Argument 'destFragment' of type Fragment (#1 out of 8, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void replace(Fragment fragment, Fragment fragment2, String str, View... viewArr) {
        if (fragment == null) {
            throw new NullPointerException("Argument 'srcFragment' of type Fragment (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (fragment2 != null) {
            replace(fragment, fragment2, str, false, viewArr);
            return;
        }
        throw new NullPointerException("Argument 'destFragment' of type Fragment (#1 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void add(h hVar, List<Fragment> list, int i, String[] strArr, int i2) {
        if (hVar == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (list != null) {
            add(hVar, (Fragment[]) list.toArray(new Fragment[0]), i, strArr, i2);
            return;
        }
        throw new NullPointerException("Argument 'adds' of type List<Fragment> (#1 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void replace(Fragment fragment, Fragment fragment2, String str, boolean z, View... viewArr) {
        if (fragment == null) {
            throw new NullPointerException("Argument 'srcFragment' of type Fragment (#0 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (fragment2 != null) {
            h hVarR = fragment.r();
            if (hVarR == null) {
                return;
            }
            replace(hVarR, fragment2, getArgs(fragment).id, str, z, viewArr);
            return;
        }
        throw new NullPointerException("Argument 'destFragment' of type Fragment (#1 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void add(h hVar, Fragment[] fragmentArr, int i, String[] strArr, int i2) {
        if (hVar == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (fragmentArr != null) {
            if (strArr == null) {
                int length = fragmentArr.length;
                int i3 = 0;
                while (i3 < length) {
                    putArgs(fragmentArr[i3], new Args(i, null, i2 != i3, false));
                    i3++;
                }
            } else {
                int length2 = fragmentArr.length;
                int i4 = 0;
                while (i4 < length2) {
                    putArgs(fragmentArr[i4], new Args(i, strArr[i4], i2 != i4, false));
                    i4++;
                }
            }
            operateNoAnim(hVar, 1, null, fragmentArr);
            return;
        }
        throw new NullPointerException("Argument 'adds' of type Fragment[] (#1 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void replace(h hVar, Fragment fragment, int i, String str) {
        if (hVar == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (fragment != null) {
            replace(hVar, fragment, i, str, false);
            return;
        }
        throw new NullPointerException("Argument 'fragment' of type Fragment (#1 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void replace(h hVar, Fragment fragment, int i, String str, boolean z) {
        if (hVar == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (fragment != null) {
            m mVarA = hVar.a();
            putArgs(fragment, new Args(i, str, false, z));
            operate(16, hVar, mVarA, null, fragment);
            return;
        }
        throw new NullPointerException("Argument 'fragment' of type Fragment (#1 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void replace(h hVar, Fragment fragment, int i, String str, int i2, int i3) {
        if (hVar == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (fragment != null) {
            replace(hVar, fragment, i, str, false, i2, i3, 0, 0);
            return;
        }
        throw new NullPointerException("Argument 'fragment' of type Fragment (#1 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void replace(h hVar, Fragment fragment, int i, String str, boolean z, int i2, int i3) {
        if (hVar == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 7, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (fragment != null) {
            replace(hVar, fragment, i, str, z, i2, i3, 0, 0);
            return;
        }
        throw new NullPointerException("Argument 'fragment' of type Fragment (#1 out of 7, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void replace(h hVar, Fragment fragment, int i, String str, int i2, int i3, int i4, int i5) {
        if (hVar == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 8, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (fragment != null) {
            replace(hVar, fragment, i, str, false, i2, i3, i4, i5);
            return;
        }
        throw new NullPointerException("Argument 'fragment' of type Fragment (#1 out of 8, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void replace(h hVar, Fragment fragment, int i, String str, boolean z, int i2, int i3, int i4, int i5) {
        if (hVar == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 9, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (fragment != null) {
            m mVarA = hVar.a();
            putArgs(fragment, new Args(i, str, false, z));
            addAnim(mVarA, i2, i3, i4, i5);
            operate(16, hVar, mVarA, null, fragment);
            return;
        }
        throw new NullPointerException("Argument 'fragment' of type Fragment (#1 out of 9, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void replace(h hVar, Fragment fragment, int i, String str, View... viewArr) {
        if (hVar == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (fragment != null) {
            replace(hVar, fragment, i, str, false, viewArr);
            return;
        }
        throw new NullPointerException("Argument 'fragment' of type Fragment (#1 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void replace(h hVar, Fragment fragment, int i, String str, boolean z, View... viewArr) {
        if (hVar == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (fragment != null) {
            m mVarA = hVar.a();
            putArgs(fragment, new Args(i, str, false, z));
            addSharedElement(mVarA, viewArr);
            operate(16, hVar, mVarA, null, fragment);
            return;
        }
        throw new NullPointerException("Argument 'fragment' of type Fragment (#1 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }
}
