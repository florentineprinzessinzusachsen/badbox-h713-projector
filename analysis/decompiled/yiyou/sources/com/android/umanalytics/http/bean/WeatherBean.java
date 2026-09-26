package com.android.umanalytics.http.bean;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class WeatherBean {
    private String date;
    private int error;
    private List<ResultsBean> results;
    private String status;

    public static class ResultsBean {
        private String currentCity;
        private List<IndexBean> index;
        private String pm25;
        private List<WeatherDataBean> weather_data;

        public static class IndexBean {
            private String des;
            private String tipt;
            private String title;
            private String zs;

            public String getDes() {
                return this.des;
            }

            public String getTipt() {
                return this.tipt;
            }

            public String getTitle() {
                return this.title;
            }

            public String getZs() {
                return this.zs;
            }

            public void setDes(String str) {
                this.des = str;
            }

            public void setTipt(String str) {
                this.tipt = str;
            }

            public void setTitle(String str) {
                this.title = str;
            }

            public void setZs(String str) {
                this.zs = str;
            }
        }

        public static class WeatherDataBean {
            private String date;
            private String dayPictureUrl;
            private String nightPictureUrl;
            private String temperature;
            private String weather;
            private String wind;

            public String getDate() {
                return this.date;
            }

            public String getDayPictureUrl() {
                return this.dayPictureUrl;
            }

            public String getNightPictureUrl() {
                return this.nightPictureUrl;
            }

            public String getTemperature() {
                return this.temperature;
            }

            public String getWeather() {
                return this.weather;
            }

            public String getWind() {
                return this.wind;
            }

            public void setDate(String str) {
                this.date = str;
            }

            public void setDayPictureUrl(String str) {
                this.dayPictureUrl = str;
            }

            public void setNightPictureUrl(String str) {
                this.nightPictureUrl = str;
            }

            public void setTemperature(String str) {
                this.temperature = str;
            }

            public void setWeather(String str) {
                this.weather = str;
            }

            public void setWind(String str) {
                this.wind = str;
            }
        }

        public String getCurrentCity() {
            return this.currentCity;
        }

        public List<IndexBean> getIndex() {
            return this.index;
        }

        public String getPm25() {
            return this.pm25;
        }

        public List<WeatherDataBean> getWeather_data() {
            return this.weather_data;
        }

        public void setCurrentCity(String str) {
            this.currentCity = str;
        }

        public void setIndex(List<IndexBean> list) {
            this.index = list;
        }

        public void setPm25(String str) {
            this.pm25 = str;
        }

        public void setWeather_data(List<WeatherDataBean> list) {
            this.weather_data = list;
        }
    }

    public String getDate() {
        return this.date;
    }

    public int getError() {
        return this.error;
    }

    public List<ResultsBean> getResults() {
        return this.results;
    }

    public String getStatus() {
        return this.status;
    }

    public void setDate(String str) {
        this.date = str;
    }

    public void setError(int i) {
        this.error = i;
    }

    public void setResults(List<ResultsBean> list) {
        this.results = list;
    }

    public void setStatus(String str) {
        this.status = str;
    }
}
