package com.umeng.commonsdk.stateless;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import com.baidu.mobstat.Config;
import com.umeng.commonsdk.debug.UMRTLog;
import com.umeng.commonsdk.framework.UMEnvelopeBuild;
import com.umeng.commonsdk.internal.crash.UMCrashManager;
import com.umeng.commonsdk.statistics.SdkVersion;
import com.umeng.commonsdk.statistics.common.DataHelper;
import com.umeng.commonsdk.statistics.common.DeviceConfig;
import com.umeng.commonsdk.statistics.common.MLog;
import com.umeng.commonsdk.statistics.common.ULog;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.security.SecureRandom;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLHandshakeException;
import org.apache.http.conn.ssl.SSLSocketFactory;

/* JADX INFO: compiled from: UMSLNetWorkSenderHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f4052a = "10.0.0.172";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f4053b = 80;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Context f4054c;

    public e(Context context) {
        this.f4054c = context;
    }

    private void a() {
        String strImprintProperty = UMEnvelopeBuild.imprintProperty(this.f4054c, "sl_domain_p", "");
        if (TextUtils.isEmpty(strImprintProperty)) {
            return;
        }
        a.g = DataHelper.assembleStatelessURL(strImprintProperty);
    }

    private void b() {
        String strImprintProperty = UMEnvelopeBuild.imprintProperty(this.f4054c, "sl_domain_p", "");
        String strImprintProperty2 = UMEnvelopeBuild.imprintProperty(this.f4054c, "oversea_sl_domain_p", "");
        if (!TextUtils.isEmpty(strImprintProperty)) {
            a.f4026f = DataHelper.assembleStatelessURL(strImprintProperty);
        }
        if (!TextUtils.isEmpty(strImprintProperty2)) {
            a.h = DataHelper.assembleStatelessURL(strImprintProperty2);
        }
        a.g = a.h;
        if (TextUtils.isEmpty(com.umeng.commonsdk.statistics.b.f4063b)) {
            return;
        }
        if (com.umeng.commonsdk.statistics.b.f4063b.startsWith("460") || com.umeng.commonsdk.statistics.b.f4063b.startsWith("461")) {
            a.g = a.f4026f;
        }
    }

    private boolean c() {
        NetworkInfo activeNetworkInfo;
        String extraInfo;
        Context context = this.f4054c;
        if (context == null || context.getPackageManager().checkPermission("android.permission.ACCESS_NETWORK_STATE", this.f4054c.getPackageName()) != 0) {
            return false;
        }
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) this.f4054c.getSystemService("connectivity");
            return (!DeviceConfig.checkPermission(this.f4054c, "android.permission.ACCESS_NETWORK_STATE") || connectivityManager == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null || activeNetworkInfo.getType() == 1 || (extraInfo = activeNetworkInfo.getExtraInfo()) == null || (!extraInfo.equals("cmwap") && !extraInfo.equals("3gwap") && !extraInfo.equals("uniwap"))) ? false : true;
        } catch (Throwable th) {
            UMCrashManager.reportCrash(this.f4054c, th);
        }
    }

    public boolean a(byte[] bArr, String str) {
        HttpsURLConnection httpsURLConnection;
        InputStream inputStream;
        boolean z = false;
        if (bArr != null && str != null) {
            if (SdkVersion.SDK_TYPE == 0) {
                a();
            } else {
                a.f4026f = a.h;
                b();
            }
            OutputStream outputStream = null;
            try {
                try {
                    try {
                        if (c()) {
                            httpsURLConnection = (HttpsURLConnection) new URL(a.g + "/" + str).openConnection(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(this.f4052a, this.f4053b)));
                        } else {
                            httpsURLConnection = (HttpsURLConnection) new URL(a.g + "/" + str).openConnection();
                        }
                        try {
                            httpsURLConnection.setHostnameVerifier(SSLSocketFactory.STRICT_HOSTNAME_VERIFIER);
                            SSLContext sSLContext = SSLContext.getInstance("TLS");
                            sSLContext.init(null, null, new SecureRandom());
                            httpsURLConnection.setSSLSocketFactory(sSLContext.getSocketFactory());
                            httpsURLConnection.setRequestProperty("X-Umeng-UTC", String.valueOf(System.currentTimeMillis()));
                            httpsURLConnection.setRequestProperty("Msg-Type", "envelope/json");
                            httpsURLConnection.setConnectTimeout(Config.SESSION_PERIOD);
                            httpsURLConnection.setReadTimeout(Config.SESSION_PERIOD);
                            httpsURLConnection.setRequestMethod("POST");
                            httpsURLConnection.setDoOutput(true);
                            httpsURLConnection.setDoInput(true);
                            httpsURLConnection.setUseCaches(false);
                            outputStream = httpsURLConnection.getOutputStream();
                            outputStream.write(bArr);
                            outputStream.flush();
                            httpsURLConnection.connect();
                            if (httpsURLConnection.getResponseCode() == 200) {
                                UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> send stateless message success : " + a.g + "/" + str);
                                z = true;
                            }
                            if (outputStream != null) {
                                try {
                                    outputStream.close();
                                } catch (Exception unused) {
                                }
                            }
                            if (httpsURLConnection != null) {
                                inputStream = httpsURLConnection.getInputStream();
                                inputStream.close();
                                httpsURLConnection.disconnect();
                            }
                        } catch (SSLHandshakeException e2) {
                            e = e2;
                            MLog.e("SSLHandshakeException, Failed to send message.", e);
                            if (outputStream != null) {
                                try {
                                    outputStream.close();
                                } catch (Exception unused2) {
                                }
                            }
                            if (httpsURLConnection != null) {
                                inputStream = httpsURLConnection.getInputStream();
                            }
                            return z;
                        } catch (Throwable th) {
                            th = th;
                            MLog.e("Exception,Failed to send message.", th);
                            if (outputStream != null) {
                                try {
                                    outputStream.close();
                                } catch (Exception unused3) {
                                }
                            }
                            if (httpsURLConnection != null) {
                                inputStream = httpsURLConnection.getInputStream();
                            }
                            return z;
                        }
                    } catch (IOException unused4) {
                    }
                } catch (SSLHandshakeException e3) {
                    e = e3;
                    httpsURLConnection = null;
                } catch (Throwable th2) {
                    th = th2;
                    httpsURLConnection = null;
                }
                return z;
            } catch (Throwable th3) {
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (Exception unused5) {
                    }
                }
                if (httpsURLConnection == null) {
                    throw th3;
                }
                try {
                    httpsURLConnection.getInputStream().close();
                } catch (IOException unused6) {
                }
                httpsURLConnection.disconnect();
                throw th3;
            }
        }
        ULog.i("walle", "[stateless] sendMessage, envelopeByte == null || path == null ");
        return false;
    }

    public boolean b(byte[] bArr, String str) {
        HttpURLConnection httpURLConnection;
        boolean z = false;
        if (bArr == null || str == null) {
            return false;
        }
        OutputStream outputStream = null;
        try {
            if (c()) {
                httpURLConnection = (HttpURLConnection) new URL(a.g + "/" + str).openConnection(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(this.f4052a, this.f4053b)));
            } else {
                httpURLConnection = (HttpURLConnection) new URL(a.g + "/" + str).openConnection();
            }
            try {
                httpURLConnection.setRequestProperty("X-Umeng-UTC", String.valueOf(System.currentTimeMillis()));
                httpURLConnection.setRequestProperty("Msg-Type", "envelope/json");
                httpURLConnection.setConnectTimeout(Config.SESSION_PERIOD);
                httpURLConnection.setReadTimeout(Config.SESSION_PERIOD);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                httpURLConnection.setUseCaches(false);
                outputStream = httpURLConnection.getOutputStream();
                outputStream.write(bArr);
                outputStream.flush();
                httpURLConnection.connect();
                z = httpURLConnection.getResponseCode() == 200;
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (Exception unused) {
                    }
                }
                if (httpURLConnection != null) {
                }
            } catch (Throwable th) {
                th = th;
                try {
                    UMCrashManager.reportCrash(this.f4054c, th);
                    return z;
                } finally {
                    if (outputStream != null) {
                        try {
                            outputStream.close();
                        } catch (Exception unused2) {
                        }
                    }
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                }
            }
        } catch (Throwable th2) {
            th = th2;
            httpURLConnection = null;
        }
        return z;
    }
}
