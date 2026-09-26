package x;

import a3.h;
import android.content.ContentValues;
import android.database.Cursor;
import java.io.Closeable;
import y.j;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public interface a extends Closeable {
    void B();

    void I();

    int L(ContentValues contentValues, Object[] objArr);

    boolean N();

    void h();

    void i();

    boolean isOpen();

    boolean p();

    Cursor r(h hVar);

    void s(String str);

    void w(Object[] objArr);

    void y();

    j z(String str);
}
