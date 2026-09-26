package d0;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class b0 {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final b0 f417d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final b0 f418e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final b0 f419f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final b0 f420g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final b0 f421h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final b0 f422i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final /* synthetic */ b0[] f423j;

    static {
        b0 b0Var = new b0("NOT_REQUIRED", 0);
        f417d = b0Var;
        b0 b0Var2 = new b0("CONNECTED", 1);
        f418e = b0Var2;
        b0 b0Var3 = new b0("UNMETERED", 2);
        f419f = b0Var3;
        b0 b0Var4 = new b0("NOT_ROAMING", 3);
        f420g = b0Var4;
        b0 b0Var5 = new b0("METERED", 4);
        f421h = b0Var5;
        b0 b0Var6 = new b0("TEMPORARILY_UNMETERED", 5);
        f422i = b0Var6;
        f423j = new b0[]{b0Var, b0Var2, b0Var3, b0Var4, b0Var5, b0Var6};
    }

    public static b0 valueOf(String str) {
        return (b0) Enum.valueOf(b0.class, str);
    }

    public static b0[] values() {
        return (b0[]) f423j.clone();
    }
}
