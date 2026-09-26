package com.ad.proxy.b;

import java.io.DataOutputStream;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public abstract class I {
    public static void a(DataOutputStream dataOutputStream, com.ad.proxy.c.A a) throws IOException {
        dataOutputStream.writeBytes("KK");
        dataOutputStream.writeLong(a.a);
        dataOutputStream.write(a.a());
        dataOutputStream.writeByte(a.c);
        dataOutputStream.writeByte(a.d);
        dataOutputStream.writeInt(a.e);
        dataOutputStream.writeInt(a.f);
        if (a.e > 0) {
            dataOutputStream.write(a.g);
        }
        dataOutputStream.flush();
    }
}
