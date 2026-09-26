package androidx.core.f.c0;

import android.R;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.core.R$id;
import com.blankj.utilcode.constant.MemoryConstants;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: AccessibilityNodeInfoCompat.java */
/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static int f1062d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final AccessibilityNodeInfo f1063a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f1064b = -1;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f1065c = -1;

    /* JADX INFO: compiled from: AccessibilityNodeInfoCompat.java */
    public static class a {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public static final a f1066d = new a(1, null);

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public static final a f1067e = new a(2, null);

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final Object f1068a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final Class<? extends e.a> f1069b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        protected final e f1070c;

        static {
            new a(4, null);
            new a(8, null);
            new a(16, null);
            new a(32, null);
            new a(64, null);
            new a(128, null);
            new a(256, null, e.b.class);
            new a(512, null, e.b.class);
            new a(1024, null, e.c.class);
            new a(2048, null, e.c.class);
            new a(4096, null);
            new a(8192, null);
            new a(16384, null);
            new a(32768, null);
            new a(65536, null);
            new a(131072, null, e.g.class);
            new a(262144, null);
            new a(524288, null);
            new a(MemoryConstants.MB, null);
            new a(2097152, null, e.h.class);
            new a(Build.VERSION.SDK_INT >= 23 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_SHOW_ON_SCREEN : null, R.id.accessibilityActionShowOnScreen, null, null, null);
            new a(Build.VERSION.SDK_INT >= 23 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_TO_POSITION : null, R.id.accessibilityActionScrollToPosition, null, null, e.C0024e.class);
            new a(Build.VERSION.SDK_INT >= 23 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_UP : null, R.id.accessibilityActionScrollUp, null, null, null);
            new a(Build.VERSION.SDK_INT >= 23 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_LEFT : null, R.id.accessibilityActionScrollLeft, null, null, null);
            new a(Build.VERSION.SDK_INT >= 23 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_DOWN : null, R.id.accessibilityActionScrollDown, null, null, null);
            new a(Build.VERSION.SDK_INT >= 23 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_RIGHT : null, R.id.accessibilityActionScrollRight, null, null, null);
            new a(Build.VERSION.SDK_INT >= 23 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_CONTEXT_CLICK : null, R.id.accessibilityActionContextClick, null, null, null);
            new a(Build.VERSION.SDK_INT >= 24 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_SET_PROGRESS : null, R.id.accessibilityActionSetProgress, null, null, e.f.class);
            new a(Build.VERSION.SDK_INT >= 26 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_MOVE_WINDOW : null, R.id.accessibilityActionMoveWindow, null, null, e.d.class);
            new a(Build.VERSION.SDK_INT >= 28 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_SHOW_TOOLTIP : null, R.id.accessibilityActionShowTooltip, null, null, null);
            new a(Build.VERSION.SDK_INT >= 28 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_HIDE_TOOLTIP : null, R.id.accessibilityActionHideTooltip, null, null, null);
        }

        public a(int i, CharSequence charSequence) {
            this(null, i, charSequence, null, null);
        }

        public int a() {
            if (Build.VERSION.SDK_INT >= 21) {
                return ((AccessibilityNodeInfo.AccessibilityAction) this.f1068a).getId();
            }
            return 0;
        }

        private a(int i, CharSequence charSequence, Class<? extends e.a> cls) {
            this(null, i, charSequence, null, cls);
        }

        a(Object obj, int i, CharSequence charSequence, e eVar, Class<? extends e.a> cls) {
            this.f1070c = eVar;
            if (Build.VERSION.SDK_INT >= 21 && obj == null) {
                this.f1068a = new AccessibilityNodeInfo.AccessibilityAction(i, charSequence);
            } else {
                this.f1068a = obj;
            }
            this.f1069b = cls;
        }

        public boolean a(View view, Bundle bundle) {
            if (this.f1070c == null) {
                return false;
            }
            e.a aVar = null;
            Class<? extends e.a> cls = this.f1069b;
            if (cls != null) {
                try {
                    e.a aVarNewInstance = cls.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
                    try {
                        aVarNewInstance.a(bundle);
                        aVar = aVarNewInstance;
                    } catch (Exception e2) {
                        e = e2;
                        aVar = aVarNewInstance;
                        Class<? extends e.a> cls2 = this.f1069b;
                        Log.e("A11yActionCompat", "Failed to execute command with argument class ViewCommandArgument: " + (cls2 == null ? "null" : cls2.getName()), e);
                    }
                } catch (Exception e3) {
                    e = e3;
                }
            }
            return this.f1070c.a(view, aVar);
        }
    }

    private b(AccessibilityNodeInfo accessibilityNodeInfo) {
        this.f1063a = accessibilityNodeInfo;
    }

    public static b a(AccessibilityNodeInfo accessibilityNodeInfo) {
        return new b(accessibilityNodeInfo);
    }

    private static String b(int i) {
        if (i == 1) {
            return "ACTION_FOCUS";
        }
        if (i == 2) {
            return "ACTION_CLEAR_FOCUS";
        }
        switch (i) {
            case 4:
                return "ACTION_SELECT";
            case 8:
                return "ACTION_CLEAR_SELECTION";
            case 16:
                return "ACTION_CLICK";
            case 32:
                return "ACTION_LONG_CLICK";
            case 64:
                return "ACTION_ACCESSIBILITY_FOCUS";
            case 128:
                return "ACTION_CLEAR_ACCESSIBILITY_FOCUS";
            case 256:
                return "ACTION_NEXT_AT_MOVEMENT_GRANULARITY";
            case 512:
                return "ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY";
            case 1024:
                return "ACTION_NEXT_HTML_ELEMENT";
            case 2048:
                return "ACTION_PREVIOUS_HTML_ELEMENT";
            case 4096:
                return "ACTION_SCROLL_FORWARD";
            case 8192:
                return "ACTION_SCROLL_BACKWARD";
            case 16384:
                return "ACTION_COPY";
            case 32768:
                return "ACTION_PASTE";
            case 65536:
                return "ACTION_CUT";
            case 131072:
                return "ACTION_SET_SELECTION";
            default:
                return "ACTION_UNKNOWN";
        }
    }

    private void v() {
        if (Build.VERSION.SDK_INT >= 19) {
            this.f1063a.getExtras().remove("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_START_KEY");
            this.f1063a.getExtras().remove("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_END_KEY");
            this.f1063a.getExtras().remove("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_FLAGS_KEY");
            this.f1063a.getExtras().remove("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_ID_KEY");
        }
    }

    private boolean w() {
        return !a("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_START_KEY").isEmpty();
    }

    public boolean b(a aVar) {
        if (Build.VERSION.SDK_INT >= 21) {
            return this.f1063a.removeAction((AccessibilityNodeInfo.AccessibilityAction) aVar.f1068a);
        }
        return false;
    }

    public void c(View view) {
        this.f1065c = -1;
        this.f1063a.setSource(view);
    }

    public void d(Rect rect) {
        this.f1063a.setBoundsInScreen(rect);
    }

    public void e(boolean z) {
        this.f1063a.setFocusable(z);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || b.class != obj.getClass()) {
            return false;
        }
        b bVar = (b) obj;
        AccessibilityNodeInfo accessibilityNodeInfo = this.f1063a;
        if (accessibilityNodeInfo == null) {
            if (bVar.f1063a != null) {
                return false;
            }
        } else if (!accessibilityNodeInfo.equals(bVar.f1063a)) {
            return false;
        }
        return this.f1065c == bVar.f1065c && this.f1064b == bVar.f1064b;
    }

    public void f(boolean z) {
        this.f1063a.setFocused(z);
    }

    public String g() {
        if (Build.VERSION.SDK_INT >= 18) {
            return this.f1063a.getViewIdResourceName();
        }
        return null;
    }

    public boolean h() {
        if (Build.VERSION.SDK_INT >= 16) {
            return this.f1063a.isAccessibilityFocused();
        }
        return false;
    }

    public int hashCode() {
        AccessibilityNodeInfo accessibilityNodeInfo = this.f1063a;
        if (accessibilityNodeInfo == null) {
            return 0;
        }
        return accessibilityNodeInfo.hashCode();
    }

    public boolean i() {
        return this.f1063a.isCheckable();
    }

    public boolean j() {
        return this.f1063a.isChecked();
    }

    public void k(boolean z) {
        this.f1063a.setSelected(z);
    }

    public void l(boolean z) {
        if (Build.VERSION.SDK_INT >= 16) {
            this.f1063a.setVisibleToUser(z);
        }
    }

    public boolean m() {
        return this.f1063a.isFocusable();
    }

    public boolean n() {
        return this.f1063a.isFocused();
    }

    public boolean o() {
        return this.f1063a.isLongClickable();
    }

    public boolean p() {
        return this.f1063a.isPassword();
    }

    public boolean q() {
        return this.f1063a.isScrollable();
    }

    public boolean r() {
        return this.f1063a.isSelected();
    }

    public boolean s() {
        if (Build.VERSION.SDK_INT >= 16) {
            return this.f1063a.isVisibleToUser();
        }
        return false;
    }

    public void t() {
        this.f1063a.recycle();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        Rect rect = new Rect();
        a(rect);
        sb.append("; boundsInParent: " + rect);
        b(rect);
        sb.append("; boundsInScreen: " + rect);
        sb.append("; packageName: ");
        sb.append(e());
        sb.append("; className: ");
        sb.append(b());
        sb.append("; text: ");
        sb.append(f());
        sb.append("; contentDescription: ");
        sb.append(c());
        sb.append("; viewId: ");
        sb.append(g());
        sb.append("; checkable: ");
        sb.append(i());
        sb.append("; checked: ");
        sb.append(j());
        sb.append("; focusable: ");
        sb.append(m());
        sb.append("; focused: ");
        sb.append(n());
        sb.append("; selected: ");
        sb.append(r());
        sb.append("; clickable: ");
        sb.append(k());
        sb.append("; longClickable: ");
        sb.append(o());
        sb.append("; enabled: ");
        sb.append(l());
        sb.append("; password: ");
        sb.append(p());
        sb.append("; scrollable: " + q());
        sb.append("; [");
        int iA = a();
        while (iA != 0) {
            int iNumberOfTrailingZeros = 1 << Integer.numberOfTrailingZeros(iA);
            iA &= iNumberOfTrailingZeros ^ (-1);
            sb.append(b(iNumberOfTrailingZeros));
            if (iA != 0) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public AccessibilityNodeInfo u() {
        return this.f1063a;
    }

    public static b a(b bVar) {
        return a(AccessibilityNodeInfo.obtain(bVar.f1063a));
    }

    public void d(boolean z) {
        this.f1063a.setEnabled(z);
    }

    public CharSequence e() {
        return this.f1063a.getPackageName();
    }

    public CharSequence f() {
        if (!w()) {
            return this.f1063a.getText();
        }
        List<Integer> listA = a("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_START_KEY");
        List<Integer> listA2 = a("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_END_KEY");
        List<Integer> listA3 = a("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_FLAGS_KEY");
        List<Integer> listA4 = a("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_ID_KEY");
        SpannableString spannableString = new SpannableString(TextUtils.substring(this.f1063a.getText(), 0, this.f1063a.getText().length()));
        for (int i = 0; i < listA.size(); i++) {
            spannableString.setSpan(new androidx.core.f.c0.a(listA4.get(i).intValue(), this, d().getInt("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_ACTION_ID_KEY")), listA.get(i).intValue(), listA2.get(i).intValue(), listA3.get(i).intValue());
        }
        return spannableString;
    }

    public void i(boolean z) {
        if (Build.VERSION.SDK_INT >= 28) {
            this.f1063a.setScreenReaderFocusable(z);
        } else {
            a(1, z);
        }
    }

    public void j(boolean z) {
        this.f1063a.setScrollable(z);
    }

    public boolean k() {
        return this.f1063a.isClickable();
    }

    private SparseArray<WeakReference<ClickableSpan>> d(View view) {
        SparseArray<WeakReference<ClickableSpan>> sparseArrayE = e(view);
        if (sparseArrayE != null) {
            return sparseArrayE;
        }
        SparseArray<WeakReference<ClickableSpan>> sparseArray = new SparseArray<>();
        view.setTag(R$id.tag_accessibility_clickable_spans, sparseArray);
        return sparseArray;
    }

    private SparseArray<WeakReference<ClickableSpan>> e(View view) {
        return (SparseArray) view.getTag(R$id.tag_accessibility_clickable_spans);
    }

    public void a(View view) {
        this.f1063a.addChild(view);
    }

    public void b(View view) {
        this.f1064b = -1;
        this.f1063a.setParent(view);
    }

    public void c(Rect rect) {
        this.f1063a.setBoundsInParent(rect);
    }

    public void g(boolean z) {
        if (Build.VERSION.SDK_INT >= 28) {
            this.f1063a.setHeading(z);
        } else {
            a(2, z);
        }
    }

    public void h(boolean z) {
        this.f1063a.setLongClickable(z);
    }

    public boolean l() {
        return this.f1063a.isEnabled();
    }

    public static ClickableSpan[] e(CharSequence charSequence) {
        if (charSequence instanceof Spanned) {
            return (ClickableSpan[]) ((Spanned) charSequence).getSpans(0, charSequence.length(), ClickableSpan.class);
        }
        return null;
    }

    public int a() {
        return this.f1063a.getActions();
    }

    public void c(boolean z) {
        this.f1063a.setClickable(z);
    }

    public void a(int i) {
        this.f1063a.addAction(i);
    }

    public void b(Rect rect) {
        this.f1063a.getBoundsInScreen(rect);
    }

    public void c(CharSequence charSequence) {
        this.f1063a.setPackageName(charSequence);
    }

    private List<Integer> a(String str) {
        if (Build.VERSION.SDK_INT < 19) {
            return new ArrayList();
        }
        ArrayList<Integer> integerArrayList = this.f1063a.getExtras().getIntegerArrayList(str);
        if (integerArrayList != null) {
            return integerArrayList;
        }
        ArrayList<Integer> arrayList = new ArrayList<>();
        this.f1063a.getExtras().putIntegerArrayList(str, arrayList);
        return arrayList;
    }

    public CharSequence b() {
        return this.f1063a.getClassName();
    }

    public CharSequence c() {
        return this.f1063a.getContentDescription();
    }

    public Bundle d() {
        if (Build.VERSION.SDK_INT >= 19) {
            return this.f1063a.getExtras();
        }
        return new Bundle();
    }

    public void b(CharSequence charSequence) {
        this.f1063a.setContentDescription(charSequence);
    }

    public void b(boolean z) {
        if (Build.VERSION.SDK_INT >= 19) {
            this.f1063a.setCanOpenPopup(z);
        }
    }

    public void d(CharSequence charSequence) {
        int i = Build.VERSION.SDK_INT;
        if (i >= 28) {
            this.f1063a.setPaneTitle(charSequence);
        } else if (i >= 19) {
            this.f1063a.getExtras().putCharSequence("androidx.view.accessibility.AccessibilityNodeInfoCompat.PANE_TITLE_KEY", charSequence);
        }
    }

    public void a(a aVar) {
        if (Build.VERSION.SDK_INT >= 21) {
            this.f1063a.addAction((AccessibilityNodeInfo.AccessibilityAction) aVar.f1068a);
        }
    }

    public boolean a(int i, Bundle bundle) {
        if (Build.VERSION.SDK_INT >= 16) {
            return this.f1063a.performAction(i, bundle);
        }
        return false;
    }

    private void f(View view) {
        SparseArray<WeakReference<ClickableSpan>> sparseArrayE = e(view);
        if (sparseArrayE != null) {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < sparseArrayE.size(); i++) {
                if (sparseArrayE.valueAt(i).get() == null) {
                    arrayList.add(Integer.valueOf(i));
                }
            }
            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                sparseArrayE.remove(((Integer) arrayList.get(i2)).intValue());
            }
        }
    }

    public void a(Rect rect) {
        this.f1063a.getBoundsInParent(rect);
    }

    public void a(boolean z) {
        if (Build.VERSION.SDK_INT >= 16) {
            this.f1063a.setAccessibilityFocused(z);
        }
    }

    public void a(CharSequence charSequence) {
        this.f1063a.setClassName(charSequence);
    }

    public void a(CharSequence charSequence, View view) {
        int i = Build.VERSION.SDK_INT;
        if (i < 19 || i >= 26) {
            return;
        }
        v();
        f(view);
        ClickableSpan[] clickableSpanArrE = e(charSequence);
        if (clickableSpanArrE == null || clickableSpanArrE.length <= 0) {
            return;
        }
        d().putInt("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_ACTION_ID_KEY", R$id.accessibility_action_clickable_span);
        SparseArray<WeakReference<ClickableSpan>> sparseArrayD = d(view);
        for (int i2 = 0; clickableSpanArrE != null && i2 < clickableSpanArrE.length; i2++) {
            int iA = a(clickableSpanArrE[i2], sparseArrayD);
            sparseArrayD.put(iA, new WeakReference<>(clickableSpanArrE[i2]));
            a(clickableSpanArrE[i2], (Spanned) charSequence, iA);
        }
    }

    private int a(ClickableSpan clickableSpan, SparseArray<WeakReference<ClickableSpan>> sparseArray) {
        if (sparseArray != null) {
            for (int i = 0; i < sparseArray.size(); i++) {
                if (clickableSpan.equals(sparseArray.valueAt(i).get())) {
                    return sparseArray.keyAt(i);
                }
            }
        }
        int i2 = f1062d;
        f1062d = i2 + 1;
        return i2;
    }

    private void a(ClickableSpan clickableSpan, Spanned spanned, int i) {
        a("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_START_KEY").add(Integer.valueOf(spanned.getSpanStart(clickableSpan)));
        a("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_END_KEY").add(Integer.valueOf(spanned.getSpanEnd(clickableSpan)));
        a("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_FLAGS_KEY").add(Integer.valueOf(spanned.getSpanFlags(clickableSpan)));
        a("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_ID_KEY").add(Integer.valueOf(i));
    }

    private void a(int i, boolean z) {
        Bundle bundleD = d();
        if (bundleD != null) {
            int i2 = bundleD.getInt("androidx.view.accessibility.AccessibilityNodeInfoCompat.BOOLEAN_PROPERTY_KEY", 0) & (i ^ (-1));
            if (!z) {
                i = 0;
            }
            bundleD.putInt("androidx.view.accessibility.AccessibilityNodeInfoCompat.BOOLEAN_PROPERTY_KEY", i | i2);
        }
    }
}
