package androidx.constraintlayout.widget;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public class Group extends ConstraintHelper {
    public Group(Context context) {
        super(context);
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    protected void a(AttributeSet attributeSet) {
        super.a(attributeSet);
        this.f855e = false;
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public void c(ConstraintLayout constraintLayout) {
        int visibility = getVisibility();
        float elevation = Build.VERSION.SDK_INT >= 21 ? getElevation() : 0.0f;
        for (int i = 0; i < this.f852b; i++) {
            View viewA = constraintLayout.a(this.f851a[i]);
            if (viewA != null) {
                viewA.setVisibility(visibility);
                if (elevation > 0.0f && Build.VERSION.SDK_INT >= 21) {
                    viewA.setElevation(elevation);
                }
            }
        }
    }

    public Group(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public Group(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public void a(ConstraintLayout constraintLayout) {
        ConstraintLayout.a aVar = (ConstraintLayout.a) getLayoutParams();
        aVar.k0.o(0);
        aVar.k0.g(0);
    }
}
