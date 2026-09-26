package retrofit2;

import d.a0;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
final class ExecutorCallAdapterFactory extends CallAdapter.Factory {
    final Executor callbackExecutor;

    static final class ExecutorCallbackCall<T> implements Call<T> {
        final Executor callbackExecutor;
        final Call<T> delegate;

        ExecutorCallbackCall(Executor executor, Call<T> call) {
            this.callbackExecutor = executor;
            this.delegate = call;
        }

        @Override // retrofit2.Call
        public void cancel() {
            this.delegate.cancel();
        }

        @Override // retrofit2.Call
        public void enqueue(final Callback<T> callback) {
            Utils.checkNotNull(callback, "callback == null");
            this.delegate.enqueue(new Callback<T>() { // from class: retrofit2.ExecutorCallAdapterFactory.ExecutorCallbackCall.1
                @Override // retrofit2.Callback
                public void onFailure(Call<T> call, final Throwable th) {
                    ExecutorCallbackCall.this.callbackExecutor.execute(new Runnable() { // from class: retrofit2.ExecutorCallAdapterFactory.ExecutorCallbackCall.1.2
                        @Override // java.lang.Runnable
                        public void run() {
                            AnonymousClass1 anonymousClass1 = AnonymousClass1.this;
                            callback.onFailure(ExecutorCallbackCall.this, th);
                        }
                    });
                }

                @Override // retrofit2.Callback
                public void onResponse(Call<T> call, final Response<T> response) {
                    ExecutorCallbackCall.this.callbackExecutor.execute(new Runnable() { // from class: retrofit2.ExecutorCallAdapterFactory.ExecutorCallbackCall.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            if (ExecutorCallbackCall.this.delegate.isCanceled()) {
                                AnonymousClass1 anonymousClass1 = AnonymousClass1.this;
                                callback.onFailure(ExecutorCallbackCall.this, new IOException("Canceled"));
                            } else {
                                AnonymousClass1 anonymousClass2 = AnonymousClass1.this;
                                callback.onResponse(ExecutorCallbackCall.this, response);
                            }
                        }
                    });
                }
            });
        }

        @Override // retrofit2.Call
        public Response<T> execute() {
            return this.delegate.execute();
        }

        @Override // retrofit2.Call
        public boolean isCanceled() {
            return this.delegate.isCanceled();
        }

        @Override // retrofit2.Call
        public boolean isExecuted() {
            return this.delegate.isExecuted();
        }

        @Override // retrofit2.Call
        public a0 request() {
            return this.delegate.request();
        }

        @Override // retrofit2.Call
        public Call<T> clone() {
            return new ExecutorCallbackCall(this.callbackExecutor, this.delegate.clone());
        }
    }

    ExecutorCallAdapterFactory(Executor executor) {
        this.callbackExecutor = executor;
    }

    @Override // retrofit2.CallAdapter.Factory
    public CallAdapter<?, ?> get(Type type, Annotation[] annotationArr, Retrofit retrofit) {
        if (CallAdapter.Factory.getRawType(type) != Call.class) {
            return null;
        }
        final Type callResponseType = Utils.getCallResponseType(type);
        return new CallAdapter<Object, Call<?>>() { // from class: retrofit2.ExecutorCallAdapterFactory.1
            @Override // retrofit2.CallAdapter
            public Type responseType() {
                return callResponseType;
            }

            @Override // retrofit2.CallAdapter
            public Call<?> adapt(Call<Object> call) {
                return new ExecutorCallbackCall(ExecutorCallAdapterFactory.this.callbackExecutor, call);
            }
        };
    }
}
