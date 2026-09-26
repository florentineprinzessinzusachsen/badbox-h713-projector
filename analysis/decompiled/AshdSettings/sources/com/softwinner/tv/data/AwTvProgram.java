package com.softwinner.tv.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.media.tv.TvContentRating;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class AwTvProgram {
    private static final boolean DEBUG = false;
    public static final int INVALID_VALUE = -1;
    private static final String TAG = "AwTvProgram";
    private long mChannelId;
    private TvContentRating[] mContentRatings;
    private long mEndTimeUtcMillis;
    private int mEpisodeNumber;
    private String mEpisodeTitle;
    private long mId;
    private String mInternalProviderData;
    private String mLongDescription;
    private int mProgramId;
    private String mShortDescription;
    private long mStartTimeUtcMillis;
    private String mTitle;

    private AwTvProgram() {
        this.mId = -1L;
        this.mChannelId = -1L;
        this.mProgramId = -1;
        this.mEpisodeNumber = -1;
        this.mStartTimeUtcMillis = -1L;
        this.mEndTimeUtcMillis = -1L;
    }

    public long getId() {
        return this.mId;
    }

    public long getProgramId() {
        return this.mProgramId;
    }

    public long getChannelId() {
        return this.mChannelId;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public String getEpisodeTitle() {
        return this.mEpisodeTitle;
    }

    public int getEpisodeNumber() {
        return this.mEpisodeNumber;
    }

    public long getStartTimeUtcMillis() {
        return this.mStartTimeUtcMillis;
    }

    public long getEndTimeUtcMillis() {
        return this.mEndTimeUtcMillis;
    }

    public String getShortDescription() {
        return this.mShortDescription;
    }

    public String getLongDescription() {
        return this.mLongDescription;
    }

    public TvContentRating[] getContentRatings() {
        return this.mContentRatings;
    }

    public String toString() {
        return "Program{id=" + this.mId + "programId=" + this.mProgramId + ", channelId=" + this.mChannelId + ", title=" + this.mTitle + ", episodeTitle=" + this.mEpisodeTitle + ", episodeNumber=" + this.mEpisodeNumber + ", startTimeUtcSec=" + (this.mStartTimeUtcMillis / 1000) + ", endTimeUtcSec=" + (this.mEndTimeUtcMillis / 1000) + ", contentRatings=" + this.mContentRatings + "}";
    }

    public ContentValues toContentValues() {
        ContentValues contentValues = new ContentValues();
        if (this.mChannelId != -1) {
            contentValues.put("channel_id", Long.valueOf(this.mChannelId));
        } else {
            contentValues.putNull("channel_id");
        }
        if (!TextUtils.isEmpty(this.mTitle)) {
            contentValues.put("title", this.mTitle);
        } else {
            contentValues.putNull("title");
        }
        if (!TextUtils.isEmpty(this.mEpisodeTitle)) {
            contentValues.put("episode_title", this.mEpisodeTitle);
        } else {
            contentValues.putNull("episode_title");
        }
        if (this.mEpisodeNumber != -1) {
            contentValues.put("episode_number", Integer.valueOf(this.mEpisodeNumber));
        } else {
            contentValues.putNull("episode_number");
        }
        if (!TextUtils.isEmpty(this.mShortDescription)) {
            contentValues.put("short_description", this.mShortDescription);
        } else {
            contentValues.putNull("short_description");
        }
        if (this.mContentRatings != null && this.mContentRatings.length > 0) {
            contentValues.put("content_rating", contentRatingsToString(this.mContentRatings));
        } else {
            contentValues.putNull("content_rating");
        }
        if (this.mStartTimeUtcMillis != -1) {
            contentValues.put("start_time_utc_millis", Long.valueOf(this.mStartTimeUtcMillis));
        } else {
            contentValues.putNull("start_time_utc_millis");
        }
        if (this.mEndTimeUtcMillis != -1) {
            contentValues.put("end_time_utc_millis", Long.valueOf(this.mEndTimeUtcMillis));
        } else {
            contentValues.putNull("end_time_utc_millis");
        }
        return contentValues;
    }

    public static AwTvProgram fromCursor(Cursor cursor) {
        Builder builder = new Builder();
        int columnIndex = cursor.getColumnIndex("_id");
        if (columnIndex >= 0 && !cursor.isNull(columnIndex)) {
            builder.setId(cursor.getLong(columnIndex));
        }
        int columnIndex2 = cursor.getColumnIndex("channel_id");
        if (columnIndex2 >= 0 && !cursor.isNull(columnIndex2)) {
            builder.setChannelId(cursor.getLong(columnIndex2));
        }
        int columnIndex3 = cursor.getColumnIndex("title");
        if (columnIndex3 >= 0 && !cursor.isNull(columnIndex3)) {
            builder.setTitle(cursor.getString(columnIndex3));
        }
        int columnIndex4 = cursor.getColumnIndex("episode_title");
        if (columnIndex4 >= 0 && !cursor.isNull(columnIndex4)) {
            builder.setEpisodeTitle(cursor.getString(columnIndex4));
        }
        int columnIndex5 = cursor.getColumnIndex("episode_number");
        if (columnIndex5 >= 0 && !cursor.isNull(columnIndex5)) {
            builder.setEpisodeNumber(cursor.getInt(columnIndex5));
        }
        int columnIndex6 = cursor.getColumnIndex("short_description");
        if (columnIndex6 >= 0 && !cursor.isNull(columnIndex6)) {
            builder.setShortDescription(cursor.getString(columnIndex6));
        }
        int columnIndex7 = cursor.getColumnIndex("long_description");
        if (columnIndex7 >= 0 && !cursor.isNull(columnIndex7)) {
            builder.setLongDescription(cursor.getString(columnIndex7));
        }
        int columnIndex8 = cursor.getColumnIndex("content_rating");
        if (columnIndex8 >= 0 && !cursor.isNull(columnIndex8)) {
            builder.setContentRatings(stringToContentRatings(cursor.getString(columnIndex8)));
        }
        int columnIndex9 = cursor.getColumnIndex("start_time_utc_millis");
        if (columnIndex9 >= 0 && !cursor.isNull(columnIndex9)) {
            builder.setStartTimeUtcMillis(cursor.getLong(columnIndex9));
        }
        int columnIndex10 = cursor.getColumnIndex("end_time_utc_millis");
        if (columnIndex10 >= 0 && !cursor.isNull(columnIndex10)) {
            builder.setEndTimeUtcMillis(cursor.getLong(columnIndex10));
        }
        return builder.build();
    }

    public static List<AwTvProgram> fromSearchEPGEvent(AwEPGEvent awEPGEvent, long j) {
        if (awEPGEvent == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < awEPGEvent.events.length; i++) {
            AwEPGEvent.Event event = awEPGEvent.events[i];
            arrayList.add(new Builder().setChannelId(j).setTitle(event.title).setStartTimeUtcMillis(Long.parseLong(event.startTimeUtcMillis)).setEndTimeUtcMillis(Long.parseLong(event.endTimeUtcMillis)).setShortDescription(event.desc).build());
        }
        return arrayList;
    }

    public static final class Builder {
        private final AwTvProgram mProgram = new AwTvProgram();

        public Builder setId(long j) {
            this.mProgram.mId = j;
            return this;
        }

        public Builder setProgramId(int i) {
            this.mProgram.mProgramId = i;
            return this;
        }

        public Builder setChannelId(long j) {
            this.mProgram.mChannelId = j;
            return this;
        }

        public Builder setTitle(String str) {
            this.mProgram.mTitle = str;
            return this;
        }

        public Builder setEpisodeTitle(String str) {
            this.mProgram.mEpisodeTitle = str;
            return this;
        }

        public Builder setEpisodeNumber(int i) {
            this.mProgram.mEpisodeNumber = i;
            return this;
        }

        public Builder setStartTimeUtcMillis(long j) {
            this.mProgram.mStartTimeUtcMillis = j;
            return this;
        }

        public Builder setEndTimeUtcMillis(long j) {
            this.mProgram.mEndTimeUtcMillis = j;
            return this;
        }

        public Builder setShortDescription(String str) {
            this.mProgram.mShortDescription = str;
            return this;
        }

        public Builder setLongDescription(String str) {
            this.mProgram.mLongDescription = str;
            return this;
        }

        public Builder setContentRatings(TvContentRating[] tvContentRatingArr) {
            this.mProgram.mContentRatings = tvContentRatingArr;
            return this;
        }

        public AwTvProgram build() {
            return this.mProgram;
        }
    }

    public static TvContentRating[] stringToContentRatings(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String[] strArrSplit = str.split("\\s*,\\s*");
        TvContentRating[] tvContentRatingArr = new TvContentRating[strArrSplit.length];
        for (int i = 0; i < tvContentRatingArr.length; i++) {
            tvContentRatingArr[i] = TvContentRating.unflattenFromString(strArrSplit[i]);
        }
        return tvContentRatingArr;
    }

    public static String contentRatingsToString(TvContentRating[] tvContentRatingArr) {
        if (tvContentRatingArr == null || tvContentRatingArr.length == 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder(tvContentRatingArr[0].flattenToString());
        for (int i = 1; i < tvContentRatingArr.length; i++) {
            sb.append(",");
            sb.append(tvContentRatingArr[i].flattenToString());
        }
        return sb.toString();
    }
}
