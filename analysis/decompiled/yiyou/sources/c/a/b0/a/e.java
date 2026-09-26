package c.a.b0.a;

import c.a.b0.j.j;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/* JADX INFO: compiled from: ListCompositeDisposable.java */
/* JADX INFO: loaded from: classes.dex */
public final class e implements c.a.y.b, b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    List<c.a.y.b> f1755a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    volatile boolean f1756b;

    @Override // c.a.b0.a.b
    public boolean a(c.a.y.b bVar) {
        c.a.b0.b.b.a(bVar, "Disposable item is null");
        if (this.f1756b) {
            return false;
        }
        synchronized (this) {
            if (this.f1756b) {
                return false;
            }
            List<c.a.y.b> list = this.f1755a;
            if (list != null && list.remove(bVar)) {
                return true;
            }
            return false;
        }
    }

    @Override // c.a.b0.a.b
    public boolean b(c.a.y.b bVar) {
        if (!a(bVar)) {
            return false;
        }
        bVar.dispose();
        return true;
    }

    @Override // c.a.b0.a.b
    public boolean c(c.a.y.b bVar) {
        c.a.b0.b.b.a(bVar, "d is null");
        if (!this.f1756b) {
            synchronized (this) {
                if (!this.f1756b) {
                    List linkedList = this.f1755a;
                    if (linkedList == null) {
                        linkedList = new LinkedList();
                        this.f1755a = linkedList;
                    }
                    linkedList.add(bVar);
                    return true;
                }
            }
        }
        bVar.dispose();
        return false;
    }

    @Override // c.a.y.b
    public void dispose() {
        if (this.f1756b) {
            return;
        }
        synchronized (this) {
            if (this.f1756b) {
                return;
            }
            this.f1756b = true;
            List<c.a.y.b> list = this.f1755a;
            this.f1755a = null;
            a(list);
        }
    }

    void a(List<c.a.y.b> list) {
        if (list == null) {
            return;
        }
        ArrayList arrayList = null;
        Iterator<c.a.y.b> it = list.iterator();
        while (it.hasNext()) {
            try {
                it.next().dispose();
            } catch (Throwable th) {
                c.a.z.b.b(th);
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(th);
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
