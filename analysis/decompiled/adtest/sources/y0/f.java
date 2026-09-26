package y0;

import java.sql.Date;
import java.sql.Timestamp;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public abstract class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final boolean f2716a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final e f2717b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final e f2718c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final a.C0000a f2719d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final b.a f2720e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final c f2721f;

    static {
        boolean z3;
        try {
            Class.forName("java.sql.Date");
            z3 = true;
        } catch (ClassNotFoundException unused) {
            z3 = false;
        }
        f2716a = z3;
        if (z3) {
            f2717b = new e(Date.class, 0);
            f2718c = new e(Timestamp.class, 1);
            f2719d = a.f2709b;
            f2720e = b.f2711b;
            f2721f = d.f2713b;
            return;
        }
        f2717b = null;
        f2718c = null;
        f2719d = null;
        f2720e = null;
        f2721f = null;
    }
}
