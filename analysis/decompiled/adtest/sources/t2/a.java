package t2;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final a f2174d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final a f2175e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final a f2176f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final /* synthetic */ a[] f2177g;

    static {
        a aVar = new a("SUSPEND", 0);
        f2174d = aVar;
        a aVar2 = new a("DROP_OLDEST", 1);
        f2175e = aVar2;
        a aVar3 = new a("DROP_LATEST", 2);
        f2176f = aVar3;
        f2177g = new a[]{aVar, aVar2, aVar3};
    }

    public static a valueOf(String str) {
        return (a) Enum.valueOf(a.class, str);
    }

    public static a[] values() {
        return (a[]) f2177g.clone();
    }
}
