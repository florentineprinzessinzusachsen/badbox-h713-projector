package com.android.umanalytics.http.api;

import c.a.l;
import com.android.umanalytics.http.bean.LetvLocation;
import com.android.umanalytics.http.bean.TaobaoLocation;
import retrofit2.http.GET;
import retrofit2.http.Headers;

/* JADX INFO: loaded from: classes.dex */
public interface LocationApi {
    @GET("https://g3.le.com/r?format=1")
    l<LetvLocation> getLocationByLetv();

    @Headers({"User-Agent:Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; rv:11.0) like Gecko", "Cache-Control: max-age=3600"})
    @GET("http://ip.taobao.com/service/getIpInfo.php?ip=myip")
    l<TaobaoLocation> getLocationByTaobao();
}
