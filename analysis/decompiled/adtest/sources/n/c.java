package n;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class c {
    private static final /* synthetic */ b2.a $ENTRIES;
    private static final /* synthetic */ c[] $VALUES;
    public static final a Companion;
    public static final c ON_ANY;
    public static final c ON_CREATE;
    public static final c ON_DESTROY;
    public static final c ON_PAUSE;
    public static final c ON_RESUME;
    public static final c ON_START;
    public static final c ON_STOP;

    static {
        c cVar = new c("ON_CREATE", 0);
        ON_CREATE = cVar;
        c cVar2 = new c("ON_START", 1);
        ON_START = cVar2;
        c cVar3 = new c("ON_RESUME", 2);
        ON_RESUME = cVar3;
        c cVar4 = new c("ON_PAUSE", 3);
        ON_PAUSE = cVar4;
        c cVar5 = new c("ON_STOP", 4);
        ON_STOP = cVar5;
        c cVar6 = new c("ON_DESTROY", 5);
        ON_DESTROY = cVar6;
        c cVar7 = new c("ON_ANY", 6);
        ON_ANY = cVar7;
        c[] cVarArr = {cVar, cVar2, cVar3, cVar4, cVar5, cVar6, cVar7};
        $VALUES = cVarArr;
        $ENTRIES = new b2.b(cVarArr);
        Companion = new a();
    }

    public static c valueOf(String str) {
        return (c) Enum.valueOf(c.class, str);
    }

    public static c[] values() {
        return (c[]) $VALUES.clone();
    }
}
