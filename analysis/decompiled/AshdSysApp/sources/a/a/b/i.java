package a.a.b;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class i extends BroadcastReceiver {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Context f9a;

    public List<String> a(File file) {
        Log.d("ScreenReceiver", "getRWUsbDirectory");
        ArrayList arrayList = new ArrayList();
        Log.d("ScreenReceiver", arrayList.size() + "");
        if (file.isDirectory()) {
            File[] fileArrListFiles = file.listFiles();
            if (fileArrListFiles == null || fileArrListFiles.length <= 0) {
                return null;
            }
            for (File file2 : fileArrListFiles) {
                Log.d("ScreenReceiver", file2.canRead() + "");
                if (file2.isDirectory() && file2.canRead()) {
                    Log.d("ScreenReceiver", file2.getName() + "");
                    arrayList.add(file2.getName());
                }
            }
        } else {
            Log.v("ScreenReceiver", "你输入的不是一个文件夹，请检查路径是否有误！！");
        }
        return arrayList;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        this.f9a = context;
        if ("android.intent.action.MEDIA_MOUNTED".equals(intent.getAction())) {
            Log.d("ScreenReceiver", "U盘已插入");
            Log.d("ScreenReceiver", "开始查找文件");
            try {
                List<String> listA = a(new File("/storage"));
                if (listA == null) {
                    Log.i("ScreenReceiver", "getUsbstatus() list==null");
                    return;
                }
                for (int i = 0; i < listA.size(); i++) {
                    String str = "/storage/" + listA.get(i) + "/cleantime";
                    File file = new File(str);
                    Log.d("ScreenReceiver", str);
                    if (file.exists()) {
                        Intent intent2 = new Intent();
                        intent2.setFlags(268435456);
                        intent2.setAction("com.ashd.cleantime");
                        this.f9a.sendBroadcast(intent2);
                        Log.d("ScreenReceiver", "发送广播");
                    }
                }
            } catch (Exception unused) {
                Log.i("ScreenReceiver", "getUsbstatus error");
            }
        }
    }
}
