package androidx.activity;

import androidx.lifecycle.e;
import androidx.lifecycle.f;
import androidx.lifecycle.h;
import java.util.ArrayDeque;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class OnBackPressedDispatcher {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Runnable f201a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final ArrayDeque<b> f202b = new ArrayDeque<>();

    private class LifecycleOnBackPressedCancellable implements f, androidx.activity.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final e f203a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final b f204b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private androidx.activity.a f205c;

        LifecycleOnBackPressedCancellable(e eVar, b bVar) {
            this.f203a = eVar;
            this.f204b = bVar;
            eVar.a(this);
        }

        @Override // androidx.lifecycle.f
        public void a(h hVar, e.a aVar) {
            if (aVar == e.a.ON_START) {
                this.f205c = OnBackPressedDispatcher.this.a(this.f204b);
                return;
            }
            if (aVar != e.a.ON_STOP) {
                if (aVar == e.a.ON_DESTROY) {
                    cancel();
                }
            } else {
                androidx.activity.a aVar2 = this.f205c;
                if (aVar2 != null) {
                    aVar2.cancel();
                }
            }
        }

        @Override // androidx.activity.a
        public void cancel() {
            this.f203a.b(this);
            this.f204b.b(this);
            androidx.activity.a aVar = this.f205c;
            if (aVar != null) {
                aVar.cancel();
                this.f205c = null;
            }
        }
    }

    private class a implements androidx.activity.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final b f207a;

        a(b bVar) {
            this.f207a = bVar;
        }

        @Override // androidx.activity.a
        public void cancel() {
            OnBackPressedDispatcher.this.f202b.remove(this.f207a);
            this.f207a.b(this);
        }
    }

    public OnBackPressedDispatcher(Runnable runnable) {
        this.f201a = runnable;
    }

    androidx.activity.a a(b bVar) {
        this.f202b.add(bVar);
        a aVar = new a(bVar);
        bVar.a(aVar);
        return aVar;
    }

    public void a(h hVar, b bVar) {
        e eVarA = hVar.a();
        if (eVarA.a() == e.b.DESTROYED) {
            return;
        }
        bVar.a(new LifecycleOnBackPressedCancellable(eVarA, bVar));
    }

    public void a() {
        Iterator<b> itDescendingIterator = this.f202b.descendingIterator();
        while (itDescendingIterator.hasNext()) {
            b next = itDescendingIterator.next();
            if (next.b()) {
                next.a();
                return;
            }
        }
        Runnable runnable = this.f201a;
        if (runnable != null) {
            runnable.run();
        }
    }
}
