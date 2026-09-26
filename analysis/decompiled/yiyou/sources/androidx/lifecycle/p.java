package androidx.lifecycle;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.ComponentCallbacks2;
import android.os.Bundle;

/* JADX INFO: compiled from: ReportFragment.java */
/* JADX INFO: loaded from: classes.dex */
public class p extends Fragment {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private a f1403a;

    /* JADX INFO: compiled from: ReportFragment.java */
    interface a {
        void a();

        void b();

        void onStart();
    }

    public static void a(Activity activity) {
        FragmentManager fragmentManager = activity.getFragmentManager();
        if (fragmentManager.findFragmentByTag("androidx.lifecycle.LifecycleDispatcher.report_fragment_tag") == null) {
            fragmentManager.beginTransaction().add(new p(), "androidx.lifecycle.LifecycleDispatcher.report_fragment_tag").commit();
            fragmentManager.executePendingTransactions();
        }
    }

    private void b(a aVar) {
        if (aVar != null) {
            aVar.b();
        }
    }

    private void c(a aVar) {
        if (aVar != null) {
            aVar.onStart();
        }
    }

    @Override // android.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        a(this.f1403a);
        a(e.a.ON_CREATE);
    }

    @Override // android.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        a(e.a.ON_DESTROY);
        this.f1403a = null;
    }

    @Override // android.app.Fragment
    public void onPause() {
        super.onPause();
        a(e.a.ON_PAUSE);
    }

    @Override // android.app.Fragment
    public void onResume() {
        super.onResume();
        b(this.f1403a);
        a(e.a.ON_RESUME);
    }

    @Override // android.app.Fragment
    public void onStart() {
        super.onStart();
        c(this.f1403a);
        a(e.a.ON_START);
    }

    @Override // android.app.Fragment
    public void onStop() {
        super.onStop();
        a(e.a.ON_STOP);
    }

    private void a(a aVar) {
        if (aVar != null) {
            aVar.a();
        }
    }

    private void a(e.a aVar) {
        ComponentCallbacks2 activity = getActivity();
        if (activity instanceof j) {
            ((j) activity).a().a(aVar);
        } else if (activity instanceof h) {
            e eVarA = ((h) activity).a();
            if (eVarA instanceof i) {
                ((i) eVarA).a(aVar);
            }
        }
    }
}
