package com.android.sysapp;

import a.a.b.m;
import a.a.c.a.b;
import a.a.c.a.d;
import a.a.c.a.g;
import android.app.Activity;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class UpdateActivity extends Activity {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public g f55a;

    @Override // android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        Log.i("CMUpdate2Activity", "onActivityResult !!!");
        if (intent != null) {
            String string = intent.getExtras().getString("file");
            Log.i("CMUpdate2Activity", "file =" + string);
            if (string != null) {
                b bVar = new b(this, R.style.MyUpdateDialog_Dim_on);
                bVar.f = string;
                bVar.show();
            }
        }
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Log.i("CMUpdate2Activity", "onCreat()!");
        UpdateService.u = false;
        m.c();
        getWindow().getDecorView().setSystemUiVisibility(6);
        setContentView(R.layout.main);
        this.f55a = new g(this, findViewById(R.id.root));
        Log.i("SW", "smallest width : " + getResources().getConfiguration().smallestScreenWidthDp);
    }

    @Override // android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        g gVar = this.f55a;
        Log.i(gVar.b, "onDestroy() ");
        d[] dVarArr = gVar.q;
        dVarArr[0].f25a = true;
        dVarArr[1].f25a = true;
        ServiceConnection serviceConnection = gVar.v;
        if (serviceConnection != null) {
            gVar.f29a.unbindService(serviceConnection);
        }
        Log.i("CMUpdate2Activity", "onDestroy()");
    }

    @Override // android.app.Activity
    public void onPause() {
        super.onPause();
        this.f55a.b();
        Log.i("CMUpdate2Activity", "onPause()");
    }

    @Override // android.app.Activity
    public void onResume() {
        super.onResume();
        Log.i("CMUpdate2Activity", "onResume()");
        this.f55a.c();
        Log.i("CMUpdate2Activity", "onResume()2");
    }
}
