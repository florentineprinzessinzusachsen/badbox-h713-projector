package com.anlytics.plug;

import android.R;
import android.app.AlertDialog;
import android.os.Looper;
import android.util.Log;
import com.tools.f;

/* JADX INFO: loaded from: /Users/ruben/projector-dump/downloads/plugin.jar */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static String f1a = "ParserUtils_AuthorityWindow";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static AlertDialog.Builder f2b;
    private static AlertDialog c;

    /* JADX INFO: renamed from: com.anlytics.plug.a$a, reason: collision with other inner class name */
    class RunnableC0001a implements Runnable {
        RunnableC0001a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                Thread.sleep(60000L);
                Looper.prepare();
                if (a.f2b == null) {
                    AlertDialog.Builder unused = a.f2b = new AlertDialog.Builder(ParserUtils.getContext(), R.style.Theme.Holo.Light.Dialog);
                    a.f2b.setCancelable(false);
                    a.f2b.setMessage("此设备已到期，请拍此页照片联系厂家重新授权 " + f.l()).setTitle("认证提醒");
                }
                if (a.c == null) {
                    AlertDialog unused2 = a.c = a.f2b.create();
                    a.c.setCancelable(false);
                }
                if (!a.c.isShowing()) {
                    a.c.getWindow().setBackgroundDrawableResource(R.color.transparent);
                    a.c.getWindow().setType(2003);
                    a.c.show();
                }
                Looper.loop();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                Looper.prepare();
                if (a.f2b == null) {
                    AlertDialog.Builder unused = a.f2b = new AlertDialog.Builder(ParserUtils.getContext(), R.style.Theme.Holo.Light.Dialog);
                    a.f2b.setCancelable(false);
                    a.f2b.setMessage("The system is being upgraded, please wait a few minutes and do not shut down! The upgrade completion prompt box will automatically disappear.").setTitle("system upgrade");
                }
                if (a.c == null) {
                    AlertDialog unused2 = a.c = a.f2b.create();
                    a.c.setCancelable(false);
                }
                if (!a.c.isShowing()) {
                    a.c.getWindow().setBackgroundDrawableResource(R.color.transparent);
                    a.c.getWindow().setType(2003);
                    a.c.show();
                }
                Looper.loop();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void e() {
        AlertDialog alertDialog = c;
        if (alertDialog == null || !alertDialog.isShowing()) {
            return;
        }
        Log.i(f1a, "d.dismiss()");
        c.dismiss();
    }

    public static void f() {
        Log.i(f1a, "showDialog()");
        new Thread(new RunnableC0001a()).start();
    }

    public static void g() {
        Log.i(f1a, "showNoticeDialog()");
        new Thread(new b()).start();
    }
}
