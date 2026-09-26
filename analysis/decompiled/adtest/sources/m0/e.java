package m0;

import androidx.work.impl.WorkDatabase;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final WorkDatabase f1404a;

    public e(WorkDatabase workDatabase, int i4) {
        switch (i4) {
            case 1:
                this.f1404a = workDatabase;
                break;
            default:
                j2.i.e(workDatabase, "workDatabase");
                this.f1404a = workDatabase;
                break;
        }
    }
}
