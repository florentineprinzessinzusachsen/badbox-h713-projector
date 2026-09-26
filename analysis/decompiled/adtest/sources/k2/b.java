package k2;

import j2.i;
import java.util.Random;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class b extends a {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final f3.d f1290e = new f3.d(1);

    @Override // k2.a
    public final Random e() {
        Object obj = this.f1290e.get();
        i.d(obj, "get(...)");
        return (Random) obj;
    }
}
