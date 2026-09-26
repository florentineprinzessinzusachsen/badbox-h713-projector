package com.link.core.a;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* JADX INFO: loaded from: classes.dex */
public final class k {
    public static final ByteOrder a = ByteOrder.LITTLE_ENDIAN;

    public static class a {
        public int a = 0;
        public int b = 0;
        public int c = 0;
        public int d = 0;
        public long e = 0;
        public byte[] f;
    }

    public static a a(ByteBuffer byteBuffer) {
        if (byteBuffer.order() != a || byteBuffer.remaining() < 32) {
            return null;
        }
        a aVar = new a();
        aVar.a = byteBuffer.getInt();
        aVar.b = byteBuffer.get() & 255;
        aVar.c = byteBuffer.get() & 255;
        aVar.d = byteBuffer.getShort() & 65535;
        aVar.e = byteBuffer.getLong();
        byte[] bArr = new byte[16];
        aVar.f = bArr;
        byteBuffer.get(bArr);
        int i = aVar.a;
        if (!(((-16777216) & i) == -855638016 && (i & 16777215) <= 4096)) {
            return null;
        }
        aVar.a = i & 16777215;
        return aVar;
    }
}
