package retrofit2;

import d.a0;
import d.c0;
import d.d0;
import d.e;
import d.f;
import d.v;
import e.c;
import e.h;
import e.l;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
final class OkHttpCall<T> implements Call<T> {
    private final Object[] args;
    private volatile boolean canceled;
    private Throwable creationFailure;
    private boolean executed;
    private e rawCall;
    private final ServiceMethod<T, ?> serviceMethod;

    static final class ExceptionCatchingRequestBody extends d0 {
        private final d0 delegate;
        IOException thrownException;

        ExceptionCatchingRequestBody(d0 d0Var) {
            this.delegate = d0Var;
        }

        @Override // d.d0, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            this.delegate.close();
        }

        @Override // d.d0
        public long contentLength() {
            return this.delegate.contentLength();
        }

        @Override // d.d0
        public v contentType() {
            return this.delegate.contentType();
        }

        @Override // d.d0
        public e.e source() {
            return l.a(new h(this.delegate.source()) { // from class: retrofit2.OkHttpCall.ExceptionCatchingRequestBody.1
                @Override // e.h, e.s
                public long read(c cVar, long j) throws IOException {
                    try {
                        return super.read(cVar, j);
                    } catch (IOException e2) {
                        ExceptionCatchingRequestBody.this.thrownException = e2;
                        throw e2;
                    }
                }
            });
        }

