package c3;

import a3.a0;
import a3.c;
import a3.c0;
import a3.d0;
import a3.f0;
import a3.i0;
import a3.r;
import a3.t;
import a3.u;
import a3.y;
import a3.z;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.Build;
import android.provider.Settings;
import b3.d;
import d0.l0;
import f3.i;
import java.io.IOException;
import java.util.ArrayList;
import l3.h;
import p2.p;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class a implements u {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f371a;

    public /* synthetic */ a(int i4) {
        this.f371a = i4;
    }

    /* JADX WARN: Code duplicated, block: B:106:0x02a2  */
    /* JADX WARN: Code duplicated, block: B:108:0x02ac  */
    /* JADX WARN: Code duplicated, block: B:110:0x02b4  */
    /* JADX WARN: Code duplicated, block: B:111:0x02b9  */
    /* JADX WARN: Code duplicated, block: B:113:0x02c1  */
    /* JADX WARN: Code duplicated, block: B:115:0x02c9  */
    /* JADX WARN: Code duplicated, block: B:117:0x02d2  */
    /* JADX WARN: Code duplicated, block: B:118:0x02d7  */
    /* JADX WARN: Code duplicated, block: B:120:0x02df  */
    /* JADX WARN: Code duplicated, block: B:121:0x02e4  */
    /* JADX WARN: Code duplicated, block: B:123:0x02ec  */
    /* JADX WARN: Code duplicated, block: B:124:0x02f1  */
    /* JADX WARN: Code duplicated, block: B:126:0x02f9  */
    /* JADX WARN: Code duplicated, block: B:127:0x02fe  */
    /* JADX WARN: Code duplicated, block: B:129:0x0306  */
    /* JADX WARN: Code duplicated, block: B:130:0x030e  */
    /* JADX WARN: Code duplicated, block: B:132:0x0316  */
    /* JADX WARN: Code duplicated, block: B:133:0x031c  */
    /* JADX WARN: Code duplicated, block: B:135:0x0325  */
    /* JADX WARN: Code duplicated, block: B:136:0x032b  */
    /* JADX WARN: Code duplicated, block: B:138:0x0333  */
    /* JADX WARN: Code duplicated, block: B:139:0x0339  */
    /* JADX WARN: Code duplicated, block: B:141:0x0341  */
    /* JADX WARN: Code duplicated, block: B:239:0x01e7 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:240:0x01ed A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:67:0x01ca  */
    /* JADX WARN: Code duplicated, block: B:69:0x01d1  */
    /* JADX WARN: Code duplicated, block: B:72:0x01e0 A[LOOP:3: B:68:0x01cf->B:72:0x01e0, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:80:0x0214  */
    @Override // a3.u
    public final d0 a(i iVar) throws IOException {
        b bVar;
        d0 d0Var;
        int i4;
        int i5;
        int length;
        int length2;
        b bVar2;
        String string;
        r rVar;
        String string2;
        long longVersionCode;
        int i6 = 0;
        IOException iOException = null;
        String string3 = null;
        switch (this.f371a) {
            case 0:
                System.currentTimeMillis();
                a0 a0Var = iVar.f901e;
                b bVar3 = new b(a0Var, (d0) null);
                c cVar = (c) a0Var.f67g;
                if (cVar == null) {
                    int i7 = c.f74n;
                    r rVar2 = (r) a0Var.f64d;
                    j2.i.e(rVar2, "headers");
                    int size = rVar2.size();
                    int i8 = 0;
                    boolean z3 = false;
                    boolean z4 = false;
                    boolean z5 = false;
                    boolean z6 = false;
                    boolean z7 = false;
                    boolean z8 = false;
                    boolean z9 = false;
                    boolean z10 = false;
                    String str = null;
                    boolean z11 = true;
                    int iM = -1;
                    int iM2 = -1;
                    int iM3 = -1;
                    int iM4 = -1;
                    while (i8 < size) {
                        String strB = rVar2.b(i8);
                        String strD = rVar2.d(i8);
                        if (strB.equalsIgnoreCase("Cache-Control")) {
                            if (str == null) {
                                str = strD;
                            }
                            i5 = 0;
                            while (i5 < strD.length()) {
                                length = strD.length();
                                length2 = i5;
                                while (true) {
                                    if (length2 < length) {
                                        bVar2 = bVar3;
                                        if (p2.i.A0("=,;", strD.charAt(length2))) {
                                            length2++;
                                            bVar3 = bVar2;
                                        }
                                    } else {
                                        bVar2 = bVar3;
                                        length2 = strD.length();
                                    }
                                }
                                String strSubstring = strD.substring(i5, length2);
                                j2.i.d(strSubstring, "substring(...)");
                                string = p2.i.S0(strSubstring).toString();
                                if (length2 != strD.length() || strD.charAt(length2) == ',' || strD.charAt(length2) == ';') {
                                    rVar = rVar2;
                                    i5 = length2 + 1;
                                    string2 = null;
                                } else {
                                    int length3 = length2 + 1;
                                    byte[] bArr = d.f343a;
                                    int length4 = strD.length();
                                    while (true) {
                                        if (length3 < length4) {
                                            char cCharAt = strD.charAt(length3);
                                            int i9 = length4;
                                            if (cCharAt == ' ' || cCharAt == '\t') {
                                                length3++;
                                                length4 = i9;
                                            }
                                        } else {
                                            length3 = strD.length();
                                        }
                                    }
                                    if (length3 >= strD.length() || strD.charAt(length3) != '\"') {
                                        int length5 = strD.length();
                                        int length6 = length3;
                                        while (true) {
                                            if (length6 < length5) {
                                                int i10 = length5;
                                                rVar = rVar2;
                                                if (!p2.i.A0(",;", strD.charAt(length6))) {
                                                    length6++;
                                                    length5 = i10;
                                                    rVar2 = rVar;
                                                }
                                            } else {
                                                rVar = rVar2;
                                                length6 = strD.length();
                                            }
                                        }
                                        String strSubstring2 = strD.substring(length3, length6);
                                        j2.i.d(strSubstring2, "substring(...)");
                                        int i11 = length6;
                                        string2 = p2.i.S0(strSubstring2).toString();
                                        i5 = i11;
                                    } else {
                                        int i12 = length3 + 1;
                                        int iE0 = p2.i.E0(strD, '\"', i12, 4);
                                        string2 = strD.substring(i12, iE0);
                                        j2.i.d(string2, "substring(...)");
                                        i5 = iE0 + 1;
                                        rVar = rVar2;
                                    }
                                }
                                if ("no-cache".equalsIgnoreCase(string)) {
                                    z3 = true;
                                } else if ("no-store".equalsIgnoreCase(string)) {
                                    z4 = true;
                                } else if ("max-age".equalsIgnoreCase(string)) {
                                    iM = d.m(-1, string2);
                                } else if ("s-maxage".equalsIgnoreCase(string)) {
                                    iM2 = d.m(-1, string2);
                                } else if ("private".equalsIgnoreCase(string)) {
                                    z5 = true;
                                } else if ("public".equalsIgnoreCase(string)) {
                                    z6 = true;
                                } else if ("must-revalidate".equalsIgnoreCase(string)) {
                                    z7 = true;
                                } else if ("max-stale".equalsIgnoreCase(string)) {
                                    iM3 = d.m(Integer.MAX_VALUE, string2);
                                } else if ("min-fresh".equalsIgnoreCase(string)) {
                                    iM4 = d.m(-1, string2);
                                } else if ("only-if-cached".equalsIgnoreCase(string)) {
                                    z8 = true;
                                } else if ("no-transform".equalsIgnoreCase(string)) {
                                    z9 = true;
                                } else if ("immutable".equalsIgnoreCase(string)) {
                                    z10 = true;
                                }
                                bVar3 = bVar2;
                                rVar2 = rVar;
                            }
                            i8++;
                            bVar3 = bVar3;
                            rVar2 = rVar2;
                        } else {
                            if (strB.equalsIgnoreCase("Pragma")) {
                            }
                            i8++;
                            bVar3 = bVar3;
                            rVar2 = rVar2;
                        }
                        z11 = false;
                        i5 = 0;
                        while (i5 < strD.length()) {
                            length = strD.length();
                            length2 = i5;
                            while (true) {
                                if (length2 < length) {
                                    bVar2 = bVar3;
                                    if (p2.i.A0("=,;", strD.charAt(length2))) {
                                        length2++;
                                        bVar3 = bVar2;
                                    }
                                } else {
                                    bVar2 = bVar3;
                                    length2 = strD.length();
                                }
                            }
                            String strSubstring3 = strD.substring(i5, length2);
                            j2.i.d(strSubstring3, "substring(...)");
                            string = p2.i.S0(strSubstring3).toString();
                            if (length2 != strD.length()) {
                                rVar = rVar2;
                                i5 = length2 + 1;
                                string2 = null;
                            } else {
                                rVar = rVar2;
                                i5 = length2 + 1;
                                string2 = null;
                            }
                            if ("no-cache".equalsIgnoreCase(string)) {
                                z3 = true;
                            } else if ("no-store".equalsIgnoreCase(string)) {
                                z4 = true;
                            } else if ("max-age".equalsIgnoreCase(string)) {
                                iM = d.m(-1, string2);
                            } else if ("s-maxage".equalsIgnoreCase(string)) {
                                iM2 = d.m(-1, string2);
                            } else if ("private".equalsIgnoreCase(string)) {
                                z5 = true;
                            } else if ("public".equalsIgnoreCase(string)) {
                                z6 = true;
                            } else if ("must-revalidate".equalsIgnoreCase(string)) {
                                z7 = true;
                            } else if ("max-stale".equalsIgnoreCase(string)) {
                                iM3 = d.m(Integer.MAX_VALUE, string2);
                            } else if ("min-fresh".equalsIgnoreCase(string)) {
                                iM4 = d.m(-1, string2);
                            } else if ("only-if-cached".equalsIgnoreCase(string)) {
                                z8 = true;
                            } else if ("no-transform".equalsIgnoreCase(string)) {
                                z9 = true;
                            } else if ("immutable".equalsIgnoreCase(string)) {
                                z10 = true;
                            }
                            bVar3 = bVar2;
                            rVar2 = rVar;
                        }
                        i8++;
                        bVar3 = bVar3;
                        rVar2 = rVar2;
                    }
                    bVar = bVar3;
                    c cVar2 = new c(z3, z4, iM, iM2, z5, z6, z7, iM3, iM4, z8, z9, z10, !z11 ? null : str);
                    a0Var.f67g = cVar2;
                    cVar = cVar2;
                } else {
                    bVar = bVar3;
                }
                b bVar4 = cVar.f84j ? new b((a0) null, (d0) null) : bVar;
                a0 a0Var2 = (a0) bVar4.f372d;
                d0 d0Var2 = (d0) bVar4.f373e;
                if (a0Var2 == null && d0Var2 == null) {
                    return new d0(a0Var, y.f272g, "Unsatisfiable Request (only-if-cached)", 504, null, new r((String[]) new ArrayList(20).toArray(new String[0])), f0.f124d, null, null, null, null, -1L, System.currentTimeMillis(), null, i0.f162b);
                }
                if (a0Var2 == null) {
                    j2.i.b(d0Var2);
                    c0 c0VarB = d0Var2.b();
                    d0 d0VarL = l0.L(d0Var2);
                    c0.b(d0VarL, "cacheResponse");
                    c0VarB.f97j = d0VarL;
                    return c0VarB.a();
                }
                d0 d0VarB = iVar.b(a0Var2);
                if (d0Var2 == null) {
                    d0Var = null;
                } else {
                    if (d0VarB.f106g == 304) {
                        c0 c0VarB2 = d0Var2.b();
                        r rVar3 = d0Var2.f108i;
                        r rVar4 = d0VarB.f108i;
                        ArrayList arrayList = new ArrayList(20);
                        int i13 = 0;
                        for (int size2 = rVar3.size(); i13 < size2; size2 = i4) {
                            String strB2 = rVar3.b(i13);
                            String strD2 = rVar3.d(i13);
                            if ("Warning".equalsIgnoreCase(strB2)) {
                                i4 = size2;
                                if (p.z0(strD2, "1", false)) {
                                }
                                i13++;
                            } else {
                                i4 = size2;
                            }
                            if ("Content-Length".equalsIgnoreCase(strB2) || "Content-Encoding".equalsIgnoreCase(strB2) || "Content-Type".equalsIgnoreCase(strB2) || !h.P(strB2) || rVar4.a(strB2) == null) {
                                arrayList.add(strB2);
                                arrayList.add(p2.i.S0(strD2).toString());
                            }
                            i13++;
                        }
                        int size3 = rVar4.size();
                        for (int i14 = 0; i14 < size3; i14++) {
                            String strB3 = rVar4.b(i14);
                            if (!"Content-Length".equalsIgnoreCase(strB3) && !"Content-Encoding".equalsIgnoreCase(strB3) && !"Content-Type".equalsIgnoreCase(strB3) && h.P(strB3)) {
                                String strD3 = rVar4.d(i14);
                                arrayList.add(strB3);
                                arrayList.add(p2.i.S0(strD3).toString());
                            }
                        }
                        c0VarB2.f93f = new r((String[]) arrayList.toArray(new String[0])).c();
                        c0VarB2.f99l = d0VarB.f114o;
                        c0VarB2.f100m = d0VarB.f115p;
                        d0 d0VarL2 = l0.L(d0Var2);
                        c0.b(d0VarL2, "cacheResponse");
                        c0VarB2.f97j = d0VarL2;
                        d0 d0VarL3 = l0.L(d0VarB);
                        c0.b(d0VarL3, "networkResponse");
                        c0VarB2.f96i = d0VarL3;
                        c0VarB2.a();
                        d0VarB.f109j.close();
                        j2.i.b(null);
                        throw null;
                    }
                    d0Var = null;
                    d.b(d0Var2.f109j);
                }
                c0 c0VarB3 = d0VarB.b();
                d0 d0VarL4 = d0Var2 != null ? l0.L(d0Var2) : d0Var;
                c0.b(d0VarL4, "cacheResponse");
                c0VarB3.f97j = d0VarL4;
                d0 d0VarL5 = l0.L(d0VarB);
                c0.b(d0VarL5, "networkResponse");
                c0VarB3.f96i = d0VarL5;
                return c0VarB3.a();
            case 1:
                a0 a0Var3 = iVar.f901e;
                Context contextA = b1.a.f336a.a();
                String strF = h1.c0.f1036a.f();
                z zVarC = a0Var3.c();
                zVarC.b("subname", a.a.G("ro.appstore.project.name"));
                zVarC.b("X-Device-Id", strF);
                String strG = h.G();
                if (strG == null) {
                    strG = "";
                }
                zVarC.b("X-Device-Mac", strG);
                try {
                    string3 = Settings.Secure.getString(contextA.getContentResolver(), "android_id");
                    break;
                } catch (Exception unused) {
                }
                zVarC.b("X-Device-AndroidId", string3 != null ? string3 : "");
                zVarC.b("X-Device-Uuid", strF);
                zVarC.b("X-Channel-Id", h1.c0.c());
                try {
                    PackageInfo packageInfo = contextA.getPackageManager().getPackageInfo(contextA.getPackageName(), 0);
                    longVersionCode = Build.VERSION.SDK_INT >= 28 ? packageInfo.getLongVersionCode() : packageInfo.versionCode;
                    break;
                } catch (Exception unused2) {
                    longVersionCode = -1;
                }
                zVarC.b("X-App-VersionCode", String.valueOf(longVersionCode));
                String str2 = "unknown";
                try {
                    String str3 = contextA.getPackageManager().getPackageInfo(contextA.getPackageName(), 0).versionName;
                    if (str3 != null) {
                        str2 = str3;
                    }
                } catch (Exception unused3) {
                }
                zVarC.b("X-App-VersionName", str2);
                return iVar.b(new a0(zVarC));
            case 2:
                a0 a0Var4 = iVar.f901e;
                h.e0(a0Var4);
                return iVar.b(a0Var4);
            default:
                a0 a0Var5 = iVar.f901e;
                t tVar = (t) a0Var5.f63c;
                while (i6 <= 3) {
                    try {
                        d0 d0VarB2 = iVar.b(a0Var5);
                        if (!d0VarB2.f118s) {
                            d0VarB2.close();
                            throw new IOException("请求失败，状态码：" + d0VarB2.f106g);
                        }
                        if (i6 > 0) {
                            h.a0("请求重试成功，第" + i6 + "次重试 [" + tVar + "]");
                        }
                        return d0VarB2;
                    } catch (IOException e4) {
                        iOException = e4;
                        i6++;
                        if (i6 <= 3) {
                            h.a0("请求失败，准备第" + i6 + "次重试 [" + tVar + "] 错误: " + iOException.getMessage());
                            try {
                                Thread.sleep(2000L);
                            } catch (InterruptedException unused4) {
                                Thread.currentThread().interrupt();
                                throw new IOException("重试被中断", iOException);
                            }
                        } else {
                            h.a0("请求失败，已达最大重试次数3 [" + tVar + "] 错误: " + iOException.getMessage());
                        }
                    }
                }
                if (iOException != null) {
                    throw iOException;
                }
                throw new IOException("未知网络错误");
        }
    }
}
