package d0;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class n {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final n f483d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final n f484e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final n f485f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final n f486g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final /* synthetic */ n[] f487h;

    static {
        n nVar = new n("REPLACE", 0);
        f483d = nVar;
        n nVar2 = new n("KEEP", 1);
        f484e = nVar2;
        n nVar3 = new n("APPEND", 2);
        f485f = nVar3;
        n nVar4 = new n("APPEND_OR_REPLACE", 3);
        f486g = nVar4;
        f487h = new n[]{nVar, nVar2, nVar3, nVar4};
    }

    public static n valueOf(String str) {
        return (n) Enum.valueOf(n.class, str);
    }

    public static n[] values() {
        return (n[]) f487h.clone();
    }
}
