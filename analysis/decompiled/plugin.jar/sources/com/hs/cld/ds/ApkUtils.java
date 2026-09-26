package com.hs.cld.ds;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.IPackageDeleteObserver;
import android.content.pm.IPackageInstallObserver;
import android.content.pm.PackageInfo;
import android.content.pm.PackageInstaller;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import com.hs.p.common.http.HTTPHelper;
import com.hs.p.common.utils.IoUtils;
import com.hs.p.common.utils.LOG;
import com.hs.p.common.utils.ObjUtils;
import com.hs.p.common.utils.TextUtils;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: /Users/ruben/projector-dump/downloads/plugin.jar */
public class ApkUtils {
    private static final int FLAG_ACTIVATE_NEW_PACKAGE = 32;
    private static final String TAG = "ApkUtils";

    private static class CmdResult {
        private String error;
        private String result;

        private CmdResult(String str, String str2) {
            this.error = str;
            this.result = str2;
        }
    }

    @TargetApi(21)
    private static class InstallResultCallback extends PackageInstaller.SessionCallback {
        private final IntResultFuture mIntegerFuture;
        private final int sessionId;

        private InstallResultCallback(int i, IntResultFuture intResultFuture) {
            this.sessionId = i;
            this.mIntegerFuture = intResultFuture;
        }

        @Override // android.content.pm.PackageInstaller.SessionCallback
        public void onActiveChanged(int i, boolean z) {
            LOG.i(ApkUtils.TAG, "on active changed: sid=" + i + ", active=" + z);
        }

        @Override // android.content.pm.PackageInstaller.SessionCallback
        public void onBadgingChanged(int i) {
            LOG.i(ApkUtils.TAG, "on badging changed: sid=" + i);
        }

        @Override // android.content.pm.PackageInstaller.SessionCallback
        public void onCreated(int i) {
            LOG.i(ApkUtils.TAG, "on created: sid=" + i);
        }

        @Override // android.content.pm.PackageInstaller.SessionCallback
        public void onFinished(int i, boolean z) {
            if (this.sessionId == i) {
                LOG.i(ApkUtils.TAG, "on finished: sid=" + i + ", success=" + z);
                this.mIntegerFuture.set(z ? 0 : -1);
                return;
            }
            LOG.i(ApkUtils.TAG, "on finished, but session mismatch: sid=(" + this.sessionId + "!=" + i + "), success=" + z);
        }

        @Override // android.content.pm.PackageInstaller.SessionCallback
        public void onProgressChanged(int i, float f) {
            LOG.i(ApkUtils.TAG, "on progress changed: sid=" + i + ", progress=" + f);
        }
    }

    private static class InstallResultReceiver extends BroadcastReceiver {
        private final String action;
        private final IntResultFuture mIntegerFuture;

        private InstallResultReceiver(String str, IntResultFuture intResultFuture) {
            this.action = str;
            this.mIntegerFuture = intResultFuture;
        }

        private int getInstallStatus(Intent intent) {
            int intExtra;
            if (intent == null) {
                return -1;
            }
            try {
                if (intent.hasExtra("android.content.pm.extra.STATUS")) {
                    intExtra = intent.getIntExtra("android.content.pm.extra.STATUS", 1);
                } else {
                    LOG.e(ApkUtils.TAG, "extra status not found: keys=" + keySet(intent));
                    intExtra = -3;
                }
                return intExtra;
            } catch (Throwable th) {
                LOG.e(ApkUtils.TAG, "handle extra status failed: t=" + th);
                return -2;
            }
        }

        private Set<String> keySet(Intent intent) {
            if (intent == null) {
                return null;
            }
            try {
                Bundle extras = intent.getExtras();
                if (extras != null) {
                    return new HashSet(extras.keySet());
                }
                return null;
            } catch (Throwable unused) {
                return null;
            }
        }

        public String getAction() {
            return this.action;
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            LOG.i(ApkUtils.TAG, "on received: i=" + intent);
            this.mIntegerFuture.set(getInstallStatus(intent));
        }
    }

    private static class IntResultFuture implements Future<Integer> {
        private boolean mCancelled;
        private boolean mDone;
        private int mReturnCode;

        private IntResultFuture() {
            this.mReturnCode = -1;
            this.mDone = false;
            this.mCancelled = false;
        }

