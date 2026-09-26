package a3;

import d0.l0;
import java.nio.charset.Charset;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class v {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final p2.h f216c = new p2.h("([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)/([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)");

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final p2.h f217d = new p2.h(";\\s*(?:([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)=(?:([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)|\"([^\"]*)\"))?");

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f218a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String[] f219b;

    public v(String str, String str2, String str3, String[] strArr) {
        j2.i.e(str, "mediaType");
        j2.i.e(strArr, "parameterNamesAndValues");
        this.f218a = str;
        this.f219b = strArr;
    }

    public final Charset a(Charset charset) {
        String str;
        String[] strArr = this.f219b;
        int i4 = 0;
        int iW = l0.w(0, strArr.length - 1, 2);
        if (iW < 0) {
            str = null;
            break;
        }
        while (true) {
            if (!p2.p.v0(strArr[i4], "charset")) {
                if (i4 == iW) {
                    str = null;
                    break;
                }
                i4 += 2;
            } else {
                str = strArr[i4 + 1];
                break;
            }
        }
        if (str == null) {
            return charset;
        }
        try {
            return Charset.forName(str);
        } catch (IllegalArgumentException unused) {
            return charset;
        }
    }

    public final boolean equals(Object obj) {
        return (obj instanceof v) && j2.i.a(((v) obj).f218a, this.f218a);
    }

    public final int hashCode() {
        return this.f218a.hashCode();
    }

    public final String toString() {
        return this.f218a;
    }
}
