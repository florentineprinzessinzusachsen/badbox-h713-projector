package com.android.umanalytics.utils;

import d.d0;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
public class FUtils {
    public static File saveFile(d0 d0Var, String str, String str2) throws Throwable {
        InputStream inputStreamByteStream;
        byte[] bArr = new byte[2048];
        FileOutputStream fileOutputStream = null;
        try {
            inputStreamByteStream = d0Var.byteStream();
            try {
                d0Var.contentLength();
                File file = new File(str);
                if (!file.exists()) {
                    file.mkdirs();
                }
                File file2 = new File(file, str2);
                FileOutputStream fileOutputStream2 = new FileOutputStream(file2);
                while (true) {
                    try {
                        int i = inputStreamByteStream.read(bArr);
                        if (i == -1) {
                            break;
                        }
                        fileOutputStream2.write(bArr, 0, i);
                    } catch (Throwable th) {
                        fileOutputStream = fileOutputStream2;
                        th = th;
                        if (inputStreamByteStream != null) {
                            try {
                                inputStreamByteStream.close();
                            } catch (IOException e2) {
                                e2.printStackTrace();
                            }
                        }
                        if (fileOutputStream == null) {
                            throw th;
                        }
                        try {
                            fileOutputStream.close();
                            throw th;
                        } catch (IOException e3) {
                            e3.printStackTrace();
                            throw th;
                        }
                    }
                }
                fileOutputStream2.flush();
                if (inputStreamByteStream != null) {
                    try {
                        inputStreamByteStream.close();
                    } catch (IOException e4) {
                        e4.printStackTrace();
                    }
                }
                try {
                    fileOutputStream2.close();
                } catch (IOException e5) {
                    e5.printStackTrace();
                }
                return file2;
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Throwable th3) {
            th = th3;
            inputStreamByteStream = null;
        }
    }
}
