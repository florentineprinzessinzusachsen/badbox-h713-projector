package com.link.core.a;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* JADX INFO: loaded from: classes.dex */
public final class o {
    public static byte[] a(long j) {
        byte[] bArr = new byte[9];
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr);
        ByteOrder byteOrder = k.a;
        byteBufferWrap.order(byteOrder);
        byteBufferWrap.putLong(j);
        byte[] bArr2 = new byte[41];
        ByteBuffer byteBufferWrap2 = ByteBuffer.wrap(bArr2);
        byteBufferWrap2.order(byteOrder);
        if (byteBufferWrap2.order() == k.a && byteBufferWrap2.remaining() >= 32) {
            byteBufferWrap2.putInt(-855637975);
            byte b = (byte) 0;
            byteBufferWrap2.put(b);
            byteBufferWrap2.put(b);
            byteBufferWrap2.putShort((short) 6);
            byteBufferWrap2.putLong(0L);
            byteBufferWrap2.putLong(0L);
            byteBufferWrap2.putLong(0L);
            byteBufferWrap2.put(bArr);
        }
        return bArr2;
    }
}
