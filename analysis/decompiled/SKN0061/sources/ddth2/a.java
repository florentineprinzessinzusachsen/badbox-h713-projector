package ddth2;

/* JADX INFO: loaded from: classes.dex */
public class a {
    public static a a;

    /* JADX INFO: renamed from: a, reason: collision with other field name */
    public e f0a;

    /* JADX INFO: renamed from: a, reason: collision with other field name */
    public f f1a = new f();

    public void a() {
        if (this.f0a == null) {
            throw new IllegalStateException("SDK not init，please call init() first");
        }
        this.f1a.c();
    }

    public void a(e eVar) {
        this.f0a = eVar;
        this.f1a.a(eVar);
    }

    /* JADX INFO: renamed from: a, reason: collision with other method in class */
    public boolean m0a() {
        return this.f1a.m49a();
    }

    public void b() {
        this.f1a.d();
    }
}
