package com.umeng.commonsdk.statistics.internal;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.text.TextUtils;
import com.baidu.mobstat.Config;
import com.umeng.commonsdk.debug.UMLog;
import com.umeng.commonsdk.debug.UMRTLog;
import com.umeng.commonsdk.framework.UMEnvelopeBuild;
import com.umeng.commonsdk.internal.crash.UMCrashManager;
import com.umeng.commonsdk.statistics.AnalyticsConstants;
import com.umeng.commonsdk.statistics.SdkVersion;
import com.umeng.commonsdk.statistics.UMServerURL;
import com.umeng.commonsdk.statistics.common.DataHelper;
import com.umeng.commonsdk.statistics.common.DeviceConfig;
import com.umeng.commonsdk.statistics.common.HelperUtils;
import com.umeng.commonsdk.statistics.common.MLog;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.UnknownHostException;
import java.security.SecureRandom;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLHandshakeException;
import org.apache.http.conn.ssl.SSLSocketFactory;

/* JADX INFO: compiled from: NetworkHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class c {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static boolean f4159e = false;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f4160a = "10.0.0.172";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f4161b = 80;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Context f4162c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private b f4163d;

    public c(Context context) {
        this.f4162c = context;
    }

    private void b() {
        String strImprintProperty = UMEnvelopeBuild.imprintProperty(this.f4162c, "domain_p", "");
        String strImprintProperty2 = UMEnvelopeBuild.imprintProperty(this.f4162c, "domain_s", "");
        if (!TextUtils.isEmpty(strImprintProperty)) {
            UMServerURL.DEFAULT_URL = DataHelper.assembleURL(strImprintProperty);
        }
        if (!TextUtils.isEmpty(strImprintProperty2)) {
            UMServerURL.SECONDARY_URL = DataHelper.assembleURL(strImprintProperty2);
        }
        String strImprintProperty3 = UMEnvelopeBuild.imprintProperty(this.f4162c, "oversea_domain_p", "");
        String strImprintProperty4 = UMEnvelopeBuild.imprintProperty(this.f4162c, "oversea_domain_s", "");
        if (!TextUtils.isEmpty(strImprintProperty3)) {
            UMServerURL.OVERSEA_DEFAULT_URL = DataHelper.assembleURL(strImprintProperty3);
        }
        if (!TextUtils.isEmpty(strImprintProperty4)) {
            UMServerURL.OVERSEA_SECONDARY_URL = DataHelper.assembleURL(strImprintProperty4);
        }
        AnalyticsConstants.APPLOG_URL_LIST = new String[]{UMServerURL.OVERSEA_DEFAULT_URL, UMServerURL.OVERSEA_SECONDARY_URL};
        if (TextUtils.isEmpty(com.umeng.commonsdk.statistics.b.f4063b)) {
            return;
        }
        if (com.umeng.commonsdk.statistics.b.f4063b.startsWith("460") || com.umeng.commonsdk.statistics.b.f4063b.startsWith("461")) {
            AnalyticsConstants.APPLOG_URL_LIST = new String[]{UMServerURL.DEFAULT_URL, UMServerURL.SECONDARY_URL};
        }
    }

    private boolean c() {
        NetworkInfo activeNetworkInfo;
        String extraInfo;
        if (this.f4162c.getPackageManager().checkPermission("android.permission.ACCESS_NETWORK_STATE", this.f4162c.getPackageName()) != 0) {
            return false;
        }
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) this.f4162c.getSystemService("connectivity");
            return (!DeviceConfig.checkPermission(this.f4162c, "android.permission.ACCESS_NETWORK_STATE") || connectivityManager == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null || activeNetworkInfo.getType() == 1 || (extraInfo = activeNetworkInfo.getExtraInfo()) == null || (!extraInfo.equals("cmwap") && !extraInfo.equals("3gwap") && !extraInfo.equals("uniwap"))) ? false : true;
        } catch (Throwable th) {
            UMCrashManager.reportCrash(this.f4162c, th);
        }
    }

    public void a(b bVar) {
        this.f4163d = bVar;
    }

    private void a() {
        String strImprintProperty = UMEnvelopeBuild.imprintProperty(this.f4162c, "domain_p", "");
        String strImprintProperty2 = UMEnvelopeBuild.imprintProperty(this.f4162c, "domain_s", "");
        if (!TextUtils.isEmpty(strImprintProperty)) {
            UMServerURL.DEFAULT_URL = DataHelper.assembleURL(strImprintProperty);
        }
        if (!TextUtils.isEmpty(strImprintProperty2)) {
            UMServerURL.SECONDARY_URL = DataHelper.assembleURL(strImprintProperty2);
        }
        AnalyticsConstants.APPLOG_URL_LIST = new String[]{UMServerURL.DEFAULT_URL, UMServerURL.SECONDARY_URL};
    }

    public byte[] a(byte[] bArr, boolean z) {
        if (SdkVersion.SDK_TYPE == 0) {
            a();
        } else {
            UMServerURL.DEFAULT_URL = UMServerURL.OVERSEA_DEFAULT_URL;
            UMServerURL.SECONDARY_URL = UMServerURL.OVERSEA_SECONDARY_URL;
            b();
        }
        int i = 0;
        byte[] bArrB = null;
        while (true) {
            String[] strArr = AnalyticsConstants.APPLOG_URL_LIST;
            if (i >= strArr.length) {
                break;
            }
            bArrB = b(bArr, strArr[i]);
            if (bArrB != null) {
                b bVar = this.f4163d;
                if (bVar == null) {
                    break;
                }
                bVar.onRequestSucceed(z);
                break;
            }
            b bVar2 = this.f4163d;
            if (bVar2 != null) {
                bVar2.onRequestFailed();
            }
            i++;
        }
        return bArrB;
    }

    /* JADX WARN: Not initialized variable reg: 5, insn: 0x01e0: IF  (r5 I:??[int, boolean, OBJECT, ARRAY, byte, short, char]) == (0 ??[int, boolean, OBJECT, ARRAY, byte, short, char])  -> B:136:?, block:B:94:0x01e0 */
    /* JADX WARN: Not initialized variable reg: 8, insn: 0x01d4: IF  (r8 I:??[int, boolean, OBJECT, ARRAY, byte, short, char]) == (0 ??[int, boolean, OBJECT, ARRAY, byte, short, char])  -> B:94:0x01e0, block:B:89:0x01d4 */
    public byte[] b(byte[] bArr, String str) {
        HttpsURLConnection httpsURLConnection;
        OutputStream outputStream;
        OutputStream outputStream2;
        HttpsURLConnection httpsURLConnection2;
        try {
            try {
                if (this.f4163d != null) {
                    this.f4163d.onRequestStart();
                }
                if (c()) {
                    httpsURLConnection = (HttpsURLConnection) new URL(str).openConnection(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(this.f4160a, this.f4161b)));
                } else {
                    httpsURLConnection = (HttpsURLConnection) new URL(str).openConnection();
                }
                try {
                    if (!f4159e) {
                        httpsURLConnection.setHostnameVerifier(SSLSocketFactory.STRICT_HOSTNAME_VERIFIER);
                        SSLContext sSLContext = SSLContext.getInstance("TLS");
                        sSLContext.init(null, null, new SecureRandom());
                        httpsURLConnection.setSSLSocketFactory(sSLContext.getSocketFactory());
                        f4159e = true;
                    }
                    httpsURLConnection.setRequestProperty("X-Umeng-UTC", String.valueOf(System.currentTimeMillis()));
                    httpsURLConnection.setRequestProperty("X-Umeng-Sdk", a.a(this.f4162c).b());
                    httpsURLConnection.setRequestProperty("Content-Type", a.a(this.f4162c).a());
                    httpsURLConnection.setRequestProperty("Msg-Type", "envelope/json");
                    httpsURLConnection.setRequestProperty("X-Umeng-Pro-Ver", "1.0.0");
                    httpsURLConnection.setConnectTimeout(Config.SESSION_PERIOD);
                    httpsURLConnection.setReadTimeout(Config.SESSION_PERIOD);
                    httpsURLConnection.setRequestMethod("POST");
                    httpsURLConnection.setDoOutput(true);
                    httpsURLConnection.setDoInput(true);
                    boolean z = false;
                    httpsURLConnection.setUseCaches(false);
                    outputStream = httpsURLConnection.getOutputStream();
                    try {
                        outputStream.write(bArr);
                        outputStream.flush();
                        httpsURLConnection.connect();
                        if (this.f4163d != null) {
                            this.f4163d.onRequestEnd();
                        }
                        int responseCode = httpsURLConnection.getResponseCode();
                        String headerField = httpsURLConnection.getHeaderField("Content-Type");
                        if (!TextUtils.isEmpty(headerField) && headerField.equalsIgnoreCase("application/thrift")) {
                            z = true;
                        }
                        if (AnalyticsConstants.UM_DEBUG) {
                            MLog.i("status code : " + responseCode + "; isThrifit:" + z);
                        }
                        if (responseCode != 200 || !z) {
                            if (outputStream != null) {
                                try {
                                    outputStream.close();
                                } catch (Exception e2) {
                                    UMCrashManager.reportCrash(this.f4162c, e2);
                                }
                            }
                            if (httpsURLConnection != null) {
                                try {
                                    httpsURLConnection.getInputStream().close();
                                } catch (IOException unused) {
                                }
                                httpsURLConnection.disconnect();
                            }
                            return null;
                        }
                        MLog.i("Send message to server. status code is: " + responseCode);
                        UMRTLog.i(UMRTLog.RTLOG_TAG, "Send message to server. status code is: " + responseCode + "; url = " + str);
                        InputStream inputStream = httpsURLConnection.getInputStream();
                        try {
                            byte[] streamToByteArray = HelperUtils.readStreamToByteArray(inputStream);
                            HelperUtils.safeClose(inputStream);
                            if (outputStream != null) {
                                try {
                                    outputStream.close();
                                } catch (Exception e3) {
                                    UMCrashManager.reportCrash(this.f4162c, e3);
                                }
                            }
                            if (httpsURLConnection != null) {
                                try {
                                    httpsURLConnection.getInputStream().close();
                                } catch (IOException unused2) {
                                }
                                httpsURLConnection.disconnect();
                            }
                            return streamToByteArray;
                        } catch (Throwable th) {
                            HelperUtils.safeClose(inputStream);
                            throw th;
                        }
                    } catch (UnknownHostException unused3) {
                        UMLog.aq("A_10200", 2, "\\|");
                        if (outputStream != null) {
                            try {
                                outputStream.close();
                            } catch (Exception e4) {
                                UMCrashManager.reportCrash(this.f4162c, e4);
                            }
                        }
                        if (httpsURLConnection != null) {
                            try {
                                httpsURLConnection.getInputStream().close();
                            } catch (IOException unused4) {
                            }
                            httpsURLConnection.disconnect();
                        }
                        return null;
                    } catch (SSLHandshakeException unused5) {
                        UMLog.aq("A_10201", 2, "\\|");
                        if (outputStream != null) {
                            try {
                                outputStream.close();
                            } catch (Exception e5) {
                                UMCrashManager.reportCrash(this.f4162c, e5);
                            }
                        }
                        if (httpsURLConnection != null) {
                            try {
                                httpsURLConnection.getInputStream().close();
                            } catch (IOException unused6) {
                            }
                            httpsURLConnection.disconnect();
                        }
                        return null;
                    } catch (Throwable unused7) {
                        if (outputStream != null) {
                            try {
                                outputStream.close();
                            } catch (Exception e6) {
                                UMCrashManager.reportCrash(this.f4162c, e6);
                            }
                        }
                        if (httpsURLConnection != null) {
                            try {
                                httpsURLConnection.getInputStream().close();
                            } catch (IOException unused8) {
                            }
                            httpsURLConnection.disconnect();
                        }
                        return null;
                    }
                } catch (UnknownHostException unused9) {
                    outputStream = null;
                } catch (SSLHandshakeException unused10) {
                    outputStream = null;
                } catch (Throwable unused11) {
                    outputStream = null;
                }
            } catch (Throwable th2) {
                if (outputStream2 != null) {
                    try {
                        outputStream2.close();
                    } catch (Exception e7) {
                        UMCrashManager.reportCrash(this.f4162c, e7);
                    }
                }
                if (httpsURLConnection2 == null) {
                    throw th2;
                }
                try {
                    httpsURLConnection2.getInputStream().close();
                } catch (IOException unused12) {
                }
                httpsURLConnection2.disconnect();
                throw th2;
            }
        } catch (UnknownHostException unused13) {
            httpsURLConnection = null;
            outputStream = null;
        } catch (SSLHandshakeException unused14) {
            httpsURLConnection = null;
            outputStream = null;
        } catch (Throwable unused15) {
            httpsURLConnection = null;
            outputStream = null;
        }
    }

    public byte[] a(byte[] bArr, String str) {
        HttpURLConnection httpURLConnection;
        try {
            if (this.f4163d != null) {
                this.f4163d.onRequestStart();
            }
            if (c()) {
                httpURLConnection = (HttpURLConnection) new URL(str).openConnection(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(this.f4160a, this.f4161b)));
            } else {
                httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            }
            try {
                httpURLConnection.setRequestProperty("X-Umeng-UTC", String.valueOf(System.currentTimeMillis()));
                httpURLConnection.setRequestProperty("X-Umeng-Sdk", a.a(this.f4162c).b());
                httpURLConnection.setRequestProperty("Msg-Type", "envelope/json");
                httpURLConnection.setRequestProperty("Content-Type", a.a(this.f4162c).a());
                httpURLConnection.setRequestProperty("X-Umeng-Pro-Ver", "1.0.0");
                httpURLConnection.setConnectTimeout(10000);
                httpURLConnection.setReadTimeout(Config.SESSION_PERIOD);
                httpURLConnection.setRequestMethod("POST");
                boolean z = true;
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                httpURLConnection.setUseCaches(false);
                if (Build.VERSION.SDK_INT < 8) {
                    System.setProperty("http.keepAlive", "false");
                }
                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(bArr);
                outputStream.flush();
                outputStream.close();
                if (this.f4163d != null) {
                    this.f4163d.onRequestEnd();
                }
                int responseCode = httpURLConnection.getResponseCode();
                String headerField = httpURLConnection.getHeaderField("Content-Type");
                if (TextUtils.isEmpty(headerField) || !headerField.equalsIgnoreCase("application/thrift")) {
                    z = false;
                }
                if (AnalyticsConstants.UM_DEBUG) {
                    MLog.d("status code : " + responseCode + "; isThrifit:" + z);
                }
                if (responseCode != 200 || !z) {
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    return null;
                }
                MLog.i("Send message to " + str);
                InputStream inputStream = httpURLConnection.getInputStream();
                try {
                    byte[] streamToByteArray = HelperUtils.readStreamToByteArray(inputStream);
                    HelperUtils.safeClose(inputStream);
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    return streamToByteArray;
                } catch (Throwable th) {
                    HelperUtils.safeClose(inputStream);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                try {
                    MLog.e("IOException,Failed to send message.", th);
                    return null;
                } finally {
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                }
            }
        } catch (Throwable th3) {
            th = th3;
            httpURLConnection = null;
        }
    }
}
