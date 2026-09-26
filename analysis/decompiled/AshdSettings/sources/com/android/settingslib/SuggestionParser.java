package com.android.settingslib;

import android.accounts.AccountManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.os.UserHandle;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Pair;
import android.util.Xml;
import android.view.InflateException;
import com.android.settingslib.drawer.Tile;
import com.android.settingslib.drawer.TileUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes.dex */
public class SuggestionParser {
    private static final String DISMISS_INDEX = "_dismiss_index";
    private static final String IS_DISMISSED = "_is_dismissed";
    public static final String META_DATA_DISMISS_CONTROL = "com.android.settings.dismiss";
    private static final String META_DATA_IS_SUPPORTED = "com.android.settings.is_supported";
    private static final String META_DATA_REQUIRE_ACCOUNT = "com.android.settings.require_account";
    public static final String META_DATA_REQUIRE_FEATURE = "com.android.settings.require_feature";
    private static final long MILLIS_IN_DAY = 86400000;
    private static final String SETUP_TIME = "_setup_time";
    private static final String TAG = "SuggestionParser";
    private final ArrayMap<Pair<String, String>, Tile> addCache = new ArrayMap<>();
    private final Context mContext;
    private final SharedPreferences mSharedPrefs;
    private final List<SuggestionCategory> mSuggestionList;

    private long getEndTime(long j, int i) {
        return j + (((long) i) * MILLIS_IN_DAY);
    }

    public SuggestionParser(Context context, SharedPreferences sharedPreferences, int i) {
        this.mContext = context;
        this.mSuggestionList = (List) new SuggestionOrderInflater(this.mContext).parse(i);
        this.mSharedPrefs = sharedPreferences;
    }

    public List<Tile> getSuggestions() {
        ArrayList arrayList = new ArrayList();
        int size = this.mSuggestionList.size();
        for (int i = 0; i < size; i++) {
            readSuggestions(this.mSuggestionList.get(i), arrayList);
        }
        return arrayList;
    }

    public boolean dismissSuggestion(Tile tile) {
        String strFlattenToShortString = tile.intent.getComponent().flattenToShortString();
        int i = this.mSharedPrefs.getInt(strFlattenToShortString + DISMISS_INDEX, 0);
        String string = tile.metaData.getString(META_DATA_DISMISS_CONTROL);
        if (string == null || parseDismissString(string).length == i) {
            return true;
        }
        this.mSharedPrefs.edit().putBoolean(strFlattenToShortString + IS_DISMISSED, true).commit();
        return false;
    }

    private void readSuggestions(SuggestionCategory suggestionCategory, List<Tile> list) {
        int size = list.size();
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory(suggestionCategory.category);
        if (suggestionCategory.pkg != null) {
            intent.setPackage(suggestionCategory.pkg);
        }
        TileUtils.getTilesForIntent(this.mContext, new UserHandle(UserHandle.myUserId()), intent, this.addCache, null, list, true, false);
        int i = size;
        while (i < list.size()) {
            if (!isAvailable(list.get(i)) || !isSupported(list.get(i)) || !satisfiesRequiredAccount(list.get(i)) || isDismissed(list.get(i))) {
                list.remove(i);
                i--;
            }
            i++;
        }
        if (suggestionCategory.multiple || list.size() <= size + 1) {
            return;
        }
        Tile tileRemove = list.remove(list.size() - 1);
        while (list.size() > size) {
            Tile tileRemove2 = list.remove(list.size() - 1);
            if (tileRemove2.priority > tileRemove.priority) {
                tileRemove = tileRemove2;
            }
        }
        if (isCategoryDone(suggestionCategory.category)) {
            return;
        }
        list.add(tileRemove);
    }

    private boolean isAvailable(Tile tile) {
        String string = tile.metaData.getString(META_DATA_REQUIRE_FEATURE);
        if (string != null) {
            return this.mContext.getPackageManager().hasSystemFeature(string);
        }
        return true;
    }

    public boolean satisfiesRequiredAccount(Tile tile) {
        String string = tile.metaData.getString(META_DATA_REQUIRE_ACCOUNT);
        return string == null || AccountManager.get(this.mContext).getAccountsByType(string).length > 0;
    }

    public boolean isSupported(Tile tile) {
        int i = tile.metaData.getInt(META_DATA_IS_SUPPORTED);
        try {
            if (tile.intent == null) {
                return false;
            }
            Resources resourcesForActivity = this.mContext.getPackageManager().getResourcesForActivity(tile.intent.getComponent());
            if (i != 0) {
                return resourcesForActivity.getBoolean(i);
            }
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            Log.w(TAG, "Cannot find resources for " + tile.intent.getComponent());
            return false;
        } catch (Resources.NotFoundException e) {
            Log.w(TAG, "Cannot find resources for " + tile.intent.getComponent(), e);
            return false;
        }
    }

