package androidx.constraintlayout.widget;

import a.c.a.j.b;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;

/* JADX INFO: loaded from: classes.dex */
public class Barrier extends ConstraintHelper {
    private int g;
    private int h;
    private b i;

    public Barrier(Context context) {
        super(context);
        super.setVisibility(8);
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    protected void a(AttributeSet attributeSet) {
        super.a(attributeSet);
        this.i = new b();
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R$styleable.ConstraintLayout_Layout);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                if (index == R$styleable.ConstraintLayout_Layout_barrierDirection) {
                    setType(typedArrayObtainStyledAttributes.getInt(index, 0));
                } else if (index == R$styleable.ConstraintLayout_Layout_barrierAllowsGoneWidgets) {
                    this.i.c(typedArrayObtainStyledAttributes.getBoolean(index, true));
                }
            }
        }
        this.f854d = this.i;
        a();
    }

    public int getType() {
        return this.g;
    }

    public void setAllowsGoneWidget(boolean z) {
        this.i.c(z);
    }

    public void setType(int i) {
        this.g = i;
        this.h = i;
        if (Build.VERSION.SDK_INT < 17) {
            int i2 = this.g;
            if (i2 == 5) {
                this.h = 0;
            } else if (i2 == 6) {
                this.h = 1;
            }
        } else {
            if (1 == getResources().getConfiguration().getLayoutDirection()) {
                int i3 = this.g;
                if (i3 == 5) {
                    this.h = 1;
                } else if (i3 == 6) {
                    this.h = 0;
                }
            } else {
                int i4 = this.g;
                if (i4 == 5) {
                    this.h = 0;
                } else if (i4 == 6) {
                    this.h = 1;
                }
            }
        }
        this.i.t(this.h);
    }

    public Barrier(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        super.setVisibility(8);
    }

    public Barrier(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        super.setVisibility(8);
    }
}
