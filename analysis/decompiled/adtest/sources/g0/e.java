package g0;

import android.content.ComponentName;
import android.content.Context;
import androidx.work.impl.background.systemjob.SystemJobService;
import d0.a0;
import d0.l;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class e {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final String f947d = a0.g("SystemJobInfoConverter");

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ComponentName f948a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final l f949b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final boolean f950c;

    public e(Context context, l lVar, boolean z3) {
        this.f949b = lVar;
        this.f948a = new ComponentName(context.getApplicationContext(), (Class<?>) SystemJobService.class);
        this.f950c = z3;
    }
}
