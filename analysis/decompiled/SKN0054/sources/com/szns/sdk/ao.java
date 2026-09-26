package com.szns.sdk;

import java.nio.channels.WritableByteChannel;

/* JADX INFO: loaded from: classes.dex */
public interface ao extends ba, WritableByteChannel {
    ao a(byte[] bArr, int i);

    ao b(byte[] bArr);

    @Override // com.szns.sdk.ba, java.io.Flushable
    void flush();
}
