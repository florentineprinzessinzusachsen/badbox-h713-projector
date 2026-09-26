package com.hs.cld.ds;

import android.content.Context;
import com.hs.p.basic.SSLUtils;
import com.hs.p.common.async.Implementable;
import com.hs.p.common.http.HTTPBuilder;
import com.hs.p.common.http.HTTPError;
import com.hs.p.common.http.HTTPHelper;
import com.hs.p.common.utils.LOG;
import com.hs.p.common.utils.SystemUtils;
import com.hs.p.common.utils.TextUtils;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: /Users/ruben/projector-dump/downloads/plugin.jar */
public class DownloadManager {
    private static final String TAG = "DownloadManager";
    private static AtomicInteger mTaskCounter = new AtomicInteger(1);
    private ExecutorService mListenWorker;
    private final Map<String, AbortableTask> mTaskList;
    private ExecutorService mTaskWorker;

    private abstract class AbortableTask extends Implementable {
        protected boolean mAborted;
        private boolean mCancelNotified;
        private int mCurProgress;
        protected final String mLogContext;
        private OnTransferListener mOnTransferListener;
        protected final String mTaskId;

        public AbortableTask(String str) {
            super(str);
            this.mAborted = false;
            this.mOnTransferListener = null;
            this.mCurProgress = 0;
            this.mCancelNotified = false;
            String str2 = "" + DownloadManager.mTaskCounter.getAndIncrement();
            this.mTaskId = str2;
            this.mLogContext = "[ID:" + str2 + "]";
        }

        public String ID() {
            return this.mTaskId;
        }

        public synchronized void abort() {
            this.mAborted = true;
        }

        public synchronized void onBegin() {
            if (!this.mAborted) {
                DownloadManager.this.notifyBegin(this.mOnTransferListener, this.mTaskId);
            }
        }

        public synchronized void onCancel() {
            if (!this.mCancelNotified) {
                DownloadManager.this.notifyCancel(this.mOnTransferListener, this.mTaskId);
                this.mCancelNotified = true;
            }
        }

        public synchronized void onException(Exception exc) {
            if (!this.mAborted) {
                DownloadManager.this.notifyException(this.mOnTransferListener, this.mTaskId, exc);
            }
        }

        public synchronized void onFinish() {
            if (!this.mAborted) {
                DownloadManager.this.notifyFinish(this.mOnTransferListener, this.mTaskId);
            }
        }

        public synchronized void onNetworkError() {
            if (!this.mAborted) {
                DownloadManager.this.notifyNetworkError(this.mOnTransferListener, this.mTaskId);
            }
        }

        public synchronized void onProgress(long j, long j2) {
            if (!this.mAborted && j2 > 0) {
                int iMin = Math.min(100, Math.max(0, (int) ((100 * j) / j2)));
                if (iMin - this.mCurProgress >= 1) {
                    this.mCurProgress = iMin;
                    DownloadManager.this.notifyProgress(this.mOnTransferListener, this.mTaskId, iMin, j, j2);
                }
            }
        }

        public synchronized void onRedirectUrl(String str) {
            if (!this.mAborted) {
                DownloadManager.this.notifyRedirectUrl(this.mOnTransferListener, this.mTaskId, str);
            }
        }

        public void setOnTransferListener(OnTransferListener onTransferListener) {
            this.mOnTransferListener = onTransferListener;
        }
    }

    private class DownloadTask extends AbortableTask implements HTTPHelper.OnBlockListener {
        private final Context mContext;
        private RandomAccessFile mFileAccessor;
        private final String mLocalFileUrl;
        private String mRealDownloadUrl;
        private final String mRemoteFileUrl;
        private long mTotalBytes;
        private long mTransferred;

        private DownloadTask(Context context, String str, String str2) {
            super("downloadTask");
            this.mTotalBytes = 0L;
            this.mTransferred = 0L;
            this.mFileAccessor = null;
            this.mContext = context;
            this.mRemoteFileUrl = str;
            this.mRealDownloadUrl = str;
            this.mLocalFileUrl = str2;
        }

        private boolean canRetryOnError(HTTPError hTTPError) {
            if (hTTPError != null) {
                return hTTPError.codeEquals(101);
            }
            return false;
        }

