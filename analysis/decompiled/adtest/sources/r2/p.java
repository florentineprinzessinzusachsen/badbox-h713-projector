package r2;

import java.util.concurrent.CancellationException;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f2011a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final f f2012b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final i2.q f2013c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Object f2014d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Throwable f2015e;

    public p(Object obj, f fVar, i2.q qVar, Object obj2, Throwable th) {
        this.f2011a = obj;
        this.f2012b = fVar;
        this.f2013c = qVar;
        this.f2014d = obj2;
        this.f2015e = th;
    }

    public static p a(p pVar, f fVar, CancellationException cancellationException, int i4) {
        Object obj = pVar.f2011a;
        if ((i4 & 2) != 0) {
            fVar = pVar.f2012b;
        }
        f fVar2 = fVar;
        i2.q qVar = pVar.f2013c;
        Object obj2 = pVar.f2014d;
        Throwable th = cancellationException;
        if ((i4 & 16) != 0) {
            th = pVar.f2015e;
        }
        return new p(obj, fVar2, qVar, obj2, th);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof p)) {
            return false;
        }
        p pVar = (p) obj;
        return j2.i.a(this.f2011a, pVar.f2011a) && j2.i.a(this.f2012b, pVar.f2012b) && j2.i.a(this.f2013c, pVar.f2013c) && j2.i.a(this.f2014d, pVar.f2014d) && j2.i.a(this.f2015e, pVar.f2015e);
    }

    public final int hashCode() {
        Object obj = this.f2011a;
        int iHashCode = (obj == null ? 0 : obj.hashCode()) * 31;
        f fVar = this.f2012b;
        int iHashCode2 = (iHashCode + (fVar == null ? 0 : fVar.hashCode())) * 31;
        i2.q qVar = this.f2013c;
        int iHashCode3 = (iHashCode2 + (qVar == null ? 0 : qVar.hashCode())) * 31;
        Object obj2 = this.f2014d;
        int iHashCode4 = (iHashCode3 + (obj2 == null ? 0 : obj2.hashCode())) * 31;
        Throwable th = this.f2015e;
        return iHashCode4 + (th != null ? th.hashCode() : 0);
    }

    public final String toString() {
        return "CompletedContinuation(result=" + this.f2011a + ", cancelHandler=" + this.f2012b + ", onCancellation=" + this.f2013c + ", idempotentResume=" + this.f2014d + ", cancelCause=" + this.f2015e + ')';
    }

    public /* synthetic */ p(Object obj, f fVar, i2.q qVar, CancellationException cancellationException, int i4) {
        this(obj, (i4 & 2) != 0 ? null : fVar, (i4 & 4) != 0 ? null : qVar, (Object) null, (i4 & 16) != 0 ? null : cancellationException);
    }
}
