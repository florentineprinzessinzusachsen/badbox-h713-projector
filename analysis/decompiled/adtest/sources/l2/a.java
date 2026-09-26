package l2;

import j2.i;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class a extends k2.a {
    @Override // k2.d
    public final long d(long j4, long j5) {
        return ThreadLocalRandom.current().nextLong(j4, j5);
    }

    @Override // k2.a
    public final Random e() {
        ThreadLocalRandom threadLocalRandomCurrent = ThreadLocalRandom.current();
        i.d(threadLocalRandomCurrent, "current(...)");
        return threadLocalRandomCurrent;
    }
}
