package f3;

import a3.y;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final y f909a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f910b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final String f911c;

    public k(y yVar, int i4, String str) {
        this.f909a = yVar;
        this.f910b = i4;
        this.f911c = str;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.f909a == y.f271f) {
            sb.append("HTTP/1.0");
        } else {
            sb.append("HTTP/1.1");
        }
        sb.append(' ');
        sb.append(this.f910b);
        sb.append(' ');
        sb.append(this.f911c);
        return sb.toString();
    }
}
