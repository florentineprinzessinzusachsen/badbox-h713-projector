package ddth2.hidden;

import java.net.InetAddress;

/* JADX INFO: loaded from: classes.dex */
public abstract class P {
    public static InetAddress a(String str) {
        InetAddress byAddress;
        if (str != null && str.length() != 0) {
            for (int i = 0; i < str.length(); i++) {
                char cCharAt = str.charAt(i);
                if ((cCharAt < '0' || cCharAt > '9') && cCharAt != '.' && cCharAt != ':' && ((cCharAt < 'a' || cCharAt > 'f') && (cCharAt < 'A' || cCharAt > 'F'))) {
                    return null;
                }
            }
            if (str.indexOf(46) >= 0 || str.indexOf(58) >= 0) {
                byte[] bArr = new byte[4];
                int i2 = 0;
                int i3 = 0;
                int i4 = 0;
                int i5 = 0;
                while (true) {
                    if (i2 >= str.length()) {
                        if (i3 != 0 && i4 == 3) {
                            bArr[i4] = (byte) i5;
                            try {
                                byAddress = InetAddress.getByAddress(bArr);
                                break;
                            } catch (Exception unused) {
                                byAddress = null;
                                break;
                            }
                        }
                        break;
                    }
                    char cCharAt2 = str.charAt(i2);
                    if (cCharAt2 >= '0' && cCharAt2 <= '9') {
                        int i6 = (cCharAt2 - '0') + (i5 * 10);
                        i3++;
                        if (i6 <= 255 && i3 <= 3) {
                            i5 = i6;
                            i2++;
                        }
                    } else if (cCharAt2 == '.' && i3 != 0 && i4 < 3) {
                        bArr[i4] = (byte) i5;
                        i4++;
                        i3 = 0;
                        i5 = 0;
                        i2++;
                    }
                    byAddress = null;
                    break;
                }
                if (byAddress != null) {
                    return byAddress;
                }
                if (str.indexOf(58) < 0) {
                    return null;
                }
                try {
                    return InetAddress.getByName(str);
                } catch (Exception unused2) {
                }
            }
            return null;
        }
        return null;
    }
}
