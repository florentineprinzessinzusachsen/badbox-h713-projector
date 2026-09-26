package android.support.v17.leanback.widget;

import android.graphics.Paint;
import android.support.v17.leanback.R;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.TextView;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractDetailsDescriptionPresenter extends Presenter {
    protected abstract void onBindDescription(ViewHolder viewHolder, Object obj);

    @Override // android.support.v17.leanback.widget.Presenter
    public void onUnbindViewHolder(Presenter.ViewHolder viewHolder) {
    }

    public static class ViewHolder extends Presenter.ViewHolder {
        final TextView mBody;
        final Paint.FontMetricsInt mBodyFontMetricsInt;
        final int mBodyLineSpacing;
        final int mBodyMaxLines;
        final int mBodyMinLines;
        private ViewTreeObserver.OnPreDrawListener mPreDrawListener;
        final TextView mSubtitle;
        final Paint.FontMetricsInt mSubtitleFontMetricsInt;
        final TextView mTitle;
        final Paint.FontMetricsInt mTitleFontMetricsInt;
        final int mTitleLineSpacing;
        final int mTitleMargin;
        final int mTitleMaxLines;
        final int mUnderSubtitleBaselineMargin;
        final int mUnderTitleBaselineMargin;

        public ViewHolder(View view) {
            super(view);
            this.mTitle = (TextView) view.findViewById(R.id.lb_details_description_title);
            this.mSubtitle = (TextView) view.findViewById(R.id.lb_details_description_subtitle);
            this.mBody = (TextView) view.findViewById(R.id.lb_details_description_body);
            this.mTitleMargin = view.getResources().getDimensionPixelSize(R.dimen.lb_details_description_title_baseline) + getFontMetricsInt(this.mTitle).ascent;
            this.mUnderTitleBaselineMargin = view.getResources().getDimensionPixelSize(R.dimen.lb_details_description_under_title_baseline_margin);
            this.mUnderSubtitleBaselineMargin = view.getResources().getDimensionPixelSize(R.dimen.lb_details_description_under_subtitle_baseline_margin);
            this.mTitleLineSpacing = view.getResources().getDimensionPixelSize(R.dimen.lb_details_description_title_line_spacing);
            this.mBodyLineSpacing = view.getResources().getDimensionPixelSize(R.dimen.lb_details_description_body_line_spacing);
            this.mBodyMaxLines = view.getResources().getInteger(R.integer.lb_details_description_body_max_lines);
            this.mBodyMinLines = view.getResources().getInteger(R.integer.lb_details_description_body_min_lines);
            this.mTitleMaxLines = this.mTitle.getMaxLines();
            this.mTitleFontMetricsInt = getFontMetricsInt(this.mTitle);
            this.mSubtitleFontMetricsInt = getFontMetricsInt(this.mSubtitle);
            this.mBodyFontMetricsInt = getFontMetricsInt(this.mBody);
            this.mTitle.addOnLayoutChangeListener(new View.OnLayoutChangeListener() { // from class: android.support.v17.leanback.widget.AbstractDetailsDescriptionPresenter.ViewHolder.1
                @Override // android.view.View.OnLayoutChangeListener
                public void onLayoutChange(View view2, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                    ViewHolder.this.addPreDrawListener();
                }
            });
        }

        void addPreDrawListener() {
            if (this.mPreDrawListener != null) {
                return;
            }
            this.mPreDrawListener = new ViewTreeObserver.OnPreDrawListener() { // from class: android.support.v17.leanback.widget.AbstractDetailsDescriptionPresenter.ViewHolder.2
                @Override // android.view.ViewTreeObserver.OnPreDrawListener
                public boolean onPreDraw() {
                    if (ViewHolder.this.mSubtitle.getVisibility() == 0 && ViewHolder.this.mSubtitle.getTop() > ViewHolder.this.view.getHeight() && ViewHolder.this.mTitle.getLineCount() > 1) {
                        ViewHolder.this.mTitle.setMaxLines(ViewHolder.this.mTitle.getLineCount() - 1);
                        return false;
                    }
                    int i = ViewHolder.this.mTitle.getLineCount() > 1 ? ViewHolder.this.mBodyMinLines : ViewHolder.this.mBodyMaxLines;
                    if (ViewHolder.this.mBody.getMaxLines() != i) {
                        ViewHolder.this.mBody.setMaxLines(i);
                        return false;
                    }
                    ViewHolder.this.removePreDrawListener();
                    return true;
                }
            };
            this.view.getViewTreeObserver().addOnPreDrawListener(this.mPreDrawListener);
        }

        void removePreDrawListener() {
            if (this.mPreDrawListener != null) {
                this.view.getViewTreeObserver().removeOnPreDrawListener(this.mPreDrawListener);
                this.mPreDrawListener = null;
            }
        }

        public TextView getTitle() {
            return this.mTitle;
        }

        public TextView getSubtitle() {
            return this.mSubtitle;
        }

        public TextView getBody() {
            return this.mBody;
        }

        private Paint.FontMetricsInt getFontMetricsInt(TextView textView) {
            Paint paint = new Paint(1);
            paint.setTextSize(textView.getTextSize());
            paint.setTypeface(textView.getTypeface());
            return paint.getFontMetricsInt();
        }
    }

    @Override // android.support.v17.leanback.widget.Presenter
    public final ViewHolder onCreateViewHolder(ViewGroup viewGroup) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.lb_details_description, viewGroup, false));
    }

    @Override // android.support.v17.leanback.widget.Presenter
    public final void onBindViewHolder(Presenter.ViewHolder viewHolder, Object obj) {
        boolean z;
        ViewHolder viewHolder2 = (ViewHolder) viewHolder;
        onBindDescription(viewHolder2, obj);
        boolean z2 = true;
        if (TextUtils.isEmpty(viewHolder2.mTitle.getText())) {
            viewHolder2.mTitle.setVisibility(8);
            z = false;
        } else {
            viewHolder2.mTitle.setVisibility(0);
            viewHolder2.mTitle.setLineSpacing((viewHolder2.mTitleLineSpacing - viewHolder2.mTitle.getLineHeight()) + viewHolder2.mTitle.getLineSpacingExtra(), viewHolder2.mTitle.getLineSpacingMultiplier());
            viewHolder2.mTitle.setMaxLines(viewHolder2.mTitleMaxLines);
            z = true;
        }
        setTopMargin(viewHolder2.mTitle, viewHolder2.mTitleMargin);
        if (TextUtils.isEmpty(viewHolder2.mSubtitle.getText())) {
            viewHolder2.mSubtitle.setVisibility(8);
            z2 = false;
        } else {
            viewHolder2.mSubtitle.setVisibility(0);
            if (z) {
                setTopMargin(viewHolder2.mSubtitle, (viewHolder2.mUnderTitleBaselineMargin + viewHolder2.mSubtitleFontMetricsInt.ascent) - viewHolder2.mTitleFontMetricsInt.descent);
            } else {
                setTopMargin(viewHolder2.mSubtitle, 0);
            }
        }
        if (TextUtils.isEmpty(viewHolder2.mBody.getText())) {
            viewHolder2.mBody.setVisibility(8);
            return;
        }
        viewHolder2.mBody.setVisibility(0);
        viewHolder2.mBody.setLineSpacing((viewHolder2.mBodyLineSpacing - viewHolder2.mBody.getLineHeight()) + viewHolder2.mBody.getLineSpacingExtra(), viewHolder2.mBody.getLineSpacingMultiplier());
        if (z2) {
            setTopMargin(viewHolder2.mBody, (viewHolder2.mUnderSubtitleBaselineMargin + viewHolder2.mBodyFontMetricsInt.ascent) - viewHolder2.mSubtitleFontMetricsInt.descent);
        } else if (z) {
            setTopMargin(viewHolder2.mBody, (viewHolder2.mUnderTitleBaselineMargin + viewHolder2.mBodyFontMetricsInt.ascent) - viewHolder2.mTitleFontMetricsInt.descent);
        } else {
            setTopMargin(viewHolder2.mBody, 0);
        }
    }

    @Override // android.support.v17.leanback.widget.Presenter
    public void onViewAttachedToWindow(Presenter.ViewHolder viewHolder) {
        ((ViewHolder) viewHolder).addPreDrawListener();
        super.onViewAttachedToWindow(viewHolder);
    }

    @Override // android.support.v17.leanback.widget.Presenter
    public void onViewDetachedFromWindow(Presenter.ViewHolder viewHolder) {
        ((ViewHolder) viewHolder).removePreDrawListener();
        super.onViewDetachedFromWindow(viewHolder);
    }

    private void setTopMargin(TextView textView, int i) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) textView.getLayoutParams();
        marginLayoutParams.topMargin = i;
        textView.setLayoutParams(marginLayoutParams);
    }
}
