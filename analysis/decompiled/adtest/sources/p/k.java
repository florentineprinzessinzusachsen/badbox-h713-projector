package p;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class k {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final k f1676d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final k f1677e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final k f1678f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final /* synthetic */ k[] f1679g;

    static {
        k kVar = new k("NO_OP", 0);
        f1676d = kVar;
        k kVar2 = new k("ADD", 1);
        f1677e = kVar2;
        k kVar3 = new k("REMOVE", 2);
        f1678f = kVar3;
        f1679g = new k[]{kVar, kVar2, kVar3};
    }

    public static k valueOf(String str) {
        return (k) Enum.valueOf(k.class, str);
    }

    public static k[] values() {
        return (k[]) f1679g.clone();
    }
}
