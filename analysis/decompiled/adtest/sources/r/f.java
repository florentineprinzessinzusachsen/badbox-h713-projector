package r;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.RandomAccess;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class f implements w.a, z2.a {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final w.a f1883d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final z2.a f1884e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public y1.h f1885f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public Throwable f1886g;

    public f(w.a aVar) {
        z2.c cVar = new z2.c();
        j2.i.e(aVar, "delegate");
        this.f1883d = aVar;
        this.f1884e = cVar;
    }

    @Override // w.a
    public final w.c P(String str) {
        j2.i.e(str, "sql");
        return this.f1883d.P(str);
    }

    @Override // z2.a
    public final void b(Object obj) {
        this.f1884e.b(null);
    }

    @Override // z2.a
    public final Object c(a2.c cVar) {
        return this.f1884e.c(cVar);
    }

    @Override // java.lang.AutoCloseable
    public final void close() throws Exception {
        this.f1883d.close();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v11, types: [java.util.Collection, java.util.List] */
    /* JADX WARN: Type inference failed for: r0v15 */
    /* JADX WARN: Type inference failed for: r0v18 */
    /* JADX WARN: Type inference failed for: r0v8 */
    /* JADX WARN: Type inference failed for: r3v0, types: [v1.p] */
    /* JADX WARN: Type inference failed for: r3v1, types: [java.lang.Iterable] */
    /* JADX WARN: Type inference failed for: r3v3, types: [java.util.ArrayList] */
    /* JADX WARN: Type inference failed for: r3v4, types: [java.util.List] */
    public final void k(StringBuilder sb) {
        ?? S;
        if (this.f1885f == null && this.f1886g == null) {
            sb.append("\t\tStatus: Free connection");
            sb.append('\n');
            return;
        }
        sb.append("\t\tStatus: Acquired connection");
        sb.append('\n');
        y1.h hVar = this.f1885f;
        if (hVar != null) {
            sb.append("\t\tCoroutine: " + hVar);
            sb.append('\n');
        }
        Throwable th = this.f1886g;
        if (th != null) {
            sb.append("\t\tAcquired:");
            sb.append('\n');
            p2.d dVar = new p2.d(l3.h.j0(th));
            boolean zHasNext = dVar.hasNext();
            ?? arrayList = v1.p.f2517d;
            if (zHasNext) {
                Object next = dVar.next();
                if (dVar.hasNext()) {
                    ArrayList arrayList2 = new ArrayList();
                    arrayList2.add(next);
                    while (dVar.hasNext()) {
                        arrayList2.add(dVar.next());
                    }
                    S = arrayList2;
                } else {
                    S = l3.h.S(next);
                }
            } else {
                S = arrayList;
            }
            int size = S.size() - 1;
            if (size > 0) {
                if (size == 1) {
                    arrayList = l3.h.S(v1.j.z0(S));
                } else {
                    arrayList = new ArrayList(size);
                    if (S instanceof RandomAccess) {
                        int size2 = S.size();
                        for (int i4 = 1; i4 < size2; i4++) {
                            arrayList.add(S.get(i4));
                        }
                    } else {
                        ListIterator listIterator = S.listIterator(1);
                        while (listIterator.hasNext()) {
                            arrayList.add(listIterator.next());
                        }
                    }
                }
            }
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                sb.append("\t\t" + ((String) it.next()));
                sb.append('\n');
            }
        }
    }

    public final String toString() {
        return this.f1883d.toString();
    }
}
