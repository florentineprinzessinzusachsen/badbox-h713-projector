package p2;

import java.util.Iterator;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class c implements o2.c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final CharSequence f1749a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f1750b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final i2.p f1751c;

    public c(CharSequence charSequence, int i4, i2.p pVar) {
        j2.i.e(charSequence, "input");
        this.f1749a = charSequence;
        this.f1750b = i4;
        this.f1751c = pVar;
    }

    @Override // o2.c
    public final Iterator iterator() {
        return new b(this);
    }
}
