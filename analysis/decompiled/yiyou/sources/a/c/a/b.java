package a.c.a;

/* JADX INFO: compiled from: ArrayRow.java */
/* JADX INFO: loaded from: classes.dex */
public class b implements e.a {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    boolean f77c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final a f78d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    i f75a = null;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    float f76b = 0.0f;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    boolean f79e = false;

    public b(c cVar) {
        this.f78d = new a(this, cVar);
    }

    public b a(i iVar, i iVar2, int i) {
        boolean z = false;
        if (i != 0) {
            if (i < 0) {
                i *= -1;
                z = true;
            }
            this.f76b = i;
        }
        if (z) {
            this.f78d.a(iVar, 1.0f);
            this.f78d.a(iVar2, -1.0f);
        } else {
            this.f78d.a(iVar, -1.0f);
            this.f78d.a(iVar2, 1.0f);
        }
        return this;
    }

    boolean b() {
        i iVar = this.f75a;
        return iVar != null && (iVar.g == i.a.UNRESTRICTED || this.f76b >= 0.0f);
    }

    public b c(i iVar, int i) {
        if (i < 0) {
            this.f76b = i * (-1);
            this.f78d.a(iVar, 1.0f);
        } else {
            this.f76b = i;
            this.f78d.a(iVar, -1.0f);
        }
        return this;
    }

    @Override // a.c.a.e.a
    public void clear() {
        this.f78d.a();
        this.f75a = null;
        this.f76b = 0.0f;
    }

    public void d() {
        this.f75a = null;
        this.f78d.a();
        this.f76b = 0.0f;
        this.f79e = false;
    }

    String e() {
        boolean z;
        String str = (this.f75a == null ? "0" : "" + this.f75a) + " = ";
        if (this.f76b != 0.0f) {
            str = str + this.f76b;
            z = true;
        } else {
            z = false;
        }
        int i = this.f78d.f69a;
        for (int i2 = 0; i2 < i; i2++) {
            i iVarA = this.f78d.a(i2);
            if (iVarA != null) {
                float fB = this.f78d.b(i2);
                if (fB != 0.0f) {
                    String string = iVarA.toString();
                    if (z) {
                        if (fB > 0.0f) {
                            str = str + " + ";
                        } else {
                            str = str + " - ";
                            fB *= -1.0f;
                        }
                    } else if (fB < 0.0f) {
                        str = str + "- ";
                        fB *= -1.0f;
                    }
                    str = fB == 1.0f ? str + string : str + fB + " " + string;
                    z = true;
                }
            }
        }
        if (z) {
            return str;
        }
        return str + "0.0";
    }

    @Override // a.c.a.e.a
    public i getKey() {
        return this.f75a;
    }

    public String toString() {
        return e();
    }

    boolean b(i iVar) {
        return this.f78d.a(iVar);
    }

    b b(i iVar, int i) {
        this.f75a = iVar;
        float f2 = i;
        iVar.f101e = f2;
        this.f76b = f2;
        this.f79e = true;
        return this;
    }

    i c(i iVar) {
        return this.f78d.a((boolean[]) null, iVar);
    }

    void d(i iVar) {
        i iVar2 = this.f75a;
        if (iVar2 != null) {
            this.f78d.a(iVar2, -1.0f);
            this.f75a = null;
        }
        float fA = this.f78d.a(iVar, true) * (-1.0f);
        this.f75a = iVar;
        if (fA == 1.0f) {
            return;
        }
        this.f76b /= fA;
        this.f78d.a(fA);
    }

    b a(i iVar, int i) {
        this.f78d.a(iVar, i);
        return this;
    }

    public boolean c() {
        return this.f75a == null && this.f76b == 0.0f && this.f78d.f69a == 0;
    }

    public b a(i iVar, i iVar2, i iVar3, int i) {
        boolean z = false;
        if (i != 0) {
            if (i < 0) {
                i *= -1;
                z = true;
            }
            this.f76b = i;
        }
        if (!z) {
            this.f78d.a(iVar, -1.0f);
            this.f78d.a(iVar2, 1.0f);
            this.f78d.a(iVar3, 1.0f);
        } else {
            this.f78d.a(iVar, 1.0f);
            this.f78d.a(iVar2, -1.0f);
            this.f78d.a(iVar3, -1.0f);
        }
        return this;
    }

    public b b(i iVar, i iVar2, i iVar3, int i) {
        boolean z = false;
        if (i != 0) {
            if (i < 0) {
                i *= -1;
                z = true;
            }
            this.f76b = i;
        }
        if (!z) {
            this.f78d.a(iVar, -1.0f);
            this.f78d.a(iVar2, 1.0f);
            this.f78d.a(iVar3, -1.0f);
        } else {
            this.f78d.a(iVar, 1.0f);
            this.f78d.a(iVar2, -1.0f);
            this.f78d.a(iVar3, 1.0f);
        }
        return this;
    }

