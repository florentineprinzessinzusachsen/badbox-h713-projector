package ddth2.hidden;

/* JADX INFO: loaded from: classes.dex */
public final class I {
    public final String a;
    public final int b;

    public I(int i, String str) {
        this.a = str;
        this.b = i;
    }

    public static I a(String str) {
        int iLastIndexOf = str.lastIndexOf(58);
        if (iLastIndexOf <= 0 || iLastIndexOf == str.length() - 1) {
            throw new IllegalArgumentException("gateway must be host:port");
        }
        int i = Integer.parseInt(str.substring(iLastIndexOf + 1));
        if (i <= 0 || i > 65535) {
            throw new IllegalArgumentException("gateway port invalid");
        }
        return new I(i, str.substring(0, iLastIndexOf));
    }

    public final String toString() {
        return this.a + ":" + this.b;
    }
}
