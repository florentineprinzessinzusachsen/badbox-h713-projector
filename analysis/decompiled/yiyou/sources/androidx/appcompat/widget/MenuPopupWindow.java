package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.transition.Transition;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.PopupWindow;
import androidx.appcompat.view.menu.ListMenuItemView;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public class MenuPopupWindow extends s implements t {
    private static Method K;
    private t J;

    public static class MenuDropDownListView extends DropDownListView {
        final int o;
        final int p;
        private t q;
        private MenuItem r;

        public MenuDropDownListView(Context context, boolean z) {
            super(context, z);
            Configuration configuration = context.getResources().getConfiguration();
            if (Build.VERSION.SDK_INT < 17 || 1 != configuration.getLayoutDirection()) {
                this.o = 22;
                this.p = 21;
            } else {
                this.o = 21;
                this.p = 22;
            }
        }

        @Override // androidx.appcompat.widget.DropDownListView, android.view.View
        public boolean onHoverEvent(MotionEvent motionEvent) {
            int headersCount;
            androidx.appcompat.view.menu.f fVar;
            int iPointToPosition;
            int i;
            if (this.q != null) {
                ListAdapter adapter = getAdapter();
                if (adapter instanceof HeaderViewListAdapter) {
                    HeaderViewListAdapter headerViewListAdapter = (HeaderViewListAdapter) adapter;
                    headersCount = headerViewListAdapter.getHeadersCount();
                    fVar = (androidx.appcompat.view.menu.f) headerViewListAdapter.getWrappedAdapter();
                } else {
                    headersCount = 0;
                    fVar = (androidx.appcompat.view.menu.f) adapter;
                }
                androidx.appcompat.view.menu.j item = null;
                if (motionEvent.getAction() != 10 && (iPointToPosition = pointToPosition((int) motionEvent.getX(), (int) motionEvent.getY())) != -1 && (i = iPointToPosition - headersCount) >= 0 && i < fVar.getCount()) {
                    item = fVar.getItem(i);
                }
                MenuItem menuItem = this.r;
                if (menuItem != item) {
                    androidx.appcompat.view.menu.g gVarB = fVar.b();
                    if (menuItem != null) {
                        this.q.b(gVarB, menuItem);
                    }
                    this.r = item;
                    if (item != null) {
                        this.q.a(gVarB, item);
                    }
                }
            }
            return super.onHoverEvent(motionEvent);
        }

        @Override // android.widget.ListView, android.widget.AbsListView, android.view.View, android.view.KeyEvent.Callback
        public boolean onKeyDown(int i, KeyEvent keyEvent) {
            ListMenuItemView listMenuItemView = (ListMenuItemView) getSelectedView();
            if (listMenuItemView != null && i == this.o) {
                if (listMenuItemView.isEnabled() && listMenuItemView.getItemData().hasSubMenu()) {
                    performItemClick(listMenuItemView, getSelectedItemPosition(), getSelectedItemId());
                }
                return true;
            }
            if (listMenuItemView == null || i != this.p) {
                return super.onKeyDown(i, keyEvent);
            }
            setSelection(-1);
            ((androidx.appcompat.view.menu.f) getAdapter()).b().a(false);
            return true;
        }

        public void setHoverListener(t tVar) {
            this.q = tVar;
        }

        @Override // androidx.appcompat.widget.DropDownListView, android.widget.AbsListView
        public /* bridge */ /* synthetic */ void setSelector(Drawable drawable) {
            super.setSelector(drawable);
        }
    }

    static {
        try {
            if (Build.VERSION.SDK_INT <= 28) {
                K = PopupWindow.class.getDeclaredMethod("setTouchModal", Boolean.TYPE);
            }
        } catch (NoSuchMethodException unused) {
            Log.i("MenuPopupWindow", "Could not find method setTouchModal() on PopupWindow. Oh well.");
        }
    }

    public MenuPopupWindow(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    @Override // androidx.appcompat.widget.s
    DropDownListView a(Context context, boolean z) {
        MenuDropDownListView menuDropDownListView = new MenuDropDownListView(context, z);
        menuDropDownListView.setHoverListener(this);
        return menuDropDownListView;
    }

    public void b(Object obj) {
        if (Build.VERSION.SDK_INT >= 23) {
            this.F.setExitTransition((Transition) obj);
        }
    }

    public void c(boolean z) {
        if (Build.VERSION.SDK_INT > 28) {
            this.F.setTouchModal(z);
            return;
        }
        Method method = K;
        if (method != null) {
            try {
                method.invoke(this.F, Boolean.valueOf(z));
            } catch (Exception unused) {
                Log.i("MenuPopupWindow", "Could not invoke setTouchModal() on PopupWindow. Oh well.");
            }
        }
    }

    public void a(Object obj) {
        if (Build.VERSION.SDK_INT >= 23) {
            this.F.setEnterTransition((Transition) obj);
        }
    }

    @Override // androidx.appcompat.widget.t
    public void b(androidx.appcompat.view.menu.g gVar, MenuItem menuItem) {
        t tVar = this.J;
        if (tVar != null) {
            tVar.b(gVar, menuItem);
        }
    }

    public void a(t tVar) {
        this.J = tVar;
    }

    @Override // androidx.appcompat.widget.t
    public void a(androidx.appcompat.view.menu.g gVar, MenuItem menuItem) {
        t tVar = this.J;
        if (tVar != null) {
            tVar.a(gVar, menuItem);
        }
    }
}
