package a.a.b;

import android.os.Environment;
import android.os.SystemProperties;
import android.util.Log;
import com.android.sysapp.R;
import com.android.sysapp.UpdateApp;
import java.io.File;
import java.net.URLEncoder;

/* JADX INFO: loaded from: classes.dex */
public class n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static boolean f13a = true;
    public static boolean b = false;
    public static String c = a(1);
    public static String d = a(2);
    public static final int e = Integer.parseInt(UpdateApp.f56a.getString(R.string.check_cycle));
    public static final String f = UpdateApp.f56a.getString(R.string.cache_dir);
    public static final String g;
    public static String h;

    static {
        String string;
        if (Environment.getExternalStorageState().equals("mounted")) {
            File externalStorageDirectory = Environment.getExternalStorageDirectory();
            Log.i("CMUpdate2", "sdcard=" + externalStorageDirectory);
            string = externalStorageDirectory.toString();
        } else {
            Log.i("CMUpdate2", "sdcard==null");
            string = null;
        }
        g = string;
        h = f + "/update.zip";
        if (f13a) {
            String str = SystemProperties.get("ro.build.type", "unkown");
            if (str.equals("user")) {
                f13a = false;
                return;
            }
            if (str.contains("eng")) {
                Log.v("CMUpdate2", "xml url1:" + c);
                Log.v("CMUpdate2", "xml url2:" + d);
                Log.v("CMUpdate2", "check cycle:" + e);
                Log.v("CMUpdate2", "cache dir:" + f);
            }
        }
    }

    public static String a(int i) {
        m.c();
        String str = "http://wjtysj.ishanghd.com/hx_kt.php?act=project&do=getPackageInfo&productid=" + m.e + "&device_mode=" + URLEncoder.encode(m.f12a) + "&devices_version=" + URLEncoder.encode(m.d) + "&update_type=" + (b ? 2 : 1);
        if (f13a) {
            Log.i("CMUpdate2", "str=" + str);
        }
        return str;
    }
}
