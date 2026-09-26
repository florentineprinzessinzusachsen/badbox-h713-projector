package h3;

import d0.l0;
import java.io.IOException;
import java.util.ArrayList;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class e {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final q3.o f1092c;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f1095f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f1096g;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f1090a = 4096;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ArrayList f1091b = new ArrayList();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public d[] f1093d = new d[8];

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f1094e = 7;

    public e(t tVar) {
        this.f1092c = l0.f(tVar);
    }

    public final int a(int i4) {
        int i5;
        int i6 = 0;
        if (i4 > 0) {
            int length = this.f1093d.length;
            while (true) {
                length--;
                i5 = this.f1094e;
                if (length < i5 || i4 <= 0) {
                    break;
                }
                d dVar = this.f1093d[length];
                j2.i.b(dVar);
                int i7 = dVar.f1087c;
                i4 -= i7;
                this.f1096g -= i7;
                this.f1095f--;
                i6++;
            }
            d[] dVarArr = this.f1093d;
            System.arraycopy(dVarArr, i5 + 1, dVarArr, i5 + 1 + i6, this.f1095f);
            this.f1094e += i6;
        }
        return i6;
    }

    public final q3.h b(int i4) throws IOException {
        if (i4 >= 0) {
            d[] dVarArr = g.f1106a;
            if (i4 <= dVarArr.length - 1) {
                return dVarArr[i4].f1085a;
            }
        }
        int length = this.f1094e + 1 + (i4 - g.f1106a.length);
        if (length >= 0) {
            d[] dVarArr2 = this.f1093d;
            if (length < dVarArr2.length) {
                d dVar = dVarArr2[length];
                j2.i.b(dVar);
                return dVar.f1085a;
            }
        }
        throw new IOException("Header index too large " + (i4 + 1));
    }

    public final void c(d dVar) {
        this.f1091b.add(dVar);
        int i4 = dVar.f1087c;
        int i5 = this.f1090a;
        if (i4 > i5) {
            d[] dVarArr = this.f1093d;
            v1.i.Z(dVarArr, null, 0, dVarArr.length);
            this.f1094e = this.f1093d.length - 1;
            this.f1095f = 0;
            this.f1096g = 0;
            return;
        }
        a((this.f1096g + i4) - i5);
        int i6 = this.f1095f + 1;
        d[] dVarArr2 = this.f1093d;
        if (i6 > dVarArr2.length) {
            d[] dVarArr3 = new d[dVarArr2.length * 2];
            System.arraycopy(dVarArr2, 0, dVarArr3, dVarArr2.length, dVarArr2.length);
            this.f1094e = this.f1093d.length - 1;
            this.f1093d = dVarArr3;
        }
        int i7 = this.f1094e;
        this.f1094e = i7 - 1;
        this.f1093d[i7] = dVar;
        this.f1095f++;
        this.f1096g += i4;
    }

    public final q3.h d() {
        q3.o oVar = this.f1092c;
        byte b4 = oVar.readByte();
        byte[] bArr = b3.d.f343a;
        int i4 = b4 & 255;
        int i5 = 0;
        boolean z3 = (b4 & 128) == 128;
        long jE = e(i4, 127);
        if (!z3) {
            return oVar.q(jE);
        }
        q3.e eVar = new q3.e();
        int[] iArr = b0.f1074a;
        j2.i.e(oVar, "source");
        a0 a0Var = b0.f1076c;
        a0 a0Var2 = a0Var;
        int i6 = 0;
        for (long j4 = 0; j4 < jE; j4++) {
            byte b5 = oVar.readByte();
            byte[] bArr2 = b3.d.f343a;
            i5 = (i5 << 8) | (b5 & 255);
            i6 += 8;
            while (i6 >= 8) {
                a0[] a0VarArr = a0Var2.f1062a;
                j2.i.b(a0VarArr);
                a0Var2 = a0VarArr[(i5 >>> (i6 - 8)) & 255];
                j2.i.b(a0Var2);
                if (a0Var2.f1062a == null) {
                    eVar.X(a0Var2.f1063b);
                    i6 -= a0Var2.f1064c;
                    a0Var2 = a0Var;
                } else {
                    i6 -= 8;
                }
            }
        }
        while (i6 > 0) {
            a0[] a0VarArr2 = a0Var2.f1062a;
            j2.i.b(a0VarArr2);
            a0 a0Var3 = a0VarArr2[(i5 << (8 - i6)) & 255];
            j2.i.b(a0Var3);
            int i7 = a0Var3.f1064c;
            if (a0Var3.f1062a != null || i7 > i6) {
                break;
            }
            eVar.X(a0Var3.f1063b);
            i6 -= i7;
            a0Var2 = a0Var;
        }
        return eVar.q(eVar.f1822e);
    }

    public final int e(int i4, int i5) {
        int i6 = i4 & i5;
        if (i6 < i5) {
            return i6;
        }
        int i7 = 0;
        while (true) {
            byte b4 = this.f1092c.readByte();
            byte[] bArr = b3.d.f343a;
            int i8 = b4 & 255;
            if ((b4 & 128) == 0) {
                return i5 + (i8 << i7);
            }
            i5 += (b4 & 127) << i7;
            i7 += 7;
        }
    }
}
