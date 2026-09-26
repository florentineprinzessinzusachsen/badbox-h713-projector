package androidx.appcompat.app;

import android.R;
import android.app.Activity;
import android.app.Dialog;
import android.app.UiModeManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;
import android.text.TextUtils;
import android.util.AndroidRuntimeException;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.ActionMode;
import android.view.ContextThemeWrapper;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.KeyboardShortcutGroup;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.appcompat.R$attr;
import androidx.appcompat.R$color;
import androidx.appcompat.R$id;
import androidx.appcompat.R$layout;
import androidx.appcompat.R$style;
import androidx.appcompat.R$styleable;
import androidx.appcompat.widget.ActionBarContextView;
import androidx.appcompat.widget.ContentFrameLayout;
import androidx.appcompat.widget.ViewStubCompat;
import androidx.appcompat.widget.d0;
import androidx.appcompat.widget.i0;
import androidx.appcompat.widget.j0;
import androidx.appcompat.widget.q;
import androidx.core.f.b0;
import androidx.core.f.p;
import androidx.core.f.t;
import androidx.core.f.x;
import androidx.core.f.y;
import androidx.core.f.z;
import java.util.List;
import java.util.Map;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: classes.dex */
class AppCompatDelegateImpl extends androidx.appcompat.app.f implements androidx.appcompat.view.menu.g.a, LayoutInflater.Factory2 {
    private static final Map<Class<?>, Integer> b0 = new a.b.a();
    private static final boolean c0;
    private static final int[] d0;
    private static boolean e0;
    private static final boolean f0;
    boolean A;
    boolean B;
    boolean C;
    boolean D;
    boolean F;
    private boolean G;
    private n[] H;
    private n I;
    private boolean J;
    private boolean K;
    private boolean L;
    private boolean M;
    boolean N;
    private int O;
    private int P;
    private boolean Q;
    private boolean R;
    private l S;
    private l T;
    boolean U;
    int V;
    private final Runnable W;
    private boolean X;
    private Rect Y;
    private Rect Z;
    private AppCompatViewInflater a0;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final Object f254d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    final Context f255e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    Window f256f;
    private j g;
    final androidx.appcompat.app.e h;
    androidx.appcompat.app.a i;
    MenuInflater j;
    private CharSequence k;
    private androidx.appcompat.widget.n l;
    private h m;
    private o n;
    androidx.appcompat.d.b o;
    ActionBarContextView p;
    PopupWindow q;
    Runnable r;
    x s;
    private boolean t;
    private boolean u;
    private ViewGroup v;
    private TextView w;
    private View x;
    private boolean y;
    private boolean z;

    private class ListMenuDecorView extends ContentFrameLayout {
        public ListMenuDecorView(Context context) {
            super(context);
        }

        private boolean a(int i, int i2) {
            return i < -5 || i2 < -5 || i > getWidth() + 5 || i2 > getHeight() + 5;
        }

        @Override // android.view.ViewGroup, android.view.View
        public boolean dispatchKeyEvent(KeyEvent keyEvent) {
            return AppCompatDelegateImpl.this.a(keyEvent) || super.dispatchKeyEvent(keyEvent);
        }

        @Override // android.view.ViewGroup
        public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
            if (motionEvent.getAction() != 0 || !a((int) motionEvent.getX(), (int) motionEvent.getY())) {
                return super.onInterceptTouchEvent(motionEvent);
            }
            AppCompatDelegateImpl.this.e(0);
            return true;
        }

