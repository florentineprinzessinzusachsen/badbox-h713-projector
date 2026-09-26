package com.hs.p.common.async;

import android.os.Handler;
import android.os.HandlerThread;
import com.hs.p.common.utils.LOG;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: loaded from: /Users/ruben/projector-dump/downloads/plugin.jar */
public class AsyncHandler {
    private static final String TAG = "AsyncHandler";
    private static volatile Set<AsyncHandler> mAsyncHandlers = Collections.synchronizedSet(new HashSet());
    private Handler mWorkHandler;
    private HandlerThread mWorkerThread;

    private AsyncHandler(String str) {
        HandlerThread handlerThread = new HandlerThread("Thread-7");
        this.mWorkerThread = handlerThread;
        handlerThread.start();
        this.mWorkHandler = new Handler(this.mWorkerThread.getLooper());
        LOG.i(TAG, "create handler: name=" + str + ", h=" + this.mWorkHandler + ", t=" + this.mWorkerThread);
    }

    private void close() {
        try {
            LOG.i(TAG, "close handler: handler=" + this.mWorkHandler + ", thread=" + this.mWorkerThread);
            Handler handler = this.mWorkHandler;
            if (handler != null) {
                handler.removeCallbacksAndMessages(null);
                this.mWorkHandler = null;
            }
            HandlerThread handlerThread = this.mWorkerThread;
            if (handlerThread != null) {
                handlerThread.quit();
                this.mWorkerThread = null;
            }
        } catch (Throwable th) {
            LOG.w(TAG, "close handler failed: " + th);
        }
    }

    public static AsyncHandler create(String str) {
        AsyncHandler asyncHandler = new AsyncHandler(str);
        mAsyncHandlers.add(asyncHandler);
        return asyncHandler;
    }

    public static void destroy() {
        try {
            LOG.i(TAG, "destroy ...");
            Iterator<AsyncHandler> it = mAsyncHandlers.iterator();
            if (it.hasNext()) {
                do {
                    it.next().close();
                } while (it.hasNext());
            }
        } catch (Throwable th) {
            try {
                LOG.w(TAG, "destroy failed: " + th);
            } finally {
                mAsyncHandlers.clear();
            }
        }
    }

    public void post(Runnable runnable) {
        try {
            Handler handler = this.mWorkHandler;
            if (handler == null) {
                LOG.w(TAG, "handle closed ...");
            } else {
                handler.post(runnable);
            }
        } catch (Throwable th) {
            LOG.w(TAG, "handler post failed: " + th);
        }
    }

    public void postDelayed(Runnable runnable, long j) {
        try {
            Handler handler = this.mWorkHandler;
            if (handler == null) {
                LOG.w(TAG, "handler closed ...");
            } else if (j > 0) {
                handler.postDelayed(runnable, j);
            } else {
                handler.post(runnable);
            }
        } catch (Throwable th) {
            LOG.w(TAG, "handler post delayed failed: " + th);
        }
    }

    public void removeAll() {
        try {
            Handler handler = this.mWorkHandler;
            if (handler == null) {
                LOG.w(TAG, "handle closed ...");
            } else {
                handler.removeCallbacksAndMessages(null);
            }
        } catch (Throwable th) {
            LOG.w(TAG, "handler remove all failed: " + th);
        }
    }

    public static void close(AsyncHandler asyncHandler) {
        if (asyncHandler != null) {
            try {
                asyncHandler.close();
                mAsyncHandlers.remove(asyncHandler);
            } catch (Throwable th) {
                LOG.w(TAG, "close handler(" + asyncHandler + ") failed: " + th);
            }
        }
    }
}
