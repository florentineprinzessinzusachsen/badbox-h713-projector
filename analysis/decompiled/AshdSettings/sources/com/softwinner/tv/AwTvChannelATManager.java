package com.softwinner.tv;

import android.content.Context;
import android.media.tv.TvContract;
import android.os.Bundle;
import android.util.Log;
import com.softwinner.tv.data.AwChannelListFilter;
import com.softwinner.tv.data.AwTvChannelInfo;
import com.softwinner.tv.data.AwTvChannelJsonBean;
import com.softwinner.tv.module.AwTvHelper;
import com.softwinner.tv.module.FileUtils;
import com.softwinner.tv.module.JsonUtils;
import java.io.File;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class AwTvChannelATManager {
    private static final boolean ISDEBUG = false;
    private static final String KEY_RESULT = "result";
    private static AwTvChannelATManager sAwTvChannelATManager;
    private Context mContext;
    private AwTvHelper mTvProviderHepler;
    private static final String TAG = "AwTvChannelATManager";
    private static final boolean DEBUG = Log.isLoggable(TAG, 3);

    private void debug(String str) {
        if (DEBUG) {
            Log.d(TAG, str);
        }
    }

    private AwTvChannelATManager(Context context) {
        debug("AwTvChannelATManager created");
        this.mContext = context;
        this.mTvProviderHepler = AwTvHelper.getInstance(this.mContext);
    }

    public static synchronized AwTvChannelATManager getInstance(Context context) {
        if (sAwTvChannelATManager == null) {
            sAwTvChannelATManager = new AwTvChannelATManager(context);
        }
        return sAwTvChannelATManager;
    }

    public boolean exportChannelAll(String str, boolean z) {
        Log.d(TAG, "exportChannelAll filePath: " + str + " isJson=" + z);
        if (str == null || str.length() == 0) {
            Log.e(TAG, "path wrong");
            return false;
        }
        if (!z) {
            File file = new File(str);
            if (file.exists()) {
                file.delete();
            }
            Bundle bundleCall = this.mContext.getContentResolver().call(TvContract.Channels.CONTENT_URI, "backup_all_database", file.getAbsolutePath(), (Bundle) null);
            Log.d(TAG, "export [" + file.getAbsolutePath() + "] ret=" + bundleCall.getInt(KEY_RESULT));
            return bundleCall.getInt(KEY_RESULT) == 0;
        }
        String strObjToJson = JsonUtils.objToJson(AwTvChannelJsonBean.getJsonBean(this.mTvProviderHepler.getChannelListByFilter(AwChannelListFilter.ENUM_CHANNEL_LIST_FILTER_ALL, 3, null)));
        debug("saveJsonStr: " + strObjToJson);
        return FileUtils.writeStringToFile(str, strObjToJson);
    }

    public boolean importChannelAll(String str, boolean z) throws Throwable {
        Log.d(TAG, "importChannelAll filePath: " + str + " isJson=" + z);
        if (str == null || str.length() == 0) {
            Log.e(TAG, "path wrong");
            return false;
        }
        if (!z) {
            File file = new File(str);
            if (file.exists()) {
                Bundle bundleCall = this.mContext.getContentResolver().call(TvContract.Channels.CONTENT_URI, "recovery_all_database", file.getAbsolutePath(), (Bundle) null);
                Log.d(TAG, "import [" + file.getAbsolutePath() + "] ret=" + bundleCall.getInt(KEY_RESULT));
                return bundleCall.getInt(KEY_RESULT) == 0;
            }
            Log.e(TAG, "file " + str + "not exists");
            return false;
        }
        String fileToString = FileUtils.readFileToString(str);
        debug("jsonStr: " + fileToString);
        AwTvChannelJsonBean awTvChannelJsonBean = (AwTvChannelJsonBean) JsonUtils.jsonToObj(fileToString, AwTvChannelJsonBean.class);
        List<AwTvChannelInfo> awTvChannelInfoList = AwTvChannelJsonBean.getAwTvChannelInfoList(awTvChannelJsonBean, this.mTvProviderHepler.getTvInputId(2), true);
        if (awTvChannelInfoList != null) {
            this.mTvProviderHepler.deleteTvChannelsToProviderByType(2, null);
            Log.d(TAG, "delete old atv channels");
            for (AwTvChannelInfo awTvChannelInfo : awTvChannelInfoList) {
                this.mTvProviderHepler.addTvChannelToProvider(awTvChannelInfo);
                debug("" + awTvChannelInfo.toString());
            }
        }
        List<AwTvChannelInfo> awTvChannelInfoList2 = AwTvChannelJsonBean.getAwTvChannelInfoList(awTvChannelJsonBean, this.mTvProviderHepler.getTvInputId(1), false);
        if (awTvChannelInfoList2 != null) {
            this.mTvProviderHepler.deleteTvChannelsToProviderByType(1, null);
            Log.d(TAG, "delete old dtv channels");
            for (AwTvChannelInfo awTvChannelInfo2 : awTvChannelInfoList2) {
                this.mTvProviderHepler.addTvChannelToProvider(awTvChannelInfo2);
                debug("" + awTvChannelInfo2.toString());
            }
        }
        return !((awTvChannelInfoList == null) | (awTvChannelInfoList2 == null));
    }
}
