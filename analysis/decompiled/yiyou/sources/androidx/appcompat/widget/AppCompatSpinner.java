package androidx.appcompat.widget;

import android.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.ThemedSpinnerAdapter;
import androidx.appcompat.R$attr;
import androidx.appcompat.R$layout;
import androidx.appcompat.R$styleable;

/* JADX INFO: loaded from: classes.dex */
public class AppCompatSpinner extends Spinner implements androidx.core.f.s {
    private static final int[] i = {R.attr.spinnerMode};

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final androidx.appcompat.widget.c f578a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Context f579b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private r f580c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private SpinnerAdapter f581d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final boolean f582e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private g f583f;
    int g;
    final Rect h;

    class a extends r {
        final /* synthetic */ e j;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(View view, e eVar) {
            super(view);
            this.j = eVar;
        }

        @Override // androidx.appcompat.widget.r
        public androidx.appcompat.view.menu.p a() {
            return this.j;
        }

        @Override // androidx.appcompat.widget.r
        @SuppressLint({"SyntheticAccessor"})
        public boolean b() {
            if (AppCompatSpinner.this.getInternalPopup().b()) {
                return true;
            }
            AppCompatSpinner.this.a();
            return true;
        }
    }

    class b implements ViewTreeObserver.OnGlobalLayoutListener {
        b() {
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            if (!AppCompatSpinner.this.getInternalPopup().b()) {
                AppCompatSpinner.this.a();
            }
            ViewTreeObserver viewTreeObserver = AppCompatSpinner.this.getViewTreeObserver();
            if (viewTreeObserver != null) {
                if (Build.VERSION.SDK_INT >= 16) {
                    viewTreeObserver.removeOnGlobalLayoutListener(this);
                } else {
                    viewTreeObserver.removeGlobalOnLayoutListener(this);
                }
            }
        }
    }

    class c implements g, DialogInterface.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        androidx.appcompat.app.c f585a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private ListAdapter f586b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private CharSequence f587c;

        c() {
        }

        @Override // androidx.appcompat.widget.AppCompatSpinner.g
        public int a() {
            return 0;
        }

        @Override // androidx.appcompat.widget.AppCompatSpinner.g
        public void a(ListAdapter listAdapter) {
            this.f586b = listAdapter;
        }

        @Override // androidx.appcompat.widget.AppCompatSpinner.g
        public boolean b() {
            androidx.appcompat.app.c cVar = this.f585a;
            if (cVar != null) {
                return cVar.isShowing();
            }
            return false;
        }

        @Override // androidx.appcompat.widget.AppCompatSpinner.g
        public int c() {
            return 0;
        }

        @Override // androidx.appcompat.widget.AppCompatSpinner.g
        public void c(int i) {
            Log.e("AppCompatSpinner", "Cannot set horizontal (original) offset for MODE_DIALOG, ignoring");
        }

        @Override // androidx.appcompat.widget.AppCompatSpinner.g
        public Drawable d() {
            return null;
        }

        @Override // androidx.appcompat.widget.AppCompatSpinner.g
        public void dismiss() {
            androidx.appcompat.app.c cVar = this.f585a;
            if (cVar != null) {
                cVar.dismiss();
                this.f585a = null;
            }
        }

        @Override // androidx.appcompat.widget.AppCompatSpinner.g
        public CharSequence e() {
            return this.f587c;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i) {
            AppCompatSpinner.this.setSelection(i);
            if (AppCompatSpinner.this.getOnItemClickListener() != null) {
                AppCompatSpinner.this.performItemClick(null, i, this.f586b.getItemId(i));
            }
            dismiss();
        }

        @Override // androidx.appcompat.widget.AppCompatSpinner.g
        public void a(CharSequence charSequence) {
            this.f587c = charSequence;
        }

        @Override // androidx.appcompat.widget.AppCompatSpinner.g
        public void b(int i) {
            Log.e("AppCompatSpinner", "Cannot set vertical offset for MODE_DIALOG, ignoring");
        }

