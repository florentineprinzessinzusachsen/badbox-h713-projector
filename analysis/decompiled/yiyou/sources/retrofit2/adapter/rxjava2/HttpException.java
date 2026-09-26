package retrofit2.adapter.rxjava2;

import retrofit2.Response;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public final class HttpException extends retrofit2.HttpException {
    public HttpException(Response<?> response) {
        super(response);
    }
}
