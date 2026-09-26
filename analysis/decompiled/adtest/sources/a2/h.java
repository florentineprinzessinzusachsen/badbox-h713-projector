package a2;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public abstract class h extends a {
    public h(y1.c cVar) {
        super(cVar);
        if (cVar != null && cVar.g() != y1.i.f2726d) {
            throw new IllegalArgumentException("Coroutines with restricted suspension must have EmptyCoroutineContext");
        }
    }

    @Override // y1.c
    public final y1.h g() {
        return y1.i.f2726d;
    }
}
