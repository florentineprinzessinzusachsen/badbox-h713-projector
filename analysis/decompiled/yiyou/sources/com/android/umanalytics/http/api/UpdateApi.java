package com.android.umanalytics.http.api;

import c.a.l;
import com.android.umanalytics.http.bean.BaiduLocationBean;
import com.android.umanalytics.http.bean.MGRecomBean;
import com.android.umanalytics.http.bean.UpdateDesBean;
import com.android.umanalytics.http.bean.UpdateInfoBean;
import com.android.umanalytics.http.bean.WeatherBean;
import d.d0;
import retrofit2.http.GET;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/* JADX INFO: loaded from: classes.dex */
public interface UpdateApi {
    @Streaming
    @GET
    l<d0> downloadFile(@Url String str);

    @GET
    l<BaiduLocationBean> getLocation(@Url String str);

    @GET
    l<MGRecomBean> getMGRecomInfo(@Url String str);

    @GET
    l<UpdateDesBean> getUpdateDes(@Url String str);

    @GET
    l<UpdateInfoBean> getUpdateInfo(@Url String str);

    @GET
    l<WeatherBean> getWeather(@Url String str);
}
