package p;

import android.os.IInterface;
import android.os.RemoteCallbackList;
import androidx.room.MultiInstanceInvalidationService;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class j extends RemoteCallbackList {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ MultiInstanceInvalidationService f1675a;

    public j(MultiInstanceInvalidationService multiInstanceInvalidationService) {
        this.f1675a = multiInstanceInvalidationService;
    }

    @Override // android.os.RemoteCallbackList
    public final void onCallbackDied(IInterface iInterface, Object obj) {
        j2.i.e((d) iInterface, "callback");
        j2.i.e(obj, "cookie");
        this.f1675a.f303b.remove((Integer) obj);
    }
}
