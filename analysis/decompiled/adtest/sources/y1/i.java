package y1;

import i2.p;
import java.io.Serializable;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class i implements h, Serializable {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final i f2726d = new i();

    @Override // y1.h
    public final h C(g gVar) {
        j2.i.e(gVar, "key");
        return this;
    }

    public final int hashCode() {
        return 0;
    }

    @Override // y1.h
    public final f k(g gVar) {
        j2.i.e(gVar, "key");
        return null;
    }

    @Override // y1.h
    public final h l(h hVar) {
        j2.i.e(hVar, "context");
        return hVar;
    }

    public final String toString() {
        return "EmptyCoroutineContext";
    }

    @Override // y1.h
    public final Object K(Object obj, p pVar) {
        return obj;
    }
}
