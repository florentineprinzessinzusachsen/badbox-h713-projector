package com.softwinner.tv.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/* JADX INFO: loaded from: classes.dex */
public class AwTvChannelJsonBean {
    private List<ATVChTableBean> ATV_Ch_Table;
    private List<DTMBChTableBean> DTMB_Ch_Table;

    public List<ATVChTableBean> getATV_Ch_Table() {
        return this.ATV_Ch_Table;
    }

    public void setATV_Ch_Table(List<ATVChTableBean> list) {
        this.ATV_Ch_Table = list;
    }

    public List<DTMBChTableBean> getDTMB_Ch_Table() {
        return this.DTMB_Ch_Table;
    }

    public void setDTMB_Ch_Table(List<DTMBChTableBean> list) {
        this.DTMB_Ch_Table = list;
    }

    private static ATVChTableBean getATVChTableBean(AwTvChannelInfo awTvChannelInfo) {
        return new ATVChTableBean.Builder().setDisplayNumber(awTvChannelInfo.getDisplayNumber()).setDisplayName(awTvChannelInfo.getDisplayName()).setVideoStd(awTvChannelInfo.getVideoStd()).setAudioStd(awTvChannelInfo.getAudioStd()).setFrequency(awTvChannelInfo.getFrequency()).setVfmt(awTvChannelInfo.getVfmt()).setFineTune(awTvChannelInfo.getFineTune()).setIsAutoStd(awTvChannelInfo.getIsAutoStd()).setAudioCompensation(awTvChannelInfo.getAudioCompensation()).setChannelNo(awTvChannelInfo.getChannelNo()).setCountry(awTvChannelInfo.getCountry()).setAudioSys(awTvChannelInfo.getAudioSys()).build();
    }

    private static DTMBChTableBean getDTMBChTableBean(AwTvChannelInfo awTvChannelInfo) {
        return new DTMBChTableBean.Builder().setFrequency(awTvChannelInfo.getFrequency()).setModulation(awTvChannelInfo.getModulation()).setSymbolRate(awTvChannelInfo.getSymbolRate()).setBandWidth(awTvChannelInfo.getBandwidth()).setDisplayNumber(awTvChannelInfo.getDisplayNumber()).setDisplayName(awTvChannelInfo.getDisplayName()).setServiceId(awTvChannelInfo.getServiceId()).setVideoPid(awTvChannelInfo.getVideoPid()).setDisplayNameMulti(awTvChannelInfo.getDisplayNameMulti()).setFreeCa(awTvChannelInfo.getFreeCa()).setScrambled(awTvChannelInfo.getScrambled()).setAudioPids(awTvChannelInfo.getAudioPids() != null ? (List) Arrays.stream(awTvChannelInfo.getAudioPids()).boxed().collect(Collectors.toList()) : null).build();
    }

    public static AwTvChannelJsonBean getJsonBean(List<AwTvChannelInfo> list) {
        if (list == null || list.size() == 0) {
            return null;
        }
        AwTvChannelJsonBean awTvChannelJsonBean = new AwTvChannelJsonBean();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (AwTvChannelInfo awTvChannelInfo : list) {
            if (awTvChannelInfo.isAnalogChannel()) {
                arrayList.add(getATVChTableBean(awTvChannelInfo));
            } else {
                arrayList2.add(getDTMBChTableBean(awTvChannelInfo));
            }
        }
        awTvChannelJsonBean.setATV_Ch_Table(arrayList);
        awTvChannelJsonBean.setDTMB_Ch_Table(arrayList2);
        return awTvChannelJsonBean;
    }

