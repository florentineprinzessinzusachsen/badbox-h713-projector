package h3;

import java.io.EOFException;
import java.util.ArrayList;
import java.util.Arrays;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final q3.e f1098a;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f1100c;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f1104g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f1105h;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f1099b = Integer.MAX_VALUE;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f1101d = 4096;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public d[] f1102e = new d[8];

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f1103f = 7;

    public f(q3.e eVar) {
        this.f1098a = eVar;
    }

    public final void a(int i4) {
        int i5;
        if (i4 > 0) {
            int length = this.f1102e.length - 1;
            int i6 = 0;
            while (true) {
                i5 = this.f1103f;
                if (length < i5 || i4 <= 0) {
                    break;
                }
                d dVar = this.f1102e[length];
                j2.i.b(dVar);
                i4 -= dVar.f1087c;
                int i7 = this.f1105h;
                d dVar2 = this.f1102e[length];
                j2.i.b(dVar2);
                this.f1105h = i7 - dVar2.f1087c;
                this.f1104g--;
                i6++;
                length--;
            }
            d[] dVarArr = this.f1102e;
            int i8 = i5 + 1;
            System.arraycopy(dVarArr, i8, dVarArr, i8 + i6, this.f1104g);
            d[] dVarArr2 = this.f1102e;
            int i9 = this.f1103f + 1;
            Arrays.fill(dVarArr2, i9, i9 + i6, (Object) null);
            this.f1103f += i6;
        }
    }

    public final void b(d dVar) {
        int i4 = dVar.f1087c;
        int i5 = this.f1101d;
        if (i4 > i5) {
            d[] dVarArr = this.f1102e;
            v1.i.Z(dVarArr, null, 0, dVarArr.length);
            this.f1103f = this.f1102e.length - 1;
            this.f1104g = 0;
            this.f1105h = 0;
            return;
        }
        a((this.f1105h + i4) - i5);
        int i6 = this.f1104g + 1;
        d[] dVarArr2 = this.f1102e;
        if (i6 > dVarArr2.length) {
            d[] dVarArr3 = new d[dVarArr2.length * 2];
            System.arraycopy(dVarArr2, 0, dVarArr3, dVarArr2.length, dVarArr2.length);
            this.f1103f = this.f1102e.length - 1;
            this.f1102e = dVarArr3;
        }
        int i7 = this.f1103f;
        this.f1103f = i7 - 1;
        this.f1102e[i7] = dVar;
        this.f1104g++;
        this.f1105h += i4;
    }

    public final void c(q3.h hVar) throws EOFException {
        j2.i.e(hVar, "data");
        int[] iArr = b0.f1074a;
        int iA = hVar.a();
        long j4 = 0;
        long j5 = 0;
        for (int i4 = 0; i4 < iA; i4++) {
            byte bD = hVar.d(i4);
            byte[] bArr = b3.d.f343a;
            j5 += (long) b0.f1075b[bD & 255];
        }
        int i5 = (int) ((j5 + ((long) 7)) >> 3);
        int iA2 = hVar.a();
        q3.e eVar = this.f1098a;
        if (i5 >= iA2) {
            e(hVar.a(), 127, 0);
            eVar.V(hVar);
            return;
        }
        q3.e eVar2 = new q3.e();
        int[] iArr2 = b0.f1074a;
        int iA3 = hVar.a();
        int i6 = 0;
        for (int i7 = 0; i7 < iA3; i7++) {
            byte bD2 = hVar.d(i7);
            byte[] bArr2 = b3.d.f343a;
            int i8 = bD2 & 255;
            int i9 = b0.f1074a[i8];
            byte b4 = b0.f1075b[i8];
            j4 = (j4 << b4) | ((long) i9);
            i6 += b4;
            while (i6 >= 8) {
                i6 -= 8;
                eVar2.X((int) (j4 >> i6));
            }
        }
        if (i6 > 0) {
            eVar2.X((int) ((j4 << (8 - i6)) | (255 >>> i6)));
        }
        q3.h hVarQ = eVar2.q(eVar2.f1822e);
        e(hVarQ.a(), 127, 128);
        eVar.V(hVarQ);
    }

    /* JADX WARN: Code duplicated, block: B:22:0x0069  */
    public final void d(ArrayList arrayList) throws EOFException {
        int length;
        int length2;
        if (this.f1100c) {
            int i4 = this.f1099b;
            if (i4 < this.f1101d) {
                e(i4, 31, 32);
            }
            this.f1100c = false;
            this.f1099b = Integer.MAX_VALUE;
            e(this.f1101d, 31, 32);
        }
        int size = arrayList.size();
        for (int i5 = 0; i5 < size; i5++) {
            d dVar = (d) arrayList.get(i5);
            q3.h hVarI = dVar.f1085a.i();
            q3.h hVar = dVar.f1086b;
            Integer num = (Integer) g.f1107b.get(hVarI);
            if (num != null) {
                int iIntValue = num.intValue();
                length2 = iIntValue + 1;
                if (2 > length2 || length2 >= 8) {
                    length = length2;
                    length2 = -1;
                } else {
                    d[] dVarArr = g.f1106a;
                    if (j2.i.a(dVarArr[iIntValue].f1086b, hVar)) {
                        length = length2;
                    } else if (j2.i.a(dVarArr[length2].f1086b, hVar)) {
                        length2 = iIntValue + 2;
                        length = length2;
                    } else {
                        length = length2;
                        length2 = -1;
                    }
                }
            } else {
                length = -1;
                length2 = -1;
            }
            if (length2 == -1) {
                int length3 = this.f1102e.length;
                for (int i6 = this.f1103f + 1; i6 < length3; i6++) {
                    d dVar2 = this.f1102e[i6];
                    j2.i.b(dVar2);
                    if (j2.i.a(dVar2.f1085a, hVarI)) {
                        d dVar3 = this.f1102e[i6];
                        j2.i.b(dVar3);
                        if (j2.i.a(dVar3.f1086b, hVar)) {
                            length2 = g.f1106a.length + (i6 - this.f1103f);
                            break;
                        } else if (length == -1) {
                            length = (i6 - this.f1103f) + g.f1106a.length;
                        }
                    }
                }
            }
            if (length2 != -1) {
                e(length2, 127, 128);
            } else if (length == -1) {
                this.f1098a.X(64);
                c(hVarI);
                c(hVar);
                b(dVar);
            } else {
                q3.h hVar2 = d.f1079d;
                hVarI.getClass();
                j2.i.e(hVar2, "prefix");
                if (!hVarI.f(hVar2, hVar2.a()) || j2.i.a(d.f1084i, hVarI)) {
                    e(length, 63, 64);
                    c(hVar);
                    b(dVar);
                } else {
                    e(length, 15, 0);
                    c(hVar);
                }
            }
        }
    }

    public final void e(int i4, int i5, int i6) {
        q3.e eVar = this.f1098a;
        if (i4 < i5) {
            eVar.X(i4 | i6);
            return;
        }
        eVar.X(i6 | i5);
        int i7 = i4 - i5;
        while (i7 >= 128) {
            eVar.X(128 | (i7 & 127));
            i7 >>>= 7;
        }
        eVar.X(i7);
    }
}
