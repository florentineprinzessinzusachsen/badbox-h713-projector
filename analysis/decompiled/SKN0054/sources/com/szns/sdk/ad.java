package com.szns.sdk;

import android.content.Context;
import android.text.TextUtils;
import com.hotota.p.d.common.utils.LOG;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public final class ad {
    private final Context a;
    private final ai b;
    private final String c;
    private final String d;
    private String h;
    private String i;
    private Future j;
    private String k;
    private final Object e = new Object();
    private final List f = new ArrayList();
    private int g = 0;
    private String l = "moon2";
    private int m = 1;

    public ad(Context context, String str, String str2, b bVar) {
        this.a = context;
        this.b = bVar;
        this.c = str;
        this.d = str2;
    }

    private static String a(JSONObject jSONObject, String str) {
        JSONArray jSONArrayOptJSONArray;
        String strOptString = jSONObject.optString(str);
        return (TextUtils.isEmpty(strOptString) || (jSONArrayOptJSONArray = jSONObject.optJSONArray(str)) == null) ? strOptString : jSONArrayOptJSONArray.optString(new Random().nextInt(jSONArrayOptJSONArray.length()));
    }

    private JSONObject a(String str) {
        String string = this.a.getSharedPreferences("device_id.xml", 0).getString(str, null);
        if (string == null) {
            return null;
        }
        return new JSONObject(string);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, String str) {
        int i2;
        ScheduledFuture scheduledFutureA;
        int i3 = this.g + 1;
        this.g = i3;
        if (i < 7 && i3 <= 3 && (scheduledFutureA = n.a().a(new ae(this, i), 3L, TimeUnit.SECONDS)) != null) {
            a(scheduledFutureA);
            return;
        }
        this.g = 0;
        switch (i) {
            case 1:
                i2 = 2;
                break;
            case 2:
                a(3);
                return;
            case 3:
                i2 = 4;
                break;
            case LOG.I /* 4 */:
                i2 = 5;
                break;
            case LOG.W /* 5 */:
                i2 = 6;
                break;
            case LOG.E /* 6 */:
                a(7);
                return;
            default:
                return;
        }
        a(i2);
    }

    private void a(Future future) {
        if (future == null) {
            return;
        }
        synchronized (this.e) {
            Iterator it = this.f.iterator();
            while (it.hasNext()) {
                Future future2 = (Future) it.next();
                if (future2 == null || future2.isDone() || future2.isCancelled()) {
                    it.remove();
                }
            }
            this.f.add(future);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a(JSONObject jSONObject, Boolean bool) {
        return a(jSONObject, "domain_info", bool);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a(JSONObject jSONObject, String str, Boolean bool) {
        if (jSONObject == null) {
            return false;
        }
        String strOptString = jSONObject.optString("du");
        if (System.currentTimeMillis() > jSONObject.optLong("dct")) {
            return false;
        }
        if (bool.booleanValue()) {
            b(jSONObject, str);
        }
        if (strOptString.isEmpty()) {
            return false;
        }
        this.b.a(strOptString);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i) {
        if (i == 1) {
            try {
                if (a(a("domain_info"), Boolean.FALSE)) {
                    return;
                }
            } catch (JSONException unused) {
            }
        }
        if (TextUtils.isEmpty(this.h)) {
            this.h = ac.b();
        }
        HashMap map = new HashMap();
        map.put("operator", this.c);
        map.put("c", this.l);
        map.put("name", this.d);
        x.a(this.h, map, new af(this, i));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(JSONObject jSONObject, String str) {
        this.a.getSharedPreferences("device_id.xml", 0).edit().putString(str, jSONObject.toString()).apply();
    }

    private String c() throws Throwable {
        DatagramSocket datagramSocket;
        Throwable th;
        try {
            datagramSocket = new DatagramSocket();
            try {
                try {
                    datagramSocket.setSoTimeout(1000);
                    byte[] bytes = this.l.getBytes();
                    DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length);
                    DatagramPacket datagramPacket2 = new DatagramPacket(new byte[1024], 1024);
                    for (int i = 12; i <= 80; i++) {
                        for (int i2 = 1; i2 <= 100; i2++) {
                            if (Thread.currentThread().isInterrupted()) {
                                if (!datagramSocket.isClosed()) {
                                    datagramSocket.close();
                                }
                                return null;
                            }
                            datagramPacket.setAddress(InetAddress.getByName("43.153." + i + "." + i2));
                            datagramPacket.setPort(8080);
                            datagramSocket.send(datagramPacket);
                            datagramPacket2.setLength(1024);
                            try {
                                datagramSocket.receive(datagramPacket2);
                                String str = new String(datagramPacket2.getData(), 0, datagramPacket2.getLength());
                                if (str.startsWith("http")) {
                                    if (!datagramSocket.isClosed()) {
                                        datagramSocket.close();
                                    }
                                    return str;
                                }
                            } catch (Exception e) {
                                e.getMessage();
                            }
                        }
                    }
                    if (!datagramSocket.isClosed()) {
                        datagramSocket.close();
                    }
                    return null;
                } catch (Exception unused) {
                    if (datagramSocket != null && !datagramSocket.isClosed()) {
                        datagramSocket.close();
                    }
                    return null;
                }
            } catch (Throwable th2) {
                th = th2;
                if (datagramSocket != null && !datagramSocket.isClosed()) {
                    datagramSocket.close();
                }
                throw th;
            }
        } catch (Exception unused2) {
            datagramSocket = null;
        } catch (Throwable th3) {
            datagramSocket = null;
            th = th3;
        }
    }

    private void c(int i) {
        if (i == 3) {
            try {
                JSONObject jSONObjectA = a("oss_domain_info");
                if (jSONObjectA != null) {
                    String strOptString = jSONObjectA.optString("du");
                    if (System.currentTimeMillis() < jSONObjectA.optLong("dct") && !TextUtils.isEmpty(strOptString)) {
                        this.h = strOptString;
                        b(i);
                        return;
                    }
                }
            } catch (JSONException unused) {
            }
        }
        if (TextUtils.isEmpty(this.i)) {
            this.i = ac.c();
        }
        x.a(this.i, new HashMap(), new ag(this, i));
    }

    private void d() {
        synchronized (this.e) {
            for (Future future : this.f) {
                if (future != null && !future.isDone()) {
                    future.cancel(true);
                }
            }
            this.f.clear();
        }
        this.j = null;
    }

    private void d(int i) {
        if (i == 5) {
            try {
                if (a(a("ip_tcp_info"), "ip_tcp_info", Boolean.FALSE)) {
                    return;
                }
            } catch (JSONException unused) {
            }
        }
        if (TextUtils.isEmpty(this.k)) {
            this.k = ac.d();
        }
        HashMap map = new HashMap();
        map.put("operator", this.c);
        map.put("c", this.l);
        map.put("name", this.d);
        x.a(this.k, new JSONObject(map).toString(), new ah(this, i));
    }

    private void e(final int i) {
        if (i == 7) {
            try {
                JSONObject jSONObjectA = a("udp_domain_info");
                if (jSONObjectA != null) {
                    String strOptString = jSONObjectA.optString("dudp");
                    if (System.currentTimeMillis() < jSONObjectA.optLong("dct") && !TextUtils.isEmpty(strOptString)) {
                        this.h = strOptString;
                        b(i);
                        return;
                    }
                }
            } catch (JSONException unused) {
            }
        }
        Future future = this.j;
        if (future != null && !future.isDone()) {
            this.j.cancel(true);
        }
        ScheduledFuture scheduledFutureC = n.a().c(new Runnable() { // from class: com.szns.sdk.ad$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() throws Throwable {
                this.f$0.f(i);
            }
        });
        this.j = scheduledFutureC;
        if (scheduledFutureC == null) {
            a(i, "UDP scan scheduler rejected");
        } else {
            a(scheduledFutureC);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void f(int i) throws Throwable {
        try {
            String strC = c();
            if (TextUtils.isEmpty(strC)) {
                a(i, "UDP scan failed");
                return;
            }
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("dudp", strC);
            jSONObject.put("dct", System.currentTimeMillis() + 86400000);
            b(jSONObject, "udp_domain_info");
            this.h = strC;
            b(i);
        } catch (Exception e) {
            a(i, "UDP scan error: " + e.getMessage());
        }
    }

    public final void a() {
        d();
    }

    public final void a(int i) {
        String str;
        String str2;
        String str3;
        this.m = i;
        switch (i) {
            case 1:
                b(i);
                break;
            case 2:
                try {
                    JSONObject jSONObjectA = a("domain_info");
                    if (jSONObjectA != null) {
                        String strA = a(jSONObjectA, "db");
                        if (!TextUtils.isEmpty(strA) && ((str3 = this.h) == null || !str3.equals(strA))) {
                            this.h = strA;
                            b(i);
                        }
                    }
                    break;
                } catch (JSONException unused) {
                }
                c(3);
                break;
            case 3:
                c(i);
                break;
            case LOG.I /* 4 */:
                try {
                    JSONObject jSONObjectA2 = a("domain_info");
                    if (jSONObjectA2 != null) {
                        String strA2 = a(jSONObjectA2, "do");
                        if (!TextUtils.isEmpty(strA2) && ((str2 = this.i) == null || !str2.equals(strA2))) {
                            this.i = strA2;
                            c(i);
                        }
                    }
                    break;
                } catch (JSONException unused2) {
                }
                d(5);
                break;
            case LOG.W /* 5 */:
                d(i);
                break;
            case LOG.E /* 6 */:
                try {
                    JSONObject jSONObjectA3 = a("domain_info");
                    if (jSONObjectA3 != null) {
                        String strA3 = a(jSONObjectA3, "dti");
                        if (!TextUtils.isEmpty(strA3) && ((str = this.k) == null || !str.equals(strA3))) {
                            this.k = strA3;
                            d(i);
                        }
                    }
                    break;
                } catch (JSONException unused3) {
                }
                e(7);
                break;
            case 7:
                e(i);
                break;
        }
    }

    public final void b() {
        this.g = 0;
        d();
    }
}
