package retrofit2.adapter.rxjava2;

import c.a.e0.a;
import c.a.l;
import c.a.s;
import c.a.z.b;
import retrofit2.Response;

/* JADX INFO: loaded from: classes.dex */
final class ResultObservable<T> extends l<Result<T>> {
    private final l<Response<T>> upstream;

    private static class ResultObserver<R> implements s<Response<R>> {
        private final s<? super Result<R>> observer;

        ResultObserver(s<? super Result<R>> sVar) {
            this.observer = sVar;
        }

        @Override // c.a.s
        public void onComplete() {
            this.observer.onComplete();
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            try {
                this.observer.onNext(Result.error(th));
                this.observer.onComplete();
            } catch (Throwable th2) {
                try {
                    this.observer.onError(th2);
                } catch (Throwable th3) {
                    b.b(th3);
                    a.b(new c.a.z.a(th2, th3));
                }
            }
        }

        @Override // c.a.s
        public void onSubscribe(c.a.y.b bVar) {
            this.observer.onSubscribe(bVar);
        }

        @Override // c.a.s
        public void onNext(Response<R> response) {
            this.observer.onNext(Result.response(response));
        }
    }

    ResultObservable(l<Response<T>> lVar) {
        this.upstream = lVar;
    }

    @Override // c.a.l
    protected void subscribeActual(s<? super Result<T>> sVar) {
        this.upstream.subscribe(new ResultObserver(sVar));
    }
}
