package com.android.umanalytics.http.bean;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class LetvLocation {
    private String buss;
    private int curtime;
    private String desc;
    private String fixlist;
    private String geo;
    private String host;
    private String identify;
    private String ipend;
    private String ipint;
    private String ipstart;
    private String manual;
    private List<NodelistBean> nodelist;
    private String perfect;
    private String prior;
    private String qosfix;
    private String recommend;
    private String remote;

    public static class NodelistBean {
        private int aid;
        private String detail;
        private int gone;
        private int isp;
        private String location;
        private String name;
        private int pid;

        public int getAid() {
            return this.aid;
        }

        public String getDetail() {
            return this.detail;
        }

        public int getGone() {
            return this.gone;
        }

        public int getIsp() {
            return this.isp;
        }

        public String getLocation() {
            return this.location;
        }

        public String getName() {
            return this.name;
        }

        public int getPid() {
            return this.pid;
        }

        public void setAid(int i) {
            this.aid = i;
        }

        public void setDetail(String str) {
            this.detail = str;
        }

        public void setGone(int i) {
            this.gone = i;
        }

        public void setIsp(int i) {
            this.isp = i;
        }

        public void setLocation(String str) {
            this.location = str;
        }

        public void setName(String str) {
            this.name = str;
        }

        public void setPid(int i) {
            this.pid = i;
        }
    }

    public String getBuss() {
        return this.buss;
    }

    public int getCurtime() {
        return this.curtime;
    }

    public String getDesc() {
        return this.desc;
    }

    public String getFixlist() {
        return this.fixlist;
    }

    public String getGeo() {
        return this.geo;
    }

    public String getHost() {
        return this.host;
    }

    public String getIdentify() {
        return this.identify;
    }

    public String getIpend() {
        return this.ipend;
    }

    public String getIpint() {
        return this.ipint;
    }

    public String getIpstart() {
        return this.ipstart;
    }

    public String getManual() {
        return this.manual;
    }

    public List<NodelistBean> getNodelist() {
        return this.nodelist;
    }

    public String getPerfect() {
        return this.perfect;
    }

    public String getPrior() {
        return this.prior;
    }

    public String getQosfix() {
        return this.qosfix;
    }

    public String getRecommend() {
        return this.recommend;
    }

    public String getRemote() {
        return this.remote;
    }

    public void setBuss(String str) {
        this.buss = str;
    }

    public void setCurtime(int i) {
        this.curtime = i;
    }

    public void setDesc(String str) {
        this.desc = str;
    }

    public void setFixlist(String str) {
        this.fixlist = str;
    }

    public void setGeo(String str) {
        this.geo = str;
    }

    public void setHost(String str) {
        this.host = str;
    }

    public void setIdentify(String str) {
        this.identify = str;
    }

    public void setIpend(String str) {
        this.ipend = str;
    }

    public void setIpint(String str) {
        this.ipint = str;
    }

    public void setIpstart(String str) {
        this.ipstart = str;
    }

    public void setManual(String str) {
        this.manual = str;
    }

    public void setNodelist(List<NodelistBean> list) {
        this.nodelist = list;
    }

    public void setPerfect(String str) {
        this.perfect = str;
    }

    public void setPrior(String str) {
        this.prior = str;
    }

    public void setQosfix(String str) {
        this.qosfix = str;
    }

    public void setRecommend(String str) {
        this.recommend = str;
    }

    public void setRemote(String str) {
        this.remote = str;
    }
}
