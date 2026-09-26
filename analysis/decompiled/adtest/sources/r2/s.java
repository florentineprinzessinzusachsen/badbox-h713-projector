package r2;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public abstract class s extends y1.a implements y1.e {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final r f2024e = new r(y1.d.f2725d, new d0.h(19));

    public s() {
        super(y1.d.f2725d);
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x001d, code lost:
    
        if (((y1.f) r3.f2021d.h(r2)) == null) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0022, code lost:
    
        if (y1.d.f2725d == r3) goto L15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0026, code lost:
    
        return y1.i.f2726d;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0027, code lost:
    
        return r2;
     */
    @Override // y1.a, y1.h
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final y1.h C(y1.g r3) {
        /*
            r2 = this;
            java.lang.String r0 = "key"
            j2.i.e(r3, r0)
            boolean r0 = r3 instanceof r2.r
            if (r0 == 0) goto L20
            r2.r r3 = (r2.r) r3
            y1.g r0 = r2.f2722d
            if (r0 == r3) goto L15
            y1.g r1 = r3.f2022e
            if (r1 != r0) goto L14
            goto L15
        L14:
            return r2
        L15:
            i2.l r3 = r3.f2021d
            java.lang.Object r3 = r3.h(r2)
            y1.f r3 = (y1.f) r3
            if (r3 == 0) goto L27
            goto L24
        L20:
            y1.d r0 = y1.d.f2725d
            if (r0 != r3) goto L27
        L24:
            y1.i r3 = y1.i.f2726d
            return r3
        L27:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: r2.s.C(y1.g):y1.h");
    }

    public abstract void S(y1.h hVar, Runnable runnable);

    public void T(y1.h hVar, Runnable runnable) {
        S(hVar, runnable);
    }

    public boolean U(y1.h hVar) {
        return !(this instanceof o1);
    }

    public s V(int i4) {
        w2.a.a(i4);
        return new w2.g(this, i4);
    }

    @Override // y1.a, y1.h
    public final y1.f k(y1.g gVar) {
        y1.f fVar;
        j2.i.e(gVar, "key");
        if (!(gVar instanceof r)) {
            if (y1.d.f2725d == gVar) {
                return this;
            }
            return null;
        }
        r rVar = (r) gVar;
        y1.g gVar2 = this.f2722d;
        if ((gVar2 == rVar || rVar.f2022e == gVar2) && (fVar = (y1.f) rVar.f2021d.h(this)) != null) {
            return fVar;
        }
        return null;
    }

    public String toString() {
        return getClass().getSimpleName() + '@' + x.k(this);
    }
}
