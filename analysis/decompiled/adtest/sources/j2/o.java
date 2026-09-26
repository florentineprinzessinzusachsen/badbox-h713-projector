package j2;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public abstract class o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final p f1277a;

    static {
        p pVar = null;
        try {
            pVar = (p) Class.forName("kotlin.reflect.jvm.internal.ReflectionFactoryImpl").newInstance();
        } catch (ClassCastException | ClassNotFoundException | IllegalAccessException | InstantiationException unused) {
        }
        if (pVar == null) {
            pVar = new p();
        }
        f1277a = pVar;
    }

    public static e a(Class cls) {
        f1277a.getClass();
        return new e(cls);
    }
}
