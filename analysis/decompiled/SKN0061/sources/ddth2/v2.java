package ddth2;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/* JADX INFO: loaded from: classes.dex */
public class v2 {
    public final int a;

    /* JADX INFO: renamed from: a, reason: collision with other field name */
    public final e f97a;
    public final int b;
    public final int c;

    public v2(e eVar) {
        if (eVar == null) {
            throw new IllegalArgumentException("config required");
        }
        this.f97a = eVar;
        this.a = eVar.m32b() * 1000;
        this.b = eVar.n() * 1000;
        this.c = Math.max(4096, eVar.d());
    }

    public final int a() {
        e eVar = this.f97a;
        return eVar != null ? Math.max(4096, eVar.d()) : this.c;
    }

    public String a(String str) throws Throwable {
        HttpURLConnection httpURLConnection;
        int responseCode;
        String headerField;
        int i = 0;
        while (true) {
            if (i > 1) {
                throw new IOException("redirect too much: 1");
            }
            m74a();
            HttpURLConnection httpURLConnection2 = null;
            try {
                httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
                try {
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.setConnectTimeout(this.a);
                    httpURLConnection.setReadTimeout(this.b);
                    httpURLConnection.setRequestProperty("Content-Type", "application/json; charset=utf-8");
                    httpURLConnection.setInstanceFollowRedirects(true);
                    responseCode = httpURLConnection.getResponseCode();
                    if ((responseCode != 301 && responseCode != 302 && responseCode != 303 && responseCode != 307 && responseCode != 308) || (headerField = httpURLConnection.getHeaderField("Location")) == null || headerField.isEmpty()) {
                        break;
                    }
                    httpURLConnection.disconnect();
                    b();
                    i++;
                    str = headerField;
                } catch (Throwable th) {
                    th = th;
                    httpURLConnection2 = httpURLConnection;
                    if (httpURLConnection2 != null) {
                        httpURLConnection2.disconnect();
                    }
                    b();
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }
        if (responseCode == 200) {
            String strA = c0.a(httpURLConnection, a());
            httpURLConnection.disconnect();
            b();
            return strA;
        }
        throw new IOException("HTTP request failed: " + responseCode + " " + httpURLConnection.getResponseMessage());
    }

    public String a(String str, String str2) {
        return b(str, str2, null);
    }

    public String a(String str, String str2, String str3) {
        return b(str, str2, str3);
    }

    /* JADX INFO: renamed from: a, reason: collision with other method in class */
    public final void m74a() throws IOException {
        e eVar = this.f97a;
        if (eVar != null) {
            f2.a(eVar);
        }
    }

    public final String b(String str, String str2, String str3) throws Throwable {
        m74a();
        HttpURLConnection httpURLConnection = null;
        try {
            URL url = new URL(str);
            if (str3 != null) {
                str3.isEmpty();
            }
            HttpURLConnection httpURLConnection2 = (HttpURLConnection) url.openConnection();
            try {
                httpURLConnection2.setRequestMethod("POST");
                httpURLConnection2.setConnectTimeout(this.a);
                httpURLConnection2.setReadTimeout(this.b);
                httpURLConnection2.setRequestProperty("Content-Type", "application/json; charset=utf-8");
                httpURLConnection2.setDoOutput(true);
                OutputStream outputStream = httpURLConnection2.getOutputStream();
                try {
                    byte[] bytes = str2.getBytes("utf-8");
                    outputStream.write(bytes, 0, bytes.length);
                    outputStream.close();
                    int responseCode = httpURLConnection2.getResponseCode();
                    if (responseCode == 200) {
                        String strA = c0.a(httpURLConnection2, a());
                        httpURLConnection2.disconnect();
                        b();
                        return strA;
                    }
                    throw new IOException("HTTP request failed: " + responseCode + " " + httpURLConnection2.getResponseMessage());
                } catch (Throwable th) {
                    try {
                        throw th;
                    } catch (Throwable th2) {
                        if (outputStream != null) {
                            try {
                                outputStream.close();
                            } catch (Throwable th3) {
                                th.addSuppressed(th3);
                            }
                        }
                        throw th2;
                    }
                }
            } catch (Throwable th4) {
                httpURLConnection = httpURLConnection2;
                th = th4;
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                b();
                throw th;
            }
        } catch (Throwable th5) {
            th = th5;
        }
    }

    public final void b() {
        if (this.f97a != null) {
            f2.m58a();
        }
    }
}
