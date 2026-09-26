package com.android.umanalytics.http;

import android.util.Log;
import b.b.a.f;
import b.c.a.b;
import b.c.a.d;
import com.android.umanalytics.App;
import com.android.umanalytics.http.utils.AES;
import com.android.umanalytics.http.utils.MD5Utils;
import com.android.umanalytics.http.utils.MapUtils;
import com.baidu.mobstat.Config;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.PathUtils;
import d.a0;
import d.b0;
import d.c0;
import d.i0.a;
import d.q;
import d.t;
import d.u;
import d.v;
import d.x;
import e.c;
import java.io.File;
import java.net.Proxy;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/* JADX INFO: loaded from: classes.dex */
public class HttpUtils {
    public static final int DEFAULT_TIMEOUT = 15;
    private static HttpUtils instance;
    private f gson = new f();
    d mLoggingInterceptor;
    u mParamsInterceptor;

    public HttpUtils() {
        d.e eVar = new d.e();
        eVar.b(App.b().a());
        eVar.a(b.BASIC);
        eVar.a(4);
        eVar.a("Network");
        eVar.b("Network");
        this.mLoggingInterceptor = eVar.a();
        this.mParamsInterceptor = new u() { // from class: com.android.umanalytics.http.HttpUtils.7
            @Override // d.u
            public c0 intercept(u.a aVar) {
                a0 a0VarA;
                a0 a0VarRequest = aVar.request();
                String strE = a0VarRequest.e();
                HashMap map = new HashMap();
                if ("GET".equals(strE)) {
                    t tVarG = a0VarRequest.g();
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < tVarG.m(); i++) {
                        map.put(tVarG.a(i), tVarG.b(i));
                    }
                    LogUtils.d("jsonStr: " + HttpUtils.this.gson.a(map));
                    Iterator<Map.Entry<String, String>> it = MapUtils.sortMapByKey(map).entrySet().iterator();
                    while (it.hasNext()) {
                        sb.append(it.next().getValue());
                    }
                    String mD5String = MD5Utils.getMD5String(sb.toString());
                    t.a aVarI = tVarG.i();
                    Iterator it2 = map.entrySet().iterator();
                    while (it2.hasNext()) {
                        aVarI.d((String) ((Map.Entry) it2.next()).getKey());
                    }
                    map.put("sign", mD5String);
                    String strA = HttpUtils.this.gson.a(map);
                    LogUtils.d("jsonStr: " + strA);
                    try {
                        String strEncrypt = AES.Encrypt(strA, AES.cKey, AES.aes_iv);
                        LogUtils.d("aesData: " + strEncrypt);
                        aVarI.b("value", strEncrypt);
                        a0.a aVarF = a0VarRequest.f();
                        aVarF.a(aVarI.a());
                        a0VarA = aVarF.a();
                        a0VarRequest = a0VarA;
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        LogUtils.e("AES加密失败：" + e2.toString());
                    }
                } else if ("POST".equals(strE)) {
                    if (a0VarRequest.a() instanceof q) {
                        q.a aVar2 = new q.a();
                        q qVar = (q) a0VarRequest.a();
                        StringBuilder sb2 = new StringBuilder();
                        for (int i2 = 0; i2 < qVar.a(); i2++) {
                            map.put(qVar.c(i2), qVar.d(i2));
                        }
                        Iterator<Map.Entry<String, String>> it3 = MapUtils.sortMapByKey(map).entrySet().iterator();
                        while (it3.hasNext()) {
                            sb2.append(it3.next().getValue());
                        }
                        map.put("sign", MD5Utils.getMD5String(sb2.toString()));
                        String strA2 = HttpUtils.this.gson.a(map);
                        LogUtils.d("jsonStr: " + strA2);
                        try {
                            String strEncrypt2 = AES.Encrypt(strA2, AES.cKey, AES.aes_iv);
                            LogUtils.d("aesData: " + strEncrypt2);
                            aVar2.a("value", strEncrypt2);
                            a0.a aVarF2 = a0VarRequest.f();
                            aVarF2.a(aVar2.a());
                            a0VarA = aVarF2.a();
                            a0VarRequest = a0VarA;
                        } catch (Exception e3) {
                            e3.printStackTrace();
                            LogUtils.e("AES加密失败：" + e3.toString());
                        }
                    } else if (a0VarRequest.a() instanceof b0) {
                        LogUtils.i("intercept: " + a0VarRequest.g());
                        b0 b0VarA = a0VarRequest.a();
                        c cVar = new c();
                        b0VarA.writeTo(cVar);
                        String strO = cVar.o();
                        f fVar = new f();
                        HashMap map2 = (HashMap) fVar.a(strO, HashMap.class);
                        StringBuilder sb3 = new StringBuilder();
                        Iterator<Map.Entry<String, String>> it4 = MapUtils.sortMapByKey(map2).entrySet().iterator();
                        while (it4.hasNext()) {
                            sb3.append(it4.next().getValue());
                        }
                        map2.put("sign", MD5Utils.getMD5String(sb3.toString()));
                        b0 b0VarCreate = b0.create(v.b("application/json; charset=UTF-8"), fVar.a(map2));
                        a0.a aVarF3 = a0VarRequest.f();
                        aVarF3.a(b0VarCreate);
                        a0VarRequest = aVarF3.a();
                    }
                }
                return aVar.a(a0VarRequest);
            }
        };
    }

    public static HttpUtils getInstance() {
        if (instance == null) {
            synchronized (HttpUtils.class) {
                if (instance == null) {
                    instance = new HttpUtils();
                }
            }
        }
        return instance;
    }

    private x getUnsafeOkHttpClient() {
        x.b bVar;
        try {
            TrustManager[] trustManagerArr = {new X509TrustManager() { // from class: com.android.umanalytics.http.HttpUtils.1
                @Override // javax.net.ssl.X509TrustManager
                public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) {
                }

                @Override // javax.net.ssl.X509TrustManager
                public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) {
                }

                @Override // javax.net.ssl.X509TrustManager
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }
            }};
            SSLContext sSLContext = SSLContext.getInstance("SSL");
            sSLContext.init(null, trustManagerArr, new SecureRandom());
            SSLSocketFactory socketFactory = sSLContext.getSocketFactory();
            if (App.b().a()) {
                bVar = new x.b();
            } else {
                bVar = new x.b();
                bVar.a(Proxy.NO_PROXY);
            }
            bVar.a(socketFactory);
            bVar.a(new HostnameVerifier() { // from class: com.android.umanalytics.http.HttpUtils.2
                @Override // javax.net.ssl.HostnameVerifier
                public boolean verify(String str, SSLSession sSLSession) {
                    return true;
                }
            });
            bVar.a(15L, TimeUnit.SECONDS);
            a.EnumC0103a enumC0103a = a.EnumC0103a.BODY;
            a aVar = new a(new a.b() { // from class: com.android.umanalytics.http.HttpUtils.3
                @Override // d.i0.a.b
                public void log(String str) {
                    if (App.b().a()) {
                        Log.d("okHttp", str);
                    }
                }
            });
            aVar.a(enumC0103a);
            bVar.a(aVar);
            bVar.a(this.mLoggingInterceptor);
            bVar.a(this.mParamsInterceptor);
            bVar.a(new CacheInterceptor());
            bVar.b(new CacheInterceptor());
            bVar.a(new d.c(new File(PathUtils.getInternalAppCachePath(), "response"), Config.FULL_TRACE_LOG_LIMIT));
            return bVar.a();
        } catch (Exception e2) {
            throw new RuntimeException(e2);
        }
    }

    private static x getUnsafeOkHttpClientNoSign() {
        x.b bVar;
        try {
            TrustManager[] trustManagerArr = {new X509TrustManager() { // from class: com.android.umanalytics.http.HttpUtils.4
                @Override // javax.net.ssl.X509TrustManager
                public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) {
                }

                @Override // javax.net.ssl.X509TrustManager
                public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) {
                }

                @Override // javax.net.ssl.X509TrustManager
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }
            }};
            SSLContext sSLContext = SSLContext.getInstance("SSL");
            sSLContext.init(null, trustManagerArr, new SecureRandom());
            SSLSocketFactory socketFactory = sSLContext.getSocketFactory();
            if (App.b().a()) {
                bVar = new x.b();
            } else {
                bVar = new x.b();
                bVar.a(Proxy.NO_PROXY);
            }
            bVar.a(socketFactory);
            bVar.a(new HostnameVerifier() { // from class: com.android.umanalytics.http.HttpUtils.5
                @Override // javax.net.ssl.HostnameVerifier
                public boolean verify(String str, SSLSession sSLSession) {
                    return true;
                }
            });
            bVar.a(15L, TimeUnit.SECONDS);
            a.EnumC0103a enumC0103a = a.EnumC0103a.BODY;
            a aVar = new a(new a.b() { // from class: com.android.umanalytics.http.HttpUtils.6
                @Override // d.i0.a.b
                public void log(String str) {
                    if (App.b().a()) {
                        Log.d("Network", str);
                    }
                }
            });
            aVar.a(enumC0103a);
            bVar.a(aVar);
            return bVar.a();
        } catch (Exception e2) {
            throw new RuntimeException(e2);
        }
    }

    public Retrofit getRetrofit() {
        return new Retrofit.Builder().client(getUnsafeOkHttpClient()).addConverterFactory(ResponseConvertFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).baseUrl("http://www.baidu.com").build();
    }

    public Retrofit getRetrofit4NoFilter(String str) {
        return new Retrofit.Builder().client(getUnsafeOkHttpClient()).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).baseUrl(str).build();
    }

    public Retrofit getRetrofit(String str) {
        return new Retrofit.Builder().client(getUnsafeOkHttpClient()).addConverterFactory(ResponseConvertFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).baseUrl(str).build();
    }

    public Retrofit getRetrofit4NoFilter() {
        return new Retrofit.Builder().client(getUnsafeOkHttpClientNoSign()).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).baseUrl("http://www.baidu.com").build();
    }
}
