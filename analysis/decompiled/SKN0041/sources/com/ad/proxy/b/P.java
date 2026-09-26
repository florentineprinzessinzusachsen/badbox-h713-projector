package com.ad.proxy.b;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/* JADX INFO: loaded from: classes.dex */
public final class P implements Runnable {
    public final /* synthetic */ com.ad.proxy.c.A a;
    public final /* synthetic */ a0 b;

    public P(a0 a0Var, com.ad.proxy.c.A a) {
        this.b = a0Var;
        this.a = a;
    }

    @Override // java.lang.Runnable
    public final void run() {
        StringBuilder sb = new StringBuilder();
        com.ad.proxy.c.A a = this.a;
        com.ad.proxy.c.A a2 = new com.ad.proxy.c.A(a.a, a.b, (byte) 13, 0, "".getBytes());
        try {
            String str = new String(this.a.g);
            com.ad.proxy.g.F.a("SocketClient", "ping host: ".concat(str));
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec("ping -c 10 ".concat(str)).getInputStream()));
            while (true) {
                String line = bufferedReader.readLine();
                if (line == null) {
                    a2.g = sb.toString().getBytes();
                    a0 a0Var = this.b;
                    a0Var.e.execute(new T(a0Var, a2, null));
                    return;
                }
                sb.append(line);
                sb.append("\n");
            }
        } catch (Exception e) {
            com.ad.proxy.g.F.a("SocketClient", e);
            sb.append(e.getMessage());
            a2.g = sb.toString().getBytes();
            a0 a0Var2 = this.b;
            a0Var2.e.execute(new T(a0Var2, a2, null));
        }
    }
}
