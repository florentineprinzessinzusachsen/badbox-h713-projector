package o;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.AssetFileDescriptor;
import android.os.Build;
import g.o;
import java.io.File;
import java.io.IOException;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public abstract class m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final o f1535a = new o();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Object f1536b = new Object();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static a1.a f1537c = null;

    public static long a(Context context) {
        PackageManager packageManager = context.getApplicationContext().getPackageManager();
        return Build.VERSION.SDK_INT >= 33 ? k.a(packageManager, context).lastUpdateTime : packageManager.getPackageInfo(context.getPackageName(), 0).lastUpdateTime;
    }

    public static a1.a b() {
        a1.a aVar = new a1.a(13);
        f1537c = aVar;
        f1535a.j(aVar);
        return f1537c;
    }

    /* JADX WARN: Code duplicated, block: B:101:0x00f4 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:109:0x00a8 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:20:0x002c  */
    /* JADX WARN: Code duplicated, block: B:21:0x002e  */
    /* JADX WARN: Code duplicated, block: B:43:0x006f  */
    /* JADX WARN: Code duplicated, block: B:49:0x0092  */
    /* JADX WARN: Code duplicated, block: B:58:0x00b2  */
    /* JADX WARN: Code duplicated, block: B:67:0x00c3 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:68:0x00c5  */
    /* JADX WARN: Code duplicated, block: B:69:0x00c8 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:70:0x00ca  */
    /* JADX WARN: Code duplicated, block: B:71:0x00cc A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:72:0x00ce  */
    public static void c(Context context, boolean z3) {
        int i4;
        boolean z4;
        int i5;
        File file;
        boolean z5;
        File file2;
        long length;
        boolean z6;
        File file3;
        l lVarA;
        l lVar;
        int i6;
        AssetFileDescriptor assetFileDescriptorOpenFd;
        if (z3 || f1537c == null) {
            synchronized (f1536b) {
                if (z3) {
                    i4 = 0;
                    assetFileDescriptorOpenFd = context.getAssets().openFd("dexopt/baseline.prof");
                    if (assetFileDescriptorOpenFd.getLength() > 0) {
                        z4 = true;
                    } else {
                        z4 = false;
                    }
                    assetFileDescriptorOpenFd.close();
                    i5 = Build.VERSION.SDK_INT;
                    if (i5 >= 28) {
                        file = new File(new File("/data/misc/profiles/ref/", context.getPackageName()), "primary.prof");
                        long length2 = file.length();
                        if (file.exists()) {
                            z5 = false;
                        } else {
                            z5 = false;
                        }
                        file2 = new File(new File("/data/misc/profiles/cur/0/", context.getPackageName()), "primary.prof");
                        length = file2.length();
                        if (file2.exists()) {
                            z6 = false;
                        } else {
                            z6 = false;
                        }
                        long jA = a(context);
                        file3 = new File(context.getFilesDir(), "profileInstalled");
                        if (file3.exists()) {
                            lVarA = l.a(file3);
                        } else {
                            lVarA = null;
                        }
                        if (lVarA == null) {
                            if (!z4) {
                                i4 = 327680;
                            } else if (z5) {
                                i4 = 1;
                            } else if (z6) {
                                i4 = 2;
                            }
                        } else if (!z4) {
                            i4 = 327680;
                        } else if (z5) {
                            i4 = 1;
                        } else if (z6) {
                            i4 = 2;
                        }
                        if (z3) {
                            i4 = 2;
                        }
                        if (lVarA != null) {
                            i4 = 3;
                        }
                        lVar = new l(1, i4, jA, length);
                        if (lVarA != null) {
                            lVar.b(file3);
                        } else {
                            lVar.b(file3);
                        }
                        b();
                        return;
                    }
                    b();
                    return;
                }
                if (f1537c != null) {
                    return;
                }
                i4 = 0;
                try {
                    assetFileDescriptorOpenFd = context.getAssets().openFd("dexopt/baseline.prof");
                    try {
                        if (assetFileDescriptorOpenFd.getLength() > 0) {
                            z4 = true;
                        } else {
                            z4 = false;
                        }
                        assetFileDescriptorOpenFd.close();
                    } catch (Throwable th) {
                        if (assetFileDescriptorOpenFd == null) {
                            throw th;
                        }
                        try {
                            assetFileDescriptorOpenFd.close();
                            throw th;
                        } catch (Throwable th2) {
                            th.addSuppressed(th2);
                            throw th;
                        }
                    }
                } catch (IOException unused) {
                    z4 = false;
                }
                i5 = Build.VERSION.SDK_INT;
                if (i5 >= 28 && i5 != 30) {
                    file = new File(new File("/data/misc/profiles/ref/", context.getPackageName()), "primary.prof");
                    long length3 = file.length();
                    if (file.exists() || length3 <= 0) {
                        z5 = false;
                    } else {
                        z5 = true;
                    }
                    file2 = new File(new File("/data/misc/profiles/cur/0/", context.getPackageName()), "primary.prof");
                    length = file2.length();
                    if (file2.exists() || length <= 0) {
                        z6 = false;
                    } else {
                        z6 = true;
                    }
                    try {
                        long jA2 = a(context);
                        file3 = new File(context.getFilesDir(), "profileInstalled");
                        if (file3.exists()) {
                            try {
                                lVarA = l.a(file3);
                            } catch (IOException unused2) {
                                b();
                                return;
                            }
                        } else {
                            lVarA = null;
                        }
                        if (lVarA == null && lVarA.f1533c == jA2 && (i6 = lVarA.f1532b) != 2) {
                            i4 = i6;
                        } else if (!z4) {
                            i4 = 327680;
                        } else if (z5) {
                            i4 = 1;
                        } else if (z6) {
                            i4 = 2;
                        }
                        if (z3 && z6 && i4 != 1) {
                            i4 = 2;
                        }
                        if (lVarA != null && lVarA.f1532b == 2 && i4 == 1 && length3 < lVarA.f1534d) {
                            i4 = 3;
                        }
                        lVar = new l(1, i4, jA2, length);
                        if (lVarA != null || !lVarA.equals(lVar)) {
                            try {
                                lVar.b(file3);
                            } catch (IOException unused3) {
                            }
                        }
                        b();
                        return;
                    } catch (PackageManager.NameNotFoundException unused4) {
                        b();
                        return;
                    }
                }
                b();
                return;
                throw th;
            }
        }
    }
}
