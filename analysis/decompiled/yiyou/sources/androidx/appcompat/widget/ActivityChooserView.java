package androidx.appcompat.widget;

import android.R;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.appcompat.R$dimen;
import androidx.appcompat.R$id;
import androidx.appcompat.R$layout;
import androidx.appcompat.R$string;
import androidx.appcompat.R$styleable;
import com.blankj.utilcode.constant.MemoryConstants;

/* JADX INFO: loaded from: classes.dex */
public class ActivityChooserView extends ViewGroup implements androidx.appcompat.widget.b.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final f f536a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final g f537b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final View f538c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final Drawable f539d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    final FrameLayout f540e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final ImageView f541f;
    final FrameLayout g;
    private final ImageView h;
    private final int i;
    androidx.core.f.b j;
    final DataSetObserver k;
    private final ViewTreeObserver.OnGlobalLayoutListener l;
    private s m;
    PopupWindow.OnDismissListener n;
    boolean o;
    int p;
    private boolean q;
    private int r;

    public static class InnerLayout extends LinearLayout {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private static final int[] f542a = {R.attr.background};

        public InnerLayout(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            d0 d0VarA = d0.a(context, attributeSet, f542a);
            setBackgroundDrawable(d0VarA.b(0));
            d0VarA.a();
        }
    }

    class a extends DataSetObserver {
        a() {
        }

        @Override // android.database.DataSetObserver
        public void onChanged() {
            super.onChanged();
            ActivityChooserView.this.f536a.notifyDataSetChanged();
        }

        @Override // android.database.DataSetObserver
        public void onInvalidated() {
            super.onInvalidated();
            ActivityChooserView.this.f536a.notifyDataSetInvalidated();
        }
    }

    class b implements ViewTreeObserver.OnGlobalLayoutListener {
        b() {
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            if (ActivityChooserView.this.b()) {
                if (!ActivityChooserView.this.isShown()) {
                    ActivityChooserView.this.getListPopupWindow().dismiss();
                    return;
                }
                ActivityChooserView.this.getListPopupWindow().show();
                androidx.core.f.b bVar = ActivityChooserView.this.j;
                if (bVar != null) {
                    bVar.a(true);
                }
            }
        }
    }

    class c extends View.AccessibilityDelegate {
        c(ActivityChooserView activityChooserView) {
        }

        @Override // android.view.View.AccessibilityDelegate
        public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
            super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
            androidx.core.f.c0.b.a(accessibilityNodeInfo).b(true);
        }
    }

    class d extends r {
        d(View view) {
            super(view);
        }

        @Override // androidx.appcompat.widget.r
        public androidx.appcompat.view.menu.p a() {
            return ActivityChooserView.this.getListPopupWindow();
        }

        @Override // androidx.appcompat.widget.r
        protected boolean b() {
            ActivityChooserView.this.c();
            return true;
        }

        @Override // androidx.appcompat.widget.r
        protected boolean c() {
            ActivityChooserView.this.a();
            return true;
        }
    }

    class e extends DataSetObserver {
        e() {
        }

        @Override // android.database.DataSetObserver
        public void onChanged() {
            super.onChanged();
            ActivityChooserView.this.d();
        }
    }

    private class g implements AdapterView.OnItemClickListener, View.OnClickListener, View.OnLongClickListener, PopupWindow.OnDismissListener {
        g() {
        }

        private void a() {
            PopupWindow.OnDismissListener onDismissListener = ActivityChooserView.this.n;
            if (onDismissListener != null) {
                onDismissListener.onDismiss();
            }
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ActivityChooserView activityChooserView = ActivityChooserView.this;
            if (view != activityChooserView.g) {
                if (view != activityChooserView.f540e) {
                    throw new IllegalArgumentException();
                }
                activityChooserView.o = false;
                activityChooserView.a(activityChooserView.p);
                return;
            }
            activityChooserView.a();
            Intent intentA = ActivityChooserView.this.f536a.b().a(ActivityChooserView.this.f536a.b().a(ActivityChooserView.this.f536a.c()));
            if (intentA != null) {
                intentA.addFlags(524288);
                ActivityChooserView.this.getContext().startActivity(intentA);
            }
        }

        @Override // android.widget.PopupWindow.OnDismissListener
        public void onDismiss() {
            a();
            androidx.core.f.b bVar = ActivityChooserView.this.j;
            if (bVar != null) {
                bVar.a(false);
            }
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            int itemViewType = ((f) adapterView.getAdapter()).getItemViewType(i);
            if (itemViewType != 0) {
                if (itemViewType != 1) {
                    throw new IllegalArgumentException();
                }
                ActivityChooserView.this.a(Integer.MAX_VALUE);
                return;
            }
            ActivityChooserView.this.a();
            ActivityChooserView activityChooserView = ActivityChooserView.this;
            if (activityChooserView.o) {
                if (i > 0) {
                    activityChooserView.f536a.b().c(i);
                    return;
                }
                return;
            }
            if (!activityChooserView.f536a.e()) {
                i++;
            }
            Intent intentA = ActivityChooserView.this.f536a.b().a(i);
            if (intentA != null) {
                intentA.addFlags(524288);
                ActivityChooserView.this.getContext().startActivity(intentA);
            }
        }

        @Override // android.view.View.OnLongClickListener
        public boolean onLongClick(View view) {
            ActivityChooserView activityChooserView = ActivityChooserView.this;
            if (view != activityChooserView.g) {
                throw new IllegalArgumentException();
            }
            if (activityChooserView.f536a.getCount() > 0) {
                ActivityChooserView activityChooserView2 = ActivityChooserView.this;
                activityChooserView2.o = true;
                activityChooserView2.a(activityChooserView2.p);
            }
            return true;
        }
    }

    public ActivityChooserView(Context context) {
        this(context, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v15 */
    /* JADX WARN: Type inference failed for: r0v6 */
    /* JADX WARN: Type inference failed for: r0v7, types: [boolean, int] */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    void a(int i) {
        if (this.f536a.b() == null) {
            throw new IllegalStateException("No data model. Did you call #setDataModel?");
        }
        getViewTreeObserver().addOnGlobalLayoutListener(this.l);
        ?? r0 = this.g.getVisibility() == 0 ? 1 : 0;
        int iA = this.f536a.a();
        if (i == Integer.MAX_VALUE || iA <= i + r0) {
            this.f536a.a(false);
            this.f536a.a(i);
        } else {
            this.f536a.a(true);
            this.f536a.a(i - 1);
        }
        s listPopupWindow = getListPopupWindow();
        if (listPopupWindow.b()) {
            return;
        }
        if (this.o || r0 == 0) {
            this.f536a.a(true, r0);
        } else {
            this.f536a.a(false, false);
        }
        listPopupWindow.e(Math.min(this.f536a.f(), this.i));
        listPopupWindow.show();
        androidx.core.f.b bVar = this.j;
        if (bVar != null) {
            bVar.a(true);
        }
        listPopupWindow.f().setContentDescription(getContext().getString(R$string.abc_activitychooserview_choose_application));
        listPopupWindow.f().setSelector(new ColorDrawable(0));
    }

    public boolean b() {
        return getListPopupWindow().b();
    }

    public boolean c() {
        if (b() || !this.q) {
            return false;
        }
        this.o = false;
        a(this.p);
        return true;
    }

    void d() {
        if (this.f536a.getCount() > 0) {
            this.f540e.setEnabled(true);
        } else {
            this.f540e.setEnabled(false);
        }
        int iA = this.f536a.a();
        int iD = this.f536a.d();
        if (iA == 1 || (iA > 1 && iD > 0)) {
            this.g.setVisibility(0);
            ResolveInfo resolveInfoC = this.f536a.c();
            PackageManager packageManager = getContext().getPackageManager();
            this.h.setImageDrawable(resolveInfoC.loadIcon(packageManager));
            if (this.r != 0) {
                this.g.setContentDescription(getContext().getString(this.r, resolveInfoC.loadLabel(packageManager)));
            }
        } else {
            this.g.setVisibility(8);
        }
        if (this.g.getVisibility() == 0) {
            this.f538c.setBackgroundDrawable(this.f539d);
        } else {
            this.f538c.setBackgroundDrawable(null);
        }
    }

    public androidx.appcompat.widget.b getDataModel() {
        return this.f536a.b();
    }

    s getListPopupWindow() {
        if (this.m == null) {
            this.m = new s(getContext());
            this.m.a(this.f536a);
            this.m.a(this);
            this.m.a(true);
            this.m.a((AdapterView.OnItemClickListener) this.f537b);
            this.m.a((PopupWindow.OnDismissListener) this.f537b);
        }
        return this.m;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        androidx.appcompat.widget.b bVarB = this.f536a.b();
        if (bVarB != null) {
            bVarB.registerObserver(this.k);
        }
        this.q = true;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        androidx.appcompat.widget.b bVarB = this.f536a.b();
        if (bVarB != null) {
            bVarB.unregisterObserver(this.k);
        }
        ViewTreeObserver viewTreeObserver = getViewTreeObserver();
        if (viewTreeObserver.isAlive()) {
            viewTreeObserver.removeGlobalOnLayoutListener(this.l);
        }
        if (b()) {
            a();
        }
        this.q = false;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        this.f538c.layout(0, 0, i3 - i, i4 - i2);
        if (b()) {
            return;
        }
        a();
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        View view = this.f538c;
        if (this.g.getVisibility() != 0) {
            i2 = View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(i2), MemoryConstants.GB);
        }
        measureChild(view, i, i2);
        setMeasuredDimension(view.getMeasuredWidth(), view.getMeasuredHeight());
    }

    public void setActivityChooserModel(androidx.appcompat.widget.b bVar) {
        this.f536a.a(bVar);
        if (b()) {
            a();
            c();
        }
    }

    public void setDefaultActionButtonContentDescription(int i) {
        this.r = i;
    }

    public void setExpandActivityOverflowButtonContentDescription(int i) {
        this.f541f.setContentDescription(getContext().getString(i));
    }

    public void setExpandActivityOverflowButtonDrawable(Drawable drawable) {
        this.f541f.setImageDrawable(drawable);
    }

    public void setInitialActivityCount(int i) {
        this.p = i;
    }

    public void setOnDismissListener(PopupWindow.OnDismissListener onDismissListener) {
        this.n = onDismissListener;
    }

    public void setProvider(androidx.core.f.b bVar) {
        this.j = bVar;
    }

    public ActivityChooserView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ActivityChooserView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.k = new a();
        this.l = new b();
        this.p = 4;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.ActivityChooserView, i, 0);
        if (Build.VERSION.SDK_INT >= 29) {
            saveAttributeDataForStyleable(context, R$styleable.ActivityChooserView, attributeSet, typedArrayObtainStyledAttributes, i, 0);
        }
        this.p = typedArrayObtainStyledAttributes.getInt(R$styleable.ActivityChooserView_initialActivityCount, 4);
        Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(R$styleable.ActivityChooserView_expandActivityOverflowButtonDrawable);
        typedArrayObtainStyledAttributes.recycle();
        LayoutInflater.from(getContext()).inflate(R$layout.abc_activity_chooser_view, (ViewGroup) this, true);
        this.f537b = new g();
        this.f538c = findViewById(R$id.activity_chooser_view_content);
        this.f539d = this.f538c.getBackground();
        this.g = (FrameLayout) findViewById(R$id.default_activity_button);
        this.g.setOnClickListener(this.f537b);
        this.g.setOnLongClickListener(this.f537b);
        this.h = (ImageView) this.g.findViewById(R$id.image);
        FrameLayout frameLayout = (FrameLayout) findViewById(R$id.expand_activities_button);
        frameLayout.setOnClickListener(this.f537b);
        frameLayout.setAccessibilityDelegate(new c(this));
        frameLayout.setOnTouchListener(new d(frameLayout));
        this.f540e = frameLayout;
        this.f541f = (ImageView) frameLayout.findViewById(R$id.image);
        this.f541f.setImageDrawable(drawable);
        this.f536a = new f();
        this.f536a.registerDataSetObserver(new e());
        Resources resources = context.getResources();
        this.i = Math.max(resources.getDisplayMetrics().widthPixels / 2, resources.getDimensionPixelSize(R$dimen.abc_config_prefDialogWidth));
    }

    private class f extends BaseAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private androidx.appcompat.widget.b f546a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private int f547b = 4;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private boolean f548c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private boolean f549d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private boolean f550e;

        f() {
        }

        public void a(androidx.appcompat.widget.b bVar) {
            androidx.appcompat.widget.b bVarB = ActivityChooserView.this.f536a.b();
            if (bVarB != null && ActivityChooserView.this.isShown()) {
                bVarB.unregisterObserver(ActivityChooserView.this.k);
            }
            this.f546a = bVar;
            if (bVar != null && ActivityChooserView.this.isShown()) {
                bVar.registerObserver(ActivityChooserView.this.k);
            }
            notifyDataSetChanged();
        }

        public androidx.appcompat.widget.b b() {
            return this.f546a;
        }

        public ResolveInfo c() {
            return this.f546a.b();
        }

        public int d() {
            return this.f546a.c();
        }

        public boolean e() {
            return this.f548c;
        }

        public int f() {
            int i = this.f547b;
            this.f547b = Integer.MAX_VALUE;
            int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
            int iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(0, 0);
            int count = getCount();
            View view = null;
            int iMax = 0;
            for (int i2 = 0; i2 < count; i2++) {
                view = getView(i2, view, null);
                view.measure(iMakeMeasureSpec, iMakeMeasureSpec2);
                iMax = Math.max(iMax, view.getMeasuredWidth());
            }
            this.f547b = i;
            return iMax;
        }

        @Override // android.widget.Adapter
        public int getCount() {
            int iA = this.f546a.a();
            if (!this.f548c && this.f546a.b() != null) {
                iA--;
            }
            int iMin = Math.min(iA, this.f547b);
            return this.f550e ? iMin + 1 : iMin;
        }

        @Override // android.widget.Adapter
        public Object getItem(int i) {
            int itemViewType = getItemViewType(i);
            if (itemViewType != 0) {
                if (itemViewType == 1) {
                    return null;
                }
                throw new IllegalArgumentException();
            }
            if (!this.f548c && this.f546a.b() != null) {
                i++;
            }
            return this.f546a.b(i);
        }

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return i;
        }

        @Override // android.widget.BaseAdapter, android.widget.Adapter
        public int getItemViewType(int i) {
            return (this.f550e && i == getCount() - 1) ? 1 : 0;
        }

        @Override // android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            int itemViewType = getItemViewType(i);
            if (itemViewType != 0) {
                if (itemViewType != 1) {
                    throw new IllegalArgumentException();
                }
                if (view != null && view.getId() == 1) {
                    return view;
                }
                View viewInflate = LayoutInflater.from(ActivityChooserView.this.getContext()).inflate(R$layout.abc_activity_chooser_view_list_item, viewGroup, false);
                viewInflate.setId(1);
                ((TextView) viewInflate.findViewById(R$id.title)).setText(ActivityChooserView.this.getContext().getString(R$string.abc_activity_chooser_view_see_all));
                return viewInflate;
            }
            if (view == null || view.getId() != R$id.list_item) {
                view = LayoutInflater.from(ActivityChooserView.this.getContext()).inflate(R$layout.abc_activity_chooser_view_list_item, viewGroup, false);
            }
            PackageManager packageManager = ActivityChooserView.this.getContext().getPackageManager();
            ImageView imageView = (ImageView) view.findViewById(R$id.icon);
            ResolveInfo resolveInfo = (ResolveInfo) getItem(i);
            imageView.setImageDrawable(resolveInfo.loadIcon(packageManager));
            ((TextView) view.findViewById(R$id.title)).setText(resolveInfo.loadLabel(packageManager));
            if (this.f548c && i == 0 && this.f549d) {
                view.setActivated(true);
            } else {
                view.setActivated(false);
            }
            return view;
        }

        @Override // android.widget.BaseAdapter, android.widget.Adapter
        public int getViewTypeCount() {
            return 3;
        }

        public void a(int i) {
            if (this.f547b != i) {
                this.f547b = i;
                notifyDataSetChanged();
            }
        }

        public void a(boolean z) {
            if (this.f550e != z) {
                this.f550e = z;
                notifyDataSetChanged();
            }
        }

        public int a() {
            return this.f546a.a();
        }

        public void a(boolean z, boolean z2) {
            if (this.f548c == z && this.f549d == z2) {
                return;
            }
            this.f548c = z;
            this.f549d = z2;
            notifyDataSetChanged();
        }
    }

    public boolean a() {
        if (!b()) {
            return true;
        }
        getListPopupWindow().dismiss();
        ViewTreeObserver viewTreeObserver = getViewTreeObserver();
        if (!viewTreeObserver.isAlive()) {
            return true;
        }
        viewTreeObserver.removeGlobalOnLayoutListener(this.l);
        return true;
    }
}
