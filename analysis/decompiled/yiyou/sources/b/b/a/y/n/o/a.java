package b.b.a.y.n.o;

import com.baidu.mobstat.Config;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/* JADX INFO: compiled from: ISO8601Utils.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final TimeZone f1701a = TimeZone.getTimeZone("UTC");

    /* JADX WARN: Code duplicated, block: B:49:0x00ca A[Catch: IllegalArgumentException | IndexOutOfBoundsException | NumberFormatException -> 0x01b7, NumberFormatException -> 0x01b9, IndexOutOfBoundsException -> 0x01bb, TryCatch #2 {IllegalArgumentException | IndexOutOfBoundsException | NumberFormatException -> 0x01b7, blocks: (B:3:0x0004, B:5:0x0016, B:6:0x0018, B:8:0x0024, B:9:0x0026, B:11:0x0035, B:13:0x003b, B:17:0x0050, B:19:0x0060, B:20:0x0062, B:22:0x006e, B:23:0x0070, B:25:0x0076, B:29:0x0080, B:34:0x0090, B:36:0x0098, B:47:0x00c4, B:49:0x00ca, B:51:0x00d1, B:75:0x017e, B:55:0x00db, B:56:0x00f6, B:57:0x00f7, B:61:0x0113, B:63:0x0120, B:66:0x0129, B:68:0x0148, B:71:0x0157, B:72:0x0179, B:74:0x017c, B:60:0x0102, B:77:0x01af, B:78:0x01b6, B:40:0x00b2, B:41:0x00b5), top: B:95:0x0004 }] */
    /* JADX WARN: Code duplicated, block: B:51:0x00d1 A[Catch: IllegalArgumentException | IndexOutOfBoundsException | NumberFormatException -> 0x01b7, NumberFormatException -> 0x01b9, IndexOutOfBoundsException -> 0x01bb, TryCatch #2 {IllegalArgumentException | IndexOutOfBoundsException | NumberFormatException -> 0x01b7, blocks: (B:3:0x0004, B:5:0x0016, B:6:0x0018, B:8:0x0024, B:9:0x0026, B:11:0x0035, B:13:0x003b, B:17:0x0050, B:19:0x0060, B:20:0x0062, B:22:0x006e, B:23:0x0070, B:25:0x0076, B:29:0x0080, B:34:0x0090, B:36:0x0098, B:47:0x00c4, B:49:0x00ca, B:51:0x00d1, B:75:0x017e, B:55:0x00db, B:56:0x00f6, B:57:0x00f7, B:61:0x0113, B:63:0x0120, B:66:0x0129, B:68:0x0148, B:71:0x0157, B:72:0x0179, B:74:0x017c, B:60:0x0102, B:77:0x01af, B:78:0x01b6, B:40:0x00b2, B:41:0x00b5), top: B:95:0x0004 }] */
    /* JADX WARN: Code duplicated, block: B:52:0x00d6  */
    /* JADX WARN: Code duplicated, block: B:53:0x00d8 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:59:0x0101  */
    /* JADX WARN: Code duplicated, block: B:60:0x0102 A[Catch: IllegalArgumentException | IndexOutOfBoundsException | NumberFormatException -> 0x01b7, NumberFormatException -> 0x01b9, IndexOutOfBoundsException -> 0x01bb, TryCatch #2 {IllegalArgumentException | IndexOutOfBoundsException | NumberFormatException -> 0x01b7, blocks: (B:3:0x0004, B:5:0x0016, B:6:0x0018, B:8:0x0024, B:9:0x0026, B:11:0x0035, B:13:0x003b, B:17:0x0050, B:19:0x0060, B:20:0x0062, B:22:0x006e, B:23:0x0070, B:25:0x0076, B:29:0x0080, B:34:0x0090, B:36:0x0098, B:47:0x00c4, B:49:0x00ca, B:51:0x00d1, B:75:0x017e, B:55:0x00db, B:56:0x00f6, B:57:0x00f7, B:61:0x0113, B:63:0x0120, B:66:0x0129, B:68:0x0148, B:71:0x0157, B:72:0x0179, B:74:0x017c, B:60:0x0102, B:77:0x01af, B:78:0x01b6, B:40:0x00b2, B:41:0x00b5), top: B:95:0x0004 }] */
    /* JADX WARN: Code duplicated, block: B:63:0x0120 A[Catch: IllegalArgumentException | IndexOutOfBoundsException | NumberFormatException -> 0x01b7, NumberFormatException -> 0x01b9, IndexOutOfBoundsException -> 0x01bb, TryCatch #2 {IllegalArgumentException | IndexOutOfBoundsException | NumberFormatException -> 0x01b7, blocks: (B:3:0x0004, B:5:0x0016, B:6:0x0018, B:8:0x0024, B:9:0x0026, B:11:0x0035, B:13:0x003b, B:17:0x0050, B:19:0x0060, B:20:0x0062, B:22:0x006e, B:23:0x0070, B:25:0x0076, B:29:0x0080, B:34:0x0090, B:36:0x0098, B:47:0x00c4, B:49:0x00ca, B:51:0x00d1, B:75:0x017e, B:55:0x00db, B:56:0x00f6, B:57:0x00f7, B:61:0x0113, B:63:0x0120, B:66:0x0129, B:68:0x0148, B:71:0x0157, B:72:0x0179, B:74:0x017c, B:60:0x0102, B:77:0x01af, B:78:0x01b6, B:40:0x00b2, B:41:0x00b5), top: B:95:0x0004 }] */
    /* JADX WARN: Code duplicated, block: B:74:0x017c A[Catch: IllegalArgumentException | IndexOutOfBoundsException | NumberFormatException -> 0x01b7, NumberFormatException -> 0x01b9, IndexOutOfBoundsException -> 0x01bb, TryCatch #2 {IllegalArgumentException | IndexOutOfBoundsException | NumberFormatException -> 0x01b7, blocks: (B:3:0x0004, B:5:0x0016, B:6:0x0018, B:8:0x0024, B:9:0x0026, B:11:0x0035, B:13:0x003b, B:17:0x0050, B:19:0x0060, B:20:0x0062, B:22:0x006e, B:23:0x0070, B:25:0x0076, B:29:0x0080, B:34:0x0090, B:36:0x0098, B:47:0x00c4, B:49:0x00ca, B:51:0x00d1, B:75:0x017e, B:55:0x00db, B:56:0x00f6, B:57:0x00f7, B:61:0x0113, B:63:0x0120, B:66:0x0129, B:68:0x0148, B:71:0x0157, B:72:0x0179, B:74:0x017c, B:60:0x0102, B:77:0x01af, B:78:0x01b6, B:40:0x00b2, B:41:0x00b5), top: B:95:0x0004 }] */
    /* JADX WARN: Code duplicated, block: B:77:0x01af A[Catch: IllegalArgumentException | IndexOutOfBoundsException | NumberFormatException -> 0x01b7, NumberFormatException -> 0x01b9, IndexOutOfBoundsException -> 0x01bb, TryCatch #2 {IllegalArgumentException | IndexOutOfBoundsException | NumberFormatException -> 0x01b7, blocks: (B:3:0x0004, B:5:0x0016, B:6:0x0018, B:8:0x0024, B:9:0x0026, B:11:0x0035, B:13:0x003b, B:17:0x0050, B:19:0x0060, B:20:0x0062, B:22:0x006e, B:23:0x0070, B:25:0x0076, B:29:0x0080, B:34:0x0090, B:36:0x0098, B:47:0x00c4, B:49:0x00ca, B:51:0x00d1, B:75:0x017e, B:55:0x00db, B:56:0x00f6, B:57:0x00f7, B:61:0x0113, B:63:0x0120, B:66:0x0129, B:68:0x0148, B:71:0x0157, B:72:0x0179, B:74:0x017c, B:60:0x0102, B:77:0x01af, B:78:0x01b6, B:40:0x00b2, B:41:0x00b5), top: B:95:0x0004 }] */
    /* JADX WARN: Instruction removed from duplicated block: B:60:0x0102, please report this as an issue */
    public static Date a(String str, ParsePosition parsePosition) throws ParseException {
        String str2;
        int i;
        int iA;
        int iA2;
        int i2;
        int i3;
        char cCharAt;
        String strSubstring;
        int length;
        TimeZone timeZone;
        char cCharAt2;
        try {
            int index = parsePosition.getIndex();
            int i4 = index + 4;
            int iA3 = a(str, index, i4);
            if (a(str, i4, '-')) {
                i4++;
            }
            int i5 = i4 + 2;
            int iA4 = a(str, i4, i5);
            if (a(str, i5, '-')) {
                i5++;
            }
            int i6 = i5 + 2;
            int iA5 = a(str, i5, i6);
            boolean zA = a(str, i6, 'T');
            if (!zA && str.length() <= i6) {
                GregorianCalendar gregorianCalendar = new GregorianCalendar(iA3, iA4 - 1, iA5);
                parsePosition.setIndex(i6);
                return gregorianCalendar.getTime();
            }
            if (zA) {
                int i7 = i6 + 1;
                int i8 = i7 + 2;
                iA = a(str, i7, i8);
                if (a(str, i8, ':')) {
                    i8++;
                }
                i = i8 + 2;
                iA2 = a(str, i8, i);
                if (a(str, i, ':')) {
                    i++;
                }
                if (str.length() > i && (cCharAt2 = str.charAt(i)) != 'Z' && cCharAt2 != '+' && cCharAt2 != '-') {
                    int i9 = i + 2;
                    int iA6 = a(str, i, i9);
                    if (iA6 > 59 && iA6 < 63) {
                        iA6 = 59;
                    }
                    if (a(str, i9, '.')) {
                        int i10 = i9 + 1;
                        int iA7 = a(str, i10 + 1);
                        int iMin = Math.min(iA7, i10 + 3);
                        int iA8 = a(str, i10, iMin);
                        int i11 = iMin - i10;
                        if (i11 == 1) {
                            iA8 *= 100;
                        } else if (i11 == 2) {
                            iA8 *= 10;
                        }
                        i3 = iA8;
                        i2 = iA6;
                        i = iA7;
                    } else {
                        i2 = iA6;
                        i = i9;
                    }
                    if (str.length() <= i) {
                        throw new IllegalArgumentException("No time zone indicator");
                    }
                    cCharAt = str.charAt(i);
                    if (cCharAt == 'Z') {
                        timeZone = f1701a;
                        length = i + 1;
                    } else {
                        if (cCharAt != '+' && cCharAt != '-') {
                            throw new IndexOutOfBoundsException("Invalid time zone indicator '" + cCharAt + "'");
                        }
                        strSubstring = str.substring(i);
                        if (strSubstring.length() < 5) {
                            strSubstring = strSubstring + "00";
                        }
                        length = i + strSubstring.length();
                        if (!"+0000".equals(strSubstring) || "+00:00".equals(strSubstring)) {
                            timeZone = f1701a;
                        } else {
                            String str3 = "GMT" + strSubstring;
                            TimeZone timeZone2 = TimeZone.getTimeZone(str3);
                            String id = timeZone2.getID();
                            if (!id.equals(str3) && !id.replace(Config.TRACE_TODAY_VISIT_SPLIT, "").equals(str3)) {
                                throw new IndexOutOfBoundsException("Mismatching time zone indicator: " + str3 + " given, resolves to " + timeZone2.getID());
                            }
                            timeZone = timeZone2;
                        }
                    }
                    GregorianCalendar gregorianCalendar2 = new GregorianCalendar(timeZone);
                    gregorianCalendar2.setLenient(false);
                    gregorianCalendar2.set(1, iA3);
                    gregorianCalendar2.set(2, iA4 - 1);
                    gregorianCalendar2.set(5, iA5);
                    gregorianCalendar2.set(11, iA);
                    gregorianCalendar2.set(12, iA2);
                    gregorianCalendar2.set(13, i2);
                    gregorianCalendar2.set(14, i3);
                    parsePosition.setIndex(length);
                    return gregorianCalendar2.getTime();
                }
                i3 = 0;
                if (str.length() <= i) {
                    throw new IllegalArgumentException("No time zone indicator");
                }
                cCharAt = str.charAt(i);
                if (cCharAt == 'Z') {
                    timeZone = f1701a;
                    length = i + 1;
                } else {
                    if (cCharAt != '+') {
                        throw new IndexOutOfBoundsException("Invalid time zone indicator '" + cCharAt + "'");
                    }
                    strSubstring = str.substring(i);
                    if (strSubstring.length() < 5) {
                        strSubstring = strSubstring + "00";
                    }
                    length = i + strSubstring.length();
                    if ("+0000".equals(strSubstring)) {
                        timeZone = f1701a;
                    } else {
                        timeZone = f1701a;
                    }
                }
                GregorianCalendar gregorianCalendar3 = new GregorianCalendar(timeZone);
                gregorianCalendar3.setLenient(false);
                gregorianCalendar3.set(1, iA3);
                gregorianCalendar3.set(2, iA4 - 1);
                gregorianCalendar3.set(5, iA5);
                gregorianCalendar3.set(11, iA);
                gregorianCalendar3.set(12, iA2);
                gregorianCalendar3.set(13, i2);
                gregorianCalendar3.set(14, i3);
                parsePosition.setIndex(length);
                return gregorianCalendar3.getTime();
            }
            i = i6;
            iA = 0;
            iA2 = 0;
            i2 = 0;
            i3 = 0;
            if (str.length() <= i) {
                throw new IllegalArgumentException("No time zone indicator");
            }
            cCharAt = str.charAt(i);
            if (cCharAt == 'Z') {
                timeZone = f1701a;
                length = i + 1;
            } else {
                if (cCharAt != '+') {
                    throw new IndexOutOfBoundsException("Invalid time zone indicator '" + cCharAt + "'");
                }
                strSubstring = str.substring(i);
                if (strSubstring.length() < 5) {
                    strSubstring = strSubstring + "00";
                }
                length = i + strSubstring.length();
                if ("+0000".equals(strSubstring)) {
                    timeZone = f1701a;
                } else {
                    timeZone = f1701a;
                }
            }
            GregorianCalendar gregorianCalendar4 = new GregorianCalendar(timeZone);
            gregorianCalendar4.setLenient(false);
            gregorianCalendar4.set(1, iA3);
            gregorianCalendar4.set(2, iA4 - 1);
            gregorianCalendar4.set(5, iA5);
            gregorianCalendar4.set(11, iA);
            gregorianCalendar4.set(12, iA2);
            gregorianCalendar4.set(13, i2);
            gregorianCalendar4.set(14, i3);
            parsePosition.setIndex(length);
            return gregorianCalendar4.getTime();
        } catch (IllegalArgumentException | IndexOutOfBoundsException | NumberFormatException e2) {
            if (str == null) {
                str2 = null;
            } else {
                str2 = '\"' + str + '\"';
            }
            String message = e2.getMessage();
            if (message == null || message.isEmpty()) {
                message = "(" + e2.getClass().getName() + ")";
            }
            ParseException parseException = new ParseException("Failed to parse date [" + str2 + "]: " + message, parsePosition.getIndex());
            parseException.initCause(e2);
            throw parseException;
        }
    }

    private static boolean a(String str, int i, char c2) {
        return i < str.length() && str.charAt(i) == c2;
    }

    private static int a(String str, int i, int i2) {
        int i3;
        int i4;
        if (i < 0 || i2 > str.length() || i > i2) {
            throw new NumberFormatException(str);
        }
        if (i < i2) {
            i3 = i + 1;
            int iDigit = Character.digit(str.charAt(i), 10);
            if (iDigit < 0) {
                throw new NumberFormatException("Invalid number: " + str.substring(i, i2));
            }
            i4 = -iDigit;
        } else {
            i3 = i;
            i4 = 0;
        }
        while (i3 < i2) {
            int i5 = i3 + 1;
            int iDigit2 = Character.digit(str.charAt(i3), 10);
            if (iDigit2 < 0) {
                throw new NumberFormatException("Invalid number: " + str.substring(i, i2));
            }
            i4 = (i4 * 10) - iDigit2;
            i3 = i5;
        }
        return -i4;
    }

    private static int a(String str, int i) {
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
