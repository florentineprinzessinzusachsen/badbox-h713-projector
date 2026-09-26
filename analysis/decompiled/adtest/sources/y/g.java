package y;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.util.Pair;
import d0.l0;
import e3.w;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import p.p;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class g extends SQLiteOpenHelper {

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final /* synthetic */ int f2692k = 0;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Context f2693d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final a3.h f2694e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final w f2695f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final boolean f2696g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f2697h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final z.a f2698i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f2699j;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public g(Context context, String str, final a3.h hVar, final w wVar, boolean z3) {
        String string;
        super(context, str, null, wVar.f825a, new DatabaseErrorHandler() { // from class: y.d
            @Override // android.database.DatabaseErrorHandler
            public final void onCorruption(SQLiteDatabase sQLiteDatabase) {
                int i4 = g.f2692k;
                j2.i.b(sQLiteDatabase);
                c cVarZ = l0.z(hVar, sQLiteDatabase);
                wVar.getClass();
                Log.e("SupportSQLite", "Corruption reported by sqlite on database: " + cVarZ + ".path");
                SQLiteDatabase sQLiteDatabase2 = cVarZ.f2681d;
                if (!sQLiteDatabase2.isOpen()) {
                    String path = sQLiteDatabase2.getPath();
                    if (path != null) {
                        w.a(path);
                        return;
                    }
                    return;
                }
                List<Pair<String, String>> attachedDbs = null;
                try {
                    try {
                        attachedDbs = sQLiteDatabase2.getAttachedDbs();
                    } finally {
                        if (attachedDbs != null) {
                            Iterator<T> it = attachedDbs.iterator();
                            while (it.hasNext()) {
                                Object obj = ((Pair) it.next()).second;
                                j2.i.d(obj, "second");
                                w.a((String) obj);
                            }
                        } else {
                            String path2 = sQLiteDatabase2.getPath();
                            if (path2 != null) {
                                w.a(path2);
                            }
                        }
                    }
                } catch (SQLiteException unused) {
                }
                try {
                    cVarZ.close();
                } catch (IOException unused2) {
                }
                if (attachedDbs != null) {
                    return;
                }
            }
        });
        j2.i.e(context, "context");
        j2.i.e(wVar, "callback");
        this.f2693d = context;
        this.f2694e = hVar;
        this.f2695f = wVar;
        this.f2696g = z3;
        if (str == null) {
            string = UUID.randomUUID().toString();
            j2.i.d(string, "toString(...)");
        } else {
            string = str;
        }
        this.f2698i = new z.a(string, context.getCacheDir(), false);
    }

    public final x.a b(boolean z3) {
        z.a aVar = this.f2698i;
        try {
            aVar.a((this.f2699j || getDatabaseName() == null) ? false : true);
            this.f2697h = false;
            SQLiteDatabase sQLiteDatabaseC = c(z3);
            if (!this.f2697h) {
                return l0.z(this.f2694e, sQLiteDatabaseC);
            }
            close();
            return b(z3);
        } finally {
            aVar.b();
        }
    }

    public final SQLiteDatabase c(boolean z3) throws Throwable {
        SQLiteDatabase readableDatabase;
        SQLiteDatabase readableDatabase2;
        File parentFile;
        String databaseName = getDatabaseName();
        boolean z4 = this.f2699j;
        Context context = this.f2693d;
        if (databaseName != null && !z4 && (parentFile = context.getDatabasePath(databaseName).getParentFile()) != null) {
            parentFile.mkdirs();
            if (!parentFile.isDirectory()) {
                Log.w("SupportSQLite", "Invalid database parent file, not a directory: " + parentFile);
            }
        }
        try {
            if (z3) {
                SQLiteDatabase writableDatabase = getWritableDatabase();
                j2.i.b(writableDatabase);
                return writableDatabase;
            }
            SQLiteDatabase readableDatabase3 = getReadableDatabase();
            j2.i.b(readableDatabase3);
            return readableDatabase3;
        } catch (Throwable unused) {
            try {
                Thread.sleep(500L);
            } catch (InterruptedException unused2) {
            }
            try {
                if (z3) {
                    readableDatabase2 = getWritableDatabase();
                    j2.i.b(readableDatabase2);
                } else {
                    readableDatabase2 = getReadableDatabase();
                    j2.i.b(readableDatabase2);
                }
                return readableDatabase2;
            } catch (Throwable th) {
                th = th;
                if (th instanceof e) {
                    e eVar = (e) th;
                    int iOrdinal = eVar.f2684d.ordinal();
                    th = eVar.f2685e;
                    if (iOrdinal == 0 || iOrdinal == 1 || iOrdinal == 2 || iOrdinal == 3) {
                        throw th;
                    }
                    if (iOrdinal != 4) {
                        throw new a0.c();
                    }
                    if (!(th instanceof SQLiteException)) {
                        throw th;
                    }
                }
                if (!(th instanceof SQLiteException) || databaseName == null || !this.f2696g) {
                    throw th;
                }
                context.deleteDatabase(databaseName);
                try {
                    if (z3) {
                        readableDatabase = getWritableDatabase();
                        j2.i.b(readableDatabase);
                    } else {
                        readableDatabase = getReadableDatabase();
                        j2.i.b(readableDatabase);
                    }
                    return readableDatabase;
                } catch (e e4) {
                    throw e4.f2685e;
                }
            }
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper, java.lang.AutoCloseable
    public final void close() {
        z.a aVar = this.f2698i;
        try {
            aVar.a(aVar.f2774a);
            super.close();
            this.f2694e.f149e = null;
            this.f2699j = false;
        } finally {
            aVar.b();
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public final void onConfigure(SQLiteDatabase sQLiteDatabase) {
        j2.i.e(sQLiteDatabase, "db");
        boolean z3 = this.f2697h;
        w wVar = this.f2695f;
        if (!z3 && wVar.f825a != sQLiteDatabase.getVersion()) {
            sQLiteDatabase.setMaxSqlCacheSize(1);
        }
        try {
            l0.z(this.f2694e, sQLiteDatabase);
            wVar.getClass();
        } catch (Throwable th) {
            throw new e(f.f2686d, th);
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public final void onCreate(SQLiteDatabase sQLiteDatabase) {
        j2.i.e(sQLiteDatabase, "sqLiteDatabase");
        try {
            ((p) this.f2695f.f826b).d(new s.a(l0.z(this.f2694e, sQLiteDatabase)));
        } catch (Throwable th) {
            throw new e(f.f2687e, th);
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public final void onDowngrade(SQLiteDatabase sQLiteDatabase, int i4, int i5) {
        j2.i.e(sQLiteDatabase, "db");
        this.f2697h = true;
        try {
            this.f2695f.c(l0.z(this.f2694e, sQLiteDatabase), i4, i5);
        } catch (Throwable th) {
            throw new e(f.f2689g, th);
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public final void onOpen(SQLiteDatabase sQLiteDatabase) {
        j2.i.e(sQLiteDatabase, "db");
        if (!this.f2697h) {
            try {
                w wVar = this.f2695f;
                c cVarZ = l0.z(this.f2694e, sQLiteDatabase);
                p pVar = (p) wVar.f826b;
                pVar.f(new s.a(cVarZ));
                pVar.f1689g = cVarZ;
            } catch (Throwable th) {
                throw new e(f.f2690h, th);
            }
        }
        this.f2699j = true;
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public final void onUpgrade(SQLiteDatabase sQLiteDatabase, int i4, int i5) {
        j2.i.e(sQLiteDatabase, "sqLiteDatabase");
        this.f2697h = true;
        try {
            this.f2695f.c(l0.z(this.f2694e, sQLiteDatabase), i4, i5);
        } catch (Throwable th) {
            throw new e(f.f2688f, th);
        }
    }
}