        void throwIfCaught() throws IOException {
            IOException iOException = this.thrownException;
            if (iOException != null) {
                throw iOException;
            }
        }
    }

    static final class NoContentResponseBody extends d0 {
        private final long contentLength;
        private final v contentType;

        NoContentResponseBody(v vVar, long j) {
            this.contentType = vVar;
            this.contentLength = j;
        }

        @Override // d.d0
        public long contentLength() {
            return this.contentLength;
        }

        @Override // d.d0
        public v contentType() {
            return this.contentType;
        }

        @Override // d.d0
        public e.e source() {
            throw new IllegalStateException("Cannot read raw response body of a converted body.");
        }
    }

    OkHttpCall(ServiceMethod<T, ?> serviceMethod, Object[] objArr) {
        this.serviceMethod = serviceMethod;
        this.args = objArr;
    }

    private e createRawCall() {
        e call = this.serviceMethod.toCall(this.args);
        if (call != null) {
            return call;
        }
        throw new NullPointerException("Call.Factory returned null.");
    }

    @Override // retrofit2.Call
    public void cancel() {
        e eVar;
        this.canceled = true;
        synchronized (this) {
            eVar = this.rawCall;
        }
        if (eVar != null) {
            eVar.cancel();
        }
    }

    @Override // retrofit2.Call
    public void enqueue(final Callback<T> callback) {
        e eVar;
        Throwable th;
        Utils.checkNotNull(callback, "callback == null");
        synchronized (this) {
            if (this.executed) {
                throw new IllegalStateException("Already executed.");
            }
            this.executed = true;
            eVar = this.rawCall;
            th = this.creationFailure;
            if (eVar == null && th == null) {
                try {
                    e eVarCreateRawCall = createRawCall();
                    this.rawCall = eVarCreateRawCall;
                    eVar = eVarCreateRawCall;
                } catch (Throwable th2) {
                    th = th2;
                    Utils.throwIfFatal(th);
                    this.creationFailure = th;
                }
            }
        }
        if (th != null) {
            callback.onFailure(this, th);
            return;
        }
        if (this.canceled) {
            eVar.cancel();
        }
        eVar.a(new f() { // from class: retrofit2.OkHttpCall.1
            private void callFailure(Throwable th3) {
                try {
                    callback.onFailure(OkHttpCall.this, th3);
                } catch (Throwable th4) {
                    th4.printStackTrace();
                }
            }

            @Override // d.f
            public void onFailure(e eVar2, IOException iOException) {
                callFailure(iOException);
            }

            @Override // d.f
            public void onResponse(e eVar2, c0 c0Var) {
                try {
                    try {
                        callback.onResponse(OkHttpCall.this, OkHttpCall.this.parseResponse(c0Var));
                    } catch (Throwable th3) {
                        th3.printStackTrace();
                    }
                } catch (Throwable th4) {
                    callFailure(th4);
                }
            }
        });
    }

    @Override // retrofit2.Call
    public Response<T> execute() {
        e eVarCreateRawCall;
        synchronized (this) {
            if (this.executed) {
                throw new IllegalStateException("Already executed.");
            }
            this.executed = true;
            if (this.creationFailure != null) {
                if (this.creationFailure instanceof IOException) {
                    throw ((IOException) this.creationFailure);
                }
                if (this.creationFailure instanceof RuntimeException) {
                    throw ((RuntimeException) this.creationFailure);
                }
                throw ((Error) this.creationFailure);
            }
            eVarCreateRawCall = this.rawCall;
            if (eVarCreateRawCall == null) {
                try {
                    eVarCreateRawCall = createRawCall();
                    this.rawCall = eVarCreateRawCall;
                } catch (IOException | Error | RuntimeException e2) {
                    Utils.throwIfFatal(e2);
                    this.creationFailure = e2;
                    throw e2;
                }
            }
        }
        if (this.canceled) {
            eVarCreateRawCall.cancel();
        }
        return parseResponse(eVarCreateRawCall.execute());
    }

    @Override // retrofit2.Call
    public boolean isCanceled() {
        boolean z = true;
        if (this.canceled) {
            return true;
        }
        synchronized (this) {
            if (this.rawCall == null || !this.rawCall.isCanceled()) {
                z = false;
            }
        }
        return z;
    }

    @Override // retrofit2.Call
    public synchronized boolean isExecuted() {
        return this.executed;
    }

    Response<T> parseResponse(c0 c0Var) throws IOException {
        d0 d0VarA = c0Var.a();
        c0.a aVarS = c0Var.s();
        aVarS.a(new NoContentResponseBody(d0VarA.contentType(), d0VarA.contentLength()));
        c0 c0VarA = aVarS.a();
        int iM = c0VarA.m();
        if (iM < 200 || iM >= 300) {
            try {
                return Response.error(Utils.buffer(d0VarA), c0VarA);
            } finally {
                d0VarA.close();
            }
        }
        if (iM == 204 || iM == 205) {
            d0VarA.close();
            return Response.success((Object) null, c0VarA);
        }
        ExceptionCatchingRequestBody exceptionCatchingRequestBody = new ExceptionCatchingRequestBody(d0VarA);
        try {
            return Response.success(this.serviceMethod.toResponse(exceptionCatchingRequestBody), c0VarA);
        } catch (RuntimeException e2) {
            exceptionCatchingRequestBody.throwIfCaught();
            throw e2;
        }
    }

    @Override // retrofit2.Call
    public synchronized a0 request() {
        e eVar = this.rawCall;
        if (eVar != null) {
            return eVar.request();
        }
        if (this.creationFailure != null) {
            if (this.creationFailure instanceof IOException) {
                throw new RuntimeException("Unable to create request.", this.creationFailure);
            }
            if (this.creationFailure instanceof RuntimeException) {
                throw ((RuntimeException) this.creationFailure);
            }
            throw ((Error) this.creationFailure);
        }
        try {
            e eVarCreateRawCall = createRawCall();
            this.rawCall = eVarCreateRawCall;
            return eVarCreateRawCall.request();
        } catch (IOException e2) {
            this.creationFailure = e2;
            throw new RuntimeException("Unable to create request.", e2);
        } catch (Error e3) {
            e = e3;
            Utils.throwIfFatal(e);
            this.creationFailure = e;
            throw e;
        } catch (RuntimeException e4) {
            e = e4;
            Utils.throwIfFatal(e);
            this.creationFailure = e;
            throw e;
        }
    }

    @Override // retrofit2.Call
    public OkHttpCall<T> clone() {
        return new OkHttpCall<>(this.serviceMethod, this.args);
    }
}
