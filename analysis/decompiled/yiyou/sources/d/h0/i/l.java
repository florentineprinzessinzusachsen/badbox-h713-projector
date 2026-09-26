package d.h0.i;

import java.util.List;

/* JADX INFO: compiled from: PushObserver.java */
/* JADX INFO: loaded from: classes.dex */
public interface l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final l f4578a = new a();

    /* JADX INFO: compiled from: PushObserver.java */
    class a implements l {
        a() {
        }

        @Override // d.h0.i.l
        public void a(int i, b bVar) {
        }

        @Override // d.h0.i.l
        public boolean a(int i, e.e eVar, int i2, boolean z) {
            eVar.skip(i2);
            return true;
        }

        @Override // d.h0.i.l
        public boolean a(int i, List<c> list) {
            return true;
        }

        @Override // d.h0.i.l
        public boolean a(int i, List<c> list, boolean z) {
            return true;
        }
    }

    void a(int i, b bVar);

    boolean a(int i, e.e eVar, int i2, boolean z);

    boolean a(int i, List<c> list);

    boolean a(int i, List<c> list, boolean z);
}
