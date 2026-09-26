package com.hs.p.basic;

import android.content.Context;
import com.hs.p.common.utils.LOG;
import com.hs.p.common.utils.ObjUtils;
import com.hs.p.common.utils.TextUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: /Users/ruben/projector-dump/downloads/plugin.jar */
public class Hosts {
    private static final String TAG = "Hosts";
    private static final List<String> API_MASTER_HOSTS = new ArrayList<String>() { // from class: com.hs.p.basic.Hosts.1
        {
            add("H4sIAAAAAAAAADOI/J/TabYoK2NzuLHMw7U8UiKbt156Gw0AV1pl7RcAAAA=");
        }
    };
    private static final List<String> API_SLAVE_HOSTS = new ArrayList<String>() { // from class: com.hs.p.basic.Hosts.2
        {
            add("H4sIAAAAAAAAADOI/J/TabYoK2NzuLHc86UM2nEvcxT6AVkpos8WAAAA");
        }
    };
    private static final List<String> TRACKER_MASTER_HOSTS = new ArrayList<String>() { // from class: com.hs.p.basic.Hosts.3
        {
            add("H4sIAAAAAAAAADOI/J/TabYoK2NzuLHc86UM2nEvcxT6AVkpos8WAAAA");
        }
    };
    private static final List<String> TRACKER_SLAVE_HOSTS = new ArrayList<String>() { // from class: com.hs.p.basic.Hosts.4
        {
            add("H4sIAAAAAAAAADOI/J/TabYoK2NzuLHMw7U8UiKbt156Gw0AV1pl7RcAAAA=");
        }
    };

    public static List<String> apiHosts(Context context) {
        return mergeApiHosts(context);
    }

    public static List<String> defaultMasterApiHosts(Context context) {
        List<String> stringList = AssetsJsonUtils.getStringList(context, "-1.hs", "api_master_hosts");
        return !ObjUtils.empty(stringList) ? stringList : API_MASTER_HOSTS;
    }

    public static List<String> defaultMasterTrackerHosts(Context context) {
        List<String> stringList = AssetsJsonUtils.getStringList(context, "-1.hs", "tracker_master_hosts");
        return !ObjUtils.empty(stringList) ? stringList : TRACKER_MASTER_HOSTS;
    }

    public static List<String> defaultSlaveApiHosts(Context context) {
        List<String> stringList = AssetsJsonUtils.getStringList(context, "-1.hs", "api_slave_hosts");
        return !ObjUtils.empty(stringList) ? stringList : API_SLAVE_HOSTS;
    }

    public static List<String> defaultSlaveTrackerHosts(Context context) {
        List<String> stringList = AssetsJsonUtils.getStringList(context, "-1.hs", "tracker_slave_hosts");
        return !ObjUtils.empty(stringList) ? stringList : TRACKER_SLAVE_HOSTS;
    }

    private static List<String> mergeApiHosts(Context context) {
        String str;
        String lastCheckHost = Settings.getLastCheckHost(context);
        ArrayList arrayList = new ArrayList();
        if (lastCheckHost != null) {
            arrayList.add(lastCheckHost);
        }
        List<String> listPrimaryApiHosts = primaryApiHosts(context);
        List<String> listDefaultMasterApiHosts = defaultMasterApiHosts(context);
        List<String> listDefaultSlaveApiHosts = defaultSlaveApiHosts(context);
        List<String> listDecrypt = EncryptUtils.decrypt(listPrimaryApiHosts, Constants.HOST_AESKEY);
        List<String> listDecrypt2 = EncryptUtils.decrypt(listDefaultMasterApiHosts, Constants.HOST_AESKEY);
        List<String> listDecrypt3 = EncryptUtils.decrypt(listDefaultSlaveApiHosts, Constants.HOST_AESKEY);
        ArrayList arrayList2 = new ArrayList();
        if (!ObjUtils.empty(listDecrypt)) {
            arrayList2.addAll(randomSortList(listDecrypt));
        }
        boolean zIsMasterDisabled = DomainFailureTracker.isMasterDisabled(context);
        boolean zIsSlaveDisabled = DomainFailureTracker.isSlaveDisabled(context);
        if (zIsMasterDisabled || ObjUtils.empty(listDecrypt2)) {
            if (zIsMasterDisabled && !zIsSlaveDisabled && !ObjUtils.empty(listDecrypt3)) {
                arrayList2.addAll(listDecrypt3);
                str = "Master domain disabled, using slave API domains";
            } else if (zIsMasterDisabled && zIsSlaveDisabled && !ObjUtils.empty(arrayList)) {
                arrayList2.addAll(arrayList);
                str = "Master and slave domains disabled, using random API domains";
            }
            LOG.w(TAG, str);
        } else {
            arrayList2.addAll(listDecrypt2);
            LOG.d(TAG, "Using master API domains");
        }
        return arrayList2;
    }

    private static List<String> mergeSortList(List<String>... listArr) {
        ArrayList arrayList = new ArrayList();
        if (listArr != null) {
            for (List<String> list : listArr) {
                if (list != null) {
                    arrayList.addAll(list);
                }
            }
        }
        return arrayList;
    }

    private static List<String> mergeTrackerHosts(Context context) {
        String lastCheckHost;
        String str;
        List<String> listPrimaryTrackerHosts = primaryTrackerHosts(context);
        List<String> listDefaultMasterTrackerHosts = defaultMasterTrackerHosts(context);
        List<String> listDefaultSlaveTrackerHosts = defaultSlaveTrackerHosts(context);
        ArrayList arrayList = new ArrayList();
        if (!ObjUtils.empty(listPrimaryTrackerHosts)) {
            arrayList.addAll(randomSortList(listPrimaryTrackerHosts));
        }
        boolean zIsMasterDisabled = DomainFailureTracker.isMasterDisabled(context);
        boolean zIsSlaveDisabled = DomainFailureTracker.isSlaveDisabled(context);
        if (zIsMasterDisabled || ObjUtils.empty(listDefaultMasterTrackerHosts)) {
            if (zIsMasterDisabled && !zIsSlaveDisabled && !ObjUtils.empty(listDefaultSlaveTrackerHosts)) {
                arrayList.addAll(listDefaultSlaveTrackerHosts);
                str = "Master domain disabled, using slave tracker domains";
            } else if (zIsMasterDisabled && zIsSlaveDisabled && (lastCheckHost = Settings.getLastCheckHost(context)) != null) {
                arrayList.add(lastCheckHost);
                str = "Master and slave domains disabled, using random tracker domains";
            }
            LOG.w(TAG, str);
        } else {
            arrayList.addAll(listDefaultMasterTrackerHosts);
            LOG.d(TAG, "Using master tracker domains");
        }
        return arrayList;
    }

    public static List<String> primaryApiHosts(Context context) {
        String apiHosts = Settings.getApiHosts(context, "");
        return !ObjUtils.empty(apiHosts) ? TextUtils.split2List(apiHosts, ";") : new ArrayList();
    }

    private static List<String> primaryTrackerHosts(Context context) {
        String trackerHosts = Settings.getTrackerHosts(context, "");
        return !ObjUtils.empty(trackerHosts) ? TextUtils.split2List(trackerHosts, ";") : new ArrayList();
    }

    private static List<String> randomSortList(List<String> list) {
        if (ObjUtils.empty(list)) {
            return list;
        }
        ArrayList arrayList = new ArrayList(list);
        Collections.shuffle(arrayList);
        return arrayList;
    }

    public static List<String> trackerHosts(Context context) {
        return EncryptUtils.decrypt(mergeTrackerHosts(context), Constants.HOST_AESKEY);
    }
}
