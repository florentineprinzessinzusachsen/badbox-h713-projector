package com.android.umanalytics.http;

import c.a.a0.n;

/* JADX INFO: loaded from: classes.dex */
public class HttpResultFunc<T> implements n<HttpResult<T>, T> {
    @Override // c.a.a0.n
    public T apply(HttpResult<T> httpResult) {
        if (httpResult.getCode() == 0) {
            return httpResult.getData();
        }
        throw new ApiException(httpResult.getCode());
    }
}
