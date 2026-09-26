package android.support.v17.leanback.app;

import android.app.Fragment;
import android.support.annotation.RestrictTo;

/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public final class BackgroundFragment extends Fragment {
    private BackgroundManager mBackgroundManager;

    void setBackgroundManager(BackgroundManager backgroundManager) {
        this.mBackgroundManager = backgroundManager;
    }

    BackgroundManager getBackgroundManager() {
        return this.mBackgroundManager;
    }

    @Override // android.app.Fragment
    public void onStart() {
        super.onStart();
        if (this.mBackgroundManager != null) {
            this.mBackgroundManager.onActivityStart();
        }
    }

    @Override // android.app.Fragment
    public void onResume() {
        super.onResume();
        if (this.mBackgroundManager != null) {
            this.mBackgroundManager.onResume();
        }
    }

    @Override // android.app.Fragment
    public void onStop() {
        if (this.mBackgroundManager != null) {
            this.mBackgroundManager.onStop();
        }
        super.onStop();
    }

    @Override // android.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        if (this.mBackgroundManager != null) {
            this.mBackgroundManager.detach();
        }
    }
}
