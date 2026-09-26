package g;

import java.util.concurrent.Executor;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class m implements Executor {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final m f943d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final /* synthetic */ m[] f944e;

    static {
        m mVar = new m("INSTANCE", 0);
        f943d = mVar;
        f944e = new m[]{mVar};
    }

    public static m valueOf(String str) {
        return (m) Enum.valueOf(m.class, str);
    }

    public static m[] values() {
        return (m[]) f944e.clone();
    }

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        runnable.run();
    }

    @Override // java.lang.Enum
    public final String toString() {
        return "DirectExecutor";
    }
}
