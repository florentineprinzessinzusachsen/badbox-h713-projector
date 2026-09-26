package androidx.appcompat.view.menu;

import android.content.Context;
import android.os.IBinder;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import androidx.appcompat.R$layout;
import java.util.ArrayList;

/* JADX INFO: compiled from: ListMenuPresenter.java */
/* JADX INFO: loaded from: classes.dex */
public class e implements m, AdapterView.OnItemClickListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    Context f453a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    LayoutInflater f454b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    g f455c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    ExpandedMenuView f456d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    int f457e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    int f458f;
    int g;
    private m.a h;
    a i;

    /* JADX INFO: compiled from: ListMenuPresenter.java */
    private class a extends BaseAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private int f459a = -1;

        public a() {
            a();
        }

        void a() {
            j jVarF = e.this.f455c.f();
            if (jVarF != null) {
                ArrayList<j> arrayListJ = e.this.f455c.j();
                int size = arrayListJ.size();
                for (int i = 0; i < size; i++) {
                    if (arrayListJ.get(i) == jVarF) {
                        this.f459a = i;
                        return;
                    }
                }
            }
            this.f459a = -1;
        }

        @Override // android.widget.Adapter
        public int getCount() {
            int size = e.this.f455c.j().size() - e.this.f457e;
            return this.f459a < 0 ? size : size - 1;
        }

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return i;
        }

        @Override // android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                e eVar = e.this;
                view = eVar.f454b.inflate(eVar.g, viewGroup, false);
            }
            ((n.a) view).a(getItem(i), 0);
            return view;
        }

        @Override // android.widget.BaseAdapter
        public void notifyDataSetChanged() {
            a();
            super.notifyDataSetChanged();
        }

        @Override // android.widget.Adapter
        public j getItem(int i) {
            ArrayList<j> arrayListJ = e.this.f455c.j();
            int i2 = i + e.this.f457e;
            int i3 = this.f459a;
            if (i3 >= 0 && i2 >= i3) {
                i2++;
            }
            return arrayListJ.get(i2);
        }
    }

    public e(Context context, int i) {
        this(i, 0);
        this.f453a = context;
        this.f454b = LayoutInflater.from(this.f453a);
    }

    @Override // androidx.appcompat.view.menu.m
    public void a(Context context, g gVar) {
        int i = this.f458f;
        if (i != 0) {
            this.f453a = new ContextThemeWrapper(context, i);
            this.f454b = LayoutInflater.from(this.f453a);
        } else if (this.f453a != null) {
            this.f453a = context;
            if (this.f454b == null) {
                this.f454b = LayoutInflater.from(this.f453a);
            }
        }
        this.f455c = gVar;
        a aVar = this.i;
        if (aVar != null) {
            aVar.notifyDataSetChanged();
        }
    }

    @Override // androidx.appcompat.view.menu.m
    public boolean a() {
        return false;
    }

    @Override // androidx.appcompat.view.menu.m
    public boolean a(g gVar, j jVar) {
        return false;
    }

    public ListAdapter b() {
        if (this.i == null) {
            this.i = new a();
        }
        return this.i;
    }

    @Override // androidx.appcompat.view.menu.m
    public boolean b(g gVar, j jVar) {
        return false;
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        this.f455c.a(this.i.getItem(i), this, 0);
    }

    public e(int i, int i2) {
        this.g = i;
        this.f458f = i2;
    }

    public n a(ViewGroup viewGroup) {
        if (this.f456d == null) {
            this.f456d = (ExpandedMenuView) this.f454b.inflate(R$layout.abc_expanded_menu_layout, viewGroup, false);
            if (this.i == null) {
                this.i = new a();
            }
            this.f456d.setAdapter((ListAdapter) this.i);
            this.f456d.setOnItemClickListener(this);
        }
        return this.f456d;
    }

    @Override // androidx.appcompat.view.menu.m
    public void a(boolean z) {
        a aVar = this.i;
        if (aVar != null) {
            aVar.notifyDataSetChanged();
        }
    }

    @Override // androidx.appcompat.view.menu.m
    public void a(m.a aVar) {
        this.h = aVar;
    }

    @Override // androidx.appcompat.view.menu.m
    public boolean a(r rVar) {
        if (!rVar.hasVisibleItems()) {
            return false;
        }
        new h(rVar).a((IBinder) null);
        m.a aVar = this.h;
        if (aVar == null) {
            return true;
        }
        aVar.a(rVar);
        return true;
    }

    @Override // androidx.appcompat.view.menu.m
    public void a(g gVar, boolean z) {
        m.a aVar = this.h;
        if (aVar != null) {
            aVar.a(gVar, z);
        }
    }
}
