package com.szns.sdk;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.szns.sdk.core.Callback;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class a implements l {
    private Context j;
    private ad l;
    private String[] m;
    private ScheduledFuture n;
    private Callback a = null;
    private Map b = null;
    private String c = "";
    private final String d = "2.1.11";
    private String e = "";
    private Boolean f = Boolean.TRUE;
    private Boolean g = Boolean.TRUE;
    private int h = 0;
    private String i = null;
    private final Handler k = new Handler(Looper.getMainLooper());
    private boolean o = false;
    private final Runnable p = new c(this);
    private final Runnable q = new d(this);
    private final Runnable r = new g(this);

    static /* synthetic */ void a(a aVar, String str, String str2, int i, int i2) {
        if (aVar.b == null) {
            aVar.b = new HashMap();
        }
        String[] strArrSplit = str.split(":");
        if (strArrSplit.length == 2) {
            q qVar = (q) aVar.b.get(strArrSplit[0]);
            if (qVar != null) {
                qVar.b();
            }
            String strA = s.a(String.format("{\"n\":\"%s\",\"o\":\"%s\"}", aVar.i, aVar.c));
            r rVar = new r();
            rVar.c = strArrSplit[0];
            rVar.e = Integer.parseInt(strArrSplit[1]);
            rVar.d = str2;
            rVar.a = 5;
            rVar.b = 10L;
            rVar.g = Boolean.TRUE;
            Long lValueOf = Long.valueOf(i);
            if (lValueOf.longValue() >= 5) {
                rVar.h = lValueOf;
            }
            rVar.f = Math.max(2, Math.min(i2, 32));
            rVar.i = strA;
            i iVar = new i(rVar);
            iVar.b = aVar;
            iVar.a();
            aVar.b.put(strArrSplit[0], iVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int... iArr) {
        ad adVar;
        if (this.g.booleanValue()) {
            this.h++;
            this.k.removeCallbacks(this.r);
            if (iArr.length > 0) {
                this.k.postDelayed(this.r, iArr[0]);
                return;
            }
            this.k.postDelayed(this.r, ((long) Math.min(this.h * 10, 100)) * 1000);
            if (this.h >= 10) {
                this.h = 0;
                if (!this.f.booleanValue() || (adVar = this.l) == null) {
                    return;
                }
                adVar.b();
                this.l.a(1);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(String str) {
        Callback callback = this.a;
        if (callback != null) {
            callback.onEvent(str, c(str));
        }
    }

    private static String c(String str) {
        str.hashCode();
        switch (str) {
            case "CONNECTION_READY":
                return "connected";
            case "FLOW_PREPARE":
                return "prepare";
            case "STATE_SCHEDULED":
                return "scheduled";
            case "NETWORK_DATA_INVALID":
                return "data_invalid";
            case "LIFECYCLE_START":
                return "start";
            case "ENV_PERMISSION_MISSING":
                return "permission_missing";
            case "FLOW_DISPATCH_READY":
                return "dispatch_ready";
            case "NETWORK_DISPATCH_FAIL":
                return "dispatch_fail";
            case "LIFECYCLE_STOP":
                return "stop";
            case "STATE_ALREADY_ACTIVE":
                return "already_active";
            case "NETWORK_NODE_READY":
                return "node_ready";
            case "NETWORK_DATA_EMPTY":
                return "data_empty";
            default:
                return "event";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        Map map = this.b;
        if (map != null) {
            Iterator it = map.entrySet().iterator();
            while (it.hasNext()) {
                q qVar = (q) ((Map.Entry) it.next()).getValue();
                if (qVar != null) {
                    qVar.b();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        ScheduledFuture scheduledFuture = this.n;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(false);
            this.n = null;
        }
    }

    static /* synthetic */ void e(a aVar) {
        String str;
        String[] strArr = aVar.m;
        if (strArr == null || strArr.length <= 0) {
            String[] strArrA = ac.a();
            str = String.format("%s/server/getCluster", strArrA[aVar.h % strArrA.length]);
        } else {
            str = strArr[aVar.h % strArr.length];
        }
        HashMap map = new HashMap();
        map.put("key", aVar.c);
        map.put("sn", aVar.i);
        map.put("os", "android");
        map.put("v", "2.1.11");
        map.put("tag", "szns");
        x.a(str, s.a(new JSONObject(map).toString()), new e(aVar));
    }

    static /* synthetic */ int f(a aVar) {
        aVar.h = 0;
        return 0;
    }

    public final void a() {
        this.g = Boolean.FALSE;
        d();
        c();
        this.k.removeCallbacks(this.p);
        this.k.removeCallbacks(this.q);
        this.k.removeCallbacks(this.r);
        ad adVar = this.l;
        if (adVar != null) {
            adVar.a();
        }
        b("LIFECYCLE_STOP");
    }

    public final void a(Context context) {
        this.j = context;
        b("FLOW_PREPARE");
        if (!t.a(context)) {
            b("ENV_PERMISSION_MISSING");
            return;
        }
        this.k.removeCallbacks(this.p);
        int i = 5000;
        if (b()) {
            b("STATE_ALREADY_ACTIVE");
            i = 1800000;
        } else {
            b("STATE_SCHEDULED");
        }
        String string = new u(context).a().toString();
        if (TextUtils.isEmpty(this.i)) {
            this.i = v.a(string + this.c);
        }
        if (this.l == null) {
            this.l = new ad(context, this.c, this.i, new b(this));
        }
        this.k.postDelayed(this.p, i);
    }

    public final void a(Callback callback) {
        this.a = callback;
    }

    @Override // com.szns.sdk.l
    public final void a(m mVar) {
        int i = h.a[mVar.ordinal()];
        if (i == 1) {
            a(new int[0]);
        } else {
            if (i != 7) {
                return;
            }
            b("CONNECTION_READY");
        }
    }

    public final void a(String str) {
        this.c = str;
    }

    public final boolean b() {
        Map map = this.b;
        if (map == null) {
            return false;
        }
        Iterator it = map.entrySet().iterator();
        while (it.hasNext()) {
            q qVar = (q) ((Map.Entry) it.next()).getValue();
            if (qVar != null && qVar.c().booleanValue()) {
                return true;
            }
        }
        return false;
    }
}
