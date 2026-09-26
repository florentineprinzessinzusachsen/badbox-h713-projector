package b.b.a;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: JsonArray.java */
/* JADX INFO: loaded from: classes.dex */
public final class i extends l implements Iterable<l> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final List<l> f1557a = new ArrayList();

    public void a(l lVar) {
        if (lVar == null) {
            lVar = n.f1558a;
        }
        this.f1557a.add(lVar);
    }

    public boolean equals(Object obj) {
        return obj == this || ((obj instanceof i) && ((i) obj).f1557a.equals(this.f1557a));
    }

    public int hashCode() {
        return this.f1557a.hashCode();
    }

    @Override // java.lang.Iterable
    public Iterator<l> iterator() {
        return this.f1557a.iterator();
    }
}
