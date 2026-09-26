package com.tools;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.StatFs;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import com.anlytics.plug.ParserUtils;
import com.hs.p.common.http.HTTPHelper;
import com.hs.p.common.utils.DigestUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.SocketException;
import java.security.KeyStore;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.GZIPInputStream;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.util.EntityUtils;

/* JADX INFO: loaded from: /Users/ruben/projector-dump/downloads/plugin.jar */
public class f {
    public static Context c = ParserUtils.getContext();
    private static volatile f d = null;
    public static String e = "";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Map<String, String> f53a = new HashMap();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final TrustManager[] f54b = {new a()};

    class a implements X509TrustManager {
        a() {
        }

        @Override // javax.net.ssl.X509TrustManager
        public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        }

        @Override // javax.net.ssl.X509TrustManager
        public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        }

        @Override // javax.net.ssl.X509TrustManager
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }

    private class b extends SSLSocketFactory {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        SSLContext f56a;

        public b(KeyStore keyStore) throws Throwable {
            super(keyStore);
            SSLContext sSLContext = SSLContext.getInstance("TLS");
            this.f56a = sSLContext;
            sSLContext.init(null, f.this.f54b, new SecureRandom());
        }

        @Override // org.apache.http.conn.ssl.SSLSocketFactory, org.apache.http.conn.scheme.SocketFactory
        public Socket createSocket() throws IOException {
            return this.f56a.getSocketFactory().createSocket();
        }

        @Override // org.apache.http.conn.ssl.SSLSocketFactory, org.apache.http.conn.scheme.LayeredSocketFactory
        public Socket createSocket(Socket socket, String str, int i, boolean z) throws IOException {
            return this.f56a.getSocketFactory().createSocket(socket, str, i, z);
        }
    }

    private f() {
    }

    private String a(HttpUriRequest httpUriRequest, String str, boolean z) {
        try {
            DefaultHttpClient defaultHttpClientW = w();
            defaultHttpClientW.getParams().setParameter("http.connection.timeout", Integer.valueOf(com.cloudmedia.tv.server.a.m));
            defaultHttpClientW.getParams().setParameter("http.socket.timeout", Integer.valueOf(com.cloudmedia.tv.server.a.m));
            HttpResponse httpResponseExecute = defaultHttpClientW.execute(httpUriRequest);
            boolean z2 = false;
            for (Header header : httpResponseExecute.getHeaders("Content-Encoding")) {
                if (header.getValue().equals("gzip")) {
                    z2 = true;
                }
            }
            HttpEntity entity = httpResponseExecute.getEntity();
            return z2 ? e(new GZIPInputStream(entity.getContent())) : EntityUtils.toString(entity);
        } catch (ClientProtocolException e2) {
            e2.printStackTrace();
            return null;
        } catch (IOException e3) {
            e3.printStackTrace();
            return null;
        }
    }

    public static String e(InputStream inputStream) throws IOException {
        if (inputStream != null) {
            try {
                StringBuilder sb = new StringBuilder();
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
                    while (true) {
                        String line = bufferedReader.readLine();
                        if (line == null) {
                            inputStream.close();
                            return sb.toString();
                        }
                        sb.append(line);
                        sb.append("\n");
                    }
                } catch (Throwable th) {
                    inputStream.close();
                    throw th;
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return "";
    }

    private static String f(byte[] bArr) {
        String hexString;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bArr.length; i++) {
            int i2 = bArr[i];
            if (i2 < 0 || i2 >= 16) {
                if (i2 < 16) {
                    i2 += 256;
                }
                hexString = Integer.toHexString(i2);
            } else {
                hexString = "0" + Integer.toHexString(i2);
            }
            sb.append(hexString);
            if (i != bArr.length - 1) {
                sb.append(":");
            }
        }
        return sb.toString();
    }

    public static f g() {
        if (d == null) {
            synchronized (f.class) {
                if (d == null) {
                    d = new f();
                }
            }
        }
        return d;
    }

    public static String l() throws Throwable {
        if (TextUtils.isEmpty(e)) {
            if (e.b("ro.board.platform", "unknow").equals("rk3188")) {
                e = "/sys/devices/platform/ashd_mio/dts_model";
            } else {
                int i = 0;
                if (e.b("ro.board.platform", "unknow").equals("rk3326")) {
                    File[] fileArrListFiles = new File("/sys/devices/platform").listFiles();
                    if (fileArrListFiles != null) {
                        int length = fileArrListFiles.length;
                        while (i < length) {
                            File file = fileArrListFiles[i];
                            if (file.isDirectory() && file.getName().startsWith("rockchip_led_gpio")) {
                                e = file.getAbsolutePath() + "/dts_model";
                            }
                            i++;
                        }
                    }
                } else {
                    File[] fileArrListFiles2 = new File("/sys/devices").listFiles();
                    if (fileArrListFiles2 != null) {
                        int length2 = fileArrListFiles2.length;
                        while (i < length2) {
                            File file2 = fileArrListFiles2[i];
                            if (file2.isDirectory() && file2.getName().startsWith("rockchip_leds_gpio.")) {
                                e = file2.getAbsolutePath() + "/dts_model";
                            }
                            i++;
                        }
                    }
                }
            }
        }
        if (!TextUtils.isEmpty(e)) {
            File file3 = new File(e);
            if (file3.exists()) {
                String strX = x(file3);
                if (!strX.equals("unknown") && !strX.equals("")) {
                    e.c("persist.sys.cm.dtsmodel", strX.trim());
                    return strX.trim();
                }
            }
        }
        String strB = e.b("persist.sys.cm.dtsmodel", "unknown");
        return (TextUtils.isEmpty(strB) || strB.equals("unknown")) ? "unknown" : strB;
    }

    public static String m(Context context) {
        int ipAddress = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo().getIpAddress();
        return String.format("%d.%d.%d.%d", Integer.valueOf(ipAddress & 255), Integer.valueOf((ipAddress >> 8) & 255), Integer.valueOf((ipAddress >> 16) & 255), Integer.valueOf((ipAddress >> 24) & 255));
    }

    public static String o() {
        String strQ = com.anlytics.plug.b.Q();
        return e.b((strQ.contains("H723") || strQ.contains("H726")) ? "ro.ashd.version.release" : "ro.build.version.release", "1111");
    }

    public static String x(File file) throws Throwable {
        if (file.exists()) {
            FileInputStream fileInputStream = null;
            try {
                FileInputStream fileInputStream2 = new FileInputStream(file);
                try {
                    byte[] bArr = new byte[fileInputStream2.available()];
                    fileInputStream2.read(bArr);
                    try {
                        fileInputStream2.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                    return new String(bArr);
                } catch (Exception unused) {
                    fileInputStream = fileInputStream2;
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e3) {
                            e3.printStackTrace();
                        }
                    }
                    return "error";
                } catch (Throwable th) {
                    th = th;
                    fileInputStream = fileInputStream2;
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e4) {
                            e4.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (Exception unused2) {
            } catch (Throwable th2) {
                th = th2;
            }
        }
        return "error";
    }

    public static String z(String str) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(str));
            while (true) {
                String line = bufferedReader.readLine();
                if (line == null) {
                    bufferedReader.close();
                    return "error";
                }
                String strTrim = line.trim();
                if (!strTrim.equals("") && strTrim.length() > 0) {
                    return strTrim;
                }
            }
        } catch (FileNotFoundException | IOException | NumberFormatException e2) {
            e2.printStackTrace();
            return "error";
        }
    }

    public void A(String str) {
        this.f53a.clear();
        if (str.contains("#")) {
            str = str.split("#", 2)[0];
        }
        if (str.contains("?")) {
            str = str.split("\\?", 2)[1];
        }
        if (!str.contains("&")) {
            if (str.indexOf("=") > -1) {
                String[] strArrSplit = str.split("=", 2);
                this.f53a.put(strArrSplit[0].trim(), strArrSplit[1].trim());
                return;
            }
            return;
        }
        String[] strArrSplit2 = str.split("&");
        for (int i = 0; i < strArrSplit2.length; i++) {
            if (strArrSplit2[i].indexOf("=") > -1) {
                String[] strArrSplit3 = strArrSplit2[i].split("=", 2);
                this.f53a.put(strArrSplit3[0].trim(), strArrSplit3[1].trim());
            }
        }
    }

    public String B(int i) {
        return C(String.valueOf(i));
    }

    public String C(String str) {
        return "0".equals(str) ? "Mozilla/5.0 (Windows NT 6.1; WOW64; Trident/7.0; rv:11.0) like Gecko" : "Dalvik/2.1.0 (Linux; U; Android 5.0.2; X800 Build)";
    }

    public String b(String str) {
        try {
            File file = new File(str);
            if (!file.exists() || file.isDirectory()) {
                return "";
            }
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                fileInputStream.read(new byte[fileInputStream.available()]);
                return fileInputStream.toString();
            } catch (IOException e2) {
                e2.printStackTrace();
                return "";
            }
        } catch (Exception e3) {
            e3.printStackTrace();
            return "";
        }
    }

    public String c(String str) {
        try {
            return new String(Base64.decode(str, 0));
        } catch (IllegalArgumentException unused) {
            return "";
        }
    }

    public String d(String str) {
        return Base64.encodeToString(str.getBytes(), 2);
    }

    public int h() {
        StatFs statFs = new StatFs("/data");
        int blockSize = statFs.getBlockSize() >> 10;
        return ((blockSize * (statFs.getAvailableBlocks() >> 10)) * 100) / ((statFs.getBlockCount() >> 10) * blockSize);
    }

    public String i() {
        long j;
        String strQ = com.anlytics.plug.b.Q();
        String str = "mmcblk0";
        if (!strQ.contains("H713") && !strQ.contains("H716") && !strQ.contains("H723") && strQ.contains("rk3326")) {
            str = "mmcblk2";
        }
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("/proc/partitions"));
            while (true) {
                String line = bufferedReader.readLine();
                j = 0;
                if (line == null) {
                    break;
                }
                String strTrim = line.trim();
                if (!strTrim.equals("")) {
                    String[] strArrSplit = strTrim.split("\\s+");
                    if (strArrSplit.length == 4 && strArrSplit[3].equals(str)) {
                        j = 0 + ((long) Integer.parseInt(strArrSplit[2]));
                        Log.i("dyl", "totalsize= " + j);
                        break;
                    }
                }
            }
            bufferedReader.close();
            int i = (int) (j >> 10);
            if (i < 4096 && i >= 0) {
                return "4GB";
            }
            if (i >= 4096 && i < 8192) {
                return "8GB";
            }
            if (i >= 8192 && i < 16324) {
                return "16GB";
            }
            if (i >= 16324 && i < 32768) {
                return "32GB";
            }
            if (i >= 32768 && i < 65536) {
                return "64GB";
            }
            if (i >= 65536 && i < 131072) {
                return "128GB";
            }
            if (i < 131072 || i >= 262144) {
                return i >= 262144 ? "512GB" : "8GB";
            }
            return "256GB";
        } catch (FileNotFoundException | IOException | NumberFormatException e2) {
            e2.printStackTrace();
            return "8GB";
        }
    }

    public String j() {
        StatFs statFs = new StatFs("/data");
        int blockSize = (statFs.getBlockSize() >> 10) * (statFs.getBlockCount() >> 10);
        if (blockSize < 4096) {
            return "4GB";
        }
        if (blockSize >= 4096 && blockSize < 8192) {
            return "8GB";
        }
        if (blockSize >= 8192 && blockSize < 16384) {
            return "16GB";
        }
        if (blockSize < 16384 || blockSize >= 32768) {
            return (blockSize < 32768 || blockSize >= 65536) ? "8GB" : "64GB";
        }
        return "32GB";
    }

    public int k() {
        long j;
        if (com.anlytics.plug.b.Q().contains("H713")) {
            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader("/proc/partitions"));
                while (true) {
                    String line = bufferedReader.readLine();
                    j = 0;
                    if (line == null) {
                        break;
                    }
                    String strTrim = line.trim();
                    if (!strTrim.equals("")) {
                        String[] strArrSplit = strTrim.split("\\s+");
                        if (strArrSplit.length == 4 && strArrSplit[3].equals("mmcblk0p9")) {
                            j = 0 + ((long) Integer.parseInt(strArrSplit[2]));
                            break;
                        }
                    }
                }
                bufferedReader.close();
                return (int) (j >> 10);
            } catch (FileNotFoundException | IOException | NumberFormatException e2) {
                e2.printStackTrace();
            }
        }
        return -1;
    }

    public String n(String str) {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterfaceNextElement = networkInterfaces.nextElement();
                String displayName = networkInterfaceNextElement.getDisplayName();
                if (displayName != null && displayName.equals("eth0")) {
                    return f(networkInterfaceNextElement.getHardwareAddress());
                }
            }
            return "";
        } catch (SocketException e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public String p() {
        try {
            FileInputStream fileInputStream = new FileInputStream("/sys/class/net/wlan0/address");
            byte[] bArr = new byte[17];
            fileInputStream.read(bArr, 0, 17);
            String str = new String(bArr);
            fileInputStream.close();
            return str.toUpperCase();
        } catch (Exception e2) {
            System.out.println("getLocalMacAddress failure !");
            e2.printStackTrace();
            return "unknow";
        }
    }

    public String q(String str) {
        return r(str, new BasicHeader(HTTPHelper.HEADER_USER_AGENT, "okhttp/3.8.1"));
    }

    public String r(String str, Header... headerArr) {
        HttpGet httpGet = new HttpGet(str);
        httpGet.setHeader("Connection", "Keep-Alive");
        httpGet.setHeaders(headerArr);
        return a(httpGet, HTTPHelper.CHARSET_UTF8, false);
    }

    public boolean s() {
        NetworkInfo[] allNetworkInfo;
        ConnectivityManager connectivityManager = (ConnectivityManager) ParserUtils.getContext().getSystemService("connectivity");
        if (connectivityManager != null && (allNetworkInfo = connectivityManager.getAllNetworkInfo()) != null) {
            for (NetworkInfo networkInfo : allNetworkInfo) {
                if (networkInfo.getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean t() {
        File file = new File("/sys/class/switch/hdmi/state");
        if (!file.exists()) {
            return false;
        }
        try {
            String strB = b(file.getPath());
            if (TextUtils.isEmpty(strB)) {
                return false;
            }
            return "1".equals(strB.trim());
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public boolean u(int i) {
        try {
            new Socket(InetAddress.getByName("127.0.0.1"), i);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public String v(File file) {
        int i;
        byte[] bArr = new byte[com.cloudmedia.tv.server.a.l.s];
        if (!file.exists()) {
            return "";
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(DigestUtils.MD5);
            FileInputStream fileInputStream = new FileInputStream(file);
            while (true) {
                int i2 = fileInputStream.read(bArr, 0, com.cloudmedia.tv.server.a.l.s);
                if (i2 == -1) {
                    break;
                }
                messageDigest.update(bArr, 0, i2);
            }
            fileInputStream.close();
            byte[] bArrDigest = messageDigest.digest();
            char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
            StringBuilder sb = new StringBuilder(bArrDigest.length * 2);
            for (byte b2 : bArrDigest) {
                sb.append(cArr[(b2 >> 4) & 15]);
                sb.append(cArr[b2 & 15]);
            }
            return sb.toString();
        } catch (FileNotFoundException e2) {
            e2.printStackTrace();
            return "";
        } catch (IOException e3) {
            e3.printStackTrace();
            return "";
        } catch (NoSuchAlgorithmException e4) {
            e4.printStackTrace();
            return "";
        }
    }

    public DefaultHttpClient w() {
        try {
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null, null);
            b bVar = new b(keyStore);
            bVar.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            BasicHttpParams basicHttpParams = new BasicHttpParams();
            HttpProtocolParams.setVersion(basicHttpParams, HttpVersion.HTTP_1_1);
            HttpProtocolParams.setContentCharset(basicHttpParams, HTTPHelper.CHARSET_UTF8);
            SchemeRegistry schemeRegistry = new SchemeRegistry();
            schemeRegistry.register(new Scheme("https", bVar, 443));
            schemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
            return new DefaultHttpClient(new ThreadSafeClientConnManager(basicHttpParams, schemeRegistry), basicHttpParams);
        } catch (Throwable unused) {
            return new DefaultHttpClient();
        }
    }

    public String y(File file) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file.getAbsolutePath()));
            while (true) {
                String line = bufferedReader.readLine();
                if (line == null) {
                    bufferedReader.close();
                    return "error";
                }
                String strTrim = line.trim();
                if (!strTrim.equals("") && strTrim.length() > 0) {
                    return strTrim;
                }
            }
        } catch (FileNotFoundException | IOException | NumberFormatException e2) {
            e2.printStackTrace();
            return "error";
        }
    }
}
