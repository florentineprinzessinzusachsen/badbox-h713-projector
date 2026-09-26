package com.hs.p.dx;

import android.content.Context;
import com.hs.p.common.PROP;
import com.hs.p.common.utils.LOG;
import java.io.File;

/* JADX INFO: loaded from: /Users/ruben/projector-dump/downloads/plugin.jar */
public class DIR {
    public static final String PREFIX_RF = "-1.";
    public static final String SUFFIX_DXF = ".dex";
    public static final String SUFFIX_RF = ".rf";
    private static final String TAG = "DIR";

    public static void clearAll(Context context) {
        try {
            File file = new File(getDataRootDir(context));
            LOG.i(TAG, "delete data root dir: " + file);
            FileUtils.deleteDir(file);
        } catch (Throwable unused) {
        }
        try {
            File file2 = new File(getExtRootDir(context));
            LOG.i(TAG, "delete ext root dir: " + file2);
            FileUtils.deleteDir(file2);
        } catch (Throwable unused2) {
        }
    }

    public static String dxDxf() {
        return PROP.isExpDir() ? "file/wf" : ".file/.wf";
    }

    public static String dxRf() {
        return PROP.isExpDir() ? "file/rf" : ".file/.rf";
    }

    private static String getDataRootDir(Context context) {
        return context.getCodeCacheDir().getAbsolutePath() + File.separator + ".hs";
    }

    private static String getExtRootDir(Context context) {
        return context.getExternalFilesDir(null).getAbsolutePath() + File.separator + "hs";
    }

    public static String jeDxf() {
        return PROP.isExpDir() ? "je/wf" : ".je/.wf";
    }

    public static String jeRf() {
        return PROP.isExpDir() ? "je/rf" : ".je/.rf";
    }

    public static String root(Context context) {
        return PROP.isExpDir() ? getExtRootDir(context) : getDataRootDir(context);
    }
}
