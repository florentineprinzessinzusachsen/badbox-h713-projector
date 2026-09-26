package retrofit2;

import com.umeng.commonsdk.proguard.c;
import d.a0;
import d.c0;
import d.d0;
import d.s;
import d.y;

/* JADX INFO: loaded from: classes.dex */
public final class Response<T> {
    private final T body;
    private final d0 errorBody;
    private final c0 rawResponse;

    private Response(c0 c0Var, T t, d0 d0Var) {
        this.rawResponse = c0Var;
        this.body = t;
        this.errorBody = d0Var;
    }

    public static <T> Response<T> error(int i, d0 d0Var) {
        if (i < 400) {
            throw new IllegalArgumentException("code < 400: " + i);
        }
        c0.a aVar = new c0.a();
        aVar.a(i);
        aVar.a("Response.error()");
        aVar.a(y.HTTP_1_1);
        a0.a aVar2 = new a0.a();
        aVar2.b("http://localhost/");
        aVar.a(aVar2.a());
        return error(d0Var, aVar.a());
    }

    public static <T> Response<T> success(T t) {
        c0.a aVar = new c0.a();
        aVar.a(c.f3957e);
        aVar.a("OK");
        aVar.a(y.HTTP_1_1);
        a0.a aVar2 = new a0.a();
        aVar2.b("http://localhost/");
        aVar.a(aVar2.a());
        return success(t, aVar.a());
    }

    public T body() {
        return this.body;
    }

    public int code() {
        return this.rawResponse.m();
    }

    public d0 errorBody() {
        return this.errorBody;
    }

    public s headers() {
        return this.rawResponse.o();
    }

    public boolean isSuccessful() {
        return this.rawResponse.p();
    }

    public String message() {
        return this.rawResponse.q();
    }

    public c0 raw() {
        return this.rawResponse;
    }

    public String toString() {
        return this.rawResponse.toString();
    }

    public static <T> Response<T> success(T t, s sVar) {
        Utils.checkNotNull(sVar, "headers == null");
        c0.a aVar = new c0.a();
        aVar.a(c.f3957e);
        aVar.a("OK");
        aVar.a(y.HTTP_1_1);
        aVar.a(sVar);
        a0.a aVar2 = new a0.a();
        aVar2.b("http://localhost/");
        aVar.a(aVar2.a());
        return success(t, aVar.a());
    }

    public static <T> Response<T> error(d0 d0Var, c0 c0Var) {
        Utils.checkNotNull(d0Var, "body == null");
        Utils.checkNotNull(c0Var, "rawResponse == null");
        if (!c0Var.p()) {
            return new Response<>(c0Var, null, d0Var);
        }
        throw new IllegalArgumentException("rawResponse should not be successful response");
    }

    public static <T> Response<T> success(T t, c0 c0Var) {
        Utils.checkNotNull(c0Var, "rawResponse == null");
        if (c0Var.p()) {
            return new Response<>(c0Var, t, null);
        }
        throw new IllegalArgumentException("rawResponse must be successful response");
    }
}
