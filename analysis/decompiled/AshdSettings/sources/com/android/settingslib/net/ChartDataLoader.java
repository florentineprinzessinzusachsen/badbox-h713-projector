package com.android.settingslib.net;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.net.INetworkStatsSession;
import android.net.NetworkStatsHistory;
import android.net.NetworkTemplate;
import android.os.Bundle;
import android.os.RemoteException;
import com.android.settingslib.AppItem;

/* JADX INFO: loaded from: classes.dex */
public class ChartDataLoader extends AsyncTaskLoader<ChartData> {
    private static final String KEY_APP = "app";
    private static final String KEY_FIELDS = "fields";
    private static final String KEY_TEMPLATE = "template";
    private final Bundle mArgs;
    private final INetworkStatsSession mSession;

    public static Bundle buildArgs(NetworkTemplate networkTemplate, AppItem appItem) {
        return buildArgs(networkTemplate, appItem, 10);
    }

    public static Bundle buildArgs(NetworkTemplate networkTemplate, AppItem appItem, int i) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(KEY_TEMPLATE, networkTemplate);
        bundle.putParcelable(KEY_APP, appItem);
        bundle.putInt(KEY_FIELDS, i);
        return bundle;
    }

    public ChartDataLoader(Context context, INetworkStatsSession iNetworkStatsSession, Bundle bundle) {
        super(context);
        this.mSession = iNetworkStatsSession;
        this.mArgs = bundle;
    }

    @Override // android.content.Loader
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // android.content.AsyncTaskLoader
    public ChartData loadInBackground() {
        try {
            return loadInBackground((NetworkTemplate) this.mArgs.getParcelable(KEY_TEMPLATE), (AppItem) this.mArgs.getParcelable(KEY_APP), this.mArgs.getInt(KEY_FIELDS));
        } catch (RemoteException e) {
            throw new RuntimeException("problem reading network stats", e);
        }
    }

    private ChartData loadInBackground(NetworkTemplate networkTemplate, AppItem appItem, int i) throws RemoteException {
        ChartData chartData = new ChartData();
        chartData.network = this.mSession.getHistoryForNetwork(networkTemplate, i);
        if (appItem != null) {
            int size = appItem.uids.size();
            for (int i2 = 0; i2 < size; i2++) {
                int iKeyAt = appItem.uids.keyAt(i2);
                chartData.detailDefault = collectHistoryForUid(networkTemplate, iKeyAt, 0, chartData.detailDefault);
                chartData.detailForeground = collectHistoryForUid(networkTemplate, iKeyAt, 1, chartData.detailForeground);
            }
            if (size > 0) {
                chartData.detail = new NetworkStatsHistory(chartData.detailForeground.getBucketDuration());
                chartData.detail.recordEntireHistory(chartData.detailDefault);
                chartData.detail.recordEntireHistory(chartData.detailForeground);
            } else {
                chartData.detailDefault = new NetworkStatsHistory(3600000L);
                chartData.detailForeground = new NetworkStatsHistory(3600000L);
                chartData.detail = new NetworkStatsHistory(3600000L);
            }
        }
        return chartData;
    }

    @Override // android.content.Loader
    protected void onStopLoading() {
        super.onStopLoading();
        cancelLoad();
    }

    @Override // android.content.Loader
    protected void onReset() {
        super.onReset();
        cancelLoad();
    }

    private NetworkStatsHistory collectHistoryForUid(NetworkTemplate networkTemplate, int i, int i2, NetworkStatsHistory networkStatsHistory) throws RemoteException {
        NetworkStatsHistory historyForUid = this.mSession.getHistoryForUid(networkTemplate, i, i2, 0, 10);
        if (networkStatsHistory == null) {
            return historyForUid;
        }
        networkStatsHistory.recordEntireHistory(historyForUid);
        return networkStatsHistory;
    }
}
