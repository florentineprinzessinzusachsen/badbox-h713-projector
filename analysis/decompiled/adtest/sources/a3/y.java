package a3;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public enum y {
    f271f("http/1.0"),
    f272g("http/1.1"),
    f273h("spdy/3.1"),
    f274i("h2"),
    f275j("h2_prior_knowledge"),
    f276k("quic"),
    f277l("h3");


    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final b f270e = new b();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final String f279d;

    y(String str) {
        this.f279d = str;
    }

    @Override // java.lang.Enum
    public final String toString() {
        return this.f279d;
    }
}
