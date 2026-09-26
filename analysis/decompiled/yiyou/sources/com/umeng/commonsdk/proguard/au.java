package com.umeng.commonsdk.proguard;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* JADX INFO: compiled from: TIOStreamTransport.java */
/* JADX INFO: loaded from: classes.dex */
public class au extends aw {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected InputStream f3937a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected OutputStream f3938b;

    protected au() {
        this.f3937a = null;
        this.f3938b = null;
    }

    @Override // com.umeng.commonsdk.proguard.aw
    public int a(byte[] bArr, int i, int i2) throws ax {
        InputStream inputStream = this.f3937a;
        if (inputStream == null) {
            throw new ax(1, "Cannot read from null inputStream");
        }
        try {
            int i3 = inputStream.read(bArr, i, i2);
            if (i3 >= 0) {
                return i3;
            }
            throw new ax(4);
        } catch (IOException e2) {
            throw new ax(0, e2);
        }
    }

    @Override // com.umeng.commonsdk.proguard.aw
    public boolean a() {
        return true;
    }

    @Override // com.umeng.commonsdk.proguard.aw
    public void b() {
    }

    @Override // com.umeng.commonsdk.proguard.aw
    public void b(byte[] bArr, int i, int i2) throws ax {
        OutputStream outputStream = this.f3938b;
        if (outputStream == null) {
            throw new ax(1, "Cannot write to null outputStream");
        }
        try {
            outputStream.write(bArr, i, i2);
        } catch (IOException e2) {
            throw new ax(0, e2);
        }
    }

    @Override // com.umeng.commonsdk.proguard.aw
    public void c() {
        InputStream inputStream = this.f3937a;
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
            this.f3937a = null;
        }
        OutputStream outputStream = this.f3938b;
        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (IOException e3) {
                e3.printStackTrace();
            }
            this.f3938b = null;
        }
    }

    @Override // com.umeng.commonsdk.proguard.aw
    public void d() throws ax {
        OutputStream outputStream = this.f3938b;
        if (outputStream == null) {
            throw new ax(1, "Cannot flush null outputStream");
        }
        try {
            outputStream.flush();
        } catch (IOException e2) {
            throw new ax(0, e2);
        }
    }

    public au(InputStream inputStream) {
        this.f3937a = null;
        this.f3938b = null;
        this.f3937a = inputStream;
    }

    public au(OutputStream outputStream) {
        this.f3937a = null;
        this.f3938b = null;
        this.f3938b = outputStream;
    }

    public au(InputStream inputStream, OutputStream outputStream) {
        this.f3937a = null;
        this.f3938b = null;
        this.f3937a = inputStream;
        this.f3938b = outputStream;
    }
}
