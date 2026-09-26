package e0;

import android.content.Context;
import androidx.work.impl.background.systemalarm.RescheduleReceiver;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class o extends a2.i implements i2.p {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public /* synthetic */ boolean f666h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ Context f667i;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public o(Context context, y1.c cVar) {
        super(2, cVar);
        this.f667i = context;
    }

    @Override // i2.p
    public final Object f(Object obj, Object obj2) {
        Boolean bool = (Boolean) obj;
        bool.booleanValue();
        o oVar = (o) i(bool, (y1.c) obj2);
        u1.k kVar = u1.k.f2301a;
        oVar.l(kVar);
        return kVar;
    }

    @Override // a2.a
    public final y1.c i(Object obj, y1.c cVar) {
        o oVar = new o(this.f667i, cVar);
        oVar.f666h = ((Boolean) obj).booleanValue();
        return oVar;
    }

    @Override // a2.a
    public final Object l(Object obj) {
        d0.l0.M(obj);
        m0.h.a(this.f667i, RescheduleReceiver.class, this.f666h);
        return u1.k.f2301a;
    }
}
