package q1;

import a2.i;
import a3.a0;
import a3.l;
import a3.x;
import a3.z;
import android.content.Context;
import com.speed.ad.NativePluginLoader;
import com.speed.service.DexLoaderService;
import d0.h;
import d0.l0;
import i2.p;
import java.io.File;
import r2.v;
import u1.k;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class b extends i implements p {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ int f1773h = 1;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ String f1774i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final /* synthetic */ Object f1775j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final /* synthetic */ Object f1776k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f1777l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public b(String str, DexLoaderService dexLoaderService, String str2, String str3, y1.c cVar) {
        super(2, cVar);
        this.f1774i = str;
        this.f1775j = dexLoaderService;
        this.f1776k = str2;
        this.f1777l = str3;
    }

    @Override // i2.p
    public final Object f(Object obj, Object obj2) {
        v vVar = (v) obj;
        y1.c cVar = (y1.c) obj2;
        switch (this.f1773h) {
            case 0:
                b bVar = (b) i(vVar, cVar);
                k kVar = k.f2301a;
                bVar.l(kVar);
                return kVar;
            default:
                return ((b) i(vVar, cVar)).l(k.f2301a);
        }
    }

    @Override // a2.a
    public final y1.c i(Object obj, y1.c cVar) {
        switch (this.f1773h) {
            case 0:
                return new b(this.f1774i, (File) this.f1775j, (Context) this.f1777l, (File) this.f1776k, cVar);
            default:
                return new b(this.f1774i, (DexLoaderService) this.f1775j, (String) this.f1776k, (String) this.f1777l, cVar);
        }
    }

    @Override // a2.a
    public final Object l(Object obj) {
        String strSubstring;
        File file;
        switch (this.f1773h) {
            case 0:
                l0.M(obj);
                c.f1780c = -1;
                x xVar = f.f1797a;
                String str = this.f1774i;
                File file2 = (File) this.f1775j;
                h hVar = new h(18);
                a aVar = new a((Context) this.f1777l, str, file2, (File) this.f1776k);
                File parentFile = file2.getParentFile();
                if (parentFile != null) {
                    parentFile.mkdirs();
                }
                z zVar = new z();
                zVar.d(str);
                a0 a0Var = new a0(zVar);
                x xVar2 = f.f1797a;
                xVar2.getClass();
                e3.p pVar = new e3.p(xVar2, a0Var);
                f.f1798b = pVar;
                f.f1799c.put(str, pVar);
                pVar.e(new l(str, aVar, file2, hVar));
                return k.f2301a;
            default:
                l0.M(obj);
                String str2 = this.f1774i;
                File file3 = new File(str2);
                String name = file3.getName();
                j2.i.d(name, "getName(...)");
                int iI0 = p2.i.I0(name, '.', 0, 6);
                if (iI0 == -1) {
                    strSubstring = "";
                } else {
                    strSubstring = name.substring(iI0 + 1, name.length());
                    j2.i.d(strSubstring, "substring(...)");
                }
                if (strSubstring.equalsIgnoreCase("apk")) {
                    file = file3;
                } else {
                    File parentFile2 = file3.getParentFile();
                    if (parentFile2 == null) {
                        parentFile2 = file3.getParentFile();
                    }
                    String name2 = file3.getName();
                    j2.i.d(name2, "getName(...)");
                    int iC0 = (6 & 2) != 0 ? p2.i.C0(name2) : 0;
                    j2.i.e(name2, "<this>");
                    j2.i.e(".", "string");
                    int iLastIndexOf = name2.lastIndexOf(".", iC0);
                    if (iLastIndexOf != -1) {
                        name2 = name2.substring(0, iLastIndexOf);
                        j2.i.d(name2, "substring(...)");
                    }
                    file = new File(parentFile2, name2.concat(".apk"));
                }
                if (!NativePluginLoader.f376a.prepareAndLoadPlugin((DexLoaderService) this.f1775j, (String) this.f1776k, str2, (String) this.f1777l)) {
                    throw new IllegalStateException("插件安装失败");
                }
                if (file.exists()) {
                    return file.getAbsolutePath();
                }
                if (file3.exists()) {
                    return file3.getAbsolutePath();
                }
                throw new IllegalArgumentException("插件准备后的文件不存在");
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public b(String str, File file, Context context, File file2, y1.c cVar) {
        super(2, cVar);
        this.f1774i = str;
        this.f1775j = file;
        this.f1777l = context;
        this.f1776k = file2;
    }
}
