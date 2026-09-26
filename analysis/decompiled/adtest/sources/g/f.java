package g;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class f extends l3.h {
    @Override // l3.h
    public final void f0(g gVar, g gVar2) {
        gVar.f928b = gVar2;
    }

    @Override // l3.h
    public final boolean g(h hVar, d dVar, d dVar2) {
        synchronized (hVar) {
            try {
                if (hVar.f934e != dVar) {
                    return false;
                }
                hVar.f934e = dVar2;
                return true;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // l3.h
    public final void g0(g gVar, Thread thread) {
        gVar.f927a = thread;
    }

    @Override // l3.h
    public final boolean h(h hVar, Object obj, Object obj2) {
        synchronized (hVar) {
            try {
                if (hVar.f933d != obj) {
                    return false;
                }
                hVar.f933d = obj2;
                return true;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // l3.h
    public final boolean i(h hVar, g gVar, g gVar2) {
        synchronized (hVar) {
            try {
                if (hVar.f935f != gVar) {
                    return false;
                }
                hVar.f935f = gVar2;
                return true;
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
