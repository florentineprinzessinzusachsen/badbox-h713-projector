package d.h0.g;

import d.y;
import java.net.ProtocolException;

/* JADX INFO: compiled from: StatusLine.java */
/* JADX INFO: loaded from: classes.dex */
public final class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final y f4430a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f4431b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final String f4432c;

    public k(y yVar, int i, String str) {
        this.f4430a = yVar;
        this.f4431b = i;
        this.f4432c = str;
    }

    public static k a(String str) throws ProtocolException {
        y yVar;
        String strSubstring;
        int i = 9;
        if (str.startsWith("HTTP/1.")) {
            if (str.length() < 9 || str.charAt(8) != ' ') {
                throw new ProtocolException("Unexpected status line: " + str);
            }
            int iCharAt = str.charAt(7) - '0';
            if (iCharAt == 0) {
                yVar = y.HTTP_1_0;
            } else {
                if (iCharAt != 1) {
                    throw new ProtocolException("Unexpected status line: " + str);
                }
                yVar = y.HTTP_1_1;
            }
        } else {
            if (!str.startsWith("ICY ")) {
                throw new ProtocolException("Unexpected status line: " + str);
            }
            yVar = y.HTTP_1_0;
            i = 4;
        }
        int i2 = i + 3;
        if (str.length() < i2) {
            throw new ProtocolException("Unexpected status line: " + str);
        }
        try {
            int i3 = Integer.parseInt(str.substring(i, i2));
            if (str.length() <= i2) {
                strSubstring = "";
            } else {
                if (str.charAt(i2) != ' ') {
                    throw new ProtocolException("Unexpected status line: " + str);
                }
                strSubstring = str.substring(i + 4);
            }
            return new k(yVar, i3, strSubstring);
        } catch (NumberFormatException unused) {
            throw new ProtocolException("Unexpected status line: " + str);
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.f4430a == y.HTTP_1_0 ? "HTTP/1.0" : "HTTP/1.1");
        sb.append(' ');
        sb.append(this.f4431b);
        if (this.f4432c != null) {
            sb.append(' ');
            sb.append(this.f4432c);
        }
        return sb.toString();
    }
}
