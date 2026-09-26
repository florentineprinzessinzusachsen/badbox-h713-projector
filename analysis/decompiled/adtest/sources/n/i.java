package n;

import android.app.Fragment;
import android.content.ComponentCallbacks2;
import android.os.Build;
import android.os.Bundle;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public class i extends Fragment {
    public final void a(c cVar) {
        f fVar;
        if (Build.VERSION.SDK_INT < 29) {
            ComponentCallbacks2 activity = getActivity();
            j2.i.d(activity, "getActivity(...)");
            j2.i.e(cVar, "event");
            if (!(activity instanceof e) || (fVar = (f) ((g) ((e) activity)).f1472a.f45e) == null) {
                return;
            }
            fVar.a(cVar);
        }
    }

    @Override // android.app.Fragment
    public final void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        a(c.ON_CREATE);
    }

    @Override // android.app.Fragment
    public final void onDestroy() {
        super.onDestroy();
        a(c.ON_DESTROY);
    }

    @Override // android.app.Fragment
    public final void onPause() {
        super.onPause();
        a(c.ON_PAUSE);
    }

    @Override // android.app.Fragment
    public final void onResume() {
        super.onResume();
        a(c.ON_RESUME);
    }

    @Override // android.app.Fragment
    public final void onStart() {
        super.onStart();
        a(c.ON_START);
    }

    @Override // android.app.Fragment
    public final void onStop() {
        super.onStop();
        a(c.ON_STOP);
    }
}
