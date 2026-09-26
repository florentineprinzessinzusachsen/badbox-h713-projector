package com.hs.p.dx;

import dalvik.system.DexClassLoader;
import java.io.File;

/* JADX INFO: loaded from: /Users/ruben/projector-dump/downloads/plugin.jar */
public class Invocation {
    public DexClassLoader mClassLoader;
    public String mClassName;
    public byte[] mDexData;
    public String mInitMethod;
    public File mLocalDexFile;
    public String mUninitMethod;

    public DexClassLoader getClassLoader() {
        return this.mClassLoader;
    }

    public byte[] getDexData() {
        return this.mDexData;
    }

    public void setClassLoader(DexClassLoader dexClassLoader) {
        this.mClassLoader = dexClassLoader;
    }

    public void setDexData(byte[] bArr) {
        this.mDexData = bArr;
    }

    public String toString() {
        File file = this.mLocalDexFile;
        return (file != null ? file.toString() : "[in-memory]") + " " + this.mClassName + " " + this.mInitMethod + " " + this.mUninitMethod;
    }
}
