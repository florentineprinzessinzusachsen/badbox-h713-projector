package a.b;

/* JADX INFO: compiled from: LongSparseArray.java */
/* JADX INFO: loaded from: classes.dex */
public class d<E> implements Cloneable {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static final Object f32e = new Object();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private boolean f33a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private long[] f34b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Object[] f35c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f36d;

    public d() {
        this(10);
    }

    @Deprecated
    public void a(long j) {
        c(j);
    }

    public E b(long j) {
        return b(j, null);
    }

    public void c(long j) {
        int iA = c.a(this.f34b, this.f36d, j);
        if (iA >= 0) {
            Object[] objArr = this.f35c;
            Object obj = objArr[iA];
            Object obj2 = f32e;
            if (obj != obj2) {
                objArr[iA] = obj2;
                this.f33a = true;
            }
        }
    }

    public String toString() {
        if (b() <= 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.f36d * 28);
        sb.append('{');
        for (int i = 0; i < this.f36d; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(a(i));
            sb.append('=');
            E eB = b(i);
            if (eB != this) {
                sb.append(eB);
            } else {
                sb.append("(this Map)");
            }
        }
        sb.append('}');
        return sb.toString();
    }

    public d(int i) {
        this.f33a = false;
        if (i == 0) {
            this.f34b = c.f30b;
            this.f35c = c.f31c;
        } else {
            int iC = c.c(i);
            this.f34b = new long[iC];
            this.f35c = new Object[iC];
        }
    }

    public long a(int i) {
        if (this.f33a) {
            c();
        }
        return this.f34b[i];
    }

    public E b(long j, E e2) {
        int iA = c.a(this.f34b, this.f36d, j);
        if (iA >= 0) {
            Object[] objArr = this.f35c;
            if (objArr[iA] != f32e) {
                return (E) objArr[iA];
            }
        }
        return e2;
    }

    /* JADX INFO: renamed from: clone, reason: merged with bridge method [inline-methods] */
    public d<E> m0clone() {
        try {
            d<E> dVar = (d) super.clone();
            dVar.f34b = (long[]) this.f34b.clone();
            dVar.f35c = (Object[]) this.f35c.clone();
            return dVar;
        } catch (CloneNotSupportedException e2) {
            throw new AssertionError(e2);
        }
    }

    private void c() {
        int i = this.f36d;
        long[] jArr = this.f34b;
        Object[] objArr = this.f35c;
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            Object obj = objArr[i3];
            if (obj != f32e) {
                if (i3 != i2) {
                    jArr[i2] = jArr[i3];
                    objArr[i2] = obj;
                    objArr[i3] = null;
                }
                i2++;
            }
        }
        this.f33a = false;
        this.f36d = i2;
    }

    public void a() {
        int i = this.f36d;
        Object[] objArr = this.f35c;
        for (int i2 = 0; i2 < i; i2++) {
            objArr[i2] = null;
        }
        this.f36d = 0;
        this.f33a = false;
    }

    public int b() {
        if (this.f33a) {
            c();
        }
        return this.f36d;
    }

    public E b(int i) {
        if (this.f33a) {
            c();
        }
        return (E) this.f35c[i];
    }

    public void a(long j, E e2) {
        int i = this.f36d;
        if (i != 0 && j <= this.f34b[i - 1]) {
            c(j, e2);
            return;
        }
        if (this.f33a && this.f36d >= this.f34b.length) {
            c();
        }
        int i2 = this.f36d;
        if (i2 >= this.f34b.length) {
            int iC = c.c(i2 + 1);
            long[] jArr = new long[iC];
            Object[] objArr = new Object[iC];
            long[] jArr2 = this.f34b;
            System.arraycopy(jArr2, 0, jArr, 0, jArr2.length);
            Object[] objArr2 = this.f35c;
            System.arraycopy(objArr2, 0, objArr, 0, objArr2.length);
            this.f34b = jArr;
            this.f35c = objArr;
        }
        this.f34b[i2] = j;
        this.f35c[i2] = e2;
        this.f36d = i2 + 1;
    }

    public void c(long j, E e2) {
        int iA = c.a(this.f34b, this.f36d, j);
        if (iA >= 0) {
            this.f35c[iA] = e2;
            return;
        }
        int iA2 = iA ^ (-1);
        if (iA2 < this.f36d) {
            Object[] objArr = this.f35c;
            if (objArr[iA2] == f32e) {
                this.f34b[iA2] = j;
                objArr[iA2] = e2;
                return;
            }
        }
        if (this.f33a && this.f36d >= this.f34b.length) {
            c();
            iA2 = c.a(this.f34b, this.f36d, j) ^ (-1);
        }
        int i = this.f36d;
        if (i >= this.f34b.length) {
            int iC = c.c(i + 1);
            long[] jArr = new long[iC];
            Object[] objArr2 = new Object[iC];
            long[] jArr2 = this.f34b;
            System.arraycopy(jArr2, 0, jArr, 0, jArr2.length);
            Object[] objArr3 = this.f35c;
            System.arraycopy(objArr3, 0, objArr2, 0, objArr3.length);
            this.f34b = jArr;
            this.f35c = objArr2;
        }
        int i2 = this.f36d;
        if (i2 - iA2 != 0) {
            long[] jArr3 = this.f34b;
            int i3 = iA2 + 1;
            System.arraycopy(jArr3, iA2, jArr3, i3, i2 - iA2);
            Object[] objArr4 = this.f35c;
            System.arraycopy(objArr4, iA2, objArr4, i3, this.f36d - iA2);
        }
        this.f34b[iA2] = j;
        this.f35c[iA2] = e2;
        this.f36d++;
    }
}
