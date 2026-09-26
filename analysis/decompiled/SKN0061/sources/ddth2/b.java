package ddth2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class b {
    public final e a;

    /* JADX INFO: renamed from: a, reason: collision with other field name */
    public final v2 f2a;

    public b(e eVar) {
        this.a = eVar;
        this.f2a = new v2(eVar);
    }

    public n2 a() {
        try {
            return a(this.f2a.a(m1a()));
        } catch (IOException e) {
            return new n2(1000, "network request has failed: " + e.getMessage());
        } catch (Exception e2) {
            return new n2(1000, "config resolve failed: " + e2.getMessage());
        }
    }

    public final n2 a(String str) {
        String string;
        try {
            JSONObject jSONObject = new JSONObject(str);
            byte[] bArr = s.d;
            int i = jSONObject.has(a(bArr)) ? jSONObject.getInt(a(bArr)) : 1001;
            byte[] bArr2 = s.e;
            n2 n2Var = new n2(i, jSONObject.has(a(bArr2)) ? jSONObject.getString(a(bArr2)) : "");
            if (i == 0) {
                byte[] bArr3 = s.f;
                if (jSONObject.has(a(bArr3))) {
                    Object obj = jSONObject.get(a(bArr3));
                    if (this.a.m39c() && (obj instanceof String)) {
                        string = s2.a((String) obj, this.a);
                    } else if (obj instanceof JSONArray) {
                        string = obj.toString();
                    }
                    n2Var.a(m2a(string));
                }
            }
            return n2Var;
        } catch (Exception e) {
            return new n2(1000, "json parse failed: " + e.getMessage());
        }
    }

    /* JADX INFO: renamed from: a, reason: collision with other method in class */
    public final String m1a() {
        return t.a(this.a, c(), System.currentTimeMillis() / 1000);
    }

    public final String a(byte[] bArr) {
        return q.a(bArr);
    }

    /* JADX INFO: renamed from: a, reason: collision with other method in class */
    public final List<m2> m2a(String str) {
        ArrayList arrayList = new ArrayList();
        try {
            JSONArray jSONArray = new JSONArray(str);
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                m2 m2Var = new m2();
                byte[] bArr = s.a;
                m2Var.a(jSONObject.has(a(bArr)) ? jSONObject.getString(a(bArr)) : "");
                byte[] bArr2 = s.b;
                m2Var.a(jSONObject.has(a(bArr2)) ? jSONObject.getInt(a(bArr2)) : 0);
                byte[] bArr3 = s.c;
                m2Var.b(jSONObject.has(a(bArr3)) ? jSONObject.getInt(a(bArr3)) : 0);
                arrayList.add(m2Var);
            }
        } catch (Exception unused) {
        }
        return arrayList;
    }

    public final String b() {
        return r.a();
    }

    public final String c() {
        String strA;
        if (this.a.m41d() != null && !this.a.m41d().isEmpty()) {
            return this.a.m41d();
        }
        if (this.a.m35b() && (strA = t2.a()) != null && !strA.isEmpty()) {
            return strA;
        }
        String strB = b();
        if (this.a.m35b()) {
            t2.a(strB);
        }
        return strB;
    }
}
