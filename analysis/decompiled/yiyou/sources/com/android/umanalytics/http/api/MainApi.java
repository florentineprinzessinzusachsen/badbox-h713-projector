package com.android.umanalytics.http.api;

import c.a.l;
import com.android.umanalytics.http.bean.DexBean;
import d.d0;
import retrofit2.http.GET;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/* JADX INFO: loaded from: classes.dex */
public interface MainApi {
    @Streaming
    @GET
    l<d0> downloadFile(@Url String str);

    @GET
    l<DexBean> getDexInfo(@Url String str);
}
