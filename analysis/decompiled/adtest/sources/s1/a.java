package s1;

import a2.i;
import a3.a0;
import com.speed.ad.bean.PluginInfoEntity;
import d0.h;
import d0.l0;
import d1.g;
import i2.p;
import j1.e;
import java.io.File;
import r2.v;
import r2.x;
import t1.o;
import t1.u;
import u1.k;
import y1.c;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class a extends i implements p {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ int f2140h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f2141i;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ a(int i4, c cVar, int i5) {
        super(i4, cVar);
        this.f2140h = i5;
    }

    @Override // i2.p
    public final Object f(Object obj, Object obj2) {
        v vVar = (v) obj;
        c cVar = (c) obj2;
        switch (this.f2140h) {
            case 0:
                break;
        }
        return ((a) i(vVar, cVar)).l(k.f2301a);
    }

    @Override // a2.a
    public final c i(Object obj, c cVar) {
        switch (this.f2140h) {
            case 0:
                return new a(2, cVar, 0);
            default:
                return new a(2, cVar, 1);
        }
    }

    @Override // a2.a
    public final Object l(Object obj) {
        String md5;
        int i4 = this.f2140h;
        k kVar = k.f2301a;
        z1.a aVar = z1.a.f2781d;
        switch (i4) {
            case 0:
                int i5 = this.f2141i;
                if (i5 == 0) {
                    l0.M(obj);
                    this.f2141i = 1;
                    if (x.f(180000L, this) != aVar) {
                    }
                    return aVar;
                }
                if (i5 != 1) {
                    if (i5 != 2) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    l0.M(obj);
                    return kVar;
                }
                l0.M(obj);
                e eVar = e.f1256a;
                h hVar = new h(20);
                this.f2141i = 2;
                if (eVar.a(hVar, this) != aVar) {
                    return kVar;
                }
                return aVar;
            default:
                int i6 = this.f2141i;
                if (i6 == 0) {
                    l0.M(obj);
                    this.f2141i = 1;
                    if (x.f(5000L, this) == aVar) {
                        return aVar;
                    }
                } else {
                    if (i6 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    l0.M(obj);
                }
                l3.h.a0("loadLocalPlugin: 延迟5秒后开始执行");
                g.f520a.getClass();
                if (g.a().length() <= 0) {
                    l3.h.a0("本地不存在插件，无法加载本地插件，跳过（pluginPath=" + g.a() + ", pluginVersion=" + g.b() + "）");
                    o.f2158a.a("no_local_plugin", "本地不存在插件，无法加载本地插件，跳过（pluginPath=" + g.a() + ", pluginVersion=" + g.b() + "）");
                    return kVar;
                }
                l3.h.a0("本地存在插件版本：" + g.b() + "，开始加载本地插件：" + g.a());
                o oVar = o.f2158a;
                oVar.a("load_local_plugin", "本地存在插件版本：" + g.b() + "，开始加载本地插件：" + g.a());
                if (new File(g.a()).exists()) {
                    l3.h.a0("插件文件是存在的----开始安装加载插件");
                    oVar.a("plugin_file_exists", "插件文件是存在的----开始安装加载插件");
                    String strA = g.a();
                    int i7 = u.f2173b;
                    PluginInfoEntity pluginInfoEntity = (PluginInfoEntity) g.f524e.a(g.f521b[3]);
                    u.c(strA, false, (pluginInfoEntity == null || (md5 = pluginInfoEntity.getMd5()) == null) ? "" : md5, "", g.b(), null, 64);
                    return kVar;
                }
                l3.h.a0("插件文件不存在,清空缓存值");
                oVar.a("plugin_file_not_exists", "插件文件不存在,清空缓存值");
                a0 a0Var = g.f522c;
                n2.c[] cVarArr = g.f521b;
                a0Var.e(cVarArr[0], "");
                g.f523d.e(cVarArr[1], "0");
                return kVar;
        }
    }
}
