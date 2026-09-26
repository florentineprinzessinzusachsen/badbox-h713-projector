package androidx.core.f.c0;

import android.os.Bundle;
import android.text.style.ClickableSpan;
import android.view.View;

/* JADX INFO: compiled from: AccessibilityClickableSpanCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class a extends ClickableSpan {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final int f1059a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final b f1060b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final int f1061c;

    public a(int i, b bVar, int i2) {
        this.f1059a = i;
        this.f1060b = bVar;
        this.f1061c = i2;
    }

    @Override // android.text.style.ClickableSpan
    public void onClick(View view) {
        Bundle bundle = new Bundle();
        bundle.putInt("ACCESSIBILITY_CLICKABLE_SPAN_ID", this.f1059a);
        this.f1060b.a(this.f1061c, bundle);
    }
}
