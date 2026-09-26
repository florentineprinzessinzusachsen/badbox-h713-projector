package y0;

import java.sql.Timestamp;
import java.util.Date;
import s0.b0;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class d extends b0 {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final c f2713b = new c();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final b0 f2714a;

    public d(b0 b0Var) {
        this.f2714a = b0Var;
    }

    @Override // s0.b0
    public final Object b(a1.b bVar) {
        Date date = (Date) this.f2714a.b(bVar);
        if (date != null) {
            return new Timestamp(date.getTime());
        }
        return null;
    }

    @Override // s0.b0
    public final void c(a1.d dVar, Object obj) {
        this.f2714a.c(dVar, (Timestamp) obj);
    }
}
