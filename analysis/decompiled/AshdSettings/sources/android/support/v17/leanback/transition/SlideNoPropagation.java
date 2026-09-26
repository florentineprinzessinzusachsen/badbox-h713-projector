package android.support.v17.leanback.transition;

import android.annotation.TargetApi;
import android.content.Context;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.transition.Slide;
import android.util.AttributeSet;

/* JADX INFO: loaded from: classes.dex */
@RequiresApi(21)
@TargetApi(21)
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class SlideNoPropagation extends Slide {
    public SlideNoPropagation() {
    }

    public SlideNoPropagation(int i) {
        super(i);
    }

    public SlideNoPropagation(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // android.transition.Slide
    public void setSlideEdge(int i) {
        super.setSlideEdge(i);
        setPropagation(null);
    }
}
