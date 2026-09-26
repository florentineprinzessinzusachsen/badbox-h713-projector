package z;

import android.util.Log;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.HashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final HashMap f2773e = new HashMap();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final boolean f2774a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final File f2775b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Lock f2776c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public FileChannel f2777d;

    public a(String str, File file, boolean z3) {
        Lock lock;
        this.f2774a = z3;
        this.f2775b = file != null ? new File(file, str.concat(".lck")) : null;
        HashMap map = f2773e;
        synchronized (map) {
            try {
                Object reentrantLock = map.get(str);
                if (reentrantLock == null) {
                    reentrantLock = new ReentrantLock();
                    map.put(str, reentrantLock);
                }
                lock = (Lock) reentrantLock;
            } catch (Throwable th) {
                throw th;
            }
        }
        this.f2776c = lock;
    }

    public final void a(boolean z3) {
        this.f2776c.lock();
        if (z3) {
            File file = this.f2775b;
            try {
                if (file == null) {
                    throw new IOException("No lock directory was provided.");
                }
                File parentFile = file.getParentFile();
                if (parentFile != null) {
                    parentFile.mkdirs();
                }
                FileChannel channel = new FileOutputStream(file).getChannel();
                channel.lock();
                this.f2777d = channel;
            } catch (IOException e4) {
                this.f2777d = null;
                Log.w("SupportSQLiteLock", "Unable to grab file lock.", e4);
            }
        }
    }

    public final void b() {
        try {
            FileChannel fileChannel = this.f2777d;
            if (fileChannel != null) {
                fileChannel.close();
            }
        } catch (IOException unused) {
        }
        this.f2776c.unlock();
    }
}
