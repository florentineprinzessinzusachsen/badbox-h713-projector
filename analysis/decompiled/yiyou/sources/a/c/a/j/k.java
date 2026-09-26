package a.c.a.j;

/* JADX INFO: compiled from: Optimizer.java */
/* JADX INFO: loaded from: classes.dex */
public class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static boolean[] f155a = new boolean[3];

    static void a(g gVar, a.c.a.e eVar, f fVar) {
        if (gVar.C[0] != f.b.WRAP_CONTENT && fVar.C[0] == f.b.MATCH_PARENT) {
            int i = fVar.s.f119e;
            int iS = gVar.s() - fVar.u.f119e;
            e eVar2 = fVar.s;
            eVar2.i = eVar.a(eVar2);
            e eVar3 = fVar.u;
            eVar3.i = eVar.a(eVar3);
            eVar.a(fVar.s.i, i);
            eVar.a(fVar.u.i, iS);
            fVar.f135a = 2;
            fVar.a(i, iS);
        }
        if (gVar.C[1] == f.b.WRAP_CONTENT || fVar.C[1] != f.b.MATCH_PARENT) {
            return;
        }
        int i2 = fVar.t.f119e;
        int i3 = gVar.i() - fVar.v.f119e;
        e eVar4 = fVar.t;
        eVar4.i = eVar.a(eVar4);
        e eVar5 = fVar.v;
        eVar5.i = eVar.a(eVar5);
        eVar.a(fVar.t.i, i2);
        eVar.a(fVar.v.i, i3);
        if (fVar.Q > 0 || fVar.r() == 8) {
            e eVar6 = fVar.w;
            eVar6.i = eVar.a(eVar6);
            eVar.a(fVar.w.i, fVar.Q + i2);
        }
        fVar.f136b = 2;
        fVar.e(i2, i3);
    }

    private static boolean a(f fVar, int i) {
        f.b[] bVarArr = fVar.C;
        if (bVarArr[i] != f.b.MATCH_CONSTRAINT) {
            return false;
        }
        if (fVar.G != 0.0f) {
            if (bVarArr[i != 0 ? (char) 0 : (char) 1] == f.b.MATCH_CONSTRAINT) {
            }
            return false;
        }
        if (i == 0) {
            if (fVar.f139e != 0 || fVar.h != 0 || fVar.i != 0) {
                return false;
            }
        } else if (fVar.f140f != 0 || fVar.k != 0 || fVar.l != 0) {
            return false;
        }
        return true;
    }

    static void a(int i, f fVar) {
        fVar.I();
        m mVarD = fVar.s.d();
        m mVarD2 = fVar.t.d();
        m mVarD3 = fVar.u.d();
        m mVarD4 = fVar.v.d();
        boolean z = (i & 8) == 8;
        boolean z2 = fVar.C[0] == f.b.MATCH_CONSTRAINT && a(fVar, 0);
        if (mVarD.h != 4 && mVarD3.h != 4) {
            if (fVar.C[0] == f.b.FIXED || (z2 && fVar.r() == 8)) {
                if (fVar.s.f118d == null && fVar.u.f118d == null) {
                    mVarD.b(1);
                    mVarD3.b(1);
                    if (z) {
                        mVarD3.a(mVarD, 1, fVar.m());
                    } else {
                        mVarD3.a(mVarD, fVar.s());
                    }
                } else if (fVar.s.f118d != null && fVar.u.f118d == null) {
                    mVarD.b(1);
                    mVarD3.b(1);
                    if (z) {
                        mVarD3.a(mVarD, 1, fVar.m());
                    } else {
                        mVarD3.a(mVarD, fVar.s());
                    }
                } else if (fVar.s.f118d == null && fVar.u.f118d != null) {
                    mVarD.b(1);
                    mVarD3.b(1);
                    mVarD.a(mVarD3, -fVar.s());
                    if (z) {
                        mVarD.a(mVarD3, -1, fVar.m());
                    } else {
                        mVarD.a(mVarD3, -fVar.s());
                    }
                } else if (fVar.s.f118d != null && fVar.u.f118d != null) {
                    mVarD.b(2);
                    mVarD3.b(2);
                    if (z) {
                        fVar.m().a(mVarD);
                        fVar.m().a(mVarD3);
                        mVarD.b(mVarD3, -1, fVar.m());
                        mVarD3.b(mVarD, 1, fVar.m());
                    } else {
                        mVarD.b(mVarD3, -fVar.s());
                        mVarD3.b(mVarD, fVar.s());
                    }
                }
            } else if (z2) {
                int iS = fVar.s();
                mVarD.b(1);
                mVarD3.b(1);
                if (fVar.s.f118d == null && fVar.u.f118d == null) {
                    if (z) {
                        mVarD3.a(mVarD, 1, fVar.m());
                    } else {
                        mVarD3.a(mVarD, iS);
                    }
                } else if (fVar.s.f118d == null || fVar.u.f118d != null) {
                    if (fVar.s.f118d != null || fVar.u.f118d == null) {
                        if (fVar.s.f118d != null && fVar.u.f118d != null) {
                            if (z) {
                                fVar.m().a(mVarD);
                                fVar.m().a(mVarD3);
                            }
                            if (fVar.G == 0.0f) {
                                mVarD.b(3);
                                mVarD3.b(3);
                                mVarD.b(mVarD3, 0.0f);
                                mVarD3.b(mVarD, 0.0f);
                            } else {
                                mVarD.b(2);
                                mVarD3.b(2);
                                mVarD.b(mVarD3, -iS);
                                mVarD3.b(mVarD, iS);
                                fVar.o(iS);
                            }
                        }
                    } else if (z) {
                        mVarD.a(mVarD3, -1, fVar.m());
                    } else {
                        mVarD.a(mVarD3, -iS);
                    }
                } else if (z) {
                    mVarD3.a(mVarD, 1, fVar.m());
                } else {
                    mVarD3.a(mVarD, iS);
                }
            }
        }
        boolean z3 = fVar.C[1] == f.b.MATCH_CONSTRAINT && a(fVar, 1);
        if (mVarD2.h == 4 || mVarD4.h == 4) {
            return;
        }
        if (fVar.C[1] != f.b.FIXED && (!z3 || fVar.r() != 8)) {
            if (z3) {
                int i2 = fVar.i();
                mVarD2.b(1);
                mVarD4.b(1);
                if (fVar.t.f118d == null && fVar.v.f118d == null) {
                    if (z) {
                        mVarD4.a(mVarD2, 1, fVar.l());
                        return;
                    } else {
                        mVarD4.a(mVarD2, i2);
                        return;
                    }
                }
                if (fVar.t.f118d != null && fVar.v.f118d == null) {
                    if (z) {
                        mVarD4.a(mVarD2, 1, fVar.l());
                        return;
                    } else {
                        mVarD4.a(mVarD2, i2);
                        return;
                    }
                }
                if (fVar.t.f118d == null && fVar.v.f118d != null) {
                    if (z) {
                        mVarD2.a(mVarD4, -1, fVar.l());
                        return;
                    } else {
                        mVarD2.a(mVarD4, -i2);
                        return;
                    }
                }
                if (fVar.t.f118d == null || fVar.v.f118d == null) {
                    return;
                }
                if (z) {
                    fVar.l().a(mVarD2);
                    fVar.m().a(mVarD4);
                }
                if (fVar.G == 0.0f) {
                    mVarD2.b(3);
                    mVarD4.b(3);
                    mVarD2.b(mVarD4, 0.0f);
                    mVarD4.b(mVarD2, 0.0f);
                    return;
                }
                mVarD2.b(2);
                mVarD4.b(2);
                mVarD2.b(mVarD4, -i2);
                mVarD4.b(mVarD2, i2);
                fVar.g(i2);
                if (fVar.Q > 0) {
                    fVar.w.d().a(1, mVarD2, fVar.Q);
                    return;
                }
                return;
            }
            return;
        }
        if (fVar.t.f118d == null && fVar.v.f118d == null) {
            mVarD2.b(1);
            mVarD4.b(1);
            if (z) {
                mVarD4.a(mVarD2, 1, fVar.l());
            } else {
                mVarD4.a(mVarD2, fVar.i());
            }
            e eVar = fVar.w;
            if (eVar.f118d != null) {
                eVar.d().b(1);
                mVarD2.a(1, fVar.w.d(), -fVar.Q);
                return;
            }
            return;
        }
        if (fVar.t.f118d != null && fVar.v.f118d == null) {
            mVarD2.b(1);
            mVarD4.b(1);
            if (z) {
                mVarD4.a(mVarD2, 1, fVar.l());
            } else {
                mVarD4.a(mVarD2, fVar.i());
            }
            if (fVar.Q > 0) {
                fVar.w.d().a(1, mVarD2, fVar.Q);
                return;
            }
            return;
        }
        if (fVar.t.f118d == null && fVar.v.f118d != null) {
            mVarD2.b(1);
            mVarD4.b(1);
            if (z) {
                mVarD2.a(mVarD4, -1, fVar.l());
            } else {
                mVarD2.a(mVarD4, -fVar.i());
            }
            if (fVar.Q > 0) {
                fVar.w.d().a(1, mVarD2, fVar.Q);
                return;
            }
            return;
        }
        if (fVar.t.f118d == null || fVar.v.f118d == null) {
            return;
        }
        mVarD2.b(2);
        mVarD4.b(2);
        if (z) {
            mVarD2.b(mVarD4, -1, fVar.l());
            mVarD4.b(mVarD2, 1, fVar.l());
            fVar.l().a(mVarD2);
            fVar.m().a(mVarD4);
        } else {
            mVarD2.b(mVarD4, -fVar.i());
            mVarD4.b(mVarD2, fVar.i());
        }
        if (fVar.Q > 0) {
            fVar.w.d().a(1, mVarD2, fVar.Q);
        }
    }

    /* JADX WARN: Code duplicated, block: B:14:0x0034 A[PHI: r11 r12
      0x0034: PHI (r11v14 boolean) = (r11v2 boolean), (r11v17 boolean) binds: [B:25:0x0048, B:13:0x0032] A[DONT_GENERATE, DONT_INLINE]
      0x0034: PHI (r12v8 boolean) = (r12v2 boolean), (r12v11 boolean) binds: [B:25:0x0048, B:13:0x0032] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:15:0x0036 A[PHI: r11 r12
      0x0036: PHI (r11v4 boolean) = (r11v2 boolean), (r11v17 boolean) binds: [B:25:0x0048, B:13:0x0032] A[DONT_GENERATE, DONT_INLINE]
      0x0036: PHI (r12v4 boolean) = (r12v2 boolean), (r12v11 boolean) binds: [B:25:0x0048, B:13:0x0032] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:193:0x0319 A[PHI: r3
      0x0319: PHI (r3v20 float) = (r3v16 float), (r3v14 float) binds: [B:201:0x0371, B:191:0x0316] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:76:0x0101  */
    static boolean a(g gVar, a.c.a.e eVar, int i, int i2, d dVar) {
        boolean z;
        boolean z2;
        boolean z3;
        m mVar;
        float fB;
        int i3;
        int i4;
        float f2;
        f fVar;
        boolean z4;
        int i5;
        f fVar2 = dVar.f109a;
        f fVar3 = dVar.f111c;
        f fVar4 = dVar.f110b;
        f fVar5 = dVar.f112d;
        f fVar6 = dVar.f113e;
        float f3 = dVar.k;
        f fVar7 = dVar.f114f;
        f fVar8 = dVar.g;
        f.b bVar = gVar.C[i];
        f.b bVar2 = f.b.WRAP_CONTENT;
        if (i == 0) {
            z = fVar6.e0 == 0;
            z2 = fVar6.e0 == 1;
            if (fVar6.e0 == 2) {
                z3 = true;
            } else {
                z3 = false;
            }
        } else {
            z = fVar6.f0 == 0;
            z2 = fVar6.f0 == 1;
            if (fVar6.f0 == 2) {
                z3 = true;
            } else {
                z3 = false;
            }
        }
        f fVar9 = fVar2;
        int i6 = 0;
        boolean z5 = false;
        int i7 = 0;
        float fB2 = 0.0f;
        float fB3 = 0.0f;
        while (!z5) {
            if (fVar9.r() != 8) {
                i7++;
                if (i == 0) {
                    i5 = fVar9.s();
                } else {
                    i5 = fVar9.i();
                }
                fB2 += i5;
                if (fVar9 != fVar4) {
                    fB2 += fVar9.A[i2].b();
                }
                if (fVar9 != fVar5) {
                    fB2 += fVar9.A[i2 + 1].b();
                }
                fB3 = fB3 + fVar9.A[i2].b() + fVar9.A[i2 + 1].b();
            }
            e eVar2 = fVar9.A[i2];
            if (fVar9.r() != 8 && fVar9.C[i] == f.b.MATCH_CONSTRAINT) {
                i6++;
                if (i == 0) {
                    if (fVar9.f139e != 0) {
                        return false;
                    }
                    z4 = false;
                    if (fVar9.h != 0 || fVar9.i != 0) {
                        return false;
                    }
                } else {
                    z4 = false;
                    if (fVar9.f140f != 0) {
                        return false;
                    }
                    if (fVar9.k != 0 || fVar9.l != 0) {
                    }
                    return z4;
                }
                if (fVar9.G != 0.0f) {
                    return z4;
                }
            }
            e eVar3 = fVar9.A[i2 + 1].f118d;
            if (eVar3 != null) {
                f fVar10 = eVar3.f116b;
                e[] eVarArr = fVar10.A;
                if (eVarArr[i2].f118d == null || eVarArr[i2].f118d.f116b != fVar9) {
                    fVar = null;
                } else {
                    fVar = fVar10;
                }
            } else {
                fVar = null;
            }
            if (fVar != null) {
                fVar9 = fVar;
            } else {
                z5 = true;
            }
        }
        m mVarD = fVar2.A[i2].d();
        int i8 = i2 + 1;
        m mVarD2 = fVar3.A[i8].d();
        m mVar2 = mVarD.f157d;
        if (mVar2 == null || (mVar = mVarD2.f157d) == null || mVar2.f162b != 1 || mVar.f162b != 1) {
            return false;
        }
        if (i6 > 0 && i6 != i7) {
            return false;
        }
        if (z3 || z || z2) {
            fB = fVar4 != null ? fVar4.A[i2].b() : 0.0f;
            if (fVar5 != null) {
                fB += fVar5.A[i8].b();
            }
        } else {
            fB = 0.0f;
        }
        float f4 = mVarD.f157d.g;
        float f5 = mVarD2.f157d.g;
        float f6 = (f4 < f5 ? f5 - f4 : f4 - f5) - fB2;
        if (i6 > 0 && i6 == i7) {
            if (fVar9.k() != null && fVar9.k().C[i] == f.b.WRAP_CONTENT) {
                return false;
            }
            float f7 = (f6 + fB2) - fB3;
            float fB4 = f4;
            f fVar11 = fVar2;
            while (fVar11 != null) {
                a.c.a.f fVar12 = a.c.a.e.q;
                if (fVar12 != null) {
                    fVar12.z--;
                    fVar12.r++;
                    fVar12.x++;
                }
                f fVar13 = fVar11.i0[i];
                if (fVar13 != null || fVar11 == fVar3) {
                    float f8 = f7 / i6;
                    if (f3 > 0.0f) {
                        float[] fArr = fVar11.g0;
                        if (fArr[i] == -1.0f) {
                            f2 = 0.0f;
                        } else {
                            f8 = (fArr[i] * f7) / f3;
                            f2 = f8;
                        }
                    } else {
                        f2 = f8;
                    }
                    if (fVar11.r() == 8) {
                        f2 = 0.0f;
                    }
                    float fB5 = fB4 + fVar11.A[i2].b();
                    fVar11.A[i2].d().a(mVarD.f159f, fB5);
                    float f9 = fB5 + f2;
                    fVar11.A[i8].d().a(mVarD.f159f, f9);
                    fVar11.A[i2].d().a(eVar);
                    fVar11.A[i8].d().a(eVar);
                    fB4 = f9 + fVar11.A[i8].b();
                }
                fVar11 = fVar13;
            }
            return true;
        }
        if (f6 < 0.0f) {
            z3 = true;
            z = false;
            z2 = false;
        }
        if (z3) {
            f fVar14 = fVar2;
            float fB6 = f4 + ((f6 - fB) * fVar14.b(i));
            while (fVar14 != null) {
                a.c.a.f fVar15 = a.c.a.e.q;
                if (fVar15 != null) {
                    fVar15.z--;
                    fVar15.r++;
                    fVar15.x++;
                }
                f fVar16 = fVar14.i0[i];
                if (fVar16 != null || fVar14 == fVar3) {
                    if (i == 0) {
                        i4 = fVar14.s();
                    } else {
                        i4 = fVar14.i();
                    }
                    float fB7 = fB6 + fVar14.A[i2].b();
                    fVar14.A[i2].d().a(mVarD.f159f, fB7);
                    float f10 = fB7 + i4;
                    fVar14.A[i8].d().a(mVarD.f159f, f10);
                    fVar14.A[i2].d().a(eVar);
                    fVar14.A[i8].d().a(eVar);
                    fB6 = f10 + fVar14.A[i8].b();
                }
                fVar14 = fVar16;
            }
            return true;
        }
        f fVar17 = fVar2;
        if (!z && !z2) {
            return true;
        }
        if (z || z2) {
            f6 -= fB;
        }
        float f11 = f6 / (i7 + 1);
        if (z2) {
            f11 = f6 / (i7 > 1 ? i7 - 1 : 2.0f);
        }
        float fB8 = fVar17.r() != 8 ? f4 + f11 : f4;
        if (z2 && i7 > 1) {
            fB8 = fVar4.A[i2].b() + f4;
        }
        if (z && fVar4 != null) {
            fB8 += fVar4.A[i2].b();
        }
        while (fVar17 != null) {
            a.c.a.f fVar18 = a.c.a.e.q;
            if (fVar18 != null) {
                fVar18.z--;
                fVar18.r++;
                fVar18.x++;
            }
            f fVar19 = fVar17.i0[i];
            if (fVar19 != null || fVar17 == fVar3) {
                if (i == 0) {
                    i3 = fVar17.s();
                } else {
                    i3 = fVar17.i();
                }
                float f12 = i3;
                if (fVar17 != fVar4) {
                    fB8 += fVar17.A[i2].b();
                }
                fVar17.A[i2].d().a(mVarD.f159f, fB8);
                fVar17.A[i8].d().a(mVarD.f159f, fB8 + f12);
                fVar17.A[i2].d().a(eVar);
                fVar17.A[i8].d().a(eVar);
                fB8 += f12 + fVar17.A[i8].b();
                if (fVar19 != null) {
                    if (fVar19.r() != 8) {
                        fB8 += f11;
                    }
                }
            }
            fVar17 = fVar19;
        }
        return true;
    }

    static void a(f fVar, int i, int i2) {
        int i3 = i * 2;
        int i4 = i3 + 1;
        fVar.A[i3].d().f159f = fVar.k().s.d();
        fVar.A[i3].d().g = i2;
        fVar.A[i3].d().f162b = 1;
        fVar.A[i4].d().f159f = fVar.A[i3].d();
        fVar.A[i4].d().g = fVar.d(i);
        fVar.A[i4].d().f162b = 1;
    }
}
