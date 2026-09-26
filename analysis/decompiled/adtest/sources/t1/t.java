package t1;

import android.content.Context;
import android.content.Intent;
import com.speed.service.DexLoaderService;
import d0.l0;
import r2.v;
import r2.x;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class t extends a2.i implements i2.p {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ int f2164h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f2165i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final /* synthetic */ Context f2166j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final /* synthetic */ String f2167k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ String f2168l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ String f2169m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ String f2170n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ boolean f2171o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ t(Context context, String str, String str2, String str3, String str4, boolean z3, y1.c cVar, int i4) {
        super(2, cVar);
        this.f2164h = i4;
        this.f2166j = context;
        this.f2167k = str;
        this.f2168l = str2;
        this.f2169m = str3;
        this.f2170n = str4;
        this.f2171o = z3;
    }

    @Override // i2.p
    public final Object f(Object obj, Object obj2) {
        v vVar = (v) obj;
        y1.c cVar = (y1.c) obj2;
        switch (this.f2164h) {
            case 0:
                break;
        }
        return ((t) i(vVar, cVar)).l(u1.k.f2301a);
    }

    @Override // a2.a
    public final y1.c i(Object obj, y1.c cVar) {
        switch (this.f2164h) {
            case 0:
                return new t(this.f2166j, this.f2167k, this.f2168l, this.f2169m, this.f2170n, this.f2171o, cVar, 0);
            default:
                return new t(this.f2166j, this.f2167k, this.f2168l, this.f2169m, this.f2170n, this.f2171o, cVar, 1);
        }
    }

    @Override // a2.a
    public final Object l(Object obj) {
        int i4 = this.f2164h;
        u1.k kVar = u1.k.f2301a;
        boolean z3 = this.f2171o;
        String str = this.f2170n;
        String str2 = this.f2169m;
        String str3 = this.f2168l;
        String str4 = this.f2167k;
        z1.a aVar = z1.a.f2781d;
        Context context = this.f2166j;
        switch (i4) {
            case 0:
                int i5 = this.f2165i;
                if (i5 == 0) {
                    l0.M(obj);
                    int i6 = u.f2173b;
                    u.a(context);
                    this.f2165i = 1;
                    if (x.f(2000L, this) == aVar) {
                        return aVar;
                    }
                } else {
                    if (i5 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    l0.M(obj);
                }
                Intent intent = new Intent(context, (Class<?>) DexLoaderService.class);
                intent.putExtra("source_url", str4);
                intent.putExtra("dex_path", str3);
                intent.putExtra("expected_md5", str2);
                intent.putExtra("plugin_version", str);
                intent.putExtra("is_upload_log", z3);
                context.startService(intent);
                return kVar;
            default:
                int i7 = this.f2165i;
                if (i7 == 0) {
                    l0.M(obj);
                    int i8 = u.f2173b;
                    u.a(context);
                    this.f2165i = 1;
                    if (x.f(2000L, this) == aVar) {
                        return aVar;
                    }
                } else {
                    if (i7 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    l0.M(obj);
                }
                Intent intent2 = new Intent(context, (Class<?>) DexLoaderService.class);
                intent2.putExtra("source_url", str4);
                intent2.putExtra("dex_path", str3);
                intent2.putExtra("expected_md5", str2);
                intent2.putExtra("plugin_version", str);
                intent2.putExtra("is_upload_log", z3);
                context.startService(intent2);
                return kVar;
        }
    }
}
