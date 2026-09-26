package androidx.loader.a;

import androidx.lifecycle.h;
import androidx.lifecycle.t;
import java.io.FileDescriptor;
import java.io.PrintWriter;

/* JADX INFO: compiled from: LoaderManager.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class a {
    public static <T extends h & t> a a(T t) {
        return new b(t, t.d());
    }

    public abstract void a();

    @Deprecated
    public abstract void a(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr);
}
