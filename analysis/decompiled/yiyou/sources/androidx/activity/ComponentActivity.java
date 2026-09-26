package androidx.activity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import androidx.core.app.e;
import androidx.lifecycle.f;
import androidx.lifecycle.h;
import androidx.lifecycle.i;
import androidx.lifecycle.p;
import androidx.lifecycle.s;
import androidx.lifecycle.t;
import androidx.savedstate.SavedStateRegistry;

/* JADX INFO: loaded from: classes.dex */
public class ComponentActivity extends e implements h, t, androidx.savedstate.b, c {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private s f189d;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private int f191f;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final i f187b = new i(this);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final androidx.savedstate.a f188c = androidx.savedstate.a.a(this);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final OnBackPressedDispatcher f190e = new OnBackPressedDispatcher(new a());

    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ComponentActivity.super.onBackPressed();
        }
    }

    static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        s f195a;

        b() {
        }
    }

    public ComponentActivity() {
        if (a() == null) {
            throw new IllegalStateException("getLifecycle() returned null in ComponentActivity's constructor. Please make sure you are lazily constructing your Lifecycle in the first call to getLifecycle() rather than relying on field initialization.");
        }
        if (Build.VERSION.SDK_INT >= 19) {
            a().a(new f() { // from class: androidx.activity.ComponentActivity.2
                @Override // androidx.lifecycle.f
                public void a(h hVar, androidx.lifecycle.e.a aVar) {
                    if (aVar == androidx.lifecycle.e.a.ON_STOP) {
                        Window window = ComponentActivity.this.getWindow();
                        View viewPeekDecorView = window != null ? window.peekDecorView() : null;
                        if (viewPeekDecorView != null) {
                            viewPeekDecorView.cancelPendingInputEvents();
                        }
                    }
                }
            });
        }
        a().a(new f() { // from class: androidx.activity.ComponentActivity.3
            @Override // androidx.lifecycle.f
            public void a(h hVar, androidx.lifecycle.e.a aVar) {
                if (aVar != androidx.lifecycle.e.a.ON_DESTROY || ComponentActivity.this.isChangingConfigurations()) {
                    return;
                }
                ComponentActivity.this.d().a();
            }
        });
        int i = Build.VERSION.SDK_INT;
        if (19 > i || i > 23) {
            return;
        }
        a().a(new ImmLeaksCleaner(this));
    }

    @Override // androidx.activity.c
    public final OnBackPressedDispatcher b() {
        return this.f190e;
    }

    @Override // androidx.savedstate.b
    public final SavedStateRegistry c() {
        return this.f188c.a();
    }

    @Override // androidx.lifecycle.t
    public s d() {
        if (getApplication() == null) {
            throw new IllegalStateException("Your activity is not yet attached to the Application instance. You can't request ViewModel before onCreate call.");
        }
        if (this.f189d == null) {
            b bVar = (b) getLastNonConfigurationInstance();
            if (bVar != null) {
                this.f189d = bVar.f195a;
            }
            if (this.f189d == null) {
                this.f189d = new s();
            }
        }
        return this.f189d;
    }

    @Deprecated
    public Object f() {
        return null;
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        this.f190e.a();
    }

    @Override // androidx.core.app.e, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f188c.a(bundle);
        p.a(this);
        int i = this.f191f;
        if (i != 0) {
            setContentView(i);
        }
    }

    @Override // android.app.Activity
    public final Object onRetainNonConfigurationInstance() {
        b bVar;
        Object objF = f();
        s sVar = this.f189d;
        if (sVar == null && (bVar = (b) getLastNonConfigurationInstance()) != null) {
            sVar = bVar.f195a;
        }
        if (sVar == null && objF == null) {
            return null;
        }
        b bVar2 = new b();
        bVar2.f195a = sVar;
        return bVar2;
    }

    @Override // androidx.core.app.e, android.app.Activity
    protected void onSaveInstanceState(Bundle bundle) {
        androidx.lifecycle.e eVarA = a();
        if (eVarA instanceof i) {
            ((i) eVarA).b(androidx.lifecycle.e.b.CREATED);
        }
        super.onSaveInstanceState(bundle);
        this.f188c.b(bundle);
    }

    @Override // androidx.lifecycle.h
    public androidx.lifecycle.e a() {
        return this.f187b;
    }
}
