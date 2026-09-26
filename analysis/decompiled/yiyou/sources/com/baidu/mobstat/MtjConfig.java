package com.baidu.mobstat;

/* JADX INFO: loaded from: classes.dex */
public class MtjConfig {
    public static final String BAIDU_MTJ_PUSH_CALL = "Baidu_mtj_push_call";
    public static final String BAIDU_MTJ_PUSH_MSG = "Baidu_mtj_push_msg";

    public enum FeedTrackStrategy {
        TRACK_ALL,
        TRACK_SINGLE,
        TRACK_NONE
    }

    public enum PushPlatform {
        BAIDUYUN("baiduyun", 0),
        JIGUANG("jiguang", 1),
        GETUI("getui", 2),
        HUAWEI("huawei", 3),
        XIAOMI("xiaomi", 4),
        UMENG("umeng", 5),
        XINGE("xinge", 6),
        ALIYUN("aliyun", 7),
        OPPO("oppo", 8),
        MEIZU("meizu", 9);


        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private String f3395a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private int f3396b;

        PushPlatform(String str, int i) {
            this.f3395a = str;
            this.f3396b = i;
        }

        public String showName() {
            return this.f3395a;
        }

        public String value() {
            return "p" + this.f3396b;
        }
    }
}
