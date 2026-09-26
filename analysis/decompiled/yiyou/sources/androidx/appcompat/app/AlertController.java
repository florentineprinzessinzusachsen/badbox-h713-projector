package androidx.appcompat.app;

import android.R;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewStub;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.CursorAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import androidx.appcompat.R$attr;
import androidx.appcompat.R$id;
import androidx.appcompat.R$styleable;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.core.f.t;
import androidx.core.widget.NestedScrollView;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
class AlertController {
    NestedScrollView A;
    private Drawable C;
    private ImageView D;
    private TextView E;
    private TextView F;
    private View G;
    ListAdapter H;
    private int J;
    private int K;
    int L;
    int M;
    int N;
    int O;
    private boolean P;
    Handler R;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Context f216a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final androidx.appcompat.app.g f217b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Window f218c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final int f219d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private CharSequence f220e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private CharSequence f221f;
    ListView g;
    private View h;
    private int i;
    private int j;
    private int k;
    private int l;
    private int m;
    Button o;
    private CharSequence p;
    Message q;
    private Drawable r;
    Button s;
    private CharSequence t;
    Message u;
    private Drawable v;
    Button w;
    private CharSequence x;
    Message y;
    private Drawable z;
    private boolean n = false;
    private int B = 0;
    int I = -1;
    private int Q = 0;
    private final View.OnClickListener S = new a();

    public static class RecycleListView extends ListView {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final int f222a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final int f223b;

        public RecycleListView(Context context) {
            this(context, null);
        }

        public void a(boolean z, boolean z2) {
            if (z2 && z) {
                return;
            }
            setPadding(getPaddingLeft(), z ? getPaddingTop() : this.f222a, getPaddingRight(), z2 ? getPaddingBottom() : this.f223b);
        }

