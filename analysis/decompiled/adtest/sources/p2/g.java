package p2;

import d0.l0;
import java.util.Iterator;
import java.util.regex.Matcher;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class g extends v1.a {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ a2.f f1760d;

    public g(a2.f fVar) {
        this.f1760d = fVar;
    }

    @Override // v1.a
    public final int a() {
        return ((Matcher) this.f1760d.f45e).groupCount() + 1;
    }

    public final e b(int i4) {
        Matcher matcher = (Matcher) this.f1760d.f45e;
        m2.c cVarQ = l0.Q(matcher.start(i4), matcher.end(i4));
        if (cVarQ.f1443d < 0) {
            return null;
        }
        String strGroup = matcher.group(i4);
        j2.i.d(strGroup, "group(...)");
        return new e(strGroup, cVarQ);
    }

    @Override // v1.a, java.util.Collection, java.util.List
    public final /* bridge */ boolean contains(Object obj) {
        if (obj == null ? true : obj instanceof e) {
            return super.contains((e) obj);
        }
        return false;
    }

    @Override // v1.a, java.util.Collection
    public final boolean isEmpty() {
        return false;
    }

    @Override // java.util.Collection, java.lang.Iterable
    public final Iterator iterator() {
        return new o2.h(new o2.i(new o2.f(2, new m2.c(0, a() - 1, 1)), new f1.c(2, this)));
    }
}
