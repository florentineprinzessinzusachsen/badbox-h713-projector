package androidx.lifecycle;

import java.util.HashMap;
import java.util.Iterator;

/* JADX INFO: compiled from: ViewModelStore.java */
/* JADX INFO: loaded from: classes.dex */
public class s {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final HashMap<String, q> f1407a = new HashMap<>();

    final void a(String str, q qVar) {
        q qVarPut = this.f1407a.put(str, qVar);
        if (qVarPut != null) {
            qVarPut.b();
        }
    }

    final q a(String str) {
        return this.f1407a.get(str);
    }

    public final void a() {
        Iterator<q> it = this.f1407a.values().iterator();
        while (it.hasNext()) {
            it.next().a();
        }
        this.f1407a.clear();
    }
}
