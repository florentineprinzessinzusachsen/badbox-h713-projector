package n;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class d {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final d f1460d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final d f1461e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final d f1462f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final d f1463g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final d f1464h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final /* synthetic */ d[] f1465i;

    static {
        d dVar = new d("DESTROYED", 0);
        f1460d = dVar;
        d dVar2 = new d("INITIALIZED", 1);
        f1461e = dVar2;
        d dVar3 = new d("CREATED", 2);
        f1462f = dVar3;
        d dVar4 = new d("STARTED", 3);
        f1463g = dVar4;
        d dVar5 = new d("RESUMED", 4);
        f1464h = dVar5;
        f1465i = new d[]{dVar, dVar2, dVar3, dVar4, dVar5};
    }

    public static d valueOf(String str) {
        return (d) Enum.valueOf(d.class, str);
    }

    public static d[] values() {
        return (d[]) f1465i.clone();
    }
}