        @Override // android.view.View
        public void setBackgroundResource(int i) {
            setBackgroundDrawable(androidx.appcompat.a.a.a.c(getContext(), i));
        }
    }

    static class a implements Thread.UncaughtExceptionHandler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Thread.UncaughtExceptionHandler f257a;

        a(Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
            this.f257a = uncaughtExceptionHandler;
        }

        private boolean a(Throwable th) {
            String message;
            if (!(th instanceof Resources.NotFoundException) || (message = th.getMessage()) == null) {
                return false;
            }
            return message.contains("drawable") || message.contains("Drawable");
        }

        @Override // java.lang.Thread.UncaughtExceptionHandler
        public void uncaughtException(Thread thread, Throwable th) {
            if (!a(th)) {
                this.f257a.uncaughtException(thread, th);
                return;
            }
            Resources.NotFoundException notFoundException = new Resources.NotFoundException(th.getMessage() + ". If the resource you are trying to use is a vector resource, you may be referencing it in an unsupported way. See AppCompatDelegate.setCompatVectorFromResourcesEnabled() for more info.");
            notFoundException.initCause(th.getCause());
            notFoundException.setStackTrace(th.getStackTrace());
            this.f257a.uncaughtException(thread, notFoundException);
        }
    }

    class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            AppCompatDelegateImpl appCompatDelegateImpl = AppCompatDelegateImpl.this;
            if ((appCompatDelegateImpl.V & 1) != 0) {
                appCompatDelegateImpl.f(0);
            }
            AppCompatDelegateImpl appCompatDelegateImpl2 = AppCompatDelegateImpl.this;
            if ((appCompatDelegateImpl2.V & 4096) != 0) {
                appCompatDelegateImpl2.f(108);
            }
            AppCompatDelegateImpl appCompatDelegateImpl3 = AppCompatDelegateImpl.this;
            appCompatDelegateImpl3.U = false;
            appCompatDelegateImpl3.V = 0;
        }
    }

    class c implements p {
        c() {
        }

        @Override // androidx.core.f.p
        public b0 a(View view, b0 b0Var) {
            int iD = b0Var.d();
            int iJ = AppCompatDelegateImpl.this.j(iD);
            if (iD != iJ) {
                b0Var = b0Var.a(b0Var.b(), iJ, b0Var.c(), b0Var.a());
            }
            return t.b(view, b0Var);
        }
    }

    class d implements q.a {
        d() {
        }

        @Override // androidx.appcompat.widget.q.a
        public void a(Rect rect) {
            rect.top = AppCompatDelegateImpl.this.j(rect.top);
        }
    }

    class e implements ContentFrameLayout.a {
        e() {
        }

        @Override // androidx.appcompat.widget.ContentFrameLayout.a
        public void a() {
        }

        @Override // androidx.appcompat.widget.ContentFrameLayout.a
        public void onDetachedFromWindow() {
            AppCompatDelegateImpl.this.l();
        }
    }

    class f implements Runnable {

        class a extends z {
            a() {
            }

            @Override // androidx.core.f.y
            public void a(View view) {
                AppCompatDelegateImpl.this.p.setAlpha(1.0f);
                AppCompatDelegateImpl.this.s.a((y) null);
                AppCompatDelegateImpl.this.s = null;
            }

            @Override // androidx.core.f.z, androidx.core.f.y
            public void b(View view) {
                AppCompatDelegateImpl.this.p.setVisibility(0);
            }
        }

        f() {
        }

        @Override // java.lang.Runnable
        public void run() {
            AppCompatDelegateImpl appCompatDelegateImpl = AppCompatDelegateImpl.this;
            appCompatDelegateImpl.q.showAtLocation(appCompatDelegateImpl.p, 55, 0, 0);
            AppCompatDelegateImpl.this.m();
            if (!AppCompatDelegateImpl.this.u()) {
                AppCompatDelegateImpl.this.p.setAlpha(1.0f);
                AppCompatDelegateImpl.this.p.setVisibility(0);
                return;
            }
            AppCompatDelegateImpl.this.p.setAlpha(0.0f);
            AppCompatDelegateImpl appCompatDelegateImpl2 = AppCompatDelegateImpl.this;
            x xVarA = t.a(appCompatDelegateImpl2.p);
            xVarA.a(1.0f);
            appCompatDelegateImpl2.s = xVarA;
            AppCompatDelegateImpl.this.s.a(new a());
        }
    }

    class g extends z {
        g() {
        }

        @Override // androidx.core.f.y
        public void a(View view) {
            AppCompatDelegateImpl.this.p.setAlpha(1.0f);
            AppCompatDelegateImpl.this.s.a((y) null);
            AppCompatDelegateImpl.this.s = null;
        }

        @Override // androidx.core.f.z, androidx.core.f.y
        public void b(View view) {
            AppCompatDelegateImpl.this.p.setVisibility(0);
            AppCompatDelegateImpl.this.p.sendAccessibilityEvent(32);
            if (AppCompatDelegateImpl.this.p.getParent() instanceof View) {
                t.u((View) AppCompatDelegateImpl.this.p.getParent());
            }
        }
    }

    class i implements androidx.appcompat.d.b.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private androidx.appcompat.d.b.a f266a;

        class a extends z {
            a() {
            }

            @Override // androidx.core.f.y
            public void a(View view) {
                AppCompatDelegateImpl.this.p.setVisibility(8);
                AppCompatDelegateImpl appCompatDelegateImpl = AppCompatDelegateImpl.this;
                PopupWindow popupWindow = appCompatDelegateImpl.q;
                if (popupWindow != null) {
                    popupWindow.dismiss();
                } else if (appCompatDelegateImpl.p.getParent() instanceof View) {
                    t.u((View) AppCompatDelegateImpl.this.p.getParent());
                }
                AppCompatDelegateImpl.this.p.removeAllViews();
                AppCompatDelegateImpl.this.s.a((y) null);
                AppCompatDelegateImpl.this.s = null;
            }
        }

        public i(androidx.appcompat.d.b.a aVar) {
            this.f266a = aVar;
        }

        @Override // androidx.appcompat.d.b.a
        public boolean a(androidx.appcompat.d.b bVar, Menu menu) {
            return this.f266a.a(bVar, menu);
        }

        @Override // androidx.appcompat.d.b.a
        public boolean b(androidx.appcompat.d.b bVar, Menu menu) {
            return this.f266a.b(bVar, menu);
        }

        @Override // androidx.appcompat.d.b.a
        public boolean a(androidx.appcompat.d.b bVar, MenuItem menuItem) {
            return this.f266a.a(bVar, menuItem);
        }

        @Override // androidx.appcompat.d.b.a
        public void a(androidx.appcompat.d.b bVar) {
            this.f266a.a(bVar);
            AppCompatDelegateImpl appCompatDelegateImpl = AppCompatDelegateImpl.this;
            if (appCompatDelegateImpl.q != null) {
                appCompatDelegateImpl.f256f.getDecorView().removeCallbacks(AppCompatDelegateImpl.this.r);
            }
            AppCompatDelegateImpl appCompatDelegateImpl2 = AppCompatDelegateImpl.this;
            if (appCompatDelegateImpl2.p != null) {
                appCompatDelegateImpl2.m();
                AppCompatDelegateImpl appCompatDelegateImpl3 = AppCompatDelegateImpl.this;
                x xVarA = t.a(appCompatDelegateImpl3.p);
                xVarA.a(0.0f);
                appCompatDelegateImpl3.s = xVarA;
                AppCompatDelegateImpl.this.s.a(new a());
            }
            AppCompatDelegateImpl appCompatDelegateImpl4 = AppCompatDelegateImpl.this;
            androidx.appcompat.app.e eVar = appCompatDelegateImpl4.h;
            if (eVar != null) {
                eVar.b(appCompatDelegateImpl4.o);
            }
            AppCompatDelegateImpl.this.o = null;
        }
    }

    private class k extends l {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final PowerManager f270c;

        k(Context context) {
            super();
            this.f270c = (PowerManager) context.getSystemService("power");
        }

        @Override // androidx.appcompat.app.AppCompatDelegateImpl.l
        IntentFilter b() {
            if (Build.VERSION.SDK_INT < 21) {
                return null;
            }
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.os.action.POWER_SAVE_MODE_CHANGED");
            return intentFilter;
        }

        @Override // androidx.appcompat.app.AppCompatDelegateImpl.l
        public int c() {
            return (Build.VERSION.SDK_INT < 21 || !this.f270c.isPowerSaveMode()) ? 1 : 2;
        }

        @Override // androidx.appcompat.app.AppCompatDelegateImpl.l
        public void d() {
            AppCompatDelegateImpl.this.k();
        }
    }

    abstract class l {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private BroadcastReceiver f272a;

        class a extends BroadcastReceiver {
            a() {
            }

            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                l.this.d();
            }
        }

        l() {
        }

        void a() {
            BroadcastReceiver broadcastReceiver = this.f272a;
            if (broadcastReceiver != null) {
                try {
                    AppCompatDelegateImpl.this.f255e.unregisterReceiver(broadcastReceiver);
                } catch (IllegalArgumentException unused) {
                }
                this.f272a = null;
            }
        }

        abstract IntentFilter b();

        abstract int c();

        abstract void d();

        void e() {
            a();
            IntentFilter intentFilterB = b();
            if (intentFilterB == null || intentFilterB.countActions() == 0) {
                return;
            }
            if (this.f272a == null) {
                this.f272a = new a();
            }
            AppCompatDelegateImpl.this.f255e.registerReceiver(this.f272a, intentFilterB);
        }
    }

    private class m extends l {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final androidx.appcompat.app.j f275c;

        m(androidx.appcompat.app.j jVar) {
            super();
            this.f275c = jVar;
        }

        @Override // androidx.appcompat.app.AppCompatDelegateImpl.l
        IntentFilter b() {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.TIME_SET");
            intentFilter.addAction("android.intent.action.TIMEZONE_CHANGED");
            intentFilter.addAction("android.intent.action.TIME_TICK");
            return intentFilter;
        }

        @Override // androidx.appcompat.app.AppCompatDelegateImpl.l
        public int c() {
            return this.f275c.a() ? 2 : 1;
        }

        @Override // androidx.appcompat.app.AppCompatDelegateImpl.l
        public void d() {
            AppCompatDelegateImpl.this.k();
        }
    }

    static {
        boolean z = false;
        c0 = Build.VERSION.SDK_INT < 21;
        d0 = new int[]{R.attr.windowBackground};
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 21 && i2 <= 25) {
            z = true;
        }
        f0 = z;
        if (!c0 || e0) {
            return;
        }
        Thread.setDefaultUncaughtExceptionHandler(new a(Thread.getDefaultUncaughtExceptionHandler()));
        e0 = true;
    }

    AppCompatDelegateImpl(Activity activity, androidx.appcompat.app.e eVar) {
        this(activity, null, eVar, activity);
    }

    private void A() {
        if (this.f256f == null) {
            Object obj = this.f254d;
            if (obj instanceof Activity) {
                a(((Activity) obj).getWindow());
            }
        }
        if (this.f256f == null) {
            throw new IllegalStateException("We have not been given a Window");
        }
    }

    private l B() {
        if (this.T == null) {
            this.T = new k(this.f255e);
        }
        return this.T;
    }

    private void C() {
        z();
        if (this.A && this.i == null) {
            Object obj = this.f254d;
            if (obj instanceof Activity) {
                this.i = new androidx.appcompat.app.k((Activity) obj, this.B);
            } else if (obj instanceof Dialog) {
                this.i = new androidx.appcompat.app.k((Dialog) obj);
            }
            androidx.appcompat.app.a aVar = this.i;
            if (aVar != null) {
                aVar.c(this.X);
            }
        }
    }

    private boolean D() {
        if (!this.R && (this.f254d instanceof Activity)) {
            PackageManager packageManager = this.f255e.getPackageManager();
            if (packageManager == null) {
                return false;
            }
            try {
                ActivityInfo activityInfo = packageManager.getActivityInfo(new ComponentName(this.f255e, this.f254d.getClass()), 0);
                this.Q = (activityInfo == null || (activityInfo.configChanges & 512) == 0) ? false : true;
            } catch (PackageManager.NameNotFoundException e2) {
                Log.d("AppCompatDelegate", "Exception while getting ActivityInfo", e2);
                this.Q = false;
            }
        }
        this.R = true;
        return this.Q;
    }

    private void E() {
        if (this.u) {
            throw new AndroidRuntimeException("Window feature must be requested before adding content");
        }
    }

    private androidx.appcompat.app.d F() {
        for (Context baseContext = this.f255e; baseContext != null; baseContext = ((ContextWrapper) baseContext).getBaseContext()) {
            if (baseContext instanceof androidx.appcompat.app.d) {
                return (androidx.appcompat.app.d) baseContext;
            }
            if (!(baseContext instanceof ContextWrapper)) {
                break;
            }
        }
        return null;
    }

    private void k(int i2) {
        this.V = (1 << i2) | this.V;
        if (this.U) {
            return;
        }
        t.a(this.f256f.getDecorView(), this.W);
        this.U = true;
    }

    private int l(int i2) {
        if (i2 == 8) {
            Log.i("AppCompatDelegate", "You should now use the AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR id when requesting this feature.");
            return 108;
        }
        if (i2 != 9) {
            return i2;
        }
        Log.i("AppCompatDelegate", "You should now use the AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR_OVERLAY id when requesting this feature.");
        return 109;
    }

    private void v() {
        ContentFrameLayout contentFrameLayout = (ContentFrameLayout) this.v.findViewById(R.id.content);
        View decorView = this.f256f.getDecorView();
        contentFrameLayout.a(decorView.getPaddingLeft(), decorView.getPaddingTop(), decorView.getPaddingRight(), decorView.getPaddingBottom());
        TypedArray typedArrayObtainStyledAttributes = this.f255e.obtainStyledAttributes(R$styleable.AppCompatTheme);
        typedArrayObtainStyledAttributes.getValue(R$styleable.AppCompatTheme_windowMinWidthMajor, contentFrameLayout.getMinWidthMajor());
        typedArrayObtainStyledAttributes.getValue(R$styleable.AppCompatTheme_windowMinWidthMinor, contentFrameLayout.getMinWidthMinor());
        if (typedArrayObtainStyledAttributes.hasValue(R$styleable.AppCompatTheme_windowFixedWidthMajor)) {
            typedArrayObtainStyledAttributes.getValue(R$styleable.AppCompatTheme_windowFixedWidthMajor, contentFrameLayout.getFixedWidthMajor());
        }
        if (typedArrayObtainStyledAttributes.hasValue(R$styleable.AppCompatTheme_windowFixedWidthMinor)) {
            typedArrayObtainStyledAttributes.getValue(R$styleable.AppCompatTheme_windowFixedWidthMinor, contentFrameLayout.getFixedWidthMinor());
        }
        if (typedArrayObtainStyledAttributes.hasValue(R$styleable.AppCompatTheme_windowFixedHeightMajor)) {
            typedArrayObtainStyledAttributes.getValue(R$styleable.AppCompatTheme_windowFixedHeightMajor, contentFrameLayout.getFixedHeightMajor());
        }
        if (typedArrayObtainStyledAttributes.hasValue(R$styleable.AppCompatTheme_windowFixedHeightMinor)) {
            typedArrayObtainStyledAttributes.getValue(R$styleable.AppCompatTheme_windowFixedHeightMinor, contentFrameLayout.getFixedHeightMinor());
        }
        typedArrayObtainStyledAttributes.recycle();
        contentFrameLayout.requestLayout();
    }

    private int w() {
        int i2 = this.O;
        return i2 != -100 ? i2 : androidx.appcompat.app.f.j();
    }

    private void x() {
        l lVar = this.S;
        if (lVar != null) {
            lVar.a();
        }
        l lVar2 = this.T;
        if (lVar2 != null) {
            lVar2.a();
        }
    }

    private ViewGroup y() {
        ViewGroup viewGroup;
        TypedArray typedArrayObtainStyledAttributes = this.f255e.obtainStyledAttributes(R$styleable.AppCompatTheme);
        if (!typedArrayObtainStyledAttributes.hasValue(R$styleable.AppCompatTheme_windowActionBar)) {
            typedArrayObtainStyledAttributes.recycle();
            throw new IllegalStateException("You need to use a Theme.AppCompat theme (or descendant) with this activity.");
        }
        if (typedArrayObtainStyledAttributes.getBoolean(R$styleable.AppCompatTheme_windowNoTitle, false)) {
            b(1);
        } else if (typedArrayObtainStyledAttributes.getBoolean(R$styleable.AppCompatTheme_windowActionBar, false)) {
            b(108);
        }
        if (typedArrayObtainStyledAttributes.getBoolean(R$styleable.AppCompatTheme_windowActionBarOverlay, false)) {
            b(109);
        }
        if (typedArrayObtainStyledAttributes.getBoolean(R$styleable.AppCompatTheme_windowActionModeOverlay, false)) {
            b(10);
        }
        this.D = typedArrayObtainStyledAttributes.getBoolean(R$styleable.AppCompatTheme_android_windowIsFloating, false);
        typedArrayObtainStyledAttributes.recycle();
        A();
        this.f256f.getDecorView();
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(this.f255e);
        if (this.F) {
            viewGroup = this.C ? (ViewGroup) layoutInflaterFrom.inflate(R$layout.abc_screen_simple_overlay_action_mode, (ViewGroup) null) : (ViewGroup) layoutInflaterFrom.inflate(R$layout.abc_screen_simple, (ViewGroup) null);
            if (Build.VERSION.SDK_INT >= 21) {
                t.a(viewGroup, new c());
            } else {
                ((q) viewGroup).setOnFitSystemWindowsListener(new d());
            }
        } else if (this.D) {
            viewGroup = (ViewGroup) layoutInflaterFrom.inflate(R$layout.abc_dialog_title_material, (ViewGroup) null);
            this.B = false;
            this.A = false;
        } else if (this.A) {
            TypedValue typedValue = new TypedValue();
            this.f255e.getTheme().resolveAttribute(R$attr.actionBarTheme, typedValue, true);
            int i2 = typedValue.resourceId;
            viewGroup = (ViewGroup) LayoutInflater.from(i2 != 0 ? new androidx.appcompat.d.d(this.f255e, i2) : this.f255e).inflate(R$layout.abc_screen_toolbar, (ViewGroup) null);
            this.l = (androidx.appcompat.widget.n) viewGroup.findViewById(R$id.decor_content_parent);
            this.l.setWindowCallback(q());
            if (this.B) {
                this.l.a(109);
            }
            if (this.y) {
                this.l.a(2);
            }
            if (this.z) {
                this.l.a(5);
            }
        } else {
            viewGroup = null;
        }
        if (viewGroup == null) {
            throw new IllegalArgumentException("AppCompat does not support the current theme features: { windowActionBar: " + this.A + ", windowActionBarOverlay: " + this.B + ", android:windowIsFloating: " + this.D + ", windowActionModeOverlay: " + this.C + ", windowNoTitle: " + this.F + " }");
        }
        if (this.l == null) {
            this.w = (TextView) viewGroup.findViewById(R$id.title);
        }
        j0.b(viewGroup);
        ContentFrameLayout contentFrameLayout = (ContentFrameLayout) viewGroup.findViewById(R$id.action_bar_activity_content);
        ViewGroup viewGroup2 = (ViewGroup) this.f256f.findViewById(R.id.content);
        if (viewGroup2 != null) {
            while (viewGroup2.getChildCount() > 0) {
                View childAt = viewGroup2.getChildAt(0);
                viewGroup2.removeViewAt(0);
                contentFrameLayout.addView(childAt);
            }
            viewGroup2.setId(-1);
            contentFrameLayout.setId(R.id.content);
            if (viewGroup2 instanceof FrameLayout) {
                ((FrameLayout) viewGroup2).setForeground(null);
            }
        }
        this.f256f.setContentView(viewGroup);
        contentFrameLayout.setAttachListener(new e());
        return viewGroup;
    }

    private void z() {
        if (this.u) {
            return;
        }
        this.v = y();
        CharSequence charSequenceP = p();
        if (!TextUtils.isEmpty(charSequenceP)) {
            androidx.appcompat.widget.n nVar = this.l;
            if (nVar != null) {
                nVar.setWindowTitle(charSequenceP);
            } else if (t() != null) {
                t().a(charSequenceP);
            } else {
                TextView textView = this.w;
                if (textView != null) {
                    textView.setText(charSequenceP);
                }
            }
        }
        v();
        a(this.v);
        this.u = true;
        n nVarA = a(0, false);
        if (this.N) {
            return;
        }
        if (nVarA == null || nVarA.j == null) {
            k(108);
        }
    }

    @Override // androidx.appcompat.app.f
    public void a(Context context) {
        a(false);
        this.K = true;
    }

    void a(ViewGroup viewGroup) {
    }

    @Override // androidx.appcompat.app.f
    public void b(Bundle bundle) {
        z();
    }

    @Override // androidx.appcompat.app.f
    public androidx.appcompat.app.a c() {
        C();
        return this.i;
    }

    @Override // androidx.appcompat.app.f
    public void d(int i2) {
        this.P = i2;
    }

    @Override // androidx.appcompat.app.f
    public void e() {
        androidx.appcompat.app.a aVarC = c();
        if (aVarC == null || !aVarC.i()) {
            k(0);
        }
    }

    @Override // androidx.appcompat.app.f
    public void f() {
        androidx.appcompat.app.f.b(this);
        if (this.U) {
            this.f256f.getDecorView().removeCallbacks(this.W);
        }
        this.M = false;
        this.N = true;
        androidx.appcompat.app.a aVar = this.i;
        if (aVar != null) {
            aVar.j();
        }
        x();
    }

    @Override // androidx.appcompat.app.f
    public void g() {
        androidx.appcompat.app.a aVarC = c();
        if (aVarC != null) {
            aVarC.d(true);
        }
    }

    @Override // androidx.appcompat.app.f
    public void h() {
        this.M = true;
        k();
        androidx.appcompat.app.f.a(this);
    }

    @Override // androidx.appcompat.app.f
    public void i() {
        this.M = false;
        androidx.appcompat.app.f.b(this);
        androidx.appcompat.app.a aVarC = c();
        if (aVarC != null) {
            aVarC.d(false);
        }
        if (this.f254d instanceof Dialog) {
            x();
        }
    }

    int j(int i2) {
        boolean z;
        boolean z2;
        ActionBarContextView actionBarContextView = this.p;
        if (actionBarContextView == null || !(actionBarContextView.getLayoutParams() instanceof ViewGroup.MarginLayoutParams)) {
            z = false;
        } else {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.p.getLayoutParams();
            if (this.p.isShown()) {
                if (this.Y == null) {
                    this.Y = new Rect();
                    this.Z = new Rect();
                }
                Rect rect = this.Y;
                Rect rect2 = this.Z;
                rect.set(0, i2, 0, 0);
                j0.a(this.v, rect, rect2);
                if (marginLayoutParams.topMargin != (rect2.top == 0 ? i2 : 0)) {
                    marginLayoutParams.topMargin = i2;
                    View view = this.x;
                    if (view == null) {
                        this.x = new View(this.f255e);
                        this.x.setBackgroundColor(this.f255e.getResources().getColor(R$color.abc_input_method_navigation_guard));
                        this.v.addView(this.x, -1, new ViewGroup.LayoutParams(-1, i2));
                    } else {
                        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                        if (layoutParams.height != i2) {
                            layoutParams.height = i2;
                            this.x.setLayoutParams(layoutParams);
                        }
                    }
                    z2 = true;
                } else {
                    z2 = false;
                }
                z = this.x != null;
                if (!this.C && z) {
                    i2 = 0;
                }
            } else {
                if (marginLayoutParams.topMargin != 0) {
                    marginLayoutParams.topMargin = 0;
                    z2 = true;
                } else {
                    z2 = false;
                }
                z = false;
            }
            if (z2) {
                this.p.setLayoutParams(marginLayoutParams);
            }
        }
        View view2 = this.x;
        if (view2 != null) {
            view2.setVisibility(z ? 0 : 8);
        }
        return i2;
    }

    void m() {
        x xVar = this.s;
        if (xVar != null) {
            xVar.a();
        }
    }

    final Context n() {
        androidx.appcompat.app.a aVarC = c();
        Context contextH = aVarC != null ? aVarC.h() : null;
        return contextH == null ? this.f255e : contextH;
    }

    final l o() {
        if (this.S == null) {
            this.S = new m(androidx.appcompat.app.j.a(this.f255e));
        }
        return this.S;
    }

    @Override // android.view.LayoutInflater.Factory2
    public final View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        return a(view, str, context, attributeSet);
    }

    final CharSequence p() {
        Object obj = this.f254d;
        return obj instanceof Activity ? ((Activity) obj).getTitle() : this.k;
    }

    final Window.Callback q() {
        return this.f256f.getCallback();
    }

    public boolean r() {
        return this.t;
    }

    boolean s() {
        androidx.appcompat.d.b bVar = this.o;
        if (bVar != null) {
            bVar.a();
            return true;
        }
        androidx.appcompat.app.a aVarC = c();
        return aVarC != null && aVarC.f();
    }

    final androidx.appcompat.app.a t() {
        return this.i;
    }

    final boolean u() {
        ViewGroup viewGroup;
        return this.u && (viewGroup = this.v) != null && t.r(viewGroup);
    }

    private final class h implements androidx.appcompat.view.menu.m.a {
        h() {
        }

        @Override // androidx.appcompat.view.menu.m.a
        public boolean a(androidx.appcompat.view.menu.g gVar) {
            Window.Callback callbackQ = AppCompatDelegateImpl.this.q();
            if (callbackQ == null) {
                return true;
            }
            callbackQ.onMenuOpened(108, gVar);
            return true;
        }

        @Override // androidx.appcompat.view.menu.m.a
        public void a(androidx.appcompat.view.menu.g gVar, boolean z) {
            AppCompatDelegateImpl.this.b(gVar);
        }
    }

    AppCompatDelegateImpl(Dialog dialog, androidx.appcompat.app.e eVar) {
        this(dialog.getContext(), dialog.getWindow(), eVar, dialog);
    }

    @Override // androidx.appcompat.app.f
    public MenuInflater b() {
        if (this.j == null) {
            C();
            androidx.appcompat.app.a aVar = this.i;
            this.j = new androidx.appcompat.d.g(aVar != null ? aVar.h() : this.f255e);
        }
        return this.j;
    }

    @Override // androidx.appcompat.app.f
    public void d() {
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(this.f255e);
        if (layoutInflaterFrom.getFactory() == null) {
            androidx.core.f.e.b(layoutInflaterFrom, this);
        } else {
            if (layoutInflaterFrom.getFactory2() instanceof AppCompatDelegateImpl) {
                return;
            }
            Log.i("AppCompatDelegate", "The Activity's LayoutInflater already has a Factory installed so we can not install AppCompat's");
        }
    }

    @Override // android.view.LayoutInflater.Factory
    public View onCreateView(String str, Context context, AttributeSet attributeSet) {
        return onCreateView(null, str, context, attributeSet);
    }

    protected static final class n {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        int f277a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int f278b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f279c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        int f280d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        int f281e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        int f282f;
        ViewGroup g;
        View h;
        View i;
        androidx.appcompat.view.menu.g j;
        androidx.appcompat.view.menu.e k;
        Context l;
        boolean m;
        boolean n;
        boolean o;
        public boolean p;
        boolean q = false;
        boolean r;
        Bundle s;

        n(int i) {
            this.f277a = i;
        }

        public boolean a() {
            if (this.h == null) {
                return false;
            }
            return this.i != null || this.k.b().getCount() > 0;
        }

        void a(Context context) {
            TypedValue typedValue = new TypedValue();
            Resources.Theme themeNewTheme = context.getResources().newTheme();
            themeNewTheme.setTo(context.getTheme());
            themeNewTheme.resolveAttribute(R$attr.actionBarPopupTheme, typedValue, true);
            int i = typedValue.resourceId;
            if (i != 0) {
                themeNewTheme.applyStyle(i, true);
            }
            themeNewTheme.resolveAttribute(R$attr.panelMenuListTheme, typedValue, true);
            int i2 = typedValue.resourceId;
            if (i2 != 0) {
                themeNewTheme.applyStyle(i2, true);
            } else {
                themeNewTheme.applyStyle(R$style.Theme_AppCompat_CompactMenu, true);
            }
            androidx.appcompat.d.d dVar = new androidx.appcompat.d.d(context, 0);
            dVar.getTheme().setTo(themeNewTheme);
            this.l = dVar;
            TypedArray typedArrayObtainStyledAttributes = dVar.obtainStyledAttributes(R$styleable.AppCompatTheme);
            this.f278b = typedArrayObtainStyledAttributes.getResourceId(R$styleable.AppCompatTheme_panelBackground, 0);
            this.f282f = typedArrayObtainStyledAttributes.getResourceId(R$styleable.AppCompatTheme_android_windowAnimationStyle, 0);
            typedArrayObtainStyledAttributes.recycle();
        }

        void a(androidx.appcompat.view.menu.g gVar) {
            androidx.appcompat.view.menu.e eVar;
            androidx.appcompat.view.menu.g gVar2 = this.j;
            if (gVar == gVar2) {
                return;
            }
            if (gVar2 != null) {
                gVar2.b(this.k);
            }
            this.j = gVar;
            if (gVar == null || (eVar = this.k) == null) {
                return;
            }
            gVar.a(eVar);
        }

        androidx.appcompat.view.menu.n a(androidx.appcompat.view.menu.m.a aVar) {
            if (this.j == null) {
                return null;
            }
            if (this.k == null) {
                this.k = new androidx.appcompat.view.menu.e(this.l, R$layout.abc_list_menu_item_layout);
                this.k.a(aVar);
                this.j.a(this.k);
            }
            return this.k.a(this.g);
        }
    }

    private AppCompatDelegateImpl(Context context, Window window, androidx.appcompat.app.e eVar, Object obj) {
        Integer num;
        androidx.appcompat.app.d dVarF;
        this.s = null;
        this.t = true;
        this.O = -100;
        this.W = new b();
        this.f255e = context;
        this.h = eVar;
        this.f254d = obj;
        if (this.O == -100 && (this.f254d instanceof Dialog) && (dVarF = F()) != null) {
            this.O = dVarF.j().a();
        }
        if (this.O == -100 && (num = b0.get(this.f254d.getClass())) != null) {
            this.O = num.intValue();
            b0.remove(this.f254d.getClass());
        }
        if (window != null) {
            a(window);
        }
        androidx.appcompat.widget.e.c();
    }

    @Override // androidx.appcompat.app.f
    public void a(Bundle bundle) {
        this.K = true;
        a(false);
        A();
        Object obj = this.f254d;
        if (obj instanceof Activity) {
            String strB = null;
            try {
                strB = androidx.core.app.f.b((Activity) obj);
            } catch (IllegalArgumentException unused) {
            }
            if (strB != null) {
                androidx.appcompat.app.a aVarT = t();
                if (aVarT == null) {
                    this.X = true;
                } else {
                    aVarT.c(true);
                }
            }
        }
        this.L = true;
    }

    @Override // androidx.appcompat.app.f
    public void c(int i2) {
        z();
        ViewGroup viewGroup = (ViewGroup) this.v.findViewById(R.id.content);
        viewGroup.removeAllViews();
        LayoutInflater.from(this.f255e).inflate(i2, viewGroup);
        this.g.a().onContentChanged();
    }

    int g(int i2) {
        if (i2 == -100) {
            return -1;
        }
        if (i2 == -1) {
            return i2;
        }
        if (i2 == 0) {
            if (Build.VERSION.SDK_INT < 23 || ((UiModeManager) this.f255e.getSystemService(UiModeManager.class)).getNightMode() != 0) {
                return o().c();
            }
            return -1;
        }
        if (i2 == 1 || i2 == 2) {
            return i2;
        }
        if (i2 == 3) {
            return B().c();
        }
        throw new IllegalStateException("Unknown value set for night mode. Please use one of the MODE_NIGHT values from AppCompatDelegate.");
    }

    void l() {
        androidx.appcompat.view.menu.g gVar;
        androidx.appcompat.widget.n nVar = this.l;
        if (nVar != null) {
            nVar.g();
        }
        if (this.q != null) {
            this.f256f.getDecorView().removeCallbacks(this.r);
            if (this.q.isShowing()) {
                try {
                    this.q.dismiss();
                } catch (IllegalArgumentException unused) {
                }
            }
            this.q = null;
        }
        m();
        n nVarA = a(0, false);
        if (nVarA == null || (gVar = nVarA.j) == null) {
            return;
        }
        gVar.close();
    }

    class j extends androidx.appcompat.d.i {
        j(Window.Callback callback) {
            super(callback);
        }

        final ActionMode a(ActionMode.Callback callback) {
            androidx.appcompat.d.f.a aVar = new androidx.appcompat.d.f.a(AppCompatDelegateImpl.this.f255e, callback);
            androidx.appcompat.d.b bVarA = AppCompatDelegateImpl.this.a(aVar);
            if (bVarA != null) {
                return aVar.b(bVarA);
            }
            return null;
        }

        @Override // androidx.appcompat.d.i, android.view.Window.Callback
        public boolean dispatchKeyEvent(KeyEvent keyEvent) {
            return AppCompatDelegateImpl.this.a(keyEvent) || super.dispatchKeyEvent(keyEvent);
        }

        @Override // androidx.appcompat.d.i, android.view.Window.Callback
        public boolean dispatchKeyShortcutEvent(KeyEvent keyEvent) {
            return super.dispatchKeyShortcutEvent(keyEvent) || AppCompatDelegateImpl.this.b(keyEvent.getKeyCode(), keyEvent);
        }

        @Override // android.view.Window.Callback
        public void onContentChanged() {
        }

        @Override // androidx.appcompat.d.i, android.view.Window.Callback
        public boolean onCreatePanelMenu(int i, Menu menu) {
            if (i != 0 || (menu instanceof androidx.appcompat.view.menu.g)) {
                return super.onCreatePanelMenu(i, menu);
            }
            return false;
        }

        @Override // androidx.appcompat.d.i, android.view.Window.Callback
        public boolean onMenuOpened(int i, Menu menu) {
            super.onMenuOpened(i, menu);
            AppCompatDelegateImpl.this.h(i);
            return true;
        }

        @Override // androidx.appcompat.d.i, android.view.Window.Callback
        public void onPanelClosed(int i, Menu menu) {
            super.onPanelClosed(i, menu);
            AppCompatDelegateImpl.this.i(i);
        }

        @Override // androidx.appcompat.d.i, android.view.Window.Callback
        public boolean onPreparePanel(int i, View view, Menu menu) {
            androidx.appcompat.view.menu.g gVar = menu instanceof androidx.appcompat.view.menu.g ? (androidx.appcompat.view.menu.g) menu : null;
            if (i == 0 && gVar == null) {
                return false;
            }
            if (gVar != null) {
                gVar.c(true);
            }
            boolean zOnPreparePanel = super.onPreparePanel(i, view, menu);
            if (gVar != null) {
                gVar.c(false);
            }
            return zOnPreparePanel;
        }

        @Override // androidx.appcompat.d.i, android.view.Window.Callback
        public void onProvideKeyboardShortcuts(List<KeyboardShortcutGroup> list, Menu menu, int i) {
            androidx.appcompat.view.menu.g gVar;
            n nVarA = AppCompatDelegateImpl.this.a(0, true);
            if (nVarA == null || (gVar = nVarA.j) == null) {
                super.onProvideKeyboardShortcuts(list, menu, i);
            } else {
                super.onProvideKeyboardShortcuts(list, gVar, i);
            }
        }

        @Override // androidx.appcompat.d.i, android.view.Window.Callback
        public ActionMode onWindowStartingActionMode(ActionMode.Callback callback) {
            if (Build.VERSION.SDK_INT >= 23) {
                return null;
            }
            return AppCompatDelegateImpl.this.r() ? a(callback) : super.onWindowStartingActionMode(callback);
        }

        @Override // androidx.appcompat.d.i, android.view.Window.Callback
        public ActionMode onWindowStartingActionMode(ActionMode.Callback callback, int i) {
            if (AppCompatDelegateImpl.this.r() && i == 0) {
                return a(callback);
            }
            return super.onWindowStartingActionMode(callback, i);
        }
    }

    void e(int i2) {
        a(a(i2, true), true);
    }

    void h(int i2) {
        androidx.appcompat.app.a aVarC;
        if (i2 != 108 || (aVarC = c()) == null) {
            return;
        }
        aVarC.b(true);
    }

    private final class o implements androidx.appcompat.view.menu.m.a {
        o() {
        }

        @Override // androidx.appcompat.view.menu.m.a
        public void a(androidx.appcompat.view.menu.g gVar, boolean z) {
            androidx.appcompat.view.menu.g gVarM = gVar.m();
            boolean z2 = gVarM != gVar;
            AppCompatDelegateImpl appCompatDelegateImpl = AppCompatDelegateImpl.this;
            if (z2) {
                gVar = gVarM;
            }
            n nVarA = appCompatDelegateImpl.a((Menu) gVar);
            if (nVarA != null) {
                if (!z2) {
                    AppCompatDelegateImpl.this.a(nVarA, z);
                } else {
                    AppCompatDelegateImpl.this.a(nVarA.f277a, nVarA, gVarM);
                    AppCompatDelegateImpl.this.a(nVarA, true);
                }
            }
        }

        @Override // androidx.appcompat.view.menu.m.a
        public boolean a(androidx.appcompat.view.menu.g gVar) {
            Window.Callback callbackQ;
            if (gVar != null) {
                return true;
            }
            AppCompatDelegateImpl appCompatDelegateImpl = AppCompatDelegateImpl.this;
            if (!appCompatDelegateImpl.A || (callbackQ = appCompatDelegateImpl.q()) == null || AppCompatDelegateImpl.this.N) {
                return true;
            }
            callbackQ.onMenuOpened(108, gVar);
            return true;
        }
    }

    /* JADX WARN: Code duplicated, block: B:34:0x0063  */
    private boolean e(int i2, KeyEvent keyEvent) {
        boolean zC;
        boolean zB;
        androidx.appcompat.widget.n nVar;
        if (this.o != null) {
            return false;
        }
        n nVarA = a(i2, true);
        if (i2 == 0 && (nVar = this.l) != null && nVar.f() && !ViewConfiguration.get(this.f255e).hasPermanentMenuKey()) {
            if (!this.l.b()) {
                if (this.N || !b(nVarA, keyEvent)) {
                    zC = false;
                } else {
                    zC = this.l.d();
                }
            } else {
                zC = this.l.c();
            }
        } else if (!nVarA.o && !nVarA.n) {
            if (nVarA.m) {
                if (nVarA.r) {
                    nVarA.m = false;
                    zB = b(nVarA, keyEvent);
                } else {
                    zB = true;
                }
                if (zB) {
                    a(nVarA, keyEvent);
                    zC = true;
                } else {
                    zC = false;
                }
            } else {
                zC = false;
            }
        } else {
            zC = nVarA.o;
            a(nVarA, true);
        }
        if (zC) {
            AudioManager audioManager = (AudioManager) this.f255e.getSystemService("audio");
            if (audioManager != null) {
                audioManager.playSoundEffect(0);
            } else {
                Log.w("AppCompatDelegate", "Couldn't get audio manager");
            }
        }
        return zC;
    }

    public boolean k() {
        return a(true);
    }

    private boolean d(int i2, KeyEvent keyEvent) {
        if (keyEvent.getRepeatCount() != 0) {
            return false;
        }
        n nVarA = a(i2, true);
        if (nVarA.o) {
            return false;
        }
        return b(nVarA, keyEvent);
    }

    @Override // androidx.appcompat.app.f
    public void b(View view, ViewGroup.LayoutParams layoutParams) {
        z();
        ViewGroup viewGroup = (ViewGroup) this.v.findViewById(R.id.content);
        viewGroup.removeAllViews();
        viewGroup.addView(view, layoutParams);
        this.g.a().onContentChanged();
    }

    void i(int i2) {
        if (i2 == 108) {
            androidx.appcompat.app.a aVarC = c();
            if (aVarC != null) {
                aVarC.b(false);
                return;
            }
            return;
        }
        if (i2 == 0) {
            n nVarA = a(i2, true);
            if (nVarA.o) {
                a(nVarA, false);
            }
        }
    }

    @Override // androidx.appcompat.app.f
    public void c(Bundle bundle) {
        if (this.O != -100) {
            b0.put(this.f254d.getClass(), Integer.valueOf(this.O));
        }
    }

    void f(int i2) {
        n nVarA;
        n nVarA2 = a(i2, true);
        if (nVarA2.j != null) {
            Bundle bundle = new Bundle();
            nVarA2.j.b(bundle);
            if (bundle.size() > 0) {
                nVarA2.s = bundle;
            }
            nVarA2.j.s();
            nVarA2.j.clear();
        }
        nVarA2.r = true;
        nVarA2.q = true;
        if ((i2 != 108 && i2 != 0) || this.l == null || (nVarA = a(0, false)) == null) {
            return;
        }
        nVarA.m = false;
        b(nVarA, (KeyEvent) null);
    }

    boolean c(int i2, KeyEvent keyEvent) {
        if (i2 == 4) {
            boolean z = this.J;
            this.J = false;
            n nVarA = a(0, false);
            if (nVarA != null && nVarA.o) {
                if (!z) {
                    a(nVarA, true);
                }
                return true;
            }
            if (s()) {
                return true;
            }
        } else if (i2 == 82) {
            e(0, keyEvent);
            return true;
        }
        return false;
    }

    @Override // androidx.appcompat.app.f
    public <T extends View> T a(int i2) {
        z();
        return (T) this.f256f.findViewById(i2);
    }

    @Override // androidx.appcompat.app.f
    public boolean b(int i2) {
        int iL = l(i2);
        if (this.F && iL == 108) {
            return false;
        }
        if (this.A && iL == 1) {
            this.A = false;
        }
        if (iL == 1) {
            E();
            this.F = true;
            return true;
        }
        if (iL == 2) {
            E();
            this.y = true;
            return true;
        }
        if (iL == 5) {
            E();
            this.z = true;
            return true;
        }
        if (iL == 10) {
            E();
            this.C = true;
            return true;
        }
        if (iL == 108) {
            E();
            this.A = true;
            return true;
        }
        if (iL != 109) {
            return this.f256f.requestFeature(iL);
        }
        E();
        this.B = true;
        return true;
    }

    @Override // androidx.appcompat.app.f
    public void a(Configuration configuration) {
        androidx.appcompat.app.a aVarC;
        if (this.A && this.u && (aVarC = c()) != null) {
            aVarC.a(configuration);
        }
        androidx.appcompat.widget.e.b().a(this.f255e);
        a(false);
    }

    private boolean c(n nVar) {
        Context context = this.f255e;
        int i2 = nVar.f277a;
        if ((i2 == 0 || i2 == 108) && this.l != null) {
            TypedValue typedValue = new TypedValue();
            Resources.Theme theme = context.getTheme();
            theme.resolveAttribute(R$attr.actionBarTheme, typedValue, true);
            Resources.Theme themeNewTheme = null;
            if (typedValue.resourceId != 0) {
                themeNewTheme = context.getResources().newTheme();
                themeNewTheme.setTo(theme);
                themeNewTheme.applyStyle(typedValue.resourceId, true);
                themeNewTheme.resolveAttribute(R$attr.actionBarWidgetTheme, typedValue, true);
            } else {
                theme.resolveAttribute(R$attr.actionBarWidgetTheme, typedValue, true);
            }
            if (typedValue.resourceId != 0) {
                if (themeNewTheme == null) {
                    themeNewTheme = context.getResources().newTheme();
                    themeNewTheme.setTo(theme);
                }
                themeNewTheme.applyStyle(typedValue.resourceId, true);
            }
            if (themeNewTheme != null) {
                androidx.appcompat.d.d dVar = new androidx.appcompat.d.d(context, 0);
                dVar.getTheme().setTo(themeNewTheme);
                context = dVar;
            }
        }
        androidx.appcompat.view.menu.g gVar = new androidx.appcompat.view.menu.g(context);
        gVar.a(this);
        nVar.a(gVar);
        return true;
    }

    @Override // androidx.appcompat.app.f
    public void a(View view) {
        z();
        ViewGroup viewGroup = (ViewGroup) this.v.findViewById(R.id.content);
        viewGroup.removeAllViews();
        viewGroup.addView(view);
        this.g.a().onContentChanged();
    }

    @Override // androidx.appcompat.app.f
    public void a(View view, ViewGroup.LayoutParams layoutParams) {
        z();
        ((ViewGroup) this.v.findViewById(R.id.content)).addView(view, layoutParams);
        this.g.a().onContentChanged();
    }

    private void a(Window window) {
        if (this.f256f == null) {
            Window.Callback callback = window.getCallback();
            if (!(callback instanceof j)) {
                this.g = new j(callback);
                window.setCallback(this.g);
                d0 d0VarA = d0.a(this.f255e, (AttributeSet) null, d0);
                Drawable drawableC = d0VarA.c(0);
                if (drawableC != null) {
                    window.setBackgroundDrawable(drawableC);
                }
                d0VarA.a();
                this.f256f = window;
                return;
            }
            throw new IllegalStateException("AppCompat has already installed itself into the Window");
        }
        throw new IllegalStateException("AppCompat has already installed itself into the Window");
    }

    androidx.appcompat.d.b b(androidx.appcompat.d.b.a aVar) {
        androidx.appcompat.d.b bVarA;
        Context dVar;
        androidx.appcompat.app.e eVar;
        m();
        androidx.appcompat.d.b bVar = this.o;
        if (bVar != null) {
            bVar.a();
        }
        if (!(aVar instanceof i)) {
            aVar = new i(aVar);
        }
        androidx.appcompat.app.e eVar2 = this.h;
        if (eVar2 == null || this.N) {
            bVarA = null;
        } else {
            try {
                bVarA = eVar2.a(aVar);
            } catch (AbstractMethodError unused) {
                bVarA = null;
            }
        }
        if (bVarA != null) {
            this.o = bVarA;
        } else {
            if (this.p == null) {
                if (this.D) {
                    TypedValue typedValue = new TypedValue();
                    Resources.Theme theme = this.f255e.getTheme();
                    theme.resolveAttribute(R$attr.actionBarTheme, typedValue, true);
                    if (typedValue.resourceId != 0) {
                        Resources.Theme themeNewTheme = this.f255e.getResources().newTheme();
                        themeNewTheme.setTo(theme);
                        themeNewTheme.applyStyle(typedValue.resourceId, true);
                        dVar = new androidx.appcompat.d.d(this.f255e, 0);
                        dVar.getTheme().setTo(themeNewTheme);
                    } else {
                        dVar = this.f255e;
                    }
                    this.p = new ActionBarContextView(dVar);
                    this.q = new PopupWindow(dVar, (AttributeSet) null, R$attr.actionModePopupWindowStyle);
                    androidx.core.widget.h.a(this.q, 2);
                    this.q.setContentView(this.p);
                    this.q.setWidth(-1);
                    dVar.getTheme().resolveAttribute(R$attr.actionBarSize, typedValue, true);
                    this.p.setContentHeight(TypedValue.complexToDimensionPixelSize(typedValue.data, dVar.getResources().getDisplayMetrics()));
                    this.q.setHeight(-2);
                    this.r = new f();
                } else {
                    ViewStubCompat viewStubCompat = (ViewStubCompat) this.v.findViewById(R$id.action_mode_bar_stub);
                    if (viewStubCompat != null) {
                        viewStubCompat.setLayoutInflater(LayoutInflater.from(n()));
                        this.p = (ActionBarContextView) viewStubCompat.a();
                    }
                }
            }
            if (this.p != null) {
                m();
                this.p.c();
                androidx.appcompat.d.e eVar3 = new androidx.appcompat.d.e(this.p.getContext(), this.p, aVar, this.q == null);
                if (aVar.b(eVar3, eVar3.c())) {
                    eVar3.i();
                    this.p.a(eVar3);
                    this.o = eVar3;
                    if (u()) {
                        this.p.setAlpha(0.0f);
                        x xVarA = t.a(this.p);
                        xVarA.a(1.0f);
                        this.s = xVarA;
                        this.s.a(new g());
                    } else {
                        this.p.setAlpha(1.0f);
                        this.p.setVisibility(0);
                        this.p.sendAccessibilityEvent(32);
                        if (this.p.getParent() instanceof View) {
                            t.u((View) this.p.getParent());
                        }
                    }
                    if (this.q != null) {
                        this.f256f.getDecorView().post(this.r);
                    }
                } else {
                    this.o = null;
                }
            }
        }
        androidx.appcompat.d.b bVar2 = this.o;
        if (bVar2 != null && (eVar = this.h) != null) {
            eVar.a(bVar2);
        }
        return this.o;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void c(int i2, boolean z) {
        Resources resources = this.f255e.getResources();
        Configuration configuration = new Configuration(resources.getConfiguration());
        configuration.uiMode = i2 | (resources.getConfiguration().uiMode & (-49));
        resources.updateConfiguration(configuration, null);
        if (Build.VERSION.SDK_INT < 26) {
            androidx.appcompat.app.h.a(resources);
        }
        int i3 = this.P;
        if (i3 != 0) {
            this.f255e.setTheme(i3);
            if (Build.VERSION.SDK_INT >= 23) {
                this.f255e.getTheme().applyStyle(this.P, true);
            }
        }
        if (z) {
            Object obj = this.f254d;
            if (obj instanceof Activity) {
                Activity activity = (Activity) obj;
                if (activity instanceof androidx.lifecycle.h) {
                    if (((androidx.lifecycle.h) activity).a().a().a(androidx.lifecycle.e.b.STARTED)) {
                        activity.onConfigurationChanged(configuration);
                    }
                } else if (this.M) {
                    activity.onConfigurationChanged(configuration);
                }
            }
        }
    }

    @Override // androidx.appcompat.app.f
    public final void a(CharSequence charSequence) {
        this.k = charSequence;
        androidx.appcompat.widget.n nVar = this.l;
        if (nVar != null) {
            nVar.setWindowTitle(charSequence);
            return;
        }
        if (t() != null) {
            t().a(charSequence);
            return;
        }
        TextView textView = this.w;
        if (textView != null) {
            textView.setText(charSequence);
        }
    }

    @Override // androidx.appcompat.view.menu.g.a
    public boolean a(androidx.appcompat.view.menu.g gVar, MenuItem menuItem) {
        n nVarA;
        Window.Callback callbackQ = q();
        if (callbackQ == null || this.N || (nVarA = a((Menu) gVar.m())) == null) {
            return false;
        }
        return callbackQ.onMenuItemSelected(nVarA.f277a, menuItem);
    }

    @Override // androidx.appcompat.view.menu.g.a
    public void a(androidx.appcompat.view.menu.g gVar) {
        a(gVar, true);
    }

    public androidx.appcompat.d.b a(androidx.appcompat.d.b.a aVar) {
        androidx.appcompat.app.e eVar;
        if (aVar != null) {
            androidx.appcompat.d.b bVar = this.o;
            if (bVar != null) {
                bVar.a();
            }
            i iVar = new i(aVar);
            androidx.appcompat.app.a aVarC = c();
            if (aVarC != null) {
                this.o = aVarC.a(iVar);
                androidx.appcompat.d.b bVar2 = this.o;
                if (bVar2 != null && (eVar = this.h) != null) {
                    eVar.a(bVar2);
                }
            }
            if (this.o == null) {
                this.o = b(iVar);
            }
            return this.o;
        }
        throw new IllegalArgumentException("ActionMode callback can not be null.");
    }

    boolean a(KeyEvent keyEvent) {
        View decorView;
        Object obj = this.f254d;
        if (((obj instanceof androidx.core.f.d.a) || (obj instanceof androidx.appcompat.app.g)) && (decorView = this.f256f.getDecorView()) != null && androidx.core.f.d.a(decorView, keyEvent)) {
            return true;
        }
        if (keyEvent.getKeyCode() == 82 && this.g.a().dispatchKeyEvent(keyEvent)) {
            return true;
        }
        int keyCode = keyEvent.getKeyCode();
        return keyEvent.getAction() == 0 ? a(keyCode, keyEvent) : c(keyCode, keyEvent);
    }

    boolean a(int i2, KeyEvent keyEvent) {
        if (i2 == 4) {
            this.J = (keyEvent.getFlags() & 128) != 0;
        } else if (i2 == 82) {
            d(0, keyEvent);
            return true;
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public View a(View view, String str, Context context, AttributeSet attributeSet) {
        boolean z;
        boolean zA = false;
        if (this.a0 == null) {
            String string = this.f255e.obtainStyledAttributes(R$styleable.AppCompatTheme).getString(R$styleable.AppCompatTheme_viewInflaterClass);
            if (string != null && !AppCompatViewInflater.class.getName().equals(string)) {
                try {
                    this.a0 = (AppCompatViewInflater) Class.forName(string).getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
                } catch (Throwable th) {
                    Log.i("AppCompatDelegate", "Failed to instantiate custom view inflater " + string + ". Falling back to default.", th);
                    this.a0 = new AppCompatViewInflater();
                }
            } else {
                this.a0 = new AppCompatViewInflater();
            }
        }
        if (c0) {
            if (attributeSet instanceof XmlPullParser) {
                if (((XmlPullParser) attributeSet).getDepth() > 1) {
                    zA = true;
                }
            } else {
                zA = a((ViewParent) view);
            }
            z = zA;
        } else {
            z = false;
        }
        return this.a0.a(view, str, context, attributeSet, z, c0, true, i0.b());
    }

    boolean b(int i2, KeyEvent keyEvent) {
        androidx.appcompat.app.a aVarC = c();
        if (aVarC != null && aVarC.a(i2, keyEvent)) {
            return true;
        }
        n nVar = this.I;
        if (nVar != null && a(nVar, keyEvent.getKeyCode(), keyEvent, 1)) {
            n nVar2 = this.I;
            if (nVar2 != null) {
                nVar2.n = true;
            }
            return true;
        }
        if (this.I == null) {
            n nVarA = a(0, true);
            b(nVarA, keyEvent);
            boolean zA = a(nVarA, keyEvent.getKeyCode(), keyEvent, 1);
            nVarA.m = false;
            if (zA) {
                return true;
            }
        }
        return false;
    }

    private boolean a(ViewParent viewParent) {
        if (viewParent == null) {
            return false;
        }
        View decorView = this.f256f.getDecorView();
        while (viewParent != null) {
            if (viewParent == decorView || !(viewParent instanceof View) || t.q((View) viewParent)) {
                return false;
            }
            viewParent = viewParent.getParent();
        }
        return true;
    }

    private void a(n nVar, KeyEvent keyEvent) {
        int i2;
        ViewGroup.LayoutParams layoutParams;
        if (nVar.o || this.N) {
            return;
        }
        if (nVar.f277a == 0) {
            if ((this.f255e.getResources().getConfiguration().screenLayout & 15) == 4) {
                return;
            }
        }
        Window.Callback callbackQ = q();
        if (callbackQ != null && !callbackQ.onMenuOpened(nVar.f277a, nVar.j)) {
            a(nVar, true);
            return;
        }
        WindowManager windowManager = (WindowManager) this.f255e.getSystemService("window");
        if (windowManager != null && b(nVar, keyEvent)) {
            if (nVar.g != null && !nVar.q) {
                View view = nVar.i;
                if (view != null && (layoutParams = view.getLayoutParams()) != null && layoutParams.width == -1) {
                    i2 = -1;
                }
                nVar.n = false;
                WindowManager.LayoutParams layoutParams2 = new WindowManager.LayoutParams(i2, -2, nVar.f280d, nVar.f281e, 1002, 8519680, -3);
                layoutParams2.gravity = nVar.f279c;
                layoutParams2.windowAnimations = nVar.f282f;
                windowManager.addView(nVar.g, layoutParams2);
                nVar.o = true;
            }
            ViewGroup viewGroup = nVar.g;
            if (viewGroup == null) {
                if (!b(nVar) || nVar.g == null) {
                    return;
                }
            } else if (nVar.q && viewGroup.getChildCount() > 0) {
                nVar.g.removeAllViews();
            }
            if (!a(nVar) || !nVar.a()) {
                return;
            }
            ViewGroup.LayoutParams layoutParams3 = nVar.h.getLayoutParams();
            if (layoutParams3 == null) {
                layoutParams3 = new ViewGroup.LayoutParams(-2, -2);
            }
            nVar.g.setBackgroundResource(nVar.f278b);
            ViewParent parent = nVar.h.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(nVar.h);
            }
            nVar.g.addView(nVar.h, layoutParams3);
            if (!nVar.h.hasFocus()) {
                nVar.h.requestFocus();
            }
            i2 = -2;
            nVar.n = false;
            WindowManager.LayoutParams layoutParams4 = new WindowManager.LayoutParams(i2, -2, nVar.f280d, nVar.f281e, 1002, 8519680, -3);
            layoutParams4.gravity = nVar.f279c;
            layoutParams4.windowAnimations = nVar.f282f;
            windowManager.addView(nVar.g, layoutParams4);
            nVar.o = true;
        }
    }

    private boolean b(n nVar) {
        nVar.a(n());
        nVar.g = new ListMenuDecorView(nVar.l);
        nVar.f279c = 81;
        return true;
    }

    private boolean b(n nVar, KeyEvent keyEvent) {
        androidx.appcompat.widget.n nVar2;
        androidx.appcompat.widget.n nVar3;
        androidx.appcompat.widget.n nVar4;
        if (this.N) {
            return false;
        }
        if (nVar.m) {
            return true;
        }
        n nVar5 = this.I;
        if (nVar5 != null && nVar5 != nVar) {
            a(nVar5, false);
        }
        Window.Callback callbackQ = q();
        if (callbackQ != null) {
            nVar.i = callbackQ.onCreatePanelView(nVar.f277a);
        }
        int i2 = nVar.f277a;
        boolean z = i2 == 0 || i2 == 108;
        if (z && (nVar4 = this.l) != null) {
            nVar4.e();
        }
        if (nVar.i == null) {
            if (z) {
                t();
            }
            if (nVar.j == null || nVar.r) {
                if (nVar.j == null && (!c(nVar) || nVar.j == null)) {
                    return false;
                }
                if (z && this.l != null) {
                    if (this.m == null) {
                        this.m = new h();
                    }
                    this.l.a(nVar.j, this.m);
                }
                nVar.j.s();
                if (!callbackQ.onCreatePanelMenu(nVar.f277a, nVar.j)) {
                    nVar.a((androidx.appcompat.view.menu.g) null);
                    if (z && (nVar2 = this.l) != null) {
                        nVar2.a(null, this.m);
                    }
                    return false;
                }
                nVar.r = false;
            }
            nVar.j.s();
            Bundle bundle = nVar.s;
            if (bundle != null) {
                nVar.j.a(bundle);
                nVar.s = null;
            }
            if (!callbackQ.onPreparePanel(0, nVar.i, nVar.j)) {
                if (z && (nVar3 = this.l) != null) {
                    nVar3.a(null, this.m);
                }
                nVar.j.r();
                return false;
            }
            nVar.p = KeyCharacterMap.load(keyEvent != null ? keyEvent.getDeviceId() : -1).getKeyboardType() != 1;
            nVar.j.setQwertyMode(nVar.p);
            nVar.j.r();
        }
        nVar.m = true;
        nVar.n = false;
        this.I = nVar;
        return true;
    }

    private void a(androidx.appcompat.view.menu.g gVar, boolean z) {
        androidx.appcompat.widget.n nVar = this.l;
        if (nVar != null && nVar.f() && (!ViewConfiguration.get(this.f255e).hasPermanentMenuKey() || this.l.a())) {
            Window.Callback callbackQ = q();
            if (this.l.b() && z) {
                this.l.c();
                if (this.N) {
                    return;
                }
                callbackQ.onPanelClosed(108, a(0, true).j);
                return;
            }
            if (callbackQ == null || this.N) {
                return;
            }
            if (this.U && (this.V & 1) != 0) {
                this.f256f.getDecorView().removeCallbacks(this.W);
                this.W.run();
            }
            n nVarA = a(0, true);
            androidx.appcompat.view.menu.g gVar2 = nVarA.j;
            if (gVar2 == null || nVarA.r || !callbackQ.onPreparePanel(0, nVarA.i, gVar2)) {
                return;
            }
            callbackQ.onMenuOpened(108, nVarA.j);
            this.l.d();
            return;
        }
        n nVarA2 = a(0, true);
        nVarA2.q = true;
        a(nVarA2, false);
        a(nVarA2, (KeyEvent) null);
    }

    void b(androidx.appcompat.view.menu.g gVar) {
        if (this.G) {
            return;
        }
        this.G = true;
        this.l.g();
        Window.Callback callbackQ = q();
        if (callbackQ != null && !this.N) {
            callbackQ.onPanelClosed(108, gVar);
        }
        this.G = false;
    }

    private boolean b(int i2, boolean z) {
        int i3;
        int i4 = this.f255e.getApplicationContext().getResources().getConfiguration().uiMode & 48;
        boolean z2 = true;
        if (i2 != 1) {
            i3 = i2 != 2 ? i4 : 32;
        } else {
            i3 = 16;
        }
        boolean zD = D();
        boolean z3 = false;
        if ((f0 || i3 != i4) && !zD && Build.VERSION.SDK_INT >= 17 && !this.K && (this.f254d instanceof ContextThemeWrapper)) {
            Configuration configuration = new Configuration();
            configuration.uiMode = (configuration.uiMode & (-49)) | i3;
            try {
                ((ContextThemeWrapper) this.f254d).applyOverrideConfiguration(configuration);
                z3 = true;
            } catch (IllegalStateException e2) {
                Log.e("AppCompatDelegate", "updateForNightMode. Calling applyOverrideConfiguration() failed with an exception. Will fall back to using Resources.updateConfiguration()", e2);
            }
        }
        int i5 = this.f255e.getResources().getConfiguration().uiMode & 48;
        if (!z3 && i5 != i3 && z && !zD && this.K && (Build.VERSION.SDK_INT >= 17 || this.L)) {
            Object obj = this.f254d;
            if (obj instanceof Activity) {
                androidx.core.app.a.b((Activity) obj);
                z3 = true;
            }
        }
        if (z3 || i5 == i3) {
            z2 = z3;
        } else {
            c(i3, zD);
        }
        if (z2) {
            Object obj2 = this.f254d;
            if (obj2 instanceof androidx.appcompat.app.d) {
                ((androidx.appcompat.app.d) obj2).b(i2);
            }
        }
        return z2;
    }

    private boolean a(n nVar) {
        View view = nVar.i;
        if (view != null) {
            nVar.h = view;
            return true;
        }
        if (nVar.j == null) {
            return false;
        }
        if (this.n == null) {
            this.n = new o();
        }
        nVar.h = (View) nVar.a(this.n);
        return nVar.h != null;
    }

    void a(n nVar, boolean z) {
        ViewGroup viewGroup;
        androidx.appcompat.widget.n nVar2;
        if (z && nVar.f277a == 0 && (nVar2 = this.l) != null && nVar2.b()) {
            b(nVar.j);
            return;
        }
        WindowManager windowManager = (WindowManager) this.f255e.getSystemService("window");
        if (windowManager != null && nVar.o && (viewGroup = nVar.g) != null) {
            windowManager.removeView(viewGroup);
            if (z) {
                a(nVar.f277a, nVar, null);
            }
        }
        nVar.m = false;
        nVar.n = false;
        nVar.o = false;
        nVar.h = null;
        nVar.q = true;
        if (this.I == nVar) {
            this.I = null;
        }
    }

    void a(int i2, n nVar, Menu menu) {
        if (menu == null) {
            if (nVar == null && i2 >= 0) {
                n[] nVarArr = this.H;
                if (i2 < nVarArr.length) {
                    nVar = nVarArr[i2];
                }
            }
            if (nVar != null) {
                menu = nVar.j;
            }
        }
        if ((nVar == null || nVar.o) && !this.N) {
            this.g.a().onPanelClosed(i2, menu);
        }
    }

    n a(Menu menu) {
        n[] nVarArr = this.H;
        int length = nVarArr != null ? nVarArr.length : 0;
        for (int i2 = 0; i2 < length; i2++) {
            n nVar = nVarArr[i2];
            if (nVar != null && nVar.j == menu) {
                return nVar;
            }
        }
        return null;
    }

    protected n a(int i2, boolean z) {
        n[] nVarArr = this.H;
        if (nVarArr == null || nVarArr.length <= i2) {
            n[] nVarArr2 = new n[i2 + 1];
            if (nVarArr != null) {
                System.arraycopy(nVarArr, 0, nVarArr2, 0, nVarArr.length);
            }
            this.H = nVarArr2;
            nVarArr = nVarArr2;
        }
        n nVar = nVarArr[i2];
        if (nVar != null) {
            return nVar;
        }
        n nVar2 = new n(i2);
        nVarArr[i2] = nVar2;
        return nVar2;
    }

    private boolean a(n nVar, int i2, KeyEvent keyEvent, int i3) {
        androidx.appcompat.view.menu.g gVar;
        boolean zPerformShortcut = false;
        if (keyEvent.isSystem()) {
            return false;
        }
        if ((nVar.m || b(nVar, keyEvent)) && (gVar = nVar.j) != null) {
            zPerformShortcut = gVar.performShortcut(i2, keyEvent, i3);
        }
        if (zPerformShortcut && (i3 & 1) == 0 && this.l == null) {
            a(nVar, true);
        }
        return zPerformShortcut;
    }

    private boolean a(boolean z) {
        if (this.N) {
            return false;
        }
        int iW = w();
        boolean zB = b(g(iW), z);
        if (iW == 0) {
            o().e();
        } else {
            l lVar = this.S;
            if (lVar != null) {
                lVar.a();
            }
        }
        if (iW == 3) {
            B().e();
        } else {
            l lVar2 = this.T;
            if (lVar2 != null) {
                lVar2.a();
            }
        }
        return zB;
    }

    @Override // androidx.appcompat.app.f
    public int a() {
        return this.O;
    }
}
