package p2;

import java.util.regex.Matcher;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class f extends v1.d {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ a2.f f1759d;

    public f(a2.f fVar) {
        this.f1759d = fVar;
    }

    @Override // v1.a
    public final int a() {
        return ((Matcher) this.f1759d.f45e).groupCount() + 1;
    }

    @Override // v1.a, java.util.Collection, java.util.List
    public final /* bridge */ boolean contains(Object obj) {
        if (obj instanceof String) {
            return super.contains((String) obj);
        }
        return false;
    }

    @Override // java.util.List
    public final Object get(int i4) {
        String strGroup = ((Matcher) this.f1759d.f45e).group(i4);
        return strGroup == null ? "" : strGroup;
    }

    @Override // v1.d, java.util.List
    public final /* bridge */ int indexOf(Object obj) {
        if (obj instanceof String) {
            return super.indexOf((String) obj);
        }
        return -1;
    }

    @Override // v1.d, java.util.List
    public final /* bridge */ int lastIndexOf(Object obj) {
        if (obj instanceof String) {
            return super.lastIndexOf((String) obj);
        }
        return -1;
    }
}
