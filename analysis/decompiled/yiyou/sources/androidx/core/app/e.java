package androidx.core.app;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import androidx.lifecycle.p;

/* JADX INFO: compiled from: ComponentActivity.java */
/* JADX INFO: loaded from: classes.dex */
public class e extends Activity implements androidx.lifecycle.h, androidx.core.f.d.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private androidx.lifecycle.i f924a;

    public e() {
        new a.b.g();
        this.f924a = new androidx.lifecycle.i(this);
    }

    @Override // androidx.core.f.d.a
    public boolean a(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        View decorView = getWindow().getDecorView();
        if (decorView == null || !androidx.core.f.d.a(decorView, keyEvent)) {
            return androidx.core.f.d.a(this, decorView, this, keyEvent);
        }
        return true;
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchKeyShortcutEvent(KeyEvent keyEvent) {
        View decorView = getWindow().getDecorView();
        if (decorView == null || !androidx.core.f.d.a(decorView, keyEvent)) {
            return super.dispatchKeyShortcutEvent(keyEvent);
        }
        return true;
    }

    @Override // android.app.Activity
    @SuppressLint({"RestrictedApi"})
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        p.a(this);
    }

    @Override // android.app.Activity
    protected void onSaveInstanceState(Bundle bundle) {
        this.f924a.a(androidx.lifecycle.e.b.CREATED);
        super.onSaveInstanceState(bundle);
    }
}
