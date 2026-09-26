package android.support.v17.leanback.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.v17.leanback.R;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/* JADX INFO: loaded from: classes.dex */
public class ImageCardView extends BaseCardView {
    public static final int CARD_TYPE_FLAG_CONTENT = 2;
    public static final int CARD_TYPE_FLAG_ICON_LEFT = 8;
    public static final int CARD_TYPE_FLAG_ICON_RIGHT = 4;
    public static final int CARD_TYPE_FLAG_IMAGE_ONLY = 0;
    public static final int CARD_TYPE_FLAG_TITLE = 1;
    private boolean mAttachedToWindow;
    private ImageView mBadgeImage;
    private TextView mContentView;
    private ImageView mImageView;
    private ViewGroup mInfoArea;
    private TextView mTitleView;

    @Override // android.view.View
    public boolean hasOverlappingRendering() {
        return false;
    }

    @Deprecated
    public ImageCardView(Context context, int i) {
        this(new ContextThemeWrapper(context, i));
    }

    public ImageCardView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        buildImageCardView(attributeSet, i, R.style.Widget_Leanback_ImageCardView);
    }

    private void buildImageCardView(AttributeSet attributeSet, int i, int i2) {
        setFocusable(true);
        setFocusableInTouchMode(true);
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(getContext());
        layoutInflaterFrom.inflate(R.layout.lb_image_card_view, this);
        TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.lbImageCardView, i, i2);
        int i3 = typedArrayObtainStyledAttributes.getInt(R.styleable.lbImageCardView_lbImageCardViewType, 0);
        boolean z = i3 == 0;
        boolean z2 = (i3 & 1) == 1;
        boolean z3 = (i3 & 2) == 2;
        boolean z4 = (i3 & 4) == 4;
        boolean z5 = !z4 && (i3 & 8) == 8;
        this.mImageView = (ImageView) findViewById(R.id.main_image);
        if (this.mImageView.getDrawable() == null) {
            this.mImageView.setVisibility(4);
        }
        this.mInfoArea = (ViewGroup) findViewById(R.id.info_field);
        if (z) {
            removeView(this.mInfoArea);
            typedArrayObtainStyledAttributes.recycle();
            return;
        }
        if (z2) {
            this.mTitleView = (TextView) layoutInflaterFrom.inflate(R.layout.lb_image_card_view_themed_title, this.mInfoArea, false);
            this.mInfoArea.addView(this.mTitleView);
        }
        if (z3) {
            this.mContentView = (TextView) layoutInflaterFrom.inflate(R.layout.lb_image_card_view_themed_content, this.mInfoArea, false);
            this.mInfoArea.addView(this.mContentView);
        }
        if (z4 || z5) {
            int i4 = R.layout.lb_image_card_view_themed_badge_right;
            if (z5) {
                i4 = R.layout.lb_image_card_view_themed_badge_left;
            }
            this.mBadgeImage = (ImageView) layoutInflaterFrom.inflate(i4, this.mInfoArea, false);
            this.mInfoArea.addView(this.mBadgeImage);
        }
        if (z2 && !z3 && this.mBadgeImage != null) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.mTitleView.getLayoutParams();
            if (z5) {
                layoutParams.addRule(17, this.mBadgeImage.getId());
            } else {
                layoutParams.addRule(16, this.mBadgeImage.getId());
            }
            this.mTitleView.setLayoutParams(layoutParams);
        }
        if (z3) {
            RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) this.mContentView.getLayoutParams();
            if (!z2) {
                layoutParams2.addRule(10);
            }
            if (z5) {
                layoutParams2.removeRule(16);
                layoutParams2.removeRule(20);
                layoutParams2.addRule(17, this.mBadgeImage.getId());
            }
            this.mContentView.setLayoutParams(layoutParams2);
        }
        if (this.mBadgeImage != null) {
            RelativeLayout.LayoutParams layoutParams3 = (RelativeLayout.LayoutParams) this.mBadgeImage.getLayoutParams();
            if (z3) {
                layoutParams3.addRule(8, this.mContentView.getId());
            } else if (z2) {
                layoutParams3.addRule(8, this.mTitleView.getId());
            }
            this.mBadgeImage.setLayoutParams(layoutParams3);
        }
        Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(R.styleable.lbImageCardView_infoAreaBackground);
        if (drawable != null) {
            setInfoAreaBackground(drawable);
        }
        if (this.mBadgeImage != null && this.mBadgeImage.getDrawable() == null) {
            this.mBadgeImage.setVisibility(8);
        }
        typedArrayObtainStyledAttributes.recycle();
    }

    public ImageCardView(Context context) {
        this(context, (AttributeSet) null);
    }

    public ImageCardView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.imageCardViewStyle);
    }

    public final ImageView getMainImageView() {
        return this.mImageView;
    }

    public void setMainImageAdjustViewBounds(boolean z) {
        if (this.mImageView != null) {
            this.mImageView.setAdjustViewBounds(z);
        }
    }

    public void setMainImageScaleType(ImageView.ScaleType scaleType) {
        if (this.mImageView != null) {
            this.mImageView.setScaleType(scaleType);
        }
    }

    public void setMainImage(Drawable drawable) {
        setMainImage(drawable, true);
    }

    public void setMainImage(Drawable drawable, boolean z) {
        if (this.mImageView == null) {
            return;
        }
        this.mImageView.setImageDrawable(drawable);
        if (drawable == null) {
            this.mImageView.animate().cancel();
            this.mImageView.setAlpha(1.0f);
            this.mImageView.setVisibility(4);
        } else {
            this.mImageView.setVisibility(0);
            if (z) {
                fadeIn();
            } else {
                this.mImageView.animate().cancel();
                this.mImageView.setAlpha(1.0f);
            }
        }
    }

    public void setMainImageDimensions(int i, int i2) {
        ViewGroup.LayoutParams layoutParams = this.mImageView.getLayoutParams();
        layoutParams.width = i;
        layoutParams.height = i2;
        this.mImageView.setLayoutParams(layoutParams);
    }

    public Drawable getMainImage() {
        if (this.mImageView == null) {
            return null;
        }
        return this.mImageView.getDrawable();
    }

    public Drawable getInfoAreaBackground() {
        if (this.mInfoArea != null) {
            return this.mInfoArea.getBackground();
        }
        return null;
    }

    public void setInfoAreaBackground(Drawable drawable) {
        if (this.mInfoArea != null) {
            this.mInfoArea.setBackground(drawable);
        }
    }

    public void setInfoAreaBackgroundColor(@ColorInt int i) {
        if (this.mInfoArea != null) {
            this.mInfoArea.setBackgroundColor(i);
        }
    }

    public void setTitleText(CharSequence charSequence) {
        if (this.mTitleView == null) {
            return;
        }
        this.mTitleView.setText(charSequence);
    }

    public CharSequence getTitleText() {
        if (this.mTitleView == null) {
            return null;
        }
        return this.mTitleView.getText();
    }

    public void setContentText(CharSequence charSequence) {
        if (this.mContentView == null) {
            return;
        }
        this.mContentView.setText(charSequence);
    }

    public CharSequence getContentText() {
        if (this.mContentView == null) {
            return null;
        }
        return this.mContentView.getText();
    }

    public void setBadgeImage(Drawable drawable) {
        if (this.mBadgeImage == null) {
            return;
        }
        this.mBadgeImage.setImageDrawable(drawable);
        if (drawable != null) {
            this.mBadgeImage.setVisibility(0);
        } else {
            this.mBadgeImage.setVisibility(8);
        }
    }

    public Drawable getBadgeImage() {
        if (this.mBadgeImage == null) {
            return null;
        }
        return this.mBadgeImage.getDrawable();
    }

    private void fadeIn() {
        this.mImageView.setAlpha(0.0f);
        if (this.mAttachedToWindow) {
            this.mImageView.animate().alpha(1.0f).setDuration(this.mImageView.getResources().getInteger(android.R.integer.config_shortAnimTime));
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mAttachedToWindow = true;
        if (this.mImageView.getAlpha() == 0.0f) {
            fadeIn();
        }
    }

    @Override // android.support.v17.leanback.widget.BaseCardView, android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        this.mAttachedToWindow = false;
        this.mImageView.animate().cancel();
        this.mImageView.setAlpha(1.0f);
        super.onDetachedFromWindow();
    }
}
