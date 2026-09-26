package d0;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public abstract class m0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final UUID f480a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final l0.p f481b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Set f482c;

    public m0(UUID uuid, l0.p pVar, LinkedHashSet linkedHashSet) {
        j2.i.e(uuid, "id");
        j2.i.e(pVar, "workSpec");
        j2.i.e(linkedHashSet, "tags");
        this.f480a = uuid;
        this.f481b = pVar;
        this.f482c = linkedHashSet;
    }
}
