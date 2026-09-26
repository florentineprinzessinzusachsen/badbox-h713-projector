package android.support.v17.leanback.widget;

import android.animation.Animator;
import android.support.annotation.NonNull;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface FragmentAnimationProvider {
    void onImeAppearing(@NonNull List<Animator> list);

    void onImeDisappearing(@NonNull List<Animator> list);
}
