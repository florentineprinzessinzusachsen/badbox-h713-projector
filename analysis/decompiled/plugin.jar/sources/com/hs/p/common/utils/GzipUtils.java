package com.hs.p.common.utils;

import com.cloudmedia.tv.server.a;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipException;

/* JADX INFO: loaded from: /Users/ruben/projector-dump/downloads/plugin.jar */
public class GzipUtils {
    public static byte[] compress(byte[] bArr) throws Exception {
        ByteArrayOutputStream byteArrayOutputStream;
        GZIPOutputStream gZIPOutputStream;
        Closeable closeable = null;
        closeable = null;
        ByteArrayOutputStream byteArrayOutputStream2 = null;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
                try {
                    gZIPOutputStream.write(bArr);
                    gZIPOutputStream.flush();
                    gZIPOutputStream.finish();
                    byte[] byteArray = byteArrayOutputStream.toByteArray();
                    IoUtils.close(gZIPOutputStream, byteArrayOutputStream);
                    return byteArray;
                } catch (Exception e) {
                    e = e;
                    byteArrayOutputStream2 = byteArrayOutputStream;
                    try {
                        throw new Exception("compress(gzip) failed: " + e, e);
                    } catch (Throwable th) {
                        th = th;
                        byteArrayOutputStream = byteArrayOutputStream2;
                        closeable = gZIPOutputStream;
                        IoUtils.close(closeable, byteArrayOutputStream);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    closeable = gZIPOutputStream;
                    IoUtils.close(closeable, byteArrayOutputStream);
                    throw th;
                }
            } catch (Exception e2) {
                e = e2;
                gZIPOutputStream = null;
            } catch (Throwable th3) {
                th = th3;
                IoUtils.close(closeable, byteArrayOutputStream);
                throw th;
            }
        } catch (Exception e3) {
            e = e3;
            gZIPOutputStream = null;
        } catch (Throwable th4) {
            th = th4;
            byteArrayOutputStream = null;
        }
    }

    public static byte[] decompress(byte[] bArr) throws Exception {
        try {
            return decompressInternal(bArr);
        } catch (Exception e) {
            return doWhileUngzFailed(e, bArr);
        }
    }

    private static byte[] decompressInternal(byte[] bArr) throws Exception {
        ByteArrayOutputStream byteArrayOutputStream;
        ByteArrayInputStream byteArrayInputStream;
        GZIPInputStream gZIPInputStream = null;
        try {
            byte[] bArr2 = new byte[a.l.s];
            byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                byteArrayInputStream = new ByteArrayInputStream(bArr);
                try {
                    GZIPInputStream gZIPInputStream2 = new GZIPInputStream(byteArrayInputStream);
                    while (true) {
                        try {
                            int i = gZIPInputStream2.read(bArr2);
                            if (i <= 0) {
                                byte[] byteArray = byteArrayOutputStream.toByteArray();
                                IoUtils.close(gZIPInputStream2, byteArrayInputStream, byteArrayOutputStream);
                                return byteArray;
                            }
                            byteArrayOutputStream.write(bArr2, 0, i);
                        } catch (Throwable th) {
                            th = th;
                            gZIPInputStream = gZIPInputStream2;
                            IoUtils.close(gZIPInputStream, byteArrayInputStream, byteArrayOutputStream);
                            throw th;
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                byteArrayInputStream = null;
            }
        } catch (Throwable th4) {
            th = th4;
            byteArrayOutputStream = null;
            byteArrayInputStream = null;
        }
    }

    private static byte[] doWhileUngzFailed(Exception exc, byte[] bArr) throws Exception {
        try {
            if ((exc instanceof ZipException) && TextUtils.contains(exc.getMessage(), "GZIP format")) {
                return bArr;
            }
            if ((exc instanceof IOException) && TextUtils.contains(exc.getMessage(), "unknown format")) {
                return bArr;
            }
            throw exc;
        } catch (Exception unused) {
            throw exc;
        }
    }
}
