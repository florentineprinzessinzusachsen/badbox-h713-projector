package com.rk_itvui.settings;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.preference.Preference;
import android.util.AttributeSet;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.widget.Checkable;

/* JADX INFO: loaded from: classes.dex */
public class RadioPreference extends Preference {
    private AccessibilityManager mAccessibilityManager;
    private boolean mChecked;
    private boolean mDisableDependentsState;
    private boolean mSendAccessibilityEventViewClickedType;
    private CharSequence mSummaryOff;
    private CharSequence mSummaryOn;

    @Override // android.preference.Preference
    public boolean isPersistent() {
        return false;
    }

    public RadioPreference(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mAccessibilityManager = (AccessibilityManager) getContext().getSystemService("accessibility");
    }

    public RadioPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mAccessibilityManager = (AccessibilityManager) getContext().getSystemService("accessibility");
    }

    public RadioPreference(Context context) {
        super(context, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.preference.Preference
    protected void onBindView(View view) {
        super.onBindView(view);
        View viewFindViewById = view.findViewById(com.ashd.settings.R.id.radiobutton);
        if (viewFindViewById == 0 || !(viewFindViewById instanceof Checkable)) {
            return;
        }
        ((Checkable) viewFindViewById).setChecked(this.mChecked);
        if (this.mSendAccessibilityEventViewClickedType && this.mAccessibilityManager.isEnabled() && viewFindViewById.isEnabled()) {
            this.mSendAccessibilityEventViewClickedType = false;
            viewFindViewById.sendAccessibilityEventUnchecked(AccessibilityEvent.obtain(1));
        }
    }

    @Override // android.preference.Preference
    protected void onClick() {
        super.onClick();
        boolean z = !isChecked();
        this.mSendAccessibilityEventViewClickedType = true;
        if (callChangeListener(Boolean.valueOf(z))) {
            setChecked(z);
        }
    }

    public void setChecked(boolean z) {
        if (this.mChecked != z) {
            this.mChecked = z;
            persistBoolean(z);
            notifyDependencyChange(shouldDisableDependents());
            notifyChanged();
        }
    }

    public boolean isChecked() {
        return this.mChecked;
    }

    @Override // android.preference.Preference
    public boolean shouldDisableDependents() {
        boolean z;
        if (this.mDisableDependentsState) {
            z = this.mChecked;
        } else {
            z = !this.mChecked;
        }
        return z || super.shouldDisableDependents();
    }

    public void setSummaryOn(CharSequence charSequence) {
        this.mSummaryOn = charSequence;
        if (isChecked()) {
            notifyChanged();
        }
    }

    public void setSummaryOn(int i) {
        setSummaryOn(getContext().getString(i));
    }

    public CharSequence getSummaryOn() {
        return this.mSummaryOn;
    }

    public void setSummaryOff(CharSequence charSequence) {
        this.mSummaryOff = charSequence;
        if (isChecked()) {
            return;
        }
        notifyChanged();
    }

    public void setSummaryOff(int i) {
        setSummaryOff(getContext().getString(i));
    }

    public CharSequence getSummaryOff() {
        return this.mSummaryOff;
    }

    public boolean getDisableDependentsState() {
        return this.mDisableDependentsState;
    }

    public void setDisableDependentsState(boolean z) {
        this.mDisableDependentsState = z;
    }

    @Override // android.preference.Preference
    protected Object onGetDefaultValue(TypedArray typedArray, int i) {
        return Boolean.valueOf(typedArray.getBoolean(i, false));
    }

    @Override // android.preference.Preference
    protected void onSetInitialValue(boolean z, Object obj) {
        boolean zBooleanValue;
        if (z) {
            zBooleanValue = getPersistedBoolean(this.mChecked);
        } else {
            zBooleanValue = ((Boolean) obj).booleanValue();
        }
        setChecked(zBooleanValue);
    }

    @Override // android.preference.Preference
    protected Parcelable onSaveInstanceState() {
        Parcelable parcelableOnSaveInstanceState = super.onSaveInstanceState();
        if (isPersistent()) {
            return parcelableOnSaveInstanceState;
        }
        SavedState savedState = new SavedState(parcelableOnSaveInstanceState);
        savedState.checked = isChecked();
        return savedState;
    }

    @Override // android.preference.Preference
    protected void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable == null || !parcelable.getClass().equals(SavedState.class)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        setChecked(savedState.checked);
    }

    private static class SavedState extends Preference.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() { // from class: com.rk_itvui.settings.RadioPreference.SavedState.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        };
        boolean checked;

        public SavedState(Parcel parcel) {
            super(parcel);
            this.checked = parcel.readInt() == 1;
        }

        @Override // android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.checked ? 1 : 0);
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }
    }
}
