package p2;

import java.nio.charset.Charset;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public abstract class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Charset f1738a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Charset f1739b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final Charset f1740c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static volatile Charset f1741d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static volatile Charset f1742e;

    static {
        Charset charsetForName = Charset.forName("UTF-8");
        j2.i.d(charsetForName, "forName(...)");
        f1738a = charsetForName;
        j2.i.d(Charset.forName("UTF-16"), "forName(...)");
        Charset charsetForName2 = Charset.forName("UTF-16BE");
        j2.i.d(charsetForName2, "forName(...)");
        f1739b = charsetForName2;
        Charset charsetForName3 = Charset.forName("UTF-16LE");
        j2.i.d(charsetForName3, "forName(...)");
        f1740c = charsetForName3;
        j2.i.d(Charset.forName("US-ASCII"), "forName(...)");
        j2.i.d(Charset.forName("ISO-8859-1"), "forName(...)");
    }
}
