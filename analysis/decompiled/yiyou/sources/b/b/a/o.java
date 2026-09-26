package b.b.a;

import java.util.Map;
import java.util.Set;

/* JADX INFO: compiled from: JsonObject.java */
/* JADX INFO: loaded from: classes.dex */
public final class o extends l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final b.b.a.y.h<String, l> f1559a = new b.b.a.y.h<>();

    public void a(String str, l lVar) {
        if (lVar == null) {
            lVar = n.f1558a;
        }
        this.f1559a.put(str, lVar);
    }

    public boolean equals(Object obj) {
        return obj == this || ((obj instanceof o) && ((o) obj).f1559a.equals(this.f1559a));
    }

    public Set<Map.Entry<String, l>> h() {
        return this.f1559a.entrySet();
    }

    public int hashCode() {
        return this.f1559a.hashCode();
    }
}
