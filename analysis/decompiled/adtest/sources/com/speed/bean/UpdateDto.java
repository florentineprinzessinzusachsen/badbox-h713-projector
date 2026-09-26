package com.speed.bean;

import j2.i;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class UpdateDto {
    private final String appId;
    private final String channel;
    private final String cype;
    private String downLink;
    private final String model;
    private final Integer tip;
    private final String update_content;
    private final Integer version;

    public UpdateDto(String str, String str2, String str3, String str4, String str5, Integer num, Integer num2, String str6) {
        this.appId = str;
        this.channel = str2;
        this.cype = str3;
        this.downLink = str4;
        this.model = str5;
        this.tip = num;
        this.version = num2;
        this.update_content = str6;
    }

    public static /* synthetic */ UpdateDto copy$default(UpdateDto updateDto, String str, String str2, String str3, String str4, String str5, Integer num, Integer num2, String str6, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            str = updateDto.appId;
        }
        if ((i4 & 2) != 0) {
            str2 = updateDto.channel;
        }
        if ((i4 & 4) != 0) {
            str3 = updateDto.cype;
        }
        if ((i4 & 8) != 0) {
            str4 = updateDto.downLink;
        }
        if ((i4 & 16) != 0) {
            str5 = updateDto.model;
        }
        if ((i4 & 32) != 0) {
            num = updateDto.tip;
        }
        if ((i4 & 64) != 0) {
            num2 = updateDto.version;
        }
        if ((i4 & 128) != 0) {
            str6 = updateDto.update_content;
        }
        Integer num3 = num2;
        String str7 = str6;
        String str8 = str5;
        Integer num4 = num;
        return updateDto.copy(str, str2, str3, str4, str8, num4, num3, str7);
    }

    public final String component1() {
        return this.appId;
    }

    public final String component2() {
        return this.channel;
    }

    public final String component3() {
        return this.cype;
    }

    public final String component4() {
        return this.downLink;
    }

    public final String component5() {
        return this.model;
    }

    public final Integer component6() {
        return this.tip;
    }

    public final Integer component7() {
        return this.version;
    }

    public final String component8() {
        return this.update_content;
    }

    public final UpdateDto copy(String str, String str2, String str3, String str4, String str5, Integer num, Integer num2, String str6) {
        return new UpdateDto(str, str2, str3, str4, str5, num, num2, str6);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof UpdateDto)) {
            return false;
        }
        UpdateDto updateDto = (UpdateDto) obj;
        return i.a(this.appId, updateDto.appId) && i.a(this.channel, updateDto.channel) && i.a(this.cype, updateDto.cype) && i.a(this.downLink, updateDto.downLink) && i.a(this.model, updateDto.model) && i.a(this.tip, updateDto.tip) && i.a(this.version, updateDto.version) && i.a(this.update_content, updateDto.update_content);
    }

    public final String getAppId() {
        return this.appId;
    }

    public final String getChannel() {
        return this.channel;
    }

    public final String getCype() {
        return this.cype;
    }

    public final String getDownLink() {
        return this.downLink;
    }

    public final String getModel() {
        return this.model;
    }

    public final Integer getTip() {
        return this.tip;
    }

    public final String getUpdate_content() {
        return this.update_content;
    }

    public final Integer getVersion() {
        return this.version;
    }

    public int hashCode() {
        String str = this.appId;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.channel;
        int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.cype;
        int iHashCode3 = (iHashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.downLink;
        int iHashCode4 = (iHashCode3 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.model;
        int iHashCode5 = (iHashCode4 + (str5 == null ? 0 : str5.hashCode())) * 31;
        Integer num = this.tip;
        int iHashCode6 = (iHashCode5 + (num == null ? 0 : num.hashCode())) * 31;
        Integer num2 = this.version;
        int iHashCode7 = (iHashCode6 + (num2 == null ? 0 : num2.hashCode())) * 31;
        String str6 = this.update_content;
        return iHashCode7 + (str6 != null ? str6.hashCode() : 0);
    }

    public final void setDownLink(String str) {
        this.downLink = str;
    }

    public String toString() {
        return "UpdateDto(appId=" + this.appId + ", channel=" + this.channel + ", cype=" + this.cype + ", downLink=" + this.downLink + ", model=" + this.model + ", tip=" + this.tip + ", version=" + this.version + ", update_content=" + this.update_content + ")";
    }
}
