package com.rk_itvui.settings.network;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.ashd.settings.R;

/* JADX INFO: loaded from: classes.dex */
public class WirelessNetworkFragment extends Fragment {
    private View wirelessNetworkFragmentLayout;

    public void updateView() {
    }

    @Override // android.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.wirelessNetworkFragmentLayout = layoutInflater.inflate(R.layout.wirelessnetwork_fragment, viewGroup, false);
        return this.wirelessNetworkFragmentLayout;
    }

    public void checkNetworkMode() {
    }
}
