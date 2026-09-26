package b.b.a.y;

/* JADX INFO: compiled from: $Gson$Preconditions.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {
    public static <T> T a(T t) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException();
    }

    public static void a(boolean z) {
        if (!z) {
            throw new IllegalArgumentException();
        }
    }
}
