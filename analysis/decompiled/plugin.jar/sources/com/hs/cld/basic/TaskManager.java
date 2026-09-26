package com.hs.cld.basic;

import android.content.Context;
import com.hs.p.basic.Processor;
import com.hs.p.common.async.AsyncHandler;
import com.hs.p.common.async.Implementable;
import com.hs.p.common.utils.LOG;
import java.util.HashSet;
import java.util.Iterator;

/* JADX INFO: loaded from: /Users/ruben/projector-dump/downloads/plugin.jar */
public class TaskManager {
    private static final String TAG = "TaskManager";
    private static AsyncHandler mAsyncHandle = AsyncHandler.create(TAG);
    private static volatile HashSet<Implementable> gMainTasks = new HashSet<>();

    public interface OnTaskListener {
        void onFinished();
    }

    private static class TasksProcessor extends Implementable {
        private final Context mContext;
        private final OnTaskListener mOnTaskListener;

        private TasksProcessor(Context context, OnTaskListener onTaskListener) {
            super("tasks processor");
            this.mContext = context.getApplicationContext();
            this.mOnTaskListener = onTaskListener;
        }

        private void processAllTasks() {
            Iterator<Class<? extends Processor>> it = Tasks.PROCESSORS.iterator();
            while (it.hasNext()) {
                processTask(it.next());
            }
        }

        private void processTask(Class<? extends Processor> cls) {
            try {
                Processor processorNewInstance = cls.newInstance();
                long jCurrentTimeMillis = System.currentTimeMillis();
                processorNewInstance.process(this.mContext, null);
                LOG.i(TaskManager.TAG, "process " + cls + " done: id=" + processorNewInstance.myId() + ", ms=" + (System.currentTimeMillis() - jCurrentTimeMillis));
            } catch (Throwable th) {
                LOG.e(TaskManager.TAG, "process " + cls + " failed: e=" + th, th);
            }
        }

        @Override // com.hs.p.common.async.Implementable
        protected void implement() {
            try {
                TaskManager.onMainTaskBegin(this);
                long jCurrentTimeMillis = System.currentTimeMillis();
                processAllTasks();
                LOG.i(TaskManager.TAG, "process all tasks done: " + (System.currentTimeMillis() - jCurrentTimeMillis) + "MS");
            } catch (Throwable th) {
                try {
                    LOG.e(TaskManager.TAG, "process all tasks failed", th);
                } finally {
                    TaskManager.onMainTaskFinish(this, this.mOnTaskListener);
                }
            }
        }
    }

    private static void callbackFinished(OnTaskListener onTaskListener) {
        if (onTaskListener != null) {
            try {
                onTaskListener.onFinished();
            } catch (Throwable th) {
                LOG.e(TAG, "callback finished failed", th);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void onMainTaskBegin(Implementable implementable) {
        if (implementable != null) {
            synchronized (TaskManager.class) {
                gMainTasks.add(implementable);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void onMainTaskFinish(Implementable implementable, OnTaskListener onTaskListener) {
        if (implementable != null) {
            synchronized (TaskManager.class) {
                gMainTasks.remove(implementable);
            }
        }
        callbackFinished(onTaskListener);
    }

    public static void process(Context context, OnTaskListener onTaskListener) {
        synchronized (TaskManager.class) {
            if (gMainTasks.isEmpty()) {
                mAsyncHandle.post(new TasksProcessor(context, onTaskListener));
            } else {
                LOG.i(TAG, "there's main task running, ignore ...");
            }
        }
    }
}
