package d.h0.g;

import d.a0;
import d.t;
import java.net.Proxy;

/* JADX INFO: compiled from: RequestLine.java */
/* JADX INFO: loaded from: classes.dex */
public final class i {
    public static String a(a0 a0Var, Proxy.Type type) {
        StringBuilder sb = new StringBuilder();
        sb.append(a0Var.e());
        sb.append(' ');
        if (b(a0Var, type)) {
            sb.append(a0Var.g());
        } else {
            sb.append(a(a0Var.g()));
        }
        sb.append(" HTTP/1.1");
        return sb.toString();
    }

    private static boolean b(a0 a0Var, Proxy.Type type) {
        return !a0Var.d() && type == Proxy.Type.HTTP;
    }

    public static String a(t tVar) {
        String strC = tVar.c();
        String strE = tVar.e();
        if (strE == null) {
            return strC;
        }
        return strC + '?' + strE;
    }
}
