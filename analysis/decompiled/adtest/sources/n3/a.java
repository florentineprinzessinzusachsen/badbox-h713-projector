package n3;

import a1.c;
import a3.a0;
import j2.i;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.IDN;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;
import o2.b;
import o2.e;
import o2.f;
import q3.h;
import v1.j;
import v1.p;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final h f1494b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final List f1495c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final a f1496d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final a0 f1497a;

    static {
        byte[] bArrCopyOf = Arrays.copyOf(new byte[]{42}, 1);
        i.d(bArrCopyOf, "copyOf(...)");
        f1494b = new h(bArrCopyOf);
        f1495c = l3.h.S("*");
        f1496d = new a(new a0());
    }

    public a(a0 a0Var) {
        this.f1497a = a0Var;
    }

    public static List b(String str) {
        List listP0 = p2.i.P0(str, new char[]{'.'});
        if (!i.a(j.z0(listP0), "")) {
            return listP0;
        }
        int size = listP0.size() - 1;
        if (size < 0) {
            size = 0;
        }
        if (size < 0) {
            throw new IllegalArgumentException(c.d(size, "Requested element count ", " is less than zero.").toString());
        }
        p pVar = p.f2517d;
        if (size == 0) {
            return pVar;
        }
        if (size >= listP0.size()) {
            return j.G0(listP0);
        }
        if (size == 1) {
            if (listP0.isEmpty()) {
                throw new NoSuchElementException("List is empty.");
            }
            return l3.h.S(listP0.get(0));
        }
        ArrayList arrayList = new ArrayList(size);
        Iterator it = listP0.iterator();
        int i4 = 0;
        while (it.hasNext()) {
            arrayList.add(it.next());
            i4++;
            if (i4 == size) {
                break;
            }
        }
        int size2 = arrayList.size();
        if (size2 != 0) {
            return size2 != 1 ? arrayList : l3.h.S(arrayList.get(0));
        }
        return pVar;
    }

    public final String a(String str) {
        String strG;
        String strG2;
        String strG3;
        List listP0;
        int size;
        int size2;
        String unicode = IDN.toUnicode(str);
        i.b(unicode);
        List listB = b(unicode);
        a0 a0Var = this.f1497a;
        AtomicBoolean atomicBoolean = (AtomicBoolean) a0Var.f63c;
        if (atomicBoolean.get() || !atomicBoolean.compareAndSet(false, true)) {
            try {
                ((CountDownLatch) a0Var.f64d).await();
            } catch (InterruptedException unused) {
                Thread.currentThread().interrupt();
            }
        } else {
            boolean z3 = false;
            while (true) {
                try {
                    try {
                        a0Var.d();
                        break;
                    } catch (InterruptedIOException unused2) {
                        Thread.interrupted();
                        z3 = true;
                    } catch (IOException e4) {
                        a0Var.f67g = e4;
                        if (z3) {
                        }
                    }
                } catch (Throwable th) {
                    if (z3) {
                        Thread.currentThread().interrupt();
                    }
                    throw th;
                }
            }
            if (z3) {
                Thread.currentThread().interrupt();
            }
        }
        if (((h) a0Var.f65e) == null) {
            IllegalStateException illegalStateException = new IllegalStateException("Unable to load " + ((Object) a0Var.f62b) + " resource.");
            illegalStateException.initCause((IOException) a0Var.f67g);
            throw illegalStateException;
        }
        int size3 = listB.size();
        h[] hVarArr = new h[size3];
        for (int i4 = 0; i4 < size3; i4++) {
            h hVar = h.f1823g;
            hVarArr[i4] = a1.a.m((String) listB.get(i4));
        }
        int i5 = 0;
        while (true) {
            if (i5 >= size3) {
                strG = null;
                break;
            }
            h hVar2 = (h) a0Var.f65e;
            if (hVar2 == null) {
                i.h("bytes");
                throw null;
            }
            strG = a1.a.g(hVar2, hVarArr, i5);
            if (strG != null) {
                break;
            }
            i5++;
        }
        if (size3 <= 1) {
            strG2 = null;
            break;
        }
        h[] hVarArr2 = (h[]) hVarArr.clone();
        int length = hVarArr2.length - 1;
        int i6 = 0;
        while (true) {
            if (i6 >= length) {
                strG2 = null;
                break;
            }
            hVarArr2[i6] = f1494b;
            h hVar3 = (h) a0Var.f65e;
            if (hVar3 == null) {
                i.h("bytes");
                throw null;
            }
            strG2 = a1.a.g(hVar3, hVarArr2, i6);
            if (strG2 != null) {
                break;
            }
            i6++;
        }
        if (strG2 == null) {
            strG3 = null;
            break;
        }
        int i7 = size3 - 1;
        int i8 = 0;
        while (true) {
            if (i8 >= i7) {
                strG3 = null;
                break;
            }
            h hVar4 = (h) a0Var.f66f;
            if (hVar4 == null) {
                i.h("exceptionBytes");
                throw null;
            }
            strG3 = a1.a.g(hVar4, hVarArr, i8);
            if (strG3 != null) {
                break;
            }
            i8++;
        }
        if (strG3 != null) {
            listP0 = p2.i.P0("!".concat(strG3), new char[]{'.'});
        } else if (strG == null && strG2 == null) {
            listP0 = f1495c;
        } else {
            List listP1 = p.f2517d;
            List listP2 = strG != null ? p2.i.P0(strG, new char[]{'.'}) : listP1;
            if (strG2 != null) {
                listP1 = p2.i.P0(strG2, new char[]{'.'});
            }
            listP0 = listP2.size() > listP1.size() ? listP2 : listP1;
        }
        if (listB.size() == listP0.size() && ((String) listP0.get(0)).charAt(0) != '!') {
            return null;
        }
        if (((String) listP0.get(0)).charAt(0) == '!') {
            size = listB.size();
            size2 = listP0.size();
        } else {
            size = listB.size();
            size2 = listP0.size() + 1;
        }
        int i9 = size - size2;
        o2.c fVar = new f(2, b(str));
        if (i9 < 0) {
            throw new IllegalArgumentException(c.d(i9, "Requested element count ", " is less than zero.").toString());
        }
        if (i9 != 0) {
            fVar = new b(fVar, i9);
        }
        return e.J(fVar, ".");
    }
}
