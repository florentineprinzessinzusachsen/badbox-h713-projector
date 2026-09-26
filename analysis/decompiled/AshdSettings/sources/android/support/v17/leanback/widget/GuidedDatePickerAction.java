package android.support.v17.leanback.widget;

import android.content.Context;
import android.os.Bundle;
import java.util.Calendar;

/* JADX INFO: loaded from: classes.dex */
public class GuidedDatePickerAction extends GuidedAction {
    long mDate;
    String mDatePickerFormat;
    long mMinDate = Long.MIN_VALUE;
    long mMaxDate = Long.MAX_VALUE;

    public static abstract class BuilderBase<B extends BuilderBase> extends GuidedAction.BuilderBase<B> {
        private long mDate;
        private String mDatePickerFormat;
        private long mMaxDate;
        private long mMinDate;

        public BuilderBase(Context context) {
            super(context);
            this.mMinDate = Long.MIN_VALUE;
            this.mMaxDate = Long.MAX_VALUE;
            this.mDate = Calendar.getInstance().getTimeInMillis();
            hasEditableActivatorView(true);
        }

        public B datePickerFormat(String str) {
            this.mDatePickerFormat = str;
            return this;
        }

        public B date(long j) {
            this.mDate = j;
            return this;
        }

        public B minDate(long j) {
            this.mMinDate = j;
            return this;
        }

        public B maxDate(long j) {
            this.mMaxDate = j;
            return this;
        }

        protected final void applyDatePickerValues(GuidedDatePickerAction guidedDatePickerAction) {
            super.applyValues(guidedDatePickerAction);
            guidedDatePickerAction.mDatePickerFormat = this.mDatePickerFormat;
            guidedDatePickerAction.mDate = this.mDate;
            if (this.mMinDate > this.mMaxDate) {
                throw new IllegalArgumentException("MinDate cannot be larger than MaxDate");
            }
            guidedDatePickerAction.mMinDate = this.mMinDate;
            guidedDatePickerAction.mMaxDate = this.mMaxDate;
        }
    }

    public static final class Builder extends BuilderBase<Builder> {
        public Builder(Context context) {
            super(context);
        }

        public GuidedDatePickerAction build() {
            GuidedDatePickerAction guidedDatePickerAction = new GuidedDatePickerAction();
            applyDatePickerValues(guidedDatePickerAction);
            return guidedDatePickerAction;
        }
    }

    public String getDatePickerFormat() {
        return this.mDatePickerFormat;
    }

    public long getDate() {
        return this.mDate;
    }

    public void setDate(long j) {
        this.mDate = j;
    }

    public long getMinDate() {
        return this.mMinDate;
    }

    public long getMaxDate() {
        return this.mMaxDate;
    }

    @Override // android.support.v17.leanback.widget.GuidedAction
    public void onSaveInstanceState(Bundle bundle, String str) {
        bundle.putLong(str, getDate());
    }

    @Override // android.support.v17.leanback.widget.GuidedAction
    public void onRestoreInstanceState(Bundle bundle, String str) {
        setDate(bundle.getLong(str, getDate()));
    }
}
