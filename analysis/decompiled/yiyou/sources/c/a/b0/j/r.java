package c.a.b0.j;

/* JADX INFO: compiled from: QueueDrainHelper.java */
/* JADX INFO: loaded from: classes.dex */
public final class r {
    public static <T, U> void a(c.a.b0.c.i<T> iVar, c.a.s<? super U> sVar, boolean z, c.a.y.b bVar, o<T, U> oVar) {
        int iA = 1;
        while (!a(oVar.a(), iVar.isEmpty(), sVar, z, iVar, bVar, oVar)) {
            while (true) {
                boolean zA = oVar.a();
                T tPoll = iVar.poll();
                boolean z2 = tPoll == null;
                if (a(zA, z2, sVar, z, iVar, bVar, oVar)) {
                    return;
                }
                if (z2) {
                    break;
                } else {
                    oVar.a(sVar, tPoll);
                }
            }
            iA = oVar.a(-iA);
            if (iA == 0) {
                return;
            }
        }
    }

    public static <T, U> boolean a(boolean z, boolean z2, c.a.s<?> sVar, boolean z3, c.a.b0.c.j<?> jVar, c.a.y.b bVar, o<T, U> oVar) {
        if (oVar.b()) {
            jVar.clear();
            bVar.dispose();
            return true;
        }
        if (!z) {
            return false;
        }
        if (z3) {
            if (!z2) {
                return false;
            }
            if (bVar != null) {
                bVar.dispose();
            }
            Throwable thC = oVar.c();
            if (thC != null) {
                sVar.onError(thC);
            } else {
                sVar.onComplete();
            }
            return true;
        }
        Throwable thC2 = oVar.c();
        if (thC2 != null) {
            jVar.clear();
            if (bVar != null) {
                bVar.dispose();
            }
            sVar.onError(thC2);
            return true;
        }
        if (!z2) {
            return false;
        }
        if (bVar != null) {
            bVar.dispose();
        }
        sVar.onComplete();
        return true;
    }

    public static <T> c.a.b0.c.j<T> a(int i) {
        if (i < 0) {
            return new c.a.b0.f.c(-i);
        }
        return new c.a.b0.f.b(i);
    }
}
