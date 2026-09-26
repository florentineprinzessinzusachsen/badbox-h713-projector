package com.android.umanalytics.http.bean;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class MGRecomBean {
    private ErrorBean error;
    private String src_identification;
    private String update_time;
    private int version_time;
    private int video_count;
    private List<VideoInfosBean> video_infos;
    private int video_total_count;

    public static class ErrorBean {
        private String code;
        private String info;

        public String getCode() {
            return this.code;
        }

        public String getInfo() {
            return this.info;
        }

        public void setCode(String str) {
            this.code = str;
        }

        public void setInfo(String str) {
            this.info = str;
        }
    }

    public static class VideoInfosBean {
        private List<String> video_actors;
        private String video_category_id;
        private String video_category_name;
        private String video_desc;
        private String video_description;
        private List<String> video_director;
        private String video_duration;
        private String video_id;
        private VideoImgListBean video_img_list;
        private List<String> video_kind;
        private String video_name;
        private String video_ui_style;

        public static class VideoImgListBean {
            private String video_img_url_1;
            private String video_img_url_2;
            private String video_img_url_3;
            private String video_img_url_4;
            private String video_img_url_5;
            private String video_img_url_6;

            public String getVideo_img_url_1() {
                return this.video_img_url_1;
            }

            public String getVideo_img_url_2() {
                return this.video_img_url_2;
            }

            public String getVideo_img_url_3() {
                return this.video_img_url_3;
            }

            public String getVideo_img_url_4() {
                return this.video_img_url_4;
            }

            public String getVideo_img_url_5() {
                return this.video_img_url_5;
            }

            public String getVideo_img_url_6() {
                return this.video_img_url_6;
            }

            public void setVideo_img_url_1(String str) {
                this.video_img_url_1 = str;
            }

            public void setVideo_img_url_2(String str) {
                this.video_img_url_2 = str;
            }

            public void setVideo_img_url_3(String str) {
                this.video_img_url_3 = str;
            }

            public void setVideo_img_url_4(String str) {
                this.video_img_url_4 = str;
            }

            public void setVideo_img_url_5(String str) {
                this.video_img_url_5 = str;
            }

            public void setVideo_img_url_6(String str) {
                this.video_img_url_6 = str;
            }
        }

        public List<String> getVideo_actors() {
            return this.video_actors;
        }

        public String getVideo_category_id() {
            return this.video_category_id;
        }

        public String getVideo_category_name() {
            return this.video_category_name;
        }

        public String getVideo_desc() {
            return this.video_desc;
        }

        public String getVideo_description() {
            return this.video_description;
        }

        public List<String> getVideo_director() {
            return this.video_director;
        }

        public String getVideo_duration() {
            return this.video_duration;
        }

        public String getVideo_id() {
            return this.video_id;
        }

        public VideoImgListBean getVideo_img_list() {
            return this.video_img_list;
        }

        public List<String> getVideo_kind() {
            return this.video_kind;
        }

        public String getVideo_name() {
            return this.video_name;
        }

        public String getVideo_ui_style() {
            return this.video_ui_style;
        }

        public void setVideo_actors(List<String> list) {
            this.video_actors = list;
        }

        public void setVideo_category_id(String str) {
            this.video_category_id = str;
        }

        public void setVideo_category_name(String str) {
            this.video_category_name = str;
        }

        public void setVideo_desc(String str) {
            this.video_desc = str;
        }

        public void setVideo_description(String str) {
            this.video_description = str;
        }

        public void setVideo_director(List<String> list) {
            this.video_director = list;
        }

        public void setVideo_duration(String str) {
            this.video_duration = str;
        }

        public void setVideo_id(String str) {
            this.video_id = str;
        }

        public void setVideo_img_list(VideoImgListBean videoImgListBean) {
            this.video_img_list = videoImgListBean;
        }

        public void setVideo_kind(List<String> list) {
            this.video_kind = list;
        }

        public void setVideo_name(String str) {
            this.video_name = str;
        }

        public void setVideo_ui_style(String str) {
            this.video_ui_style = str;
        }
    }

    public ErrorBean getError() {
        return this.error;
    }

    public String getSrc_identification() {
        return this.src_identification;
    }

    public String getUpdate_time() {
        return this.update_time;
    }

    public int getVersion_time() {
        return this.version_time;
    }

    public int getVideo_count() {
        return this.video_count;
    }

    public List<VideoInfosBean> getVideo_infos() {
        return this.video_infos;
    }

    public int getVideo_total_count() {
        return this.video_total_count;
    }

    public void setError(ErrorBean errorBean) {
        this.error = errorBean;
    }

    public void setSrc_identification(String str) {
        this.src_identification = str;
    }

    public void setUpdate_time(String str) {
        this.update_time = str;
    }

    public void setVersion_time(int i) {
        this.version_time = i;
    }

    public void setVideo_count(int i) {
        this.video_count = i;
    }

    public void setVideo_infos(List<VideoInfosBean> list) {
        this.video_infos = list;
    }

    public void setVideo_total_count(int i) {
        this.video_total_count = i;
    }
}
