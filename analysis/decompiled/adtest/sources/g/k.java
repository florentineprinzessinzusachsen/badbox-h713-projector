package g;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class k extends h {

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final /* synthetic */ l f940k;

    public k(l lVar) {
        this.f940k = lVar;
    }

    @Override // g.h
    public final String g() {
        i iVar = (i) this.f940k.f941d.get();
        if (iVar == null) {
            return "Completer object has been garbage collected, future will fail soon";
        }
        return "tag=[" + iVar.f936a + "]";
    }
}
