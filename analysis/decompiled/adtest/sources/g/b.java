package g;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class b extends Throwable {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f915d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ b(int i4, String str) {
        super(str);
        this.f915d = i4;
    }

    private final synchronized Throwable a() {
        return this;
    }

    private final synchronized Throwable b() {
        return this;
    }

    @Override // java.lang.Throwable
    public final synchronized Throwable fillInStackTrace() {
        switch (this.f915d) {
            case 0:
                a();
                break;
            default:
                b();
                break;
        }
        return this;
    }
}
