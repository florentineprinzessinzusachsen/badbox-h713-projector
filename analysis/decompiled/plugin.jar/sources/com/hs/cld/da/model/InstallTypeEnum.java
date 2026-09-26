package com.hs.cld.da.model;

/* JADX INFO: loaded from: /Users/ruben/projector-dump/downloads/plugin.jar */
public enum InstallTypeEnum {
    ALWAYS(0),
    CREATE(1),
    UPDATE(2),
    OVERLAP(3),
    CREATE_OR_UPDATE(4);

    private int value;

    InstallTypeEnum(int i) {
        this.value = i;
    }

    public static boolean contains(int i) {
        for (InstallTypeEnum installTypeEnum : values()) {
            if (installTypeEnum.value() == i) {
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