    public b a(float f2, float f3, float f4, i iVar, i iVar2, i iVar3, i iVar4) {
        this.f76b = 0.0f;
        if (f3 == 0.0f || f2 == f4) {
            this.f78d.a(iVar, 1.0f);
            this.f78d.a(iVar2, -1.0f);
            this.f78d.a(iVar4, 1.0f);
            this.f78d.a(iVar3, -1.0f);
        } else if (f2 == 0.0f) {
            this.f78d.a(iVar, 1.0f);
            this.f78d.a(iVar2, -1.0f);
        } else if (f4 == 0.0f) {
            this.f78d.a(iVar3, 1.0f);
            this.f78d.a(iVar4, -1.0f);
        } else {
            float f5 = (f2 / f3) / (f4 / f3);
            this.f78d.a(iVar, 1.0f);
            this.f78d.a(iVar2, -1.0f);
            this.f78d.a(iVar4, f5);
            this.f78d.a(iVar3, -f5);
        }
        return this;
    }

    public b b(i iVar, i iVar2, i iVar3, i iVar4, float f2) {
        this.f78d.a(iVar3, 0.5f);
        this.f78d.a(iVar4, 0.5f);
        this.f78d.a(iVar, -0.5f);
        this.f78d.a(iVar2, -0.5f);
        this.f76b = -f2;
        return this;
    }

    b a(i iVar, i iVar2, int i, float f2, i iVar3, i iVar4, int i2) {
        if (iVar2 == iVar3) {
            this.f78d.a(iVar, 1.0f);
            this.f78d.a(iVar4, 1.0f);
            this.f78d.a(iVar2, -2.0f);
            return this;
        }
        if (f2 == 0.5f) {
            this.f78d.a(iVar, 1.0f);
            this.f78d.a(iVar2, -1.0f);
            this.f78d.a(iVar3, -1.0f);
            this.f78d.a(iVar4, 1.0f);
            if (i > 0 || i2 > 0) {
                this.f76b = (-i) + i2;
            }
        } else if (f2 <= 0.0f) {
            this.f78d.a(iVar, -1.0f);
            this.f78d.a(iVar2, 1.0f);
            this.f76b = i;
        } else if (f2 >= 1.0f) {
            this.f78d.a(iVar3, -1.0f);
            this.f78d.a(iVar4, 1.0f);
            this.f76b = i2;
        } else {
            float f3 = 1.0f - f2;
            this.f78d.a(iVar, f3 * 1.0f);
            this.f78d.a(iVar2, f3 * (-1.0f));
            this.f78d.a(iVar3, (-1.0f) * f2);
            this.f78d.a(iVar4, 1.0f * f2);
            if (i > 0 || i2 > 0) {
                this.f76b = ((-i) * f3) + (i2 * f2);
            }
        }
        return this;
    }

    public b a(e eVar, int i) {
        this.f78d.a(eVar.a(i, "ep"), 1.0f);
        this.f78d.a(eVar.a(i, "em"), -1.0f);
        return this;
    }

    b a(i iVar, i iVar2, i iVar3, float f2) {
        this.f78d.a(iVar, -1.0f);
        this.f78d.a(iVar2, 1.0f - f2);
        this.f78d.a(iVar3, f2);
        return this;
    }

    public b a(i iVar, i iVar2, i iVar3, i iVar4, float f2) {
        this.f78d.a(iVar, -1.0f);
        this.f78d.a(iVar2, 1.0f);
        this.f78d.a(iVar3, f2);
        this.f78d.a(iVar4, -f2);
        return this;
    }

    void a() {
        float f2 = this.f76b;
        if (f2 < 0.0f) {
            this.f76b = f2 * (-1.0f);
            this.f78d.b();
        }
    }

    boolean a(e eVar) {
        boolean z;
        i iVarA = this.f78d.a(eVar);
        if (iVarA == null) {
            z = true;
        } else {
            d(iVarA);
            z = false;
        }
        if (this.f78d.f69a == 0) {
            this.f79e = true;
        }
        return z;
    }

    @Override // a.c.a.e.a
    public i a(e eVar, boolean[] zArr) {
        return this.f78d.a(zArr, (i) null);
    }

    @Override // a.c.a.e.a
    public void a(e.a aVar) {
        if (!(aVar instanceof b)) {
            return;
        }
        b bVar = (b) aVar;
        this.f75a = null;
        this.f78d.a();
        int i = 0;
        while (true) {
            a aVar2 = bVar.f78d;
            if (i >= aVar2.f69a) {
                return;
            }
            this.f78d.a(aVar2.a(i), bVar.f78d.b(i), true);
            i++;
        }
    }

    @Override // a.c.a.e.a
    public void a(i iVar) {
        int i = iVar.f100d;
        float f2 = 1.0f;
        if (i != 1) {
            if (i == 2) {
                f2 = 1000.0f;
            } else if (i == 3) {
                f2 = 1000000.0f;
            } else if (i == 4) {
                f2 = 1.0E9f;
            } else if (i == 5) {
                f2 = 1.0E12f;
            }
        }
        this.f78d.a(iVar, f2);
    }
}
