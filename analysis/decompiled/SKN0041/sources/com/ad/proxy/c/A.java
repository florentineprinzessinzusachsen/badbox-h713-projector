package com.ad.proxy.c;

import java.nio.ByteBuffer;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public final class A {
    public long a;
    public String b;
    public byte c;
    public byte d;
    public int e;
    public int f;
    public byte[] g;

    public A() {
    }

    public final byte[] a() {
        String str = this.b;
        long jM = A$$ExternalSyntheticBackport2.m(str, 0, str.length(), 16);
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(8);
        byteBufferAllocate.putLong(jM);
        return byteBufferAllocate.array();
    }

    public final String toString() {
        return String.format(Locale.US, "Packet[transaction=%016X, uuid=%s, ttl=%d, command=%d, payloadLength=%d]", Long.valueOf(this.a), this.b, Byte.valueOf(this.c), Byte.valueOf(this.d), Integer.valueOf(this.e));
    }

    public A(long j, String str, byte b, int i, byte[] bArr) {
        this.a = j;
        this.b = str;
        this.c = (byte) 16;
        this.d = b;
        this.f = i;
        bArr = bArr == null ? new byte[0] : bArr;
        this.g = bArr;
        this.e = bArr.length;
    }
}
