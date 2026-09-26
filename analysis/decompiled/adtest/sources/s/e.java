package s;

import android.database.Cursor;
import j2.i;
import java.util.Arrays;
import l3.h;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class e extends g {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int[] f2074g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public long[] f2075h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public double[] f2076i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public String[] f2077j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public byte[][] f2078k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public Cursor f2079l;

    public static void l(Cursor cursor, int i4) {
        if (i4 < 0 || i4 >= cursor.getColumnCount()) {
            h.m0(25, "column index out of range");
            throw null;
        }
    }

    public final Cursor A() {
        Cursor cursor = this.f2079l;
        if (cursor != null) {
            return cursor;
        }
        h.m0(21, "no row");
        throw null;
    }

    @Override // w.c
    public final boolean F() {
        b();
        k();
        Cursor cursor = this.f2079l;
        if (cursor != null) {
            return cursor.moveToNext();
        }
        throw new IllegalStateException("Required value was null.");
    }

    @Override // w.c
    public final void a(int i4, long j4) {
        b();
        c(1, i4);
        this.f2074g[i4] = 1;
        this.f2075h[i4] = j4;
    }

    public final void c(int i4, int i5) {
        int i6 = i5 + 1;
        int[] iArr = this.f2074g;
        if (iArr.length < i6) {
            int[] iArrCopyOf = Arrays.copyOf(iArr, i6);
            i.d(iArrCopyOf, "copyOf(...)");
            this.f2074g = iArrCopyOf;
        }
        if (i4 == 1) {
            long[] jArr = this.f2075h;
            if (jArr.length < i6) {
                long[] jArrCopyOf = Arrays.copyOf(jArr, i6);
                i.d(jArrCopyOf, "copyOf(...)");
                this.f2075h = jArrCopyOf;
                return;
            }
            return;
        }
        if (i4 == 2) {
            double[] dArr = this.f2076i;
            if (dArr.length < i6) {
                double[] dArrCopyOf = Arrays.copyOf(dArr, i6);
                i.d(dArrCopyOf, "copyOf(...)");
                this.f2076i = dArrCopyOf;
                return;
            }
            return;
        }
        if (i4 == 3) {
            String[] strArr = this.f2077j;
            if (strArr.length < i6) {
                Object[] objArrCopyOf = Arrays.copyOf(strArr, i6);
                i.d(objArrCopyOf, "copyOf(...)");
                this.f2077j = (String[]) objArrCopyOf;
                return;
            }
            return;
        }
        if (i4 != 4) {
            return;
        }
        byte[][] bArr = this.f2078k;
        if (bArr.length < i6) {
            Object[] objArrCopyOf2 = Arrays.copyOf(bArr, i6);
            i.d(objArrCopyOf2, "copyOf(...)");
            this.f2078k = (byte[][]) objArrCopyOf2;
        }
    }

    @Override // java.lang.AutoCloseable
    public final void close() {
        if (!this.f2083f) {
            b();
            this.f2074g = new int[0];
            this.f2075h = new long[0];
            this.f2076i = new double[0];
            this.f2077j = new String[0];
            this.f2078k = new byte[0][];
            reset();
        }
        this.f2083f = true;
    }

    @Override // w.c
    public final void d(int i4, byte[] bArr) {
        b();
        c(4, i4);
        this.f2074g[i4] = 4;
        this.f2078k[i4] = bArr;
    }

    @Override // w.c
    public final void e(int i4) {
        b();
        c(5, i4);
        this.f2074g[i4] = 5;
    }

    @Override // w.c
    public final byte[] getBlob(int i4) {
        b();
        Cursor cursorA = A();
        l(cursorA, i4);
        byte[] blob = cursorA.getBlob(i4);
        i.d(blob, "getBlob(...)");
        return blob;
    }

    @Override // w.c
    public final int getColumnCount() {
        b();
        k();
        Cursor cursor = this.f2079l;
        if (cursor != null) {
            return cursor.getColumnCount();
        }
        return 0;
    }

    @Override // w.c
    public final String getColumnName(int i4) {
        b();
        k();
        Cursor cursor = this.f2079l;
        if (cursor == null) {
            throw new IllegalStateException("Required value was null.");
        }
        l(cursor, i4);
        String columnName = cursor.getColumnName(i4);
        i.d(columnName, "getColumnName(...)");
        return columnName;
    }

    @Override // w.c
    public final long getLong(int i4) {
        b();
        Cursor cursorA = A();
        l(cursorA, i4);
        return cursorA.getLong(i4);
    }

    @Override // w.c
    public final boolean isNull(int i4) {
        b();
        Cursor cursorA = A();
        l(cursorA, i4);
        return cursorA.isNull(i4);
    }

    public final void k() {
        if (this.f2079l == null) {
            this.f2079l = this.f2081d.r(new a3.h(9, this));
        }
    }

    @Override // w.c
    public final String n(int i4) {
        b();
        Cursor cursorA = A();
        l(cursorA, i4);
        String string = cursorA.getString(i4);
        i.d(string, "getString(...)");
        return string;
    }

    @Override // w.c
    public final void o(int i4, String str) {
        i.e(str, "value");
        b();
        c(3, i4);
        this.f2074g[i4] = 3;
        this.f2077j[i4] = str;
    }

    @Override // w.c
    public final void reset() {
        b();
        Cursor cursor = this.f2079l;
        if (cursor != null) {
            cursor.close();
        }
        this.f2079l = null;
    }
}
