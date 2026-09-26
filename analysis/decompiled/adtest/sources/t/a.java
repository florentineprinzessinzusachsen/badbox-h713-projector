package t;

import j2.i;
import u1.e;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public abstract class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f2149a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f2150b;

    public a(int i4, int i5) {
        this.f2149a = i4;
        this.f2150b = i5;
    }

    public void a(w.a aVar) {
        i.e(aVar, "connection");
        if (!(aVar instanceof s.a)) {
            throw new e("Migration functionality with a provided SQLiteDriver requires overriding the migrate(SQLiteConnection) function.");
        }
        b(((s.a) aVar).f2066d);
    }

    public void b(x.a aVar) {
        i.e(aVar, "db");
        throw new e("Migration functionality with a SupportSQLiteDatabase (without a provided SQLiteDriver) requires overriding the migrate(SupportSQLiteDatabase) function.");
    }
}
