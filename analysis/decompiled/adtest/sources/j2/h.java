package j2;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public abstract class h extends c implements g, n2.a, u1.a {

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final int f1271j;

    public h(int i4, Class cls, String str, String str2, int i5) {
        this(i4, b.f1262d, cls, str, str2, i5, 0);
    }

    @Override // j2.g
    public final int b() {
        return this.f1271j;
    }

    @Override // j2.c
    public final n2.a c() {
        o.f1277a.getClass();
        return this;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof h) {
            h hVar = (h) obj;
            return this.f1266g.equals(hVar.f1266g) && this.f1267h.equals(hVar.f1267h) && i.a(this.f1264e, hVar.f1264e) && e().equals(hVar.e());
        }
        if (!(obj instanceof h)) {
            return false;
        }
        n2.a aVar = this.f1263d;
        if (aVar == null) {
            c();
            this.f1263d = this;
            aVar = this;
        }
        return obj.equals(aVar);
    }

    public final int hashCode() {
        e();
        return this.f1267h.hashCode() + ((this.f1266g.hashCode() + (e().hashCode() * 31)) * 31);
    }

    public final String toString() {
        n2.a aVar = this.f1263d;
        if (aVar == null) {
            c();
            this.f1263d = this;
            aVar = this;
        }
        if (aVar != this) {
            return aVar.toString();
        }
        String str = this.f1266g;
        if ("<init>".equals(str)) {
            return "constructor (Kotlin reflection is not available)";
        }
        return "function " + str + " (Kotlin reflection is not available)";
    }

    public h(int i4, Object obj, Class cls, String str, String str2, int i5, int i6) {
        super(obj, cls, str, str2, (i5 & 1) == 1);
        this.f1271j = i4;
    }
}
