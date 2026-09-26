package com.softwinner;

import android.content.Context;
import android.util.Log;
import com.ashd.settings.R;
import com.rk_itvui.utils.ReflectionUtils;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer;

/* JADX INFO: loaded from: classes.dex */
public class PQControl {
    public static final int RET_SUCCESS = 0;
    public static final String TAG = "PQControlClient";
    Method getServiceMethod;
    private Context mContext;
    String[] mPictureModeKeys;
    private ITvServer mService;

    public PQControl(Context context) {
        Log.d(TAG, "PQControl init with context");
        this.mContext = context;
        try {
            this.getServiceMethod = Class.forName("vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer").getMethod("getService", Boolean.class);
            this.mService = (ITvServer) this.getServiceMethod.invoke(null, true);
            if (this.mService == null) {
                Log.e(TAG, "fail to get service");
            } else {
                Log.d(TAG, "success to get service");
                this.mPictureModeKeys = this.mContext.getResources().getStringArray(R.array.setting_picture_mode_values);
            }
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            e.printStackTrace();
            Log.e(TAG, "exception, fail to get service");
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
            e2.printStackTrace();
        } catch (InvocationTargetException e3) {
            e3.printStackTrace();
        }
    }

    public String getPictureModeName() {
        return (String) ReflectionUtils.invokeMethod(this.mService, "getPictureModeName", new Object[0]);
    }

    public Map<String, String> getPictureModeList() {
        String[] stringArray = this.mContext.getResources().getStringArray(R.array.setting_picture_mode_choices);
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (String str : (ArrayList) ReflectionUtils.invokeMethod(this.mService, "getPictureModeList", new Object[0])) {
            int iIndexOf = Arrays.asList(this.mPictureModeKeys).indexOf(str);
            if (iIndexOf < 0) {
                Log.e(TAG, "getPictureModeList invalid mode=" + str + " convert to the first mode");
                iIndexOf = 0;
            }
            linkedHashMap.put(this.mPictureModeKeys[iIndexOf], stringArray[iIndexOf]);
            Log.d(TAG, "getPictureModeList " + iIndexOf + " key:" + this.mPictureModeKeys[iIndexOf] + " value:" + stringArray[iIndexOf]);
        }
        Iterator it = linkedHashMap.keySet().iterator();
        while (it.hasNext()) {
            String str2 = (String) it.next();
            if (checkPictureMode(str2.toLowerCase()) == 0) {
                Log.d(TAG, "remove " + str2 + ", because current sourceType does not support it!");
                it.remove();
            }
        }
        return linkedHashMap;
    }

    public int checkPictureMode(String str) {
        return ((Integer) ReflectionUtils.invokeMethod(this.mService, "checkPictureMode", 8, str)).intValue();
    }
}
