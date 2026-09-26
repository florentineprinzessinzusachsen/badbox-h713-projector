package com.baidu.mobstat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Rect;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import android.webkit.WebView;
import android.widget.GridView;
import android.widget.ListView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class ap {
    public static View a(Activity activity) {
        Window window;
        if (activity == null || (window = activity.getWindow()) == null) {
            return null;
        }
        return window.getDecorView();
    }

    public static String b(View view) {
        ViewParent parent;
        String strValueOf;
        if (view == null || (parent = view.getParent()) == null || !(parent instanceof ViewGroup)) {
            return "";
        }
        String strA = a(parent.getClass());
        if ("android.widget".equals(strA) || "android.view".equals(strA)) {
            return "";
        }
        ViewGroup viewGroup = (ViewGroup) parent;
        Class<?> cls = null;
        try {
            cls = Class.forName("androidx.viewpager.widget.ViewPager");
        } catch (ClassNotFoundException unused) {
        }
        if (cls == null || !cls.isAssignableFrom(viewGroup.getClass())) {
            return "";
        }
        try {
            ViewPager viewPager = (ViewPager) viewGroup;
            ArrayList arrayList = new ArrayList();
            int childCount = viewPager.getChildCount();
            int i = 0;
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt = viewPager.getChildAt(i2);
                arrayList.add(childAt);
                if (c(childAt) != null) {
                    i++;
                }
            }
            if (arrayList.size() < 2 || i < 2) {
                strValueOf = String.valueOf(viewPager.getCurrentItem());
            } else {
                try {
                    Collections.sort(arrayList, new Comparator<View>() { // from class: com.baidu.mobstat.ap.1
                        @Override // java.util.Comparator
                        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                        public int compare(View view2, View view3) {
                            return view2.getLeft() - view3.getLeft();
                        }
                    });
                } catch (Exception unused2) {
                }
                int left = view.getLeft() / Math.abs(((View) arrayList.get(1)).getLeft() - ((View) arrayList.get(0)).getLeft());
                int iA = viewPager.getAdapter().a();
                if (iA != 0) {
                    left %= iA;
                }
                strValueOf = String.valueOf(left);
            }
            return strValueOf;
        } catch (Throwable unused3) {
            return "";
        }
    }

    private static String c(Class<?> cls) {
        if (cls == null) {
            return "";
        }
        String strA = a(cls);
        return ("android.widget".equals(strA) || "android.view".equals(strA)) ? d(cls) : c(cls.getSuperclass());
    }

    public static String d(View view) {
        int iLastIndexOf;
        int i;
        String strSubstring = null;
        try {
            if (view.getId() != 0) {
                strSubstring = view.getResources().getResourceName(view.getId());
            }
        } catch (Exception unused) {
        }
        if (!TextUtils.isEmpty(strSubstring) && strSubstring.contains(":id/") && (iLastIndexOf = strSubstring.lastIndexOf(":id/")) != -1 && (i = iLastIndexOf + 4) < strSubstring.length()) {
            strSubstring = strSubstring.substring(i);
        }
        return strSubstring == null ? "" : strSubstring;
    }

    public static Map<String, String> e(View view) {
        Map<String, String> map;
        Object tag = view.getTag(-96000);
        if (tag != null && (tag instanceof Map)) {
            try {
                map = (Map) tag;
            } catch (Exception unused) {
                map = null;
            }
            if (map != null && map.size() != 0) {
                return map;
            }
        }
        return null;
    }

    public static String f(View view) {
        Class<?> cls;
        String str;
        if (view == null || (cls = view.getClass()) == null) {
            return "";
        }
        String strD = d(cls);
        if (TextUtils.isEmpty(strD) || !cls.isAnonymousClass()) {
            str = strD;
        } else {
            str = strD + "$";
        }
        return str == null ? "" : str;
    }

    private static boolean g(View view) {
        return view != null && "com.android.internal.policy".equals(a(view.getClass())) && "DecorView".equals(f(view));
    }

    public static String a(View view) {
        String simpleName;
        if (view instanceof ListView) {
            simpleName = ListView.class.getSimpleName();
        } else {
            simpleName = view instanceof WebView ? WebView.class.getSimpleName() : "";
        }
        if (TextUtils.isEmpty(simpleName)) {
            String strA = a(view.getClass());
            if (!"android.widget".equals(strA) && !"android.view".equals(strA)) {
                Class<?> cls = null;
                try {
                    cls = Class.forName("androidx.recyclerview.widget.RecyclerView");
                } catch (Exception unused) {
                }
                if (cls != null && cls.isAssignableFrom(view.getClass())) {
                    simpleName = "RecyclerView";
                }
            }
        }
        if (TextUtils.isEmpty(simpleName)) {
            simpleName = c(view.getClass());
        }
        return TextUtils.isEmpty(simpleName) ? "Object" : simpleName;
    }

    public static Rect c(View view) {
        if (view.getVisibility() != 0) {
            return null;
        }
        Rect rect = new Rect();
        if (a(view, rect) && rect.right > rect.left && rect.bottom > rect.top) {
            return rect;
        }
        return null;
    }

    public static String d(JSONArray jSONArray) {
        if (jSONArray == null || jSONArray.length() == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                JSONObject jSONObject = (JSONObject) jSONArray.get(i);
                sb.append("/" + b(jSONObject.getString("p")) + "[" + jSONObject.getString("i") + "]");
                String strOptString = jSONObject.optString("d");
                if (!TextUtils.isEmpty(strOptString)) {
                    sb.append("#" + strOptString);
                }
            } catch (Exception unused) {
                return "";
            }
        }
        return sb.toString();
    }

    public static String c(JSONArray jSONArray) {
        if (jSONArray == null || jSONArray.length() == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                JSONObject jSONObject = (JSONObject) jSONArray.get(i);
                sb.append("/" + b(jSONObject.getString("p")) + "[" + jSONObject.getString("i") + "]");
            } catch (Exception unused) {
                return "";
            }
        }
        return sb.toString();
    }

    public static String a(Class<?> cls) {
        if (cls == null) {
            return "";
        }
        Package r1 = cls.getPackage();
        String name = r1 != null ? r1.getName() : "";
        return name == null ? "" : name;
    }

    public static String a(View view, View view2) {
        if (view == null) {
            return String.valueOf(0);
        }
        if (view == view2) {
            return String.valueOf(0);
        }
        ViewParent parent = view.getParent();
        if (parent != null && (parent instanceof ViewGroup)) {
            Class<?> cls = view.getClass();
            if (cls == null) {
                return String.valueOf(0);
            }
            String strB = b(cls);
            if (TextUtils.isEmpty(strB)) {
                return String.valueOf(0);
            }
            ViewGroup viewGroup = (ViewGroup) parent;
            int i = 0;
            for (int i2 = 0; i2 < viewGroup.getChildCount(); i2++) {
                View childAt = viewGroup.getChildAt(i2);
                if (childAt != null) {
                    if (childAt == view) {
                        break;
                    }
                    if (childAt.getClass() != null && strB.equals(b(childAt.getClass()))) {
                        i++;
                    }
                }
            }
            return String.valueOf(i);
        }
        return String.valueOf(0);
    }

    private static String d(Class<?> cls) {
        return a(cls, true);
    }

    public static String b(JSONArray jSONArray) {
        if (jSONArray == null || jSONArray.length() == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                JSONObject jSONObject = (JSONObject) jSONArray.get(i);
                sb.append("/" + jSONObject.getString("p") + "[" + jSONObject.getString("i") + "]");
                String strOptString = jSONObject.optString("d");
                if (!TextUtils.isEmpty(strOptString)) {
                    sb.append("#" + strOptString);
                }
            } catch (Exception unused) {
                return "";
            }
        }
        return sb.toString();
    }

    private static String b(String str) {
        String strA = aj.a().a(str);
        if (TextUtils.isEmpty(strA)) {
            strA = ag.a().a(str, ag.a.f3447a);
        }
        return strA == null ? "" : strA;
    }

    public static String a(View view, String str) {
        String strValueOf = "";
        if (TextUtils.isEmpty(str) || view == null) {
            return "";
        }
        RecyclerView parent = view.getParent();
        if (parent != null && (parent instanceof View)) {
            RecyclerView recyclerView = (View) parent;
            try {
                if (ListView.class.getSimpleName().equals(str)) {
                    if ((recyclerView instanceof ListView) && view.getParent() != null) {
                        strValueOf = String.valueOf(((ListView) recyclerView).getPositionForView(view));
                    }
                } else if (GridView.class.getSimpleName().equals(str)) {
                    if ((recyclerView instanceof GridView) && view.getParent() != null) {
                        strValueOf = String.valueOf(((GridView) recyclerView).getPositionForView(view));
                    }
                } else if ("RecyclerView".equals(str)) {
                    strValueOf = String.valueOf(recyclerView.getChildLayoutPosition(view));
                }
            } catch (Throwable unused) {
            }
        }
        return strValueOf;
    }

    public static String b(Class<?> cls) {
        String str;
        if (cls == null) {
            return "";
        }
        String strA = a(cls, false);
        if (TextUtils.isEmpty(strA) || !cls.isAnonymousClass()) {
            str = strA;
        } else {
            str = strA + "$";
        }
        return str == null ? "" : str;
    }

    private static boolean a(View view, Rect rect) {
        if (view == null || rect == null) {
            return false;
        }
        try {
            return view.getGlobalVisibleRect(rect);
        } catch (Exception unused) {
            return false;
        }
    }

    public static JSONArray a(Activity activity, View view) {
        JSONArray jSONArray = new JSONArray();
        if (activity == null || view == null) {
            return jSONArray;
        }
        View viewA = null;
        try {
            viewA = a(activity);
        } catch (Exception unused) {
        }
        if (viewA == null) {
            return jSONArray;
        }
        while (view != null) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("p", f(view));
                String strB = b(view);
                if (TextUtils.isEmpty(strB)) {
                    String strA = "";
                    Object parent = view.getParent();
                    if (parent != null && (parent instanceof View)) {
                        strA = a((View) parent);
                    }
                    strB = a(view, strA);
                    if (TextUtils.isEmpty(strB)) {
                        strB = a(view, viewA);
                    }
                }
                jSONObject.put("i", strB);
                jSONObject.put("t", a(view));
                jSONArray.put(jSONObject);
                Object parent2 = view.getParent();
                if (parent2 == null || view == viewA || !(parent2 instanceof View) || g(view) || jSONArray.length() > 1000) {
                    break;
                    break;
                    break;
                    break;
                    break;
                }
                view = (View) parent2;
            } catch (Exception unused2) {
                jSONArray = new JSONArray();
            }
        }
        JSONArray jSONArray2 = new JSONArray();
        try {
            for (int length = jSONArray.length() - 1; length >= 0; length--) {
                jSONArray2.put(jSONArray.get(length));
            }
        } catch (Exception unused3) {
        }
        return jSONArray2;
    }

    public static String a(JSONArray jSONArray) {
        if (jSONArray == null || jSONArray.length() == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                JSONObject jSONObject = (JSONObject) jSONArray.get(i);
                sb.append("/" + jSONObject.getString("p") + "[" + jSONObject.getString("i") + "]");
            } catch (Exception unused) {
                return "";
            }
        }
        return sb.toString();
    }

    public static String a(String str) {
        String strA = ag.a().a(str, ag.a.f3448b);
        return strA == null ? "" : strA;
    }

    private static String a(Class<?> cls, boolean z) {
        if (!cls.isAnonymousClass()) {
            return z ? cls.getSimpleName() : cls.getName();
        }
        Class<? super Object> superclass = cls.getSuperclass();
        if (superclass != null) {
            return z ? superclass.getSimpleName() : superclass.getName();
        }
        return "";
    }

    public static String a(Context context) {
        ActivityInfo activityInfo;
        if (context == null) {
            return "";
        }
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.HOME");
        PackageManager packageManager = context.getPackageManager();
        if (packageManager == null) {
            return "";
        }
        ResolveInfo resolveInfoResolveActivity = null;
        try {
            resolveInfoResolveActivity = packageManager.resolveActivity(intent, 0);
        } catch (Exception unused) {
        }
        if (resolveInfoResolveActivity == null || (activityInfo = resolveInfoResolveActivity.activityInfo) == null) {
            return "";
        }
        String str = activityInfo.packageName;
        return ("android".equals(str) || TextUtils.isEmpty(str)) ? "" : str;
    }

    public static boolean a(Context context, String str) {
        PackageManager packageManager;
        if (context == null || TextUtils.isEmpty(str) || (packageManager = context.getPackageManager()) == null) {
            return false;
        }
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.HOME");
        List<ResolveInfo> listQueryIntentActivities = null;
        try {
            listQueryIntentActivities = packageManager.queryIntentActivities(intent, 65536);
        } catch (Exception unused) {
        }
        if (listQueryIntentActivities == null) {
            return false;
        }
        Iterator<ResolveInfo> it = listQueryIntentActivities.iterator();
        while (it.hasNext()) {
            ActivityInfo activityInfo = it.next().activityInfo;
            if (activityInfo != null && str.equals(activityInfo.packageName)) {
                return true;
            }
        }
        return false;
    }
}
