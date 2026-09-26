package retrofit2.adapter.rxjava2;

import c.a.e0.a;
import c.a.l;
import c.a.s;
import c.a.y.b;
import retrofit2.Response;

/* JADX INFO: loaded from: classes.dex */
final class BodyObservable<T> extends l<T> {
    private final l<Response<T>> upstream;

    private static class BodyObserver<R> implements s<Response<R>> {
        private final s<? super R> observer;
        private boolean terminated;

        BodyObserver(s<? super R> sVar) {
            this.observer = sVar;
        }

        @Override // c.a.s
        public void onComplete() {
            if (this.terminated) {
                return;
            }
            this.observer.onComplete();
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            if (!this.terminated) {
                this.observer.onError(th);
                return;
            }
            AssertionError assertionError = new AssertionError("This should never happen! Report as a bug with the full stacktrace.");
            assertionError.initCause(th);
            a.b(assertionError);
        }

        @Override // c.a.s
        public void onSubscribe(b bVar) {
            this.observer.onSubscribe(bVar);
        }

        @Override // c.a.s
        public void onNext(Response<R> response) {
            if (response.isSuccessful()) {
                this.observer.onNext(response.body());
                return;
            }
            this.terminated = true;
            HttpException httpException = new HttpException(response);
            try {
                this.observer.onError(httpException);
            } catch (Throwable th) {
                c.a.z.b.b(th);
                a.b(new c.a.z.a(httpException, th));
            }
        }
    }

    BodyObservable(l<Response<T>> lVar) {
        this.upstream = lVar;
    }

    @Override // c.a.l
    protected void subscribeActual(s<? super T> sVar) {
        this.upstream.subscribe(new BodyObserver(sVar));
    }
}
