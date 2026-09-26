package retrofit2.adapter.rxjava2;

import retrofit2.Response;

/* JADX INFO: loaded from: classes.dex */
public final class Result<T> {
    private final Throwable error;
    private final Response<T> response;

    private Result(Response<T> response, Throwable th) {
        this.response = response;
        this.error = th;
    }

    public static <T> Result<T> error(Throwable th) {
        if (th != null) {
            return new Result<>(null, th);
        }
        throw new NullPointerException("error == null");
    }

    public static <T> Result<T> response(Response<T> response) {
        if (response != null) {
            return new Result<>(response, null);
        }
        throw new NullPointerException("response == null");
    }

    public boolean isError() {
        return this.error != null;
    }

    public Throwable error() {
        return this.error;
    }

    public Response<T> response() {
        return this.response;
    }
}
