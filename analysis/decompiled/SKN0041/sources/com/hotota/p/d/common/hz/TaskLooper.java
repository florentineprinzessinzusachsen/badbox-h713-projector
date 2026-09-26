package com.hotota.p.d.common.hz;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public class TaskLooper {
    private static final String ALARM_ACTION = "com.action.alarm";
    private static final long LOOP_INTERVAL_TIME_ALARM = 80000;
    private static final long LOOP_INTERVAL_TIME_HANDLER = 30000;
    private static final long LOOP_INTERVAL_TIME_TIMER = 30000;
    private static final long LOOP_TIME = 900000;
    private static final int MSG_WHAT_LOOP_TASK = 1001;
    private static volatile TaskLooper taskLooper;
    private final HandlerThread handlerThread;
    private boolean isLooping;
    private Handler loopHandler;
    private AlarmManager mAlarmManager;
    private final String mChannel;
    private final Context mContext;
    private ScheduledExecutorService mExecutorService;
    private LoopRunnable mHandlerLoopRunnable;
    private PendingIntent mPendingIntent;
    private long latestLoopTime = 0;
    private final BroadcastReceiver loopReceiver = new BroadcastReceiver() { // from class: com.hotota.p.d.common.hz.TaskLooper.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent != null ? intent.getAction() : null;
            if (TextUtils.isEmpty(action) || !action.equals(TaskLooper.ALARM_ACTION)) {
                TaskLooper.this.resetLoopTime();
            } else {
                TaskLooper.this.loopHandler.sendEmptyMessage(TaskLooper.MSG_WHAT_LOOP_TASK);
            }
        }
    };

    private TaskLooper(Context context, String str) {
        this.mContext = context;
        this.mChannel = str;
        HandlerThread handlerThread = new HandlerThread("lp-handler");
        this.handlerThread = handlerThread;
        handlerThread.start();
        this.loopHandler = new LoopHandler(handlerThread.getLooper());
        ScheduledExecutorService scheduledExecutorService = this.mExecutorService;
        if (scheduledExecutorService == null || scheduledExecutorService.isShutdown()) {
            this.mExecutorService = Executors.newSingleThreadScheduledExecutor();
        }
        this.mAlarmManager = (AlarmManager) context.getSystemService("alarm");
        Intent intent = new Intent();
        intent.setAction(ALARM_ACTION);
        this.mPendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
    }

    public static TaskLooper getInstance(Context context, String str) {
        if (taskLooper == null) {
            synchronized (TaskLooper.class) {
                if (taskLooper == null) {
                    taskLooper = new TaskLooper(context, str);
                }
            }
        }
        return taskLooper;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loopTask() {
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (jCurrentTimeMillis - this.latestLoopTime > LOOP_TIME) {
            this.latestLoopTime = jCurrentTimeMillis;
        }
    }

    public void startLoop() {
        synchronized (TaskLooper.class) {
            if (this.isLooping) {
                return;
            }
            stopLoop();
            if (this.mHandlerLoopRunnable == null) {
                this.mHandlerLoopRunnable = new LoopRunnable("handler");
            }
            this.loopHandler.post(this.mHandlerLoopRunnable);
            LoopRunnable loopRunnable = new LoopRunnable("timer");
            ScheduledExecutorService scheduledExecutorService = this.mExecutorService;
            if (scheduledExecutorService == null || scheduledExecutorService.isShutdown()) {
                this.mExecutorService = Executors.newSingleThreadScheduledExecutor();
            }
            this.mExecutorService.scheduleAtFixedRate(loopRunnable, 15000L, 30000L, TimeUnit.MILLISECONDS);
            registerComponent(this.mContext);
            startAlarm(this.mContext);
            this.isLooping = true;
        }
    }

    private void startAlarm(Context context) {
        AlarmManager alarmManager = this.mAlarmManager;
        if (alarmManager == null || this.mPendingIntent == null) {
            return;
        }
        alarmManager.setRepeating(3, SystemClock.elapsedRealtime() + LOOP_INTERVAL_TIME_ALARM, LOOP_INTERVAL_TIME_ALARM, this.mPendingIntent);
    }

    private void registerComponent(Context context) {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ALARM_ACTION);
        intentFilter.addAction("android.intent.action.TIMEZONE_CHANGED");
        intentFilter.addAction("android.intent.action.TIME_SET");
        context.registerReceiver(this.loopReceiver, intentFilter);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resetLoopTime() {
        this.latestLoopTime = System.currentTimeMillis();
    }

    public void stopLoop() {
        synchronized (TaskLooper.class) {
            this.isLooping = false;
            this.latestLoopTime = 0L;
        }
        Handler handler = this.loopHandler;
        if (handler != null) {
            handler.removeMessages(MSG_WHAT_LOOP_TASK);
            LoopRunnable loopRunnable = this.mHandlerLoopRunnable;
            if (loopRunnable != null) {
                this.loopHandler.removeCallbacks(loopRunnable);
            }
        }
        ScheduledExecutorService scheduledExecutorService = this.mExecutorService;
        if (scheduledExecutorService != null) {
            scheduledExecutorService.shutdown();
        }
        unRegisterComponent(this.mContext);
        cancelAlarm();
    }

    private void cancelAlarm() {
        PendingIntent pendingIntent;
        AlarmManager alarmManager = this.mAlarmManager;
        if (alarmManager == null || (pendingIntent = this.mPendingIntent) == null) {
            return;
        }
        try {
            alarmManager.cancel(pendingIntent);
        } catch (Exception unused) {
        }
    }

    private void unRegisterComponent(Context context) {
        try {
            context.unregisterReceiver(this.loopReceiver);
        } catch (Exception unused) {
        }
    }

    private final class LoopHandler extends Handler {
        public LoopHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what == TaskLooper.MSG_WHAT_LOOP_TASK) {
                TaskLooper.this.loopTask();
            }
        }
    }

    private final class LoopRunnable implements Runnable {
        private final String tag;

        private LoopRunnable(String str) {
            this.tag = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            TaskLooper.this.loopHandler.sendEmptyMessage(TaskLooper.MSG_WHAT_LOOP_TASK);
            if (this.tag.equals("handler")) {
                TaskLooper.this.loopHandler.postDelayed(this, 30000L);
            }
        }
    }
}
