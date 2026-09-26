package androidx.core.c;

import android.util.Base64;
import androidx.core.e.e;
import java.util.List;

/* JADX INFO: compiled from: FontRequest.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f970a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f971b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final String f972c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final List<List<byte[]>> f973d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final int f974e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final String f975f;

    public a(String str, String str2, String str3, List<List<byte[]>> list) {
        e.a(str);
        this.f970a = str;
        e.a(str2);
        this.f971b = str2;
        e.a(str3);
        this.f972c = str3;
        e.a(list);
        this.f973d = list;
        this.f974e = 0;
        this.f975f = this.f970a + "-" + this.f971b + "-" + this.f972c;
    }

    public List<List<byte[]>> a() {
        return this.f973d;
    }

    public int b() {
        return this.f974e;
    }

    public String c() {
        return this.f975f;
    }

    public String d() {
        return this.f970a;
    }

    public String e() {
        return this.f971b;
    }

    public String f() {
        return this.f972c;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("FontRequest {mProviderAuthority: " + this.f970a + ", mProviderPackage: " + this.f971b + ", mQuery: " + this.f972c + ", mCertificates:");
        for (int i = 0; i < this.f973d.size(); i++) {
            sb.append(" [");
            List<byte[]> list = this.f973d.get(i);
            for (int i2 = 0; i2 < list.size(); i2++) {
                sb.append(" \"");
                sb.append(Base64.encodeToString(list.get(i2), 0));
                sb.append("\"");
            }
            sb.append(" ]");
        }
        sb.append("}");
        sb.append("mCertificatesArray: " + this.f974e);
        return sb.toString();
    }
}
