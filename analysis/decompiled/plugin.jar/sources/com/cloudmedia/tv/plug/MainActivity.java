package com.cloudmedia.tv.plug;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import com.anlytics.plug.ParserUtils;
import com.hs.p.common.utils.DigestUtils;
import com.tools.f;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: /Users/ruben/projector-dump/downloads/plugin.jar */
public class MainActivity extends Activity {

    class a extends Thread {
        a() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            Log.e("ParserUtils", "yy0=" + ParserUtils.AnalyticsHelper("com.disney.disneyplus"));
            Log.i("ParserUtils", "getDataAvailPer=" + f.g().h());
        }
    }

    private void a(byte b2, StringBuffer stringBuffer) {
        char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        stringBuffer.append(cArr[(b2 & 240) >> 4]);
        stringBuffer.append(cArr[b2 & 15]);
    }

    public static boolean b(Context context, String str, String str2) {
        try {
            InputStream inputStreamOpen = context.getAssets().open(str);
            if (inputStreamOpen != null) {
                File file = new File(str2.substring(0, str2.lastIndexOf(File.separator)));
                if (!file.exists()) {
                    file.mkdirs();
                }
                File file2 = new File(str2);
                if (file2.exists()) {
                    file2.delete();
                }
                file2.createNewFile();
                FileOutputStream fileOutputStream = new FileOutputStream(file2);
                byte[] bArr = new byte[2048];
                while (true) {
                    int i = inputStreamOpen.read(bArr);
                    if (i == -1) {
                        fileOutputStream.flush();
                        fileOutputStream.close();
                        inputStreamOpen.close();
                        return true;
                    }
                    fileOutputStream.write(bArr, 0, i);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private String c(String str, byte[] bArr) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(str);
            messageDigest.update(bArr);
            return g(messageDigest.digest());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String d(String str, String str2) {
        if (!str2.contains(str + "=")) {
            return "0";
        }
        String strSubstring = str2.substring(str2.indexOf(str + "="), str2.length());
        String strSubstring2 = strSubstring.substring(str.length() + 0 + 1, strSubstring.indexOf("&"));
        Log.i("dyl", "str1=" + strSubstring2);
        return strSubstring2;
    }

    private String g(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer();
        int length = bArr.length;
        for (int i = 0; i < length; i++) {
            a(bArr[i], stringBuffer);
            if (i < length - 1) {
                stringBuffer.append(":");
            }
        }
        return stringBuffer.toString();
    }

    public static boolean h(String str, String str2) {
        try {
            File file = new File(str);
            File parentFile = file.getParentFile();
            if (!parentFile.exists()) {
                parentFile.mkdirs();
            }
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(str);
            fileOutputStream.write(str2.getBytes());
            fileOutputStream.close();
            return true;
        } catch (FileNotFoundException | IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Map<String, String> e(Context context) {
        HashMap map = new HashMap();
        try {
            byte[] byteArray = context.getPackageManager().getPackageInfo(context.getPackageName(), 64).signatures[0].toByteArray();
            X509Certificate x509CertificateF = f(byteArray);
            map.put("signName", x509CertificateF.getSigAlgName());
            map.put("pubKey", x509CertificateF.getPublicKey().toString());
            map.put("serialNumber", x509CertificateF.getSerialNumber().toString());
            map.put("sigAlgOID", x509CertificateF.getSigAlgOID());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            map.put("startTime", simpleDateFormat.format(x509CertificateF.getNotBefore()));
            map.put("endTime", simpleDateFormat.format(x509CertificateF.getNotAfter()));
            map.put("subjectDN", x509CertificateF.getSubjectDN().toString());
            map.put(DigestUtils.MD5, c(DigestUtils.MD5, byteArray));
            map.put("SHA1", c("SHA1", byteArray));
            map.put("SHA256", c("SHA256", byteArray));
            for (Map.Entry entry : map.entrySet()) {
                Log.i("", String.format("%s=%s", entry.getKey(), entry.getValue()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    public X509Certificate f(byte[] bArr) {
        try {
            return (X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(bArr));
        } catch (CertificateException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        b(this, "libWasu.so", "/data/data/" + getPackageName() + "/files/libWasu.so");
        b(this, "libWasusdk44.so", "/data/data/" + getPackageName() + "/files/libWasusdk44.so");
        b(this, "libhdsjs.so", "/data/data/" + getPackageName() + "/files/libhdsjs.so");
        b(this, "libdsjs.so", "/data/data/" + getPackageName() + "/files/libdsjs.so");
        b(this, "libhvbp.so", "/data/data/" + getPackageName() + "/files/libhvbp.so");
        b(this, "libovbp.so", "/data/data/" + getPackageName() + "/files/libovbp.so");
        setContentView(a.a.d.activity_main);
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        new a().start();
    }
}
