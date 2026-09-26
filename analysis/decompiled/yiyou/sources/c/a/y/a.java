package c.a.y;

import c.a.b0.j.j;
import c.a.b0.j.p;
import java.util.ArrayList;

/* JADX INFO: compiled from: CompositeDisposable.java */
/* JADX INFO: loaded from: classes.dex */
public final class a implements b, c.a.b0.a.b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    p<b> f3190a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    volatile boolean f3191b;

    public boolean a() {
        return this.f3191b;
    }

    @Override // c.a.b0.a.b
    public boolean b(b bVar) {
        if (!a(bVar)) {
            return false;
        }
        bVar.dispose();
        return true;
    }

    @Override // c.a.b0.a.b
    public boolean c(b bVar) {
        c.a.b0.b.b.a(bVar, "d is null");
        if (!this.f3191b) {
            synchronized (this) {
                if (!this.f3191b) {
                    p<b> pVar = this.f3190a;
                    if (pVar == null) {
                        pVar = new p<>();
                        this.f3190a = pVar;
                    }
                    pVar.a(bVar);
                    return true;
                }
            }
        }
        bVar.dispose();
        return false;
    }

    @Override // c.a.y.b
    public void dispose() {
        if (this.f3191b) {
            return;
        }
        synchronized (this) {
            if (this.f3191b) {
                return;
            }
            this.f3191b = true;
            p<b> pVar = this.f3190a;
            this.f3190a = null;
            a(pVar);
        }
    }

    @Override // c.a.b0.a.b
    public boolean a(b bVar) {
        c.a.b0.b.b.a(bVar, "Disposable item is null");
        if (this.f3191b) {
            return false;
        }
        synchronized (this) {
            if (this.f3191b) {
                return false;
            }
            p<b> pVar = this.f3190a;
            if (pVar != null && pVar.b(bVar)) {
                return true;
            }
            return false;
        }
    }

    public int b() {
        if (this.f3191b) {
            return 0;
        }
        synchronized (this) {
            if (this.f3191b) {
                return 0;
            }
            p<b> pVar = this.f3190a;
            return pVar != null ? pVar.c() : 0;
        }
    }

    void a(p<b> pVar) {
        if (pVar == null) {
            return;
        }
        ArrayList arrayList = null;
        for (Object obj : pVar.a()) {
            if (obj instanceof b) {
                try {
                    ((b) obj).dispose();
                } catch (Throwable th) {
                    c.a.z.b.b(th);
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                    }
                    arrayList.add(th);
                }
            }
        }
        if (arrayList != null) {
            if (arrayList.size() == 1) {
                throw j.a((Throwable) arrayList.get(0));
            }
            throw new c.a.z.a(arrayList);
        }
    }
}
