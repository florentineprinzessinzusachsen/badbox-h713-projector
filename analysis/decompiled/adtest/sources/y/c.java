package y;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteCursorDriver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQuery;
import android.database.sqlite.SQLiteStatement;
import android.text.TextUtils;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class c implements x.a {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final String[] f2677e = {"", " OR ROLLBACK ", " OR ABORT ", " OR FAIL ", " OR IGNORE ", " OR REPLACE "};

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final String[] f2678f = new String[0];

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final Object f2679g = a.a.v(new h1.a(3));

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final Object f2680h = a.a.v(new h1.a(4));

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final SQLiteDatabase f2681d;

    public c(SQLiteDatabase sQLiteDatabase) {
        this.f2681d = sQLiteDatabase;
    }

    @Override // x.a
    public final void B() {
        this.f2681d.beginTransactionNonExclusive();
    }

    /* JADX WARN: Type inference failed for: r2v0, types: [java.lang.Object, u1.c] */
    /* JADX WARN: Type inference failed for: r3v2, types: [java.lang.Object, u1.c] */
    @Override // x.a
    public final void I() throws IllegalAccessException, InvocationTargetException {
        ?? r4 = f2680h;
        if (((Method) r4.getValue()) != null) {
            ?? r5 = f2679g;
            if (((Method) r5.getValue()) != null) {
                Method method = (Method) r4.getValue();
                j2.i.b(method);
                Method method2 = (Method) r5.getValue();
                j2.i.b(method2);
                Object objInvoke = method2.invoke(this.f2681d, null);
                if (objInvoke == null) {
                    throw new IllegalStateException("Required value was null.");
                }
                method.invoke(objInvoke, 0, null, 0, null);
                return;
            }
        }
        i();
    }

    @Override // x.a
    public final int L(ContentValues contentValues, Object[] objArr) {
        if (contentValues.size() == 0) {
            throw new IllegalArgumentException("Empty values");
        }
        int size = contentValues.size();
        int length = objArr.length + size;
        Object[] objArr2 = new Object[length];
        StringBuilder sb = new StringBuilder("UPDATE ");
        sb.append(f2677e[3]);
        sb.append("WorkSpec SET ");
        int i4 = 0;
        int i5 = 0;
        for (String str : contentValues.keySet()) {
            sb.append(i5 > 0 ? "," : "");
            sb.append(str);
            objArr2[i5] = contentValues.get(str);
            sb.append("=?");
            i5++;
        }
        for (int i6 = size; i6 < length; i6++) {
            objArr2[i6] = objArr[i6 - size];
        }
        if (!TextUtils.isEmpty("last_enqueue_time = 0 AND interval_duration <> 0 ")) {
            sb.append(" WHERE last_enqueue_time = 0 AND interval_duration <> 0 ");
        }
        j jVarZ = z(sb.toString());
        while (i4 < length) {
            Object obj = objArr2[i4];
            i4++;
            if (obj == null) {
                jVarZ.e(i4);
            } else if (obj instanceof byte[]) {
                jVarZ.f2707d.bindBlob(i4, (byte[]) obj);
            } else if (obj instanceof Float) {
                jVarZ.E(i4, ((Number) obj).floatValue());
            } else if (obj instanceof Double) {
                jVarZ.E(i4, ((Number) obj).doubleValue());
            } else if (obj instanceof Long) {
                jVarZ.a(i4, ((Number) obj).longValue());
            } else if (obj instanceof Integer) {
                jVarZ.a(i4, ((Number) obj).intValue());
            } else if (obj instanceof Short) {
                jVarZ.a(i4, ((Number) obj).shortValue());
            } else if (obj instanceof Byte) {
                jVarZ.a(i4, ((Number) obj).byteValue());
            } else if (obj instanceof String) {
                jVarZ.u(i4, (String) obj);
            } else {
                if (!(obj instanceof Boolean)) {
                    throw new IllegalArgumentException("Cannot bind " + obj + " at index " + i4 + " Supported types: Null, ByteArray, Float, Double, Long, Int, Short, Byte, String");
                }
                jVarZ.a(i4, ((Boolean) obj).booleanValue() ? 1L : 0L);
            }
        }
        return jVarZ.f2708e.executeUpdateDelete();
    }

    @Override // x.a
    public final boolean N() {
        return this.f2681d.inTransaction();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        this.f2681d.close();
    }

    @Override // x.a
    public final void h() {
        this.f2681d.endTransaction();
    }

    @Override // x.a
    public final void i() {
        this.f2681d.beginTransaction();
    }

    @Override // x.a
    public final boolean isOpen() {
        return this.f2681d.isOpen();
    }

    @Override // x.a
    public final boolean p() {
        return this.f2681d.isWriteAheadLoggingEnabled();
    }

    @Override // x.a
    public final Cursor r(a3.h hVar) {
        final a aVar = new a(hVar);
        Cursor cursorRawQueryWithFactory = this.f2681d.rawQueryWithFactory(new SQLiteDatabase.CursorFactory() { // from class: y.b
            @Override // android.database.sqlite.SQLiteDatabase.CursorFactory
            public final Cursor newCursor(SQLiteDatabase sQLiteDatabase, SQLiteCursorDriver sQLiteCursorDriver, String str, SQLiteQuery sQLiteQuery) {
                a3.h hVar2 = aVar.f2675d;
                SQLiteCursorDriver sQLiteCursorDriver2 = sQLiteCursorDriver;
                j2.i.b(sQLiteQuery);
                i iVar = new i(sQLiteQuery);
                s.e eVar = (s.e) hVar2.f149e;
                int length = eVar.f2074g.length;
                for (int i4 = 1; i4 < length; i4++) {
                    int i5 = eVar.f2074g[i4];
                    if (i5 == 1) {
                        iVar.a(i4, eVar.f2075h[i4]);
                    } else if (i5 == 2) {
                        iVar.E(i4, eVar.f2076i[i4]);
                    } else if (i5 == 3) {
                        String str2 = eVar.f2077j[i4];
                        j2.i.b(str2);
                        iVar.u(i4, str2);
                    } else if (i5 == 4) {
                        byte[] bArr = eVar.f2078k[i4];
                        j2.i.b(bArr);
                        iVar.d(i4, bArr);
                    } else if (i5 == 5) {
                        iVar.e(i4);
                    }
                }
                return new SQLiteCursor(sQLiteCursorDriver2, str, sQLiteQuery);
            }
        }, ((s.e) hVar.f149e).f2082e, f2678f, null);
        j2.i.d(cursorRawQueryWithFactory, "rawQueryWithFactory(...)");
        return cursorRawQueryWithFactory;
    }

    @Override // x.a
    public final void s(String str) {
        j2.i.e(str, "sql");
        this.f2681d.execSQL(str);
    }

    @Override // x.a
    public final void w(Object[] objArr) {
        this.f2681d.execSQL("INSERT OR REPLACE INTO `Preference` (`key`, `long_value`) VALUES (@key, @long_value)", objArr);
    }

    @Override // x.a
    public final void y() {
        this.f2681d.setTransactionSuccessful();
    }

    @Override // x.a
    public final j z(String str) {
        j2.i.e(str, "sql");
        SQLiteStatement sQLiteStatementCompileStatement = this.f2681d.compileStatement(str);
        j2.i.d(sQLiteStatementCompileStatement, "compileStatement(...)");
        return new j(sQLiteStatementCompileStatement);
    }
}
