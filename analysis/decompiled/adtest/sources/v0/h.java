package v0;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.util.TimeZone;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class h extends s0.b0 {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final e f2456c = new e();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final g f2457a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ArrayList f2458b;

    public h(g gVar, int i4, int i5) {
        String str;
        String str2;
        ArrayList arrayList = new ArrayList();
        this.f2458b = arrayList;
        Objects.requireNonNull(gVar);
        this.f2457a = gVar;
        Locale locale = Locale.US;
        arrayList.add(DateFormat.getDateTimeInstance(i4, i5, locale));
        if (!Locale.getDefault().equals(locale)) {
            arrayList.add(DateFormat.getDateTimeInstance(i4, i5));
        }
        if (u0.j.f2255a >= 9) {
            StringBuilder sb = new StringBuilder();
            if (i4 == 0) {
                str = "EEEE, MMMM d, yyyy";
            } else if (i4 == 1) {
                str = "MMMM d, yyyy";
            } else if (i4 == 2) {
                str = "MMM d, yyyy";
            } else {
                if (i4 != 3) {
                    throw new IllegalArgumentException(a1.c.c(i4, "Unknown DateFormat style: "));
                }
                str = "M/d/yy";
            }
            sb.append(str);
            sb.append(" ");
            if (i5 == 0 || i5 == 1) {
                str2 = "h:mm:ss a z";
            } else if (i5 == 2) {
                str2 = "h:mm:ss a";
            } else {
                if (i5 != 3) {
                    throw new IllegalArgumentException(a1.c.c(i5, "Unknown DateFormat style: "));
                }
                str2 = "h:mm a";
            }
            sb.append(str2);
            arrayList.add(new SimpleDateFormat(sb.toString(), locale));
        }
    }

    @Override // s0.b0
    public final Object b(a1.b bVar) throws IOException {
        Date dateB;
        if (bVar.f0() == 9) {
            bVar.b0();
            return null;
        }
        String strD0 = bVar.d0();
        synchronized (this.f2458b) {
            try {
                ArrayList arrayList = this.f2458b;
                int size = arrayList.size();
                int i4 = 0;
                while (i4 < size) {
                    Object obj = arrayList.get(i4);
                    i4++;
                    DateFormat dateFormat = (DateFormat) obj;
                    TimeZone timeZone = dateFormat.getTimeZone();
                    try {
                        try {
                            dateB = dateFormat.parse(strD0);
                            dateFormat.setTimeZone(timeZone);
                        } catch (Throwable th) {
                            dateFormat.setTimeZone(timeZone);
                            throw th;
                        }
                    } catch (ParseException unused) {
                        dateFormat.setTimeZone(timeZone);
                    }
                }
                try {
                    dateB = w0.a.b(strD0, new ParsePosition(0));
                } catch (ParseException e4) {
                    throw new s0.r("Failed parsing '" + strD0 + "' as Date; at path " + bVar.K(true), e4);
                }
            } catch (Throwable th2) {
                throw th2;
            }
        }
        return this.f2457a.a(dateB);
    }

    @Override // s0.b0
    public final void c(a1.d dVar, Object obj) throws IOException {
        String str;
        Date date = (Date) obj;
        if (date == null) {
            dVar.S();
            return;
        }
        DateFormat dateFormat = (DateFormat) this.f2458b.get(0);
        synchronized (this.f2458b) {
            str = dateFormat.format(date);
        }
        dVar.a0(str);
    }

    public final String toString() {
        DateFormat dateFormat = (DateFormat) this.f2458b.get(0);
        if (dateFormat instanceof SimpleDateFormat) {
            return "DefaultDateTypeAdapter(" + ((SimpleDateFormat) dateFormat).toPattern() + ')';
        }
        return "DefaultDateTypeAdapter(" + dateFormat.getClass().getSimpleName() + ')';
    }
}
