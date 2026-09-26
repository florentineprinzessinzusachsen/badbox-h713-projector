package ddth2.hidden;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;

/* JADX INFO: loaded from: classes.dex */
public abstract class B {
    public static final SecureRandom a = new SecureRandom();

    /* JADX WARN: Code duplicated, block: B:30:0x005a  */
    /* JADX WARN: Code duplicated, block: B:61:0x0072 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    public static String a(String str) {
        String strTrim;
        String strA;
        File parentFile;
        FileOutputStream fileOutputStream;
        FileInputStream fileInputStream;
        FileOutputStream fileOutputStream2 = null;
        File file = (str == null || str.length() == 0) ? null : new File(str);
        if (file != null) {
            try {
                if (file.isFile()) {
                    try {
                        byte[] bArr = new byte[(int) Math.min(file.length(), 128L)];
                        fileInputStream = new FileInputStream(file);
                        try {
                            int i = fileInputStream.read(bArr);
                            if (i <= 0) {
                                fileInputStream.close();
                                strTrim = null;
                            } else {
                                strTrim = new String(bArr, 0, i, StandardCharsets.UTF_8).trim();
                                if (strTrim.length() < 16) {
                                    strTrim = null;
                                }
                                try {
                                    fileInputStream.close();
                                } catch (Throwable unused) {
                                }
                            }
                        } catch (Throwable unused2) {
                            if (fileInputStream != null) {
                                fileInputStream.close();
                            }
                            strTrim = null;
                            if (strTrim == null) {
                            }
                            byte[] bArr2 = new byte[16];
                            a.nextBytes(bArr2);
                            strA = M.a(bArr2);
                            try {
                                if (file != null) {
                                    try {
                                        parentFile = file.getParentFile();
                                        if (parentFile != null) {
                                            parentFile.mkdirs();
                                        }
                                        fileOutputStream = new FileOutputStream(file);
                                        try {
                                            fileOutputStream.write(strA.getBytes(StandardCharsets.UTF_8));
                                            fileOutputStream.flush();
                                            fileOutputStream.close();
                                        } catch (Throwable unused3) {
                                            fileOutputStream2 = fileOutputStream;
                                            if (fileOutputStream2 != null) {
                                                fileOutputStream2.close();
                                            }
                                            return strA;
                                        }
                                    } catch (Throwable unused4) {
                                    }
                                }
                            } catch (Throwable unused5) {
                            }
                            return strA;
                        }
                    } catch (Throwable unused6) {
                        fileInputStream = null;
                    }
                } else {
                    strTrim = null;
                }
            } catch (Throwable unused7) {
            }
        } else {
            strTrim = null;
        }
        if (strTrim == null && strTrim.length() > 0) {
            return strTrim;
        }
        byte[] bArr3 = new byte[16];
        a.nextBytes(bArr3);
        strA = M.a(bArr3);
        if (file != null) {
            parentFile = file.getParentFile();
            if (parentFile != null && !parentFile.isDirectory()) {
                parentFile.mkdirs();
            }
            fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(strA.getBytes(StandardCharsets.UTF_8));
            fileOutputStream.flush();
            fileOutputStream.close();
        }
        return strA;
    }
}
