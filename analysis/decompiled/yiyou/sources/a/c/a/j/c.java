package a.c.a.j;

import java.util.ArrayList;

/* JADX INFO: compiled from: Chain.java */
/* JADX INFO: loaded from: classes.dex */
class c {
    static void a(g gVar, a.c.a.e eVar, int i) {
        int i2;
        int i3;
        d[] dVarArr;
        if (i == 0) {
            int i4 = gVar.s0;
            dVarArr = gVar.v0;
            i3 = i4;
            i2 = 0;
        } else {
            i2 = 2;
            i3 = gVar.t0;
            dVarArr = gVar.u0;
        }
        for (int i5 = 0; i5 < i3; i5++) {
            d dVar = dVarArr[i5];
            dVar.a();
            if (!gVar.t(4)) {
                a(gVar, eVar, i, i2, dVar);
            } else if (!k.a(gVar, eVar, i, i2, dVar)) {
                a(gVar, eVar, i, i2, dVar);
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:155:0x02a8  */
    /* JADX WARN: Code duplicated, block: B:29:0x004a A[PHI: r8 r14
      0x004a: PHI (r8v4 boolean) = (r8v2 boolean), (r8v42 boolean) binds: [B:28:0x0048, B:17:0x0035] A[DONT_GENERATE, DONT_INLINE]
      0x004a: PHI (r14v4 boolean) = (r14v2 boolean), (r14v19 boolean) binds: [B:28:0x0048, B:17:0x0035] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:30:0x004c A[PHI: r8 r14
      0x004c: PHI (r8v39 boolean) = (r8v2 boolean), (r8v42 boolean) binds: [B:28:0x0048, B:17:0x0035] A[DONT_GENERATE, DONT_INLINE]
      0x004c: PHI (r14v16 boolean) = (r14v2 boolean), (r14v19 boolean) binds: [B:28:0x0048, B:17:0x0035] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:85:0x014c  */
    static void a(g gVar, a.c.a.e eVar, int i, int i2, d dVar) {
        boolean z;
        boolean z2;
        boolean z3;
        int i3;
        int i4;
        e eVar2;
        a.c.a.i iVar;
        a.c.a.i iVar2;
        e eVar3;
        a.c.a.i iVar3;
        a.c.a.i iVar4;
        float f2;
        int size;
        int i5;
        int i6;
        f fVar = dVar.f109a;
        f fVar2 = dVar.f111c;
        f fVar3 = dVar.f110b;
        f fVar4 = dVar.f112d;
        f fVar5 = dVar.f113e;
        float f3 = dVar.k;
        f fVar6 = dVar.f114f;
        f fVar7 = dVar.g;
        boolean z4 = gVar.C[i] == f.b.WRAP_CONTENT;
        if (i == 0) {
            z = fVar5.e0 == 0;
            z2 = fVar5.e0 == 1;
            if (fVar5.e0 == 2) {
                z3 = true;
            } else {
                z3 = false;
            }
        } else {
            z = fVar5.f0 == 0;
            z2 = fVar5.f0 == 1;
            if (fVar5.f0 == 2) {
                z3 = true;
            } else {
                z3 = false;
            }
        }
        boolean z5 = z;
        f fVar8 = fVar;
        boolean z6 = z2;
        boolean z7 = z3;
        boolean z8 = false;
        while (true) {
            f fVar9 = null;
            if (z8) {
                break;
            }
            e eVar4 = fVar8.A[i2];
            int i7 = (z4 || z7) ? 1 : 4;
            int iB = eVar4.b();
            e eVar5 = eVar4.f118d;
            if (eVar5 != null && fVar8 != fVar) {
                iB += eVar5.b();
            }
            int i8 = iB;
            if (!z7 || fVar8 == fVar || fVar8 == fVar3) {
                i5 = (z5 && z4) ? 4 : i7;
            } else {
                i5 = 6;
            }
            e eVar6 = eVar4.f118d;
            if (eVar6 != null) {
                if (fVar8 == fVar3) {
                    eVar.b(eVar4.i, eVar6.i, i8, 5);
                } else {
                    eVar.b(eVar4.i, eVar6.i, i8, 6);
                }
                eVar.a(eVar4.i, eVar4.f118d.i, i8, i5);
            } else {
                fVar5 = fVar5;
                z5 = z5;
            }
            if (z4) {
                if (fVar8.r() == 8 || fVar8.C[i] != f.b.MATCH_CONSTRAINT) {
                    i6 = 0;
                } else {
                    e[] eVarArr = fVar8.A;
                    i6 = 0;
                    eVar.b(eVarArr[i2 + 1].i, eVarArr[i2].i, 0, 5);
                }
                eVar.b(fVar8.A[i2].i, gVar.A[i2].i, i6, 6);
            }
            e eVar7 = fVar8.A[i2 + 1].f118d;
            if (eVar7 != null) {
                f fVar10 = eVar7.f116b;
                e[] eVarArr2 = fVar10.A;
                if (eVarArr2[i2].f118d != null && eVarArr2[i2].f118d.f116b == fVar8) {
                    fVar9 = fVar10;
                }
            }
            if (fVar9 != null) {
                fVar8 = fVar9;
                z8 = z8;
            } else {
                z8 = true;
            }
            f3 = f3;
            z5 = z5;
            fVar5 = fVar5;
        }
        f fVar11 = fVar5;
        float f4 = f3;
        boolean z9 = z5;
        if (fVar4 != null) {
            e[] eVarArr3 = fVar2.A;
            int i9 = i2 + 1;
            if (eVarArr3[i9].f118d != null) {
                e eVar8 = fVar4.A[i9];
                eVar.c(eVar8.i, eVarArr3[i9].f118d.i, -eVar8.b(), 5);
            }
        }
        if (z4) {
            int i10 = i2 + 1;
            a.c.a.i iVar5 = gVar.A[i10].i;
            e[] eVarArr4 = fVar2.A;
            eVar.b(iVar5, eVarArr4[i10].i, eVarArr4[i10].b(), 6);
        }
        ArrayList<f> arrayList = dVar.h;
        if (arrayList != null && (size = arrayList.size()) > 1) {
            float f5 = (!dVar.n || dVar.p) ? f4 : dVar.j;
            float f6 = 0.0f;
            f fVar12 = null;
            int i11 = 0;
            float f7 = 0.0f;
            while (i11 < size) {
                f fVar13 = arrayList.get(i11);
                float f8 = fVar13.g0[i];
                if (f8 < f6) {
                    if (dVar.p) {
                        e[] eVarArr5 = fVar13.A;
                        eVar.a(eVarArr5[i2 + 1].i, eVarArr5[i2].i, 0, 4);
                    } else {
                        f8 = 1.0f;
                        f6 = 0.0f;
                    }
                    arrayList = arrayList;
                    size = size;
                    i11++;
                    size = size;
                    arrayList = arrayList;
                    f6 = 0.0f;
                }
                if (f8 == f6) {
                    e[] eVarArr6 = fVar13.A;
                    eVar.a(eVarArr6[i2 + 1].i, eVarArr6[i2].i, 0, 6);
                    arrayList = arrayList;
                    size = size;
                } else {
                    if (fVar12 != null) {
                        e[] eVarArr7 = fVar12.A;
                        a.c.a.i iVar6 = eVarArr7[i2].i;
                        int i12 = i2 + 1;
                        a.c.a.i iVar7 = eVarArr7[i12].i;
                        e[] eVarArr8 = fVar13.A;
                        a.c.a.i iVar8 = eVarArr8[i2].i;
                        a.c.a.i iVar9 = eVarArr8[i12].i;
                        a.c.a.b bVarB = eVar.b();
                        bVarB.a(f7, f5, f8, iVar6, iVar7, iVar8, iVar9);
                        eVar.a(bVarB);
                    }
                    f7 = f8;
                    fVar12 = fVar13;
                }
                i11++;
                size = size;
                arrayList = arrayList;
                f6 = 0.0f;
            }
        }
        if (fVar3 != null && (fVar3 == fVar4 || z7)) {
            e[] eVarArr9 = fVar.A;
            e eVar9 = eVarArr9[i2];
            int i13 = i2 + 1;
            e eVar10 = fVar2.A[i13];
            a.c.a.i iVar10 = eVarArr9[i2].f118d != null ? eVarArr9[i2].f118d.i : null;
            e[] eVarArr10 = fVar2.A;
            a.c.a.i iVar11 = eVarArr10[i13].f118d != null ? eVarArr10[i13].f118d.i : null;
            if (fVar3 == fVar4) {
                e[] eVarArr11 = fVar3.A;
                eVar9 = eVarArr11[i2];
                eVar10 = eVarArr11[i13];
            }
            if (iVar10 != null && iVar11 != null) {
                if (i == 0) {
                    f2 = fVar11.V;
                } else {
                    f2 = fVar11.W;
                }
                eVar.a(eVar9.i, iVar10, eVar9.b(), f2, iVar11, eVar10.i, eVar10.b(), 5);
            }
        } else if (!z9 || fVar3 == null) {
            int i14 = 8;
            if (z6 && fVar3 != null) {
                int i15 = dVar.j;
                boolean z10 = i15 > 0 && dVar.i == i15;
                f fVar14 = fVar3;
                f fVar15 = fVar14;
                while (fVar14 != null) {
                    f fVar16 = fVar14.i0[i];
                    while (fVar16 != null && fVar16.r() == i14) {
                        fVar16 = fVar16.i0[i];
                    }
                    if (fVar14 == fVar3 || fVar14 == fVar4 || fVar16 == null) {
                        fVar15 = fVar15;
                        i4 = 8;
                    } else {
                        f fVar17 = fVar16 == fVar4 ? null : fVar16;
                        e eVar11 = fVar14.A[i2];
                        a.c.a.i iVar12 = eVar11.i;
                        e eVar12 = eVar11.f118d;
                        if (eVar12 != null) {
                            a.c.a.i iVar13 = eVar12.i;
                        }
                        int i16 = i2 + 1;
                        a.c.a.i iVar14 = fVar15.A[i16].i;
                        int iB2 = eVar11.b();
                        int iB3 = fVar14.A[i16].b();
                        if (fVar17 != null) {
                            eVar2 = fVar17.A[i2];
                            iVar = eVar2.i;
                            e eVar13 = eVar2.f118d;
                            iVar2 = eVar13 != null ? eVar13.i : null;
                        } else {
                            eVar2 = fVar14.A[i16].f118d;
                            iVar = eVar2 != null ? eVar2.i : null;
                            iVar2 = fVar14.A[i16].i;
                        }
                        if (eVar2 != null) {
                            iB3 += eVar2.b();
                        }
                        int i17 = iB3;
                        if (fVar15 != null) {
                            iB2 += fVar15.A[i16].b();
                        }
                        int i18 = iB2;
                        int i19 = z10 ? 6 : 4;
                        if (iVar12 == null || iVar14 == null || iVar == null || iVar2 == null) {
                            i4 = 8;
                        } else {
                            i4 = 8;
                            eVar.a(iVar12, iVar14, i18, 0.5f, iVar, iVar2, i17, i19);
                        }
                        fVar16 = fVar17;
                    }
                    if (fVar14.r() == i4) {
                        fVar14 = fVar15;
                    }
                    fVar15 = fVar14;
                    i14 = 8;
                    fVar14 = fVar16;
                }
                e eVar14 = fVar3.A[i2];
                e eVar15 = fVar.A[i2].f118d;
                int i20 = i2 + 1;
                e eVar16 = fVar4.A[i20];
                e eVar17 = fVar2.A[i20].f118d;
                if (eVar15 == null) {
                    i3 = 5;
                } else if (fVar3 != fVar4) {
                    i3 = 5;
                    eVar.a(eVar14.i, eVar15.i, eVar14.b(), 5);
                } else {
                    i3 = 5;
                    if (eVar17 != null) {
                        eVar.a(eVar14.i, eVar15.i, eVar14.b(), 0.5f, eVar16.i, eVar17.i, eVar16.b(), 5);
                    }
                }
                if (eVar17 != null && fVar3 != fVar4) {
                    eVar.a(eVar16.i, eVar17.i, -eVar16.b(), i3);
                }
            }
        } else {
            int i21 = dVar.j;
            boolean z11 = i21 > 0 && dVar.i == i21;
            f fVar18 = fVar3;
            f fVar19 = fVar18;
            while (fVar18 != null) {
                f fVar20 = fVar18.i0[i];
                while (fVar20 != null && fVar20.r() == 8) {
                    fVar20 = fVar20.i0[i];
                }
                if (fVar20 != null || fVar18 == fVar4) {
                    e eVar18 = fVar18.A[i2];
                    a.c.a.i iVar15 = eVar18.i;
                    e eVar19 = eVar18.f118d;
                    a.c.a.i iVar16 = eVar19 != null ? eVar19.i : null;
                    if (fVar19 != fVar18) {
                        iVar16 = fVar19.A[i2 + 1].i;
                    } else if (fVar18 == fVar3 && fVar19 == fVar18) {
                        e[] eVarArr12 = fVar.A;
                        iVar16 = eVarArr12[i2].f118d != null ? eVarArr12[i2].f118d.i : null;
                    }
                    int iB4 = eVar18.b();
                    int i22 = i2 + 1;
                    int iB5 = fVar18.A[i22].b();
                    if (fVar20 != null) {
                        eVar3 = fVar20.A[i2];
                        iVar3 = eVar3.i;
                        iVar4 = fVar18.A[i22].i;
                    } else {
                        eVar3 = fVar2.A[i22].f118d;
                        iVar3 = eVar3 != null ? eVar3.i : null;
                        iVar4 = fVar18.A[i22].i;
                    }
                    if (eVar3 != null) {
                        iB5 += eVar3.b();
                    }
                    if (fVar19 != null) {
                        iB4 += fVar19.A[i22].b();
                    }
                    if (iVar15 != null && iVar16 != null && iVar3 != null && iVar4 != null) {
                        if (fVar18 == fVar3) {
                            iB4 = fVar3.A[i2].b();
                        }
                        eVar.a(iVar15, iVar16, iB4, 0.5f, iVar3, iVar4, fVar18 == fVar4 ? fVar4.A[i22].b() : iB5, z11 ? 6 : 4);
                    }
                }
                if (fVar18.r() != 8) {
                    fVar19 = fVar18;
                }
                fVar18 = fVar20;
            }
        }
        if ((z9 || z6) && fVar3 != null) {
            e eVar20 = fVar3.A[i2];
            int i23 = i2 + 1;
            e eVar21 = fVar4.A[i23];
            e eVar22 = eVar20.f118d;
            a.c.a.i iVar17 = eVar22 != null ? eVar22.i : null;
            e eVar23 = eVar21.f118d;
            a.c.a.i iVar18 = eVar23 != null ? eVar23.i : null;
            if (fVar2 != fVar4) {
                e eVar24 = fVar2.A[i23].f118d;
                iVar18 = eVar24 != null ? eVar24.i : null;
            }
            a.c.a.i iVar19 = iVar18;
            if (fVar3 == fVar4) {
                e[] eVarArr13 = fVar3.A;
                e eVar25 = eVarArr13[i2];
                eVar21 = eVarArr13[i23];
                eVar20 = eVar25;
            }
            if (iVar17 == null || iVar19 == null) {
                return;
            }
            int iB6 = eVar20.b();
            if (fVar4 != null) {
                fVar2 = fVar4;
            }
            eVar.a(eVar20.i, iVar17, iB6, 0.5f, iVar19, eVar21.i, fVar2.A[i23].b(), 5);
        }
    }
}
