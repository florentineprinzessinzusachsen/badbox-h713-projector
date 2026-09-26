package com.alibaba.fastjson.asm;

/* JADX INFO: loaded from: classes.dex */
public class MethodCollector {
    protected boolean debugInfoPresent;
    private final int ignoreCount;
    private final int paramCount;
    private final StringBuilder result = new StringBuilder();
    private int currentParameter = 0;

    protected MethodCollector(int i, int i2) {
        this.ignoreCount = i;
        this.paramCount = i2;
        this.debugInfoPresent = i2 == 0;
    }

    protected void visitLocalVariable(String str, int i) {
        if (i < this.ignoreCount || i >= this.ignoreCount + this.paramCount) {
            return;
        }
        if (!str.equals("arg" + this.currentParameter)) {
            this.debugInfoPresent = true;
        }
        this.result.append(',');
        this.result.append(str);
        this.currentParameter++;
    }

    protected String getResult() {
        return this.result.length() != 0 ? this.result.substring(1) : "";
    }
}
