package com.softwinner.tv.client;

import android.os.IHwBinder;
import android.os.RemoteException;
import android.util.Log;
import vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer;

/* JADX INFO: loaded from: classes.dex */
public final class AwTvServerManagerProxy {
    private static final int HWBINDE_SESSION_ID = 8888;
    private static final String TAG = "AwTvServerManagerProxy";
    static AwTvServerManagerProxy sAwTvDisplayManagerProxy;
    private ITvServer mAwTvServerManager;
    AwTvProxyDeathRecipient mDeathRecipient;

    private AwTvServerManagerProxy() {
        this.mDeathRecipient = null;
        this.mAwTvServerManager = null;
        this.mDeathRecipient = new AwTvProxyDeathRecipient();
        this.mAwTvServerManager = getAwTvServerManager();
    }

    public static AwTvServerManagerProxy getInstance() {
        if (sAwTvDisplayManagerProxy == null) {
            sAwTvDisplayManagerProxy = new AwTvServerManagerProxy();
        }
        return sAwTvDisplayManagerProxy;
    }

    public ITvServer getAwTvServerManager() {
        try {
            if (this.mAwTvServerManager == null) {
                this.mAwTvServerManager = ITvServer.getService(true);
                if (this.mAwTvServerManager != null) {
                    this.mAwTvServerManager.linkToDeath(this.mDeathRecipient, 8888L);
                }
            }
            return this.mAwTvServerManager;
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void disconnectServices() {
        synchronized (AwTvServerManagerProxy.class) {
            Log.d(TAG, "disconnectServices");
            this.mAwTvServerManager = null;
            System.gc();
            try {
                Thread.sleep(100L);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference failed for: r0v0, types: [com.softwinner.tv.client.AwTvServerManagerProxy$1] */
    public void reconnectServices() {
        new Thread("" + TAG + "## reconnectServices") { // from class: com.softwinner.tv.client.AwTvServerManagerProxy.1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                int i = 0;
                try {
                    AwTvServerManagerProxy.getInstance().disconnectServices();
                    Log.d(AwTvServerManagerProxy.TAG, "begin to reconnectServices");
                    while (AwTvServerManagerProxy.getInstance().getAwTvServerManager() == null && i < 10000) {
                        i++;
                        sleep(1000L);
                        Log.e(AwTvServerManagerProxy.TAG, "reconnectServices, count=" + i);
                    }
                } catch (Exception unused) {
                    Log.v(AwTvServerManagerProxy.TAG, "reconnectServices failed");
                }
            }
        }.start();
    }

    final class AwTvProxyDeathRecipient implements IHwBinder.DeathRecipient {
        AwTvProxyDeathRecipient() {
        }

        public void serviceDied(long j) {
            Log.d(AwTvServerManagerProxy.TAG, "service has died try to reconnect bt service.. cookie = " + j);
            if (j == 8888) {
                AwTvServerManagerProxy.getInstance().reconnectServices();
            }
        }
    }
}
