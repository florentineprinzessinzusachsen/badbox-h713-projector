package d;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: compiled from: TlsVersion.java */
/* JADX INFO: loaded from: classes.dex */
public enum f0 {
    TLS_1_3("TLSv1.3"),
    TLS_1_2("TLSv1.2"),
    TLS_1_1("TLSv1.1"),
    TLS_1_0("TLSv1"),
    SSL_3_0("SSLv3");


    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final String f4320a;

    f0(String str) {
        this.f4320a = str;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:23:0x0048  */
    /* JADX WARN: Failed to clean up code after switch over string restore
    jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r0v0 int, still in use, count: 2, list:
      (r0v0 int) from 0x000b: IF  (r0v0 int) != (79201641 int)  -> B:4:0x000d A[HIDDEN]
      (r0v0 int) from 0x0010: IF  (r0v0 int) != (79923350 int)  -> B:6:0x0012 A[HIDDEN]
    	at jadx.core.utils.InsnRemover.removeSsaVar(InsnRemover.java:164)
    	at jadx.core.utils.InsnRemover.unbindResult(InsnRemover.java:129)
    	at jadx.core.utils.InsnRemover.unbindInsn(InsnRemover.java:93)
    	at jadx.core.utils.InsnRemover.remove(InsnRemover.java:226)
    	at jadx.core.utils.InsnRemover.remove(InsnRemover.java:215)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.replaceWithMergedSwitch(SwitchOverStringVisitor.java:355)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.restoreSwitchOverString(SwitchOverStringVisitor.java:111)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visitRegion(SwitchOverStringVisitor.java:72)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:140)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterative(DepthRegionTraversal.java:47)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visit(SwitchOverStringVisitor.java:66)
     */
    public static f0 a(String str) {
        byte b2;
        if (iHashCode != 79201641) {
            if (iHashCode != 79923350) {
                switch (str) {
                    case "TLSv1.1":
                        b2 = 2;
                        break;
                    case "TLSv1.2":
                        b2 = 1;
                        break;
                    case "TLSv1.3":
                        b2 = 0;
                        break;
                    default:
                        b2 = -1;
                        break;
                }
            } else if (str.equals("TLSv1")) {
                b2 = 3;
            } else {
                b2 = -1;
            }
        } else if (str.equals("SSLv3")) {
            b2 = 4;
        } else {
            b2 = -1;
        }
        if (b2 == 0) {
            return TLS_1_3;
        }
        if (b2 == 1) {
            return TLS_1_2;
        }
        if (b2 == 2) {
            return TLS_1_1;
        }
        if (b2 == 3) {
            return TLS_1_0;
        }
        if (b2 == 4) {
            return SSL_3_0;
        }
        throw new IllegalArgumentException("Unexpected TLS version: " + str);
    }

    static List<f0> a(String... strArr) {
        ArrayList arrayList = new ArrayList(strArr.length);
        for (String str : strArr) {
            arrayList.add(a(str));
        }
        return Collections.unmodifiableList(arrayList);
    }

    public String a() {
        return this.f4320a;
    }
}
