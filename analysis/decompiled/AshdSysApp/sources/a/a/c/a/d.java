package a.a.c.a;

import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class d implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f25a = false;

    public void a() {
        throw null;
    }

    public void b() {
        Log.i("QueryThread", " QueryThread start");
        this.f25a = false;
        new Thread(this).start();
    }

    @Override // java.lang.Runnable
    public void run() {
        while (!this.f25a) {
            try {
                Thread.sleep(500L);
            } catch (Exception e) {
                e.printStackTrace();
            }
            a();
        }
    }
}
