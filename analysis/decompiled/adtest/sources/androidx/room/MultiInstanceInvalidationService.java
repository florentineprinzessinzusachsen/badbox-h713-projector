package androidx.room;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import java.util.LinkedHashMap;
import p.i;
import p.j;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class MultiInstanceInvalidationService extends Service {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f302a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final LinkedHashMap f303b = new LinkedHashMap();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final j f304c = new j(this);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final i f305d = new i(this);

    @Override // android.app.Service
    public final IBinder onBind(Intent intent) {
        j2.i.e(intent, "intent");
        return this.f305d;
    }
}
