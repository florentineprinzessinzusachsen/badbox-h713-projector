package com.android.sysapp;

import a.a.b.e;
import a.a.b.f;
import a.a.c.a.a;
import a.a.c.a.b;
import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;

/* JADX INFO: loaded from: classes.dex */
public class OtaActivity extends Activity {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f54a = "";

    public final void a() {
        b bVar = new b(this, R.style.MyUpdateDialog);
        bVar.f = this.f54a;
        bVar.show();
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        this.f54a = getIntent().getStringExtra("path");
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            finish();
        }
        return super.onKeyDown(i, keyEvent);
    }

    @Override // android.app.Activity
    public void onResume() {
        super.onResume();
        a aVar = new a(this, getString(R.string.confirm_update), getString(R.string.confirm_cancel), R.layout.show_dialog, getString(R.string.message_6), getString(R.string.message_7));
        aVar.setCancelable(false);
        aVar.setOnDismissListener(new e(this));
        aVar.show();
        aVar.a(new f(this, aVar));
    }
}
