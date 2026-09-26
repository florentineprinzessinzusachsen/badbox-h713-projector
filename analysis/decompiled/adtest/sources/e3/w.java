package e3;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import java.io.File;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class w {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f825a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Object f826b;

    public w(p.p pVar, int i4) {
        this.f826b = pVar;
        this.f825a = i4;
    }

    public static void a(String str) {
        if (str.equalsIgnoreCase(":memory:")) {
            return;
        }
        int length = str.length() - 1;
        int i4 = 0;
        boolean z3 = false;
        while (i4 <= length) {
            boolean z4 = j2.i.f(str.charAt(!z3 ? i4 : length), 32) <= 0;
            if (z3) {
                if (!z4) {
                    break;
                } else {
                    length--;
                }
            } else if (z4) {
                i4++;
            } else {
                z3 = true;
            }
        }
        if (str.subSequence(i4, length + 1).toString().length() == 0) {
            return;
        }
        Log.w("SupportSQLite", "deleting the database file: ".concat(str));
        try {
            SQLiteDatabase.deleteDatabase(new File(str));
        } catch (Exception e4) {
            Log.w("SupportSQLite", "delete failed: ", e4);
        }
    }

    public void b(int i4, q3.c cVar) {
        while (true) {
            int i5 = i4 >> 1;
            if (i5 == 0) {
                break;
            }
            q3.c cVar2 = ((q3.c[]) this.f826b)[i5];
            j2.i.b(cVar2);
            long j4 = cVar.f1818g - cVar2.f1818g;
            if (0 < j4 || 0 == j4) {
                break;
            }
            cVar2.f1817f = i4;
            ((q3.c[]) this.f826b)[i4] = cVar2;
            i4 = i5;
        }
        ((q3.c[]) this.f826b)[i4] = cVar;
        cVar.f1817f = i4;
    }

    public void c(y.c cVar, int i4, int i5) {
        ((p.p) this.f826b).e(new s.a(cVar), i4, i5);
    }

    public void d(q3.c cVar) {
        q3.c cVar2;
        int i4 = cVar.f1817f;
        byte b4 = -1;
        if (i4 == -1) {
            throw new IllegalArgumentException("Failed requirement.");
        }
        int i5 = this.f825a;
        q3.c cVar3 = ((q3.c[]) this.f826b)[i5];
        j2.i.b(cVar3);
        cVar.f1817f = -1;
        q3.c[] cVarArr = (q3.c[]) this.f826b;
        cVarArr[i5] = null;
        this.f825a = i5 - 1;
        if (cVar == cVar3) {
            return;
        }
        long j4 = cVar3.f1818g - cVar.f1818g;
        if (0 >= j4) {
            b4 = 0 == j4 ? (byte) 0 : (byte) 1;
        }
        if (b4 == 0) {
            cVarArr[i4] = cVar3;
            cVar3.f1817f = i4;
            return;
        }
        if (b4 >= 0) {
            b(i4, cVar3);
            return;
        }
        while (true) {
            int i6 = i4 << 1;
            int i7 = i6 + 1;
            int i8 = this.f825a;
            if (i7 > i8) {
                if (i6 > i8) {
                    break;
                }
                cVar2 = ((q3.c[]) this.f826b)[i6];
                j2.i.b(cVar2);
            } else {
                cVar2 = ((q3.c[]) this.f826b)[i6];
                j2.i.b(cVar2);
                q3.c cVar4 = ((q3.c[]) this.f826b)[i7];
                j2.i.b(cVar4);
                if (0 >= cVar4.f1818g - cVar2.f1818g) {
                    cVar2 = cVar4;
                }
            }
            long j5 = cVar2.f1818g - cVar3.f1818g;
            if (0 < j5 || 0 == j5) {
                break;
            }
            int i9 = cVar2.f1817f;
            cVar2.f1817f = i4;
            ((q3.c[]) this.f826b)[i4] = cVar2;
            i4 = i9;
        }
        ((q3.c[]) this.f826b)[i4] = cVar3;
        cVar3.f1817f = i4;
    }
}
