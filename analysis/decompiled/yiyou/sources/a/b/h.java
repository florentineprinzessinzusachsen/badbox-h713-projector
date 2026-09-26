package a.b;

/* JADX INFO: compiled from: SparseArrayCompat.java */
/* JADX INFO: loaded from: classes.dex */
public class h<E> implements Cloneable {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static final Object f64e = new Object();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private boolean f65a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int[] f66b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Object[] f67c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f68d;

    public h() {
        this(10);
    }

    public E a(int i) {
        return b(i, null);
    }

    public E b(int i, E e2) {
        int iA = c.a(this.f66b, this.f68d, i);
        if (iA >= 0) {
            Object[] objArr = this.f67c;
            if (objArr[iA] != f64e) {
                return (E) objArr[iA];
            }
        }
        return e2;
    }

    public void c(int i) {
        int iA = c.a(this.f66b, this.f68d, i);
        if (iA >= 0) {
            Object[] objArr = this.f67c;
            Object obj = objArr[iA];
            Object obj2 = f64e;
            if (obj != obj2) {
                objArr[iA] = obj2;
                this.f65a = true;
            }
        }
    }

    public E d(int i) {
        if (this.f65a) {
            c();
        }
        return (E) this.f67c[i];
    }

    public String toString() {
        if (b() <= 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.f68d * 28);
        sb.append('{');
        for (int i = 0; i < this.f68d; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(b(i));
            sb.append('=');
            E eD = d(i);
            if (eD != this) {
                sb.append(eD);
            } else {
                sb.append("(this Map)");
            }
        }
        sb.append('}');
        return sb.toString();
    }

    public h(int i) {
        this.f65a = false;
        if (i == 0) {
            this.f66b = c.f29a;
            this.f67c = c.f31c;
        } else {
            int iB = c.b(i);
            this.f66b = new int[iB];
            this.f67c = new Object[iB];
        }
    }

    public void a() {
        int i = this.f68d;
        Object[] objArr = this.f67c;
        for (int i2 = 0; i2 < i; i2++) {
            objArr[i2] = null;
        }
        this.f68d = 0;
        this.f65a = false;
    }

    /* JADX INFO: renamed from: clone, reason: merged with bridge method [inline-methods] */
    public h<E> m1clone() {
        try {
            h<E> hVar = (h) super.clone();
            hVar.f66b = (int[]) this.f66b.clone();
            hVar.f67c = (Object[]) this.f67c.clone();
            return hVar;
        } catch (CloneNotSupportedException e2) {
            throw new AssertionError(e2);
        }
    }

    public int b() {
        if (this.f65a) {
            c();
        }
        return this.f68d;
    }

    private void c() {
        int i = this.f68d;
        int[] iArr = this.f66b;
        Object[] objArr = this.f67c;
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            Object obj = objArr[i3];
            if (obj != f64e) {
                if (i3 != i2) {
                    iArr[i2] = iArr[i3];
                    objArr[i2] = obj;
                    objArr[i3] = null;
                }
                i2++;
            }
        }
        this.f65a = false;
        this.f68d = i2;
    }

    public void a(int i, E e2) {
        int i2 = this.f68d;
        if (i2 != 0 && i <= this.f66b[i2 - 1]) {
            c(i, e2);
            return;
        }
        if (this.f65a && this.f68d >= this.f66b.length) {
            c();
        }
        int i3 = this.f68d;
        if (i3 >= this.f66b.length) {
            int iB = c.b(i3 + 1);
            int[] iArr = new int[iB];
            Object[] objArr = new Object[iB];
            int[] iArr2 = this.f66b;
            System.arraycopy(iArr2, 0, iArr, 0, iArr2.length);
            Object[] objArr2 = this.f67c;
            System.arraycopy(objArr2, 0, objArr, 0, objArr2.length);
            this.f66b = iArr;
            this.f67c = objArr;
        }
        this.f66b[i3] = i;
        this.f67c[i3] = e2;
        this.f68d = i3 + 1;
    }

    public int b(int i) {
        if (this.f65a) {
            c();
        }
        return this.f66b[i];
    }

    public void c(int i, E e2) {
        int iA = c.a(this.f66b, this.f68d, i);
        if (iA >= 0) {
            this.f67c[iA] = e2;
            return;
        }
        int iA2 = iA ^ (-1);
        if (iA2 < this.f68d) {
            Object[] objArr = this.f67c;
            if (objArr[iA2] == f64e) {
                this.f66b[iA2] = i;
                objArr[iA2] = e2;
                return;
            }
        }
        if (this.f65a && this.f68d >= this.f66b.length) {
            c();
            iA2 = c.a(this.f66b, this.f68d, i) ^ (-1);
        }
        int i2 = this.f68d;
        if (i2 >= this.f66b.length) {
            int iB = c.b(i2 + 1);
            int[] iArr = new int[iB];
            Object[] objArr2 = new Object[iB];
            int[] iArr2 = this.f66b;
            System.arraycopy(iArr2, 0, iArr, 0, iArr2.length);
            Object[] objArr3 = this.f67c;
            System.arraycopy(objArr3, 0, objArr2, 0, objArr3.length);
            this.f66b = iArr;
            this.f67c = objArr2;
        }
        int i3 = this.f68d;
        if (i3 - iA2 != 0) {
            int[] iArr3 = this.f66b;
            int i4 = iA2 + 1;
            System.arraycopy(iArr3, iA2, iArr3, i4, i3 - iA2);
            Object[] objArr4 = this.f67c;
            System.arraycopy(objArr4, iA2, objArr4, i4, this.f68d - iA2);
        }
        this.f66b[iA2] = i;
        this.f67c[iA2] = e2;
        this.f68d++;
    }
}
