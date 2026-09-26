package k2;

import java.util.Random;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public abstract class a extends d {
    @Override // k2.d
    public final int a(int i4) {
        return ((-i4) >> 31) & (e().nextInt() >>> (32 - i4));
    }

    @Override // k2.d
    public final int b() {
        return e().nextInt();
    }

    @Override // k2.d
    public final long c() {
        return e().nextLong();
    }

    public abstract Random e();
}
