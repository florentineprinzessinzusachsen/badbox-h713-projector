package a3;

import java.util.List;
import javax.net.ssl.SSLPeerUnverifiedException;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class n implements i2.a {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f189d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ i2.a f190e;

    public /* synthetic */ n(int i4, i2.a aVar) {
        this.f189d = i4;
        this.f190e = aVar;
    }

    @Override // i2.a
    public final Object a() {
        switch (this.f189d) {
            case 0:
                try {
                    return (List) this.f190e.a();
                } catch (SSLPeerUnverifiedException unused) {
                    return v1.p.f2517d;
                }
            default:
                this.f190e.a();
                return u1.k.f2301a;
        }
    }
}
