package com.softwinner.tv.data;

/* JADX INFO: loaded from: classes.dex */
public class AwEPGEvent {
    public long channelID;
    public Event[] events;
    public int freq;
    public int serviceID;

    public static class Event {
        public String desc;
        public String endTimeUtcMillis;
        public String longDesc;
        public String ratings;
        public String startTimeUtcMillis;
        public String title;
    }
}
