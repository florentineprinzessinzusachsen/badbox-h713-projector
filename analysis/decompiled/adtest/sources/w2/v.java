package w2;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class v implements y1.g {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final ThreadLocal f2655d;

    public v(ThreadLocal threadLocal) {
        this.f2655d = threadLocal;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof v) && j2.i.a(this.f2655d, ((v) obj).f2655d);
    }

    public final int hashCode() {
        return this.f2655d.hashCode();
    }

    public final String toString() {
        return "ThreadLocalKey(threadLocal=" + this.f2655d + ')';
    }
}
