package com.android.settingslib.net;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.net.INetworkStatsSession;
import android.net.NetworkStats;
import android.net.NetworkTemplate;
import android.os.Bundle;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
public class SummaryForAllUidLoader extends AsyncTaskLoader<NetworkStats> {
    private static final String KEY_END = "end";
    private static final String KEY_START = "start";
    private static final String KEY_TEMPLATE = "template";
    private final Bundle mArgs;
    private final INetworkStatsSession mSession;

    public static Bundle buildArgs(NetworkTemplate networkTemplate, long j, long j2) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(KEY_TEMPLATE, networkTemplate);
        bundle.putLong(KEY_START, j);
        bundle.putLong(KEY_END, j2);
        return bundle;
    }

    public SummaryForAllUidLoader(Context context, INetworkStatsSession iNetworkStatsSession, Bundle bundle) {
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
    public NetworkStats loadInBackground() {
        try {
            return this.mSession.getSummaryForAllUid(this.mArgs.getParcelable(KEY_TEMPLATE), this.mArgs.getLong(KEY_START), this.mArgs.getLong(KEY_END), false);
        } catch (RemoteException unused) {
            return null;
        }
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
}
