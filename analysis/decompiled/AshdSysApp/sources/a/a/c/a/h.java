package a.a.c.a;

import com.android.sysapp.R;

/* JADX INFO: loaded from: classes.dex */
public class h implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ g.c f42a;

    public h(g.c cVar) {
        this.f42a = cVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        g.this.l.setVisibility(8);
        g.this.m.setVisibility(0);
        g.this.n.setText(R.string.check_failed);
    }
}