        public RecycleListView(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.RecycleListView);
            this.f223b = typedArrayObtainStyledAttributes.getDimensionPixelOffset(R$styleable.RecycleListView_paddingBottomNoButtons, -1);
            this.f222a = typedArrayObtainStyledAttributes.getDimensionPixelOffset(R$styleable.RecycleListView_paddingTopNoTitle, -1);
        }
    }

    class a implements View.OnClickListener {
        a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Message messageObtain;
            Message message;
            Message message2;
            Message message3;
            AlertController alertController = AlertController.this;
            if (view != alertController.o || (message3 = alertController.q) == null) {
                AlertController alertController2 = AlertController.this;
                if (view != alertController2.s || (message2 = alertController2.u) == null) {
                    AlertController alertController3 = AlertController.this;
                    messageObtain = (view != alertController3.w || (message = alertController3.y) == null) ? null : Message.obtain(message);
                } else {
                    messageObtain = Message.obtain(message2);
                }
            } else {
                messageObtain = Message.obtain(message3);
            }
            if (messageObtain != null) {
                messageObtain.sendToTarget();
            }
            AlertController alertController4 = AlertController.this;
            alertController4.R.obtainMessage(1, alertController4.f217b).sendToTarget();
        }
    }

    class b implements NestedScrollView.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ View f225a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ View f226b;

        b(AlertController alertController, View view, View view2) {
            this.f225a = view;
            this.f226b = view2;
        }

        @Override // androidx.core.widget.NestedScrollView.b
        public void a(NestedScrollView nestedScrollView, int i, int i2, int i3, int i4) {
            AlertController.a(nestedScrollView, this.f225a, this.f226b);
        }
    }

    class c implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ View f227a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ View f228b;

        c(View view, View view2) {
            this.f227a = view;
            this.f228b = view2;
        }

        @Override // java.lang.Runnable
        public void run() {
            AlertController.a(AlertController.this.A, this.f227a, this.f228b);
        }
    }

    class d implements AbsListView.OnScrollListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ View f230a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ View f231b;

        d(AlertController alertController, View view, View view2) {
            this.f230a = view;
            this.f231b = view2;
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScroll(AbsListView absListView, int i, int i2, int i3) {
            AlertController.a(absListView, this.f230a, this.f231b);
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScrollStateChanged(AbsListView absListView, int i) {
        }
    }

    class e implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ View f232a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ View f233b;

        e(View view, View view2) {
            this.f232a = view;
            this.f233b = view2;
        }

        @Override // java.lang.Runnable
        public void run() {
            AlertController.a(AlertController.this.g, this.f232a, this.f233b);
        }
    }

    public static class f {
        public int A;
        public int B;
        public int C;
        public int D;
        public boolean[] F;
        public boolean G;
        public boolean H;
        public DialogInterface.OnMultiChoiceClickListener J;
        public Cursor K;
        public String L;
        public String M;
        public AdapterView.OnItemSelectedListener N;
        public e O;

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Context f235a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final LayoutInflater f236b;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public Drawable f238d;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public CharSequence f240f;
        public View g;
        public CharSequence h;
        public CharSequence i;
        public Drawable j;
        public DialogInterface.OnClickListener k;
        public CharSequence l;
        public Drawable m;
        public DialogInterface.OnClickListener n;
        public CharSequence o;
        public Drawable p;
        public DialogInterface.OnClickListener q;
        public DialogInterface.OnCancelListener s;
        public DialogInterface.OnDismissListener t;
        public DialogInterface.OnKeyListener u;
        public CharSequence[] v;
        public ListAdapter w;
        public DialogInterface.OnClickListener x;
        public int y;
        public View z;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f237c = 0;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f239e = 0;
        public boolean E = false;
        public int I = -1;
        public boolean r = true;

        class a extends ArrayAdapter<CharSequence> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ RecycleListView f241a;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            a(Context context, int i, int i2, CharSequence[] charSequenceArr, RecycleListView recycleListView) {
                super(context, i, i2, charSequenceArr);
                this.f241a = recycleListView;
            }

            @Override // android.widget.ArrayAdapter, android.widget.Adapter
            public View getView(int i, View view, ViewGroup viewGroup) {
                View view2 = super.getView(i, view, viewGroup);
                boolean[] zArr = f.this.F;
                if (zArr != null && zArr[i]) {
                    this.f241a.setItemChecked(i, true);
                }
                return view2;
            }
        }

        class b extends CursorAdapter {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            private final int f243a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            private final int f244b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            final /* synthetic */ RecycleListView f245c;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            final /* synthetic */ AlertController f246d;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            b(Context context, Cursor cursor, boolean z, RecycleListView recycleListView, AlertController alertController) {
                super(context, cursor, z);
                this.f245c = recycleListView;
                this.f246d = alertController;
                Cursor cursor2 = getCursor();
                this.f243a = cursor2.getColumnIndexOrThrow(f.this.L);
                this.f244b = cursor2.getColumnIndexOrThrow(f.this.M);
            }

            @Override // android.widget.CursorAdapter
            public void bindView(View view, Context context, Cursor cursor) {
                ((CheckedTextView) view.findViewById(R.id.text1)).setText(cursor.getString(this.f243a));
                this.f245c.setItemChecked(cursor.getPosition(), cursor.getInt(this.f244b) == 1);
            }

            @Override // android.widget.CursorAdapter
            public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
                return f.this.f236b.inflate(this.f246d.M, viewGroup, false);
            }
        }

        class c implements AdapterView.OnItemClickListener {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ AlertController f248a;

            c(AlertController alertController) {
                this.f248a = alertController;
            }

            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                f.this.x.onClick(this.f248a.f217b, i);
                if (f.this.H) {
                    return;
                }
                this.f248a.f217b.dismiss();
            }
        }

        class d implements AdapterView.OnItemClickListener {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ RecycleListView f250a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ AlertController f251b;

            d(RecycleListView recycleListView, AlertController alertController) {
                this.f250a = recycleListView;
                this.f251b = alertController;
            }

            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                boolean[] zArr = f.this.F;
                if (zArr != null) {
                    zArr[i] = this.f250a.isItemChecked(i);
                }
                f.this.J.onClick(this.f251b.f217b, i, this.f250a.isItemChecked(i));
            }
        }

        public interface e {
            void a(ListView listView);
        }

        public f(Context context) {
            this.f235a = context;
            this.f236b = (LayoutInflater) context.getSystemService("layout_inflater");
        }

        private void b(AlertController alertController) {
            ListAdapter hVar;
            RecycleListView recycleListView = (RecycleListView) this.f236b.inflate(alertController.L, (ViewGroup) null);
            if (this.G) {
                Cursor cursor = this.K;
                hVar = cursor == null ? new a(this.f235a, alertController.M, R.id.text1, this.v, recycleListView) : new b(this.f235a, cursor, false, recycleListView, alertController);
            } else {
                int i = this.H ? alertController.N : alertController.O;
                Cursor cursor2 = this.K;
                if (cursor2 != null) {
                    hVar = new SimpleCursorAdapter(this.f235a, i, cursor2, new String[]{this.L}, new int[]{R.id.text1});
                } else {
                    hVar = this.w;
                    if (hVar == null) {
                        hVar = new h(this.f235a, i, R.id.text1, this.v);
                    }
                }
            }
            e eVar = this.O;
            if (eVar != null) {
                eVar.a(recycleListView);
            }
            alertController.H = hVar;
            alertController.I = this.I;
            if (this.x != null) {
                recycleListView.setOnItemClickListener(new c(alertController));
            } else if (this.J != null) {
                recycleListView.setOnItemClickListener(new d(recycleListView, alertController));
            }
            AdapterView.OnItemSelectedListener onItemSelectedListener = this.N;
            if (onItemSelectedListener != null) {
                recycleListView.setOnItemSelectedListener(onItemSelectedListener);
            }
            if (this.H) {
                recycleListView.setChoiceMode(1);
            } else if (this.G) {
                recycleListView.setChoiceMode(2);
            }
            alertController.g = recycleListView;
        }

        public void a(AlertController alertController) {
            View view = this.g;
            if (view != null) {
                alertController.a(view);
            } else {
                CharSequence charSequence = this.f240f;
                if (charSequence != null) {
                    alertController.b(charSequence);
                }
                Drawable drawable = this.f238d;
                if (drawable != null) {
                    alertController.a(drawable);
                }
                int i = this.f237c;
                if (i != 0) {
                    alertController.b(i);
                }
                int i2 = this.f239e;
                if (i2 != 0) {
                    alertController.b(alertController.a(i2));
                }
            }
            CharSequence charSequence2 = this.h;
            if (charSequence2 != null) {
                alertController.a(charSequence2);
            }
            if (this.i != null || this.j != null) {
                alertController.a(-1, this.i, this.k, (Message) null, this.j);
            }
            if (this.l != null || this.m != null) {
                alertController.a(-2, this.l, this.n, (Message) null, this.m);
            }
            if (this.o != null || this.p != null) {
                alertController.a(-3, this.o, this.q, (Message) null, this.p);
            }
            if (this.v != null || this.K != null || this.w != null) {
                b(alertController);
            }
            View view2 = this.z;
            if (view2 != null) {
                if (this.E) {
                    alertController.a(view2, this.A, this.B, this.C, this.D);
                    return;
                } else {
                    alertController.b(view2);
                    return;
                }
            }
            int i3 = this.y;
            if (i3 != 0) {
                alertController.c(i3);
            }
        }
    }

    private static final class g extends Handler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private WeakReference<DialogInterface> f253a;

        public g(DialogInterface dialogInterface) {
            this.f253a = new WeakReference<>(dialogInterface);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i = message.what;
            if (i == -3 || i == -2 || i == -1) {
                ((DialogInterface.OnClickListener) message.obj).onClick(this.f253a.get(), message.what);
            } else {
                if (i != 1) {
                    return;
                }
                ((DialogInterface) message.obj).dismiss();
            }
        }
    }

    private static class h extends ArrayAdapter<CharSequence> {
        public h(Context context, int i, int i2, CharSequence[] charSequenceArr) {
            super(context, i, i2, charSequenceArr);
        }

        @Override // android.widget.ArrayAdapter, android.widget.Adapter
        public long getItemId(int i) {
            return i;
        }

        @Override // android.widget.BaseAdapter, android.widget.Adapter
        public boolean hasStableIds() {
            return true;
        }
    }

    public AlertController(Context context, androidx.appcompat.app.g gVar, Window window) {
        this.f216a = context;
        this.f217b = gVar;
        this.f218c = window;
        this.R = new g(gVar);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(null, R$styleable.AlertDialog, R$attr.alertDialogStyle, 0);
        this.J = typedArrayObtainStyledAttributes.getResourceId(R$styleable.AlertDialog_android_layout, 0);
        this.K = typedArrayObtainStyledAttributes.getResourceId(R$styleable.AlertDialog_buttonPanelSideLayout, 0);
        this.L = typedArrayObtainStyledAttributes.getResourceId(R$styleable.AlertDialog_listLayout, 0);
        this.M = typedArrayObtainStyledAttributes.getResourceId(R$styleable.AlertDialog_multiChoiceItemLayout, 0);
        this.N = typedArrayObtainStyledAttributes.getResourceId(R$styleable.AlertDialog_singleChoiceItemLayout, 0);
        this.O = typedArrayObtainStyledAttributes.getResourceId(R$styleable.AlertDialog_listItemLayout, 0);
        this.P = typedArrayObtainStyledAttributes.getBoolean(R$styleable.AlertDialog_showTitle, true);
        this.f219d = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.AlertDialog_buttonIconDimen, 0);
        typedArrayObtainStyledAttributes.recycle();
        gVar.a(1);
    }

    private static boolean a(Context context) {
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(R$attr.alertDialogCenterButtons, typedValue, true);
        return typedValue.data != 0;
    }

    static boolean c(View view) {
        if (view.onCheckIsTextEditor()) {
            return true;
        }
        if (!(view instanceof ViewGroup)) {
            return false;
        }
        ViewGroup viewGroup = (ViewGroup) view;
        int childCount = viewGroup.getChildCount();
        while (childCount > 0) {
            childCount--;
            if (c(viewGroup.getChildAt(childCount))) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void d() {
        View viewFindViewById;
        ListAdapter listAdapter;
        View viewFindViewById2;
        View viewFindViewById3 = this.f218c.findViewById(R$id.parentPanel);
        View viewFindViewById4 = viewFindViewById3.findViewById(R$id.topPanel);
        View viewFindViewById5 = viewFindViewById3.findViewById(R$id.contentPanel);
        View viewFindViewById6 = viewFindViewById3.findViewById(R$id.buttonPanel);
        ViewGroup viewGroup = (ViewGroup) viewFindViewById3.findViewById(R$id.customPanel);
        c(viewGroup);
        View viewFindViewById7 = viewGroup.findViewById(R$id.topPanel);
        View viewFindViewById8 = viewGroup.findViewById(R$id.contentPanel);
        View viewFindViewById9 = viewGroup.findViewById(R$id.buttonPanel);
        ViewGroup viewGroupA = a(viewFindViewById7, viewFindViewById4);
        ViewGroup viewGroupA2 = a(viewFindViewById8, viewFindViewById5);
        ViewGroup viewGroupA3 = a(viewFindViewById9, viewFindViewById6);
        b(viewGroupA2);
        a(viewGroupA3);
        d(viewGroupA);
        boolean z = (viewGroup == null || viewGroup.getVisibility() == 8) ? false : true;
        boolean z2 = (viewGroupA == null || viewGroupA.getVisibility() == 8) ? 0 : 1;
        boolean z3 = (viewGroupA3 == null || viewGroupA3.getVisibility() == 8) ? false : true;
        if (!z3 && viewGroupA2 != null && (viewFindViewById2 = viewGroupA2.findViewById(R$id.textSpacerNoButtons)) != null) {
            viewFindViewById2.setVisibility(0);
        }
        if (z2 != 0) {
            NestedScrollView nestedScrollView = this.A;
            if (nestedScrollView != null) {
                nestedScrollView.setClipToPadding(true);
            }
            View viewFindViewById10 = (this.f221f == null && this.g == null) ? null : viewGroupA.findViewById(R$id.titleDividerNoCustom);
            if (viewFindViewById10 != null) {
                viewFindViewById10.setVisibility(0);
            }
        } else if (viewGroupA2 != null && (viewFindViewById = viewGroupA2.findViewById(R$id.textSpacerNoTitle)) != null) {
            viewFindViewById.setVisibility(0);
        }
        ListView listView = this.g;
        if (listView instanceof RecycleListView) {
            ((RecycleListView) listView).a(z2, z3);
        }
        if (!z) {
            View view = this.g;
            if (view == null) {
                view = this.A;
            }
            if (view != null) {
                a(viewGroupA2, view, z2 | (z3 ? 2 : 0), 3);
            }
        }
        ListView listView2 = this.g;
        if (listView2 == null || (listAdapter = this.H) == null) {
            return;
        }
        listView2.setAdapter(listAdapter);
        int i = this.I;
        if (i > -1) {
            listView2.setItemChecked(i, true);
            listView2.setSelection(i);
        }
    }

    public void b() {
        this.f217b.setContentView(c());
        d();
    }

    public void a(View view) {
        this.G = view;
    }

    public void b(CharSequence charSequence) {
        this.f220e = charSequence;
        TextView textView = this.E;
        if (textView != null) {
            textView.setText(charSequence);
        }
    }

    public void a(CharSequence charSequence) {
        this.f221f = charSequence;
        TextView textView = this.F;
        if (textView != null) {
            textView.setText(charSequence);
        }
    }

    private int c() {
        int i = this.K;
        if (i == 0) {
            return this.J;
        }
        return this.Q == 1 ? i : this.J;
    }

    public void b(View view) {
        this.h = view;
        this.i = 0;
        this.n = false;
    }

    public void a(View view, int i, int i2, int i3, int i4) {
        this.h = view;
        this.i = 0;
        this.n = true;
        this.j = i;
        this.k = i2;
        this.l = i3;
        this.m = i4;
    }

    public void b(int i) {
        this.C = null;
        this.B = i;
        ImageView imageView = this.D;
        if (imageView != null) {
            if (i != 0) {
                imageView.setVisibility(0);
                this.D.setImageResource(this.B);
            } else {
                imageView.setVisibility(8);
            }
        }
    }

    public void c(int i) {
        this.h = null;
        this.i = i;
        this.n = false;
    }

    private void c(ViewGroup viewGroup) {
        View viewInflate = this.h;
        if (viewInflate == null) {
            viewInflate = this.i != 0 ? LayoutInflater.from(this.f216a).inflate(this.i, viewGroup, false) : null;
        }
        boolean z = viewInflate != null;
        if (!z || !c(viewInflate)) {
            this.f218c.setFlags(131072, 131072);
        }
        if (z) {
            FrameLayout frameLayout = (FrameLayout) this.f218c.findViewById(R$id.custom);
            frameLayout.addView(viewInflate, new ViewGroup.LayoutParams(-1, -1));
            if (this.n) {
                frameLayout.setPadding(this.j, this.k, this.l, this.m);
            }
            if (this.g != null) {
                ((LinearLayoutCompat.a) viewGroup.getLayoutParams()).f626a = 0.0f;
                return;
            }
            return;
        }
        viewGroup.setVisibility(8);
    }

    public void a(int i, CharSequence charSequence, DialogInterface.OnClickListener onClickListener, Message message, Drawable drawable) {
        if (message == null && onClickListener != null) {
            message = this.R.obtainMessage(i, onClickListener);
        }
        if (i == -3) {
            this.x = charSequence;
            this.y = message;
            this.z = drawable;
        } else if (i == -2) {
            this.t = charSequence;
            this.u = message;
            this.v = drawable;
        } else {
            if (i == -1) {
                this.p = charSequence;
                this.q = message;
                this.r = drawable;
                return;
            }
            throw new IllegalArgumentException("Button does not exist");
        }
    }

    public boolean b(int i, KeyEvent keyEvent) {
        NestedScrollView nestedScrollView = this.A;
        return nestedScrollView != null && nestedScrollView.a(keyEvent);
    }

    private void b(ViewGroup viewGroup) {
        this.A = (NestedScrollView) this.f218c.findViewById(R$id.scrollView);
        this.A.setFocusable(false);
        this.A.setNestedScrollingEnabled(false);
        this.F = (TextView) viewGroup.findViewById(R.id.message);
        TextView textView = this.F;
        if (textView == null) {
            return;
        }
        CharSequence charSequence = this.f221f;
        if (charSequence != null) {
            textView.setText(charSequence);
            return;
        }
        textView.setVisibility(8);
        this.A.removeView(this.F);
        if (this.g != null) {
            ViewGroup viewGroup2 = (ViewGroup) this.A.getParent();
            int iIndexOfChild = viewGroup2.indexOfChild(this.A);
            viewGroup2.removeViewAt(iIndexOfChild);
            viewGroup2.addView(this.g, iIndexOfChild, new ViewGroup.LayoutParams(-1, -1));
            return;
        }
        viewGroup.setVisibility(8);
    }

    public void a(Drawable drawable) {
        this.C = drawable;
        this.B = 0;
        ImageView imageView = this.D;
        if (imageView != null) {
            if (drawable != null) {
                imageView.setVisibility(0);
                this.D.setImageDrawable(drawable);
            } else {
                imageView.setVisibility(8);
            }
        }
    }

    public int a(int i) {
        TypedValue typedValue = new TypedValue();
        this.f216a.getTheme().resolveAttribute(i, typedValue, true);
        return typedValue.resourceId;
    }

    public ListView a() {
        return this.g;
    }

    public boolean a(int i, KeyEvent keyEvent) {
        NestedScrollView nestedScrollView = this.A;
        return nestedScrollView != null && nestedScrollView.a(keyEvent);
    }

    private ViewGroup a(View view, View view2) {
        if (view == null) {
            if (view2 instanceof ViewStub) {
                view2 = ((ViewStub) view2).inflate();
            }
            return (ViewGroup) view2;
        }
        if (view2 != null) {
            ViewParent parent = view2.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(view2);
            }
        }
        if (view instanceof ViewStub) {
            view = ((ViewStub) view).inflate();
        }
        return (ViewGroup) view;
    }

    private void d(ViewGroup viewGroup) {
        if (this.G != null) {
            viewGroup.addView(this.G, 0, new ViewGroup.LayoutParams(-1, -2));
            this.f218c.findViewById(R$id.title_template).setVisibility(8);
            return;
        }
        this.D = (ImageView) this.f218c.findViewById(R.id.icon);
        if ((!TextUtils.isEmpty(this.f220e)) && this.P) {
            this.E = (TextView) this.f218c.findViewById(R$id.alertTitle);
            this.E.setText(this.f220e);
            int i = this.B;
            if (i != 0) {
                this.D.setImageResource(i);
                return;
            }
            Drawable drawable = this.C;
            if (drawable != null) {
                this.D.setImageDrawable(drawable);
                return;
            } else {
                this.E.setPadding(this.D.getPaddingLeft(), this.D.getPaddingTop(), this.D.getPaddingRight(), this.D.getPaddingBottom());
                this.D.setVisibility(8);
                return;
            }
        }
        this.f218c.findViewById(R$id.title_template).setVisibility(8);
        this.D.setVisibility(8);
        viewGroup.setVisibility(8);
    }

    private void a(ViewGroup viewGroup, View view, int i, int i2) {
        View viewFindViewById = this.f218c.findViewById(R$id.scrollIndicatorUp);
        View viewFindViewById2 = this.f218c.findViewById(R$id.scrollIndicatorDown);
        if (Build.VERSION.SDK_INT >= 23) {
            t.a(view, i, i2);
            if (viewFindViewById != null) {
                viewGroup.removeView(viewFindViewById);
            }
            if (viewFindViewById2 != null) {
                viewGroup.removeView(viewFindViewById2);
                return;
            }
            return;
        }
        View view2 = null;
        if (viewFindViewById != null && (i & 1) == 0) {
            viewGroup.removeView(viewFindViewById);
            viewFindViewById = null;
        }
        if (viewFindViewById2 == null || (i & 2) != 0) {
            view2 = viewFindViewById2;
        } else {
            viewGroup.removeView(viewFindViewById2);
        }
        if (viewFindViewById == null && view2 == null) {
            return;
        }
        if (this.f221f != null) {
            this.A.setOnScrollChangeListener(new b(this, viewFindViewById, view2));
            this.A.post(new c(viewFindViewById, view2));
            return;
        }
        ListView listView = this.g;
        if (listView != null) {
            listView.setOnScrollListener(new d(this, viewFindViewById, view2));
            this.g.post(new e(viewFindViewById, view2));
            return;
        }
        if (viewFindViewById != null) {
            viewGroup.removeView(viewFindViewById);
        }
        if (view2 != null) {
            viewGroup.removeView(view2);
        }
    }

    static void a(View view, View view2, View view3) {
        if (view2 != null) {
            view2.setVisibility(view.canScrollVertically(-1) ? 0 : 4);
        }
        if (view3 != null) {
            view3.setVisibility(view.canScrollVertically(1) ? 0 : 4);
        }
    }

    private void a(ViewGroup viewGroup) {
        int i;
        this.o = (Button) viewGroup.findViewById(R.id.button1);
        this.o.setOnClickListener(this.S);
        if (TextUtils.isEmpty(this.p) && this.r == null) {
            this.o.setVisibility(8);
            i = 0;
        } else {
            this.o.setText(this.p);
            Drawable drawable = this.r;
            if (drawable != null) {
                int i2 = this.f219d;
                drawable.setBounds(0, 0, i2, i2);
                this.o.setCompoundDrawables(this.r, null, null, null);
            }
            this.o.setVisibility(0);
            i = 1;
        }
        this.s = (Button) viewGroup.findViewById(R.id.button2);
        this.s.setOnClickListener(this.S);
        if (TextUtils.isEmpty(this.t) && this.v == null) {
            this.s.setVisibility(8);
        } else {
            this.s.setText(this.t);
            Drawable drawable2 = this.v;
            if (drawable2 != null) {
                int i3 = this.f219d;
                drawable2.setBounds(0, 0, i3, i3);
                this.s.setCompoundDrawables(this.v, null, null, null);
            }
            this.s.setVisibility(0);
            i |= 2;
        }
        this.w = (Button) viewGroup.findViewById(R.id.button3);
        this.w.setOnClickListener(this.S);
        if (TextUtils.isEmpty(this.x) && this.z == null) {
            this.w.setVisibility(8);
        } else {
            this.w.setText(this.x);
            Drawable drawable3 = this.r;
            if (drawable3 != null) {
                int i4 = this.f219d;
                drawable3.setBounds(0, 0, i4, i4);
                this.o.setCompoundDrawables(this.r, null, null, null);
            }
            this.w.setVisibility(0);
            i |= 4;
        }
        if (a(this.f216a)) {
            if (i == 1) {
                a(this.o);
            } else if (i == 2) {
                a(this.s);
            } else if (i == 4) {
                a(this.w);
            }
        }
        if (i != 0) {
            return;
        }
        viewGroup.setVisibility(8);
    }

    private void a(Button button) {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) button.getLayoutParams();
        layoutParams.gravity = 1;
        layoutParams.weight = 0.5f;
        button.setLayoutParams(layoutParams);
    }
}
