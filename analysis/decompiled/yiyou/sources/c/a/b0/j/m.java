package c.a.b0.j;

import java.util.ArrayList;

/* JADX INFO: compiled from: LinkedArrayList.java */
/* JADX INFO: loaded from: classes.dex */
public class m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final int f3094a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    Object[] f3095b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    Object[] f3096c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    volatile int f3097d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    int f3098e;

    public m(int i) {
        this.f3094a = i;
    }

    public void a(Object obj) {
        if (this.f3097d == 0) {
            this.f3095b = new Object[this.f3094a + 1];
            Object[] objArr = this.f3095b;
            this.f3096c = objArr;
            objArr[0] = obj;
            this.f3098e = 1;
            this.f3097d = 1;
            return;
        }
        int i = this.f3098e;
        int i2 = this.f3094a;
        if (i != i2) {
            this.f3096c[i] = obj;
            this.f3098e = i + 1;
            this.f3097d++;
        } else {
            Object[] objArr2 = new Object[i2 + 1];
            objArr2[0] = obj;
            this.f3096c[i2] = objArr2;
            this.f3096c = objArr2;
            this.f3098e = 1;
            this.f3097d++;
        }
    }

    public int b() {
        return this.f3097d;
    }

    public String toString() {
        int i = this.f3094a;
        int i2 = this.f3097d;
        ArrayList arrayList = new ArrayList(i2 + 1);
        Object[] objArrA = a();
        int i3 = 0;
        while (true) {
            int i4 = 0;
            while (i3 < i2) {
                arrayList.add(objArrA[i4]);
                i3++;
                i4++;
                if (i4 == i) {
                    objArrA = objArrA[i];
                }
            }
            return arrayList.toString();
        }
    }

    public Object[] a() {
        return this.f3095b;
    }
}
