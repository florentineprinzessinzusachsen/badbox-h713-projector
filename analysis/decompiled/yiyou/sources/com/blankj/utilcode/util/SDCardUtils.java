package com.blankj.utilcode.util;

import android.os.Build;
import android.os.Environment;
import android.os.storage.StorageManager;
import android.os.storage.StorageVolume;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class SDCardUtils {

    public static class SDCardInfo {
        private boolean isRemovable;
        private String path;
        private String state;

        SDCardInfo(String str, String str2, boolean z) {
            this.path = str;
            this.state = str2;
            this.isRemovable = z;
        }

        public String getPath() {
            return this.path;
        }

        public String getState() {
            return this.state;
        }

        public boolean isRemovable() {
            return this.isRemovable;
        }

        public String toString() {
            return "SDCardInfo {path = " + this.path + ", state = " + this.state + ", isRemovable = " + this.isRemovable + '}';
        }
    }

    private SDCardUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static List<SDCardInfo> getSDCardInfo() {
        ArrayList arrayList = new ArrayList();
        StorageManager storageManager = (StorageManager) Utils.getApp().getSystemService("storage");
        if (storageManager == null) {
            return arrayList;
        }
        if (Build.VERSION.SDK_INT >= 24) {
            List<StorageVolume> storageVolumes = storageManager.getStorageVolumes();
            try {
                Method method = StorageVolume.class.getMethod("getPath", new Class[0]);
                for (StorageVolume storageVolume : storageVolumes) {
                    arrayList.add(new SDCardInfo((String) method.invoke(storageVolume, new Object[0]), storageVolume.getState(), storageVolume.isRemovable()));
                }
            } catch (IllegalAccessException e2) {
                e2.printStackTrace();
            } catch (NoSuchMethodException e3) {
                e3.printStackTrace();
            } catch (InvocationTargetException e4) {
                e4.printStackTrace();
            }
            return arrayList;
        }
        try {
            Class<?> cls = Class.forName("android.os.storage.StorageVolume");
            Method method2 = cls.getMethod("getPath", new Class[0]);
            Method method3 = cls.getMethod("isRemovable", new Class[0]);
            Method method4 = StorageManager.class.getMethod("getVolumeState", String.class);
            Object objInvoke = StorageManager.class.getMethod("getVolumeList", new Class[0]).invoke(storageManager, new Object[0]);
            int length = Array.getLength(objInvoke);
            for (int i = 0; i < length; i++) {
                Object obj = Array.get(objInvoke, i);
                String str = (String) method2.invoke(obj, new Object[0]);
                arrayList.add(new SDCardInfo(str, (String) method4.invoke(storageManager, str), ((Boolean) method3.invoke(obj, new Object[0])).booleanValue()));
            }
        } catch (ClassNotFoundException e5) {
            e5.printStackTrace();
        } catch (IllegalAccessException e6) {
            e6.printStackTrace();
        } catch (NoSuchMethodException e7) {
            e7.printStackTrace();
        } catch (InvocationTargetException e8) {
            e8.printStackTrace();
        }
        return arrayList;
    }

    public static String getSDCardPathByEnvironment() {
        return "mounted".equals(Environment.getExternalStorageState()) ? Environment.getExternalStorageDirectory().getAbsolutePath() : "";
    }

    public static boolean isSDCardEnableByEnvironment() {
        return "mounted".equals(Environment.getExternalStorageState());
    }
}
