package androidx.activity;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: compiled from: OnBackPressedCallback.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private boolean f209a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private CopyOnWriteArrayList<a> f210b = new CopyOnWriteArrayList<>();

    public b(boolean z) {
        this.f209a = z;
    }

    public abstract void a();

    public final void a(boolean z) {
        this.f209a = z;
    }

    public final boolean b() {
        return this.f209a;
    }

    public final void c() {
        Iterator<a> it = this.f210b.iterator();
        while (it.hasNext()) {
            it.next().cancel();
        }
    }

    void a(a aVar) {
        this.f210b.add(aVar);
    }

    void b(a aVar) {
        this.f210b.remove(aVar);
    }
}
