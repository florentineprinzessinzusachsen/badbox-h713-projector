package a.c.a.j;

import java.util.ArrayList;

/* JADX INFO: compiled from: Snapshot.java */
/* JADX INFO: loaded from: classes.dex */
public class p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f163a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f164b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f165c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f166d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private ArrayList<a> f167e = new ArrayList<>();

    /* JADX INFO: compiled from: Snapshot.java */
    static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private e f168a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private e f169b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private int f170c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private e.c f171d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private int f172e;

        public a(e eVar) {
            this.f168a = eVar;
            this.f169b = eVar.g();
            this.f170c = eVar.b();
            this.f171d = eVar.f();
            this.f172e = eVar.a();
        }

        public void a(f fVar) {
            fVar.a(this.f168a.h()).a(this.f169b, this.f170c, this.f171d, this.f172e);
        }

        public void b(f fVar) {
            this.f168a = fVar.a(this.f168a.h());
            e eVar = this.f168a;
            if (eVar != null) {
                this.f169b = eVar.g();
                this.f170c = this.f168a.b();
                this.f171d = this.f168a.f();
                this.f172e = this.f168a.a();
                return;
            }
            this.f169b = null;
            this.f170c = 0;
            this.f171d = e.c.STRONG;
            this.f172e = 0;
        }
    }

    public p(f fVar) {
        this.f163a = fVar.v();
        this.f164b = fVar.w();
        this.f165c = fVar.s();
        this.f166d = fVar.i();
        ArrayList<e> arrayListB = fVar.b();
        int size = arrayListB.size();
        for (int i = 0; i < size; i++) {
            this.f167e.add(new a(arrayListB.get(i)));
        }
    }

    public void a(f fVar) {
        fVar.r(this.f163a);
        fVar.s(this.f164b);
        fVar.o(this.f165c);
        fVar.g(this.f166d);
        int size = this.f167e.size();
        for (int i = 0; i < size; i++) {
            this.f167e.get(i).a(fVar);
        }
    }

    public void b(f fVar) {
        this.f163a = fVar.v();
        this.f164b = fVar.w();
        this.f165c = fVar.s();
        this.f166d = fVar.i();
        int size = this.f167e.size();
        for (int i = 0; i < size; i++) {
            this.f167e.get(i).b(fVar);
        }
    }
}
