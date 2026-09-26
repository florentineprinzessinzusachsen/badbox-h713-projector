package c.a.z;

import c.a.b0.j.j;

/* JADX INFO: compiled from: Exceptions.java */
/* JADX INFO: loaded from: classes.dex */
public final class b {
    public static RuntimeException a(Throwable th) {
        throw j.a(th);
    }

    public static void b(Throwable th) {
        if (th instanceof VirtualMachineError) {
            throw ((VirtualMachineError) th);
        }
        if (th instanceof ThreadDeath) {
            throw ((ThreadDeath) th);
        }
        if (th instanceof LinkageError) {
            throw ((LinkageError) th);
        }
    }
}
