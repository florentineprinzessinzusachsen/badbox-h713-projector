package androidx.appcompat.view.menu;

import android.content.DialogInterface;
import android.os.IBinder;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import androidx.appcompat.R$layout;

/* JADX INFO: compiled from: MenuDialogHelper.java */
/* JADX INFO: loaded from: classes.dex */
class h implements DialogInterface.OnKeyListener, DialogInterface.OnClickListener, DialogInterface.OnDismissListener, m.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private g f473a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private androidx.appcompat.app.c f474b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    e f475c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private m.a f476d;

    public h(g gVar) {
        this.f473a = gVar;
    }

    public void a(IBinder iBinder) {
        g gVar = this.f473a;
        androidx.appcompat.app.c.a aVar = new androidx.appcompat.app.c.a(gVar.e());
        this.f475c = new e(aVar.b(), R$layout.abc_list_menu_item_layout);
        this.f475c.a(this);
        this.f473a.a(this.f475c);
        aVar.a(this.f475c.b(), this);
        View viewI = gVar.i();
        if (viewI != null) {
            aVar.a(viewI);
        } else {
            aVar.a(gVar.g());
            aVar.a(gVar.h());
        }
        aVar.a(this);
        this.f474b = aVar.a();
        this.f474b.setOnDismissListener(this);
        WindowManager.LayoutParams attributes = this.f474b.getWindow().getAttributes();
        attributes.type = 1003;
        if (iBinder != null) {
            attributes.token = iBinder;
        }
        attributes.flags |= 131072;
        this.f474b.show();
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        this.f473a.a((j) this.f475c.b().getItem(i), 0);
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialogInterface) {
        this.f475c.a(this.f473a, true);
    }

    @Override // android.content.DialogInterface.OnKeyListener
    public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
        Window window;
        View decorView;
        KeyEvent.DispatcherState keyDispatcherState;
        View decorView2;
        KeyEvent.DispatcherState keyDispatcherState2;
        if (i == 82 || i == 4) {
            if (keyEvent.getAction() == 0 && keyEvent.getRepeatCount() == 0) {
                Window window2 = this.f474b.getWindow();
                if (window2 != null && (decorView2 = window2.getDecorView()) != null && (keyDispatcherState2 = decorView2.getKeyDispatcherState()) != null) {
                    keyDispatcherState2.startTracking(keyEvent, this);
                    return true;
                }
            } else if (keyEvent.getAction() == 1 && !keyEvent.isCanceled() && (window = this.f474b.getWindow()) != null && (decorView = window.getDecorView()) != null && (keyDispatcherState = decorView.getKeyDispatcherState()) != null && keyDispatcherState.isTracking(keyEvent)) {
                this.f473a.a(true);
                dialogInterface.dismiss();
                return true;
            }
        }
        return this.f473a.performShortcut(i, keyEvent, 0);
    }

    public void a() {
        androidx.appcompat.app.c cVar = this.f474b;
        if (cVar != null) {
            cVar.dismiss();
        }
    }

    @Override // androidx.appcompat.view.menu.m.a
    public void a(g gVar, boolean z) {
        if (z || gVar == this.f473a) {
            a();
        }
        m.a aVar = this.f476d;
        if (aVar != null) {
            aVar.a(gVar, z);
        }
    }

    @Override // androidx.appcompat.view.menu.m.a
    public boolean a(g gVar) {
        m.a aVar = this.f476d;
        if (aVar != null) {
            return aVar.a(gVar);
        }
        return false;
    }
}
