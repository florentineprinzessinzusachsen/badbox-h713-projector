package androidx.core.app;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: compiled from: TaskStackBuilder.java */
/* JADX INFO: loaded from: classes.dex */
public final class n implements Iterable<Intent> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final ArrayList<Intent> f968a = new ArrayList<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Context f969b;

    /* JADX INFO: compiled from: TaskStackBuilder.java */
    public interface a {
        Intent e();
    }

    private n(Context context) {
        this.f969b = context;
    }

    public static n a(Context context) {
        return new n(context);
    }

    @Override // java.lang.Iterable
    @Deprecated
    public Iterator<Intent> iterator() {
        return this.f968a.iterator();
    }

    public n a(Intent intent) {
        this.f968a.add(intent);
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public n a(Activity activity) {
        Intent intentE = activity instanceof a ? ((a) activity).e() : null;
        if (intentE == null) {
            intentE = f.a(activity);
        }
        if (intentE != null) {
            ComponentName component = intentE.getComponent();
            if (component == null) {
                component = intentE.resolveActivity(this.f969b.getPackageManager());
            }
            a(component);
            a(intentE);
        }
        return this;
    }

    public n a(ComponentName componentName) {
        int size = this.f968a.size();
        try {
            Intent intentA = f.a(this.f969b, componentName);
            while (intentA != null) {
                this.f968a.add(size, intentA);
                intentA = f.a(this.f969b, intentA.getComponent());
            }
            return this;
        } catch (PackageManager.NameNotFoundException e2) {
            Log.e("TaskStackBuilder", "Bad ComponentName while traversing activity parent metadata");
            throw new IllegalArgumentException(e2);
        }
    }

    public void a() {
        a((Bundle) null);
    }

    public void a(Bundle bundle) {
        if (!this.f968a.isEmpty()) {
            ArrayList<Intent> arrayList = this.f968a;
            Intent[] intentArr = (Intent[]) arrayList.toArray(new Intent[arrayList.size()]);
            intentArr[0] = new Intent(intentArr[0]).addFlags(268484608);
            if (androidx.core.content.a.a(this.f969b, intentArr, bundle)) {
                return;
            }
            Intent intent = new Intent(intentArr[intentArr.length - 1]);
            intent.addFlags(268435456);
            this.f969b.startActivity(intent);
            return;
        }
        throw new IllegalStateException("No intents added to TaskStackBuilder; cannot startActivities");
    }
}