    public static List<AwTvChannelInfo> getAwTvChannelInfoList(AwTvChannelJsonBean awTvChannelJsonBean, String str, boolean z) {
        if (awTvChannelJsonBean == null) {
            return null;
        }
        List<ATVChTableBean> aTV_Ch_Table = awTvChannelJsonBean.getATV_Ch_Table();
        List<DTMBChTableBean> dTMB_Ch_Table = awTvChannelJsonBean.getDTMB_Ch_Table();
        ArrayList arrayList = new ArrayList();
        if (z) {
            for (ATVChTableBean aTVChTableBean : aTV_Ch_Table) {
                arrayList.add(new AwTvChannelInfo.Builder().setInputId(str).setServiceType("SERVICE_TYPE_AUDIO_VIDEO").setType("AnalogTv").setDisplayNumber(aTVChTableBean.getDisplayNumber()).setDisplayName(aTVChTableBean.getDisplayName()).setVideoStd(aTVChTableBean.getVideoStd()).setAudioStd(aTVChTableBean.getAudioStd()).setFrequency(aTVChTableBean.getFrequency()).setVfmt(aTVChTableBean.getVfmt()).setFineTune(aTVChTableBean.getFineTune()).setIsAutoStd(aTVChTableBean.getIsAutoStd()).setAudioCompensation(aTVChTableBean.getAudioCompensation()).setChannelNo(aTVChTableBean.getChannelNo()).setCountry(aTVChTableBean.getCountry()).setAudioSys(aTVChTableBean.getAudioSys()).build());
            }
        } else {
            for (DTMBChTableBean dTMBChTableBean : dTMB_Ch_Table) {
                arrayList.add(new AwTvChannelInfo.Builder().setInputId(str).setServiceType("SERVICE_TYPE_AUDIO_VIDEO").setType("TYPE_DTMB").setFrequency(dTMBChTableBean.getFrequency()).setModulation(dTMBChTableBean.getModulation()).setSymbolRate(dTMBChTableBean.getSymbolRate()).setBandwidth(dTMBChTableBean.getBandWidth()).setDisplayNumber(dTMBChTableBean.getDisplayNumber()).setDisplayName(dTMBChTableBean.getDisplayName()).setServiceId(dTMBChTableBean.getServiceId()).setVideoPid(dTMBChTableBean.getVideoPid()).setDisplayNameMulti(dTMBChTableBean.getDisplayNameMulti()).setFreeCa(dTMBChTableBean.getFreeCa()).setScrambled(dTMBChTableBean.getScrambled()).setAudioPids(dTMBChTableBean.getAudioPids() != null ? dTMBChTableBean.getAudioPids().stream().mapToInt((v0) -> {
                    return v0.intValue();
                }).toArray() : null).build());
            }
        }
        return arrayList;
    }

    public String toString() {
        return "AwTvChannelJsonBean{ATV_Ch_Table=" + Arrays.toString(this.ATV_Ch_Table.toArray()) + ", DTMB_Ch_Table=" + Arrays.toString(this.DTMB_Ch_Table.toArray()) + '}';
    }

    public static class ATVChTableBean {
        private int audioCompensation;
        private int audioStd;
        private int audioSys;
        private int channelNo;
        private String country;
        private String displayName;
        private String displayNumber;
        private int fineTune;
        private int frequency;
        private int isAutoStd;
        private int vfmt;
        private int videoStd;

        public String getDisplayNumber() {
            return this.displayNumber;
        }

        public void setDisplayNumber(String str) {
            this.displayNumber = str;
        }

        public String getDisplayName() {
            return this.displayName;
        }

        public void setDisplayName(String str) {
            this.displayName = str;
        }

        public int getVideoStd() {
            return this.videoStd;
        }

        public void setVideoStd(int i) {
            this.videoStd = i;
        }

        public int getAudioStd() {
            return this.audioStd;
        }

        public void setAudioStd(int i) {
            this.audioStd = i;
        }

        public int getFrequency() {
            return this.frequency;
        }

        public void setFrequency(int i) {
            this.frequency = i;
        }

        public int getVfmt() {
            return this.vfmt;
        }

        public void setVfmt(int i) {
            this.vfmt = i;
        }

        public int getFineTune() {
            return this.fineTune;
        }

        public void setFineTune(int i) {
            this.fineTune = i;
        }

