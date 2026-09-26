package androidx.viewpager.widget;

import android.R;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.FocusFinder;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SoundEffectConstants;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.animation.Interpolator;
import android.widget.EdgeEffect;
import android.widget.Scroller;
import androidx.core.f.b0;
import androidx.core.f.p;
import androidx.core.f.t;
import com.blankj.utilcode.constant.MemoryConstants;
import com.blankj.utilcode.constant.TimeConstants;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class ViewPager extends ViewGroup {
    static final int[] g0 = {R.attr.layout_gravity};
    private static final Comparator<f> h0 = new a();
    private static final Interpolator i0 = new b();
    private static final n j0 = new n();
    private int A;
    private int B;
    private float C;
    private float D;
    private float F;
    private float G;
    private int H;
    private VelocityTracker I;
    private int J;
    private int K;
    private int L;
    private int M;
    private boolean N;
    private EdgeEffect O;
    private EdgeEffect P;
    private boolean Q;
    private boolean R;
    private int S;
    private List<j> T;
    private j U;
    private j V;
    private List<i> W;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f1491a;
    private k a0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final ArrayList<f> f1492b;
    private int b0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final f f1493c;
    private int c0;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final Rect f1494d;
    private ArrayList<View> d0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    androidx.viewpager.widget.a f1495e;
    private final Runnable e0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    int f1496f;
    private int f0;
    private int g;
    private Parcelable h;
    private ClassLoader i;
    private Scroller j;
    private boolean k;
    private l l;
    private int m;
    private Drawable n;
    private int o;
    private int p;
    private float q;
    private float r;
    private int s;
    private boolean t;
    private boolean u;
    private boolean v;
    private int w;
    private boolean x;
    private boolean y;
    private int z;

    static class a implements Comparator<f> {
        a() {
        }

        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(f fVar, f fVar2) {
            return fVar.f1501b - fVar2.f1501b;
        }
    }

    static class b implements Interpolator {
        b() {
        }

        @Override // android.animation.TimeInterpolator
        public float getInterpolation(float f2) {
            float f3 = f2 - 1.0f;
            return (f3 * f3 * f3 * f3 * f3) + 1.0f;
        }
    }

    class c implements Runnable {
        c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ViewPager.this.setScrollState(0);
            ViewPager.this.e();
        }
    }

    class d implements p {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Rect f1498a = new Rect();

        d() {
        }

        @Override // androidx.core.f.p
        public b0 a(View view, b0 b0Var) {
            b0 b0VarB = t.b(view, b0Var);
            if (b0VarB.e()) {
                return b0VarB;
            }
            Rect rect = this.f1498a;
            rect.left = b0VarB.b();
            rect.top = b0VarB.d();
            rect.right = b0VarB.c();
            rect.bottom = b0VarB.a();
            int childCount = ViewPager.this.getChildCount();
            for (int i = 0; i < childCount; i++) {
                b0 b0VarA = t.a(ViewPager.this.getChildAt(i), b0VarB);
                rect.left = Math.min(b0VarA.b(), rect.left);
                rect.top = Math.min(b0VarA.d(), rect.top);
                rect.right = Math.min(b0VarA.c(), rect.right);
                rect.bottom = Math.min(b0VarA.a(), rect.bottom);
            }
            return b0VarB.a(rect.left, rect.top, rect.right, rect.bottom);
        }
    }

    @Target({ElementType.TYPE})
    @Inherited
    @Retention(RetentionPolicy.RUNTIME)
    public @interface e {
    }

    static class f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        Object f1500a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int f1501b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        boolean f1502c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        float f1503d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        float f1504e;

        f() {
        }
    }

    public interface i {
        void a(ViewPager viewPager, androidx.viewpager.widget.a aVar, androidx.viewpager.widget.a aVar2);
    }

    public interface j {
        void a(int i);

        void a(int i, float f2, int i2);

        void b(int i);
    }

    public interface k {
        void a(View view, float f2);
    }

    private class l extends DataSetObserver {
        l() {
        }

        @Override // android.database.DataSetObserver
        public void onChanged() {
            ViewPager.this.a();
        }

        @Override // android.database.DataSetObserver
        public void onInvalidated() {
            ViewPager.this.a();
        }
    }

    public static class m extends androidx.customview.a.a {
        public static final Parcelable.Creator<m> CREATOR = new a();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f1513c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        Parcelable f1514d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        ClassLoader f1515e;

        static class a implements Parcelable.ClassLoaderCreator<m> {
            a() {
            }

            @Override // android.os.Parcelable.Creator
            public m[] newArray(int i) {
                return new m[i];
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.ClassLoaderCreator
            public m createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new m(parcel, classLoader);
            }

            @Override // android.os.Parcelable.Creator
            public m createFromParcel(Parcel parcel) {
                return new m(parcel, null);
            }
        }

        public m(Parcelable parcelable) {
            super(parcelable);
        }

        public String toString() {
            return "FragmentPager.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " position=" + this.f1513c + "}";
        }

        @Override // androidx.customview.a.a, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.f1513c);
            parcel.writeParcelable(this.f1514d, i);
        }

        m(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            classLoader = classLoader == null ? m.class.getClassLoader() : classLoader;
            this.f1513c = parcel.readInt();
            this.f1514d = parcel.readParcelable(classLoader);
            this.f1515e = classLoader;
        }
    }

    static class n implements Comparator<View> {
        n() {
        }

        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(View view, View view2) {
            g gVar = (g) view.getLayoutParams();
            g gVar2 = (g) view2.getLayoutParams();
            boolean z = gVar.f1505a;
            if (z != gVar2.f1505a) {
                return z ? 1 : -1;
            }
            return gVar.f1509e - gVar2.f1509e;
        }
    }

    public ViewPager(Context context) {
        super(context);
        this.f1492b = new ArrayList<>();
        this.f1493c = new f();
        this.f1494d = new Rect();
        this.g = -1;
        this.h = null;
        this.i = null;
        this.q = -3.4028235E38f;
        this.r = Float.MAX_VALUE;
        this.w = 1;
        this.H = -1;
        this.Q = true;
        this.e0 = new c();
        this.f0 = 0;
        b();
    }

    private void d(int i2) {
        j jVar = this.U;
        if (jVar != null) {
            jVar.b(i2);
        }
        List<j> list = this.T;
        if (list != null) {
            int size = list.size();
            for (int i3 = 0; i3 < size; i3++) {
                j jVar2 = this.T.get(i3);
                if (jVar2 != null) {
                    jVar2.b(i2);
                }
            }
        }
        j jVar3 = this.V;
        if (jVar3 != null) {
            jVar3.b(i2);
        }
    }

    private boolean f(int i2) {
        if (this.f1492b.size() == 0) {
            if (this.Q) {
                return false;
            }
            this.R = false;
            a(0, 0.0f, 0);
            if (this.R) {
                return false;
            }
            throw new IllegalStateException("onPageScrolled did not call superclass implementation");
        }
        f fVarG = g();
        int clientWidth = getClientWidth();
        int i3 = this.m;
        int i4 = clientWidth + i3;
        float f2 = clientWidth;
        int i5 = fVarG.f1501b;
        float f3 = ((i2 / f2) - fVarG.f1504e) / (fVarG.f1503d + (i3 / f2));
        this.R = false;
        a(i5, f3, (int) (i4 * f3));
        if (this.R) {
            return true;
        }
        throw new IllegalStateException("onPageScrolled did not call superclass implementation");
    }

    private f g() {
        int i2;
        int clientWidth = getClientWidth();
        float scrollX = clientWidth > 0 ? getScrollX() / clientWidth : 0.0f;
        float f2 = clientWidth > 0 ? this.m / clientWidth : 0.0f;
        f fVar = null;
        int i3 = 0;
        boolean z = true;
        int i4 = -1;
        float f3 = 0.0f;
        float f4 = 0.0f;
        while (i3 < this.f1492b.size()) {
            f fVar2 = this.f1492b.get(i3);
            if (!z && fVar2.f1501b != (i2 = i4 + 1)) {
                fVar2 = this.f1493c;
                fVar2.f1504e = f3 + f4 + f2;
                fVar2.f1501b = i2;
                fVar2.f1503d = this.f1495e.b(fVar2.f1501b);
                i3--;
            }
            f3 = fVar2.f1504e;
            float f5 = fVar2.f1503d + f3 + f2;
            if (!z && scrollX < f3) {
                return fVar;
            }
            if (scrollX < f5 || i3 == this.f1492b.size() - 1) {
                return fVar2;
            }
            i4 = fVar2.f1501b;
            f4 = fVar2.f1503d;
            i3++;
            fVar = fVar2;
            z = false;
        }
        return fVar;
    }

    private int getClientWidth() {
        return (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight();
    }

    private void h() {
        int i2 = 0;
        while (i2 < getChildCount()) {
            if (!((g) getChildAt(i2).getLayoutParams()).f1505a) {
                removeViewAt(i2);
                i2--;
            }
            i2++;
        }
    }

    private boolean i() {
        this.H = -1;
        f();
        this.O.onRelease();
        this.P.onRelease();
        return this.O.isFinished() || this.P.isFinished();
    }

    private void j() {
        if (this.c0 != 0) {
            ArrayList<View> arrayList = this.d0;
            if (arrayList == null) {
                this.d0 = new ArrayList<>();
            } else {
                arrayList.clear();
            }
            int childCount = getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                this.d0.add(getChildAt(i2));
            }
            Collections.sort(this.d0, j0);
        }
    }

    private void setScrollingCacheEnabled(boolean z) {
        if (this.u != z) {
            this.u = z;
        }
    }

    public void a(i iVar) {
        if (this.W == null) {
            this.W = new ArrayList();
        }
        this.W.add(iVar);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void addFocusables(ArrayList<View> arrayList, int i2, int i3) {
        f fVarB;
        int size = arrayList.size();
        int descendantFocusability = getDescendantFocusability();
        if (descendantFocusability != 393216) {
            for (int i4 = 0; i4 < getChildCount(); i4++) {
                View childAt = getChildAt(i4);
                if (childAt.getVisibility() == 0 && (fVarB = b(childAt)) != null && fVarB.f1501b == this.f1496f) {
                    childAt.addFocusables(arrayList, i2, i3);
                }
            }
        }
        if ((descendantFocusability != 262144 || size == arrayList.size()) && isFocusable()) {
            if (((i3 & 1) == 1 && isInTouchMode() && !isFocusableInTouchMode()) || arrayList == null) {
                return;
            }
            arrayList.add(this);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void addTouchables(ArrayList<View> arrayList) {
        f fVarB;
        for (int i2 = 0; i2 < getChildCount(); i2++) {
            View childAt = getChildAt(i2);
            if (childAt.getVisibility() == 0 && (fVarB = b(childAt)) != null && fVarB.f1501b == this.f1496f) {
                childAt.addTouchables(arrayList);
            }
        }
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i2, ViewGroup.LayoutParams layoutParams) {
        if (!checkLayoutParams(layoutParams)) {
            layoutParams = generateLayoutParams(layoutParams);
        }
        g gVar = (g) layoutParams;
        gVar.f1505a |= c(view);
        if (!this.t) {
            super.addView(view, i2, layoutParams);
        } else {
            if (gVar != null && gVar.f1505a) {
                throw new IllegalStateException("Cannot add pager decor view during layout");
            }
            gVar.f1508d = true;
            addViewInLayout(view, i2, layoutParams);
        }
    }

    void b() {
        setWillNotDraw(false);
        setDescendantFocusability(262144);
        setFocusable(true);
        Context context = getContext();
        this.j = new Scroller(context, i0);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        float f2 = context.getResources().getDisplayMetrics().density;
        this.B = viewConfiguration.getScaledPagingTouchSlop();
        this.J = (int) (400.0f * f2);
        this.K = viewConfiguration.getScaledMaximumFlingVelocity();
        this.O = new EdgeEffect(context);
        this.P = new EdgeEffect(context);
        this.L = (int) (25.0f * f2);
        this.M = (int) (2.0f * f2);
        this.z = (int) (f2 * 16.0f);
        t.a(this, new h());
        if (t.i(this) == 0) {
            t.f(this, 1);
        }
        t.a(this, new d());
    }

    /* JADX WARN: Code duplicated, block: B:64:0x00fa A[PHI: r7 r10 r15
      0x00fa: PHI (r7v14 float) = (r7v12 float), (r7v13 float), (r7v5 float) binds: [B:62:0x00ef, B:59:0x00d9, B:53:0x00c3] A[DONT_GENERATE, DONT_INLINE]
      0x00fa: PHI (r10v4 int) = (r10v3 int), (r10v2 int), (r10v7 int) binds: [B:62:0x00ef, B:59:0x00d9, B:53:0x00c3] A[DONT_GENERATE, DONT_INLINE]
      0x00fa: PHI (r15v6 int) = (r15v4 int), (r15v5 int), (r15v9 int) binds: [B:62:0x00ef, B:59:0x00d9, B:53:0x00c3] A[DONT_GENERATE, DONT_INLINE]] */
    void c(int i2) {
        f fVarB;
        String hexString;
        f fVarA;
        f fVarB2;
        f fVar;
        int i3 = this.f1496f;
        if (i3 != i2) {
            fVarB = b(i3);
            this.f1496f = i2;
        } else {
            fVarB = null;
        }
        if (this.f1495e == null) {
            j();
            return;
        }
        if (this.v) {
            j();
            return;
        }
        if (getWindowToken() == null) {
            return;
        }
        this.f1495e.b((ViewGroup) this);
        int i4 = this.w;
        int iMax = Math.max(0, this.f1496f - i4);
        int iA = this.f1495e.a();
        int iMin = Math.min(iA - 1, this.f1496f + i4);
        if (iA != this.f1491a) {
            try {
                hexString = getResources().getResourceName(getId());
            } catch (Resources.NotFoundException unused) {
                hexString = Integer.toHexString(getId());
            }
            throw new IllegalStateException("The application's PagerAdapter changed the adapter's contents without calling PagerAdapter#notifyDataSetChanged! Expected adapter item count: " + this.f1491a + ", found: " + iA + " Pager id: " + hexString + " Pager class: " + ViewPager.class + " Problematic adapter: " + this.f1495e.getClass());
        }
        int i5 = 0;
        while (true) {
            if (i5 < this.f1492b.size()) {
                fVarA = this.f1492b.get(i5);
                int i6 = fVarA.f1501b;
                int i7 = this.f1496f;
                if (i6 >= i7) {
                    if (i6 != i7) {
                        break;
                    } else {
                        break;
                    }
                }
                i5++;
            }
            fVarA = null;
            break;
        }
        if (fVarA == null && iA > 0) {
            fVarA = a(this.f1496f, i5);
        }
        if (fVarA != null) {
            int i8 = i5 - 1;
            f fVar2 = i8 >= 0 ? this.f1492b.get(i8) : null;
            int clientWidth = getClientWidth();
            float paddingLeft = clientWidth <= 0 ? 0.0f : (2.0f - fVarA.f1503d) + (getPaddingLeft() / clientWidth);
            int i9 = i8;
            int i10 = i5;
            float f2 = 0.0f;
            for (int i11 = this.f1496f - 1; i11 >= 0; i11--) {
                if (f2 < paddingLeft || i11 >= iMax) {
                    if (fVar2 == null || i11 != fVar2.f1501b) {
                        f2 += a(i11, i9 + 1).f1503d;
                        i10++;
                        if (i9 >= 0) {
                            fVar = this.f1492b.get(i9);
                        } else {
                            fVar = null;
                        }
                    } else {
                        f2 += fVar2.f1503d;
                        i9--;
                        if (i9 >= 0) {
                            fVar = this.f1492b.get(i9);
                        } else {
                            fVar = null;
                        }
                    }
                    fVar2 = fVar;
                } else {
                    if (fVar2 == null) {
                        break;
                    }
                    if (i11 == fVar2.f1501b && !fVar2.f1502c) {
                        this.f1492b.remove(i9);
                        this.f1495e.a((ViewGroup) this, i11, fVar2.f1500a);
                        i9--;
                        i10--;
                        if (i9 >= 0) {
                            fVar = this.f1492b.get(i9);
                        } else {
                            fVar = null;
                        }
                        fVar2 = fVar;
                    }
                }
            }
            float f3 = fVarA.f1503d;
            int i12 = i10 + 1;
            if (f3 < 2.0f) {
                f fVar3 = i12 < this.f1492b.size() ? this.f1492b.get(i12) : null;
                float paddingRight = clientWidth <= 0 ? 0.0f : (getPaddingRight() / clientWidth) + 2.0f;
                int i13 = this.f1496f;
                while (true) {
                    i13++;
                    if (i13 >= iA) {
                        break;
                    }
                    if (f3 >= paddingRight && i13 > iMin) {
                        if (fVar3 == null) {
                            break;
                        }
                        if (i13 == fVar3.f1501b && !fVar3.f1502c) {
                            this.f1492b.remove(i12);
                            this.f1495e.a((ViewGroup) this, i13, fVar3.f1500a);
                            if (i12 < this.f1492b.size()) {
                                fVar3 = this.f1492b.get(i12);
                            }
                        }
                    } else if (fVar3 == null || i13 != fVar3.f1501b) {
                        f fVarA2 = a(i13, i12);
                        i12++;
                        f3 += fVarA2.f1503d;
                        fVar3 = i12 < this.f1492b.size() ? this.f1492b.get(i12) : null;
                    } else {
                        f3 += fVar3.f1503d;
                        i12++;
                        if (i12 < this.f1492b.size()) {
                            fVar3 = this.f1492b.get(i12);
                        }
                    }
                }
            }
            a(fVarA, i10, fVarB);
            this.f1495e.b((ViewGroup) this, this.f1496f, fVarA.f1500a);
        }
        this.f1495e.a((ViewGroup) this);
        int childCount = getChildCount();
        for (int i14 = 0; i14 < childCount; i14++) {
            View childAt = getChildAt(i14);
            g gVar = (g) childAt.getLayoutParams();
            gVar.f1510f = i14;
            if (!gVar.f1505a && gVar.f1507c == 0.0f && (fVarB2 = b(childAt)) != null) {
                gVar.f1507c = fVarB2.f1503d;
                gVar.f1509e = fVarB2.f1501b;
            }
        }
        j();
        if (hasFocus()) {
            View viewFindFocus = findFocus();
            f fVarA3 = viewFindFocus != null ? a(viewFindFocus) : null;
            if (fVarA3 == null || fVarA3.f1501b != this.f1496f) {
                for (int i15 = 0; i15 < getChildCount(); i15++) {
                    View childAt2 = getChildAt(i15);
                    f fVarB3 = b(childAt2);
                    if (fVarB3 != null && fVarB3.f1501b == this.f1496f && childAt2.requestFocus(2)) {
                        return;
                    }
                }
            }
        }
    }

    @Override // android.view.View
    public boolean canScrollHorizontally(int i2) {
        if (this.f1495e == null) {
            return false;
        }
        int clientWidth = getClientWidth();
        int scrollX = getScrollX();
        if (i2 < 0) {
            return scrollX > ((int) (((float) clientWidth) * this.q));
        }
        return i2 > 0 && scrollX < ((int) (((float) clientWidth) * this.r));
    }

    @Override // android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return (layoutParams instanceof g) && super.checkLayoutParams(layoutParams);
    }

    @Override // android.view.View
    public void computeScroll() {
        this.k = true;
        if (this.j.isFinished() || !this.j.computeScrollOffset()) {
            a(true);
            return;
        }
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        int currX = this.j.getCurrX();
        int currY = this.j.getCurrY();
        if (scrollX != currX || scrollY != currY) {
            scrollTo(currX, currY);
            if (!f(currX)) {
                this.j.abortAnimation();
                scrollTo(0, currY);
            }
        }
        t.t(this);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent) || a(keyEvent);
    }

    @Override // android.view.View
    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        f fVarB;
        if (accessibilityEvent.getEventType() == 4096) {
            return super.dispatchPopulateAccessibilityEvent(accessibilityEvent);
        }
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            if (childAt.getVisibility() == 0 && (fVarB = b(childAt)) != null && fVarB.f1501b == this.f1496f && childAt.dispatchPopulateAccessibilityEvent(accessibilityEvent)) {
                return true;
            }
        }
        return false;
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        androidx.viewpager.widget.a aVar;
        super.draw(canvas);
        int overScrollMode = getOverScrollMode();
        boolean zDraw = false;
        if (overScrollMode == 0 || (overScrollMode == 1 && (aVar = this.f1495e) != null && aVar.a() > 1)) {
            if (!this.O.isFinished()) {
                int iSave = canvas.save();
                int height = (getHeight() - getPaddingTop()) - getPaddingBottom();
                int width = getWidth();
                canvas.rotate(270.0f);
                canvas.translate((-height) + getPaddingTop(), this.q * width);
                this.O.setSize(height, width);
                zDraw = false | this.O.draw(canvas);
                canvas.restoreToCount(iSave);
            }
            if (!this.P.isFinished()) {
                int iSave2 = canvas.save();
                int width2 = getWidth();
                int height2 = (getHeight() - getPaddingTop()) - getPaddingBottom();
                canvas.rotate(90.0f);
                canvas.translate(-getPaddingTop(), (-(this.r + 1.0f)) * width2);
                this.P.setSize(height2, width2);
                zDraw |= this.P.draw(canvas);
                canvas.restoreToCount(iSave2);
            }
        } else {
            this.O.finish();
            this.P.finish();
        }
        if (zDraw) {
            t.t(this);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        Drawable drawable = this.n;
        if (drawable == null || !drawable.isStateful()) {
            return;
        }
        drawable.setState(getDrawableState());
    }

    void e() {
        c(this.f1496f);
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new g();
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return generateDefaultLayoutParams();
    }

    public androidx.viewpager.widget.a getAdapter() {
        return this.f1495e;
    }

    @Override // android.view.ViewGroup
    protected int getChildDrawingOrder(int i2, int i3) {
        if (this.c0 == 2) {
            i3 = (i2 - 1) - i3;
        }
        return ((g) this.d0.get(i3).getLayoutParams()).f1510f;
    }

    public int getCurrentItem() {
        return this.f1496f;
    }

    public int getOffscreenPageLimit() {
        return this.w;
    }

    public int getPageMargin() {
        return this.m;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.Q = true;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        removeCallbacks(this.e0);
        Scroller scroller = this.j;
        if (scroller != null && !scroller.isFinished()) {
            this.j.abortAnimation();
        }
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        float f2;
        super.onDraw(canvas);
        if (this.m <= 0 || this.n == null || this.f1492b.size() <= 0 || this.f1495e == null) {
            return;
        }
        int scrollX = getScrollX();
        int width = getWidth();
        float f3 = width;
        float f4 = this.m / f3;
        int i2 = 0;
        f fVar = this.f1492b.get(0);
        float f5 = fVar.f1504e;
        int size = this.f1492b.size();
        int i3 = fVar.f1501b;
        int i4 = this.f1492b.get(size - 1).f1501b;
        while (i3 < i4) {
            while (i3 > fVar.f1501b && i2 < size) {
                i2++;
                fVar = this.f1492b.get(i2);
            }
            if (i3 == fVar.f1501b) {
                float f6 = fVar.f1504e;
                float f7 = fVar.f1503d;
                f2 = (f6 + f7) * f3;
                f5 = f6 + f7 + f4;
            } else {
                float fB = this.f1495e.b(i3);
                f2 = (f5 + fB) * f3;
                f5 += fB + f4;
            }
            if (this.m + f2 > scrollX) {
                this.n.setBounds(Math.round(f2), this.o, Math.round(this.m + f2), this.p);
                this.n.draw(canvas);
            }
            if (f2 > scrollX + width) {
                return;
            }
            i3++;
            f4 = f4;
        }
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction() & 255;
        if (action == 3 || action == 1) {
            i();
            return false;
        }
        if (action != 0) {
            if (this.x) {
                return true;
            }
            if (this.y) {
                return false;
            }
        }
        if (action == 0) {
            float x = motionEvent.getX();
            this.F = x;
            this.C = x;
            float y = motionEvent.getY();
            this.G = y;
            this.D = y;
            this.H = motionEvent.getPointerId(0);
            this.y = false;
            this.k = true;
            this.j.computeScrollOffset();
            if (this.f0 != 2 || Math.abs(this.j.getFinalX() - this.j.getCurrX()) <= this.M) {
                a(false);
                this.x = false;
            } else {
                this.j.abortAnimation();
                this.v = false;
                e();
                this.x = true;
                c(true);
                setScrollState(1);
            }
        } else if (action == 2) {
            int i2 = this.H;
            if (i2 != -1) {
                int iFindPointerIndex = motionEvent.findPointerIndex(i2);
                float x2 = motionEvent.getX(iFindPointerIndex);
                float f2 = x2 - this.C;
                float fAbs = Math.abs(f2);
                float y2 = motionEvent.getY(iFindPointerIndex);
                float fAbs2 = Math.abs(y2 - this.G);
                if (f2 != 0.0f && !a(this.C, f2) && a(this, false, (int) f2, (int) x2, (int) y2)) {
                    this.C = x2;
                    this.D = y2;
                    this.y = true;
                    return false;
                }
                if (fAbs > this.B && fAbs * 0.5f > fAbs2) {
                    this.x = true;
                    c(true);
                    setScrollState(1);
                    this.C = f2 > 0.0f ? this.F + this.B : this.F - this.B;
                    this.D = y2;
                    setScrollingCacheEnabled(true);
                } else if (fAbs2 > this.B) {
                    this.y = true;
                }
                if (this.x && b(x2)) {
                    t.t(this);
                }
            }
        } else if (action == 6) {
            a(motionEvent);
        }
        if (this.I == null) {
            this.I = VelocityTracker.obtain();
        }
        this.I.addMovement(motionEvent);
        return this.x;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        boolean z2;
        f fVarB;
        int iMax;
        int iMax2;
        int childCount = getChildCount();
        int i6 = i4 - i2;
        int i7 = i5 - i3;
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();
        int scrollX = getScrollX();
        int measuredHeight = paddingBottom;
        int i8 = 0;
        int measuredHeight2 = paddingTop;
        int measuredWidth = paddingLeft;
        for (int i9 = 0; i9 < childCount; i9++) {
            View childAt = getChildAt(i9);
            if (childAt.getVisibility() != 8) {
                g gVar = (g) childAt.getLayoutParams();
                if (gVar.f1505a) {
                    int i10 = gVar.f1506b;
                    int i11 = i10 & 7;
                    int i12 = i10 & 112;
                    if (i11 == 1) {
                        iMax = Math.max((i6 - childAt.getMeasuredWidth()) / 2, measuredWidth);
                    } else if (i11 == 3) {
                        iMax = measuredWidth;
                        measuredWidth = childAt.getMeasuredWidth() + measuredWidth;
                    } else if (i11 != 5) {
                        iMax = measuredWidth;
                    } else {
                        iMax = (i6 - paddingRight) - childAt.getMeasuredWidth();
                        paddingRight += childAt.getMeasuredWidth();
                    }
                    if (i12 == 16) {
                        iMax2 = Math.max((i7 - childAt.getMeasuredHeight()) / 2, measuredHeight2);
                    } else if (i12 == 48) {
                        iMax2 = measuredHeight2;
                        measuredHeight2 = childAt.getMeasuredHeight() + measuredHeight2;
                    } else if (i12 != 80) {
                        iMax2 = measuredHeight2;
                    } else {
                        iMax2 = (i7 - measuredHeight) - childAt.getMeasuredHeight();
                        measuredHeight += childAt.getMeasuredHeight();
                    }
                    int i13 = iMax + scrollX;
                    childAt.layout(i13, iMax2, childAt.getMeasuredWidth() + i13, iMax2 + childAt.getMeasuredHeight());
                    i8++;
                }
            }
        }
        int i14 = (i6 - measuredWidth) - paddingRight;
        for (int i15 = 0; i15 < childCount; i15++) {
            View childAt2 = getChildAt(i15);
            if (childAt2.getVisibility() != 8) {
                g gVar2 = (g) childAt2.getLayoutParams();
                if (!gVar2.f1505a && (fVarB = b(childAt2)) != null) {
                    float f2 = i14;
                    int i16 = ((int) (fVarB.f1504e * f2)) + measuredWidth;
                    if (gVar2.f1508d) {
                        gVar2.f1508d = false;
                        childAt2.measure(View.MeasureSpec.makeMeasureSpec((int) (f2 * gVar2.f1507c), MemoryConstants.GB), View.MeasureSpec.makeMeasureSpec((i7 - measuredHeight2) - measuredHeight, MemoryConstants.GB));
                    }
                    childAt2.layout(i16, measuredHeight2, childAt2.getMeasuredWidth() + i16, childAt2.getMeasuredHeight() + measuredHeight2);
                }
            }
        }
        this.o = measuredHeight2;
        this.p = i7 - measuredHeight;
        this.S = i8;
        if (this.Q) {
            z2 = false;
            a(this.f1496f, false, 0, false);
        } else {
            z2 = false;
        }
        this.Q = z2;
    }

    /* JADX WARN: Code duplicated, block: B:32:0x0084 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:34:0x0087  */
    /* JADX WARN: Code duplicated, block: B:36:0x008b  */
    /* JADX WARN: Code duplicated, block: B:39:0x0090 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:41:0x0093  */
    /* JADX WARN: Code duplicated, block: B:42:0x0095  */
    /* JADX WARN: Code duplicated, block: B:45:0x00a4  */
    /* JADX WARN: Code duplicated, block: B:46:0x00aa A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:47:0x00ac  */
    /* JADX WARN: Code duplicated, block: B:66:0x00b1 A[SYNTHETIC] */
    @Override // android.view.View
    protected void onMeasure(int i2, int i3) {
        g gVar;
        g gVar2;
        int i4;
        int i5;
        int i6;
        setMeasuredDimension(ViewGroup.getDefaultSize(0, i2), ViewGroup.getDefaultSize(0, i3));
        int measuredWidth = getMeasuredWidth();
        this.A = Math.min(measuredWidth / 10, this.z);
        int paddingLeft = (measuredWidth - getPaddingLeft()) - getPaddingRight();
        int measuredHeight = (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom();
        int childCount = getChildCount();
        int measuredHeight2 = measuredHeight;
        int measuredWidth2 = paddingLeft;
        int i7 = 0;
        while (true) {
            boolean z = true;
            int i8 = MemoryConstants.GB;
            if (i7 >= childCount) {
                break;
            }
            View childAt = getChildAt(i7);
            if (childAt.getVisibility() != 8 && (gVar2 = (g) childAt.getLayoutParams()) != null && gVar2.f1505a) {
                int i9 = gVar2.f1506b;
                int i10 = i9 & 7;
                int i11 = i9 & 112;
                boolean z2 = i11 == 48 || i11 == 80;
                if (i10 != 3 && i10 != 5) {
                    z = false;
                }
                int i12 = Integer.MIN_VALUE;
                if (z2) {
                    i12 = MemoryConstants.GB;
                } else {
                    i4 = z ? MemoryConstants.GB : Integer.MIN_VALUE;
                    i5 = ((ViewGroup.LayoutParams) gVar2).width;
                    if (i5 != -2) {
                        if (i5 == -1) {
                            i5 = measuredWidth2;
                        }
                        i12 = MemoryConstants.GB;
                    } else {
                        i5 = measuredWidth2;
                    }
                    i6 = ((ViewGroup.LayoutParams) gVar2).height;
                    if (i6 != -2) {
                        i6 = measuredHeight2;
                        i8 = i4;
                    } else if (i6 == -1) {
                        i6 = measuredHeight2;
                    }
                    childAt.measure(View.MeasureSpec.makeMeasureSpec(i5, i12), View.MeasureSpec.makeMeasureSpec(i6, i8));
                    if (z2) {
                        measuredHeight2 -= childAt.getMeasuredHeight();
                    } else if (z) {
                        measuredWidth2 -= childAt.getMeasuredWidth();
                    }
                }
                i5 = ((ViewGroup.LayoutParams) gVar2).width;
                if (i5 != -2) {
                    if (i5 == -1) {
                        i5 = measuredWidth2;
                    }
                    i12 = MemoryConstants.GB;
                } else {
                    i5 = measuredWidth2;
                }
                i6 = ((ViewGroup.LayoutParams) gVar2).height;
                if (i6 != -2) {
                    i6 = measuredHeight2;
                    i8 = i4;
                } else if (i6 == -1) {
                    i6 = measuredHeight2;
                }
                childAt.measure(View.MeasureSpec.makeMeasureSpec(i5, i12), View.MeasureSpec.makeMeasureSpec(i6, i8));
                if (z2) {
                    measuredHeight2 -= childAt.getMeasuredHeight();
                } else if (z) {
                    measuredWidth2 -= childAt.getMeasuredWidth();
                }
            }
            i7++;
        }
        View.MeasureSpec.makeMeasureSpec(measuredWidth2, MemoryConstants.GB);
        this.s = View.MeasureSpec.makeMeasureSpec(measuredHeight2, MemoryConstants.GB);
        this.t = true;
        e();
        this.t = false;
        int childCount2 = getChildCount();
        for (int i13 = 0; i13 < childCount2; i13++) {
            View childAt2 = getChildAt(i13);
            if (childAt2.getVisibility() != 8 && ((gVar = (g) childAt2.getLayoutParams()) == null || !gVar.f1505a)) {
                childAt2.measure(View.MeasureSpec.makeMeasureSpec((int) (measuredWidth2 * gVar.f1507c), MemoryConstants.GB), this.s);
            }
        }
    }

    @Override // android.view.ViewGroup
    protected boolean onRequestFocusInDescendants(int i2, Rect rect) {
        int i3;
        int i4;
        f fVarB;
        int childCount = getChildCount();
        int i5 = -1;
        if ((i2 & 2) != 0) {
            i5 = childCount;
            i3 = 0;
            i4 = 1;
        } else {
            i3 = childCount - 1;
            i4 = -1;
        }
        while (i3 != i5) {
            View childAt = getChildAt(i3);
            if (childAt.getVisibility() == 0 && (fVarB = b(childAt)) != null && fVarB.f1501b == this.f1496f && childAt.requestFocus(i2, rect)) {
                return true;
            }
            i3 += i4;
        }
        return false;
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof m)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        m mVar = (m) parcelable;
        super.onRestoreInstanceState(mVar.a());
        androidx.viewpager.widget.a aVar = this.f1495e;
        if (aVar != null) {
            aVar.a(mVar.f1514d, mVar.f1515e);
            a(mVar.f1513c, false, true);
        } else {
            this.g = mVar.f1513c;
            this.h = mVar.f1514d;
            this.i = mVar.f1515e;
        }
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        m mVar = new m(super.onSaveInstanceState());
        mVar.f1513c = this.f1496f;
        androidx.viewpager.widget.a aVar = this.f1495e;
        if (aVar != null) {
            mVar.f1514d = aVar.b();
        }
        return mVar;
    }

    @Override // android.view.View
    protected void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        if (i2 != i4) {
            int i6 = this.m;
            a(i2, i4, i6, i6);
        }
    }

    /* JADX WARN: Code duplicated, block: B:53:0x00dc  */
    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        androidx.viewpager.widget.a aVar;
        if (this.N) {
            return true;
        }
        boolean zI = false;
        if ((motionEvent.getAction() == 0 && motionEvent.getEdgeFlags() != 0) || (aVar = this.f1495e) == null || aVar.a() == 0) {
            return false;
        }
        if (this.I == null) {
            this.I = VelocityTracker.obtain();
        }
        this.I.addMovement(motionEvent);
        int action = motionEvent.getAction() & 255;
        if (action == 0) {
            this.j.abortAnimation();
            this.v = false;
            e();
            float x = motionEvent.getX();
            this.F = x;
            this.C = x;
            float y = motionEvent.getY();
            this.G = y;
            this.D = y;
            this.H = motionEvent.getPointerId(0);
        } else if (action != 1) {
            if (action != 2) {
                if (action != 3) {
                    if (action == 5) {
                        int actionIndex = motionEvent.getActionIndex();
                        this.C = motionEvent.getX(actionIndex);
                        this.H = motionEvent.getPointerId(actionIndex);
                    } else if (action == 6) {
                        a(motionEvent);
                        this.C = motionEvent.getX(motionEvent.findPointerIndex(this.H));
                    }
                } else if (this.x) {
                    a(this.f1496f, true, 0, false);
                    zI = i();
                }
            } else if (!this.x) {
                int iFindPointerIndex = motionEvent.findPointerIndex(this.H);
                if (iFindPointerIndex == -1) {
                    zI = i();
                } else {
                    float x2 = motionEvent.getX(iFindPointerIndex);
                    float fAbs = Math.abs(x2 - this.C);
                    float y2 = motionEvent.getY(iFindPointerIndex);
                    float fAbs2 = Math.abs(y2 - this.D);
                    if (fAbs > this.B && fAbs > fAbs2) {
                        this.x = true;
                        c(true);
                        float f2 = this.F;
                        this.C = x2 - f2 > 0.0f ? f2 + this.B : f2 - this.B;
                        this.D = y2;
                        setScrollState(1);
                        setScrollingCacheEnabled(true);
                        ViewParent parent = getParent();
                        if (parent != null) {
                            parent.requestDisallowInterceptTouchEvent(true);
                        }
                    }
                    if (this.x) {
                        zI = false | b(motionEvent.getX(motionEvent.findPointerIndex(this.H)));
                    }
                }
            } else if (this.x) {
                zI = false | b(motionEvent.getX(motionEvent.findPointerIndex(this.H)));
            }
        } else if (this.x) {
            VelocityTracker velocityTracker = this.I;
            velocityTracker.computeCurrentVelocity(TimeConstants.SEC, this.K);
            int xVelocity = (int) velocityTracker.getXVelocity(this.H);
            this.v = true;
            int clientWidth = getClientWidth();
            int scrollX = getScrollX();
            f fVarG = g();
            float f3 = clientWidth;
            a(a(fVarG.f1501b, ((scrollX / f3) - fVarG.f1504e) / (fVarG.f1503d + (this.m / f3)), xVelocity, (int) (motionEvent.getX(motionEvent.findPointerIndex(this.H)) - this.F)), true, true, xVelocity);
            zI = i();
        }
        if (zI) {
            t.t(this);
        }
        return true;
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    public void removeView(View view) {
        if (this.t) {
            removeViewInLayout(view);
        } else {
            super.removeView(view);
        }
    }

    public void setAdapter(androidx.viewpager.widget.a aVar) {
        androidx.viewpager.widget.a aVar2 = this.f1495e;
        if (aVar2 != null) {
            aVar2.b((DataSetObserver) null);
            this.f1495e.b((ViewGroup) this);
            for (int i2 = 0; i2 < this.f1492b.size(); i2++) {
                f fVar = this.f1492b.get(i2);
                this.f1495e.a((ViewGroup) this, fVar.f1501b, fVar.f1500a);
            }
            this.f1495e.a((ViewGroup) this);
            this.f1492b.clear();
            h();
            this.f1496f = 0;
            scrollTo(0, 0);
        }
        androidx.viewpager.widget.a aVar3 = this.f1495e;
        this.f1495e = aVar;
        this.f1491a = 0;
        if (this.f1495e != null) {
            if (this.l == null) {
                this.l = new l();
            }
            this.f1495e.b(this.l);
            this.v = false;
            boolean z = this.Q;
            this.Q = true;
            this.f1491a = this.f1495e.a();
            if (this.g >= 0) {
                this.f1495e.a(this.h, this.i);
                a(this.g, false, true);
                this.g = -1;
                this.h = null;
                this.i = null;
            } else if (z) {
                requestLayout();
            } else {
                e();
            }
        }
        List<i> list = this.W;
        if (list == null || list.isEmpty()) {
            return;
        }
        int size = this.W.size();
        for (int i3 = 0; i3 < size; i3++) {
            this.W.get(i3).a(this, aVar3, aVar);
        }
    }

    public void setCurrentItem(int i2) {
        this.v = false;
        a(i2, !this.Q, false);
    }

    public void setOffscreenPageLimit(int i2) {
        if (i2 < 1) {
            Log.w("ViewPager", "Requested offscreen page limit " + i2 + " too small; defaulting to 1");
            i2 = 1;
        }
        if (i2 != this.w) {
            this.w = i2;
            e();
        }
    }

    @Deprecated
    public void setOnPageChangeListener(j jVar) {
        this.U = jVar;
    }

    public void setPageMargin(int i2) {
        int i3 = this.m;
        this.m = i2;
        int width = getWidth();
        a(width, width, i2, i3);
        requestLayout();
    }

    public void setPageMarginDrawable(Drawable drawable) {
        this.n = drawable;
        if (drawable != null) {
            refreshDrawableState();
        }
        setWillNotDraw(drawable == null);
        invalidate();
    }

    void setScrollState(int i2) {
        if (this.f0 == i2) {
            return;
        }
        this.f0 = i2;
        if (this.a0 != null) {
            b(i2 != 0);
        }
        e(i2);
    }

    @Override // android.view.View
    protected boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.n;
    }

    public static class g extends ViewGroup.LayoutParams {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f1505a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f1506b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        float f1507c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        boolean f1508d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        int f1509e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        int f1510f;

        public g() {
            super(-1, -1);
            this.f1507c = 0.0f;
        }

        public g(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f1507c = 0.0f;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, ViewPager.g0);
            this.f1506b = typedArrayObtainStyledAttributes.getInteger(0, 48);
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    private void e(int i2) {
        j jVar = this.U;
        if (jVar != null) {
            jVar.a(i2);
        }
        List<j> list = this.T;
        if (list != null) {
            int size = list.size();
            for (int i3 = 0; i3 < size; i3++) {
                j jVar2 = this.T.get(i3);
                if (jVar2 != null) {
                    jVar2.a(i2);
                }
            }
        }
        j jVar3 = this.V;
        if (jVar3 != null) {
            jVar3.a(i2);
        }
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new g(getContext(), attributeSet);
    }

    public void a(int i2, boolean z) {
        this.v = false;
        a(i2, z, false);
    }

    public void setPageMarginDrawable(int i2) {
        setPageMarginDrawable(androidx.core.content.a.c(getContext(), i2));
    }

    void a(int i2, boolean z, boolean z2) {
        a(i2, z, z2, 0);
    }

    class h extends androidx.core.f.a {
        h() {
        }

        @Override // androidx.core.f.a
        public void a(View view, androidx.core.f.c0.b bVar) {
            super.a(view, bVar);
            bVar.a((CharSequence) ViewPager.class.getName());
            bVar.j(b());
            if (ViewPager.this.canScrollHorizontally(1)) {
                bVar.a(4096);
            }
            if (ViewPager.this.canScrollHorizontally(-1)) {
                bVar.a(8192);
            }
        }

        @Override // androidx.core.f.a
        public void b(View view, AccessibilityEvent accessibilityEvent) {
            androidx.viewpager.widget.a aVar;
            super.b(view, accessibilityEvent);
            accessibilityEvent.setClassName(ViewPager.class.getName());
            accessibilityEvent.setScrollable(b());
            if (accessibilityEvent.getEventType() != 4096 || (aVar = ViewPager.this.f1495e) == null) {
                return;
            }
            accessibilityEvent.setItemCount(aVar.a());
            accessibilityEvent.setFromIndex(ViewPager.this.f1496f);
            accessibilityEvent.setToIndex(ViewPager.this.f1496f);
        }

        private boolean b() {
            androidx.viewpager.widget.a aVar = ViewPager.this.f1495e;
            return aVar != null && aVar.a() > 1;
        }

        @Override // androidx.core.f.a
        public boolean a(View view, int i, Bundle bundle) {
            if (super.a(view, i, bundle)) {
                return true;
            }
            if (i != 4096) {
                if (i != 8192 || !ViewPager.this.canScrollHorizontally(-1)) {
                    return false;
                }
                ViewPager viewPager = ViewPager.this;
                viewPager.setCurrentItem(viewPager.f1496f - 1);
                return true;
            }
            if (!ViewPager.this.canScrollHorizontally(1)) {
                return false;
            }
            ViewPager viewPager2 = ViewPager.this;
            viewPager2.setCurrentItem(viewPager2.f1496f + 1);
            return true;
        }
    }

    void a(int i2, boolean z, boolean z2, int i3) {
        androidx.viewpager.widget.a aVar = this.f1495e;
        if (aVar != null && aVar.a() > 0) {
            if (!z2 && this.f1496f == i2 && this.f1492b.size() != 0) {
                setScrollingCacheEnabled(false);
                return;
            }
            if (i2 < 0) {
                i2 = 0;
            } else if (i2 >= this.f1495e.a()) {
                i2 = this.f1495e.a() - 1;
            }
            int i4 = this.w;
            int i5 = this.f1496f;
            if (i2 > i5 + i4 || i2 < i5 - i4) {
                for (int i6 = 0; i6 < this.f1492b.size(); i6++) {
                    this.f1492b.get(i6).f1502c = true;
                }
            }
            boolean z3 = this.f1496f != i2;
            if (this.Q) {
                this.f1496f = i2;
                if (z3) {
                    d(i2);
                }
                requestLayout();
                return;
            }
            c(i2);
            a(i2, z, i3, z3);
            return;
        }
        setScrollingCacheEnabled(false);
    }

    boolean d() {
        androidx.viewpager.widget.a aVar = this.f1495e;
        if (aVar == null || this.f1496f >= aVar.a() - 1) {
            return false;
        }
        a(this.f1496f + 1, true);
        return true;
    }

    public ViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f1492b = new ArrayList<>();
        this.f1493c = new f();
        this.f1494d = new Rect();
        this.g = -1;
        this.h = null;
        this.i = null;
        this.q = -3.4028235E38f;
        this.r = Float.MAX_VALUE;
        this.w = 1;
        this.H = -1;
        this.Q = true;
        this.e0 = new c();
        this.f0 = 0;
        b();
    }

    private void f() {
        this.x = false;
        this.y = false;
        VelocityTracker velocityTracker = this.I;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.I = null;
        }
    }

    public void b(i iVar) {
        List<i> list = this.W;
        if (list != null) {
            list.remove(iVar);
        }
    }

    f b(View view) {
        for (int i2 = 0; i2 < this.f1492b.size(); i2++) {
            f fVar = this.f1492b.get(i2);
            if (this.f1495e.a(view, fVar.f1500a)) {
                return fVar;
            }
        }
        return null;
    }

    private void a(int i2, boolean z, int i3, boolean z2) {
        f fVarB = b(i2);
        int clientWidth = fVarB != null ? (int) (getClientWidth() * Math.max(this.q, Math.min(fVarB.f1504e, this.r))) : 0;
        if (z) {
            a(clientWidth, 0, i3);
            if (z2) {
                d(i2);
                return;
            }
            return;
        }
        if (z2) {
            d(i2);
        }
        a(false);
        scrollTo(clientWidth, 0);
        f(clientWidth);
    }

    f b(int i2) {
        for (int i3 = 0; i3 < this.f1492b.size(); i3++) {
            f fVar = this.f1492b.get(i3);
            if (fVar.f1501b == i2) {
                return fVar;
            }
        }
        return null;
    }

    private void b(int i2, float f2, int i3) {
        j jVar = this.U;
        if (jVar != null) {
            jVar.a(i2, f2, i3);
        }
        List<j> list = this.T;
        if (list != null) {
            int size = list.size();
            for (int i4 = 0; i4 < size; i4++) {
                j jVar2 = this.T.get(i4);
                if (jVar2 != null) {
                    jVar2.a(i2, f2, i3);
                }
            }
        }
        j jVar3 = this.V;
        if (jVar3 != null) {
            jVar3.a(i2, f2, i3);
        }
    }

    j a(j jVar) {
        j jVar2 = this.V;
        this.V = jVar;
        return jVar2;
    }

    private void b(boolean z) {
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            getChildAt(i2).setLayerType(z ? this.b0 : 0, null);
        }
    }

    float a(float f2) {
        return (float) Math.sin((f2 - 0.5f) * 0.47123894f);
    }

    void a(int i2, int i3, int i4) {
        int scrollX;
        int iAbs;
        if (getChildCount() == 0) {
            setScrollingCacheEnabled(false);
            return;
        }
        Scroller scroller = this.j;
        if ((scroller == null || scroller.isFinished()) ? false : true) {
            scrollX = this.k ? this.j.getCurrX() : this.j.getStartX();
            this.j.abortAnimation();
            setScrollingCacheEnabled(false);
        } else {
            scrollX = getScrollX();
        }
        int i5 = scrollX;
        int scrollY = getScrollY();
        int i6 = i2 - i5;
        int i7 = i3 - scrollY;
        if (i6 == 0 && i7 == 0) {
            a(false);
            e();
            setScrollState(0);
            return;
        }
        setScrollingCacheEnabled(true);
        setScrollState(2);
        int clientWidth = getClientWidth();
        int i8 = clientWidth / 2;
        float f2 = clientWidth;
        float f3 = i8;
        float fA = f3 + (a(Math.min(1.0f, (Math.abs(i6) * 1.0f) / f2)) * f3);
        int iAbs2 = Math.abs(i4);
        if (iAbs2 > 0) {
            iAbs = Math.round(Math.abs(fA / iAbs2) * 1000.0f) * 4;
        } else {
            iAbs = (int) (((Math.abs(i6) / ((f2 * this.f1495e.b(this.f1496f)) + this.m)) + 1.0f) * 100.0f);
        }
        int iMin = Math.min(iAbs, 600);
        this.k = false;
        this.j.startScroll(i5, scrollY, i6, i7, iMin);
        t.t(this);
    }

    private boolean b(float f2) {
        boolean z;
        boolean z2;
        float f3 = this.C - f2;
        this.C = f2;
        float scrollX = getScrollX() + f3;
        float clientWidth = getClientWidth();
        float f4 = this.q * clientWidth;
        float f5 = this.r * clientWidth;
        boolean z3 = false;
        f fVar = this.f1492b.get(0);
        ArrayList<f> arrayList = this.f1492b;
        f fVar2 = arrayList.get(arrayList.size() - 1);
        if (fVar.f1501b != 0) {
            f4 = fVar.f1504e * clientWidth;
            z = false;
        } else {
            z = true;
        }
        if (fVar2.f1501b != this.f1495e.a() - 1) {
            f5 = fVar2.f1504e * clientWidth;
            z2 = false;
        } else {
            z2 = true;
        }
        if (scrollX < f4) {
            if (z) {
                this.O.onPull(Math.abs(f4 - scrollX) / clientWidth);
                z3 = true;
            }
            scrollX = f4;
        } else if (scrollX > f5) {
            if (z2) {
                this.P.onPull(Math.abs(scrollX - f5) / clientWidth);
                z3 = true;
            }
            scrollX = f5;
        }
        int i2 = (int) scrollX;
        this.C += scrollX - i2;
        scrollTo(i2, getScrollY());
        f(i2);
        return z3;
    }

    f a(int i2, int i3) {
        f fVar = new f();
        fVar.f1501b = i2;
        fVar.f1500a = this.f1495e.a((ViewGroup) this, i2);
        fVar.f1503d = this.f1495e.b(i2);
        if (i3 >= 0 && i3 < this.f1492b.size()) {
            this.f1492b.add(i3, fVar);
        } else {
            this.f1492b.add(fVar);
        }
        return fVar;
    }

    void a() {
        int iA = this.f1495e.a();
        this.f1491a = iA;
        boolean z = this.f1492b.size() < (this.w * 2) + 1 && this.f1492b.size() < iA;
        int iMax = this.f1496f;
        int i2 = 0;
        boolean z2 = false;
        while (i2 < this.f1492b.size()) {
            f fVar = this.f1492b.get(i2);
            int iA2 = this.f1495e.a(fVar.f1500a);
            if (iA2 != -1) {
                if (iA2 == -2) {
                    this.f1492b.remove(i2);
                    i2--;
                    if (!z2) {
                        this.f1495e.b((ViewGroup) this);
                        z2 = true;
                    }
                    this.f1495e.a((ViewGroup) this, fVar.f1501b, fVar.f1500a);
                    int i3 = this.f1496f;
                    if (i3 == fVar.f1501b) {
                        iMax = Math.max(0, Math.min(i3, iA - 1));
                    }
                } else {
                    int i4 = fVar.f1501b;
                    if (i4 != iA2) {
                        if (i4 == this.f1496f) {
                            iMax = iA2;
                        }
                        fVar.f1501b = iA2;
                    }
                }
                z = true;
            }
            i2++;
        }
        if (z2) {
            this.f1495e.a((ViewGroup) this);
        }
        Collections.sort(this.f1492b, h0);
        if (z) {
            int childCount = getChildCount();
            for (int i5 = 0; i5 < childCount; i5++) {
                g gVar = (g) getChildAt(i5).getLayoutParams();
                if (!gVar.f1505a) {
                    gVar.f1507c = 0.0f;
                }
            }
            a(iMax, false, true);
            requestLayout();
        }
    }

    private static boolean c(View view) {
        return view.getClass().getAnnotation(e.class) != null;
    }

    private void c(boolean z) {
        ViewParent parent = getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(z);
        }
    }

    boolean c() {
        int i2 = this.f1496f;
        if (i2 <= 0) {
            return false;
        }
        a(i2 - 1, true);
        return true;
    }

    private void a(f fVar, int i2, f fVar2) {
        int i3;
        int i4;
        f fVar3;
        f fVar4;
        int iA = this.f1495e.a();
        int clientWidth = getClientWidth();
        float f2 = clientWidth > 0 ? this.m / clientWidth : 0.0f;
        if (fVar2 != null) {
            int i5 = fVar2.f1501b;
            int i6 = fVar.f1501b;
            if (i5 < i6) {
                int i7 = 0;
                float fB = fVar2.f1504e + fVar2.f1503d + f2;
                while (true) {
                    i5++;
                    if (i5 > fVar.f1501b || i7 >= this.f1492b.size()) {
                        break;
                    }
                    f fVar5 = this.f1492b.get(i7);
                    while (true) {
                        fVar4 = fVar5;
                        if (i5 <= fVar4.f1501b || i7 >= this.f1492b.size() - 1) {
                            break;
                        }
                        i7++;
                        fVar5 = this.f1492b.get(i7);
                    }
                    while (i5 < fVar4.f1501b) {
                        fB += this.f1495e.b(i5) + f2;
                        i5++;
                    }
                    fVar4.f1504e = fB;
                    fB += fVar4.f1503d + f2;
                }
            } else if (i5 > i6) {
                int size = this.f1492b.size() - 1;
                float fB2 = fVar2.f1504e;
                while (true) {
                    i5--;
                    if (i5 < fVar.f1501b || size < 0) {
                        break;
                    }
                    f fVar6 = this.f1492b.get(size);
                    while (true) {
                        fVar3 = fVar6;
                        if (i5 >= fVar3.f1501b || size <= 0) {
                            break;
                        }
                        size--;
                        fVar6 = this.f1492b.get(size);
                    }
                    while (i5 > fVar3.f1501b) {
                        fB2 -= this.f1495e.b(i5) + f2;
                        i5--;
                    }
                    fB2 -= fVar3.f1503d + f2;
                    fVar3.f1504e = fB2;
                }
            }
        }
        int size2 = this.f1492b.size();
        float fB3 = fVar.f1504e;
        int i8 = fVar.f1501b;
        int i9 = i8 - 1;
        this.q = i8 == 0 ? fB3 : -3.4028235E38f;
        int i10 = iA - 1;
        this.r = fVar.f1501b == i10 ? (fVar.f1504e + fVar.f1503d) - 1.0f : Float.MAX_VALUE;
        int i11 = i2 - 1;
        while (i11 >= 0) {
            f fVar7 = this.f1492b.get(i11);
            while (true) {
                i4 = fVar7.f1501b;
                if (i9 <= i4) {
                    break;
                }
                fB3 -= this.f1495e.b(i9) + f2;
                i9--;
            }
            fB3 -= fVar7.f1503d + f2;
            fVar7.f1504e = fB3;
            if (i4 == 0) {
                this.q = fB3;
            }
            i11--;
            i9--;
        }
        float fB4 = fVar.f1504e + fVar.f1503d + f2;
        int i12 = fVar.f1501b + 1;
        int i13 = i2 + 1;
        while (i13 < size2) {
            f fVar8 = this.f1492b.get(i13);
            while (true) {
                i3 = fVar8.f1501b;
                if (i12 >= i3) {
                    break;
                }
                fB4 += this.f1495e.b(i12) + f2;
                i12++;
            }
            if (i3 == i10) {
                this.r = (fVar8.f1503d + fB4) - 1.0f;
            }
            fVar8.f1504e = fB4;
            fB4 += fVar8.f1503d + f2;
            i13++;
            i12++;
        }
    }

    f a(View view) {
        while (true) {
            Object parent = view.getParent();
            if (parent != this) {
                if (parent == null || !(parent instanceof View)) {
                    return null;
                }
                view = (View) parent;
            } else {
                return b(view);
            }
        }
    }

    private void a(int i2, int i3, int i4, int i5) {
        if (i3 > 0 && !this.f1492b.isEmpty()) {
            if (!this.j.isFinished()) {
                this.j.setFinalX(getCurrentItem() * getClientWidth());
                return;
            } else {
                scrollTo((int) ((getScrollX() / (((i3 - getPaddingLeft()) - getPaddingRight()) + i5)) * (((i2 - getPaddingLeft()) - getPaddingRight()) + i4)), getScrollY());
                return;
            }
        }
        f fVarB = b(this.f1496f);
        int iMin = (int) ((fVarB != null ? Math.min(fVarB.f1504e, this.r) : 0.0f) * ((i2 - getPaddingLeft()) - getPaddingRight()));
        if (iMin != getScrollX()) {
            a(false);
            scrollTo(iMin, getScrollY());
        }
    }

    /* JADX WARN: Code duplicated, block: B:22:0x0066  */
    protected void a(int i2, float f2, int i3) {
        int iMax;
        int width;
        int left;
        if (this.S > 0) {
            int scrollX = getScrollX();
            int paddingLeft = getPaddingLeft();
            int paddingRight = getPaddingRight();
            int width2 = getWidth();
            int childCount = getChildCount();
            int measuredWidth = paddingRight;
            int i4 = paddingLeft;
            for (int i5 = 0; i5 < childCount; i5++) {
                View childAt = getChildAt(i5);
                g gVar = (g) childAt.getLayoutParams();
                if (gVar.f1505a) {
                    int i6 = gVar.f1506b & 7;
                    if (i6 == 1) {
                        iMax = Math.max((width2 - childAt.getMeasuredWidth()) / 2, i4);
                    } else {
                        if (i6 == 3) {
                            width = childAt.getWidth() + i4;
                        } else if (i6 != 5) {
                            width = i4;
                        } else {
                            iMax = (width2 - measuredWidth) - childAt.getMeasuredWidth();
                            measuredWidth += childAt.getMeasuredWidth();
                        }
                        left = (i4 + scrollX) - childAt.getLeft();
                        if (left != 0) {
                            childAt.offsetLeftAndRight(left);
                        }
                        i4 = width;
                    }
                    int i7 = iMax;
                    width = i4;
                    i4 = i7;
                    left = (i4 + scrollX) - childAt.getLeft();
                    if (left != 0) {
                        childAt.offsetLeftAndRight(left);
                    }
                    i4 = width;
                }
            }
        }
        b(i2, f2, i3);
        if (this.a0 != null) {
            int scrollX2 = getScrollX();
            int childCount2 = getChildCount();
            for (int i8 = 0; i8 < childCount2; i8++) {
                View childAt2 = getChildAt(i8);
                if (!((g) childAt2.getLayoutParams()).f1505a) {
                    this.a0.a(childAt2, (childAt2.getLeft() - scrollX2) / getClientWidth());
                }
            }
        }
        this.R = true;
    }

    private void a(boolean z) {
        boolean z2 = this.f0 == 2;
        if (z2) {
            setScrollingCacheEnabled(false);
            if (!this.j.isFinished()) {
                this.j.abortAnimation();
                int scrollX = getScrollX();
                int scrollY = getScrollY();
                int currX = this.j.getCurrX();
                int currY = this.j.getCurrY();
                if (scrollX != currX || scrollY != currY) {
                    scrollTo(currX, currY);
                    if (currX != scrollX) {
                        f(currX);
                    }
                }
            }
        }
        this.v = false;
        boolean z3 = z2;
        for (int i2 = 0; i2 < this.f1492b.size(); i2++) {
            f fVar = this.f1492b.get(i2);
            if (fVar.f1502c) {
                fVar.f1502c = false;
                z3 = true;
            }
        }
        if (z3) {
            if (z) {
                t.a(this, this.e0);
            } else {
                this.e0.run();
            }
        }
    }

    private boolean a(float f2, float f3) {
        return (f2 < ((float) this.A) && f3 > 0.0f) || (f2 > ((float) (getWidth() - this.A)) && f3 < 0.0f);
    }

    private int a(int i2, float f2, int i3, int i4) {
        if (Math.abs(i4) <= this.L || Math.abs(i3) <= this.J) {
            i2 += (int) (f2 + (i2 >= this.f1496f ? 0.4f : 0.6f));
        } else if (i3 <= 0) {
            i2++;
        }
        if (this.f1492b.size() <= 0) {
            return i2;
        }
        f fVar = this.f1492b.get(0);
        ArrayList<f> arrayList = this.f1492b;
        return Math.max(fVar.f1501b, Math.min(i2, arrayList.get(arrayList.size() - 1).f1501b));
    }

    private void a(MotionEvent motionEvent) {
        int actionIndex = motionEvent.getActionIndex();
        if (motionEvent.getPointerId(actionIndex) == this.H) {
            int i2 = actionIndex == 0 ? 1 : 0;
            this.C = motionEvent.getX(i2);
            this.H = motionEvent.getPointerId(i2);
            VelocityTracker velocityTracker = this.I;
            if (velocityTracker != null) {
                velocityTracker.clear();
            }
        }
    }

    protected boolean a(View view, boolean z, int i2, int i3, int i4) {
        int i5;
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int scrollX = view.getScrollX();
            int scrollY = view.getScrollY();
            for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
                View childAt = viewGroup.getChildAt(childCount);
                int i6 = i3 + scrollX;
                if (i6 >= childAt.getLeft() && i6 < childAt.getRight() && (i5 = i4 + scrollY) >= childAt.getTop() && i5 < childAt.getBottom() && a(childAt, true, i2, i6 - childAt.getLeft(), i5 - childAt.getTop())) {
                    return true;
                }
            }
        }
        return z && view.canScrollHorizontally(-i2);
    }

    public boolean a(KeyEvent keyEvent) {
        if (keyEvent.getAction() == 0) {
            int keyCode = keyEvent.getKeyCode();
            if (keyCode == 21) {
                if (keyEvent.hasModifiers(2)) {
                    return c();
                }
                return a(17);
            }
            if (keyCode == 22) {
                if (keyEvent.hasModifiers(2)) {
                    return d();
                }
                return a(66);
            }
            if (keyCode == 61) {
                if (keyEvent.hasNoModifiers()) {
                    return a(2);
                }
                if (keyEvent.hasModifiers(1)) {
                    return a(1);
                }
            }
        }
        return false;
    }

    /* JADX WARN: Code duplicated, block: B:19:0x0068  */
    public boolean a(int i2) {
        boolean zRequestFocus;
        boolean z;
        View viewFindFocus = findFocus();
        boolean zC = false;
        View view = null;
        if (viewFindFocus != this) {
            if (viewFindFocus != null) {
                ViewParent parent = viewFindFocus.getParent();
                while (true) {
                    if (!(parent instanceof ViewGroup)) {
                        z = false;
                        break;
                    }
                    if (parent == this) {
                        z = true;
                        break;
                    }
                    parent = parent.getParent();
                }
                if (z) {
                    view = viewFindFocus;
                } else {
                    StringBuilder sb = new StringBuilder();
                    sb.append(viewFindFocus.getClass().getSimpleName());
                    for (ViewParent parent2 = viewFindFocus.getParent(); parent2 instanceof ViewGroup; parent2 = parent2.getParent()) {
                        sb.append(" => ");
                        sb.append(parent2.getClass().getSimpleName());
                    }
                    Log.e("ViewPager", "arrowScroll tried to find focus based on non-child current focused view " + sb.toString());
                }
            } else {
                view = viewFindFocus;
            }
        }
        View viewFindNextFocus = FocusFinder.getInstance().findNextFocus(this, view, i2);
        if (viewFindNextFocus != null && viewFindNextFocus != view) {
            if (i2 == 17) {
                int i3 = a(this.f1494d, viewFindNextFocus).left;
                int i4 = a(this.f1494d, view).left;
                if (view != null && i3 >= i4) {
                    zRequestFocus = c();
                } else {
                    zRequestFocus = viewFindNextFocus.requestFocus();
                }
            } else if (i2 == 66) {
                int i5 = a(this.f1494d, viewFindNextFocus).left;
                int i6 = a(this.f1494d, view).left;
                if (view != null && i5 <= i6) {
                    zRequestFocus = d();
                } else {
                    zRequestFocus = viewFindNextFocus.requestFocus();
                }
            }
            zC = zRequestFocus;
        } else if (i2 == 17 || i2 == 1) {
            zC = c();
        } else if (i2 == 66 || i2 == 2) {
            zC = d();
        }
        if (zC) {
            playSoundEffect(SoundEffectConstants.getContantForFocusDirection(i2));
        }
        return zC;
    }

    private Rect a(Rect rect, View view) {
        if (rect == null) {
            rect = new Rect();
        }
        if (view == null) {
            rect.set(0, 0, 0, 0);
            return rect;
        }
        rect.left = view.getLeft();
        rect.right = view.getRight();
        rect.top = view.getTop();
        rect.bottom = view.getBottom();
        ViewParent parent = view.getParent();
        while ((parent instanceof ViewGroup) && parent != this) {
            ViewGroup viewGroup = (ViewGroup) parent;
            rect.left += viewGroup.getLeft();
            rect.right += viewGroup.getRight();
            rect.top += viewGroup.getTop();
            rect.bottom += viewGroup.getBottom();
            parent = viewGroup.getParent();
        }
        return rect;
    }
}
