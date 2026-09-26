package retrofit2.adapter.rxjava2;

import c.a.a;
import c.a.l;
import c.a.t;
import java.lang.reflect.Type;
import retrofit2.Call;
import retrofit2.CallAdapter;

/* JADX INFO: loaded from: classes.dex */
final class RxJava2CallAdapter<R> implements CallAdapter<R, Object> {
    private final boolean isAsync;
    private final boolean isBody;
    private final boolean isCompletable;
    private final boolean isFlowable;
    private final boolean isMaybe;
    private final boolean isResult;
    private final boolean isSingle;
    private final Type responseType;
    private final t scheduler;

    RxJava2CallAdapter(Type type, t tVar, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7) {
        this.responseType = type;
        this.scheduler = tVar;
        this.isAsync = z;
        this.isResult = z2;
        this.isBody = z3;
        this.isFlowable = z4;
        this.isSingle = z5;
        this.isMaybe = z6;
        this.isCompletable = z7;
    }

    @Override // retrofit2.CallAdapter
    public Object adapt(Call<R> call) {
        l bodyObservable;
        l callEnqueueObservable = this.isAsync ? new CallEnqueueObservable(call) : new CallExecuteObservable(call);
        if (this.isResult) {
            bodyObservable = new ResultObservable(callEnqueueObservable);
        } else {
            bodyObservable = this.isBody ? new BodyObservable(callEnqueueObservable) : callEnqueueObservable;
        }
        t tVar = this.scheduler;
        if (tVar != null) {
            bodyObservable = bodyObservable.subscribeOn(tVar);
        }
        if (this.isFlowable) {
            return bodyObservable.toFlowable(a.LATEST);
        }
        if (this.isSingle) {
            return bodyObservable.singleOrError();
        }
        if (this.isMaybe) {
            return bodyObservable.singleElement();
        }
        return this.isCompletable ? bodyObservable.ignoreElements() : bodyObservable;
    }

    @Override // retrofit2.CallAdapter
    public Type responseType() {
        return this.responseType;
    }
}
