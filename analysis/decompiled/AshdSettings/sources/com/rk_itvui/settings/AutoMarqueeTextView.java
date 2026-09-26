package com.rk_itvui.settings;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/* JADX INFO: loaded from: classes.dex */
public class AutoMarqueeTextView extends TextView {
    private boolean isAutoMarquee;

    public AutoMarqueeTextView(Context context) {
        super(context);
        this.isAutoMarquee = false;
    }

    public AutoMarqueeTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.isAutoMarquee = false;
    }

    public AutoMarqueeTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.isAutoMarquee = false;
    }

    @Override // android.view.View
    public boolean isFocused() {
        return this.isAutoMarquee;
    }

    public void setAutoMarquee(boolean z) {
        this.isAutoMarquee = z;
    }
}
