package android.support.v17.leanback.app;

import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v17.leanback.R;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/* JADX INFO: loaded from: classes.dex */
public class ErrorSupportFragment extends BrandedSupportFragment {
    private Drawable mBackgroundDrawable;
    private Button mButton;
    private View.OnClickListener mButtonClickListener;
    private String mButtonText;
    private Drawable mDrawable;
    private ViewGroup mErrorFrame;
    private ImageView mImageView;
    private boolean mIsBackgroundTranslucent = true;
    private CharSequence mMessage;
    private TextView mTextView;

    public void setDefaultBackground(boolean z) {
        this.mBackgroundDrawable = null;
        this.mIsBackgroundTranslucent = z;
        updateBackground();
        updateMessage();
    }

    public boolean isBackgroundTranslucent() {
        return this.mIsBackgroundTranslucent;
    }

    public void setBackgroundDrawable(Drawable drawable) {
        this.mBackgroundDrawable = drawable;
        if (drawable != null) {
            int opacity = drawable.getOpacity();
            this.mIsBackgroundTranslucent = opacity == -3 || opacity == -2;
        }
        updateBackground();
        updateMessage();
    }

    public Drawable getBackgroundDrawable() {
        return this.mBackgroundDrawable;
    }

    public void setImageDrawable(Drawable drawable) {
        this.mDrawable = drawable;
        updateImageDrawable();
    }

    public Drawable getImageDrawable() {
        return this.mDrawable;
    }

    public void setMessage(CharSequence charSequence) {
        this.mMessage = charSequence;
        updateMessage();
    }

    public CharSequence getMessage() {
        return this.mMessage;
    }

    public void setButtonText(String str) {
        this.mButtonText = str;
        updateButton();
    }

    public String getButtonText() {
        return this.mButtonText;
    }

    public void setButtonClickListener(View.OnClickListener onClickListener) {
        this.mButtonClickListener = onClickListener;
        updateButton();
    }

    public View.OnClickListener getButtonClickListener() {
        return this.mButtonClickListener;
    }

    @Override // android.support.v4.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View viewInflate = layoutInflater.inflate(R.layout.lb_error_fragment, viewGroup, false);
        this.mErrorFrame = (ViewGroup) viewInflate.findViewById(R.id.error_frame);
        updateBackground();
        installTitleView(layoutInflater, this.mErrorFrame, bundle);
        this.mImageView = (ImageView) viewInflate.findViewById(R.id.image);
        updateImageDrawable();
        this.mTextView = (TextView) viewInflate.findViewById(R.id.message);
        updateMessage();
        this.mButton = (Button) viewInflate.findViewById(R.id.button);
        updateButton();
        Paint.FontMetricsInt fontMetricsInt = getFontMetricsInt(this.mTextView);
        setTopMargin(this.mTextView, viewGroup.getResources().getDimensionPixelSize(R.dimen.lb_error_under_image_baseline_margin) + fontMetricsInt.ascent);
        setTopMargin(this.mButton, viewGroup.getResources().getDimensionPixelSize(R.dimen.lb_error_under_message_baseline_margin) - fontMetricsInt.descent);
        return viewInflate;
    }

    private void updateBackground() {
        if (this.mErrorFrame != null) {
            if (this.mBackgroundDrawable != null) {
                this.mErrorFrame.setBackground(this.mBackgroundDrawable);
            } else {
                this.mErrorFrame.setBackgroundColor(this.mErrorFrame.getResources().getColor(this.mIsBackgroundTranslucent ? R.color.lb_error_background_color_translucent : R.color.lb_error_background_color_opaque));
            }
        }
    }

    private void updateMessage() {
        if (this.mTextView != null) {
            this.mTextView.setText(this.mMessage);
            this.mTextView.setVisibility(TextUtils.isEmpty(this.mMessage) ? 8 : 0);
        }
    }

    private void updateImageDrawable() {
        if (this.mImageView != null) {
            this.mImageView.setImageDrawable(this.mDrawable);
            this.mImageView.setVisibility(this.mDrawable == null ? 8 : 0);
        }
    }

    private void updateButton() {
        if (this.mButton != null) {
            this.mButton.setText(this.mButtonText);
            this.mButton.setOnClickListener(this.mButtonClickListener);
            this.mButton.setVisibility(TextUtils.isEmpty(this.mButtonText) ? 8 : 0);
            this.mButton.requestFocus();
        }
    }

    @Override // android.support.v17.leanback.app.BrandedSupportFragment, android.support.v4.app.Fragment
    public void onStart() {
        super.onStart();
        this.mErrorFrame.requestFocus();
    }

    private static Paint.FontMetricsInt getFontMetricsInt(TextView textView) {
        Paint paint = new Paint(1);
        paint.setTextSize(textView.getTextSize());
        paint.setTypeface(textView.getTypeface());
        return paint.getFontMetricsInt();
    }

    private static void setTopMargin(TextView textView, int i) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) textView.getLayoutParams();
        marginLayoutParams.topMargin = i;
        textView.setLayoutParams(marginLayoutParams);
    }
}
