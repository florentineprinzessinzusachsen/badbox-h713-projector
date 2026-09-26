package androidx.appcompat.app;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.R$attr;

/* JADX INFO: compiled from: AppCompatDialog.java */
/* JADX INFO: loaded from: classes.dex */
public class g extends Dialog implements e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private f f300a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final androidx.core.f.d.a f301b;

    /* JADX INFO: compiled from: AppCompatDialog.java */
    class a implements androidx.core.f.d.a {
        a() {
        }

        @Override // androidx.core.f.d.a
        public boolean a(KeyEvent keyEvent) {
            return g.this.a(keyEvent);
        }
    }

    public g(Context context, int i) {
        super(context, a(context, i));
        this.f301b = new a();
        f fVarA = a();
        fVarA.d(a(context, i));
        fVarA.a((Bundle) null);
    }

    @Override // androidx.appcompat.app.e
    public androidx.appcompat.d.b a(androidx.appcompat.d.b.a aVar) {
        return null;
    }

    @Override // androidx.appcompat.app.e
    public void a(androidx.appcompat.d.b bVar) {
    }

    public boolean a(int i) {
        return a().b(i);
    }

    @Override // android.app.Dialog
    public void addContentView(View view, ViewGroup.LayoutParams layoutParams) {
        a().a(view, layoutParams);
    }

    @Override // androidx.appcompat.app.e
    public void b(androidx.appcompat.d.b bVar) {
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return androidx.core.f.d.a(this.f301b, getWindow().getDecorView(), this, keyEvent);
    }

    @Override // android.app.Dialog
    public <T extends View> T findViewById(int i) {
        return (T) a().a(i);
    }

    @Override // android.app.Dialog
    public void invalidateOptionsMenu() {
        a().e();
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        a().d();
        super.onCreate(bundle);
        a().a(bundle);
    }

    @Override // android.app.Dialog
    protected void onStop() {
        super.onStop();
        a().i();
    }

    @Override // android.app.Dialog
    public void setContentView(int i) {
        a().c(i);
    }

    @Override // android.app.Dialog
    public void setTitle(CharSequence charSequence) {
        super.setTitle(charSequence);
        a().a(charSequence);
    }

    public f a() {
        if (this.f300a == null) {
            this.f300a = f.a(this, this);
        }
        return this.f300a;
    }

    @Override // android.app.Dialog
    public void setContentView(View view) {
        a().a(view);
    }

    @Override // android.app.Dialog
    public void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        a().b(view, layoutParams);
    }

    @Override // android.app.Dialog
    public void setTitle(int i) {
        super.setTitle(i);
        a().a(getContext().getString(i));
    }

    private static int a(Context context, int i) {
        if (i != 0) {
            return i;
        }
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(R$attr.dialogTheme, typedValue, true);
        return typedValue.resourceId;
    }

    boolean a(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent);
    }
}
