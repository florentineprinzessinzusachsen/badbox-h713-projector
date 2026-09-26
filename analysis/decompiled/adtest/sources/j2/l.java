package j2;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public abstract class l extends c implements n2.c {

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final boolean f1274j;

    public l(Object obj, Class cls, String str, String str2, int i4) {
        super(obj, cls, str, str2, (i4 & 1) == 1);
        this.f1274j = false;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof l) {
            l lVar = (l) obj;
            return e().equals(lVar.e()) && this.f1266g.equals(lVar.f1266g) && this.f1267h.equals(lVar.f1267h) && i.a(this.f1264e, lVar.f1264e);
        }
        if (obj instanceof n2.c) {
            return obj.equals(g());
        }
        return false;
    }

    public final n2.a g() {
        if (this.f1274j) {
            return this;
        }
        n2.a aVar = this.f1263d;
        if (aVar != null) {
            return aVar;
        }
        n2.a aVarC = c();
        this.f1263d = aVarC;
        return aVarC;
    }

    public final int hashCode() {
        return this.f1267h.hashCode() + ((this.f1266g.hashCode() + (e().hashCode() * 31)) * 31);
    }

    public final String toString() {
        n2.a aVarG = g();
        if (aVarG != this) {
            return aVarG.toString();
        }
        return "property " + this.f1266g + " (Kotlin reflection is not available)";
    }
}
