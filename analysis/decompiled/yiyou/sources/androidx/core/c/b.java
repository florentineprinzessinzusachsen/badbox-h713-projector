package androidx.core.c;

import android.content.ContentUris;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.Signature;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.CancellationSignal;
import android.os.Handler;
import androidx.core.a.i;
import com.android.umanalytics.http.ApiException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

/* JADX INFO: compiled from: FontsContractCompat.java */
/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final a.b.e<String, Typeface> f976a = new a.b.e<>(16);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final androidx.core.c.c f977b = new androidx.core.c.c("fonts", 10, 10000);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    static final Object f978c = new Object();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    static final a.b.g<String, ArrayList<androidx.core.c.c.d<g>>> f979d = new a.b.g<>();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static final Comparator<byte[]> f980e = new d();

    /* JADX INFO: compiled from: FontsContractCompat.java */
    static class a implements Callable<g> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Context f981a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ androidx.core.c.a f982b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ int f983c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ String f984d;

        a(Context context, androidx.core.c.a aVar, int i, String str) {
            this.f981a = context;
            this.f982b = aVar;
            this.f983c = i;
            this.f984d = str;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.concurrent.Callable
        public g call() {
            g gVarA = b.a(this.f981a, this.f982b, this.f983c);
            Typeface typeface = gVarA.f995a;
            if (typeface != null) {
                b.f976a.a(this.f984d, typeface);
            }
            return gVarA;
        }
    }

    /* JADX INFO: renamed from: androidx.core.c.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: FontsContractCompat.java */
    static class C0016b implements androidx.core.c.c.d<g> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ androidx.core.content.c.f.a f985a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ Handler f986b;

        C0016b(androidx.core.content.c.f.a aVar, Handler handler) {
            this.f985a = aVar;
            this.f986b = handler;
        }

        @Override // androidx.core.c.c.d
        public void a(g gVar) {
            if (gVar == null) {
                this.f985a.a(1, this.f986b);
                return;
            }
            int i = gVar.f996b;
            if (i == 0) {
                this.f985a.a(gVar.f995a, this.f986b);
            } else {
                this.f985a.a(i, this.f986b);
            }
        }
    }

    /* JADX INFO: compiled from: FontsContractCompat.java */
    static class c implements androidx.core.c.c.d<g> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ String f987a;

        c(String str) {
            this.f987a = str;
        }

        @Override // androidx.core.c.c.d
        public void a(g gVar) {
            synchronized (b.f978c) {
                ArrayList<androidx.core.c.c.d<g>> arrayList = b.f979d.get(this.f987a);
                if (arrayList == null) {
                    return;
                }
                b.f979d.remove(this.f987a);
                for (int i = 0; i < arrayList.size(); i++) {
                    arrayList.get(i).a(gVar);
                }
            }
        }
    }

    /* JADX INFO: compiled from: FontsContractCompat.java */
    static class d implements Comparator<byte[]> {
        d() {
        }

        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(byte[] bArr, byte[] bArr2) {
            int length;
            int length2;
            if (bArr.length == bArr2.length) {
                for (int i = 0; i < bArr.length; i++) {
                    if (bArr[i] != bArr2[i]) {
                        length = bArr[i];
                        length2 = bArr2[i];
                    }
                }
                return 0;
            }
            length = bArr.length;
            length2 = bArr2.length;
            return length - length2;
        }
    }

    /* JADX INFO: compiled from: FontsContractCompat.java */
    public static class e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final int f988a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final f[] f989b;

        public e(int i, f[] fVarArr) {
            this.f988a = i;
            this.f989b = fVarArr;
        }

        public f[] a() {
            return this.f989b;
        }

        public int b() {
            return this.f988a;
        }
    }

    /* JADX INFO: compiled from: FontsContractCompat.java */
    public static class f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Uri f990a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final int f991b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final int f992c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final boolean f993d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final int f994e;

        public f(Uri uri, int i, int i2, boolean z, int i3) {
            androidx.core.e.e.a(uri);
            this.f990a = uri;
            this.f991b = i;
            this.f992c = i2;
            this.f993d = z;
            this.f994e = i3;
        }

        public int a() {
            return this.f994e;
        }

        public int b() {
            return this.f991b;
        }

        public Uri c() {
            return this.f990a;
        }

        public int d() {
            return this.f992c;
        }

        public boolean e() {
            return this.f993d;
        }
    }

    /* JADX INFO: compiled from: FontsContractCompat.java */
    private static final class g {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final Typeface f995a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final int f996b;

        g(Typeface typeface, int i) {
            this.f995a = typeface;
            this.f996b = i;
        }
    }

    static g a(Context context, androidx.core.c.a aVar, int i) {
        try {
            e eVarA = a(context, (CancellationSignal) null, aVar);
            if (eVarA.b() != 0) {
                return new g(null, eVarA.b() == 1 ? -2 : -3);
            }
            Typeface typefaceA = androidx.core.a.c.a(context, null, eVarA.a(), i);
            return new g(typefaceA, typefaceA != null ? 0 : -3);
        } catch (PackageManager.NameNotFoundException unused) {
            return new g(null, -1);
        }
    }

    public static Typeface a(Context context, androidx.core.c.a aVar, androidx.core.content.c.f.a aVar2, Handler handler, boolean z, int i, int i2) {
        String str = aVar.c() + "-" + i2;
        Typeface typefaceB = f976a.b(str);
        if (typefaceB != null) {
            if (aVar2 != null) {
                aVar2.a(typefaceB);
            }
            return typefaceB;
        }
        if (z && i == -1) {
            g gVarA = a(context, aVar, i2);
            if (aVar2 != null) {
                int i3 = gVarA.f996b;
                if (i3 == 0) {
                    aVar2.a(gVarA.f995a, handler);
                } else {
                    aVar2.a(i3, handler);
                }
            }
            return gVarA.f995a;
        }
        a aVar3 = new a(context, aVar, i2, str);
        if (z) {
            try {
                return ((g) f977b.a(aVar3, i)).f995a;
            } catch (InterruptedException unused) {
                return null;
            }
        }
        C0016b c0016b = aVar2 == null ? null : new C0016b(aVar2, handler);
        synchronized (f978c) {
            ArrayList<androidx.core.c.c.d<g>> arrayList = f979d.get(str);
            if (arrayList != null) {
                if (c0016b != null) {
                    arrayList.add(c0016b);
                }
                return null;
            }
            if (c0016b != null) {
                ArrayList<androidx.core.c.c.d<g>> arrayList2 = new ArrayList<>();
                arrayList2.add(c0016b);
                f979d.put(str, arrayList2);
            }
            f977b.a(aVar3, new c(str));
            return null;
        }
    }

    public static Map<Uri, ByteBuffer> a(Context context, f[] fVarArr, CancellationSignal cancellationSignal) {
        HashMap map = new HashMap();
        for (f fVar : fVarArr) {
            if (fVar.a() == 0) {
                Uri uriC = fVar.c();
                if (!map.containsKey(uriC)) {
                    map.put(uriC, i.a(context, cancellationSignal, uriC));
                }
            }
        }
        return Collections.unmodifiableMap(map);
    }

    public static e a(Context context, CancellationSignal cancellationSignal, androidx.core.c.a aVar) throws PackageManager.NameNotFoundException {
        ProviderInfo providerInfoA = a(context.getPackageManager(), aVar, context.getResources());
        if (providerInfoA == null) {
            return new e(1, null);
        }
        return new e(0, a(context, aVar, providerInfoA.authority, cancellationSignal));
    }

    public static ProviderInfo a(PackageManager packageManager, androidx.core.c.a aVar, Resources resources) throws PackageManager.NameNotFoundException {
        String strD = aVar.d();
        ProviderInfo providerInfoResolveContentProvider = packageManager.resolveContentProvider(strD, 0);
        if (providerInfoResolveContentProvider != null) {
            if (providerInfoResolveContentProvider.packageName.equals(aVar.e())) {
                List<byte[]> listA = a(packageManager.getPackageInfo(providerInfoResolveContentProvider.packageName, 64).signatures);
                Collections.sort(listA, f980e);
                List<List<byte[]>> listA2 = a(aVar, resources);
                for (int i = 0; i < listA2.size(); i++) {
                    ArrayList arrayList = new ArrayList(listA2.get(i));
                    Collections.sort(arrayList, f980e);
                    if (a(listA, arrayList)) {
                        return providerInfoResolveContentProvider;
                    }
                }
                return null;
            }
            throw new PackageManager.NameNotFoundException("Found content provider " + strD + ", but package was not " + aVar.e());
        }
        throw new PackageManager.NameNotFoundException("No package found for authority: " + strD);
    }

    private static List<List<byte[]>> a(androidx.core.c.a aVar, Resources resources) {
        if (aVar.a() != null) {
            return aVar.a();
        }
        return androidx.core.content.c.c.a(resources, aVar.b());
    }

    private static boolean a(List<byte[]> list, List<byte[]> list2) {
        if (list.size() != list2.size()) {
            return false;
        }
        for (int i = 0; i < list.size(); i++) {
            if (!Arrays.equals(list.get(i), list2.get(i))) {
                return false;
            }
        }
        return true;
    }

    private static List<byte[]> a(Signature[] signatureArr) {
        ArrayList arrayList = new ArrayList();
        for (Signature signature : signatureArr) {
            arrayList.add(signature.toByteArray());
        }
        return arrayList;
    }

    static f[] a(Context context, androidx.core.c.a aVar, String str, CancellationSignal cancellationSignal) {
        Uri uriWithAppendedId;
        ArrayList arrayList = new ArrayList();
        Uri uriBuild = new Uri.Builder().scheme("content").authority(str).build();
        Uri uriBuild2 = new Uri.Builder().scheme("content").authority(str).appendPath("file").build();
        Cursor cursorQuery = null;
        try {
            if (Build.VERSION.SDK_INT > 16) {
                cursorQuery = context.getContentResolver().query(uriBuild, new String[]{"_id", "file_id", "font_ttc_index", "font_variation_settings", "font_weight", "font_italic", "result_code"}, "query = ?", new String[]{aVar.f()}, null, cancellationSignal);
            } else {
                cursorQuery = context.getContentResolver().query(uriBuild, new String[]{"_id", "file_id", "font_ttc_index", "font_variation_settings", "font_weight", "font_italic", "result_code"}, "query = ?", new String[]{aVar.f()}, null);
            }
            if (cursorQuery != null && cursorQuery.getCount() > 0) {
                int columnIndex = cursorQuery.getColumnIndex("result_code");
                ArrayList arrayList2 = new ArrayList();
                int columnIndex2 = cursorQuery.getColumnIndex("_id");
                int columnIndex3 = cursorQuery.getColumnIndex("file_id");
                int columnIndex4 = cursorQuery.getColumnIndex("font_ttc_index");
                int columnIndex5 = cursorQuery.getColumnIndex("font_weight");
                int columnIndex6 = cursorQuery.getColumnIndex("font_italic");
                while (cursorQuery.moveToNext()) {
                    int i = columnIndex != -1 ? cursorQuery.getInt(columnIndex) : 0;
                    int i2 = columnIndex4 != -1 ? cursorQuery.getInt(columnIndex4) : 0;
                    if (columnIndex3 == -1) {
                        uriWithAppendedId = ContentUris.withAppendedId(uriBuild, cursorQuery.getLong(columnIndex2));
                    } else {
                        uriWithAppendedId = ContentUris.withAppendedId(uriBuild2, cursorQuery.getLong(columnIndex3));
                    }
                    arrayList2.add(new f(uriWithAppendedId, i2, columnIndex5 != -1 ? cursorQuery.getInt(columnIndex5) : ApiException.FAILURE, columnIndex6 != -1 && cursorQuery.getInt(columnIndex6) == 1, i));
                }
                arrayList = arrayList2;
            }
            return (f[]) arrayList.toArray(new f[0]);
        } finally {
            if (cursorQuery != null) {
                cursorQuery.close();
            }
        }
    }
}
