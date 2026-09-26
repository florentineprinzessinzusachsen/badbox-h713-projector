package com.hs.s;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.hs.common.utils.LOG;
import java.lang.ref.WeakReference;
import java.util.concurrent.atomic.AtomicLong;

/* JADX INFO: loaded from: /Users/ruben/projector-dump/downloads/plugin.jar */
public abstract class IntentService extends Service {
    private static final String TAG = "IntentService";
    private static AtomicLong mHandleCounter = new AtomicLong(1);
    private volatile Looper mServiceLooper = null;
    private volatile ServiceHandler mServiceHandler = null;
    private String mName = null;
    private boolean mRedelivery = false;
    private boolean mInited = false;

    private static final class ServiceHandler extends Handler {
        private WeakReference<IntentService> reference;

        public ServiceHandler(IntentService intentService, Looper looper) {
            super(looper);
            this.reference = new WeakReference<>(intentService);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            try {
                this.reference.get().handleIntent((Intent) message.obj);
            } catch (Throwable th) {
                LOG.e(IntentService.TAG, "[" + message + "] handle intent failed", th);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleIntent(Intent intent) {
        onHandleIntent(intent, this.mInited, mHandleCounter.getAndIncrement());
        this.mInited = true;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        HandlerThread handlerThread = new HandlerThread("ServiceHandlerThread[" + this.mName + "]");
        handlerThread.start();
        this.mServiceLooper = handlerThread.getLooper();
        this.mServiceHandler = new ServiceHandler(this, this.mServiceLooper);
    }

    @Override // android.app.Service
    public void onDestroy() {
        this.mServiceLooper.quit();
        if (this.mServiceHandler != null) {
            this.mServiceHandler.removeCallbacksAndMessages(null);
            this.mServiceHandler = null;
        }
        super.onDestroy();
    }

    protected abstract void onHandleIntent(Intent intent, boolean z, long j);

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        Message messageObtainMessage = this.mServiceHandler.obtainMessage();
        messageObtainMessage.arg1 = i2;
        messageObtainMessage.obj = intent;
        this.mServiceHandler.sendMessage(messageObtainMessage);
        return this.mRedelivery ? 3 : 1;
    }

    public void postIntent(Intent intent) {
        try {
            Message messageObtainMessage = this.mServiceHandler.obtainMessage();
            messageObtainMessage.obj = intent;
            this.mServiceHandler.sendMessage(messageObtainMessage);
        } catch (Throwable th) {
            LOG.i(TAG, "[" + intent + "] post intent failed: " + th);
        }
    }

    public void postIntentDelayed(Intent intent, long j) {
        try {
            Message messageObtainMessage = this.mServiceHandler.obtainMessage();
            messageObtainMessage.obj = intent;
            this.mServiceHandler.sendMessageDelayed(messageObtainMessage, j);
        } catch (Throwable th) {
            LOG.i(TAG, "[" + intent + "][" + j + "] post intent delayed failed: " + th);
        }
    }

    public void setIntentRedelivery(boolean z) {
        this.mRedelivery = z;
    }

    protected void setName(String str) {
        this.mName = str;
    }
}
