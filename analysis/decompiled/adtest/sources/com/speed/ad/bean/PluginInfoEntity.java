package com.speed.ad.bean;

import a.a;
import h1.c0;
import j2.f;
import j2.i;
import l3.h;
import t1.o;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class PluginInfoEntity {
    private Long bu_domain_ms;
    private Boolean enable_interval_checks;
    private Boolean is_upload_log;
    private String md5;
    private Long ping_domain_interval;
    private Long rd_domain_ms;
    private Long schedule_time;
    private Long sdkIntervalTime;
    private final String sdk_addr;
    private final String sdk_version;

    public PluginInfoEntity(String str, String str2, Long l4, String str3, Long l5, Long l6, Long l7, Long l8, Boolean bool, Boolean bool2) {
        this.sdk_addr = str;
        this.sdk_version = str2;
        this.sdkIntervalTime = l4;
        this.md5 = str3;
        this.schedule_time = l5;
        this.bu_domain_ms = l6;
        this.rd_domain_ms = l7;
        this.ping_domain_interval = l8;
        this.enable_interval_checks = bool;
        this.is_upload_log = bool2;
    }

    private final void checkBdDomainMs() {
        Long l4 = this.bu_domain_ms;
        if (l4 != null) {
            long jLongValue = l4.longValue();
            c0.f1036a.getClass();
            long j4 = 1000;
            if (jLongValue != c0.f1039d / j4) {
                Long l5 = this.bu_domain_ms;
                i.b(l5);
                if (l5.longValue() >= 900) {
                    String str = "切换备用域名冷却时间和接口不一致，当前是：" + (c0.f1039d / j4) + "，接口是：" + this.bu_domain_ms;
                    h.a0(str);
                    o.f2158a.a("backup_domain_interval_mismatch", str);
                    Long l6 = this.bu_domain_ms;
                    i.b(l6);
                    long jLongValue2 = l6.longValue() * j4;
                    c0.f1039d = jLongValue2;
                    a.E("persist.autorun.budomain_ms", jLongValue2);
                }
            }
        }
    }

    private final void checkEnableUploadLog() {
        Boolean bool = this.is_upload_log;
        if (bool != null) {
            boolean zBooleanValue = bool.booleanValue();
            c0.f1036a.getClass();
            if (zBooleanValue != c0.k()) {
                String str = "本地上传日志和接口不一致，当前是：" + c0.k() + "，接口是：" + this.is_upload_log;
                h.a0(str);
                o.f2158a.a("enable_uploadlog_mismatch", str);
                Boolean bool2 = this.is_upload_log;
                i.b(bool2);
                boolean zBooleanValue2 = bool2.booleanValue();
                c0.f1058w.e(c0.f1037b[17], bool2);
                try {
                    Class.forName("android.os.SystemProperties").getMethod("set", String.class, String.class).invoke(null, "persist.autorun.uplog", String.valueOf(zBooleanValue2));
                } catch (Exception unused) {
                }
            }
        }
    }

    private final void checkPingDomainInterval() {
        Long l4 = this.ping_domain_interval;
        if (l4 != null) {
            long jLongValue = l4.longValue();
            c0.f1036a.getClass();
            long j4 = 1000;
            if (jLongValue != c0.f1041f / j4) {
                Long l5 = this.ping_domain_interval;
                i.b(l5);
                if (l5.longValue() >= 900) {
                    String str = "获取随机域名间隔时间和接口不一致，当前是：" + (c0.f1041f / j4) + "，接口是：" + this.ping_domain_interval;
                    h.a0(str);
                    o.f2158a.a("domain_ping_interval_mismatch", str);
                    Long l6 = this.ping_domain_interval;
                    i.b(l6);
                    long jLongValue2 = l6.longValue() * j4;
                    c0.f1041f = jLongValue2;
                    a.E("persist.autorun.ping_rd_time", jLongValue2);
                }
            }
        }
    }

    private final void checkRdDomainMs() {
        Long l4 = this.rd_domain_ms;
        if (l4 != null) {
            long jLongValue = l4.longValue();
            c0.f1036a.getClass();
            long j4 = 1000;
            if (jLongValue != c0.f1040e / j4) {
                Long l5 = this.rd_domain_ms;
                i.b(l5);
                if (l5.longValue() >= 900) {
                    String str = "切换随机域名冷却时间和接口不一致，当前是：" + (c0.f1040e / j4) + "，接口是：" + this.rd_domain_ms;
                    h.a0(str);
                    o.f2158a.a("rd_domain_interval_mismatch", str);
                    Long l6 = this.rd_domain_ms;
                    i.b(l6);
                    long jLongValue2 = l6.longValue() * j4;
                    c0.f1040e = jLongValue2;
                    a.E("persist.autorun.rddomain_ms", jLongValue2);
                }
            }
        }
    }

    private final void checkScheduleTime() {
        Long l4 = this.schedule_time;
        if (l4 != null) {
            long jLongValue = l4.longValue();
            c0.f1036a.getClass();
            if (jLongValue != c0.f1038c) {
                Long l5 = this.schedule_time;
                i.b(l5);
                if (l5.longValue() >= 900) {
                    String str = "本地调度器执行间隔和接口不一致，当前是：" + c0.f1038c + "，接口是：" + this.schedule_time;
                    h.a0(str);
                    o.f2158a.a("schedule_interval_mismatch", str);
                    Long l6 = this.schedule_time;
                    i.b(l6);
                    long jLongValue2 = l6.longValue();
                    c0.f1038c = jLongValue2;
                    a.E("persist.autorun.schedule_time", jLongValue2);
                }
            }
        }
    }

    public static /* synthetic */ PluginInfoEntity copy$default(PluginInfoEntity pluginInfoEntity, String str, String str2, Long l4, String str3, Long l5, Long l6, Long l7, Long l8, Boolean bool, Boolean bool2, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            str = pluginInfoEntity.sdk_addr;
        }
        if ((i4 & 2) != 0) {
            str2 = pluginInfoEntity.sdk_version;
        }
        if ((i4 & 4) != 0) {
            l4 = pluginInfoEntity.sdkIntervalTime;
        }
        if ((i4 & 8) != 0) {
            str3 = pluginInfoEntity.md5;
        }
        if ((i4 & 16) != 0) {
            l5 = pluginInfoEntity.schedule_time;
        }
        if ((i4 & 32) != 0) {
            l6 = pluginInfoEntity.bu_domain_ms;
        }
        if ((i4 & 64) != 0) {
            l7 = pluginInfoEntity.rd_domain_ms;
        }
        if ((i4 & 128) != 0) {
            l8 = pluginInfoEntity.ping_domain_interval;
        }
        if ((i4 & 256) != 0) {
            bool = pluginInfoEntity.enable_interval_checks;
        }
        if ((i4 & 512) != 0) {
            bool2 = pluginInfoEntity.is_upload_log;
        }
        Boolean bool3 = bool;
        Boolean bool4 = bool2;
        Long l9 = l7;
        Long l10 = l8;
        Long l11 = l5;
        Long l12 = l6;
        return pluginInfoEntity.copy(str, str2, l4, str3, l11, l12, l9, l10, bool3, bool4);
    }

    public final void checkInterval() {
        Boolean bool = this.enable_interval_checks;
        if (bool == null || !i.a(bool, Boolean.TRUE)) {
            return;
        }
        checkScheduleTime();
        checkBdDomainMs();
        checkRdDomainMs();
        checkPingDomainInterval();
        checkEnableUploadLog();
    }

    public final void checkPeriodicTaskInterval() {
        Long l4 = this.sdkIntervalTime;
        if (l4 != null) {
            long jLongValue = l4.longValue();
            c0.f1036a.getClass();
            long j4 = 1000;
            if (jLongValue != c0.g() * j4) {
                Long l5 = this.sdkIntervalTime;
                i.b(l5);
                if (l5.longValue() >= 300000) {
                    h.a0("本地任务执行间隔时间和接口不一致，当前是：" + (c0.g() * j4) + "，接口是：" + this.sdkIntervalTime);
                    o.f2158a.a("task_interval_error", "本地任务执行间隔时间和接口不一致，当前是：" + (c0.g() * j4) + "，接口是：" + this.sdkIntervalTime);
                    Long l6 = this.sdkIntervalTime;
                    i.b(l6);
                    c0.f1051p.e(c0.f1037b[9], Long.valueOf(l6.longValue() / j4));
                }
            }
        }
    }

    public final String component1() {
        return this.sdk_addr;
    }

    public final Boolean component10() {
        return this.is_upload_log;
    }

    public final String component2() {
        return this.sdk_version;
    }

    public final Long component3() {
        return this.sdkIntervalTime;
    }

    public final String component4() {
        return this.md5;
    }

    public final Long component5() {
        return this.schedule_time;
    }

    public final Long component6() {
        return this.bu_domain_ms;
    }

    public final Long component7() {
        return this.rd_domain_ms;
    }

    public final Long component8() {
        return this.ping_domain_interval;
    }

    public final Boolean component9() {
        return this.enable_interval_checks;
    }

    public final PluginInfoEntity copy(String str, String str2, Long l4, String str3, Long l5, Long l6, Long l7, Long l8, Boolean bool, Boolean bool2) {
        return new PluginInfoEntity(str, str2, l4, str3, l5, l6, l7, l8, bool, bool2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PluginInfoEntity)) {
            return false;
        }
        PluginInfoEntity pluginInfoEntity = (PluginInfoEntity) obj;
        return i.a(this.sdk_addr, pluginInfoEntity.sdk_addr) && i.a(this.sdk_version, pluginInfoEntity.sdk_version) && i.a(this.sdkIntervalTime, pluginInfoEntity.sdkIntervalTime) && i.a(this.md5, pluginInfoEntity.md5) && i.a(this.schedule_time, pluginInfoEntity.schedule_time) && i.a(this.bu_domain_ms, pluginInfoEntity.bu_domain_ms) && i.a(this.rd_domain_ms, pluginInfoEntity.rd_domain_ms) && i.a(this.ping_domain_interval, pluginInfoEntity.ping_domain_interval) && i.a(this.enable_interval_checks, pluginInfoEntity.enable_interval_checks) && i.a(this.is_upload_log, pluginInfoEntity.is_upload_log);
    }

    public final Long getBu_domain_ms() {
        return this.bu_domain_ms;
    }

    public final Boolean getEnable_interval_checks() {
        return this.enable_interval_checks;
    }

    public final String getMd5() {
        return this.md5;
    }

    public final Long getPing_domain_interval() {
        return this.ping_domain_interval;
    }

    public final Long getRd_domain_ms() {
        return this.rd_domain_ms;
    }

    public final Long getSchedule_time() {
        return this.schedule_time;
    }

    public final Long getSdkIntervalTime() {
        return this.sdkIntervalTime;
    }

    public final String getSdk_addr() {
        return this.sdk_addr;
    }

    public final String getSdk_version() {
        return this.sdk_version;
    }

    public int hashCode() {
        String str = this.sdk_addr;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.sdk_version;
        int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        Long l4 = this.sdkIntervalTime;
        int iHashCode3 = (iHashCode2 + (l4 == null ? 0 : l4.hashCode())) * 31;
        String str3 = this.md5;
        int iHashCode4 = (iHashCode3 + (str3 == null ? 0 : str3.hashCode())) * 31;
        Long l5 = this.schedule_time;
        int iHashCode5 = (iHashCode4 + (l5 == null ? 0 : l5.hashCode())) * 31;
        Long l6 = this.bu_domain_ms;
        int iHashCode6 = (iHashCode5 + (l6 == null ? 0 : l6.hashCode())) * 31;
        Long l7 = this.rd_domain_ms;
        int iHashCode7 = (iHashCode6 + (l7 == null ? 0 : l7.hashCode())) * 31;
        Long l8 = this.ping_domain_interval;
        int iHashCode8 = (iHashCode7 + (l8 == null ? 0 : l8.hashCode())) * 31;
        Boolean bool = this.enable_interval_checks;
        int iHashCode9 = (iHashCode8 + (bool == null ? 0 : bool.hashCode())) * 31;
        Boolean bool2 = this.is_upload_log;
        return iHashCode9 + (bool2 != null ? bool2.hashCode() : 0);
    }

    public final Boolean is_upload_log() {
        return this.is_upload_log;
    }

    public final void setBu_domain_ms(Long l4) {
        this.bu_domain_ms = l4;
    }

    public final void setEnable_interval_checks(Boolean bool) {
        this.enable_interval_checks = bool;
    }

    public final void setMd5(String str) {
        this.md5 = str;
    }

    public final void setPing_domain_interval(Long l4) {
        this.ping_domain_interval = l4;
    }

    public final void setRd_domain_ms(Long l4) {
        this.rd_domain_ms = l4;
    }

    public final void setSchedule_time(Long l4) {
        this.schedule_time = l4;
    }

    public final void setSdkIntervalTime(Long l4) {
        this.sdkIntervalTime = l4;
    }

    public final void set_upload_log(Boolean bool) {
        this.is_upload_log = bool;
    }

    public String toString() {
        return "PluginInfoEntity(sdk_addr=" + this.sdk_addr + ", sdk_version=" + this.sdk_version + ", sdkIntervalTime=" + this.sdkIntervalTime + ", md5=" + this.md5 + ", schedule_time=" + this.schedule_time + ", bu_domain_ms=" + this.bu_domain_ms + ", rd_domain_ms=" + this.rd_domain_ms + ", ping_domain_interval=" + this.ping_domain_interval + ", enable_interval_checks=" + this.enable_interval_checks + ", is_upload_log=" + this.is_upload_log + ")";
    }

    public /* synthetic */ PluginInfoEntity(String str, String str2, Long l4, String str3, Long l5, Long l6, Long l7, Long l8, Boolean bool, Boolean bool2, int i4, f fVar) {
        this(str, str2, (i4 & 4) != 0 ? 5000L : l4, (i4 & 8) != 0 ? "" : str3, (i4 & 16) != 0 ? null : l5, (i4 & 32) != 0 ? null : l6, (i4 & 64) != 0 ? null : l7, (i4 & 128) != 0 ? null : l8, (i4 & 256) != 0 ? null : bool, (i4 & 512) != 0 ? null : bool2);
    }
}
