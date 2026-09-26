package androidx.appcompat.view.menu;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.util.ArrayList;

/* JADX INFO: compiled from: MenuAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public class f extends BaseAdapter {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    g f461a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f462b = -1;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f463c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final boolean f464d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final LayoutInflater f465e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final int f466f;

    public f(g gVar, LayoutInflater layoutInflater, boolean z, int i) {
        this.f464d = z;
        this.f465e = layoutInflater;
        this.f461a = gVar;
        this.f466f = i;
        a();
    }

    public void a(boolean z) {
        this.f463c = z;
    }

    public g b() {
        return this.f461a;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        ArrayList<j> arrayListJ = this.f464d ? this.f461a.j() : this.f461a.n();
        return this.f462b < 0 ? arrayListJ.size() : arrayListJ.size() - 1;
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = this.f465e.inflate(this.f466f, viewGroup, false);
        }
        int groupId = getItem(i).getGroupId();
        int i2 = i - 1;
        ListMenuItemView listMenuItemView = (ListMenuItemView) view;
        listMenuItemView.setGroupDividerEnabled(this.f461a.o() && groupId != (i2 >= 0 ? getItem(i2).getGroupId() : groupId));
        n.a aVar = (n.a) view;
        if (this.f463c) {
            listMenuItemView.setForceShowIcon(true);
        }
        aVar.a(getItem(i), 0);
        return view;
    }

    @Override // android.widget.BaseAdapter
    public void notifyDataSetChanged() {
        a();
        super.notifyDataSetChanged();
    }

    void a() {
        j jVarF = this.f461a.f();
        if (jVarF != null) {
            ArrayList<j> arrayListJ = this.f461a.j();
            int size = arrayListJ.size();
            for (int i = 0; i < size; i++) {
                if (arrayListJ.get(i) == jVarF) {
                    this.f462b = i;
                    return;
                }
            }
        }
        this.f462b = -1;
    }

    @Override // android.widget.Adapter
    public j getItem(int i) {
        ArrayList<j> arrayListJ = this.f464d ? this.f461a.j() : this.f461a.n();
        int i2 = this.f462b;
        if (i2 >= 0 && i >= i2) {
            i++;
        }
        return arrayListJ.get(i);
    }
}
