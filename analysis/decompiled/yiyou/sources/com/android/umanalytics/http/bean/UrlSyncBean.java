package com.android.umanalytics.http.bean;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class UrlSyncBean {
    private int code;
    private DataBean data;
    private String msg;

    public static class DataBean {
        private List<ListBean> list;
        private String source;
        private String ver;

        public static class ListBean {
            private String desc;
            private String quality;
            private String type;
            private String url;

            public String getDesc() {
                return this.desc;
            }

            public String getQuality() {
                return this.quality;
            }

            public String getType() {
                return this.type;
            }

            public String getUrl() {
                return this.url;
            }

            public void setDesc(String str) {
                this.desc = str;
            }

            public void setQuality(String str) {
                this.quality = str;
            }

            public void setType(String str) {
                this.type = str;
            }

            public void setUrl(String str) {
                this.url = str;
            }
        }

        public List<ListBean> getList() {
            return this.list;
        }

        public String getSource() {
            return this.source;
        }

        public String getVer() {
            return this.ver;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public void setSource(String str) {
            this.source = str;
        }

        public void setVer(String str) {
            this.ver = str;
        }
    }

    public int getCode() {
        return this.code;
    }

    public DataBean getData() {
        return this.data;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setCode(int i) {
        this.code = i;
    }

    public void setData(DataBean dataBean) {
        this.data = dataBean;
    }

    public void setMsg(String str) {
        this.msg = str;
    }
}
