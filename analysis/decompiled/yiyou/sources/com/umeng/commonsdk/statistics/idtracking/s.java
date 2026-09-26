package com.umeng.commonsdk.statistics.idtracking;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.baidu.mobstat.Config;
import com.umeng.commonsdk.statistics.internal.PreferenceWrapper;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import org.apache.http.conn.ssl.BrowserCompatHostnameVerifier;

/* JADX INFO: compiled from: UUIDTracker.java */
/* JADX INFO: loaded from: classes.dex */
public class s extends a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f4147a = "uuid";

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static final String f4148e = "yosuid";

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static final String f4149f = "23346339";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Context f4150b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private String f4151c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private String f4152d;

    public s(Context context) {
        super(f4147a);
        this.f4150b = null;
        this.f4151c = null;
        this.f4152d = null;
        this.f4150b = context;
        this.f4151c = null;
        this.f4152d = null;
    }

    public static String a(String str, String str2) {
        try {
            return (String) Class.forName("android.os.SystemProperties").getMethod("get", String.class, String.class).invoke(null, str, str2);
        } catch (Exception unused) {
            return str2;
        }
    }

    /* JADX WARN: Code duplicated, block: B:108:? A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:69:0x011b  */
    /* JADX WARN: Code duplicated, block: B:83:0x0106 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:85:0x0114 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:89:0x010d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Multi-variable type inference failed */
    private String b(String str) throws Throwable {
        InputStream inputStream;
        HttpsURLConnection httpsURLConnection;
        HttpsURLConnection httpsURLConnection2;
        this.f4152d = a("ro.yunos.openuuid", "");
        if (!TextUtils.isEmpty(this.f4152d)) {
            return this.f4152d;
        }
        this.f4151c = a("ro.aliyun.clouduuid", "");
        if (TextUtils.isEmpty(this.f4151c)) {
            this.f4151c = a("ro.sys.aliyun.clouduuid", "");
        }
        if (!TextUtils.isEmpty(this.f4151c)) {
            DataOutputStream dataOutputStream = null;
            BufferedReader bufferedReader = null;
            InputStream inputStream2 = null;
            dataOutputStream = null;
            dataOutputStream = null;
            DataOutputStream dataOutputStream2 = null;
            try {
                httpsURLConnection2 = (HttpsURLConnection) new URL("https://cmnsguider.yunos.com:443/genDeviceToken").openConnection();
                try {
                    httpsURLConnection2.setConnectTimeout(Config.SESSION_PERIOD);
                    httpsURLConnection2.setReadTimeout(Config.SESSION_PERIOD);
                    httpsURLConnection2.setRequestMethod("POST");
                    httpsURLConnection2.setDoInput(true);
                    httpsURLConnection2.setDoOutput(true);
                    httpsURLConnection2.setUseCaches(false);
                    httpsURLConnection2.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                    httpsURLConnection2.setHostnameVerifier(new HostnameVerifier() { // from class: com.umeng.commonsdk.statistics.idtracking.s.1
                        @Override // javax.net.ssl.HostnameVerifier
                        public boolean verify(String str2, SSLSession sSLSession) {
                            return new BrowserCompatHostnameVerifier().verify("cmnsguider.yunos.com", sSLSession);
                        }
                    });
                    String str2 = "appKey=" + URLEncoder.encode("23338940", "UTF-8") + "&uuid=" + URLEncoder.encode("FC1FE84794417B1BEF276234F6FB4E63", "UTF-8");
                    DataOutputStream dataOutputStream3 = new DataOutputStream(httpsURLConnection2.getOutputStream());
                    try {
                        try {
                            dataOutputStream3.writeBytes(str2);
                            dataOutputStream3.flush();
                            if (httpsURLConnection2.getResponseCode() == 200) {
                                try {
                                    InputStream inputStream3 = httpsURLConnection2.getInputStream();
                                    try {
                                        bufferedReader = new BufferedReader(new InputStreamReader(inputStream3));
                                        try {
                                            StringBuffer stringBuffer = new StringBuffer();
                                            while (true) {
                                                String line = bufferedReader.readLine();
                                                if (line == null) {
                                                    break;
                                                }
                                                stringBuffer.append(line);
                                            }
                                            this.f4152d = stringBuffer.toString();
                                        } catch (Exception unused) {
                                        } catch (Throwable th) {
                                            th = th;
                                            dataOutputStream = dataOutputStream3;
                                            httpsURLConnection = httpsURLConnection2;
                                            inputStream = inputStream3;
                                            if (dataOutputStream != null) {
                                                try {
                                                    dataOutputStream.close();
                                                } catch (Exception unused2) {
                                                }
                                            }
                                            if (bufferedReader != null) {
                                                try {
                                                    bufferedReader.close();
                                                } catch (Exception unused3) {
                                                }
                                            }
                                            if (inputStream != 0) {
                                                try {
                                                    inputStream.close();
                                                } catch (Exception unused4) {
                                                }
                                            }
                                            if (httpsURLConnection != null) {
                                                throw th;
                                            }
                                            httpsURLConnection.disconnect();
                                            throw th;
                                        }
                                    } catch (Exception unused5) {
                                        bufferedReader = null;
                                    } catch (Throwable th2) {
                                        th = th2;
                                        bufferedReader = null;
                                    }
                                    inputStream2 = inputStream3;
                                } catch (Exception unused6) {
                                    bufferedReader = null;
                                }
                            } else {
                                bufferedReader = null;
                            }
                            try {
                                dataOutputStream3.close();
                            } catch (Exception unused7) {
                            }
                            if (bufferedReader != null) {
                                try {
                                    bufferedReader.close();
                                } catch (Exception unused8) {
                                }
                            }
                            if (inputStream2 != null) {
                                try {
                                    inputStream2.close();
                                } catch (Exception unused9) {
                                }
                            }
                            if (httpsURLConnection2 != null) {
                                httpsURLConnection2.disconnect();
                            }
                        } catch (Exception unused10) {
                            dataOutputStream2 = dataOutputStream3;
                            if (dataOutputStream2 != null) {
                                try {
                                    dataOutputStream2.close();
                                } catch (Exception unused11) {
                                }
                            }
                            if (httpsURLConnection2 != null) {
                            }
                            return this.f4152d;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        dataOutputStream = dataOutputStream3;
                        httpsURLConnection = httpsURLConnection2;
                        inputStream = dataOutputStream;
                        if (dataOutputStream != null) {
                            dataOutputStream.close();
                        }
                        if (bufferedReader != null) {
                            bufferedReader.close();
                        }
                        if (inputStream != 0) {
                            inputStream.close();
                        }
                        if (httpsURLConnection != null) {
                            throw th;
                        }
                        httpsURLConnection.disconnect();
                        throw th;
                    }
                } catch (Exception unused12) {
                } catch (Throwable th4) {
                    th = th4;
                }
            } catch (Exception unused13) {
                httpsURLConnection2 = null;
            } catch (Throwable th5) {
                th = th5;
                inputStream = 0;
                httpsURLConnection = null;
                bufferedReader = null;
            }
        }
        return this.f4152d;
    }

    @Override // com.umeng.commonsdk.statistics.idtracking.a
    public String f() {
        SharedPreferences sharedPreferences;
        SharedPreferences.Editor editorEdit;
        try {
            if (TextUtils.isEmpty(a("ro.yunos.version", "")) || this.f4150b == null || (sharedPreferences = PreferenceWrapper.getDefault(this.f4150b)) == null) {
                return null;
            }
            String string = sharedPreferences.getString(f4148e, "");
            if (!TextUtils.isEmpty(string)) {
                return string;
            }
            this.f4152d = b(f4149f);
            if (!TextUtils.isEmpty(this.f4152d) && this.f4150b != null && sharedPreferences != null && (editorEdit = sharedPreferences.edit()) != null) {
                editorEdit.putString(f4148e, this.f4152d).commit();
            }
            return this.f4152d;
        } catch (Exception unused) {
            return null;
        }
    }
}
