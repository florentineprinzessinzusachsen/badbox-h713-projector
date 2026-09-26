package c.a.b0.e.d;

import java.util.concurrent.LinkedBlockingQueue;

/* JADX INFO: compiled from: ObservableBlockingSubscribe.java */
/* JADX INFO: loaded from: classes.dex */
public final class k {
    public static <T> void a(c.a.q<? extends T> qVar, c.a.s<? super T> sVar) {
        LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue();
        c.a.b0.d.h hVar = new c.a.b0.d.h(linkedBlockingQueue);
        sVar.onSubscribe(hVar);
        qVar.subscribe(hVar);
        while (!hVar.a()) {
            Object objPoll = linkedBlockingQueue.poll();
            if (objPoll == null) {
                try {
                    objPoll = linkedBlockingQueue.take();
                } catch (InterruptedException e2) {
                    hVar.dispose();
                    sVar.onError(e2);
                    return;
                }
            }
            if (hVar.a() || qVar == c.a.b0.d.h.f1808b || c.a.b0.j.n.b(objPoll, sVar)) {
                return;
            }
        }
    }

    public static <T> void a(c.a.q<? extends T> qVar) {
        c.a.b0.j.f fVar = new c.a.b0.j.f();
        c.a.b0.d.o oVar = new c.a.b0.d.o(c.a.b0.b.a.d(), fVar, fVar, c.a.b0.b.a.d());
        qVar.subscribe(oVar);
        c.a.b0.j.e.a(fVar, oVar);
        Throwable th = fVar.f3084a;
        if (th != null) {
            throw c.a.b0.j.j.a(th);
        }
    }

    public static <T> void a(c.a.q<? extends T> qVar, c.a.a0.f<? super T> fVar, c.a.a0.f<? super Throwable> fVar2, c.a.a0.a aVar) {
        c.a.b0.b.b.a(fVar, "onNext is null");
        c.a.b0.b.b.a(fVar2, "onError is null");
        c.a.b0.b.b.a(aVar, "onComplete is null");
        a(qVar, new c.a.b0.d.o(fVar, fVar2, aVar, c.a.b0.b.a.d()));
    }
}
