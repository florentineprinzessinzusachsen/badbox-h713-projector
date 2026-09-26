package android.support.v17.leanback.widget;

import android.database.Cursor;
import android.support.v17.leanback.database.CursorMapper;
import android.util.LruCache;

/* JADX INFO: loaded from: classes.dex */
public class CursorObjectAdapter extends ObjectAdapter {
    private static final int CACHE_SIZE = 100;
    private Cursor mCursor;
    private final LruCache<Integer, Object> mItemCache;
    private CursorMapper mMapper;

    @Override // android.support.v17.leanback.widget.ObjectAdapter
    public boolean isImmediateNotifySupported() {
        return true;
    }

    protected void onMapperChanged() {
    }

    public CursorObjectAdapter(PresenterSelector presenterSelector) {
        super(presenterSelector);
        this.mItemCache = new LruCache<>(100);
    }

    public CursorObjectAdapter(Presenter presenter) {
        super(presenter);
        this.mItemCache = new LruCache<>(100);
    }

    public CursorObjectAdapter() {
        this.mItemCache = new LruCache<>(100);
    }

    public void changeCursor(Cursor cursor) {
        if (cursor == this.mCursor) {
            return;
        }
        if (this.mCursor != null) {
            this.mCursor.close();
        }
        this.mCursor = cursor;
        this.mItemCache.trimToSize(0);
        onCursorChanged();
    }

    public Cursor swapCursor(Cursor cursor) {
        if (cursor == this.mCursor) {
            return this.mCursor;
        }
        Cursor cursor2 = this.mCursor;
        this.mCursor = cursor;
        this.mItemCache.trimToSize(0);
        onCursorChanged();
        return cursor2;
    }

    protected void onCursorChanged() {
        notifyChanged();
    }

    public final Cursor getCursor() {
        return this.mCursor;
    }

    public final void setMapper(CursorMapper cursorMapper) {
        boolean z = this.mMapper != cursorMapper;
        this.mMapper = cursorMapper;
        if (z) {
            onMapperChanged();
        }
    }

    public final CursorMapper getMapper() {
        return this.mMapper;
    }

    @Override // android.support.v17.leanback.widget.ObjectAdapter
    public int size() {
        if (this.mCursor == null) {
            return 0;
        }
        return this.mCursor.getCount();
    }

    @Override // android.support.v17.leanback.widget.ObjectAdapter
    public Object get(int i) {
        if (this.mCursor == null) {
            return null;
        }
        if (!this.mCursor.moveToPosition(i)) {
            throw new ArrayIndexOutOfBoundsException();
        }
        Object obj = this.mItemCache.get(Integer.valueOf(i));
        if (obj != null) {
            return obj;
        }
        Object objConvert = this.mMapper.convert(this.mCursor);
        this.mItemCache.put(Integer.valueOf(i), objConvert);
        return objConvert;
    }

    public void close() {
        if (this.mCursor != null) {
            this.mCursor.close();
            this.mCursor = null;
        }
    }

    public boolean isClosed() {
        return this.mCursor == null || this.mCursor.isClosed();
    }

    protected final void invalidateCache(int i) {
        this.mItemCache.remove(Integer.valueOf(i));
    }

    protected final void invalidateCache(int i, int i2) {
        int i3 = i2 + i;
        while (i < i3) {
            invalidateCache(i);
            i++;
        }
    }
}
