package retrofit2.adapter.rxjava2;

import c.a.e0.a;
import c.a.l;
import c.a.s;
import c.a.y.b;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/* JADX INFO: loaded from: classes.dex */
final class CallEnqueueObservable<T> extends l<Response<T>> {
    private final Call<T> originalCall;

    private static final class CallCallback<T> implements b, Callback<T> {
        private final Call<?> call;
        private volatile boolean disposed;
        private final s<? super Response<T>> observer;
        boolean terminated = false;

        CallCallback(Call<?> call, s<? super Response<T>> sVar) {
            this.call = call;
            this.observer = sVar;
        }

        @Override // c.a.y.b
        public void dispose() {
            this.disposed = true;
            this.call.cancel();
        }

        public boolean isDisposed() {
            return this.disposed;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<T> call, Throwable th) {
            if (call.isCanceled()) {
                return;
            }
            try {
                this.observer.onError(th);
            } catch (Throwable th2) {
                c.a.z.b.b(th2);
                a.b(new c.a.z.a(th, th2));
            }
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<T> call, Response<T> response) {
            if (this.disposed) {
                return;
            }
            try {
                this.observer.onNext(response);
                if (this.disposed) {
                    return;
                }
                this.terminated = true;
                this.observer.onComplete();
            } catch (Throwable th) {
                if (this.terminated) {
                    a.b(th);
                    return;
                }
                if (this.disposed) {
                    return;
                }
                try {
                    this.observer.onError(th);
                } catch (Throwable th2) {
                    c.a.z.b.b(th2);
                    a.b(new c.a.z.a(th, th2));
                }
            }
        }
    }

    CallEnqueueObservable(Call<T> call) {
        this.originalCall = call;
    }

    @Override // c.a.l
    protected void subscribeActual(s<? super Response<T>> sVar) {
        Call<T> callClone = this.originalCall.clone();
        CallCallback callCallback = new CallCallback(callClone, sVar);
        sVar.onSubscribe(callCallback);
        callClone.enqueue(callCallback);
    }
}
