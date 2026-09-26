package s0;

import java.io.IOException;
import v0.b1;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public abstract class q {
    public final u a() {
        if (this instanceof u) {
            return (u) this;
        }
        throw new IllegalStateException("Not a JSON Primitive: " + this);
    }

    public String b() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public final String toString() {
        try {
            StringBuilder sb = new StringBuilder();
            a1.d dVar = new a1.d(new u0.s(sb));
            dVar.f36k = 1;
            b1.f2448z.getClass();
            v0.m.e(dVar, this);
            return sb.toString();
        } catch (IOException e4) {
            throw new AssertionError(e4);
        }
    }
}
