package android.support.v17.leanback.widget;

import android.support.v17.leanback.R;

/* JADX INFO: loaded from: classes.dex */
public class DetailsParallax extends RecyclerViewParallax {
    final Parallax.IntProperty mFrameTop = addProperty("overviewRowTop").adapterPosition(0).viewId(R.id.details_frame);
    final Parallax.IntProperty mFrameBottom = addProperty("overviewRowBottom").adapterPosition(0).viewId(R.id.details_frame).fraction(1.0f);

    public Parallax.IntProperty getOverviewRowTop() {
        return this.mFrameTop;
    }

    public Parallax.IntProperty getOverviewRowBottom() {
        return this.mFrameBottom;
    }
}
