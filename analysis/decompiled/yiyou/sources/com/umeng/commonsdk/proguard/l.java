package com.umeng.commonsdk.proguard;

import java.io.ByteArrayOutputStream;

/* JADX INFO: compiled from: TByteArrayOutputStream.java */
/* JADX INFO: loaded from: classes.dex */
public class l extends ByteArrayOutputStream {
    public l(int i) {
        super(i);
    }

    public byte[] a() {
        return ((ByteArrayOutputStream) this).buf;
    }

    public int b() {
        return ((ByteArrayOutputStream) this).count;
    }

    public l() {
    }
}
