package androidx.savedstate;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.lifecycle.d;
import androidx.lifecycle.e;
import androidx.lifecycle.h;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"RestrictedApi"})
public final class SavedStateRegistry {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private a.a.a.b.b<String, b> f1414a = new a.a.a.b.b<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Bundle f1415b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f1416c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    boolean f1417d;

    public interface a {
        void a(androidx.savedstate.b bVar);
    }

    public interface b {
        Bundle a();
    }

    SavedStateRegistry() {
    }

    public Bundle a(String str) {
        if (!this.f1416c) {
            throw new IllegalStateException("You can consumeRestoredStateForKey only after super.onCreate of corresponding component");
        }
        Bundle bundle = this.f1415b;
        if (bundle == null) {
            return null;
        }
        Bundle bundle2 = bundle.getBundle(str);
        this.f1415b.remove(str);
        if (this.f1415b.isEmpty()) {
            this.f1415b = null;
        }
        return bundle2;
    }

    void a(e eVar, Bundle bundle) {
        if (!this.f1416c) {
            if (bundle != null) {
                this.f1415b = bundle.getBundle("androidx.lifecycle.BundlableSavedStateRegistry.key");
            }
            eVar.a(new d() { // from class: androidx.savedstate.SavedStateRegistry.1
                @Override // androidx.lifecycle.f
                public void a(h hVar, e.a aVar) {
                    if (aVar == e.a.ON_START) {
                        SavedStateRegistry.this.f1417d = true;
                    } else if (aVar == e.a.ON_STOP) {
                        SavedStateRegistry.this.f1417d = false;
                    }
                }
            });
            this.f1416c = true;
            return;
        }
        throw new IllegalStateException("SavedStateRegistry was already restored.");
    }

    void a(Bundle bundle) {
        Bundle bundle2 = new Bundle();
        Bundle bundle3 = this.f1415b;
        if (bundle3 != null) {
            bundle2.putAll(bundle3);
        }
        a.a.a.b.b<String, b>.d dVarB = this.f1414a.b();
        while (dVarB.hasNext()) {
            Map.Entry next = dVarB.next();
            bundle2.putBundle((String) next.getKey(), ((b) next.getValue()).a());
        }
        bundle.putBundle("androidx.lifecycle.BundlableSavedStateRegistry.key", bundle2);
    }
}
