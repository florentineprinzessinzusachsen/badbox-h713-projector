package com.baidu.mobstat;

import android.content.ContentValues;
import android.database.Cursor;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
class q extends j {
    public q() {
        super("app_list3", "Create table if not exists app_list3(_id Integer primary key AUTOINCREMENT,time VARCHAR(50),content TEXT);");
    }

    @Override // com.baidu.mobstat.j
    public ArrayList<i> a(int i, int i2) {
        Cursor cursorA = a("time", i, i2);
        ArrayList<i> arrayListA = a(cursorA);
        if (cursorA != null) {
            cursorA.close();
        }
        return arrayListA;
    }

    @Override // com.baidu.mobstat.j
    public boolean b(long j) {
        return a(j);
    }

    @Override // com.baidu.mobstat.j
    public long a(String str, String str2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("time", str);
        contentValues.put("content", str2);
        return a(contentValues);
    }

    private ArrayList<i> a(Cursor cursor) {
        ArrayList<i> arrayList = new ArrayList<>();
        if (cursor == null || cursor.getCount() == 0) {
            return arrayList;
        }
        int columnIndex = cursor.getColumnIndex("_id");
        int columnIndex2 = cursor.getColumnIndex("time");
        int columnIndex3 = cursor.getColumnIndex("content");
        while (cursor.moveToNext()) {
            arrayList.add(new i(cursor.getLong(columnIndex), cursor.getString(columnIndex2), cursor.getString(columnIndex3)));
        }
        return arrayList;
    }
}
