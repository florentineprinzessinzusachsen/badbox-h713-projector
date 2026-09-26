package a.c.a.j;

import java.util.HashSet;
import java.util.Iterator;

/* JADX INFO: compiled from: ResolutionNode.java */
/* JADX INFO: loaded from: classes.dex */
public class o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    HashSet<o> f161a = new HashSet<>(2);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    int f162b = 0;

    public void a(o oVar) {
        this.f161a.add(oVar);
    }

    public void b() {
        this.f162b = 0;
        Iterator<o> it = this.f161a.iterator();
        while (it.hasNext()) {
            it.next().b();
        }
    }

    public boolean c() {
        return this.f162b == 1;
    }

    public void d() {
        this.f162b = 0;
        this.f161a.clear();
    }

    public void e() {
    }

    public void a() {
        this.f162b = 1;
        Iterator<o> it = this.f161a.iterator();
        while (it.hasNext()) {
            it.next().e();
        }
    }
}
