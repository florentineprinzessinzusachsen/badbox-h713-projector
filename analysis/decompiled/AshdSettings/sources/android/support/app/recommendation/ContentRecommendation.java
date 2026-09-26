package android.support.app.recommendation;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.hardware.audio.common.V2_0.AudioFormat;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class ContentRecommendation {
    public static final String CONTENT_MATURITY_ALL = "android.contentMaturity.all";
    public static final String CONTENT_MATURITY_HIGH = "android.contentMaturity.high";
    public static final String CONTENT_MATURITY_LOW = "android.contentMaturity.low";
    public static final String CONTENT_MATURITY_MEDIUM = "android.contentMaturity.medium";
    public static final String CONTENT_PRICING_FREE = "android.contentPrice.free";
    public static final String CONTENT_PRICING_PREORDER = "android.contentPrice.preorder";
    public static final String CONTENT_PRICING_PURCHASE = "android.contentPrice.purchase";
    public static final String CONTENT_PRICING_RENTAL = "android.contentPrice.rental";
    public static final String CONTENT_PRICING_SUBSCRIPTION = "android.contentPrice.subscription";
    public static final int CONTENT_STATUS_AVAILABLE = 2;
    public static final int CONTENT_STATUS_PENDING = 1;
    public static final int CONTENT_STATUS_READY = 0;
    public static final int CONTENT_STATUS_UNAVAILABLE = 3;
    public static final String CONTENT_TYPE_APP = "android.contentType.app";
    public static final String CONTENT_TYPE_BOOK = "android.contentType.book";
    public static final String CONTENT_TYPE_COMIC = "android.contentType.comic";
    public static final String CONTENT_TYPE_GAME = "android.contentType.game";
    public static final String CONTENT_TYPE_MAGAZINE = "android.contentType.magazine";
    public static final String CONTENT_TYPE_MOVIE = "android.contentType.movie";
    public static final String CONTENT_TYPE_MUSIC = "android.contentType.music";
    public static final String CONTENT_TYPE_NEWS = "android.contentType.news";
    public static final String CONTENT_TYPE_PODCAST = "android.contentType.podcast";
    public static final String CONTENT_TYPE_RADIO = "android.contentType.radio";
    public static final String CONTENT_TYPE_SERIAL = "android.contentType.serial";
    public static final String CONTENT_TYPE_SPORTS = "android.contentType.sports";
    public static final String CONTENT_TYPE_TRAILER = "android.contentType.trailer";
    public static final String CONTENT_TYPE_VIDEO = "android.contentType.video";
    public static final String CONTENT_TYPE_WEBSITE = "android.contentType.website";
    public static final int INTENT_TYPE_ACTIVITY = 1;
    public static final int INTENT_TYPE_BROADCAST = 2;
    public static final int INTENT_TYPE_SERVICE = 3;
    private boolean mAutoDismiss;
    private final String mBackgroundImageUri;
    private final int mBadgeIconId;
    private final int mColor;
    private final String[] mContentGenres;
    private final Bitmap mContentImage;
    private final IntentData mContentIntentData;
    private final String[] mContentTypes;
    private final IntentData mDismissIntentData;
    private String mGroup;
    private final String mIdTag;
    private final String mMaturityRating;
    private final String mPriceType;
    private final String mPriceValue;
    private int mProgressAmount;
    private int mProgressMax;
    private final long mRunningTime;
    private String mSortKey;
    private final String mSourceName;
    private int mStatus;
    private final String mText;
    private final String mTitle;

    @Retention(RetentionPolicy.SOURCE)
    public @interface ContentMaturity {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ContentPricing {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ContentStatus {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ContentType {
    }

    public static class IntentData {
        Intent mIntent;
        Bundle mOptions;
        int mRequestCode;
        int mType;
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface IntentType {
    }

    private ContentRecommendation(Builder builder) {
        this.mIdTag = builder.mBuilderIdTag;
        this.mTitle = builder.mBuilderTitle;
        this.mText = builder.mBuilderText;
        this.mSourceName = builder.mBuilderSourceName;
        this.mContentImage = builder.mBuilderContentImage;
        this.mBadgeIconId = builder.mBuilderBadgeIconId;
        this.mBackgroundImageUri = builder.mBuilderBackgroundImageUri;
        this.mColor = builder.mBuilderColor;
        this.mContentIntentData = builder.mBuilderContentIntentData;
        this.mDismissIntentData = builder.mBuilderDismissIntentData;
        this.mContentTypes = builder.mBuilderContentTypes;
        this.mContentGenres = builder.mBuilderContentGenres;
        this.mPriceType = builder.mBuilderPriceType;
        this.mPriceValue = builder.mBuilderPriceValue;
        this.mMaturityRating = builder.mBuilderMaturityRating;
        this.mRunningTime = builder.mBuilderRunningTime;
        this.mGroup = builder.mBuilderGroup;
        this.mSortKey = builder.mBuilderSortKey;
        this.mProgressAmount = builder.mBuilderProgressAmount;
        this.mProgressMax = builder.mBuilderProgressMax;
        this.mAutoDismiss = builder.mBuilderAutoDismiss;
        this.mStatus = builder.mBuilderStatus;
    }

    public String getIdTag() {
        return this.mIdTag;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public String getText() {
        return this.mText;
    }

    public String getSourceName() {
        return this.mSourceName;
    }

    public Bitmap getContentImage() {
        return this.mContentImage;
    }

    public int getBadgeImageResourceId() {
        return this.mBadgeIconId;
    }

    public String getBackgroundImageUri() {
        return this.mBackgroundImageUri;
    }

    public int getColor() {
        return this.mColor;
    }

    public void setGroup(String str) {
        this.mGroup = str;
    }

    public String getGroup() {
        return this.mGroup;
    }

    public void setSortKey(String str) {
        this.mSortKey = str;
    }

    public String getSortKey() {
        return this.mSortKey;
    }

    public void setProgress(int i, int i2) {
        if (i < 0 || i2 < 0) {
            throw new IllegalArgumentException();
        }
        this.mProgressMax = i;
        this.mProgressAmount = i2;
    }

    public boolean hasProgressInfo() {
        return this.mProgressMax != 0;
    }

    public int getProgressMax() {
        return this.mProgressMax;
    }

    public int getProgressValue() {
        return this.mProgressAmount;
    }

    public void setAutoDismiss(boolean z) {
        this.mAutoDismiss = z;
    }

    public boolean isAutoDismiss() {
        return this.mAutoDismiss;
    }

    public IntentData getContentIntent() {
        return this.mContentIntentData;
    }

    public IntentData getDismissIntent() {
        return this.mDismissIntentData;
    }

    public String[] getContentTypes() {
        if (this.mContentTypes != null) {
            return (String[]) Arrays.copyOf(this.mContentTypes, this.mContentTypes.length);
        }
        return this.mContentTypes;
    }

    public String getPrimaryContentType() {
        if (this.mContentTypes == null || this.mContentTypes.length <= 0) {
            return null;
        }
        return this.mContentTypes[0];
    }

    public String[] getGenres() {
        if (this.mContentGenres != null) {
            return (String[]) Arrays.copyOf(this.mContentGenres, this.mContentGenres.length);
        }
        return this.mContentGenres;
    }

    public String getPricingType() {
        return this.mPriceType;
    }

    public String getPricingValue() {
        return this.mPriceValue;
    }

    public void setStatus(int i) {
        this.mStatus = i;
    }

    public int getStatus() {
        return this.mStatus;
    }

    public String getMaturityRating() {
        return this.mMaturityRating;
    }

    public long getRunningTime() {
        return this.mRunningTime;
    }

    public boolean equals(Object obj) {
        if (obj instanceof ContentRecommendation) {
            return TextUtils.equals(this.mIdTag, ((ContentRecommendation) obj).getIdTag());
        }
        return false;
    }

    public int hashCode() {
        if (this.mIdTag != null) {
            return this.mIdTag.hashCode();
        }
        return Integer.MAX_VALUE;
    }

    public static final class Builder {
        private boolean mBuilderAutoDismiss;
        private String mBuilderBackgroundImageUri;
        private int mBuilderBadgeIconId;
        private int mBuilderColor;
        private String[] mBuilderContentGenres;
        private Bitmap mBuilderContentImage;
        private IntentData mBuilderContentIntentData;
        private String[] mBuilderContentTypes;
        private IntentData mBuilderDismissIntentData;
        private String mBuilderGroup;
        private String mBuilderIdTag;
        private String mBuilderMaturityRating;
        private String mBuilderPriceType;
        private String mBuilderPriceValue;
        private int mBuilderProgressAmount;
        private int mBuilderProgressMax;
        private long mBuilderRunningTime;
        private String mBuilderSortKey;
        private String mBuilderSourceName;
        private int mBuilderStatus;
        private String mBuilderText;
        private String mBuilderTitle;

        public Builder setIdTag(String str) {
            this.mBuilderIdTag = (String) ContentRecommendation.checkNotNull(str);
            return this;
        }

        public Builder setTitle(String str) {
            this.mBuilderTitle = (String) ContentRecommendation.checkNotNull(str);
            return this;
        }

        public Builder setText(@Nullable String str) {
            this.mBuilderText = str;
            return this;
        }

        public Builder setSourceName(@Nullable String str) {
            this.mBuilderSourceName = str;
            return this;
        }

        public Builder setContentImage(Bitmap bitmap) {
            this.mBuilderContentImage = (Bitmap) ContentRecommendation.checkNotNull(bitmap);
            return this;
        }

        public Builder setBadgeIcon(@DrawableRes int i) {
            this.mBuilderBadgeIconId = i;
            return this;
        }

        public Builder setBackgroundImageUri(@Nullable String str) {
            this.mBuilderBackgroundImageUri = str;
            return this;
        }

        public Builder setColor(@ColorInt int i) {
            this.mBuilderColor = i;
            return this;
        }

        public Builder setGroup(@Nullable String str) {
            this.mBuilderGroup = str;
            return this;
        }

        public Builder setSortKey(@Nullable String str) {
            this.mBuilderSortKey = str;
            return this;
        }

        public Builder setProgress(int i, int i2) {
            if (i < 0 || i2 < 0) {
                throw new IllegalArgumentException();
            }
            this.mBuilderProgressMax = i;
            this.mBuilderProgressAmount = i2;
            return this;
        }

        public Builder setAutoDismiss(boolean z) {
            this.mBuilderAutoDismiss = z;
            return this;
        }

        public Builder setContentIntentData(int i, Intent intent, int i2, @Nullable Bundle bundle) {
            if (i != 1 && i != 2 && i != 3) {
                throw new IllegalArgumentException("Invalid Intent type specified.");
            }
            this.mBuilderContentIntentData = new IntentData();
            this.mBuilderContentIntentData.mType = i;
            this.mBuilderContentIntentData.mIntent = (Intent) ContentRecommendation.checkNotNull(intent);
            this.mBuilderContentIntentData.mRequestCode = i2;
            this.mBuilderContentIntentData.mOptions = bundle;
            return this;
        }

        public Builder setDismissIntentData(int i, @Nullable Intent intent, int i2, @Nullable Bundle bundle) {
            if (intent == null) {
                this.mBuilderDismissIntentData = null;
            } else {
                if (i != 1 && i != 2 && i != 3) {
                    throw new IllegalArgumentException("Invalid Intent type specified.");
                }
                this.mBuilderDismissIntentData = new IntentData();
                this.mBuilderDismissIntentData.mType = i;
                this.mBuilderDismissIntentData.mIntent = intent;
                this.mBuilderDismissIntentData.mRequestCode = i2;
                this.mBuilderDismissIntentData.mOptions = bundle;
            }
            return this;
        }

        public Builder setContentTypes(String[] strArr) {
            this.mBuilderContentTypes = (String[]) ContentRecommendation.checkNotNull(strArr);
            return this;
        }

        public Builder setGenres(String[] strArr) {
            this.mBuilderContentGenres = strArr;
            return this;
        }

        public Builder setPricingInformation(String str, @Nullable String str2) {
            this.mBuilderPriceType = (String) ContentRecommendation.checkNotNull(str);
            this.mBuilderPriceValue = str2;
            return this;
        }

        public Builder setStatus(int i) {
            this.mBuilderStatus = i;
            return this;
        }

        public Builder setMaturityRating(String str) {
            this.mBuilderMaturityRating = (String) ContentRecommendation.checkNotNull(str);
            return this;
        }

        public Builder setRunningTime(long j) {
            if (j < 0) {
                throw new IllegalArgumentException();
            }
            this.mBuilderRunningTime = j;
            return this;
        }

        public ContentRecommendation build() {
            return new ContentRecommendation(this);
        }
    }

    public Notification getNotificationObject(Context context) {
        PendingIntent broadcast;
        PendingIntent broadcast2;
        Notification.Builder builder = new Notification.Builder(context);
        RecommendationExtender recommendationExtender = new RecommendationExtender();
        builder.setCategory("recommendation");
        builder.setContentTitle(this.mTitle);
        builder.setContentText(this.mText);
        builder.setContentInfo(this.mSourceName);
        builder.setLargeIcon(this.mContentImage);
        builder.setSmallIcon(this.mBadgeIconId);
        if (this.mBackgroundImageUri != null) {
            builder.getExtras().putString(NotificationCompat.EXTRA_BACKGROUND_IMAGE_URI, this.mBackgroundImageUri);
        }
        builder.setColor(this.mColor);
        builder.setGroup(this.mGroup);
        builder.setSortKey(this.mSortKey);
        builder.setProgress(this.mProgressMax, this.mProgressAmount, false);
        builder.setAutoCancel(this.mAutoDismiss);
        if (this.mContentIntentData != null) {
            if (this.mContentIntentData.mType == 1) {
                broadcast2 = PendingIntent.getActivity(context, this.mContentIntentData.mRequestCode, this.mContentIntentData.mIntent, AudioFormat.OPUS, this.mContentIntentData.mOptions);
            } else if (this.mContentIntentData.mType == 3) {
                broadcast2 = PendingIntent.getService(context, this.mContentIntentData.mRequestCode, this.mContentIntentData.mIntent, AudioFormat.OPUS);
            } else {
                broadcast2 = PendingIntent.getBroadcast(context, this.mContentIntentData.mRequestCode, this.mContentIntentData.mIntent, AudioFormat.OPUS);
            }
            builder.setContentIntent(broadcast2);
        }
        if (this.mDismissIntentData != null) {
            if (this.mDismissIntentData.mType == 1) {
                broadcast = PendingIntent.getActivity(context, this.mDismissIntentData.mRequestCode, this.mDismissIntentData.mIntent, AudioFormat.OPUS, this.mDismissIntentData.mOptions);
            } else if (this.mDismissIntentData.mType == 3) {
                broadcast = PendingIntent.getService(context, this.mDismissIntentData.mRequestCode, this.mDismissIntentData.mIntent, AudioFormat.OPUS);
            } else {
                broadcast = PendingIntent.getBroadcast(context, this.mDismissIntentData.mRequestCode, this.mDismissIntentData.mIntent, AudioFormat.OPUS);
            }
            builder.setDeleteIntent(broadcast);
        }
        recommendationExtender.setContentTypes(this.mContentTypes);
        recommendationExtender.setGenres(this.mContentGenres);
        recommendationExtender.setPricingInformation(this.mPriceType, this.mPriceValue);
        recommendationExtender.setStatus(this.mStatus);
        recommendationExtender.setMaturityRating(this.mMaturityRating);
        recommendationExtender.setRunningTime(this.mRunningTime);
        builder.extend(recommendationExtender);
        return builder.build();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <T> T checkNotNull(T t) {
        if (t == null) {
            throw new NullPointerException();
        }
        return t;
    }
}
