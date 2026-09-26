package a.c.a.j;

import java.util.ArrayList;

/* JADX INFO: compiled from: WidgetContainer.java */
/* JADX INFO: loaded from: classes.dex */
public class q extends f {
    protected ArrayList<f> k0 = new ArrayList<>();

    @Override // a.c.a.j.f
    public void D() {
        this.k0.clear();
        super.D();
    }

    @Override // a.c.a.j.f
    public void H() {
        super.H();
        ArrayList<f> arrayList = this.k0;
        if (arrayList == null) {
            return;
        }
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            f fVar = this.k0.get(i);
            fVar.b(g(), h());
            if (!(fVar instanceof g)) {
                fVar.H();
            }
        }
    }

    public g J() {
        f fVarK = k();
        g gVar = this instanceof g ? (g) this : null;
        while (fVarK != null) {
            f fVarK2 = fVarK.k();
            if (fVarK instanceof g) {
                gVar = (g) fVarK;
            }
            fVarK = fVarK2;
        }
        return gVar;
    }

    public void K() {
        H();
        ArrayList<f> arrayList = this.k0;
        if (arrayList == null) {
            return;
        }
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            f fVar = this.k0.get(i);
            if (fVar instanceof q) {
                ((q) fVar).K();
            }
        }
    }

    public void L() {
        this.k0.clear();
    }

    @Override // a.c.a.j.f
    public void a(a.c.a.c cVar) {
        super.a(cVar);
        int size = this.k0.size();
        for (int i = 0; i < size; i++) {
            this.k0.get(i).a(cVar);
        }
    }

    public void b(f fVar) {
        this.k0.add(fVar);
        if (fVar.k() != null) {
            ((q) fVar.k()).c(fVar);
        }
        fVar.a((f) this);
    }

    public void c(f fVar) {
        this.k0.remove(fVar);
        fVar.a((f) null);
    }

    @Override // a.c.a.j.f
    public void b(int i, int i2) {
        super.b(i, i2);
        int size = this.k0.size();
        for (int i3 = 0; i3 < size; i3++) {
            this.k0.get(i3).b(o(), p());
        }
    }
}
