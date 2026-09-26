package com.google.gson.internal.bind.util;

import com.android.settingslib.accessibility.AccessibilityUtils;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

/* JADX INFO: loaded from: classes.dex */
public class ISO8601Utils {
    private static final String UTC_ID = "UTC";
    private static final TimeZone TIMEZONE_UTC = TimeZone.getTimeZone(UTC_ID);

    public static String format(Date date) {
        return format(date, false, TIMEZONE_UTC);
    }

    public static String format(Date date, boolean z) {
        return format(date, z, TIMEZONE_UTC);
    }

    public static String format(Date date, boolean z, TimeZone timeZone) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar(timeZone, Locale.US);
        gregorianCalendar.setTime(date);
        StringBuilder sb = new StringBuilder("yyyy-MM-ddThh:mm:ss".length() + (z ? ".sss".length() : 0) + (timeZone.getRawOffset() == 0 ? "Z" : "+hh:mm").length());
        padInt(sb, gregorianCalendar.get(1), "yyyy".length());
        sb.append('-');
        padInt(sb, gregorianCalendar.get(2) + 1, "MM".length());
        sb.append('-');
        padInt(sb, gregorianCalendar.get(5), "dd".length());
        sb.append('T');
        padInt(sb, gregorianCalendar.get(11), "hh".length());
        sb.append(AccessibilityUtils.ENABLED_ACCESSIBILITY_SERVICES_SEPARATOR);
        padInt(sb, gregorianCalendar.get(12), "mm".length());
        sb.append(AccessibilityUtils.ENABLED_ACCESSIBILITY_SERVICES_SEPARATOR);
        padInt(sb, gregorianCalendar.get(13), "ss".length());
        if (z) {
            sb.append('.');
            padInt(sb, gregorianCalendar.get(14), "sss".length());
        }
        int offset = timeZone.getOffset(gregorianCalendar.getTimeInMillis());
        if (offset != 0) {
            int i = offset / 60000;
            int iAbs = Math.abs(i / 60);
            int iAbs2 = Math.abs(i % 60);
            sb.append(offset >= 0 ? '+' : '-');
            padInt(sb, iAbs, "hh".length());
            sb.append(AccessibilityUtils.ENABLED_ACCESSIBILITY_SERVICES_SEPARATOR);
            padInt(sb, iAbs2, "mm".length());
        } else {
            sb.append('Z');
        }
        return sb.toString();
    }

    /* JADX WARN: Code duplicated, block: B:48:0x00c7 A[Catch: IllegalArgumentException -> 0x01b7, NumberFormatException -> 0x01ba, IndexOutOfBoundsException -> 0x01bd, TryCatch #2 {IllegalArgumentException -> 0x01b7, IndexOutOfBoundsException -> 0x01bd, NumberFormatException -> 0x01ba, blocks: (B:3:0x0004, B:5:0x0016, B:6:0x0018, B:8:0x0024, B:9:0x0026, B:11:0x0035, B:13:0x003b, B:17:0x004f, B:19:0x005f, B:20:0x0061, B:22:0x006d, B:23:0x006f, B:25:0x0075, B:29:0x007f, B:34:0x008f, B:36:0x0097, B:37:0x00aa, B:46:0x00c1, B:48:0x00c7, B:49:0x00ce, B:50:0x00cf, B:52:0x00d6, B:76:0x0185, B:57:0x00e2, B:58:0x00fd, B:59:0x00fe, B:63:0x011a, B:65:0x0127, B:68:0x0130, B:70:0x014f, B:72:0x015d, B:73:0x017f, B:75:0x0182, B:62:0x0109, B:39:0x00b0, B:40:0x00b3), top: B:94:0x0004 }] */
    /* JADX WARN: Code duplicated, block: B:50:0x00cf A[Catch: IllegalArgumentException -> 0x01b7, NumberFormatException -> 0x01ba, IndexOutOfBoundsException -> 0x01bd, TryCatch #2 {IllegalArgumentException -> 0x01b7, IndexOutOfBoundsException -> 0x01bd, NumberFormatException -> 0x01ba, blocks: (B:3:0x0004, B:5:0x0016, B:6:0x0018, B:8:0x0024, B:9:0x0026, B:11:0x0035, B:13:0x003b, B:17:0x004f, B:19:0x005f, B:20:0x0061, B:22:0x006d, B:23:0x006f, B:25:0x0075, B:29:0x007f, B:34:0x008f, B:36:0x0097, B:37:0x00aa, B:46:0x00c1, B:48:0x00c7, B:49:0x00ce, B:50:0x00cf, B:52:0x00d6, B:76:0x0185, B:57:0x00e2, B:58:0x00fd, B:59:0x00fe, B:63:0x011a, B:65:0x0127, B:68:0x0130, B:70:0x014f, B:72:0x015d, B:73:0x017f, B:75:0x0182, B:62:0x0109, B:39:0x00b0, B:40:0x00b3), top: B:94:0x0004 }] */
    /* JADX WARN: Code duplicated, block: B:52:0x00d6 A[Catch: IllegalArgumentException -> 0x01b7, NumberFormatException -> 0x01ba, IndexOutOfBoundsException -> 0x01bd, TryCatch #2 {IllegalArgumentException -> 0x01b7, IndexOutOfBoundsException -> 0x01bd, NumberFormatException -> 0x01ba, blocks: (B:3:0x0004, B:5:0x0016, B:6:0x0018, B:8:0x0024, B:9:0x0026, B:11:0x0035, B:13:0x003b, B:17:0x004f, B:19:0x005f, B:20:0x0061, B:22:0x006d, B:23:0x006f, B:25:0x0075, B:29:0x007f, B:34:0x008f, B:36:0x0097, B:37:0x00aa, B:46:0x00c1, B:48:0x00c7, B:49:0x00ce, B:50:0x00cf, B:52:0x00d6, B:76:0x0185, B:57:0x00e2, B:58:0x00fd, B:59:0x00fe, B:63:0x011a, B:65:0x0127, B:68:0x0130, B:70:0x014f, B:72:0x015d, B:73:0x017f, B:75:0x0182, B:62:0x0109, B:39:0x00b0, B:40:0x00b3), top: B:94:0x0004 }] */
    /* JADX WARN: Code duplicated, block: B:53:0x00db  */
    /* JADX WARN: Code duplicated, block: B:61:0x0108  */
    /* JADX WARN: Code duplicated, block: B:62:0x0109 A[Catch: IllegalArgumentException -> 0x01b7, NumberFormatException -> 0x01ba, IndexOutOfBoundsException -> 0x01bd, TryCatch #2 {IllegalArgumentException -> 0x01b7, IndexOutOfBoundsException -> 0x01bd, NumberFormatException -> 0x01ba, blocks: (B:3:0x0004, B:5:0x0016, B:6:0x0018, B:8:0x0024, B:9:0x0026, B:11:0x0035, B:13:0x003b, B:17:0x004f, B:19:0x005f, B:20:0x0061, B:22:0x006d, B:23:0x006f, B:25:0x0075, B:29:0x007f, B:34:0x008f, B:36:0x0097, B:37:0x00aa, B:46:0x00c1, B:48:0x00c7, B:49:0x00ce, B:50:0x00cf, B:52:0x00d6, B:76:0x0185, B:57:0x00e2, B:58:0x00fd, B:59:0x00fe, B:63:0x011a, B:65:0x0127, B:68:0x0130, B:70:0x014f, B:72:0x015d, B:73:0x017f, B:75:0x0182, B:62:0x0109, B:39:0x00b0, B:40:0x00b3), top: B:94:0x0004 }] */
    /* JADX WARN: Code duplicated, block: B:75:0x0182 A[Catch: IllegalArgumentException -> 0x01b7, NumberFormatException -> 0x01ba, IndexOutOfBoundsException -> 0x01bd, TryCatch #2 {IllegalArgumentException -> 0x01b7, IndexOutOfBoundsException -> 0x01bd, NumberFormatException -> 0x01ba, blocks: (B:3:0x0004, B:5:0x0016, B:6:0x0018, B:8:0x0024, B:9:0x0026, B:11:0x0035, B:13:0x003b, B:17:0x004f, B:19:0x005f, B:20:0x0061, B:22:0x006d, B:23:0x006f, B:25:0x0075, B:29:0x007f, B:34:0x008f, B:36:0x0097, B:37:0x00aa, B:46:0x00c1, B:48:0x00c7, B:49:0x00ce, B:50:0x00cf, B:52:0x00d6, B:76:0x0185, B:57:0x00e2, B:58:0x00fd, B:59:0x00fe, B:63:0x011a, B:65:0x0127, B:68:0x0130, B:70:0x014f, B:72:0x015d, B:73:0x017f, B:75:0x0182, B:62:0x0109, B:39:0x00b0, B:40:0x00b3), top: B:94:0x0004 }] */
    /* JADX WARN: Code duplicated, block: B:85:0x01c1  */
    /* JADX WARN: Code duplicated, block: B:86:0x01c3  */
    /* JADX WARN: Code duplicated, block: B:89:0x01df  */
    /* JADX WARN: Code duplicated, block: B:91:0x01e5  */
    /* JADX WARN: Instruction removed from duplicated block: B:62:0x0109, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:86:0x01c3, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:91:0x01e5, please report this as an issue */
    public static Date parse(String str, ParsePosition parsePosition) throws ParseException {
        Exception exc;
        String str2;
        String message;
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        char cCharAt;
        String strSubstring;
        int length;
        TimeZone timeZone;
        char cCharAt2;
        try {
            int index = parsePosition.getIndex();
            int i6 = index + 4;
            int i7 = parseInt(str, index, i6);
            if (checkOffset(str, i6, '-')) {
                i6++;
            }
            int i8 = i6 + 2;
            int i9 = parseInt(str, i6, i8);
            if (checkOffset(str, i8, '-')) {
                i8++;
            }
            int i10 = i8 + 2;
            int i11 = parseInt(str, i8, i10);
            boolean zCheckOffset = checkOffset(str, i10, 'T');
            if (!zCheckOffset && str.length() <= i10) {
                GregorianCalendar gregorianCalendar = new GregorianCalendar(i7, i9 - 1, i11);
                parsePosition.setIndex(i10);
                return gregorianCalendar.getTime();
            }
            if (zCheckOffset) {
                int i12 = i10 + 1;
                int i13 = i12 + 2;
                i2 = parseInt(str, i12, i13);
                if (checkOffset(str, i13, AccessibilityUtils.ENABLED_ACCESSIBILITY_SERVICES_SEPARATOR)) {
                    i13++;
                }
                int i14 = i13 + 2;
                i3 = parseInt(str, i13, i14);
                if (checkOffset(str, i14, AccessibilityUtils.ENABLED_ACCESSIBILITY_SERVICES_SEPARATOR)) {
                    i14++;
                }
                if (str.length() <= i14 || (cCharAt2 = str.charAt(i14)) == 'Z' || cCharAt2 == '+' || cCharAt2 == '-') {
                    i = i14;
                } else {
                    i = i14 + 2;
                    i5 = parseInt(str, i14, i);
                    if (i5 > 59 && i5 < 63) {
                        i5 = 59;
                    }
                    if (checkOffset(str, i, '.')) {
                        int i15 = i + 1;
                        int iIndexOfNonDigit = indexOfNonDigit(str, i15 + 1);
                        int iMin = Math.min(iIndexOfNonDigit, i15 + 3);
                        int i16 = parseInt(str, i15, iMin);
                        switch (iMin - i15) {
                            case 1:
                                i16 *= 100;
                                break;
                            case 2:
                                i16 *= 10;
                                break;
                        }
                        i4 = i16;
                        i = iIndexOfNonDigit;
                    } else {
                        i4 = 0;
                    }
                }
                if (str.length() <= i) {
                    throw new IllegalArgumentException("No time zone indicator");
                }
                cCharAt = str.charAt(i);
                if (cCharAt == 'Z') {
                    timeZone = TIMEZONE_UTC;
                    length = i + 1;
                } else {
                    if (cCharAt != '+' && cCharAt != '-') {
                        throw new IndexOutOfBoundsException("Invalid time zone indicator '" + cCharAt + "'");
                    }
                    strSubstring = str.substring(i);
                    if (strSubstring.length() >= 5) {
                        strSubstring = strSubstring + "00";
                    }
                    length = i + strSubstring.length();
                    if (!"+0000".equals(strSubstring) || "+00:00".equals(strSubstring)) {
                        timeZone = TIMEZONE_UTC;
                    } else {
                        String str3 = "GMT" + strSubstring;
                        TimeZone timeZone2 = TimeZone.getTimeZone(str3);
                        String id = timeZone2.getID();
                        if (!id.equals(str3) && !id.replace(":", "").equals(str3)) {
                            throw new IndexOutOfBoundsException("Mismatching time zone indicator: " + str3 + " given, resolves to " + timeZone2.getID());
                        }
                        timeZone = timeZone2;
                    }
                }
                GregorianCalendar gregorianCalendar2 = new GregorianCalendar(timeZone);
                gregorianCalendar2.setLenient(false);
                gregorianCalendar2.set(1, i7);
                gregorianCalendar2.set(2, i9 - 1);
                gregorianCalendar2.set(5, i11);
                gregorianCalendar2.set(11, i2);
                gregorianCalendar2.set(12, i3);
                gregorianCalendar2.set(13, i5);
                gregorianCalendar2.set(14, i4);
                parsePosition.setIndex(length);
                return gregorianCalendar2.getTime();
            }
            i = i10;
            i2 = 0;
            i3 = 0;
            i4 = 0;
            i5 = 0;
            if (str.length() <= i) {
                throw new IllegalArgumentException("No time zone indicator");
            }
            cCharAt = str.charAt(i);
            if (cCharAt == 'Z') {
                timeZone = TIMEZONE_UTC;
                length = i + 1;
            } else {
                if (cCharAt != '+') {
                    throw new IndexOutOfBoundsException("Invalid time zone indicator '" + cCharAt + "'");
                }
                strSubstring = str.substring(i);
                if (strSubstring.length() >= 5) {
                    strSubstring = strSubstring + "00";
                }
                length = i + strSubstring.length();
                if (!"+0000".equals(strSubstring)) {
                    timeZone = TIMEZONE_UTC;
                } else {
                    timeZone = TIMEZONE_UTC;
                }
            }
            GregorianCalendar gregorianCalendar3 = new GregorianCalendar(timeZone);
            gregorianCalendar3.setLenient(false);
            gregorianCalendar3.set(1, i7);
            gregorianCalendar3.set(2, i9 - 1);
            gregorianCalendar3.set(5, i11);
            gregorianCalendar3.set(11, i2);
            gregorianCalendar3.set(12, i3);
            gregorianCalendar3.set(13, i5);
            gregorianCalendar3.set(14, i4);
            parsePosition.setIndex(length);
            return gregorianCalendar3.getTime();
        } catch (IllegalArgumentException e) {
            exc = e;
            if (str == null) {
                str2 = null;
            } else {
                str2 = '\"' + str + "'";
            }
            message = exc.getMessage();
            if (message != null || message.isEmpty()) {
                message = "(" + exc.getClass().getName() + ")";
            }
            ParseException parseException = new ParseException("Failed to parse date [" + str2 + "]: " + message, parsePosition.getIndex());
            parseException.initCause(exc);
            throw parseException;
        } catch (IndexOutOfBoundsException e2) {
            exc = e2;
            if (str == null) {
                str2 = null;
            } else {
                str2 = '\"' + str + "'";
            }
            message = exc.getMessage();
            if (message != null) {
                message = "(" + exc.getClass().getName() + ")";
            } else {
                message = "(" + exc.getClass().getName() + ")";
            }
            ParseException parseException2 = new ParseException("Failed to parse date [" + str2 + "]: " + message, parsePosition.getIndex());
            parseException2.initCause(exc);
            throw parseException2;
        } catch (NumberFormatException e3) {
            exc = e3;
            if (str == null) {
                str2 = null;
            } else {
                str2 = '\"' + str + "'";
            }
            message = exc.getMessage();
            if (message != null) {
                message = "(" + exc.getClass().getName() + ")";
            } else {
                message = "(" + exc.getClass().getName() + ")";
            }
            ParseException parseException3 = new ParseException("Failed to parse date [" + str2 + "]: " + message, parsePosition.getIndex());
            parseException3.initCause(exc);
            throw parseException3;
        }
    }

    private static boolean checkOffset(String str, int i, char c) {
        return i < str.length() && str.charAt(i) == c;
    }

    private static int parseInt(String str, int i, int i2) throws NumberFormatException {
        int i3;
        int i4;
        if (i < 0 || i2 > str.length() || i > i2) {
            throw new NumberFormatException(str);
        }
        if (i < i2) {
            i4 = i + 1;
            int iDigit = Character.digit(str.charAt(i), 10);
            if (iDigit < 0) {
                throw new NumberFormatException("Invalid number: " + str.substring(i, i2));
            }
            i3 = -iDigit;
        } else {
            i3 = 0;
            i4 = i;
        }
        while (i4 < i2) {
            int i5 = i4 + 1;
            int iDigit2 = Character.digit(str.charAt(i4), 10);
            if (iDigit2 < 0) {
                throw new NumberFormatException("Invalid number: " + str.substring(i, i2));
            }
            i3 = (i3 * 10) - iDigit2;
            i4 = i5;
        }
        return -i3;
    }

    private static void padInt(StringBuilder sb, int i, int i2) {
        String string = Integer.toString(i);
        for (int length = i2 - string.length(); length > 0; length--) {
            sb.append('0');
        }
        sb.append(string);
    }

    private static int indexOfNonDigit(String str, int i) {
        while (i < str.length()) {
            char cCharAt = str.charAt(i);
            if (cCharAt < '0' || cCharAt > '9') {
                return i;
            }
            i++;
        }
        return str.length();
    }
}
