package j2;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class j extends l implements n2.c, i2.l {
    public j(Class cls, String str, String str2) {
        super(b.f1262d, cls, str, str2, 0);
    }

    @Override // j2.c
    public final n2.a c() {
        o.f1277a.getClass();
        return this;
    }

    @Override // i2.l
    public final Object h(Object obj) {
        i();
        throw null;
    }

    public final void i() {
        if (this.f1274j) {
            throw new UnsupportedOperationException("Kotlin reflection is not yet supported for synthetic Java properties. Please follow/upvote https://youtrack.jetbrains.com/issue/KT-55980");
        }
        n2.a aVarG = g();
        if (aVarG == this) {
            throw new h2.a("Kotlin reflection implementation is not found at runtime. Make sure you have kotlin-reflect.jar in the classpath");
        }
        ((j) ((n2.c) aVarG)).i();
    }
}
