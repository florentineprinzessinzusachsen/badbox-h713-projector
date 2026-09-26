package a.a.c.a;

import com.android.sysapp.R;

/* JADX INFO: loaded from: classes.dex */
public class i implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ g.c f43a;

    public i(g.c cVar) {
        this.f43a = cVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        g.this.l.setVisibility(4);
        g.this.n.setText(R.string.check_unknown);
    }
}
