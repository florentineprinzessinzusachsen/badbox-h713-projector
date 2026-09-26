package androidx.appcompat.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListAdapter;
import android.widget.ListView;
import androidx.appcompat.R$attr;
import com.blankj.utilcode.constant.MemoryConstants;
import java.lang.reflect.Field;

/* JADX INFO: loaded from: classes.dex */
class DropDownListView extends ListView {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Rect f610a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f611b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f612c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f613d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f614e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private int f615f;
    private Field g;
    private a h;
    private boolean i;
    private boolean j;
    private boolean k;
    private androidx.core.f.x l;
    private androidx.core.widget.f m;
    b n;

    private static class a extends androidx.appcompat.b.a.c {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private boolean f616b;

        a(Drawable drawable) {
            super(drawable);
            this.f616b = true;
        }

        void a(boolean z) {
            this.f616b = z;
        }

        @Override // androidx.appcompat.b.a.c, android.graphics.drawable.Drawable
        public void draw(Canvas canvas) {
            if (this.f616b) {
                super.draw(canvas);
            }
        }

        @Override // androidx.appcompat.b.a.c, android.graphics.drawable.Drawable
        public void setHotspot(float f2, float f3) {
            if (this.f616b) {
                super.setHotspot(f2, f3);
            }
        }

        @Override // androidx.appcompat.b.a.c, android.graphics.drawable.Drawable
        public void setHotspotBounds(int i, int i2, int i3, int i4) {
            if (this.f616b) {
                super.setHotspotBounds(i, i2, i3, i4);
            }
        }

        @Override // androidx.appcompat.b.a.c, android.graphics.drawable.Drawable
        public boolean setState(int[] iArr) {
            if (this.f616b) {
                return super.setState(iArr);
            }
            return false;
        }

        @Override // androidx.appcompat.b.a.c, android.graphics.drawable.Drawable
        public boolean setVisible(boolean z, boolean z2) {
            if (this.f616b) {
                return super.setVisible(z, z2);
            }
            return false;
        }
    }

    private class b implements Runnable {
        b() {
        }

        public void a() {
            DropDownListView dropDownListView = DropDownListView.this;
            dropDownListView.n = null;
            dropDownListView.removeCallbacks(this);
        }

        public void b() {
            DropDownListView.this.post(this);
        }

        @Override // java.lang.Runnable
        public void run() {
            DropDownListView dropDownListView = DropDownListView.this;
            dropDownListView.n = null;
            dropDownListView.drawableStateChanged();
        }
    }

    DropDownListView(Context context, boolean z) {
        super(context, null, R$attr.dropDownListViewStyle);
        this.f610a = new Rect();
        this.f611b = 0;
        this.f612c = 0;
        this.f613d = 0;
        this.f614e = 0;
        this.j = z;
        setCacheColorHint(0);
        try {
            this.g = AbsListView.class.getDeclaredField("mIsChildViewEnabled");
            this.g.setAccessible(true);
        } catch (NoSuchFieldException e2) {
            e2.printStackTrace();
        }
    }

    private void b(int i, View view) {
        Drawable selector = getSelector();
        boolean z = (selector == null || i == -1) ? false : true;
        if (z) {
            selector.setVisible(false, false);
        }
        a(i, view);
        if (z) {
            Rect rect = this.f610a;
            float fExactCenterX = rect.exactCenterX();
            float fExactCenterY = rect.exactCenterY();
            selector.setVisible(getVisibility() == 0, false);
            androidx.core.graphics.drawable.a.a(selector, fExactCenterX, fExactCenterY);
        }
    }

    private void c() {
        Drawable selector = getSelector();
        if (selector != null && b() && isPressed()) {
            selector.setState(getDrawableState());
        }
    }

    private void setSelectorEnabled(boolean z) {
        a aVar = this.h;
        if (aVar != null) {
            aVar.a(z);
        }
    }

