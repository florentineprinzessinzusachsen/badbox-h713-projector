package p2;

import a3.a0;
import com.speed.ad.bean.PluginInfoEntity;
import d0.l0;
import h1.c0;
import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import r2.d1;
import r2.t;
import r2.v0;
import t1.u;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class q implements i2.p {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f1762d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ Object f1763e;

    public /* synthetic */ q(int i4, Object obj) {
        this.f1762d = i4;
        this.f1763e = obj;
    }

    /* JADX WARN: Code duplicated, block: B:114:0x034a A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:115:0x034c A[LOOP:1: B:103:0x031a->B:115:0x034c, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:131:0x0384 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:132:0x0386 A[LOOP:3: B:121:0x0357->B:132:0x0386, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:144:0x02dd A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:145:0x033f A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:149:0x037a A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:150:0x02dd A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:18:0x0044 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:21:0x004b  */
    /* JADX WARN: Code duplicated, block: B:8:0x002f  */
    /* JADX WARN: Instruction removed from duplicated block: B:21:0x004b, please report this as an issue */
    @Override // i2.p
    public final Object f(Object obj, Object obj2) {
        Object next;
        String str;
        u1.f fVar;
        String str2;
        u1.f fVar2;
        Object next2;
        String str3;
        String str4;
        int i4 = this.f1762d;
        v0 v0Var = null;
        Object obj3 = this.f1763e;
        switch (i4) {
            case 0:
                List list = (List) obj3;
                CharSequence charSequence = (CharSequence) obj;
                int iIntValue = ((Integer) obj2).intValue();
                j2.i.e(charSequence, "$this$DelimitedRangesSequence");
                if (list.size() == 1) {
                    int size = list.size();
                    if (size == 0) {
                        throw new NoSuchElementException("List is empty.");
                    }
                    if (size != 1) {
                        throw new IllegalArgumentException("List has more than one element.");
                    }
                    String str5 = (String) list.get(0);
                    int iF0 = i.F0(charSequence, str5, iIntValue, 4);
                    fVar2 = iF0 < 0 ? null : new u1.f(Integer.valueOf(iF0), str5);
                } else {
                    int i5 = iIntValue >= 0 ? iIntValue : 0;
                    m2.c cVar = new m2.c(i5, charSequence.length(), 1);
                    boolean z3 = charSequence instanceof String;
                    int i6 = cVar.f1445f;
                    int i7 = cVar.f1444e;
                    if (z3) {
                        if ((i6 > 0 && i5 <= i7) || (i6 < 0 && i7 <= i5)) {
                            int i8 = i5;
                            while (true) {
                                Iterator it = list.iterator();
                                do {
                                    if (it.hasNext()) {
                                        next2 = it.next();
                                        str4 = (String) next2;
                                    } else {
                                        next2 = null;
                                    }
                                    str3 = (String) next2;
                                    if (str3 != null) {
                                        fVar = new u1.f(Integer.valueOf(i8), str3);
                                        fVar2 = fVar;
                                    } else if (i8 != i7) {
                                        i8 += i6;
                                    }
                                } while (!p.w0(str4, 0, (String) charSequence, i8, str4.length(), false));
                                str3 = (String) next2;
                                if (str3 != null) {
                                    fVar = new u1.f(Integer.valueOf(i8), str3);
                                    fVar2 = fVar;
                                } else if (i8 != i7) {
                                    i8 += i6;
                                }
                            }
                        }
                    } else {
                        if ((i6 > 0 && i5 <= i7) || (i6 < 0 && i7 <= i5)) {
                            int i9 = i5;
                            while (true) {
                                Iterator it2 = list.iterator();
                                do {
                                    if (it2.hasNext()) {
                                        next = it2.next();
                                        str2 = (String) next;
                                    } else {
                                        next = null;
                                    }
                                    str = (String) next;
                                    if (str != null) {
                                        fVar = new u1.f(Integer.valueOf(i9), str);
                                        fVar2 = fVar;
                                    } else if (i9 != i7) {
                                        i9 += i6;
                                    }
                                } while (!i.K0(str2, 0, charSequence, i9, str2.length(), false));
                                str = (String) next;
                                if (str != null) {
                                    fVar = new u1.f(Integer.valueOf(i9), str);
                                    fVar2 = fVar;
                                } else if (i9 != i7) {
                                    i9 += i6;
                                }
                            }
                        }
                    }
                }
                if (fVar2 != null) {
                    return new u1.f(fVar2.f2294d, Integer.valueOf(((String) fVar2.f2295e).length()));
                }
                return null;
            case 1:
                CharSequence charSequence2 = (CharSequence) obj;
                int iIntValue2 = ((Integer) obj2).intValue();
                j2.i.e(charSequence2, "$this$DelimitedRangesSequence");
                int iG0 = i.G0(charSequence2, (char[]) obj3, iIntValue2, false);
                if (iG0 < 0) {
                    return null;
                }
                return new u1.f(Integer.valueOf(iG0), 1);
            case 2:
                a3.o oVar = (a3.o) obj3;
                PluginInfoEntity pluginInfoEntity = (PluginInfoEntity) obj;
                Throwable th = (Throwable) obj2;
                int i10 = u.f2173b;
                if (th != null) {
                    a1.c.f("插件请求失败: ", th.getMessage());
                    t1.o.f2158a.a("plugin_request_failed", "插件请求失败: " + th.getMessage());
                    oVar.a();
                } else {
                    c0.f1036a.getClass();
                    c0.B.e(c0.f1037b[23], c0.b());
                    l3.h.a0("插件信息请求成功，已标记今日已请求");
                    t1.o oVar2 = t1.o.f2158a;
                    oVar2.a("plugin_request_success", "插件信息请求成功");
                    a1.c.f("获取到插件信息，插件下载地址：", pluginInfoEntity != null ? pluginInfoEntity.getSdk_addr() : null);
                    oVar2.a("plugin_data_received", "获取到插件信息，插件下载地址：" + (pluginInfoEntity != null ? pluginInfoEntity.getSdk_addr() : null));
                    if (pluginInfoEntity != null) {
                        pluginInfoEntity.checkPeriodicTaskInterval();
                    }
                    if (pluginInfoEntity != null) {
                        pluginInfoEntity.checkInterval();
                    }
                    String sdk_addr = pluginInfoEntity != null ? pluginInfoEntity.getSdk_addr() : null;
                    b1.a aVar = b1.a.f336a;
                    if (sdk_addr == null || sdk_addr.length() == 0) {
                        l3.h.a0("插件下载地址是空的，开始清理本地插件进程和缓存");
                        oVar2.a("plugin_url_empty", "插件下载地址是空的，开始清理本地插件进程和缓存");
                        d1.g.f520a.getClass();
                        String strA = d1.g.a();
                        if (strA.length() > 0 || new File(strA).exists()) {
                            a0 a0Var = d1.g.f522c;
                            n2.c[] cVarArr = d1.g.f521b;
                            a0Var.e(cVarArr[0], "");
                            d1.g.f523d.e(cVarArr[1], "0");
                            l0.n();
                            u.a(aVar.a());
                        }
                        oVar.a();
                    } else {
                        d1.g.f520a.getClass();
                        d1.g.f524e.e(d1.g.f521b[3], pluginInfoEntity);
                        l3.h.a0("本地插件版本：" + d1.g.b() + "，接口返回的最新插件版本：" + pluginInfoEntity.getSdk_version());
                        oVar2.a("plugin_version", "本地插件版本：" + d1.g.b() + "，接口返回的最新插件版本：" + pluginInfoEntity.getSdk_version());
                        String sdk_version = pluginInfoEntity.getSdk_version();
                        if (Integer.parseInt(sdk_version != null ? sdk_version : "0") > Integer.parseInt(d1.g.b())) {
                            l0.n();
                            String string = UUID.randomUUID().toString();
                            j2.i.d(string, "toString(...)");
                            String str6 = ".p_" + p.x0(string, "-", "") + ".jar";
                            String str7 = aVar.a().getFilesDir().getAbsolutePath() + "/plugin";
                            File file = new File(str7);
                            if (!file.exists()) {
                                file.mkdirs();
                            }
                            String str8 = str7 + "/" + str6;
                            l3.h.a0("准备处理插件载荷");
                            oVar2.a("plugin_payload", "处理插件载荷");
                            l3.h.a0("插件版本有更新，开始加载新插件");
                            oVar2.a("plugin_update", "开始加载新插件");
                            String md5 = pluginInfoEntity.getMd5();
                            u.c(str8, true, md5 == null ? "" : md5, pluginInfoEntity.getSdk_addr(), String.valueOf(pluginInfoEntity.getSdk_version()), new a3.o(8, oVar), 32);
                        } else {
                            l3.h.a0("本地运行的已经是最新的插件版本：" + d1.g.b());
                            oVar2.a("no_newplugin_version", "本地运行的已经是最新的插件");
                            oVar.a();
                        }
                    }
                }
                return u1.k.f2301a;
            default:
                int iIntValue3 = ((Integer) obj).intValue();
                y1.f fVar3 = (y1.f) obj2;
                y1.g key = fVar3.getKey();
                y1.f fVarK = ((v2.o) obj3).f2558h.k(key);
                if (key == t.f2027e) {
                    v0 v0Var2 = (v0) fVarK;
                    v0 parent = (v0) fVar3;
                    while (parent != null) {
                        if (parent != v0Var2 && (parent instanceof w2.q)) {
                            r2.l lVar = (r2.l) d1.f1972e.get((w2.q) parent);
                            parent = lVar != null ? lVar.getParent() : null;
                        } else {
                            v0Var = parent;
                            if (v0Var == v0Var2) {
                                throw new IllegalStateException(("Flow invariant is violated:\n\t\tEmission from another coroutine is detected.\n\t\tChild of " + v0Var + ", expected child of " + v0Var2 + ".\n\t\tFlowCollector is not thread-safe and concurrent emissions are prohibited.\n\t\tTo mitigate this restriction please use 'channelFlow' builder instead of 'flow'").toString());
                            }
                            if (v0Var2 != null) {
                                iIntValue3++;
                            }
                        }
                    }
                    if (v0Var == v0Var2) {
                        throw new IllegalStateException(("Flow invariant is violated:\n\t\tEmission from another coroutine is detected.\n\t\tChild of " + v0Var + ", expected child of " + v0Var2 + ".\n\t\tFlowCollector is not thread-safe and concurrent emissions are prohibited.\n\t\tTo mitigate this restriction please use 'channelFlow' builder instead of 'flow'").toString());
                    }
                    if (v0Var2 != null) {
                        iIntValue3++;
                    }
                } else if (fVar3 != fVarK) {
                    iIntValue3 = Integer.MIN_VALUE;
                } else {
                    iIntValue3++;
                }
                return Integer.valueOf(iIntValue3);
        }
    }
}
