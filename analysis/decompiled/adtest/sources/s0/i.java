package s0;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class i {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final i f2087d = new i("", "", false);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final i f2088e = new i("\n", "  ", true);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f2089a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f2090b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final boolean f2091c;

    public i(String str, String str2, boolean z3) {
        if (!str.matches("[\r\n]*")) {
            throw new IllegalArgumentException("Only combinations of \\n and \\r are allowed in newline.");
        }
        if (!str2.matches("[ \t]*")) {
            throw new IllegalArgumentException("Only combinations of spaces and tabs are allowed in indent.");
        }
        this.f2089a = str;
        this.f2090b = str2;
        this.f2091c = z3;
    }
}
