package androidx.appcompat.widget;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.database.DataSetObservable;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;
import android.util.Xml;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

/* JADX INFO: compiled from: ActivityChooserModel.java */
/* JADX INFO: loaded from: classes.dex */
class b extends DataSetObservable {
    static final String n = b.class.getSimpleName();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Object f702a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final List<C0009b> f703b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final List<d> f704c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final Context f705d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    final String f706e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private Intent f707f;
    private c g;
    private int h;
    boolean i;
    private boolean j;
    private boolean k;
    private boolean l;
    private e m;

    /* JADX INFO: compiled from: ActivityChooserModel.java */
    public interface a {
    }

    /* JADX INFO: renamed from: androidx.appcompat.widget.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ActivityChooserModel.java */
    public static final class C0009b implements Comparable<C0009b> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final ResolveInfo f708a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public float f709b;

        public C0009b(ResolveInfo resolveInfo) {
            this.f708a = resolveInfo;
        }

        @Override // java.lang.Comparable
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compareTo(C0009b c0009b) {
            return Float.floatToIntBits(c0009b.f709b) - Float.floatToIntBits(this.f709b);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && C0009b.class == obj.getClass() && Float.floatToIntBits(this.f709b) == Float.floatToIntBits(((C0009b) obj).f709b);
        }

        public int hashCode() {
            return Float.floatToIntBits(this.f709b) + 31;
        }

