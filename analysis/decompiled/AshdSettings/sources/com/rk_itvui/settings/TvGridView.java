package com.rk_itvui.settings;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Transformation;
import android.widget.AdapterView;
import android.widget.GridView;

/* JADX INFO: loaded from: classes.dex */
public class TvGridView extends GridView implements AdapterView.OnItemSelectedListener {
    private int FocusPosition;
    private float mItemScale;
    private final float mItemSelectedScale;

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    public TvGridView(Context context) {
        this(context, null);
    }

    public TvGridView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public TvGridView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mItemSelectedScale = 2.0f;
        setOnItemSelectedListener(this);
        setStaticTransformationsEnabled(true);
    }

    @Override // android.widget.GridView, android.widget.AbsListView, android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        int numRow = getNumRow();
        int focusViewNumRow = getFocusViewNumRow();
        int numColumns = (getNumColumns() - (-(this.FocusPosition - (getNumColumns() * focusViewNumRow)))) + 1;
        if (i == 19 && focusViewNumRow == 1) {
            int numColumns2 = (((numRow - 1) * getNumColumns()) + numColumns) - 1;
            if (numColumns2 >= getCount()) {
                numColumns2 = getCount() - 1;
            }
            setSelection(numColumns2);
        }
        if (i == 20 && focusViewNumRow >= numRow) {
            setSelection(numColumns - 1);
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    public int getNumRow() {
        if (getCount() % getNumColumns() != 0) {
            return (getCount() / getNumColumns()) + 1;
        }
        return getCount() / getNumColumns();
    }

    public int getFocusViewNumRow() {
        return (getSelectedItemPosition() / getNumColumns()) + 1;
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
        this.FocusPosition = i;
    }

    @Override // android.view.ViewGroup
    protected boolean getChildStaticTransformation(View view, Transformation transformation) {
        transformation.setTransformationType(2);
        if (view == getSelectedView() && view.isSelected()) {
            this.mItemScale = 1.0f;
            this.mItemScale += 0.1f;
            if (this.mItemScale >= 2.0f) {
                this.mItemScale = 2.0f;
            } else {
                view.postInvalidateDelayed(80L);
            }
            transformation.getMatrix().setScale(this.mItemScale, this.mItemScale, view.getWidth() / 2.0f, view.getHeight() / 2.0f);
            view.setZ(9.0f);
            return true;
        }
        transformation.getMatrix().setScale(1.0f, 1.0f);
        view.setZ(0.0f);
        return true;
    }

    public int getFocusPosition() {
        return this.FocusPosition;
    }
}
