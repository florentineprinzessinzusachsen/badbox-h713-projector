package a1;

import android.content.ContentProviderClient;
import android.content.ContentValues;
import android.content.res.TypedArray;
import android.drm.DrmManagerClient;
import android.media.MediaDrm;
import android.media.MediaMetadataRetriever;
import e0.x;
import j2.i;
import java.util.concurrent.ExecutorService;
import l3.h;
import s0.n;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class c {
    public static void a(a aVar, w.a aVar2) {
        i.e(aVar2, "connection");
        if (aVar2 instanceof s.a) {
            x.a aVar3 = ((s.a) aVar2).f2066d;
            switch (aVar.f10d) {
                case 1:
                    i.e(aVar3, "db");
                    aVar3.s("UPDATE workspec SET period_count = 1 WHERE last_enqueue_time <> 0 AND interval_duration <> 0");
                    ContentValues contentValues = new ContentValues(1);
                    contentValues.put("last_enqueue_time", Long.valueOf(System.currentTimeMillis()));
                    aVar3.L(contentValues, new Object[0]);
                    break;
                default:
                    i.e(aVar3, "db");
                    aVar3.s("UPDATE WorkSpec SET `last_enqueue_time` = -1 WHERE `last_enqueue_time` = 0");
                    break;
            }
        }
    }

    public static String b(int i4, int i5, String str, String str2) {
        return str + i4 + str2 + i5;
    }

    public static String c(int i4, String str) {
        return str + i4;
    }

    public static String d(int i4, String str, String str2) {
        return str + i4 + str2;
    }

    public static n e(String str, String str2) {
        i.e(str, str2);
        return new n();
    }

    public static void f(String str, String str2) {
        h.a0(str + str2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void g(w.c cVar) throws Exception {
        if (cVar instanceof AutoCloseable) {
            cVar.close();
            return;
        }
        if (cVar instanceof ExecutorService) {
            x.f((ExecutorService) cVar);
            return;
        }
        if (cVar instanceof TypedArray) {
            ((TypedArray) cVar).recycle();
            return;
        }
        if (cVar instanceof MediaMetadataRetriever) {
            ((MediaMetadataRetriever) cVar).release();
            return;
        }
        if (cVar instanceof MediaDrm) {
            ((MediaDrm) cVar).release();
        } else if (cVar instanceof DrmManagerClient) {
            ((DrmManagerClient) cVar).release();
        } else {
            if (!(cVar instanceof ContentProviderClient)) {
                throw new IllegalArgumentException();
            }
            ((ContentProviderClient) cVar).release();
        }
    }

    public static /* synthetic */ String h(int i4) {
        switch (i4) {
            case 1:
                return "BEGIN_ARRAY";
            case 2:
                return "END_ARRAY";
            case 3:
                return "BEGIN_OBJECT";
            case 4:
                return "END_OBJECT";
            case 5:
                return "NAME";
            case 6:
                return "STRING";
            case 7:
                return "NUMBER";
            case 8:
                return "BOOLEAN";
            case 9:
                return "NULL";
            case 10:
                return "END_DOCUMENT";
            default:
                return "null";
        }
    }
}
