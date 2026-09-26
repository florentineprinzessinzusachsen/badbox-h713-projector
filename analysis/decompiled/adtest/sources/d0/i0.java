package d0;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class i0 {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final i0 f461d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final i0 f462e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final /* synthetic */ i0[] f463f;

    static {
        i0 i0Var = new i0("RUN_AS_NON_EXPEDITED_WORK_REQUEST", 0);
        f461d = i0Var;
        i0 i0Var2 = new i0("DROP_WORK_REQUEST", 1);
        f462e = i0Var2;
        f463f = new i0[]{i0Var, i0Var2};
    }

    public static i0 valueOf(String str) {
        return (i0) Enum.valueOf(i0.class, str);
    }

    public static i0[] values() {
        return (i0[]) f463f.clone();
    }
}
