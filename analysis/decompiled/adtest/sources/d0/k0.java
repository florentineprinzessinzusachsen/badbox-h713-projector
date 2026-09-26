package d0;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class k0 {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final k0 f467d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final k0 f468e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final k0 f469f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final k0 f470g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final k0 f471h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final k0 f472i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final /* synthetic */ k0[] f473j;

    static {
        k0 k0Var = new k0("ENQUEUED", 0);
        f467d = k0Var;
        k0 k0Var2 = new k0("RUNNING", 1);
        f468e = k0Var2;
        k0 k0Var3 = new k0("SUCCEEDED", 2);
        f469f = k0Var3;
        k0 k0Var4 = new k0("FAILED", 3);
        f470g = k0Var4;
        k0 k0Var5 = new k0("BLOCKED", 4);
        f471h = k0Var5;
        k0 k0Var6 = new k0("CANCELLED", 5);
        f472i = k0Var6;
        f473j = new k0[]{k0Var, k0Var2, k0Var3, k0Var4, k0Var5, k0Var6};
    }

    public static k0 valueOf(String str) {
        return (k0) Enum.valueOf(k0.class, str);
    }

    public static k0[] values() {
        return (k0[]) f473j.clone();
    }

    public final boolean a() {
        return this == f469f || this == f470g || this == f472i;
    }
}
