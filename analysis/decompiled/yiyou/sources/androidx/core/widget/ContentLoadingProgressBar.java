package androidx.core.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ProgressBar;

/* JADX INFO: loaded from: classes.dex */
public class ContentLoadingProgressBar extends ProgressBar {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    long f1133a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    boolean f1134b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    boolean f1135c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    boolean f1136d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final Runnable f1137e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final Runnable f1138f;

    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ContentLoadingProgressBar contentLoadingProgressBar = ContentLoadingProgressBar.this;
            contentLoadingProgressBar.f1134b = false;
            contentLoadingProgressBar.f1133a = -1L;
            contentLoadingProgressBar.setVisibility(8);
        }
    }

    class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ContentLoadingProgressBar contentLoadingProgressBar = ContentLoadingProgressBar.this;
            contentLoadingProgressBar.f1135c = false;
            if (contentLoadingProgressBar.f1136d) {
                return;
            }
            contentLoadingProgressBar.f1133a = System.currentTimeMillis();
            ContentLoadingProgressBar.this.setVisibility(0);
        }
    }

    public ContentLoadingProgressBar(Context context) {
        this(context, null);
    }

    private void a() {
        removeCallbacks(this.f1137e);
        removeCallbacks(this.f1138f);
    }

    @Override // android.widget.ProgressBar, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        a();
    }

    @Override // android.widget.ProgressBar, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        a();
    }

    public ContentLoadingProgressBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
        this.f1136d = false;
        this.f1137e = new a();
        this.f1138f = new b();
    }
}
