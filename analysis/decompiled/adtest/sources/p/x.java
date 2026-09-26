package p;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class x {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final x f1729d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final x f1730e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final /* synthetic */ x[] f1731f;

    static {
        x xVar = new x("DEFERRED", 0);
        f1729d = xVar;
        x xVar2 = new x("IMMEDIATE", 1);
        f1730e = xVar2;
        f1731f = new x[]{xVar, xVar2, new x("EXCLUSIVE", 2)};
    }

    public static x valueOf(String str) {
        return (x) Enum.valueOf(x.class, str);
    }

    public static x[] values() {
        return (x[]) f1731f.clone();
    }
}
