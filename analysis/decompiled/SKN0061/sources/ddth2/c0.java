package ddth2;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

/* JADX INFO: loaded from: classes.dex */
public final class c0 {
    public static String a(HttpURLConnection httpURLConnection, int i) throws IOException {
        if (i <= 0) {
            i = 524288;
        }
        InputStream inputStream = httpURLConnection.getInputStream();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(Math.min(8192, i));
        byte[] bArr = new byte[4096];
        int i2 = 0;
        while (true) {
            int i3 = inputStream.read(bArr);
            if (i3 == -1) {
                return byteArrayOutputStream.toString("utf-8");
            }
            i2 += i3;
            if (i2 > i) {
                throw new IOException("HTTP response body exceeds limit: " + i);
            }
            byteArrayOutputStream.write(bArr, 0, i3);
        }
    }
}
