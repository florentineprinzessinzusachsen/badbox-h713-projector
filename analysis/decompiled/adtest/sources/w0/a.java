package w0;

import java.text.ParseException;
import java.text.ParsePosition;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public abstract class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final TimeZone f2567a = TimeZone.getTimeZone("UTC");

    public static boolean a(String str, int i4, char c4) {
        return i4 < str.length() && str.charAt(i4) == c4;
    }

    /* JADX WARN: Code duplicated, block: B:101:0x0205  */
    /* JADX WARN: Code duplicated, block: B:64:0x00f7 A[Catch: IllegalArgumentException -> 0x004e, IndexOutOfBoundsException -> 0x0051, TRY_LEAVE, TryCatch #2 {IllegalArgumentException -> 0x004e, IndexOutOfBoundsException -> 0x0051, blocks: (B:3:0x0004, B:5:0x0017, B:6:0x0019, B:8:0x0025, B:9:0x0027, B:11:0x0037, B:13:0x003d, B:21:0x005b, B:23:0x006b, B:24:0x006d, B:26:0x0079, B:27:0x007c, B:29:0x0082, B:33:0x008c, B:38:0x009c, B:40:0x00a4, B:41:0x00a8, B:43:0x00ae, B:48:0x00bb, B:51:0x00c6, B:62:0x00f1, B:64:0x00f7, B:90:0x01a9, B:72:0x0109, B:73:0x0124, B:74:0x0125, B:78:0x0142, B:80:0x014f, B:83:0x0158, B:85:0x0177, B:88:0x0186, B:89:0x01a8, B:77:0x0131, B:92:0x01da, B:93:0x01e1, B:55:0x00d6, B:56:0x00d9, B:50:0x00c2), top: B:104:0x0004 }] */
    /* JADX WARN: Code duplicated, block: B:67:0x00ff  */
    /* JADX WARN: Code duplicated, block: B:68:0x0102  */
    /* JADX WARN: Code duplicated, block: B:76:0x0130  */
    /* JADX WARN: Code duplicated, block: B:77:0x0131 A[Catch: IllegalArgumentException -> 0x004e, IndexOutOfBoundsException -> 0x0051, TryCatch #2 {IllegalArgumentException -> 0x004e, IndexOutOfBoundsException -> 0x0051, blocks: (B:3:0x0004, B:5:0x0017, B:6:0x0019, B:8:0x0025, B:9:0x0027, B:11:0x0037, B:13:0x003d, B:21:0x005b, B:23:0x006b, B:24:0x006d, B:26:0x0079, B:27:0x007c, B:29:0x0082, B:33:0x008c, B:38:0x009c, B:40:0x00a4, B:41:0x00a8, B:43:0x00ae, B:48:0x00bb, B:51:0x00c6, B:62:0x00f1, B:64:0x00f7, B:90:0x01a9, B:72:0x0109, B:73:0x0124, B:74:0x0125, B:78:0x0142, B:80:0x014f, B:83:0x0158, B:85:0x0177, B:88:0x0186, B:89:0x01a8, B:77:0x0131, B:92:0x01da, B:93:0x01e1, B:55:0x00d6, B:56:0x00d9, B:50:0x00c2), top: B:104:0x0004 }] */
    /* JADX WARN: Code duplicated, block: B:92:0x01da A[Catch: IllegalArgumentException -> 0x004e, IndexOutOfBoundsException -> 0x0051, TryCatch #2 {IllegalArgumentException -> 0x004e, IndexOutOfBoundsException -> 0x0051, blocks: (B:3:0x0004, B:5:0x0017, B:6:0x0019, B:8:0x0025, B:9:0x0027, B:11:0x0037, B:13:0x003d, B:21:0x005b, B:23:0x006b, B:24:0x006d, B:26:0x0079, B:27:0x007c, B:29:0x0082, B:33:0x008c, B:38:0x009c, B:40:0x00a4, B:41:0x00a8, B:43:0x00ae, B:48:0x00bb, B:51:0x00c6, B:62:0x00f1, B:64:0x00f7, B:90:0x01a9, B:72:0x0109, B:73:0x0124, B:74:0x0125, B:78:0x0142, B:80:0x014f, B:83:0x0158, B:85:0x0177, B:88:0x0186, B:89:0x01a8, B:77:0x0131, B:92:0x01da, B:93:0x01e1, B:55:0x00d6, B:56:0x00d9, B:50:0x00c2), top: B:104:0x0004 }] */
    /* JADX WARN: Code duplicated, block: B:95:0x01e4  */
    /* JADX WARN: Code duplicated, block: B:96:0x01e6  */
    /* JADX WARN: Instruction removed from duplicated block: B:101:0x0205, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:77:0x0131, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:96:0x01e6, please report this as an issue */
    public static Date b(String str, ParsePosition parsePosition) {
        String str2;
        String message;
        int i4;
        int i5;
        int iC;
        int iC2;
        char cCharAt;
        TimeZone timeZone;
        String strSubstring;
        int length;
        String str3;
        String id;
        char cCharAt2;
        int length2;
        try {
            int index = parsePosition.getIndex();
            int i6 = index + 4;
            int iC3 = c(str, index, i6);
            if (a(str, i6, '-')) {
                i6 = index + 5;
            }
            int i7 = i6 + 2;
            int iC4 = c(str, i6, i7);
            if (a(str, i7, '-')) {
                i7 = i6 + 3;
            }
            int i8 = i7 + 2;
            int iC5 = c(str, i7, i8);
            boolean zA = a(str, i8, 'T');
            if (!zA && str.length() <= i8) {
                GregorianCalendar gregorianCalendar = new GregorianCalendar(iC3, iC4 - 1, iC5);
                gregorianCalendar.setLenient(false);
                parsePosition.setIndex(i8);
                return gregorianCalendar.getTime();
            }
            if (zA) {
                int i9 = i7 + 5;
                int iC6 = c(str, i7 + 3, i9);
                if (a(str, i9, ':')) {
                    i9 = i7 + 6;
                }
                int i10 = i9 + 2;
                int iC7 = c(str, i9, i10);
                if (a(str, i10, ':')) {
                    i10 = i9 + 3;
                }
                if (str.length() <= i10 || (cCharAt2 = str.charAt(i10)) == 'Z' || cCharAt2 == '+' || cCharAt2 == '-') {
                    i8 = i10;
                    i4 = iC6;
                    i5 = iC7;
                } else {
                    int i11 = i10 + 2;
                    iC2 = c(str, i10, i11);
                    if (iC2 > 59 && iC2 < 63) {
                        iC2 = 59;
                    }
                    if (a(str, i11, '.')) {
                        int i12 = i10 + 3;
                        int i13 = i10 + 4;
                        while (true) {
                            if (i13 >= str.length()) {
                                length2 = str.length();
                                break;
                            }
                            char cCharAt3 = str.charAt(i13);
                            if (cCharAt3 >= '0' && cCharAt3 <= '9') {
                                i13++;
                            }
                            length2 = i13;
                            break;
                        }
                        int iMin = Math.min(length2, i10 + 6);
                        iC = c(str, i12, iMin);
                        int i14 = iMin - i12;
                        if (i14 == 1) {
                            iC *= 100;
                        } else if (i14 == 2) {
                            iC *= 10;
                        }
                        i4 = iC6;
                        i8 = length2;
                        i5 = iC7;
                    } else {
                        i4 = iC6;
                        i8 = i11;
                        i5 = iC7;
                        iC = 0;
                    }
                }
                if (str.length() > i8) {
                    throw new IllegalArgumentException("No time zone indicator");
                }
                cCharAt = str.charAt(i8);
                timeZone = f2567a;
                if (cCharAt == 'Z') {
                    length = i8 + 1;
                } else {
                    if (cCharAt == '+' && cCharAt != '-') {
                        throw new IndexOutOfBoundsException("Invalid time zone indicator '" + cCharAt + "'");
                    }
                    strSubstring = str.substring(i8);
                    if (strSubstring.length() >= 5) {
                        strSubstring = strSubstring + "00";
                    }
                    length = i8 + strSubstring.length();
                    if (!strSubstring.equals("+0000") && !strSubstring.equals("+00:00")) {
                        str3 = "GMT" + strSubstring;
                        timeZone = TimeZone.getTimeZone(str3);
                        id = timeZone.getID();
                        if (!id.equals(str3) && !id.replace(":", "").equals(str3)) {
                            throw new IndexOutOfBoundsException("Mismatching time zone indicator: " + str3 + " given, resolves to " + timeZone.getID());
                        }
                    }
                }
                GregorianCalendar gregorianCalendar2 = new GregorianCalendar(timeZone);
                gregorianCalendar2.setLenient(false);
                gregorianCalendar2.set(1, iC3);
                gregorianCalendar2.set(2, iC4 - 1);
                gregorianCalendar2.set(5, iC5);
                gregorianCalendar2.set(11, i4);
                gregorianCalendar2.set(12, i5);
                gregorianCalendar2.set(13, iC2);
                gregorianCalendar2.set(14, iC);
                parsePosition.setIndex(length);
                return gregorianCalendar2.getTime();
            }
            i4 = 0;
            i5 = 0;
            iC = 0;
            iC2 = 0;
            if (str.length() > i8) {
                throw new IllegalArgumentException("No time zone indicator");
            }
            cCharAt = str.charAt(i8);
            timeZone = f2567a;
            if (cCharAt == 'Z') {
                length = i8 + 1;
            } else {
                if (cCharAt == '+') {
                }
                strSubstring = str.substring(i8);
                if (strSubstring.length() >= 5) {
                    strSubstring = strSubstring + "00";
                }
                length = i8 + strSubstring.length();
                if (!strSubstring.equals("+0000")) {
                    str3 = "GMT" + strSubstring;
                    timeZone = TimeZone.getTimeZone(str3);
                    id = timeZone.getID();
                    if (!id.equals(str3)) {
                        throw new IndexOutOfBoundsException("Mismatching time zone indicator: " + str3 + " given, resolves to " + timeZone.getID());
                    }
                }
            }
            GregorianCalendar gregorianCalendar3 = new GregorianCalendar(timeZone);
            gregorianCalendar3.setLenient(false);
            gregorianCalendar3.set(1, iC3);
            gregorianCalendar3.set(2, iC4 - 1);
            gregorianCalendar3.set(5, iC5);
            gregorianCalendar3.set(11, i4);
            gregorianCalendar3.set(12, i5);
            gregorianCalendar3.set(13, iC2);
            gregorianCalendar3.set(14, iC);
            parsePosition.setIndex(length);
            return gregorianCalendar3.getTime();
        } catch (IllegalArgumentException e4) {
            e = e4;
            if (str == null) {
                str2 = null;
            } else {
                str2 = "\"" + str + '\"';
            }
            message = e.getMessage();
            if (message != null || message.isEmpty()) {
                message = "(" + e.getClass().getName() + ")";
            }
            ParseException parseException = new ParseException("Failed to parse date [" + str2 + "]: " + message, parsePosition.getIndex());
            parseException.initCause(e);
            throw parseException;
        } catch (IndexOutOfBoundsException e5) {
            e = e5;
            if (str == null) {
                str2 = null;
            } else {
                str2 = "\"" + str + '\"';
            }
            message = e.getMessage();
            if (message != null) {
                message = "(" + e.getClass().getName() + ")";
            } else {
                message = "(" + e.getClass().getName() + ")";
            }
            ParseException parseException2 = new ParseException("Failed to parse date [" + str2 + "]: " + message, parsePosition.getIndex());
            parseException2.initCause(e);
            throw parseException2;
        }
    }

    public static int c(String str, int i4, int i5) {
        int i6;
        int i7;
        if (i4 < 0 || i5 > str.length() || i4 > i5) {
            throw new NumberFormatException(str);
        }
        if (i4 < i5) {
            i7 = i4 + 1;
            int iDigit = Character.digit(str.charAt(i4), 10);
            if (iDigit < 0) {
                throw new NumberFormatException("Invalid number: " + str.substring(i4, i5));
            }
            i6 = -iDigit;
        } else {
            i6 = 0;
            i7 = i4;
        }
        while (i7 < i5) {
            int i8 = i7 + 1;
            int iDigit2 = Character.digit(str.charAt(i7), 10);
            if (iDigit2 < 0) {
                throw new NumberFormatException("Invalid number: " + str.substring(i4, i5));
            }
            i6 = (i6 * 10) - iDigit2;
            i7 = i8;
        }
        return -i6;
    }
}
