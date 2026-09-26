package com.softwinner.tv.client;

import android.os.IHwBinder;
import android.os.RemoteException;
import android.util.Log;
import vendor.display.config.V1_0.IDisplayConfig;

/* JADX INFO: loaded from: classes.dex */
public final class AwDisplayConfigManagerProxy {
    private static final int HWBINDE_SESSION_ID = 6666;
    private static final String TAG = "AwDisplayConfigManagerProxy";
    static AwDisplayConfigManagerProxy sAwTvSystemManagerProxy;
    AwTvProxyDeathRecipient mDeathRecipient;
    private IDisplayConfig mDisplayConfigManager;

    private AwDisplayConfigManagerProxy() {
        this.mDeathRecipient = null;
        this.mDisplayConfigManager = null;
        this.mDeathRecipient = new AwTvProxyDeathRecipient();
        this.mDisplayConfigManager = getDisplayConfigManager();
    }

    public static AwDisplayConfigManagerProxy getInstance() {
        if (sAwTvSystemManagerProxy == null) {
            sAwTvSystemManagerProxy = new AwDisplayConfigManagerProxy();
        }
        return sAwTvSystemManagerProxy;
    }

    public IDisplayConfig getDisplayConfigManager() {
        try {
            if (this.mDisplayConfigManager == null) {
                this.mDisplayConfigManager = IDisplayConfig.getService(true);
                if (this.mDisplayConfigManager != null) {
                    this.mDisplayConfigManager.linkToDeath(this.mDeathRecipient, 6666L);
                }
            }
            return this.mDisplayConfigManager;
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void disconnectServices() {
        synchronized (AwDisplayConfigManagerProxy.class) {
            Log.d(TAG, "disconnectServices");
            this.mDisplayConfigManager = null;
            System.gc();
            try {
                Thread.sleep(100L);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference failed for: r0v0, types: [com.softwinner.tv.client.AwDisplayConfigManagerProxy$1] */
    public void reconnectServices() {
        new Thread("" + TAG + "## reconnectServices") { // from class: com.softwinner.tv.client.AwDisplayConfigManagerProxy.1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                int i = 0;
                try {
                    AwDisplayConfigManagerProxy.getInstance().disconnectServices();
                    Log.d(AwDisplayConfigManagerProxy.TAG, "begin to reconnectServices");
                    while (AwDisplayConfigManagerProxy.getInstance().getDisplayConfigManager() == null && i < 10000) {
                        i++;
                        sleep(1000L);
                        Log.e(AwDisplayConfigManagerProxy.TAG, "reconnectServices, count=" + i);
                    }
                } catch (Exception unused) {
                    Log.v(AwDisplayConfigManagerProxy.TAG, "reconnectServices failed");
                }
            }
        }.start();
    }

    final class AwTvProxyDeathRecipient implements IHwBinder.DeathRecipient {
        AwTvProxyDeathRecipient() {
        }

        public void serviceDied(long j) {
            Log.d(AwDisplayConfigManagerProxy.TAG, "service has died try to reconnect bt service.. cookie = " + j);
            if (j == 6666) {
                AwDisplayConfigManagerProxy.getInstance().reconnectServices();
            }
        }
    }
}
