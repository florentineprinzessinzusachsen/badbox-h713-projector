package a.a.b;

import android.os.Environment;
import android.os.StatFs;
import android.text.TextUtils;
import android.util.Log;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.InputStream;
import java.io.InputStreamReader;

/* JADX INFO: loaded from: classes.dex */
public class k {

    public static class b implements FileFilter {
        public /* synthetic */ b(a aVar) {
        }

        @Override // java.io.FileFilter
        public boolean accept(File file) {
            if (file == null) {
                return false;
            }
            if (file.isDirectory()) {
                return true;
            }
            return file.getPath().toLowerCase().endsWith(".zip");
        }
    }

    public static String a(long j) {
        String str = !TextUtils.isEmpty(n.g) ? n.g : "";
        long freeSpace = new File(n.f).getFreeSpace() / 1024;
        Log.i("CMUpdate2Tool", "KB cache FreeSpace: =" + freeSpace);
        StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
        long availableBlocks = (((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize())) >> 10;
        Log.i("CMUpdate2Tool", "KB dataSize=" + availableBlocks);
        if (j >= freeSpace || !new File(n.f).canWrite()) {
            return j < availableBlocks ? "/data/data/com.android.sysapp/update.zip" : str;
        }
        return n.f + "/update.zip";
    }

    public static String a(InputStream inputStream) {
        if (inputStream == null) {
            return "";
        }
        try {
            StringBuilder sb = new StringBuilder();
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
                while (true) {
                    String line = bufferedReader.readLine();
                    if (line == null) {
                        inputStream.close();
                        return sb.toString();
                    }
                    sb.append(line);
                    sb.append("\n");
                }
            } catch (Throwable th) {
                inputStream.close();
                throw th;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static void a() {
        File[] fileArrListFiles;
        if (n.f13a) {
            Log.i("CMUpdate2Tool", "clearCache() ");
        }
        File file = new File(g.e);
        if (file.exists() && file.isDirectory() && file.canWrite() && (fileArrListFiles = file.listFiles(new b(null))) != null) {
            for (File file2 : fileArrListFiles) {
                file2.delete();
            }
        }
        File file3 = new File(g.e + "/update.zip");
        if (file3.exists()) {
            file3.delete();
        }
        File file4 = new File("/data/data/com.android.sysapp/update.zip");
        if (file3.exists()) {
            file4.delete();
        }
        if (n.g != null) {
            File file5 = new File(n.g + "/update.zip");
            if (file3.exists()) {
                file5.delete();
            }
        }
    }
}
