package p;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class r {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final r f1709d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final r f1710e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final r f1711f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final /* synthetic */ r[] f1712g;

    static {
        r rVar = new r("AUTOMATIC", 0);
        f1709d = rVar;
        r rVar2 = new r("TRUNCATE", 1);
        f1710e = rVar2;
        r rVar3 = new r("WRITE_AHEAD_LOGGING", 2);
        f1711f = rVar3;
        f1712g = new r[]{rVar, rVar2, rVar3};
    }

    public static r valueOf(String str) {
        return (r) Enum.valueOf(r.class, str);
    }

    public static r[] values() {
        return (r[]) f1712g.clone();
    }
}
