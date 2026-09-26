package com.android.umanalytics.http;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class HttpResult<T> {
    private int code;
    private int count;
    private T data;
    private DebugBean debug;
    private String msg;

    public static class DebugBean {
        private List<?> sqls;
        private List<?> stack;
        private String version;

        public List<?> getSqls() {
            return this.sqls;
        }

        public List<?> getStack() {
            return this.stack;
        }

        public String getVersion() {
            return this.version;
        }

        public void setSqls(List<?> list) {
            this.sqls = list;
        }

        public void setStack(List<?> list) {
            this.stack = list;
        }

        public void setVersion(String str) {
            this.version = str;
        }
    }

    public int getCode() {
        return this.code;
    }

    public int getCount() {
        return this.count;
    }

    public T getData() {
        return this.data;
    }

    public DebugBean getDebug() {
        return this.debug;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setCode(int i) {
        this.code = i;
    }

    public void setCount(int i) {
        this.count = i;
    }

    public void setData(T t) {
        this.data = t;
    }

    public void setDebug(DebugBean debugBean) {
        this.debug = debugBean;
    }

    public void setMsg(String str) {
        this.msg = str;
    }
}
