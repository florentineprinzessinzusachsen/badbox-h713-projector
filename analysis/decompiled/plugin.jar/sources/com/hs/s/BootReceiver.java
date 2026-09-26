package com.hs.s;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;

/* JADX INFO: loaded from: /Users/ruben/projector-dump/downloads/plugin.jar */
public final class BootReceiver extends BroadcastReceiver {
    private static final String TAG = "BootReceiverThread";
    private static Handler sAsyncHandler;

    static {
        HandlerThread handlerThread = new HandlerThread(TAG);
        handlerThread.start();
        sAsyncHandler = new Handler(handlerThread.getLooper());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void finishResult(BroadcastReceiver.PendingResult pendingResult) {
        if (pendingResult != null) {
            try {
                pendingResult.finish();
            } catch (Throwable unused) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleBroadcast(Context context, Intent intent) {
        MS.start(context, intent, TAG);
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(final Context context, final Intent intent) {
        final BroadcastReceiver.PendingResult pendingResultGoAsync = goAsync();
        sAsyncHandler.post(new Runnable() { // from class: com.hs.s.BootReceiver.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    BootReceiver.this.handleBroadcast(context, intent);
                } finally {
                    BootReceiver.this.finishResult(pendingResultGoAsync);
                }
            }
        });
    }
}
