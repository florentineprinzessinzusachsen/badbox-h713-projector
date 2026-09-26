package a.a.b;

import android.util.Log;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* JADX INFO: loaded from: classes.dex */
public class d {
    public static boolean a(String str, String str2) {
        File file = new File(str2);
        byte[] bArr = new byte[1024];
        String str3 = null;
        if (file.exists()) {
            try {
                MessageDigest messageDigest = MessageDigest.getInstance("MD5");
                FileInputStream fileInputStream = new FileInputStream(file);
                while (true) {
                    int i = fileInputStream.read(bArr, 0, 1024);
                    if (i == -1) {
                        break;
                    }
                    messageDigest.update(bArr, 0, i);
                }
                fileInputStream.close();
                BigInteger bigInteger = new BigInteger(1, messageDigest.digest());
                Log.v("CMUpdate2MD5", "create_MD5=" + bigInteger.toString(16));
                String string = bigInteger.toString(16);
                if (string == null || string.length() >= 32) {
                    str3 = string;
                } else {
                    int length = 32 - string.length();
                    String str4 = string;
                    for (int i2 = 0; i2 < length; i2++) {
                        str4 = "0" + str4;
                    }
                    str3 = str4;
                }
            } catch (IOException | NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
        if (str3 != null) {
            Log.d("CMUpdate2MD5", "xmlMd5 = " + str + ",  localMD5=" + str3);
            if (str.toLowerCase().compareTo(str3.toLowerCase()) == 0) {
                return true;
            }
        }
        return false;
    }
}
