package androidx.appcompat.app;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import androidx.appcompat.R$attr;

/* JADX INFO: compiled from: AlertDialog.java */
/* JADX INFO: loaded from: classes.dex */
public class c extends g implements DialogInterface {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final AlertController f294c;

    /* JADX INFO: compiled from: AlertDialog.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final AlertController.f f295a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final int f296b;

        public a(Context context) {
            this(context, c.a(context, 0));
        }

        public a a(CharSequence charSequence) {
            this.f295a.f240f = charSequence;
            return this;
        }

        public Context b() {
            return this.f295a.f235a;
        }

        public a(Context context, int i) {
            this.f295a = new AlertController.f(new ContextThemeWrapper(context, c.a(context, i)));
            this.f296b = i;
        }

        public a a(View view) {
            this.f295a.g = view;
            return this;
        }

        public a a(Drawable drawable) {
            this.f295a.f238d = drawable;
            return this;
        }

        public a a(DialogInterface.OnKeyListener onKeyListener) {
            this.f295a.u = onKeyListener;
            return this;
        }

        public a a(ListAdapter listAdapter, DialogInterface.OnClickListener onClickListener) {
            AlertController.f fVar = this.f295a;
            fVar.w = listAdapter;
            fVar.x = onClickListener;
            return this;
        }

        public a a(ListAdapter listAdapter, int i, DialogInterface.OnClickListener onClickListener) {
            AlertController.f fVar = this.f295a;
            fVar.w = listAdapter;
            fVar.x = onClickListener;
            fVar.I = i;
            fVar.H = true;
            return this;
        }

        public c a() {
            c cVar = new c(this.f295a.f235a, this.f296b);
            this.f295a.a(cVar.f294c);
            cVar.setCancelable(this.f295a.r);
            if (this.f295a.r) {
                cVar.setCanceledOnTouchOutside(true);
            }
            cVar.setOnCancelListener(this.f295a.s);
            cVar.setOnDismissListener(this.f295a.t);
            DialogInterface.OnKeyListener onKeyListener = this.f295a.u;
            if (onKeyListener != null) {
                cVar.setOnKeyListener(onKeyListener);
            }
            return cVar;
        }
    }

    protected c(Context context, int i) {
        super(context, a(context, i));
        this.f294c = new AlertController(getContext(), this, getWindow());
    }

    static int a(Context context, int i) {
        if (((i >>> 24) & 255) >= 1) {
            return i;
        }
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(R$attr.alertDialogTheme, typedValue, true);
        return typedValue.resourceId;
    }

    public ListView b() {
        return this.f294c.a();
    }

    @Override // androidx.appcompat.app.g, android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f294c.b();
    }

    @Override // android.app.Dialog, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (this.f294c.a(i, keyEvent)) {
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    @Override // android.app.Dialog, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (this.f294c.b(i, keyEvent)) {
            return true;
        }
        return super.onKeyUp(i, keyEvent);
    }

    @Override // androidx.appcompat.app.g, android.app.Dialog
    public void setTitle(CharSequence charSequence) {
        super.setTitle(charSequence);
        this.f294c.b(charSequence);
    }
}
