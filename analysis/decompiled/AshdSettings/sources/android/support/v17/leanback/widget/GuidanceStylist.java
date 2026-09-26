package android.support.v17.leanback.widget;

import android.animation.Animator;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v17.leanback.R;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class GuidanceStylist implements FragmentAnimationProvider {
    private TextView mBreadcrumbView;
    private TextView mDescriptionView;
    private View mGuidanceContainer;
    private ImageView mIconView;
    private TextView mTitleView;

    @Override // android.support.v17.leanback.widget.FragmentAnimationProvider
    public void onImeAppearing(@NonNull List<Animator> list) {
    }

    @Override // android.support.v17.leanback.widget.FragmentAnimationProvider
    public void onImeDisappearing(@NonNull List<Animator> list) {
    }

    public static class Guidance {
        private final String mBreadcrumb;
        private final String mDescription;
        private final Drawable mIconDrawable;
        private final String mTitle;

        public Guidance(String str, String str2, String str3, Drawable drawable) {
            this.mBreadcrumb = str3;
            this.mTitle = str;
            this.mDescription = str2;
            this.mIconDrawable = drawable;
        }

        public String getTitle() {
            return this.mTitle;
        }

        public String getDescription() {
            return this.mDescription;
        }

        public String getBreadcrumb() {
            return this.mBreadcrumb;
        }

        public Drawable getIconDrawable() {
            return this.mIconDrawable;
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Guidance guidance) {
        View viewInflate = layoutInflater.inflate(onProvideLayoutId(), viewGroup, false);
        this.mTitleView = (TextView) viewInflate.findViewById(R.id.guidance_title);
        this.mBreadcrumbView = (TextView) viewInflate.findViewById(R.id.guidance_breadcrumb);
        this.mDescriptionView = (TextView) viewInflate.findViewById(R.id.guidance_description);
        this.mIconView = (ImageView) viewInflate.findViewById(R.id.guidance_icon);
        this.mGuidanceContainer = viewInflate.findViewById(R.id.guidance_container);
        if (this.mTitleView != null) {
            this.mTitleView.setText(guidance.getTitle());
        }
        if (this.mBreadcrumbView != null) {
            this.mBreadcrumbView.setText(guidance.getBreadcrumb());
        }
        if (this.mDescriptionView != null) {
            this.mDescriptionView.setText(guidance.getDescription());
        }
        if (this.mIconView != null) {
            if (guidance.getIconDrawable() != null) {
                this.mIconView.setImageDrawable(guidance.getIconDrawable());
            } else {
                this.mIconView.setVisibility(8);
            }
        }
        if (this.mGuidanceContainer != null && TextUtils.isEmpty(this.mGuidanceContainer.getContentDescription())) {
            StringBuilder sb = new StringBuilder();
            if (!TextUtils.isEmpty(guidance.getBreadcrumb())) {
                sb.append(guidance.getBreadcrumb());
                sb.append('\n');
            }
            if (!TextUtils.isEmpty(guidance.getTitle())) {
                sb.append(guidance.getTitle());
                sb.append('\n');
            }
            if (!TextUtils.isEmpty(guidance.getDescription())) {
                sb.append(guidance.getDescription());
                sb.append('\n');
            }
            this.mGuidanceContainer.setContentDescription(sb);
        }
        return viewInflate;
    }

    public void onDestroyView() {
        this.mBreadcrumbView = null;
        this.mDescriptionView = null;
        this.mIconView = null;
        this.mTitleView = null;
    }

    public int onProvideLayoutId() {
        return R.layout.lb_guidance;
    }

    public TextView getTitleView() {
        return this.mTitleView;
    }

    public TextView getDescriptionView() {
        return this.mDescriptionView;
    }

    public TextView getBreadcrumbView() {
        return this.mBreadcrumbView;
    }

    public ImageView getIconView() {
        return this.mIconView;
    }
}
