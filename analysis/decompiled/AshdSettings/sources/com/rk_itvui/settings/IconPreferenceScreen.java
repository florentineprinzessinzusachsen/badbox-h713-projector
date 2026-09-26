package com.rk_itvui.settings;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.preference.Preference;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

/* JADX INFO: loaded from: classes.dex */
public class IconPreferenceScreen extends Preference {
    private boolean mHighlight;
    private Drawable mIcon;

    public IconPreferenceScreen(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public IconPreferenceScreen(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        setLayoutResource(com.ashd.settings.R.layout.preference_icon);
    }

    @Override // android.preference.Preference
    public void onBindView(View view) {
        super.onBindView(view);
        ImageView imageView = (ImageView) view.findViewById(com.ashd.settings.R.id.icon);
        if (imageView != null && this.mIcon != null) {
            imageView.setImageDrawable(this.mIcon);
        }
    }

    @Override // android.preference.Preference
    public void setIcon(Drawable drawable) {
        if ((drawable != null || this.mIcon == null) && (drawable == null || drawable.equals(this.mIcon))) {
            return;
        }
        this.mIcon = drawable;
        notifyChanged();
    }

    @Override // android.preference.Preference
    public Drawable getIcon() {
        return this.mIcon;
    }

    public void setHighlighted(boolean z) {
        this.mHighlight = z;
        notifyChanged();
    }
}
