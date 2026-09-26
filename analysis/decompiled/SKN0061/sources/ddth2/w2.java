package ddth2;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/* JADX INFO: loaded from: classes.dex */
public class w2 {
    public static String a(String str) {
        if (str == null) {
            return "";
        }
        try {
            return URLEncoder.encode(str, StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException unused) {
            return str;
        }
    }
}
