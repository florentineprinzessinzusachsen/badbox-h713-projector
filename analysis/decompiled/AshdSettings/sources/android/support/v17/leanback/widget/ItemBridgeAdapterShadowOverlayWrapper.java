package android.support.v17.leanback.widget;

import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public class ItemBridgeAdapterShadowOverlayWrapper extends ItemBridgeAdapter.Wrapper {
    private final ShadowOverlayHelper mHelper;

    public ItemBridgeAdapterShadowOverlayWrapper(ShadowOverlayHelper shadowOverlayHelper) {
        this.mHelper = shadowOverlayHelper;
    }

    @Override // android.support.v17.leanback.widget.ItemBridgeAdapter.Wrapper
    public View createWrapper(View view) {
        return this.mHelper.createShadowOverlayContainer(view.getContext());
    }

    @Override // android.support.v17.leanback.widget.ItemBridgeAdapter.Wrapper
    public void wrap(View view, View view2) {
        ((ShadowOverlayContainer) view).wrap(view2);
    }
}
