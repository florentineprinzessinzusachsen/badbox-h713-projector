package w2;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import r2.l0;
import r2.m0;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public class w {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final /* synthetic */ AtomicIntegerFieldUpdater f2656b = AtomicIntegerFieldUpdater.newUpdater(w.class, "_size$volatile");
    private volatile /* synthetic */ int _size$volatile;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public l0[] f2657a;

    public final void a(l0 l0Var) {
        l0Var.e((m0) this);
        l0[] l0VarArr = this.f2657a;
        AtomicIntegerFieldUpdater atomicIntegerFieldUpdater = f2656b;
        if (l0VarArr == null) {
            l0VarArr = new l0[4];
            this.f2657a = l0VarArr;
        } else if (atomicIntegerFieldUpdater.get(this) >= l0VarArr.length) {
            Object[] objArrCopyOf = Arrays.copyOf(l0VarArr, atomicIntegerFieldUpdater.get(this) * 2);
            j2.i.d(objArrCopyOf, "copyOf(...)");
            l0VarArr = (l0[]) objArrCopyOf;
            this.f2657a = l0VarArr;
        }
        int i4 = atomicIntegerFieldUpdater.get(this);
        atomicIntegerFieldUpdater.set(this, i4 + 1);
        l0VarArr[i4] = l0Var;
        l0Var.f1997e = i4;
        d(i4);
    }

    public final void b(l0 l0Var) {
        synchronized (this) {
            if (l0Var.c() != null) {
                c(l0Var.f1997e);
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:12:0x0045  */
    /* JADX WARN: Code duplicated, block: B:14:0x0052  */
    /* JADX WARN: Code duplicated, block: B:17:0x0063  */
    /* JADX WARN: Code duplicated, block: B:21:0x0075 A[LOOP:0: B:9:0x003a->B:21:0x0075, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:24:0x007a A[EDGE_INSN: B:24:0x007a->B:22:0x007a BREAK  A[LOOP:0: B:9:0x003a->B:21:0x0075], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:25:0x007a A[EDGE_INSN: B:25:0x007a->B:22:0x007a BREAK  A[LOOP:0: B:9:0x003a->B:21:0x0075], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:26:? A[SYNTHETIC] */
    public final l0 c(int i4) {
        int i5;
        int i6;
        Object[] objArr;
        int i7;
        Comparable comparable;
        Comparable comparable2;
        Comparable comparable3;
        Object obj;
        Object[] objArr2 = this.f2657a;
        j2.i.b(objArr2);
        AtomicIntegerFieldUpdater atomicIntegerFieldUpdater = f2656b;
        atomicIntegerFieldUpdater.set(this, atomicIntegerFieldUpdater.get(this) - 1);
        if (i4 < atomicIntegerFieldUpdater.get(this)) {
            e(i4, atomicIntegerFieldUpdater.get(this));
            int i8 = (i4 - 1) / 2;
            if (i4 > 0) {
                l0 l0Var = objArr2[i4];
                j2.i.b(l0Var);
                Object obj2 = objArr2[i8];
                j2.i.b(obj2);
                if (l0Var.compareTo(obj2) < 0) {
                    e(i4, i8);
                    d(i8);
                } else {
                    while (true) {
                        i5 = i4 * 2;
                        i6 = i5 + 1;
                        if (i6 >= atomicIntegerFieldUpdater.get(this)) {
                            break;
                        }
                        objArr = this.f2657a;
                        j2.i.b(objArr);
                        i7 = i5 + 2;
                        if (i7 < atomicIntegerFieldUpdater.get(this)) {
                            comparable3 = objArr[i7];
                            j2.i.b(comparable3);
                            obj = objArr[i6];
                            j2.i.b(obj);
                            if (comparable3.compareTo(obj) >= 0) {
                                i7 = i6;
                            }
                        } else {
                            i7 = i6;
                        }
                        comparable = objArr[i4];
                        j2.i.b(comparable);
                        comparable2 = objArr[i7];
                        j2.i.b(comparable2);
                        if (comparable.compareTo(comparable2) <= 0) {
                            break;
                        }
                        e(i4, i7);
                        i4 = i7;
                    }
                }
            } else {
                while (true) {
                    i5 = i4 * 2;
                    i6 = i5 + 1;
                    if (i6 >= atomicIntegerFieldUpdater.get(this)) {
                        break;
                        break;
                    }
                    objArr = this.f2657a;
                    j2.i.b(objArr);
                    i7 = i5 + 2;
                    if (i7 < atomicIntegerFieldUpdater.get(this)) {
                        comparable3 = objArr[i7];
                        j2.i.b(comparable3);
                        obj = objArr[i6];
                        j2.i.b(obj);
                        if (comparable3.compareTo(obj) >= 0) {
                            i7 = i6;
                        }
                    } else {
                        i7 = i6;
                    }
                    comparable = objArr[i4];
                    j2.i.b(comparable);
                    comparable2 = objArr[i7];
                    j2.i.b(comparable2);
                    if (comparable.compareTo(comparable2) <= 0) {
                        break;
                        break;
                    }
                    e(i4, i7);
                    i4 = i7;
                }
            }
        }
        l0 l0Var2 = objArr2[atomicIntegerFieldUpdater.get(this)];
        j2.i.b(l0Var2);
        l0Var2.e(null);
        l0Var2.f1997e = -1;
        objArr2[atomicIntegerFieldUpdater.get(this)] = null;
        return l0Var2;
    }

    public final void d(int i4) {
        while (i4 > 0) {
            l0[] l0VarArr = this.f2657a;
            j2.i.b(l0VarArr);
            int i5 = (i4 - 1) / 2;
            l0 l0Var = l0VarArr[i5];
            j2.i.b(l0Var);
            l0 l0Var2 = l0VarArr[i4];
            j2.i.b(l0Var2);
            if (l0Var.compareTo(l0Var2) <= 0) {
                return;
            }
            e(i4, i5);
            i4 = i5;
        }
    }

    public final void e(int i4, int i5) {
        l0[] l0VarArr = this.f2657a;
        j2.i.b(l0VarArr);
        l0 l0Var = l0VarArr[i5];
        j2.i.b(l0Var);
        l0 l0Var2 = l0VarArr[i4];
        j2.i.b(l0Var2);
        l0VarArr[i4] = l0Var;
        l0VarArr[i5] = l0Var2;
        l0Var.f1997e = i4;
        l0Var2.f1997e = i5;
    }
}