        @Override // androidx.appcompat.widget.AppCompatSpinner.g
        public void a(int i, int i2) {
            if (this.f586b == null) {
                return;
            }
            androidx.appcompat.app.c.a aVar = new androidx.appcompat.app.c.a(AppCompatSpinner.this.getPopupContext());
            CharSequence charSequence = this.f587c;
            if (charSequence != null) {
                aVar.a(charSequence);
            }
            aVar.a(this.f586b, AppCompatSpinner.this.getSelectedItemPosition(), this);
            this.f585a = aVar.a();
            ListView listViewB = this.f585a.b();
            if (Build.VERSION.SDK_INT >= 17) {
                listViewB.setTextDirection(i);
                listViewB.setTextAlignment(i2);
            }
            this.f585a.show();
        }

        @Override // androidx.appcompat.widget.AppCompatSpinner.g
        public void a(Drawable drawable) {
            Log.e("AppCompatSpinner", "Cannot set popup background for MODE_DIALOG, ignoring");
        }

        @Override // androidx.appcompat.widget.AppCompatSpinner.g
        public void a(int i) {
            Log.e("AppCompatSpinner", "Cannot set horizontal offset for MODE_DIALOG, ignoring");
        }
    }

    private static class d implements ListAdapter, SpinnerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private SpinnerAdapter f589a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private ListAdapter f590b;

        public d(SpinnerAdapter spinnerAdapter, Resources.Theme theme) {
            this.f589a = spinnerAdapter;
            if (spinnerAdapter instanceof ListAdapter) {
                this.f590b = (ListAdapter) spinnerAdapter;
            }
            if (theme != null) {
                if (Build.VERSION.SDK_INT >= 23 && (spinnerAdapter instanceof ThemedSpinnerAdapter)) {
                    ThemedSpinnerAdapter themedSpinnerAdapter = (ThemedSpinnerAdapter) spinnerAdapter;
                    if (themedSpinnerAdapter.getDropDownViewTheme() != theme) {
                        themedSpinnerAdapter.setDropDownViewTheme(theme);
                        return;
                    }
                    return;
                }
                if (spinnerAdapter instanceof z) {
                    z zVar = (z) spinnerAdapter;
                    if (zVar.getDropDownViewTheme() == null) {
                        zVar.setDropDownViewTheme(theme);
                    }
                }
            }
        }

        @Override // android.widget.ListAdapter
        public boolean areAllItemsEnabled() {
            ListAdapter listAdapter = this.f590b;
            if (listAdapter != null) {
                return listAdapter.areAllItemsEnabled();
            }
            return true;
        }

        @Override // android.widget.Adapter
        public int getCount() {
            SpinnerAdapter spinnerAdapter = this.f589a;
            if (spinnerAdapter == null) {
                return 0;
            }
            return spinnerAdapter.getCount();
        }

        @Override // android.widget.SpinnerAdapter
        public View getDropDownView(int i, View view, ViewGroup viewGroup) {
            SpinnerAdapter spinnerAdapter = this.f589a;
            if (spinnerAdapter == null) {
                return null;
            }
            return spinnerAdapter.getDropDownView(i, view, viewGroup);
        }

        @Override // android.widget.Adapter
        public Object getItem(int i) {
            SpinnerAdapter spinnerAdapter = this.f589a;
            if (spinnerAdapter == null) {
                return null;
            }
            return spinnerAdapter.getItem(i);
        }

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            SpinnerAdapter spinnerAdapter = this.f589a;
            if (spinnerAdapter == null) {
                return -1L;
            }
            return spinnerAdapter.getItemId(i);
        }

        @Override // android.widget.Adapter
        public int getItemViewType(int i) {
            return 0;
        }

        @Override // android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            return getDropDownView(i, view, viewGroup);
        }

        @Override // android.widget.Adapter
        public int getViewTypeCount() {
            return 1;
        }

        @Override // android.widget.Adapter
        public boolean hasStableIds() {
            SpinnerAdapter spinnerAdapter = this.f589a;
            return spinnerAdapter != null && spinnerAdapter.hasStableIds();
        }

        @Override // android.widget.Adapter
        public boolean isEmpty() {
            return getCount() == 0;
        }

        @Override // android.widget.ListAdapter
        public boolean isEnabled(int i) {
            ListAdapter listAdapter = this.f590b;
            if (listAdapter != null) {
                return listAdapter.isEnabled(i);
            }
            return true;
        }

        @Override // android.widget.Adapter
        public void registerDataSetObserver(DataSetObserver dataSetObserver) {
            SpinnerAdapter spinnerAdapter = this.f589a;
            if (spinnerAdapter != null) {
                spinnerAdapter.registerDataSetObserver(dataSetObserver);
            }
        }

        @Override // android.widget.Adapter
        public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {
            SpinnerAdapter spinnerAdapter = this.f589a;
            if (spinnerAdapter != null) {
                spinnerAdapter.unregisterDataSetObserver(dataSetObserver);
            }
        }
    }

    class e extends s implements g {
        private CharSequence J;
        ListAdapter K;
        private final Rect L;
        private int M;

        class a implements AdapterView.OnItemClickListener {
            a(AppCompatSpinner appCompatSpinner) {
            }

            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                AppCompatSpinner.this.setSelection(i);
                if (AppCompatSpinner.this.getOnItemClickListener() != null) {
                    e eVar = e.this;
                    AppCompatSpinner.this.performItemClick(view, i, eVar.K.getItemId(i));
                }
                e.this.dismiss();
            }
        }

        class b implements ViewTreeObserver.OnGlobalLayoutListener {
            b() {
            }

            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                e eVar = e.this;
                if (!eVar.b(AppCompatSpinner.this)) {
                    e.this.dismiss();
                } else {
                    e.this.l();
                    e.super.show();
                }
            }
        }

        class c implements PopupWindow.OnDismissListener {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ ViewTreeObserver.OnGlobalLayoutListener f593a;

            c(ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener) {
                this.f593a = onGlobalLayoutListener;
            }

            @Override // android.widget.PopupWindow.OnDismissListener
            public void onDismiss() {
                ViewTreeObserver viewTreeObserver = AppCompatSpinner.this.getViewTreeObserver();
                if (viewTreeObserver != null) {
                    viewTreeObserver.removeGlobalOnLayoutListener(this.f593a);
                }
            }
        }

        public e(Context context, AttributeSet attributeSet, int i) {
            super(context, attributeSet, i);
            this.L = new Rect();
            a(AppCompatSpinner.this);
            a(true);
            h(0);
            a(new a(AppCompatSpinner.this));
        }

        boolean b(View view) {
            return androidx.core.f.t.q(view) && view.getGlobalVisibleRect(this.L);
        }

        @Override // androidx.appcompat.widget.AppCompatSpinner.g
        public void c(int i) {
            this.M = i;
        }

        @Override // androidx.appcompat.widget.AppCompatSpinner.g
        public CharSequence e() {
            return this.J;
        }

        void l() {
            Drawable drawableD = d();
            int i = 0;
            if (drawableD != null) {
                drawableD.getPadding(AppCompatSpinner.this.h);
                i = j0.a(AppCompatSpinner.this) ? AppCompatSpinner.this.h.right : -AppCompatSpinner.this.h.left;
            } else {
                Rect rect = AppCompatSpinner.this.h;
                rect.right = 0;
                rect.left = 0;
            }
            int paddingLeft = AppCompatSpinner.this.getPaddingLeft();
            int paddingRight = AppCompatSpinner.this.getPaddingRight();
            int width = AppCompatSpinner.this.getWidth();
            AppCompatSpinner appCompatSpinner = AppCompatSpinner.this;
            int i2 = appCompatSpinner.g;
            if (i2 == -2) {
                int iA = appCompatSpinner.a((SpinnerAdapter) this.K, d());
                int i3 = AppCompatSpinner.this.getContext().getResources().getDisplayMetrics().widthPixels;
                Rect rect2 = AppCompatSpinner.this.h;
                int i4 = (i3 - rect2.left) - rect2.right;
                if (iA > i4) {
                    iA = i4;
                }
                e(Math.max(iA, (width - paddingLeft) - paddingRight));
            } else if (i2 == -1) {
                e((width - paddingLeft) - paddingRight);
            } else {
                e(i2);
            }
            a(j0.a(AppCompatSpinner.this) ? i + (((width - paddingRight) - i()) - m()) : i + paddingLeft + m());
        }

        public int m() {
            return this.M;
        }

        @Override // androidx.appcompat.widget.s, androidx.appcompat.widget.AppCompatSpinner.g
        public void a(ListAdapter listAdapter) {
            super.a(listAdapter);
            this.K = listAdapter;
        }

        @Override // androidx.appcompat.widget.AppCompatSpinner.g
        public void a(CharSequence charSequence) {
            this.J = charSequence;
        }

        @Override // androidx.appcompat.widget.AppCompatSpinner.g
        public void a(int i, int i2) {
            ViewTreeObserver viewTreeObserver;
            boolean zB = b();
            l();
            g(2);
            super.show();
            ListView listViewF = f();
            listViewF.setChoiceMode(1);
            if (Build.VERSION.SDK_INT >= 17) {
                listViewF.setTextDirection(i);
                listViewF.setTextAlignment(i2);
            }
            i(AppCompatSpinner.this.getSelectedItemPosition());
            if (zB || (viewTreeObserver = AppCompatSpinner.this.getViewTreeObserver()) == null) {
                return;
            }
            b bVar = new b();
            viewTreeObserver.addOnGlobalLayoutListener(bVar);
            a(new c(bVar));
        }
    }

    static class f extends View.BaseSavedState {
        public static final Parcelable.Creator<f> CREATOR = new a();

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        boolean f595a;

        static class a implements Parcelable.Creator<f> {
            a() {
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public f createFromParcel(Parcel parcel) {
                return new f(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public f[] newArray(int i) {
                return new f[i];
            }
        }

        f(Parcelable parcelable) {
            super(parcelable);
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeByte(this.f595a ? (byte) 1 : (byte) 0);
        }

        f(Parcel parcel) {
            super(parcel);
            this.f595a = parcel.readByte() != 0;
        }
    }

    interface g {
        int a();

        void a(int i);

        void a(int i, int i2);

        void a(Drawable drawable);

        void a(ListAdapter listAdapter);

        void a(CharSequence charSequence);

        void b(int i);

        boolean b();

        int c();

        void c(int i);

        Drawable d();

        void dismiss();

        CharSequence e();
    }

    public AppCompatSpinner(Context context) {
        this(context, null);
    }

    int a(SpinnerAdapter spinnerAdapter, Drawable drawable) {
        int i2 = 0;
        if (spinnerAdapter == null) {
            return 0;
        }
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 0);
        int iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 0);
        int iMax = Math.max(0, getSelectedItemPosition());
        int iMin = Math.min(spinnerAdapter.getCount(), iMax + 15);
        View view = null;
        int iMax2 = 0;
        for (int iMax3 = Math.max(0, iMax - (15 - (iMin - iMax))); iMax3 < iMin; iMax3++) {
            int itemViewType = spinnerAdapter.getItemViewType(iMax3);
            if (itemViewType != i2) {
                view = null;
                i2 = itemViewType;
            }
            view = spinnerAdapter.getView(iMax3, view, this);
            if (view.getLayoutParams() == null) {
                view.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
            }
            view.measure(iMakeMeasureSpec, iMakeMeasureSpec2);
            iMax2 = Math.max(iMax2, view.getMeasuredWidth());
        }
        if (drawable == null) {
            return iMax2;
        }
        drawable.getPadding(this.h);
        Rect rect = this.h;
        return iMax2 + rect.left + rect.right;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        androidx.appcompat.widget.c cVar = this.f578a;
        if (cVar != null) {
            cVar.a();
        }
    }

    @Override // android.widget.Spinner
    public int getDropDownHorizontalOffset() {
        g gVar = this.f583f;
        if (gVar != null) {
            return gVar.a();
        }
        if (Build.VERSION.SDK_INT >= 16) {
            return super.getDropDownHorizontalOffset();
        }
        return 0;
    }

    @Override // android.widget.Spinner
    public int getDropDownVerticalOffset() {
        g gVar = this.f583f;
        if (gVar != null) {
            return gVar.c();
        }
        if (Build.VERSION.SDK_INT >= 16) {
            return super.getDropDownVerticalOffset();
        }
        return 0;
    }

    @Override // android.widget.Spinner
    public int getDropDownWidth() {
        if (this.f583f != null) {
            return this.g;
        }
        if (Build.VERSION.SDK_INT >= 16) {
            return super.getDropDownWidth();
        }
        return 0;
    }

    final g getInternalPopup() {
        return this.f583f;
    }

    @Override // android.widget.Spinner
    public Drawable getPopupBackground() {
        g gVar = this.f583f;
        if (gVar != null) {
            return gVar.d();
        }
        if (Build.VERSION.SDK_INT >= 16) {
            return super.getPopupBackground();
        }
        return null;
    }

    @Override // android.widget.Spinner
    public Context getPopupContext() {
        return this.f579b;
    }

    @Override // android.widget.Spinner
    public CharSequence getPrompt() {
        g gVar = this.f583f;
        return gVar != null ? gVar.e() : super.getPrompt();
    }

    @Override // androidx.core.f.s
    public ColorStateList getSupportBackgroundTintList() {
        androidx.appcompat.widget.c cVar = this.f578a;
        if (cVar != null) {
            return cVar.b();
        }
        return null;
    }

    @Override // androidx.core.f.s
    public PorterDuff.Mode getSupportBackgroundTintMode() {
        androidx.appcompat.widget.c cVar = this.f578a;
        if (cVar != null) {
            return cVar.c();
        }
        return null;
    }

    @Override // android.widget.Spinner, android.widget.AdapterView, android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        g gVar = this.f583f;
        if (gVar == null || !gVar.b()) {
            return;
        }
        this.f583f.dismiss();
    }

    @Override // android.widget.Spinner, android.widget.AbsSpinner, android.view.View
    protected void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
        if (this.f583f == null || View.MeasureSpec.getMode(i2) != Integer.MIN_VALUE) {
            return;
        }
        setMeasuredDimension(Math.min(Math.max(getMeasuredWidth(), a(getAdapter(), getBackground())), View.MeasureSpec.getSize(i2)), getMeasuredHeight());
    }

    @Override // android.widget.Spinner, android.widget.AbsSpinner, android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        ViewTreeObserver viewTreeObserver;
        f fVar = (f) parcelable;
        super.onRestoreInstanceState(fVar.getSuperState());
        if (!fVar.f595a || (viewTreeObserver = getViewTreeObserver()) == null) {
            return;
        }
        viewTreeObserver.addOnGlobalLayoutListener(new b());
    }

    @Override // android.widget.Spinner, android.widget.AbsSpinner, android.view.View
    public Parcelable onSaveInstanceState() {
        f fVar = new f(super.onSaveInstanceState());
        g gVar = this.f583f;
        fVar.f595a = gVar != null && gVar.b();
        return fVar;
    }

    @Override // android.widget.Spinner, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        r rVar = this.f580c;
        if (rVar == null || !rVar.onTouch(this, motionEvent)) {
            return super.onTouchEvent(motionEvent);
        }
        return true;
    }

    @Override // android.widget.Spinner, android.view.View
    public boolean performClick() {
        g gVar = this.f583f;
        if (gVar == null) {
            return super.performClick();
        }
        if (gVar.b()) {
            return true;
        }
        a();
        return true;
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        androidx.appcompat.widget.c cVar = this.f578a;
        if (cVar != null) {
            cVar.a(drawable);
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i2) {
        super.setBackgroundResource(i2);
        androidx.appcompat.widget.c cVar = this.f578a;
        if (cVar != null) {
            cVar.a(i2);
        }
    }

    @Override // android.widget.Spinner
    public void setDropDownHorizontalOffset(int i2) {
        g gVar = this.f583f;
        if (gVar != null) {
            gVar.c(i2);
            this.f583f.a(i2);
        } else if (Build.VERSION.SDK_INT >= 16) {
            super.setDropDownHorizontalOffset(i2);
        }
    }

    @Override // android.widget.Spinner
    public void setDropDownVerticalOffset(int i2) {
        g gVar = this.f583f;
        if (gVar != null) {
            gVar.b(i2);
        } else if (Build.VERSION.SDK_INT >= 16) {
            super.setDropDownVerticalOffset(i2);
        }
    }

    @Override // android.widget.Spinner
    public void setDropDownWidth(int i2) {
        if (this.f583f != null) {
            this.g = i2;
        } else if (Build.VERSION.SDK_INT >= 16) {
            super.setDropDownWidth(i2);
        }
    }

    @Override // android.widget.Spinner
    public void setPopupBackgroundDrawable(Drawable drawable) {
        g gVar = this.f583f;
        if (gVar != null) {
            gVar.a(drawable);
        } else if (Build.VERSION.SDK_INT >= 16) {
            super.setPopupBackgroundDrawable(drawable);
        }
    }

    @Override // android.widget.Spinner
    public void setPopupBackgroundResource(int i2) {
        setPopupBackgroundDrawable(androidx.appcompat.a.a.a.c(getPopupContext(), i2));
    }

    @Override // android.widget.Spinner
    public void setPrompt(CharSequence charSequence) {
        g gVar = this.f583f;
        if (gVar != null) {
            gVar.a(charSequence);
        } else {
            super.setPrompt(charSequence);
        }
    }

    @Override // androidx.core.f.s
    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        androidx.appcompat.widget.c cVar = this.f578a;
        if (cVar != null) {
            cVar.b(colorStateList);
        }
    }

    @Override // androidx.core.f.s
    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        androidx.appcompat.widget.c cVar = this.f578a;
        if (cVar != null) {
            cVar.a(mode);
        }
    }

    public AppCompatSpinner(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.spinnerStyle);
    }

    @Override // android.widget.AdapterView
    public void setAdapter(SpinnerAdapter spinnerAdapter) {
        if (!this.f582e) {
            this.f581d = spinnerAdapter;
            return;
        }
        super.setAdapter(spinnerAdapter);
        if (this.f583f != null) {
            Context context = this.f579b;
            if (context == null) {
                context = getContext();
            }
            this.f583f.a(new d(spinnerAdapter, context.getTheme()));
        }
    }

    public AppCompatSpinner(Context context, AttributeSet attributeSet, int i2) {
        this(context, attributeSet, i2, -1);
    }

    public AppCompatSpinner(Context context, AttributeSet attributeSet, int i2, int i3) {
        this(context, attributeSet, i2, i3, null);
    }

    /* JADX WARN: Code duplicated, block: B:16:0x004a A[PHI: r10 r11
      0x004a: PHI (r10v2 int) = (r10v0 int), (r10v4 int) binds: [B:24:0x005c, B:15:0x0048] A[DONT_GENERATE, DONT_INLINE]
      0x004a: PHI (r11v9 android.content.res.TypedArray) = (r11v8 android.content.res.TypedArray), (r11v11 android.content.res.TypedArray) binds: [B:24:0x005c, B:15:0x0048] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:28:0x0062  */
    public AppCompatSpinner(Context context, AttributeSet attributeSet, int i2, int i3, Resources.Theme theme) throws Throwable {
        TypedArray typedArrayObtainStyledAttributes;
        super(context, attributeSet, i2);
        this.h = new Rect();
        d0 d0VarA = d0.a(context, attributeSet, R$styleable.Spinner, i2, 0);
        this.f578a = new androidx.appcompat.widget.c(this);
        if (theme != null) {
            this.f579b = new androidx.appcompat.d.d(context, theme);
        } else {
            int iG = d0VarA.g(R$styleable.Spinner_popupTheme, 0);
            if (iG != 0) {
                this.f579b = new androidx.appcompat.d.d(context, iG);
            } else {
                this.f579b = context;
            }
        }
        if (i3 == -1) {
            try {
                typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, i, i2, 0);
                try {
                    try {
                        i3 = typedArrayObtainStyledAttributes.hasValue(0) ? typedArrayObtainStyledAttributes.getInt(0, 0) : i3;
                        if (typedArrayObtainStyledAttributes != null) {
                            typedArrayObtainStyledAttributes.recycle();
                        }
                    } catch (Exception e2) {
                        e = e2;
                        Log.i("AppCompatSpinner", "Could not read android:spinnerMode", e);
                        if (typedArrayObtainStyledAttributes != null) {
                            typedArrayObtainStyledAttributes.recycle();
                        }
                    }
                } catch (Throwable th) {
                    th = th;
                    if (typedArrayObtainStyledAttributes != null) {
                        typedArrayObtainStyledAttributes.recycle();
                    }
                    throw th;
                }
            } catch (Exception e3) {
                e = e3;
                typedArrayObtainStyledAttributes = null;
            } catch (Throwable th2) {
                th = th2;
                typedArrayObtainStyledAttributes = null;
                if (typedArrayObtainStyledAttributes != null) {
                    typedArrayObtainStyledAttributes.recycle();
                }
                throw th;
            }
        }
        if (i3 == 0) {
            this.f583f = new c();
            this.f583f.a(d0VarA.d(R$styleable.Spinner_android_prompt));
        } else if (i3 == 1) {
            e eVar = new e(this.f579b, attributeSet, i2);
            d0 d0VarA2 = d0.a(this.f579b, attributeSet, R$styleable.Spinner, i2, 0);
            this.g = d0VarA2.f(R$styleable.Spinner_android_dropDownWidth, -2);
            eVar.a(d0VarA2.b(R$styleable.Spinner_android_popupBackground));
            eVar.a(d0VarA.d(R$styleable.Spinner_android_prompt));
            d0VarA2.a();
            this.f583f = eVar;
            this.f580c = new a(this, eVar);
        }
        CharSequence[] charSequenceArrF = d0VarA.f(R$styleable.Spinner_android_entries);
        if (charSequenceArrF != null) {
            ArrayAdapter arrayAdapter = new ArrayAdapter(context, R.layout.simple_spinner_item, charSequenceArrF);
            arrayAdapter.setDropDownViewResource(R$layout.support_simple_spinner_dropdown_item);
            setAdapter((SpinnerAdapter) arrayAdapter);
        }
        d0VarA.a();
        this.f582e = true;
        SpinnerAdapter spinnerAdapter = this.f581d;
        if (spinnerAdapter != null) {
            setAdapter(spinnerAdapter);
            this.f581d = null;
        }
        this.f578a.a(attributeSet, i2);
    }

    void a() {
        if (Build.VERSION.SDK_INT >= 17) {
            this.f583f.a(getTextDirection(), getTextAlignment());
        } else {
            this.f583f.a(-1, -1);
        }
    }
}
