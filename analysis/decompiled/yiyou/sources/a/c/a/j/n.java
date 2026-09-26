package a.c.a.j;

/* JADX INFO: compiled from: ResolutionDimension.java */
/* JADX INFO: loaded from: classes.dex */
public class n extends o {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    float f160c = 0.0f;

    public void a(int i) {
        if (this.f162b == 0 || this.f160c != i) {
            this.f160c = i;
            if (this.f162b == 1) {
                b();
            }
            a();
        }
    }

    @Override // a.c.a.j.o
    public void d() {
        super.d();
        this.f160c = 0.0f;
    }

    public void f() {
        this.f162b = 2;
    }
}
