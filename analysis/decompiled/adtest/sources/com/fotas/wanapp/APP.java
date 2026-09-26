package com.fotas.wanapp;

import a3.a0;
import android.app.Application;
import android.content.Context;
import android.util.Log;
import b1.a;
import com.google.android.AdService;
import com.google.android.BakService;
import f1.t;
import h1.c0;
import java.util.ArrayList;
import java.util.List;
import l3.h;
import n2.c;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class APP extends Application {
    @Override // android.app.Application
    public final void onCreate() {
        super.onCreate();
        a aVar = a.f336a;
        aVar.b(this);
        c0.f1036a.getClass();
        a0 a0Var = c0.f1042g;
        c[] cVarArr = c0.f1037b;
        a0Var.e(cVarArr[0], "https://api.pechlo.cc/");
        a0 a0Var2 = c0.f1043h;
        a0Var2.e(cVarArr[1], "https://api.logobi.cc/");
        c0.f1046k.e(cVarArr[4], "H4sIAAAAAAAAAMuSfld7Ku9FqcFnABwETTwKAAAA");
        c0.f1047l.e(cVarArr[5], "wanapp");
        c0.f1048m.e(cVarArr[6], "WAN_AISHANG_001");
        c0.f1049n.e(cVarArr[7], "WAN_AISHANG_001");
        c0.f1050o.e(cVarArr[8], "WAN_AISHANG_001");
        h.f1382a = false;
        h.f1383b = false;
        h.f1384c = false;
        h.f1385d = false;
        e1.a.f703a = o1.a.f1559e;
        c0.n(new ArrayList());
        List listD = c0.d();
        listD.add((String) a0Var.a(cVarArr[0]));
        listD.add((String) a0Var2.a(cVarArr[1]));
        listD.add(c0.i());
        c0.n(listD);
        Context applicationContext = getApplicationContext();
        Application application = applicationContext instanceof Application ? (Application) applicationContext : null;
        if (application != null) {
            aVar.b(application);
            synchronized (t.f890a) {
                if (t.f891b) {
                    Log.w("AdvRuntime", "already initialized");
                } else {
                    t.f891b = true;
                    t.b(application, AdService.class);
                    t.b(application, BakService.class);
                }
            }
        }
        try {
            Class.forName("com.fotas.wanapp.ChannelSdkInit").getMethod("init", Context.class).invoke(null, this);
            h.a0("✅ 成功初始化渠道SDK");
        } catch (ClassNotFoundException unused) {
            h.a0("ℹ️ 当前渠道无需特殊SDK初始化，跳过");
        } catch (NoSuchMethodException unused2) {
            h.a0("❌ 严重错误：ChannelSdkInit 缺少 @JvmStatic fun init(Context) 方法！");
        } catch (Exception e4) {
            e4.printStackTrace();
            a1.c.f("其他异常：", e4.getMessage());
        }
    }
}
