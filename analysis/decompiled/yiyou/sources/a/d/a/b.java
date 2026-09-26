package a.d.a;

import android.database.Cursor;
import android.widget.Filter;

/* JADX INFO: compiled from: CursorFilter.java */
/* JADX INFO: loaded from: classes.dex */
class b extends Filter {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    a f181a;

    /* JADX INFO: compiled from: CursorFilter.java */
    interface a {
        Cursor a();

        Cursor a(CharSequence charSequence);

        CharSequence a(Cursor cursor);

        void b(Cursor cursor);
    }

    b(a aVar) {
        this.f181a = aVar;
    }

    @Override // android.widget.Filter
    public CharSequence convertResultToString(Object obj) {
        return this.f181a.a((Cursor) obj);
    }

    @Override // android.widget.Filter
    protected Filter.FilterResults performFiltering(CharSequence charSequence) {
        Cursor cursorA = this.f181a.a(charSequence);
        Filter.FilterResults filterResults = new Filter.FilterResults();
        if (cursorA != null) {
            filterResults.count = cursorA.getCount();
            filterResults.values = cursorA;
        } else {
            filterResults.count = 0;
            filterResults.values = null;
        }
        return filterResults;
    }

    @Override // android.widget.Filter
    protected void publishResults(CharSequence charSequence, Filter.FilterResults filterResults) {
        Cursor cursorA = this.f181a.a();
        Object obj = filterResults.values;
        if (obj == null || obj == cursorA) {
            return;
        }
        this.f181a.b((Cursor) obj);
    }
}
