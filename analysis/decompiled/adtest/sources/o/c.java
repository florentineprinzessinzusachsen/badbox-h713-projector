package o;

import android.content.res.AssetManager;
import android.os.Build;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Executor f1501a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final f f1502b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final byte[] f1503c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final File f1504d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final String f1505e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f1506f = false;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public d[] f1507g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public byte[] f1508h;

    public c(AssetManager assetManager, Executor executor, f fVar, String str, File file) {
        this.f1501a = executor;
        this.f1502b = fVar;
        this.f1505e = str;
        this.f1504d = file;
        int i4 = Build.VERSION.SDK_INT;
        byte[] bArr = null;
        if (i4 >= 24) {
            if (i4 < 31) {
                switch (i4) {
                    case 24:
                    case 25:
                        bArr = g.f1525h;
                        break;
                    case 26:
                        bArr = g.f1524g;
                        break;
                    case 27:
                        bArr = g.f1523f;
                        break;
                    case 28:
                    case 29:
                    case 30:
                        bArr = g.f1522e;
                        break;
                }
            } else {
                bArr = g.f1521d;
            }
        }
        this.f1503c = bArr;
    }

    public final FileInputStream a(AssetManager assetManager, String str) {
        try {
            return assetManager.openFd(str).createInputStream();
        } catch (FileNotFoundException e4) {
            String message = e4.getMessage();
            if (message == null || !message.contains("compressed")) {
                return null;
            }
            this.f1502b.b();
            return null;
        }
    }

    public final void b(final int i4, final Serializable serializable) {
        this.f1501a.execute(new Runnable() { // from class: o.b
            @Override // java.lang.Runnable
            public final void run() {
                this.f1498d.f1502b.f(i4, serializable);
            }
        });
    }
}
