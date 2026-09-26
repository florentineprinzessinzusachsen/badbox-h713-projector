package androidx.constraintlayout.widget;

import a.c.a.j.j;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public abstract class ConstraintHelper extends View {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected int[] f851a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected int f852b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    protected Context f853c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    protected j f854d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    protected boolean f855e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private String f856f;

    public ConstraintHelper(Context context) {
        super(context);
        this.f851a = new int[32];
        this.f855e = false;
        this.f853c = context;
        a((AttributeSet) null);
    }

    private void setIds(String str) {
        if (str == null) {
            return;
        }
        int i = 0;
        while (true) {
            int iIndexOf = str.indexOf(44, i);
            if (iIndexOf == -1) {
                a(str.substring(i));
                return;
            } else {
                a(str.substring(i, iIndexOf));
                i = iIndexOf + 1;
            }
        }
    }

    protected void a(AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R$styleable.ConstraintLayout_Layout);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                if (index == R$styleable.ConstraintLayout_Layout_constraint_referenced_ids) {
                    this.f856f = typedArrayObtainStyledAttributes.getString(index);
                    setIds(this.f856f);
                }
            }
        }
    }

    public void a(ConstraintLayout constraintLayout) {
    }

    public void b(ConstraintLayout constraintLayout) {
    }

    public void c(ConstraintLayout constraintLayout) {
        if (isInEditMode()) {
            setIds(this.f856f);
        }
        j jVar = this.f854d;
        if (jVar == null) {
            return;
        }
        jVar.J();
        for (int i = 0; i < this.f852b; i++) {
            View viewA = constraintLayout.a(this.f851a[i]);
            if (viewA != null) {
                this.f854d.b(constraintLayout.a(viewA));
            }
        }
    }

    public int[] getReferencedIds() {
        return Arrays.copyOf(this.f851a, this.f852b);
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        if (this.f855e) {
            super.onMeasure(i, i2);
        } else {
            setMeasuredDimension(0, 0);
        }
    }

    public void setReferencedIds(int[] iArr) {
        this.f852b = 0;
        for (int i : iArr) {
            setTag(i, null);
        }
    }

    @Override // android.view.View
    public void setTag(int i, Object obj) {
        int i2 = this.f852b + 1;
        int[] iArr = this.f851a;
        if (i2 > iArr.length) {
            this.f851a = Arrays.copyOf(iArr, iArr.length * 2);
        }
        int[] iArr2 = this.f851a;
        int i3 = this.f852b;
        iArr2[i3] = i;
        this.f852b = i3 + 1;
    }

    public ConstraintHelper(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f851a = new int[32];
        this.f855e = false;
        this.f853c = context;
        a(attributeSet);
    }

    public void a() {
        if (this.f854d == null) {
            return;
        }
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        if (layoutParams instanceof ConstraintLayout.a) {
            ((ConstraintLayout.a) layoutParams).k0 = this.f854d;
        }
    }

    public ConstraintHelper(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f851a = new int[32];
        this.f855e = false;
        this.f853c = context;
        a(attributeSet);
    }

    private void a(String str) {
        int iIntValue;
        Object objA;
        if (str == null || this.f853c == null) {
            return;
        }
        String strTrim = str.trim();
        try {
            iIntValue = R$id.class.getField(strTrim).getInt(null);
        } catch (Exception unused) {
            iIntValue = 0;
        }
        if (iIntValue == 0) {
            iIntValue = this.f853c.getResources().getIdentifier(strTrim, "id", this.f853c.getPackageName());
        }
        if (iIntValue == 0 && isInEditMode() && (getParent() instanceof ConstraintLayout) && (objA = ((ConstraintLayout) getParent()).a(0, strTrim)) != null && (objA instanceof Integer)) {
            iIntValue = ((Integer) objA).intValue();
        }
        if (iIntValue != 0) {
            setTag(iIntValue, null);
            return;
        }
        Log.w("ConstraintHelper", "Could not find id of \"" + strTrim + "\"");
    }
}
