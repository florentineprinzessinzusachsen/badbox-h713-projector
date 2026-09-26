package ddth2;

/* JADX INFO: loaded from: classes.dex */
public class o2 {
    public int a;

    /* JADX INFO: renamed from: a, reason: collision with other field name */
    public q2 f88a;

    /* JADX INFO: renamed from: a, reason: collision with other field name */
    public String f89a;

    public o2(int i, String str) {
        this.a = i;
        this.f89a = str;
    }

    public q2 a() {
        return this.f88a;
    }

    /* JADX INFO: renamed from: a, reason: collision with other method in class */
    public String m66a() {
        return this.f89a;
    }

    public void a(q2 q2Var) {
        this.f88a = q2Var;
    }

    /* JADX INFO: renamed from: a, reason: collision with other method in class */
    public boolean m67a() {
        return this.a == 0;
    }
}
