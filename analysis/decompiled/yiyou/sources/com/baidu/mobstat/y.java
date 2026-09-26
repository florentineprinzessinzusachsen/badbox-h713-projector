package com.baidu.mobstat;

import android.content.Context;
import android.text.TextUtils;
import java.text.SimpleDateFormat;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class y {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static y f3538a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Context f3539b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private JSONObject f3540c = new JSONObject();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private long f3541d = 24;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private long f3542e = 0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private long f3543f = 0;
    private long g = 0;
    private long h = 5;
    private long i = 24;
    private long j = 15;
    private long k = 15;
    private long l = 30;
    private long m = 12;
    private long n = 1;
    private long o = 24;
    private String p = "";
    private String q = "";

    private y(Context context) throws Throwable {
        this.f3539b = context;
        m();
        j();
        k();
    }

    public static y a(Context context) {
        if (f3538a == null) {
            synchronized (y.class) {
                if (f3538a == null) {
                    f3538a = new y(context);
                }
            }
        }
        return f3538a;
    }

    private void m() throws Throwable {
        String strB = at.b("backups/system/.timestamp");
        try {
            if (TextUtils.isEmpty(strB)) {
                return;
            }
            this.f3540c = new JSONObject(strB);
        } catch (Exception unused) {
        }
    }

    public boolean b() {
        return this.f3543f != 0;
    }

    public long c() {
        return this.f3541d * 60 * 60 * 1000;
    }

    public long d() {
        return this.o * 60 * 60 * 1000;
    }

    public long e() {
        return this.h * 60 * 1000;
    }

    public long f() {
        return this.i * 60 * 60 * 1000;
    }

    public long g() {
        return this.j * 24 * 60 * 60 * 1000;
    }

    public long h() {
        return this.k * 24 * 60 * 60 * 1000;
    }

    public long i() {
        return this.m * 60 * 60 * 1000;
    }

    public void j() {
        try {
            String str = new String(ba.b(false, aw.a(), au.a(at.a(this.f3539b, ".config2").getBytes())));
            if (TextUtils.isEmpty(str)) {
                return;
            }
            JSONObject jSONObject = new JSONObject(str);
            try {
                this.f3542e = jSONObject.getLong("c");
            } catch (JSONException e2) {
                al.c().b(e2);
            }
            try {
                this.h = jSONObject.getLong("d");
            } catch (JSONException e3) {
                al.c().b(e3);
            }
            try {
                this.i = jSONObject.getLong("e");
            } catch (JSONException e4) {
                al.c().b(e4);
            }
            try {
                this.j = jSONObject.getLong("i");
            } catch (JSONException e5) {
                al.c().b(e5);
            }
            try {
                this.f3541d = jSONObject.getLong("f");
            } catch (JSONException e6) {
                al.c().b(e6);
            }
            try {
                this.o = jSONObject.getLong("s");
            } catch (JSONException e7) {
                al.c().b(e7);
            }
            try {
                this.k = jSONObject.getLong("pk");
            } catch (JSONException e8) {
                al.c().b(e8);
            }
            try {
                this.l = jSONObject.getLong("at");
            } catch (JSONException e9) {
                al.c().b(e9);
            }
            try {
                this.m = jSONObject.getLong("as");
            } catch (JSONException e10) {
                al.c().b(e10);
            }
            try {
                this.n = jSONObject.getLong("ac");
            } catch (JSONException e11) {
                al.c().b(e11);
            }
            try {
                this.f3543f = jSONObject.getLong("mc");
            } catch (JSONException e12) {
                al.c().b(e12);
            }
            try {
                this.g = jSONObject.getLong("lsc");
            } catch (JSONException e13) {
                al.c().b(e13);
            }
        } catch (Exception e14) {
            al.c().b(e14);
        }
    }

    public void k() {
        try {
            String str = new String(ba.b(false, aw.a(), au.a(at.a(this.f3539b, ".sign").getBytes())));
            if (TextUtils.isEmpty(str)) {
                return;
            }
            JSONObject jSONObject = new JSONObject(str);
            try {
                this.q = jSONObject.getString("sign");
            } catch (Exception e2) {
                al.c().b(e2);
            }
            try {
                this.p = jSONObject.getString("ver");
            } catch (Exception e3) {
                al.c().b(e3);
            }
        } catch (Exception e4) {
            al.c().b(e4);
        }
    }

    public boolean l() {
        long jCurrentTimeMillis = System.currentTimeMillis();
        long jA = a(g.LAST_SEND);
        long jD = d();
        al.c().a("canSend now=" + jCurrentTimeMillis + ";lastSendTime=" + jA + ";sendLogTimeInterval=" + jD);
        return jCurrentTimeMillis - jA > jD || !a(jA);
    }

    public void b(String str) {
        at.a(this.f3539b, ".sign", str, false);
        k();
    }

    public String c(String str) {
        return (TextUtils.isEmpty(this.p) || !this.p.equals(str) || TextUtils.isEmpty(this.q)) ? "" : this.q;
    }

    private long b(long j) {
        if (j - System.currentTimeMillis() > 0) {
            return 0L;
        }
        return j;
    }

    public boolean a() {
        return this.f3542e != 0;
    }

    public void a(String str) {
        at.a(this.f3539b, ".config2", str, false);
        j();
    }

    public long a(g gVar) {
        long j = gVar.j;
        try {
            String string = gVar.toString();
            if (this.f3540c.has(string)) {
                j = this.f3540c.getLong(string);
            }
        } catch (Exception e2) {
            al.c().a(e2);
        }
        return b(j);
    }

    public void a(g gVar, long j) {
        gVar.j = j;
        try {
            this.f3540c.put(gVar.toString(), j);
        } catch (Exception e2) {
            al.c().a(e2);
        }
        try {
            at.a("backups/system/.timestamp", this.f3540c.toString(), false);
        } catch (Exception e3) {
            al.c().a(e3);
        }
    }

    public boolean a(long j) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        return simpleDateFormat.format(Long.valueOf(j)).equals(simpleDateFormat.format(Long.valueOf(System.currentTimeMillis())));
    }
}
