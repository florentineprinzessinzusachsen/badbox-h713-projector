package c.a.d0;

import c.a.b0.j.s;
import c.a.d0.a;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/* JADX INFO: compiled from: BaseTestConsumer.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class a<T, U extends a<T, U>> implements c.a.y.b {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    protected long f3112d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    protected boolean f3113e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    protected int f3114f;
    protected int g;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected final List<T> f3110b = new s();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    protected final List<Throwable> f3111c = new s();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected final CountDownLatch f3109a = new CountDownLatch(1);
}