    public boolean isCategoryDone(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("suggested.completed_category.");
        sb.append(str);
        return Settings.Secure.getInt(this.mContext.getContentResolver(), sb.toString(), 0) != 0;
    }

    public void markCategoryDone(String str) {
        Settings.Secure.putInt(this.mContext.getContentResolver(), "suggested.completed_category." + str, 1);
    }

    private boolean isDismissed(Tile tile) {
        Object obj = tile.metaData.get(META_DATA_DISMISS_CONTROL);
        if (obj == null) {
            return false;
        }
        String strValueOf = String.valueOf(obj);
        String strFlattenToShortString = tile.intent.getComponent().flattenToShortString();
        if (!this.mSharedPrefs.contains(strFlattenToShortString + SETUP_TIME)) {
            this.mSharedPrefs.edit().putLong(strFlattenToShortString + SETUP_TIME, System.currentTimeMillis()).commit();
        }
        if (!this.mSharedPrefs.getBoolean(strFlattenToShortString + IS_DISMISSED, true)) {
            return false;
        }
        int i = this.mSharedPrefs.getInt(strFlattenToShortString + DISMISS_INDEX, 0);
        if (System.currentTimeMillis() < getEndTime(this.mSharedPrefs.getLong(strFlattenToShortString + SETUP_TIME, 0L), parseDismissString(strValueOf)[i])) {
            return true;
        }
        this.mSharedPrefs.edit().putBoolean(strFlattenToShortString + IS_DISMISSED, false).putInt(strFlattenToShortString + DISMISS_INDEX, i + 1).commit();
        return false;
    }

    private int[] parseDismissString(String str) {
        String[] strArrSplit = str.split(",");
        int[] iArr = new int[strArrSplit.length];
        for (int i = 0; i < strArrSplit.length; i++) {
            iArr[i] = Integer.parseInt(strArrSplit[i]);
        }
        return iArr;
    }

    private static class SuggestionCategory {
        public String category;
        public boolean multiple;
        public String pkg;

        private SuggestionCategory() {
        }
    }

    private static class SuggestionOrderInflater {
        private static final String ATTR_CATEGORY = "category";
        private static final String ATTR_MULTIPLE = "multiple";
        private static final String ATTR_PACKAGE = "package";
        private static final String TAG_ITEM = "step";
        private static final String TAG_LIST = "optional-steps";
        private final Context mContext;

        public SuggestionOrderInflater(Context context) {
            this.mContext = context;
        }

        public Object parse(int i) {
            int next;
            XmlResourceParser xml = this.mContext.getResources().getXml(i);
            AttributeSet attributeSetAsAttributeSet = Xml.asAttributeSet(xml);
            do {
                try {
                    next = xml.next();
                    if (next == 2) {
                        break;
                    }
                } catch (IOException | XmlPullParserException e) {
                    Log.w(SuggestionParser.TAG, "Problem parser resource " + i, e);
                    return null;
                }
            } while (next != 1);
            if (next != 2) {
                throw new InflateException(xml.getPositionDescription() + ": No start tag found!");
            }
            Object objOnCreateItem = onCreateItem(xml.getName(), attributeSetAsAttributeSet);
            rParse(xml, objOnCreateItem, attributeSetAsAttributeSet);
            return objOnCreateItem;
        }

        private void rParse(XmlPullParser xmlPullParser, Object obj, AttributeSet attributeSet) throws XmlPullParserException, IOException {
            int depth = xmlPullParser.getDepth();
            while (true) {
                int next = xmlPullParser.next();
                if ((next == 3 && xmlPullParser.getDepth() <= depth) || next == 1) {
                    return;
                }
                if (next == 2) {
                    Object objOnCreateItem = onCreateItem(xmlPullParser.getName(), attributeSet);
                    onAddChildItem(obj, objOnCreateItem);
                    rParse(xmlPullParser, objOnCreateItem, attributeSet);
                }
            }
        }

        protected void onAddChildItem(Object obj, Object obj2) {
            if ((obj instanceof List) && (obj2 instanceof SuggestionCategory)) {
                ((List) obj).add((SuggestionCategory) obj2);
                return;
            }
            throw new IllegalArgumentException("Parent was not a list");
        }

        protected Object onCreateItem(String str, AttributeSet attributeSet) {
            if (str.equals(TAG_LIST)) {
                return new ArrayList();
            }
            if (str.equals(TAG_ITEM)) {
                SuggestionCategory suggestionCategory = new SuggestionCategory();
                suggestionCategory.category = attributeSet.getAttributeValue(null, ATTR_CATEGORY);
                suggestionCategory.pkg = attributeSet.getAttributeValue(null, ATTR_PACKAGE);
                String attributeValue = attributeSet.getAttributeValue(null, ATTR_MULTIPLE);
                suggestionCategory.multiple = !TextUtils.isEmpty(attributeValue) && Boolean.parseBoolean(attributeValue);
                return suggestionCategory;
            }
            throw new IllegalArgumentException("Unknown item " + str);
        }
    }
}
