package d3;

import j2.i;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public abstract class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f527a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f528b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public c f529c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public long f530d;

    public a(String str) {
        i.e(str, "name");
        this.f527a = str;
        this.f528b = true;
        this.f530d = -1L;
    }

    public abstract long a();

    public final String toString() {
        return this.f527a;
    }
}
