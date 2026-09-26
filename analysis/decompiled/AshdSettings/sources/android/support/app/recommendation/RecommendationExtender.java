package android.support.app.recommendation;

import android.app.Notification;
import android.os.Bundle;

/* JADX INFO: loaded from: classes.dex */
public final class RecommendationExtender implements Notification.Extender {
    private static final String EXTRA_CONTENT_INFO_EXTENDER = "android.CONTENT_INFO_EXTENSIONS";
    private static final String KEY_CONTENT_GENRES = "android.contentGenre";
    private static final String KEY_CONTENT_MATURITY_RATING = "android.contentMaturity";
    private static final String KEY_CONTENT_PRICING_TYPE = "android.contentPricing.type";
    private static final String KEY_CONTENT_PRICING_VALUE = "android.contentPricing.value";
    private static final String KEY_CONTENT_RUN_LENGTH = "android.contentLength";
    private static final String KEY_CONTENT_STATUS = "android.contentStatus";
    private static final String KEY_CONTENT_TYPE = "android.contentType";
    private static final String TAG = "RecommendationExtender";
    private int mContentStatus;
    private String[] mGenres;
    private String mMaturityRating;
    private String mPricingType;
    private String mPricingValue;
    private long mRunLength;
    private String[] mTypes;

    public RecommendationExtender() {
        this.mContentStatus = -1;
        this.mRunLength = -1L;
    }

    public RecommendationExtender(Notification notification) {
        this.mContentStatus = -1;
        this.mRunLength = -1L;
        Bundle bundle = notification.extras == null ? null : notification.extras.getBundle(EXTRA_CONTENT_INFO_EXTENDER);
        if (bundle != null) {
            this.mTypes = bundle.getStringArray(KEY_CONTENT_TYPE);
            this.mGenres = bundle.getStringArray(KEY_CONTENT_GENRES);
            this.mPricingType = bundle.getString(KEY_CONTENT_PRICING_TYPE);
            this.mPricingValue = bundle.getString(KEY_CONTENT_PRICING_VALUE);
            this.mContentStatus = bundle.getInt(KEY_CONTENT_STATUS, -1);
            this.mMaturityRating = bundle.getString(KEY_CONTENT_MATURITY_RATING);
            this.mRunLength = bundle.getLong(KEY_CONTENT_RUN_LENGTH, -1L);
        }
    }

    @Override // android.app.Notification.Extender
    public Notification.Builder extend(Notification.Builder builder) {
        Bundle bundle = new Bundle();
        if (this.mTypes != null) {
            bundle.putStringArray(KEY_CONTENT_TYPE, this.mTypes);
        }
        if (this.mGenres != null) {
            bundle.putStringArray(KEY_CONTENT_GENRES, this.mGenres);
        }
        if (this.mPricingType != null) {
            bundle.putString(KEY_CONTENT_PRICING_TYPE, this.mPricingType);
        }
        if (this.mPricingValue != null) {
            bundle.putString(KEY_CONTENT_PRICING_VALUE, this.mPricingValue);
        }
        if (this.mContentStatus != -1) {
            bundle.putInt(KEY_CONTENT_STATUS, this.mContentStatus);
        }
        if (this.mMaturityRating != null) {
            bundle.putString(KEY_CONTENT_MATURITY_RATING, this.mMaturityRating);
        }
        if (this.mRunLength > 0) {
            bundle.putLong(KEY_CONTENT_RUN_LENGTH, this.mRunLength);
        }
        builder.getExtras().putBundle(EXTRA_CONTENT_INFO_EXTENDER, bundle);
        return builder;
    }

    public RecommendationExtender setContentTypes(String[] strArr) {
        this.mTypes = strArr;
        return this;
    }

    public String[] getContentTypes() {
        return this.mTypes;
    }

    public String getPrimaryContentType() {
        if (this.mTypes == null || this.mTypes.length == 0) {
            return null;
        }
        return this.mTypes[0];
    }

    public RecommendationExtender setGenres(String[] strArr) {
        this.mGenres = strArr;
        return this;
    }

    public String[] getGenres() {
        return this.mGenres;
    }

    public RecommendationExtender setPricingInformation(String str, String str2) {
        this.mPricingType = str;
        this.mPricingValue = str2;
        return this;
    }

    public String getPricingType() {
        return this.mPricingType;
    }

    public String getPricingValue() {
        if (this.mPricingType == null) {
            return null;
        }
        return this.mPricingValue;
    }

    public RecommendationExtender setStatus(int i) {
        this.mContentStatus = i;
        return this;
    }

    public int getStatus() {
        return this.mContentStatus;
    }

    public RecommendationExtender setMaturityRating(String str) {
        this.mMaturityRating = str;
        return this;
    }

    public String getMaturityRating() {
        return this.mMaturityRating;
    }

    public RecommendationExtender setRunningTime(long j) {
        if (j < 0) {
            throw new IllegalArgumentException("Invalid value for Running Time");
        }
        this.mRunLength = j;
        return this;
    }

    public long getRunningTime() {
        return this.mRunLength;
    }
}
