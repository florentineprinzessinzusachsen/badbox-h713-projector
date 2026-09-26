package com.android.settingslib.graph;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.GravityCompat;
import android.util.AttributeSet;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.settingslib.R;

/* JADX INFO: loaded from: classes.dex */
public class UsageView extends FrameLayout {
    private final TextView[] mBottomLabels;
    private final TextView[] mLabels;
    private final UsageGraph mUsageGraph;

    public UsageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        LayoutInflater.from(context).inflate(R.layout.usage_view, this);
        this.mUsageGraph = (UsageGraph) findViewById(R.id.usage_graph);
        this.mLabels = new TextView[]{(TextView) findViewById(R.id.label_bottom), (TextView) findViewById(R.id.label_middle), (TextView) findViewById(R.id.label_top)};
        this.mBottomLabels = new TextView[]{(TextView) findViewById(R.id.label_start), (TextView) findViewById(R.id.label_end)};
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.UsageView, 0, 0);
        if (typedArrayObtainStyledAttributes.hasValue(R.styleable.UsageView_sideLabels)) {
            setSideLabels(typedArrayObtainStyledAttributes.getTextArray(R.styleable.UsageView_sideLabels));
        }
        if (typedArrayObtainStyledAttributes.hasValue(R.styleable.UsageView_bottomLabels)) {
            setBottomLabels(typedArrayObtainStyledAttributes.getTextArray(R.styleable.UsageView_bottomLabels));
        }
        if (typedArrayObtainStyledAttributes.hasValue(R.styleable.UsageView_textColor)) {
            int color = typedArrayObtainStyledAttributes.getColor(R.styleable.UsageView_textColor, 0);
            for (TextView textView : this.mLabels) {
                textView.setTextColor(color);
            }
            for (TextView textView2 : this.mBottomLabels) {
                textView2.setTextColor(color);
            }
        }
        if (typedArrayObtainStyledAttributes.hasValue(R.styleable.UsageView_android_gravity)) {
            int i = typedArrayObtainStyledAttributes.getInt(R.styleable.UsageView_android_gravity, 0);
            if (i == 8388613) {
                LinearLayout linearLayout = (LinearLayout) findViewById(R.id.graph_label_group);
                LinearLayout linearLayout2 = (LinearLayout) findViewById(R.id.label_group);
                linearLayout.removeView(linearLayout2);
                linearLayout.addView(linearLayout2);
                linearLayout2.setGravity(GravityCompat.END);
                LinearLayout linearLayout3 = (LinearLayout) findViewById(R.id.bottom_label_group);
                View viewFindViewById = linearLayout3.findViewById(R.id.bottom_label_space);
                linearLayout3.removeView(viewFindViewById);
                linearLayout3.addView(viewFindViewById);
            } else if (i != 8388611) {
                throw new IllegalArgumentException("Unsupported gravity " + i);
            }
        }
        this.mUsageGraph.setAccentColor(typedArrayObtainStyledAttributes.getColor(R.styleable.UsageView_android_colorAccent, 0));
    }

    public void clearPaths() {
        this.mUsageGraph.clearPaths();
    }

    public void addPath(SparseIntArray sparseIntArray) {
        this.mUsageGraph.addPath(sparseIntArray);
    }

    public void configureGraph(int i, int i2, boolean z, boolean z2) {
        this.mUsageGraph.setMax(i, i2);
        this.mUsageGraph.setShowProjection(z, z2);
    }

    public void setAccentColor(int i) {
        this.mUsageGraph.setAccentColor(i);
    }

    public void setDividerLoc(int i) {
        this.mUsageGraph.setDividerLoc(i);
    }

    public void setDividerColors(int i, int i2) {
        this.mUsageGraph.setDividerColors(i, i2);
    }

    public void setSideLabelWeights(float f, float f2) {
        setWeight(R.id.space1, f);
        setWeight(R.id.space2, f2);
    }

    private void setWeight(int i, float f) {
        View viewFindViewById = findViewById(i);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) viewFindViewById.getLayoutParams();
        layoutParams.weight = f;
        viewFindViewById.setLayoutParams(layoutParams);
    }

    public void setSideLabels(CharSequence[] charSequenceArr) {
        if (charSequenceArr.length != this.mLabels.length) {
            throw new IllegalArgumentException("Invalid number of labels");
        }
        for (int i = 0; i < this.mLabels.length; i++) {
            this.mLabels[i].setText(charSequenceArr[i]);
        }
    }

    public void setBottomLabels(CharSequence[] charSequenceArr) {
        if (charSequenceArr.length != this.mBottomLabels.length) {
            throw new IllegalArgumentException("Invalid number of labels");
        }
        for (int i = 0; i < this.mBottomLabels.length; i++) {
            this.mBottomLabels[i].setText(charSequenceArr[i]);
        }
    }
}
