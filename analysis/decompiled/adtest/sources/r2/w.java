package r2;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class w {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final w f2033d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final w f2034e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final w f2035f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final w f2036g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final /* synthetic */ w[] f2037h;

    static {
        w wVar = new w("DEFAULT", 0);
        f2033d = wVar;
        w wVar2 = new w("LAZY", 1);
        f2034e = wVar2;
        w wVar3 = new w("ATOMIC", 2);
        f2035f = wVar3;
        w wVar4 = new w("UNDISPATCHED", 3);
        f2036g = wVar4;
        f2037h = new w[]{wVar, wVar2, wVar3, wVar4};
    }

    public static w valueOf(String str) {
        return (w) Enum.valueOf(w.class, str);
    }

    public static w[] values() {
        return (w[]) f2037h.clone();
    }
}
