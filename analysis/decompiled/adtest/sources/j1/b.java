package j1;

import a2.i;
import d0.l0;
import i2.l;
import i2.p;
import java.util.concurrent.atomic.AtomicBoolean;
import r2.c1;
import r2.d1;
import r2.e0;
import r2.t;
import r2.v;
import r2.v0;
import r2.x;
import u1.k;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class b extends i implements p {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public String f1236h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public z2.c f1237i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public l f1238j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f1239k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ String f1240l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f1241m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ AtomicBoolean f1242n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ v f1243o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ l f1244p;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public b(String str, int i4, AtomicBoolean atomicBoolean, v vVar, l lVar, y1.c cVar) {
        super(2, cVar);
        this.f1240l = str;
        this.f1241m = i4;
        this.f1242n = atomicBoolean;
        this.f1243o = vVar;
        this.f1244p = lVar;
    }

    @Override // i2.p
    public final Object f(Object obj, Object obj2) {
        return ((b) i((v) obj, (y1.c) obj2)).l(k.f2301a);
    }

    @Override // a2.a
    public final y1.c i(Object obj, y1.c cVar) {
        return new b(this.f1240l, this.f1241m, this.f1242n, this.f1243o, this.f1244p, cVar);
    }

    /* JADX WARN: Code duplicated, block: B:26:0x006f A[LOOP:2: B:14:0x004c->B:26:0x006f, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:45:0x00ff  */
    /* JADX WARN: Code duplicated, block: B:48:0x0115 A[LOOP:0: B:46:0x010f->B:48:0x0115, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:60:0x0071 A[EDGE_INSN: B:60:0x0071->B:27:0x0071 BREAK  A[LOOP:2: B:14:0x004c->B:26:0x006f], SYNTHETIC] */
    @Override // a2.a
    public final Object l(Object obj) {
        String str;
        String str2;
        l lVar;
        z2.c cVar;
        v0 v0Var;
        o2.d dVar;
        int i4 = this.f1239k;
        z1.a aVar = z1.a.f2781d;
        if (i4 != 0) {
            if (i4 == 1) {
                str = this.f1236h;
                l0.M(obj);
            } else {
                if (i4 != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                lVar = this.f1238j;
                cVar = this.f1237i;
                str2 = this.f1236h;
                l0.M(obj);
            }
            try {
                lVar.h("https://" + str2 + "/");
                cVar.b(null);
                v0Var = (v0) this.f1243o.i().k(t.f2027e);
                if (v0Var != null) {
                    dVar = new o2.d();
                    c1 c1Var = new c1((d1) v0Var, dVar);
                    c1Var.f1967i = dVar;
                    dVar.f1566f = c1Var;
                    while (dVar.hasNext()) {
                        ((v0) dVar.next()).b(null);
                    }
                }
                return k.f2301a;
            } catch (Throwable th) {
                cVar.b(null);
                throw th;
            }
        }
        l0.M(obj);
        e eVar = e.f1256a;
        int i5 = this.f1241m % 100;
        String str3 = this.f1240l;
        int iCharAt = (str3.charAt(0) + 1) * i5 * i5;
        char[] charArray = str3.toCharArray();
        j2.i.d(charArray, "toCharArray(...)");
        for (int i6 = 0; i6 < iCharAt; i6++) {
            int length = charArray.length - 1;
            boolean z3 = true;
            if (length >= 0) {
                while (true) {
                    int i7 = length - 1;
                    if (!z3) {
                        break;
                    }
                    char c4 = charArray[length];
                    if (c4 != '9') {
                        if (c4 != 'z') {
                            charArray[length] = (char) (c4 + 1);
                        } else {
                            charArray[length] = '0';
                            z3 = true;
                        }
                        if (i7 < 0) {
                            break;
                        }
                        length = i7;
                    } else {
                        charArray[length] = 'a';
                    }
                    z3 = false;
                    if (i7 < 0) {
                        break;
                        break;
                    }
                    length = i7;
                }
            }
            if (z3) {
                charArray = "1".concat(new String(charArray)).toCharArray();
                j2.i.d(charArray, "toCharArray(...)");
            }
        }
        String strConcat = new String(charArray).concat(".cc");
        e eVar2 = e.f1256a;
        this.f1236h = strConcat;
        this.f1239k = 1;
        y2.e eVar3 = e0.f1974a;
        Object objW = x.w(y2.d.f2753f, new d(strConcat, null, 0), this);
        if (objW != aVar) {
            str = strConcat;
            obj = objW;
        }
        return aVar;
        if (((Boolean) obj).booleanValue() && this.f1242n.compareAndSet(false, true)) {
            z2.c cVar2 = e.f1257b;
            this.f1236h = str;
            this.f1237i = cVar2;
            l lVar2 = this.f1244p;
            this.f1238j = lVar2;
            this.f1239k = 2;
            if (cVar2.c(this) != aVar) {
                str2 = str;
                lVar = lVar2;
                cVar = cVar2;
                lVar.h("https://" + str2 + "/");
                cVar.b(null);
                v0Var = (v0) this.f1243o.i().k(t.f2027e);
                if (v0Var != null) {
                    dVar = new o2.d();
                    c1 c1Var2 = new c1((d1) v0Var, dVar);
                    c1Var2.f1967i = dVar;
                    dVar.f1566f = c1Var2;
                    while (dVar.hasNext()) {
                        ((v0) dVar.next()).b(null);
                    }
                }
            }
            return aVar;
        }
        return k.f2301a;
    }
}
