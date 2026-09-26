package com.szns.sdk;

import android.accounts.NetworkErrorException;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class w {
    private static String a(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[1024];
        while (true) {
            int i = inputStream.read(bArr);
            if (i == -1) {
                inputStream.close();
                String string = byteArrayOutputStream.toString();
                byteArrayOutputStream.close();
                return string;
            }
            byteArrayOutputStream.write(bArr, 0, i);
        }
    }

    public static String a(String str) throws Throwable {
        Throwable th;
        HttpURLConnection httpURLConnection;
        try {
            httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            try {
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(10000);
                httpURLConnection.setRequestProperty("User-Agent", "");
                int responseCode = httpURLConnection.getResponseCode();
                if (responseCode != 200) {
                    throw new NetworkErrorException("response status is ".concat(String.valueOf(responseCode)));
                }
                String strA = a(httpURLConnection.getInputStream());
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                return strA;
            } catch (Exception unused) {
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                return null;
            } catch (Throwable th2) {
                th = th2;
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                throw th;
            }
        } catch (Exception unused2) {
            httpURLConnection = null;
        } catch (Throwable th3) {
            th = th3;
            httpURLConnection = null;
        }
    }

    public static String a(String str, String str2) throws Throwable {
        HttpURLConnection httpURLConnection;
        HttpURLConnection httpURLConnection2 = null;
        try {
            httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            try {
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setReadTimeout(8000);
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setRequestProperty("Content-Type", "application/json");
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
                bufferedOutputStream.write(str2.getBytes());
                bufferedOutputStream.flush();
                bufferedOutputStream.close();
                outputStream.close();
                int responseCode = httpURLConnection.getResponseCode();
                httpURLConnection.getErrorStream();
                if (responseCode == 200) {
                    String strA = a(httpURLConnection.getInputStream());
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    return strA;
                }
                throw new NetworkErrorException("response status is " + responseCode + "responsemsg: " + a(httpURLConnection.getErrorStream()));
            } catch (Exception unused) {
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                return null;
            } catch (Throwable th) {
                th = th;
                httpURLConnection2 = httpURLConnection;
                if (httpURLConnection2 != null) {
                    httpURLConnection2.disconnect();
                }
                throw th;
            }
        } catch (Exception unused2) {
            httpURLConnection = null;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static StringBuffer a(Map map, String str) {
        StringBuffer stringBuffer = new StringBuffer();
        try {
            for (Map.Entry entry : map.entrySet()) {
                stringBuffer.append((String) entry.getKey());
                stringBuffer.append("=");
                stringBuffer.append(URLEncoder.encode((String) entry.getValue(), str));
                stringBuffer.append("&");
            }
            stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        } catch (Exception unused) {
        }
        return stringBuffer;
    }
}
