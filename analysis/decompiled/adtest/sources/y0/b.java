package y0;

import java.io.IOException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import s0.b0;
import s0.c0;
import s0.n;
import s0.r;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class b extends b0 {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final a f2711b = new a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final SimpleDateFormat f2712a;

    /* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
    public class a implements c0 {
        @Override // s0.c0
        public final b0 a(n nVar, z0.a aVar) {
            if (aVar.f2778a == Time.class) {
                return new b(0);
            }
            return null;
        }
    }

    public /* synthetic */ b(int i4) {
        this();
    }

    @Override // s0.b0
    public final Object b(a1.b bVar) throws IOException {
        Time time;
        if (bVar.f0() == 9) {
            bVar.b0();
            return null;
        }
        String strD0 = bVar.d0();
        synchronized (this) {
            TimeZone timeZone = this.f2712a.getTimeZone();
            try {
                try {
                    time = new Time(this.f2712a.parse(strD0).getTime());
                    this.f2712a.setTimeZone(timeZone);
                } catch (ParseException e4) {
                    throw new r("Failed parsing '" + strD0 + "' as SQL Time; at path " + bVar.K(true), e4);
                }
            } catch (Throwable th) {
                this.f2712a.setTimeZone(timeZone);
                throw th;
            }
        }
        return time;
    }

    @Override // s0.b0
    public final void c(a1.d dVar, Object obj) throws IOException {
        String str;
        Time time = (Time) obj;
        if (time == null) {
            dVar.S();
            return;
        }
        synchronized (this) {
            str = this.f2712a.format((Date) time);
        }
        dVar.a0(str);
    }

    private b() {
        this.f2712a = new SimpleDateFormat("hh:mm:ss a");
    }
}
