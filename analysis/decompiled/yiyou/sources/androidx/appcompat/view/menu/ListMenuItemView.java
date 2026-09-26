package androidx.appcompat.view.menu;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import androidx.appcompat.R$attr;
import androidx.appcompat.R$id;
import androidx.appcompat.R$layout;
import androidx.appcompat.R$styleable;
import androidx.appcompat.widget.d0;
import androidx.core.f.t;

/* JADX INFO: loaded from: classes.dex */
public class ListMenuItemView extends LinearLayout implements n.a, AbsListView.SelectionBoundsAdjuster {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private j f407a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private ImageView f408b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private RadioButton f409c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private TextView f410d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private CheckBox f411e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private TextView f412f;
    private ImageView g;
    private ImageView h;
    private LinearLayout i;
    private Drawable j;
    private int k;
    private Context l;
    private boolean m;
    private Drawable n;
    private boolean o;
    private LayoutInflater p;
    private boolean q;

    public ListMenuItemView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.listMenuViewStyle);
    }

    private void b() {
        this.f408b = (ImageView) getInflater().inflate(R$layout.abc_list_menu_item_icon, (ViewGroup) this, false);
        a(this.f408b, 0);
    }

    private void d() {
        this.f409c = (RadioButton) getInflater().inflate(R$layout.abc_list_menu_item_radio, (ViewGroup) this, false);
        a(this.f409c);
    }

    private LayoutInflater getInflater() {
        if (this.p == null) {
            this.p = LayoutInflater.from(getContext());
        }
        return this.p;
    }

    private void setSubMenuArrowVisible(boolean z) {
        ImageView imageView = this.g;
        if (imageView != null) {
            imageView.setVisibility(z ? 0 : 8);
        }
    }

    @Override // androidx.appcompat.view.menu.n.a
    public void a(j jVar, int i) {
        this.f407a = jVar;
        setVisibility(jVar.isVisible() ? 0 : 8);
        setTitle(jVar.a(this));
        setCheckable(jVar.isCheckable());
        a(jVar.m(), jVar.d());
        setIcon(jVar.getIcon());
        setEnabled(jVar.isEnabled());
        setSubMenuArrowVisible(jVar.hasSubMenu());
        setContentDescription(jVar.getContentDescription());
    }

    @Override // android.widget.AbsListView.SelectionBoundsAdjuster
    public void adjustListItemSelectionBounds(Rect rect) {
        ImageView imageView = this.h;
        if (imageView == null || imageView.getVisibility() != 0) {
            return;
        }
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.h.getLayoutParams();
        rect.top += this.h.getHeight() + layoutParams.topMargin + layoutParams.bottomMargin;
    }

    @Override // androidx.appcompat.view.menu.n.a
    public boolean c() {
        return false;
    }

    @Override // androidx.appcompat.view.menu.n.a
    public j getItemData() {
        return this.f407a;
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        t.a(this, this.j);
        this.f410d = (TextView) findViewById(R$id.title);
        int i = this.k;
        if (i != -1) {
            this.f410d.setTextAppearance(this.l, i);
        }
        this.f412f = (TextView) findViewById(R$id.shortcut);
        this.g = (ImageView) findViewById(R$id.submenuarrow);
        ImageView imageView = this.g;
        if (imageView != null) {
            imageView.setImageDrawable(this.n);
        }
        this.h = (ImageView) findViewById(R$id.group_divider);
        this.i = (LinearLayout) findViewById(R$id.content);
    }

    @Override // android.widget.LinearLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        if (this.f408b != null && this.m) {
            ViewGroup.LayoutParams layoutParams = getLayoutParams();
            LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) this.f408b.getLayoutParams();
            if (layoutParams.height > 0 && layoutParams2.width <= 0) {
                layoutParams2.width = layoutParams.height;
            }
        }
        super.onMeasure(i, i2);
    }

    public void setCheckable(boolean z) {
        CompoundButton compoundButton;
        CompoundButton compoundButton2;
        if (!z && this.f409c == null && this.f411e == null) {
            return;
        }
        if (this.f407a.i()) {
            if (this.f409c == null) {
                d();
            }
            compoundButton = this.f409c;
            compoundButton2 = this.f411e;
        } else {
            if (this.f411e == null) {
                a();
            }
            compoundButton = this.f411e;
            compoundButton2 = this.f409c;
        }
        if (z) {
            compoundButton.setChecked(this.f407a.isChecked());
            if (compoundButton.getVisibility() != 0) {
                compoundButton.setVisibility(0);
            }
            if (compoundButton2 == null || compoundButton2.getVisibility() == 8) {
                return;
            }
            compoundButton2.setVisibility(8);
            return;
        }
        CheckBox checkBox = this.f411e;
        if (checkBox != null) {
            checkBox.setVisibility(8);
        }
        RadioButton radioButton = this.f409c;
        if (radioButton != null) {
            radioButton.setVisibility(8);
        }
    }

    public void setChecked(boolean z) {
        CompoundButton compoundButton;
        if (this.f407a.i()) {
            if (this.f409c == null) {
                d();
            }
            compoundButton = this.f409c;
        } else {
            if (this.f411e == null) {
                a();
            }
            compoundButton = this.f411e;
        }
        compoundButton.setChecked(z);
    }

    public void setForceShowIcon(boolean z) {
        this.q = z;
        this.m = z;
    }

    public void setGroupDividerEnabled(boolean z) {
        ImageView imageView = this.h;
        if (imageView != null) {
            imageView.setVisibility((this.o || !z) ? 8 : 0);
        }
    }

    public void setIcon(Drawable drawable) {
        boolean z = this.f407a.l() || this.q;
        if (z || this.m) {
            if (this.f408b == null && drawable == null && !this.m) {
                return;
            }
            if (this.f408b == null) {
                b();
            }
            if (drawable == null && !this.m) {
                this.f408b.setVisibility(8);
                return;
            }
            ImageView imageView = this.f408b;
            if (!z) {
                drawable = null;
            }
            imageView.setImageDrawable(drawable);
            if (this.f408b.getVisibility() != 0) {
                this.f408b.setVisibility(0);
            }
        }
    }

    public void setTitle(CharSequence charSequence) {
        if (charSequence == null) {
            if (this.f410d.getVisibility() != 8) {
                this.f410d.setVisibility(8);
            }
        } else {
            this.f410d.setText(charSequence);
            if (this.f410d.getVisibility() != 0) {
                this.f410d.setVisibility(0);
            }
        }
    }

    public ListMenuItemView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet);
        d0 d0VarA = d0.a(getContext(), attributeSet, R$styleable.MenuView, i, 0);
        this.j = d0VarA.b(R$styleable.MenuView_android_itemBackground);
        this.k = d0VarA.g(R$styleable.MenuView_android_itemTextAppearance, -1);
        this.m = d0VarA.a(R$styleable.MenuView_preserveIconSpacing, false);
        this.l = context;
        this.n = d0VarA.b(R$styleable.MenuView_subMenuArrow);
        TypedArray typedArrayObtainStyledAttributes = context.getTheme().obtainStyledAttributes(null, new int[]{R.attr.divider}, R$attr.dropDownListViewStyle, 0);
        this.o = typedArrayObtainStyledAttributes.hasValue(0);
        d0VarA.a();
        typedArrayObtainStyledAttributes.recycle();
    }

    private void a(View view) {
        a(view, -1);
    }

    private void a(View view, int i) {
        LinearLayout linearLayout = this.i;
        if (linearLayout != null) {
            linearLayout.addView(view, i);
        } else {
            addView(view, i);
        }
    }

    public void a(boolean z, char c2) {
        int i = (z && this.f407a.m()) ? 0 : 8;
        if (i == 0) {
            this.f412f.setText(this.f407a.e());
        }
        if (this.f412f.getVisibility() != i) {
            this.f412f.setVisibility(i);
        }
    }

    private void a() {
        this.f411e = (CheckBox) getInflater().inflate(R$layout.abc_list_menu_item_checkbox, (ViewGroup) this, false);
        a(this.f411e);
    }
}
