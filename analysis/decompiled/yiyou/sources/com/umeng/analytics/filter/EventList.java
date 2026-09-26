package com.umeng.analytics.filter;

import android.content.Context;
import android.text.TextUtils;
import com.umeng.commonsdk.debug.UMRTLog;
import com.umeng.commonsdk.framework.UMEnvelopeBuild;
import com.umeng.commonsdk.internal.crash.UMCrashManager;
import com.umeng.commonsdk.statistics.common.HelperUtils;
import com.umeng.commonsdk.statistics.idtracking.ImprintHandler;
import com.umeng.commonsdk.utils.FileLockCallback;
import com.umeng.commonsdk.utils.FileLockUtil;
import com.umeng.commonsdk.utils.UMUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class EventList extends c implements FileLockCallback {
    private static final int DELETE_LIST_DATA = 2;
    private static final int LOAD_LIST_DATA = 1;
    private static final int SAVE_LIST_DATA = 0;
    private Context mAppContext;
    protected String mEventList;
    protected String mEventListKey;
    protected String mEventListName;
    protected String mEventListVersionKey;
    private FileLockUtil mFileLock = new FileLockUtil();

    public EventList(String str, String str2) {
        this.mEventListName = str;
        this.mEventListKey = str;
        this.mEventListVersionKey = str2;
    }

    private void deleteDataFile(File file) {
        if (this.mAppContext != null) {
            synchronized (this.mFileLock) {
                if (file.exists()) {
                    file.delete();
                }
            }
        }
    }

    private boolean loadEventListFromFile(Context context, File file) {
        Throwable th;
        BufferedReader bufferedReader;
        if (file.exists() && TextUtils.isEmpty(this.mEventList)) {
            try {
                try {
                    bufferedReader = new BufferedReader(new FileReader(file));
                    try {
                        StringBuilder sb = new StringBuilder();
                        while (true) {
                            String line = bufferedReader.readLine();
                            if (line == null) {
                                break;
                            }
                            sb.append(line);
                        }
                        String string = sb.toString();
                        if (!TextUtils.isEmpty(string)) {
                            String md5 = HelperUtils.getMD5(string);
                            String strImprintProperty = UMEnvelopeBuild.imprintProperty(context, this.mEventListVersionKey, "");
                            this.mEventList = string;
                            eventListChange();
                            UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> loadEventListFromFile: mEventList = " + this.mEventList);
                            if (!UMUtils.isMainProgress(context)) {
                                if (!md5.equalsIgnoreCase(strImprintProperty)) {
                                    try {
                                        bufferedReader.close();
                                    } catch (Throwable th2) {
                                        UMCrashManager.reportCrash(context, th2);
                                    }
                                    return false;
                                }
                                this.mEventList = string;
                                eventListChange();
                                try {
                                    bufferedReader.close();
                                } catch (Throwable th3) {
                                    UMCrashManager.reportCrash(context, th3);
                                }
                                return true;
                            }
                            if (!md5.equalsIgnoreCase(strImprintProperty)) {
                                setMD5ClearFlag(true);
                                try {
                                    bufferedReader.close();
                                } catch (Throwable th4) {
                                    UMCrashManager.reportCrash(context, th4);
                                }
                                return false;
                            }
                            this.mEventList = string;
                            setMD5ClearFlag(false);
                            try {
                                bufferedReader.close();
                            } catch (Throwable th5) {
                                UMCrashManager.reportCrash(context, th5);
                            }
                            return true;
                        }
                        bufferedReader.close();
                    } catch (Throwable th6) {
                        th = th6;
                        try {
                            UMCrashManager.reportCrash(context, th);
                            if (bufferedReader != null) {
                                bufferedReader.close();
                            }
                            return false;
                        } catch (Throwable th7) {
                            if (bufferedReader != null) {
                                try {
                                    bufferedReader.close();
                                } catch (Throwable th8) {
                                    UMCrashManager.reportCrash(context, th8);
                                }
                            }
                            throw th7;
                        }
                    }
                } catch (Throwable th9) {
                    UMCrashManager.reportCrash(context, th9);
                }
            } catch (Throwable th10) {
                th = th10;
                bufferedReader = null;
            }
        }
        return false;
    }

    private void saveEventListToFile(Context context, File file) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(this.mEventList.getBytes());
            fileOutputStream.close();
        } catch (Throwable th) {
            UMCrashManager.reportCrash(context, th);
        }
    }

    public boolean enabled() {
        synchronized (this) {
            return this.mEventList != null;
        }
    }

    protected void eventListChange() {
    }

    public boolean matchHit(String str) {
        return false;
    }

    @Override // com.umeng.commonsdk.utils.FileLockCallback
    public boolean onFileLock(File file, int i) {
        if (i == 0) {
            synchronized (this) {
                saveEventListToFile(this.mAppContext, file);
            }
        } else if (i == 1) {
            synchronized (this) {
                if (loadEventListFromFile(this.mAppContext, file)) {
                    UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> find event list data file, load it.");
                } else {
                    UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> can't find event list file.");
                }
            }
        } else if (i == 2) {
            synchronized (this) {
                this.mEventList = null;
                deleteDataFile(file);
            }
        }
        return true;
    }

    @Override // com.umeng.commonsdk.utils.FileLockCallback
    public boolean onFileLock(String str) {
        return false;
    }

    @Override // com.umeng.commonsdk.utils.FileLockCallback
    public boolean onFileLock(String str, Object obj) {
        return false;
    }

    @Override // com.umeng.analytics.filter.c, com.umeng.commonsdk.statistics.internal.UMImprintChangeCallback
    public void onImprintValueChanged(String str, String str2) {
        if (com.umeng.analytics.pro.b.ak.equals(str) && str2 == null) {
            UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> disable black list for ekv.");
            this.mFileLock.doFileOperateion(new File(this.mAppContext.getFilesDir(), this.mEventListName), this, 2);
        }
        if (com.umeng.analytics.pro.b.al.equals(str) && str2 == null) {
            UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> disable white list for ekv.");
            this.mFileLock.doFileOperateion(new File(this.mAppContext.getFilesDir(), this.mEventListName), this, 2);
        }
    }

    @Override // com.umeng.analytics.filter.c, com.umeng.commonsdk.statistics.internal.UMImprintPreProcessCallback
    public boolean onPreProcessImprintKey(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return true;
        }
        UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> onPreProcessImprintKey: key = " + str + "; len of value=" + str2.length());
        StringBuilder sb = new StringBuilder();
        sb.append("--->>> onPreProcessImprintKey: value = ");
        sb.append(str2);
        UMRTLog.i(UMRTLog.RTLOG_TAG, sb.toString());
        this.mEventList = str2;
        eventListChange();
        File file = new File(this.mAppContext.getFilesDir(), this.mEventListName);
        if (!file.exists()) {
            try {
                try {
                    file.createNewFile();
                } catch (IOException e2) {
                    UMCrashManager.reportCrash(this.mAppContext, e2);
                }
            } catch (IOException unused) {
                file.createNewFile();
            }
        }
        this.mFileLock.doFileOperateion(file, this, 0);
        setMD5ClearFlag(false);
        return true;
    }

    public void register(Context context) {
        if (this.mAppContext == null) {
            this.mAppContext = context.getApplicationContext();
        }
        File file = new File(this.mAppContext.getFilesDir(), this.mEventListName);
        if (!TextUtils.isEmpty(UMEnvelopeBuild.imprintProperty(this.mAppContext, this.mEventListVersionKey, ""))) {
            if (file.exists()) {
                this.mFileLock.doFileOperateion(file, this, 1);
            } else {
                setMD5ClearFlag(true);
            }
        }
        if (UMUtils.isMainProgress(this.mAppContext)) {
            ImprintHandler.getImprintService(this.mAppContext).registPreProcessCallback(this.mEventListKey, this);
            ImprintHandler.getImprintService(this.mAppContext).registImprintCallback(this.mEventListVersionKey, this);
        }
    }

    public void setMD5ClearFlag(boolean z) {
    }

    public String toString() {
        if (TextUtils.isEmpty(this.mEventListName) || TextUtils.isEmpty(this.mEventListKey)) {
            return "Uninitialized EventList.";
        }
        StringBuilder sb = new StringBuilder("[");
        sb.append("EventListName:" + this.mEventListName + ",");
        sb.append("listKey:" + this.mEventListKey + ",");
        if (TextUtils.isEmpty(this.mEventList)) {
            sb.append("listKeyValue:empty,");
        } else {
            sb.append("listKeyValue:" + this.mEventList + "]");
        }
        if (TextUtils.isEmpty(this.mEventListVersionKey)) {
            sb.append("listKeyVer:empty]");
        } else {
            sb.append("listKeyVer:" + this.mEventListVersionKey + "]");
        }
        return sb.toString();
    }
}
