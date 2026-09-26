package o;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f1531a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f1532b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final long f1533c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final long f1534d;

    public l(int i4, int i5, long j4, long j5) {
        this.f1531a = i4;
        this.f1532b = i5;
        this.f1533c = j4;
        this.f1534d = j5;
    }

    public static l a(File file) throws IOException {
        DataInputStream dataInputStream = new DataInputStream(new FileInputStream(file));
        try {
            l lVar = new l(dataInputStream.readInt(), dataInputStream.readInt(), dataInputStream.readLong(), dataInputStream.readLong());
            dataInputStream.close();
            return lVar;
        } catch (Throwable th) {
            try {
                dataInputStream.close();
                throw th;
            } catch (Throwable th2) {
                th.addSuppressed(th2);
                throw th;
            }
        }
    }

    public final void b(File file) throws IOException {
        file.delete();
        DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(file));
        try {
            dataOutputStream.writeInt(this.f1531a);
            dataOutputStream.writeInt(this.f1532b);
            dataOutputStream.writeLong(this.f1533c);
            dataOutputStream.writeLong(this.f1534d);
            dataOutputStream.close();
        } catch (Throwable th) {
            try {
                dataOutputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && (obj instanceof l)) {
            l lVar = (l) obj;
            if (this.f1532b == lVar.f1532b && this.f1533c == lVar.f1533c && this.f1531a == lVar.f1531a && this.f1534d == lVar.f1534d) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hash(Integer.valueOf(this.f1532b), Long.valueOf(this.f1533c), Integer.valueOf(this.f1531a), Long.valueOf(this.f1534d));
    }
}
