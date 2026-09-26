package v0;

import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public class p0 extends s0.b0 {
    @Override // s0.b0
    public final Object b(a1.b bVar) throws IOException {
        if (bVar.f0() == 9) {
            bVar.b0();
            return null;
        }
        bVar.c();
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        int i9 = 0;
        while (true) {
            if (bVar.f0() == 4) {
                bVar.C();
                return new GregorianCalendar(i4, i5, i6, i7, i8, i9);
            }
            String strZ = bVar.Z();
            int iX = bVar.X();
            strZ.getClass();
            switch (strZ) {
                case "dayOfMonth":
                    i6 = iX;
                    break;
                case "minute":
                    i8 = iX;
                    break;
                case "second":
                    i9 = iX;
                    break;
                case "year":
                    i4 = iX;
                    break;
                case "month":
                    i5 = iX;
                    break;
                case "hourOfDay":
                    i7 = iX;
                    break;
            }
        }
    }

    @Override // s0.b0
    public final void c(a1.d dVar, Object obj) throws IOException {
        Calendar calendar = (Calendar) obj;
        if (calendar == null) {
            dVar.S();
            return;
        }
        dVar.k();
        dVar.J("year");
        dVar.Y(calendar.get(1));
        dVar.J("month");
        dVar.Y(calendar.get(2));
        dVar.J("dayOfMonth");
        dVar.Y(calendar.get(5));
        dVar.J("hourOfDay");
        dVar.Y(calendar.get(11));
        dVar.J("minute");
        dVar.Y(calendar.get(12));
        dVar.J("second");
        dVar.Y(calendar.get(13));
        dVar.C();
    }
}
