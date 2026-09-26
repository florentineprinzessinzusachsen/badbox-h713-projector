package ddth2;

import java.io.IOException;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class c {
    public final e a;

    /* JADX INFO: renamed from: a, reason: collision with other field name */
    public final v2 f3a;

    public c(e eVar) {
        this.a = eVar;
        this.f3a = new v2(eVar);
    }

    public final o2 a(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            byte[] bArr = j0.a;
            int i = jSONObject.has(a(bArr)) ? jSONObject.getInt(a(bArr)) : -1;
            byte[] bArr2 = j0.b;
            o2 o2Var = new o2(i, jSONObject.has(a(bArr2)) ? jSONObject.getString(a(bArr2)) : "");
            if (i == 0) {
                q2 q2VarM3a = null;
                if (this.a.m39c()) {
                    byte[] bArr3 = j0.c;
                    if (jSONObject.has(a(bArr3)) && (jSONObject.get(a(bArr3)) instanceof String)) {
                        q2VarM3a = m3a(jSONObject.getString(a(bArr3)));
                    }
                } else {
                    byte[] bArr4 = j0.c;
                    if (jSONObject.has(a(bArr4))) {
                        q2VarM3a = a(jSONObject.getJSONObject(a(bArr4)));
                    }
                }
                if (q2VarM3a != null) {
                    o2Var.a(q2VarM3a);
                }
            }
            return o2Var;
        } catch (Exception e) {
            return new o2(1000, "json parse failed: " + e.getMessage());
        }
    }

    public o2 a(String str, String str2, String str3) {
        try {
            String strM5a = m5a(str, str2, str3);
            String strM4a = m4a(str);
            return a((this.a.m28a() == null || this.a.m28a().isEmpty()) ? this.f3a.a(strM4a, strM5a) : this.f3a.a(strM4a, strM5a, this.a.m28a()));
        } catch (IOException e) {
            return new o2(1000, "network request failed: " + e.getMessage());
        } catch (Exception e2) {
            return new o2(1000, "response parse failed: " + e2.getMessage());
        }
    }

    /* JADX INFO: renamed from: a, reason: collision with other method in class */
    public final q2 m3a(String str) {
        try {
            return a(new JSONObject(s2.a(str, this.a)));
        } catch (Exception e) {
            System.err.println("config dump failed: " + e.getMessage());
            return null;
        }
    }

    public final q2 a(JSONObject jSONObject) {
        q2 q2Var = new q2();
        try {
            byte[] bArr = k0.a;
            if (jSONObject.has(a(bArr))) {
                JSONObject jSONObject2 = jSONObject.getJSONObject(a(bArr));
                r2 r2Var = new r2();
                byte[] bArr2 = k0.b;
                r2Var.c(jSONObject2.has(a(bArr2)) ? jSONObject2.getInt(a(bArr2)) : 3);
                byte[] bArr3 = k0.c;
                r2Var.d(jSONObject2.has(a(bArr3)) ? jSONObject2.getInt(a(bArr3)) : 5);
                byte[] bArr4 = k0.d;
                r2Var.a(jSONObject2.has(a(bArr4)) ? jSONObject2.getInt(a(bArr4)) : 30);
                byte[] bArr5 = k0.e;
                r2Var.a(jSONObject2.has(a(bArr5)) ? jSONObject2.getString(a(bArr5)) : "");
                byte[] bArr6 = k0.f;
                r2Var.b(jSONObject2.has(a(bArr6)) ? jSONObject2.getInt(a(bArr6)) : 0);
                q2Var.a(r2Var);
            }
            byte[] bArr7 = l0.a;
            if (jSONObject.has(a(bArr7))) {
                JSONObject jSONObject3 = jSONObject.getJSONObject(a(bArr7));
                p2 p2Var = new p2();
                byte[] bArr8 = k0.d;
                p2Var.a(jSONObject3.has(a(bArr8)) ? jSONObject3.getInt(a(bArr8)) : 30);
                byte[] bArr9 = l0.b;
                p2Var.b(jSONObject3.has(a(bArr9)) ? jSONObject3.getInt(a(bArr9)) : 60);
                q2Var.a(p2Var);
            }
            byte[] bArr10 = l0.c;
            q2Var.a(jSONObject.has(a(bArr10)) ? jSONObject.getInt(a(bArr10)) : 3600);
        } catch (JSONException unused) {
        }
        return q2Var;
    }

    public final String a() {
        try {
            return a(e0.a);
        } catch (Exception unused) {
            return "";
        }
    }

    /* JADX INFO: renamed from: a, reason: collision with other method in class */
    public final String m4a(String str) {
        return d0.a(str, System.currentTimeMillis() / 1000);
    }

    /* JADX INFO: renamed from: a, reason: collision with other method in class */
    public final String m5a(String str, String str2, String str3) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(a(f0.a), u2.a());
            jSONObject.put(a(f0.b), u2.d());
            jSONObject.put(a(f0.c), "");
            jSONObject.put(a(f0.d), u2.b());
            jSONObject.put(a(f0.e), u2.e());
            jSONObject.put(a(f0.f), "");
            jSONObject.put(a(f0.g), str2);
            jSONObject.put(a(f0.h), u2.f());
            jSONObject.put(a(g0.a), 3);
            jSONObject.put(a(g0.b), 0);
            jSONObject.put(a(g0.c), 1);
            jSONObject.put(a(i0.a), 19);
            jSONObject.put(a(i0.b), this.a.m44f());
            jSONObject.put(a(i0.c), "2.0.10");
            jSONObject.put(a(i0.d), "2001000");
            jSONObject.put(a(i0.e), "");
            jSONObject.put(a(), this.a.m34b());
            if (str3 != null && !str3.isEmpty()) {
                jSONObject.put(a(i0.f), str3);
            }
            jSONObject.put(a(h0.a), str);
            long jCurrentTimeMillis = System.currentTimeMillis() / 1000;
            jSONObject.put(a(h0.b), jCurrentTimeMillis);
            jSONObject.put(a(h0.c), s2.a(str, (int) jCurrentTimeMillis));
            jSONObject.put(a(h0.d), this.a.j());
            if (!this.a.m39c()) {
                return "login=" + jSONObject.toString();
            }
            return "login=" + w2.a(s2.b(jSONObject.toString(), this.a)) + "&e=1";
        } catch (JSONException unused) {
            return "";
        }
    }

    public final String a(byte[] bArr) {
        return q.a(bArr);
    }
}
