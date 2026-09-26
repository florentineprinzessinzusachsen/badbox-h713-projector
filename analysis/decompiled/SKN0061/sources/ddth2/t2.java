package ddth2;

import android.util.Base64;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;

/* JADX INFO: loaded from: classes.dex */
public class t2 {
    public static final byte[] a = {80, 106, 56, 115, 77, 122, 107, 47, 66, 84, 77, 43};

    public static String a() {
        try {
            Path pathM72a = m72a();
            if (!Files.exists(pathM72a, new LinkOption[0])) {
                return null;
            }
            String strTrim = new String(Files.readAllBytes(pathM72a), StandardCharsets.UTF_8).trim();
            if (strTrim.isEmpty()) {
                return null;
            }
            return strTrim;
        } catch (IOException unused) {
            return null;
        }
    }

    public static String a(byte[] bArr) {
        try {
            byte[] bArrDecode = Base64.decode(new String(bArr), 2);
            StringBuilder sb = new StringBuilder();
            for (byte b : bArrDecode) {
                sb.append((char) (b ^ 90));
            }
            return sb.toString();
        } catch (Exception unused) {
            return "";
        }
    }

    /* JADX INFO: renamed from: a, reason: collision with other method in class */
    public static Path m72a() {
        return Paths.get(System.getProperty("user.dir"), b());
    }

    public static boolean a(String str) {
        try {
            Path pathM72a = m72a();
            Files.createDirectories(pathM72a.getParent(), new FileAttribute[0]);
            Files.write(pathM72a, str.getBytes(StandardCharsets.UTF_8), new OpenOption[0]);
            return true;
        } catch (IOException unused) {
            return false;
        }
    }

    public static String b() {
        return a(a);
    }
}
