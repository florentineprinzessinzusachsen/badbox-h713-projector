package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import androidx.appcompat.R$attr;
import androidx.appcompat.R$styleable;
import com.blankj.utilcode.constant.MemoryConstants;
import java.lang.reflect.Method;

/* JADX INFO: compiled from: ListPopupWindow.java */
/* JADX INFO: loaded from: classes.dex */
public class s implements androidx.appcompat.view.menu.p {
    private static Method G;
    private static Method H;
    private static Method I;
    final Handler A;
    private final Rect B;
    private Rect C;
    private boolean D;
    PopupWindow F;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Context f814a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private ListAdapter f815b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    DropDownListView f816c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f817d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f818e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private int f819f;
    private int g;
    private int h;
    private boolean i;
    private boolean j;
    private boolean k;
    private int l;
    private boolean m;
    private boolean n;
    int o;
    private View p;
    private int q;
    private DataSetObserver r;
    private View s;
    private Drawable t;
    private AdapterView.OnItemClickListener u;
    private AdapterView.OnItemSelectedListener v;
    final g w;
    private final f x;
    private final e y;
    private final c z;

    /* JADX INFO: compiled from: ListPopupWindow.java */
    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            View viewH = s.this.h();
            if (viewH == null || viewH.getWindowToken() == null) {
                return;
            }
            s.this.show();
        }
    }

    /* JADX INFO: compiled from: ListPopupWindow.java */
    class b implements AdapterView.OnItemSelectedListener {
        b() {
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
            DropDownListView dropDownListView;
            if (i == -1 || (dropDownListView = s.this.f816c) == null) {
                return;
            }
            dropDownListView.setListSelectionHidden(false);
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onNothingSelected(AdapterView<?> adapterView) {
        }
    }

    /* JADX INFO: compiled from: ListPopupWindow.java */
    private class c implements Runnable {
        c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            s.this.g();
        }
    }

    /* JADX INFO: compiled from: ListPopupWindow.java */
    private class d extends DataSetObserver {
        d() {
        }

        @Override // android.database.DataSetObserver
        public void onChanged() {
            if (s.this.b()) {
                s.this.show();
            }
        }

        @Override // android.database.DataSetObserver
        public void onInvalidated() {
            s.this.dismiss();
        }
    }

    /* JADX INFO: compiled from: ListPopupWindow.java */
    private class e implements AbsListView.OnScrollListener {
        e() {
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScrollStateChanged(AbsListView absListView, int i) {
            if (i != 1 || s.this.j() || s.this.F.getContentView() == null) {
                return;
            }
            s sVar = s.this;
            sVar.A.removeCallbacks(sVar.w);
            s.this.w.run();
        }
    }

    /* JADX INFO: compiled from: ListPopupWindow.java */
    private class f implements View.OnTouchListener {
        f() {
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            PopupWindow popupWindow;
            int action = motionEvent.getAction();
            int x = (int) motionEvent.getX();
            int y = (int) motionEvent.getY();
            if (action == 0 && (popupWindow = s.this.F) != null && popupWindow.isShowing() && x >= 0 && x < s.this.F.getWidth() && y >= 0 && y < s.this.F.getHeight()) {
                s sVar = s.this;
                sVar.A.postDelayed(sVar.w, 250L);
                return false;
            }
            if (action != 1) {
                return false;
            }
            s sVar2 = s.this;
            sVar2.A.removeCallbacks(sVar2.w);
            return false;
        }
    }

    /* JADX INFO: compiled from: ListPopupWindow.java */
    private class g implements Runnable {
        g() {
        }

        @Override // java.lang.Runnable
        public void run() {
            DropDownListView dropDownListView = s.this.f816c;
            if (dropDownListView == null || !androidx.core.f.t.q(dropDownListView) || s.this.f816c.getCount() <= s.this.f816c.getChildCount()) {
                return;
            }
            int childCount = s.this.f816c.getChildCount();
            s sVar = s.this;
            if (childCount <= sVar.o) {
                sVar.F.setInputMethodMode(2);
                s.this.show();
            }
        }
    }

    static {
        if (Build.VERSION.SDK_INT <= 28) {
            try {
                G = PopupWindow.class.getDeclaredMethod("setClipToScreenEnabled", Boolean.TYPE);
            } catch (NoSuchMethodException unused) {
                Log.i("ListPopupWindow", "Could not find method setClipToScreenEnabled() on PopupWindow. Oh well.");
            }
            try {
                I = PopupWindow.class.getDeclaredMethod("setEpicenterBounds", Rect.class);
            } catch (NoSuchMethodException unused2) {
                Log.i("ListPopupWindow", "Could not find method setEpicenterBounds(Rect) on PopupWindow. Oh well.");
            }
        }
        if (Build.VERSION.SDK_INT <= 23) {
            try {
                H = PopupWindow.class.getDeclaredMethod("getMaxAvailableHeight", View.class, Integer.TYPE, Boolean.TYPE);
            } catch (NoSuchMethodException unused3) {
                Log.i("ListPopupWindow", "Could not find method getMaxAvailableHeight(View, int, boolean) on PopupWindow. Oh well.");
            }
        }
    }

    public s(Context context) {
        this(context, null, R$attr.listPopupWindowStyle);
    }

    private int l() {
        int measuredHeight;
        int i;
        int iMakeMeasureSpec;
        View view;
        int i2;
        if (this.f816c == null) {
            Context context = this.f814a;
            new a();
            this.f816c = a(context, !this.D);
            Drawable drawable = this.t;
            if (drawable != null) {
                this.f816c.setSelector(drawable);
            }
            this.f816c.setAdapter(this.f815b);
            this.f816c.setOnItemClickListener(this.u);
            this.f816c.setFocusable(true);
            this.f816c.setFocusableInTouchMode(true);
            this.f816c.setOnItemSelectedListener(new b());
            this.f816c.setOnScrollListener(this.y);
            AdapterView.OnItemSelectedListener onItemSelectedListener = this.v;
            if (onItemSelectedListener != null) {
                this.f816c.setOnItemSelectedListener(onItemSelectedListener);
            }
            DropDownListView dropDownListView = this.f816c;
            View view2 = this.p;
            if (view2 != null) {
                LinearLayout linearLayout = new LinearLayout(context);
                linearLayout.setOrientation(1);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, 0, 1.0f);
                int i3 = this.q;
                if (i3 == 0) {
                    linearLayout.addView(view2);
                    linearLayout.addView(dropDownListView, layoutParams);
                } else if (i3 != 1) {
                    Log.e("ListPopupWindow", "Invalid hint position " + this.q);
                } else {
                    linearLayout.addView(dropDownListView, layoutParams);
                    linearLayout.addView(view2);
                }
                int i4 = this.f818e;
                if (i4 >= 0) {
                    i2 = Integer.MIN_VALUE;
                } else {
                    i4 = 0;
                    i2 = 0;
                }
                view2.measure(View.MeasureSpec.makeMeasureSpec(i4, i2), 0);
                LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) view2.getLayoutParams();
                measuredHeight = view2.getMeasuredHeight() + layoutParams2.topMargin + layoutParams2.bottomMargin;
                view = linearLayout;
            } else {
                measuredHeight = 0;
                view = dropDownListView;
            }
            this.F.setContentView(view);
        } else {
            View view3 = this.p;
            if (view3 != null) {
                LinearLayout.LayoutParams layoutParams3 = (LinearLayout.LayoutParams) view3.getLayoutParams();
                measuredHeight = view3.getMeasuredHeight() + layoutParams3.topMargin + layoutParams3.bottomMargin;
            } else {
                measuredHeight = 0;
            }
        }
        Drawable background = this.F.getBackground();
        if (background != null) {
            background.getPadding(this.B);
            Rect rect = this.B;
            int i5 = rect.top;
            i = rect.bottom + i5;
            if (!this.i) {
                this.g = -i5;
            }
        } else {
            this.B.setEmpty();
            i = 0;
        }
        int iA = a(h(), this.g, this.F.getInputMethodMode() == 2);
        if (this.m || this.f817d == -1) {
            return iA + i;
        }
        int i6 = this.f818e;
        if (i6 == -2) {
            int i7 = this.f814a.getResources().getDisplayMetrics().widthPixels;
            Rect rect2 = this.B;
            iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i7 - (rect2.left + rect2.right), Integer.MIN_VALUE);
        } else if (i6 != -1) {
            iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i6, MemoryConstants.GB);
        } else {
            int i8 = this.f814a.getResources().getDisplayMetrics().widthPixels;
            Rect rect3 = this.B;
            iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i8 - (rect3.left + rect3.right), MemoryConstants.GB);
        }
        int iA2 = this.f816c.a(iMakeMeasureSpec, 0, -1, iA - measuredHeight, -1);
        if (iA2 > 0) {
            measuredHeight += i + this.f816c.getPaddingTop() + this.f816c.getPaddingBottom();
        }
        return iA2 + measuredHeight;
    }

    private void m() {
        View view = this.p;
        if (view != null) {
            ViewParent parent = view.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(this.p);
            }
        }
    }

    public void a(ListAdapter listAdapter) {
        DataSetObserver dataSetObserver = this.r;
        if (dataSetObserver == null) {
            this.r = new d();
        } else {
            ListAdapter listAdapter2 = this.f815b;
            if (listAdapter2 != null) {
                listAdapter2.unregisterDataSetObserver(dataSetObserver);
            }
        }
        this.f815b = listAdapter;
        if (listAdapter != null) {
            listAdapter.registerDataSetObserver(this.r);
        }
        DropDownListView dropDownListView = this.f816c;
        if (dropDownListView != null) {
            dropDownListView.setAdapter(this.f815b);
        }
    }

    public void b(int i) {
        this.g = i;
        this.i = true;
    }

    public int c() {
        if (this.i) {
            return this.g;
        }
        return 0;
    }

    public Drawable d() {
        return this.F.getBackground();
    }

    @Override // androidx.appcompat.view.menu.p
    public void dismiss() {
        this.F.dismiss();
        m();
        this.F.setContentView(null);
        this.f816c = null;
        this.A.removeCallbacks(this.w);
    }

    public void e(int i) {
        Drawable background = this.F.getBackground();
        if (background == null) {
            j(i);
            return;
        }
        background.getPadding(this.B);
        Rect rect = this.B;
        this.f818e = rect.left + rect.right + i;
    }

    public void f(int i) {
        this.l = i;
    }

    public void g(int i) {
        this.F.setInputMethodMode(i);
    }

    public void h(int i) {
        this.q = i;
    }

    public int i() {
        return this.f818e;
    }

    public void j(int i) {
        this.f818e = i;
    }

    public boolean k() {
        return this.D;
    }

    @Override // androidx.appcompat.view.menu.p
    public void show() {
        int iL = l();
        boolean zJ = j();
        androidx.core.widget.h.a(this.F, this.h);
        if (this.F.isShowing()) {
            if (androidx.core.f.t.q(h())) {
                int width = this.f818e;
                if (width == -1) {
                    width = -1;
                } else if (width == -2) {
                    width = h().getWidth();
                }
                int i = this.f817d;
                if (i == -1) {
                    if (!zJ) {
                        iL = -1;
                    }
                    if (zJ) {
                        this.F.setWidth(this.f818e == -1 ? -1 : 0);
                        this.F.setHeight(0);
                    } else {
                        this.F.setWidth(this.f818e == -1 ? -1 : 0);
                        this.F.setHeight(-1);
                    }
                } else if (i != -2) {
                    iL = i;
                }
                this.F.setOutsideTouchable((this.n || this.m) ? false : true);
                this.F.update(h(), this.f819f, this.g, width < 0 ? -1 : width, iL < 0 ? -1 : iL);
                return;
            }
            return;
        }
        int width2 = this.f818e;
        if (width2 == -1) {
            width2 = -1;
        } else if (width2 == -2) {
            width2 = h().getWidth();
        }
        int i2 = this.f817d;
        if (i2 == -1) {
            iL = -1;
        } else if (i2 != -2) {
            iL = i2;
        }
        this.F.setWidth(width2);
        this.F.setHeight(iL);
        c(true);
        this.F.setOutsideTouchable((this.n || this.m) ? false : true);
        this.F.setTouchInterceptor(this.x);
        if (this.k) {
            androidx.core.widget.h.a(this.F, this.j);
        }
        if (Build.VERSION.SDK_INT <= 28) {
            Method method = I;
            if (method != null) {
                try {
                    method.invoke(this.F, this.C);
                } catch (Exception e2) {
                    Log.e("ListPopupWindow", "Could not invoke setEpicenterBounds on PopupWindow", e2);
                }
            }
        } else {
            this.F.setEpicenterBounds(this.C);
        }
        androidx.core.widget.h.a(this.F, h(), this.f819f, this.g, this.l);
        this.f816c.setSelection(-1);
        if (!this.D || this.f816c.isInTouchMode()) {
            g();
        }
        if (this.D) {
            return;
        }
        this.A.post(this.z);
    }

    public s(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public void d(int i) {
        this.F.setAnimationStyle(i);
    }

    @Override // androidx.appcompat.view.menu.p
    public ListView f() {
        return this.f816c;
    }

    public void g() {
        DropDownListView dropDownListView = this.f816c;
        if (dropDownListView != null) {
            dropDownListView.setListSelectionHidden(true);
            dropDownListView.requestLayout();
        }
    }

    public View h() {
        return this.s;
    }

    public void i(int i) {
        DropDownListView dropDownListView = this.f816c;
        if (!b() || dropDownListView == null) {
            return;
        }
        dropDownListView.setListSelectionHidden(false);
        dropDownListView.setSelection(i);
        if (dropDownListView.getChoiceMode() != 0) {
            dropDownListView.setItemChecked(i, true);
        }
    }

    public boolean j() {
        return this.F.getInputMethodMode() == 2;
    }

    public s(Context context, AttributeSet attributeSet, int i, int i2) {
        this.f817d = -2;
        this.f818e = -2;
        this.h = 1002;
        this.l = 0;
        this.m = false;
        this.n = false;
        this.o = Integer.MAX_VALUE;
        this.q = 0;
        this.w = new g();
        this.x = new f();
        this.y = new e();
        this.z = new c();
        this.B = new Rect();
        this.f814a = context;
        this.A = new Handler(context.getMainLooper());
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.ListPopupWindow, i, i2);
        this.f819f = typedArrayObtainStyledAttributes.getDimensionPixelOffset(R$styleable.ListPopupWindow_android_dropDownHorizontalOffset, 0);
        this.g = typedArrayObtainStyledAttributes.getDimensionPixelOffset(R$styleable.ListPopupWindow_android_dropDownVerticalOffset, 0);
        if (this.g != 0) {
            this.i = true;
        }
        typedArrayObtainStyledAttributes.recycle();
        this.F = new h(context, attributeSet, i, i2);
        this.F.setInputMethodMode(1);
    }

    private void c(boolean z) {
        if (Build.VERSION.SDK_INT <= 28) {
            Method method = G;
            if (method != null) {
                try {
                    method.invoke(this.F, Boolean.valueOf(z));
                    return;
                } catch (Exception unused) {
                    Log.i("ListPopupWindow", "Could not call setClipToScreenEnabled() on PopupWindow. Oh well.");
                    return;
                }
            }
            return;
        }
        this.F.setIsClippedToScreen(z);
    }

    @Override // androidx.appcompat.view.menu.p
    public boolean b() {
        return this.F.isShowing();
    }

    public void b(boolean z) {
        this.k = true;
        this.j = z;
    }

    public void a(boolean z) {
        this.D = z;
        this.F.setFocusable(z);
    }

    public void a(Drawable drawable) {
        this.F.setBackgroundDrawable(drawable);
    }

    public void a(View view) {
        this.s = view;
    }

    public int a() {
        return this.f819f;
    }

    public void a(int i) {
        this.f819f = i;
    }

    public void a(Rect rect) {
        this.C = rect != null ? new Rect(rect) : null;
    }

    public void a(AdapterView.OnItemClickListener onItemClickListener) {
        this.u = onItemClickListener;
    }

    public void a(PopupWindow.OnDismissListener onDismissListener) {
        this.F.setOnDismissListener(onDismissListener);
    }

    DropDownListView a(Context context, boolean z) {
        return new DropDownListView(context, z);
    }

    private int a(View view, int i, boolean z) {
        if (Build.VERSION.SDK_INT <= 23) {
            Method method = H;
            if (method != null) {
                try {
                    return ((Integer) method.invoke(this.F, view, Integer.valueOf(i), Boolean.valueOf(z))).intValue();
                } catch (Exception unused) {
                    Log.i("ListPopupWindow", "Could not call getMaxAvailableHeightMethod(View, int, boolean) on PopupWindow. Using the public version.");
                }
            }
            return this.F.getMaxAvailableHeight(view, i);
        }
        return this.F.getMaxAvailableHeight(view, i, z);
    }
}
