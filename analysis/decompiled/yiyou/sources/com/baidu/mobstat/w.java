package com.baidu.mobstat;

import android.content.Context;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
class w implements a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private z f3531a = z.f3544a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Object f3532b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Class<?> f3533c;

    public w(Object obj) {
        if (obj == null) {
            throw new IllegalArgumentException("proxy is null.");
        }
        if (!"com.baidu.bottom.remote.BPStretegyController2".equals(obj.getClass().getName())) {
            throw new IllegalArgumentException("class isn't com.baidu.bottom.remote.BPStretegyController2");
        }
        this.f3532b = obj;
        this.f3533c = obj.getClass();
    }

    @Override // com.baidu.mobstat.a
    public void a(Context context, JSONObject jSONObject) throws Throwable {
        try {
            a(new Object[]{context, jSONObject}, "startDataAnynalyze", new Class[]{Context.class, JSONObject.class});
        } catch (Exception e2) {
            al.c().b(e2);
            this.f3531a.a(context, jSONObject);
        }
    }

    @Override // com.baidu.mobstat.a
    public void b(Context context, String str) {
        try {
            a(new Object[]{context, str}, "saveRemoteSign", new Class[]{Context.class, String.class});
        } catch (Exception e2) {
            al.c().b(e2);
            this.f3531a.b(context, str);
        }
    }

    @Override // com.baidu.mobstat.a
    public void a(Context context, String str) {
        try {
            a(new Object[]{context, str}, "saveRemoteConfig2", new Class[]{Context.class, String.class});
        } catch (Exception e2) {
            al.c().b(e2);
            this.f3531a.a(context, str);
        }
    }

    @Override // com.baidu.mobstat.a
    public boolean b(Context context) {
        try {
            return ((Boolean) a(new Object[]{context}, "canStartService", new Class[]{Context.class})).booleanValue();
        } catch (Exception e2) {
            al.c().b(e2);
            return this.f3531a.b(context);
        }
    }

    @Override // com.baidu.mobstat.a
    public void a(Context context, long j) {
        try {
            a(new Object[]{context, Long.valueOf(j)}, "setLastUpdateTime", new Class[]{Context.class, Long.TYPE});
        } catch (Exception e2) {
            al.c().b(e2);
            this.f3531a.a(context, j);
        }
    }

    @Override // com.baidu.mobstat.a
    public boolean a(Context context) {
        try {
            return ((Boolean) a(new Object[]{context}, "needUpdate", new Class[]{Context.class})).booleanValue();
        } catch (Exception e2) {
            al.c().b(e2);
            return this.f3531a.a(context);
        }
    }

    private <T> T a(Object[] objArr, String str, Class<?>[] clsArr) {
        return (T) this.f3533c.getMethod(str, clsArr).invoke(this.f3532b, objArr);
    }
}
