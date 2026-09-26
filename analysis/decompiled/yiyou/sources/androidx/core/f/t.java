package androidx.core.f;

import android.animation.ValueAnimator;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.view.WindowInsets;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import androidx.core.R$id;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: compiled from: ViewCompat.java */
/* JADX INFO: loaded from: classes.dex */
public class t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static Field f1088a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static boolean f1089b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static WeakHashMap<View, String> f1090c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static WeakHashMap<View, x> f1091d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static Field f1092e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static boolean f1093f;
    private static ThreadLocal<Rect> g;

    /* JADX INFO: compiled from: ViewCompat.java */
    static class a implements View.OnApplyWindowInsetsListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ p f1094a;

        a(p pVar) {
            this.f1094a = pVar;
        }

        @Override // android.view.View.OnApplyWindowInsetsListener
        public WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
            return (WindowInsets) b0.a(this.f1094a.a(view, b0.a(windowInsets)));
        }
    }

    /* JADX INFO: compiled from: ViewCompat.java */
    static class b extends f<Boolean> {
        b(int i, Class cls, int i2) {
            super(i, cls, i2);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // androidx.core.f.t.f
        public Boolean a(View view) {
            return Boolean.valueOf(view.isScreenReaderFocusable());
        }
    }

    /* JADX INFO: compiled from: ViewCompat.java */
    static class c extends f<CharSequence> {
        c(int i, Class cls, int i2, int i3) {
            super(i, cls, i2, i3);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // androidx.core.f.t.f
        public CharSequence a(View view) {
            return view.getAccessibilityPaneTitle();
        }
    }

    /* JADX INFO: compiled from: ViewCompat.java */
    static class d extends f<Boolean> {
        d(int i, Class cls, int i2) {
            super(i, cls, i2);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // androidx.core.f.t.f
        public Boolean a(View view) {
            return Boolean.valueOf(view.isAccessibilityHeading());
        }
    }

    /* JADX INFO: compiled from: ViewCompat.java */
    static abstract class f<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final int f1096a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final Class<T> f1097b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final int f1098c;

        f(int i, Class<T> cls, int i2) {
            this(i, cls, 0, i2);
        }

        private boolean a() {
            return Build.VERSION.SDK_INT >= 19;
        }

        abstract T a(View view);

        T b(View view) {
            if (b()) {
                return a(view);
            }
            if (!a()) {
                return null;
            }
            T t = (T) view.getTag(this.f1096a);
            if (this.f1097b.isInstance(t)) {
                return t;
            }
            return null;
        }

        f(int i, Class<T> cls, int i2, int i3) {
            this.f1096a = i;
            this.f1097b = cls;
            this.f1098c = i3;
        }

        private boolean b() {
            return Build.VERSION.SDK_INT >= this.f1098c;
        }
    }

    /* JADX INFO: compiled from: ViewCompat.java */
    public interface g {
        boolean a(View view, KeyEvent keyEvent);
    }

    static {
        new AtomicInteger(1);
        f1091d = null;
        f1093f = false;
        int[] iArr = {R$id.accessibility_custom_action_0, R$id.accessibility_custom_action_1, R$id.accessibility_custom_action_2, R$id.accessibility_custom_action_3, R$id.accessibility_custom_action_4, R$id.accessibility_custom_action_5, R$id.accessibility_custom_action_6, R$id.accessibility_custom_action_7, R$id.accessibility_custom_action_8, R$id.accessibility_custom_action_9, R$id.accessibility_custom_action_10, R$id.accessibility_custom_action_11, R$id.accessibility_custom_action_12, R$id.accessibility_custom_action_13, R$id.accessibility_custom_action_14, R$id.accessibility_custom_action_15, R$id.accessibility_custom_action_16, R$id.accessibility_custom_action_17, R$id.accessibility_custom_action_18, R$id.accessibility_custom_action_19, R$id.accessibility_custom_action_20, R$id.accessibility_custom_action_21, R$id.accessibility_custom_action_22, R$id.accessibility_custom_action_23, R$id.accessibility_custom_action_24, R$id.accessibility_custom_action_25, R$id.accessibility_custom_action_26, R$id.accessibility_custom_action_27, R$id.accessibility_custom_action_28, R$id.accessibility_custom_action_29, R$id.accessibility_custom_action_30, R$id.accessibility_custom_action_31};
        new e();
    }

    public static void a(View view, androidx.core.f.a aVar) {
        if (aVar == null && (b(view) instanceof androidx.core.f.a.C0022a)) {
            aVar = new androidx.core.f.a();
        }
        view.setAccessibilityDelegate(aVar == null ? null : aVar.a());
    }

    private static Rect b() {
        if (g == null) {
            g = new ThreadLocal<>();
        }
        Rect rect = g.get();
        if (rect == null) {
            rect = new Rect();
            g.set(rect);
        }
        rect.setEmpty();
        return rect;
    }

    public static int c(View view) {
        if (Build.VERSION.SDK_INT >= 19) {
            return view.getAccessibilityLiveRegion();
        }
        return 0;
    }

    public static void d(View view, int i) {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 23) {
            view.offsetLeftAndRight(i);
            return;
        }
        if (i2 < 21) {
            a(view, i);
            return;
        }
        Rect rectB = b();
        boolean z = false;
        Object parent = view.getParent();
        if (parent instanceof View) {
            View view2 = (View) parent;
            rectB.set(view2.getLeft(), view2.getTop(), view2.getRight(), view2.getBottom());
            z = !rectB.intersects(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
        }
        a(view, i);
        if (z && rectB.intersect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom())) {
            ((View) parent).invalidate(rectB);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static ColorStateList e(View view) {
        if (Build.VERSION.SDK_INT >= 21) {
            return view.getBackgroundTintList();
        }
        if (view instanceof s) {
            return ((s) view).getSupportBackgroundTintList();
        }
        return null;
    }

    public static void f(View view, int i) {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 19) {
            view.setImportantForAccessibility(i);
        } else if (i2 >= 16) {
            if (i == 4) {
                i = 2;
            }
            view.setImportantForAccessibility(i);
        }
    }

    public static float g(View view) {
        if (Build.VERSION.SDK_INT >= 21) {
            return view.getElevation();
        }
        return 0.0f;
    }

    public static boolean h(View view) {
        if (Build.VERSION.SDK_INT >= 16) {
            return view.getFitsSystemWindows();
        }
        return false;
    }

    public static int i(View view) {
        if (Build.VERSION.SDK_INT >= 16) {
            return view.getImportantForAccessibility();
        }
        return 0;
    }

    public static int j(View view) {
        if (Build.VERSION.SDK_INT >= 17) {
            return view.getLayoutDirection();
        }
        return 0;
    }

    public static int k(View view) {
        if (Build.VERSION.SDK_INT >= 16) {
            return view.getMinimumHeight();
        }
        if (!f1089b) {
            try {
                f1088a = View.class.getDeclaredField("mMinHeight");
                f1088a.setAccessible(true);
            } catch (NoSuchFieldException unused) {
            }
            f1089b = true;
        }
        Field field = f1088a;
        if (field == null) {
            return 0;
        }
        try {
            return ((Integer) field.get(view)).intValue();
        } catch (Exception unused2) {
            return 0;
        }
    }

    public static ViewParent l(View view) {
        return Build.VERSION.SDK_INT >= 16 ? view.getParentForAccessibility() : view.getParent();
    }

    public static String m(View view) {
        if (Build.VERSION.SDK_INT >= 21) {
            return view.getTransitionName();
        }
        WeakHashMap<View, String> weakHashMap = f1090c;
        if (weakHashMap == null) {
            return null;
        }
        return weakHashMap.get(view);
    }

    public static int n(View view) {
        if (Build.VERSION.SDK_INT >= 16) {
            return view.getWindowSystemUiVisibility();
        }
        return 0;
    }

    public static boolean o(View view) {
        if (Build.VERSION.SDK_INT >= 15) {
            return view.hasOnClickListeners();
        }
        return false;
    }

    public static boolean p(View view) {
        Boolean boolB = a().b(view);
        if (boolB == null) {
            return false;
        }
        return boolB.booleanValue();
    }

    public static boolean q(View view) {
        if (Build.VERSION.SDK_INT >= 19) {
            return view.isAttachedToWindow();
        }
        return view.getWindowToken() != null;
    }

    public static boolean r(View view) {
        if (Build.VERSION.SDK_INT >= 19) {
            return view.isLaidOut();
        }
        return view.getWidth() > 0 && view.getHeight() > 0;
    }

    public static boolean s(View view) {
        Boolean boolB = d().b(view);
        if (boolB == null) {
            return false;
        }
        return boolB.booleanValue();
    }

    public static void t(View view) {
        if (Build.VERSION.SDK_INT >= 16) {
            view.postInvalidateOnAnimation();
        } else {
            view.postInvalidate();
        }
    }

    public static void u(View view) {
        int i = Build.VERSION.SDK_INT;
        if (i >= 20) {
            view.requestApplyInsets();
        } else if (i >= 16) {
            view.requestFitSystemWindows();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void v(View view) {
        if (Build.VERSION.SDK_INT >= 21) {
            view.stopNestedScroll();
        } else if (view instanceof j) {
            ((j) view).stopNestedScroll();
        }
    }

    private static void w(View view) {
        float translationY = view.getTranslationY();
        view.setTranslationY(1.0f + translationY);
        view.setTranslationY(translationY);
    }

    /* JADX INFO: compiled from: ViewCompat.java */
    static class e implements ViewTreeObserver.OnGlobalLayoutListener, View.OnAttachStateChangeListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private WeakHashMap<View, Boolean> f1095a = new WeakHashMap<>();

        e() {
        }

        private void a(View view, boolean z) {
            boolean z2 = view.getVisibility() == 0;
            if (z != z2) {
                if (z2) {
                    t.c(view, 16);
                }
                this.f1095a.put(view, Boolean.valueOf(z2));
            }
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            for (Map.Entry<View, Boolean> entry : this.f1095a.entrySet()) {
                a(entry.getKey(), entry.getValue().booleanValue());
            }
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View view) {
            a(view);
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View view) {
        }

        private void a(View view) {
            view.getViewTreeObserver().addOnGlobalLayoutListener(this);
        }
    }

    /* JADX INFO: compiled from: ViewCompat.java */
    static class h {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private static final ArrayList<WeakReference<View>> f1099d = new ArrayList<>();

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private WeakHashMap<View, Boolean> f1100a = null;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private SparseArray<WeakReference<View>> f1101b = null;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private WeakReference<KeyEvent> f1102c = null;

        h() {
        }

        private SparseArray<WeakReference<View>> a() {
            if (this.f1101b == null) {
                this.f1101b = new SparseArray<>();
            }
            return this.f1101b;
        }

        private View b(View view, KeyEvent keyEvent) {
            WeakHashMap<View, Boolean> weakHashMap = this.f1100a;
            if (weakHashMap != null && weakHashMap.containsKey(view)) {
                if (view instanceof ViewGroup) {
                    ViewGroup viewGroup = (ViewGroup) view;
                    for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
                        View viewB = b(viewGroup.getChildAt(childCount), keyEvent);
                        if (viewB != null) {
                            return viewB;
                        }
                    }
                }
                if (c(view, keyEvent)) {
                    return view;
                }
            }
            return null;
        }

        private boolean c(View view, KeyEvent keyEvent) {
            ArrayList arrayList = (ArrayList) view.getTag(R$id.tag_unhandled_key_listeners);
            if (arrayList == null) {
                return false;
            }
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                if (((g) arrayList.get(size)).a(view, keyEvent)) {
                    return true;
                }
            }
            return false;
        }

        static h a(View view) {
            h hVar = (h) view.getTag(R$id.tag_unhandled_key_event_manager);
            if (hVar != null) {
                return hVar;
            }
            h hVar2 = new h();
            view.setTag(R$id.tag_unhandled_key_event_manager, hVar2);
            return hVar2;
        }

        private void b() {
            WeakHashMap<View, Boolean> weakHashMap = this.f1100a;
            if (weakHashMap != null) {
                weakHashMap.clear();
            }
            if (f1099d.isEmpty()) {
                return;
            }
            synchronized (f1099d) {
                if (this.f1100a == null) {
                    this.f1100a = new WeakHashMap<>();
                }
                for (int size = f1099d.size() - 1; size >= 0; size--) {
                    View view = f1099d.get(size).get();
                    if (view == null) {
                        f1099d.remove(size);
                    } else {
                        this.f1100a.put(view, Boolean.TRUE);
                        for (ViewParent parent = view.getParent(); parent instanceof View; parent = parent.getParent()) {
                            this.f1100a.put((View) parent, Boolean.TRUE);
                        }
                    }
                }
            }
        }

        boolean a(View view, KeyEvent keyEvent) {
            if (keyEvent.getAction() == 0) {
                b();
            }
            View viewB = b(view, keyEvent);
            if (keyEvent.getAction() == 0) {
                int keyCode = keyEvent.getKeyCode();
                if (viewB != null && !KeyEvent.isModifierKey(keyCode)) {
                    a().put(keyCode, new WeakReference<>(viewB));
                }
            }
            return viewB != null;
        }

        boolean a(KeyEvent keyEvent) {
            int iIndexOfKey;
            WeakReference<KeyEvent> weakReference = this.f1102c;
            if (weakReference != null && weakReference.get() == keyEvent) {
                return false;
            }
            this.f1102c = new WeakReference<>(keyEvent);
            WeakReference<View> weakReferenceValueAt = null;
            SparseArray<WeakReference<View>> sparseArrayA = a();
            if (keyEvent.getAction() == 1 && (iIndexOfKey = sparseArrayA.indexOfKey(keyEvent.getKeyCode())) >= 0) {
                weakReferenceValueAt = sparseArrayA.valueAt(iIndexOfKey);
                sparseArrayA.removeAt(iIndexOfKey);
            }
            if (weakReferenceValueAt == null) {
                weakReferenceValueAt = sparseArrayA.get(keyEvent.getKeyCode());
            }
            if (weakReferenceValueAt == null) {
                return false;
            }
            View view = weakReferenceValueAt.get();
            if (view != null && t.q(view)) {
                c(view, keyEvent);
            }
            return true;
        }
    }

    private static f<CharSequence> c() {
        return new c(R$id.tag_accessibility_pane_title, CharSequence.class, 8, 28);
    }

    public static void a(View view, Runnable runnable) {
        if (Build.VERSION.SDK_INT >= 16) {
            view.postOnAnimation(runnable);
        } else {
            view.postDelayed(runnable, ValueAnimator.getFrameDelay());
        }
    }

    static void c(View view, int i) {
        if (((AccessibilityManager) view.getContext().getSystemService("accessibility")).isEnabled()) {
            boolean z = d(view) != null;
            if (c(view) == 0 && (!z || view.getVisibility() != 0)) {
                if (view.getParent() != null) {
                    try {
                        view.getParent().notifySubtreeAccessibilityStateChanged(view, view, i);
                        return;
                    } catch (AbstractMethodError e2) {
                        Log.e("ViewCompat", view.getParent().getClass().getSimpleName() + " does not fully implement ViewParent", e2);
                        return;
                    }
                }
                return;
            }
            AccessibilityEvent accessibilityEventObtain = AccessibilityEvent.obtain();
            accessibilityEventObtain.setEventType(z ? 32 : 2048);
            accessibilityEventObtain.setContentChangeTypes(i);
            view.sendAccessibilityEventUnchecked(accessibilityEventObtain);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static PorterDuff.Mode f(View view) {
        if (Build.VERSION.SDK_INT >= 21) {
            return view.getBackgroundTintMode();
        }
        if (view instanceof s) {
            return ((s) view).getSupportBackgroundTintMode();
        }
        return null;
    }

    public static void e(View view, int i) {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 23) {
            view.offsetTopAndBottom(i);
            return;
        }
        if (i2 >= 21) {
            Rect rectB = b();
            boolean z = false;
            Object parent = view.getParent();
            if (parent instanceof View) {
                View view2 = (View) parent;
                rectB.set(view2.getLeft(), view2.getTop(), view2.getRight(), view2.getBottom());
                z = !rectB.intersects(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
            }
            b(view, i);
            if (z && rectB.intersect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom())) {
                ((View) parent).invalidate(rectB);
                return;
            }
            return;
        }
        b(view, i);
    }

    public static void a(View view, Runnable runnable, long j) {
        if (Build.VERSION.SDK_INT >= 16) {
            view.postOnAnimationDelayed(runnable, j);
        } else {
            view.postDelayed(runnable, ValueAnimator.getFrameDelay() + j);
        }
    }

    private static View.AccessibilityDelegate b(View view) {
        if (f1093f) {
            return null;
        }
        if (f1092e == null) {
            try {
                f1092e = View.class.getDeclaredField("mAccessibilityDelegate");
                f1092e.setAccessible(true);
            } catch (Throwable unused) {
                f1093f = true;
                return null;
            }
        }
        try {
            Object obj = f1092e.get(view);
            if (obj instanceof View.AccessibilityDelegate) {
                return (View.AccessibilityDelegate) obj;
            }
            return null;
        } catch (Throwable unused2) {
            f1093f = true;
            return null;
        }
    }

    public static x a(View view) {
        if (f1091d == null) {
            f1091d = new WeakHashMap<>();
        }
        x xVar = f1091d.get(view);
        if (xVar != null) {
            return xVar;
        }
        x xVar2 = new x(view);
        f1091d.put(view, xVar2);
        return xVar2;
    }

    public static void a(View view, float f2) {
        if (Build.VERSION.SDK_INT >= 21) {
            view.setElevation(f2);
        }
    }

    public static void a(View view, String str) {
        if (Build.VERSION.SDK_INT >= 21) {
            view.setTransitionName(str);
            return;
        }
        if (f1090c == null) {
            f1090c = new WeakHashMap<>();
        }
        f1090c.put(view, str);
    }

    public static b0 b(View view, b0 b0Var) {
        if (Build.VERSION.SDK_INT < 21) {
            return b0Var;
        }
        WindowInsets windowInsets = (WindowInsets) b0.a(b0Var);
        WindowInsets windowInsetsOnApplyWindowInsets = view.onApplyWindowInsets(windowInsets);
        if (!windowInsetsOnApplyWindowInsets.equals(windowInsets)) {
            windowInsets = new WindowInsets(windowInsetsOnApplyWindowInsets);
        }
        return b0.a(windowInsets);
    }

    private static f<Boolean> d() {
        return new b(R$id.tag_screen_reader_focusable, Boolean.class, 28);
    }

    public static CharSequence d(View view) {
        return c().b(view);
    }

    public static void a(View view, p pVar) {
        if (Build.VERSION.SDK_INT >= 21) {
            if (pVar == null) {
                view.setOnApplyWindowInsetsListener(null);
            } else {
                view.setOnApplyWindowInsetsListener(new a(pVar));
            }
        }
    }

    private static void b(View view, int i) {
        view.offsetTopAndBottom(i);
        if (view.getVisibility() == 0) {
            w(view);
            Object parent = view.getParent();
            if (parent instanceof View) {
                w((View) parent);
            }
        }
    }

    public static b0 a(View view, b0 b0Var) {
        if (Build.VERSION.SDK_INT < 21) {
            return b0Var;
        }
        WindowInsets windowInsets = (WindowInsets) b0.a(b0Var);
        WindowInsets windowInsetsDispatchApplyWindowInsets = view.dispatchApplyWindowInsets(windowInsets);
        if (!windowInsetsDispatchApplyWindowInsets.equals(windowInsets)) {
            windowInsets = new WindowInsets(windowInsetsDispatchApplyWindowInsets);
        }
        return b0.a(windowInsets);
    }

    static boolean b(View view, KeyEvent keyEvent) {
        if (Build.VERSION.SDK_INT >= 28) {
            return false;
        }
        return h.a(view).a(keyEvent);
    }

    public static void a(View view, Drawable drawable) {
        if (Build.VERSION.SDK_INT >= 16) {
            view.setBackground(drawable);
        } else {
            view.setBackgroundDrawable(drawable);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void a(View view, ColorStateList colorStateList) {
        if (Build.VERSION.SDK_INT >= 21) {
            view.setBackgroundTintList(colorStateList);
            if (Build.VERSION.SDK_INT == 21) {
                Drawable background = view.getBackground();
                boolean z = (view.getBackgroundTintList() == null && view.getBackgroundTintMode() == null) ? false : true;
                if (background == null || !z) {
                    return;
                }
                if (background.isStateful()) {
                    background.setState(view.getDrawableState());
                }
                view.setBackground(background);
                return;
            }
            return;
        }
        if (view instanceof s) {
            ((s) view).setSupportBackgroundTintList(colorStateList);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void a(View view, PorterDuff.Mode mode) {
        if (Build.VERSION.SDK_INT >= 21) {
            view.setBackgroundTintMode(mode);
            if (Build.VERSION.SDK_INT == 21) {
                Drawable background = view.getBackground();
                boolean z = (view.getBackgroundTintList() == null && view.getBackgroundTintMode() == null) ? false : true;
                if (background == null || !z) {
                    return;
                }
                if (background.isStateful()) {
                    background.setState(view.getDrawableState());
                }
                view.setBackground(background);
                return;
            }
            return;
        }
        if (view instanceof s) {
            ((s) view).setSupportBackgroundTintMode(mode);
        }
    }

    private static void a(View view, int i) {
        view.offsetLeftAndRight(i);
        if (view.getVisibility() == 0) {
            w(view);
            Object parent = view.getParent();
            if (parent instanceof View) {
                w((View) parent);
            }
        }
    }

    public static void a(View view, int i, int i2) {
        if (Build.VERSION.SDK_INT >= 23) {
            view.setScrollIndicators(i, i2);
        }
    }

    static boolean a(View view, KeyEvent keyEvent) {
        if (Build.VERSION.SDK_INT >= 28) {
            return false;
        }
        return h.a(view).a(view, keyEvent);
    }

    private static f<Boolean> a() {
        return new d(R$id.tag_accessibility_heading, Boolean.class, 28);
    }
}
