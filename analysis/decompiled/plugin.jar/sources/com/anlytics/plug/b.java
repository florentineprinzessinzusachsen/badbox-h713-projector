package com.anlytics.plug;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import com.hs.App;
import com.hs.Builder;
import com.tools.d;
import com.tools.e;
import com.tools.f;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: /Users/ruben/projector-dump/downloads/plugin.jar */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static boolean f3a = false;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static String f4b = "ParserUtils_Init";
    private static BroadcastReceiver c = null;
    private static IntentFilter d = null;
    public static boolean e = false;
    public static boolean f = false;
    public static boolean g = false;
    public static boolean h = false;
    public static long i = 0;
    private static boolean j = true;
    private static boolean k = false;
    private static String l = "/vendor/etc/bluetooth/skwbt.conf";
    private static boolean m = false;
    private static int n;

    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                b.e0();
            } catch (Exception e) {
                Log.e(b.f4b, "installDSZB exception=" + e);
            }
        }
    }

    /* JADX INFO: renamed from: com.anlytics.plug.b$b, reason: collision with other inner class name */
    class C0002b extends BroadcastReceiver {
        C0002b() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Log.d(b.f4b, "action=" + action);
            b.f0();
        }
    }

    private static class c extends Thread {
        private c() {
        }

        /* JADX WARN: Code duplicated, block: B:101:? A[RETURN, SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:69:0x024c  */
        /* JADX WARN: Code duplicated, block: B:77:0x027f  */
        /* JADX WARN: Code duplicated, block: B:82:0x0294  */
        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            String str;
            String str2;
            StringBuilder sb;
            String str3;
            String str4;
            com.tools.c.p();
            if (b.F0()) {
                b.b(true);
            }
            b.W0();
            b.X0();
            b.Y0();
            b.h1();
            b.H0();
            b.a1();
            if (!b.g) {
                b.u1();
            }
            if (b.B0()) {
                boolean zEquals = f.g().v(new File("/system/lib/librkffplayer.so")).equals("A54F15E099AD4439DA7EC3BFA86A1795");
                e.c("persist.ashd.mediastr0", "http://127.0.0.1:;.gitv.tv&;&tvid=&");
                if (zEquals) {
                    e.c("persist.ashd.mediastr1", "http://127.0.0.1:;.ott.cibntv.net/;/m3u8");
                    str4 = Builder.VERSION;
                } else {
                    e.c("persist.ashd.mediastr1", "pl-ali.youku.com/playlist/m3u8;&device_type=");
                    e.c("persist.ashd.mediastr2", ".iqiyi.com/;.m3u8");
                    e.c("persist.ashd.mediastr3", "http://127.0.0.1:;.ott.cibntv.net/;m3u8");
                    str4 = "4";
                }
                e.c("persist.ashd.medianum", str4);
                b.v1();
            } else if (!b.A0() && !b.D0()) {
                if (b.r0()) {
                    b.j0();
                } else if (b.t0()) {
                    b.R(com.tools.a.P, "infosdate_skw");
                }
            }
            if (e.a("persist.liveservice.enable", 0) == 0) {
                e.c("persist.liveservice.enable", "1");
            }
            int i = 0;
            while (true) {
                i++;
                if (i > Integer.MAX_VALUE) {
                    i = 0;
                }
                if (f.g().s()) {
                    break;
                }
                try {
                    Thread.sleep(20000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (!b.g) {
                b.u1();
            }
            if (b.w0()) {
                b.d0();
            }
            if (b.n0()) {
                b.c0();
            }
            if (b.t0()) {
                b.R(com.tools.a.P, "infosdate_skw");
            }
            if (b.s0()) {
                b.R(com.tools.a.Q, "infosdate_nefhelp");
            }
            if (b.o0()) {
                str = com.tools.a.f42b + b.Q() + "_" + b.Y() + "/" + com.tools.c.h(ParserUtils.getContext()) + "_AD/infos.json";
                str2 = b.f4b;
                sb = new StringBuilder();
            } else if (b.m0()) {
                str = com.tools.a.f42b + b.Q() + "_" + b.Y() + "/" + com.tools.c.h(ParserUtils.getContext()) + "_AD/infos.json";
                str2 = b.f4b;
                sb = new StringBuilder();
            } else if (b.u0()) {
                str = com.tools.a.f42b + b.Q() + "_" + b.Y() + "/" + com.tools.c.h(ParserUtils.getContext()) + "_AD/infos.json";
                str2 = b.f4b;
                sb = new StringBuilder();
            } else {
                if (!b.v0()) {
                    if (b.p0()) {
                        str = com.tools.a.f42b + b.Q() + "_" + b.Y() + "/" + com.tools.c.h(ParserUtils.getContext()) + "_AD/infos.json";
                        str2 = b.f4b;
                        sb = new StringBuilder();
                    }
                    if (b.y0()) {
                        if (!b.Q().contains("H713") || b.Q().contains("H723") || b.Q().contains("H716")) {
                            Log.i(b.f4b, "isNeed_Update_DSN H713");
                            str3 = com.tools.a.R;
                        } else {
                            Log.i(b.f4b, "isNeed_Update_DSN rk3326");
                            str3 = com.tools.a.S;
                        }
                        b.R(str3, "infosdate_dsn");
                    }
                    if (b.Z()) {
                        return;
                    }
                    try {
                        Thread.sleep(10000L);
                    } catch (InterruptedException e2) {
                        e2.printStackTrace();
                        return;
                    }
                }
                str = com.tools.a.f42b + b.Q() + "_" + b.Y() + "/" + com.tools.c.h(ParserUtils.getContext()) + "_AD/infos.json";
                str2 = b.f4b;
                sb = new StringBuilder();
            }
            sb.append("path= ");
            sb.append(str);
            Log.i(str2, sb.toString());
            b.R(str, "infos_ad");
            if (b.y0()) {
                if (b.Q().contains("H713")) {
                    Log.i(b.f4b, "isNeed_Update_DSN H713");
                    str3 = com.tools.a.R;
                } else {
                    Log.i(b.f4b, "isNeed_Update_DSN H713");
                    str3 = com.tools.a.R;
                }
                b.R(str3, "infosdate_dsn");
            }
            if (b.Z()) {
                return;
            }
            Thread.sleep(10000L);
        }

        /* synthetic */ c(a aVar) {
            this();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean A0() {
        try {
            String strTrim = e.b("ro.board.platform", "0").trim();
            String strTrim2 = e.b("ro.product.model", "0").trim();
            String strTrim3 = e.b("ro.zeasn.devicetype", "0").trim();
            if (!strTrim.equals("rk3128") || strTrim2.equals("C800X") || strTrim3.equals("AS3128")) {
                return false;
            }
            Log.i(f4b, "is rk3128");
            String strTrim4 = e.b("ro.build.version.release", "0").trim();
            return strTrim4.contains("2020") || strTrim4.contains("2022") || strTrim4.contains("2023") || strTrim4.contains("2021");
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean B0() {
        try {
            if (!e.b("ro.rk.cpu", "0").trim().equals("rk3188")) {
                return false;
            }
            Log.i(f4b, "is rk3188");
            return true;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    private static boolean C0() {
        try {
            Log.i(f4b, "isRk3326Haiwai()1");
            if (e.b("ro.board.platform", "1111").endsWith("rk3326")) {
                Log.i(f4b, "isRk3326Haiwai()2");
                if (new File("/system/etc/voice.tar.gz").exists()) {
                    Log.i(f4b, "isRk3326Haiwai()3");
                    return true;
                }
            }
        } catch (Exception e2) {
            Log.i(f4b, "isRk3326Haiwai()4");
            e2.printStackTrace();
        }
        Log.i(f4b, "isRk3326Haiwai()5");
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean D0() {
        try {
            String strTrim = e.b("ro.board.platform", "0").trim();
            String strTrim2 = e.b("ro.build.product", "0").trim();
            if (!strTrim.equals("rk3326") || !strTrim2.equals("rk3326_box")) {
                return false;
            }
            Log.i(f4b, "is rk3326");
            return (!T() || com.tools.c.c("com.zeasn.whale.saas") || com.tools.c.c("com.ashd.launcher8") || com.tools.c.c("com.uv.droid.launcher.projectorsaier")) ? false : true;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    private static boolean E0() {
        try {
            String strB = e.b("persist.ashd.push.test", "false");
            return !TextUtils.isEmpty(strB) && strB.equals("true");
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean F0() {
        try {
            if (!"RK3128_WY2".endsWith(Build.MODEL)) {
                return false;
            }
            String str = Build.VERSION.RELEASE;
            if (!"7.1.2-RS-20210430.1027".endsWith(str) && !"7.1.2-RS-20210619.1626".endsWith(str)) {
                return false;
            }
            Log.i(f4b, "isWY_DV600P381");
            return true;
        } catch (Exception e2) {
            Log.i(f4b, "isnotWY_DV600P381 Exception");
            e2.printStackTrace();
            return false;
        }
    }

    private static boolean G0() {
        try {
            String str = Build.MODEL;
            if (!"RK3128_WY".endsWith(str) && !"WY_AET".endsWith(str) && !"RK3128_WY2".endsWith(str) && !"VY_FJ600".endsWith(str) && !"VY2_FJ600".endsWith(str)) {
                return false;
            }
            e.c("persist.product.modename", "null");
            if (com.tools.c.c("com.ashd.launcher")) {
                return com.tools.c.n("com.ashd.launcher") < 145 || com.tools.c.n("com.ashd.settings") < 158;
            }
            return false;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean H0() {
        if (!e.b("persist.aicast.name", "1111").contains("1111") || W().contains("13-RS-20260304.1518")) {
            return false;
        }
        String strB = e.b("ro.product.model2", "1111");
        if (strB.contains("1111") || !strB.contains("NULL")) {
            return false;
        }
        e.c("persist.aicast.name", "AICast_" + strB.replace("NULL", " "));
        return true;
    }

    private static boolean I0() {
        try {
            return e.b("ro.board.platform", "1111").contains("rk3326") && e.b("persist.sys.cm.dtsmodel", "1111").contains("BNX_TXD265_AHW_3+64_Lingbo");
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    private static boolean J0() {
        try {
            if (!e.b("ro.board.platform", "1111").contains("rk3326") || !e.b("persist.sys.cm.dtsmodel", "1111").contains("RK3326_HY300A_GBPT_SUR269_AHW")) {
                return false;
            }
            String strB = e.b("ro.build.version.release", "1111");
            return strB.equals("9.0-RS-20240807.1756") || strB.equals("11.0-RS-20240817.1348") || strB.equals("9.0-RS-20240730.2144");
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    private static boolean K0() {
        try {
            if ("RK3128_WY6".endsWith(Build.MODEL)) {
                e.c("persist.product.modename", "null");
                return com.tools.c.c("com.ashd.launcher") && (com.tools.c.n("com.ashd.launcher") < 146 || com.tools.c.n("com.ashd.settings") < 158);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return false;
    }

    private static boolean L0() {
        try {
            return e.b("ro.sys.cputype", "1111").contains("QuadCore-H713") && e.b("persist.sys.cm.dtsmodel", "1111").contains("H713M_HY300_D");
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    private static boolean M0() {
        try {
            return e.b("ro.sys.cputype", "1111").contains("QuadCore-H713") && e.b("persist.sys.cm.dtsmodel", "1111").contains("H713M_HY300_G") && e.b("ro.build.version.release", "1111").contains("11-RS-20241207.1757") && !e.b("persist.sys.wifitype", "1111").contains("AIC8800D40");
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public static void N() {
        c = new C0002b();
        IntentFilter intentFilter = new IntentFilter();
        d = intentFilter;
        intentFilter.addAction("ashd.action.INSTALL_DSZB");
        ParserUtils.getContext().registerReceiver(c, d);
    }

    private static boolean N0() {
        if (!e.b("ro.sys.cputype", "1111").contains("QuadCore-H713")) {
            return false;
        }
        String strB = e.b("ro.build.version.release", "1111");
        return strB.equals("11-RS-20241008.1730") || strB.equals("11-RS-20240928.1605");
    }

    public static boolean O() {
        File file = new File("/data/mediadrm/IDM1013/L3");
        try {
            Log.i(f4b, "createFolderS 1");
            if (file.exists()) {
                Log.i(f4b, "createFolderS 8");
                System.out.println("Folder already exists at: /data/mediadrm/IDM1013/L3");
                return true;
            }
            Log.i(f4b, "createFolderS 2");
            File file2 = new File("/data/mediadrm");
            if (file2.exists() || !file2.mkdir()) {
                return false;
            }
            Log.i(f4b, "createFolderS 3");
            Runtime.getRuntime().exec("chmod 777 /data/mediadrm \n");
            Log.i(f4b, "createFolderS 4");
            File file3 = new File("/data/mediadrm/IDM1013");
            if (file3.exists() || !file3.mkdir()) {
                return false;
            }
            Log.i(f4b, "createFolderS 5");
            Runtime.getRuntime().exec("chmod 777 /data/mediadrm/IDM1013 \n");
            if (!file.mkdir()) {
                Log.i(f4b, "createFolderS 7");
                System.out.println("Failed to create folder at: /data/mediadrm/IDM1013/L3");
                return false;
            }
            Log.i(f4b, "createFolderS 6");
            Runtime.getRuntime().exec("chmod 777 /data/mediadrm/IDM1013/L3 \n");
            System.out.println("Folder created at: /data/mediadrm/IDM1013/L3");
            return true;
        } catch (IOException e2) {
            Log.i(f4b, "createFolderS 9");
            e2.printStackTrace();
            return false;
        }
    }

    private static boolean O0() {
        try {
            if (!e.b("ro.sys.cputype", "1111").contains("QuadCore-H713") || !e.b("ro.build.version.release", "1111").contains("13-RS-20260304.1518") || !e.b("persist.sys.cm.dtsmodel", "1111").contains("H713M_HY300")) {
                return false;
            }
            e.c("persist.aicast.name", "View 515 Plus");
            e.c("persist.miracast.name", "View 515 Plus");
            e.c("persist.ashd.bt.name", "View 515 Plus");
            return true;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    /* JADX WARN: Code duplicated, block: B:90:0x0322 A[Catch: JSONException | Exception -> 0x0458, JSONException -> 0x045a, TryCatch #2 {JSONException | Exception -> 0x0458, blocks: (B:6:0x007d, B:8:0x00a8, B:11:0x00b8, B:13:0x00d8, B:35:0x0171, B:38:0x017b, B:40:0x019b, B:43:0x01a2, B:46:0x01ad, B:48:0x01b6, B:50:0x01bd, B:52:0x01c3, B:54:0x020b, B:56:0x022b, B:59:0x0232, B:62:0x023d, B:64:0x0246, B:66:0x024d, B:68:0x0253, B:70:0x025b, B:71:0x0272, B:105:0x0432, B:72:0x0276, B:74:0x027c, B:76:0x0282, B:78:0x029e, B:80:0x02c9, B:82:0x02d9, B:84:0x02df, B:86:0x02e5, B:90:0x0322, B:92:0x0328, B:94:0x0336, B:96:0x03bb, B:100:0x0407, B:102:0x0427, B:104:0x042d, B:97:0x03d5, B:98:0x03ef, B:88:0x02f3, B:101:0x040f, B:108:0x0442, B:16:0x0100, B:19:0x010f, B:22:0x011b, B:24:0x0123, B:26:0x0143, B:29:0x014a, B:32:0x0163, B:34:0x016c), top: B:118:0x007d }] */
    /* JADX WARN: Code duplicated, block: B:98:0x03ef A[Catch: JSONException | Exception -> 0x0458, JSONException -> 0x045a, TryCatch #2 {JSONException | Exception -> 0x0458, blocks: (B:6:0x007d, B:8:0x00a8, B:11:0x00b8, B:13:0x00d8, B:35:0x0171, B:38:0x017b, B:40:0x019b, B:43:0x01a2, B:46:0x01ad, B:48:0x01b6, B:50:0x01bd, B:52:0x01c3, B:54:0x020b, B:56:0x022b, B:59:0x0232, B:62:0x023d, B:64:0x0246, B:66:0x024d, B:68:0x0253, B:70:0x025b, B:71:0x0272, B:105:0x0432, B:72:0x0276, B:74:0x027c, B:76:0x0282, B:78:0x029e, B:80:0x02c9, B:82:0x02d9, B:84:0x02df, B:86:0x02e5, B:90:0x0322, B:92:0x0328, B:94:0x0336, B:96:0x03bb, B:100:0x0407, B:102:0x0427, B:104:0x042d, B:97:0x03d5, B:98:0x03ef, B:88:0x02f3, B:101:0x040f, B:108:0x0442, B:16:0x0100, B:19:0x010f, B:22:0x011b, B:24:0x0123, B:26:0x0143, B:29:0x014a, B:32:0x0163, B:34:0x016c), top: B:118:0x007d }] */
    /* JADX WARN: Instruction removed from duplicated block: B:98:0x03ef, please report this as an issue */
    private static boolean P() {
        String str;
        String str2;
        String str3;
        String str4;
        String str5 = com.tools.a.f42b + Q() + "_" + com.tools.c.h(ParserUtils.getContext()) + "/3326_update/infos.json";
        Log.i(f4b, "3326update url_model=" + str5);
        String strQ = f.g().q(str5);
        Log.i(f4b, "infos=" + strQ);
        if (!TextUtils.isEmpty(strQ) && strQ.length() > 100) {
            try {
                int iF = d.f(ParserUtils.getContext(), "infosdate_3326_update", 0);
                Log.i(f4b, "3326_update localinfosdate=" + iF);
                JSONObject jSONObject = new JSONObject(strQ);
                int iOptInt = jSONObject.optInt("date");
                if (iF < iOptInt) {
                    JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("appInfos");
                    if (jSONObject.has("wifimac")) {
                        String strOptString = jSONObject.optString("wifimac");
                        Log.i(f4b, "wifimac=" + strOptString);
                        if (TextUtils.isEmpty(strOptString)) {
                            str = "infosdate_3326_update";
                        } else {
                            String strP = f.g().p();
                            String str6 = f4b;
                            StringBuilder sb = new StringBuilder();
                            str = "infosdate_3326_update";
                            sb.append("wmac=");
                            sb.append(strP);
                            Log.i(str6, sb.toString());
                            if (!strOptString.contains("ALL") && !strOptString.toUpperCase().contains(strP.toUpperCase())) {
                                Log.i(f4b, "return ,wifimac cuowu!");
                                return true;
                            }
                        }
                    } else {
                        str = "infosdate_3326_update";
                        if (jSONObject.has("mac")) {
                            String strOptString2 = jSONObject.optString("mac");
                            Log.i(f4b, "macstr=" + strOptString2);
                            if (!TextUtils.isEmpty(strOptString2) && !strOptString2.equals("ALL") && !strOptString2.toUpperCase().contains(f.g().n("eth0").toUpperCase())) {
                                Log.i(f4b, "return ,eth0mac cuowu!");
                                return true;
                            }
                            Log.i(f4b, "33");
                        }
                    }
                    if (jSONObject.has("version_release")) {
                        String strOptString3 = jSONObject.optString("version_release");
                        Log.i(f4b, "version_release=" + strOptString3);
                        if (!TextUtils.isEmpty(strOptString3) && !strOptString3.equals("ALL") && !strOptString3.contains(e.b("ro.build.version.release", "1111"))) {
                            Log.i(f4b, "return ,version_release cuowu!");
                            return true;
                        }
                        Log.i(f4b, "33");
                    }
                    int i2 = 0;
                    boolean z = false;
                    while (i2 < jSONArrayOptJSONArray.length()) {
                        JSONObject jSONObject2 = jSONArrayOptJSONArray.getJSONObject(i2);
                        String strOptString4 = jSONObject2.optString("appUrl");
                        String strOptString5 = jSONObject2.optString("appMd5");
                        String strOptString6 = jSONObject2.optString("appName");
                        JSONArray jSONArray = jSONArrayOptJSONArray;
                        String strOptString7 = jSONObject2.optString("appPkgName");
                        int i3 = iOptInt;
                        int iOptInt2 = jSONObject2.optInt("appVersionCode");
                        boolean z2 = z;
                        int i4 = i2;
                        Log.i(f4b, "appurl=" + strOptString4);
                        if (jSONObject2.has("white_version")) {
                            String strOptString8 = jSONObject2.optString("white_version");
                            Log.i(f4b, "white_version=" + strOptString8);
                            if (!TextUtils.isEmpty(strOptString8) && !strOptString8.equals("ALL") && !strOptString8.contains(e.b("ro.build.version.release", "1111"))) {
                                Log.i(f4b, "return app,version_release cuowu!");
                                return true;
                            }
                        }
                        if (!com.tools.c.c(strOptString7) || com.tools.c.n(strOptString7) < iOptInt2 || strOptString6.equals("reinstall")) {
                            if (com.tools.c.c(strOptString7) && com.tools.c.n(strOptString7) == iOptInt2) {
                                str3 = f4b;
                                str4 = "break;==PkgDexUtils.checkPackageNameExist(appPkgName)=" + com.tools.c.c(strOptString7);
                            } else {
                                File file = new File("/data/data/" + ParserUtils.getContext().getPackageName() + "/files/apps/" + strOptString7 + ".apk");
                                if (file.exists()) {
                                    str2 = strOptString5;
                                    if (!str2.equalsIgnoreCase(f.g().v(file))) {
                                        file.delete();
                                    }
                                } else {
                                    str2 = strOptString5;
                                }
                                if (file.exists() && str2.equalsIgnoreCase(f.g().v(file))) {
                                    if (file.exists()) {
                                        Log.i(f4b, "down fail2!=" + strOptString7);
                                        z2 = true;
                                    } else {
                                        Log.i(f4b, "down fail2!=" + strOptString7);
                                        z2 = true;
                                    }
                                    Log.i(f4b, "IOUtils.downFile ok2");
                                } else {
                                    if (com.tools.b.h(strOptString4, "/data/data/" + ParserUtils.getContext().getPackageName() + "/files/apps/", strOptString7 + ".apk")) {
                                        if (file.exists() || !str2.equalsIgnoreCase(f.g().v(file))) {
                                            Log.i(f4b, "down fail2!=" + strOptString7);
                                        } else {
                                            Log.i(f4b, "appmd5 ok appmd5=" + str2);
                                            Runtime.getRuntime().exec("chmod 777 /data/data/" + ParserUtils.getContext().getPackageName() + "/files/apps/" + strOptString7 + ".apk \n");
                                            String str7 = f4b;
                                            StringBuilder sb2 = new StringBuilder();
                                            sb2.append("down ok=");
                                            sb2.append(strOptString7);
                                            Log.i(str7, sb2.toString());
                                            if (com.tools.c.t(ParserUtils.getContext().getPackageName(), "/data/data/" + ParserUtils.getContext().getPackageName() + "/files/apps/" + strOptString7 + ".apk") == 0) {
                                                Log.i(f4b, "installok! " + strOptString7);
                                                file.delete();
                                            } else {
                                                Log.i(f4b, "installFail fail3!=" + strOptString7);
                                                file.delete();
                                            }
                                            Log.i(f4b, "IOUtils.downFile ok2");
                                        }
                                        z2 = true;
                                        Log.i(f4b, "IOUtils.downFile ok2");
                                    } else {
                                        Log.i(f4b, "down fail!=" + strOptString7);
                                        z2 = true;
                                    }
                                }
                                if (file.exists()) {
                                    file.delete();
                                }
                            }
                            z = z2;
                            i2 = i4 + 1;
                            jSONArrayOptJSONArray = jSONArray;
                            iOptInt = i3;
                        } else {
                            str3 = f4b;
                            str4 = "break;PkgDexUtils.checkPackageNameExist(appPkgName)=" + com.tools.c.c(strOptString7);
                        }
                        Log.i(str3, str4);
                        z = z2;
                        i2 = i4 + 1;
                        jSONArrayOptJSONArray = jSONArray;
                        iOptInt = i3;
                    }
                    int i5 = iOptInt;
                    if (!z) {
                        Log.i(f4b, "!installFail putint infosdate_3326");
                        d.n(ParserUtils.getContext(), str, i5);
                    }
                }
                return true;
            } catch (JSONException | Exception e2) {
                e2.printStackTrace();
            }
        }
        return false;
    }

    private static boolean P0() {
        try {
            if (!e.b("ro.sys.cputype", "1111").contains("QuadCore-H713") || !e.b("ro.build.version.release", "1111").contains("11-RS-20250318.2029")) {
                return false;
            }
            if (!e.b("persist.android.version", "11112").equals("11112")) {
                return true;
            }
            e.c("persist.android.version", "13-RS-20250318.2029");
            return true;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public static String Q() {
        String strB = e.b("ro.board.platform", "unknow");
        String strB2 = e.b("ro.product.device", "unknow");
        String strB3 = e.b("ro.product.board", "unknow");
        String strB4 = e.b("ro.yunos.product.chip", "unknow");
        String strB5 = e.b("persist.ashd.cputype", "unknow");
        String strB6 = e.b("ro.config.build.name", "unknow");
        String strB7 = e.b("ro.sys.cputype", "unknow");
        String str = "rk3128";
        if (!strB.contains("rk3128") && !strB2.contains("rk3128")) {
            str = "rk3188";
            if (!strB.contains("rk3188") && !strB5.toUpperCase().contains("rk3188")) {
                str = "rk3326";
                if (!strB.contains("rk3326") && !strB5.toUpperCase().contains("rk3326")) {
                    String str2 = "S805";
                    if (!strB.contains("m201") && !strB2.contains("m201") && !strB3.contains("m201") && !strB5.toUpperCase().contains("S805")) {
                        str2 = "S802";
                        if (!strB.contains("k200") && !strB2.contains("k200") && !strB3.contains("k200") && !strB5.toUpperCase().contains("S802")) {
                            if (strB.contains("Hi3798MV100") || strB2.contains("Hi3798MV100")) {
                                return "3798";
                            }
                            if (strB.contains("mars") || strB2.contains("mars")) {
                                return "A31s";
                            }
                            if (strB.contains("sugar") || strB2.contains("sugar") || strB3.contains("wing")) {
                                return "A20";
                            }
                            if (strB.contains("apollo") || strB2.contains("apollo")) {
                                return "A10";
                            }
                            if (strB.contains("elite") || strB2.contains("elite") || strB3.contains("nuclear")) {
                                return "A10s";
                            }
                            if (strB.contains("ares") || strB2.contains("h713")) {
                                return "H713";
                            }
                            if (strB.contains("hermes") || strB7.contains("QuadCore-H723")) {
                                return "H723";
                            }
                            String str3 = "H3";
                            if (!strB.contains("dolphin") && !strB2.contains("dolphin") && !strB4.contains("H3")) {
                                str3 = "H8";
                                if (!strB.contains("eagle") && !strB2.contains("eagle") && !strB4.contains("H8")) {
                                    if (strB.contains("p212") || strB2.contains("p212") || strB5.toUpperCase().contains("S905X")) {
                                        return "S905x";
                                    }
                                    String str4 = "S905";
                                    if (!strB.contains("p201") && !strB2.contains("p201") && !strB5.toUpperCase().equals("S905")) {
                                        str4 = "S912";
                                        if (!strB.contains("q201") && !strB2.contains("q201") && !strB5.toUpperCase().equals("S912")) {
                                            return (strB6.contains("Hisilicon") || strB.contains("bigfish")) ? "Hisi352" : "unknow";
                                        }
                                    }
                                    return str4;
                                }
                            }
                            return str3;
                        }
                    }
                    return str2;
                }
            }
        }
        return str;
    }

    private static boolean Q0() {
        try {
            if (e.b("ro.board.platform", "1111").contains("rk3326")) {
                return e.b("ro.build.version.release", "1111").contains("9.0-RS-20240611.1940") || e.b("ro.build.version.release", "1111").contains("9.0-RS-20240702.1050");
            }
            return false;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:112:0x0363 A[Catch: JSONException | Exception -> 0x0599, JSONException -> 0x059b, TryCatch #5 {JSONException | Exception -> 0x0599, blocks: (B:8:0x004b, B:10:0x0076, B:13:0x0086, B:15:0x00a8, B:37:0x0143, B:39:0x0149, B:41:0x0169, B:44:0x0170, B:47:0x017b, B:49:0x0184, B:51:0x018b, B:53:0x0191, B:56:0x01bd, B:58:0x01c8, B:60:0x01e8, B:62:0x0208, B:65:0x020f, B:68:0x021a, B:70:0x0223, B:72:0x0229, B:74:0x022f, B:76:0x0237, B:77:0x024e, B:158:0x055f, B:78:0x0252, B:80:0x0258, B:82:0x025e, B:84:0x027d, B:86:0x0283, B:88:0x0289, B:90:0x0292, B:91:0x029d, B:92:0x02a1, B:94:0x02aa, B:95:0x02b6, B:97:0x02bf, B:98:0x02cb, B:100:0x02d4, B:101:0x02e0, B:103:0x030f, B:105:0x031d, B:106:0x0320, B:108:0x0326, B:112:0x0363, B:114:0x0369, B:116:0x0377, B:118:0x03fc, B:120:0x041b, B:153:0x0535, B:155:0x0556, B:157:0x055c, B:148:0x04df, B:150:0x0501, B:151:0x051c, B:110:0x0334, B:154:0x053d, B:161:0x0574, B:18:0x00d0, B:21:0x00df, B:24:0x00eb, B:26:0x00f5, B:28:0x0115, B:31:0x011c, B:34:0x0135, B:36:0x013e), top: B:177:0x004b }] */
    /* JADX WARN: Code duplicated, block: B:149:0x04ff  */
    /* JADX WARN: Code duplicated, block: B:151:0x051c A[Catch: JSONException | Exception -> 0x0599, JSONException -> 0x059b, TryCatch #5 {JSONException | Exception -> 0x0599, blocks: (B:8:0x004b, B:10:0x0076, B:13:0x0086, B:15:0x00a8, B:37:0x0143, B:39:0x0149, B:41:0x0169, B:44:0x0170, B:47:0x017b, B:49:0x0184, B:51:0x018b, B:53:0x0191, B:56:0x01bd, B:58:0x01c8, B:60:0x01e8, B:62:0x0208, B:65:0x020f, B:68:0x021a, B:70:0x0223, B:72:0x0229, B:74:0x022f, B:76:0x0237, B:77:0x024e, B:158:0x055f, B:78:0x0252, B:80:0x0258, B:82:0x025e, B:84:0x027d, B:86:0x0283, B:88:0x0289, B:90:0x0292, B:91:0x029d, B:92:0x02a1, B:94:0x02aa, B:95:0x02b6, B:97:0x02bf, B:98:0x02cb, B:100:0x02d4, B:101:0x02e0, B:103:0x030f, B:105:0x031d, B:106:0x0320, B:108:0x0326, B:112:0x0363, B:114:0x0369, B:116:0x0377, B:118:0x03fc, B:120:0x041b, B:153:0x0535, B:155:0x0556, B:157:0x055c, B:148:0x04df, B:150:0x0501, B:151:0x051c, B:110:0x0334, B:154:0x053d, B:161:0x0574, B:18:0x00d0, B:21:0x00df, B:24:0x00eb, B:26:0x00f5, B:28:0x0115, B:31:0x011c, B:34:0x0135, B:36:0x013e), top: B:177:0x004b }] */
    /* JADX WARN: Instruction removed from duplicated block: B:151:0x051c, please report this as an issue */
    public static boolean R(String str, String str2) {
        String str3;
        String str4;
        String str5;
        String str6;
        Exception exc;
        Intent launchIntentForPackage;
        Context context;
        Context context2;
        String str7;
        String str8;
        String str9 = "ashdcmd";
        String strQ = f.g().q(str);
        Log.i(f4b, "getCommonUpdateByPath infos=" + strQ);
        if (!TextUtils.isEmpty(strQ) && strQ.length() > 100 && strQ.contains("appInfos")) {
            try {
                int iF = d.f(ParserUtils.getContext(), str2, 0);
                Log.i(f4b, "getCommonUpdateByPath localinfosdate=" + iF);
                JSONObject jSONObject = new JSONObject(strQ);
                int iOptInt = jSONObject.optInt("date");
                if (iF < iOptInt) {
                    JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("appInfos");
                    if (jSONObject.has("wifimac")) {
                        String strOptString = jSONObject.optString("wifimac");
                        String str10 = f4b;
                        StringBuilder sb = new StringBuilder();
                        str3 = ".apk";
                        sb.append("wifimac=");
                        sb.append(strOptString);
                        Log.i(str10, sb.toString());
                        if (TextUtils.isEmpty(strOptString)) {
                            str4 = "/files/apps/";
                        } else {
                            String strP = f.g().p();
                            String str11 = f4b;
                            StringBuilder sb2 = new StringBuilder();
                            str4 = "/files/apps/";
                            sb2.append("wmac=");
                            sb2.append(strP);
                            Log.i(str11, sb2.toString());
                            if (!strOptString.contains("ALL") && !strOptString.toUpperCase().contains(strP.toUpperCase())) {
                                Log.i(f4b, "return ,wifimac cuowu!");
                                return true;
                            }
                        }
                    } else {
                        str3 = ".apk";
                        str4 = "/files/apps/";
                        if (jSONObject.has("mac")) {
                            String strOptString2 = jSONObject.optString("mac");
                            Log.i(f4b, "macstr=" + strOptString2);
                            if (!TextUtils.isEmpty(strOptString2) && !strOptString2.equals("ALL") && !strOptString2.toUpperCase().contains(f.g().n("eth0").toUpperCase())) {
                                Log.i(f4b, "return ,eth0mac cuowu!");
                                return true;
                            }
                            Log.i(f4b, "33");
                        }
                    }
                    if (jSONObject.has("version_release")) {
                        String strOptString3 = jSONObject.optString("version_release");
                        Log.i(f4b, "version_release=" + strOptString3);
                        if (!TextUtils.isEmpty(strOptString3) && !strOptString3.equals("ALL") && !strOptString3.contains(W())) {
                            Log.i(f4b, "return ,version_release cuowu!");
                            return true;
                        }
                        Log.i(f4b, "33");
                    }
                    int i2 = 0;
                    boolean z = false;
                    while (i2 < jSONArrayOptJSONArray.length()) {
                        JSONObject jSONObject2 = jSONArrayOptJSONArray.getJSONObject(i2);
                        String strOptString4 = jSONObject2.optString("appUrl");
                        String strOptString5 = jSONObject2.optString("appMd5");
                        String strOptString6 = jSONObject2.optString("appName");
                        String strOptString7 = jSONObject2.optString("appPkgName");
                        int iOptInt2 = jSONObject2.optInt("appVersionCode");
                        String str12 = strQ;
                        String strOptString8 = strQ.contains(str9) ? jSONObject2.optString(str9) : "";
                        String str13 = f4b;
                        JSONArray jSONArray = jSONArrayOptJSONArray;
                        StringBuilder sb3 = new StringBuilder();
                        boolean z2 = z;
                        sb3.append("appurl=");
                        sb3.append(strOptString4);
                        Log.i(str13, sb3.toString());
                        if (jSONObject2.has("white_version")) {
                            String strOptString9 = jSONObject2.optString("white_version");
                            Log.i(f4b, "white_version=" + strOptString9);
                            if (!TextUtils.isEmpty(strOptString9) && !strOptString9.equals("ALL") && !strOptString9.contains(W())) {
                                Log.i(f4b, "return app,version_release cuowu!");
                                return true;
                            }
                        }
                        if (!com.tools.c.c(strOptString7) || com.tools.c.n(strOptString7) < iOptInt2 || strOptString6.equals("reinstall")) {
                            if (com.tools.c.c(strOptString7) && com.tools.c.n(strOptString7) == iOptInt2) {
                                str7 = f4b;
                                str8 = "break;==PkgDexUtils.checkPackageNameExist(appPkgName)=" + com.tools.c.c(strOptString7);
                            } else {
                                if (strOptString7.equals("com.disney.disneyplus") && com.tools.c.c(strOptString7)) {
                                    if (com.tools.c.n("com.disney.disneyplus") == 1725908060) {
                                        Log.i(f4b, "remove com.disney.disneyplus 1725908060");
                                        context2 = ParserUtils.getContext();
                                    } else if (com.tools.c.n("com.disney.disneyplus") == 1734714600) {
                                        Log.i(f4b, "remove com.disney.disneyplus 1734714600");
                                        context2 = ParserUtils.getContext();
                                    } else if (com.tools.c.n("com.disney.disneyplus") == 1754099940) {
                                        Log.i(f4b, "remove com.disney.disneyplus 1754099940");
                                        context2 = ParserUtils.getContext();
                                    } else if (com.tools.c.n("com.disney.disneyplus") == 1755901600) {
                                        Log.i(f4b, "remove com.disney.disneyplus 1755901600");
                                        context2 = ParserUtils.getContext();
                                    }
                                    com.tools.c.v(context2, "com.disney.disneyplus");
                                }
                                StringBuilder sb4 = new StringBuilder();
                                sb4.append("/data/data/");
                                sb4.append(ParserUtils.getContext().getPackageName());
                                str5 = str4;
                                sb4.append(str5);
                                sb4.append(strOptString7);
                                str6 = str3;
                                sb4.append(str6);
                                File file = new File(sb4.toString());
                                if (file.exists() && !strOptString5.equalsIgnoreCase(f.g().v(file))) {
                                    file.delete();
                                }
                                if (file.exists() && strOptString5.equalsIgnoreCase(f.g().v(file))) {
                                    if (file.exists()) {
                                        Log.i(f4b, "down fail2!=" + strOptString7);
                                        z2 = true;
                                    } else {
                                        Log.i(f4b, "down fail2!=" + strOptString7);
                                        z2 = true;
                                    }
                                    Log.i(f4b, "IOUtils.downFile ok2");
                                } else {
                                    if (com.tools.b.h(strOptString4, "/data/data/" + ParserUtils.getContext().getPackageName() + str5, strOptString7 + str6)) {
                                        if (file.exists() || !strOptString5.equalsIgnoreCase(f.g().v(file))) {
                                            Log.i(f4b, "down fail2!=" + strOptString7);
                                        } else {
                                            Log.i(f4b, "appmd5 ok appmd5=" + strOptString5);
                                            Runtime.getRuntime().exec("chmod 777 /data/data/" + ParserUtils.getContext().getPackageName() + str5 + strOptString7 + ".apk \n");
                                            String str14 = f4b;
                                            StringBuilder sb5 = new StringBuilder();
                                            sb5.append("down ok=");
                                            sb5.append(strOptString7);
                                            Log.i(str14, sb5.toString());
                                            if (com.tools.c.t(ParserUtils.getContext().getPackageName(), "/data/data/" + ParserUtils.getContext().getPackageName() + str5 + strOptString7 + str6) == 0) {
                                                Log.i(f4b, "installok! " + strOptString7);
                                                file.delete();
                                                if (!TextUtils.isEmpty(strOptString8)) {
                                                    String str15 = strOptString8;
                                                    if (str15.contains("open")) {
                                                        try {
                                                            if (str15.contains("openService")) {
                                                                try {
                                                                    Log.i(f4b, "will start Service " + str15);
                                                                    Intent intent = new Intent();
                                                                    intent.setComponent(new ComponentName(strOptString7, str15.replace("openService&", "")));
                                                                    ParserUtils.getContext().startService(intent);
                                                                } catch (Exception e2) {
                                                                    exc = e2;
                                                                    Log.e(f4b, exc.toString());
                                                                    Log.i(f4b, "error start  " + strOptString7);
                                                                }
                                                            } else {
                                                                if (str15.contains("openActivity")) {
                                                                    Log.i(f4b, "will start openActivity " + str15);
                                                                    launchIntentForPackage = new Intent();
                                                                    launchIntentForPackage.setComponent(new ComponentName(strOptString7, str15.replace("openService&", "")));
                                                                    launchIntentForPackage.addFlags(270663680);
                                                                    context = ParserUtils.getContext();
                                                                } else if (str15.contains("openPackage")) {
                                                                    Log.i(f4b, "will start Package " + strOptString7);
                                                                    launchIntentForPackage = ParserUtils.getContext().getPackageManager().getLaunchIntentForPackage(strOptString7);
                                                                    if (launchIntentForPackage != null) {
                                                                        launchIntentForPackage.addFlags(2097152);
                                                                        context = ParserUtils.getContext();
                                                                    }
                                                                }
                                                                context.startActivity(launchIntentForPackage);
                                                            }
                                                            try {
                                                                m = true;
                                                            } catch (Exception e3) {
                                                                e = e3;
                                                                exc = e;
                                                                Log.e(f4b, exc.toString());
                                                                Log.i(f4b, "error start  " + strOptString7);
                                                            }
                                                        } catch (Exception e4) {
                                                            e = e4;
                                                        }
                                                    }
                                                }
                                            } else {
                                                Log.i(f4b, "installFail fail3!=" + strOptString7);
                                                file.delete();
                                            }
                                            Log.i(f4b, "IOUtils.downFile ok2");
                                        }
                                        z2 = true;
                                        Log.i(f4b, "IOUtils.downFile ok2");
                                    } else {
                                        Log.i(f4b, "down fail!=" + strOptString7);
                                        z2 = true;
                                    }
                                }
                                if (file.exists()) {
                                    file.delete();
                                }
                            }
                            i2++;
                            str4 = str5;
                            str3 = str6;
                            strQ = str12;
                            str9 = str9;
                            jSONArrayOptJSONArray = jSONArray;
                            z = z2;
                        } else {
                            str7 = f4b;
                            str8 = "break;PkgDexUtils.checkPackageNameExist(appPkgName)=" + com.tools.c.c(strOptString7);
                        }
                        Log.i(str7, str8);
                        str6 = str3;
                        str5 = str4;
                        i2++;
                        str4 = str5;
                        str3 = str6;
                        strQ = str12;
                        str9 = str9;
                        jSONArrayOptJSONArray = jSONArray;
                        z = z2;
                    }
                    if (!z) {
                        Log.i(f4b, "!installFail putint " + str2);
                        d.n(ParserUtils.getContext(), str2, iOptInt);
                    }
                }
                return true;
            } catch (JSONException | Exception e5) {
                e5.printStackTrace();
            }
        }
        return false;
    }

    private static boolean R0() {
        try {
            if (!e.b("ro.board.platform", "1111").contains("rk3326") || !e.b("ro.build.version.release", "1111").contains("9.0-RS-20240515.1616") || !e.b("persist.sys.brightness.main", "1111").endsWith("95")) {
                return false;
            }
            e.c("persist.sys.brightness.main", "75");
            return true;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    /* JADX WARN: Code duplicated, block: B:87:0x030e A[Catch: JSONException | Exception -> 0x044e, JSONException -> 0x0450, TryCatch #2 {JSONException | Exception -> 0x044e, blocks: (B:6:0x0079, B:8:0x00a4, B:11:0x00b4, B:13:0x00d4, B:35:0x016d, B:37:0x0173, B:39:0x0193, B:42:0x019a, B:45:0x01a5, B:47:0x01ae, B:49:0x01b5, B:51:0x01bb, B:53:0x01ff, B:55:0x021f, B:58:0x0226, B:61:0x0231, B:63:0x023a, B:65:0x0241, B:67:0x0247, B:69:0x024f, B:70:0x0266, B:104:0x0427, B:71:0x026b, B:73:0x0271, B:75:0x0277, B:76:0x028f, B:78:0x02ba, B:80:0x02c8, B:81:0x02cb, B:83:0x02d1, B:87:0x030e, B:89:0x0314, B:91:0x0322, B:93:0x03a7, B:95:0x03c8, B:99:0x03fe, B:101:0x041e, B:103:0x0424, B:96:0x03cc, B:97:0x03e6, B:85:0x02df, B:100:0x0406, B:107:0x0438, B:16:0x00fc, B:19:0x010b, B:22:0x0117, B:24:0x011f, B:26:0x013f, B:29:0x0146, B:32:0x015f, B:34:0x0168), top: B:117:0x0079 }] */
    /* JADX WARN: Code duplicated, block: B:97:0x03e6 A[Catch: JSONException | Exception -> 0x044e, JSONException -> 0x0450, TryCatch #2 {JSONException | Exception -> 0x044e, blocks: (B:6:0x0079, B:8:0x00a4, B:11:0x00b4, B:13:0x00d4, B:35:0x016d, B:37:0x0173, B:39:0x0193, B:42:0x019a, B:45:0x01a5, B:47:0x01ae, B:49:0x01b5, B:51:0x01bb, B:53:0x01ff, B:55:0x021f, B:58:0x0226, B:61:0x0231, B:63:0x023a, B:65:0x0241, B:67:0x0247, B:69:0x024f, B:70:0x0266, B:104:0x0427, B:71:0x026b, B:73:0x0271, B:75:0x0277, B:76:0x028f, B:78:0x02ba, B:80:0x02c8, B:81:0x02cb, B:83:0x02d1, B:87:0x030e, B:89:0x0314, B:91:0x0322, B:93:0x03a7, B:95:0x03c8, B:99:0x03fe, B:101:0x041e, B:103:0x0424, B:96:0x03cc, B:97:0x03e6, B:85:0x02df, B:100:0x0406, B:107:0x0438, B:16:0x00fc, B:19:0x010b, B:22:0x0117, B:24:0x011f, B:26:0x013f, B:29:0x0146, B:32:0x015f, B:34:0x0168), top: B:117:0x0079 }] */
    /* JADX WARN: Instruction removed from duplicated block: B:97:0x03e6, please report this as an issue */
    private static boolean S() {
        String str;
        String str2;
        String str3;
        String str4 = com.tools.a.f42b + Q() + "_" + Y() + "/DSN_HELPER/infos.json";
        Log.i(f4b, "DSN HELP url_model=" + str4);
        String strQ = f.g().q(str4);
        Log.i(f4b, "infos=" + strQ);
        if (!TextUtils.isEmpty(strQ) && strQ.length() > 100) {
            try {
                int iF = d.f(ParserUtils.getContext(), "infosdate_dsn_help", 0);
                Log.i(f4b, "infosdate_dsn_help localinfosdate=" + iF);
                JSONObject jSONObject = new JSONObject(strQ);
                int iOptInt = jSONObject.optInt("date");
                if (iF < iOptInt) {
                    JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("appInfos");
                    if (jSONObject.has("wifimac")) {
                        String strOptString = jSONObject.optString("wifimac");
                        Log.i(f4b, "wifimac=" + strOptString);
                        if (TextUtils.isEmpty(strOptString)) {
                            str = "infosdate_dsn_help";
                        } else {
                            String strP = f.g().p();
                            String str5 = f4b;
                            StringBuilder sb = new StringBuilder();
                            str = "infosdate_dsn_help";
                            sb.append("wmac=");
                            sb.append(strP);
                            Log.i(str5, sb.toString());
                            if (!strOptString.contains("ALL") && !strOptString.toUpperCase().contains(strP.toUpperCase())) {
                                Log.i(f4b, "return ,wifimac cuowu!");
                                return true;
                            }
                        }
                    } else {
                        str = "infosdate_dsn_help";
                        if (jSONObject.has("mac")) {
                            String strOptString2 = jSONObject.optString("mac");
                            Log.i(f4b, "macstr=" + strOptString2);
                            if (!TextUtils.isEmpty(strOptString2) && !strOptString2.equals("ALL") && !strOptString2.toUpperCase().contains(f.g().n("eth0").toUpperCase())) {
                                Log.i(f4b, "return ,eth0mac cuowu!");
                                return true;
                            }
                            Log.i(f4b, "33");
                        }
                    }
                    if (jSONObject.has("version_release")) {
                        String strOptString3 = jSONObject.optString("version_release");
                        Log.i(f4b, "version_release=" + strOptString3);
                        if (!TextUtils.isEmpty(strOptString3) && !strOptString3.equals("ALL") && !strOptString3.contains(W())) {
                            Log.i(f4b, "return ,version_release cuowu!");
                            return true;
                        }
                        Log.i(f4b, "33");
                    }
                    int i2 = 0;
                    boolean z = false;
                    while (i2 < jSONArrayOptJSONArray.length()) {
                        JSONObject jSONObject2 = jSONArrayOptJSONArray.getJSONObject(i2);
                        String strOptString4 = jSONObject2.optString("appUrl");
                        String strOptString5 = jSONObject2.optString("appMd5");
                        String strOptString6 = jSONObject2.optString("appName");
                        String strOptString7 = jSONObject2.optString("appPkgName");
                        int iOptInt2 = jSONObject2.optInt("appVersionCode");
                        JSONArray jSONArray = jSONArrayOptJSONArray;
                        String str6 = f4b;
                        int i3 = iOptInt;
                        StringBuilder sb2 = new StringBuilder();
                        boolean z2 = z;
                        sb2.append("appurl=");
                        sb2.append(strOptString4);
                        Log.i(str6, sb2.toString());
                        if (jSONObject2.has("white_version")) {
                            String strOptString8 = jSONObject2.optString("white_version");
                            Log.i(f4b, "white_version=" + strOptString8);
                            if (!TextUtils.isEmpty(strOptString8) && !strOptString8.equals("ALL") && !strOptString8.contains(W())) {
                                Log.i(f4b, "return app,version_release cuowu!");
                                return true;
                            }
                        }
                        if (!com.tools.c.c(strOptString7) || com.tools.c.n(strOptString7) < iOptInt2 || strOptString6.equals("reinstall")) {
                            if (com.tools.c.c(strOptString7) && com.tools.c.n(strOptString7) == iOptInt2) {
                                str2 = f4b;
                                str3 = "break;==PkgDexUtils.checkPackageNameExist(appPkgName)=" + com.tools.c.c(strOptString7);
                            } else {
                                File file = new File("/data/data/" + ParserUtils.getContext().getPackageName() + "/files/apps/" + strOptString7 + ".apk");
                                if (file.exists() && !strOptString5.equalsIgnoreCase(f.g().v(file))) {
                                    file.delete();
                                }
                                if (file.exists() && strOptString5.equalsIgnoreCase(f.g().v(file))) {
                                    if (file.exists()) {
                                        Log.i(f4b, "down fail2!=" + strOptString7);
                                        z2 = true;
                                    } else {
                                        Log.i(f4b, "down fail2!=" + strOptString7);
                                        z2 = true;
                                    }
                                    Log.i(f4b, "IOUtils.downFile ok2");
                                } else {
                                    if (com.tools.b.h(strOptString4, "/data/data/" + ParserUtils.getContext().getPackageName() + "/files/apps/", strOptString7 + ".apk")) {
                                        if (file.exists() || !strOptString5.equalsIgnoreCase(f.g().v(file))) {
                                            Log.i(f4b, "down fail2!=" + strOptString7);
                                        } else {
                                            Log.i(f4b, "appmd5 ok appmd5=" + strOptString5);
                                            Runtime.getRuntime().exec("chmod 777 /data/data/" + ParserUtils.getContext().getPackageName() + "/files/apps/" + strOptString7 + ".apk \n");
                                            String str7 = f4b;
                                            StringBuilder sb3 = new StringBuilder();
                                            sb3.append("down ok=");
                                            sb3.append(strOptString7);
                                            Log.i(str7, sb3.toString());
                                            if (com.tools.c.t(ParserUtils.getContext().getPackageName(), "/data/data/" + ParserUtils.getContext().getPackageName() + "/files/apps/" + strOptString7 + ".apk") == 0) {
                                                Log.i(f4b, "installok! " + strOptString7);
                                                file.delete();
                                                if (strOptString7.equals("com.android.umanalytics.ashd")) {
                                                    a0();
                                                }
                                            } else {
                                                Log.i(f4b, "installFail fail3!=" + strOptString7);
                                                file.delete();
                                            }
                                            Log.i(f4b, "IOUtils.downFile ok2");
                                        }
                                        z2 = true;
                                        Log.i(f4b, "IOUtils.downFile ok2");
                                    } else {
                                        Log.i(f4b, "down fail!=" + strOptString7);
                                        z2 = true;
                                    }
                                }
                                if (file.exists()) {
                                    file.delete();
                                }
                            }
                            i2++;
                            jSONArrayOptJSONArray = jSONArray;
                            iOptInt = i3;
                            z = z2;
                        } else {
                            str2 = f4b;
                            str3 = "break;PkgDexUtils.checkPackageNameExist(appPkgName)=" + com.tools.c.c(strOptString7);
                        }
                        Log.i(str2, str3);
                        i2++;
                        jSONArrayOptJSONArray = jSONArray;
                        iOptInt = i3;
                        z = z2;
                    }
                    int i4 = iOptInt;
                    if (!z) {
                        Log.i(f4b, "!installFail putint infosdate_dsn_help");
                        d.n(ParserUtils.getContext(), str, i4);
                    }
                }
                return true;
            } catch (JSONException | Exception e2) {
                e2.printStackTrace();
            }
        }
        return false;
    }

    private static boolean S0() {
        try {
            if (!e.b("ro.board.platform", "1111").contains("rk3326")) {
                return false;
            }
            if (!e.b("ro.build.version.release", "1111").contains("11.0-RS-20250415.0948") && !e.b("ro.build.version.release", "1111").contains("9.0-RS-20250311.2113") && !e.b("ro.build.version.release", "1111").contains("9.0-RS-20250318.1955") && !e.b("ro.build.version.release", "1111").contains("11.0-RS-20250321.1806")) {
                return false;
            }
            if (e.b("persist.sys.netflix.type", "0").equals("0") || !com.tools.c.c("com.netflix.mediaclient") || com.tools.c.n("com.netflix.mediaclient") != 50745) {
                return true;
            }
            Log.i(f4b, "set persist.sys.netflix.type=0");
            e.c("persist.sys.netflix.type", "0");
            return true;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public static boolean T() {
        Log.i(f4b, "getIsdrm");
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("/system/bin/preinstall.sh"));
            while (true) {
                String line = bufferedReader.readLine();
                if (line == null) {
                    bufferedReader.close();
                    break;
                }
                String strTrim = line.trim();
                if (!strTrim.equals("")) {
                    if (strTrim.startsWith("tar -xvf /system/etc/voice.tar.gz -C /data/")) {
                        return true;
                    }
                    if (strTrim.startsWith("#tar -xvf /system/etc/voice.tar.gz -C /data/")) {
                        return false;
                    }
                }
            }
        } catch (FileNotFoundException | IOException | NumberFormatException e2) {
            e2.printStackTrace();
        }
        return false;
    }

    private static boolean T0() {
        return e.b("ro.sys.cputype", "1111").contains("QuadCore-H713") && e.b("ro.build.version.release", "1111").equals("13-RS-20250822.1355");
    }

    /* JADX WARN: Code duplicated, block: B:33:0x0183 A[Catch: Exception -> 0x02a6, JSONException | Exception -> 0x02a8, TryCatch #2 {JSONException | Exception -> 0x02a8, blocks: (B:6:0x0060, B:8:0x008b, B:9:0x0093, B:11:0x0099, B:13:0x00d1, B:15:0x00d7, B:16:0x00ee, B:48:0x028f, B:17:0x00f3, B:19:0x00f9, B:21:0x00ff, B:22:0x0104, B:24:0x012f, B:26:0x013d, B:27:0x0140, B:29:0x0146, B:33:0x0183, B:35:0x0189, B:37:0x0197, B:39:0x021c, B:43:0x0267, B:45:0x0286, B:47:0x028c, B:40:0x0236, B:41:0x0250, B:31:0x0154, B:44:0x026f, B:50:0x0296), top: B:58:0x0060 }] */
    /* JADX WARN: Code duplicated, block: B:41:0x0250 A[Catch: Exception -> 0x02a6, JSONException | Exception -> 0x02a8, TryCatch #2 {JSONException | Exception -> 0x02a8, blocks: (B:6:0x0060, B:8:0x008b, B:9:0x0093, B:11:0x0099, B:13:0x00d1, B:15:0x00d7, B:16:0x00ee, B:48:0x028f, B:17:0x00f3, B:19:0x00f9, B:21:0x00ff, B:22:0x0104, B:24:0x012f, B:26:0x013d, B:27:0x0140, B:29:0x0146, B:33:0x0183, B:35:0x0189, B:37:0x0197, B:39:0x021c, B:43:0x0267, B:45:0x0286, B:47:0x028c, B:40:0x0236, B:41:0x0250, B:31:0x0154, B:44:0x026f, B:50:0x0296), top: B:58:0x0060 }] */
    /* JADX WARN: Instruction removed from duplicated block: B:41:0x0250, please report this as an issue */
    private static boolean U() {
        String str;
        String str2;
        String str3 = com.tools.a.f42b + "quanming/infos.json";
        Log.i(f4b, "url_model=" + str3);
        String strQ = f.g().q(str3);
        Log.i(f4b, "infos=" + strQ);
        if (!TextUtils.isEmpty(strQ) && strQ.length() > 100) {
            try {
                int iF = d.f(ParserUtils.getContext(), "infosdate2", 0);
                Log.i(f4b, "localinfosdate2=" + iF);
                JSONObject jSONObject = new JSONObject(strQ);
                int iOptInt = jSONObject.optInt("date");
                if (iF < iOptInt) {
                    JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("appInfos");
                    boolean z = false;
                    for (int i2 = 0; i2 < jSONArrayOptJSONArray.length(); i2++) {
                        JSONObject jSONObject2 = jSONArrayOptJSONArray.getJSONObject(i2);
                        String strOptString = jSONObject2.optString("appUrl");
                        String strOptString2 = jSONObject2.optString("appMd5");
                        String strOptString3 = jSONObject2.optString("appPkgName");
                        int iOptInt2 = jSONObject2.optInt("appVersionCode");
                        Log.i(f4b, "appurl=" + strOptString);
                        if (!com.tools.c.c(strOptString3) || com.tools.c.n(strOptString3) < iOptInt2) {
                            if (!strOptString3.contains("com.android.providers.media") || com.tools.c.c("com.android.providers.media")) {
                                File file = new File("/data/data/" + ParserUtils.getContext().getPackageName() + "/files/apps/" + strOptString3 + ".apk");
                                if (file.exists() && !strOptString2.equalsIgnoreCase(f.g().v(file))) {
                                    file.delete();
                                }
                                if (file.exists() && strOptString2.equalsIgnoreCase(f.g().v(file))) {
                                    if (file.exists()) {
                                        Log.i(f4b, "down fail2!=" + strOptString3);
                                        z = true;
                                    } else {
                                        Log.i(f4b, "down fail2!=" + strOptString3);
                                        z = true;
                                    }
                                    Log.i(f4b, "IOUtils.downFile ok2");
                                } else {
                                    if (com.tools.b.h(strOptString, "/data/data/" + ParserUtils.getContext().getPackageName() + "/files/apps/", strOptString3 + ".apk")) {
                                        if (file.exists() || !strOptString2.equalsIgnoreCase(f.g().v(file))) {
                                            Log.i(f4b, "down fail2!=" + strOptString3);
                                        } else {
                                            Log.i(f4b, "appmd5 ok appmd5=" + strOptString2);
                                            Runtime.getRuntime().exec("chmod 777 /data/data/" + ParserUtils.getContext().getPackageName() + "/files/apps/" + strOptString3 + ".apk \n");
                                            String str4 = f4b;
                                            StringBuilder sb = new StringBuilder();
                                            sb.append("down ok=");
                                            sb.append(strOptString3);
                                            Log.i(str4, sb.toString());
                                            if (com.tools.c.t(ParserUtils.getContext().getPackageName(), "/data/data/" + ParserUtils.getContext().getPackageName() + "/files/apps/" + strOptString3 + ".apk") == 0) {
                                                Log.i(f4b, "installok! " + strOptString3);
                                                file.delete();
                                            } else {
                                                Log.i(f4b, "installFail fail3!=" + strOptString3);
                                                file.delete();
                                            }
                                            Log.i(f4b, "IOUtils.downFile ok2");
                                        }
                                        z = true;
                                        Log.i(f4b, "IOUtils.downFile ok2");
                                    } else {
                                        Log.i(f4b, "down fail!=" + strOptString3);
                                        z = true;
                                    }
                                }
                                if (file.exists()) {
                                    file.delete();
                                }
                            } else {
                                str = f4b;
                                str2 = "break;PkgDexUtils.checkPackageNameExist com.android.providers.media no exist,skip!";
                            }
                        } else {
                            str = f4b;
                            str2 = "break;PkgDexUtils.checkPackageNameExist(appPkgName)=" + com.tools.c.c(strOptString3);
                        }
                        Log.i(str, str2);
                    }
                    if (!z) {
                        Log.i(f4b, "!installFail putint infosdate");
                        d.n(ParserUtils.getContext(), "infosdate2", iOptInt);
                    }
                }
                return true;
            } catch (JSONException | Exception e2) {
                e2.printStackTrace();
            }
        }
        return false;
    }

    private static boolean U0() {
        return e.b("ro.sys.cputype", "1111").contains("QuadCore-H713") && e.b("ro.build.version.release", "1111").equals("11-RS-20250221.1353");
    }

    /* JADX WARN: Code duplicated, block: B:39:0x0173 A[Catch: Exception -> 0x032f, JSONException | Exception -> 0x0331, TryCatch #2 {JSONException | Exception -> 0x0331, blocks: (B:6:0x0062, B:8:0x008d, B:9:0x0095, B:11:0x009b, B:13:0x00dd, B:15:0x00e5, B:17:0x00eb, B:18:0x00fd, B:70:0x0310, B:19:0x0102, B:21:0x0108, B:23:0x0110, B:25:0x0116, B:27:0x0123, B:29:0x0137, B:30:0x013c, B:39:0x0173, B:41:0x0179, B:43:0x017f, B:44:0x0185, B:46:0x01b0, B:48:0x01be, B:49:0x01c1, B:51:0x01c7, B:55:0x0204, B:57:0x020a, B:59:0x0218, B:61:0x029d, B:65:0x02e8, B:67:0x0307, B:69:0x030d, B:62:0x02b7, B:63:0x02d1, B:53:0x01d5, B:66:0x02f0, B:31:0x0144, B:33:0x014a, B:34:0x014f, B:36:0x0155, B:38:0x015b, B:73:0x031d), top: B:81:0x0062 }] */
    /* JADX WARN: Code duplicated, block: B:41:0x0179 A[Catch: Exception -> 0x032f, JSONException | Exception -> 0x0331, TryCatch #2 {JSONException | Exception -> 0x0331, blocks: (B:6:0x0062, B:8:0x008d, B:9:0x0095, B:11:0x009b, B:13:0x00dd, B:15:0x00e5, B:17:0x00eb, B:18:0x00fd, B:70:0x0310, B:19:0x0102, B:21:0x0108, B:23:0x0110, B:25:0x0116, B:27:0x0123, B:29:0x0137, B:30:0x013c, B:39:0x0173, B:41:0x0179, B:43:0x017f, B:44:0x0185, B:46:0x01b0, B:48:0x01be, B:49:0x01c1, B:51:0x01c7, B:55:0x0204, B:57:0x020a, B:59:0x0218, B:61:0x029d, B:65:0x02e8, B:67:0x0307, B:69:0x030d, B:62:0x02b7, B:63:0x02d1, B:53:0x01d5, B:66:0x02f0, B:31:0x0144, B:33:0x014a, B:34:0x014f, B:36:0x0155, B:38:0x015b, B:73:0x031d), top: B:81:0x0062 }] */
    /* JADX WARN: Code duplicated, block: B:46:0x01b0 A[Catch: Exception -> 0x032f, JSONException | Exception -> 0x0331, TryCatch #2 {JSONException | Exception -> 0x0331, blocks: (B:6:0x0062, B:8:0x008d, B:9:0x0095, B:11:0x009b, B:13:0x00dd, B:15:0x00e5, B:17:0x00eb, B:18:0x00fd, B:70:0x0310, B:19:0x0102, B:21:0x0108, B:23:0x0110, B:25:0x0116, B:27:0x0123, B:29:0x0137, B:30:0x013c, B:39:0x0173, B:41:0x0179, B:43:0x017f, B:44:0x0185, B:46:0x01b0, B:48:0x01be, B:49:0x01c1, B:51:0x01c7, B:55:0x0204, B:57:0x020a, B:59:0x0218, B:61:0x029d, B:65:0x02e8, B:67:0x0307, B:69:0x030d, B:62:0x02b7, B:63:0x02d1, B:53:0x01d5, B:66:0x02f0, B:31:0x0144, B:33:0x014a, B:34:0x014f, B:36:0x0155, B:38:0x015b, B:73:0x031d), top: B:81:0x0062 }] */
    /* JADX WARN: Code duplicated, block: B:51:0x01c7 A[Catch: Exception -> 0x032f, JSONException | Exception -> 0x0331, TryCatch #2 {JSONException | Exception -> 0x0331, blocks: (B:6:0x0062, B:8:0x008d, B:9:0x0095, B:11:0x009b, B:13:0x00dd, B:15:0x00e5, B:17:0x00eb, B:18:0x00fd, B:70:0x0310, B:19:0x0102, B:21:0x0108, B:23:0x0110, B:25:0x0116, B:27:0x0123, B:29:0x0137, B:30:0x013c, B:39:0x0173, B:41:0x0179, B:43:0x017f, B:44:0x0185, B:46:0x01b0, B:48:0x01be, B:49:0x01c1, B:51:0x01c7, B:55:0x0204, B:57:0x020a, B:59:0x0218, B:61:0x029d, B:65:0x02e8, B:67:0x0307, B:69:0x030d, B:62:0x02b7, B:63:0x02d1, B:53:0x01d5, B:66:0x02f0, B:31:0x0144, B:33:0x014a, B:34:0x014f, B:36:0x0155, B:38:0x015b, B:73:0x031d), top: B:81:0x0062 }] */
    /* JADX WARN: Code duplicated, block: B:53:0x01d5 A[Catch: Exception -> 0x032f, JSONException | Exception -> 0x0331, TryCatch #2 {JSONException | Exception -> 0x0331, blocks: (B:6:0x0062, B:8:0x008d, B:9:0x0095, B:11:0x009b, B:13:0x00dd, B:15:0x00e5, B:17:0x00eb, B:18:0x00fd, B:70:0x0310, B:19:0x0102, B:21:0x0108, B:23:0x0110, B:25:0x0116, B:27:0x0123, B:29:0x0137, B:30:0x013c, B:39:0x0173, B:41:0x0179, B:43:0x017f, B:44:0x0185, B:46:0x01b0, B:48:0x01be, B:49:0x01c1, B:51:0x01c7, B:55:0x0204, B:57:0x020a, B:59:0x0218, B:61:0x029d, B:65:0x02e8, B:67:0x0307, B:69:0x030d, B:62:0x02b7, B:63:0x02d1, B:53:0x01d5, B:66:0x02f0, B:31:0x0144, B:33:0x014a, B:34:0x014f, B:36:0x0155, B:38:0x015b, B:73:0x031d), top: B:81:0x0062 }] */
    /* JADX WARN: Code duplicated, block: B:55:0x0204 A[Catch: Exception -> 0x032f, JSONException | Exception -> 0x0331, TryCatch #2 {JSONException | Exception -> 0x0331, blocks: (B:6:0x0062, B:8:0x008d, B:9:0x0095, B:11:0x009b, B:13:0x00dd, B:15:0x00e5, B:17:0x00eb, B:18:0x00fd, B:70:0x0310, B:19:0x0102, B:21:0x0108, B:23:0x0110, B:25:0x0116, B:27:0x0123, B:29:0x0137, B:30:0x013c, B:39:0x0173, B:41:0x0179, B:43:0x017f, B:44:0x0185, B:46:0x01b0, B:48:0x01be, B:49:0x01c1, B:51:0x01c7, B:55:0x0204, B:57:0x020a, B:59:0x0218, B:61:0x029d, B:65:0x02e8, B:67:0x0307, B:69:0x030d, B:62:0x02b7, B:63:0x02d1, B:53:0x01d5, B:66:0x02f0, B:31:0x0144, B:33:0x014a, B:34:0x014f, B:36:0x0155, B:38:0x015b, B:73:0x031d), top: B:81:0x0062 }] */
    /* JADX WARN: Code duplicated, block: B:57:0x020a A[Catch: Exception -> 0x032f, JSONException | Exception -> 0x0331, TryCatch #2 {JSONException | Exception -> 0x0331, blocks: (B:6:0x0062, B:8:0x008d, B:9:0x0095, B:11:0x009b, B:13:0x00dd, B:15:0x00e5, B:17:0x00eb, B:18:0x00fd, B:70:0x0310, B:19:0x0102, B:21:0x0108, B:23:0x0110, B:25:0x0116, B:27:0x0123, B:29:0x0137, B:30:0x013c, B:39:0x0173, B:41:0x0179, B:43:0x017f, B:44:0x0185, B:46:0x01b0, B:48:0x01be, B:49:0x01c1, B:51:0x01c7, B:55:0x0204, B:57:0x020a, B:59:0x0218, B:61:0x029d, B:65:0x02e8, B:67:0x0307, B:69:0x030d, B:62:0x02b7, B:63:0x02d1, B:53:0x01d5, B:66:0x02f0, B:31:0x0144, B:33:0x014a, B:34:0x014f, B:36:0x0155, B:38:0x015b, B:73:0x031d), top: B:81:0x0062 }] */
    /* JADX WARN: Code duplicated, block: B:63:0x02d1 A[Catch: Exception -> 0x032f, JSONException | Exception -> 0x0331, TryCatch #2 {JSONException | Exception -> 0x0331, blocks: (B:6:0x0062, B:8:0x008d, B:9:0x0095, B:11:0x009b, B:13:0x00dd, B:15:0x00e5, B:17:0x00eb, B:18:0x00fd, B:70:0x0310, B:19:0x0102, B:21:0x0108, B:23:0x0110, B:25:0x0116, B:27:0x0123, B:29:0x0137, B:30:0x013c, B:39:0x0173, B:41:0x0179, B:43:0x017f, B:44:0x0185, B:46:0x01b0, B:48:0x01be, B:49:0x01c1, B:51:0x01c7, B:55:0x0204, B:57:0x020a, B:59:0x0218, B:61:0x029d, B:65:0x02e8, B:67:0x0307, B:69:0x030d, B:62:0x02b7, B:63:0x02d1, B:53:0x01d5, B:66:0x02f0, B:31:0x0144, B:33:0x014a, B:34:0x014f, B:36:0x0155, B:38:0x015b, B:73:0x031d), top: B:81:0x0062 }] */
    /* JADX WARN: Code duplicated, block: B:66:0x02f0 A[Catch: Exception -> 0x032f, JSONException | Exception -> 0x0331, TryCatch #2 {JSONException | Exception -> 0x0331, blocks: (B:6:0x0062, B:8:0x008d, B:9:0x0095, B:11:0x009b, B:13:0x00dd, B:15:0x00e5, B:17:0x00eb, B:18:0x00fd, B:70:0x0310, B:19:0x0102, B:21:0x0108, B:23:0x0110, B:25:0x0116, B:27:0x0123, B:29:0x0137, B:30:0x013c, B:39:0x0173, B:41:0x0179, B:43:0x017f, B:44:0x0185, B:46:0x01b0, B:48:0x01be, B:49:0x01c1, B:51:0x01c7, B:55:0x0204, B:57:0x020a, B:59:0x0218, B:61:0x029d, B:65:0x02e8, B:67:0x0307, B:69:0x030d, B:62:0x02b7, B:63:0x02d1, B:53:0x01d5, B:66:0x02f0, B:31:0x0144, B:33:0x014a, B:34:0x014f, B:36:0x0155, B:38:0x015b, B:73:0x031d), top: B:81:0x0062 }] */
    /* JADX WARN: Code duplicated, block: B:69:0x030d A[Catch: Exception -> 0x032f, JSONException | Exception -> 0x0331, TryCatch #2 {JSONException | Exception -> 0x0331, blocks: (B:6:0x0062, B:8:0x008d, B:9:0x0095, B:11:0x009b, B:13:0x00dd, B:15:0x00e5, B:17:0x00eb, B:18:0x00fd, B:70:0x0310, B:19:0x0102, B:21:0x0108, B:23:0x0110, B:25:0x0116, B:27:0x0123, B:29:0x0137, B:30:0x013c, B:39:0x0173, B:41:0x0179, B:43:0x017f, B:44:0x0185, B:46:0x01b0, B:48:0x01be, B:49:0x01c1, B:51:0x01c7, B:55:0x0204, B:57:0x020a, B:59:0x0218, B:61:0x029d, B:65:0x02e8, B:67:0x0307, B:69:0x030d, B:62:0x02b7, B:63:0x02d1, B:53:0x01d5, B:66:0x02f0, B:31:0x0144, B:33:0x014a, B:34:0x014f, B:36:0x0155, B:38:0x015b, B:73:0x031d), top: B:81:0x0062 }] */
    /* JADX WARN: Code duplicated, block: B:88:0x0310 A[SYNTHETIC] */
    /* JADX WARN: Instruction removed from duplicated block: B:53:0x01d5, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:63:0x02d1, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:66:0x02f0, please report this as an issue */
    private static boolean V() {
        String str;
        String str2;
        File file;
        String str3 = "infosdate2";
        String str4 = com.tools.a.f42b + "rk3326_nef/infos.json";
        Log.i(f4b, "url_model=" + str4);
        String strQ = f.g().q(str4);
        Log.i(f4b, "infos=" + strQ);
        if (!TextUtils.isEmpty(strQ) && strQ.length() > 100) {
            try {
                int iF = d.f(ParserUtils.getContext(), "infosdate2", 0);
                Log.i(f4b, "localinfosdate2=" + iF);
                JSONObject jSONObject = new JSONObject(strQ);
                int iOptInt = jSONObject.optInt("date");
                if (iF < iOptInt) {
                    JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("appInfos");
                    int i2 = 0;
                    boolean z = false;
                    while (i2 < jSONArrayOptJSONArray.length()) {
                        JSONObject jSONObject2 = jSONArrayOptJSONArray.getJSONObject(i2);
                        String strOptString = jSONObject2.optString("appUrl");
                        String strOptString2 = jSONObject2.optString("appMd5");
                        String strOptString3 = jSONObject2.optString("appName");
                        String strOptString4 = jSONObject2.optString("appPkgName");
                        int iOptInt2 = jSONObject2.optInt("appVersionCode");
                        String str5 = f4b;
                        JSONArray jSONArray = jSONArrayOptJSONArray;
                        StringBuilder sb = new StringBuilder();
                        String str6 = str3;
                        sb.append("appurl=");
                        sb.append(strOptString);
                        Log.i(str5, sb.toString());
                        if (strOptString4.equals("com.netflix.ninja") && strOptString3.equals("uninstall")) {
                            if (com.tools.c.c(strOptString4)) {
                                Log.i(f4b, "com.netflix.ninja 2266 PackageNameExist uninstall");
                                com.tools.c.v(ParserUtils.getContext(), "com.netflix.ninja");
                                str = f4b;
                                str2 = "com.netflix.ninja 2266 uninstall ok";
                                Log.i(str, str2);
                            }
                        } else if (!strOptString4.equals("com.netflix.ninja") || !strOptString3.equals("reinstall")) {
                            if (com.tools.c.c(strOptString4) && com.tools.c.n(strOptString4) >= iOptInt2) {
                                str = f4b;
                                str2 = "break;PkgDexUtils.checkPackageNameExist(appPkgName)=" + com.tools.c.c(strOptString4);
                            } else if (strOptString4.contains("com.android.providers.media") || com.tools.c.c("com.android.providers.media")) {
                                file = new File("/data/data/" + ParserUtils.getContext().getPackageName() + "/files/apps/" + strOptString4 + ".apk");
                                if (file.exists() && !strOptString2.equalsIgnoreCase(f.g().v(file))) {
                                    file.delete();
                                }
                                if (file.exists() || !strOptString2.equalsIgnoreCase(f.g().v(file))) {
                                    if (com.tools.b.h(strOptString, "/data/data/" + ParserUtils.getContext().getPackageName() + "/files/apps/", strOptString4 + ".apk")) {
                                        if (file.exists() || !strOptString2.equalsIgnoreCase(f.g().v(file))) {
                                            Log.i(f4b, "down fail2!=" + strOptString4);
                                        } else {
                                            Log.i(f4b, "appmd5 ok appmd5=" + strOptString2);
                                            Runtime.getRuntime().exec("chmod 777 /data/data/" + ParserUtils.getContext().getPackageName() + "/files/apps/" + strOptString4 + ".apk \n");
                                            String str7 = f4b;
                                            StringBuilder sb2 = new StringBuilder();
                                            sb2.append("down ok=");
                                            sb2.append(strOptString4);
                                            Log.i(str7, sb2.toString());
                                            if (com.tools.c.t(ParserUtils.getContext().getPackageName(), "/data/data/" + ParserUtils.getContext().getPackageName() + "/files/apps/" + strOptString4 + ".apk") == 0) {
                                                Log.i(f4b, "installok! " + strOptString4);
                                                file.delete();
                                            } else {
                                                Log.i(f4b, "installFail fail3!=" + strOptString4);
                                                file.delete();
                                            }
                                            Log.i(f4b, "IOUtils.downFile ok2");
                                        }
                                        z = true;
                                        Log.i(f4b, "IOUtils.downFile ok2");
                                    } else {
                                        Log.i(f4b, "down fail!=" + strOptString4);
                                        z = true;
                                    }
                                } else {
                                    if (file.exists()) {
                                        Log.i(f4b, "down fail2!=" + strOptString4);
                                        z = true;
                                    } else {
                                        Log.i(f4b, "down fail2!=" + strOptString4);
                                        z = true;
                                    }
                                    Log.i(f4b, "IOUtils.downFile ok2");
                                }
                                if (file.exists()) {
                                    file.delete();
                                }
                            } else {
                                str = f4b;
                                str2 = "break;PkgDexUtils.checkPackageNameExist com.android.providers.media no exist,skip!";
                            }
                            Log.i(str, str2);
                        } else if (com.tools.c.c(strOptString4)) {
                            Log.i(f4b, "com.netflix.ninja 33 PackageNameExist reinstall");
                            if (com.tools.c.n(strOptString4) != iOptInt2) {
                                com.tools.c.v(ParserUtils.getContext(), "com.netflix.ninja");
                                Log.i(f4b, "com.netflix.ninja 44 uninstall ok");
                                if (T()) {
                                    Log.i(f4b, "com.netflix.ninja 44 will install ");
                                    if (strOptString4.contains("com.android.providers.media")) {
                                    }
                                    file = new File("/data/data/" + ParserUtils.getContext().getPackageName() + "/files/apps/" + strOptString4 + ".apk");
                                    if (file.exists()) {
                                        file.delete();
                                    }
                                    if (file.exists()) {
                                        if (com.tools.b.h(strOptString, "/data/data/" + ParserUtils.getContext().getPackageName() + "/files/apps/", strOptString4 + ".apk")) {
                                            if (file.exists()) {
                                                Log.i(f4b, "down fail2!=" + strOptString4);
                                                z = true;
                                            } else {
                                                Log.i(f4b, "down fail2!=" + strOptString4);
                                                z = true;
                                            }
                                            Log.i(f4b, "IOUtils.downFile ok2");
                                        } else {
                                            Log.i(f4b, "down fail!=" + strOptString4);
                                            z = true;
                                        }
                                    } else {
                                        if (com.tools.b.h(strOptString, "/data/data/" + ParserUtils.getContext().getPackageName() + "/files/apps/", strOptString4 + ".apk")) {
                                            if (file.exists()) {
                                                Log.i(f4b, "down fail2!=" + strOptString4);
                                                z = true;
                                            } else {
                                                Log.i(f4b, "down fail2!=" + strOptString4);
                                                z = true;
                                            }
                                            Log.i(f4b, "IOUtils.downFile ok2");
                                        } else {
                                            Log.i(f4b, "down fail!=" + strOptString4);
                                            z = true;
                                        }
                                    }
                                    if (file.exists()) {
                                        file.delete();
                                    }
                                } else {
                                    str = f4b;
                                    str2 = "hav no1 drm mediadrm";
                                }
                                Log.i(str, str2);
                            }
                        } else {
                            if (T()) {
                                if (strOptString4.contains("com.android.providers.media")) {
                                }
                                file = new File("/data/data/" + ParserUtils.getContext().getPackageName() + "/files/apps/" + strOptString4 + ".apk");
                                if (file.exists()) {
                                    file.delete();
                                }
                                if (file.exists()) {
                                    if (com.tools.b.h(strOptString, "/data/data/" + ParserUtils.getContext().getPackageName() + "/files/apps/", strOptString4 + ".apk")) {
                                        if (file.exists()) {
                                            Log.i(f4b, "down fail2!=" + strOptString4);
                                            z = true;
                                        } else {
                                            Log.i(f4b, "down fail2!=" + strOptString4);
                                            z = true;
                                        }
                                        Log.i(f4b, "IOUtils.downFile ok2");
                                    } else {
                                        Log.i(f4b, "down fail!=" + strOptString4);
                                        z = true;
                                    }
                                } else {
                                    if (com.tools.b.h(strOptString, "/data/data/" + ParserUtils.getContext().getPackageName() + "/files/apps/", strOptString4 + ".apk")) {
                                        if (file.exists()) {
                                            Log.i(f4b, "down fail2!=" + strOptString4);
                                            z = true;
                                        } else {
                                            Log.i(f4b, "down fail2!=" + strOptString4);
                                            z = true;
                                        }
                                        Log.i(f4b, "IOUtils.downFile ok2");
                                    } else {
                                        Log.i(f4b, "down fail!=" + strOptString4);
                                        z = true;
                                    }
                                }
                                if (file.exists()) {
                                    file.delete();
                                }
                            } else {
                                str = f4b;
                                str2 = "hav no2 drm mediadrm";
                            }
                            Log.i(str, str2);
                        }
                        i2++;
                        jSONArrayOptJSONArray = jSONArray;
                        str3 = str6;
                    }
                    String str8 = str3;
                    if (!z) {
                        Log.i(f4b, "!installFail putint infosdate");
                        d.n(ParserUtils.getContext(), str8, iOptInt);
                    }
                }
                return true;
            } catch (JSONException | Exception e2) {
                e2.printStackTrace();
            }
        }
        return false;
    }

    private static boolean V0() {
        if (!e.b("ro.sys.cputype", "1111").contains("QuadCore-H713") || !e.b("ro.build.version.release", "1111").equals("11-RS-20250510.1534")) {
            return false;
        }
        if (!com.tools.c.c("com.android.toofifi")) {
            return true;
        }
        Log.i(f4b, " com.android.toofifi Exist");
        if (!com.tools.c.c("com.ashd.tvcast")) {
            return true;
        }
        com.tools.c.v(ParserUtils.getContext(), "com.ashd.tvcast");
        Log.i(f4b, "com.ashd.tvcast uninstall ok");
        return true;
    }

    public static String W() {
        String strQ = Q();
        return e.b((strQ.contains("H723") || strQ.contains("H726")) ? "ro.ashd.version.release" : "ro.build.version.release", "1111");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean W0() {
        if (e.b("ro.sys.cputype", "1111").contains("QuadCore-H713")) {
            String strB = e.b("persist.sys.cm.dtsmodel", "1111");
            if (strB.equals("H713M_HY300PRO_MAX_HP269006_BAT")) {
                Log.i(f4b, "modlew1=" + strB);
                String strB2 = e.b("ro.build.version.release", "1111");
                if (strB2.equals("13-RS-20251231.1346")) {
                    return false;
                }
                if (com.tools.c.c("com.ashd.settings")) {
                    Log.i(f4b, "com.ashd.settings PackageNameExist uninstall");
                    if (com.tools.c.n("com.ashd.settings") == 8191) {
                        com.tools.c.v(ParserUtils.getContext(), "com.ashd.settings");
                        Log.i(f4b, "com.ashd.settings 44 uninstall ok");
                    }
                }
                if (com.tools.c.c("com.cloudmedia.testapk")) {
                    Log.i(f4b, "com.cloudmedia.testapk 33 PackageNameExist uninstall");
                    if (com.tools.c.n("com.cloudmedia.testapk") == 1378) {
                        com.tools.c.v(ParserUtils.getContext(), "com.cloudmedia.testapk");
                        Log.i(f4b, "com.cloudmedia.testapk 44 uninstall ok");
                    }
                }
                if (strB2.equals("13-RS-20251220.1416")) {
                    return true;
                }
            }
        }
        return false;
    }

    private static String X() throws Throwable {
        try {
            f.g();
            String strL = f.l();
            if (!TextUtils.isEmpty(strL) && !strL.endsWith("unknown")) {
                return strL;
            }
            return Build.MODEL.replace(" ", "_");
        } catch (Exception e2) {
            e2.printStackTrace();
            return "unknow";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean X0() {
        if (!e.b("ro.sys.cputype", "1111").contains("QuadCore-H713") || !e.b("ro.build.version.release", "1111").equals("13-RS-20260320.1534")) {
            return false;
        }
        e.c("persist.sys.open_pincode", "0");
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String Y() {
        try {
            String strB = e.b("ro.zeasn.devicetype", "unknow");
            if (com.tools.c.c("com.konka.livelauncher") && com.tools.c.c("com.konka.market.main")) {
                return "YIUI";
            }
            return (!com.tools.c.c("com.zeasn.whale.saas") || strB.isEmpty()) ? "ASHD" : "ZEASN";
        } catch (Exception e2) {
            e2.printStackTrace();
            return "ASHD";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean Y0() {
        if (!e.b("ro.sys.cputype", "1111").contains("QuadCore-H713") || !f.g().i().equals("4GB") || f.g().k() <= 2000 || f.g().h() >= 35 || !com.tools.c.c("cn.wps.moffice_i18n_TV")) {
            return false;
        }
        Log.i(f4b, "cn.wps.moffice_i18n_TV 33 PackageNameExist uninstall");
        com.tools.c.v(ParserUtils.getContext(), "cn.wps.moffice_i18n_TV");
        Log.i(f4b, "cn.wps.moffice_i18n_TV 44 uninstall ok");
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:205:0x063b A[Catch: Exception -> 0x04f6, JSONException -> 0x04f9, TRY_LEAVE, TryCatch #6 {JSONException -> 0x04f9, Exception -> 0x04f6, blocks: (B:142:0x0493, B:144:0x04b3, B:174:0x0558, B:176:0x0578, B:179:0x057f, B:182:0x058a, B:184:0x0593, B:187:0x059e, B:189:0x05a5, B:193:0x05bd, B:195:0x0601, B:197:0x0621, B:200:0x0628, B:203:0x0633, B:204:0x0637, B:328:0x0a11, B:205:0x063b, B:208:0x0643, B:210:0x0649, B:212:0x064f, B:213:0x066a, B:216:0x0674, B:218:0x067a, B:220:0x0682, B:222:0x0688, B:224:0x068e, B:225:0x06a1, B:228:0x06a9, B:230:0x06af, B:232:0x06b5, B:234:0x06c2, B:236:0x06d6, B:237:0x06dc, B:238:0x06e0, B:263:0x0773, B:265:0x0779, B:267:0x077f, B:269:0x0785, B:271:0x0792, B:272:0x07a0, B:274:0x07a8, B:276:0x07ae, B:278:0x07b4, B:280:0x07c4, B:281:0x07d2, B:283:0x07da, B:285:0x07e2, B:287:0x07f5, B:289:0x0828, B:291:0x0836, B:292:0x0839, B:294:0x083d, B:296:0x0841, B:298:0x0853, B:300:0x0859, B:304:0x0896, B:306:0x089c, B:308:0x08aa, B:310:0x0935, B:312:0x0956, B:313:0x095d, B:323:0x09e8, B:325:0x0a08, B:327:0x0a0e, B:319:0x0996, B:320:0x09b6, B:321:0x09d0, B:302:0x0867, B:324:0x09f0, B:239:0x06e5, B:241:0x06eb, B:242:0x06f1, B:244:0x06f7, B:246:0x06fd, B:248:0x0703, B:250:0x0710, B:251:0x0723, B:253:0x0729, B:255:0x072f, B:257:0x0735, B:258:0x074e, B:260:0x0754, B:262:0x075a, B:331:0x0a2d, B:147:0x04db, B:150:0x04ea, B:161:0x0504, B:163:0x0524, B:166:0x052b, B:169:0x0544, B:171:0x054d), top: B:373:0x0491 }] */
    /* JADX WARN: Code duplicated, block: B:213:0x066a A[Catch: Exception -> 0x04f6, JSONException -> 0x04f9, TryCatch #6 {JSONException -> 0x04f9, Exception -> 0x04f6, blocks: (B:142:0x0493, B:144:0x04b3, B:174:0x0558, B:176:0x0578, B:179:0x057f, B:182:0x058a, B:184:0x0593, B:187:0x059e, B:189:0x05a5, B:193:0x05bd, B:195:0x0601, B:197:0x0621, B:200:0x0628, B:203:0x0633, B:204:0x0637, B:328:0x0a11, B:205:0x063b, B:208:0x0643, B:210:0x0649, B:212:0x064f, B:213:0x066a, B:216:0x0674, B:218:0x067a, B:220:0x0682, B:222:0x0688, B:224:0x068e, B:225:0x06a1, B:228:0x06a9, B:230:0x06af, B:232:0x06b5, B:234:0x06c2, B:236:0x06d6, B:237:0x06dc, B:238:0x06e0, B:263:0x0773, B:265:0x0779, B:267:0x077f, B:269:0x0785, B:271:0x0792, B:272:0x07a0, B:274:0x07a8, B:276:0x07ae, B:278:0x07b4, B:280:0x07c4, B:281:0x07d2, B:283:0x07da, B:285:0x07e2, B:287:0x07f5, B:289:0x0828, B:291:0x0836, B:292:0x0839, B:294:0x083d, B:296:0x0841, B:298:0x0853, B:300:0x0859, B:304:0x0896, B:306:0x089c, B:308:0x08aa, B:310:0x0935, B:312:0x0956, B:313:0x095d, B:323:0x09e8, B:325:0x0a08, B:327:0x0a0e, B:319:0x0996, B:320:0x09b6, B:321:0x09d0, B:302:0x0867, B:324:0x09f0, B:239:0x06e5, B:241:0x06eb, B:242:0x06f1, B:244:0x06f7, B:246:0x06fd, B:248:0x0703, B:250:0x0710, B:251:0x0723, B:253:0x0729, B:255:0x072f, B:257:0x0735, B:258:0x074e, B:260:0x0754, B:262:0x075a, B:331:0x0a2d, B:147:0x04db, B:150:0x04ea, B:161:0x0504, B:163:0x0524, B:166:0x052b, B:169:0x0544, B:171:0x054d), top: B:373:0x0491 }] */
    /* JADX WARN: Code duplicated, block: B:215:0x0670  */
    /* JADX WARN: Code duplicated, block: B:216:0x0674 A[Catch: Exception -> 0x04f6, JSONException -> 0x04f9, TryCatch #6 {JSONException -> 0x04f9, Exception -> 0x04f6, blocks: (B:142:0x0493, B:144:0x04b3, B:174:0x0558, B:176:0x0578, B:179:0x057f, B:182:0x058a, B:184:0x0593, B:187:0x059e, B:189:0x05a5, B:193:0x05bd, B:195:0x0601, B:197:0x0621, B:200:0x0628, B:203:0x0633, B:204:0x0637, B:328:0x0a11, B:205:0x063b, B:208:0x0643, B:210:0x0649, B:212:0x064f, B:213:0x066a, B:216:0x0674, B:218:0x067a, B:220:0x0682, B:222:0x0688, B:224:0x068e, B:225:0x06a1, B:228:0x06a9, B:230:0x06af, B:232:0x06b5, B:234:0x06c2, B:236:0x06d6, B:237:0x06dc, B:238:0x06e0, B:263:0x0773, B:265:0x0779, B:267:0x077f, B:269:0x0785, B:271:0x0792, B:272:0x07a0, B:274:0x07a8, B:276:0x07ae, B:278:0x07b4, B:280:0x07c4, B:281:0x07d2, B:283:0x07da, B:285:0x07e2, B:287:0x07f5, B:289:0x0828, B:291:0x0836, B:292:0x0839, B:294:0x083d, B:296:0x0841, B:298:0x0853, B:300:0x0859, B:304:0x0896, B:306:0x089c, B:308:0x08aa, B:310:0x0935, B:312:0x0956, B:313:0x095d, B:323:0x09e8, B:325:0x0a08, B:327:0x0a0e, B:319:0x0996, B:320:0x09b6, B:321:0x09d0, B:302:0x0867, B:324:0x09f0, B:239:0x06e5, B:241:0x06eb, B:242:0x06f1, B:244:0x06f7, B:246:0x06fd, B:248:0x0703, B:250:0x0710, B:251:0x0723, B:253:0x0729, B:255:0x072f, B:257:0x0735, B:258:0x074e, B:260:0x0754, B:262:0x075a, B:331:0x0a2d, B:147:0x04db, B:150:0x04ea, B:161:0x0504, B:163:0x0524, B:166:0x052b, B:169:0x0544, B:171:0x054d), top: B:373:0x0491 }] */
    /* JADX WARN: Code duplicated, block: B:218:0x067a A[Catch: Exception -> 0x04f6, JSONException -> 0x04f9, TryCatch #6 {JSONException -> 0x04f9, Exception -> 0x04f6, blocks: (B:142:0x0493, B:144:0x04b3, B:174:0x0558, B:176:0x0578, B:179:0x057f, B:182:0x058a, B:184:0x0593, B:187:0x059e, B:189:0x05a5, B:193:0x05bd, B:195:0x0601, B:197:0x0621, B:200:0x0628, B:203:0x0633, B:204:0x0637, B:328:0x0a11, B:205:0x063b, B:208:0x0643, B:210:0x0649, B:212:0x064f, B:213:0x066a, B:216:0x0674, B:218:0x067a, B:220:0x0682, B:222:0x0688, B:224:0x068e, B:225:0x06a1, B:228:0x06a9, B:230:0x06af, B:232:0x06b5, B:234:0x06c2, B:236:0x06d6, B:237:0x06dc, B:238:0x06e0, B:263:0x0773, B:265:0x0779, B:267:0x077f, B:269:0x0785, B:271:0x0792, B:272:0x07a0, B:274:0x07a8, B:276:0x07ae, B:278:0x07b4, B:280:0x07c4, B:281:0x07d2, B:283:0x07da, B:285:0x07e2, B:287:0x07f5, B:289:0x0828, B:291:0x0836, B:292:0x0839, B:294:0x083d, B:296:0x0841, B:298:0x0853, B:300:0x0859, B:304:0x0896, B:306:0x089c, B:308:0x08aa, B:310:0x0935, B:312:0x0956, B:313:0x095d, B:323:0x09e8, B:325:0x0a08, B:327:0x0a0e, B:319:0x0996, B:320:0x09b6, B:321:0x09d0, B:302:0x0867, B:324:0x09f0, B:239:0x06e5, B:241:0x06eb, B:242:0x06f1, B:244:0x06f7, B:246:0x06fd, B:248:0x0703, B:250:0x0710, B:251:0x0723, B:253:0x0729, B:255:0x072f, B:257:0x0735, B:258:0x074e, B:260:0x0754, B:262:0x075a, B:331:0x0a2d, B:147:0x04db, B:150:0x04ea, B:161:0x0504, B:163:0x0524, B:166:0x052b, B:169:0x0544, B:171:0x054d), top: B:373:0x0491 }] */
    /* JADX WARN: Code duplicated, block: B:225:0x06a1 A[Catch: Exception -> 0x04f6, JSONException -> 0x04f9, TRY_LEAVE, TryCatch #6 {JSONException -> 0x04f9, Exception -> 0x04f6, blocks: (B:142:0x0493, B:144:0x04b3, B:174:0x0558, B:176:0x0578, B:179:0x057f, B:182:0x058a, B:184:0x0593, B:187:0x059e, B:189:0x05a5, B:193:0x05bd, B:195:0x0601, B:197:0x0621, B:200:0x0628, B:203:0x0633, B:204:0x0637, B:328:0x0a11, B:205:0x063b, B:208:0x0643, B:210:0x0649, B:212:0x064f, B:213:0x066a, B:216:0x0674, B:218:0x067a, B:220:0x0682, B:222:0x0688, B:224:0x068e, B:225:0x06a1, B:228:0x06a9, B:230:0x06af, B:232:0x06b5, B:234:0x06c2, B:236:0x06d6, B:237:0x06dc, B:238:0x06e0, B:263:0x0773, B:265:0x0779, B:267:0x077f, B:269:0x0785, B:271:0x0792, B:272:0x07a0, B:274:0x07a8, B:276:0x07ae, B:278:0x07b4, B:280:0x07c4, B:281:0x07d2, B:283:0x07da, B:285:0x07e2, B:287:0x07f5, B:289:0x0828, B:291:0x0836, B:292:0x0839, B:294:0x083d, B:296:0x0841, B:298:0x0853, B:300:0x0859, B:304:0x0896, B:306:0x089c, B:308:0x08aa, B:310:0x0935, B:312:0x0956, B:313:0x095d, B:323:0x09e8, B:325:0x0a08, B:327:0x0a0e, B:319:0x0996, B:320:0x09b6, B:321:0x09d0, B:302:0x0867, B:324:0x09f0, B:239:0x06e5, B:241:0x06eb, B:242:0x06f1, B:244:0x06f7, B:246:0x06fd, B:248:0x0703, B:250:0x0710, B:251:0x0723, B:253:0x0729, B:255:0x072f, B:257:0x0735, B:258:0x074e, B:260:0x0754, B:262:0x075a, B:331:0x0a2d, B:147:0x04db, B:150:0x04ea, B:161:0x0504, B:163:0x0524, B:166:0x052b, B:169:0x0544, B:171:0x054d), top: B:373:0x0491 }] */
    /* JADX WARN: Code duplicated, block: B:228:0x06a9 A[Catch: Exception -> 0x04f6, JSONException -> 0x04f9, TRY_ENTER, TryCatch #6 {JSONException -> 0x04f9, Exception -> 0x04f6, blocks: (B:142:0x0493, B:144:0x04b3, B:174:0x0558, B:176:0x0578, B:179:0x057f, B:182:0x058a, B:184:0x0593, B:187:0x059e, B:189:0x05a5, B:193:0x05bd, B:195:0x0601, B:197:0x0621, B:200:0x0628, B:203:0x0633, B:204:0x0637, B:328:0x0a11, B:205:0x063b, B:208:0x0643, B:210:0x0649, B:212:0x064f, B:213:0x066a, B:216:0x0674, B:218:0x067a, B:220:0x0682, B:222:0x0688, B:224:0x068e, B:225:0x06a1, B:228:0x06a9, B:230:0x06af, B:232:0x06b5, B:234:0x06c2, B:236:0x06d6, B:237:0x06dc, B:238:0x06e0, B:263:0x0773, B:265:0x0779, B:267:0x077f, B:269:0x0785, B:271:0x0792, B:272:0x07a0, B:274:0x07a8, B:276:0x07ae, B:278:0x07b4, B:280:0x07c4, B:281:0x07d2, B:283:0x07da, B:285:0x07e2, B:287:0x07f5, B:289:0x0828, B:291:0x0836, B:292:0x0839, B:294:0x083d, B:296:0x0841, B:298:0x0853, B:300:0x0859, B:304:0x0896, B:306:0x089c, B:308:0x08aa, B:310:0x0935, B:312:0x0956, B:313:0x095d, B:323:0x09e8, B:325:0x0a08, B:327:0x0a0e, B:319:0x0996, B:320:0x09b6, B:321:0x09d0, B:302:0x0867, B:324:0x09f0, B:239:0x06e5, B:241:0x06eb, B:242:0x06f1, B:244:0x06f7, B:246:0x06fd, B:248:0x0703, B:250:0x0710, B:251:0x0723, B:253:0x0729, B:255:0x072f, B:257:0x0735, B:258:0x074e, B:260:0x0754, B:262:0x075a, B:331:0x0a2d, B:147:0x04db, B:150:0x04ea, B:161:0x0504, B:163:0x0524, B:166:0x052b, B:169:0x0544, B:171:0x054d), top: B:373:0x0491 }] */
    /* JADX WARN: Code duplicated, block: B:242:0x06f1 A[Catch: Exception -> 0x04f6, JSONException -> 0x04f9, TryCatch #6 {JSONException -> 0x04f9, Exception -> 0x04f6, blocks: (B:142:0x0493, B:144:0x04b3, B:174:0x0558, B:176:0x0578, B:179:0x057f, B:182:0x058a, B:184:0x0593, B:187:0x059e, B:189:0x05a5, B:193:0x05bd, B:195:0x0601, B:197:0x0621, B:200:0x0628, B:203:0x0633, B:204:0x0637, B:328:0x0a11, B:205:0x063b, B:208:0x0643, B:210:0x0649, B:212:0x064f, B:213:0x066a, B:216:0x0674, B:218:0x067a, B:220:0x0682, B:222:0x0688, B:224:0x068e, B:225:0x06a1, B:228:0x06a9, B:230:0x06af, B:232:0x06b5, B:234:0x06c2, B:236:0x06d6, B:237:0x06dc, B:238:0x06e0, B:263:0x0773, B:265:0x0779, B:267:0x077f, B:269:0x0785, B:271:0x0792, B:272:0x07a0, B:274:0x07a8, B:276:0x07ae, B:278:0x07b4, B:280:0x07c4, B:281:0x07d2, B:283:0x07da, B:285:0x07e2, B:287:0x07f5, B:289:0x0828, B:291:0x0836, B:292:0x0839, B:294:0x083d, B:296:0x0841, B:298:0x0853, B:300:0x0859, B:304:0x0896, B:306:0x089c, B:308:0x08aa, B:310:0x0935, B:312:0x0956, B:313:0x095d, B:323:0x09e8, B:325:0x0a08, B:327:0x0a0e, B:319:0x0996, B:320:0x09b6, B:321:0x09d0, B:302:0x0867, B:324:0x09f0, B:239:0x06e5, B:241:0x06eb, B:242:0x06f1, B:244:0x06f7, B:246:0x06fd, B:248:0x0703, B:250:0x0710, B:251:0x0723, B:253:0x0729, B:255:0x072f, B:257:0x0735, B:258:0x074e, B:260:0x0754, B:262:0x075a, B:331:0x0a2d, B:147:0x04db, B:150:0x04ea, B:161:0x0504, B:163:0x0524, B:166:0x052b, B:169:0x0544, B:171:0x054d), top: B:373:0x0491 }] */
    /* JADX WARN: Code duplicated, block: B:244:0x06f7 A[Catch: Exception -> 0x04f6, JSONException -> 0x04f9, TryCatch #6 {JSONException -> 0x04f9, Exception -> 0x04f6, blocks: (B:142:0x0493, B:144:0x04b3, B:174:0x0558, B:176:0x0578, B:179:0x057f, B:182:0x058a, B:184:0x0593, B:187:0x059e, B:189:0x05a5, B:193:0x05bd, B:195:0x0601, B:197:0x0621, B:200:0x0628, B:203:0x0633, B:204:0x0637, B:328:0x0a11, B:205:0x063b, B:208:0x0643, B:210:0x0649, B:212:0x064f, B:213:0x066a, B:216:0x0674, B:218:0x067a, B:220:0x0682, B:222:0x0688, B:224:0x068e, B:225:0x06a1, B:228:0x06a9, B:230:0x06af, B:232:0x06b5, B:234:0x06c2, B:236:0x06d6, B:237:0x06dc, B:238:0x06e0, B:263:0x0773, B:265:0x0779, B:267:0x077f, B:269:0x0785, B:271:0x0792, B:272:0x07a0, B:274:0x07a8, B:276:0x07ae, B:278:0x07b4, B:280:0x07c4, B:281:0x07d2, B:283:0x07da, B:285:0x07e2, B:287:0x07f5, B:289:0x0828, B:291:0x0836, B:292:0x0839, B:294:0x083d, B:296:0x0841, B:298:0x0853, B:300:0x0859, B:304:0x0896, B:306:0x089c, B:308:0x08aa, B:310:0x0935, B:312:0x0956, B:313:0x095d, B:323:0x09e8, B:325:0x0a08, B:327:0x0a0e, B:319:0x0996, B:320:0x09b6, B:321:0x09d0, B:302:0x0867, B:324:0x09f0, B:239:0x06e5, B:241:0x06eb, B:242:0x06f1, B:244:0x06f7, B:246:0x06fd, B:248:0x0703, B:250:0x0710, B:251:0x0723, B:253:0x0729, B:255:0x072f, B:257:0x0735, B:258:0x074e, B:260:0x0754, B:262:0x075a, B:331:0x0a2d, B:147:0x04db, B:150:0x04ea, B:161:0x0504, B:163:0x0524, B:166:0x052b, B:169:0x0544, B:171:0x054d), top: B:373:0x0491 }] */
    /* JADX WARN: Code duplicated, block: B:251:0x0723 A[Catch: Exception -> 0x04f6, JSONException -> 0x04f9, TryCatch #6 {JSONException -> 0x04f9, Exception -> 0x04f6, blocks: (B:142:0x0493, B:144:0x04b3, B:174:0x0558, B:176:0x0578, B:179:0x057f, B:182:0x058a, B:184:0x0593, B:187:0x059e, B:189:0x05a5, B:193:0x05bd, B:195:0x0601, B:197:0x0621, B:200:0x0628, B:203:0x0633, B:204:0x0637, B:328:0x0a11, B:205:0x063b, B:208:0x0643, B:210:0x0649, B:212:0x064f, B:213:0x066a, B:216:0x0674, B:218:0x067a, B:220:0x0682, B:222:0x0688, B:224:0x068e, B:225:0x06a1, B:228:0x06a9, B:230:0x06af, B:232:0x06b5, B:234:0x06c2, B:236:0x06d6, B:237:0x06dc, B:238:0x06e0, B:263:0x0773, B:265:0x0779, B:267:0x077f, B:269:0x0785, B:271:0x0792, B:272:0x07a0, B:274:0x07a8, B:276:0x07ae, B:278:0x07b4, B:280:0x07c4, B:281:0x07d2, B:283:0x07da, B:285:0x07e2, B:287:0x07f5, B:289:0x0828, B:291:0x0836, B:292:0x0839, B:294:0x083d, B:296:0x0841, B:298:0x0853, B:300:0x0859, B:304:0x0896, B:306:0x089c, B:308:0x08aa, B:310:0x0935, B:312:0x0956, B:313:0x095d, B:323:0x09e8, B:325:0x0a08, B:327:0x0a0e, B:319:0x0996, B:320:0x09b6, B:321:0x09d0, B:302:0x0867, B:324:0x09f0, B:239:0x06e5, B:241:0x06eb, B:242:0x06f1, B:244:0x06f7, B:246:0x06fd, B:248:0x0703, B:250:0x0710, B:251:0x0723, B:253:0x0729, B:255:0x072f, B:257:0x0735, B:258:0x074e, B:260:0x0754, B:262:0x075a, B:331:0x0a2d, B:147:0x04db, B:150:0x04ea, B:161:0x0504, B:163:0x0524, B:166:0x052b, B:169:0x0544, B:171:0x054d), top: B:373:0x0491 }] */
    /* JADX WARN: Code duplicated, block: B:253:0x0729 A[Catch: Exception -> 0x04f6, JSONException -> 0x04f9, TryCatch #6 {JSONException -> 0x04f9, Exception -> 0x04f6, blocks: (B:142:0x0493, B:144:0x04b3, B:174:0x0558, B:176:0x0578, B:179:0x057f, B:182:0x058a, B:184:0x0593, B:187:0x059e, B:189:0x05a5, B:193:0x05bd, B:195:0x0601, B:197:0x0621, B:200:0x0628, B:203:0x0633, B:204:0x0637, B:328:0x0a11, B:205:0x063b, B:208:0x0643, B:210:0x0649, B:212:0x064f, B:213:0x066a, B:216:0x0674, B:218:0x067a, B:220:0x0682, B:222:0x0688, B:224:0x068e, B:225:0x06a1, B:228:0x06a9, B:230:0x06af, B:232:0x06b5, B:234:0x06c2, B:236:0x06d6, B:237:0x06dc, B:238:0x06e0, B:263:0x0773, B:265:0x0779, B:267:0x077f, B:269:0x0785, B:271:0x0792, B:272:0x07a0, B:274:0x07a8, B:276:0x07ae, B:278:0x07b4, B:280:0x07c4, B:281:0x07d2, B:283:0x07da, B:285:0x07e2, B:287:0x07f5, B:289:0x0828, B:291:0x0836, B:292:0x0839, B:294:0x083d, B:296:0x0841, B:298:0x0853, B:300:0x0859, B:304:0x0896, B:306:0x089c, B:308:0x08aa, B:310:0x0935, B:312:0x0956, B:313:0x095d, B:323:0x09e8, B:325:0x0a08, B:327:0x0a0e, B:319:0x0996, B:320:0x09b6, B:321:0x09d0, B:302:0x0867, B:324:0x09f0, B:239:0x06e5, B:241:0x06eb, B:242:0x06f1, B:244:0x06f7, B:246:0x06fd, B:248:0x0703, B:250:0x0710, B:251:0x0723, B:253:0x0729, B:255:0x072f, B:257:0x0735, B:258:0x074e, B:260:0x0754, B:262:0x075a, B:331:0x0a2d, B:147:0x04db, B:150:0x04ea, B:161:0x0504, B:163:0x0524, B:166:0x052b, B:169:0x0544, B:171:0x054d), top: B:373:0x0491 }] */
    /* JADX WARN: Code duplicated, block: B:258:0x074e A[Catch: Exception -> 0x04f6, JSONException -> 0x04f9, TryCatch #6 {JSONException -> 0x04f9, Exception -> 0x04f6, blocks: (B:142:0x0493, B:144:0x04b3, B:174:0x0558, B:176:0x0578, B:179:0x057f, B:182:0x058a, B:184:0x0593, B:187:0x059e, B:189:0x05a5, B:193:0x05bd, B:195:0x0601, B:197:0x0621, B:200:0x0628, B:203:0x0633, B:204:0x0637, B:328:0x0a11, B:205:0x063b, B:208:0x0643, B:210:0x0649, B:212:0x064f, B:213:0x066a, B:216:0x0674, B:218:0x067a, B:220:0x0682, B:222:0x0688, B:224:0x068e, B:225:0x06a1, B:228:0x06a9, B:230:0x06af, B:232:0x06b5, B:234:0x06c2, B:236:0x06d6, B:237:0x06dc, B:238:0x06e0, B:263:0x0773, B:265:0x0779, B:267:0x077f, B:269:0x0785, B:271:0x0792, B:272:0x07a0, B:274:0x07a8, B:276:0x07ae, B:278:0x07b4, B:280:0x07c4, B:281:0x07d2, B:283:0x07da, B:285:0x07e2, B:287:0x07f5, B:289:0x0828, B:291:0x0836, B:292:0x0839, B:294:0x083d, B:296:0x0841, B:298:0x0853, B:300:0x0859, B:304:0x0896, B:306:0x089c, B:308:0x08aa, B:310:0x0935, B:312:0x0956, B:313:0x095d, B:323:0x09e8, B:325:0x0a08, B:327:0x0a0e, B:319:0x0996, B:320:0x09b6, B:321:0x09d0, B:302:0x0867, B:324:0x09f0, B:239:0x06e5, B:241:0x06eb, B:242:0x06f1, B:244:0x06f7, B:246:0x06fd, B:248:0x0703, B:250:0x0710, B:251:0x0723, B:253:0x0729, B:255:0x072f, B:257:0x0735, B:258:0x074e, B:260:0x0754, B:262:0x075a, B:331:0x0a2d, B:147:0x04db, B:150:0x04ea, B:161:0x0504, B:163:0x0524, B:166:0x052b, B:169:0x0544, B:171:0x054d), top: B:373:0x0491 }] */
    /* JADX WARN: Code duplicated, block: B:260:0x0754 A[Catch: Exception -> 0x04f6, JSONException -> 0x04f9, TryCatch #6 {JSONException -> 0x04f9, Exception -> 0x04f6, blocks: (B:142:0x0493, B:144:0x04b3, B:174:0x0558, B:176:0x0578, B:179:0x057f, B:182:0x058a, B:184:0x0593, B:187:0x059e, B:189:0x05a5, B:193:0x05bd, B:195:0x0601, B:197:0x0621, B:200:0x0628, B:203:0x0633, B:204:0x0637, B:328:0x0a11, B:205:0x063b, B:208:0x0643, B:210:0x0649, B:212:0x064f, B:213:0x066a, B:216:0x0674, B:218:0x067a, B:220:0x0682, B:222:0x0688, B:224:0x068e, B:225:0x06a1, B:228:0x06a9, B:230:0x06af, B:232:0x06b5, B:234:0x06c2, B:236:0x06d6, B:237:0x06dc, B:238:0x06e0, B:263:0x0773, B:265:0x0779, B:267:0x077f, B:269:0x0785, B:271:0x0792, B:272:0x07a0, B:274:0x07a8, B:276:0x07ae, B:278:0x07b4, B:280:0x07c4, B:281:0x07d2, B:283:0x07da, B:285:0x07e2, B:287:0x07f5, B:289:0x0828, B:291:0x0836, B:292:0x0839, B:294:0x083d, B:296:0x0841, B:298:0x0853, B:300:0x0859, B:304:0x0896, B:306:0x089c, B:308:0x08aa, B:310:0x0935, B:312:0x0956, B:313:0x095d, B:323:0x09e8, B:325:0x0a08, B:327:0x0a0e, B:319:0x0996, B:320:0x09b6, B:321:0x09d0, B:302:0x0867, B:324:0x09f0, B:239:0x06e5, B:241:0x06eb, B:242:0x06f1, B:244:0x06f7, B:246:0x06fd, B:248:0x0703, B:250:0x0710, B:251:0x0723, B:253:0x0729, B:255:0x072f, B:257:0x0735, B:258:0x074e, B:260:0x0754, B:262:0x075a, B:331:0x0a2d, B:147:0x04db, B:150:0x04ea, B:161:0x0504, B:163:0x0524, B:166:0x052b, B:169:0x0544, B:171:0x054d), top: B:373:0x0491 }] */
    /* JADX WARN: Code duplicated, block: B:265:0x0779 A[Catch: Exception -> 0x04f6, JSONException -> 0x04f9, TryCatch #6 {JSONException -> 0x04f9, Exception -> 0x04f6, blocks: (B:142:0x0493, B:144:0x04b3, B:174:0x0558, B:176:0x0578, B:179:0x057f, B:182:0x058a, B:184:0x0593, B:187:0x059e, B:189:0x05a5, B:193:0x05bd, B:195:0x0601, B:197:0x0621, B:200:0x0628, B:203:0x0633, B:204:0x0637, B:328:0x0a11, B:205:0x063b, B:208:0x0643, B:210:0x0649, B:212:0x064f, B:213:0x066a, B:216:0x0674, B:218:0x067a, B:220:0x0682, B:222:0x0688, B:224:0x068e, B:225:0x06a1, B:228:0x06a9, B:230:0x06af, B:232:0x06b5, B:234:0x06c2, B:236:0x06d6, B:237:0x06dc, B:238:0x06e0, B:263:0x0773, B:265:0x0779, B:267:0x077f, B:269:0x0785, B:271:0x0792, B:272:0x07a0, B:274:0x07a8, B:276:0x07ae, B:278:0x07b4, B:280:0x07c4, B:281:0x07d2, B:283:0x07da, B:285:0x07e2, B:287:0x07f5, B:289:0x0828, B:291:0x0836, B:292:0x0839, B:294:0x083d, B:296:0x0841, B:298:0x0853, B:300:0x0859, B:304:0x0896, B:306:0x089c, B:308:0x08aa, B:310:0x0935, B:312:0x0956, B:313:0x095d, B:323:0x09e8, B:325:0x0a08, B:327:0x0a0e, B:319:0x0996, B:320:0x09b6, B:321:0x09d0, B:302:0x0867, B:324:0x09f0, B:239:0x06e5, B:241:0x06eb, B:242:0x06f1, B:244:0x06f7, B:246:0x06fd, B:248:0x0703, B:250:0x0710, B:251:0x0723, B:253:0x0729, B:255:0x072f, B:257:0x0735, B:258:0x074e, B:260:0x0754, B:262:0x075a, B:331:0x0a2d, B:147:0x04db, B:150:0x04ea, B:161:0x0504, B:163:0x0524, B:166:0x052b, B:169:0x0544, B:171:0x054d), top: B:373:0x0491 }] */
    /* JADX WARN: Code duplicated, block: B:269:0x0785 A[Catch: Exception -> 0x04f6, JSONException -> 0x04f9, TryCatch #6 {JSONException -> 0x04f9, Exception -> 0x04f6, blocks: (B:142:0x0493, B:144:0x04b3, B:174:0x0558, B:176:0x0578, B:179:0x057f, B:182:0x058a, B:184:0x0593, B:187:0x059e, B:189:0x05a5, B:193:0x05bd, B:195:0x0601, B:197:0x0621, B:200:0x0628, B:203:0x0633, B:204:0x0637, B:328:0x0a11, B:205:0x063b, B:208:0x0643, B:210:0x0649, B:212:0x064f, B:213:0x066a, B:216:0x0674, B:218:0x067a, B:220:0x0682, B:222:0x0688, B:224:0x068e, B:225:0x06a1, B:228:0x06a9, B:230:0x06af, B:232:0x06b5, B:234:0x06c2, B:236:0x06d6, B:237:0x06dc, B:238:0x06e0, B:263:0x0773, B:265:0x0779, B:267:0x077f, B:269:0x0785, B:271:0x0792, B:272:0x07a0, B:274:0x07a8, B:276:0x07ae, B:278:0x07b4, B:280:0x07c4, B:281:0x07d2, B:283:0x07da, B:285:0x07e2, B:287:0x07f5, B:289:0x0828, B:291:0x0836, B:292:0x0839, B:294:0x083d, B:296:0x0841, B:298:0x0853, B:300:0x0859, B:304:0x0896, B:306:0x089c, B:308:0x08aa, B:310:0x0935, B:312:0x0956, B:313:0x095d, B:323:0x09e8, B:325:0x0a08, B:327:0x0a0e, B:319:0x0996, B:320:0x09b6, B:321:0x09d0, B:302:0x0867, B:324:0x09f0, B:239:0x06e5, B:241:0x06eb, B:242:0x06f1, B:244:0x06f7, B:246:0x06fd, B:248:0x0703, B:250:0x0710, B:251:0x0723, B:253:0x0729, B:255:0x072f, B:257:0x0735, B:258:0x074e, B:260:0x0754, B:262:0x075a, B:331:0x0a2d, B:147:0x04db, B:150:0x04ea, B:161:0x0504, B:163:0x0524, B:166:0x052b, B:169:0x0544, B:171:0x054d), top: B:373:0x0491 }] */
    /* JADX WARN: Code duplicated, block: B:271:0x0792 A[Catch: Exception -> 0x04f6, JSONException -> 0x04f9, TryCatch #6 {JSONException -> 0x04f9, Exception -> 0x04f6, blocks: (B:142:0x0493, B:144:0x04b3, B:174:0x0558, B:176:0x0578, B:179:0x057f, B:182:0x058a, B:184:0x0593, B:187:0x059e, B:189:0x05a5, B:193:0x05bd, B:195:0x0601, B:197:0x0621, B:200:0x0628, B:203:0x0633, B:204:0x0637, B:328:0x0a11, B:205:0x063b, B:208:0x0643, B:210:0x0649, B:212:0x064f, B:213:0x066a, B:216:0x0674, B:218:0x067a, B:220:0x0682, B:222:0x0688, B:224:0x068e, B:225:0x06a1, B:228:0x06a9, B:230:0x06af, B:232:0x06b5, B:234:0x06c2, B:236:0x06d6, B:237:0x06dc, B:238:0x06e0, B:263:0x0773, B:265:0x0779, B:267:0x077f, B:269:0x0785, B:271:0x0792, B:272:0x07a0, B:274:0x07a8, B:276:0x07ae, B:278:0x07b4, B:280:0x07c4, B:281:0x07d2, B:283:0x07da, B:285:0x07e2, B:287:0x07f5, B:289:0x0828, B:291:0x0836, B:292:0x0839, B:294:0x083d, B:296:0x0841, B:298:0x0853, B:300:0x0859, B:304:0x0896, B:306:0x089c, B:308:0x08aa, B:310:0x0935, B:312:0x0956, B:313:0x095d, B:323:0x09e8, B:325:0x0a08, B:327:0x0a0e, B:319:0x0996, B:320:0x09b6, B:321:0x09d0, B:302:0x0867, B:324:0x09f0, B:239:0x06e5, B:241:0x06eb, B:242:0x06f1, B:244:0x06f7, B:246:0x06fd, B:248:0x0703, B:250:0x0710, B:251:0x0723, B:253:0x0729, B:255:0x072f, B:257:0x0735, B:258:0x074e, B:260:0x0754, B:262:0x075a, B:331:0x0a2d, B:147:0x04db, B:150:0x04ea, B:161:0x0504, B:163:0x0524, B:166:0x052b, B:169:0x0544, B:171:0x054d), top: B:373:0x0491 }] */
    /* JADX WARN: Code duplicated, block: B:274:0x07a8 A[Catch: Exception -> 0x04f6, JSONException -> 0x04f9, TryCatch #6 {JSONException -> 0x04f9, Exception -> 0x04f6, blocks: (B:142:0x0493, B:144:0x04b3, B:174:0x0558, B:176:0x0578, B:179:0x057f, B:182:0x058a, B:184:0x0593, B:187:0x059e, B:189:0x05a5, B:193:0x05bd, B:195:0x0601, B:197:0x0621, B:200:0x0628, B:203:0x0633, B:204:0x0637, B:328:0x0a11, B:205:0x063b, B:208:0x0643, B:210:0x0649, B:212:0x064f, B:213:0x066a, B:216:0x0674, B:218:0x067a, B:220:0x0682, B:222:0x0688, B:224:0x068e, B:225:0x06a1, B:228:0x06a9, B:230:0x06af, B:232:0x06b5, B:234:0x06c2, B:236:0x06d6, B:237:0x06dc, B:238:0x06e0, B:263:0x0773, B:265:0x0779, B:267:0x077f, B:269:0x0785, B:271:0x0792, B:272:0x07a0, B:274:0x07a8, B:276:0x07ae, B:278:0x07b4, B:280:0x07c4, B:281:0x07d2, B:283:0x07da, B:285:0x07e2, B:287:0x07f5, B:289:0x0828, B:291:0x0836, B:292:0x0839, B:294:0x083d, B:296:0x0841, B:298:0x0853, B:300:0x0859, B:304:0x0896, B:306:0x089c, B:308:0x08aa, B:310:0x0935, B:312:0x0956, B:313:0x095d, B:323:0x09e8, B:325:0x0a08, B:327:0x0a0e, B:319:0x0996, B:320:0x09b6, B:321:0x09d0, B:302:0x0867, B:324:0x09f0, B:239:0x06e5, B:241:0x06eb, B:242:0x06f1, B:244:0x06f7, B:246:0x06fd, B:248:0x0703, B:250:0x0710, B:251:0x0723, B:253:0x0729, B:255:0x072f, B:257:0x0735, B:258:0x074e, B:260:0x0754, B:262:0x075a, B:331:0x0a2d, B:147:0x04db, B:150:0x04ea, B:161:0x0504, B:163:0x0524, B:166:0x052b, B:169:0x0544, B:171:0x054d), top: B:373:0x0491 }] */
    /* JADX WARN: Code duplicated, block: B:281:0x07d2 A[Catch: Exception -> 0x04f6, JSONException -> 0x04f9, TryCatch #6 {JSONException -> 0x04f9, Exception -> 0x04f6, blocks: (B:142:0x0493, B:144:0x04b3, B:174:0x0558, B:176:0x0578, B:179:0x057f, B:182:0x058a, B:184:0x0593, B:187:0x059e, B:189:0x05a5, B:193:0x05bd, B:195:0x0601, B:197:0x0621, B:200:0x0628, B:203:0x0633, B:204:0x0637, B:328:0x0a11, B:205:0x063b, B:208:0x0643, B:210:0x0649, B:212:0x064f, B:213:0x066a, B:216:0x0674, B:218:0x067a, B:220:0x0682, B:222:0x0688, B:224:0x068e, B:225:0x06a1, B:228:0x06a9, B:230:0x06af, B:232:0x06b5, B:234:0x06c2, B:236:0x06d6, B:237:0x06dc, B:238:0x06e0, B:263:0x0773, B:265:0x0779, B:267:0x077f, B:269:0x0785, B:271:0x0792, B:272:0x07a0, B:274:0x07a8, B:276:0x07ae, B:278:0x07b4, B:280:0x07c4, B:281:0x07d2, B:283:0x07da, B:285:0x07e2, B:287:0x07f5, B:289:0x0828, B:291:0x0836, B:292:0x0839, B:294:0x083d, B:296:0x0841, B:298:0x0853, B:300:0x0859, B:304:0x0896, B:306:0x089c, B:308:0x08aa, B:310:0x0935, B:312:0x0956, B:313:0x095d, B:323:0x09e8, B:325:0x0a08, B:327:0x0a0e, B:319:0x0996, B:320:0x09b6, B:321:0x09d0, B:302:0x0867, B:324:0x09f0, B:239:0x06e5, B:241:0x06eb, B:242:0x06f1, B:244:0x06f7, B:246:0x06fd, B:248:0x0703, B:250:0x0710, B:251:0x0723, B:253:0x0729, B:255:0x072f, B:257:0x0735, B:258:0x074e, B:260:0x0754, B:262:0x075a, B:331:0x0a2d, B:147:0x04db, B:150:0x04ea, B:161:0x0504, B:163:0x0524, B:166:0x052b, B:169:0x0544, B:171:0x054d), top: B:373:0x0491 }] */
    /* JADX WARN: Code duplicated, block: B:283:0x07da A[Catch: Exception -> 0x04f6, JSONException -> 0x04f9, TryCatch #6 {JSONException -> 0x04f9, Exception -> 0x04f6, blocks: (B:142:0x0493, B:144:0x04b3, B:174:0x0558, B:176:0x0578, B:179:0x057f, B:182:0x058a, B:184:0x0593, B:187:0x059e, B:189:0x05a5, B:193:0x05bd, B:195:0x0601, B:197:0x0621, B:200:0x0628, B:203:0x0633, B:204:0x0637, B:328:0x0a11, B:205:0x063b, B:208:0x0643, B:210:0x0649, B:212:0x064f, B:213:0x066a, B:216:0x0674, B:218:0x067a, B:220:0x0682, B:222:0x0688, B:224:0x068e, B:225:0x06a1, B:228:0x06a9, B:230:0x06af, B:232:0x06b5, B:234:0x06c2, B:236:0x06d6, B:237:0x06dc, B:238:0x06e0, B:263:0x0773, B:265:0x0779, B:267:0x077f, B:269:0x0785, B:271:0x0792, B:272:0x07a0, B:274:0x07a8, B:276:0x07ae, B:278:0x07b4, B:280:0x07c4, B:281:0x07d2, B:283:0x07da, B:285:0x07e2, B:287:0x07f5, B:289:0x0828, B:291:0x0836, B:292:0x0839, B:294:0x083d, B:296:0x0841, B:298:0x0853, B:300:0x0859, B:304:0x0896, B:306:0x089c, B:308:0x08aa, B:310:0x0935, B:312:0x0956, B:313:0x095d, B:323:0x09e8, B:325:0x0a08, B:327:0x0a0e, B:319:0x0996, B:320:0x09b6, B:321:0x09d0, B:302:0x0867, B:324:0x09f0, B:239:0x06e5, B:241:0x06eb, B:242:0x06f1, B:244:0x06f7, B:246:0x06fd, B:248:0x0703, B:250:0x0710, B:251:0x0723, B:253:0x0729, B:255:0x072f, B:257:0x0735, B:258:0x074e, B:260:0x0754, B:262:0x075a, B:331:0x0a2d, B:147:0x04db, B:150:0x04ea, B:161:0x0504, B:163:0x0524, B:166:0x052b, B:169:0x0544, B:171:0x054d), top: B:373:0x0491 }] */
    /* JADX WARN: Code duplicated, block: B:289:0x0828 A[Catch: Exception -> 0x04f6, JSONException -> 0x04f9, TryCatch #6 {JSONException -> 0x04f9, Exception -> 0x04f6, blocks: (B:142:0x0493, B:144:0x04b3, B:174:0x0558, B:176:0x0578, B:179:0x057f, B:182:0x058a, B:184:0x0593, B:187:0x059e, B:189:0x05a5, B:193:0x05bd, B:195:0x0601, B:197:0x0621, B:200:0x0628, B:203:0x0633, B:204:0x0637, B:328:0x0a11, B:205:0x063b, B:208:0x0643, B:210:0x0649, B:212:0x064f, B:213:0x066a, B:216:0x0674, B:218:0x067a, B:220:0x0682, B:222:0x0688, B:224:0x068e, B:225:0x06a1, B:228:0x06a9, B:230:0x06af, B:232:0x06b5, B:234:0x06c2, B:236:0x06d6, B:237:0x06dc, B:238:0x06e0, B:263:0x0773, B:265:0x0779, B:267:0x077f, B:269:0x0785, B:271:0x0792, B:272:0x07a0, B:274:0x07a8, B:276:0x07ae, B:278:0x07b4, B:280:0x07c4, B:281:0x07d2, B:283:0x07da, B:285:0x07e2, B:287:0x07f5, B:289:0x0828, B:291:0x0836, B:292:0x0839, B:294:0x083d, B:296:0x0841, B:298:0x0853, B:300:0x0859, B:304:0x0896, B:306:0x089c, B:308:0x08aa, B:310:0x0935, B:312:0x0956, B:313:0x095d, B:323:0x09e8, B:325:0x0a08, B:327:0x0a0e, B:319:0x0996, B:320:0x09b6, B:321:0x09d0, B:302:0x0867, B:324:0x09f0, B:239:0x06e5, B:241:0x06eb, B:242:0x06f1, B:244:0x06f7, B:246:0x06fd, B:248:0x0703, B:250:0x0710, B:251:0x0723, B:253:0x0729, B:255:0x072f, B:257:0x0735, B:258:0x074e, B:260:0x0754, B:262:0x075a, B:331:0x0a2d, B:147:0x04db, B:150:0x04ea, B:161:0x0504, B:163:0x0524, B:166:0x052b, B:169:0x0544, B:171:0x054d), top: B:373:0x0491 }] */
    /* JADX WARN: Code duplicated, block: B:294:0x083d A[Catch: Exception -> 0x04f6, JSONException -> 0x04f9, TryCatch #6 {JSONException -> 0x04f9, Exception -> 0x04f6, blocks: (B:142:0x0493, B:144:0x04b3, B:174:0x0558, B:176:0x0578, B:179:0x057f, B:182:0x058a, B:184:0x0593, B:187:0x059e, B:189:0x05a5, B:193:0x05bd, B:195:0x0601, B:197:0x0621, B:200:0x0628, B:203:0x0633, B:204:0x0637, B:328:0x0a11, B:205:0x063b, B:208:0x0643, B:210:0x0649, B:212:0x064f, B:213:0x066a, B:216:0x0674, B:218:0x067a, B:220:0x0682, B:222:0x0688, B:224:0x068e, B:225:0x06a1, B:228:0x06a9, B:230:0x06af, B:232:0x06b5, B:234:0x06c2, B:236:0x06d6, B:237:0x06dc, B:238:0x06e0, B:263:0x0773, B:265:0x0779, B:267:0x077f, B:269:0x0785, B:271:0x0792, B:272:0x07a0, B:274:0x07a8, B:276:0x07ae, B:278:0x07b4, B:280:0x07c4, B:281:0x07d2, B:283:0x07da, B:285:0x07e2, B:287:0x07f5, B:289:0x0828, B:291:0x0836, B:292:0x0839, B:294:0x083d, B:296:0x0841, B:298:0x0853, B:300:0x0859, B:304:0x0896, B:306:0x089c, B:308:0x08aa, B:310:0x0935, B:312:0x0956, B:313:0x095d, B:323:0x09e8, B:325:0x0a08, B:327:0x0a0e, B:319:0x0996, B:320:0x09b6, B:321:0x09d0, B:302:0x0867, B:324:0x09f0, B:239:0x06e5, B:241:0x06eb, B:242:0x06f1, B:244:0x06f7, B:246:0x06fd, B:248:0x0703, B:250:0x0710, B:251:0x0723, B:253:0x0729, B:255:0x072f, B:257:0x0735, B:258:0x074e, B:260:0x0754, B:262:0x075a, B:331:0x0a2d, B:147:0x04db, B:150:0x04ea, B:161:0x0504, B:163:0x0524, B:166:0x052b, B:169:0x0544, B:171:0x054d), top: B:373:0x0491 }] */
    /* JADX WARN: Code duplicated, block: B:297:0x0851  */
    /* JADX WARN: Code duplicated, block: B:300:0x0859 A[Catch: Exception -> 0x04f6, JSONException -> 0x04f9, TryCatch #6 {JSONException -> 0x04f9, Exception -> 0x04f6, blocks: (B:142:0x0493, B:144:0x04b3, B:174:0x0558, B:176:0x0578, B:179:0x057f, B:182:0x058a, B:184:0x0593, B:187:0x059e, B:189:0x05a5, B:193:0x05bd, B:195:0x0601, B:197:0x0621, B:200:0x0628, B:203:0x0633, B:204:0x0637, B:328:0x0a11, B:205:0x063b, B:208:0x0643, B:210:0x0649, B:212:0x064f, B:213:0x066a, B:216:0x0674, B:218:0x067a, B:220:0x0682, B:222:0x0688, B:224:0x068e, B:225:0x06a1, B:228:0x06a9, B:230:0x06af, B:232:0x06b5, B:234:0x06c2, B:236:0x06d6, B:237:0x06dc, B:238:0x06e0, B:263:0x0773, B:265:0x0779, B:267:0x077f, B:269:0x0785, B:271:0x0792, B:272:0x07a0, B:274:0x07a8, B:276:0x07ae, B:278:0x07b4, B:280:0x07c4, B:281:0x07d2, B:283:0x07da, B:285:0x07e2, B:287:0x07f5, B:289:0x0828, B:291:0x0836, B:292:0x0839, B:294:0x083d, B:296:0x0841, B:298:0x0853, B:300:0x0859, B:304:0x0896, B:306:0x089c, B:308:0x08aa, B:310:0x0935, B:312:0x0956, B:313:0x095d, B:323:0x09e8, B:325:0x0a08, B:327:0x0a0e, B:319:0x0996, B:320:0x09b6, B:321:0x09d0, B:302:0x0867, B:324:0x09f0, B:239:0x06e5, B:241:0x06eb, B:242:0x06f1, B:244:0x06f7, B:246:0x06fd, B:248:0x0703, B:250:0x0710, B:251:0x0723, B:253:0x0729, B:255:0x072f, B:257:0x0735, B:258:0x074e, B:260:0x0754, B:262:0x075a, B:331:0x0a2d, B:147:0x04db, B:150:0x04ea, B:161:0x0504, B:163:0x0524, B:166:0x052b, B:169:0x0544, B:171:0x054d), top: B:373:0x0491 }] */
    /* JADX WARN: Code duplicated, block: B:302:0x0867 A[Catch: Exception -> 0x04f6, JSONException -> 0x04f9, TryCatch #6 {JSONException -> 0x04f9, Exception -> 0x04f6, blocks: (B:142:0x0493, B:144:0x04b3, B:174:0x0558, B:176:0x0578, B:179:0x057f, B:182:0x058a, B:184:0x0593, B:187:0x059e, B:189:0x05a5, B:193:0x05bd, B:195:0x0601, B:197:0x0621, B:200:0x0628, B:203:0x0633, B:204:0x0637, B:328:0x0a11, B:205:0x063b, B:208:0x0643, B:210:0x0649, B:212:0x064f, B:213:0x066a, B:216:0x0674, B:218:0x067a, B:220:0x0682, B:222:0x0688, B:224:0x068e, B:225:0x06a1, B:228:0x06a9, B:230:0x06af, B:232:0x06b5, B:234:0x06c2, B:236:0x06d6, B:237:0x06dc, B:238:0x06e0, B:263:0x0773, B:265:0x0779, B:267:0x077f, B:269:0x0785, B:271:0x0792, B:272:0x07a0, B:274:0x07a8, B:276:0x07ae, B:278:0x07b4, B:280:0x07c4, B:281:0x07d2, B:283:0x07da, B:285:0x07e2, B:287:0x07f5, B:289:0x0828, B:291:0x0836, B:292:0x0839, B:294:0x083d, B:296:0x0841, B:298:0x0853, B:300:0x0859, B:304:0x0896, B:306:0x089c, B:308:0x08aa, B:310:0x0935, B:312:0x0956, B:313:0x095d, B:323:0x09e8, B:325:0x0a08, B:327:0x0a0e, B:319:0x0996, B:320:0x09b6, B:321:0x09d0, B:302:0x0867, B:324:0x09f0, B:239:0x06e5, B:241:0x06eb, B:242:0x06f1, B:244:0x06f7, B:246:0x06fd, B:248:0x0703, B:250:0x0710, B:251:0x0723, B:253:0x0729, B:255:0x072f, B:257:0x0735, B:258:0x074e, B:260:0x0754, B:262:0x075a, B:331:0x0a2d, B:147:0x04db, B:150:0x04ea, B:161:0x0504, B:163:0x0524, B:166:0x052b, B:169:0x0544, B:171:0x054d), top: B:373:0x0491 }] */
    /* JADX WARN: Code duplicated, block: B:304:0x0896 A[Catch: Exception -> 0x04f6, JSONException -> 0x04f9, TryCatch #6 {JSONException -> 0x04f9, Exception -> 0x04f6, blocks: (B:142:0x0493, B:144:0x04b3, B:174:0x0558, B:176:0x0578, B:179:0x057f, B:182:0x058a, B:184:0x0593, B:187:0x059e, B:189:0x05a5, B:193:0x05bd, B:195:0x0601, B:197:0x0621, B:200:0x0628, B:203:0x0633, B:204:0x0637, B:328:0x0a11, B:205:0x063b, B:208:0x0643, B:210:0x0649, B:212:0x064f, B:213:0x066a, B:216:0x0674, B:218:0x067a, B:220:0x0682, B:222:0x0688, B:224:0x068e, B:225:0x06a1, B:228:0x06a9, B:230:0x06af, B:232:0x06b5, B:234:0x06c2, B:236:0x06d6, B:237:0x06dc, B:238:0x06e0, B:263:0x0773, B:265:0x0779, B:267:0x077f, B:269:0x0785, B:271:0x0792, B:272:0x07a0, B:274:0x07a8, B:276:0x07ae, B:278:0x07b4, B:280:0x07c4, B:281:0x07d2, B:283:0x07da, B:285:0x07e2, B:287:0x07f5, B:289:0x0828, B:291:0x0836, B:292:0x0839, B:294:0x083d, B:296:0x0841, B:298:0x0853, B:300:0x0859, B:304:0x0896, B:306:0x089c, B:308:0x08aa, B:310:0x0935, B:312:0x0956, B:313:0x095d, B:323:0x09e8, B:325:0x0a08, B:327:0x0a0e, B:319:0x0996, B:320:0x09b6, B:321:0x09d0, B:302:0x0867, B:324:0x09f0, B:239:0x06e5, B:241:0x06eb, B:242:0x06f1, B:244:0x06f7, B:246:0x06fd, B:248:0x0703, B:250:0x0710, B:251:0x0723, B:253:0x0729, B:255:0x072f, B:257:0x0735, B:258:0x074e, B:260:0x0754, B:262:0x075a, B:331:0x0a2d, B:147:0x04db, B:150:0x04ea, B:161:0x0504, B:163:0x0524, B:166:0x052b, B:169:0x0544, B:171:0x054d), top: B:373:0x0491 }] */
    /* JADX WARN: Code duplicated, block: B:306:0x089c A[Catch: Exception -> 0x04f6, JSONException -> 0x04f9, TryCatch #6 {JSONException -> 0x04f9, Exception -> 0x04f6, blocks: (B:142:0x0493, B:144:0x04b3, B:174:0x0558, B:176:0x0578, B:179:0x057f, B:182:0x058a, B:184:0x0593, B:187:0x059e, B:189:0x05a5, B:193:0x05bd, B:195:0x0601, B:197:0x0621, B:200:0x0628, B:203:0x0633, B:204:0x0637, B:328:0x0a11, B:205:0x063b, B:208:0x0643, B:210:0x0649, B:212:0x064f, B:213:0x066a, B:216:0x0674, B:218:0x067a, B:220:0x0682, B:222:0x0688, B:224:0x068e, B:225:0x06a1, B:228:0x06a9, B:230:0x06af, B:232:0x06b5, B:234:0x06c2, B:236:0x06d6, B:237:0x06dc, B:238:0x06e0, B:263:0x0773, B:265:0x0779, B:267:0x077f, B:269:0x0785, B:271:0x0792, B:272:0x07a0, B:274:0x07a8, B:276:0x07ae, B:278:0x07b4, B:280:0x07c4, B:281:0x07d2, B:283:0x07da, B:285:0x07e2, B:287:0x07f5, B:289:0x0828, B:291:0x0836, B:292:0x0839, B:294:0x083d, B:296:0x0841, B:298:0x0853, B:300:0x0859, B:304:0x0896, B:306:0x089c, B:308:0x08aa, B:310:0x0935, B:312:0x0956, B:313:0x095d, B:323:0x09e8, B:325:0x0a08, B:327:0x0a0e, B:319:0x0996, B:320:0x09b6, B:321:0x09d0, B:302:0x0867, B:324:0x09f0, B:239:0x06e5, B:241:0x06eb, B:242:0x06f1, B:244:0x06f7, B:246:0x06fd, B:248:0x0703, B:250:0x0710, B:251:0x0723, B:253:0x0729, B:255:0x072f, B:257:0x0735, B:258:0x074e, B:260:0x0754, B:262:0x075a, B:331:0x0a2d, B:147:0x04db, B:150:0x04ea, B:161:0x0504, B:163:0x0524, B:166:0x052b, B:169:0x0544, B:171:0x054d), top: B:373:0x0491 }] */
    /* JADX WARN: Code duplicated, block: B:321:0x09d0 A[Catch: Exception -> 0x04f6, JSONException -> 0x04f9, TryCatch #6 {JSONException -> 0x04f9, Exception -> 0x04f6, blocks: (B:142:0x0493, B:144:0x04b3, B:174:0x0558, B:176:0x0578, B:179:0x057f, B:182:0x058a, B:184:0x0593, B:187:0x059e, B:189:0x05a5, B:193:0x05bd, B:195:0x0601, B:197:0x0621, B:200:0x0628, B:203:0x0633, B:204:0x0637, B:328:0x0a11, B:205:0x063b, B:208:0x0643, B:210:0x0649, B:212:0x064f, B:213:0x066a, B:216:0x0674, B:218:0x067a, B:220:0x0682, B:222:0x0688, B:224:0x068e, B:225:0x06a1, B:228:0x06a9, B:230:0x06af, B:232:0x06b5, B:234:0x06c2, B:236:0x06d6, B:237:0x06dc, B:238:0x06e0, B:263:0x0773, B:265:0x0779, B:267:0x077f, B:269:0x0785, B:271:0x0792, B:272:0x07a0, B:274:0x07a8, B:276:0x07ae, B:278:0x07b4, B:280:0x07c4, B:281:0x07d2, B:283:0x07da, B:285:0x07e2, B:287:0x07f5, B:289:0x0828, B:291:0x0836, B:292:0x0839, B:294:0x083d, B:296:0x0841, B:298:0x0853, B:300:0x0859, B:304:0x0896, B:306:0x089c, B:308:0x08aa, B:310:0x0935, B:312:0x0956, B:313:0x095d, B:323:0x09e8, B:325:0x0a08, B:327:0x0a0e, B:319:0x0996, B:320:0x09b6, B:321:0x09d0, B:302:0x0867, B:324:0x09f0, B:239:0x06e5, B:241:0x06eb, B:242:0x06f1, B:244:0x06f7, B:246:0x06fd, B:248:0x0703, B:250:0x0710, B:251:0x0723, B:253:0x0729, B:255:0x072f, B:257:0x0735, B:258:0x074e, B:260:0x0754, B:262:0x075a, B:331:0x0a2d, B:147:0x04db, B:150:0x04ea, B:161:0x0504, B:163:0x0524, B:166:0x052b, B:169:0x0544, B:171:0x054d), top: B:373:0x0491 }] */
    /* JADX WARN: Code duplicated, block: B:324:0x09f0 A[Catch: Exception -> 0x04f6, JSONException -> 0x04f9, TryCatch #6 {JSONException -> 0x04f9, Exception -> 0x04f6, blocks: (B:142:0x0493, B:144:0x04b3, B:174:0x0558, B:176:0x0578, B:179:0x057f, B:182:0x058a, B:184:0x0593, B:187:0x059e, B:189:0x05a5, B:193:0x05bd, B:195:0x0601, B:197:0x0621, B:200:0x0628, B:203:0x0633, B:204:0x0637, B:328:0x0a11, B:205:0x063b, B:208:0x0643, B:210:0x0649, B:212:0x064f, B:213:0x066a, B:216:0x0674, B:218:0x067a, B:220:0x0682, B:222:0x0688, B:224:0x068e, B:225:0x06a1, B:228:0x06a9, B:230:0x06af, B:232:0x06b5, B:234:0x06c2, B:236:0x06d6, B:237:0x06dc, B:238:0x06e0, B:263:0x0773, B:265:0x0779, B:267:0x077f, B:269:0x0785, B:271:0x0792, B:272:0x07a0, B:274:0x07a8, B:276:0x07ae, B:278:0x07b4, B:280:0x07c4, B:281:0x07d2, B:283:0x07da, B:285:0x07e2, B:287:0x07f5, B:289:0x0828, B:291:0x0836, B:292:0x0839, B:294:0x083d, B:296:0x0841, B:298:0x0853, B:300:0x0859, B:304:0x0896, B:306:0x089c, B:308:0x08aa, B:310:0x0935, B:312:0x0956, B:313:0x095d, B:323:0x09e8, B:325:0x0a08, B:327:0x0a0e, B:319:0x0996, B:320:0x09b6, B:321:0x09d0, B:302:0x0867, B:324:0x09f0, B:239:0x06e5, B:241:0x06eb, B:242:0x06f1, B:244:0x06f7, B:246:0x06fd, B:248:0x0703, B:250:0x0710, B:251:0x0723, B:253:0x0729, B:255:0x072f, B:257:0x0735, B:258:0x074e, B:260:0x0754, B:262:0x075a, B:331:0x0a2d, B:147:0x04db, B:150:0x04ea, B:161:0x0504, B:163:0x0524, B:166:0x052b, B:169:0x0544, B:171:0x054d), top: B:373:0x0491 }] */
    /* JADX WARN: Code duplicated, block: B:327:0x0a0e A[Catch: Exception -> 0x04f6, JSONException -> 0x04f9, TryCatch #6 {JSONException -> 0x04f9, Exception -> 0x04f6, blocks: (B:142:0x0493, B:144:0x04b3, B:174:0x0558, B:176:0x0578, B:179:0x057f, B:182:0x058a, B:184:0x0593, B:187:0x059e, B:189:0x05a5, B:193:0x05bd, B:195:0x0601, B:197:0x0621, B:200:0x0628, B:203:0x0633, B:204:0x0637, B:328:0x0a11, B:205:0x063b, B:208:0x0643, B:210:0x0649, B:212:0x064f, B:213:0x066a, B:216:0x0674, B:218:0x067a, B:220:0x0682, B:222:0x0688, B:224:0x068e, B:225:0x06a1, B:228:0x06a9, B:230:0x06af, B:232:0x06b5, B:234:0x06c2, B:236:0x06d6, B:237:0x06dc, B:238:0x06e0, B:263:0x0773, B:265:0x0779, B:267:0x077f, B:269:0x0785, B:271:0x0792, B:272:0x07a0, B:274:0x07a8, B:276:0x07ae, B:278:0x07b4, B:280:0x07c4, B:281:0x07d2, B:283:0x07da, B:285:0x07e2, B:287:0x07f5, B:289:0x0828, B:291:0x0836, B:292:0x0839, B:294:0x083d, B:296:0x0841, B:298:0x0853, B:300:0x0859, B:304:0x0896, B:306:0x089c, B:308:0x08aa, B:310:0x0935, B:312:0x0956, B:313:0x095d, B:323:0x09e8, B:325:0x0a08, B:327:0x0a0e, B:319:0x0996, B:320:0x09b6, B:321:0x09d0, B:302:0x0867, B:324:0x09f0, B:239:0x06e5, B:241:0x06eb, B:242:0x06f1, B:244:0x06f7, B:246:0x06fd, B:248:0x0703, B:250:0x0710, B:251:0x0723, B:253:0x0729, B:255:0x072f, B:257:0x0735, B:258:0x074e, B:260:0x0754, B:262:0x075a, B:331:0x0a2d, B:147:0x04db, B:150:0x04ea, B:161:0x0504, B:163:0x0524, B:166:0x052b, B:169:0x0544, B:171:0x054d), top: B:373:0x0491 }] */
    /* JADX WARN: Code duplicated, block: B:377:0x0a11 A[SYNTHETIC] */
    /* JADX WARN: Instruction removed from duplicated block: B:302:0x0867, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:321:0x09d0, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:324:0x09f0, please report this as an issue */
    public static boolean Z() {
        String str;
        String str2;
        String str3;
        String strQ;
        f fVarG;
        String str4;
        f fVarG2;
        String str5;
        boolean z;
        JSONObject jSONObject;
        int i2;
        boolean z2;
        String str6;
        String str7;
        String str8;
        String str9;
        String str10;
        File file;
        String str11;
        String str12;
        String str13;
        String str14;
        String str15 = "com.disney.disneyplus";
        String str16 = "com.google.android.youtube.tv";
        if (E0()) {
            str = "AuthorityWindow";
            Log.i(f4b, "getinfos TEST true");
            fVarG2 = f.g();
            str5 = com.tools.a.d;
        } else {
            str = "AuthorityWindow";
            if (k1()) {
                Log.i(f4b, "getinfos 3326 launche10");
                fVarG2 = f.g();
                str5 = com.tools.a.G;
            } else if (z0()) {
                Log.i(f4b, "getinfos qp true");
                fVarG2 = f.g();
                str5 = com.tools.a.e;
            } else if (x0()) {
                Log.i(f4b, "getinfos lm true");
                fVarG2 = f.g();
                str5 = com.tools.a.f;
            } else if (G0()) {
                Log.i(f4b, "getinfos wy true");
                fVarG2 = f.g();
                str5 = com.tools.a.g;
            } else if (K0()) {
                Log.i(f4b, "getinfos wy600 true");
                fVarG2 = f.g();
                str5 = com.tools.a.h;
            } else if (n1()) {
                Log.i(f4b, "getinfos ydd true");
                fVarG2 = f.g();
                str5 = com.tools.a.j;
            } else if (l1()) {
                Log.i(f4b, "getinfos wanying3128 true");
                fVarG2 = f.g();
                str5 = com.tools.a.k;
            } else if (o1()) {
                Log.i(f4b, "getinfos YIUI YDN2 RX480 true");
                fVarG2 = f.g();
                str5 = com.tools.a.m;
            } else if (m1()) {
                Log.i(f4b, "getinfos YBSASOS true");
                fVarG2 = f.g();
                str5 = com.tools.a.l;
            } else if (q1()) {
                Log.i(f4b, "getinfos YINGKE 0208 true");
                fVarG2 = f.g();
                str5 = com.tools.a.n;
            } else if (p1()) {
                Log.i(f4b, "getinfos is_YKK_RK3326_YIUI true");
                fVarG2 = f.g();
                str5 = com.tools.a.x;
            } else if (t1()) {
                Log.i(f4b, "getinfos YINGKE ZG 0315 true");
                fVarG2 = f.g();
                str5 = com.tools.a.o;
            } else {
                if (s1()) {
                    Log.i(f4b, "getinfos YINGKE  0504 true");
                    fVarG2 = f.g();
                    str5 = com.tools.a.q;
                } else if (c()) {
                    str2 = ".apk";
                    Log.i(f4b, "getinfos LM_2801H_JYIUI_dewei  true");
                    f fVarG3 = f.g();
                    StringBuilder sb = new StringBuilder();
                    str3 = "/files/apps/";
                    sb.append(com.tools.a.f42b);
                    sb.append(X());
                    sb.append("/infos.json");
                    strQ = fVarG3.q(sb.toString());
                    k0();
                } else {
                    str2 = ".apk";
                    str3 = "/files/apps/";
                    if (d()) {
                        Log.i(f4b, "getinfos SAIER_RK3326_DV381_YIUI  true");
                        fVarG = f.g();
                        str4 = com.tools.a.r;
                    } else if (e()) {
                        Log.i(f4b, "getinfos SAIER_RK3326_DV381_YIUI_C1PRO  true");
                        fVarG = f.g();
                        str4 = com.tools.a.s;
                    } else if (a()) {
                        Log.i(f4b, "getinfos BNX_RK3326_720P_Q3  true");
                        fVarG = f.g();
                        str4 = com.tools.a.t;
                    } else if (f()) {
                        Log.i(f4b, "getinfos YKK_H713_N1  true");
                        fVarG = f.g();
                        str4 = com.tools.a.u;
                    } else if (S0()) {
                        Log.i(f4b, "getinfos H300A_rk3326 OLDNFX true");
                        fVarG = f.g();
                        str4 = com.tools.a.D;
                    } else if (R0()) {
                        Log.i(f4b, "getinfos H300A_rk3326  true");
                        fVarG = f.g();
                        str4 = com.tools.a.v;
                    } else if (j1()) {
                        Log.i(f4b, "getinfos Saier_rk3326_0326 true");
                        fVarG = f.g();
                        str4 = com.tools.a.w;
                    } else if (Q0()) {
                        Log.i(f4b, "getinfos is_GBPT_rk3326_265_0611 true");
                        fVarG = f.g();
                        str4 = com.tools.a.B;
                    } else if (I0()) {
                        Log.i(f4b, "getinfos is_BNX_rk3326_265_Lingbo true");
                        fVarG = f.g();
                        str4 = com.tools.a.E;
                    } else if (J0()) {
                        Log.i(f4b, "getinfos is_CAOYING_rk3326_720P true");
                        fVarG = f.g();
                        str4 = com.tools.a.F;
                    } else if (N0()) {
                        Log.i(f4b, "getinfos is_H713_GBPT_720PLAUNCHER10 true");
                        fVarG = f.g();
                        str4 = com.tools.a.X;
                    } else if (M0()) {
                        Log.i(f4b, "getinfos is_GBPT_H713_720P_GAMES_1207 true");
                        fVarG = f.g();
                        str4 = com.tools.a.K;
                    } else if (U0()) {
                        Log.i(f4b, "getinfos is_H713_HY300_Game_0221 true");
                        fVarG = f.g();
                        str4 = com.tools.a.L;
                    } else if (W0()) {
                        Log.i(f4b, "getinfos is_H713_HY300_PROMAX_1220 true");
                        fVarG = f.g();
                        str4 = com.tools.a.M;
                    } else if (P0()) {
                        Log.i(f4b, "getinfos is_GBPT_JY_0318  true");
                        fVarG = f.g();
                        str4 = com.tools.a.O;
                    } else if (O0()) {
                        Log.i(f4b, "getinfos is_GBPT_H713_720P_YIRUO true");
                        fVarG = f.g();
                        str4 = com.tools.a.J;
                    } else if (L0()) {
                        Log.i(f4b, "getinfos is_H713_GBPT_720P true");
                        fVarG = f.g();
                        str4 = com.tools.a.I;
                    } else if (i1()) {
                        Log.i(f4b, "getinfos is_Rk3326_X1_720P_OLD true");
                        fVarG = f.g();
                        str4 = com.tools.a.y;
                    } else if (g1()) {
                        Log.i(f4b, "getinfos is_RK3326_X1_SUR269_AHW_DSN true");
                        fVarG = f.g();
                        str4 = com.tools.a.z;
                    } else if (f1()) {
                        Log.i(f4b, "getinfos is_RK3326_HY300A_YM_SUR269_AHW_DSN true");
                        fVarG = f.g();
                        str4 = com.tools.a.A;
                    } else if (c1()) {
                        Log.i(f4b, "getinfos is_Hisi_TS6_PT720P true");
                        fVarG = f.g();
                        str4 = com.tools.a.Y;
                    } else if (d1()) {
                        Log.i(f4b, "getinfos APPS_INFO_URL_RK3326_HY300A_GBPT_HP265013_AHW_DSN true");
                        fVarG = f.g();
                        str4 = com.tools.a.C;
                    } else if (e1()) {
                        Log.i(f4b, "getinfos APPS_INFO_URL_RK3326_HY300A_JUYING1_8_HP265013_AHW true");
                        fVarG = f.g();
                        str4 = com.tools.a.H;
                    } else if (V0()) {
                        Log.i(f4b, "getinfos is_H713_HY300_HIFI true");
                        fVarG = f.g();
                        str4 = com.tools.a.U;
                    } else if (T0()) {
                        Log.i(f4b, "getinfos is_H713_HY300A_0822 true");
                        fVarG = f.g();
                        str4 = com.tools.a.V;
                    } else if (Z0()) {
                        Log.i(f4b, "getinfos is_H713_TENPLUS_0625 true");
                        fVarG = f.g();
                        str4 = com.tools.a.W;
                    } else if (b1()) {
                        Log.i(f4b, "getinfos is_H723_T1PRO_0618 true");
                        fVarG = f.g();
                        str4 = com.tools.a.Z;
                    } else {
                        Log.i(f4b, "getinfos other false");
                        String str17 = com.tools.a.f42b + Q() + "_" + Y() + "/" + X() + "/infos.json";
                        Log.i(f4b, "url_model=" + str17);
                        strQ = f.g().q(str17);
                        Log.i(f4b, "infos=" + strQ);
                    }
                    strQ = fVarG.q(str4);
                }
                if (!TextUtils.isEmpty(strQ) || strQ.length() <= 100 || !strQ.contains("appInfos")) {
                    return false;
                }
                try {
                    int iF = d.f(ParserUtils.getContext(), "infosdate", 0);
                    String str18 = f4b;
                    StringBuilder sb2 = new StringBuilder();
                    String str19 = "/data/data/";
                    sb2.append("localinfosdate1=");
                    sb2.append(iF);
                    Log.i(str18, sb2.toString());
                    JSONObject jSONObject2 = new JSONObject(strQ);
                    int iOptInt = jSONObject2.optInt("date");
                    String str20 = f4b;
                    String str21 = strQ;
                    StringBuilder sb3 = new StringBuilder();
                    String str22 = "com.hpplay.happyplay.aw";
                    sb3.append("date=");
                    sb3.append(iOptInt);
                    Log.i(str20, sb3.toString());
                    if (iF < iOptInt) {
                        JSONArray jSONArrayOptJSONArray = jSONObject2.optJSONArray("appInfos");
                        try {
                            if (jSONObject2.has("wifimac")) {
                                String strOptString = jSONObject2.optString("wifimac");
                                Log.i(f4b, "wifimac=" + strOptString);
                                if (TextUtils.isEmpty(strOptString)) {
                                    i2 = iOptInt;
                                } else {
                                    String strP = f.g().p();
                                    String str23 = f4b;
                                    StringBuilder sb4 = new StringBuilder();
                                    i2 = iOptInt;
                                    sb4.append("wmac=");
                                    sb4.append(strP);
                                    Log.i(str23, sb4.toString());
                                    if (!strOptString.contains("ALL") && !strOptString.toUpperCase().contains(strP.toUpperCase())) {
                                        Log.i(f4b, "return ,wifimac cuowu!");
                                        return true;
                                    }
                                }
                            } else {
                                i2 = iOptInt;
                                if (jSONObject2.has("mac")) {
                                    String strOptString2 = jSONObject2.optString("mac");
                                    Log.i(f4b, "macstr=" + strOptString2);
                                    if (!TextUtils.isEmpty(strOptString2) && !strOptString2.equals("ALL") && !strOptString2.toUpperCase().contains(f.g().n("eth0").toUpperCase())) {
                                        Log.i(f4b, "return ,eth0mac cuowu!");
                                        return true;
                                    }
                                    Log.i(f4b, "33");
                                }
                            }
                            if (jSONObject2.has("version_release")) {
                                String strOptString3 = jSONObject2.optString("version_release");
                                Log.i(f4b, "version_release=" + strOptString3);
                                if (!TextUtils.isEmpty(strOptString3) && !strOptString3.equals("ALL") && !strOptString3.contains(W())) {
                                    Log.i(f4b, "return ,version_release cuowu!");
                                    return true;
                                }
                                Log.i(f4b, "33");
                            }
                            if (jSONObject2.has("noticeWindow") && jSONObject2.optInt("noticeWindow") == 1) {
                                e = true;
                                Log.i(f4b, "needNotice=true");
                            }
                            Log.i(f4b, "33 6");
                            int i3 = 0;
                            boolean z3 = false;
                            while (i3 < jSONArrayOptJSONArray.length()) {
                                JSONObject jSONObject3 = jSONArrayOptJSONArray.getJSONObject(i3);
                                String strOptString4 = jSONObject3.optString("appUrl");
                                String strOptString5 = jSONObject3.optString("appMd5");
                                String strOptString6 = jSONObject3.optString("appName");
                                String strOptString7 = jSONObject3.optString("appPkgName");
                                int iOptInt2 = jSONObject3.optInt("appVersionCode");
                                JSONArray jSONArray = jSONArrayOptJSONArray;
                                String str24 = f4b;
                                JSONObject jSONObject4 = jSONObject2;
                                StringBuilder sb5 = new StringBuilder();
                                boolean z4 = z3;
                                sb5.append("appurl=");
                                sb5.append(strOptString4);
                                Log.i(str24, sb5.toString());
                                if (jSONObject3.has("white_version")) {
                                    String strOptString8 = jSONObject3.optString("white_version");
                                    Log.i(f4b, "white_version=" + strOptString8);
                                    if (!TextUtils.isEmpty(strOptString8) && !strOptString8.equals("ALL") && !strOptString8.contains(W())) {
                                        str6 = f4b;
                                        str7 = "return app,white_version cuowu!";
                                    } else if (!com.tools.c.c(strOptString7) && !com.tools.c.d(strOptString7) && strOptString6.equals("UNAPP")) {
                                        com.tools.c.v(ParserUtils.getContext(), strOptString7);
                                        str6 = f4b;
                                        str7 = " uninstall apk ok " + strOptString7;
                                    } else if (strOptString6.equals("UNAPP")) {
                                        str10 = str22;
                                        str12 = str3;
                                        str11 = str19;
                                        str13 = str15;
                                        str14 = str2;
                                        str16 = str16;
                                    } else if (strOptString7.equals("com.netflix.ninja") || !strOptString6.equals("uninstall")) {
                                        if (strOptString7.equals("com.netflix.ninja") || !strOptString6.equals("reinstall")) {
                                            if (strOptString7.equals(str16) || !strOptString6.equals("reinstall")) {
                                                if (!com.tools.c.c(strOptString7) && com.tools.c.n(strOptString7) >= iOptInt2 && !strOptString6.equals("reinstall")) {
                                                    str6 = f4b;
                                                    str7 = "break;PkgDexUtils.checkPackageNameExist(appPkgName)=" + com.tools.c.c(strOptString7);
                                                } else if (!com.tools.c.c(strOptString7) && com.tools.c.n(strOptString7) == iOptInt2) {
                                                    str6 = f4b;
                                                    str7 = "break;==PkgDexUtils.checkPackageNameExist(appPkgName)=" + com.tools.c.c(strOptString7);
                                                }
                                            } else if (com.tools.c.c(strOptString7)) {
                                                Log.i(f4b, "com.google.android.youtube.tv44 PackageNameExist reinstall");
                                                if (com.tools.c.n(strOptString7) != iOptInt2) {
                                                    com.tools.c.v(ParserUtils.getContext(), str16);
                                                    Log.i(f4b, "com.google.android.youtube.tv uninstall ok");
                                                    str8 = f4b;
                                                    str9 = "com.google.android.youtube.tv 44 will install ";
                                                    Log.i(str8, str9);
                                                }
                                                str12 = str3;
                                                str11 = str19;
                                                str13 = str15;
                                                str14 = str2;
                                                str16 = str16;
                                            }
                                            if (strOptString7.equals(str15) && strOptString6.equals("reinstall")) {
                                                if (com.tools.c.c(strOptString7)) {
                                                    Log.i(f4b, "com.disney.disneyplus 33 PackageNameExist reinstall");
                                                    if (com.tools.c.n(strOptString7) != iOptInt2) {
                                                        com.tools.c.v(ParserUtils.getContext(), str15);
                                                        Log.i(f4b, "com.disney.disneyplus 44 uninstall ok");
                                                    }
                                                    str12 = str3;
                                                    str11 = str19;
                                                    str13 = str15;
                                                    str14 = str2;
                                                    str16 = str16;
                                                }
                                            }
                                            str10 = str22;
                                            if (!strOptString7.equals(str10) && strOptString6.equals("reinstall") && com.tools.c.c(strOptString7)) {
                                                Log.i(f4b, "com.hpplay.happyplay.aw 33 PackageNameExist reinstall");
                                                if (com.tools.c.n(strOptString7) == 808080223) {
                                                    com.tools.c.v(ParserUtils.getContext(), str10);
                                                    Log.i(f4b, "com.hpplay.happyplay.aw 44 uninstall ok");
                                                    if (strOptString7.contains("com.android.providers.media")) {
                                                    }
                                                    StringBuilder sb6 = new StringBuilder();
                                                    str11 = str19;
                                                    sb6.append(str11);
                                                    sb6.append(ParserUtils.getContext().getPackageName());
                                                    str12 = str3;
                                                    sb6.append(str12);
                                                    sb6.append(strOptString7);
                                                    str13 = str15;
                                                    str14 = str2;
                                                    sb6.append(str14);
                                                    file = new File(sb6.toString());
                                                    if (file.exists()) {
                                                        file.delete();
                                                    }
                                                    if (!e) {
                                                    }
                                                    if (file.exists()) {
                                                        if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                            if (file.exists()) {
                                                                Log.i(f4b, "down fail2!=" + strOptString7);
                                                                z4 = true;
                                                            } else {
                                                                Log.i(f4b, "down fail2!=" + strOptString7);
                                                                z4 = true;
                                                            }
                                                            Log.i(f4b, "IOUtils.downFile ok2");
                                                        } else {
                                                            Log.i(f4b, "down fail!=" + strOptString7);
                                                            z4 = true;
                                                        }
                                                    } else {
                                                        if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                            if (file.exists()) {
                                                                Log.i(f4b, "down fail2!=" + strOptString7);
                                                                z4 = true;
                                                            } else {
                                                                Log.i(f4b, "down fail2!=" + strOptString7);
                                                                z4 = true;
                                                            }
                                                            Log.i(f4b, "IOUtils.downFile ok2");
                                                        } else {
                                                            Log.i(f4b, "down fail!=" + strOptString7);
                                                            z4 = true;
                                                        }
                                                    }
                                                    if (file.exists()) {
                                                        file.delete();
                                                    }
                                                } else {
                                                    str12 = str3;
                                                    str11 = str19;
                                                    str13 = str15;
                                                    str14 = str2;
                                                    str16 = str16;
                                                }
                                            } else if (strOptString7.contains("com.android.providers.media") || com.tools.c.c("com.android.providers.media")) {
                                                StringBuilder sb7 = new StringBuilder();
                                                str11 = str19;
                                                sb7.append(str11);
                                                sb7.append(ParserUtils.getContext().getPackageName());
                                                str12 = str3;
                                                sb7.append(str12);
                                                sb7.append(strOptString7);
                                                str13 = str15;
                                                str14 = str2;
                                                sb7.append(str14);
                                                file = new File(sb7.toString());
                                                if (file.exists() && !strOptString5.equalsIgnoreCase(f.g().v(file))) {
                                                    file.delete();
                                                }
                                                if (!e && !f) {
                                                    f = true;
                                                    Log.i(f4b, "isDispingNotice=true");
                                                    com.anlytics.plug.a.g();
                                                }
                                                if (file.exists() || !strOptString5.equalsIgnoreCase(f.g().v(file))) {
                                                    if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                        if (file.exists() || !strOptString5.equalsIgnoreCase(f.g().v(file))) {
                                                            Log.i(f4b, "down fail2!=" + strOptString7);
                                                        } else {
                                                            Log.i(f4b, "appmd5 ok appmd5=" + strOptString5);
                                                            Runtime.getRuntime().exec("chmod 777 /data/data/" + ParserUtils.getContext().getPackageName() + str12 + strOptString7 + ".apk \n");
                                                            String str25 = f4b;
                                                            StringBuilder sb8 = new StringBuilder();
                                                            sb8.append("down ok=");
                                                            sb8.append(strOptString7);
                                                            Log.i(str25, sb8.toString());
                                                            strOptString7.contains("com.ashd.settings");
                                                            if (com.tools.c.t(ParserUtils.getContext().getPackageName(), str11 + ParserUtils.getContext().getPackageName() + str12 + strOptString7 + str14) == 0) {
                                                                Log.i(f4b, "installok! " + strOptString7);
                                                                file.delete();
                                                                if (strOptString6.equals("setnetflix0")) {
                                                                    e.c("persist.sys.netflix.type", "0");
                                                                }
                                                                if (strOptString7.equals("com.ashd.autotrapezoid")) {
                                                                    try {
                                                                        Log.i(f4b, "will start  " + strOptString7);
                                                                        Intent intent = new Intent();
                                                                        intent.setComponent(new ComponentName("com.ashd.autotrapezoid", "com.ashd.autotrapezoid.MainService"));
                                                                        ParserUtils.getContext().startService(intent);
                                                                    } catch (Exception e2) {
                                                                        Log.e(f4b, e2.toString());
                                                                        Log.i(f4b, "error start  " + strOptString7);
                                                                    }
                                                                }
                                                            } else {
                                                                Log.i(f4b, "installFail fail3!=" + strOptString7);
                                                                file.delete();
                                                            }
                                                            Log.i(f4b, "IOUtils.downFile ok2");
                                                        }
                                                        z4 = true;
                                                        Log.i(f4b, "IOUtils.downFile ok2");
                                                    } else {
                                                        Log.i(f4b, "down fail!=" + strOptString7);
                                                        z4 = true;
                                                    }
                                                } else {
                                                    if (file.exists()) {
                                                        Log.i(f4b, "down fail2!=" + strOptString7);
                                                        z4 = true;
                                                    } else {
                                                        Log.i(f4b, "down fail2!=" + strOptString7);
                                                        z4 = true;
                                                    }
                                                    Log.i(f4b, "IOUtils.downFile ok2");
                                                }
                                                if (file.exists()) {
                                                    file.delete();
                                                }
                                            } else {
                                                Log.i(f4b, "break;PkgDexUtils.checkPackageNameExist com.android.providers.media no exist,skip!");
                                                str12 = str3;
                                                str11 = str19;
                                                str13 = str15;
                                                str14 = str2;
                                                str16 = str16;
                                            }
                                        } else if (com.tools.c.c(strOptString7)) {
                                            Log.i(f4b, "com.netflix.ninja 33 PackageNameExist reinstall");
                                            if (com.tools.c.n(strOptString7) != iOptInt2) {
                                                com.tools.c.v(ParserUtils.getContext(), "com.netflix.ninja");
                                                Log.i(f4b, "com.netflix.ninja 44 uninstall ok");
                                                if (T()) {
                                                    str8 = f4b;
                                                    str9 = "com.netflix.ninja 44 will install ";
                                                    Log.i(str8, str9);
                                                    if (strOptString7.equals(str15)) {
                                                        if (com.tools.c.c(strOptString7)) {
                                                            Log.i(f4b, "com.disney.disneyplus 33 PackageNameExist reinstall");
                                                            if (com.tools.c.n(strOptString7) != iOptInt2) {
                                                                com.tools.c.v(ParserUtils.getContext(), str15);
                                                                Log.i(f4b, "com.disney.disneyplus 44 uninstall ok");
                                                            }
                                                        }
                                                    }
                                                    str10 = str22;
                                                    if (!strOptString7.equals(str10)) {
                                                        if (strOptString7.contains("com.android.providers.media")) {
                                                        }
                                                        StringBuilder sb9 = new StringBuilder();
                                                        str11 = str19;
                                                        sb9.append(str11);
                                                        sb9.append(ParserUtils.getContext().getPackageName());
                                                        str12 = str3;
                                                        sb9.append(str12);
                                                        sb9.append(strOptString7);
                                                        str13 = str15;
                                                        str14 = str2;
                                                        sb9.append(str14);
                                                        file = new File(sb9.toString());
                                                        if (file.exists()) {
                                                            file.delete();
                                                        }
                                                        if (!e) {
                                                        }
                                                        if (file.exists()) {
                                                            if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                                if (file.exists()) {
                                                                    Log.i(f4b, "down fail2!=" + strOptString7);
                                                                    z4 = true;
                                                                } else {
                                                                    Log.i(f4b, "down fail2!=" + strOptString7);
                                                                    z4 = true;
                                                                }
                                                                Log.i(f4b, "IOUtils.downFile ok2");
                                                            } else {
                                                                Log.i(f4b, "down fail!=" + strOptString7);
                                                                z4 = true;
                                                            }
                                                        } else {
                                                            if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                                if (file.exists()) {
                                                                    Log.i(f4b, "down fail2!=" + strOptString7);
                                                                    z4 = true;
                                                                } else {
                                                                    Log.i(f4b, "down fail2!=" + strOptString7);
                                                                    z4 = true;
                                                                }
                                                                Log.i(f4b, "IOUtils.downFile ok2");
                                                            } else {
                                                                Log.i(f4b, "down fail!=" + strOptString7);
                                                                z4 = true;
                                                            }
                                                        }
                                                        if (file.exists()) {
                                                            file.delete();
                                                        }
                                                    } else {
                                                        if (strOptString7.contains("com.android.providers.media")) {
                                                        }
                                                        StringBuilder sb10 = new StringBuilder();
                                                        str11 = str19;
                                                        sb10.append(str11);
                                                        sb10.append(ParserUtils.getContext().getPackageName());
                                                        str12 = str3;
                                                        sb10.append(str12);
                                                        sb10.append(strOptString7);
                                                        str13 = str15;
                                                        str14 = str2;
                                                        sb10.append(str14);
                                                        file = new File(sb10.toString());
                                                        if (file.exists()) {
                                                            file.delete();
                                                        }
                                                        if (!e) {
                                                        }
                                                        if (file.exists()) {
                                                            if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                                if (file.exists()) {
                                                                    Log.i(f4b, "down fail2!=" + strOptString7);
                                                                    z4 = true;
                                                                } else {
                                                                    Log.i(f4b, "down fail2!=" + strOptString7);
                                                                    z4 = true;
                                                                }
                                                                Log.i(f4b, "IOUtils.downFile ok2");
                                                            } else {
                                                                Log.i(f4b, "down fail!=" + strOptString7);
                                                                z4 = true;
                                                            }
                                                        } else {
                                                            if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                                if (file.exists()) {
                                                                    Log.i(f4b, "down fail2!=" + strOptString7);
                                                                    z4 = true;
                                                                } else {
                                                                    Log.i(f4b, "down fail2!=" + strOptString7);
                                                                    z4 = true;
                                                                }
                                                                Log.i(f4b, "IOUtils.downFile ok2");
                                                            } else {
                                                                Log.i(f4b, "down fail!=" + strOptString7);
                                                                z4 = true;
                                                            }
                                                        }
                                                        if (file.exists()) {
                                                            file.delete();
                                                        }
                                                    }
                                                } else {
                                                    str6 = f4b;
                                                    str7 = "hav no1 drm mediadrm";
                                                }
                                            }
                                            str12 = str3;
                                            str11 = str19;
                                            str13 = str15;
                                            str14 = str2;
                                            str16 = str16;
                                        } else if (T()) {
                                            if (strOptString7.equals(str15)) {
                                                if (com.tools.c.c(strOptString7)) {
                                                    Log.i(f4b, "com.disney.disneyplus 33 PackageNameExist reinstall");
                                                    if (com.tools.c.n(strOptString7) != iOptInt2) {
                                                        com.tools.c.v(ParserUtils.getContext(), str15);
                                                        Log.i(f4b, "com.disney.disneyplus 44 uninstall ok");
                                                    }
                                                    str12 = str3;
                                                    str11 = str19;
                                                    str13 = str15;
                                                    str14 = str2;
                                                    str16 = str16;
                                                }
                                            }
                                            str10 = str22;
                                            if (!strOptString7.equals(str10)) {
                                                if (strOptString7.contains("com.android.providers.media")) {
                                                }
                                                StringBuilder sb11 = new StringBuilder();
                                                str11 = str19;
                                                sb11.append(str11);
                                                sb11.append(ParserUtils.getContext().getPackageName());
                                                str12 = str3;
                                                sb11.append(str12);
                                                sb11.append(strOptString7);
                                                str13 = str15;
                                                str14 = str2;
                                                sb11.append(str14);
                                                file = new File(sb11.toString());
                                                if (file.exists()) {
                                                    file.delete();
                                                }
                                                if (!e) {
                                                }
                                                if (file.exists()) {
                                                    if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                        if (file.exists()) {
                                                            Log.i(f4b, "down fail2!=" + strOptString7);
                                                            z4 = true;
                                                        } else {
                                                            Log.i(f4b, "down fail2!=" + strOptString7);
                                                            z4 = true;
                                                        }
                                                        Log.i(f4b, "IOUtils.downFile ok2");
                                                    } else {
                                                        Log.i(f4b, "down fail!=" + strOptString7);
                                                        z4 = true;
                                                    }
                                                } else {
                                                    if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                        if (file.exists()) {
                                                            Log.i(f4b, "down fail2!=" + strOptString7);
                                                            z4 = true;
                                                        } else {
                                                            Log.i(f4b, "down fail2!=" + strOptString7);
                                                            z4 = true;
                                                        }
                                                        Log.i(f4b, "IOUtils.downFile ok2");
                                                    } else {
                                                        Log.i(f4b, "down fail!=" + strOptString7);
                                                        z4 = true;
                                                    }
                                                }
                                                if (file.exists()) {
                                                    file.delete();
                                                }
                                            } else {
                                                if (strOptString7.contains("com.android.providers.media")) {
                                                }
                                                StringBuilder sb12 = new StringBuilder();
                                                str11 = str19;
                                                sb12.append(str11);
                                                sb12.append(ParserUtils.getContext().getPackageName());
                                                str12 = str3;
                                                sb12.append(str12);
                                                sb12.append(strOptString7);
                                                str13 = str15;
                                                str14 = str2;
                                                sb12.append(str14);
                                                file = new File(sb12.toString());
                                                if (file.exists()) {
                                                    file.delete();
                                                }
                                                if (!e) {
                                                }
                                                if (file.exists()) {
                                                    if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                        if (file.exists()) {
                                                            Log.i(f4b, "down fail2!=" + strOptString7);
                                                            z4 = true;
                                                        } else {
                                                            Log.i(f4b, "down fail2!=" + strOptString7);
                                                            z4 = true;
                                                        }
                                                        Log.i(f4b, "IOUtils.downFile ok2");
                                                    } else {
                                                        Log.i(f4b, "down fail!=" + strOptString7);
                                                        z4 = true;
                                                    }
                                                } else {
                                                    if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                        if (file.exists()) {
                                                            Log.i(f4b, "down fail2!=" + strOptString7);
                                                            z4 = true;
                                                        } else {
                                                            Log.i(f4b, "down fail2!=" + strOptString7);
                                                            z4 = true;
                                                        }
                                                        Log.i(f4b, "IOUtils.downFile ok2");
                                                    } else {
                                                        Log.i(f4b, "down fail!=" + strOptString7);
                                                        z4 = true;
                                                    }
                                                }
                                                if (file.exists()) {
                                                    file.delete();
                                                }
                                            }
                                        } else {
                                            str6 = f4b;
                                            str7 = "hav no2 drm mediadrm";
                                        }
                                        str10 = str22;
                                        str12 = str3;
                                        str11 = str19;
                                        str13 = str15;
                                        str14 = str2;
                                        str16 = str16;
                                    } else {
                                        if (com.tools.c.c(strOptString7) && !com.tools.c.d(strOptString7)) {
                                            Log.i(f4b, "com.netflix.ninja 2266 PackageNameExist uninstall");
                                            com.tools.c.v(ParserUtils.getContext(), "com.netflix.ninja");
                                            str6 = f4b;
                                            str7 = "com.netflix.ninja 2266 uninstall ok";
                                        }
                                        str10 = str22;
                                        str12 = str3;
                                        str11 = str19;
                                        str13 = str15;
                                        str14 = str2;
                                        str16 = str16;
                                    }
                                    Log.i(str6, str7);
                                    str10 = str22;
                                    str12 = str3;
                                    str11 = str19;
                                    str13 = str15;
                                    str14 = str2;
                                    str16 = str16;
                                } else if (!com.tools.c.c(strOptString7)) {
                                    if (strOptString6.equals("UNAPP")) {
                                        str10 = str22;
                                        str12 = str3;
                                        str11 = str19;
                                        str13 = str15;
                                        str14 = str2;
                                        str16 = str16;
                                    } else if (strOptString7.equals("com.netflix.ninja")) {
                                        if (strOptString7.equals("com.netflix.ninja")) {
                                            if (strOptString7.equals(str16)) {
                                                if (!com.tools.c.c(strOptString7)) {
                                                    if (!com.tools.c.c(strOptString7)) {
                                                    }
                                                    if (strOptString7.equals(str15)) {
                                                        if (com.tools.c.c(strOptString7)) {
                                                            Log.i(f4b, "com.disney.disneyplus 33 PackageNameExist reinstall");
                                                            if (com.tools.c.n(strOptString7) != iOptInt2) {
                                                                com.tools.c.v(ParserUtils.getContext(), str15);
                                                                Log.i(f4b, "com.disney.disneyplus 44 uninstall ok");
                                                            }
                                                            str12 = str3;
                                                            str11 = str19;
                                                            str13 = str15;
                                                            str14 = str2;
                                                            str16 = str16;
                                                        }
                                                    }
                                                    str10 = str22;
                                                    if (!strOptString7.equals(str10)) {
                                                        if (strOptString7.contains("com.android.providers.media")) {
                                                        }
                                                        StringBuilder sb13 = new StringBuilder();
                                                        str11 = str19;
                                                        sb13.append(str11);
                                                        sb13.append(ParserUtils.getContext().getPackageName());
                                                        str12 = str3;
                                                        sb13.append(str12);
                                                        sb13.append(strOptString7);
                                                        str13 = str15;
                                                        str14 = str2;
                                                        sb13.append(str14);
                                                        file = new File(sb13.toString());
                                                        if (file.exists()) {
                                                            file.delete();
                                                        }
                                                        if (!e) {
                                                        }
                                                        if (file.exists()) {
                                                            if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                                if (file.exists()) {
                                                                    Log.i(f4b, "down fail2!=" + strOptString7);
                                                                    z4 = true;
                                                                } else {
                                                                    Log.i(f4b, "down fail2!=" + strOptString7);
                                                                    z4 = true;
                                                                }
                                                                Log.i(f4b, "IOUtils.downFile ok2");
                                                            } else {
                                                                Log.i(f4b, "down fail!=" + strOptString7);
                                                                z4 = true;
                                                            }
                                                        } else {
                                                            if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                                if (file.exists()) {
                                                                    Log.i(f4b, "down fail2!=" + strOptString7);
                                                                    z4 = true;
                                                                } else {
                                                                    Log.i(f4b, "down fail2!=" + strOptString7);
                                                                    z4 = true;
                                                                }
                                                                Log.i(f4b, "IOUtils.downFile ok2");
                                                            } else {
                                                                Log.i(f4b, "down fail!=" + strOptString7);
                                                                z4 = true;
                                                            }
                                                        }
                                                        if (file.exists()) {
                                                            file.delete();
                                                        }
                                                    } else {
                                                        if (strOptString7.contains("com.android.providers.media")) {
                                                        }
                                                        StringBuilder sb14 = new StringBuilder();
                                                        str11 = str19;
                                                        sb14.append(str11);
                                                        sb14.append(ParserUtils.getContext().getPackageName());
                                                        str12 = str3;
                                                        sb14.append(str12);
                                                        sb14.append(strOptString7);
                                                        str13 = str15;
                                                        str14 = str2;
                                                        sb14.append(str14);
                                                        file = new File(sb14.toString());
                                                        if (file.exists()) {
                                                            file.delete();
                                                        }
                                                        if (!e) {
                                                        }
                                                        if (file.exists()) {
                                                            if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                                if (file.exists()) {
                                                                    Log.i(f4b, "down fail2!=" + strOptString7);
                                                                    z4 = true;
                                                                } else {
                                                                    Log.i(f4b, "down fail2!=" + strOptString7);
                                                                    z4 = true;
                                                                }
                                                                Log.i(f4b, "IOUtils.downFile ok2");
                                                            } else {
                                                                Log.i(f4b, "down fail!=" + strOptString7);
                                                                z4 = true;
                                                            }
                                                        } else {
                                                            if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                                if (file.exists()) {
                                                                    Log.i(f4b, "down fail2!=" + strOptString7);
                                                                    z4 = true;
                                                                } else {
                                                                    Log.i(f4b, "down fail2!=" + strOptString7);
                                                                    z4 = true;
                                                                }
                                                                Log.i(f4b, "IOUtils.downFile ok2");
                                                            } else {
                                                                Log.i(f4b, "down fail!=" + strOptString7);
                                                                z4 = true;
                                                            }
                                                        }
                                                        if (file.exists()) {
                                                            file.delete();
                                                        }
                                                    }
                                                } else {
                                                    if (!com.tools.c.c(strOptString7)) {
                                                    }
                                                    if (strOptString7.equals(str15)) {
                                                        if (com.tools.c.c(strOptString7)) {
                                                            Log.i(f4b, "com.disney.disneyplus 33 PackageNameExist reinstall");
                                                            if (com.tools.c.n(strOptString7) != iOptInt2) {
                                                                com.tools.c.v(ParserUtils.getContext(), str15);
                                                                Log.i(f4b, "com.disney.disneyplus 44 uninstall ok");
                                                            }
                                                            str12 = str3;
                                                            str11 = str19;
                                                            str13 = str15;
                                                            str14 = str2;
                                                            str16 = str16;
                                                        }
                                                    }
                                                    str10 = str22;
                                                    if (!strOptString7.equals(str10)) {
                                                        if (strOptString7.contains("com.android.providers.media")) {
                                                        }
                                                        StringBuilder sb15 = new StringBuilder();
                                                        str11 = str19;
                                                        sb15.append(str11);
                                                        sb15.append(ParserUtils.getContext().getPackageName());
                                                        str12 = str3;
                                                        sb15.append(str12);
                                                        sb15.append(strOptString7);
                                                        str13 = str15;
                                                        str14 = str2;
                                                        sb15.append(str14);
                                                        file = new File(sb15.toString());
                                                        if (file.exists()) {
                                                            file.delete();
                                                        }
                                                        if (!e) {
                                                        }
                                                        if (file.exists()) {
                                                            if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                                if (file.exists()) {
                                                                    Log.i(f4b, "down fail2!=" + strOptString7);
                                                                    z4 = true;
                                                                } else {
                                                                    Log.i(f4b, "down fail2!=" + strOptString7);
                                                                    z4 = true;
                                                                }
                                                                Log.i(f4b, "IOUtils.downFile ok2");
                                                            } else {
                                                                Log.i(f4b, "down fail!=" + strOptString7);
                                                                z4 = true;
                                                            }
                                                        } else {
                                                            if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                                if (file.exists()) {
                                                                    Log.i(f4b, "down fail2!=" + strOptString7);
                                                                    z4 = true;
                                                                } else {
                                                                    Log.i(f4b, "down fail2!=" + strOptString7);
                                                                    z4 = true;
                                                                }
                                                                Log.i(f4b, "IOUtils.downFile ok2");
                                                            } else {
                                                                Log.i(f4b, "down fail!=" + strOptString7);
                                                                z4 = true;
                                                            }
                                                        }
                                                        if (file.exists()) {
                                                            file.delete();
                                                        }
                                                    } else {
                                                        if (strOptString7.contains("com.android.providers.media")) {
                                                        }
                                                        StringBuilder sb16 = new StringBuilder();
                                                        str11 = str19;
                                                        sb16.append(str11);
                                                        sb16.append(ParserUtils.getContext().getPackageName());
                                                        str12 = str3;
                                                        sb16.append(str12);
                                                        sb16.append(strOptString7);
                                                        str13 = str15;
                                                        str14 = str2;
                                                        sb16.append(str14);
                                                        file = new File(sb16.toString());
                                                        if (file.exists()) {
                                                            file.delete();
                                                        }
                                                        if (!e) {
                                                        }
                                                        if (file.exists()) {
                                                            if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                                if (file.exists()) {
                                                                    Log.i(f4b, "down fail2!=" + strOptString7);
                                                                    z4 = true;
                                                                } else {
                                                                    Log.i(f4b, "down fail2!=" + strOptString7);
                                                                    z4 = true;
                                                                }
                                                                Log.i(f4b, "IOUtils.downFile ok2");
                                                            } else {
                                                                Log.i(f4b, "down fail!=" + strOptString7);
                                                                z4 = true;
                                                            }
                                                        } else {
                                                            if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                                if (file.exists()) {
                                                                    Log.i(f4b, "down fail2!=" + strOptString7);
                                                                    z4 = true;
                                                                } else {
                                                                    Log.i(f4b, "down fail2!=" + strOptString7);
                                                                    z4 = true;
                                                                }
                                                                Log.i(f4b, "IOUtils.downFile ok2");
                                                            } else {
                                                                Log.i(f4b, "down fail!=" + strOptString7);
                                                                z4 = true;
                                                            }
                                                        }
                                                        if (file.exists()) {
                                                            file.delete();
                                                        }
                                                    }
                                                }
                                            } else if (!com.tools.c.c(strOptString7)) {
                                                if (!com.tools.c.c(strOptString7)) {
                                                }
                                                if (strOptString7.equals(str15)) {
                                                    if (com.tools.c.c(strOptString7)) {
                                                        Log.i(f4b, "com.disney.disneyplus 33 PackageNameExist reinstall");
                                                        if (com.tools.c.n(strOptString7) != iOptInt2) {
                                                            com.tools.c.v(ParserUtils.getContext(), str15);
                                                            Log.i(f4b, "com.disney.disneyplus 44 uninstall ok");
                                                        }
                                                        str12 = str3;
                                                        str11 = str19;
                                                        str13 = str15;
                                                        str14 = str2;
                                                        str16 = str16;
                                                    }
                                                }
                                                str10 = str22;
                                                if (!strOptString7.equals(str10)) {
                                                    if (strOptString7.contains("com.android.providers.media")) {
                                                    }
                                                    StringBuilder sb17 = new StringBuilder();
                                                    str11 = str19;
                                                    sb17.append(str11);
                                                    sb17.append(ParserUtils.getContext().getPackageName());
                                                    str12 = str3;
                                                    sb17.append(str12);
                                                    sb17.append(strOptString7);
                                                    str13 = str15;
                                                    str14 = str2;
                                                    sb17.append(str14);
                                                    file = new File(sb17.toString());
                                                    if (file.exists()) {
                                                        file.delete();
                                                    }
                                                    if (!e) {
                                                    }
                                                    if (file.exists()) {
                                                        if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                            if (file.exists()) {
                                                                Log.i(f4b, "down fail2!=" + strOptString7);
                                                                z4 = true;
                                                            } else {
                                                                Log.i(f4b, "down fail2!=" + strOptString7);
                                                                z4 = true;
                                                            }
                                                            Log.i(f4b, "IOUtils.downFile ok2");
                                                        } else {
                                                            Log.i(f4b, "down fail!=" + strOptString7);
                                                            z4 = true;
                                                        }
                                                    } else {
                                                        if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                            if (file.exists()) {
                                                                Log.i(f4b, "down fail2!=" + strOptString7);
                                                                z4 = true;
                                                            } else {
                                                                Log.i(f4b, "down fail2!=" + strOptString7);
                                                                z4 = true;
                                                            }
                                                            Log.i(f4b, "IOUtils.downFile ok2");
                                                        } else {
                                                            Log.i(f4b, "down fail!=" + strOptString7);
                                                            z4 = true;
                                                        }
                                                    }
                                                    if (file.exists()) {
                                                        file.delete();
                                                    }
                                                } else {
                                                    if (strOptString7.contains("com.android.providers.media")) {
                                                    }
                                                    StringBuilder sb18 = new StringBuilder();
                                                    str11 = str19;
                                                    sb18.append(str11);
                                                    sb18.append(ParserUtils.getContext().getPackageName());
                                                    str12 = str3;
                                                    sb18.append(str12);
                                                    sb18.append(strOptString7);
                                                    str13 = str15;
                                                    str14 = str2;
                                                    sb18.append(str14);
                                                    file = new File(sb18.toString());
                                                    if (file.exists()) {
                                                        file.delete();
                                                    }
                                                    if (!e) {
                                                    }
                                                    if (file.exists()) {
                                                        if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                            if (file.exists()) {
                                                                Log.i(f4b, "down fail2!=" + strOptString7);
                                                                z4 = true;
                                                            } else {
                                                                Log.i(f4b, "down fail2!=" + strOptString7);
                                                                z4 = true;
                                                            }
                                                            Log.i(f4b, "IOUtils.downFile ok2");
                                                        } else {
                                                            Log.i(f4b, "down fail!=" + strOptString7);
                                                            z4 = true;
                                                        }
                                                    } else {
                                                        if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                            if (file.exists()) {
                                                                Log.i(f4b, "down fail2!=" + strOptString7);
                                                                z4 = true;
                                                            } else {
                                                                Log.i(f4b, "down fail2!=" + strOptString7);
                                                                z4 = true;
                                                            }
                                                            Log.i(f4b, "IOUtils.downFile ok2");
                                                        } else {
                                                            Log.i(f4b, "down fail!=" + strOptString7);
                                                            z4 = true;
                                                        }
                                                    }
                                                    if (file.exists()) {
                                                        file.delete();
                                                    }
                                                }
                                            } else {
                                                if (!com.tools.c.c(strOptString7)) {
                                                }
                                                if (strOptString7.equals(str15)) {
                                                    if (com.tools.c.c(strOptString7)) {
                                                        Log.i(f4b, "com.disney.disneyplus 33 PackageNameExist reinstall");
                                                        if (com.tools.c.n(strOptString7) != iOptInt2) {
                                                            com.tools.c.v(ParserUtils.getContext(), str15);
                                                            Log.i(f4b, "com.disney.disneyplus 44 uninstall ok");
                                                        }
                                                        str12 = str3;
                                                        str11 = str19;
                                                        str13 = str15;
                                                        str14 = str2;
                                                        str16 = str16;
                                                    }
                                                }
                                                str10 = str22;
                                                if (!strOptString7.equals(str10)) {
                                                    if (strOptString7.contains("com.android.providers.media")) {
                                                    }
                                                    StringBuilder sb19 = new StringBuilder();
                                                    str11 = str19;
                                                    sb19.append(str11);
                                                    sb19.append(ParserUtils.getContext().getPackageName());
                                                    str12 = str3;
                                                    sb19.append(str12);
                                                    sb19.append(strOptString7);
                                                    str13 = str15;
                                                    str14 = str2;
                                                    sb19.append(str14);
                                                    file = new File(sb19.toString());
                                                    if (file.exists()) {
                                                        file.delete();
                                                    }
                                                    if (!e) {
                                                    }
                                                    if (file.exists()) {
                                                        if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                            if (file.exists()) {
                                                                Log.i(f4b, "down fail2!=" + strOptString7);
                                                                z4 = true;
                                                            } else {
                                                                Log.i(f4b, "down fail2!=" + strOptString7);
                                                                z4 = true;
                                                            }
                                                            Log.i(f4b, "IOUtils.downFile ok2");
                                                        } else {
                                                            Log.i(f4b, "down fail!=" + strOptString7);
                                                            z4 = true;
                                                        }
                                                    } else {
                                                        if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                            if (file.exists()) {
                                                                Log.i(f4b, "down fail2!=" + strOptString7);
                                                                z4 = true;
                                                            } else {
                                                                Log.i(f4b, "down fail2!=" + strOptString7);
                                                                z4 = true;
                                                            }
                                                            Log.i(f4b, "IOUtils.downFile ok2");
                                                        } else {
                                                            Log.i(f4b, "down fail!=" + strOptString7);
                                                            z4 = true;
                                                        }
                                                    }
                                                    if (file.exists()) {
                                                        file.delete();
                                                    }
                                                } else {
                                                    if (strOptString7.contains("com.android.providers.media")) {
                                                    }
                                                    StringBuilder sb110 = new StringBuilder();
                                                    str11 = str19;
                                                    sb110.append(str11);
                                                    sb110.append(ParserUtils.getContext().getPackageName());
                                                    str12 = str3;
                                                    sb110.append(str12);
                                                    sb110.append(strOptString7);
                                                    str13 = str15;
                                                    str14 = str2;
                                                    sb110.append(str14);
                                                    file = new File(sb110.toString());
                                                    if (file.exists()) {
                                                        file.delete();
                                                    }
                                                    if (!e) {
                                                    }
                                                    if (file.exists()) {
                                                        if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                            if (file.exists()) {
                                                                Log.i(f4b, "down fail2!=" + strOptString7);
                                                                z4 = true;
                                                            } else {
                                                                Log.i(f4b, "down fail2!=" + strOptString7);
                                                                z4 = true;
                                                            }
                                                            Log.i(f4b, "IOUtils.downFile ok2");
                                                        } else {
                                                            Log.i(f4b, "down fail!=" + strOptString7);
                                                            z4 = true;
                                                        }
                                                    } else {
                                                        if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                            if (file.exists()) {
                                                                Log.i(f4b, "down fail2!=" + strOptString7);
                                                                z4 = true;
                                                            } else {
                                                                Log.i(f4b, "down fail2!=" + strOptString7);
                                                                z4 = true;
                                                            }
                                                            Log.i(f4b, "IOUtils.downFile ok2");
                                                        } else {
                                                            Log.i(f4b, "down fail!=" + strOptString7);
                                                            z4 = true;
                                                        }
                                                    }
                                                    if (file.exists()) {
                                                        file.delete();
                                                    }
                                                }
                                            }
                                        } else if (strOptString7.equals(str16)) {
                                            if (!com.tools.c.c(strOptString7)) {
                                                if (!com.tools.c.c(strOptString7)) {
                                                }
                                                if (strOptString7.equals(str15)) {
                                                    if (com.tools.c.c(strOptString7)) {
                                                        Log.i(f4b, "com.disney.disneyplus 33 PackageNameExist reinstall");
                                                        if (com.tools.c.n(strOptString7) != iOptInt2) {
                                                            com.tools.c.v(ParserUtils.getContext(), str15);
                                                            Log.i(f4b, "com.disney.disneyplus 44 uninstall ok");
                                                        }
                                                        str12 = str3;
                                                        str11 = str19;
                                                        str13 = str15;
                                                        str14 = str2;
                                                        str16 = str16;
                                                    }
                                                }
                                                str10 = str22;
                                                if (!strOptString7.equals(str10)) {
                                                    if (strOptString7.contains("com.android.providers.media")) {
                                                    }
                                                    StringBuilder sb111 = new StringBuilder();
                                                    str11 = str19;
                                                    sb111.append(str11);
                                                    sb111.append(ParserUtils.getContext().getPackageName());
                                                    str12 = str3;
                                                    sb111.append(str12);
                                                    sb111.append(strOptString7);
                                                    str13 = str15;
                                                    str14 = str2;
                                                    sb111.append(str14);
                                                    file = new File(sb111.toString());
                                                    if (file.exists()) {
                                                        file.delete();
                                                    }
                                                    if (!e) {
                                                    }
                                                    if (file.exists()) {
                                                        if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                            if (file.exists()) {
                                                                Log.i(f4b, "down fail2!=" + strOptString7);
                                                                z4 = true;
                                                            } else {
                                                                Log.i(f4b, "down fail2!=" + strOptString7);
                                                                z4 = true;
                                                            }
                                                            Log.i(f4b, "IOUtils.downFile ok2");
                                                        } else {
                                                            Log.i(f4b, "down fail!=" + strOptString7);
                                                            z4 = true;
                                                        }
                                                    } else {
                                                        if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                            if (file.exists()) {
                                                                Log.i(f4b, "down fail2!=" + strOptString7);
                                                                z4 = true;
                                                            } else {
                                                                Log.i(f4b, "down fail2!=" + strOptString7);
                                                                z4 = true;
                                                            }
                                                            Log.i(f4b, "IOUtils.downFile ok2");
                                                        } else {
                                                            Log.i(f4b, "down fail!=" + strOptString7);
                                                            z4 = true;
                                                        }
                                                    }
                                                    if (file.exists()) {
                                                        file.delete();
                                                    }
                                                } else {
                                                    if (strOptString7.contains("com.android.providers.media")) {
                                                    }
                                                    StringBuilder sb112 = new StringBuilder();
                                                    str11 = str19;
                                                    sb112.append(str11);
                                                    sb112.append(ParserUtils.getContext().getPackageName());
                                                    str12 = str3;
                                                    sb112.append(str12);
                                                    sb112.append(strOptString7);
                                                    str13 = str15;
                                                    str14 = str2;
                                                    sb112.append(str14);
                                                    file = new File(sb112.toString());
                                                    if (file.exists()) {
                                                        file.delete();
                                                    }
                                                    if (!e) {
                                                    }
                                                    if (file.exists()) {
                                                        if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                            if (file.exists()) {
                                                                Log.i(f4b, "down fail2!=" + strOptString7);
                                                                z4 = true;
                                                            } else {
                                                                Log.i(f4b, "down fail2!=" + strOptString7);
                                                                z4 = true;
                                                            }
                                                            Log.i(f4b, "IOUtils.downFile ok2");
                                                        } else {
                                                            Log.i(f4b, "down fail!=" + strOptString7);
                                                            z4 = true;
                                                        }
                                                    } else {
                                                        if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                            if (file.exists()) {
                                                                Log.i(f4b, "down fail2!=" + strOptString7);
                                                                z4 = true;
                                                            } else {
                                                                Log.i(f4b, "down fail2!=" + strOptString7);
                                                                z4 = true;
                                                            }
                                                            Log.i(f4b, "IOUtils.downFile ok2");
                                                        } else {
                                                            Log.i(f4b, "down fail!=" + strOptString7);
                                                            z4 = true;
                                                        }
                                                    }
                                                    if (file.exists()) {
                                                        file.delete();
                                                    }
                                                }
                                            } else {
                                                if (!com.tools.c.c(strOptString7)) {
                                                }
                                                if (strOptString7.equals(str15)) {
                                                    if (com.tools.c.c(strOptString7)) {
                                                        Log.i(f4b, "com.disney.disneyplus 33 PackageNameExist reinstall");
                                                        if (com.tools.c.n(strOptString7) != iOptInt2) {
                                                            com.tools.c.v(ParserUtils.getContext(), str15);
                                                            Log.i(f4b, "com.disney.disneyplus 44 uninstall ok");
                                                        }
                                                        str12 = str3;
                                                        str11 = str19;
                                                        str13 = str15;
                                                        str14 = str2;
                                                        str16 = str16;
                                                    }
                                                }
                                                str10 = str22;
                                                if (!strOptString7.equals(str10)) {
                                                    if (strOptString7.contains("com.android.providers.media")) {
                                                    }
                                                    StringBuilder sb113 = new StringBuilder();
                                                    str11 = str19;
                                                    sb113.append(str11);
                                                    sb113.append(ParserUtils.getContext().getPackageName());
                                                    str12 = str3;
                                                    sb113.append(str12);
                                                    sb113.append(strOptString7);
                                                    str13 = str15;
                                                    str14 = str2;
                                                    sb113.append(str14);
                                                    file = new File(sb113.toString());
                                                    if (file.exists()) {
                                                        file.delete();
                                                    }
                                                    if (!e) {
                                                    }
                                                    if (file.exists()) {
                                                        if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                            if (file.exists()) {
                                                                Log.i(f4b, "down fail2!=" + strOptString7);
                                                                z4 = true;
                                                            } else {
                                                                Log.i(f4b, "down fail2!=" + strOptString7);
                                                                z4 = true;
                                                            }
                                                            Log.i(f4b, "IOUtils.downFile ok2");
                                                        } else {
                                                            Log.i(f4b, "down fail!=" + strOptString7);
                                                            z4 = true;
                                                        }
                                                    } else {
                                                        if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                            if (file.exists()) {
                                                                Log.i(f4b, "down fail2!=" + strOptString7);
                                                                z4 = true;
                                                            } else {
                                                                Log.i(f4b, "down fail2!=" + strOptString7);
                                                                z4 = true;
                                                            }
                                                            Log.i(f4b, "IOUtils.downFile ok2");
                                                        } else {
                                                            Log.i(f4b, "down fail!=" + strOptString7);
                                                            z4 = true;
                                                        }
                                                    }
                                                    if (file.exists()) {
                                                        file.delete();
                                                    }
                                                } else {
                                                    if (strOptString7.contains("com.android.providers.media")) {
                                                    }
                                                    StringBuilder sb114 = new StringBuilder();
                                                    str11 = str19;
                                                    sb114.append(str11);
                                                    sb114.append(ParserUtils.getContext().getPackageName());
                                                    str12 = str3;
                                                    sb114.append(str12);
                                                    sb114.append(strOptString7);
                                                    str13 = str15;
                                                    str14 = str2;
                                                    sb114.append(str14);
                                                    file = new File(sb114.toString());
                                                    if (file.exists()) {
                                                        file.delete();
                                                    }
                                                    if (!e) {
                                                    }
                                                    if (file.exists()) {
                                                        if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                            if (file.exists()) {
                                                                Log.i(f4b, "down fail2!=" + strOptString7);
                                                                z4 = true;
                                                            } else {
                                                                Log.i(f4b, "down fail2!=" + strOptString7);
                                                                z4 = true;
                                                            }
                                                            Log.i(f4b, "IOUtils.downFile ok2");
                                                        } else {
                                                            Log.i(f4b, "down fail!=" + strOptString7);
                                                            z4 = true;
                                                        }
                                                    } else {
                                                        if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                            if (file.exists()) {
                                                                Log.i(f4b, "down fail2!=" + strOptString7);
                                                                z4 = true;
                                                            } else {
                                                                Log.i(f4b, "down fail2!=" + strOptString7);
                                                                z4 = true;
                                                            }
                                                            Log.i(f4b, "IOUtils.downFile ok2");
                                                        } else {
                                                            Log.i(f4b, "down fail!=" + strOptString7);
                                                            z4 = true;
                                                        }
                                                    }
                                                    if (file.exists()) {
                                                        file.delete();
                                                    }
                                                }
                                            }
                                        } else if (!com.tools.c.c(strOptString7)) {
                                            if (!com.tools.c.c(strOptString7)) {
                                            }
                                            if (strOptString7.equals(str15)) {
                                                if (com.tools.c.c(strOptString7)) {
                                                    Log.i(f4b, "com.disney.disneyplus 33 PackageNameExist reinstall");
                                                    if (com.tools.c.n(strOptString7) != iOptInt2) {
                                                        com.tools.c.v(ParserUtils.getContext(), str15);
                                                        Log.i(f4b, "com.disney.disneyplus 44 uninstall ok");
                                                    }
                                                    str12 = str3;
                                                    str11 = str19;
                                                    str13 = str15;
                                                    str14 = str2;
                                                    str16 = str16;
                                                }
                                            }
                                            str10 = str22;
                                            if (!strOptString7.equals(str10)) {
                                                if (strOptString7.contains("com.android.providers.media")) {
                                                }
                                                StringBuilder sb115 = new StringBuilder();
                                                str11 = str19;
                                                sb115.append(str11);
                                                sb115.append(ParserUtils.getContext().getPackageName());
                                                str12 = str3;
                                                sb115.append(str12);
                                                sb115.append(strOptString7);
                                                str13 = str15;
                                                str14 = str2;
                                                sb115.append(str14);
                                                file = new File(sb115.toString());
                                                if (file.exists()) {
                                                    file.delete();
                                                }
                                                if (!e) {
                                                }
                                                if (file.exists()) {
                                                    if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                        if (file.exists()) {
                                                            Log.i(f4b, "down fail2!=" + strOptString7);
                                                            z4 = true;
                                                        } else {
                                                            Log.i(f4b, "down fail2!=" + strOptString7);
                                                            z4 = true;
                                                        }
                                                        Log.i(f4b, "IOUtils.downFile ok2");
                                                    } else {
                                                        Log.i(f4b, "down fail!=" + strOptString7);
                                                        z4 = true;
                                                    }
                                                } else {
                                                    if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                        if (file.exists()) {
                                                            Log.i(f4b, "down fail2!=" + strOptString7);
                                                            z4 = true;
                                                        } else {
                                                            Log.i(f4b, "down fail2!=" + strOptString7);
                                                            z4 = true;
                                                        }
                                                        Log.i(f4b, "IOUtils.downFile ok2");
                                                    } else {
                                                        Log.i(f4b, "down fail!=" + strOptString7);
                                                        z4 = true;
                                                    }
                                                }
                                                if (file.exists()) {
                                                    file.delete();
                                                }
                                            } else {
                                                if (strOptString7.contains("com.android.providers.media")) {
                                                }
                                                StringBuilder sb116 = new StringBuilder();
                                                str11 = str19;
                                                sb116.append(str11);
                                                sb116.append(ParserUtils.getContext().getPackageName());
                                                str12 = str3;
                                                sb116.append(str12);
                                                sb116.append(strOptString7);
                                                str13 = str15;
                                                str14 = str2;
                                                sb116.append(str14);
                                                file = new File(sb116.toString());
                                                if (file.exists()) {
                                                    file.delete();
                                                }
                                                if (!e) {
                                                }
                                                if (file.exists()) {
                                                    if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                        if (file.exists()) {
                                                            Log.i(f4b, "down fail2!=" + strOptString7);
                                                            z4 = true;
                                                        } else {
                                                            Log.i(f4b, "down fail2!=" + strOptString7);
                                                            z4 = true;
                                                        }
                                                        Log.i(f4b, "IOUtils.downFile ok2");
                                                    } else {
                                                        Log.i(f4b, "down fail!=" + strOptString7);
                                                        z4 = true;
                                                    }
                                                } else {
                                                    if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                        if (file.exists()) {
                                                            Log.i(f4b, "down fail2!=" + strOptString7);
                                                            z4 = true;
                                                        } else {
                                                            Log.i(f4b, "down fail2!=" + strOptString7);
                                                            z4 = true;
                                                        }
                                                        Log.i(f4b, "IOUtils.downFile ok2");
                                                    } else {
                                                        Log.i(f4b, "down fail!=" + strOptString7);
                                                        z4 = true;
                                                    }
                                                }
                                                if (file.exists()) {
                                                    file.delete();
                                                }
                                            }
                                        } else {
                                            if (!com.tools.c.c(strOptString7)) {
                                            }
                                            if (strOptString7.equals(str15)) {
                                                if (com.tools.c.c(strOptString7)) {
                                                    Log.i(f4b, "com.disney.disneyplus 33 PackageNameExist reinstall");
                                                    if (com.tools.c.n(strOptString7) != iOptInt2) {
                                                        com.tools.c.v(ParserUtils.getContext(), str15);
                                                        Log.i(f4b, "com.disney.disneyplus 44 uninstall ok");
                                                    }
                                                    str12 = str3;
                                                    str11 = str19;
                                                    str13 = str15;
                                                    str14 = str2;
                                                    str16 = str16;
                                                }
                                            }
                                            str10 = str22;
                                            if (!strOptString7.equals(str10)) {
                                                if (strOptString7.contains("com.android.providers.media")) {
                                                }
                                                StringBuilder sb117 = new StringBuilder();
                                                str11 = str19;
                                                sb117.append(str11);
                                                sb117.append(ParserUtils.getContext().getPackageName());
                                                str12 = str3;
                                                sb117.append(str12);
                                                sb117.append(strOptString7);
                                                str13 = str15;
                                                str14 = str2;
                                                sb117.append(str14);
                                                file = new File(sb117.toString());
                                                if (file.exists()) {
                                                    file.delete();
                                                }
                                                if (!e) {
                                                }
                                                if (file.exists()) {
                                                    if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                        if (file.exists()) {
                                                            Log.i(f4b, "down fail2!=" + strOptString7);
                                                            z4 = true;
                                                        } else {
                                                            Log.i(f4b, "down fail2!=" + strOptString7);
                                                            z4 = true;
                                                        }
                                                        Log.i(f4b, "IOUtils.downFile ok2");
                                                    } else {
                                                        Log.i(f4b, "down fail!=" + strOptString7);
                                                        z4 = true;
                                                    }
                                                } else {
                                                    if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                        if (file.exists()) {
                                                            Log.i(f4b, "down fail2!=" + strOptString7);
                                                            z4 = true;
                                                        } else {
                                                            Log.i(f4b, "down fail2!=" + strOptString7);
                                                            z4 = true;
                                                        }
                                                        Log.i(f4b, "IOUtils.downFile ok2");
                                                    } else {
                                                        Log.i(f4b, "down fail!=" + strOptString7);
                                                        z4 = true;
                                                    }
                                                }
                                                if (file.exists()) {
                                                    file.delete();
                                                }
                                            } else {
                                                if (strOptString7.contains("com.android.providers.media")) {
                                                }
                                                StringBuilder sb118 = new StringBuilder();
                                                str11 = str19;
                                                sb118.append(str11);
                                                sb118.append(ParserUtils.getContext().getPackageName());
                                                str12 = str3;
                                                sb118.append(str12);
                                                sb118.append(strOptString7);
                                                str13 = str15;
                                                str14 = str2;
                                                sb118.append(str14);
                                                file = new File(sb118.toString());
                                                if (file.exists()) {
                                                    file.delete();
                                                }
                                                if (!e) {
                                                }
                                                if (file.exists()) {
                                                    if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                        if (file.exists()) {
                                                            Log.i(f4b, "down fail2!=" + strOptString7);
                                                            z4 = true;
                                                        } else {
                                                            Log.i(f4b, "down fail2!=" + strOptString7);
                                                            z4 = true;
                                                        }
                                                        Log.i(f4b, "IOUtils.downFile ok2");
                                                    } else {
                                                        Log.i(f4b, "down fail!=" + strOptString7);
                                                        z4 = true;
                                                    }
                                                } else {
                                                    if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                        if (file.exists()) {
                                                            Log.i(f4b, "down fail2!=" + strOptString7);
                                                            z4 = true;
                                                        } else {
                                                            Log.i(f4b, "down fail2!=" + strOptString7);
                                                            z4 = true;
                                                        }
                                                        Log.i(f4b, "IOUtils.downFile ok2");
                                                    } else {
                                                        Log.i(f4b, "down fail!=" + strOptString7);
                                                        z4 = true;
                                                    }
                                                }
                                                if (file.exists()) {
                                                    file.delete();
                                                }
                                            }
                                        }
                                        str10 = str22;
                                        str12 = str3;
                                        str11 = str19;
                                        str13 = str15;
                                        str14 = str2;
                                        str16 = str16;
                                    } else {
                                        if (strOptString7.equals("com.netflix.ninja")) {
                                            if (strOptString7.equals(str16)) {
                                                if (!com.tools.c.c(strOptString7)) {
                                                    if (!com.tools.c.c(strOptString7)) {
                                                    }
                                                    if (strOptString7.equals(str15)) {
                                                        if (com.tools.c.c(strOptString7)) {
                                                            Log.i(f4b, "com.disney.disneyplus 33 PackageNameExist reinstall");
                                                            if (com.tools.c.n(strOptString7) != iOptInt2) {
                                                                com.tools.c.v(ParserUtils.getContext(), str15);
                                                                Log.i(f4b, "com.disney.disneyplus 44 uninstall ok");
                                                            }
                                                            str12 = str3;
                                                            str11 = str19;
                                                            str13 = str15;
                                                            str14 = str2;
                                                            str16 = str16;
                                                        }
                                                    }
                                                    str10 = str22;
                                                    if (!strOptString7.equals(str10)) {
                                                        if (strOptString7.contains("com.android.providers.media")) {
                                                        }
                                                        StringBuilder sb119 = new StringBuilder();
                                                        str11 = str19;
                                                        sb119.append(str11);
                                                        sb119.append(ParserUtils.getContext().getPackageName());
                                                        str12 = str3;
                                                        sb119.append(str12);
                                                        sb119.append(strOptString7);
                                                        str13 = str15;
                                                        str14 = str2;
                                                        sb119.append(str14);
                                                        file = new File(sb119.toString());
                                                        if (file.exists()) {
                                                            file.delete();
                                                        }
                                                        if (!e) {
                                                        }
                                                        if (file.exists()) {
                                                            if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                                if (file.exists()) {
                                                                    Log.i(f4b, "down fail2!=" + strOptString7);
                                                                    z4 = true;
                                                                } else {
                                                                    Log.i(f4b, "down fail2!=" + strOptString7);
                                                                    z4 = true;
                                                                }
                                                                Log.i(f4b, "IOUtils.downFile ok2");
                                                            } else {
                                                                Log.i(f4b, "down fail!=" + strOptString7);
                                                                z4 = true;
                                                            }
                                                        } else {
                                                            if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                                if (file.exists()) {
                                                                    Log.i(f4b, "down fail2!=" + strOptString7);
                                                                    z4 = true;
                                                                } else {
                                                                    Log.i(f4b, "down fail2!=" + strOptString7);
                                                                    z4 = true;
                                                                }
                                                                Log.i(f4b, "IOUtils.downFile ok2");
                                                            } else {
                                                                Log.i(f4b, "down fail!=" + strOptString7);
                                                                z4 = true;
                                                            }
                                                        }
                                                        if (file.exists()) {
                                                            file.delete();
                                                        }
                                                    } else {
                                                        if (strOptString7.contains("com.android.providers.media")) {
                                                        }
                                                        StringBuilder sb1110 = new StringBuilder();
                                                        str11 = str19;
                                                        sb1110.append(str11);
                                                        sb1110.append(ParserUtils.getContext().getPackageName());
                                                        str12 = str3;
                                                        sb1110.append(str12);
                                                        sb1110.append(strOptString7);
                                                        str13 = str15;
                                                        str14 = str2;
                                                        sb1110.append(str14);
                                                        file = new File(sb1110.toString());
                                                        if (file.exists()) {
                                                            file.delete();
                                                        }
                                                        if (!e) {
                                                        }
                                                        if (file.exists()) {
                                                            if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                                if (file.exists()) {
                                                                    Log.i(f4b, "down fail2!=" + strOptString7);
                                                                    z4 = true;
                                                                } else {
                                                                    Log.i(f4b, "down fail2!=" + strOptString7);
                                                                    z4 = true;
                                                                }
                                                                Log.i(f4b, "IOUtils.downFile ok2");
                                                            } else {
                                                                Log.i(f4b, "down fail!=" + strOptString7);
                                                                z4 = true;
                                                            }
                                                        } else {
                                                            if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                                if (file.exists()) {
                                                                    Log.i(f4b, "down fail2!=" + strOptString7);
                                                                    z4 = true;
                                                                } else {
                                                                    Log.i(f4b, "down fail2!=" + strOptString7);
                                                                    z4 = true;
                                                                }
                                                                Log.i(f4b, "IOUtils.downFile ok2");
                                                            } else {
                                                                Log.i(f4b, "down fail!=" + strOptString7);
                                                                z4 = true;
                                                            }
                                                        }
                                                        if (file.exists()) {
                                                            file.delete();
                                                        }
                                                    }
                                                } else {
                                                    if (!com.tools.c.c(strOptString7)) {
                                                    }
                                                    if (strOptString7.equals(str15)) {
                                                        if (com.tools.c.c(strOptString7)) {
                                                            Log.i(f4b, "com.disney.disneyplus 33 PackageNameExist reinstall");
                                                            if (com.tools.c.n(strOptString7) != iOptInt2) {
                                                                com.tools.c.v(ParserUtils.getContext(), str15);
                                                                Log.i(f4b, "com.disney.disneyplus 44 uninstall ok");
                                                            }
                                                            str12 = str3;
                                                            str11 = str19;
                                                            str13 = str15;
                                                            str14 = str2;
                                                            str16 = str16;
                                                        }
                                                    }
                                                    str10 = str22;
                                                    if (!strOptString7.equals(str10)) {
                                                        if (strOptString7.contains("com.android.providers.media")) {
                                                        }
                                                        StringBuilder sb1111 = new StringBuilder();
                                                        str11 = str19;
                                                        sb1111.append(str11);
                                                        sb1111.append(ParserUtils.getContext().getPackageName());
                                                        str12 = str3;
                                                        sb1111.append(str12);
                                                        sb1111.append(strOptString7);
                                                        str13 = str15;
                                                        str14 = str2;
                                                        sb1111.append(str14);
                                                        file = new File(sb1111.toString());
                                                        if (file.exists()) {
                                                            file.delete();
                                                        }
                                                        if (!e) {
                                                        }
                                                        if (file.exists()) {
                                                            if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                                if (file.exists()) {
                                                                    Log.i(f4b, "down fail2!=" + strOptString7);
                                                                    z4 = true;
                                                                } else {
                                                                    Log.i(f4b, "down fail2!=" + strOptString7);
                                                                    z4 = true;
                                                                }
                                                                Log.i(f4b, "IOUtils.downFile ok2");
                                                            } else {
                                                                Log.i(f4b, "down fail!=" + strOptString7);
                                                                z4 = true;
                                                            }
                                                        } else {
                                                            if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                                if (file.exists()) {
                                                                    Log.i(f4b, "down fail2!=" + strOptString7);
                                                                    z4 = true;
                                                                } else {
                                                                    Log.i(f4b, "down fail2!=" + strOptString7);
                                                                    z4 = true;
                                                                }
                                                                Log.i(f4b, "IOUtils.downFile ok2");
                                                            } else {
                                                                Log.i(f4b, "down fail!=" + strOptString7);
                                                                z4 = true;
                                                            }
                                                        }
                                                        if (file.exists()) {
                                                            file.delete();
                                                        }
                                                    } else {
                                                        if (strOptString7.contains("com.android.providers.media")) {
                                                        }
                                                        StringBuilder sb1112 = new StringBuilder();
                                                        str11 = str19;
                                                        sb1112.append(str11);
                                                        sb1112.append(ParserUtils.getContext().getPackageName());
                                                        str12 = str3;
                                                        sb1112.append(str12);
                                                        sb1112.append(strOptString7);
                                                        str13 = str15;
                                                        str14 = str2;
                                                        sb1112.append(str14);
                                                        file = new File(sb1112.toString());
                                                        if (file.exists()) {
                                                            file.delete();
                                                        }
                                                        if (!e) {
                                                        }
                                                        if (file.exists()) {
                                                            if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                                if (file.exists()) {
                                                                    Log.i(f4b, "down fail2!=" + strOptString7);
                                                                    z4 = true;
                                                                } else {
                                                                    Log.i(f4b, "down fail2!=" + strOptString7);
                                                                    z4 = true;
                                                                }
                                                                Log.i(f4b, "IOUtils.downFile ok2");
                                                            } else {
                                                                Log.i(f4b, "down fail!=" + strOptString7);
                                                                z4 = true;
                                                            }
                                                        } else {
                                                            if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                                if (file.exists()) {
                                                                    Log.i(f4b, "down fail2!=" + strOptString7);
                                                                    z4 = true;
                                                                } else {
                                                                    Log.i(f4b, "down fail2!=" + strOptString7);
                                                                    z4 = true;
                                                                }
                                                                Log.i(f4b, "IOUtils.downFile ok2");
                                                            } else {
                                                                Log.i(f4b, "down fail!=" + strOptString7);
                                                                z4 = true;
                                                            }
                                                        }
                                                        if (file.exists()) {
                                                            file.delete();
                                                        }
                                                    }
                                                }
                                            } else if (!com.tools.c.c(strOptString7)) {
                                                if (!com.tools.c.c(strOptString7)) {
                                                }
                                                if (strOptString7.equals(str15)) {
                                                    if (com.tools.c.c(strOptString7)) {
                                                        Log.i(f4b, "com.disney.disneyplus 33 PackageNameExist reinstall");
                                                        if (com.tools.c.n(strOptString7) != iOptInt2) {
                                                            com.tools.c.v(ParserUtils.getContext(), str15);
                                                            Log.i(f4b, "com.disney.disneyplus 44 uninstall ok");
                                                        }
                                                        str12 = str3;
                                                        str11 = str19;
                                                        str13 = str15;
                                                        str14 = str2;
                                                        str16 = str16;
                                                    }
                                                }
                                                str10 = str22;
                                                if (!strOptString7.equals(str10)) {
                                                    if (strOptString7.contains("com.android.providers.media")) {
                                                    }
                                                    StringBuilder sb1113 = new StringBuilder();
                                                    str11 = str19;
                                                    sb1113.append(str11);
                                                    sb1113.append(ParserUtils.getContext().getPackageName());
                                                    str12 = str3;
                                                    sb1113.append(str12);
                                                    sb1113.append(strOptString7);
                                                    str13 = str15;
                                                    str14 = str2;
                                                    sb1113.append(str14);
                                                    file = new File(sb1113.toString());
                                                    if (file.exists()) {
                                                        file.delete();
                                                    }
                                                    if (!e) {
                                                    }
                                                    if (file.exists()) {
                                                        if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                            if (file.exists()) {
                                                                Log.i(f4b, "down fail2!=" + strOptString7);
                                                                z4 = true;
                                                            } else {
                                                                Log.i(f4b, "down fail2!=" + strOptString7);
                                                                z4 = true;
                                                            }
                                                            Log.i(f4b, "IOUtils.downFile ok2");
                                                        } else {
                                                            Log.i(f4b, "down fail!=" + strOptString7);
                                                            z4 = true;
                                                        }
                                                    } else {
                                                        if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                            if (file.exists()) {
                                                                Log.i(f4b, "down fail2!=" + strOptString7);
                                                                z4 = true;
                                                            } else {
                                                                Log.i(f4b, "down fail2!=" + strOptString7);
                                                                z4 = true;
                                                            }
                                                            Log.i(f4b, "IOUtils.downFile ok2");
                                                        } else {
                                                            Log.i(f4b, "down fail!=" + strOptString7);
                                                            z4 = true;
                                                        }
                                                    }
                                                    if (file.exists()) {
                                                        file.delete();
                                                    }
                                                } else {
                                                    if (strOptString7.contains("com.android.providers.media")) {
                                                    }
                                                    StringBuilder sb1114 = new StringBuilder();
                                                    str11 = str19;
                                                    sb1114.append(str11);
                                                    sb1114.append(ParserUtils.getContext().getPackageName());
                                                    str12 = str3;
                                                    sb1114.append(str12);
                                                    sb1114.append(strOptString7);
                                                    str13 = str15;
                                                    str14 = str2;
                                                    sb1114.append(str14);
                                                    file = new File(sb1114.toString());
                                                    if (file.exists()) {
                                                        file.delete();
                                                    }
                                                    if (!e) {
                                                    }
                                                    if (file.exists()) {
                                                        if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                            if (file.exists()) {
                                                                Log.i(f4b, "down fail2!=" + strOptString7);
                                                                z4 = true;
                                                            } else {
                                                                Log.i(f4b, "down fail2!=" + strOptString7);
                                                                z4 = true;
                                                            }
                                                            Log.i(f4b, "IOUtils.downFile ok2");
                                                        } else {
                                                            Log.i(f4b, "down fail!=" + strOptString7);
                                                            z4 = true;
                                                        }
                                                    } else {
                                                        if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                            if (file.exists()) {
                                                                Log.i(f4b, "down fail2!=" + strOptString7);
                                                                z4 = true;
                                                            } else {
                                                                Log.i(f4b, "down fail2!=" + strOptString7);
                                                                z4 = true;
                                                            }
                                                            Log.i(f4b, "IOUtils.downFile ok2");
                                                        } else {
                                                            Log.i(f4b, "down fail!=" + strOptString7);
                                                            z4 = true;
                                                        }
                                                    }
                                                    if (file.exists()) {
                                                        file.delete();
                                                    }
                                                }
                                            } else {
                                                if (!com.tools.c.c(strOptString7)) {
                                                }
                                                if (strOptString7.equals(str15)) {
                                                    if (com.tools.c.c(strOptString7)) {
                                                        Log.i(f4b, "com.disney.disneyplus 33 PackageNameExist reinstall");
                                                        if (com.tools.c.n(strOptString7) != iOptInt2) {
                                                            com.tools.c.v(ParserUtils.getContext(), str15);
                                                            Log.i(f4b, "com.disney.disneyplus 44 uninstall ok");
                                                        }
                                                        str12 = str3;
                                                        str11 = str19;
                                                        str13 = str15;
                                                        str14 = str2;
                                                        str16 = str16;
                                                    }
                                                }
                                                str10 = str22;
                                                if (!strOptString7.equals(str10)) {
                                                    if (strOptString7.contains("com.android.providers.media")) {
                                                    }
                                                    StringBuilder sb1115 = new StringBuilder();
                                                    str11 = str19;
                                                    sb1115.append(str11);
                                                    sb1115.append(ParserUtils.getContext().getPackageName());
                                                    str12 = str3;
                                                    sb1115.append(str12);
                                                    sb1115.append(strOptString7);
                                                    str13 = str15;
                                                    str14 = str2;
                                                    sb1115.append(str14);
                                                    file = new File(sb1115.toString());
                                                    if (file.exists()) {
                                                        file.delete();
                                                    }
                                                    if (!e) {
                                                    }
                                                    if (file.exists()) {
                                                        if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                            if (file.exists()) {
                                                                Log.i(f4b, "down fail2!=" + strOptString7);
                                                                z4 = true;
                                                            } else {
                                                                Log.i(f4b, "down fail2!=" + strOptString7);
                                                                z4 = true;
                                                            }
                                                            Log.i(f4b, "IOUtils.downFile ok2");
                                                        } else {
                                                            Log.i(f4b, "down fail!=" + strOptString7);
                                                            z4 = true;
                                                        }
                                                    } else {
                                                        if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                            if (file.exists()) {
                                                                Log.i(f4b, "down fail2!=" + strOptString7);
                                                                z4 = true;
                                                            } else {
                                                                Log.i(f4b, "down fail2!=" + strOptString7);
                                                                z4 = true;
                                                            }
                                                            Log.i(f4b, "IOUtils.downFile ok2");
                                                        } else {
                                                            Log.i(f4b, "down fail!=" + strOptString7);
                                                            z4 = true;
                                                        }
                                                    }
                                                    if (file.exists()) {
                                                        file.delete();
                                                    }
                                                } else {
                                                    if (strOptString7.contains("com.android.providers.media")) {
                                                    }
                                                    StringBuilder sb1116 = new StringBuilder();
                                                    str11 = str19;
                                                    sb1116.append(str11);
                                                    sb1116.append(ParserUtils.getContext().getPackageName());
                                                    str12 = str3;
                                                    sb1116.append(str12);
                                                    sb1116.append(strOptString7);
                                                    str13 = str15;
                                                    str14 = str2;
                                                    sb1116.append(str14);
                                                    file = new File(sb1116.toString());
                                                    if (file.exists()) {
                                                        file.delete();
                                                    }
                                                    if (!e) {
                                                    }
                                                    if (file.exists()) {
                                                        if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                            if (file.exists()) {
                                                                Log.i(f4b, "down fail2!=" + strOptString7);
                                                                z4 = true;
                                                            } else {
                                                                Log.i(f4b, "down fail2!=" + strOptString7);
                                                                z4 = true;
                                                            }
                                                            Log.i(f4b, "IOUtils.downFile ok2");
                                                        } else {
                                                            Log.i(f4b, "down fail!=" + strOptString7);
                                                            z4 = true;
                                                        }
                                                    } else {
                                                        if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                            if (file.exists()) {
                                                                Log.i(f4b, "down fail2!=" + strOptString7);
                                                                z4 = true;
                                                            } else {
                                                                Log.i(f4b, "down fail2!=" + strOptString7);
                                                                z4 = true;
                                                            }
                                                            Log.i(f4b, "IOUtils.downFile ok2");
                                                        } else {
                                                            Log.i(f4b, "down fail!=" + strOptString7);
                                                            z4 = true;
                                                        }
                                                    }
                                                    if (file.exists()) {
                                                        file.delete();
                                                    }
                                                }
                                            }
                                        } else if (strOptString7.equals(str16)) {
                                            if (!com.tools.c.c(strOptString7)) {
                                                if (!com.tools.c.c(strOptString7)) {
                                                }
                                                if (strOptString7.equals(str15)) {
                                                    if (com.tools.c.c(strOptString7)) {
                                                        Log.i(f4b, "com.disney.disneyplus 33 PackageNameExist reinstall");
                                                        if (com.tools.c.n(strOptString7) != iOptInt2) {
                                                            com.tools.c.v(ParserUtils.getContext(), str15);
                                                            Log.i(f4b, "com.disney.disneyplus 44 uninstall ok");
                                                        }
                                                        str12 = str3;
                                                        str11 = str19;
                                                        str13 = str15;
                                                        str14 = str2;
                                                        str16 = str16;
                                                    }
                                                }
                                                str10 = str22;
                                                if (!strOptString7.equals(str10)) {
                                                    if (strOptString7.contains("com.android.providers.media")) {
                                                    }
                                                    StringBuilder sb1117 = new StringBuilder();
                                                    str11 = str19;
                                                    sb1117.append(str11);
                                                    sb1117.append(ParserUtils.getContext().getPackageName());
                                                    str12 = str3;
                                                    sb1117.append(str12);
                                                    sb1117.append(strOptString7);
                                                    str13 = str15;
                                                    str14 = str2;
                                                    sb1117.append(str14);
                                                    file = new File(sb1117.toString());
                                                    if (file.exists()) {
                                                        file.delete();
                                                    }
                                                    if (!e) {
                                                    }
                                                    if (file.exists()) {
                                                        if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                            if (file.exists()) {
                                                                Log.i(f4b, "down fail2!=" + strOptString7);
                                                                z4 = true;
                                                            } else {
                                                                Log.i(f4b, "down fail2!=" + strOptString7);
                                                                z4 = true;
                                                            }
                                                            Log.i(f4b, "IOUtils.downFile ok2");
                                                        } else {
                                                            Log.i(f4b, "down fail!=" + strOptString7);
                                                            z4 = true;
                                                        }
                                                    } else {
                                                        if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                            if (file.exists()) {
                                                                Log.i(f4b, "down fail2!=" + strOptString7);
                                                                z4 = true;
                                                            } else {
                                                                Log.i(f4b, "down fail2!=" + strOptString7);
                                                                z4 = true;
                                                            }
                                                            Log.i(f4b, "IOUtils.downFile ok2");
                                                        } else {
                                                            Log.i(f4b, "down fail!=" + strOptString7);
                                                            z4 = true;
                                                        }
                                                    }
                                                    if (file.exists()) {
                                                        file.delete();
                                                    }
                                                } else {
                                                    if (strOptString7.contains("com.android.providers.media")) {
                                                    }
                                                    StringBuilder sb1118 = new StringBuilder();
                                                    str11 = str19;
                                                    sb1118.append(str11);
                                                    sb1118.append(ParserUtils.getContext().getPackageName());
                                                    str12 = str3;
                                                    sb1118.append(str12);
                                                    sb1118.append(strOptString7);
                                                    str13 = str15;
                                                    str14 = str2;
                                                    sb1118.append(str14);
                                                    file = new File(sb1118.toString());
                                                    if (file.exists()) {
                                                        file.delete();
                                                    }
                                                    if (!e) {
                                                    }
                                                    if (file.exists()) {
                                                        if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                            if (file.exists()) {
                                                                Log.i(f4b, "down fail2!=" + strOptString7);
                                                                z4 = true;
                                                            } else {
                                                                Log.i(f4b, "down fail2!=" + strOptString7);
                                                                z4 = true;
                                                            }
                                                            Log.i(f4b, "IOUtils.downFile ok2");
                                                        } else {
                                                            Log.i(f4b, "down fail!=" + strOptString7);
                                                            z4 = true;
                                                        }
                                                    } else {
                                                        if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                            if (file.exists()) {
                                                                Log.i(f4b, "down fail2!=" + strOptString7);
                                                                z4 = true;
                                                            } else {
                                                                Log.i(f4b, "down fail2!=" + strOptString7);
                                                                z4 = true;
                                                            }
                                                            Log.i(f4b, "IOUtils.downFile ok2");
                                                        } else {
                                                            Log.i(f4b, "down fail!=" + strOptString7);
                                                            z4 = true;
                                                        }
                                                    }
                                                    if (file.exists()) {
                                                        file.delete();
                                                    }
                                                }
                                            } else {
                                                if (!com.tools.c.c(strOptString7)) {
                                                }
                                                if (strOptString7.equals(str15)) {
                                                    if (com.tools.c.c(strOptString7)) {
                                                        Log.i(f4b, "com.disney.disneyplus 33 PackageNameExist reinstall");
                                                        if (com.tools.c.n(strOptString7) != iOptInt2) {
                                                            com.tools.c.v(ParserUtils.getContext(), str15);
                                                            Log.i(f4b, "com.disney.disneyplus 44 uninstall ok");
                                                        }
                                                        str12 = str3;
                                                        str11 = str19;
                                                        str13 = str15;
                                                        str14 = str2;
                                                        str16 = str16;
                                                    }
                                                }
                                                str10 = str22;
                                                if (!strOptString7.equals(str10)) {
                                                    if (strOptString7.contains("com.android.providers.media")) {
                                                    }
                                                    StringBuilder sb1119 = new StringBuilder();
                                                    str11 = str19;
                                                    sb1119.append(str11);
                                                    sb1119.append(ParserUtils.getContext().getPackageName());
                                                    str12 = str3;
                                                    sb1119.append(str12);
                                                    sb1119.append(strOptString7);
                                                    str13 = str15;
                                                    str14 = str2;
                                                    sb1119.append(str14);
                                                    file = new File(sb1119.toString());
                                                    if (file.exists()) {
                                                        file.delete();
                                                    }
                                                    if (!e) {
                                                    }
                                                    if (file.exists()) {
                                                        if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                            if (file.exists()) {
                                                                Log.i(f4b, "down fail2!=" + strOptString7);
                                                                z4 = true;
                                                            } else {
                                                                Log.i(f4b, "down fail2!=" + strOptString7);
                                                                z4 = true;
                                                            }
                                                            Log.i(f4b, "IOUtils.downFile ok2");
                                                        } else {
                                                            Log.i(f4b, "down fail!=" + strOptString7);
                                                            z4 = true;
                                                        }
                                                    } else {
                                                        if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                            if (file.exists()) {
                                                                Log.i(f4b, "down fail2!=" + strOptString7);
                                                                z4 = true;
                                                            } else {
                                                                Log.i(f4b, "down fail2!=" + strOptString7);
                                                                z4 = true;
                                                            }
                                                            Log.i(f4b, "IOUtils.downFile ok2");
                                                        } else {
                                                            Log.i(f4b, "down fail!=" + strOptString7);
                                                            z4 = true;
                                                        }
                                                    }
                                                    if (file.exists()) {
                                                        file.delete();
                                                    }
                                                } else {
                                                    if (strOptString7.contains("com.android.providers.media")) {
                                                    }
                                                    StringBuilder sb11110 = new StringBuilder();
                                                    str11 = str19;
                                                    sb11110.append(str11);
                                                    sb11110.append(ParserUtils.getContext().getPackageName());
                                                    str12 = str3;
                                                    sb11110.append(str12);
                                                    sb11110.append(strOptString7);
                                                    str13 = str15;
                                                    str14 = str2;
                                                    sb11110.append(str14);
                                                    file = new File(sb11110.toString());
                                                    if (file.exists()) {
                                                        file.delete();
                                                    }
                                                    if (!e) {
                                                    }
                                                    if (file.exists()) {
                                                        if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                            if (file.exists()) {
                                                                Log.i(f4b, "down fail2!=" + strOptString7);
                                                                z4 = true;
                                                            } else {
                                                                Log.i(f4b, "down fail2!=" + strOptString7);
                                                                z4 = true;
                                                            }
                                                            Log.i(f4b, "IOUtils.downFile ok2");
                                                        } else {
                                                            Log.i(f4b, "down fail!=" + strOptString7);
                                                            z4 = true;
                                                        }
                                                    } else {
                                                        if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                            if (file.exists()) {
                                                                Log.i(f4b, "down fail2!=" + strOptString7);
                                                                z4 = true;
                                                            } else {
                                                                Log.i(f4b, "down fail2!=" + strOptString7);
                                                                z4 = true;
                                                            }
                                                            Log.i(f4b, "IOUtils.downFile ok2");
                                                        } else {
                                                            Log.i(f4b, "down fail!=" + strOptString7);
                                                            z4 = true;
                                                        }
                                                    }
                                                    if (file.exists()) {
                                                        file.delete();
                                                    }
                                                }
                                            }
                                        } else if (!com.tools.c.c(strOptString7)) {
                                            if (!com.tools.c.c(strOptString7)) {
                                            }
                                            if (strOptString7.equals(str15)) {
                                                if (com.tools.c.c(strOptString7)) {
                                                    Log.i(f4b, "com.disney.disneyplus 33 PackageNameExist reinstall");
                                                    if (com.tools.c.n(strOptString7) != iOptInt2) {
                                                        com.tools.c.v(ParserUtils.getContext(), str15);
                                                        Log.i(f4b, "com.disney.disneyplus 44 uninstall ok");
                                                    }
                                                    str12 = str3;
                                                    str11 = str19;
                                                    str13 = str15;
                                                    str14 = str2;
                                                    str16 = str16;
                                                }
                                            }
                                            str10 = str22;
                                            if (!strOptString7.equals(str10)) {
                                                if (strOptString7.contains("com.android.providers.media")) {
                                                }
                                                StringBuilder sb11111 = new StringBuilder();
                                                str11 = str19;
                                                sb11111.append(str11);
                                                sb11111.append(ParserUtils.getContext().getPackageName());
                                                str12 = str3;
                                                sb11111.append(str12);
                                                sb11111.append(strOptString7);
                                                str13 = str15;
                                                str14 = str2;
                                                sb11111.append(str14);
                                                file = new File(sb11111.toString());
                                                if (file.exists()) {
                                                    file.delete();
                                                }
                                                if (!e) {
                                                }
                                                if (file.exists()) {
                                                    if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                        if (file.exists()) {
                                                            Log.i(f4b, "down fail2!=" + strOptString7);
                                                            z4 = true;
                                                        } else {
                                                            Log.i(f4b, "down fail2!=" + strOptString7);
                                                            z4 = true;
                                                        }
                                                        Log.i(f4b, "IOUtils.downFile ok2");
                                                    } else {
                                                        Log.i(f4b, "down fail!=" + strOptString7);
                                                        z4 = true;
                                                    }
                                                } else {
                                                    if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                        if (file.exists()) {
                                                            Log.i(f4b, "down fail2!=" + strOptString7);
                                                            z4 = true;
                                                        } else {
                                                            Log.i(f4b, "down fail2!=" + strOptString7);
                                                            z4 = true;
                                                        }
                                                        Log.i(f4b, "IOUtils.downFile ok2");
                                                    } else {
                                                        Log.i(f4b, "down fail!=" + strOptString7);
                                                        z4 = true;
                                                    }
                                                }
                                                if (file.exists()) {
                                                    file.delete();
                                                }
                                            } else {
                                                if (strOptString7.contains("com.android.providers.media")) {
                                                }
                                                StringBuilder sb11112 = new StringBuilder();
                                                str11 = str19;
                                                sb11112.append(str11);
                                                sb11112.append(ParserUtils.getContext().getPackageName());
                                                str12 = str3;
                                                sb11112.append(str12);
                                                sb11112.append(strOptString7);
                                                str13 = str15;
                                                str14 = str2;
                                                sb11112.append(str14);
                                                file = new File(sb11112.toString());
                                                if (file.exists()) {
                                                    file.delete();
                                                }
                                                if (!e) {
                                                }
                                                if (file.exists()) {
                                                    if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                        if (file.exists()) {
                                                            Log.i(f4b, "down fail2!=" + strOptString7);
                                                            z4 = true;
                                                        } else {
                                                            Log.i(f4b, "down fail2!=" + strOptString7);
                                                            z4 = true;
                                                        }
                                                        Log.i(f4b, "IOUtils.downFile ok2");
                                                    } else {
                                                        Log.i(f4b, "down fail!=" + strOptString7);
                                                        z4 = true;
                                                    }
                                                } else {
                                                    if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                        if (file.exists()) {
                                                            Log.i(f4b, "down fail2!=" + strOptString7);
                                                            z4 = true;
                                                        } else {
                                                            Log.i(f4b, "down fail2!=" + strOptString7);
                                                            z4 = true;
                                                        }
                                                        Log.i(f4b, "IOUtils.downFile ok2");
                                                    } else {
                                                        Log.i(f4b, "down fail!=" + strOptString7);
                                                        z4 = true;
                                                    }
                                                }
                                                if (file.exists()) {
                                                    file.delete();
                                                }
                                            }
                                        } else {
                                            if (!com.tools.c.c(strOptString7)) {
                                            }
                                            if (strOptString7.equals(str15)) {
                                                if (com.tools.c.c(strOptString7)) {
                                                    Log.i(f4b, "com.disney.disneyplus 33 PackageNameExist reinstall");
                                                    if (com.tools.c.n(strOptString7) != iOptInt2) {
                                                        com.tools.c.v(ParserUtils.getContext(), str15);
                                                        Log.i(f4b, "com.disney.disneyplus 44 uninstall ok");
                                                    }
                                                    str12 = str3;
                                                    str11 = str19;
                                                    str13 = str15;
                                                    str14 = str2;
                                                    str16 = str16;
                                                }
                                            }
                                            str10 = str22;
                                            if (!strOptString7.equals(str10)) {
                                                if (strOptString7.contains("com.android.providers.media")) {
                                                }
                                                StringBuilder sb11113 = new StringBuilder();
                                                str11 = str19;
                                                sb11113.append(str11);
                                                sb11113.append(ParserUtils.getContext().getPackageName());
                                                str12 = str3;
                                                sb11113.append(str12);
                                                sb11113.append(strOptString7);
                                                str13 = str15;
                                                str14 = str2;
                                                sb11113.append(str14);
                                                file = new File(sb11113.toString());
                                                if (file.exists()) {
                                                    file.delete();
                                                }
                                                if (!e) {
                                                }
                                                if (file.exists()) {
                                                    if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                        if (file.exists()) {
                                                            Log.i(f4b, "down fail2!=" + strOptString7);
                                                            z4 = true;
                                                        } else {
                                                            Log.i(f4b, "down fail2!=" + strOptString7);
                                                            z4 = true;
                                                        }
                                                        Log.i(f4b, "IOUtils.downFile ok2");
                                                    } else {
                                                        Log.i(f4b, "down fail!=" + strOptString7);
                                                        z4 = true;
                                                    }
                                                } else {
                                                    if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                        if (file.exists()) {
                                                            Log.i(f4b, "down fail2!=" + strOptString7);
                                                            z4 = true;
                                                        } else {
                                                            Log.i(f4b, "down fail2!=" + strOptString7);
                                                            z4 = true;
                                                        }
                                                        Log.i(f4b, "IOUtils.downFile ok2");
                                                    } else {
                                                        Log.i(f4b, "down fail!=" + strOptString7);
                                                        z4 = true;
                                                    }
                                                }
                                                if (file.exists()) {
                                                    file.delete();
                                                }
                                            } else {
                                                if (strOptString7.contains("com.android.providers.media")) {
                                                }
                                                StringBuilder sb11114 = new StringBuilder();
                                                str11 = str19;
                                                sb11114.append(str11);
                                                sb11114.append(ParserUtils.getContext().getPackageName());
                                                str12 = str3;
                                                sb11114.append(str12);
                                                sb11114.append(strOptString7);
                                                str13 = str15;
                                                str14 = str2;
                                                sb11114.append(str14);
                                                file = new File(sb11114.toString());
                                                if (file.exists()) {
                                                    file.delete();
                                                }
                                                if (!e) {
                                                }
                                                if (file.exists()) {
                                                    if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                        if (file.exists()) {
                                                            Log.i(f4b, "down fail2!=" + strOptString7);
                                                            z4 = true;
                                                        } else {
                                                            Log.i(f4b, "down fail2!=" + strOptString7);
                                                            z4 = true;
                                                        }
                                                        Log.i(f4b, "IOUtils.downFile ok2");
                                                    } else {
                                                        Log.i(f4b, "down fail!=" + strOptString7);
                                                        z4 = true;
                                                    }
                                                } else {
                                                    if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                        if (file.exists()) {
                                                            Log.i(f4b, "down fail2!=" + strOptString7);
                                                            z4 = true;
                                                        } else {
                                                            Log.i(f4b, "down fail2!=" + strOptString7);
                                                            z4 = true;
                                                        }
                                                        Log.i(f4b, "IOUtils.downFile ok2");
                                                    } else {
                                                        Log.i(f4b, "down fail!=" + strOptString7);
                                                        z4 = true;
                                                    }
                                                }
                                                if (file.exists()) {
                                                    file.delete();
                                                }
                                            }
                                        }
                                        str10 = str22;
                                        str12 = str3;
                                        str11 = str19;
                                        str13 = str15;
                                        str14 = str2;
                                        str16 = str16;
                                    }
                                } else if (strOptString6.equals("UNAPP")) {
                                    str10 = str22;
                                    str12 = str3;
                                    str11 = str19;
                                    str13 = str15;
                                    str14 = str2;
                                    str16 = str16;
                                } else if (strOptString7.equals("com.netflix.ninja")) {
                                    if (strOptString7.equals("com.netflix.ninja")) {
                                        if (strOptString7.equals(str16)) {
                                            if (!com.tools.c.c(strOptString7)) {
                                                if (!com.tools.c.c(strOptString7)) {
                                                }
                                                if (strOptString7.equals(str15)) {
                                                    if (com.tools.c.c(strOptString7)) {
                                                        Log.i(f4b, "com.disney.disneyplus 33 PackageNameExist reinstall");
                                                        if (com.tools.c.n(strOptString7) != iOptInt2) {
                                                            com.tools.c.v(ParserUtils.getContext(), str15);
                                                            Log.i(f4b, "com.disney.disneyplus 44 uninstall ok");
                                                        }
                                                        str12 = str3;
                                                        str11 = str19;
                                                        str13 = str15;
                                                        str14 = str2;
                                                        str16 = str16;
                                                    }
                                                }
                                                str10 = str22;
                                                if (!strOptString7.equals(str10)) {
                                                    if (strOptString7.contains("com.android.providers.media")) {
                                                    }
                                                    StringBuilder sb11115 = new StringBuilder();
                                                    str11 = str19;
                                                    sb11115.append(str11);
                                                    sb11115.append(ParserUtils.getContext().getPackageName());
                                                    str12 = str3;
                                                    sb11115.append(str12);
                                                    sb11115.append(strOptString7);
                                                    str13 = str15;
                                                    str14 = str2;
                                                    sb11115.append(str14);
                                                    file = new File(sb11115.toString());
                                                    if (file.exists()) {
                                                        file.delete();
                                                    }
                                                    if (!e) {
                                                    }
                                                    if (file.exists()) {
                                                        if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                            if (file.exists()) {
                                                                Log.i(f4b, "down fail2!=" + strOptString7);
                                                                z4 = true;
                                                            } else {
                                                                Log.i(f4b, "down fail2!=" + strOptString7);
                                                                z4 = true;
                                                            }
                                                            Log.i(f4b, "IOUtils.downFile ok2");
                                                        } else {
                                                            Log.i(f4b, "down fail!=" + strOptString7);
                                                            z4 = true;
                                                        }
                                                    } else {
                                                        if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                            if (file.exists()) {
                                                                Log.i(f4b, "down fail2!=" + strOptString7);
                                                                z4 = true;
                                                            } else {
                                                                Log.i(f4b, "down fail2!=" + strOptString7);
                                                                z4 = true;
                                                            }
                                                            Log.i(f4b, "IOUtils.downFile ok2");
                                                        } else {
                                                            Log.i(f4b, "down fail!=" + strOptString7);
                                                            z4 = true;
                                                        }
                                                    }
                                                    if (file.exists()) {
                                                        file.delete();
                                                    }
                                                } else {
                                                    if (strOptString7.contains("com.android.providers.media")) {
                                                    }
                                                    StringBuilder sb11116 = new StringBuilder();
                                                    str11 = str19;
                                                    sb11116.append(str11);
                                                    sb11116.append(ParserUtils.getContext().getPackageName());
                                                    str12 = str3;
                                                    sb11116.append(str12);
                                                    sb11116.append(strOptString7);
                                                    str13 = str15;
                                                    str14 = str2;
                                                    sb11116.append(str14);
                                                    file = new File(sb11116.toString());
                                                    if (file.exists()) {
                                                        file.delete();
                                                    }
                                                    if (!e) {
                                                    }
                                                    if (file.exists()) {
                                                        if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                            if (file.exists()) {
                                                                Log.i(f4b, "down fail2!=" + strOptString7);
                                                                z4 = true;
                                                            } else {
                                                                Log.i(f4b, "down fail2!=" + strOptString7);
                                                                z4 = true;
                                                            }
                                                            Log.i(f4b, "IOUtils.downFile ok2");
                                                        } else {
                                                            Log.i(f4b, "down fail!=" + strOptString7);
                                                            z4 = true;
                                                        }
                                                    } else {
                                                        if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                            if (file.exists()) {
                                                                Log.i(f4b, "down fail2!=" + strOptString7);
                                                                z4 = true;
                                                            } else {
                                                                Log.i(f4b, "down fail2!=" + strOptString7);
                                                                z4 = true;
                                                            }
                                                            Log.i(f4b, "IOUtils.downFile ok2");
                                                        } else {
                                                            Log.i(f4b, "down fail!=" + strOptString7);
                                                            z4 = true;
                                                        }
                                                    }
                                                    if (file.exists()) {
                                                        file.delete();
                                                    }
                                                }
                                            } else {
                                                if (!com.tools.c.c(strOptString7)) {
                                                }
                                                if (strOptString7.equals(str15)) {
                                                    if (com.tools.c.c(strOptString7)) {
                                                        Log.i(f4b, "com.disney.disneyplus 33 PackageNameExist reinstall");
                                                        if (com.tools.c.n(strOptString7) != iOptInt2) {
                                                            com.tools.c.v(ParserUtils.getContext(), str15);
                                                            Log.i(f4b, "com.disney.disneyplus 44 uninstall ok");
                                                        }
                                                        str12 = str3;
                                                        str11 = str19;
                                                        str13 = str15;
                                                        str14 = str2;
                                                        str16 = str16;
                                                    }
                                                }
                                                str10 = str22;
                                                if (!strOptString7.equals(str10)) {
                                                    if (strOptString7.contains("com.android.providers.media")) {
                                                    }
                                                    StringBuilder sb11117 = new StringBuilder();
                                                    str11 = str19;
                                                    sb11117.append(str11);
                                                    sb11117.append(ParserUtils.getContext().getPackageName());
                                                    str12 = str3;
                                                    sb11117.append(str12);
                                                    sb11117.append(strOptString7);
                                                    str13 = str15;
                                                    str14 = str2;
                                                    sb11117.append(str14);
                                                    file = new File(sb11117.toString());
                                                    if (file.exists()) {
                                                        file.delete();
                                                    }
                                                    if (!e) {
                                                    }
                                                    if (file.exists()) {
                                                        if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                            if (file.exists()) {
                                                                Log.i(f4b, "down fail2!=" + strOptString7);
                                                                z4 = true;
                                                            } else {
                                                                Log.i(f4b, "down fail2!=" + strOptString7);
                                                                z4 = true;
                                                            }
                                                            Log.i(f4b, "IOUtils.downFile ok2");
                                                        } else {
                                                            Log.i(f4b, "down fail!=" + strOptString7);
                                                            z4 = true;
                                                        }
                                                    } else {
                                                        if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                            if (file.exists()) {
                                                                Log.i(f4b, "down fail2!=" + strOptString7);
                                                                z4 = true;
                                                            } else {
                                                                Log.i(f4b, "down fail2!=" + strOptString7);
                                                                z4 = true;
                                                            }
                                                            Log.i(f4b, "IOUtils.downFile ok2");
                                                        } else {
                                                            Log.i(f4b, "down fail!=" + strOptString7);
                                                            z4 = true;
                                                        }
                                                    }
                                                    if (file.exists()) {
                                                        file.delete();
                                                    }
                                                } else {
                                                    if (strOptString7.contains("com.android.providers.media")) {
                                                    }
                                                    StringBuilder sb11118 = new StringBuilder();
                                                    str11 = str19;
                                                    sb11118.append(str11);
                                                    sb11118.append(ParserUtils.getContext().getPackageName());
                                                    str12 = str3;
                                                    sb11118.append(str12);
                                                    sb11118.append(strOptString7);
                                                    str13 = str15;
                                                    str14 = str2;
                                                    sb11118.append(str14);
                                                    file = new File(sb11118.toString());
                                                    if (file.exists()) {
                                                        file.delete();
                                                    }
                                                    if (!e) {
                                                    }
                                                    if (file.exists()) {
                                                        if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                            if (file.exists()) {
                                                                Log.i(f4b, "down fail2!=" + strOptString7);
                                                                z4 = true;
                                                            } else {
                                                                Log.i(f4b, "down fail2!=" + strOptString7);
                                                                z4 = true;
                                                            }
                                                            Log.i(f4b, "IOUtils.downFile ok2");
                                                        } else {
                                                            Log.i(f4b, "down fail!=" + strOptString7);
                                                            z4 = true;
                                                        }
                                                    } else {
                                                        if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                            if (file.exists()) {
                                                                Log.i(f4b, "down fail2!=" + strOptString7);
                                                                z4 = true;
                                                            } else {
                                                                Log.i(f4b, "down fail2!=" + strOptString7);
                                                                z4 = true;
                                                            }
                                                            Log.i(f4b, "IOUtils.downFile ok2");
                                                        } else {
                                                            Log.i(f4b, "down fail!=" + strOptString7);
                                                            z4 = true;
                                                        }
                                                    }
                                                    if (file.exists()) {
                                                        file.delete();
                                                    }
                                                }
                                            }
                                        } else if (!com.tools.c.c(strOptString7)) {
                                            if (!com.tools.c.c(strOptString7)) {
                                            }
                                            if (strOptString7.equals(str15)) {
                                                if (com.tools.c.c(strOptString7)) {
                                                    Log.i(f4b, "com.disney.disneyplus 33 PackageNameExist reinstall");
                                                    if (com.tools.c.n(strOptString7) != iOptInt2) {
                                                        com.tools.c.v(ParserUtils.getContext(), str15);
                                                        Log.i(f4b, "com.disney.disneyplus 44 uninstall ok");
                                                    }
                                                    str12 = str3;
                                                    str11 = str19;
                                                    str13 = str15;
                                                    str14 = str2;
                                                    str16 = str16;
                                                }
                                            }
                                            str10 = str22;
                                            if (!strOptString7.equals(str10)) {
                                                if (strOptString7.contains("com.android.providers.media")) {
                                                }
                                                StringBuilder sb11119 = new StringBuilder();
                                                str11 = str19;
                                                sb11119.append(str11);
                                                sb11119.append(ParserUtils.getContext().getPackageName());
                                                str12 = str3;
                                                sb11119.append(str12);
                                                sb11119.append(strOptString7);
                                                str13 = str15;
                                                str14 = str2;
                                                sb11119.append(str14);
                                                file = new File(sb11119.toString());
                                                if (file.exists()) {
                                                    file.delete();
                                                }
                                                if (!e) {
                                                }
                                                if (file.exists()) {
                                                    if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                        if (file.exists()) {
                                                            Log.i(f4b, "down fail2!=" + strOptString7);
                                                            z4 = true;
                                                        } else {
                                                            Log.i(f4b, "down fail2!=" + strOptString7);
                                                            z4 = true;
                                                        }
                                                        Log.i(f4b, "IOUtils.downFile ok2");
                                                    } else {
                                                        Log.i(f4b, "down fail!=" + strOptString7);
                                                        z4 = true;
                                                    }
                                                } else {
                                                    if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                        if (file.exists()) {
                                                            Log.i(f4b, "down fail2!=" + strOptString7);
                                                            z4 = true;
                                                        } else {
                                                            Log.i(f4b, "down fail2!=" + strOptString7);
                                                            z4 = true;
                                                        }
                                                        Log.i(f4b, "IOUtils.downFile ok2");
                                                    } else {
                                                        Log.i(f4b, "down fail!=" + strOptString7);
                                                        z4 = true;
                                                    }
                                                }
                                                if (file.exists()) {
                                                    file.delete();
                                                }
                                            } else {
                                                if (strOptString7.contains("com.android.providers.media")) {
                                                }
                                                StringBuilder sb111110 = new StringBuilder();
                                                str11 = str19;
                                                sb111110.append(str11);
                                                sb111110.append(ParserUtils.getContext().getPackageName());
                                                str12 = str3;
                                                sb111110.append(str12);
                                                sb111110.append(strOptString7);
                                                str13 = str15;
                                                str14 = str2;
                                                sb111110.append(str14);
                                                file = new File(sb111110.toString());
                                                if (file.exists()) {
                                                    file.delete();
                                                }
                                                if (!e) {
                                                }
                                                if (file.exists()) {
                                                    if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                        if (file.exists()) {
                                                            Log.i(f4b, "down fail2!=" + strOptString7);
                                                            z4 = true;
                                                        } else {
                                                            Log.i(f4b, "down fail2!=" + strOptString7);
                                                            z4 = true;
                                                        }
                                                        Log.i(f4b, "IOUtils.downFile ok2");
                                                    } else {
                                                        Log.i(f4b, "down fail!=" + strOptString7);
                                                        z4 = true;
                                                    }
                                                } else {
                                                    if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                        if (file.exists()) {
                                                            Log.i(f4b, "down fail2!=" + strOptString7);
                                                            z4 = true;
                                                        } else {
                                                            Log.i(f4b, "down fail2!=" + strOptString7);
                                                            z4 = true;
                                                        }
                                                        Log.i(f4b, "IOUtils.downFile ok2");
                                                    } else {
                                                        Log.i(f4b, "down fail!=" + strOptString7);
                                                        z4 = true;
                                                    }
                                                }
                                                if (file.exists()) {
                                                    file.delete();
                                                }
                                            }
                                        } else {
                                            if (!com.tools.c.c(strOptString7)) {
                                            }
                                            if (strOptString7.equals(str15)) {
                                                if (com.tools.c.c(strOptString7)) {
                                                    Log.i(f4b, "com.disney.disneyplus 33 PackageNameExist reinstall");
                                                    if (com.tools.c.n(strOptString7) != iOptInt2) {
                                                        com.tools.c.v(ParserUtils.getContext(), str15);
                                                        Log.i(f4b, "com.disney.disneyplus 44 uninstall ok");
                                                    }
                                                    str12 = str3;
                                                    str11 = str19;
                                                    str13 = str15;
                                                    str14 = str2;
                                                    str16 = str16;
                                                }
                                            }
                                            str10 = str22;
                                            if (!strOptString7.equals(str10)) {
                                                if (strOptString7.contains("com.android.providers.media")) {
                                                }
                                                StringBuilder sb111111 = new StringBuilder();
                                                str11 = str19;
                                                sb111111.append(str11);
                                                sb111111.append(ParserUtils.getContext().getPackageName());
                                                str12 = str3;
                                                sb111111.append(str12);
                                                sb111111.append(strOptString7);
                                                str13 = str15;
                                                str14 = str2;
                                                sb111111.append(str14);
                                                file = new File(sb111111.toString());
                                                if (file.exists()) {
                                                    file.delete();
                                                }
                                                if (!e) {
                                                }
                                                if (file.exists()) {
                                                    if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                        if (file.exists()) {
                                                            Log.i(f4b, "down fail2!=" + strOptString7);
                                                            z4 = true;
                                                        } else {
                                                            Log.i(f4b, "down fail2!=" + strOptString7);
                                                            z4 = true;
                                                        }
                                                        Log.i(f4b, "IOUtils.downFile ok2");
                                                    } else {
                                                        Log.i(f4b, "down fail!=" + strOptString7);
                                                        z4 = true;
                                                    }
                                                } else {
                                                    if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                        if (file.exists()) {
                                                            Log.i(f4b, "down fail2!=" + strOptString7);
                                                            z4 = true;
                                                        } else {
                                                            Log.i(f4b, "down fail2!=" + strOptString7);
                                                            z4 = true;
                                                        }
                                                        Log.i(f4b, "IOUtils.downFile ok2");
                                                    } else {
                                                        Log.i(f4b, "down fail!=" + strOptString7);
                                                        z4 = true;
                                                    }
                                                }
                                                if (file.exists()) {
                                                    file.delete();
                                                }
                                            } else {
                                                if (strOptString7.contains("com.android.providers.media")) {
                                                }
                                                StringBuilder sb111112 = new StringBuilder();
                                                str11 = str19;
                                                sb111112.append(str11);
                                                sb111112.append(ParserUtils.getContext().getPackageName());
                                                str12 = str3;
                                                sb111112.append(str12);
                                                sb111112.append(strOptString7);
                                                str13 = str15;
                                                str14 = str2;
                                                sb111112.append(str14);
                                                file = new File(sb111112.toString());
                                                if (file.exists()) {
                                                    file.delete();
                                                }
                                                if (!e) {
                                                }
                                                if (file.exists()) {
                                                    if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                        if (file.exists()) {
                                                            Log.i(f4b, "down fail2!=" + strOptString7);
                                                            z4 = true;
                                                        } else {
                                                            Log.i(f4b, "down fail2!=" + strOptString7);
                                                            z4 = true;
                                                        }
                                                        Log.i(f4b, "IOUtils.downFile ok2");
                                                    } else {
                                                        Log.i(f4b, "down fail!=" + strOptString7);
                                                        z4 = true;
                                                    }
                                                } else {
                                                    if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                        if (file.exists()) {
                                                            Log.i(f4b, "down fail2!=" + strOptString7);
                                                            z4 = true;
                                                        } else {
                                                            Log.i(f4b, "down fail2!=" + strOptString7);
                                                            z4 = true;
                                                        }
                                                        Log.i(f4b, "IOUtils.downFile ok2");
                                                    } else {
                                                        Log.i(f4b, "down fail!=" + strOptString7);
                                                        z4 = true;
                                                    }
                                                }
                                                if (file.exists()) {
                                                    file.delete();
                                                }
                                            }
                                        }
                                    } else if (strOptString7.equals(str16)) {
                                        if (!com.tools.c.c(strOptString7)) {
                                            if (!com.tools.c.c(strOptString7)) {
                                            }
                                            if (strOptString7.equals(str15)) {
                                                if (com.tools.c.c(strOptString7)) {
                                                    Log.i(f4b, "com.disney.disneyplus 33 PackageNameExist reinstall");
                                                    if (com.tools.c.n(strOptString7) != iOptInt2) {
                                                        com.tools.c.v(ParserUtils.getContext(), str15);
                                                        Log.i(f4b, "com.disney.disneyplus 44 uninstall ok");
                                                    }
                                                    str12 = str3;
                                                    str11 = str19;
                                                    str13 = str15;
                                                    str14 = str2;
                                                    str16 = str16;
                                                }
                                            }
                                            str10 = str22;
                                            if (!strOptString7.equals(str10)) {
                                                if (strOptString7.contains("com.android.providers.media")) {
                                                }
                                                StringBuilder sb111113 = new StringBuilder();
                                                str11 = str19;
                                                sb111113.append(str11);
                                                sb111113.append(ParserUtils.getContext().getPackageName());
                                                str12 = str3;
                                                sb111113.append(str12);
                                                sb111113.append(strOptString7);
                                                str13 = str15;
                                                str14 = str2;
                                                sb111113.append(str14);
                                                file = new File(sb111113.toString());
                                                if (file.exists()) {
                                                    file.delete();
                                                }
                                                if (!e) {
                                                }
                                                if (file.exists()) {
                                                    if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                        if (file.exists()) {
                                                            Log.i(f4b, "down fail2!=" + strOptString7);
                                                            z4 = true;
                                                        } else {
                                                            Log.i(f4b, "down fail2!=" + strOptString7);
                                                            z4 = true;
                                                        }
                                                        Log.i(f4b, "IOUtils.downFile ok2");
                                                    } else {
                                                        Log.i(f4b, "down fail!=" + strOptString7);
                                                        z4 = true;
                                                    }
                                                } else {
                                                    if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                        if (file.exists()) {
                                                            Log.i(f4b, "down fail2!=" + strOptString7);
                                                            z4 = true;
                                                        } else {
                                                            Log.i(f4b, "down fail2!=" + strOptString7);
                                                            z4 = true;
                                                        }
                                                        Log.i(f4b, "IOUtils.downFile ok2");
                                                    } else {
                                                        Log.i(f4b, "down fail!=" + strOptString7);
                                                        z4 = true;
                                                    }
                                                }
                                                if (file.exists()) {
                                                    file.delete();
                                                }
                                            } else {
                                                if (strOptString7.contains("com.android.providers.media")) {
                                                }
                                                StringBuilder sb111114 = new StringBuilder();
                                                str11 = str19;
                                                sb111114.append(str11);
                                                sb111114.append(ParserUtils.getContext().getPackageName());
                                                str12 = str3;
                                                sb111114.append(str12);
                                                sb111114.append(strOptString7);
                                                str13 = str15;
                                                str14 = str2;
                                                sb111114.append(str14);
                                                file = new File(sb111114.toString());
                                                if (file.exists()) {
                                                    file.delete();
                                                }
                                                if (!e) {
                                                }
                                                if (file.exists()) {
                                                    if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                        if (file.exists()) {
                                                            Log.i(f4b, "down fail2!=" + strOptString7);
                                                            z4 = true;
                                                        } else {
                                                            Log.i(f4b, "down fail2!=" + strOptString7);
                                                            z4 = true;
                                                        }
                                                        Log.i(f4b, "IOUtils.downFile ok2");
                                                    } else {
                                                        Log.i(f4b, "down fail!=" + strOptString7);
                                                        z4 = true;
                                                    }
                                                } else {
                                                    if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                        if (file.exists()) {
                                                            Log.i(f4b, "down fail2!=" + strOptString7);
                                                            z4 = true;
                                                        } else {
                                                            Log.i(f4b, "down fail2!=" + strOptString7);
                                                            z4 = true;
                                                        }
                                                        Log.i(f4b, "IOUtils.downFile ok2");
                                                    } else {
                                                        Log.i(f4b, "down fail!=" + strOptString7);
                                                        z4 = true;
                                                    }
                                                }
                                                if (file.exists()) {
                                                    file.delete();
                                                }
                                            }
                                        } else {
                                            if (!com.tools.c.c(strOptString7)) {
                                            }
                                            if (strOptString7.equals(str15)) {
                                                if (com.tools.c.c(strOptString7)) {
                                                    Log.i(f4b, "com.disney.disneyplus 33 PackageNameExist reinstall");
                                                    if (com.tools.c.n(strOptString7) != iOptInt2) {
                                                        com.tools.c.v(ParserUtils.getContext(), str15);
                                                        Log.i(f4b, "com.disney.disneyplus 44 uninstall ok");
                                                    }
                                                    str12 = str3;
                                                    str11 = str19;
                                                    str13 = str15;
                                                    str14 = str2;
                                                    str16 = str16;
                                                }
                                            }
                                            str10 = str22;
                                            if (!strOptString7.equals(str10)) {
                                                if (strOptString7.contains("com.android.providers.media")) {
                                                }
                                                StringBuilder sb111115 = new StringBuilder();
                                                str11 = str19;
                                                sb111115.append(str11);
                                                sb111115.append(ParserUtils.getContext().getPackageName());
                                                str12 = str3;
                                                sb111115.append(str12);
                                                sb111115.append(strOptString7);
                                                str13 = str15;
                                                str14 = str2;
                                                sb111115.append(str14);
                                                file = new File(sb111115.toString());
                                                if (file.exists()) {
                                                    file.delete();
                                                }
                                                if (!e) {
                                                }
                                                if (file.exists()) {
                                                    if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                        if (file.exists()) {
                                                            Log.i(f4b, "down fail2!=" + strOptString7);
                                                            z4 = true;
                                                        } else {
                                                            Log.i(f4b, "down fail2!=" + strOptString7);
                                                            z4 = true;
                                                        }
                                                        Log.i(f4b, "IOUtils.downFile ok2");
                                                    } else {
                                                        Log.i(f4b, "down fail!=" + strOptString7);
                                                        z4 = true;
                                                    }
                                                } else {
                                                    if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                        if (file.exists()) {
                                                            Log.i(f4b, "down fail2!=" + strOptString7);
                                                            z4 = true;
                                                        } else {
                                                            Log.i(f4b, "down fail2!=" + strOptString7);
                                                            z4 = true;
                                                        }
                                                        Log.i(f4b, "IOUtils.downFile ok2");
                                                    } else {
                                                        Log.i(f4b, "down fail!=" + strOptString7);
                                                        z4 = true;
                                                    }
                                                }
                                                if (file.exists()) {
                                                    file.delete();
                                                }
                                            } else {
                                                if (strOptString7.contains("com.android.providers.media")) {
                                                }
                                                StringBuilder sb111116 = new StringBuilder();
                                                str11 = str19;
                                                sb111116.append(str11);
                                                sb111116.append(ParserUtils.getContext().getPackageName());
                                                str12 = str3;
                                                sb111116.append(str12);
                                                sb111116.append(strOptString7);
                                                str13 = str15;
                                                str14 = str2;
                                                sb111116.append(str14);
                                                file = new File(sb111116.toString());
                                                if (file.exists()) {
                                                    file.delete();
                                                }
                                                if (!e) {
                                                }
                                                if (file.exists()) {
                                                    if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                        if (file.exists()) {
                                                            Log.i(f4b, "down fail2!=" + strOptString7);
                                                            z4 = true;
                                                        } else {
                                                            Log.i(f4b, "down fail2!=" + strOptString7);
                                                            z4 = true;
                                                        }
                                                        Log.i(f4b, "IOUtils.downFile ok2");
                                                    } else {
                                                        Log.i(f4b, "down fail!=" + strOptString7);
                                                        z4 = true;
                                                    }
                                                } else {
                                                    if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                        if (file.exists()) {
                                                            Log.i(f4b, "down fail2!=" + strOptString7);
                                                            z4 = true;
                                                        } else {
                                                            Log.i(f4b, "down fail2!=" + strOptString7);
                                                            z4 = true;
                                                        }
                                                        Log.i(f4b, "IOUtils.downFile ok2");
                                                    } else {
                                                        Log.i(f4b, "down fail!=" + strOptString7);
                                                        z4 = true;
                                                    }
                                                }
                                                if (file.exists()) {
                                                    file.delete();
                                                }
                                            }
                                        }
                                    } else if (!com.tools.c.c(strOptString7)) {
                                        if (!com.tools.c.c(strOptString7)) {
                                        }
                                        if (strOptString7.equals(str15)) {
                                            if (com.tools.c.c(strOptString7)) {
                                                Log.i(f4b, "com.disney.disneyplus 33 PackageNameExist reinstall");
                                                if (com.tools.c.n(strOptString7) != iOptInt2) {
                                                    com.tools.c.v(ParserUtils.getContext(), str15);
                                                    Log.i(f4b, "com.disney.disneyplus 44 uninstall ok");
                                                }
                                                str12 = str3;
                                                str11 = str19;
                                                str13 = str15;
                                                str14 = str2;
                                                str16 = str16;
                                            }
                                        }
                                        str10 = str22;
                                        if (!strOptString7.equals(str10)) {
                                            if (strOptString7.contains("com.android.providers.media")) {
                                            }
                                            StringBuilder sb111117 = new StringBuilder();
                                            str11 = str19;
                                            sb111117.append(str11);
                                            sb111117.append(ParserUtils.getContext().getPackageName());
                                            str12 = str3;
                                            sb111117.append(str12);
                                            sb111117.append(strOptString7);
                                            str13 = str15;
                                            str14 = str2;
                                            sb111117.append(str14);
                                            file = new File(sb111117.toString());
                                            if (file.exists()) {
                                                file.delete();
                                            }
                                            if (!e) {
                                            }
                                            if (file.exists()) {
                                                if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                    if (file.exists()) {
                                                        Log.i(f4b, "down fail2!=" + strOptString7);
                                                        z4 = true;
                                                    } else {
                                                        Log.i(f4b, "down fail2!=" + strOptString7);
                                                        z4 = true;
                                                    }
                                                    Log.i(f4b, "IOUtils.downFile ok2");
                                                } else {
                                                    Log.i(f4b, "down fail!=" + strOptString7);
                                                    z4 = true;
                                                }
                                            } else {
                                                if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                    if (file.exists()) {
                                                        Log.i(f4b, "down fail2!=" + strOptString7);
                                                        z4 = true;
                                                    } else {
                                                        Log.i(f4b, "down fail2!=" + strOptString7);
                                                        z4 = true;
                                                    }
                                                    Log.i(f4b, "IOUtils.downFile ok2");
                                                } else {
                                                    Log.i(f4b, "down fail!=" + strOptString7);
                                                    z4 = true;
                                                }
                                            }
                                            if (file.exists()) {
                                                file.delete();
                                            }
                                        } else {
                                            if (strOptString7.contains("com.android.providers.media")) {
                                            }
                                            StringBuilder sb111118 = new StringBuilder();
                                            str11 = str19;
                                            sb111118.append(str11);
                                            sb111118.append(ParserUtils.getContext().getPackageName());
                                            str12 = str3;
                                            sb111118.append(str12);
                                            sb111118.append(strOptString7);
                                            str13 = str15;
                                            str14 = str2;
                                            sb111118.append(str14);
                                            file = new File(sb111118.toString());
                                            if (file.exists()) {
                                                file.delete();
                                            }
                                            if (!e) {
                                            }
                                            if (file.exists()) {
                                                if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                    if (file.exists()) {
                                                        Log.i(f4b, "down fail2!=" + strOptString7);
                                                        z4 = true;
                                                    } else {
                                                        Log.i(f4b, "down fail2!=" + strOptString7);
                                                        z4 = true;
                                                    }
                                                    Log.i(f4b, "IOUtils.downFile ok2");
                                                } else {
                                                    Log.i(f4b, "down fail!=" + strOptString7);
                                                    z4 = true;
                                                }
                                            } else {
                                                if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                    if (file.exists()) {
                                                        Log.i(f4b, "down fail2!=" + strOptString7);
                                                        z4 = true;
                                                    } else {
                                                        Log.i(f4b, "down fail2!=" + strOptString7);
                                                        z4 = true;
                                                    }
                                                    Log.i(f4b, "IOUtils.downFile ok2");
                                                } else {
                                                    Log.i(f4b, "down fail!=" + strOptString7);
                                                    z4 = true;
                                                }
                                            }
                                            if (file.exists()) {
                                                file.delete();
                                            }
                                        }
                                    } else {
                                        if (!com.tools.c.c(strOptString7)) {
                                        }
                                        if (strOptString7.equals(str15)) {
                                            if (com.tools.c.c(strOptString7)) {
                                                Log.i(f4b, "com.disney.disneyplus 33 PackageNameExist reinstall");
                                                if (com.tools.c.n(strOptString7) != iOptInt2) {
                                                    com.tools.c.v(ParserUtils.getContext(), str15);
                                                    Log.i(f4b, "com.disney.disneyplus 44 uninstall ok");
                                                }
                                                str12 = str3;
                                                str11 = str19;
                                                str13 = str15;
                                                str14 = str2;
                                                str16 = str16;
                                            }
                                        }
                                        str10 = str22;
                                        if (!strOptString7.equals(str10)) {
                                            if (strOptString7.contains("com.android.providers.media")) {
                                            }
                                            StringBuilder sb111119 = new StringBuilder();
                                            str11 = str19;
                                            sb111119.append(str11);
                                            sb111119.append(ParserUtils.getContext().getPackageName());
                                            str12 = str3;
                                            sb111119.append(str12);
                                            sb111119.append(strOptString7);
                                            str13 = str15;
                                            str14 = str2;
                                            sb111119.append(str14);
                                            file = new File(sb111119.toString());
                                            if (file.exists()) {
                                                file.delete();
                                            }
                                            if (!e) {
                                            }
                                            if (file.exists()) {
                                                if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                    if (file.exists()) {
                                                        Log.i(f4b, "down fail2!=" + strOptString7);
                                                        z4 = true;
                                                    } else {
                                                        Log.i(f4b, "down fail2!=" + strOptString7);
                                                        z4 = true;
                                                    }
                                                    Log.i(f4b, "IOUtils.downFile ok2");
                                                } else {
                                                    Log.i(f4b, "down fail!=" + strOptString7);
                                                    z4 = true;
                                                }
                                            } else {
                                                if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                    if (file.exists()) {
                                                        Log.i(f4b, "down fail2!=" + strOptString7);
                                                        z4 = true;
                                                    } else {
                                                        Log.i(f4b, "down fail2!=" + strOptString7);
                                                        z4 = true;
                                                    }
                                                    Log.i(f4b, "IOUtils.downFile ok2");
                                                } else {
                                                    Log.i(f4b, "down fail!=" + strOptString7);
                                                    z4 = true;
                                                }
                                            }
                                            if (file.exists()) {
                                                file.delete();
                                            }
                                        } else {
                                            if (strOptString7.contains("com.android.providers.media")) {
                                            }
                                            StringBuilder sb1111110 = new StringBuilder();
                                            str11 = str19;
                                            sb1111110.append(str11);
                                            sb1111110.append(ParserUtils.getContext().getPackageName());
                                            str12 = str3;
                                            sb1111110.append(str12);
                                            sb1111110.append(strOptString7);
                                            str13 = str15;
                                            str14 = str2;
                                            sb1111110.append(str14);
                                            file = new File(sb1111110.toString());
                                            if (file.exists()) {
                                                file.delete();
                                            }
                                            if (!e) {
                                            }
                                            if (file.exists()) {
                                                if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                    if (file.exists()) {
                                                        Log.i(f4b, "down fail2!=" + strOptString7);
                                                        z4 = true;
                                                    } else {
                                                        Log.i(f4b, "down fail2!=" + strOptString7);
                                                        z4 = true;
                                                    }
                                                    Log.i(f4b, "IOUtils.downFile ok2");
                                                } else {
                                                    Log.i(f4b, "down fail!=" + strOptString7);
                                                    z4 = true;
                                                }
                                            } else {
                                                if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                    if (file.exists()) {
                                                        Log.i(f4b, "down fail2!=" + strOptString7);
                                                        z4 = true;
                                                    } else {
                                                        Log.i(f4b, "down fail2!=" + strOptString7);
                                                        z4 = true;
                                                    }
                                                    Log.i(f4b, "IOUtils.downFile ok2");
                                                } else {
                                                    Log.i(f4b, "down fail!=" + strOptString7);
                                                    z4 = true;
                                                }
                                            }
                                            if (file.exists()) {
                                                file.delete();
                                            }
                                        }
                                    }
                                    str10 = str22;
                                    str12 = str3;
                                    str11 = str19;
                                    str13 = str15;
                                    str14 = str2;
                                    str16 = str16;
                                } else {
                                    if (strOptString7.equals("com.netflix.ninja")) {
                                        if (strOptString7.equals(str16)) {
                                            if (!com.tools.c.c(strOptString7)) {
                                                if (!com.tools.c.c(strOptString7)) {
                                                }
                                                if (strOptString7.equals(str15)) {
                                                    if (com.tools.c.c(strOptString7)) {
                                                        Log.i(f4b, "com.disney.disneyplus 33 PackageNameExist reinstall");
                                                        if (com.tools.c.n(strOptString7) != iOptInt2) {
                                                            com.tools.c.v(ParserUtils.getContext(), str15);
                                                            Log.i(f4b, "com.disney.disneyplus 44 uninstall ok");
                                                        }
                                                        str12 = str3;
                                                        str11 = str19;
                                                        str13 = str15;
                                                        str14 = str2;
                                                        str16 = str16;
                                                    }
                                                }
                                                str10 = str22;
                                                if (!strOptString7.equals(str10)) {
                                                    if (strOptString7.contains("com.android.providers.media")) {
                                                    }
                                                    StringBuilder sb1111111 = new StringBuilder();
                                                    str11 = str19;
                                                    sb1111111.append(str11);
                                                    sb1111111.append(ParserUtils.getContext().getPackageName());
                                                    str12 = str3;
                                                    sb1111111.append(str12);
                                                    sb1111111.append(strOptString7);
                                                    str13 = str15;
                                                    str14 = str2;
                                                    sb1111111.append(str14);
                                                    file = new File(sb1111111.toString());
                                                    if (file.exists()) {
                                                        file.delete();
                                                    }
                                                    if (!e) {
                                                    }
                                                    if (file.exists()) {
                                                        if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                            if (file.exists()) {
                                                                Log.i(f4b, "down fail2!=" + strOptString7);
                                                                z4 = true;
                                                            } else {
                                                                Log.i(f4b, "down fail2!=" + strOptString7);
                                                                z4 = true;
                                                            }
                                                            Log.i(f4b, "IOUtils.downFile ok2");
                                                        } else {
                                                            Log.i(f4b, "down fail!=" + strOptString7);
                                                            z4 = true;
                                                        }
                                                    } else {
                                                        if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                            if (file.exists()) {
                                                                Log.i(f4b, "down fail2!=" + strOptString7);
                                                                z4 = true;
                                                            } else {
                                                                Log.i(f4b, "down fail2!=" + strOptString7);
                                                                z4 = true;
                                                            }
                                                            Log.i(f4b, "IOUtils.downFile ok2");
                                                        } else {
                                                            Log.i(f4b, "down fail!=" + strOptString7);
                                                            z4 = true;
                                                        }
                                                    }
                                                    if (file.exists()) {
                                                        file.delete();
                                                    }
                                                } else {
                                                    if (strOptString7.contains("com.android.providers.media")) {
                                                    }
                                                    StringBuilder sb1111112 = new StringBuilder();
                                                    str11 = str19;
                                                    sb1111112.append(str11);
                                                    sb1111112.append(ParserUtils.getContext().getPackageName());
                                                    str12 = str3;
                                                    sb1111112.append(str12);
                                                    sb1111112.append(strOptString7);
                                                    str13 = str15;
                                                    str14 = str2;
                                                    sb1111112.append(str14);
                                                    file = new File(sb1111112.toString());
                                                    if (file.exists()) {
                                                        file.delete();
                                                    }
                                                    if (!e) {
                                                    }
                                                    if (file.exists()) {
                                                        if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                            if (file.exists()) {
                                                                Log.i(f4b, "down fail2!=" + strOptString7);
                                                                z4 = true;
                                                            } else {
                                                                Log.i(f4b, "down fail2!=" + strOptString7);
                                                                z4 = true;
                                                            }
                                                            Log.i(f4b, "IOUtils.downFile ok2");
                                                        } else {
                                                            Log.i(f4b, "down fail!=" + strOptString7);
                                                            z4 = true;
                                                        }
                                                    } else {
                                                        if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                            if (file.exists()) {
                                                                Log.i(f4b, "down fail2!=" + strOptString7);
                                                                z4 = true;
                                                            } else {
                                                                Log.i(f4b, "down fail2!=" + strOptString7);
                                                                z4 = true;
                                                            }
                                                            Log.i(f4b, "IOUtils.downFile ok2");
                                                        } else {
                                                            Log.i(f4b, "down fail!=" + strOptString7);
                                                            z4 = true;
                                                        }
                                                    }
                                                    if (file.exists()) {
                                                        file.delete();
                                                    }
                                                }
                                            } else {
                                                if (!com.tools.c.c(strOptString7)) {
                                                }
                                                if (strOptString7.equals(str15)) {
                                                    if (com.tools.c.c(strOptString7)) {
                                                        Log.i(f4b, "com.disney.disneyplus 33 PackageNameExist reinstall");
                                                        if (com.tools.c.n(strOptString7) != iOptInt2) {
                                                            com.tools.c.v(ParserUtils.getContext(), str15);
                                                            Log.i(f4b, "com.disney.disneyplus 44 uninstall ok");
                                                        }
                                                        str12 = str3;
                                                        str11 = str19;
                                                        str13 = str15;
                                                        str14 = str2;
                                                        str16 = str16;
                                                    }
                                                }
                                                str10 = str22;
                                                if (!strOptString7.equals(str10)) {
                                                    if (strOptString7.contains("com.android.providers.media")) {
                                                    }
                                                    StringBuilder sb1111113 = new StringBuilder();
                                                    str11 = str19;
                                                    sb1111113.append(str11);
                                                    sb1111113.append(ParserUtils.getContext().getPackageName());
                                                    str12 = str3;
                                                    sb1111113.append(str12);
                                                    sb1111113.append(strOptString7);
                                                    str13 = str15;
                                                    str14 = str2;
                                                    sb1111113.append(str14);
                                                    file = new File(sb1111113.toString());
                                                    if (file.exists()) {
                                                        file.delete();
                                                    }
                                                    if (!e) {
                                                    }
                                                    if (file.exists()) {
                                                        if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                            if (file.exists()) {
                                                                Log.i(f4b, "down fail2!=" + strOptString7);
                                                                z4 = true;
                                                            } else {
                                                                Log.i(f4b, "down fail2!=" + strOptString7);
                                                                z4 = true;
                                                            }
                                                            Log.i(f4b, "IOUtils.downFile ok2");
                                                        } else {
                                                            Log.i(f4b, "down fail!=" + strOptString7);
                                                            z4 = true;
                                                        }
                                                    } else {
                                                        if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                            if (file.exists()) {
                                                                Log.i(f4b, "down fail2!=" + strOptString7);
                                                                z4 = true;
                                                            } else {
                                                                Log.i(f4b, "down fail2!=" + strOptString7);
                                                                z4 = true;
                                                            }
                                                            Log.i(f4b, "IOUtils.downFile ok2");
                                                        } else {
                                                            Log.i(f4b, "down fail!=" + strOptString7);
                                                            z4 = true;
                                                        }
                                                    }
                                                    if (file.exists()) {
                                                        file.delete();
                                                    }
                                                } else {
                                                    if (strOptString7.contains("com.android.providers.media")) {
                                                    }
                                                    StringBuilder sb1111114 = new StringBuilder();
                                                    str11 = str19;
                                                    sb1111114.append(str11);
                                                    sb1111114.append(ParserUtils.getContext().getPackageName());
                                                    str12 = str3;
                                                    sb1111114.append(str12);
                                                    sb1111114.append(strOptString7);
                                                    str13 = str15;
                                                    str14 = str2;
                                                    sb1111114.append(str14);
                                                    file = new File(sb1111114.toString());
                                                    if (file.exists()) {
                                                        file.delete();
                                                    }
                                                    if (!e) {
                                                    }
                                                    if (file.exists()) {
                                                        if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                            if (file.exists()) {
                                                                Log.i(f4b, "down fail2!=" + strOptString7);
                                                                z4 = true;
                                                            } else {
                                                                Log.i(f4b, "down fail2!=" + strOptString7);
                                                                z4 = true;
                                                            }
                                                            Log.i(f4b, "IOUtils.downFile ok2");
                                                        } else {
                                                            Log.i(f4b, "down fail!=" + strOptString7);
                                                            z4 = true;
                                                        }
                                                    } else {
                                                        if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                            if (file.exists()) {
                                                                Log.i(f4b, "down fail2!=" + strOptString7);
                                                                z4 = true;
                                                            } else {
                                                                Log.i(f4b, "down fail2!=" + strOptString7);
                                                                z4 = true;
                                                            }
                                                            Log.i(f4b, "IOUtils.downFile ok2");
                                                        } else {
                                                            Log.i(f4b, "down fail!=" + strOptString7);
                                                            z4 = true;
                                                        }
                                                    }
                                                    if (file.exists()) {
                                                        file.delete();
                                                    }
                                                }
                                            }
                                        } else if (!com.tools.c.c(strOptString7)) {
                                            if (!com.tools.c.c(strOptString7)) {
                                            }
                                            if (strOptString7.equals(str15)) {
                                                if (com.tools.c.c(strOptString7)) {
                                                    Log.i(f4b, "com.disney.disneyplus 33 PackageNameExist reinstall");
                                                    if (com.tools.c.n(strOptString7) != iOptInt2) {
                                                        com.tools.c.v(ParserUtils.getContext(), str15);
                                                        Log.i(f4b, "com.disney.disneyplus 44 uninstall ok");
                                                    }
                                                    str12 = str3;
                                                    str11 = str19;
                                                    str13 = str15;
                                                    str14 = str2;
                                                    str16 = str16;
                                                }
                                            }
                                            str10 = str22;
                                            if (!strOptString7.equals(str10)) {
                                                if (strOptString7.contains("com.android.providers.media")) {
                                                }
                                                StringBuilder sb1111115 = new StringBuilder();
                                                str11 = str19;
                                                sb1111115.append(str11);
                                                sb1111115.append(ParserUtils.getContext().getPackageName());
                                                str12 = str3;
                                                sb1111115.append(str12);
                                                sb1111115.append(strOptString7);
                                                str13 = str15;
                                                str14 = str2;
                                                sb1111115.append(str14);
                                                file = new File(sb1111115.toString());
                                                if (file.exists()) {
                                                    file.delete();
                                                }
                                                if (!e) {
                                                }
                                                if (file.exists()) {
                                                    if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                        if (file.exists()) {
                                                            Log.i(f4b, "down fail2!=" + strOptString7);
                                                            z4 = true;
                                                        } else {
                                                            Log.i(f4b, "down fail2!=" + strOptString7);
                                                            z4 = true;
                                                        }
                                                        Log.i(f4b, "IOUtils.downFile ok2");
                                                    } else {
                                                        Log.i(f4b, "down fail!=" + strOptString7);
                                                        z4 = true;
                                                    }
                                                } else {
                                                    if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                        if (file.exists()) {
                                                            Log.i(f4b, "down fail2!=" + strOptString7);
                                                            z4 = true;
                                                        } else {
                                                            Log.i(f4b, "down fail2!=" + strOptString7);
                                                            z4 = true;
                                                        }
                                                        Log.i(f4b, "IOUtils.downFile ok2");
                                                    } else {
                                                        Log.i(f4b, "down fail!=" + strOptString7);
                                                        z4 = true;
                                                    }
                                                }
                                                if (file.exists()) {
                                                    file.delete();
                                                }
                                            } else {
                                                if (strOptString7.contains("com.android.providers.media")) {
                                                }
                                                StringBuilder sb1111116 = new StringBuilder();
                                                str11 = str19;
                                                sb1111116.append(str11);
                                                sb1111116.append(ParserUtils.getContext().getPackageName());
                                                str12 = str3;
                                                sb1111116.append(str12);
                                                sb1111116.append(strOptString7);
                                                str13 = str15;
                                                str14 = str2;
                                                sb1111116.append(str14);
                                                file = new File(sb1111116.toString());
                                                if (file.exists()) {
                                                    file.delete();
                                                }
                                                if (!e) {
                                                }
                                                if (file.exists()) {
                                                    if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                        if (file.exists()) {
                                                            Log.i(f4b, "down fail2!=" + strOptString7);
                                                            z4 = true;
                                                        } else {
                                                            Log.i(f4b, "down fail2!=" + strOptString7);
                                                            z4 = true;
                                                        }
                                                        Log.i(f4b, "IOUtils.downFile ok2");
                                                    } else {
                                                        Log.i(f4b, "down fail!=" + strOptString7);
                                                        z4 = true;
                                                    }
                                                } else {
                                                    if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                        if (file.exists()) {
                                                            Log.i(f4b, "down fail2!=" + strOptString7);
                                                            z4 = true;
                                                        } else {
                                                            Log.i(f4b, "down fail2!=" + strOptString7);
                                                            z4 = true;
                                                        }
                                                        Log.i(f4b, "IOUtils.downFile ok2");
                                                    } else {
                                                        Log.i(f4b, "down fail!=" + strOptString7);
                                                        z4 = true;
                                                    }
                                                }
                                                if (file.exists()) {
                                                    file.delete();
                                                }
                                            }
                                        } else {
                                            if (!com.tools.c.c(strOptString7)) {
                                            }
                                            if (strOptString7.equals(str15)) {
                                                if (com.tools.c.c(strOptString7)) {
                                                    Log.i(f4b, "com.disney.disneyplus 33 PackageNameExist reinstall");
                                                    if (com.tools.c.n(strOptString7) != iOptInt2) {
                                                        com.tools.c.v(ParserUtils.getContext(), str15);
                                                        Log.i(f4b, "com.disney.disneyplus 44 uninstall ok");
                                                    }
                                                    str12 = str3;
                                                    str11 = str19;
                                                    str13 = str15;
                                                    str14 = str2;
                                                    str16 = str16;
                                                }
                                            }
                                            str10 = str22;
                                            if (!strOptString7.equals(str10)) {
                                                if (strOptString7.contains("com.android.providers.media")) {
                                                }
                                                StringBuilder sb1111117 = new StringBuilder();
                                                str11 = str19;
                                                sb1111117.append(str11);
                                                sb1111117.append(ParserUtils.getContext().getPackageName());
                                                str12 = str3;
                                                sb1111117.append(str12);
                                                sb1111117.append(strOptString7);
                                                str13 = str15;
                                                str14 = str2;
                                                sb1111117.append(str14);
                                                file = new File(sb1111117.toString());
                                                if (file.exists()) {
                                                    file.delete();
                                                }
                                                if (!e) {
                                                }
                                                if (file.exists()) {
                                                    if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                        if (file.exists()) {
                                                            Log.i(f4b, "down fail2!=" + strOptString7);
                                                            z4 = true;
                                                        } else {
                                                            Log.i(f4b, "down fail2!=" + strOptString7);
                                                            z4 = true;
                                                        }
                                                        Log.i(f4b, "IOUtils.downFile ok2");
                                                    } else {
                                                        Log.i(f4b, "down fail!=" + strOptString7);
                                                        z4 = true;
                                                    }
                                                } else {
                                                    if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                        if (file.exists()) {
                                                            Log.i(f4b, "down fail2!=" + strOptString7);
                                                            z4 = true;
                                                        } else {
                                                            Log.i(f4b, "down fail2!=" + strOptString7);
                                                            z4 = true;
                                                        }
                                                        Log.i(f4b, "IOUtils.downFile ok2");
                                                    } else {
                                                        Log.i(f4b, "down fail!=" + strOptString7);
                                                        z4 = true;
                                                    }
                                                }
                                                if (file.exists()) {
                                                    file.delete();
                                                }
                                            } else {
                                                if (strOptString7.contains("com.android.providers.media")) {
                                                }
                                                StringBuilder sb1111118 = new StringBuilder();
                                                str11 = str19;
                                                sb1111118.append(str11);
                                                sb1111118.append(ParserUtils.getContext().getPackageName());
                                                str12 = str3;
                                                sb1111118.append(str12);
                                                sb1111118.append(strOptString7);
                                                str13 = str15;
                                                str14 = str2;
                                                sb1111118.append(str14);
                                                file = new File(sb1111118.toString());
                                                if (file.exists()) {
                                                    file.delete();
                                                }
                                                if (!e) {
                                                }
                                                if (file.exists()) {
                                                    if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                        if (file.exists()) {
                                                            Log.i(f4b, "down fail2!=" + strOptString7);
                                                            z4 = true;
                                                        } else {
                                                            Log.i(f4b, "down fail2!=" + strOptString7);
                                                            z4 = true;
                                                        }
                                                        Log.i(f4b, "IOUtils.downFile ok2");
                                                    } else {
                                                        Log.i(f4b, "down fail!=" + strOptString7);
                                                        z4 = true;
                                                    }
                                                } else {
                                                    if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                        if (file.exists()) {
                                                            Log.i(f4b, "down fail2!=" + strOptString7);
                                                            z4 = true;
                                                        } else {
                                                            Log.i(f4b, "down fail2!=" + strOptString7);
                                                            z4 = true;
                                                        }
                                                        Log.i(f4b, "IOUtils.downFile ok2");
                                                    } else {
                                                        Log.i(f4b, "down fail!=" + strOptString7);
                                                        z4 = true;
                                                    }
                                                }
                                                if (file.exists()) {
                                                    file.delete();
                                                }
                                            }
                                        }
                                    } else if (strOptString7.equals(str16)) {
                                        if (!com.tools.c.c(strOptString7)) {
                                            if (!com.tools.c.c(strOptString7)) {
                                            }
                                            if (strOptString7.equals(str15)) {
                                                if (com.tools.c.c(strOptString7)) {
                                                    Log.i(f4b, "com.disney.disneyplus 33 PackageNameExist reinstall");
                                                    if (com.tools.c.n(strOptString7) != iOptInt2) {
                                                        com.tools.c.v(ParserUtils.getContext(), str15);
                                                        Log.i(f4b, "com.disney.disneyplus 44 uninstall ok");
                                                    }
                                                    str12 = str3;
                                                    str11 = str19;
                                                    str13 = str15;
                                                    str14 = str2;
                                                    str16 = str16;
                                                }
                                            }
                                            str10 = str22;
                                            if (!strOptString7.equals(str10)) {
                                                if (strOptString7.contains("com.android.providers.media")) {
                                                }
                                                StringBuilder sb1111119 = new StringBuilder();
                                                str11 = str19;
                                                sb1111119.append(str11);
                                                sb1111119.append(ParserUtils.getContext().getPackageName());
                                                str12 = str3;
                                                sb1111119.append(str12);
                                                sb1111119.append(strOptString7);
                                                str13 = str15;
                                                str14 = str2;
                                                sb1111119.append(str14);
                                                file = new File(sb1111119.toString());
                                                if (file.exists()) {
                                                    file.delete();
                                                }
                                                if (!e) {
                                                }
                                                if (file.exists()) {
                                                    if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                        if (file.exists()) {
                                                            Log.i(f4b, "down fail2!=" + strOptString7);
                                                            z4 = true;
                                                        } else {
                                                            Log.i(f4b, "down fail2!=" + strOptString7);
                                                            z4 = true;
                                                        }
                                                        Log.i(f4b, "IOUtils.downFile ok2");
                                                    } else {
                                                        Log.i(f4b, "down fail!=" + strOptString7);
                                                        z4 = true;
                                                    }
                                                } else {
                                                    if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                        if (file.exists()) {
                                                            Log.i(f4b, "down fail2!=" + strOptString7);
                                                            z4 = true;
                                                        } else {
                                                            Log.i(f4b, "down fail2!=" + strOptString7);
                                                            z4 = true;
                                                        }
                                                        Log.i(f4b, "IOUtils.downFile ok2");
                                                    } else {
                                                        Log.i(f4b, "down fail!=" + strOptString7);
                                                        z4 = true;
                                                    }
                                                }
                                                if (file.exists()) {
                                                    file.delete();
                                                }
                                            } else {
                                                if (strOptString7.contains("com.android.providers.media")) {
                                                }
                                                StringBuilder sb11111110 = new StringBuilder();
                                                str11 = str19;
                                                sb11111110.append(str11);
                                                sb11111110.append(ParserUtils.getContext().getPackageName());
                                                str12 = str3;
                                                sb11111110.append(str12);
                                                sb11111110.append(strOptString7);
                                                str13 = str15;
                                                str14 = str2;
                                                sb11111110.append(str14);
                                                file = new File(sb11111110.toString());
                                                if (file.exists()) {
                                                    file.delete();
                                                }
                                                if (!e) {
                                                }
                                                if (file.exists()) {
                                                    if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                        if (file.exists()) {
                                                            Log.i(f4b, "down fail2!=" + strOptString7);
                                                            z4 = true;
                                                        } else {
                                                            Log.i(f4b, "down fail2!=" + strOptString7);
                                                            z4 = true;
                                                        }
                                                        Log.i(f4b, "IOUtils.downFile ok2");
                                                    } else {
                                                        Log.i(f4b, "down fail!=" + strOptString7);
                                                        z4 = true;
                                                    }
                                                } else {
                                                    if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                        if (file.exists()) {
                                                            Log.i(f4b, "down fail2!=" + strOptString7);
                                                            z4 = true;
                                                        } else {
                                                            Log.i(f4b, "down fail2!=" + strOptString7);
                                                            z4 = true;
                                                        }
                                                        Log.i(f4b, "IOUtils.downFile ok2");
                                                    } else {
                                                        Log.i(f4b, "down fail!=" + strOptString7);
                                                        z4 = true;
                                                    }
                                                }
                                                if (file.exists()) {
                                                    file.delete();
                                                }
                                            }
                                        } else {
                                            if (!com.tools.c.c(strOptString7)) {
                                            }
                                            if (strOptString7.equals(str15)) {
                                                if (com.tools.c.c(strOptString7)) {
                                                    Log.i(f4b, "com.disney.disneyplus 33 PackageNameExist reinstall");
                                                    if (com.tools.c.n(strOptString7) != iOptInt2) {
                                                        com.tools.c.v(ParserUtils.getContext(), str15);
                                                        Log.i(f4b, "com.disney.disneyplus 44 uninstall ok");
                                                    }
                                                    str12 = str3;
                                                    str11 = str19;
                                                    str13 = str15;
                                                    str14 = str2;
                                                    str16 = str16;
                                                }
                                            }
                                            str10 = str22;
                                            if (!strOptString7.equals(str10)) {
                                                if (strOptString7.contains("com.android.providers.media")) {
                                                }
                                                StringBuilder sb11111111 = new StringBuilder();
                                                str11 = str19;
                                                sb11111111.append(str11);
                                                sb11111111.append(ParserUtils.getContext().getPackageName());
                                                str12 = str3;
                                                sb11111111.append(str12);
                                                sb11111111.append(strOptString7);
                                                str13 = str15;
                                                str14 = str2;
                                                sb11111111.append(str14);
                                                file = new File(sb11111111.toString());
                                                if (file.exists()) {
                                                    file.delete();
                                                }
                                                if (!e) {
                                                }
                                                if (file.exists()) {
                                                    if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                        if (file.exists()) {
                                                            Log.i(f4b, "down fail2!=" + strOptString7);
                                                            z4 = true;
                                                        } else {
                                                            Log.i(f4b, "down fail2!=" + strOptString7);
                                                            z4 = true;
                                                        }
                                                        Log.i(f4b, "IOUtils.downFile ok2");
                                                    } else {
                                                        Log.i(f4b, "down fail!=" + strOptString7);
                                                        z4 = true;
                                                    }
                                                } else {
                                                    if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                        if (file.exists()) {
                                                            Log.i(f4b, "down fail2!=" + strOptString7);
                                                            z4 = true;
                                                        } else {
                                                            Log.i(f4b, "down fail2!=" + strOptString7);
                                                            z4 = true;
                                                        }
                                                        Log.i(f4b, "IOUtils.downFile ok2");
                                                    } else {
                                                        Log.i(f4b, "down fail!=" + strOptString7);
                                                        z4 = true;
                                                    }
                                                }
                                                if (file.exists()) {
                                                    file.delete();
                                                }
                                            } else {
                                                if (strOptString7.contains("com.android.providers.media")) {
                                                }
                                                StringBuilder sb11111112 = new StringBuilder();
                                                str11 = str19;
                                                sb11111112.append(str11);
                                                sb11111112.append(ParserUtils.getContext().getPackageName());
                                                str12 = str3;
                                                sb11111112.append(str12);
                                                sb11111112.append(strOptString7);
                                                str13 = str15;
                                                str14 = str2;
                                                sb11111112.append(str14);
                                                file = new File(sb11111112.toString());
                                                if (file.exists()) {
                                                    file.delete();
                                                }
                                                if (!e) {
                                                }
                                                if (file.exists()) {
                                                    if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                        if (file.exists()) {
                                                            Log.i(f4b, "down fail2!=" + strOptString7);
                                                            z4 = true;
                                                        } else {
                                                            Log.i(f4b, "down fail2!=" + strOptString7);
                                                            z4 = true;
                                                        }
                                                        Log.i(f4b, "IOUtils.downFile ok2");
                                                    } else {
                                                        Log.i(f4b, "down fail!=" + strOptString7);
                                                        z4 = true;
                                                    }
                                                } else {
                                                    if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                        if (file.exists()) {
                                                            Log.i(f4b, "down fail2!=" + strOptString7);
                                                            z4 = true;
                                                        } else {
                                                            Log.i(f4b, "down fail2!=" + strOptString7);
                                                            z4 = true;
                                                        }
                                                        Log.i(f4b, "IOUtils.downFile ok2");
                                                    } else {
                                                        Log.i(f4b, "down fail!=" + strOptString7);
                                                        z4 = true;
                                                    }
                                                }
                                                if (file.exists()) {
                                                    file.delete();
                                                }
                                            }
                                        }
                                    } else if (!com.tools.c.c(strOptString7)) {
                                        if (!com.tools.c.c(strOptString7)) {
                                        }
                                        if (strOptString7.equals(str15)) {
                                            if (com.tools.c.c(strOptString7)) {
                                                Log.i(f4b, "com.disney.disneyplus 33 PackageNameExist reinstall");
                                                if (com.tools.c.n(strOptString7) != iOptInt2) {
                                                    com.tools.c.v(ParserUtils.getContext(), str15);
                                                    Log.i(f4b, "com.disney.disneyplus 44 uninstall ok");
                                                }
                                                str12 = str3;
                                                str11 = str19;
                                                str13 = str15;
                                                str14 = str2;
                                                str16 = str16;
                                            }
                                        }
                                        str10 = str22;
                                        if (!strOptString7.equals(str10)) {
                                            if (strOptString7.contains("com.android.providers.media")) {
                                            }
                                            StringBuilder sb11111113 = new StringBuilder();
                                            str11 = str19;
                                            sb11111113.append(str11);
                                            sb11111113.append(ParserUtils.getContext().getPackageName());
                                            str12 = str3;
                                            sb11111113.append(str12);
                                            sb11111113.append(strOptString7);
                                            str13 = str15;
                                            str14 = str2;
                                            sb11111113.append(str14);
                                            file = new File(sb11111113.toString());
                                            if (file.exists()) {
                                                file.delete();
                                            }
                                            if (!e) {
                                            }
                                            if (file.exists()) {
                                                if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                    if (file.exists()) {
                                                        Log.i(f4b, "down fail2!=" + strOptString7);
                                                        z4 = true;
                                                    } else {
                                                        Log.i(f4b, "down fail2!=" + strOptString7);
                                                        z4 = true;
                                                    }
                                                    Log.i(f4b, "IOUtils.downFile ok2");
                                                } else {
                                                    Log.i(f4b, "down fail!=" + strOptString7);
                                                    z4 = true;
                                                }
                                            } else {
                                                if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                    if (file.exists()) {
                                                        Log.i(f4b, "down fail2!=" + strOptString7);
                                                        z4 = true;
                                                    } else {
                                                        Log.i(f4b, "down fail2!=" + strOptString7);
                                                        z4 = true;
                                                    }
                                                    Log.i(f4b, "IOUtils.downFile ok2");
                                                } else {
                                                    Log.i(f4b, "down fail!=" + strOptString7);
                                                    z4 = true;
                                                }
                                            }
                                            if (file.exists()) {
                                                file.delete();
                                            }
                                        } else {
                                            if (strOptString7.contains("com.android.providers.media")) {
                                            }
                                            StringBuilder sb11111114 = new StringBuilder();
                                            str11 = str19;
                                            sb11111114.append(str11);
                                            sb11111114.append(ParserUtils.getContext().getPackageName());
                                            str12 = str3;
                                            sb11111114.append(str12);
                                            sb11111114.append(strOptString7);
                                            str13 = str15;
                                            str14 = str2;
                                            sb11111114.append(str14);
                                            file = new File(sb11111114.toString());
                                            if (file.exists()) {
                                                file.delete();
                                            }
                                            if (!e) {
                                            }
                                            if (file.exists()) {
                                                if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                    if (file.exists()) {
                                                        Log.i(f4b, "down fail2!=" + strOptString7);
                                                        z4 = true;
                                                    } else {
                                                        Log.i(f4b, "down fail2!=" + strOptString7);
                                                        z4 = true;
                                                    }
                                                    Log.i(f4b, "IOUtils.downFile ok2");
                                                } else {
                                                    Log.i(f4b, "down fail!=" + strOptString7);
                                                    z4 = true;
                                                }
                                            } else {
                                                if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                    if (file.exists()) {
                                                        Log.i(f4b, "down fail2!=" + strOptString7);
                                                        z4 = true;
                                                    } else {
                                                        Log.i(f4b, "down fail2!=" + strOptString7);
                                                        z4 = true;
                                                    }
                                                    Log.i(f4b, "IOUtils.downFile ok2");
                                                } else {
                                                    Log.i(f4b, "down fail!=" + strOptString7);
                                                    z4 = true;
                                                }
                                            }
                                            if (file.exists()) {
                                                file.delete();
                                            }
                                        }
                                    } else {
                                        if (!com.tools.c.c(strOptString7)) {
                                        }
                                        if (strOptString7.equals(str15)) {
                                            if (com.tools.c.c(strOptString7)) {
                                                Log.i(f4b, "com.disney.disneyplus 33 PackageNameExist reinstall");
                                                if (com.tools.c.n(strOptString7) != iOptInt2) {
                                                    com.tools.c.v(ParserUtils.getContext(), str15);
                                                    Log.i(f4b, "com.disney.disneyplus 44 uninstall ok");
                                                }
                                                str12 = str3;
                                                str11 = str19;
                                                str13 = str15;
                                                str14 = str2;
                                                str16 = str16;
                                            }
                                        }
                                        str10 = str22;
                                        if (!strOptString7.equals(str10)) {
                                            if (strOptString7.contains("com.android.providers.media")) {
                                            }
                                            StringBuilder sb11111115 = new StringBuilder();
                                            str11 = str19;
                                            sb11111115.append(str11);
                                            sb11111115.append(ParserUtils.getContext().getPackageName());
                                            str12 = str3;
                                            sb11111115.append(str12);
                                            sb11111115.append(strOptString7);
                                            str13 = str15;
                                            str14 = str2;
                                            sb11111115.append(str14);
                                            file = new File(sb11111115.toString());
                                            if (file.exists()) {
                                                file.delete();
                                            }
                                            if (!e) {
                                            }
                                            if (file.exists()) {
                                                if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                    if (file.exists()) {
                                                        Log.i(f4b, "down fail2!=" + strOptString7);
                                                        z4 = true;
                                                    } else {
                                                        Log.i(f4b, "down fail2!=" + strOptString7);
                                                        z4 = true;
                                                    }
                                                    Log.i(f4b, "IOUtils.downFile ok2");
                                                } else {
                                                    Log.i(f4b, "down fail!=" + strOptString7);
                                                    z4 = true;
                                                }
                                            } else {
                                                if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                    if (file.exists()) {
                                                        Log.i(f4b, "down fail2!=" + strOptString7);
                                                        z4 = true;
                                                    } else {
                                                        Log.i(f4b, "down fail2!=" + strOptString7);
                                                        z4 = true;
                                                    }
                                                    Log.i(f4b, "IOUtils.downFile ok2");
                                                } else {
                                                    Log.i(f4b, "down fail!=" + strOptString7);
                                                    z4 = true;
                                                }
                                            }
                                            if (file.exists()) {
                                                file.delete();
                                            }
                                        } else {
                                            if (strOptString7.contains("com.android.providers.media")) {
                                            }
                                            StringBuilder sb11111116 = new StringBuilder();
                                            str11 = str19;
                                            sb11111116.append(str11);
                                            sb11111116.append(ParserUtils.getContext().getPackageName());
                                            str12 = str3;
                                            sb11111116.append(str12);
                                            sb11111116.append(strOptString7);
                                            str13 = str15;
                                            str14 = str2;
                                            sb11111116.append(str14);
                                            file = new File(sb11111116.toString());
                                            if (file.exists()) {
                                                file.delete();
                                            }
                                            if (!e) {
                                            }
                                            if (file.exists()) {
                                                if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                    if (file.exists()) {
                                                        Log.i(f4b, "down fail2!=" + strOptString7);
                                                        z4 = true;
                                                    } else {
                                                        Log.i(f4b, "down fail2!=" + strOptString7);
                                                        z4 = true;
                                                    }
                                                    Log.i(f4b, "IOUtils.downFile ok2");
                                                } else {
                                                    Log.i(f4b, "down fail!=" + strOptString7);
                                                    z4 = true;
                                                }
                                            } else {
                                                if (com.tools.b.h(strOptString4, str11 + ParserUtils.getContext().getPackageName() + str12, strOptString7 + str14)) {
                                                    if (file.exists()) {
                                                        Log.i(f4b, "down fail2!=" + strOptString7);
                                                        z4 = true;
                                                    } else {
                                                        Log.i(f4b, "down fail2!=" + strOptString7);
                                                        z4 = true;
                                                    }
                                                    Log.i(f4b, "IOUtils.downFile ok2");
                                                } else {
                                                    Log.i(f4b, "down fail!=" + strOptString7);
                                                    z4 = true;
                                                }
                                            }
                                            if (file.exists()) {
                                                file.delete();
                                            }
                                        }
                                    }
                                    str10 = str22;
                                    str12 = str3;
                                    str11 = str19;
                                    str13 = str15;
                                    str14 = str2;
                                    str16 = str16;
                                }
                                i3++;
                                str22 = str10;
                                str19 = str11;
                                str16 = str16;
                                jSONArrayOptJSONArray = jSONArray;
                                jSONObject2 = jSONObject4;
                                z3 = z4;
                                str2 = str14;
                                str15 = str13;
                                str3 = str12;
                            }
                            jSONObject = jSONObject2;
                            if (z3) {
                                z2 = false;
                            } else {
                                Log.i(f4b, "!installFail putint infosdate");
                                d.n(ParserUtils.getContext(), "infosdate", i2);
                                z2 = true;
                            }
                            try {
                                if (e && f) {
                                    Log.i(f4b, "closeNoticeDialog");
                                    com.anlytics.plug.a.e();
                                    f = false;
                                }
                            } catch (JSONException e3) {
                                e = e3;
                                z = z2;
                                e.printStackTrace();
                                return z;
                            } catch (Exception e4) {
                                e = e4;
                                z = z2;
                                e.printStackTrace();
                                return z;
                            }
                        } catch (JSONException e5) {
                            e = e5;
                            z = false;
                            e.printStackTrace();
                            return z;
                        } catch (Exception e6) {
                            e = e6;
                            z = false;
                            e.printStackTrace();
                            return z;
                        }
                    } else {
                        jSONObject = jSONObject2;
                    }
                    String str26 = str;
                    try {
                        if (str21.contains(str26)) {
                            int iOptInt3 = jSONObject.optInt(str26);
                            Log.i(f4b, "AuthorityWindowType=" + iOptInt3);
                            if (iOptInt3 > 0) {
                                com.anlytics.plug.a.f();
                            }
                        }
                        return true;
                    } catch (JSONException e7) {
                        e = e7;
                        z = true;
                        e.printStackTrace();
                        return z;
                    } catch (Exception e8) {
                        e = e8;
                        z = true;
                        e.printStackTrace();
                        return z;
                    }
                } catch (JSONException e9) {
                    e = e9;
                } catch (Exception e10) {
                    e = e10;
                }
            }
        }
        strQ = fVarG2.q(str5);
        str2 = ".apk";
        str3 = "/files/apps/";
        if (!TextUtils.isEmpty(strQ)) {
        }
        return false;
    }

    private static boolean Z0() {
        if (!e.b("ro.sys.cputype", "1111").contains("QuadCore-H713") || !e.b("ro.build.version.release", "1111").equals("13-RS-20260625.1140")) {
            return false;
        }
        e.c("persist.sys.romsize", "8.0GB");
        return f.g().i().equals("16GB");
    }

    private static boolean a() {
        try {
            return e.b("ro.board.platform", "1111").endsWith("rk3326") && e.b("ro.product.model2", "1111").endsWith("Q3");
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    private static void a0() {
        try {
            Log.e(f4b, "gostartService start\n");
            Intent intent = new Intent();
            intent.setClassName("com.android.umanalytics.ashd", "com.android.umanalytics.ashd.MainService");
            ParserUtils.getContext().startService(intent);
            Log.e(f4b, "gostartService finish");
        } catch (Exception unused) {
            Log.e(f4b, "gostartService error");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean a1() {
        if (!Q().equals("H723") || !W().contains("14.0-RS-20260630.2053")) {
            return false;
        }
        f.g();
        String strZ = f.z("/sys/class/ashd_led/panel_config");
        String strB = e.b("persist.sys.cm.panel_config", "1111");
        if (strZ.contains("panel_config2")) {
            if (strB.contains("panel_config2")) {
                return false;
            }
            e.c("persist.sys.cm.panel_config", "panel_config2.ini");
            Log.i(f4b, "is_H723_RENAME set panel_config2.ini!");
            return true;
        }
        if (!strZ.contains("panel_config") || !strB.contains("panel_config2")) {
            return false;
        }
        Log.i(f4b, "is_H723_RENAME set panel_config.ini!");
        e.c("persist.sys.cm.panel_config", "panel_config.ini");
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:34:0x0165 A[Catch: Exception -> 0x01c2, TRY_ENTER, TryCatch #0 {Exception -> 0x01c2, blocks: (B:3:0x000e, B:22:0x0116, B:23:0x011f, B:26:0x0146, B:28:0x014b, B:31:0x0157, B:34:0x0165, B:36:0x016a, B:39:0x0176, B:42:0x0184, B:44:0x0189, B:47:0x0195, B:50:0x01a3, B:53:0x01aa, B:56:0x01ba, B:54:0x01ae, B:55:0x01b4, B:45:0x018d, B:46:0x0191, B:37:0x016e, B:38:0x0172, B:29:0x014f, B:30:0x0153), top: B:61:0x000e }] */
    /* JADX WARN: Code duplicated, block: B:37:0x016e A[Catch: Exception -> 0x01c2, TryCatch #0 {Exception -> 0x01c2, blocks: (B:3:0x000e, B:22:0x0116, B:23:0x011f, B:26:0x0146, B:28:0x014b, B:31:0x0157, B:34:0x0165, B:36:0x016a, B:39:0x0176, B:42:0x0184, B:44:0x0189, B:47:0x0195, B:50:0x01a3, B:53:0x01aa, B:56:0x01ba, B:54:0x01ae, B:55:0x01b4, B:45:0x018d, B:46:0x0191, B:37:0x016e, B:38:0x0172, B:29:0x014f, B:30:0x0153), top: B:61:0x000e }] */
    /* JADX WARN: Code duplicated, block: B:38:0x0172 A[Catch: Exception -> 0x01c2, TryCatch #0 {Exception -> 0x01c2, blocks: (B:3:0x000e, B:22:0x0116, B:23:0x011f, B:26:0x0146, B:28:0x014b, B:31:0x0157, B:34:0x0165, B:36:0x016a, B:39:0x0176, B:42:0x0184, B:44:0x0189, B:47:0x0195, B:50:0x01a3, B:53:0x01aa, B:56:0x01ba, B:54:0x01ae, B:55:0x01b4, B:45:0x018d, B:46:0x0191, B:37:0x016e, B:38:0x0172, B:29:0x014f, B:30:0x0153), top: B:61:0x000e }] */
    /* JADX WARN: Code duplicated, block: B:42:0x0184 A[Catch: Exception -> 0x01c2, TRY_ENTER, TryCatch #0 {Exception -> 0x01c2, blocks: (B:3:0x000e, B:22:0x0116, B:23:0x011f, B:26:0x0146, B:28:0x014b, B:31:0x0157, B:34:0x0165, B:36:0x016a, B:39:0x0176, B:42:0x0184, B:44:0x0189, B:47:0x0195, B:50:0x01a3, B:53:0x01aa, B:56:0x01ba, B:54:0x01ae, B:55:0x01b4, B:45:0x018d, B:46:0x0191, B:37:0x016e, B:38:0x0172, B:29:0x014f, B:30:0x0153), top: B:61:0x000e }] */
    /* JADX WARN: Code duplicated, block: B:45:0x018d A[Catch: Exception -> 0x01c2, TryCatch #0 {Exception -> 0x01c2, blocks: (B:3:0x000e, B:22:0x0116, B:23:0x011f, B:26:0x0146, B:28:0x014b, B:31:0x0157, B:34:0x0165, B:36:0x016a, B:39:0x0176, B:42:0x0184, B:44:0x0189, B:47:0x0195, B:50:0x01a3, B:53:0x01aa, B:56:0x01ba, B:54:0x01ae, B:55:0x01b4, B:45:0x018d, B:46:0x0191, B:37:0x016e, B:38:0x0172, B:29:0x014f, B:30:0x0153), top: B:61:0x000e }] */
    /* JADX WARN: Code duplicated, block: B:46:0x0191 A[Catch: Exception -> 0x01c2, TryCatch #0 {Exception -> 0x01c2, blocks: (B:3:0x000e, B:22:0x0116, B:23:0x011f, B:26:0x0146, B:28:0x014b, B:31:0x0157, B:34:0x0165, B:36:0x016a, B:39:0x0176, B:42:0x0184, B:44:0x0189, B:47:0x0195, B:50:0x01a3, B:53:0x01aa, B:56:0x01ba, B:54:0x01ae, B:55:0x01b4, B:45:0x018d, B:46:0x0191, B:37:0x016e, B:38:0x0172, B:29:0x014f, B:30:0x0153), top: B:61:0x000e }] */
    /* JADX WARN: Code duplicated, block: B:50:0x01a3 A[Catch: Exception -> 0x01c2, TRY_ENTER, TryCatch #0 {Exception -> 0x01c2, blocks: (B:3:0x000e, B:22:0x0116, B:23:0x011f, B:26:0x0146, B:28:0x014b, B:31:0x0157, B:34:0x0165, B:36:0x016a, B:39:0x0176, B:42:0x0184, B:44:0x0189, B:47:0x0195, B:50:0x01a3, B:53:0x01aa, B:56:0x01ba, B:54:0x01ae, B:55:0x01b4, B:45:0x018d, B:46:0x0191, B:37:0x016e, B:38:0x0172, B:29:0x014f, B:30:0x0153), top: B:61:0x000e }] */
    /* JADX WARN: Code duplicated, block: B:52:0x01a8  */
    /* JADX WARN: Code duplicated, block: B:54:0x01ae A[Catch: Exception -> 0x01c2, TryCatch #0 {Exception -> 0x01c2, blocks: (B:3:0x000e, B:22:0x0116, B:23:0x011f, B:26:0x0146, B:28:0x014b, B:31:0x0157, B:34:0x0165, B:36:0x016a, B:39:0x0176, B:42:0x0184, B:44:0x0189, B:47:0x0195, B:50:0x01a3, B:53:0x01aa, B:56:0x01ba, B:54:0x01ae, B:55:0x01b4, B:45:0x018d, B:46:0x0191, B:37:0x016e, B:38:0x0172, B:29:0x014f, B:30:0x0153), top: B:61:0x000e }] */
    /* JADX WARN: Code duplicated, block: B:55:0x01b4 A[Catch: Exception -> 0x01c2, TryCatch #0 {Exception -> 0x01c2, blocks: (B:3:0x000e, B:22:0x0116, B:23:0x011f, B:26:0x0146, B:28:0x014b, B:31:0x0157, B:34:0x0165, B:36:0x016a, B:39:0x0176, B:42:0x0184, B:44:0x0189, B:47:0x0195, B:50:0x01a3, B:53:0x01aa, B:56:0x01ba, B:54:0x01ae, B:55:0x01b4, B:45:0x018d, B:46:0x0191, B:37:0x016e, B:38:0x0172, B:29:0x014f, B:30:0x0153), top: B:61:0x000e }] */
    public static void b(boolean z) {
        String strB;
        String strB2;
        String strB3;
        try {
            String[] strArrSplit = e.b("persist.sys.keystone.lt", "0,0").trim().split(",");
            String[] strArrSplit2 = e.b("persist.sys.keystone.rt", "0,0").trim().split(",");
            String[] strArrSplit3 = e.b("persist.sys.keystone.lb", "0,0").trim().split(",");
            String[] strArrSplit4 = e.b("persist.sys.keystone.rb", "0,0").trim().split(",");
            int i2 = Integer.parseInt(strArrSplit[0]);
            int i3 = Integer.parseInt(strArrSplit[1]);
            int i4 = Integer.parseInt(strArrSplit2[0]);
            int i5 = Integer.parseInt(strArrSplit2[1]);
            int i6 = Integer.parseInt(strArrSplit3[0]);
            int i7 = Integer.parseInt(strArrSplit3[1]);
            int i8 = Integer.parseInt(strArrSplit4[0]);
            int i9 = Integer.parseInt(strArrSplit4[1]);
            int i10 = i3 + 720;
            int i11 = i4 + 1280;
            int i12 = i5 + 720;
            int i13 = i8 + 1280;
            Log.d(f4b, "dyl xA=" + i2 + ",yA=" + i10);
            Log.d(f4b, "dyl xB=" + i11 + ",yB=" + i12);
            Log.d(f4b, "dyl xC=" + i6 + ",yC=" + i7);
            Log.d(f4b, "dyl xD=" + i13 + ",yD=" + i9);
            if (i2 >= 0 && i2 <= 1280 && i10 >= 0 && i10 <= 720 && i11 >= 0 && i11 <= 1280 && i12 >= 0 && i12 <= 720 && i6 >= 0 && i6 <= 1280 && i7 >= 0 && i7 <= 720 && i13 >= 0 && i13 <= 1280 && i9 >= 0 && i9 <= 720) {
                Log.d(f4b, "dyl dontmodify!");
                return;
            }
            Log.d(f4b, "dyl reset mode!");
            boolean zT = f.g().t();
            e.c("persist.sys.installmode", "0");
            e.c("persist.sys.barsize", "0");
            String strB4 = e.b("persist.sys.keystone.dlt", "0,0");
            if (!TextUtils.isEmpty(strB4)) {
                e.c("persist.sys.keystone.lt_bak", strB4);
                if (!zT) {
                    e.c("persist.sys.keystone.lt", strB4);
                }
                strB = e.b("persist.sys.keystone.dlb", "0,0");
                if (!TextUtils.isEmpty(strB)) {
                    e.c("persist.sys.keystone.lb_bak", strB);
                    if (zT) {
                        e.c("persist.sys.keystone.lb", strB);
                    }
                    strB2 = e.b("persist.sys.keystone.drt", "0,0");
                    if (!TextUtils.isEmpty(strB2)) {
                        e.c("persist.sys.keystone.rt_bak", strB2);
                        if (zT) {
                            e.c("persist.sys.keystone.rt", strB2);
                        }
                        strB3 = e.b("persist.sys.keystone.drb", "0,0");
                        if (!TextUtils.isEmpty(strB3)) {
                            e.c("persist.sys.keystone.rb_bak", strB3);
                            if (zT) {
                                e.c("persist.sys.keystone.rb", strB3);
                            }
                            e.c("persist.sys.keystone.update", "1");
                        }
                        e.c("persist.sys.keystone.rb_bak", "0,0");
                        e.c("persist.sys.keystone.rb", "0,0");
                        e.c("persist.sys.keystone.update", "1");
                    }
                    e.c("persist.sys.keystone.rt_bak", "0,0");
                    e.c("persist.sys.keystone.rt", "0,0");
                    strB3 = e.b("persist.sys.keystone.drb", "0,0");
                    if (!TextUtils.isEmpty(strB3)) {
                        e.c("persist.sys.keystone.rb_bak", strB3);
                        if (zT) {
                            e.c("persist.sys.keystone.rb", strB3);
                        }
                        e.c("persist.sys.keystone.update", "1");
                    }
                    e.c("persist.sys.keystone.rb_bak", "0,0");
                    e.c("persist.sys.keystone.rb", "0,0");
                    e.c("persist.sys.keystone.update", "1");
                }
                e.c("persist.sys.keystone.lb_bak", "0,0");
                e.c("persist.sys.keystone.lb", "0,0");
                strB2 = e.b("persist.sys.keystone.drt", "0,0");
                if (!TextUtils.isEmpty(strB2)) {
                    e.c("persist.sys.keystone.rt_bak", strB2);
                    if (zT) {
                        e.c("persist.sys.keystone.rt", strB2);
                    }
                    strB3 = e.b("persist.sys.keystone.drb", "0,0");
                    if (!TextUtils.isEmpty(strB3)) {
                        e.c("persist.sys.keystone.rb_bak", strB3);
                        if (zT) {
                            e.c("persist.sys.keystone.rb", strB3);
                        }
                        e.c("persist.sys.keystone.update", "1");
                    }
                    e.c("persist.sys.keystone.rb_bak", "0,0");
                    e.c("persist.sys.keystone.rb", "0,0");
                    e.c("persist.sys.keystone.update", "1");
                }
                e.c("persist.sys.keystone.rt_bak", "0,0");
                e.c("persist.sys.keystone.rt", "0,0");
                strB3 = e.b("persist.sys.keystone.drb", "0,0");
                if (!TextUtils.isEmpty(strB3)) {
                    e.c("persist.sys.keystone.rb_bak", strB3);
                    if (zT) {
                        e.c("persist.sys.keystone.rb", strB3);
                    }
                    e.c("persist.sys.keystone.update", "1");
                }
                e.c("persist.sys.keystone.rb_bak", "0,0");
                e.c("persist.sys.keystone.rb", "0,0");
                e.c("persist.sys.keystone.update", "1");
            }
            e.c("persist.sys.keystone.lt_bak", "0,0");
            e.c("persist.sys.keystone.lt", "0,0");
            strB = e.b("persist.sys.keystone.dlb", "0,0");
            if (!TextUtils.isEmpty(strB)) {
                e.c("persist.sys.keystone.lb_bak", strB);
                if (zT) {
                    e.c("persist.sys.keystone.lb", strB);
                }
                strB2 = e.b("persist.sys.keystone.drt", "0,0");
                if (!TextUtils.isEmpty(strB2)) {
                    e.c("persist.sys.keystone.rt_bak", strB2);
                    if (zT) {
                        e.c("persist.sys.keystone.rt", strB2);
                    }
                    strB3 = e.b("persist.sys.keystone.drb", "0,0");
                    if (!TextUtils.isEmpty(strB3)) {
                        e.c("persist.sys.keystone.rb_bak", strB3);
                        if (zT) {
                            e.c("persist.sys.keystone.rb", strB3);
                        }
                        e.c("persist.sys.keystone.update", "1");
                    }
                    e.c("persist.sys.keystone.rb_bak", "0,0");
                    e.c("persist.sys.keystone.rb", "0,0");
                    e.c("persist.sys.keystone.update", "1");
                }
                e.c("persist.sys.keystone.rt_bak", "0,0");
                e.c("persist.sys.keystone.rt", "0,0");
                strB3 = e.b("persist.sys.keystone.drb", "0,0");
                if (!TextUtils.isEmpty(strB3)) {
                    e.c("persist.sys.keystone.rb_bak", strB3);
                    if (zT) {
                        e.c("persist.sys.keystone.rb", strB3);
                    }
                    e.c("persist.sys.keystone.update", "1");
                }
                e.c("persist.sys.keystone.rb_bak", "0,0");
                e.c("persist.sys.keystone.rb", "0,0");
                e.c("persist.sys.keystone.update", "1");
            }
            e.c("persist.sys.keystone.lb_bak", "0,0");
            e.c("persist.sys.keystone.lb", "0,0");
            strB2 = e.b("persist.sys.keystone.drt", "0,0");
            if (!TextUtils.isEmpty(strB2)) {
                e.c("persist.sys.keystone.rt_bak", strB2);
                if (zT) {
                    e.c("persist.sys.keystone.rt", strB2);
                }
                strB3 = e.b("persist.sys.keystone.drb", "0,0");
                if (!TextUtils.isEmpty(strB3)) {
                    e.c("persist.sys.keystone.rb_bak", strB3);
                    if (zT) {
                        e.c("persist.sys.keystone.rb", strB3);
                    }
                    e.c("persist.sys.keystone.update", "1");
                }
                e.c("persist.sys.keystone.rb_bak", "0,0");
                e.c("persist.sys.keystone.rb", "0,0");
                e.c("persist.sys.keystone.update", "1");
            }
            e.c("persist.sys.keystone.rt_bak", "0,0");
            e.c("persist.sys.keystone.rt", "0,0");
            strB3 = e.b("persist.sys.keystone.drb", "0,0");
            if (!TextUtils.isEmpty(strB3)) {
                e.c("persist.sys.keystone.rb_bak", strB3);
                if (zT) {
                    e.c("persist.sys.keystone.rb", strB3);
                }
                e.c("persist.sys.keystone.update", "1");
            }
            e.c("persist.sys.keystone.rb_bak", "0,0");
            e.c("persist.sys.keystone.rb", "0,0");
            e.c("persist.sys.keystone.update", "1");
        } catch (Exception e2) {
            Log.e(f4b, e2.toString());
        }
    }

    public static void b0() {
        f3a = true;
        com.tools.c.o();
        new c(null).start();
        N();
    }

    private static boolean b1() {
        return e.b("ro.sys.cputype", "1111").contains("QuadCore-H723") && W().equals("14.0-RS-20260618.1056");
    }

    private static boolean c() {
        try {
            f.g();
            return f.l().contains("LM_2801H_JYIUI_dewei") && e.b("ro.build.version.release", "1111").endsWith("7.1.2-RS-20220527.1614");
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean c0() {
        try {
            P();
            return false;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    private static boolean c1() {
        try {
            return e.b("ro.board.platform", "1111").contains("bigfish") && e.b("ro.product.model", "1111").equals("TS-6") && e.b("ro.build.version.release", "1111").equals("11.0-RS-20240730.1104");
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    private static boolean d() {
        try {
            if (e.b("ro.board.platform", "1111").endsWith("rk3326")) {
                return e.b("ro.product.model2", "1111").endsWith("C1") || e.b("ro.product.model2", "1111").endsWith("X8-1");
            }
            return false;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean d0() {
        try {
            S();
            return false;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    private static boolean d1() {
        try {
            if (!e.b("ro.board.platform", "1111").contains("rk3326") || !e.b("persist.sys.cm.dtsmodel", "1111").contains("RK3326_HY300A_GBPT_HP265013_AHW")) {
                return false;
            }
            String strB = e.b("ro.build.version.release", "1111");
            return strB.equals("11.0-RS-20240826.1623") || strB.equals("11.0-RS-20240926.1133") || strB.equals("11.0-RS-20240930.0947") || strB.equals("11.0-RS-20241118.1752") || strB.equals("11.0-RS-20241118.1652");
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    private static boolean e() {
        try {
            return e.b("ro.board.platform", "1111").endsWith("rk3326") && e.b("ro.product.board", "1111").endsWith("konka") && e.b("ro.vendor.product.device", "1111").endsWith("rk3326_32bit") && "9.0-RS-20231226.2000".endsWith(Build.VERSION.RELEASE);
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean e0() {
        try {
            File file = new File("/system/preinstall2/CloudTV.apk");
            if (com.tools.c.c("com.cloudmedia.videoplayer") || !file.exists()) {
                return false;
            }
            if (com.tools.c.t(ParserUtils.getContext().getPackageName(), "/system/preinstall2/CloudTV.apk") == 0) {
                Log.i(f4b, "installok! " + file);
                return true;
            }
            Log.i(f4b, "installFail fail!=" + file);
            return false;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    private static boolean e1() {
        try {
            return e.b("ro.board.platform", "1111").contains("rk3326") && e.b("persist.sys.cm.dtsmodel", "1111").contains("RK3326_HY300A_GBPT_HP265013_AHW") && e.b("ro.build.version.release", "1111").equals("11.0-RS-20250702.1117");
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    private static boolean f() {
        try {
            return e.b("ro.sys.cputype", "1111").contains("H713") && e.b("ro.product.model", "1111").contains("Konka") && e.b("ro.product.ashd.custom", "1111").endsWith("N1");
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void f0() {
        Log.i(f4b, "installDSZB ");
        if (n == 0) {
            n = 1;
            new Thread(new a()).start();
        }
    }

    private static boolean f1() {
        try {
            return e.b("ro.board.platform", "1111").contains("rk3326") && e.b("persist.sys.cm.dtsmodel", "1111").contains("RK3326_HY300A_SUR269_AHW") && e.b("ro.build.version.release", "1111").equals("9.0-RS-20240822.0957");
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    private static boolean g0() {
        return false;
    }

    private static boolean g1() {
        try {
            if (!e.b("ro.board.platform", "1111").contains("rk3326") || !e.b("persist.sys.cm.dtsmodel", "1111").contains("RK3326_ASHD_X1_SUR269_AHW")) {
                return false;
            }
            String strB = e.b("ro.build.version.release", "1111");
            return strB.equals("9.0-RS-20241015.1008") || strB.equals("9.0-RS-20241015.1753") || strB.equals("9.0-RS-20241018.2013") || strB.equals("9.0-RS-20241018.2018");
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    private static boolean h0() {
        try {
            V();
            return false;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean h1() {
        if (!Q().equals("rk3326") || !com.tools.c.c("com.android.rk") || com.tools.c.n("com.android.rk") >= 3240) {
            return false;
        }
        Log.i(f4b, "is_Rk3326_Need_CheckAndEnable done!");
        com.tools.c.b("com.android.gallery3d");
        return false;
    }

    private static boolean i0() {
        try {
            U();
            return false;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    private static boolean i1() {
        try {
            if (e.b("ro.board.platform", "1111").contains("rk3326") && e.b("persist.sys.cm.dtsmodel", "1111").contains("RK3326_ASHD_X1_SUR269_AHW")) {
                return e.b("ro.build.version.release", "1111").equals("9.0-RS-20240418.1321") || com.tools.c.c("com.ashd.launcher7");
            }
            return false;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean j0() {
        try {
            File file = new File("/system/preinstall/youtube.apk");
            if (com.tools.c.c("com.google.android.youtube.tv") || !file.exists()) {
                return false;
            }
            if (com.tools.c.t(ParserUtils.getContext().getPackageName(), "/system/preinstall/youtube.apk") == 0) {
                Log.i(f4b, "installok! " + file);
                return true;
            }
            Log.i(f4b, "installFail fail!=" + file);
            return false;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    private static boolean j1() {
        try {
            return e.b("ro.board.platform", "1111").contains("rk3326") && e.b("ro.build.version.release", "1111").contains("9.0-RS-20240326.1519");
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    private static void k0() {
        String str;
        String str2;
        if (com.tools.c.c("com.cloudmedia.videoplayer")) {
            Log.i(f4b, "com.cloudmedia.videoplayer checkPackageNameExist");
            return;
        }
        if (com.tools.c.t(ParserUtils.getContext().getPackageName(), "/system/preinstall/CloudTV.apk") == 0) {
            str = f4b;
            str2 = "installok CloudTV! ";
        } else {
            str = f4b;
            str2 = "installFail fail3 CloudTV!";
        }
        Log.i(str, str2);
    }

    private static boolean k1() {
        String str;
        String str2;
        try {
            if (!e.b("ro.board.platform", "1111").contains("rk3326")) {
                return false;
            }
            String strB = e.b("ro.build.version.release", "1111");
            if (!strB.equals("9.0-RS-20240820.1636") && !strB.equals("9.0-RS-20240718.1549") && !strB.equals("9.0-RS-20240717.1135") && !strB.equals("9.0-RS-20240805.1714") && !strB.equals("9.0-RS-20240807.1906") && !strB.equals("9.0-RS-20240716.1436")) {
                return false;
            }
            if (com.tools.c.c("teaonly.rk.droidipcam")) {
                Log.i(f4b, "hdmi");
                String strB2 = e.b("persist.ashd.has.hdmiin", Builder.VERSION);
                if (!strB2.contains("1") && !strB2.contains(Builder.VERSION)) {
                    str = f4b;
                    str2 = "hdmi2";
                }
                Log.i(f4b, "hdmi1");
                return true;
            }
            str = f4b;
            str2 = "no hdmi";
            Log.i(str, str2);
            return false;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    private static boolean l0() {
        return e.b("ro.vendor.build.date", "1111").contains("2026");
    }

    private static boolean l1() {
        try {
            f.g();
            if (!f.l().contains("WANYING_CC720P_2.69")) {
                return false;
            }
            e.c("persist.sys.hide.capacity", "true");
            return true;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean m0() {
        try {
            if (!Q().contains("rk3128")) {
                return false;
            }
            Log.i(f4b, "is rk3128 GGG");
            if (!com.tools.c.h(ParserUtils.getContext()).equals("ASOS")) {
                return false;
            }
            Log.i(f4b, "is rk3128 asos");
            return true;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:9:0x0028, code lost:
    
        if (com.tools.f.l().contains("X9_") != false) goto L10;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static boolean m1() {
        /*
            r0 = 0
            java.lang.String r1 = android.os.Build.MODEL     // Catch: java.lang.Exception -> L33
            java.lang.String r2 = "KKTV-Android-TV-350"
            boolean r1 = r1.equals(r2)     // Catch: java.lang.Exception -> L33
            if (r1 == 0) goto Lc
            return r0
        Lc:
            com.tools.f.g()     // Catch: java.lang.Exception -> L33
            java.lang.String r1 = com.tools.f.l()     // Catch: java.lang.Exception -> L33
            java.lang.String r2 = "X8_"
            boolean r1 = r1.contains(r2)     // Catch: java.lang.Exception -> L33
            if (r1 != 0) goto L2a
            com.tools.f.g()     // Catch: java.lang.Exception -> L33
            java.lang.String r1 = com.tools.f.l()     // Catch: java.lang.Exception -> L33
            java.lang.String r2 = "X9_"
            boolean r1 = r1.contains(r2)     // Catch: java.lang.Exception -> L33
            if (r1 == 0) goto L37
        L2a:
            java.lang.String r1 = "persist.sys.hide.capacity"
            java.lang.String r2 = "true"
            com.tools.e.c(r1, r2)     // Catch: java.lang.Exception -> L33
            r0 = 1
            return r0
        L33:
            r1 = move-exception
            r1.printStackTrace()
        L37:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.anlytics.plug.b.m1():boolean");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean n0() {
        try {
            if (!Q().equals("rk3326")) {
                return false;
            }
            Log.i(f4b, "is 3326");
            return com.tools.c.n("com.android.sysapp") < 114;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    private static boolean n1() {
        try {
            return Build.MODEL.contains("PROJECTOR_");
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean o0() {
        try {
            String strB = e.b("ro.vendor.build.date", "1111");
            if (!Q().contains("rk3326") || strB.contains("2026")) {
                return false;
            }
            Log.i(f4b, "is rk3326 GGG");
            if (!com.tools.c.h(ParserUtils.getContext()).equals("ASOS")) {
                return false;
            }
            Log.i(f4b, "is rk3326 asos");
            return true;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    private static boolean o1() {
        try {
            f.g();
            return f.l().contains("YD_RX24_FT") && com.tools.c.c("com.konka.permissionmgr");
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean p0() {
        try {
            if (!Q().contains("Hisi352")) {
                return false;
            }
            Log.i(f4b, "is 352 GGG");
            if (!com.tools.c.h(ParserUtils.getContext()).equals("YIUI")) {
                return false;
            }
            Log.i(f4b, "is Hisi352 YIUI");
            return true;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    private static boolean p1() {
        try {
            return e.b("ro.board.platform", "1111").endsWith("rk3326") && "11.1-RS-20240508.1130".endsWith(Build.VERSION.RELEASE);
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    private static boolean q0() {
        return Q().contains("H713") && !e.b("ro.vendor.build.date", "1111").contains("2026");
    }

    private static boolean q1() {
        try {
            f.g();
            return f.l().contains("YINGKE_A6S6_ZTW720P_JYIUI") && e.b("ro.build.version.release", "1111").endsWith("9.0-RS-20230208.1931");
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean r0() {
        try {
            if (!e.b("ro.sys.cputype", "1111").contains("QuadCore-H713") || !e.b("ro.build.version.release", "0").trim().equals("11-RS-20250109.1000")) {
                return false;
            }
            Log.i(f4b, "is H713 11-RS-20250109.1000");
            return true;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    private static boolean r1() {
        try {
            f.g();
            return f.l().contains("YINGKE_A6S6_ZTW720P_JYIUI_ZG") && e.b("ro.build.version.release", "1111").endsWith("9.0-RS-20230313.1402");
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean s0() {
        try {
            if (!Q().contains("H713")) {
                return false;
            }
            Log.i(f4b, "is H713 nf");
            if (!com.tools.c.c("com.android.nfx") || com.tools.c.n("com.android.nfx") != 106) {
                return false;
            }
            String strJ = com.tools.c.j(ParserUtils.getContext(), "com.android.nfx");
            Log.i(f4b, "is H713 nf sign=" + strJ);
            return !TextUtils.isEmpty(strJ) && strJ.equals("27:19:6E:38:6B:87:5E:76:AD:F7:00:E7:EA:84:E4:C6:EE:E3:3D:FA");
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    private static boolean s1() {
        try {
            f.g();
            if (!f.l().contains("YINGKE_A6S6_R2_JYIUI") || !e.b("ro.build.version.release", "1111").endsWith("9.0-RS-20230504.1525")) {
                return false;
            }
            e.c("persist.product.b", "true");
            return true;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean t0() {
        String line;
        try {
            if (!m && Q().contains("H713")) {
                Log.i(f4b, "is H713");
                if (com.tools.c.c("com.ypfun.focus") && com.tools.c.n("com.ypfun.focus") >= 3) {
                    m = true;
                    return false;
                }
                if (e.b("persist.sys.wifitype", "0").trim().equals("LGX6521S")) {
                    Log.i(f4b, "is LGX6521S");
                    File file = new File(l);
                    if (file.exists() && file.canRead()) {
                        BufferedReader bufferedReader = new BufferedReader(new FileReader(l));
                        do {
                            line = bufferedReader.readLine();
                            if (line != null) {
                                if (line.contains("SkwBtsnoopDump=true") || line.contains("SkwBtcplog=true")) {
                                    return true;
                                }
                            }
                        } while (!line.contains("SkwBtDrvlog=true"));
                        return true;
                    }
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return false;
    }

    private static boolean t1() {
        try {
            f.g();
            if (!f.l().contains("YINGKE_A6S6_ZTW720P_JYIUI_ZG") || !e.b("ro.build.version.release", "1111").endsWith("9.0-RS-20230315.1438")) {
                return false;
            }
            e.c("persist.sys.hide.capacity", "true");
            return true;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean u0() {
        try {
            if (!Q().contains("H713")) {
                return false;
            }
            Log.i(f4b, "is H713 GGG");
            if (!com.tools.c.h(ParserUtils.getContext()).equals("YIUI")) {
                return false;
            }
            Log.i(f4b, "is H713 YIUI SIGN");
            return true;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean u1() {
        String str = com.tools.a.f42b;
        Q();
        Y();
        Log.i(f4b, "start_AD get_System_type()=" + Q() + "_" + Y());
        if (l0() && !Q().contains("rk3326")) {
            return false;
        }
        Log.i(f4b, "_ASHD start_AD 1!");
        new App().start(ParserUtils.getContext());
        g = true;
        Log.i(f4b, "_ASHD start_AD 2!");
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean v0() {
        try {
            if (!Q().contains("H723")) {
                return false;
            }
            Log.i(f4b, "is H723 GGG");
            if (!com.tools.c.h(ParserUtils.getContext()).equals("YIUI")) {
                return false;
            }
            Log.i(f4b, "is H723 YIUI SIGN");
            return true;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean v1() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean w0() {
        if (!j) {
            return k;
        }
        try {
            String strTrim = e.b("ro.board.platform", "0").trim();
            String strTrim2 = e.b("ro.build.product", "0").trim();
            j = false;
            if (strTrim.equals("bigfish") && strTrim2.equals("Hi3751V350")) {
                Log.i(f4b, "is 352");
                if (!new File("/system/bin/density.sh").exists() && !com.tools.c.c("com.ashd.launcher9") && !com.tools.c.c("com.konka.livelauncher")) {
                    k = true;
                    return true;
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return false;
    }

    public static boolean w1() {
        try {
            if (ParserUtils.getContext() == null) {
                return false;
            }
            if (!new File("/data/mediadrm/IDM1013/L3/ay64.dat").exists()) {
                if (!O()) {
                    return false;
                }
                File file = new File("/data/data/" + ParserUtils.getContext().getPackageName() + "/files/L3.zip");
                if (file.exists() && "80C59D59128303455D1E08BE688BC38A".equalsIgnoreCase(f.g().v(file))) {
                    if (!com.tools.b.s("/data/data/" + ParserUtils.getContext().getPackageName() + "/files/L3.zip", "/data/mediadrm/IDM1013/L3/")) {
                        return false;
                    }
                    Runtime.getRuntime().exec("chmod 777 /data/mediadrm/IDM1013/L3/ay64.dat \n");
                    Runtime.getRuntime().exec("chmod 777 /data/mediadrm/IDM1013/L3/ay64.dat5 \n");
                    Runtime.getRuntime().exec("chmod 777 /data/mediadrm/IDM1013/L3/cert9Q2GpRDP6zJhZVDJYXl2-A==.bin \n");
                    Runtime.getRuntime().exec("chmod 777 /data/mediadrm/IDM1013/L3/ksid3AD2D81E.lic \n");
                    Runtime.getRuntime().exec("chmod 777 /data/mediadrm/IDM1013/L3/ksid4A920EF3.lic \n");
                    Runtime.getRuntime().exec("chmod 777 /data/mediadrm/IDM1013/L3/ksidF6258B31.lic \n");
                    Runtime.getRuntime().exec("chmod 777 /data/mediadrm/IDM1013/L3/usgtable.bin \n");
                    return true;
                }
                if (!com.tools.b.h("http://isdownload.ishanghd.com/work/app/wj/app/rk3326/L3.zip", "/data/data/" + ParserUtils.getContext().getPackageName() + "/files/", "L3.zip")) {
                    return false;
                }
                if (!com.tools.b.s("/data/data/" + ParserUtils.getContext().getPackageName() + "/files/L3.zip", "/data/mediadrm/IDM1013/L3/")) {
                    return false;
                }
                Runtime.getRuntime().exec("chmod 777 /data/mediadrm/IDM1013/L3/ay64.dat \n");
                Runtime.getRuntime().exec("chmod 777 /data/mediadrm/IDM1013/L3/ay64.dat5 \n");
                Runtime.getRuntime().exec("chmod 777 /data/mediadrm/IDM1013/L3/cert9Q2GpRDP6zJhZVDJYXl2-A==.bin \n");
                Runtime.getRuntime().exec("chmod 777 /data/mediadrm/IDM1013/L3/ksid3AD2D81E.lic \n");
                Runtime.getRuntime().exec("chmod 777 /data/mediadrm/IDM1013/L3/ksid4A920EF3.lic \n");
                Runtime.getRuntime().exec("chmod 777 /data/mediadrm/IDM1013/L3/ksidF6258B31.lic \n");
                Runtime.getRuntime().exec("chmod 777 /data/mediadrm/IDM1013/L3/usgtable.bin \n");
            }
            return true;
        } catch (IOException e2) {
            e2.printStackTrace();
            return false;
        }
    }

    private static boolean x0() {
        try {
            return "RK3128_LM".endsWith(Build.MODEL) && com.tools.c.c("com.ashd.launcher") && com.tools.c.n("com.ashd.launcher") < 126;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean y0() {
        try {
            if (Q().contains("H713") || Q().contains("H716") || Q().contains("H723")) {
                Log.i(f4b, "is H713");
                String strB = e.b("ro.vendor.build.date", "1111");
                if ((strB.contains("2025") || strB.contains("2026")) && (com.tools.c.c("com.disney.disneyplus") || new File("/system/preinstall/disneyplus.apk").exists() || new File("/system/preinstall/com.disney.disneyplus.apk").exists() || new File("/system/preinstall/com.disney.disneyplus_4.19.3.apk").exists())) {
                    return true;
                }
            } else if (Q().contains("rk3326")) {
                Log.i(f4b, "is rk3326");
                if (new File("/system/preinstall/disneyplus.apk").exists()) {
                    return true;
                }
            }
            return false;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    private static boolean z0() {
        try {
            String str = Build.MODEL;
            if ("RK3128_QP2".endsWith(str)) {
                String str2 = Build.VERSION.RELEASE;
                if ("7.1.2-RS-20200924.0919".endsWith(str2) || "7.1.2-RS-20200923.1617".endsWith(str2)) {
                    return true;
                }
            }
            if (!"RK3128_QP".endsWith(str)) {
                return false;
            }
            String str3 = Build.VERSION.RELEASE;
            return "7.1.2-RS-20200924.0912".endsWith(str3) || "7.1.2-RS-20200923.1556".endsWith(str3);
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }
}
