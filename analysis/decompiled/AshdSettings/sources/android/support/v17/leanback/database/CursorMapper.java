package android.support.v17.leanback.database;

import android.database.Cursor;

/* JADX INFO: loaded from: classes.dex */
public abstract class CursorMapper {
    private Cursor mCursor;

    protected abstract Object bind(Cursor cursor);

    protected abstract void bindColumns(Cursor cursor);

    public Object convert(Cursor cursor) {
        if (cursor != this.mCursor) {
            this.mCursor = cursor;
            bindColumns(this.mCursor);
        }
        return bind(this.mCursor);
    }
}
