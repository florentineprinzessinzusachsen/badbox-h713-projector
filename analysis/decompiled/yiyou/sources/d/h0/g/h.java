package d.h0.g;

import d.d0;
import d.v;

/* JADX INFO: compiled from: RealResponseBody.java */
/* JADX INFO: loaded from: classes.dex */
public final class h extends d0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f4422a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final long f4423b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final e.e f4424c;

    public h(String str, long j, e.e eVar) {
        this.f4422a = str;
        this.f4423b = j;
        this.f4424c = eVar;
    }

    @Override // d.d0
    public long contentLength() {
        return this.f4423b;
    }

    @Override // d.d0
    public v contentType() {
        String str = this.f4422a;
        if (str != null) {
            return v.b(str);
        }
        return null;
    }

    @Override // d.d0
    public e.e source() {
        return this.f4424c;
    }
}
