package a.d.a;

import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;

/* JADX INFO: compiled from: CursorAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class a extends BaseAdapter implements Filterable, a.d.a.b.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected boolean f173a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected boolean f174b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    protected Cursor f175c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    protected Context f176d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    protected int f177e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    protected C0003a f178f;
    protected DataSetObserver g;
    protected a.d.a.b h;

    /* JADX INFO: renamed from: a.d.a.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: CursorAdapter.java */
    private class C0003a extends ContentObserver {
        C0003a() {
            super(new Handler());
        }

        @Override // android.database.ContentObserver
        public boolean deliverSelfNotifications() {
            return true;
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z) {
            a.this.b();
        }
    }

    /* JADX INFO: compiled from: CursorAdapter.java */
    private class b extends DataSetObserver {
        b() {
        }

        @Override // android.database.DataSetObserver
        public void onChanged() {
            a aVar = a.this;
            aVar.f173a = true;
            aVar.notifyDataSetChanged();
        }

        @Override // android.database.DataSetObserver
        public void onInvalidated() {
            a aVar = a.this;
            aVar.f173a = false;
            aVar.notifyDataSetInvalidated();
        }
    }

    public a(Context context, Cursor cursor, boolean z) {
        a(context, cursor, z ? 1 : 2);
    }

    public abstract View a(Context context, Cursor cursor, ViewGroup viewGroup);

    @Override // a.d.a.b.a
    public abstract CharSequence a(Cursor cursor);

    void a(Context context, Cursor cursor, int i) {
        if ((i & 1) == 1) {
            i |= 2;
            this.f174b = true;
        } else {
            this.f174b = false;
        }
        boolean z = cursor != null;
        this.f175c = cursor;
        this.f173a = z;
        this.f176d = context;
        this.f177e = z ? cursor.getColumnIndexOrThrow("_id") : -1;
        if ((i & 2) == 2) {
            this.f178f = new C0003a();
            this.g = new b();
        } else {
            this.f178f = null;
            this.g = null;
        }
        if (z) {
            C0003a c0003a = this.f178f;
            if (c0003a != null) {
                cursor.registerContentObserver(c0003a);
            }
            DataSetObserver dataSetObserver = this.g;
            if (dataSetObserver != null) {
                cursor.registerDataSetObserver(dataSetObserver);
            }
        }
    }

    public abstract void a(View view, Context context, Cursor cursor);

    public abstract View b(Context context, Cursor cursor, ViewGroup viewGroup);

    @Override // a.d.a.b.a
    public void b(Cursor cursor) {
        Cursor cursorC = c(cursor);
        if (cursorC != null) {
            cursorC.close();
        }
    }

    public Cursor c(Cursor cursor) {
        Cursor cursor2 = this.f175c;
        if (cursor == cursor2) {
            return null;
        }
        if (cursor2 != null) {
            C0003a c0003a = this.f178f;
            if (c0003a != null) {
                cursor2.unregisterContentObserver(c0003a);
            }
            DataSetObserver dataSetObserver = this.g;
            if (dataSetObserver != null) {
                cursor2.unregisterDataSetObserver(dataSetObserver);
            }
        }
        this.f175c = cursor;
        if (cursor != null) {
            C0003a c0003a2 = this.f178f;
            if (c0003a2 != null) {
                cursor.registerContentObserver(c0003a2);
            }
            DataSetObserver dataSetObserver2 = this.g;
            if (dataSetObserver2 != null) {
                cursor.registerDataSetObserver(dataSetObserver2);
            }
            this.f177e = cursor.getColumnIndexOrThrow("_id");
            this.f173a = true;
            notifyDataSetChanged();
        } else {
            this.f177e = -1;
            this.f173a = false;
            notifyDataSetInvalidated();
        }
        return cursor2;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        Cursor cursor;
        if (!this.f173a || (cursor = this.f175c) == null) {
            return 0;
        }
        return cursor.getCount();
    }

    @Override // android.widget.BaseAdapter, android.widget.SpinnerAdapter
    public View getDropDownView(int i, View view, ViewGroup viewGroup) {
        if (!this.f173a) {
            return null;
        }
        this.f175c.moveToPosition(i);
        if (view == null) {
            view = a(this.f176d, this.f175c, viewGroup);
        }
        a(view, this.f176d, this.f175c);
        return view;
    }

    @Override // android.widget.Filterable
    public Filter getFilter() {
        if (this.h == null) {
            this.h = new a.d.a.b(this);
        }
        return this.h;
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        Cursor cursor;
        if (!this.f173a || (cursor = this.f175c) == null) {
            return null;
        }
        cursor.moveToPosition(i);
        return this.f175c;
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        Cursor cursor;
        if (this.f173a && (cursor = this.f175c) != null && cursor.moveToPosition(i)) {
            return this.f175c.getLong(this.f177e);
        }
        return 0L;
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (!this.f173a) {
            throw new IllegalStateException("this should only be called when the cursor is valid");
        }
        if (this.f175c.moveToPosition(i)) {
            if (view == null) {
                view = b(this.f176d, this.f175c, viewGroup);
            }
            a(view, this.f176d, this.f175c);
            return view;
        }
        throw new IllegalStateException("couldn't move cursor to position " + i);
    }

    protected void b() {
        Cursor cursor;
        if (!this.f174b || (cursor = this.f175c) == null || cursor.isClosed()) {
            return;
        }
        this.f173a = this.f175c.requery();
    }

    @Override // a.d.a.b.a
    public Cursor a() {
        return this.f175c;
    }
}