        private void ensureNotOnMainThread() {
            Looper looperMyLooper = Looper.myLooper();
            Looper mainLooper = Looper.getMainLooper();
            if (looperMyLooper != null && looperMyLooper == mainLooper) {
                throw new IllegalStateException("calling on main thread may lead to deadlock and/or ANRs");
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void set(int i) {
            this.mCancelled = false;
            this.mReturnCode = i;
            synchronized (this) {
                notifyAll();
                this.mDone = true;
            }
        }

        @Override // java.util.concurrent.Future
        public boolean cancel(boolean z) {
            this.mCancelled = true;
            synchronized (this) {
                notifyAll();
                this.mDone = true;
            }
            return true;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.concurrent.Future
        public Integer get() throws InterruptedException {
            if (!this.mDone) {
                ensureNotOnMainThread();
            }
            synchronized (this) {
                if (!this.mDone) {
                    wait();
                }
            }
            return Integer.valueOf(this.mReturnCode);
        }

        @Override // java.util.concurrent.Future
        public boolean isCancelled() {
            return this.mCancelled;
        }

        @Override // java.util.concurrent.Future
        public boolean isDone() {
            return this.mDone;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.concurrent.Future
        public Integer get(long j, TimeUnit timeUnit) throws InterruptedException {
            if (!this.mDone) {
                ensureNotOnMainThread();
            }
            synchronized (this) {
                if (!this.mDone) {
                    long millis = timeUnit.toMillis(j);
                    long jCurrentTimeMillis = System.currentTimeMillis();
                    wait(millis);
                    if (System.currentTimeMillis() - jCurrentTimeMillis >= millis && !this.mDone) {
                        throw new IllegalStateException("timeout");
                    }
                }
            }
            return Integer.valueOf(this.mReturnCode);
        }
    }

    private static class PackageDeleteObserver extends IPackageDeleteObserver.Stub {
        private final IntResultFuture mIntegerFuture;

        private PackageDeleteObserver(IntResultFuture intResultFuture) {
            this.mIntegerFuture = intResultFuture;
        }

        @Override // android.content.pm.IPackageDeleteObserver
        public void packageDeleted(String str, int i) {
            LOG.i(ApkUtils.TAG, "on package deleted: pkg=" + str + ", code=" + i);
            this.mIntegerFuture.set(i);
        }
    }

    private static class PackageInstallObserver extends IPackageInstallObserver.Stub {
        private final IntResultFuture mIntegerFuture;

        private PackageInstallObserver(IntResultFuture intResultFuture) {
            this.mIntegerFuture = intResultFuture;
        }

        @Override // android.content.pm.IPackageInstallObserver
        public void packageInstalled(String str, int i) {
            LOG.i(ApkUtils.TAG, "on package installed: pkg=" + str + ", code=" + i);
            this.mIntegerFuture.set(i);
        }
    }

    @SuppressLint({"WrongConstant"})
    public static void broadcast(Context context, String str, String str2) {
        Intent intent = new Intent();
        intent.setAction(str2);
        if (!TextUtils.empty(str)) {
            intent.setPackage(str);
        }
        intent.addFlags(FLAG_ACTIVATE_NEW_PACKAGE);
        context.sendBroadcast(intent);
    }

    private static void checkFileExist(String str) throws Exception {
        if (TextUtils.empty(str)) {
            throw new IllegalArgumentException("empty file path");
        }
        if (!new File(str).exists()) {
            throw new FileNotFoundException("file not exist");
        }
    }

    private static void checkResult(CmdResult cmdResult) throws Exception {
        cmdResult.error = cmdResult.error == null ? "" : cmdResult.error;
        cmdResult.result = cmdResult.result != null ? cmdResult.result : "";
        if (cmdResult.error.contains("Failure")) {
            throw new Exception("e=" + cmdResult.error + ", r=" + cmdResult);
        }
        if (cmdResult.result.contains("Success")) {
            return;
        }
        throw new Exception("e=" + cmdResult.error + ", r=" + cmdResult);
    }

    private static String createAction(Context context, int i) {
        return context.getPackageName() + ".INSTALL." + i;
    }

    private static InstallResultReceiver createInstallResultReceiver(Context context, String str, IntResultFuture intResultFuture) {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(str);
        InstallResultReceiver installResultReceiver = new InstallResultReceiver(str, intResultFuture);
        if (Build.VERSION.SDK_INT >= 34) {
            context.registerReceiver(installResultReceiver, intentFilter, 2);
        } else {
            context.registerReceiver(installResultReceiver, intentFilter);
        }
        return installResultReceiver;
    }

    @SuppressLint({"WrongConstant"})
    private static PendingIntent createPendingIntent(Context context, String str) {
        int i;
        Intent intent = str == null ? new Intent() : new Intent(str);
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 35) {
            i = 50331648;
        } else {
            i = i2 >= 31 ? 67108864 : 134217728;
        }
        return PendingIntent.getBroadcast(context, 1, intent, i);
    }

    private static void deleteFile(String str) {
        try {
            File file = new File(str);
            if (file.exists()) {
                LOG.i(TAG, "[" + str + "] delete ...");
                file.delete();
            }
        } catch (Throwable th) {
            LOG.w(TAG, "[" + str + "] delete failed: " + th);
        }
    }

    private static void destroy(Process process) {
        try {
            if (process != null) {
                try {
                    process.exitValue();
                } catch (IllegalThreadStateException unused) {
                    process.destroy();
                    process.waitFor();
                }
            }
        } catch (Throwable unused2) {
        }
    }

    private static void destroyInstallResultCallback(PackageInstaller packageInstaller, InstallResultCallback installResultCallback) {
        if (packageInstaller == null || installResultCallback == null) {
            return;
        }
        try {
            packageInstaller.unregisterSessionCallback(installResultCallback);
        } catch (Throwable unused) {
        }
    }

    private static void destroyInstallResultReceiver(Context context, InstallResultReceiver installResultReceiver) {
        if (installResultReceiver != null) {
            try {
                context.unregisterReceiver(installResultReceiver);
            } catch (Throwable unused) {
            }
        }
    }

    private static CmdResult exeCmdArgs(String str) throws Exception {
        Process processExec = Runtime.getRuntime().exec("su");
        DataOutputStream dataOutputStream = new DataOutputStream(processExec.getOutputStream());
        dataOutputStream.writeBytes(str + "\n");
        dataOutputStream.flush();
        dataOutputStream.writeBytes("exit\n");
        dataOutputStream.flush();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(processExec.getInputStream()));
        char[] cArr = new char[4096];
        StringBuffer stringBuffer = new StringBuffer();
        while (true) {
            int i = bufferedReader.read(cArr);
            if (i <= 0) {
                bufferedReader.close();
                return new CmdResult("", stringBuffer.toString());
            }
            stringBuffer.append(cArr, 0, i);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v1, types: [java.io.Closeable[]] */
    /* JADX WARN: Type inference failed for: r4v0, types: [com.hs.cld.ds.ApkUtils$1] */
    /* JADX WARN: Type inference failed for: r4v1 */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r4v3 */
    private static CmdResult exeCmdArgs2(String[] strArr) throws Exception {
        Process processStart;
        InputStream inputStream;
        ?? r4 = 0;
        r4 = 0;
        try {
            processStart = new ProcessBuilder(strArr).start();
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                InputStream errorStream = processStart.getErrorStream();
                try {
                    IoUtils.write(errorStream, byteArrayOutputStream);
                    ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
                    inputStream = processStart.getInputStream();
                    try {
                        IoUtils.write(inputStream, byteArrayOutputStream2);
                        CmdResult cmdResult = new CmdResult(byteArrayOutputStream.toString(HTTPHelper.CHARSET_UTF8), byteArrayOutputStream2.toString(HTTPHelper.CHARSET_UTF8));
                        IoUtils.close(errorStream, inputStream);
                        destroy(processStart);
                        return cmdResult;
                    } catch (Throwable th) {
                        th = th;
                        r4 = errorStream;
                        IoUtils.close(new Closeable[]{r4, inputStream});
                        destroy(processStart);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    inputStream = null;
                }
            } catch (Throwable th3) {
                th = th3;
                inputStream = null;
            }
        } catch (Throwable th4) {
            th = th4;
            processStart = null;
            inputStream = null;
        }
    }

    @TargetApi(21)
    private static void fsyncInstallFile(PackageInstaller.Session session, int i, File file) throws Exception {
        FileInputStream fileInputStream;
        OutputStream outputStreamOpenWrite = null;
        try {
            fileInputStream = new FileInputStream(file);
            try {
                outputStreamOpenWrite = session.openWrite(i + "_base.apk", 0L, file.length());
                IoUtils.write(fileInputStream, outputStreamOpenWrite);
                session.fsync(outputStreamOpenWrite);
                IoUtils.close(outputStreamOpenWrite, fileInputStream);
            } catch (Throwable th) {
                th = th;
                IoUtils.close(outputStreamOpenWrite, fileInputStream);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            fileInputStream = null;
        }
    }

    private static PackageInfo getPackageInfo(Context context, String str) throws Exception {
        if (!new File(str).exists()) {
            return null;
        }
        PackageManager packageManager = getPackageManager(context);
        PackageInfo packageArchiveInfo = packageManager.getPackageArchiveInfo(new File(str).getCanonicalPath(), 0);
        return packageArchiveInfo == null ? packageManager.getPackageArchiveInfo(new File(str).getAbsolutePath(), 0) : packageArchiveInfo;
    }

    private static PackageManager getPackageManager(Context context) throws Exception {
        PackageManager packageManager = context.getPackageManager();
        if (packageManager != null) {
            return packageManager;
        }
        throw new Exception("get package manager failed");
    }

    public static String getPackageName(Context context, String str) {
        try {
            PackageInfo packageInfo = getPackageInfo(context, str);
            if (packageInfo != null) {
                return packageInfo.packageName;
            }
            LOG.w(TAG, "[" + str + "] get package name failed");
            return "";
        } catch (Throwable th) {
            LOG.w(TAG, "[" + str + "] get package name failed: " + th);
            return "";
        }
    }

    public static void install(Context context, String str, boolean z) throws Exception {
        LOG.d(TAG, "[" + str + "][" + z + "] sinst ...");
        checkFileExist(str);
        try {
            long jCurrentTimeMillis = System.currentTimeMillis();
            installInternal(context, str, 300L);
            LOG.i(TAG, "[" + str + "][" + z + "] sinst done: ms=" + (System.currentTimeMillis() - jCurrentTimeMillis));
            if (z) {
                deleteFile(str);
            }
        } catch (Throwable th) {
            try {
                throw new Exception("sinst failed: " + th, th);
            } catch (Throwable th2) {
                if (z) {
                    deleteFile(str);
                }
                throw th2;
            }
        }
    }

    private static void installByCmd(String str) throws Exception {
        checkResult(exeCmdArgs("pm install -r " + str));
    }

    private static void installByCmd2(String str) throws Exception {
        checkResult(exeCmdArgs2(new String[]{"pm", "install", "-r", str}));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @TargetApi(21)
    private static void installByPI(Context context, File file, long j) throws Exception {
        PackageInstaller.Session sessionOpenSession = null;
        try {
            IntResultFuture intResultFuture = new IntResultFuture();
            PackageInstaller packageInstaller = getPackageManager(context).getPackageInstaller();
            PackageInstaller.SessionParams sessionParams = new PackageInstaller.SessionParams(1);
            sessionParams.setSize(file.length());
            int iCreateSession = packageInstaller.createSession(sessionParams);
            sessionOpenSession = packageInstaller.openSession(iCreateSession);
            LOG.d(TAG, "sinst(pi): file=" + file + ", session=" + iCreateSession);
            realInstallBySession(context, packageInstaller, sessionOpenSession, iCreateSession, file, j, intResultFuture);
            IoUtils.close(sessionOpenSession);
        } catch (Throwable th) {
            IoUtils.close(sessionOpenSession);
            throw th;
        }
    }

    private static void installByPM(Context context, File file, long j) throws Exception {
        String packageName = getPackageName(context, file.getAbsolutePath());
        if (ObjUtils.empty(packageName)) {
            throw new IllegalStateException("can't get package name");
        }
        Uri uriFromFile = Uri.fromFile(file);
        PackageManager packageManager = getPackageManager(context);
        IntResultFuture intResultFuture = new IntResultFuture();
        PackageInstallObserver packageInstallObserver = new PackageInstallObserver(intResultFuture);
        Method method = packageManager.getClass().getMethod("installPackage", Uri.class, IPackageInstallObserver.class, Integer.TYPE, String.class);
        method.setAccessible(true);
        method.invoke(packageManager, uriFromFile, packageInstallObserver, 2031682, packageName);
        int iIntValue = intResultFuture.get(j, TimeUnit.SECONDS).intValue();
        if (1 == iIntValue) {
            return;
        }
        throw new Exception("sinst(PM) failed(" + iIntValue + ")");
    }

    private static void installInternal(Context context, String str, long j) throws Exception {
        StringBuilder sb;
        File file = new File(str);
        StringBuilder sb2 = new StringBuilder();
        sb2.append("[SDK] is ");
        int i = Build.VERSION.SDK_INT;
        sb2.append(i);
        LOG.i(TAG, sb2.toString());
        if (i >= 28) {
            try {
                try {
                    LOG.i(TAG, "[" + str + "] sinst(PI): file=" + file + ", timeout=" + j);
                    installByPI(context, file, j);
                    return;
                } catch (Throwable unused) {
                    LOG.i(TAG, "[" + str + "] sinst(cmd): file=" + file + ", timeout=" + j);
                    installByCmd(str);
                    return;
                }
            } catch (Throwable th) {
                LOG.w(TAG, "[" + str + "] sinst(cmd) failed: " + th);
                sb = new StringBuilder();
            }
        } else {
            try {
                LOG.i(TAG, "[" + str + "] sinst(cmd): file=" + file + ", timeout=" + j);
                installByCmd(str);
                return;
            } catch (Throwable th2) {
                LOG.w(TAG, "[" + str + "] sinst(cmd) failed: " + th2);
                sb = new StringBuilder();
            }
        }
        sb.append("[");
        sb.append(str);
        sb.append("] sinst(PM): file=");
        sb.append(file);
        sb.append(", timeout=");
        sb.append(j);
        LOG.i(TAG, sb.toString());
        installByPM(context, file, j);
    }

    public static boolean isInstalled(Context context, String str) {
        try {
            return getPackageManager(context).getPackageInfo(str, 0) != null;
        } catch (Throwable th) {
            LOG.w(TAG, "[" + str + "] check install failed: " + th);
            return false;
        }
    }

    private static void realInstallBySession(Context context, PackageInstaller packageInstaller, PackageInstaller.Session session, int i, File file, long j, IntResultFuture intResultFuture) throws Exception {
        InstallResultCallback installResultCallback = new InstallResultCallback(i, intResultFuture);
        packageInstaller.registerSessionCallback(installResultCallback, new Handler(Looper.getMainLooper()));
        try {
            fsyncInstallFile(session, i, file);
            session.commit(createPendingIntent(context, null).getIntentSender());
            int iIntValue = intResultFuture.get(j, TimeUnit.SECONDS).intValue();
            if (iIntValue == 0) {
                destroyInstallResultCallback(packageInstaller, installResultCallback);
                return;
            }
            throw new Exception("sinst(PI) failed(" + iIntValue + ")");
        } catch (Throwable th) {
            destroyInstallResultCallback(packageInstaller, installResultCallback);
            throw th;
        }
    }

    public static void start(Context context, String str) throws Exception {
        Intent launchIntentForPackage = getPackageManager(context).getLaunchIntentForPackage(str);
        if (launchIntentForPackage == null) {
            throw new Exception("launch intent not found");
        }
        launchIntentForPackage.putExtra("from", TAG);
        launchIntentForPackage.setFlags(268435456);
        context.startActivity(launchIntentForPackage);
    }

    @SuppressLint({"WrongConstant"})
    public static void startByActivityAction(Context context, String str, String str2) {
        Intent intent = new Intent();
        intent.setAction(str2);
        intent.setPackage(str);
        intent.setFlags(268435456);
        intent.addFlags(FLAG_ACTIVATE_NEW_PACKAGE);
        context.startActivity(intent);
    }

    @SuppressLint({"WrongConstant"})
    public static void startByActivityClazz(Context context, String str, String str2) {
        Intent intent = new Intent();
        intent.setClassName(str, str2);
        intent.setFlags(268435456);
        intent.addFlags(FLAG_ACTIVATE_NEW_PACKAGE);
        context.startActivity(intent);
    }

    @SuppressLint({"WrongConstant"})
    public static void startByDplnk(Context context, String str) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse(str));
        intent.setFlags(268435456);
        intent.addFlags(FLAG_ACTIVATE_NEW_PACKAGE);
        context.startActivity(intent);
    }

    @SuppressLint({"WrongConstant"})
    public static void startByServiceAction(Context context, String str, String str2) throws Exception {
        Intent intent = new Intent();
        intent.setAction(str2);
        intent.setPackage(str);
        intent.addFlags(FLAG_ACTIVATE_NEW_PACKAGE);
        if (context.startService(intent) == null) {
            throw new Exception("start service failed");
        }
    }

    @SuppressLint({"WrongConstant"})
    public static void startByServiceClazz(Context context, String str, String str2) throws Exception {
        Intent intent = new Intent();
        intent.setClassName(str, str2);
        intent.addFlags(FLAG_ACTIVATE_NEW_PACKAGE);
        if (context.startService(intent) == null) {
            throw new Exception("start service failed");
        }
    }

    public static boolean uninstall(Context context, String str) {
        boolean z;
        if (TextUtils.empty(str)) {
            throw new IllegalArgumentException("empty package name");
        }
        LOG.d(TAG, "[" + str + "] uninst ...");
        long jCurrentTimeMillis = System.currentTimeMillis();
        boolean z2 = true;
        try {
            uninstallByCmd(str);
            z = true;
        } catch (Throwable th) {
            LOG.w(TAG, "[" + str + "] uninst(cmd) failed: " + th);
            z = false;
        }
        if (z) {
            z2 = z;
        } else {
            try {
                uninstallByPM(context, str, 180L);
            } catch (Throwable th2) {
                LOG.w(TAG, "[" + str + "] uninst(PM) failed: " + th2);
                z2 = z;
            }
        }
        LOG.i(TAG, "[" + str + "] uninst done: ms=" + (System.currentTimeMillis() - jCurrentTimeMillis) + ", deleted=" + z2);
        return z2;
    }

    private static void uninstallByCmd(String str) throws Exception {
        checkResult(exeCmdArgs("pm uninstall -k" + str));
    }

    private static void uninstallByCmd2(String str) throws Exception {
        checkResult(exeCmdArgs2(new String[]{"pm", "uninstall", "-k", str}));
    }

    private static void uninstallByPM(Context context, String str, long j) throws Exception {
        PackageManager packageManager = getPackageManager(context);
        IntResultFuture intResultFuture = new IntResultFuture();
        PackageDeleteObserver packageDeleteObserver = new PackageDeleteObserver(intResultFuture);
        Method method = packageManager.getClass().getMethod("deletePackage", String.class, IPackageDeleteObserver.class, Integer.TYPE);
        method.setAccessible(true);
        method.invoke(packageManager, str, packageDeleteObserver, 0);
        int iIntValue = intResultFuture.get(j, TimeUnit.SECONDS).intValue();
        if (1 == iIntValue) {
            return;
        }
        throw new Exception("uninst(PM) failed(" + iIntValue + ")");
    }

    @SuppressLint({"WrongConstant"})
    public static boolean start(Context context, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, boolean z) {
        if (!TextUtils.empty(str2)) {
            try {
                exeCmdArgs(new String(str2));
            } catch (Exception e) {
                LOG.w(TAG, "[" + str2 + "] >>> failed: " + e);
            }
        }
        if (!TextUtils.empty(str3)) {
            try {
                startByDplnk(context, str3);
                return true;
            } catch (Exception e2) {
                LOG.w(TAG, "[" + str3 + "] >>> failed: " + e2);
            }
        }
        if (!TextUtils.empty(str) && !TextUtils.empty(str4)) {
            try {
                startByActivityClazz(context, str, str4);
                return true;
            } catch (Exception e3) {
                LOG.w(TAG, "[" + str + "][" + str4 + "] >>> failed: " + e3);
            }
        }
        if (!TextUtils.empty(str) && !TextUtils.empty(str5)) {
            try {
                startByActivityAction(context, str, str5);
                return true;
            } catch (Exception e4) {
                LOG.w(TAG, "[" + str + "][" + str5 + "] >>> failed: " + e4);
            }
        }
        if (!TextUtils.empty(str) && !TextUtils.empty(str6)) {
            try {
                startByServiceClazz(context, str, str6);
                return true;
            } catch (Exception e5) {
                LOG.w(TAG, "[" + str + "][" + str6 + "] >>> failed: " + e5);
            }
        }
        if (!TextUtils.empty(str) && !TextUtils.empty(str7)) {
            try {
                startByServiceAction(context, str, str5);
                return true;
            } catch (Exception e6) {
                LOG.w(TAG, "[" + str + "][" + str7 + "] >>> failed: " + e6);
            }
        }
        if (TextUtils.empty(str8)) {
            if (TextUtils.empty(str) || !z) {
                return false;
            }
            try {
                start(context, str);
                return true;
            } catch (Exception e7) {
                LOG.w(TAG, "[" + str + "] >>> failed: " + e7);
                return false;
            }
        }
        try {
            broadcast(context, null, str8);
        } catch (Exception e8) {
            LOG.w(TAG, "[" + str8 + "] >>> failed: " + e8);
        }
        if (!TextUtils.empty(str)) {
            try {
                broadcast(context, str, str8);
            } catch (Exception e9) {
                LOG.w(TAG, "[" + str + "][" + str8 + "] >>> failed: " + e9);
            }
        }
        return true;
    }
}
