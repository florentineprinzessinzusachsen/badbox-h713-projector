package com.hs.p.basic;

import android.annotation.SuppressLint;
import android.util.Log;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/* JADX INFO: loaded from: /Users/ruben/projector-dump/downloads/plugin.jar */
public class SSLUtils {
    private static HostnameVerifier sHostnameVerifier = null;
    private static boolean sSSLIgnoreEnabled = false;
    private static SSLSocketFactory sSSLSocketFactory;
    private static X509TrustManager sTrustManager;

    private static void configureHttpsURLConnectionImpl() {
        try {
            Class.forName("com.android.okhttp.internal.huc.HttpsURLConnectionImpl");
        } catch (Exception unused) {
        }
    }

    private static void configureOkHttpClientDefault() {
        try {
            Class<?> cls = Class.forName("com.android.okhttp.OkHttpClient");
            Object objNewInstance = cls.newInstance();
            try {
                cls.getMethod("setSslSocketFactory", SSLSocketFactory.class).invoke(objNewInstance, sSSLSocketFactory);
            } catch (Exception unused) {
            }
            cls.getMethod("setHostnameVerifier", HostnameVerifier.class).invoke(objNewInstance, sHostnameVerifier);
        } catch (Exception unused2) {
        }
    }

    private static void configureOkHttpInternal() {
        try {
            Class.forName("com.android.okhttp.internal.Internal");
        } catch (Exception unused) {
        }
    }

    private static void configureOkHttpSSL() {
        try {
            configureOkHttpClientDefault();
            configureOkHttpInternal();
            configureHttpsURLConnectionImpl();
        } catch (Exception unused) {
        }
    }

    @SuppressLint({"TrustAllX509TrustManager"})
    public static void configureSSLIgnore(HttpsURLConnection httpsURLConnection) {
        if (httpsURLConnection == null) {
            return;
        }
        try {
            Log.d("SSLUtils", "Configuring SSL ignore for connection: " + httpsURLConnection.getURL());
            TrustManager[] trustManagerArr = {new X509TrustManager() { // from class: com.hs.p.basic.SSLUtils.3
                @Override // javax.net.ssl.X509TrustManager
                public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) {
                }

                @Override // javax.net.ssl.X509TrustManager
                public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) {
                }

                @Override // javax.net.ssl.X509TrustManager
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
            }};
            SSLContext sSLContext = SSLContext.getInstance("TLS");
            sSLContext.init(null, trustManagerArr, new SecureRandom());
            httpsURLConnection.setSSLSocketFactory(sSLContext.getSocketFactory());
            httpsURLConnection.setHostnameVerifier(new HostnameVerifier() { // from class: com.hs.p.basic.SSLUtils.4
                @Override // javax.net.ssl.HostnameVerifier
                public boolean verify(String str, SSLSession sSLSession) {
                    Log.d("SSLUtils", "Per-connection HostnameVerifier: Accepting hostname: " + str);
                    return true;
                }
            });
            Log.d("SSLUtils", "SSL ignore configured successfully for connection");
        } catch (Exception e) {
            Log.e("SSLUtils", "Failed to configure SSL ignore for connection", e);
            e.printStackTrace();
        }
    }

    @SuppressLint({"TrustAllX509TrustManager"})
    public static void enableSSLIgnore() {
        if (sSSLIgnoreEnabled) {
            return;
        }
        try {
            Log.i("SSLUtils", "Enabling SSL certificate ignore for all HTTPS connections");
            X509TrustManager x509TrustManager = new X509TrustManager() { // from class: com.hs.p.basic.SSLUtils.1
                @Override // javax.net.ssl.X509TrustManager
                public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) {
                    Log.d("SSLUtils", "checkClientTrusted: Accepting all client certificates");
                }

                @Override // javax.net.ssl.X509TrustManager
                public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) {
                    Log.d("SSLUtils", "checkServerTrusted: Accepting all server certificates");
                }

                @Override // javax.net.ssl.X509TrustManager
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
            };
            sTrustManager = x509TrustManager;
            TrustManager[] trustManagerArr = {x509TrustManager};
            SSLContext sSLContext = SSLContext.getInstance("TLS");
            sSLContext.init(null, trustManagerArr, new SecureRandom());
            SSLSocketFactory socketFactory = sSLContext.getSocketFactory();
            sSSLSocketFactory = socketFactory;
            HttpsURLConnection.setDefaultSSLSocketFactory(socketFactory);
            Log.i("SSLUtils", "Set default SSLSocketFactory for HttpsURLConnection");
            HostnameVerifier hostnameVerifier = new HostnameVerifier() { // from class: com.hs.p.basic.SSLUtils.2
                @Override // javax.net.ssl.HostnameVerifier
                public boolean verify(String str, SSLSession sSLSession) {
                    Log.d("SSLUtils", "HostnameVerifier: Accepting hostname: " + str);
                    return true;
                }
            };
            sHostnameVerifier = hostnameVerifier;
            HttpsURLConnection.setDefaultHostnameVerifier(hostnameVerifier);
            Log.i("SSLUtils", "Set default HostnameVerifier for HttpsURLConnection");
            sSSLIgnoreEnabled = true;
            configureOkHttpSSL();
            Log.i("SSLUtils", "SSL certificate ignore enabled successfully");
        } catch (Exception e) {
            Log.e("SSLUtils", "Failed to enable SSL ignore", e);
            e.printStackTrace();
        }
    }

    public static HostnameVerifier getHostnameVerifier() {
        if (!sSSLIgnoreEnabled) {
            enableSSLIgnore();
        }
        return sHostnameVerifier;
    }

    public static SSLSocketFactory getSSLSocketFactory() {
        if (!sSSLIgnoreEnabled) {
            enableSSLIgnore();
        }
        return sSSLSocketFactory;
    }

    public static X509TrustManager getTrustManager() {
        if (!sSSLIgnoreEnabled) {
            enableSSLIgnore();
        }
        return sTrustManager;
    }

    public static boolean isSSLIgnoreEnabled() {
        return sSSLIgnoreEnabled;
    }
}
