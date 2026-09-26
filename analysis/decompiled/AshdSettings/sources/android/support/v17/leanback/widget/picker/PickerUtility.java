package android.support.v17.leanback.widget.picker;

import android.content.res.Resources;
import android.support.v17.leanback.R;
import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
class PickerUtility {
    PickerUtility() {
    }

    public static class DateConstant {
        public final String dateSeparator;
        public final String[] days;
        public final Locale locale;
        public final String[] months;

        private DateConstant(Locale locale, Resources resources) {
            this.locale = locale;
            this.months = DateFormatSymbols.getInstance(locale).getShortMonths();
            Calendar calendar = Calendar.getInstance(locale);
            this.days = PickerUtility.createStringIntArrays(calendar.getMinimum(5), calendar.getMaximum(5), "%02d");
            this.dateSeparator = resources.getString(R.string.lb_date_separator);
        }
    }

    public static class TimeConstant {
        public final String[] ampm;
        public final String[] hours12;
        public final String[] hours24;
        public final Locale locale;
        public final String[] minutes;
        public final String timeSeparator;

        private TimeConstant(Locale locale, Resources resources) {
            this.locale = locale;
            DateFormatSymbols dateFormatSymbols = DateFormatSymbols.getInstance(locale);
            this.hours12 = PickerUtility.createStringIntArrays(1, 12, "%02d");
            this.hours24 = PickerUtility.createStringIntArrays(0, 23, "%02d");
            this.minutes = PickerUtility.createStringIntArrays(0, 59, "%02d");
            this.ampm = dateFormatSymbols.getAmPmStrings();
            this.timeSeparator = resources.getString(R.string.lb_time_separator);
        }
    }

    public static DateConstant getDateConstantInstance(Locale locale, Resources resources) {
        return new DateConstant(locale, resources);
    }

    public static TimeConstant getTimeConstantInstance(Locale locale, Resources resources) {
        return new TimeConstant(locale, resources);
    }

    public static String[] createStringIntArrays(int i, int i2, String str) {
        String[] strArr = new String[(i2 - i) + 1];
        for (int i3 = i; i3 <= i2; i3++) {
            if (str != null) {
                strArr[i3 - i] = String.format(str, Integer.valueOf(i3));
            } else {
                strArr[i3 - i] = String.valueOf(i3);
            }
        }
        return strArr;
    }

    public static Calendar getCalendarForLocale(Calendar calendar, Locale locale) {
        if (calendar == null) {
            return Calendar.getInstance(locale);
        }
        long timeInMillis = calendar.getTimeInMillis();
        Calendar calendar2 = Calendar.getInstance(locale);
        calendar2.setTimeInMillis(timeInMillis);
        return calendar2;
    }
}
