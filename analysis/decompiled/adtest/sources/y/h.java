package y;

import a3.o;
import android.content.Context;
import e3.w;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class h implements x.d {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Context f2700d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final String f2701e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final w f2702f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final boolean f2703g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final boolean f2704h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final u1.i f2705i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f2706j;

    public h(Context context, String str, w wVar, boolean z3, boolean z4) {
        j2.i.e(context, "context");
        j2.i.e(wVar, "callback");
        this.f2700d = context;
        this.f2701e = str;
        this.f2702f = wVar;
        this.f2703g = z3;
        this.f2704h = z4;
        this.f2705i = new u1.i(new o(9, this));
    }

    @Override // x.d
    public final x.a M() {
        return ((g) this.f2705i.getValue()).b(true);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        if (this.f2705i.f2298e != u1.j.f2300a) {
            ((g) this.f2705i.getValue()).close();
        }
    }

    @Override // x.d
    public final String getDatabaseName() {
        return this.f2701e;
    }

    @Override // x.d
    public final void setWriteAheadLoggingEnabled(boolean z3) {
        if (this.f2705i.f2298e != u1.j.f2300a) {
            ((g) this.f2705i.getValue()).setWriteAheadLoggingEnabled(z3);
        }
        this.f2706j = z3;
    }
}
