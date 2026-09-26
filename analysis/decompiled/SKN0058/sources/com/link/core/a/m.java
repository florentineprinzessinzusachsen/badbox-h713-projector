package com.link.core.a;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;

/* JADX INFO: loaded from: classes.dex */
public final class m {
    public static v a(DatagramPacket datagramPacket) {
        if (datagramPacket.getLength() != 54) {
            return null;
        }
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(datagramPacket.getData());
        byteBufferWrap.order(k.a);
        k.a aVarA = k.a(byteBufferWrap);
        if (aVarA != null && aVarA.a == 54 && aVarA.d == 4) {
            try {
                byte[] bArr = new byte[4];
                byteBufferWrap.get(bArr);
                v vVar = new v();
                vVar.a = InetAddress.getByAddress(bArr);
                vVar.b = 65535 & byteBufferWrap.getShort();
                byte[] bArr2 = new byte[16];
                vVar.c = bArr2;
                byteBufferWrap.get(bArr2);
                return vVar;
            } catch (UnknownHostException unused) {
            }
        }
        return null;
    }

    public static byte[] a(InetAddress inetAddress) {
        byte[] bArr = new byte[100];
        byte[] bArr2 = new byte[132];
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr);
        ByteOrder byteOrder = k.a;
        byteBufferWrap.order(byteOrder);
        byteBufferWrap.put(inetAddress.getAddress());
        byteBufferWrap.put("".getBytes(StandardCharsets.UTF_8));
        ByteBuffer byteBufferWrap2 = ByteBuffer.wrap(bArr2);
        byteBufferWrap2.order(byteOrder);
        if (byteBufferWrap2.order() == byteOrder && byteBufferWrap2.remaining() >= 32) {
            byteBufferWrap2.putInt(-855637884);
            byte b = (byte) 0;
            byteBufferWrap2.put(b);
            byteBufferWrap2.put(b);
            byteBufferWrap2.putShort((short) 3);
            byteBufferWrap2.putLong(0L);
            byteBufferWrap2.putLong(0L);
            byteBufferWrap2.putLong(0L);
            byteBufferWrap2.put(bArr);
        }
        return bArr2;
    }
}
