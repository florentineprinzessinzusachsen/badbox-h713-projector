package retrofit2.adapter.rxjava2;

import c.a.e0.a;
import c.a.l;
import c.a.s;
import c.a.y.b;
import retrofit2.Call;
import retrofit2.Response;

/* JADX INFO: loaded from: classes.dex */
final class CallExecuteObservable<T> extends l<Response<T>> {
    private final Call<T> originalCall;

    private static final class CallDisposable implements b {
        private final Call<?> call;
        private volatile boolean disposed;

        CallDisposable(Call<?> call) {
            this.call = call;
        }

        @Override // c.a.y.b
        public void dispose() {
            this.disposed = true;
            this.call.cancel();
        }

        public boolean isDisposed() {
            return this.disposed;
        }
    }

    CallExecuteObservable(Call<T> call) {
        this.originalCall = call;
    }

    @Override // c.a.l
    protected void subscribeActual(s<? super Response<T>> sVar) {
        boolean z;
        Call<T> callClone = this.originalCall.clone();
        CallDisposable callDisposable = new CallDisposable(callClone);
        sVar.onSubscribe(callDisposable);
        try {
            Response<T> responseExecute = callClone.execute();
            if (!callDisposable.isDisposed()) {
                sVar.onNext(responseExecute);
            }
            if (callDisposable.isDisposed()) {
                return;
            }
            try {
                sVar.onComplete();
            } catch (Throwable th) {
                th = th;
                z = true;
                c.a.z.b.b(th);
                if (z) {
                    a.b(th);
                    return;
                }
                if (callDisposable.isDisposed()) {
                    return;
                }
                try {
                    sVar.onError(th);
                } catch (Throwable th2) {
                    c.a.z.b.b(th2);
                    a.b(new c.a.z.a(th, th2));
                }
            }
        } catch (Throwable th3) {
            th = th3;
            z = false;
        }
    }
}
