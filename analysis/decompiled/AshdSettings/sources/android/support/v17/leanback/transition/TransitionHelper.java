package android.support.v17.leanback.transition;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RestrictTo;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public final class TransitionHelper {
    public static final int FADE_IN = 1;
    public static final int FADE_OUT = 2;
    public static final int SLIDE_BOTTOM = 80;
    public static final int SLIDE_LEFT = 3;
    public static final int SLIDE_RIGHT = 5;
    public static final int SLIDE_TOP = 48;
    private static TransitionHelperVersionImpl sImpl;

    interface TransitionHelperVersionImpl {
        void addSharedElement(FragmentTransaction fragmentTransaction, View view, String str);

        void addTarget(Object obj, View view);

        void addTransition(Object obj, Object obj2);

        void addTransitionListener(Object obj, TransitionListener transitionListener);

        void beginDelayedTransition(ViewGroup viewGroup, Object obj);

        Object createAutoTransition();

        Object createChangeBounds(boolean z);

        Object createChangeTransform();

        Object createDefaultInterpolator(Context context);

        Object createFadeAndShortSlide(int i);

        Object createFadeAndShortSlide(int i, float f);

        Object createFadeTransition(int i);

        Object createScale();

        Object createScene(ViewGroup viewGroup, Runnable runnable);

        Object createSlide(int i);

        Object createTransitionSet(boolean z);

        void exclude(Object obj, int i, boolean z);

        void exclude(Object obj, View view, boolean z);

        void excludeChildren(Object obj, int i, boolean z);

        void excludeChildren(Object obj, View view, boolean z);

        Object getEnterTransition(Window window);

        Object getExitTransition(Window window);

        Object getReenterTransition(Window window);

        Object getReturnTransition(Window window);

        Object getSharedElementEnterTransition(Window window);

        Object getSharedElementExitTransition(Window window);

        Object getSharedElementReenterTransition(Window window);

        Object getSharedElementReturnTransition(Window window);

        void include(Object obj, int i);

        void include(Object obj, View view);

        Object loadTransition(Context context, int i);

        void removeTransitionListener(Object obj, TransitionListener transitionListener);

        void runTransition(Object obj, Object obj2);

        void setChangeBoundsDefaultStartDelay(Object obj, int i);

        void setChangeBoundsStartDelay(Object obj, int i, int i2);

        void setChangeBoundsStartDelay(Object obj, View view, int i);

        void setChangeBoundsStartDelay(Object obj, String str, int i);

        void setDuration(Object obj, long j);

        void setEnterTransition(Fragment fragment, Object obj);

        void setEpicenterCallback(Object obj, TransitionEpicenterCallback transitionEpicenterCallback);

        void setExitTransition(Fragment fragment, Object obj);

        void setInterpolator(Object obj, Object obj2);

        void setSharedElementEnterTransition(Fragment fragment, Object obj);

        void setStartDelay(Object obj, long j);

        void setTransitionGroup(ViewGroup viewGroup, boolean z);
    }

    public static boolean systemSupportsTransitions() {
        return Build.VERSION.SDK_INT >= 19;
    }

    public static boolean systemSupportsEntranceTransitions() {
        return Build.VERSION.SDK_INT >= 21;
    }

    static class TransitionHelperStubImpl implements TransitionHelperVersionImpl {
        @Override // android.support.v17.leanback.transition.TransitionHelper.TransitionHelperVersionImpl
        public void addSharedElement(FragmentTransaction fragmentTransaction, View view, String str) {
        }

        @Override // android.support.v17.leanback.transition.TransitionHelper.TransitionHelperVersionImpl
        public void addTarget(Object obj, View view) {
        }

        @Override // android.support.v17.leanback.transition.TransitionHelper.TransitionHelperVersionImpl
        public void addTransition(Object obj, Object obj2) {
        }

        @Override // android.support.v17.leanback.transition.TransitionHelper.TransitionHelperVersionImpl
        public void beginDelayedTransition(ViewGroup viewGroup, Object obj) {
        }

        @Override // android.support.v17.leanback.transition.TransitionHelper.TransitionHelperVersionImpl
        public Object createDefaultInterpolator(Context context) {
            return null;
        }

        @Override // android.support.v17.leanback.transition.TransitionHelper.TransitionHelperVersionImpl
        public Object createScene(ViewGroup viewGroup, Runnable runnable) {
            return runnable;
        }

        @Override // android.support.v17.leanback.transition.TransitionHelper.TransitionHelperVersionImpl
        public void exclude(Object obj, int i, boolean z) {
        }

        @Override // android.support.v17.leanback.transition.TransitionHelper.TransitionHelperVersionImpl
        public void exclude(Object obj, View view, boolean z) {
        }

        @Override // android.support.v17.leanback.transition.TransitionHelper.TransitionHelperVersionImpl
        public void excludeChildren(Object obj, int i, boolean z) {
        }

        @Override // android.support.v17.leanback.transition.TransitionHelper.TransitionHelperVersionImpl
        public void excludeChildren(Object obj, View view, boolean z) {
        }

        @Override // android.support.v17.leanback.transition.TransitionHelper.TransitionHelperVersionImpl
        public Object getEnterTransition(Window window) {
            return null;
        }

        @Override // android.support.v17.leanback.transition.TransitionHelper.TransitionHelperVersionImpl
        public Object getExitTransition(Window window) {
            return null;
        }

        @Override // android.support.v17.leanback.transition.TransitionHelper.TransitionHelperVersionImpl
        public Object getReenterTransition(Window window) {
            return null;
        }

        @Override // android.support.v17.leanback.transition.TransitionHelper.TransitionHelperVersionImpl
        public Object getReturnTransition(Window window) {
            return null;
        }

        @Override // android.support.v17.leanback.transition.TransitionHelper.TransitionHelperVersionImpl
        public Object getSharedElementEnterTransition(Window window) {
            return null;
        }

        @Override // android.support.v17.leanback.transition.TransitionHelper.TransitionHelperVersionImpl
        public Object getSharedElementExitTransition(Window window) {
            return null;
        }

        @Override // android.support.v17.leanback.transition.TransitionHelper.TransitionHelperVersionImpl
        public Object getSharedElementReenterTransition(Window window) {
            return null;
        }

        @Override // android.support.v17.leanback.transition.TransitionHelper.TransitionHelperVersionImpl
        public Object getSharedElementReturnTransition(Window window) {
            return null;
        }

        @Override // android.support.v17.leanback.transition.TransitionHelper.TransitionHelperVersionImpl
        public void include(Object obj, int i) {
        }

        @Override // android.support.v17.leanback.transition.TransitionHelper.TransitionHelperVersionImpl
        public void include(Object obj, View view) {
        }

        @Override // android.support.v17.leanback.transition.TransitionHelper.TransitionHelperVersionImpl
        public void setChangeBoundsDefaultStartDelay(Object obj, int i) {
        }

        @Override // android.support.v17.leanback.transition.TransitionHelper.TransitionHelperVersionImpl
        public void setChangeBoundsStartDelay(Object obj, int i, int i2) {
        }

        @Override // android.support.v17.leanback.transition.TransitionHelper.TransitionHelperVersionImpl
        public void setChangeBoundsStartDelay(Object obj, View view, int i) {
        }

        @Override // android.support.v17.leanback.transition.TransitionHelper.TransitionHelperVersionImpl
        public void setChangeBoundsStartDelay(Object obj, String str, int i) {
        }

        @Override // android.support.v17.leanback.transition.TransitionHelper.TransitionHelperVersionImpl
        public void setDuration(Object obj, long j) {
        }

        @Override // android.support.v17.leanback.transition.TransitionHelper.TransitionHelperVersionImpl
        public void setEnterTransition(Fragment fragment, Object obj) {
        }

        @Override // android.support.v17.leanback.transition.TransitionHelper.TransitionHelperVersionImpl
        public void setEpicenterCallback(Object obj, TransitionEpicenterCallback transitionEpicenterCallback) {
        }

        @Override // android.support.v17.leanback.transition.TransitionHelper.TransitionHelperVersionImpl
        public void setExitTransition(Fragment fragment, Object obj) {
        }

        @Override // android.support.v17.leanback.transition.TransitionHelper.TransitionHelperVersionImpl
        public void setInterpolator(Object obj, Object obj2) {
        }

        @Override // android.support.v17.leanback.transition.TransitionHelper.TransitionHelperVersionImpl
        public void setSharedElementEnterTransition(Fragment fragment, Object obj) {
        }

        @Override // android.support.v17.leanback.transition.TransitionHelper.TransitionHelperVersionImpl
        public void setStartDelay(Object obj, long j) {
        }

        @Override // android.support.v17.leanback.transition.TransitionHelper.TransitionHelperVersionImpl
        public void setTransitionGroup(ViewGroup viewGroup, boolean z) {
        }

        TransitionHelperStubImpl() {
        }

        private static class TransitionStub {
            ArrayList<TransitionListener> mTransitionListeners;

            TransitionStub() {
            }
        }

        @Override // android.support.v17.leanback.transition.TransitionHelper.TransitionHelperVersionImpl
        public Object createAutoTransition() {
            return new TransitionStub();
        }

        @Override // android.support.v17.leanback.transition.TransitionHelper.TransitionHelperVersionImpl
        public Object createFadeTransition(int i) {
            return new TransitionStub();
        }

        @Override // android.support.v17.leanback.transition.TransitionHelper.TransitionHelperVersionImpl
        public Object createChangeBounds(boolean z) {
            return new TransitionStub();
        }

        @Override // android.support.v17.leanback.transition.TransitionHelper.TransitionHelperVersionImpl
        public Object createChangeTransform() {
            return new TransitionStub();
        }

        @Override // android.support.v17.leanback.transition.TransitionHelper.TransitionHelperVersionImpl
        public Object createFadeAndShortSlide(int i) {
            return new TransitionStub();
        }

        @Override // android.support.v17.leanback.transition.TransitionHelper.TransitionHelperVersionImpl
        public Object createFadeAndShortSlide(int i, float f) {
            return new TransitionStub();
        }

        @Override // android.support.v17.leanback.transition.TransitionHelper.TransitionHelperVersionImpl
        public Object createSlide(int i) {
            return new TransitionStub();
        }

        @Override // android.support.v17.leanback.transition.TransitionHelper.TransitionHelperVersionImpl
        public Object createScale() {
            return new TransitionStub();
        }

        @Override // android.support.v17.leanback.transition.TransitionHelper.TransitionHelperVersionImpl
        public Object createTransitionSet(boolean z) {
            return new TransitionStub();
        }

        @Override // android.support.v17.leanback.transition.TransitionHelper.TransitionHelperVersionImpl
        public void addTransitionListener(Object obj, TransitionListener transitionListener) {
            TransitionStub transitionStub = (TransitionStub) obj;
            if (transitionStub.mTransitionListeners == null) {
                transitionStub.mTransitionListeners = new ArrayList<>();
            }
            transitionStub.mTransitionListeners.add(transitionListener);
        }

        @Override // android.support.v17.leanback.transition.TransitionHelper.TransitionHelperVersionImpl
        public void removeTransitionListener(Object obj, TransitionListener transitionListener) {
            TransitionStub transitionStub = (TransitionStub) obj;
            if (transitionStub.mTransitionListeners != null) {
                transitionStub.mTransitionListeners.remove(transitionListener);
            }
        }

        @Override // android.support.v17.leanback.transition.TransitionHelper.TransitionHelperVersionImpl
        public void runTransition(Object obj, Object obj2) {
            TransitionStub transitionStub = (TransitionStub) obj2;
            if (transitionStub != null && transitionStub.mTransitionListeners != null) {
                int size = transitionStub.mTransitionListeners.size();
                for (int i = 0; i < size; i++) {
                    transitionStub.mTransitionListeners.get(i).onTransitionStart(obj2);
                }
            }
            Runnable runnable = (Runnable) obj;
            if (runnable != null) {
                runnable.run();
            }
            if (transitionStub == null || transitionStub.mTransitionListeners == null) {
                return;
            }
            int size2 = transitionStub.mTransitionListeners.size();
            for (int i2 = 0; i2 < size2; i2++) {
                transitionStub.mTransitionListeners.get(i2).onTransitionEnd(obj2);
            }
        }

        @Override // android.support.v17.leanback.transition.TransitionHelper.TransitionHelperVersionImpl
        public Object loadTransition(Context context, int i) {
            return new TransitionStub();
        }
    }

    static class TransitionHelperKitkatImpl extends TransitionHelperStubImpl {
        @Override // android.support.v17.leanback.transition.TransitionHelper.TransitionHelperStubImpl, android.support.v17.leanback.transition.TransitionHelper.TransitionHelperVersionImpl
        public Object createDefaultInterpolator(Context context) {
            return null;
        }

        TransitionHelperKitkatImpl() {
        }

        @Override // android.support.v17.leanback.transition.TransitionHelper.TransitionHelperStubImpl, android.support.v17.leanback.transition.TransitionHelper.TransitionHelperVersionImpl
        public Object createScene(ViewGroup viewGroup, Runnable runnable) {
            return TransitionHelperKitkat.createScene(viewGroup, runnable);
        }

        @Override // android.support.v17.leanback.transition.TransitionHelper.TransitionHelperStubImpl, android.support.v17.leanback.transition.TransitionHelper.TransitionHelperVersionImpl
        public Object createAutoTransition() {
            return TransitionHelperKitkat.createAutoTransition();
        }

        @Override // android.support.v17.leanback.transition.TransitionHelper.TransitionHelperStubImpl, android.support.v17.leanback.transition.TransitionHelper.TransitionHelperVersionImpl
        public Object createFadeTransition(int i) {
            return TransitionHelperKitkat.createFadeTransition(i);
        }

        @Override // android.support.v17.leanback.transition.TransitionHelper.TransitionHelperStubImpl, android.support.v17.leanback.transition.TransitionHelper.TransitionHelperVersionImpl
        public Object createChangeBounds(boolean z) {
            return TransitionHelperKitkat.createChangeBounds(z);
        }

        @Override // android.support.v17.leanback.transition.TransitionHelper.TransitionHelperStubImpl, android.support.v17.leanback.transition.TransitionHelper.TransitionHelperVersionImpl
        public Object createSlide(int i) {
            return TransitionHelperKitkat.createSlide(i);
        }

        @Override // android.support.v17.leanback.transition.TransitionHelper.TransitionHelperStubImpl, android.support.v17.leanback.transition.TransitionHelper.TransitionHelperVersionImpl
        public Object createScale() {
            return TransitionHelperKitkat.createScale();
        }

        @Override // android.support.v17.leanback.transition.TransitionHelper.TransitionHelperStubImpl, android.support.v17.leanback.transition.TransitionHelper.TransitionHelperVersionImpl
        public void setChangeBoundsStartDelay(Object obj, View view, int i) {
            TransitionHelperKitkat.setChangeBoundsStartDelay(obj, view, i);
        }

        @Override // android.support.v17.leanback.transition.TransitionHelper.TransitionHelperStubImpl, android.support.v17.leanback.transition.TransitionHelper.TransitionHelperVersionImpl
        public void setChangeBoundsStartDelay(Object obj, int i, int i2) {
            TransitionHelperKitkat.setChangeBoundsStartDelay(obj, i, i2);
        }

        @Override // android.support.v17.leanback.transition.TransitionHelper.TransitionHelperStubImpl, android.support.v17.leanback.transition.TransitionHelper.TransitionHelperVersionImpl
        public void setChangeBoundsStartDelay(Object obj, String str, int i) {
            TransitionHelperKitkat.setChangeBoundsStartDelay(obj, str, i);
        }

        @Override // android.support.v17.leanback.transition.TransitionHelper.TransitionHelperStubImpl, android.support.v17.leanback.transition.TransitionHelper.TransitionHelperVersionImpl
        public void setChangeBoundsDefaultStartDelay(Object obj, int i) {
            TransitionHelperKitkat.setChangeBoundsDefaultStartDelay(obj, i);
        }

        @Override // android.support.v17.leanback.transition.TransitionHelper.TransitionHelperStubImpl, android.support.v17.leanback.transition.TransitionHelper.TransitionHelperVersionImpl
        public Object createTransitionSet(boolean z) {
            return TransitionHelperKitkat.createTransitionSet(z);
        }

        @Override // android.support.v17.leanback.transition.TransitionHelper.TransitionHelperStubImpl, android.support.v17.leanback.transition.TransitionHelper.TransitionHelperVersionImpl
        public void addTransition(Object obj, Object obj2) {
            TransitionHelperKitkat.addTransition(obj, obj2);
        }

        @Override // android.support.v17.leanback.transition.TransitionHelper.TransitionHelperStubImpl, android.support.v17.leanback.transition.TransitionHelper.TransitionHelperVersionImpl
        public void exclude(Object obj, int i, boolean z) {
            TransitionHelperKitkat.exclude(obj, i, z);
        }

        @Override // android.support.v17.leanback.transition.TransitionHelper.TransitionHelperStubImpl, android.support.v17.leanback.transition.TransitionHelper.TransitionHelperVersionImpl
        public void exclude(Object obj, View view, boolean z) {
            TransitionHelperKitkat.exclude(obj, view, z);
        }

        @Override // android.support.v17.leanback.transition.TransitionHelper.TransitionHelperStubImpl, android.support.v17.leanback.transition.TransitionHelper.TransitionHelperVersionImpl
        public void excludeChildren(Object obj, int i, boolean z) {
            TransitionHelperKitkat.excludeChildren(obj, i, z);
        }

        @Override // android.support.v17.leanback.transition.TransitionHelper.TransitionHelperStubImpl, android.support.v17.leanback.transition.TransitionHelper.TransitionHelperVersionImpl
        public void excludeChildren(Object obj, View view, boolean z) {
            TransitionHelperKitkat.excludeChildren(obj, view, z);
        }

        @Override // android.support.v17.leanback.transition.TransitionHelper.TransitionHelperStubImpl, android.support.v17.leanback.transition.TransitionHelper.TransitionHelperVersionImpl
        public void include(Object obj, int i) {
            TransitionHelperKitkat.include(obj, i);
        }

        @Override // android.support.v17.leanback.transition.TransitionHelper.TransitionHelperStubImpl, android.support.v17.leanback.transition.TransitionHelper.TransitionHelperVersionImpl
        public void include(Object obj, View view) {
            TransitionHelperKitkat.include(obj, view);
        }

        @Override // android.support.v17.leanback.transition.TransitionHelper.TransitionHelperStubImpl, android.support.v17.leanback.transition.TransitionHelper.TransitionHelperVersionImpl
        public void setStartDelay(Object obj, long j) {
            TransitionHelperKitkat.setStartDelay(obj, j);
        }

        @Override // android.support.v17.leanback.transition.TransitionHelper.TransitionHelperStubImpl, android.support.v17.leanback.transition.TransitionHelper.TransitionHelperVersionImpl
        public void setDuration(Object obj, long j) {
            TransitionHelperKitkat.setDuration(obj, j);
        }

        @Override // android.support.v17.leanback.transition.TransitionHelper.TransitionHelperStubImpl, android.support.v17.leanback.transition.TransitionHelper.TransitionHelperVersionImpl
        public void addTransitionListener(Object obj, TransitionListener transitionListener) {
            TransitionHelperKitkat.addTransitionListener(obj, transitionListener);
        }

        @Override // android.support.v17.leanback.transition.TransitionHelper.TransitionHelperStubImpl, android.support.v17.leanback.transition.TransitionHelper.TransitionHelperVersionImpl
        public void removeTransitionListener(Object obj, TransitionListener transitionListener) {
            TransitionHelperKitkat.removeTransitionListener(obj, transitionListener);
        }

        @Override // android.support.v17.leanback.transition.TransitionHelper.TransitionHelperStubImpl, android.support.v17.leanback.transition.TransitionHelper.TransitionHelperVersionImpl
        public void runTransition(Object obj, Object obj2) {
            TransitionHelperKitkat.runTransition(obj, obj2);
        }

        @Override // android.support.v17.leanback.transition.TransitionHelper.TransitionHelperStubImpl, android.support.v17.leanback.transition.TransitionHelper.TransitionHelperVersionImpl
        public void setInterpolator(Object obj, Object obj2) {
            TransitionHelperKitkat.setInterpolator(obj, obj2);
        }

        @Override // android.support.v17.leanback.transition.TransitionHelper.TransitionHelperStubImpl, android.support.v17.leanback.transition.TransitionHelper.TransitionHelperVersionImpl
        public void addTarget(Object obj, View view) {
            TransitionHelperKitkat.addTarget(obj, view);
        }

        @Override // android.support.v17.leanback.transition.TransitionHelper.TransitionHelperStubImpl, android.support.v17.leanback.transition.TransitionHelper.TransitionHelperVersionImpl
        public Object loadTransition(Context context, int i) {
            return TransitionHelperKitkat.loadTransition(context, i);
        }
    }

    static final class TransitionHelperApi21Impl extends TransitionHelperKitkatImpl {
        TransitionHelperApi21Impl() {
        }

        @Override // android.support.v17.leanback.transition.TransitionHelper.TransitionHelperStubImpl, android.support.v17.leanback.transition.TransitionHelper.TransitionHelperVersionImpl
        public void setEnterTransition(Fragment fragment, Object obj) {
            TransitionHelperApi21.setEnterTransition(fragment, obj);
        }

        @Override // android.support.v17.leanback.transition.TransitionHelper.TransitionHelperStubImpl, android.support.v17.leanback.transition.TransitionHelper.TransitionHelperVersionImpl
        public void setExitTransition(Fragment fragment, Object obj) {
            TransitionHelperApi21.setExitTransition(fragment, obj);
        }

        @Override // android.support.v17.leanback.transition.TransitionHelper.TransitionHelperStubImpl, android.support.v17.leanback.transition.TransitionHelper.TransitionHelperVersionImpl
        public void setSharedElementEnterTransition(Fragment fragment, Object obj) {
            TransitionHelperApi21.setSharedElementEnterTransition(fragment, obj);
        }

        @Override // android.support.v17.leanback.transition.TransitionHelper.TransitionHelperStubImpl, android.support.v17.leanback.transition.TransitionHelper.TransitionHelperVersionImpl
        public void addSharedElement(FragmentTransaction fragmentTransaction, View view, String str) {
            TransitionHelperApi21.addSharedElement(fragmentTransaction, view, str);
        }

        @Override // android.support.v17.leanback.transition.TransitionHelper.TransitionHelperStubImpl, android.support.v17.leanback.transition.TransitionHelper.TransitionHelperVersionImpl
        public Object getSharedElementEnterTransition(Window window) {
            return TransitionHelperApi21.getSharedElementEnterTransition(window);
        }

        @Override // android.support.v17.leanback.transition.TransitionHelper.TransitionHelperStubImpl, android.support.v17.leanback.transition.TransitionHelper.TransitionHelperVersionImpl
        public Object getSharedElementReturnTransition(Window window) {
            return TransitionHelperApi21.getSharedElementReturnTransition(window);
        }

        @Override // android.support.v17.leanback.transition.TransitionHelper.TransitionHelperStubImpl, android.support.v17.leanback.transition.TransitionHelper.TransitionHelperVersionImpl
        public Object getSharedElementExitTransition(Window window) {
            return TransitionHelperApi21.getSharedElementExitTransition(window);
        }

        @Override // android.support.v17.leanback.transition.TransitionHelper.TransitionHelperStubImpl, android.support.v17.leanback.transition.TransitionHelper.TransitionHelperVersionImpl
        public Object getSharedElementReenterTransition(Window window) {
            return TransitionHelperApi21.getSharedElementReenterTransition(window);
        }

        @Override // android.support.v17.leanback.transition.TransitionHelper.TransitionHelperStubImpl, android.support.v17.leanback.transition.TransitionHelper.TransitionHelperVersionImpl
        public Object createFadeAndShortSlide(int i) {
            return TransitionHelperApi21.createFadeAndShortSlide(i);
        }

        @Override // android.support.v17.leanback.transition.TransitionHelper.TransitionHelperStubImpl, android.support.v17.leanback.transition.TransitionHelper.TransitionHelperVersionImpl
        public Object createFadeAndShortSlide(int i, float f) {
            return TransitionHelperApi21.createFadeAndShortSlide(i, f);
        }

        @Override // android.support.v17.leanback.transition.TransitionHelper.TransitionHelperStubImpl, android.support.v17.leanback.transition.TransitionHelper.TransitionHelperVersionImpl
        public void beginDelayedTransition(ViewGroup viewGroup, Object obj) {
            TransitionHelperApi21.beginDelayedTransition(viewGroup, obj);
        }

        @Override // android.support.v17.leanback.transition.TransitionHelper.TransitionHelperStubImpl, android.support.v17.leanback.transition.TransitionHelper.TransitionHelperVersionImpl
        public Object getEnterTransition(Window window) {
            return TransitionHelperApi21.getEnterTransition(window);
        }

        @Override // android.support.v17.leanback.transition.TransitionHelper.TransitionHelperStubImpl, android.support.v17.leanback.transition.TransitionHelper.TransitionHelperVersionImpl
        public Object getReturnTransition(Window window) {
            return TransitionHelperApi21.getReturnTransition(window);
        }

        @Override // android.support.v17.leanback.transition.TransitionHelper.TransitionHelperStubImpl, android.support.v17.leanback.transition.TransitionHelper.TransitionHelperVersionImpl
        public Object getExitTransition(Window window) {
            return TransitionHelperApi21.getExitTransition(window);
        }

        @Override // android.support.v17.leanback.transition.TransitionHelper.TransitionHelperStubImpl, android.support.v17.leanback.transition.TransitionHelper.TransitionHelperVersionImpl
        public Object getReenterTransition(Window window) {
            return TransitionHelperApi21.getReenterTransition(window);
        }

        @Override // android.support.v17.leanback.transition.TransitionHelper.TransitionHelperKitkatImpl, android.support.v17.leanback.transition.TransitionHelper.TransitionHelperStubImpl, android.support.v17.leanback.transition.TransitionHelper.TransitionHelperVersionImpl
        public Object createScale() {
            return TransitionHelperApi21.createScale();
        }

        @Override // android.support.v17.leanback.transition.TransitionHelper.TransitionHelperKitkatImpl, android.support.v17.leanback.transition.TransitionHelper.TransitionHelperStubImpl, android.support.v17.leanback.transition.TransitionHelper.TransitionHelperVersionImpl
        public Object createDefaultInterpolator(Context context) {
            return TransitionHelperApi21.createDefaultInterpolator(context);
        }

        @Override // android.support.v17.leanback.transition.TransitionHelper.TransitionHelperStubImpl, android.support.v17.leanback.transition.TransitionHelper.TransitionHelperVersionImpl
        public void setTransitionGroup(ViewGroup viewGroup, boolean z) {
            TransitionHelperApi21.setTransitionGroup(viewGroup, z);
        }

        @Override // android.support.v17.leanback.transition.TransitionHelper.TransitionHelperStubImpl, android.support.v17.leanback.transition.TransitionHelper.TransitionHelperVersionImpl
        public Object createChangeTransform() {
            return TransitionHelperApi21.createChangeTransform();
        }

        @Override // android.support.v17.leanback.transition.TransitionHelper.TransitionHelperStubImpl, android.support.v17.leanback.transition.TransitionHelper.TransitionHelperVersionImpl
        public void setEpicenterCallback(Object obj, TransitionEpicenterCallback transitionEpicenterCallback) {
            TransitionHelperApi21.setEpicenterCallback(obj, transitionEpicenterCallback);
        }
    }

    static {
        if (Build.VERSION.SDK_INT >= 21) {
            sImpl = new TransitionHelperApi21Impl();
        } else if (systemSupportsTransitions()) {
            sImpl = new TransitionHelperKitkatImpl();
        } else {
            sImpl = new TransitionHelperStubImpl();
        }
    }

    public static Object getSharedElementEnterTransition(Window window) {
        return sImpl.getSharedElementEnterTransition(window);
    }

    public static Object getSharedElementReturnTransition(Window window) {
        return sImpl.getSharedElementReturnTransition(window);
    }

    public static Object getSharedElementExitTransition(Window window) {
        return sImpl.getSharedElementExitTransition(window);
    }

    public static Object getSharedElementReenterTransition(Window window) {
        return sImpl.getSharedElementReenterTransition(window);
    }

    public static Object getEnterTransition(Window window) {
        return sImpl.getEnterTransition(window);
    }

    public static Object getReturnTransition(Window window) {
        return sImpl.getReturnTransition(window);
    }

    public static Object getExitTransition(Window window) {
        return sImpl.getExitTransition(window);
    }

    public static Object getReenterTransition(Window window) {
        return sImpl.getReenterTransition(window);
    }

    public static Object createScene(ViewGroup viewGroup, Runnable runnable) {
        return sImpl.createScene(viewGroup, runnable);
    }

    public static Object createChangeBounds(boolean z) {
        return sImpl.createChangeBounds(z);
    }

    public static Object createChangeTransform() {
        return sImpl.createChangeTransform();
    }

    public static void setChangeBoundsStartDelay(Object obj, View view, int i) {
        sImpl.setChangeBoundsStartDelay(obj, view, i);
    }

    public static void setChangeBoundsStartDelay(Object obj, int i, int i2) {
        sImpl.setChangeBoundsStartDelay(obj, i, i2);
    }

    public static void setChangeBoundsStartDelay(Object obj, String str, int i) {
        sImpl.setChangeBoundsStartDelay(obj, str, i);
    }

    public static void setChangeBoundsDefaultStartDelay(Object obj, int i) {
        sImpl.setChangeBoundsDefaultStartDelay(obj, i);
    }

    public static Object createTransitionSet(boolean z) {
        return sImpl.createTransitionSet(z);
    }

    public static Object createSlide(int i) {
        return sImpl.createSlide(i);
    }

    public static Object createScale() {
        return sImpl.createScale();
    }

    public static void addTransition(Object obj, Object obj2) {
        sImpl.addTransition(obj, obj2);
    }

    public static void exclude(Object obj, int i, boolean z) {
        sImpl.exclude(obj, i, z);
    }

    public static void exclude(Object obj, View view, boolean z) {
        sImpl.exclude(obj, view, z);
    }

    public static void excludeChildren(Object obj, int i, boolean z) {
        sImpl.excludeChildren(obj, i, z);
    }

    public static void excludeChildren(Object obj, View view, boolean z) {
        sImpl.excludeChildren(obj, view, z);
    }

    public static void include(Object obj, int i) {
        sImpl.include(obj, i);
    }

    public static void include(Object obj, View view) {
        sImpl.include(obj, view);
    }

    public static void setStartDelay(Object obj, long j) {
        sImpl.setStartDelay(obj, j);
    }

    public static void setDuration(Object obj, long j) {
        sImpl.setDuration(obj, j);
    }

    public static Object createAutoTransition() {
        return sImpl.createAutoTransition();
    }

    public static Object createFadeTransition(int i) {
        return sImpl.createFadeTransition(i);
    }

    public static void addTransitionListener(Object obj, TransitionListener transitionListener) {
        sImpl.addTransitionListener(obj, transitionListener);
    }

    public static void removeTransitionListener(Object obj, TransitionListener transitionListener) {
        sImpl.removeTransitionListener(obj, transitionListener);
    }

    public static void runTransition(Object obj, Object obj2) {
        sImpl.runTransition(obj, obj2);
    }

    public static void setInterpolator(Object obj, Object obj2) {
        sImpl.setInterpolator(obj, obj2);
    }

    public static void addTarget(Object obj, View view) {
        sImpl.addTarget(obj, view);
    }

    public static Object createDefaultInterpolator(Context context) {
        return sImpl.createDefaultInterpolator(context);
    }

    public static Object loadTransition(Context context, int i) {
        return sImpl.loadTransition(context, i);
    }

    public static void setEnterTransition(Fragment fragment, Object obj) {
        sImpl.setEnterTransition(fragment, obj);
    }

    public static void setExitTransition(Fragment fragment, Object obj) {
        sImpl.setExitTransition(fragment, obj);
    }

    public static void setSharedElementEnterTransition(Fragment fragment, Object obj) {
        sImpl.setSharedElementEnterTransition(fragment, obj);
    }

    public static void addSharedElement(FragmentTransaction fragmentTransaction, View view, String str) {
        sImpl.addSharedElement(fragmentTransaction, view, str);
    }

    public static void setEnterTransition(android.support.v4.app.Fragment fragment, Object obj) {
        fragment.setEnterTransition(obj);
    }

    public static void setExitTransition(android.support.v4.app.Fragment fragment, Object obj) {
        fragment.setExitTransition(obj);
    }

    public static void setSharedElementEnterTransition(android.support.v4.app.Fragment fragment, Object obj) {
        fragment.setSharedElementEnterTransition(obj);
    }

    public static void addSharedElement(android.support.v4.app.FragmentTransaction fragmentTransaction, View view, String str) {
        fragmentTransaction.addSharedElement(view, str);
    }

    public static Object createFadeAndShortSlide(int i) {
        return sImpl.createFadeAndShortSlide(i);
    }

    public static Object createFadeAndShortSlide(int i, float f) {
        return sImpl.createFadeAndShortSlide(i, f);
    }

    public static void beginDelayedTransition(ViewGroup viewGroup, Object obj) {
        sImpl.beginDelayedTransition(viewGroup, obj);
    }

    public static void setTransitionGroup(ViewGroup viewGroup, boolean z) {
        sImpl.setTransitionGroup(viewGroup, z);
    }

    public static void setEpicenterCallback(Object obj, TransitionEpicenterCallback transitionEpicenterCallback) {
        sImpl.setEpicenterCallback(obj, transitionEpicenterCallback);
    }

    @Deprecated
    public static TransitionHelper getInstance() {
        return new TransitionHelper();
    }

    @Deprecated
    public static void setTransitionListener(Object obj, TransitionListener transitionListener) {
        sImpl.addTransitionListener(obj, transitionListener);
    }
}