        private void close() {
            try {
                try {
                    RandomAccessFile randomAccessFile = this.mFileAccessor;
                    if (randomAccessFile != null) {
                        randomAccessFile.close();
                    }
                } catch (Exception e) {
                    LOG.e(DownloadManager.TAG, "close file failed(Exception)", e);
                }
            } finally {
                this.mFileAccessor = null;
            }
        }

        private File convertBrokenFileOnCompleted(String str) throws Exception {
            File brokenFile = getBrokenFile(str);
            File file = new File(str);
            if (!brokenFile.exists()) {
                throw new Exception("broken file not exist");
            }
            if (file.exists()) {
                file.delete();
            }
            if (brokenFile.renameTo(file)) {
                return file;
            }
            throw new Exception("broken file rename failed");
        }

        private File createBrokenFile(String str) throws Exception {
            File brokenFile = getBrokenFile(str);
            if (!brokenFile.exists()) {
                if (!brokenFile.getParentFile().exists() && !brokenFile.getParentFile().mkdirs()) {
                    throw new Exception("mkdirs failed");
                }
                if (!brokenFile.createNewFile()) {
                    throw new Exception("create file failed");
                }
            }
            return brokenFile;
        }

        private boolean downloadFromRemote() throws Exception {
            try {
                SSLUtils.enableSSLIgnore();
                File fileCreateBrokenFile = createBrokenFile(this.mLocalFileUrl);
                long length = fileCreateBrokenFile.length();
                this.mFileAccessor = new RandomAccessFile(fileCreateBrokenFile, "rw");
                HTTPHelper hTTPHelperCreateHelper = HTTPBuilder.createHelper(this.mContext);
                hTTPHelperCreateHelper.setBlockListener(this);
                long jCurrentTimeMillis = System.currentTimeMillis();
                HTTPError hTTPErrorDownload = hTTPHelperCreateHelper.download(this.mRemoteFileUrl, length);
                long jCurrentTimeMillis2 = System.currentTimeMillis() - jCurrentTimeMillis;
                if (hTTPErrorDownload.codeEquals(0)) {
                    close();
                    File fileConvertBrokenFileOnCompleted = convertBrokenFileOnCompleted(this.mLocalFileUrl);
                    LOG.i(DownloadManager.TAG, this.mLogContext + " download done: ms=" + jCurrentTimeMillis2 + ", len=" + fileConvertBrokenFileOnCompleted.length() + ", file=" + fileConvertBrokenFileOnCompleted);
                    close();
                    return true;
                }
                if (!canRetryOnError(hTTPErrorDownload)) {
                    throw new Exception("download failed: " + hTTPErrorDownload);
                }
                LOG.e(DownloadManager.TAG, this.mLogContext + " download failed(" + hTTPErrorDownload + "), retry ...");
                close();
                return false;
            } catch (Throwable th) {
                close();
                throw th;
            }
        }

        private File getBrokenFile(String str) {
            return new File(str + ".part");
        }

        private void sleep(long j) {
            try {
                Thread.sleep(j);
            } catch (Exception unused) {
            }
        }

        private boolean tryDownload() throws Exception {
            StringBuilder sb;
            if (!this.mAborted) {
                if (!SystemUtils.isNetworkAvailable(this.mContext)) {
                    onNetworkError();
                    return false;
                }
                for (int i = 0; i < 30; i++) {
                    if (downloadFromRemote()) {
                        return true;
                    }
                    if (this.mAborted) {
                        sb = new StringBuilder();
                    } else {
                        if (!SystemUtils.isNetworkAvailable(this.mContext)) {
                            onNetworkError();
                            return false;
                        }
                        sleep(4000L);
                    }
                }
                throw new Exception("download and retry(30) failed");
            }
            sb = new StringBuilder();
            sb.append(this.mLogContext);
            sb.append(" task abort ...");
            LOG.i(DownloadManager.TAG, sb.toString());
            onCancel();
            return false;
        }