        public int getIsAutoStd() {
            return this.isAutoStd;
        }

        public void setIsAutoStd(int i) {
            this.isAutoStd = i;
        }

        public int getAudioCompensation() {
            return this.audioCompensation;
        }

        public void setAudioCompensation(int i) {
            this.audioCompensation = i;
        }

        public int getChannelNo() {
            return this.channelNo;
        }

        public void setChannelNo(int i) {
            this.channelNo = i;
        }

        public String getCountry() {
            return this.country;
        }

        public void setCountry(String str) {
            this.country = str;
        }

        public int getAudioSys() {
            return this.audioSys;
        }

        public void setAudioSys(int i) {
            this.audioSys = i;
        }

        public String toString() {
            return "ATVChTableBean{displayNumber='" + this.displayNumber + "', displayName='" + this.displayName + "', videoStd=" + this.videoStd + ", audioStd=" + this.audioStd + ", frequency=" + this.frequency + ", vfmt=" + this.vfmt + ", fineTune=" + this.fineTune + ", isAutoStd=" + this.isAutoStd + ", audioCompensation=" + this.audioCompensation + ", channelNo=" + this.channelNo + '}';
        }

        public static final class Builder {
            private final ATVChTableBean itemSelf = new ATVChTableBean();

            public Builder() {
                this.itemSelf.displayNumber = "";
                this.itemSelf.displayName = "";
                this.itemSelf.videoStd = -1;
                this.itemSelf.audioStd = -1;
                this.itemSelf.frequency = -1;
                this.itemSelf.vfmt = -1;
                this.itemSelf.fineTune = -1;
                this.itemSelf.isAutoStd = -1;
                this.itemSelf.audioCompensation = -1;
                this.itemSelf.channelNo = -1;
                this.itemSelf.country = "";
                this.itemSelf.audioSys = 0;
            }

            public Builder setDisplayNumber(String str) {
                this.itemSelf.displayNumber = str;
                return this;
            }

            public Builder setDisplayName(String str) {
                this.itemSelf.displayName = str;
                return this;
            }

            public Builder setVideoStd(int i) {
                this.itemSelf.videoStd = i;
                return this;
            }

            public Builder setAudioStd(int i) {
                this.itemSelf.audioStd = i;
                return this;
            }

            public Builder setFrequency(int i) {
                this.itemSelf.frequency = i;
                return this;
            }

            public Builder setVfmt(int i) {
                this.itemSelf.vfmt = i;
                return this;
            }

            public Builder setFineTune(int i) {
                this.itemSelf.fineTune = i;
                return this;
            }

            public Builder setIsAutoStd(int i) {
                this.itemSelf.isAutoStd = i;
                return this;
            }

            public Builder setAudioCompensation(int i) {
                this.itemSelf.audioCompensation = i;
                return this;
            }

            public Builder setChannelNo(int i) {
                this.itemSelf.channelNo = i;
                return this;
            }

            public Builder setCountry(String str) {
                this.itemSelf.country = str;
                return this;
            }

            public Builder setAudioSys(int i) {
                this.itemSelf.audioSys = i;
                return this;
            }

            public ATVChTableBean build() {
                return this.itemSelf;
            }
        }
    }

    public static class DTMBChTableBean {
        private List<Integer> audioPids;
        private int bandWidth;
        private String displayName;
        private String displayNameMulti;
        private String displayNumber;
        private int freeCa;
        private int frequency;
        private int modulation;
        private int scrambled;
        private int serviceId;
        private int symbolRate;
        private int videoPid;

        public int getFrequency() {
            return this.frequency;
        }

        public void setFrequency(int i) {
            this.frequency = i;
        }

        public int getModulation() {
            return this.modulation;
        }

        public void setModulation(int i) {
            this.modulation = i;
        }

        public int getSymbolRate() {
            return this.symbolRate;
        }

        public void setSymbolRate(int i) {
            this.symbolRate = i;
        }