        public String toString() {
            return "[resolveInfo:" + this.f708a.toString() + "; weight:" + new BigDecimal(this.f709b) + "]";
        }
    }

    /* JADX INFO: compiled from: ActivityChooserModel.java */
    public interface c {
        void a(Intent intent, List<C0009b> list, List<d> list2);
    }

    /* JADX INFO: compiled from: ActivityChooserModel.java */
    public static final class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final ComponentName f710a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final long f711b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final float f712c;

        public d(String str, long j, float f2) {
            this(ComponentName.unflattenFromString(str), j, f2);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || d.class != obj.getClass()) {
                return false;
            }
            d dVar = (d) obj;
            ComponentName componentName = this.f710a;
            if (componentName == null) {
                if (dVar.f710a != null) {
                    return false;
                }
            } else if (!componentName.equals(dVar.f710a)) {
                return false;
            }
            return this.f711b == dVar.f711b && Float.floatToIntBits(this.f712c) == Float.floatToIntBits(dVar.f712c);
        }

        public int hashCode() {
            ComponentName componentName = this.f710a;
            int iHashCode = componentName == null ? 0 : componentName.hashCode();
            long j = this.f711b;
            return ((((iHashCode + 31) * 31) + ((int) (j ^ (j >>> 32)))) * 31) + Float.floatToIntBits(this.f712c);
        }

        public String toString() {
            return "[; activity:" + this.f710a + "; time:" + this.f711b + "; weight:" + new BigDecimal(this.f712c) + "]";
        }

        public d(ComponentName componentName, long j, float f2) {
            this.f710a = componentName;
            this.f711b = j;
            this.f712c = f2;
        }
    }

    /* JADX INFO: compiled from: ActivityChooserModel.java */
    public interface e {
        boolean a(b bVar, Intent intent);
    }

    /* JADX INFO: compiled from: ActivityChooserModel.java */
    private final class f extends AsyncTask<Object, Void, Void> {
        f() {
        }

        /* JADX WARN: Code duplicated, block: B:43:0x006f A[DONT_GENERATE, EXC_TOP_SPLITTER, SYNTHETIC] */
        @Override // android.os.AsyncTask
        public Void doInBackground(Object... objArr) {
            List list = (List) objArr[0];
            String str = (String) objArr[1];
            try {
                FileOutputStream fileOutputStreamOpenFileOutput = b.this.f705d.openFileOutput(str, 0);
                XmlSerializer xmlSerializerNewSerializer = Xml.newSerializer();
                try {
                    xmlSerializerNewSerializer.setOutput(fileOutputStreamOpenFileOutput, null);
                    xmlSerializerNewSerializer.startDocument("UTF-8", true);
                    xmlSerializerNewSerializer.startTag(null, "historical-records");
                    int size = list.size();
                    for (int i = 0; i < size; i++) {
                        d dVar = (d) list.remove(0);
                        xmlSerializerNewSerializer.startTag(null, "historical-record");
                        xmlSerializerNewSerializer.attribute(null, "activity", dVar.f710a.flattenToString());
                        xmlSerializerNewSerializer.attribute(null, "time", String.valueOf(dVar.f711b));
                        xmlSerializerNewSerializer.attribute(null, "weight", String.valueOf(dVar.f712c));
                        xmlSerializerNewSerializer.endTag(null, "historical-record");
                    }
                    xmlSerializerNewSerializer.endTag(null, "historical-records");
                    xmlSerializerNewSerializer.endDocument();
                } catch (IllegalStateException e2) {
                    Log.e(b.n, "Error writing historical record file: " + b.this.f706e, e2);
                } catch (IOException e3) {
                    Log.e(b.n, "Error writing historical record file: " + b.this.f706e, e3);
                } catch (IllegalArgumentException e4) {
                    Log.e(b.n, "Error writing historical record file: " + b.this.f706e, e4);
                } finally {
                    b.this.i = true;
                    if (fileOutputStreamOpenFileOutput != null) {
                        try {
                            fileOutputStreamOpenFileOutput.close();
                        } catch (IOException unused) {
                        }
                    }
                }
                return null;
            } catch (FileNotFoundException e5) {
                Log.e(b.n, "Error writing historical record file: " + str, e5);
                return null;
            }
        }
    }

    static {
        new HashMap();
    }

    private void d() {
        boolean zE = e() | h();
        g();
        if (zE) {
            j();
            notifyChanged();
        }
    }

    private boolean e() {
        if (!this.l || this.f707f == null) {
            return false;
        }
        this.l = false;
        this.f703b.clear();
        List<ResolveInfo> listQueryIntentActivities = this.f705d.getPackageManager().queryIntentActivities(this.f707f, 0);
        int size = listQueryIntentActivities.size();
        for (int i = 0; i < size; i++) {
            this.f703b.add(new C0009b(listQueryIntentActivities.get(i)));
        }
        return true;
    }

    private void f() {
        if (!this.j) {
            throw new IllegalStateException("No preceding call to #readHistoricalData");
        }
        if (this.k) {
            this.k = false;
            if (TextUtils.isEmpty(this.f706e)) {
                return;
            }
            new f().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new ArrayList(this.f704c), this.f706e);
        }
    }

    private void g() {
        int size = this.f704c.size() - this.h;
        if (size <= 0) {
            return;
        }
        this.k = true;
        for (int i = 0; i < size; i++) {
            this.f704c.remove(0);
        }
    }

    private boolean h() {
        if (!this.i || !this.k || TextUtils.isEmpty(this.f706e)) {
            return false;
        }
        this.i = false;
        this.j = true;
        i();
        return true;
    }

    private void i() {
        try {
            FileInputStream fileInputStreamOpenFileInput = this.f705d.openFileInput(this.f706e);
            try {
                try {
                    try {
                        XmlPullParser xmlPullParserNewPullParser = Xml.newPullParser();
                        xmlPullParserNewPullParser.setInput(fileInputStreamOpenFileInput, "UTF-8");
                        for (int next = 0; next != 1 && next != 2; next = xmlPullParserNewPullParser.next()) {
                        }
                        if (!"historical-records".equals(xmlPullParserNewPullParser.getName())) {
                            throw new XmlPullParserException("Share records file does not start with historical-records tag.");
                        }
                        List<d> list = this.f704c;
                        list.clear();
                        while (true) {
                            int next2 = xmlPullParserNewPullParser.next();
                            if (next2 == 1) {
                                if (fileInputStreamOpenFileInput != null) {
                                    break;
                                } else {
                                    return;
                                }
                            } else if (next2 != 3 && next2 != 4) {
                                if (!"historical-record".equals(xmlPullParserNewPullParser.getName())) {
                                    throw new XmlPullParserException("Share records file not well-formed.");
                                }
                                list.add(new d(xmlPullParserNewPullParser.getAttributeValue(null, "activity"), Long.parseLong(xmlPullParserNewPullParser.getAttributeValue(null, "time")), Float.parseFloat(xmlPullParserNewPullParser.getAttributeValue(null, "weight"))));
                            }
                        }
                        try {
                            fileInputStreamOpenFileInput.close();
                        } catch (IOException unused) {
                        }
                    } catch (IOException e2) {
                        Log.e(n, "Error reading historical recrod file: " + this.f706e, e2);
                        if (fileInputStreamOpenFileInput == null) {
                        }
                    }
                } catch (XmlPullParserException e3) {
                    Log.e(n, "Error reading historical recrod file: " + this.f706e, e3);
                    if (fileInputStreamOpenFileInput == null) {
                    }
                }
            } catch (Throwable th) {
                if (fileInputStreamOpenFileInput != null) {
                    try {
                        fileInputStreamOpenFileInput.close();
                    } catch (IOException unused2) {
                    }
                }
                throw th;
            }
        } catch (FileNotFoundException unused3) {
        }
    }

    private boolean j() {
        if (this.g == null || this.f707f == null || this.f703b.isEmpty() || this.f704c.isEmpty()) {
            return false;
        }
        this.g.a(this.f707f, this.f703b, Collections.unmodifiableList(this.f704c));
        return true;
    }

    public int a() {
        int size;
        synchronized (this.f702a) {
            d();
            size = this.f703b.size();
        }
        return size;
    }

    public ResolveInfo b(int i) {
        ResolveInfo resolveInfo;
        synchronized (this.f702a) {
            d();
            resolveInfo = this.f703b.get(i).f708a;
        }
        return resolveInfo;
    }

    public void c(int i) {
        synchronized (this.f702a) {
            d();
            C0009b c0009b = this.f703b.get(i);
            C0009b c0009b2 = this.f703b.get(0);
            a(new d(new ComponentName(c0009b.f708a.activityInfo.packageName, c0009b.f708a.activityInfo.name), System.currentTimeMillis(), c0009b2 != null ? (c0009b2.f709b - c0009b.f709b) + 5.0f : 1.0f));
        }
    }

    public int a(ResolveInfo resolveInfo) {
        synchronized (this.f702a) {
            d();
            List<C0009b> list = this.f703b;
            int size = list.size();
            for (int i = 0; i < size; i++) {
                if (list.get(i).f708a == resolveInfo) {
                    return i;
                }
            }
            return -1;
        }
    }

    public ResolveInfo b() {
        synchronized (this.f702a) {
            d();
            if (this.f703b.isEmpty()) {
                return null;
            }
            return this.f703b.get(0).f708a;
        }
    }

    public int c() {
        int size;
        synchronized (this.f702a) {
            d();
            size = this.f704c.size();
        }
        return size;
    }

    public Intent a(int i) {
        synchronized (this.f702a) {
            if (this.f707f == null) {
                return null;
            }
            d();
            C0009b c0009b = this.f703b.get(i);
            ComponentName componentName = new ComponentName(c0009b.f708a.activityInfo.packageName, c0009b.f708a.activityInfo.name);
            Intent intent = new Intent(this.f707f);
            intent.setComponent(componentName);
            if (this.m != null) {
                if (this.m.a(this, new Intent(intent))) {
                    return null;
                }
            }
            a(new d(componentName, System.currentTimeMillis(), 1.0f));
            return intent;
        }
    }

    private boolean a(d dVar) {
        boolean zAdd = this.f704c.add(dVar);
        if (zAdd) {
            this.k = true;
            g();
            f();
            j();
            notifyChanged();
        }
        return zAdd;
    }
}
