package e0;

import android.content.Context;
import android.content.SharedPreferences;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class g extends t.a {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ int f624c = 1;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Context f625d;

    public g(Context context, int i4, int i5) {
        super(i4, i5);
        this.f625d = context;
    }

    @Override // t.a
    public final void b(x.a aVar) {
        int i4 = this.f624c;
        Context context = this.f625d;
        j2.i.e(aVar, "db");
        switch (i4) {
            case 0:
                if (this.f2150b >= 10) {
                    aVar.w(new Object[]{"reschedule_needed", 1});
                    return;
                } else {
                    context.getSharedPreferences("androidx.work.util.preferences", 0).edit().putBoolean("reschedule_needed", true).apply();
                    return;
                }
            default:
                aVar.s("CREATE TABLE IF NOT EXISTS `Preference` (`key` TEXT NOT NULL, `long_value` INTEGER, PRIMARY KEY(`key`))");
                SharedPreferences sharedPreferences = context.getSharedPreferences("androidx.work.util.preferences", 0);
                if (sharedPreferences.contains("reschedule_needed") || sharedPreferences.contains("last_cancel_all_time_ms")) {
                    long j4 = sharedPreferences.getLong("last_cancel_all_time_ms", 0L);
                    long j5 = sharedPreferences.getBoolean("reschedule_needed", false) ? 1L : 0L;
                    aVar.i();
                    try {
                        aVar.w(new Object[]{"last_cancel_all_time_ms", Long.valueOf(j4)});
                        aVar.w(new Object[]{"reschedule_needed", Long.valueOf(j5)});
                        sharedPreferences.edit().clear().apply();
                        aVar.y();
                        aVar.h();
                    } catch (Throwable th) {
                        aVar.h();
                        throw th;
                    }
                }
                SharedPreferences sharedPreferences2 = context.getSharedPreferences("androidx.work.util.id", 0);
                if (sharedPreferences2.contains("next_job_scheduler_id") || sharedPreferences2.contains("next_job_scheduler_id")) {
                    int i5 = sharedPreferences2.getInt("next_job_scheduler_id", 0);
                    int i6 = sharedPreferences2.getInt("next_alarm_manager_id", 0);
                    aVar.i();
                    try {
                        aVar.w(new Object[]{"next_job_scheduler_id", Integer.valueOf(i5)});
                        aVar.w(new Object[]{"next_alarm_manager_id", Integer.valueOf(i6)});
                        sharedPreferences2.edit().clear().apply();
                        aVar.y();
                        return;
                    } finally {
                        aVar.h();
                    }
                }
                return;
        }
    }

    public g(Context context) {
        super(9, 10);
        this.f625d = context;
    }
}
