package y2;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final b f2736d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final b f2737e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final b f2738f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final b f2739g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final b f2740h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final /* synthetic */ b[] f2741i;

    static {
        b bVar = new b("CPU_ACQUIRED", 0);
        f2736d = bVar;
        b bVar2 = new b("BLOCKING", 1);
        f2737e = bVar2;
        b bVar3 = new b("PARKING", 2);
        f2738f = bVar3;
        b bVar4 = new b("DORMANT", 3);
        f2739g = bVar4;
        b bVar5 = new b("TERMINATED", 4);
        f2740h = bVar5;
        f2741i = new b[]{bVar, bVar2, bVar3, bVar4, bVar5};
    }

    public static b valueOf(String str) {
        return (b) Enum.valueOf(b.class, str);
    }

    public static b[] values() {
        return (b[]) f2741i.clone();
    }
}
