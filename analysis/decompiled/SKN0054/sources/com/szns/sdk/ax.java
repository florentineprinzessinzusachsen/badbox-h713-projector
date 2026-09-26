package com.szns.sdk;

import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes.dex */
final class ax {
    final byte[] a;
    int b;
    int c;
    boolean d;
    boolean e;
    ax f;
    ax g;

    ax() {
        this.a = new byte[8192];
        this.e = true;
        this.d = false;
    }

    private ax(byte[] bArr, int i, int i2) {
        this.a = bArr;
        this.b = i;
        this.c = i2;
        this.d = true;
        this.e = false;
    }

    final ax a() {
        this.d = true;
        return new ax(this.a, this.b, this.c);
    }

    public final ax a(ax axVar) {
        axVar.g = this;
        axVar.f = this.f;
        this.f.g = axVar;
        this.f = axVar;
        return axVar;
    }

    public final void a(ax axVar, int i) {
        if (!axVar.e) {
            throw new IllegalArgumentException();
        }
        int i2 = axVar.c;
        if (i2 + i > 8192) {
            if (axVar.d) {
                throw new IllegalArgumentException();
            }
            int i3 = axVar.b;
            if ((i2 + i) - i3 > 8192) {
                throw new IllegalArgumentException();
            }
            byte[] bArr = axVar.a;
            System.arraycopy(bArr, i3, bArr, 0, i2 - i3);
            axVar.c -= axVar.b;
            axVar.b = 0;
        }
        System.arraycopy(this.a, this.b, axVar.a, axVar.c, i);
        axVar.c += i;
        this.b += i;
    }

    @Nullable
    public final ax b() {
        ax axVar = this.f;
        ax axVar2 = axVar != this ? axVar : null;
        ax axVar3 = this.g;
        axVar3.f = axVar;
        this.f.g = axVar3;
        this.f = null;
        this.g = null;
        return axVar2;
    }
}
