package com.android.umanalytics.http.bean;

/* JADX INFO: loaded from: classes.dex */
public class TaobaoLocation {
    private int code;
    private DataBean data;

    public static class DataBean {
        private String area;
        private String area_id;
        private String city;
        private String city_id;
        private String country;
        private String country_id;
        private String county;
        private String county_id;
        private String ip;
        private String isp;
        private String isp_id;
        private String region;
        private String region_id;

        public String getArea() {
            return this.area;
        }

        public String getArea_id() {
            return this.area_id;
        }

        public String getCity() {
            return this.city;
        }

        public String getCity_id() {
            return this.city_id;
        }

        public String getCountry() {
            return this.country;
        }

        public String getCountry_id() {
            return this.country_id;
        }

        public String getCounty() {
            return this.county;
        }

        public String getCounty_id() {
            return this.county_id;
        }

        public String getIp() {
            return this.ip;
        }

        public String getIsp() {
            return this.isp;
        }

        public String getIsp_id() {
            return this.isp_id;
        }

        public String getRegion() {
            return this.region;
        }

        public String getRegion_id() {
            return this.region_id;
        }

        public void setArea(String str) {
            this.area = str;
        }

        public void setArea_id(String str) {
            this.area_id = str;
        }

        public void setCity(String str) {
            this.city = str;
        }

        public void setCity_id(String str) {
            this.city_id = str;
        }

        public void setCountry(String str) {
            this.country = str;
        }

        public void setCountry_id(String str) {
            this.country_id = str;
        }

        public void setCounty(String str) {
            this.county = str;
        }

        public void setCounty_id(String str) {
            this.county_id = str;
        }

        public void setIp(String str) {
            this.ip = str;
        }

        public void setIsp(String str) {
            this.isp = str;
        }

        public void setIsp_id(String str) {
            this.isp_id = str;
        }

        public void setRegion(String str) {
            this.region = str;
        }

        public void setRegion_id(String str) {
            this.region_id = str;
        }

        public String toString() {
            return "DataBean{ip='" + this.ip + "', country='" + this.country + "', area='" + this.area + "', region='" + this.region + "', city='" + this.city + "', county='" + this.county + "', isp='" + this.isp + "', country_id='" + this.country_id + "', area_id='" + this.area_id + "', region_id='" + this.region_id + "', city_id='" + this.city_id + "', county_id='" + this.county_id + "', isp_id='" + this.isp_id + "'}";
        }
    }

    public int getCode() {
        return this.code;
    }

    public DataBean getData() {
        return this.data;
    }

    public void setCode(int i) {
        this.code = i;
    }

    public void setData(DataBean dataBean) {
        this.data = dataBean;
    }

    public String toString() {
        return "TaobaoLocation{code=" + this.code + ", data=" + this.data + '}';
    }
}
