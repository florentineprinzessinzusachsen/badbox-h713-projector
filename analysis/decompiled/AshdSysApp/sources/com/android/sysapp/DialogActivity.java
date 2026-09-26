package com.android.sysapp;

import a.a.b.b;
import a.a.c.a.a;
import android.app.Activity;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public class DialogActivity extends Activity implements View.OnClickListener {
    public boolean a(String str) {
        Iterator<PackageInfo> it = getPackageManager().getInstalledPackages(128).iterator();
        while (it.hasNext()) {
            if (it.next().packageName.equals(str)) {
                return true;
            }
        }
        return false;
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
        Log.e("DialogActivity", "onBackPressed");
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        a aVar = new a(this, getResources().getString(R.string.sure), getResources().getString(R.string.cancel), R.layout.show_dialog, getResources().getString(R.string.USB_disk_insertion_detected), getResources().getString(R.string.Open_file_manager_to_view), true);
        aVar.setCancelable(false);
        aVar.show();
        aVar.a(new b(this, aVar));
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (keyEvent.getAction() == 1) {
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (i != 4) {
            return super.onKeyUp(i, keyEvent);
        }
        finish();
        return true;
    }
}
