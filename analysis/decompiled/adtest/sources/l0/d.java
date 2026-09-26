package l0;

import java.util.List;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final p.t f1307a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final c f1308b = new c(0);

    public d(p.t tVar) {
        this.f1307a = tVar;
    }

    public final List a(String str) {
        j2.i.e(str, "id");
        return (List) l3.h.W(this.f1307a, true, false, new b(1, str));
    }
}
