package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.R$attr;
import androidx.appcompat.R$styleable;
import com.blankj.utilcode.constant.MemoryConstants;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class Toolbar extends ViewGroup {
    private ColorStateList A;
    private boolean B;
    private boolean C;
    private final ArrayList<View> D;
    private final ArrayList<View> F;
    private final int[] G;
    f H;
    private final ActionMenuView.e I;
    private e0 J;
    private ActionMenuPresenter K;
    private d L;
    private androidx.appcompat.view.menu.m.a M;
    private androidx.appcompat.view.menu.g.a N;
    private boolean O;
    private final Runnable P;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private ActionMenuView f677a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private TextView f678b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private TextView f679c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private ImageButton f680d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private ImageView f681e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private Drawable f682f;
    private CharSequence g;
    ImageButton h;
    View i;
    private Context j;
    private int k;
    private int l;
    private int m;
    int n;
    private int o;
    private int p;
    private int q;
    private int r;
    private int s;
    private w t;
    private int u;
    private int v;
    private int w;
    private CharSequence x;
    private CharSequence y;
    private ColorStateList z;

    class a implements ActionMenuView.e {
        a() {
        }

        @Override // androidx.appcompat.widget.ActionMenuView.e
        public boolean onMenuItemClick(MenuItem menuItem) {
            f fVar = Toolbar.this.H;
            if (fVar != null) {
                return fVar.onMenuItemClick(menuItem);
            }
            return false;
        }
    }

    class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            Toolbar.this.k();
        }
    }

    class c implements View.OnClickListener {
        c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Toolbar.this.c();
        }
    }

    public interface f {
        boolean onMenuItemClick(MenuItem menuItem);
    }

    public Toolbar(Context context) {
        this(context, null);
    }

    private MenuInflater getMenuInflater() {
        return new androidx.appcompat.d.g(getContext());
    }

    private void l() {
        if (this.t == null) {
            this.t = new w();
        }
    }

    private void m() {
        if (this.f681e == null) {
            this.f681e = new AppCompatImageView(getContext());
        }
    }

    private void n() {
        o();
        if (this.f677a.i() == null) {
            androidx.appcompat.view.menu.g gVar = (androidx.appcompat.view.menu.g) this.f677a.getMenu();
            if (this.L == null) {
                this.L = new d();
            }
            this.f677a.setExpandedActionViewsExclusive(true);
            gVar.a(this.L, this.j);
        }
    }

    private void o() {
        if (this.f677a == null) {
            this.f677a = new ActionMenuView(getContext());
            this.f677a.setPopupTheme(this.k);
            this.f677a.setOnMenuItemClickListener(this.I);
            this.f677a.a(this.M, this.N);
            e eVarGenerateDefaultLayoutParams = generateDefaultLayoutParams();
            eVarGenerateDefaultLayoutParams.f293a = 8388613 | (this.n & 112);
            this.f677a.setLayoutParams(eVarGenerateDefaultLayoutParams);
            a((View) this.f677a, false);
        }
    }

    private void p() {
        if (this.f680d == null) {
            this.f680d = new AppCompatImageButton(getContext(), null, R$attr.toolbarNavigationButtonStyle);
            e eVarGenerateDefaultLayoutParams = generateDefaultLayoutParams();
            eVarGenerateDefaultLayoutParams.f293a = 8388611 | (this.n & 112);
            this.f680d.setLayoutParams(eVarGenerateDefaultLayoutParams);
        }
    }

    private void q() {
        removeCallbacks(this.P);
        post(this.P);
    }

    private boolean r() {
        if (!this.O) {
            return false;
        }
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (d(childAt) && childAt.getMeasuredWidth() > 0 && childAt.getMeasuredHeight() > 0) {
                return false;
            }
        }
        return true;
    }

    public void a(androidx.appcompat.view.menu.g gVar, ActionMenuPresenter actionMenuPresenter) {
        if (gVar == null && this.f677a == null) {
            return;
        }
        o();
        androidx.appcompat.view.menu.g gVarI = this.f677a.i();
        if (gVarI == gVar) {
            return;
        }
        if (gVarI != null) {
            gVarI.b(this.K);
            gVarI.b(this.L);
        }
        if (this.L == null) {
            this.L = new d();
        }
        actionMenuPresenter.c(true);
        if (gVar != null) {
            gVar.a(actionMenuPresenter, this.j);
            gVar.a(this.L, this.j);
        } else {
            actionMenuPresenter.a(this.j, (androidx.appcompat.view.menu.g) null);
            this.L.a(this.j, (androidx.appcompat.view.menu.g) null);
            actionMenuPresenter.a(true);
            this.L.a(true);
        }
        this.f677a.setPopupTheme(this.k);
        this.f677a.setPresenter(actionMenuPresenter);
        this.K = actionMenuPresenter;
    }

    public boolean b() {
        ActionMenuView actionMenuView;
        return getVisibility() == 0 && (actionMenuView = this.f677a) != null && actionMenuView.h();
    }

    public void c() {
        d dVar = this.L;
        androidx.appcompat.view.menu.j jVar = dVar == null ? null : dVar.f687b;
        if (jVar != null) {
            jVar.collapseActionView();
        }
    }

    @Override // android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return super.checkLayoutParams(layoutParams) && (layoutParams instanceof e);
    }

    public void d() {
        ActionMenuView actionMenuView = this.f677a;
        if (actionMenuView != null) {
            actionMenuView.c();
        }
    }

    void e() {
        if (this.h == null) {
            this.h = new AppCompatImageButton(getContext(), null, R$attr.toolbarNavigationButtonStyle);
            this.h.setImageDrawable(this.f682f);
            this.h.setContentDescription(this.g);
            e eVarGenerateDefaultLayoutParams = generateDefaultLayoutParams();
            eVarGenerateDefaultLayoutParams.f293a = 8388611 | (this.n & 112);
            eVarGenerateDefaultLayoutParams.f689b = 2;
            this.h.setLayoutParams(eVarGenerateDefaultLayoutParams);
            this.h.setOnClickListener(new c());
        }
    }

    public boolean f() {
        d dVar = this.L;
        return (dVar == null || dVar.f687b == null) ? false : true;
    }

    public boolean g() {
        ActionMenuView actionMenuView = this.f677a;
        return actionMenuView != null && actionMenuView.e();
    }

    public CharSequence getCollapseContentDescription() {
        ImageButton imageButton = this.h;
        if (imageButton != null) {
            return imageButton.getContentDescription();
        }
        return null;
    }

    public Drawable getCollapseIcon() {
        ImageButton imageButton = this.h;
        if (imageButton != null) {
            return imageButton.getDrawable();
        }
        return null;
    }

    public int getContentInsetEnd() {
        w wVar = this.t;
        if (wVar != null) {
            return wVar.a();
        }
        return 0;
    }

    public int getContentInsetEndWithActions() {
        int i = this.v;
        return i != Integer.MIN_VALUE ? i : getContentInsetEnd();
    }

    public int getContentInsetLeft() {
        w wVar = this.t;
        if (wVar != null) {
            return wVar.b();
        }
        return 0;
    }

    public int getContentInsetRight() {
        w wVar = this.t;
        if (wVar != null) {
            return wVar.c();
        }
        return 0;
    }

    public int getContentInsetStart() {
        w wVar = this.t;
        if (wVar != null) {
            return wVar.d();
        }
        return 0;
    }

    public int getContentInsetStartWithNavigation() {
        int i = this.u;
        return i != Integer.MIN_VALUE ? i : getContentInsetStart();
    }

    public int getCurrentContentInsetEnd() {
        androidx.appcompat.view.menu.g gVarI;
        ActionMenuView actionMenuView = this.f677a;
        return actionMenuView != null && (gVarI = actionMenuView.i()) != null && gVarI.hasVisibleItems() ? Math.max(getContentInsetEnd(), Math.max(this.v, 0)) : getContentInsetEnd();
    }

    public int getCurrentContentInsetLeft() {
        return androidx.core.f.t.j(this) == 1 ? getCurrentContentInsetEnd() : getCurrentContentInsetStart();
    }

    public int getCurrentContentInsetRight() {
        return androidx.core.f.t.j(this) == 1 ? getCurrentContentInsetStart() : getCurrentContentInsetEnd();
    }

    public int getCurrentContentInsetStart() {
        return getNavigationIcon() != null ? Math.max(getContentInsetStart(), Math.max(this.u, 0)) : getContentInsetStart();
    }

    public Drawable getLogo() {
        ImageView imageView = this.f681e;
        if (imageView != null) {
            return imageView.getDrawable();
        }
        return null;
    }

    public CharSequence getLogoDescription() {
        ImageView imageView = this.f681e;
        if (imageView != null) {
            return imageView.getContentDescription();
        }
        return null;
    }

    public Menu getMenu() {
        n();
        return this.f677a.getMenu();
    }

    public CharSequence getNavigationContentDescription() {
        ImageButton imageButton = this.f680d;
        if (imageButton != null) {
            return imageButton.getContentDescription();
        }
        return null;
    }

    public Drawable getNavigationIcon() {
        ImageButton imageButton = this.f680d;
        if (imageButton != null) {
            return imageButton.getDrawable();
        }
        return null;
    }

    ActionMenuPresenter getOuterActionMenuPresenter() {
        return this.K;
    }

    public Drawable getOverflowIcon() {
        n();
        return this.f677a.getOverflowIcon();
    }

    Context getPopupContext() {
        return this.j;
    }

    public int getPopupTheme() {
        return this.k;
    }

    public CharSequence getSubtitle() {
        return this.y;
    }

    final TextView getSubtitleTextView() {
        return this.f679c;
    }

    public CharSequence getTitle() {
        return this.x;
    }

    public int getTitleMarginBottom() {
        return this.s;
    }

    public int getTitleMarginEnd() {
        return this.q;
    }

    public int getTitleMarginStart() {
        return this.p;
    }

    public int getTitleMarginTop() {
        return this.r;
    }

    final TextView getTitleTextView() {
        return this.f678b;
    }

    public o getWrapper() {
        if (this.J == null) {
            this.J = new e0(this, true);
        }
        return this.J;
    }

    public boolean h() {
        ActionMenuView actionMenuView = this.f677a;
        return actionMenuView != null && actionMenuView.f();
    }

    public boolean i() {
        ActionMenuView actionMenuView = this.f677a;
        return actionMenuView != null && actionMenuView.g();
    }

    void j() {
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = getChildAt(childCount);
            if (((e) childAt.getLayoutParams()).f689b != 2 && childAt != this.f677a) {
                removeViewAt(childCount);
                this.F.add(childAt);
            }
        }
    }

    public boolean k() {
        ActionMenuView actionMenuView = this.f677a;
        return actionMenuView != null && actionMenuView.j();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeCallbacks(this.P);
    }

    @Override // android.view.View
    public boolean onHoverEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 9) {
            this.C = false;
        }
        if (!this.C) {
            boolean zOnHoverEvent = super.onHoverEvent(motionEvent);
            if (actionMasked == 9 && !zOnHoverEvent) {
                this.C = true;
            }
        }
        if (actionMasked == 10 || actionMasked == 3) {
            this.C = false;
        }
        return true;
    }

    /* JADX WARN: Code duplicated, block: B:100:0x028f  */
    /* JADX WARN: Code duplicated, block: B:102:0x0292  */
    /* JADX WARN: Code duplicated, block: B:105:0x02a6 A[LOOP:0: B:104:0x02a4->B:105:0x02a6, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:108:0x02c8 A[LOOP:1: B:107:0x02c6->B:108:0x02c8, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:112:0x02f3 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:113:0x02f5  */
    /* JADX WARN: Code duplicated, block: B:114:0x02f9  */
    /* JADX WARN: Code duplicated, block: B:117:0x0302 A[LOOP:2: B:116:0x0300->B:117:0x0302, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:19:0x005f A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:20:0x0061  */
    /* JADX WARN: Code duplicated, block: B:21:0x0068  */
    /* JADX WARN: Code duplicated, block: B:24:0x0076 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:25:0x0078  */
    /* JADX WARN: Code duplicated, block: B:26:0x007f  */
    /* JADX WARN: Code duplicated, block: B:29:0x00b3 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:30:0x00b5  */
    /* JADX WARN: Code duplicated, block: B:31:0x00bc  */
    /* JADX WARN: Code duplicated, block: B:34:0x00ca A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:35:0x00cc  */
    /* JADX WARN: Code duplicated, block: B:36:0x00d3  */
    /* JADX WARN: Code duplicated, block: B:39:0x00e7  */
    /* JADX WARN: Code duplicated, block: B:40:0x0100  */
    /* JADX WARN: Code duplicated, block: B:42:0x0105  */
    /* JADX WARN: Code duplicated, block: B:43:0x011d  */
    /* JADX WARN: Code duplicated, block: B:49:0x012b A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:50:0x012d  */
    /* JADX WARN: Code duplicated, block: B:51:0x0130  */
    /* JADX WARN: Code duplicated, block: B:53:0x0134  */
    /* JADX WARN: Code duplicated, block: B:54:0x0137  */
    /* JADX WARN: Code duplicated, block: B:57:0x0147  */
    /* JADX WARN: Code duplicated, block: B:59:0x014f A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:66:0x016a  */
    /* JADX WARN: Code duplicated, block: B:68:0x016e  */
    /* JADX WARN: Code duplicated, block: B:70:0x0180  */
    /* JADX WARN: Code duplicated, block: B:71:0x0183  */
    /* JADX WARN: Code duplicated, block: B:73:0x018e  */
    /* JADX WARN: Code duplicated, block: B:75:0x019a  */
    /* JADX WARN: Code duplicated, block: B:76:0x01a8  */
    /* JADX WARN: Code duplicated, block: B:78:0x01b9 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:79:0x01bb  */
    /* JADX WARN: Code duplicated, block: B:80:0x01bf  */
    /* JADX WARN: Code duplicated, block: B:83:0x01d3  */
    /* JADX WARN: Code duplicated, block: B:84:0x01f7  */
    /* JADX WARN: Code duplicated, block: B:86:0x01fa  */
    /* JADX WARN: Code duplicated, block: B:87:0x0220  */
    /* JADX WARN: Code duplicated, block: B:89:0x0223  */
    /* JADX WARN: Code duplicated, block: B:91:0x022c A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:92:0x022e  */
    /* JADX WARN: Code duplicated, block: B:93:0x0231  */
    /* JADX WARN: Code duplicated, block: B:96:0x0245  */
    /* JADX WARN: Code duplicated, block: B:97:0x0268  */
    /* JADX WARN: Code duplicated, block: B:99:0x026b  */
    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int iA;
        int iB;
        int iMax;
        int iMin;
        boolean zD;
        boolean zD2;
        int measuredHeight;
        TextView textView;
        TextView textView2;
        e eVar;
        e eVar2;
        boolean z2;
        int i5;
        int i6;
        int paddingTop;
        int i7;
        int i8;
        int i9;
        int i10;
        char c2;
        int i11;
        int i12;
        int i13;
        int iMax2;
        int i14;
        int i15;
        int i16;
        int i17;
        int i18;
        int size;
        int iA2;
        int i19;
        int i20;
        int size2;
        int i21;
        int i22;
        int i23;
        int size3;
        boolean z3 = androidx.core.f.t.j(this) == 1;
        int width = getWidth();
        int height = getHeight();
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop2 = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int i24 = width - paddingRight;
        int[] iArr = this.G;
        iArr[1] = 0;
        iArr[0] = 0;
        int iK = androidx.core.f.t.k(this);
        int iMin2 = iK >= 0 ? Math.min(iK, i4 - i2) : 0;
        if (d(this.f680d)) {
            if (z3) {
                iB = b(this.f680d, i24, iArr, iMin2);
                iA = paddingLeft;
            } else {
                iA = a(this.f680d, paddingLeft, iArr, iMin2);
            }
            if (d(this.h)) {
                if (z3) {
                    iB = b(this.h, iB, iArr, iMin2);
                } else {
                    iA = a(this.h, iA, iArr, iMin2);
                }
            }
            if (d(this.f677a)) {
                if (z3) {
                    iA = a(this.f677a, iA, iArr, iMin2);
                } else {
                    iB = b(this.f677a, iB, iArr, iMin2);
                }
            }
            int currentContentInsetLeft = getCurrentContentInsetLeft();
            int currentContentInsetRight = getCurrentContentInsetRight();
            iArr[0] = Math.max(0, currentContentInsetLeft - iA);
            iArr[1] = Math.max(0, currentContentInsetRight - (i24 - iB));
            iMax = Math.max(iA, currentContentInsetLeft);
            iMin = Math.min(iB, i24 - currentContentInsetRight);
            if (d(this.i)) {
                if (z3) {
                    iMin = b(this.i, iMin, iArr, iMin2);
                } else {
                    iMax = a(this.i, iMax, iArr, iMin2);
                }
            }
            if (d(this.f681e)) {
                if (z3) {
                    iMin = b(this.f681e, iMin, iArr, iMin2);
                } else {
                    iMax = a(this.f681e, iMax, iArr, iMin2);
                }
            }
            zD = d(this.f678b);
            zD2 = d(this.f679c);
            if (zD) {
                e eVar3 = (e) this.f678b.getLayoutParams();
                measuredHeight = ((ViewGroup.MarginLayoutParams) eVar3).topMargin + this.f678b.getMeasuredHeight() + ((ViewGroup.MarginLayoutParams) eVar3).bottomMargin + 0;
            } else {
                measuredHeight = 0;
            }
            if (zD2) {
                e eVar4 = (e) this.f679c.getLayoutParams();
                measuredHeight += ((ViewGroup.MarginLayoutParams) eVar4).topMargin + this.f679c.getMeasuredHeight() + ((ViewGroup.MarginLayoutParams) eVar4).bottomMargin;
            }
            if (!zD || zD2) {
                if (zD) {
                    textView = this.f678b;
                } else {
                    textView = this.f679c;
                }
                if (zD2) {
                    textView2 = this.f679c;
                } else {
                    textView2 = this.f678b;
                }
                eVar = (e) textView.getLayoutParams();
                eVar2 = (e) textView2.getLayoutParams();
                z2 = (!zD && this.f678b.getMeasuredWidth() > 0) || (zD2 && this.f679c.getMeasuredWidth() > 0);
                i5 = this.w & 112;
                i6 = iMin2;
                if (i5 == 48) {
                    paddingTop = getPaddingTop() + ((ViewGroup.MarginLayoutParams) eVar).topMargin + this.r;
                } else if (i5 != 80) {
                    iMax2 = (((height - paddingTop2) - paddingBottom) - measuredHeight) / 2;
                    i14 = ((ViewGroup.MarginLayoutParams) eVar).topMargin;
                    i15 = this.r;
                    if (iMax2 < i14 + i15) {
                        iMax2 = i14 + i15;
                    } else {
                        i16 = (((height - paddingBottom) - measuredHeight) - iMax2) - paddingTop2;
                        i17 = ((ViewGroup.MarginLayoutParams) eVar).bottomMargin;
                        i18 = this.s;
                        if (i16 < i17 + i18) {
                            iMax2 = Math.max(0, iMax2 - ((((ViewGroup.MarginLayoutParams) eVar2).bottomMargin + i18) - i16));
                        }
                    }
                    paddingTop = paddingTop2 + iMax2;
                } else {
                    paddingTop = (((height - paddingBottom) - ((ViewGroup.MarginLayoutParams) eVar2).bottomMargin) - this.s) - measuredHeight;
                }
                if (z3) {
                    if (z2) {
                        i11 = this.p;
                        c2 = 1;
                    } else {
                        c2 = 1;
                        i11 = 0;
                    }
                    int i25 = i11 - iArr[c2];
                    iMin -= Math.max(0, i25);
                    iArr[c2] = Math.max(0, -i25);
                    if (zD) {
                        e eVar5 = (e) this.f678b.getLayoutParams();
                        int measuredWidth = iMin - this.f678b.getMeasuredWidth();
                        int measuredHeight2 = this.f678b.getMeasuredHeight() + paddingTop;
                        this.f678b.layout(measuredWidth, paddingTop, iMin, measuredHeight2);
                        i12 = measuredWidth - this.q;
                        paddingTop = measuredHeight2 + ((ViewGroup.MarginLayoutParams) eVar5).bottomMargin;
                    } else {
                        i12 = iMin;
                    }
                    if (zD2) {
                        e eVar6 = (e) this.f679c.getLayoutParams();
                        int i26 = paddingTop + ((ViewGroup.MarginLayoutParams) eVar6).topMargin;
                        this.f679c.layout(iMin - this.f679c.getMeasuredWidth(), i26, iMin, this.f679c.getMeasuredHeight() + i26);
                        i13 = iMin - this.q;
                        int i27 = ((ViewGroup.MarginLayoutParams) eVar6).bottomMargin;
                    } else {
                        i13 = iMin;
                    }
                    if (z2) {
                        iMin = Math.min(i12, i13);
                    }
                    iMax = iMax;
                } else {
                    if (z2) {
                        i7 = this.p;
                    } else {
                        i7 = 0;
                    }
                    i8 = 0;
                    int i28 = i7 - iArr[0];
                    iMax += Math.max(0, i28);
                    iArr[0] = Math.max(0, -i28);
                    if (zD) {
                        e eVar7 = (e) this.f678b.getLayoutParams();
                        int measuredWidth2 = this.f678b.getMeasuredWidth() + iMax;
                        int measuredHeight3 = this.f678b.getMeasuredHeight() + paddingTop;
                        this.f678b.layout(iMax, paddingTop, measuredWidth2, measuredHeight3);
                        i9 = measuredWidth2 + this.q;
                        paddingTop = measuredHeight3 + ((ViewGroup.MarginLayoutParams) eVar7).bottomMargin;
                    } else {
                        i9 = iMax;
                    }
                    if (zD2 != 0) {
                        e eVar8 = (e) this.f679c.getLayoutParams();
                        int i29 = paddingTop + ((ViewGroup.MarginLayoutParams) eVar8).topMargin;
                        int measuredWidth3 = this.f679c.getMeasuredWidth() + iMax;
                        this.f679c.layout(iMax, i29, measuredWidth3, this.f679c.getMeasuredHeight() + i29);
                        i10 = measuredWidth3 + this.q;
                        int i30 = ((ViewGroup.MarginLayoutParams) eVar8).bottomMargin;
                    } else {
                        i10 = iMax;
                    }
                    if (z2) {
                        iMax = Math.max(i9, i10);
                    }
                }
                a(this.D, 3);
                size = this.D.size();
                iA2 = iMax;
                for (i19 = 0; i19 < size; i19++) {
                    iA2 = a(this.D.get(i19), iA2, iArr, i6);
                }
                i20 = i6;
                a(this.D, 5);
                size2 = this.D.size();
                for (i21 = 0; i21 < size2; i21++) {
                    iMin = b(this.D.get(i21), iMin, iArr, i20);
                }
                a(this.D, 1);
                int iA3 = a(this.D, iArr);
                i22 = (paddingLeft + (((width - paddingLeft) - paddingRight) / 2)) - (iA3 / 2);
                i23 = iA3 + i22;
                if (i22 >= iA2) {
                    if (i23 > iMin) {
                        iA2 = i22 - (i23 - iMin);
                    } else {
                        iA2 = i22;
                    }
                }
                size3 = this.D.size();
                while (i8 < size3) {
                    iA2 = a(this.D.get(i8), iA2, iArr, i20);
                    i8++;
                }
                this.D.clear();
            }
            paddingLeft = paddingLeft;
            i6 = iMin2;
            i8 = 0;
            a(this.D, 3);
            size = this.D.size();
            iA2 = iMax;
            while (i19 < size) {
                iA2 = a(this.D.get(i19), iA2, iArr, i6);
            }
            i20 = i6;
            a(this.D, 5);
            size2 = this.D.size();
            while (i21 < size2) {
                iMin = b(this.D.get(i21), iMin, iArr, i20);
            }
            a(this.D, 1);
            int iA4 = a(this.D, iArr);
            i22 = (paddingLeft + (((width - paddingLeft) - paddingRight) / 2)) - (iA4 / 2);
            i23 = iA4 + i22;
            if (i22 >= iA2) {
                if (i23 > iMin) {
                    iA2 = i22 - (i23 - iMin);
                } else {
                    iA2 = i22;
                }
            }
            size3 = this.D.size();
            while (i8 < size3) {
                iA2 = a(this.D.get(i8), iA2, iArr, i20);
                i8++;
            }
            this.D.clear();
        }
        iA = paddingLeft;
        iB = i24;
        if (d(this.h)) {
            if (z3) {
                iB = b(this.h, iB, iArr, iMin2);
            } else {
                iA = a(this.h, iA, iArr, iMin2);
            }
        }
        if (d(this.f677a)) {
            if (z3) {
                iA = a(this.f677a, iA, iArr, iMin2);
            } else {
                iB = b(this.f677a, iB, iArr, iMin2);
            }
        }
        int currentContentInsetLeft2 = getCurrentContentInsetLeft();
        int currentContentInsetRight2 = getCurrentContentInsetRight();
        iArr[0] = Math.max(0, currentContentInsetLeft2 - iA);
        iArr[1] = Math.max(0, currentContentInsetRight2 - (i24 - iB));
        iMax = Math.max(iA, currentContentInsetLeft2);
        iMin = Math.min(iB, i24 - currentContentInsetRight2);
        if (d(this.i)) {
            if (z3) {
                iMin = b(this.i, iMin, iArr, iMin2);
            } else {
                iMax = a(this.i, iMax, iArr, iMin2);
            }
        }
        if (d(this.f681e)) {
            if (z3) {
                iMin = b(this.f681e, iMin, iArr, iMin2);
            } else {
                iMax = a(this.f681e, iMax, iArr, iMin2);
            }
        }
        zD = d(this.f678b);
        zD2 = d(this.f679c);
        if (zD) {
            e eVar9 = (e) this.f678b.getLayoutParams();
            measuredHeight = ((ViewGroup.MarginLayoutParams) eVar9).topMargin + this.f678b.getMeasuredHeight() + ((ViewGroup.MarginLayoutParams) eVar9).bottomMargin + 0;
        } else {
            measuredHeight = 0;
        }
        if (zD2) {
            e eVar10 = (e) this.f679c.getLayoutParams();
            measuredHeight += ((ViewGroup.MarginLayoutParams) eVar10).topMargin + this.f679c.getMeasuredHeight() + ((ViewGroup.MarginLayoutParams) eVar10).bottomMargin;
        }
        if (zD) {
            if (zD) {
                textView = this.f678b;
            } else {
                textView = this.f679c;
            }
            if (zD2) {
                textView2 = this.f679c;
            } else {
                textView2 = this.f678b;
            }
            eVar = (e) textView.getLayoutParams();
            eVar2 = (e) textView2.getLayoutParams();
            if (zD) {
            }
            i5 = this.w & 112;
            i6 = iMin2;
            if (i5 == 48) {
                paddingTop = getPaddingTop() + ((ViewGroup.MarginLayoutParams) eVar).topMargin + this.r;
            } else if (i5 != 80) {
                iMax2 = (((height - paddingTop2) - paddingBottom) - measuredHeight) / 2;
                i14 = ((ViewGroup.MarginLayoutParams) eVar).topMargin;
                i15 = this.r;
                if (iMax2 < i14 + i15) {
                    iMax2 = i14 + i15;
                } else {
                    i16 = (((height - paddingBottom) - measuredHeight) - iMax2) - paddingTop2;
                    i17 = ((ViewGroup.MarginLayoutParams) eVar).bottomMargin;
                    i18 = this.s;
                    if (i16 < i17 + i18) {
                        iMax2 = Math.max(0, iMax2 - ((((ViewGroup.MarginLayoutParams) eVar2).bottomMargin + i18) - i16));
                    }
                }
                paddingTop = paddingTop2 + iMax2;
            } else {
                paddingTop = (((height - paddingBottom) - ((ViewGroup.MarginLayoutParams) eVar2).bottomMargin) - this.s) - measuredHeight;
            }
            if (z3) {
                if (z2) {
                    i11 = this.p;
                    c2 = 1;
                } else {
                    c2 = 1;
                    i11 = 0;
                }
                int i210 = i11 - iArr[c2];
                iMin -= Math.max(0, i210);
                iArr[c2] = Math.max(0, -i210);
                if (zD) {
                    e eVar11 = (e) this.f678b.getLayoutParams();
                    int measuredWidth4 = iMin - this.f678b.getMeasuredWidth();
                    int measuredHeight4 = this.f678b.getMeasuredHeight() + paddingTop;
                    this.f678b.layout(measuredWidth4, paddingTop, iMin, measuredHeight4);
                    i12 = measuredWidth4 - this.q;
                    paddingTop = measuredHeight4 + ((ViewGroup.MarginLayoutParams) eVar11).bottomMargin;
                } else {
                    i12 = iMin;
                }
                if (zD2) {
                    e eVar12 = (e) this.f679c.getLayoutParams();
                    int i211 = paddingTop + ((ViewGroup.MarginLayoutParams) eVar12).topMargin;
                    this.f679c.layout(iMin - this.f679c.getMeasuredWidth(), i211, iMin, this.f679c.getMeasuredHeight() + i211);
                    i13 = iMin - this.q;
                    int i212 = ((ViewGroup.MarginLayoutParams) eVar12).bottomMargin;
                } else {
                    i13 = iMin;
                }
                if (z2) {
                    iMin = Math.min(i12, i13);
                }
                iMax = iMax;
                i8 = 0;
            } else {
                if (z2) {
                    i7 = this.p;
                } else {
                    i7 = 0;
                }
                i8 = 0;
                int i213 = i7 - iArr[0];
                iMax += Math.max(0, i213);
                iArr[0] = Math.max(0, -i213);
                if (zD) {
                    e eVar13 = (e) this.f678b.getLayoutParams();
                    int measuredWidth5 = this.f678b.getMeasuredWidth() + iMax;
                    int measuredHeight5 = this.f678b.getMeasuredHeight() + paddingTop;
                    this.f678b.layout(iMax, paddingTop, measuredWidth5, measuredHeight5);
                    i9 = measuredWidth5 + this.q;
                    paddingTop = measuredHeight5 + ((ViewGroup.MarginLayoutParams) eVar13).bottomMargin;
                } else {
                    i9 = iMax;
                }
                if (zD2 != 0) {
                    e eVar14 = (e) this.f679c.getLayoutParams();
                    int i214 = paddingTop + ((ViewGroup.MarginLayoutParams) eVar14).topMargin;
                    int measuredWidth6 = this.f679c.getMeasuredWidth() + iMax;
                    this.f679c.layout(iMax, i214, measuredWidth6, this.f679c.getMeasuredHeight() + i214);
                    i10 = measuredWidth6 + this.q;
                    int i31 = ((ViewGroup.MarginLayoutParams) eVar14).bottomMargin;
                } else {
                    i10 = iMax;
                }
                if (z2) {
                    iMax = Math.max(i9, i10);
                }
            }
        } else {
            if (zD) {
                textView = this.f678b;
            } else {
                textView = this.f679c;
            }
            if (zD2) {
                textView2 = this.f679c;
            } else {
                textView2 = this.f678b;
            }
            eVar = (e) textView.getLayoutParams();
            eVar2 = (e) textView2.getLayoutParams();
            if (zD) {
            }
            i5 = this.w & 112;
            i6 = iMin2;
            if (i5 == 48) {
                paddingTop = getPaddingTop() + ((ViewGroup.MarginLayoutParams) eVar).topMargin + this.r;
            } else if (i5 != 80) {
                iMax2 = (((height - paddingTop2) - paddingBottom) - measuredHeight) / 2;
                i14 = ((ViewGroup.MarginLayoutParams) eVar).topMargin;
                i15 = this.r;
                if (iMax2 < i14 + i15) {
                    iMax2 = i14 + i15;
                } else {
                    i16 = (((height - paddingBottom) - measuredHeight) - iMax2) - paddingTop2;
                    i17 = ((ViewGroup.MarginLayoutParams) eVar).bottomMargin;
                    i18 = this.s;
                    if (i16 < i17 + i18) {
                        iMax2 = Math.max(0, iMax2 - ((((ViewGroup.MarginLayoutParams) eVar2).bottomMargin + i18) - i16));
                    }
                }
                paddingTop = paddingTop2 + iMax2;
            } else {
                paddingTop = (((height - paddingBottom) - ((ViewGroup.MarginLayoutParams) eVar2).bottomMargin) - this.s) - measuredHeight;
            }
            if (z3) {
                if (z2) {
                    i11 = this.p;
                    c2 = 1;
                } else {
                    c2 = 1;
                    i11 = 0;
                }
                int i215 = i11 - iArr[c2];
                iMin -= Math.max(0, i215);
                iArr[c2] = Math.max(0, -i215);
                if (zD) {
                    e eVar15 = (e) this.f678b.getLayoutParams();
                    int measuredWidth7 = iMin - this.f678b.getMeasuredWidth();
                    int measuredHeight6 = this.f678b.getMeasuredHeight() + paddingTop;
                    this.f678b.layout(measuredWidth7, paddingTop, iMin, measuredHeight6);
                    i12 = measuredWidth7 - this.q;
                    paddingTop = measuredHeight6 + ((ViewGroup.MarginLayoutParams) eVar15).bottomMargin;
                } else {
                    i12 = iMin;
                }
                if (zD2) {
                    e eVar16 = (e) this.f679c.getLayoutParams();
                    int i216 = paddingTop + ((ViewGroup.MarginLayoutParams) eVar16).topMargin;
                    this.f679c.layout(iMin - this.f679c.getMeasuredWidth(), i216, iMin, this.f679c.getMeasuredHeight() + i216);
                    i13 = iMin - this.q;
                    int i217 = ((ViewGroup.MarginLayoutParams) eVar16).bottomMargin;
                } else {
                    i13 = iMin;
                }
                if (z2) {
                    iMin = Math.min(i12, i13);
                }
                iMax = iMax;
                i8 = 0;
            } else {
                if (z2) {
                    i7 = this.p;
                } else {
                    i7 = 0;
                }
                i8 = 0;
                int i218 = i7 - iArr[0];
                iMax += Math.max(0, i218);
                iArr[0] = Math.max(0, -i218);
                if (zD) {
                    e eVar17 = (e) this.f678b.getLayoutParams();
                    int measuredWidth8 = this.f678b.getMeasuredWidth() + iMax;
                    int measuredHeight7 = this.f678b.getMeasuredHeight() + paddingTop;
                    this.f678b.layout(iMax, paddingTop, measuredWidth8, measuredHeight7);
                    i9 = measuredWidth8 + this.q;
                    paddingTop = measuredHeight7 + ((ViewGroup.MarginLayoutParams) eVar17).bottomMargin;
                } else {
                    i9 = iMax;
                }
                if (zD2 != 0) {
                    e eVar18 = (e) this.f679c.getLayoutParams();
                    int i219 = paddingTop + ((ViewGroup.MarginLayoutParams) eVar18).topMargin;
                    int measuredWidth9 = this.f679c.getMeasuredWidth() + iMax;
                    this.f679c.layout(iMax, i219, measuredWidth9, this.f679c.getMeasuredHeight() + i219);
                    i10 = measuredWidth9 + this.q;
                    int i32 = ((ViewGroup.MarginLayoutParams) eVar18).bottomMargin;
                } else {
                    i10 = iMax;
                }
                if (z2) {
                    iMax = Math.max(i9, i10);
                }
            }
        }
        a(this.D, 3);
        size = this.D.size();
        iA2 = iMax;
        while (i19 < size) {
            iA2 = a(this.D.get(i19), iA2, iArr, i6);
        }
        i20 = i6;
        a(this.D, 5);
        size2 = this.D.size();
        while (i21 < size2) {
            iMin = b(this.D.get(i21), iMin, iArr, i20);
        }
        a(this.D, 1);
        int iA5 = a(this.D, iArr);
        i22 = (paddingLeft + (((width - paddingLeft) - paddingRight) / 2)) - (iA5 / 2);
        i23 = iA5 + i22;
        if (i22 >= iA2) {
            if (i23 > iMin) {
                iA2 = i22 - (i23 - iMin);
            } else {
                iA2 = i22;
            }
        }
        size3 = this.D.size();
        while (i8 < size3) {
            iA2 = a(this.D.get(i8), iA2, iArr, i20);
            i8++;
        }
        this.D.clear();
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        char c2;
        char c3;
        int measuredWidth;
        int iMax;
        int iCombineMeasuredStates;
        int measuredWidth2;
        int iCombineMeasuredStates2;
        int iMax2;
        int measuredHeight;
        int[] iArr = this.G;
        if (j0.a(this)) {
            c2 = 1;
            c3 = 0;
        } else {
            c2 = 0;
            c3 = 1;
        }
        if (d(this.f680d)) {
            a(this.f680d, i, 0, i2, 0, this.o);
            measuredWidth = this.f680d.getMeasuredWidth() + a(this.f680d);
            iMax = Math.max(0, this.f680d.getMeasuredHeight() + b(this.f680d));
            iCombineMeasuredStates = View.combineMeasuredStates(0, this.f680d.getMeasuredState());
        } else {
            measuredWidth = 0;
            iMax = 0;
            iCombineMeasuredStates = 0;
        }
        if (d(this.h)) {
            a(this.h, i, 0, i2, 0, this.o);
            measuredWidth = this.h.getMeasuredWidth() + a(this.h);
            iMax = Math.max(iMax, this.h.getMeasuredHeight() + b(this.h));
            iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, this.h.getMeasuredState());
        }
        int currentContentInsetStart = getCurrentContentInsetStart();
        int iMax3 = 0 + Math.max(currentContentInsetStart, measuredWidth);
        iArr[c2] = Math.max(0, currentContentInsetStart - measuredWidth);
        if (d(this.f677a)) {
            a(this.f677a, i, iMax3, i2, 0, this.o);
            measuredWidth2 = this.f677a.getMeasuredWidth() + a(this.f677a);
            iMax = Math.max(iMax, this.f677a.getMeasuredHeight() + b(this.f677a));
            iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, this.f677a.getMeasuredState());
        } else {
            measuredWidth2 = 0;
        }
        int currentContentInsetEnd = getCurrentContentInsetEnd();
        int iMax4 = iMax3 + Math.max(currentContentInsetEnd, measuredWidth2);
        iArr[c3] = Math.max(0, currentContentInsetEnd - measuredWidth2);
        if (d(this.i)) {
            iMax4 += a(this.i, i, iMax4, i2, 0, iArr);
            iMax = Math.max(iMax, this.i.getMeasuredHeight() + b(this.i));
            iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, this.i.getMeasuredState());
        }
        if (d(this.f681e)) {
            iMax4 += a(this.f681e, i, iMax4, i2, 0, iArr);
            iMax = Math.max(iMax, this.f681e.getMeasuredHeight() + b(this.f681e));
            iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, this.f681e.getMeasuredState());
        }
        int childCount = getChildCount();
        int iMax5 = iMax;
        int iA = iMax4;
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = getChildAt(i3);
            if (((e) childAt.getLayoutParams()).f689b == 0 && d(childAt)) {
                iA += a(childAt, i, iA, i2, 0, iArr);
                iMax5 = Math.max(iMax5, childAt.getMeasuredHeight() + b(childAt));
                iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, childAt.getMeasuredState());
            }
        }
        int i4 = this.r + this.s;
        int i5 = this.p + this.q;
        if (d(this.f678b)) {
            a(this.f678b, i, iA + i5, i2, i4, iArr);
            int measuredWidth3 = this.f678b.getMeasuredWidth() + a(this.f678b);
            measuredHeight = this.f678b.getMeasuredHeight() + b(this.f678b);
            iCombineMeasuredStates2 = View.combineMeasuredStates(iCombineMeasuredStates, this.f678b.getMeasuredState());
            iMax2 = measuredWidth3;
        } else {
            iCombineMeasuredStates2 = iCombineMeasuredStates;
            iMax2 = 0;
            measuredHeight = 0;
        }
        if (d(this.f679c)) {
            iMax2 = Math.max(iMax2, a(this.f679c, i, iA + i5, i2, measuredHeight + i4, iArr));
            measuredHeight += this.f679c.getMeasuredHeight() + b(this.f679c);
            iCombineMeasuredStates2 = View.combineMeasuredStates(iCombineMeasuredStates2, this.f679c.getMeasuredState());
        }
        int iMax6 = Math.max(iMax5, measuredHeight);
        int paddingLeft = iA + iMax2 + getPaddingLeft() + getPaddingRight();
        int paddingTop = iMax6 + getPaddingTop() + getPaddingBottom();
        int iResolveSizeAndState = View.resolveSizeAndState(Math.max(paddingLeft, getSuggestedMinimumWidth()), i, (-16777216) & iCombineMeasuredStates2);
        int iResolveSizeAndState2 = View.resolveSizeAndState(Math.max(paddingTop, getSuggestedMinimumHeight()), i2, iCombineMeasuredStates2 << 16);
        if (r()) {
            iResolveSizeAndState2 = 0;
        }
        setMeasuredDimension(iResolveSizeAndState, iResolveSizeAndState2);
    }

    @Override // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable) {
        MenuItem menuItemFindItem;
        if (!(parcelable instanceof g)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        g gVar = (g) parcelable;
        super.onRestoreInstanceState(gVar.a());
        ActionMenuView actionMenuView = this.f677a;
        androidx.appcompat.view.menu.g gVarI = actionMenuView != null ? actionMenuView.i() : null;
        int i = gVar.f690c;
        if (i != 0 && this.L != null && gVarI != null && (menuItemFindItem = gVarI.findItem(i)) != null) {
            menuItemFindItem.expandActionView();
        }
        if (gVar.f691d) {
            q();
        }
    }

    @Override // android.view.View
    public void onRtlPropertiesChanged(int i) {
        if (Build.VERSION.SDK_INT >= 17) {
            super.onRtlPropertiesChanged(i);
        }
        l();
        this.t.a(i == 1);
    }

    @Override // android.view.View
    protected Parcelable onSaveInstanceState() {
        androidx.appcompat.view.menu.j jVar;
        g gVar = new g(super.onSaveInstanceState());
        d dVar = this.L;
        if (dVar != null && (jVar = dVar.f687b) != null) {
            gVar.f690c = jVar.getItemId();
        }
        gVar.f691d = i();
        return gVar;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.B = false;
        }
        if (!this.B) {
            boolean zOnTouchEvent = super.onTouchEvent(motionEvent);
            if (actionMasked == 0 && !zOnTouchEvent) {
                this.B = true;
            }
        }
        if (actionMasked == 1 || actionMasked == 3) {
            this.B = false;
        }
        return true;
    }

    public void setCollapseContentDescription(int i) {
        setCollapseContentDescription(i != 0 ? getContext().getText(i) : null);
    }

    public void setCollapseIcon(int i) {
        setCollapseIcon(androidx.appcompat.a.a.a.c(getContext(), i));
    }

    public void setCollapsible(boolean z) {
        this.O = z;
        requestLayout();
    }

    public void setContentInsetEndWithActions(int i) {
        if (i < 0) {
            i = Integer.MIN_VALUE;
        }
        if (i != this.v) {
            this.v = i;
            if (getNavigationIcon() != null) {
                requestLayout();
            }
        }
    }

    public void setContentInsetStartWithNavigation(int i) {
        if (i < 0) {
            i = Integer.MIN_VALUE;
        }
        if (i != this.u) {
            this.u = i;
            if (getNavigationIcon() != null) {
                requestLayout();
            }
        }
    }

    public void setLogo(int i) {
        setLogo(androidx.appcompat.a.a.a.c(getContext(), i));
    }

    public void setLogoDescription(int i) {
        setLogoDescription(getContext().getText(i));
    }

    public void setNavigationContentDescription(int i) {
        setNavigationContentDescription(i != 0 ? getContext().getText(i) : null);
    }

    public void setNavigationIcon(int i) {
        setNavigationIcon(androidx.appcompat.a.a.a.c(getContext(), i));
    }

    public void setNavigationOnClickListener(View.OnClickListener onClickListener) {
        p();
        this.f680d.setOnClickListener(onClickListener);
    }

    public void setOnMenuItemClickListener(f fVar) {
        this.H = fVar;
    }

    public void setOverflowIcon(Drawable drawable) {
        n();
        this.f677a.setOverflowIcon(drawable);
    }

    public void setPopupTheme(int i) {
        if (this.k != i) {
            this.k = i;
            if (i == 0) {
                this.j = getContext();
            } else {
                this.j = new ContextThemeWrapper(getContext(), i);
            }
        }
    }

    public void setSubtitle(int i) {
        setSubtitle(getContext().getText(i));
    }

    public void setSubtitleTextColor(int i) {
        setSubtitleTextColor(ColorStateList.valueOf(i));
    }

    public void setTitle(int i) {
        setTitle(getContext().getText(i));
    }

    public void setTitleMarginBottom(int i) {
        this.s = i;
        requestLayout();
    }

    public void setTitleMarginEnd(int i) {
        this.q = i;
        requestLayout();
    }

    public void setTitleMarginStart(int i) {
        this.p = i;
        requestLayout();
    }

    public void setTitleMarginTop(int i) {
        this.r = i;
        requestLayout();
    }

    public void setTitleTextColor(int i) {
        setTitleTextColor(ColorStateList.valueOf(i));
    }

    public static class e extends androidx.appcompat.app.a.C0005a {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int f689b;

        public e(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f689b = 0;
        }

        void a(ViewGroup.MarginLayoutParams marginLayoutParams) {
            ((ViewGroup.MarginLayoutParams) this).leftMargin = marginLayoutParams.leftMargin;
            ((ViewGroup.MarginLayoutParams) this).topMargin = marginLayoutParams.topMargin;
            ((ViewGroup.MarginLayoutParams) this).rightMargin = marginLayoutParams.rightMargin;
            ((ViewGroup.MarginLayoutParams) this).bottomMargin = marginLayoutParams.bottomMargin;
        }

        public e(int i, int i2) {
            super(i, i2);
            this.f689b = 0;
            this.f293a = 8388627;
        }

        public e(e eVar) {
            super((androidx.appcompat.app.a.C0005a) eVar);
            this.f689b = 0;
            this.f689b = eVar.f689b;
        }

        public e(androidx.appcompat.app.a.C0005a c0005a) {
            super(c0005a);
            this.f689b = 0;
        }

        public e(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.f689b = 0;
            a(marginLayoutParams);
        }

        public e(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.f689b = 0;
        }
    }

    public Toolbar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.toolbarStyle);
    }

    public void b(Context context, int i) {
        this.l = i;
        TextView textView = this.f678b;
        if (textView != null) {
            textView.setTextAppearance(context, i);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.ViewGroup
    public e generateDefaultLayoutParams() {
        return new e(-2, -2);
    }

    public void setCollapseContentDescription(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            e();
        }
        ImageButton imageButton = this.h;
        if (imageButton != null) {
            imageButton.setContentDescription(charSequence);
        }
    }

    public void setCollapseIcon(Drawable drawable) {
        if (drawable != null) {
            e();
            this.h.setImageDrawable(drawable);
        } else {
            ImageButton imageButton = this.h;
            if (imageButton != null) {
                imageButton.setImageDrawable(this.f682f);
            }
        }
    }

    public void setLogo(Drawable drawable) {
        if (drawable != null) {
            m();
            if (!c(this.f681e)) {
                a((View) this.f681e, true);
            }
        } else {
            ImageView imageView = this.f681e;
            if (imageView != null && c(imageView)) {
                removeView(this.f681e);
                this.F.remove(this.f681e);
            }
        }
        ImageView imageView2 = this.f681e;
        if (imageView2 != null) {
            imageView2.setImageDrawable(drawable);
        }
    }

    public void setLogoDescription(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            m();
        }
        ImageView imageView = this.f681e;
        if (imageView != null) {
            imageView.setContentDescription(charSequence);
        }
    }

    public void setNavigationContentDescription(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            p();
        }
        ImageButton imageButton = this.f680d;
        if (imageButton != null) {
            imageButton.setContentDescription(charSequence);
        }
    }

    public void setNavigationIcon(Drawable drawable) {
        if (drawable != null) {
            p();
            if (!c(this.f680d)) {
                a((View) this.f680d, true);
            }
        } else {
            ImageButton imageButton = this.f680d;
            if (imageButton != null && c(imageButton)) {
                removeView(this.f680d);
                this.F.remove(this.f680d);
            }
        }
        ImageButton imageButton2 = this.f680d;
        if (imageButton2 != null) {
            imageButton2.setImageDrawable(drawable);
        }
    }

    public void setSubtitle(CharSequence charSequence) {
        if (TextUtils.isEmpty(charSequence)) {
            TextView textView = this.f679c;
            if (textView != null && c(textView)) {
                removeView(this.f679c);
                this.F.remove(this.f679c);
            }
        } else {
            if (this.f679c == null) {
                Context context = getContext();
                this.f679c = new AppCompatTextView(context);
                this.f679c.setSingleLine();
                this.f679c.setEllipsize(TextUtils.TruncateAt.END);
                int i = this.m;
                if (i != 0) {
                    this.f679c.setTextAppearance(context, i);
                }
                ColorStateList colorStateList = this.A;
                if (colorStateList != null) {
                    this.f679c.setTextColor(colorStateList);
                }
            }
            if (!c(this.f679c)) {
                a((View) this.f679c, true);
            }
        }
        TextView textView2 = this.f679c;
        if (textView2 != null) {
            textView2.setText(charSequence);
        }
        this.y = charSequence;
    }

    public void setSubtitleTextColor(ColorStateList colorStateList) {
        this.A = colorStateList;
        TextView textView = this.f679c;
        if (textView != null) {
            textView.setTextColor(colorStateList);
        }
    }

    public void setTitle(CharSequence charSequence) {
        if (TextUtils.isEmpty(charSequence)) {
            TextView textView = this.f678b;
            if (textView != null && c(textView)) {
                removeView(this.f678b);
                this.F.remove(this.f678b);
            }
        } else {
            if (this.f678b == null) {
                Context context = getContext();
                this.f678b = new AppCompatTextView(context);
                this.f678b.setSingleLine();
                this.f678b.setEllipsize(TextUtils.TruncateAt.END);
                int i = this.l;
                if (i != 0) {
                    this.f678b.setTextAppearance(context, i);
                }
                ColorStateList colorStateList = this.z;
                if (colorStateList != null) {
                    this.f678b.setTextColor(colorStateList);
                }
            }
            if (!c(this.f678b)) {
                a((View) this.f678b, true);
            }
        }
        TextView textView2 = this.f678b;
        if (textView2 != null) {
            textView2.setText(charSequence);
        }
        this.x = charSequence;
    }

    public void setTitleTextColor(ColorStateList colorStateList) {
        this.z = colorStateList;
        TextView textView = this.f678b;
        if (textView != null) {
            textView.setTextColor(colorStateList);
        }
    }

    private class d implements androidx.appcompat.view.menu.m {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        androidx.appcompat.view.menu.g f686a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        androidx.appcompat.view.menu.j f687b;

        d() {
        }

        @Override // androidx.appcompat.view.menu.m
        public void a(Context context, androidx.appcompat.view.menu.g gVar) {
            androidx.appcompat.view.menu.j jVar;
            androidx.appcompat.view.menu.g gVar2 = this.f686a;
            if (gVar2 != null && (jVar = this.f687b) != null) {
                gVar2.a(jVar);
            }
            this.f686a = gVar;
        }

        @Override // androidx.appcompat.view.menu.m
        public void a(androidx.appcompat.view.menu.g gVar, boolean z) {
        }

        @Override // androidx.appcompat.view.menu.m
        public boolean a() {
            return false;
        }

        @Override // androidx.appcompat.view.menu.m
        public boolean a(androidx.appcompat.view.menu.r rVar) {
            return false;
        }

        @Override // androidx.appcompat.view.menu.m
        public boolean b(androidx.appcompat.view.menu.g gVar, androidx.appcompat.view.menu.j jVar) {
            Toolbar.this.e();
            ViewParent parent = Toolbar.this.h.getParent();
            Toolbar toolbar = Toolbar.this;
            if (parent != toolbar) {
                if (parent instanceof ViewGroup) {
                    ((ViewGroup) parent).removeView(toolbar.h);
                }
                Toolbar toolbar2 = Toolbar.this;
                toolbar2.addView(toolbar2.h);
            }
            Toolbar.this.i = jVar.getActionView();
            this.f687b = jVar;
            ViewParent parent2 = Toolbar.this.i.getParent();
            Toolbar toolbar3 = Toolbar.this;
            if (parent2 != toolbar3) {
                if (parent2 instanceof ViewGroup) {
                    ((ViewGroup) parent2).removeView(toolbar3.i);
                }
                e eVarGenerateDefaultLayoutParams = Toolbar.this.generateDefaultLayoutParams();
                Toolbar toolbar4 = Toolbar.this;
                eVarGenerateDefaultLayoutParams.f293a = 8388611 | (toolbar4.n & 112);
                eVarGenerateDefaultLayoutParams.f689b = 2;
                toolbar4.i.setLayoutParams(eVarGenerateDefaultLayoutParams);
                Toolbar toolbar5 = Toolbar.this;
                toolbar5.addView(toolbar5.i);
            }
            Toolbar.this.j();
            Toolbar.this.requestLayout();
            jVar.a(true);
            KeyEvent.Callback callback = Toolbar.this.i;
            if (callback instanceof androidx.appcompat.d.c) {
                ((androidx.appcompat.d.c) callback).a();
            }
            return true;
        }

        @Override // androidx.appcompat.view.menu.m
        public void a(boolean z) {
            if (this.f687b != null) {
                androidx.appcompat.view.menu.g gVar = this.f686a;
                boolean z2 = false;
                if (gVar != null) {
                    int size = gVar.size();
                    for (int i = 0; i < size; i++) {
                        if (this.f686a.getItem(i) == this.f687b) {
                            z2 = true;
                            break;
                        }
                    }
                }
                if (z2) {
                    return;
                }
                a(this.f686a, this.f687b);
            }
        }

        @Override // androidx.appcompat.view.menu.m
        public boolean a(androidx.appcompat.view.menu.g gVar, androidx.appcompat.view.menu.j jVar) {
            KeyEvent.Callback callback = Toolbar.this.i;
            if (callback instanceof androidx.appcompat.d.c) {
                ((androidx.appcompat.d.c) callback).b();
            }
            Toolbar toolbar = Toolbar.this;
            toolbar.removeView(toolbar.i);
            Toolbar toolbar2 = Toolbar.this;
            toolbar2.removeView(toolbar2.h);
            Toolbar toolbar3 = Toolbar.this;
            toolbar3.i = null;
            toolbar3.a();
            this.f687b = null;
            Toolbar.this.requestLayout();
            jVar.a(false);
            return true;
        }
    }

    public static class g extends androidx.customview.a.a {
        public static final Parcelable.Creator<g> CREATOR = new a();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f690c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        boolean f691d;

        static class a implements Parcelable.ClassLoaderCreator<g> {
            a() {
            }

            @Override // android.os.Parcelable.Creator
            public g[] newArray(int i) {
                return new g[i];
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.ClassLoaderCreator
            public g createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new g(parcel, classLoader);
            }

            @Override // android.os.Parcelable.Creator
            public g createFromParcel(Parcel parcel) {
                return new g(parcel, null);
            }
        }

        public g(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.f690c = parcel.readInt();
            this.f691d = parcel.readInt() != 0;
        }

        @Override // androidx.customview.a.a, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.f690c);
            parcel.writeInt(this.f691d ? 1 : 0);
        }

        public g(Parcelable parcelable) {
            super(parcelable);
        }
    }

    public Toolbar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.w = 8388627;
        this.D = new ArrayList<>();
        this.F = new ArrayList<>();
        this.G = new int[2];
        this.I = new a();
        this.P = new b();
        d0 d0VarA = d0.a(getContext(), attributeSet, R$styleable.Toolbar, i, 0);
        this.l = d0VarA.g(R$styleable.Toolbar_titleTextAppearance, 0);
        this.m = d0VarA.g(R$styleable.Toolbar_subtitleTextAppearance, 0);
        this.w = d0VarA.e(R$styleable.Toolbar_android_gravity, this.w);
        this.n = d0VarA.e(R$styleable.Toolbar_buttonGravity, 48);
        int iB = d0VarA.b(R$styleable.Toolbar_titleMargin, 0);
        iB = d0VarA.g(R$styleable.Toolbar_titleMargins) ? d0VarA.b(R$styleable.Toolbar_titleMargins, iB) : iB;
        this.s = iB;
        this.r = iB;
        this.q = iB;
        this.p = iB;
        int iB2 = d0VarA.b(R$styleable.Toolbar_titleMarginStart, -1);
        if (iB2 >= 0) {
            this.p = iB2;
        }
        int iB3 = d0VarA.b(R$styleable.Toolbar_titleMarginEnd, -1);
        if (iB3 >= 0) {
            this.q = iB3;
        }
        int iB4 = d0VarA.b(R$styleable.Toolbar_titleMarginTop, -1);
        if (iB4 >= 0) {
            this.r = iB4;
        }
        int iB5 = d0VarA.b(R$styleable.Toolbar_titleMarginBottom, -1);
        if (iB5 >= 0) {
            this.s = iB5;
        }
        this.o = d0VarA.c(R$styleable.Toolbar_maxButtonHeight, -1);
        int iB6 = d0VarA.b(R$styleable.Toolbar_contentInsetStart, Integer.MIN_VALUE);
        int iB7 = d0VarA.b(R$styleable.Toolbar_contentInsetEnd, Integer.MIN_VALUE);
        int iC = d0VarA.c(R$styleable.Toolbar_contentInsetLeft, 0);
        int iC2 = d0VarA.c(R$styleable.Toolbar_contentInsetRight, 0);
        l();
        this.t.a(iC, iC2);
        if (iB6 != Integer.MIN_VALUE || iB7 != Integer.MIN_VALUE) {
            this.t.b(iB6, iB7);
        }
        this.u = d0VarA.b(R$styleable.Toolbar_contentInsetStartWithNavigation, Integer.MIN_VALUE);
        this.v = d0VarA.b(R$styleable.Toolbar_contentInsetEndWithActions, Integer.MIN_VALUE);
        this.f682f = d0VarA.b(R$styleable.Toolbar_collapseIcon);
        this.g = d0VarA.e(R$styleable.Toolbar_collapseContentDescription);
        CharSequence charSequenceE = d0VarA.e(R$styleable.Toolbar_title);
        if (!TextUtils.isEmpty(charSequenceE)) {
            setTitle(charSequenceE);
        }
        CharSequence charSequenceE2 = d0VarA.e(R$styleable.Toolbar_subtitle);
        if (!TextUtils.isEmpty(charSequenceE2)) {
            setSubtitle(charSequenceE2);
        }
        this.j = getContext();
        setPopupTheme(d0VarA.g(R$styleable.Toolbar_popupTheme, 0));
        Drawable drawableB = d0VarA.b(R$styleable.Toolbar_navigationIcon);
        if (drawableB != null) {
            setNavigationIcon(drawableB);
        }
        CharSequence charSequenceE3 = d0VarA.e(R$styleable.Toolbar_navigationContentDescription);
        if (!TextUtils.isEmpty(charSequenceE3)) {
            setNavigationContentDescription(charSequenceE3);
        }
        Drawable drawableB2 = d0VarA.b(R$styleable.Toolbar_logo);
        if (drawableB2 != null) {
            setLogo(drawableB2);
        }
        CharSequence charSequenceE4 = d0VarA.e(R$styleable.Toolbar_logoDescription);
        if (!TextUtils.isEmpty(charSequenceE4)) {
            setLogoDescription(charSequenceE4);
        }
        if (d0VarA.g(R$styleable.Toolbar_titleTextColor)) {
            setTitleTextColor(d0VarA.a(R$styleable.Toolbar_titleTextColor));
        }
        if (d0VarA.g(R$styleable.Toolbar_subtitleTextColor)) {
            setSubtitleTextColor(d0VarA.a(R$styleable.Toolbar_subtitleTextColor));
        }
        if (d0VarA.g(R$styleable.Toolbar_menu)) {
            a(d0VarA.g(R$styleable.Toolbar_menu, 0));
        }
        d0VarA.a();
    }

    private int c(int i) {
        int i2 = i & 112;
        return (i2 == 16 || i2 == 48 || i2 == 80) ? i2 : this.w & 112;
    }

    private boolean d(View view) {
        return (view == null || view.getParent() != this || view.getVisibility() == 8) ? false : true;
    }

    @Override // android.view.ViewGroup
    public e generateLayoutParams(AttributeSet attributeSet) {
        return new e(getContext(), attributeSet);
    }

    private boolean c(View view) {
        return view.getParent() == this || this.F.contains(view);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.ViewGroup
    public e generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams instanceof e) {
            return new e((e) layoutParams);
        }
        if (layoutParams instanceof androidx.appcompat.app.a.C0005a) {
            return new e((androidx.appcompat.app.a.C0005a) layoutParams);
        }
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            return new e((ViewGroup.MarginLayoutParams) layoutParams);
        }
        return new e(layoutParams);
    }

    private int b(View view, int i, int[] iArr, int i2) {
        e eVar = (e) view.getLayoutParams();
        int i3 = ((ViewGroup.MarginLayoutParams) eVar).rightMargin - iArr[1];
        int iMax = i - Math.max(0, i3);
        iArr[1] = Math.max(0, -i3);
        int iA = a(view, i2);
        int measuredWidth = view.getMeasuredWidth();
        view.layout(iMax - measuredWidth, iA, iMax, view.getMeasuredHeight() + iA);
        return iMax - (measuredWidth + ((ViewGroup.MarginLayoutParams) eVar).leftMargin);
    }

    private int b(int i) {
        int iJ = androidx.core.f.t.j(this);
        int iA = androidx.core.f.c.a(i, iJ) & 7;
        if (iA == 1 || iA == 3 || iA == 5) {
            return iA;
        }
        return iJ == 1 ? 5 : 3;
    }

    private int b(View view) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        return marginLayoutParams.topMargin + marginLayoutParams.bottomMargin;
    }

    public void a(Context context, int i) {
        this.m = i;
        TextView textView = this.f679c;
        if (textView != null) {
            textView.setTextAppearance(context, i);
        }
    }

    public void a(int i) {
        getMenuInflater().inflate(i, getMenu());
    }

    public void a(int i, int i2) {
        l();
        this.t.b(i, i2);
    }

    private void a(View view, boolean z) {
        e eVarGenerateLayoutParams;
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams == null) {
            eVarGenerateLayoutParams = generateDefaultLayoutParams();
        } else if (!checkLayoutParams(layoutParams)) {
            eVarGenerateLayoutParams = generateLayoutParams(layoutParams);
        } else {
            eVarGenerateLayoutParams = (e) layoutParams;
        }
        eVarGenerateLayoutParams.f689b = 1;
        if (z && this.i != null) {
            view.setLayoutParams(eVarGenerateLayoutParams);
            this.F.add(view);
        } else {
            addView(view, eVarGenerateLayoutParams);
        }
    }

    private void a(View view, int i, int i2, int i3, int i4, int i5) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        int childMeasureSpec = ViewGroup.getChildMeasureSpec(i, getPaddingLeft() + getPaddingRight() + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin + i2, marginLayoutParams.width);
        int childMeasureSpec2 = ViewGroup.getChildMeasureSpec(i3, getPaddingTop() + getPaddingBottom() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin + i4, marginLayoutParams.height);
        int mode = View.MeasureSpec.getMode(childMeasureSpec2);
        if (mode != 1073741824 && i5 >= 0) {
            if (mode != 0) {
                i5 = Math.min(View.MeasureSpec.getSize(childMeasureSpec2), i5);
            }
            childMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(i5, MemoryConstants.GB);
        }
        view.measure(childMeasureSpec, childMeasureSpec2);
    }

    private int a(View view, int i, int i2, int i3, int i4, int[] iArr) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        int i5 = marginLayoutParams.leftMargin - iArr[0];
        int i6 = marginLayoutParams.rightMargin - iArr[1];
        int iMax = Math.max(0, i5) + Math.max(0, i6);
        iArr[0] = Math.max(0, -i5);
        iArr[1] = Math.max(0, -i6);
        view.measure(ViewGroup.getChildMeasureSpec(i, getPaddingLeft() + getPaddingRight() + iMax + i2, marginLayoutParams.width), ViewGroup.getChildMeasureSpec(i3, getPaddingTop() + getPaddingBottom() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin + i4, marginLayoutParams.height));
        return view.getMeasuredWidth() + iMax;
    }

    private int a(List<View> list, int[] iArr) {
        int i = iArr[0];
        int i2 = iArr[1];
        int size = list.size();
        int i3 = i2;
        int i4 = i;
        int i5 = 0;
        int measuredWidth = 0;
        while (i5 < size) {
            View view = list.get(i5);
            e eVar = (e) view.getLayoutParams();
            int i6 = ((ViewGroup.MarginLayoutParams) eVar).leftMargin - i4;
            int i7 = ((ViewGroup.MarginLayoutParams) eVar).rightMargin - i3;
            int iMax = Math.max(0, i6);
            int iMax2 = Math.max(0, i7);
            int iMax3 = Math.max(0, -i6);
            int iMax4 = Math.max(0, -i7);
            measuredWidth += iMax + view.getMeasuredWidth() + iMax2;
            i5++;
            i3 = iMax4;
            i4 = iMax3;
        }
        return measuredWidth;
    }

    private int a(View view, int i, int[] iArr, int i2) {
        e eVar = (e) view.getLayoutParams();
        int i3 = ((ViewGroup.MarginLayoutParams) eVar).leftMargin - iArr[0];
        int iMax = i + Math.max(0, i3);
        iArr[0] = Math.max(0, -i3);
        int iA = a(view, i2);
        int measuredWidth = view.getMeasuredWidth();
        view.layout(iMax, iA, iMax + measuredWidth, view.getMeasuredHeight() + iA);
        return iMax + measuredWidth + ((ViewGroup.MarginLayoutParams) eVar).rightMargin;
    }

    private int a(View view, int i) {
        e eVar = (e) view.getLayoutParams();
        int measuredHeight = view.getMeasuredHeight();
        int i2 = i > 0 ? (measuredHeight - i) / 2 : 0;
        int iC = c(eVar.f293a);
        if (iC == 48) {
            return getPaddingTop() - i2;
        }
        if (iC != 80) {
            int paddingTop = getPaddingTop();
            int paddingBottom = getPaddingBottom();
            int height = getHeight();
            int iMax = (((height - paddingTop) - paddingBottom) - measuredHeight) / 2;
            int i3 = ((ViewGroup.MarginLayoutParams) eVar).topMargin;
            if (iMax < i3) {
                iMax = i3;
            } else {
                int i4 = (((height - paddingBottom) - measuredHeight) - iMax) - paddingTop;
                int i5 = ((ViewGroup.MarginLayoutParams) eVar).bottomMargin;
                if (i4 < i5) {
                    iMax = Math.max(0, iMax - (i5 - i4));
                }
            }
            return paddingTop + iMax;
        }
        return (((getHeight() - getPaddingBottom()) - measuredHeight) - ((ViewGroup.MarginLayoutParams) eVar).bottomMargin) - i2;
    }

    private void a(List<View> list, int i) {
        boolean z = androidx.core.f.t.j(this) == 1;
        int childCount = getChildCount();
        int iA = androidx.core.f.c.a(i, androidx.core.f.t.j(this));
        list.clear();
        if (!z) {
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt = getChildAt(i2);
                e eVar = (e) childAt.getLayoutParams();
                if (eVar.f689b == 0 && d(childAt) && b(eVar.f293a) == iA) {
                    list.add(childAt);
                }
            }
            return;
        }
        for (int i3 = childCount - 1; i3 >= 0; i3--) {
            View childAt2 = getChildAt(i3);
            e eVar2 = (e) childAt2.getLayoutParams();
            if (eVar2.f689b == 0 && d(childAt2) && b(eVar2.f293a) == iA) {
                list.add(childAt2);
            }
        }
    }

    private int a(View view) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        return androidx.core.f.f.b(marginLayoutParams) + androidx.core.f.f.a(marginLayoutParams);
    }

    void a() {
        for (int size = this.F.size() - 1; size >= 0; size--) {
            addView(this.F.get(size));
        }
        this.F.clear();
    }
}
