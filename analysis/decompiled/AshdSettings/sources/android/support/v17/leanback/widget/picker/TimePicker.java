package android.support.v17.leanback.widget.picker;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.IntRange;
import android.support.v17.leanback.R;
import android.support.v4.media.MediaDescriptionCompat;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public class TimePicker extends Picker {
    private static final int AM_INDEX = 0;
    private static final int HOURS_IN_HALF_DAY = 12;
    private static final int PM_INDEX = 1;
    static final String TAG = "TimePicker";
    PickerColumn mAmPmColumn;
    private View mAmPmSeparatorView;
    int mColAmPmIndex;
    int mColHourIndex;
    int mColMinuteIndex;
    private final PickerUtility.TimeConstant mConstant;
    private int mCurrentAmPmIndex;
    private int mCurrentHour;
    private int mCurrentMinute;
    PickerColumn mHourColumn;
    private boolean mIs24hFormat;
    PickerColumn mMinuteColumn;
    private ViewGroup mPickerView;

    public TimePicker(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public TimePicker(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mConstant = PickerUtility.getTimeConstantInstance(Locale.getDefault(), context.getResources());
        setSeparator(this.mConstant.timeSeparator);
        this.mPickerView = (ViewGroup) findViewById(R.id.picker);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.lbTimePicker);
        this.mIs24hFormat = typedArrayObtainStyledAttributes.getBoolean(R.styleable.lbTimePicker_is24HourFormat, DateFormat.is24HourFormat(context));
        boolean z = typedArrayObtainStyledAttributes.getBoolean(R.styleable.lbTimePicker_useCurrentTime, true);
        updateColumns(getTimePickerFormat());
        updateHourColumn(false);
        updateMin(this.mMinuteColumn, 0);
        updateMax(this.mMinuteColumn, 59);
        updateMin(this.mAmPmColumn, 0);
        updateMax(this.mAmPmColumn, 1);
        updateAmPmColumn();
        if (z) {
            Calendar calendarForLocale = PickerUtility.getCalendarForLocale(null, this.mConstant.locale);
            setHour(calendarForLocale.get(11));
            setMinute(calendarForLocale.get(12));
        }
    }

    private static boolean updateMin(PickerColumn pickerColumn, int i) {
        if (i == pickerColumn.getMinValue()) {
            return false;
        }
        pickerColumn.setMinValue(i);
        return true;
    }

    private static boolean updateMax(PickerColumn pickerColumn, int i) {
        if (i == pickerColumn.getMaxValue()) {
            return false;
        }
        pickerColumn.setMaxValue(i);
        return true;
    }

    private String getTimePickerFormat() {
        String pattern;
        StringBuilder sb;
        if (Build.VERSION.SDK_INT >= 18) {
            pattern = DateFormat.getBestDateTimePattern(this.mConstant.locale, "hma");
        } else {
            pattern = ((SimpleDateFormat) java.text.DateFormat.getTimeInstance(0, this.mConstant.locale)).toPattern();
        }
        boolean z = true;
        boolean z2 = TextUtils.getLayoutDirectionFromLocale(this.mConstant.locale) == 1;
        if (pattern.indexOf(97) >= 0 && pattern.indexOf("a") <= pattern.indexOf("m")) {
            z = false;
        }
        String str = z2 ? "mh" : "hm";
        if (z) {
            sb = new StringBuilder();
            sb.append(str);
            str = "a";
        } else {
            sb = new StringBuilder();
            sb.append("a");
        }
        sb.append(str);
        return sb.toString();
    }

    private void updateColumns(String str) {
        if (TextUtils.isEmpty(str)) {
            str = "hma";
        }
        String upperCase = str.toUpperCase();
        this.mAmPmColumn = null;
        this.mMinuteColumn = null;
        this.mHourColumn = null;
        this.mColAmPmIndex = -1;
        this.mColMinuteIndex = -1;
        this.mColHourIndex = -1;
        ArrayList arrayList = new ArrayList(3);
        int i = 0;
        while (true) {
            if (i < upperCase.length()) {
                char cCharAt = upperCase.charAt(i);
                if (cCharAt == 'A') {
                    PickerColumn pickerColumn = new PickerColumn();
                    this.mAmPmColumn = pickerColumn;
                    arrayList.add(pickerColumn);
                    this.mAmPmColumn.setStaticLabels(this.mConstant.ampm);
                    this.mColAmPmIndex = i;
                    updateMin(this.mAmPmColumn, 0);
                    updateMax(this.mAmPmColumn, 1);
                } else if (cCharAt == 'H') {
                    PickerColumn pickerColumn2 = new PickerColumn();
                    this.mHourColumn = pickerColumn2;
                    arrayList.add(pickerColumn2);
                    this.mHourColumn.setStaticLabels(this.mConstant.hours24);
                    this.mColHourIndex = i;
                } else if (cCharAt == 'M') {
                    PickerColumn pickerColumn3 = new PickerColumn();
                    this.mMinuteColumn = pickerColumn3;
                    arrayList.add(pickerColumn3);
                    this.mMinuteColumn.setStaticLabels(this.mConstant.minutes);
                    this.mColMinuteIndex = i;
                } else {
                    throw new IllegalArgumentException("Invalid time picker format.");
                }
                i++;
            } else {
                setColumns(arrayList);
                this.mAmPmSeparatorView = this.mPickerView.getChildAt(this.mColAmPmIndex != 0 ? (this.mColAmPmIndex * 2) - 1 : 1);
                return;
            }
        }
    }

    private void updateHourColumn(boolean z) {
        updateMin(this.mHourColumn, !this.mIs24hFormat ? 1 : 0);
        updateMax(this.mHourColumn, this.mIs24hFormat ? 23 : 12);
        if (z) {
            setColumnAt(this.mColHourIndex, this.mHourColumn);
        }
    }

    private void updateAmPmColumn() {
        if (this.mIs24hFormat) {
            this.mColumnViews.get(this.mColAmPmIndex).setVisibility(8);
            this.mAmPmSeparatorView.setVisibility(8);
        } else {
            this.mColumnViews.get(this.mColAmPmIndex).setVisibility(0);
            this.mAmPmSeparatorView.setVisibility(0);
            setColumnValue(this.mColAmPmIndex, this.mCurrentAmPmIndex, false);
        }
    }

    public void setHour(@IntRange(from = MediaDescriptionCompat.BT_FOLDER_TYPE_MIXED, to = 23) int i) {
        if (i < 0 || i > 23) {
            throw new IllegalArgumentException("hour: " + i + " is not in [0-23] range in");
        }
        this.mCurrentHour = i;
        if (!this.mIs24hFormat) {
            if (this.mCurrentHour >= 12) {
                this.mCurrentAmPmIndex = 1;
                if (this.mCurrentHour > 12) {
                    this.mCurrentHour -= 12;
                }
            } else {
                this.mCurrentAmPmIndex = 0;
                if (this.mCurrentHour == 0) {
                    this.mCurrentHour = 12;
                }
            }
            updateAmPmColumn();
        }
        setColumnValue(this.mColHourIndex, this.mCurrentHour, false);
    }

    public int getHour() {
        if (this.mIs24hFormat) {
            return this.mCurrentHour;
        }
        if (this.mCurrentAmPmIndex == 0) {
            return this.mCurrentHour % 12;
        }
        return (this.mCurrentHour % 12) + 12;
    }

    public void setMinute(@IntRange(from = MediaDescriptionCompat.BT_FOLDER_TYPE_MIXED, to = 59) int i) {
        if (this.mCurrentMinute == i) {
            return;
        }
        if (i < 0 || i > 59) {
            throw new IllegalArgumentException("minute: " + i + " is not in [0-59] range.");
        }
        this.mCurrentMinute = i;
        setColumnValue(this.mColMinuteIndex, this.mCurrentMinute, false);
    }

    public int getMinute() {
        return this.mCurrentMinute;
    }

    public void setIs24Hour(boolean z) {
        if (this.mIs24hFormat == z) {
            return;
        }
        int hour = getHour();
        this.mIs24hFormat = z;
        updateHourColumn(true);
        setHour(hour);
        updateAmPmColumn();
    }

    public boolean is24Hour() {
        return this.mIs24hFormat;
    }

    public boolean isPm() {
        return this.mCurrentAmPmIndex == 1;
    }

    @Override // android.support.v17.leanback.widget.picker.Picker
    public void onColumnValueChanged(int i, int i2) {
        if (i == this.mColHourIndex) {
            this.mCurrentHour = i2;
        } else if (i == this.mColMinuteIndex) {
            this.mCurrentMinute = i2;
        } else {
            if (i == this.mColAmPmIndex) {
                this.mCurrentAmPmIndex = i2;
                return;
            }
            throw new IllegalArgumentException("Invalid column index.");
        }
    }
}
