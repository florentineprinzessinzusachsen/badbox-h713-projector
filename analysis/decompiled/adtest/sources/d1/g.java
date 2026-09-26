package d1;

import a3.a0;
import j2.j;
import j2.o;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final g f520a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final /* synthetic */ n2.c[] f521b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final a0 f522c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final a0 f523d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final a0 f524e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final a0 f525f;

    static {
        j jVar = new j(g.class, "pluginPath", "getPluginPath()Ljava/lang/String;");
        o.f1277a.getClass();
        f521b = new n2.c[]{jVar, new j(g.class, "pluginVersion", "getPluginVersion()Ljava/lang/String;"), new j(g.class, "pluginLang", "getPluginLang()Ljava/lang/String;"), new j(g.class, "pluginInfo", "getPluginInfo()Lcom/speed/ad/bean/PluginInfoEntity;"), new j(g.class, "pluginReportedVersion", "getPluginReportedVersion()Ljava/lang/String;")};
        f520a = new g();
        b bVar = new b();
        b1.a aVar = b1.a.f336a;
        f522c = new a0(aVar.a(), "pluginLocation", "", new a(bVar.f2779b, 1));
        f523d = new a0(aVar.a(), "pluginVersion", "0", new a(new c().f2779b, 2));
        new a0(aVar.a(), "pluginLang", "en", new a(new d().f2779b, 3));
        f524e = new a0(aVar.a(), "pluginInfo", null, new a(new e().f2779b, 4));
        f525f = new a0(aVar.a(), "plugin_reported_version", "0", new a(new f().f2779b, 0));
    }

    public static String a() {
        return (String) f522c.a(f521b[0]);
    }

    public static String b() {
        return (String) f523d.a(f521b[1]);
    }
}
