package androidx.savedstate;

import android.os.Bundle;
import androidx.lifecycle.e;

/* JADX INFO: compiled from: SavedStateRegistryController.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final b f1419a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final SavedStateRegistry f1420b = new SavedStateRegistry();

    private a(b bVar) {
        this.f1419a = bVar;
    }

    public SavedStateRegistry a() {
        return this.f1420b;
    }

    public void b(Bundle bundle) {
        this.f1420b.a(bundle);
    }

    public void a(Bundle bundle) {
        e eVarA = this.f1419a.a();
        if (eVarA.a() != e.b.INITIALIZED) {
            throw new IllegalStateException("Restarter must be created only during owner's initialization stage");
        }
        eVarA.a(new Recreator(this.f1419a));
        this.f1420b.a(eVarA, bundle);
    }

    public static a a(b bVar) {
        return new a(bVar);
    }
}
