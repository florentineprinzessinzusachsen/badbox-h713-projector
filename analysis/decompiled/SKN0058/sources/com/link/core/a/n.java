package com.link.core.a;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

/* JADX INFO: loaded from: classes.dex */
public final class n {
    public static byte[] a(byte[] bArr, i iVar, String str) {
        byte[] bytes;
        byte[] bArr2 = new byte[2140];
        Object obj = a.a;
        byte[] bArr3 = new byte[2108];
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr3);
        int i = Integer.parseInt(iVar.a.a);
        byteBufferWrap.order(k.a);
        byteBufferWrap.put(bArr);
        byteBufferWrap.putInt(156);
        byteBufferWrap.putInt(i);
        byteBufferWrap.put(new byte[32]);
        if (str != null) {
            while (true) {
                bytes = str.getBytes(StandardCharsets.UTF_8);
                if (bytes.length <= 2048) {
                    break;
                }
                str = str.substring(0, str.length() - 1);
            }
            byteBufferWrap.putInt(bytes.length);
            byteBufferWrap.put(bytes);
        }
        ByteBuffer byteBufferWrap2 = ByteBuffer.wrap(bArr2);
        byteBufferWrap2.order(k.a);
        if (byteBufferWrap2.order() == k.a && byteBufferWrap2.remaining() >= 32) {
            byteBufferWrap2.putInt(-855635876);
            byte b = (byte) 0;
            byteBufferWrap2.put(b);
            byteBufferWrap2.put(b);
            byteBufferWrap2.putShort((short) 8);
            byteBufferWrap2.putLong(24032810107L);
            byteBufferWrap2.putLong(0L);
            byteBufferWrap2.putLong(0L);
            byteBufferWrap2.put(bArr3);
        }
        return bArr2;
    }
}
