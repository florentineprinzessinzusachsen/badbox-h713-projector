package a.d.a;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/* JADX INFO: compiled from: ResourceCursorAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class c extends a {
    private int i;
    private int j;
    private LayoutInflater k;

    @Deprecated
    public c(Context context, int i, Cursor cursor, boolean z) {
        super(context, cursor, z);
        this.j = i;
        this.i = i;
        this.k = (LayoutInflater) context.getSystemService("layout_inflater");
    }

    @Override // a.d.a.a
    public View a(Context context, Cursor cursor, ViewGroup viewGroup) {
        return this.k.inflate(this.j, viewGroup, false);
    }

    @Override // a.d.a.a
    public View b(Context context, Cursor cursor, ViewGroup viewGroup) {
        return this.k.inflate(this.i, viewGroup, false);
    }
}
