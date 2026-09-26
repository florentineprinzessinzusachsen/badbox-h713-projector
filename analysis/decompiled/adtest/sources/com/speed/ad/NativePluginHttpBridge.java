package com.speed.ad;

import a1.a;
import a3.a0;
import a3.d0;
import a3.f0;
import a3.w;
import a3.x;
import a3.z;
import e3.p;
import j2.i;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.SSLSession;
import l1.f;
import l3.h;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class NativePluginHttpBridge {
    public static final NativePluginHttpBridge INSTANCE = new NativePluginHttpBridge();
    private static final x client;

    static {
        w wVar = new w();
        TimeUnit timeUnit = TimeUnit.SECONDS;
        wVar.a(30L);
        wVar.b(40L);
        wVar.d(40L);
        wVar.f231l = new a(8);
        wVar.f222c.add(new c3.a(3));
        wVar.c(f.b(), new l1.a());
        wVar.f238s = new c1.a(0);
        client = new x(wVar);
    }

    private NativePluginHttpBridge() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean client$lambda$0(String str, SSLSession sSLSession) {
        return true;
    }

    public static final boolean downloadToPath(String str, String str2) {
        f0 f0Var;
        i.e(str, "url");
        i.e(str2, "destinationPath");
        File file = new File(str2);
        File parentFile = file.getParentFile();
        if (parentFile != null) {
            parentFile.mkdirs();
        }
        z zVar = new z();
        zVar.d(str);
        a0 a0Var = new a0(zVar);
        try {
            x xVar = client;
            xVar.getClass();
            d0 d0VarF = new p(xVar, a0Var).f();
            try {
                if (d0VarF.f118s && (f0Var = d0VarF.f109j) != null) {
                    BufferedInputStream bufferedInputStream = new BufferedInputStream(f0Var.k().Q());
                    try {
                        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
                        try {
                            byte[] bArr = new byte[8192];
                            while (true) {
                                int i4 = bufferedInputStream.read(bArr);
                                if (i4 == -1) {
                                    bufferedOutputStream.flush();
                                    bufferedOutputStream.close();
                                    bufferedInputStream.close();
                                    d0VarF.close();
                                    return true;
                                }
                                bufferedOutputStream.write(bArr, 0, i4);
                                try {
                                    throw th;
                                } catch (Throwable th) {
                                    h.j(bufferedInputStream, th);
                                    throw th;
                                }
                            }
                        } catch (Throwable th2) {
                            try {
                                throw th2;
                            } catch (Throwable th3) {
                                h.j(bufferedOutputStream, th2);
                                throw th3;
                            }
                        }
                    } catch (Throwable th4) {
                        throw th4;
                    }
                }
                d0VarF.close();
                return false;
            } catch (Throwable th5) {
                try {
                    throw th5;
                } catch (Throwable th6) {
                    h.j(d0VarF, th5);
                    throw th6;
                }
            }
        } catch (Exception unused) {
            file.delete();
            return false;
        }
    }
}
