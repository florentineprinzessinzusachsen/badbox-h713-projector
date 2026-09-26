package s;

import l3.h;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public abstract class g implements w.c {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final x.a f2081d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final String f2082e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f2083f;

    public g(x.a aVar, String str) {
        this.f2081d = aVar;
        this.f2082e = str;
    }

    public final void b() {
        if (this.f2083f) {
            h.m0(21, "statement is closed");
            throw null;
        }
    }

    @Override // w.c
    public final boolean v() {
        return getLong(0) != 0;
    }
}
