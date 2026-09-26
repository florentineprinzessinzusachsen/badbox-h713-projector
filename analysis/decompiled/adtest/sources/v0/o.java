package v0;

import java.util.Calendar;
import java.util.GregorianCalendar;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class o implements s0.c0 {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f2471d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ Object f2472e;

    public /* synthetic */ o(int i4, Object obj) {
        this.f2471d = i4;
        this.f2472e = obj;
    }

    @Override // s0.c0
    public final s0.b0 a(s0.n nVar, z0.a aVar) {
        switch (this.f2471d) {
            case 0:
                if (aVar.f2778a == Number.class) {
                    return (p) this.f2472e;
                }
                return null;
            case 1:
                if (aVar.f2778a == Object.class) {
                    return new q(nVar, (s0.z) this.f2472e);
                }
                return null;
            default:
                Class cls = aVar.f2778a;
                if (cls == Calendar.class || cls == GregorianCalendar.class) {
                    return (p0) this.f2472e;
                }
                return null;
        }
    }

    public String toString() {
        switch (this.f2471d) {
            case 2:
                return "Factory[type=" + Calendar.class.getName() + "+" + GregorianCalendar.class.getName() + ",adapter=" + ((p0) this.f2472e) + "]";
            default:
                return super.toString();
        }
    }
}
