package com.android.umanalytics.http.bean;

/* JADX INFO: loaded from: classes.dex */
public class BaiduLocationBean {
    private String address;
    private ContentBean content;
    private int status;

    public static class ContentBean {
        private String address;
        private AddressDetailBean address_detail;
        private PointBean point;

        public static class AddressDetailBean {
            private String city;
            private int city_code;
            private String district;
            private String province;
            private String street;
            private String street_number;

            public String getCity() {
                return this.city;
            }

            public int getCity_code() {
                return this.city_code;
            }

            public String getDistrict() {
                return this.district;
            }

            public String getProvince() {
                return this.province;
            }

            public String getStreet() {
                return this.street;
            }

            public String getStreet_number() {
                return this.street_number;
            }

            public void setCity(String str) {
                this.city = str;
            }

            public void setCity_code(int i) {
                this.city_code = i;
            }

            public void setDistrict(String str) {
                this.district = str;
            }

            public void setProvince(String str) {
                this.province = str;
            }

            public void setStreet(String str) {
                this.street = str;
            }

            public void setStreet_number(String str) {
                this.street_number = str;
            }
        }

        public static class PointBean {
            private String x;
            private String y;

            public String getX() {
                return this.x;
            }

            public String getY() {
                return this.y;
            }

            public void setX(String str) {
                this.x = str;
            }

            public void setY(String str) {
                this.y = str;
            }
        }

        public String getAddress() {
            return this.address;
        }

        public AddressDetailBean getAddress_detail() {
            return this.address_detail;
        }

        public PointBean getPoint() {
            return this.point;
        }

        public void setAddress(String str) {
            this.address = str;
        }

        public void setAddress_detail(AddressDetailBean addressDetailBean) {
            this.address_detail = addressDetailBean;
        }

        public void setPoint(PointBean pointBean) {
            this.point = pointBean;
        }
    }

    public String getAddress() {
        return this.address;
    }

    public ContentBean getContent() {
        return this.content;
    }

    public int getStatus() {
        return this.status;
    }

    public void setAddress(String str) {
        this.address = str;
    }

    public void setContent(ContentBean contentBean) {
        this.content = contentBean;
    }

    public void setStatus(int i) {
        this.status = i;
    }
}
