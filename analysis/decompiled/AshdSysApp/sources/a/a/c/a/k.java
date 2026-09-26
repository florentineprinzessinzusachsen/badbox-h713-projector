package a.a.c.a;

import com.android.sysapp.R;

/* JADX INFO: loaded from: classes.dex */
public class k implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ g.c f45a;

    public k(g.c cVar) {
        this.f45a = cVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        g.this.n.setText(R.string.net_error);
    }
}
