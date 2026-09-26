package android.support.v17.leanback.widget;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/* JADX INFO: loaded from: classes.dex */
class ItemAlignmentFacetHelper {
    private static Rect sRect = new Rect();

    ItemAlignmentFacetHelper() {
    }

    static int getAlignmentPosition(View view, ItemAlignmentFacet.ItemAlignmentDef itemAlignmentDef, int i) {
        View viewFindViewById;
        GridLayoutManager.LayoutParams layoutParams = (GridLayoutManager.LayoutParams) view.getLayoutParams();
        if (itemAlignmentDef.mViewId == 0 || (viewFindViewById = view.findViewById(itemAlignmentDef.mViewId)) == null) {
            viewFindViewById = view;
        }
        int paddingBottom = itemAlignmentDef.mOffset;
        if (i == 0) {
            if (itemAlignmentDef.mOffset >= 0) {
                if (itemAlignmentDef.mOffsetWithPadding) {
                    paddingBottom += viewFindViewById.getPaddingLeft();
                }
            } else if (itemAlignmentDef.mOffsetWithPadding) {
                paddingBottom -= viewFindViewById.getPaddingRight();
            }
            if (itemAlignmentDef.mOffsetPercent != -1.0f) {
                paddingBottom = (int) (paddingBottom + (((viewFindViewById == view ? layoutParams.getOpticalWidth(viewFindViewById) : viewFindViewById.getWidth()) * itemAlignmentDef.mOffsetPercent) / 100.0f));
            }
            if (view == viewFindViewById) {
                return paddingBottom;
            }
            sRect.left = paddingBottom;
            ((ViewGroup) view).offsetDescendantRectToMyCoords(viewFindViewById, sRect);
            return sRect.left - layoutParams.getOpticalLeftInset();
        }
        if (itemAlignmentDef.mOffset >= 0) {
            if (itemAlignmentDef.mOffsetWithPadding) {
                paddingBottom += viewFindViewById.getPaddingTop();
            }
        } else if (itemAlignmentDef.mOffsetWithPadding) {
            paddingBottom -= viewFindViewById.getPaddingBottom();
        }
        if (itemAlignmentDef.mOffsetPercent != -1.0f) {
            paddingBottom = (int) (paddingBottom + (((viewFindViewById == view ? layoutParams.getOpticalHeight(viewFindViewById) : viewFindViewById.getHeight()) * itemAlignmentDef.mOffsetPercent) / 100.0f));
        }
        if (view != viewFindViewById) {
            sRect.top = paddingBottom;
            ((ViewGroup) view).offsetDescendantRectToMyCoords(viewFindViewById, sRect);
            paddingBottom = sRect.top - layoutParams.getOpticalTopInset();
        }
        return ((viewFindViewById instanceof TextView) && itemAlignmentDef.isAlignedToTextViewBaseLine()) ? paddingBottom + (-((TextView) viewFindViewById).getPaint().getFontMetricsInt().top) : paddingBottom;
    }
}
