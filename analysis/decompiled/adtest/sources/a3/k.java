package a3;

import java.text.DateFormat;
import java.util.Date;
import java.util.regex.Pattern;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class k {

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final Pattern f169k = Pattern.compile("(\\d{2,4})[^\\d]*");

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final Pattern f170l = Pattern.compile("(?i)(jan|feb|mar|apr|may|jun|jul|aug|sep|oct|nov|dec).*");

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final Pattern f171m = Pattern.compile("(\\d{1,2})[^\\d]*");

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final Pattern f172n = Pattern.compile("(\\d{1,2}):(\\d{1,2}):(\\d{1,2})[^\\d]*");

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f173a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f174b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final long f175c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final String f176d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final String f177e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final boolean f178f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final boolean f179g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final boolean f180h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final boolean f181i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final String f182j;

    public k(String str, String str2, long j4, String str3, String str4, boolean z3, boolean z4, boolean z5, boolean z6, String str5) {
        this.f173a = str;
        this.f174b = str2;
        this.f175c = j4;
        this.f176d = str3;
        this.f177e = str4;
        this.f178f = z3;
        this.f179g = z4;
        this.f180h = z5;
        this.f181i = z6;
        this.f182j = str5;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof k)) {
            return false;
        }
        k kVar = (k) obj;
        return j2.i.a(kVar.f173a, this.f173a) && j2.i.a(kVar.f174b, this.f174b) && kVar.f175c == this.f175c && j2.i.a(kVar.f176d, this.f176d) && j2.i.a(kVar.f177e, this.f177e) && kVar.f178f == this.f178f && kVar.f179g == this.f179g && kVar.f180h == this.f180h && kVar.f181i == this.f181i && j2.i.a(kVar.f182j, this.f182j);
    }

    public final int hashCode() {
        int iHashCode = (this.f174b.hashCode() + ((this.f173a.hashCode() + 527) * 31)) * 31;
        long j4 = this.f175c;
        int iHashCode2 = (((((((((this.f177e.hashCode() + ((this.f176d.hashCode() + ((iHashCode + ((int) (j4 ^ (j4 >>> 32)))) * 31)) * 31)) * 31) + (this.f178f ? 1231 : 1237)) * 31) + (this.f179g ? 1231 : 1237)) * 31) + (this.f180h ? 1231 : 1237)) * 31) + (this.f181i ? 1231 : 1237)) * 31;
        String str = this.f182j;
        return iHashCode2 + (str != null ? str.hashCode() : 0);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.f173a);
        sb.append('=');
        sb.append(this.f174b);
        if (this.f180h) {
            long j4 = this.f175c;
            if (j4 == Long.MIN_VALUE) {
                sb.append("; max-age=0");
            } else {
                sb.append("; expires=");
                String str = ((DateFormat) f3.e.f896a.get()).format(new Date(j4));
                j2.i.d(str, "format(...)");
                sb.append(str);
            }
        }
        if (!this.f181i) {
            sb.append("; domain=");
            sb.append(this.f176d);
        }
        sb.append("; path=");
        sb.append(this.f177e);
        if (this.f178f) {
            sb.append("; secure");
        }
        if (this.f179g) {
            sb.append("; httponly");
        }
        String str2 = this.f182j;
        if (str2 != null) {
            sb.append("; samesite=");
            sb.append(str2);
        }
        String string = sb.toString();
        j2.i.d(string, "toString(...)");
        return string;
    }
}
