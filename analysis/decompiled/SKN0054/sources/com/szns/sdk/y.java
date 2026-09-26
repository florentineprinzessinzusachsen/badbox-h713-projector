package com.szns.sdk;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

/* JADX INFO: loaded from: classes.dex */
final class y implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ String b;
    final /* synthetic */ ab c;

    y(String str, String str2, ah ahVar) {
        this.a = str;
        this.b = str2;
        this.c = ahVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        String[] strArrSplit = this.a.split(":");
        Socket socket = new Socket();
        try {
            socket.connect(new InetSocketAddress(strArrSplit[0], Integer.parseInt(strArrSplit[1])), 40000);
            String str = this.b;
            av avVarA = ar.a(ar.a(socket));
            if (avVarA.isOpen()) {
                avVarA.b(str.getBytes());
                avVarA.flush();
            }
            aw awVarA = ar.a(ar.b(socket));
            an anVar = new an();
            this.c.a(awVarA.a_(anVar, 1024L) != -1 ? anVar.e() : "");
            if (socket.isClosed()) {
                return;
            }
            socket.close();
        } catch (IOException e) {
            this.c.b("response.body == " + e.getMessage());
        }
    }
}