        public int getBandWidth() {
            return this.bandWidth;
        }

        public void setBandWidth(int i) {
            this.bandWidth = i;
        }

        public String getDisplayNumber() {
            return this.displayNumber;
        }

        public void setDisplayNumber(String str) {
            this.displayNumber = str;
        }

        public String getDisplayName() {
            return this.displayName;
        }

        public void setDisplayName(String str) {
            this.displayName = str;
        }

        public int getServiceId() {
            return this.serviceId;
        }

        public void setServiceId(int i) {
            this.serviceId = i;
        }

        public int getVideoPid() {
            return this.videoPid;
        }

        public void setVideoPid(int i) {
            this.videoPid = i;
        }

        public String getDisplayNameMulti() {
            return this.displayNameMulti;
        }

        public void setDisplayNameMulti(String str) {
            this.displayNameMulti = str;
        }

        public int getFreeCa() {
            return this.freeCa;
        }

        public void setFreeCa(int i) {
            this.freeCa = i;
        }

        public int getScrambled() {
            return this.scrambled;
        }

        public void setScrambled(int i) {
            this.scrambled = i;
        }

        public List<Integer> getAudioPids() {
            return this.audioPids;
        }

        public void setAudioPids(List<Integer> list) {
            this.audioPids = list;
        }

        public String toString() {
            return "DTMBChTableBean{frequency=" + this.frequency + ", modulation=" + this.modulation + ", symbolRate=" + this.symbolRate + ", bandWidth=" + this.bandWidth + ", displayNumber='" + this.displayNumber + "', displayName='" + this.displayName + "', serviceId=" + this.serviceId + ", videoPid=" + this.videoPid + ", displayNameMulti='" + this.displayNameMulti + "', freeCa=" + this.freeCa + ", scrambled=" + this.scrambled + ", audioPids=" + this.audioPids + '}';
        }

        public static final class Builder {
            private final DTMBChTableBean itemSelf = new DTMBChTableBean();

            public Builder() {
                this.itemSelf.frequency = -1;
                this.itemSelf.modulation = -1;
                this.itemSelf.symbolRate = -1;
                this.itemSelf.bandWidth = -1;
                this.itemSelf.frequency = -1;
                this.itemSelf.displayNumber = "";
                this.itemSelf.displayName = "";
                this.itemSelf.serviceId = -1;
                this.itemSelf.videoPid = -1;
                this.itemSelf.displayNameMulti = "";
                this.itemSelf.freeCa = -1;
                this.itemSelf.scrambled = -1;
                this.itemSelf.audioPids = null;
            }

            public Builder setFrequency(int i) {
                this.itemSelf.frequency = i;
                return this;
            }

            public Builder setModulation(int i) {
                this.itemSelf.modulation = i;
                return this;
            }

            public Builder setSymbolRate(int i) {
                this.itemSelf.symbolRate = i;
                return this;
            }

            public Builder setBandWidth(int i) {
                this.itemSelf.bandWidth = i;
                return this;
            }

            public Builder setDisplayNumber(String str) {
                this.itemSelf.displayNumber = str;
                return this;
            }

            public Builder setDisplayName(String str) {
                this.itemSelf.displayName = str;
                return this;
            }

            public Builder setServiceId(int i) {
                this.itemSelf.serviceId = i;
                return this;
            }

            public Builder setVideoPid(int i) {
                this.itemSelf.videoPid = i;
                return this;
            }

            public Builder setDisplayNameMulti(String str) {
                this.itemSelf.displayNameMulti = str;
                return this;
            }

            public Builder setFreeCa(int i) {
                this.itemSelf.freeCa = i;
                return this;
            }

            public Builder setScrambled(int i) {
                this.itemSelf.scrambled = i;
                return this;
            }

            public Builder setAudioPids(List<Integer> list) {
                this.itemSelf.audioPids = list;
                return this;
            }

            public DTMBChTableBean build() {
                return this.itemSelf;
            }
        }
    }
}
