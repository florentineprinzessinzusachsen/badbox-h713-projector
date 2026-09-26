package androidx.constraintlayout.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;

/* JADX INFO: loaded from: classes.dex */
public class Constraints extends ViewGroup {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    androidx.constraintlayout.widget.a f870a;

    public Constraints(Context context) {
        super(context);
        super.setVisibility(8);
    }

    private void a(AttributeSet attributeSet) {
        Log.v("Constraints", " ################# init");
    }

    public androidx.constraintlayout.widget.a getConstraintSet() {
        if (this.f870a == null) {
            this.f870a = new androidx.constraintlayout.widget.a();
        }
        this.f870a.a(this);
        return this.f870a;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.ViewGroup
    public a generateDefaultLayoutParams() {
        return new a(-2, -2);
    }

    @Override // android.view.ViewGroup
    public a generateLayoutParams(AttributeSet attributeSet) {
        return new a(getContext(), attributeSet);
    }

    public Constraints(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(attributeSet);
        super.setVisibility(8);
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new ConstraintLayout.a(layoutParams);
    }

    public Constraints(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(attributeSet);
        super.setVisibility(8);
    }

    public static class a extends ConstraintLayout.a {
        public float m0;
        public boolean n0;
        public float o0;
        public float p0;
        public float q0;
        public float r0;
        public float s0;
        public float t0;
        public float u0;
        public float v0;
        public float w0;
        public float x0;
        public float y0;

        public a(int i, int i2) {
            super(i, i2);
            this.m0 = 1.0f;
            this.n0 = false;
            this.o0 = 0.0f;
            this.p0 = 0.0f;
            this.q0 = 0.0f;
            this.r0 = 0.0f;
            this.s0 = 1.0f;
            this.t0 = 1.0f;
            this.u0 = 0.0f;
            this.v0 = 0.0f;
            this.w0 = 0.0f;
            this.x0 = 0.0f;
            this.y0 = 0.0f;
        }

        public a(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.m0 = 1.0f;
            this.n0 = false;
            this.o0 = 0.0f;
            this.p0 = 0.0f;
            this.q0 = 0.0f;
            this.r0 = 0.0f;
            this.s0 = 1.0f;
            this.t0 = 1.0f;
            this.u0 = 0.0f;
            this.v0 = 0.0f;
            this.w0 = 0.0f;
            this.x0 = 0.0f;
            this.y0 = 0.0f;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.ConstraintSet);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                if (index == R$styleable.ConstraintSet_android_alpha) {
                    this.m0 = typedArrayObtainStyledAttributes.getFloat(index, this.m0);
                } else if (index == R$styleable.ConstraintSet_android_elevation) {
                    this.o0 = typedArrayObtainStyledAttributes.getFloat(index, this.o0);
                    this.n0 = true;
                } else if (index == R$styleable.ConstraintSet_android_rotationX) {
                    this.q0 = typedArrayObtainStyledAttributes.getFloat(index, this.q0);
                } else if (index == R$styleable.ConstraintSet_android_rotationY) {
                    this.r0 = typedArrayObtainStyledAttributes.getFloat(index, this.r0);
                } else if (index == R$styleable.ConstraintSet_android_rotation) {
                    this.p0 = typedArrayObtainStyledAttributes.getFloat(index, this.p0);
                } else if (index == R$styleable.ConstraintSet_android_scaleX) {
                    this.s0 = typedArrayObtainStyledAttributes.getFloat(index, this.s0);
                } else if (index == R$styleable.ConstraintSet_android_scaleY) {
                    this.t0 = typedArrayObtainStyledAttributes.getFloat(index, this.t0);
                } else if (index == R$styleable.ConstraintSet_android_transformPivotX) {
                    this.u0 = typedArrayObtainStyledAttributes.getFloat(index, this.u0);
                } else if (index == R$styleable.ConstraintSet_android_transformPivotY) {
                    this.v0 = typedArrayObtainStyledAttributes.getFloat(index, this.v0);
                } else if (index == R$styleable.ConstraintSet_android_translationX) {
                    this.w0 = typedArrayObtainStyledAttributes.getFloat(index, this.w0);
                } else if (index == R$styleable.ConstraintSet_android_translationY) {
                    this.x0 = typedArrayObtainStyledAttributes.getFloat(index, this.x0);
                } else if (index == R$styleable.ConstraintSet_android_translationZ) {
                    this.w0 = typedArrayObtainStyledAttributes.getFloat(index, this.y0);
                }
            }
        }
    }
}
