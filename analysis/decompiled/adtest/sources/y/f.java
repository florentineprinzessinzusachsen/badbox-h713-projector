package y;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class f {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final f f2686d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final f f2687e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final f f2688f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final f f2689g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final f f2690h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final /* synthetic */ f[] f2691i;

    static {
        f fVar = new f("ON_CONFIGURE", 0);
        f2686d = fVar;
        f fVar2 = new f("ON_CREATE", 1);
        f2687e = fVar2;
        f fVar3 = new f("ON_UPGRADE", 2);
        f2688f = fVar3;
        f fVar4 = new f("ON_DOWNGRADE", 3);
        f2689g = fVar4;
        f fVar5 = new f("ON_OPEN", 4);
        f2690h = fVar5;
        f2691i = new f[]{fVar, fVar2, fVar3, fVar4, fVar5};
    }

    public static f valueOf(String str) {
        return (f) Enum.valueOf(f.class, str);
    }

    public static f[] values() {
        return (f[]) f2691i.clone();
    }
}
