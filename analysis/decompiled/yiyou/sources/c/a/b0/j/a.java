package c.a.b0.j;

/* JADX INFO: compiled from: AppendOnlyLinkedArrayList.java */
/* JADX INFO: loaded from: classes.dex */
public class a<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final int f3078a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final Object[] f3079b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    Object[] f3080c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    int f3081d;

    /* JADX INFO: renamed from: c.a.b0.j.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: AppendOnlyLinkedArrayList.java */
    public interface InterfaceC0074a<T> extends c.a.a0.p<T> {
        @Override // c.a.a0.p
        boolean a(T t);
    }

    public a(int i) {
        this.f3078a = i;
        this.f3079b = new Object[i + 1];
        this.f3080c = this.f3079b;
    }

    public void a(T t) {
        int i = this.f3078a;
        int i2 = this.f3081d;
        if (i2 == i) {
            Object[] objArr = new Object[i + 1];
            this.f3080c[i] = objArr;
            this.f3080c = objArr;
            i2 = 0;
        }
        this.f3080c[i2] = t;
        this.f3081d = i2 + 1;
    }

    public void b(T t) {
        this.f3079b[0] = t;
    }

    public void a(InterfaceC0074a<? super T> interfaceC0074a) {
        int i = this.f3078a;
        for (Object[] objArr = this.f3079b; objArr != null; objArr = (Object[]) objArr[i]) {
            for (int i2 = 0; i2 < i; i2++) {
                Object obj = objArr[i2];
                if (obj == null) {
                    break;
                } else {
                    if (interfaceC0074a.a(obj)) {
                        return;
                    }
                }
            }
        }
    }

    public <U> boolean a(c.a.s<? super U> sVar) {
        Object[] objArr = this.f3079b;
        int i = this.f3078a;
        while (true) {
            if (objArr == null) {
                return false;
            }
            for (int i2 = 0; i2 < i; i2++) {
                Object[] objArr2 = objArr[i2];
                if (objArr2 == null) {
                    break;
                }
                if (n.b(objArr2, sVar)) {
                    return true;
                }
            }
            objArr = objArr[i];
        }
    }
}
