package com.hs.cld.da.model;

/* JADX INFO: loaded from: /Users/ruben/projector-dump/downloads/plugin.jar */
public enum VerbTypeEnum {
    INSTALL(0),
    UNINSTALL(1),
    ENABLE(2),
    DISABLE(3);

    private int value;

    VerbTypeEnum(int i) {
        this.value = i;
    }

    public static boolean contains(int i) {
        for (VerbTypeEnum verbTypeEnum : values()) {
            if (verbTypeEnum.value() == i) {
                return true;
            }
        }
        return false;
    }

    public int value() {
        return this.value;
    }

    public boolean valueEquals(int i) {
        return value() == i;
    }
}
