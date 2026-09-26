package com.rk_itvui.settings.picture;

import android.content.Context;
import com.softwinner.PQControl;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class PQControlImpl {
    Context context;
    Method getPictureModeListMethod;
    Method getPictureModeNameMethod;
    PQControl mPQControl;
    Object mPQControlInstance;

    public PQControlImpl(Context context) {
        this.context = context;
        try {
            Class<?> cls = Class.forName("com.softwinner.PQControl");
            this.mPQControlInstance = cls.getConstructor(Context.class).newInstance(context);
            this.getPictureModeListMethod = cls.getMethod("getPictureModeList", new Class[0]);
            this.getPictureModeNameMethod = cls.getMethod("getPictureModeName", new Class[0]);
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
        } catch (InstantiationException e3) {
            e3.printStackTrace();
        } catch (InvocationTargetException e4) {
            e4.printStackTrace();
        }
    }

    public Map<String, String> getPictureModeList() {
        try {
            return (Map) this.getPictureModeListMethod.invoke(this.mPQControlInstance, new Object[0]);
        } catch (Exception e) {
            e.printStackTrace();
            return new HashMap();
        }
    }

    public String getPictureModeName() {
        try {
            return (String) this.getPictureModeNameMethod.invoke(this.mPQControlInstance, new Object[0]);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
