package com.rk_itvui.settings.datetime;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.format.DateFormat;
import android.widget.TextView;
import com.ashd.settings.R;

/* JADX INFO: loaded from: classes.dex */
public class TimeActivity extends Activity {
    private Handler mHandler = new Handler() { // from class: com.rk_itvui.settings.datetime.TimeActivity.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what != 1) {
                return;
            }
            TimeActivity.this.sysTime = System.currentTimeMillis();
            TimeActivity.this.tvTime.setText(DateFormat.format("hh:mm:ss", TimeActivity.this.sysTime));
        }
    };
    long sysTime;
    private TextView tvTime;

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.date_time_setting);
        this.tvTime = (TextView) findViewById(R.id.mytime);
        new TimeThread().start();
    }

    class TimeThread extends Thread {
        TimeThread() {
        }

        /* JADX INFO: Infinite loop detected, blocks: 9, insns: 0 */
        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            while (true) {
                try {
                    Thread.sleep(1000L);
                    Message message = new Message();
                    message.what = 1;
                    TimeActivity.this.mHandler.sendMessage(message);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
