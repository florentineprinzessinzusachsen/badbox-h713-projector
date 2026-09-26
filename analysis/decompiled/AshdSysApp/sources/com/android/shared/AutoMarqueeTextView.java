package com.android.shared;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/* JADX INFO: loaded from: classes.dex */
public class AutoMarqueeTextView extends TextView {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f48a;

    public AutoMarqueeTextView(Context context) {
        super(context);
        this.f48a = false;
    }

    public AutoMarqueeTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f48a = false;
    }

    public AutoMarqueeTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f48a = false;
    }

    @Override // android.view.View
    public boolean isFocused() {
        return this.f48a;
    }

    public void setAutoMarquee(boolean z) {
        this.f48a = z;
    }
}
