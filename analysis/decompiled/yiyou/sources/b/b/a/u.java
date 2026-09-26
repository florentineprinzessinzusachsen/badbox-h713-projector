package b.b.a;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: LongSerializationPolicy.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class u {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final u f1562a = new a("DEFAULT", 0);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final u f1563b = new u("STRING", 1) { // from class: b.b.a.u.b
        {
            a aVar = null;
        }
    };

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final /* synthetic */ u[] f1564c = {f1562a, f1563b};

    /* JADX INFO: compiled from: LongSerializationPolicy.java */
    static enum a extends u {
        a(String str, int i) {
            super(str, i, null);
        }
    }

    private u(String str, int i) {
        super(str, i);
    }

    public static u valueOf(String str) {
        return (u) Enum.valueOf(u.class, str);
    }

    public static u[] values() {
        return (u[]) f1564c.clone();
    }

    /* synthetic */ u(String str, int i, a aVar) {
        this(str, i);
    }
}
