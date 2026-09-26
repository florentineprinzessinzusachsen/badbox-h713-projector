package p1;

import a3.a0;
import a3.b0;
import a3.d0;
import a3.t;
import a3.u;
import a3.z;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import d0.l0;
import f3.i;
import h1.c0;
import java.io.IOException;
import java.net.NoRouteToHostException;
import java.net.UnknownHostException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import l3.h;
import n2.c;
import t1.o;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class a implements u {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final AtomicInteger f1736a = new AtomicInteger(0);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static volatile String f1737b;

    public static d0 b(i iVar, a0 a0Var) throws IOException {
        t tVar = (t) a0Var.f63c;
        IOException e4 = null;
        int i4 = 0;
        while (i4 <= 3) {
            try {
                d0 d0VarB = iVar.b(a0Var);
                if (!d0VarB.f118s) {
                    d0VarB.close();
                    throw new IOException("请求失败，状态码：" + d0VarB.f106g);
                }
                if (i4 > 0) {
                    h.a0("请求重试成功，第" + i4 + "次重试 [" + tVar + "]");
                }
                return d0VarB;
            } catch (IOException e5) {
                e4 = e5;
                i4++;
                if (i4 <= 3) {
                    h.a0("请求失败，准备第" + i4 + "次重试 [" + tVar + "] 错误: " + e4.getMessage());
                    try {
                        Thread.sleep(2000L);
                    } catch (InterruptedException unused) {
                        Thread.currentThread().interrupt();
                        throw new IOException("重试被中断", e4);
                    }
                } else {
                    h.a0("请求失败，已达最大重试次数3 [" + tVar + "]");
                }
            }
        }
        if (e4 != null) {
            throw e4;
        }
        throw new IOException("未知网络错误");
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // a3.u
    public final d0 a(i iVar) throws IOException {
        b0 b0Var;
        char c4;
        long j4;
        boolean z3;
        z zVarC;
        b0 b0Var2;
        long j5 = 0;
        Object systemService = b1.a.f336a.a().getSystemService("connectivity");
        j2.i.c(systemService, "null cannot be cast to non-null type android.net.ConnectivityManager");
        ConnectivityManager connectivityManager = (ConnectivityManager) systemService;
        NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
        if (networkCapabilities != null) {
            int i4 = 1;
            if (networkCapabilities.hasCapability(12)) {
                a0 a0Var = iVar.f901e;
                c0.f1036a.getClass();
                a0 a0Var2 = c0.f1045j;
                c[] cVarArr = c0.f1037b;
                char c5 = 3;
                if (((String) a0Var2.a(cVarArr[3])).length() > 0) {
                    f1737b = (String) a0Var2.a(cVarArr[3]);
                }
                String str = f1737b;
                boolean z4 = false;
                if (str != null) {
                    try {
                        String strB = a.a.b(str, a0Var);
                        z zVarC2 = a0Var.c();
                        zVarC2.d(strB);
                        if (j2.i.a(a0Var.f62b, "POST") && (b0Var = (b0) a0Var.f65e) != null) {
                            zVarC2.c(a0Var.f62b, b0Var);
                        }
                        a0 a0Var3 = new a0(zVarC2);
                        h.e0(a0Var3);
                        return b(iVar, a0Var3);
                    } catch (IOException e4) {
                        if (!(e4 instanceof UnknownHostException) && !(e4 instanceof NoRouteToHostException)) {
                            throw e4;
                        }
                        h.a0("缓存的域名 " + f1737b + " 已失效");
                        o.f2158a.a("cached_domain_failed", "缓存的域名 " + f1737b + " 已失效");
                        h.i0(false);
                    }
                }
                long jCurrentTimeMillis = System.currentTimeMillis();
                c0.f1036a.getClass();
                List listD = c0.d();
                int size = listD.size();
                IOException e5 = null;
                int i5 = 0;
                while (i5 < size) {
                    AtomicInteger atomicInteger = f1736a;
                    long j6 = j5;
                    int i6 = atomicInteger.get();
                    if (i6 >= listD.size()) {
                        break;
                    }
                    try {
                        if (i6 != i4) {
                            c4 = c5;
                            if (i6 != 2) {
                                z3 = z4;
                                j4 = jCurrentTimeMillis;
                            } else {
                                c0.f1036a.getClass();
                                long jA = c0.a();
                                j4 = jCurrentTimeMillis;
                                long jF = a.a.F("persist.autorun.rddomain_ms", c0.f1040e);
                                long j7 = j4 - jA;
                                a0 a0Var4 = c0.f1059x;
                                c[] cVarArr2 = c0.f1037b;
                                if (!((Boolean) a0Var4.a(cVarArr2[18])).booleanValue() && j7 >= jF / ((long) 2)) {
                                    a0Var4.e(cVarArr2[18], Boolean.TRUE);
                                    h.a0("备用域名冷静期过半，允许获取随机域名");
                                    o.f2158a.a("enable_fetch_randomdomain", "备用域名冷静期过半，允许获取随机域名");
                                }
                                if (jA > j6 && j7 < jF) {
                                    String strP = l0.P(jF);
                                    String strP2 = l0.P(jF - j7);
                                    h.a0("备用域名失败未满" + strP + ",剩余" + strP2 + "，无法切换到随机域名");
                                    o.f2158a.a("domain_switch_blocked", "备用域名失败未满" + strP + ",剩余" + strP2 + "，无法切换到随机域名");
                                    atomicInteger.set(0);
                                    throw new IOException("备用域名失败未满" + strP + ",剩余" + strP2 + "，无法切换到随机域名");
                                }
                            }
                            String str2 = (String) listD.get(i6);
                            String strB2 = a.a.b(str2, a0Var);
                            zVarC = a0Var.c();
                            zVarC.d(strB2);
                            if (j2.i.a(a0Var.f62b, "POST") && (b0Var2 = (b0) a0Var.f65e) != null) {
                                zVarC.c(a0Var.f62b, b0Var2);
                            }
                            a0 a0Var5 = new a0(zVarC);
                            h.e0(a0Var5);
                            d0 d0VarB = b(iVar, a0Var5);
                            f1737b = str2;
                            c0.f1036a.getClass();
                            a0 a0Var6 = c0.f1045j;
                            c[] cVarArr3 = c0.f1037b;
                            a0Var6.e(cVarArr3[c4], str2);
                            if (i6 != 0 && (c0.h() != j6 || c0.a() != j6)) {
                                c0.f1055t.e(cVarArr3[14], 0L);
                                c0.f1056u.e(cVarArr3[15], 0L);
                                h.a0("主域名恢复可用，已重置备用和随机域名的冷却时间");
                                o.f2158a.a("domain_recovered", "主域名恢复可用，已重置备用和随机域名的冷却时间");
                            }
                            return d0VarB;
                        }
                        c4 = c5;
                        j4 = jCurrentTimeMillis;
                        c0 c0Var = c0.f1036a;
                        c0Var.getClass();
                        long jH = c0.h();
                        if (jH > j6) {
                            long j8 = j4 - jH;
                            c0Var.getClass();
                            if (j8 < a.a.F("persist.autorun.budomain_ms", c0.f1039d)) {
                                c0Var.getClass();
                                String strP3 = l0.P(a.a.F("persist.autorun.budomain_ms", c0.f1039d));
                                c0Var.getClass();
                                String strP4 = l0.P(a.a.F("persist.autorun.budomain_ms", c0.f1039d) - j8);
                                h.a0("主域名失败未满" + strP3 + ",剩余" + strP4 + "，无法切换到备用域名");
                                o.f2158a.a("domain_switch_blocked", "主域名失败未满" + strP3 + ",剩余" + strP4 + "，无法切换到备用域名");
                                atomicInteger.set(0);
                                throw new IOException("主域名失败未满" + strP3 + ",剩余" + strP4 + "，无法切换到备用域名");
                            }
                        }
                        String strB3 = a.a.b(str2, a0Var);
                        zVarC = a0Var.c();
                        zVarC.d(strB3);
                        if (j2.i.a(a0Var.f62b, "POST")) {
                            zVarC.c(a0Var.f62b, b0Var2);
                        }
                        a0 a0Var7 = new a0(zVarC);
                        h.e0(a0Var7);
                        d0 d0VarB2 = b(iVar, a0Var7);
                        f1737b = str2;
                        c0.f1036a.getClass();
                        a0 a0Var8 = c0.f1045j;
                        c[] cVarArr4 = c0.f1037b;
                        a0Var8.e(cVarArr4[c4], str2);
                        if (i6 != 0) {
                        }
                        return d0VarB2;
                    } catch (IOException e6) {
                        e5 = e6;
                        if (!(e5 instanceof UnknownHostException) && !(e5 instanceof NoRouteToHostException)) {
                            throw e5;
                        }
                        if (i6 == 0) {
                            c0.f1036a.getClass();
                            if (c0.h() == j6) {
                                c0.f1055t.e(c0.f1037b[14], Long.valueOf(j4));
                            }
                        } else if (i6 == 1) {
                            c0.f1036a.getClass();
                            if (c0.a() == j6) {
                                c0.f1056u.e(c0.f1037b[15], Long.valueOf(j4));
                            }
                        }
                        if (i6 < listD.size() - 1) {
                            int i7 = i6 + 1;
                            if (f1736a.compareAndSet(i6, i7)) {
                                h.a0("域名 " + listD.get(i6) + "}无法被访问了，尝试切换到域名: " + listD.get(i7));
                                o.f2158a.a("try_switch_domain", "域名 " + listD.get(i6) + "}无法被访问了，尝试切换到域名: " + listD.get(i7));
                            }
                        }
                        i5++;
                        z4 = z3;
                        j5 = j6;
                        c5 = c4;
                        jCurrentTimeMillis = j4;
                        i4 = 1;
                    }
                    z3 = false;
                    String str3 = (String) listD.get(i6);
                }
                h.i0(i4);
                h.a0("所有域名都请求失败");
                o.f2158a.a("all_domain_failed", "所有域名都请求失败");
                if (e5 != null) {
                    throw e5;
                }
                throw new IOException("所有域名请求失败");
            }
        }
        h.a0("当前网络没有连接");
        o.f2158a.a("network_unavailable", "当前网络没有连接");
        throw new IOException("当前网络没有连接");
    }
}
