package android.support.v17.leanback.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v17.leanback.R;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/* JADX INFO: loaded from: classes.dex */
class GuidanceStylingRelativeLayout extends RelativeLayout {
    private float mTitleKeylinePercent;

    public GuidanceStylingRelativeLayout(Context context) {
        this(context, null);
    }

    public GuidanceStylingRelativeLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public GuidanceStylingRelativeLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mTitleKeylinePercent = getKeyLinePercent(context);
    }

    public static float getKeyLinePercent(Context context) {
        TypedArray typedArrayObtainStyledAttributes = context.getTheme().obtainStyledAttributes(R.styleable.LeanbackGuidedStepTheme);
        float f = typedArrayObtainStyledAttributes.getFloat(R.styleable.LeanbackGuidedStepTheme_guidedStepKeyline, 40.0f);
        typedArrayObtainStyledAttributes.recycle();
        return f;
    }

    @Override // android.widget.RelativeLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        TextView textView = (TextView) getRootView().findViewById(R.id.guidance_title);
        TextView textView2 = (TextView) getRootView().findViewById(R.id.guidance_breadcrumb);
        TextView textView3 = (TextView) getRootView().findViewById(R.id.guidance_description);
        ImageView imageView = (ImageView) getRootView().findViewById(R.id.guidance_icon);
        int measuredHeight = (int) ((getMeasuredHeight() * this.mTitleKeylinePercent) / 100.0f);
        if (textView != null && textView.getParent() == this) {
            int measuredHeight2 = (((measuredHeight - (-textView.getPaint().getFontMetricsInt().top)) - textView2.getMeasuredHeight()) - textView.getPaddingTop()) - textView2.getTop();
            if (textView2 != null && textView2.getParent() == this) {
                textView2.offsetTopAndBottom(measuredHeight2);
            }
            textView.offsetTopAndBottom(measuredHeight2);
            if (textView3 != null && textView3.getParent() == this) {
                textView3.offsetTopAndBottom(measuredHeight2);
            }
        }
        if (imageView == null || imageView.getParent() != this || imageView.getDrawable() == null) {
            return;
        }
        imageView.offsetTopAndBottom(measuredHeight - (imageView.getMeasuredHeight() / 2));
    }
}
