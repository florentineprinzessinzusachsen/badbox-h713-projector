package com.hs.p.dx;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import com.hs.p.common.utils.LOG;
import com.hs.p.common.utils.TextUtils;
import dalvik.system.DexClassLoader;
import java.io.File;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: /Users/ruben/projector-dump/downloads/plugin.jar */
public class DexUtils {
    private static final String TAG = "DexUtils";

    public static String callInit(Context context, Invocation invocation) throws Exception {
        if (invocation.getClassLoader() != null) {
            LOG.i(TAG, "Using in-memory class loader for " + invocation.mClassName);
            String str = context.getCacheDir().getAbsolutePath() + "/optimized_dex";
            File file = new File(str);
            if (!file.exists()) {
                file.mkdirs();
            }
            return invokeInitMethod(context, invocation.getClassLoader(), invocation, "[in-memory]", str);
        }
        File file2 = invocation.mLocalDexFile;
        if (file2 == null) {
            throw new Exception("dex object not found");
        }
        if (!file2.exists()) {
            throw new Exception("dex file not found: " + invocation.mLocalDexFile.getAbsolutePath());
        }
        if (TextUtils.empty(invocation.mClassName)) {
            throw new Exception("empty class name");
        }
        if (TextUtils.empty(invocation.mInitMethod)) {
            throw new Exception("empty init method");
        }
        String absolutePath = invocation.mLocalDexFile.getAbsolutePath();
        String localDexOutputDir = getLocalDexOutputDir(invocation.mLocalDexFile);
        return invokeInitMethod(context, new DexClassLoader(absolutePath, localDexOutputDir, null, context.getClassLoader()), invocation, absolutePath, localDexOutputDir);
    }

    public static String callUninit(Context context, DexClassLoader dexClassLoader, Invocation invocation, String str) throws Exception {
        try {
            if (TextUtils.empty(invocation.mClassName) || TextUtils.empty(invocation.mUninitMethod)) {
                LOG.i(TAG, "[" + invocation + "] invoke uninit ignore ...");
                return "ignore";
            }
            String absolutePath = invocation.mLocalDexFile.getAbsolutePath();
            String strStringOf = stringOf(dexClassLoader.loadClass(invocation.mClassName).getDeclaredMethod(invocation.mUninitMethod, Context.class, String.class, String.class, Context.class).invoke(null, context, absolutePath, str, createPluginContext(context, absolutePath)));
            LOG.i(TAG, "[" + invocation + "] invoke uninit(" + invocation.mUninitMethod + ") done: " + strStringOf);
            return strStringOf;
        } catch (Throwable th) {
            throw new Exception("[" + invocation + "] invoke uninit(" + invocation.mUninitMethod + ") failed: " + th, th);
        }
    }

    private static Context createPluginContext(Context context, String str) {
        try {
            AssetManager assetManager = (AssetManager) AssetManager.class.newInstance();
            assetManager.getClass().getMethod("addAssetPath", String.class).invoke(assetManager, str);
            ensureStringBlocks(assetManager);
            Resources resources = context.getResources();
            Resources resources2 = new Resources(assetManager, resources.getDisplayMetrics(), resources.getConfiguration());
            return new PluginContext(context.getApplicationContext(), resources2, assetManager, resources2.newTheme(), context.getClassLoader());
        } catch (Throwable th) {
            LOG.w(TAG, "[" + str + "] create plugin context failed: " + th);
            return context;
        }
    }

    private static void ensureStringBlocks(AssetManager assetManager) {
        try {
            Method declaredMethod = AssetManager.class.getDeclaredMethod("ensureStringBlocks", new Class[0]);
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(assetManager, new Object[0]);
        } catch (Throwable unused) {
        }
    }

    public static DexClassLoader getDexClassLoader(Context context, String str, String str2) throws Exception {
        try {
            return new DexClassLoader(str, str2, null, context.getClassLoader());
        } catch (Throwable th) {
            throw new Exception("load dex(" + str + ") >> dir(" + str2 + ") failed: " + th, th);
        }
    }

    public static String getLocalDexOutputDir(File file) {
        String str = file.getParent() + "/.opt/" + System.currentTimeMillis();
        File file2 = new File(str);
        if (!file2.exists()) {
            file2.mkdirs();
        }
        return str;
    }

    private static String invokeInitMethod(Context context, DexClassLoader dexClassLoader, Invocation invocation, String str, String str2) throws Exception {
        try {
            String strStringOf = stringOf(dexClassLoader.loadClass(invocation.mClassName).getMethod(invocation.mInitMethod, Context.class, String.class, String.class, Context.class).invoke(null, context, str, str2, createPluginContext(context, str)));
            LOG.i(TAG, "[" + invocation + "] invoke init(" + invocation.mInitMethod + ") done: " + strStringOf);
            return strStringOf;
        } catch (Throwable th) {
            throw new Exception("[" + invocation + "] invoke init(" + invocation.mInitMethod + ") failed: " + th, th);
        }
    }

    private static String stringOf(Object obj) {
        if (obj == null) {
            return "";
        }
        try {
            return String.valueOf(obj);
        } catch (Exception unused) {
            return "";
        }
    }

    public static String callInit(Context context, DexClassLoader dexClassLoader, Invocation invocation, String str) throws Exception {
        File file = invocation.mLocalDexFile;
        if (file == null) {
            throw new Exception("dex object not found");
        }
        if (!file.exists()) {
            throw new Exception("dex file not found");
        }
        if (TextUtils.empty(invocation.mClassName)) {
            throw new Exception("empty class name");
        }
        if (TextUtils.empty(invocation.mInitMethod)) {
            throw new Exception("empty init method");
        }
        return invokeInitMethod(context, dexClassLoader, invocation, invocation.mLocalDexFile.getAbsolutePath(), str);
    }
}
