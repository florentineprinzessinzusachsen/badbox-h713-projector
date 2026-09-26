package com.rk_itvui.settings;

import android.annotation.TargetApi;
import android.app.Activity;
import android.hardware.audio.common.V2_0.AudioFormat;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public class FullScreenActivity extends Activity {
    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        getWindow().addFlags(Integer.MIN_VALUE);
        getWindow().addFlags(67108864);
        requestWindowFeature(1);
        super.onCreate(bundle);
        hideSystemUI();
    }

    /* JADX INFO: Access modifiers changed from: private */
    @TargetApi(19)
    public void hideSystemUI() {
        if (Build.VERSION.SDK_INT >= 19) {
            getWindow().setFlags(AudioFormat.OPUS, AudioFormat.OPUS);
            getWindow().setFlags(67108864, 67108864);
            getWindow().getDecorView().setSystemUiVisibility(5894);
            getWindow().getDecorView().setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() { // from class: com.rk_itvui.settings.FullScreenActivity.1
                @Override // android.view.View.OnSystemUiVisibilityChangeListener
                public void onSystemUiVisibilityChange(int i) {
                    new Handler().postDelayed(new Runnable() { // from class: com.rk_itvui.settings.FullScreenActivity.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            FullScreenActivity.this.hideSystemUI();
                        }
                    }, 1000L);
                }
            });
        }
    }
}
