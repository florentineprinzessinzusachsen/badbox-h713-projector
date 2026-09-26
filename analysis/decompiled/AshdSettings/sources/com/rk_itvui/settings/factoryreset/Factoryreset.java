package com.rk_itvui.settings.factoryreset;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.RecoverySystem;
import android.view.View;
import android.widget.TextView;
import com.ashd.settings.R;
import com.rk_itvui.settings.BuildConfig;
import com.rk_itvui.settings.FullScreenActivity;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class Factoryreset extends FullScreenActivity {
    @Override // com.rk_itvui.settings.FullScreenActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.factoryreset_setting);
        if (BuildConfig.FLAVOR.contains(BuildConfig.FLAVOR)) {
            TextView textView = (TextView) findViewById(R.id.app_text);
            TextView textView2 = (TextView) findViewById(R.id.settings_line);
            textView.setCompoundDrawablesWithIntrinsicBounds(getDrawable(R.drawable.icon_ashd2), (Drawable) null, (Drawable) null, (Drawable) null);
            textView2.setBackgroundResource(R.drawable.settings_head_line_ashd2);
        }
    }

    private void doMasterClear() {
        new Thread(new Runnable() { // from class: com.rk_itvui.settings.factoryreset.Factoryreset.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    RecoverySystem.rebootWipeUserData(Factoryreset.this.getApplicationContext());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.resetcancel /* 2131362267 */:
                finish();
                break;
            case R.id.resetsure /* 2131362268 */:
                doMasterClear();
                break;
        }
    }
}
