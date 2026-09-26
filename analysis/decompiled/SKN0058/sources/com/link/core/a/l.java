package com.link.core.a;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
public final class l {
    public static x a(DatagramPacket datagramPacket) {
        if (datagramPacket.getLength() != 36) {
            return null;
        }
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(datagramPacket.getData());
        byteBufferWrap.order(k.a);
        k.a aVarA = k.a(byteBufferWrap);
        if (aVarA != null && aVarA.a == 36 && aVarA.d == 2) {
            byte[] bArr = new byte[4];
            byteBufferWrap.get(bArr);
            try {
                x xVar = new x();
                long j = aVarA.e;
                xVar.a = (int) (j >> 32);
                xVar.b = (int) (j & (-1));
                xVar.c = InetAddress.getByAddress(bArr);
                return xVar;
            } catch (UnknownHostException unused) {
            }
        }
        return null;
    }
}
