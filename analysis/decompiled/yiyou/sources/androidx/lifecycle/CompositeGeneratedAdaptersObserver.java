package androidx.lifecycle;

/* JADX INFO: loaded from: classes.dex */
class CompositeGeneratedAdaptersObserver implements f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final c[] f1357a;

    CompositeGeneratedAdaptersObserver(c[] cVarArr) {
        this.f1357a = cVarArr;
    }

    @Override // androidx.lifecycle.f
    public void a(h hVar, e.a aVar) {
        l lVar = new l();
        for (c cVar : this.f1357a) {
            cVar.a(hVar, aVar, false, lVar);
        }
        for (c cVar2 : this.f1357a) {
            cVar2.a(hVar, aVar, true, lVar);
        }
    }
}