    public int a(int i, int i2, int i3, int i4, int i5) {
        int listPaddingTop = getListPaddingTop();
        int listPaddingBottom = getListPaddingBottom();
        getListPaddingLeft();
        getListPaddingRight();
        int dividerHeight = getDividerHeight();
        Drawable divider = getDivider();
        ListAdapter adapter = getAdapter();
        if (adapter == null) {
            return listPaddingTop + listPaddingBottom;
        }
        int i6 = listPaddingTop + listPaddingBottom;
        if (dividerHeight <= 0 || divider == null) {
            dividerHeight = 0;
        }
        int count = adapter.getCount();
        int measuredHeight = i6;
        View view = null;
        int i7 = 0;
        int i8 = 0;
        int i9 = 0;
        while (i7 < count) {
            int itemViewType = adapter.getItemViewType(i7);
            if (itemViewType != i8) {
                view = null;
                i8 = itemViewType;
            }
            view = adapter.getView(i7, view, this);
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            if (layoutParams == null) {
                layoutParams = generateDefaultLayoutParams();
                view.setLayoutParams(layoutParams);
            }
            int i10 = layoutParams.height;
            view.measure(i, i10 > 0 ? View.MeasureSpec.makeMeasureSpec(i10, MemoryConstants.GB) : View.MeasureSpec.makeMeasureSpec(0, 0));
            view.forceLayout();
            if (i7 > 0) {
                measuredHeight += dividerHeight;
            }
            measuredHeight += view.getMeasuredHeight();
            if (measuredHeight >= i4) {
                return (i5 < 0 || i7 <= i5 || i9 <= 0 || measuredHeight == i4) ? i4 : i9;
            }
            if (i5 >= 0 && i7 >= i5) {
                i9 = measuredHeight;
            }
            i7++;
        }
        return measuredHeight;
    }

