package q1;

import a3.w;
import a3.x;
import e3.p;
import java.util.LinkedHashMap;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public abstract class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final x f1797a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static volatile p f1798b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final LinkedHashMap f1799c;

    static {
        w wVar = new w();
        TimeUnit timeUnit = TimeUnit.SECONDS;
        wVar.a(30L);
        wVar.b(40L);
        wVar.d(40L);
        wVar.f231l = new a1.a(8);
        wVar.f222c.add(new c3.a(3));
        wVar.c(l1.f.b(), new l1.a());
        wVar.f238s = new c1.a(4);
        f1797a = new x(wVar);
        f1799c = new LinkedHashMap();
    }
}
