package d.h0.i;

import com.baidu.mobstat.Config;

/* JADX INFO: compiled from: Header.java */
/* JADX INFO: loaded from: classes.dex */
public final class c {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final e.f f4460d = e.f.d(Config.TRACE_TODAY_VISIT_SPLIT);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final e.f f4461e = e.f.d(":status");

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final e.f f4462f = e.f.d(":method");
    public static final e.f g = e.f.d(":path");
    public static final e.f h = e.f.d(":scheme");
    public static final e.f i = e.f.d(":authority");

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final e.f f4463a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final e.f f4464b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final int f4465c;

    public c(String str, String str2) {
        this(e.f.d(str), e.f.d(str2));
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof c)) {
            return false;
        }
        c cVar = (c) obj;
        return this.f4463a.equals(cVar.f4463a) && this.f4464b.equals(cVar.f4464b);
    }

    public int hashCode() {
        return ((527 + this.f4463a.hashCode()) * 31) + this.f4464b.hashCode();
    }

    public String toString() {
        return d.h0.c.a("%s: %s", this.f4463a.i(), this.f4464b.i());
    }

    public c(e.f fVar, String str) {
        this(fVar, e.f.d(str));
    }

    public c(e.f fVar, e.f fVar2) {
        this.f4463a = fVar;
        this.f4464b = fVar2;
        this.f4465c = fVar.f() + 32 + fVar2.f();
    }
}