    @Override // android.widget.ListView, android.widget.AbsListView, android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        a(canvas);
        super.dispatchDraw(canvas);
    }

    @Override // android.widget.AbsListView, android.view.ViewGroup, android.view.View
    protected void drawableStateChanged() {
        if (this.n != null) {
            return;
        }
        super.drawableStateChanged();
        setSelectorEnabled(true);
        c();
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean hasFocus() {
        return this.j || super.hasFocus();
    }

    @Override // android.view.View
    public boolean hasWindowFocus() {
        return this.j || super.hasWindowFocus();
    }

    @Override // android.view.View
    public boolean isFocused() {
        return this.j || super.isFocused();
    }

    @Override // android.view.View
    public boolean isInTouchMode() {
        return (this.j && this.i) || super.isInTouchMode();
    }

    @Override // android.widget.ListView, android.widget.AbsListView, android.widget.AdapterView, android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        this.n = null;
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    public boolean onHoverEvent(MotionEvent motionEvent) {
        if (Build.VERSION.SDK_INT < 26) {
            return super.onHoverEvent(motionEvent);
        }
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 10 && this.n == null) {
            this.n = new b();
            this.n.b();
        }
        boolean zOnHoverEvent = super.onHoverEvent(motionEvent);
        if (actionMasked == 9 || actionMasked == 7) {
            int iPointToPosition = pointToPosition((int) motionEvent.getX(), (int) motionEvent.getY());
            if (iPointToPosition != -1 && iPointToPosition != getSelectedItemPosition()) {
                View childAt = getChildAt(iPointToPosition - getFirstVisiblePosition());
                if (childAt.isEnabled()) {
                    setSelectionFromTop(iPointToPosition, childAt.getTop() - getTop());
                }
                c();
            }
        } else {
            setSelection(-1);
        }
        return zOnHoverEvent;
    }

    @Override // android.widget.AbsListView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            this.f615f = pointToPosition((int) motionEvent.getX(), (int) motionEvent.getY());
        }
        b bVar = this.n;
        if (bVar != null) {
            bVar.a();
        }
        return super.onTouchEvent(motionEvent);
    }

    void setListSelectionHidden(boolean z) {
        this.i = z;
    }

    @Override // android.widget.AbsListView
    public void setSelector(Drawable drawable) {
        this.h = drawable != null ? new a(drawable) : null;
        super.setSelector(this.h);
        Rect rect = new Rect();
        if (drawable != null) {
            drawable.getPadding(rect);
        }
        this.f611b = rect.left;
        this.f612c = rect.top;
        this.f613d = rect.right;
        this.f614e = rect.bottom;
    }

    private boolean b() {
        return this.k;
    }

    /* JADX WARN: Code duplicated, block: B:23:0x004a  */
    /* JADX WARN: Code duplicated, block: B:25:0x004f  */
    /* JADX WARN: Code duplicated, block: B:27:0x0053  */
    /* JADX WARN: Code duplicated, block: B:29:0x0065  */
    /* JADX WARN: Code duplicated, block: B:31:0x0069  */
    /* JADX WARN: Code duplicated, block: B:9:0x0011  */
    public boolean a(MotionEvent motionEvent, int i) {
        boolean z;
        boolean z2;
        androidx.core.widget.f fVar;
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 1) {
            z = false;
        } else {
            if (actionMasked != 2) {
                if (actionMasked != 3) {
                    z2 = false;
                    z = true;
                } else {
                    z2 = false;
                    z = false;
                }
                if (z || z2) {
                    a();
                }
                if (z) {
                    if (this.m == null) {
                        this.m = new androidx.core.widget.f(this);
                    }
                    this.m.a(true);
                    this.m.onTouch(this, motionEvent);
                } else {
                    fVar = this.m;
                    if (fVar != null) {
                        fVar.a(false);
                    }
                }
                return z;
            }
            z = true;
        }
        int iFindPointerIndex = motionEvent.findPointerIndex(i);
        if (iFindPointerIndex < 0) {
            z2 = false;
            z = false;
        } else {
            int x = (int) motionEvent.getX(iFindPointerIndex);
            int y = (int) motionEvent.getY(iFindPointerIndex);
            int iPointToPosition = pointToPosition(x, y);
            if (iPointToPosition == -1) {
                z2 = true;
            } else {
                View childAt = getChildAt(iPointToPosition - getFirstVisiblePosition());
                a(childAt, iPointToPosition, x, y);
                if (actionMasked == 1) {
                    a(childAt, iPointToPosition);
                }
                z2 = false;
                z = true;
            }
        }
        if (z) {
            a();
        } else {
            a();
        }
        if (z) {
            if (this.m == null) {
                this.m = new androidx.core.widget.f(this);
            }
            this.m.a(true);
            this.m.onTouch(this, motionEvent);
        } else {
            fVar = this.m;
            if (fVar != null) {
                fVar.a(false);
            }
        }
        return z;
    }

    private void a(View view, int i) {
        performItemClick(view, i, getItemIdAtPosition(i));
    }

    private void a(Canvas canvas) {
        Drawable selector;
        if (this.f610a.isEmpty() || (selector = getSelector()) == null) {
            return;
        }
        selector.setBounds(this.f610a);
        selector.draw(canvas);
    }

    private void a(int i, View view, float f2, float f3) {
        b(i, view);
        Drawable selector = getSelector();
        if (selector == null || i == -1) {
            return;
        }
        androidx.core.graphics.drawable.a.a(selector, f2, f3);
    }

    private void a(int i, View view) {
        Rect rect = this.f610a;
        rect.set(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
        rect.left -= this.f611b;
        rect.top -= this.f612c;
        rect.right += this.f613d;
        rect.bottom += this.f614e;
        try {
            boolean z = this.g.getBoolean(this);
            if (view.isEnabled() != z) {
                this.g.set(this, Boolean.valueOf(!z));
                if (i != -1) {
                    refreshDrawableState();
                }
            }
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
        }
    }

    private void a() {
        this.k = false;
        setPressed(false);
        drawableStateChanged();
        View childAt = getChildAt(this.f615f - getFirstVisiblePosition());
        if (childAt != null) {
            childAt.setPressed(false);
        }
        androidx.core.f.x xVar = this.l;
        if (xVar != null) {
            xVar.a();
            this.l = null;
        }
    }

    private void a(View view, int i, float f2, float f3) {
        View childAt;
        this.k = true;
        if (Build.VERSION.SDK_INT >= 21) {
            drawableHotspotChanged(f2, f3);
        }
        if (!isPressed()) {
            setPressed(true);
        }
        layoutChildren();
        int i2 = this.f615f;
        if (i2 != -1 && (childAt = getChildAt(i2 - getFirstVisiblePosition())) != null && childAt != view && childAt.isPressed()) {
            childAt.setPressed(false);
        }
        this.f615f = i;
        float left = f2 - view.getLeft();
        float top = f3 - view.getTop();
        if (Build.VERSION.SDK_INT >= 21) {
            view.drawableHotspotChanged(left, top);
        }
        if (!view.isPressed()) {
            view.setPressed(true);
        }
        a(i, view, f2, f3);
        setSelectorEnabled(false);
        refreshDrawableState();
    }
}