        @Override // com.hs.p.common.async.Implementable
        public void implement() {
            LOG.i(DownloadManager.TAG, "[ID:" + this.mTaskId + "] download: url=" + this.mRemoteFileUrl + ", dir=" + this.mLocalFileUrl);
            if (this.mAborted) {
                LOG.i(DownloadManager.TAG, this.mLogContext + " task abort ...");
                return;
            }
            onBegin();
            if (new File(this.mLocalFileUrl).exists()) {
                LOG.i(DownloadManager.TAG, this.mLogContext + " file exist ...");
                onFinish();
                return;
            }
            try {
                if (tryDownload()) {
                    onFinish();
                }
            } catch (Exception e) {
                if (this.mAborted) {
                    onCancel();
                } else {
                    onException(e);
                }
            }
        }

        @Override // com.hs.p.common.http.HTTPHelper.OnBlockListener
        public boolean onBlock(byte[] bArr, long j) {
            if (this.mAborted) {
                LOG.i(DownloadManager.TAG, this.mLogContext + " on block abort...");
                return false;
            }
            try {
                this.mFileAccessor.write(bArr, 0, (int) j);
                long j2 = this.mTransferred + j;
                this.mTransferred = j2;
                onProgress(j2, this.mTotalBytes);
                return true;
            } catch (IOException e) {
                LOG.e(DownloadManager.TAG, this.mLogContext + " write file failed: " + e);
                return false;
            }
        }

        @Override // com.hs.p.common.http.HTTPHelper.OnBlockListener
        public boolean onStart(String str, long j, long j2, long j3) {
            this.mRealDownloadUrl = str;
            this.mTotalBytes = j3;
            this.mTransferred = j;
            if (this.mAborted) {
                LOG.i(DownloadManager.TAG, this.mLogContext + " on start abort...");
                return false;
            }
            LOG.d(DownloadManager.TAG, this.mLogContext + " on start: range=[" + j + " " + j3 + " " + j2 + "], url=" + str);
            if (!TextUtils.equals(this.mRealDownloadUrl, this.mRemoteFileUrl)) {
                onRedirectUrl(this.mRealDownloadUrl);
            }
            try {
                this.mFileAccessor.seek(j);
                return true;
            } catch (IOException e) {
                LOG.e(DownloadManager.TAG, this.mLogContext + " seek failed(IOException): " + e.getMessage());
                return false;
            }
        }
    }

    private static class Holder {
        private static final DownloadManager INSTANCE = new DownloadManager();

        private Holder() {
        }
    }

    public interface OnTransferListener {
        void onBegin(String str);

        void onCancel(String str);

        void onException(String str, Exception exc);

        void onFinish(String str);

        void onNetworkError(String str);

        void onRedirectUrl(String str, String str2);

        void onTransfer(String str, int i, long j, long j2);
    }

    private DownloadManager() {
        this.mTaskList = Collections.synchronizedMap(new LinkedHashMap());
        this.mTaskWorker = null;
        this.mListenWorker = null;
    }

    private AbortableTask addTask(AbortableTask abortableTask) {
        synchronized (DownloadManager.class) {
            this.mTaskList.put(abortableTask.ID(), abortableTask);
        }
        return abortableTask;
    }

    public static DownloadManager get() {
        return Holder.INSTANCE;
    }

    private synchronized ExecutorService getListenWorker() {
        if (this.mListenWorker == null) {
            this.mListenWorker = Executors.newSingleThreadExecutor();
        }
        return this.mListenWorker;
    }

