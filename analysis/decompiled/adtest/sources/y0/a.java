package y0;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;
import s0.b0;
import s0.c0;
import s0.n;
import s0.r;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class a extends b0 {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final C0000a f2709b = new C0000a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final SimpleDateFormat f2710a;

    /* JADX INFO: renamed from: y0.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
    public class C0000a implements c0 {
        @Override // s0.c0
        public final b0 a(n nVar, z0.a aVar) {
            if (aVar.f2778a == Date.class) {
                return new a(0);
            }
            return null;
        }
    }

    public /* synthetic */ a(int i4) {
        this();
    }

    @Override // s0.b0
    public final Object b(a1.b bVar) throws IOException {
        Date date;
        if (bVar.f0() == 9) {
            bVar.b0();
            return null;
        }
        String strD0 = bVar.d0();
        synchronized (this) {
            TimeZone timeZone = this.f2710a.getTimeZone();
            try {
                try {
                    date = new Date(this.f2710a.parse(strD0).getTime());
                    this.f2710a.setTimeZone(timeZone);
                } catch (ParseException e4) {
                    throw new r("Failed parsing '" + strD0 + "' as SQL Date; at path " + bVar.K(true), e4);
                }
            } catch (Throwable th) {
                this.f2710a.setTimeZone(timeZone);
                throw th;
            }
        }
        return date;
    }

    @Override // s0.b0
    public final void c(a1.d dVar, Object obj) throws IOException {
        String str;
        Date date = (Date) obj;
        if (date == null) {
            dVar.S();
            return;
        }
        synchronized (this) {
            str = this.f2710a.format((java.util.Date) date);
        }
        dVar.a0(str);
    }

    private a() {
        this.f2710a = new SimpleDateFormat("MMM d, yyyy");
    }
}
