package androidx.core.f;

import android.os.Build;
import android.os.Bundle;
import android.text.style.ClickableSpan;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;
import androidx.core.R$id;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.List;

/* JADX INFO: compiled from: AccessibilityDelegateCompat.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final View.AccessibilityDelegate f1052c = new View.AccessibilityDelegate();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final View.AccessibilityDelegate f1053a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final View.AccessibilityDelegate f1054b;

    /* JADX INFO: renamed from: androidx.core.f.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: AccessibilityDelegateCompat.java */
    static final class C0022a extends View.AccessibilityDelegate {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final a f1055a;

        C0022a(a aVar) {
            this.f1055a = aVar;
        }

        @Override // android.view.View.AccessibilityDelegate
        public boolean dispatchPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            return this.f1055a.a(view, accessibilityEvent);
        }

        @Override // android.view.View.AccessibilityDelegate
        public AccessibilityNodeProvider getAccessibilityNodeProvider(View view) {
            androidx.core.f.c0.c cVarA = this.f1055a.a(view);
            if (cVarA != null) {
                return (AccessibilityNodeProvider) cVarA.a();
            }
            return null;
        }

        @Override // android.view.View.AccessibilityDelegate
        public void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            this.f1055a.b(view, accessibilityEvent);
        }

        @Override // android.view.View.AccessibilityDelegate
        public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
            androidx.core.f.c0.b bVarA = androidx.core.f.c0.b.a(accessibilityNodeInfo);
            bVarA.i(t.s(view));
            bVarA.g(t.p(view));
            bVarA.d(t.d(view));
            this.f1055a.a(view, bVarA);
            bVarA.a(accessibilityNodeInfo.getText(), view);
            List<androidx.core.f.c0.b.a> listB = a.b(view);
            for (int i = 0; i < listB.size(); i++) {
                bVarA.a(listB.get(i));
            }
        }

        @Override // android.view.View.AccessibilityDelegate
        public void onPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            this.f1055a.c(view, accessibilityEvent);
        }

        @Override // android.view.View.AccessibilityDelegate
        public boolean onRequestSendAccessibilityEvent(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
            return this.f1055a.a(viewGroup, view, accessibilityEvent);
        }

        @Override // android.view.View.AccessibilityDelegate
        public boolean performAccessibilityAction(View view, int i, Bundle bundle) {
            return this.f1055a.a(view, i, bundle);
        }

        @Override // android.view.View.AccessibilityDelegate
        public void sendAccessibilityEvent(View view, int i) {
            this.f1055a.a(view, i);
        }

        @Override // android.view.View.AccessibilityDelegate
        public void sendAccessibilityEventUnchecked(View view, AccessibilityEvent accessibilityEvent) {
            this.f1055a.d(view, accessibilityEvent);
        }
    }

    public a() {
        this(f1052c);
    }

    View.AccessibilityDelegate a() {
        return this.f1054b;
    }

    public void b(View view, AccessibilityEvent accessibilityEvent) {
        this.f1053a.onInitializeAccessibilityEvent(view, accessibilityEvent);
    }

    public void c(View view, AccessibilityEvent accessibilityEvent) {
        this.f1053a.onPopulateAccessibilityEvent(view, accessibilityEvent);
    }

    public void d(View view, AccessibilityEvent accessibilityEvent) {
        this.f1053a.sendAccessibilityEventUnchecked(view, accessibilityEvent);
    }

    public a(View.AccessibilityDelegate accessibilityDelegate) {
        this.f1053a = accessibilityDelegate;
        this.f1054b = new C0022a(this);
    }

    static List<androidx.core.f.c0.b.a> b(View view) {
        List<androidx.core.f.c0.b.a> list = (List) view.getTag(R$id.tag_accessibility_actions);
        return list == null ? Collections.emptyList() : list;
    }

    public void a(View view, int i) {
        this.f1053a.sendAccessibilityEvent(view, i);
    }

    public boolean a(View view, AccessibilityEvent accessibilityEvent) {
        return this.f1053a.dispatchPopulateAccessibilityEvent(view, accessibilityEvent);
    }

    public void a(View view, androidx.core.f.c0.b bVar) {
        this.f1053a.onInitializeAccessibilityNodeInfo(view, bVar.u());
    }

    public boolean a(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
        return this.f1053a.onRequestSendAccessibilityEvent(viewGroup, view, accessibilityEvent);
    }

    public androidx.core.f.c0.c a(View view) {
        AccessibilityNodeProvider accessibilityNodeProvider;
        if (Build.VERSION.SDK_INT < 16 || (accessibilityNodeProvider = this.f1053a.getAccessibilityNodeProvider(view)) == null) {
            return null;
        }
        return new androidx.core.f.c0.c(accessibilityNodeProvider);
    }

    public boolean a(View view, int i, Bundle bundle) {
        List<androidx.core.f.c0.b.a> listB = b(view);
        boolean zPerformAccessibilityAction = false;
        for (int i2 = 0; i2 < listB.size(); i2++) {
            androidx.core.f.c0.b.a aVar = listB.get(i2);
            if (aVar.a() == i) {
                zPerformAccessibilityAction = aVar.a(view, bundle);
                break;
            }
        }
        if (!zPerformAccessibilityAction && Build.VERSION.SDK_INT >= 16) {
            zPerformAccessibilityAction = this.f1053a.performAccessibilityAction(view, i, bundle);
        }
        return (zPerformAccessibilityAction || i != R$id.accessibility_action_clickable_span) ? zPerformAccessibilityAction : a(bundle.getInt("ACCESSIBILITY_CLICKABLE_SPAN_ID", -1), view);
    }

    private boolean a(int i, View view) {
        WeakReference weakReference;
        SparseArray sparseArray = (SparseArray) view.getTag(R$id.tag_accessibility_clickable_spans);
        if (sparseArray == null || (weakReference = (WeakReference) sparseArray.get(i)) == null) {
            return false;
        }
        ClickableSpan clickableSpan = (ClickableSpan) weakReference.get();
        if (!a(clickableSpan, view)) {
            return false;
        }
        clickableSpan.onClick(view);
        return true;
    }

    private boolean a(ClickableSpan clickableSpan, View view) {
        if (clickableSpan != null) {
            ClickableSpan[] clickableSpanArrE = androidx.core.f.c0.b.e(view.createAccessibilityNodeInfo().getText());
            for (int i = 0; clickableSpanArrE != null && i < clickableSpanArrE.length; i++) {
                if (clickableSpan.equals(clickableSpanArrE[i])) {
                    return true;
                }
            }
        }
        return false;
    }
}