    private synchronized ExecutorService getTaskWorker() {
        if (this.mTaskWorker == null) {
            this.mTaskWorker = Executors.newFixedThreadPool(5);
        }
        return this.mTaskWorker;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyBegin(final OnTransferListener onTransferListener, final String str) {
        new Implementable("notifyBegin") { // from class: com.hs.cld.ds.DownloadManager.1
            @Override // com.hs.p.common.async.Implementable
            public void implement() {
                OnTransferListener onTransferListener2 = onTransferListener;
                if (onTransferListener2 != null) {
                    onTransferListener2.onBegin(str);
                }
            }
        }.execute(getListenWorker());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyCancel(final OnTransferListener onTransferListener, final String str) {
        new Implementable("notifyCancel") { // from class: com.hs.cld.ds.DownloadManager.6
            @Override // com.hs.p.common.async.Implementable
            public void implement() {
                synchronized (DownloadManager.class) {
                    DownloadManager.this.mTaskList.remove(str);
                }
                OnTransferListener onTransferListener2 = onTransferListener;
                if (onTransferListener2 != null) {
                    onTransferListener2.onCancel(str);
                }
            }
        }.execute(getListenWorker());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyException(final OnTransferListener onTransferListener, final String str, final Exception exc) {
        new Implementable("notifyError") { // from class: com.hs.cld.ds.DownloadManager.5
            @Override // com.hs.p.common.async.Implementable
            public void implement() {
                synchronized (DownloadManager.class) {
                    DownloadManager.this.mTaskList.remove(str);
                }
                OnTransferListener onTransferListener2 = onTransferListener;
                if (onTransferListener2 != null) {
                    onTransferListener2.onException(str, exc);
                }
            }
        }.execute(getListenWorker());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyFinish(final OnTransferListener onTransferListener, final String str) {
        new Implementable("notifyFinished") { // from class: com.hs.cld.ds.DownloadManager.4
            @Override // com.hs.p.common.async.Implementable
            public void implement() {
                synchronized (DownloadManager.class) {
                    DownloadManager.this.mTaskList.remove(str);
                }
                OnTransferListener onTransferListener2 = onTransferListener;
                if (onTransferListener2 != null) {
                    onTransferListener2.onFinish(str);
                }
            }
        }.execute(getListenWorker());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyNetworkError(final OnTransferListener onTransferListener, final String str) {
        new Implementable("notifyNetworkError") { // from class: com.hs.cld.ds.DownloadManager.7
            @Override // com.hs.p.common.async.Implementable
            public void implement() {
                synchronized (DownloadManager.class) {
                    DownloadManager.this.mTaskList.remove(str);
                }
                OnTransferListener onTransferListener2 = onTransferListener;
                if (onTransferListener2 != null) {
                    onTransferListener2.onNetworkError(str);
                }
            }
        }.execute(getListenWorker());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyProgress(final OnTransferListener onTransferListener, final String str, final int i, final long j, final long j2) {
        new Implementable("notifyProgress") { // from class: com.hs.cld.ds.DownloadManager.3
            @Override // com.hs.p.common.async.Implementable
            public void implement() {
                OnTransferListener onTransferListener2 = onTransferListener;
                if (onTransferListener2 != null) {
                    onTransferListener2.onTransfer(str, i, j, j2);
                }
            }
        }.execute(getListenWorker());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyRedirectUrl(final OnTransferListener onTransferListener, final String str, final String str2) {
        new Implementable("notifyRedirectUrl") { // from class: com.hs.cld.ds.DownloadManager.2
            @Override // com.hs.p.common.async.Implementable
            public void implement() {
                OnTransferListener onTransferListener2 = onTransferListener;
                if (onTransferListener2 != null) {
                    onTransferListener2.onRedirectUrl(str, str2);
                }
            }
        }.execute(getListenWorker());
    }

    private Map<String, AbortableTask> removeAllTask() {
        LinkedHashMap linkedHashMap;
        synchronized (DownloadManager.class) {
            linkedHashMap = new LinkedHashMap(this.mTaskList);
            this.mTaskList.clear();
        }
        return linkedHashMap;
    }

    private AbortableTask removeTask(String str) {
        AbortableTask abortableTaskRemove;
        synchronized (DownloadManager.class) {
            abortableTaskRemove = this.mTaskList.remove(str);
        }
        return abortableTaskRemove;
    }

    public void remove(String str) {
        if (TextUtils.empty(str)) {
            return;
        }
        LOG.d(TAG, "remove: id=" + str);
        AbortableTask abortableTaskRemoveTask = removeTask(str);
        if (abortableTaskRemoveTask != null) {
            abortableTaskRemoveTask.abort();
        }
    }

    public void removeAll() {
        LOG.d(TAG, "remove all tasks ...");
        for (AbortableTask abortableTask : removeAllTask().values()) {
            if (abortableTask != null) {
                abortableTask.abort();
            }
        }
    }

    public String start(Context context, String str, String str2, OnTransferListener onTransferListener) {
        LOG.d(TAG, "start download: url=" + str + ", local=" + str2);
        DownloadTask downloadTask = new DownloadTask(context, str, str2);
        downloadTask.setOnTransferListener(onTransferListener);
        downloadTask.execute(getTaskWorker());
        return addTask(downloadTask).ID();
    }
}
