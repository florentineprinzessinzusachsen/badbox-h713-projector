package ddth2;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class n2 {
    public int a;

    /* JADX INFO: renamed from: a, reason: collision with other field name */
    public String f86a;

    /* JADX INFO: renamed from: a, reason: collision with other field name */
    public List<m2> f87a;

    public n2(int i, String str) {
        this.a = i;
        this.f86a = str;
    }

    public String a() {
        return this.f86a;
    }

    /* JADX INFO: renamed from: a, reason: collision with other method in class */
    public List<m2> m64a() {
        return this.f87a;
    }

    public void a(List<m2> list) {
        this.f87a = list;
    }

    /* JADX INFO: renamed from: a, reason: collision with other method in class */
    public boolean m65a() {
        return this.a == 0;
    }
}
