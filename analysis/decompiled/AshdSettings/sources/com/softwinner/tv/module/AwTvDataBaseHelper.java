package com.softwinner.tv.module;

import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.media.tv.TvContract;
import android.net.Uri;
import android.util.Log;
import com.softwinner.tv.common.AwTvUtils;
import com.softwinner.tv.data.AwTvChannelInfo;
import com.softwinner.tv.data.AwTvProgram;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class AwTvDataBaseHelper {
    public static final String COLUMN_LAST_TUNE_INPUT_KEY = "input_key";
    public static final String COLUMN_LAST_TUNE_URI = "last_uri";
    public static final Uri CONTENT_LAST_TUNE_URI = Uri.parse("content://android.media.tv/uri");
    private static final boolean DEBUG = true;
    private static final String TAG = "AwTvDataBaseHelper";
    public static final int UPDATE_SUCCESS = -1;
    private ContentResolver mContentResolver;
    private Context mContext;

    public AwTvDataBaseHelper(Context context) {
        this.mContext = context;
        this.mContentResolver = this.mContext.getContentResolver();
    }

    public void registerChannelObserver(ContentObserver contentObserver) {
        this.mContentResolver.registerContentObserver(TvContract.Channels.CONTENT_URI, true, contentObserver);
    }

    public void unregisterChannelObserver(ContentObserver contentObserver) {
        this.mContentResolver.unregisterContentObserver(contentObserver);
    }

    public int deleteChannels(String str) {
        return deleteChannels(str, null);
    }

    public int deleteChannels(String str, String str2) {
        int iDelete;
        Log.d(TAG, "deleteChannels inputId=" + str + " type=" + str2);
        Uri uriBuildChannelsUriForInput = TvContract.buildChannelsUriForInput(str);
        try {
            if (str2 == null) {
                iDelete = this.mContentResolver.delete(uriBuildChannelsUriForInput, "_id!=-1", null);
            } else {
                iDelete = this.mContentResolver.delete(uriBuildChannelsUriForInput, "_id!=-1 and type='" + str2 + "'", null);
            }
            return iDelete;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int deleteChannel(AwTvChannelInfo awTvChannelInfo) {
        return deleteChannel(awTvChannelInfo, false);
    }

    public int deleteChannel(AwTvChannelInfo awTvChannelInfo, boolean z) throws Throwable {
        Cursor cursorQuery;
        Uri uriBuildChannelsUriForInput = TvContract.buildChannelsUriForInput(awTvChannelInfo.getInputId());
        String[] strArr = {"_id", "display_number", "display_name"};
        ArrayList<ContentProviderOperation> arrayList = new ArrayList<>();
        int iDelete = this.mContentResolver.delete(uriBuildChannelsUriForInput, "_id=?", new String[]{awTvChannelInfo.getId() + ""});
        if (iDelete > 0 && z) {
            try {
                cursorQuery = this.mContentResolver.query(uriBuildChannelsUriForInput, strArr, "service_type=?", new String[]{awTvChannelInfo.getServiceType()}, null);
                while (cursorQuery != null) {
                    try {
                        if (!cursorQuery.moveToNext()) {
                            break;
                        }
                        long j = cursorQuery.getLong(findPosition(strArr, "_id"));
                        int i = cursorQuery.getInt(findPosition(strArr, "display_number"));
                        String string = cursorQuery.getString(findPosition(strArr, "display_name"));
                        if (i > awTvChannelInfo.getNumber()) {
                            Log.d(TAG, "deleteChannel: update channel: number=" + i + " name=" + string);
                            Uri uriBuildChannelUri = TvContract.buildChannelUri(j);
                            ContentValues contentValues = new ContentValues();
                            contentValues.put("display_number", Integer.valueOf(i + (-1)));
                            arrayList.add(ContentProviderOperation.newUpdate(uriBuildChannelUri).withValues(contentValues).build());
                        }
                    } catch (Exception unused) {
                        if (cursorQuery != null) {
                        }
                        return iDelete;
                    } catch (Throwable th) {
                        th = th;
                        if (cursorQuery != null) {
                            cursorQuery.close();
                        }
                        throw th;
                    }
                }
                this.mContentResolver.applyBatch("android.media.tv", arrayList);
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
            } catch (Exception unused2) {
                cursorQuery = null;
            } catch (Throwable th2) {
                th = th2;
                cursorQuery = null;
            }
        }
        return iDelete;
    }

    private int updateDtvChannel(AwTvChannelInfo awTvChannelInfo) throws Throwable {
        Cursor cursorQuery;
        int count;
        String[] strArr = {"_id", "service_id", "original_network_id", "transport_stream_id", "display_number", "display_name", "internal_provider_data"};
        Cursor cursor = null;
        int i = 0;
        try {
            try {
                cursorQuery = this.mContentResolver.query(TvContract.buildChannelsUriForInput(awTvChannelInfo.getInputId()), strArr, "service_type=?", new String[]{awTvChannelInfo.getServiceType()}, null);
                boolean z = false;
                while (cursorQuery != null) {
                    try {
                        if (!cursorQuery.moveToNext()) {
                            break;
                        }
                        long j = cursorQuery.getLong(findPosition(strArr, "_id"));
                        if (awTvChannelInfo.getId() == -1) {
                            int i2 = cursorQuery.getInt(findPosition(strArr, "service_id"));
                            int i3 = cursorQuery.getInt(findPosition(strArr, "original_network_id"));
                            int i4 = cursorQuery.getInt(findPosition(strArr, "transport_stream_id"));
                            cursorQuery.getString(findPosition(strArr, "display_name"));
                            int columnIndex = cursorQuery.getColumnIndex("internal_provider_data");
                            int i5 = columnIndex >= 0 ? Integer.parseInt(AwTvUtils.jsonToMap(cursorQuery.getString(columnIndex)).get(AwTvChannelInfo.KEY_FREQUENCY)) : 0;
                            if (i2 == awTvChannelInfo.getServiceId() && i3 == awTvChannelInfo.getOriginalNetworkId() && i4 == awTvChannelInfo.getTransportStreamId() && i5 == awTvChannelInfo.getFrequency()) {
                                z = true;
                            }
                        } else if (j == awTvChannelInfo.getId()) {
                            z = true;
                        }
                        if (z) {
                            this.mContentResolver.update(TvContract.buildChannelUri(j), buildDtvChannelData(awTvChannelInfo), null, null);
                            i = -1;
                            break;
                        }
                    } catch (Exception e) {
                        e = e;
                        cursor = cursorQuery;
                        e.printStackTrace();
                        if (cursor != null) {
                            cursor.close();
                        }
                        count = i;
                    } catch (Throwable th) {
                        th = th;
                        if (cursorQuery != null) {
                            cursorQuery.close();
                        }
                        throw th;
                    }
                }
                count = i != -1 ? cursorQuery.getCount() : i;
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
            } catch (Exception e2) {
                e = e2;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("update ");
            sb.append(count == -1 ? "found" : "notfound");
            sb.append(" DTV CH: [_id:");
            sb.append(awTvChannelInfo.getId());
            sb.append("][sid:");
            sb.append(awTvChannelInfo.getServiceId());
            sb.append("][freq:");
            sb.append(awTvChannelInfo.getFrequency());
            sb.append("][name:");
            sb.append(awTvChannelInfo.getDisplayName());
            sb.append("][num:");
            sb.append(awTvChannelInfo.getDisplayNumber());
            sb.append("]");
            Log.d(TAG, sb.toString());
            return count;
        } catch (Throwable th2) {
            th = th2;
            cursorQuery = cursor;
        }
    }

    public Uri insertDtvChannel(AwTvChannelInfo awTvChannelInfo, String str) {
        Log.d(TAG, "insertDtvChannel uri" + TvContract.buildChannelsUriForInput(awTvChannelInfo.getInputId()));
        awTvChannelInfo.setDisplayNumber(str);
        Uri uriInsert = this.mContentResolver.insert(TvContract.Channels.CONTENT_URI, buildDtvChannelData(awTvChannelInfo));
        Log.d(TAG, "Insert DTV CH: [sid:" + awTvChannelInfo.getServiceId() + "][freq:" + awTvChannelInfo.getFrequency() + "][name:" + awTvChannelInfo.getDisplayName() + "][num:" + awTvChannelInfo.getDisplayNumber() + "]");
        return uriInsert;
    }

    public Uri insertDtvChannel(AwTvChannelInfo awTvChannelInfo) {
        Log.d(TAG, "insertDtvChannel uri" + TvContract.buildChannelsUriForInput(awTvChannelInfo.getInputId()));
        Uri uriInsert = this.mContentResolver.insert(TvContract.Channels.CONTENT_URI, buildDtvChannelData(awTvChannelInfo));
        Log.d(TAG, "Insert DTV CH: [sid:" + awTvChannelInfo.getServiceId() + "][freq:" + awTvChannelInfo.getFrequency() + "][name:" + awTvChannelInfo.getDisplayName() + "][num:" + awTvChannelInfo.getDisplayNumber() + "]");
        return uriInsert;
    }

    private ContentValues buildDtvChannelData(AwTvChannelInfo awTvChannelInfo) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("input_id", awTvChannelInfo.getInputId());
        contentValues.put("display_name", awTvChannelInfo.getDisplayName());
        contentValues.put("display_number", awTvChannelInfo.getDisplayNumber());
        contentValues.put("original_network_id", Integer.valueOf(awTvChannelInfo.getOriginalNetworkId()));
        contentValues.put("transport_stream_id", Integer.valueOf(awTvChannelInfo.getTransportStreamId()));
        contentValues.put("service_id", Integer.valueOf(awTvChannelInfo.getServiceId()));
        contentValues.put("type", awTvChannelInfo.getType());
        contentValues.put("browsable", Integer.valueOf(awTvChannelInfo.isBrowsable() ? 1 : 0));
        contentValues.put("locked", Integer.valueOf(awTvChannelInfo.isLocked() ? 1 : 0));
        contentValues.put("service_type", awTvChannelInfo.getServiceType());
        HashMap map = new HashMap();
        map.put(AwTvChannelInfo.KEY_FREQUENCY, String.valueOf(awTvChannelInfo.getFrequency()));
        map.put(AwTvChannelInfo.KEY_SYMBOL_RATE, String.valueOf(awTvChannelInfo.getSymbolRate()));
        map.put(AwTvChannelInfo.KEY_MODULATION, String.valueOf(awTvChannelInfo.getModulation()));
        map.put(AwTvChannelInfo.KEY_BAND_WIDTH, String.valueOf(awTvChannelInfo.getBandwidth()));
        map.put(AwTvChannelInfo.KEY_VIDEO_PID, String.valueOf(awTvChannelInfo.getVideoPid()));
        map.put(AwTvChannelInfo.KEY_AUDIO_PIDS, Arrays.toString(awTvChannelInfo.getAudioPids()));
        map.put(AwTvChannelInfo.KEY_PCR_PID, String.valueOf(awTvChannelInfo.getPcrPid()));
        map.put(AwTvChannelInfo.KEY_AUDIO_TRACK_INDEX, String.valueOf(awTvChannelInfo.getAudioTrackIndex()));
        map.put(AwTvChannelInfo.KEY_SUBT_TRACK_INDEX, String.valueOf(awTvChannelInfo.getSubtitleTrackIndex()));
        map.put(AwTvChannelInfo.KEY_MULTI_NAME, AwTvUtils.TvString.toString(awTvChannelInfo.getDisplayNameMulti()));
        map.put(AwTvChannelInfo.KEY_FREE_CA, String.valueOf(awTvChannelInfo.getFreeCa()));
        map.put(AwTvChannelInfo.KEY_SCRAMBLED, String.valueOf(awTvChannelInfo.getScrambled()));
        map.put(AwTvChannelInfo.KEY_EIT_SCHEDULE, String.valueOf(awTvChannelInfo.getEitSchedule()));
        map.put(AwTvChannelInfo.KEY_EIT_PRESENT_FOLLOWING, String.valueOf(awTvChannelInfo.getEitPresentFollowing()));
        map.put(AwTvChannelInfo.KEY_CHANNEL_FAVOURITE, String.valueOf(awTvChannelInfo.getFavourite()));
        map.put(AwTvChannelInfo.KEY_CHANNEL_FAVOURITE_GROUP, String.valueOf(awTvChannelInfo.getFavgroup()));
        map.put(AwTvChannelInfo.KEY_CHANNEL_SKIP, String.valueOf(awTvChannelInfo.getSkip()));
        contentValues.put("internal_provider_data", AwTvUtils.mapToJson(map));
        Log.d(TAG, "buildDtvChannelData " + contentValues);
        return contentValues;
    }

    public void updateChannelInfo(AwTvChannelInfo awTvChannelInfo) throws Throwable {
        if (awTvChannelInfo.getInputId() == null) {
            return;
        }
        if (awTvChannelInfo.isAnalogChannel()) {
            updateAtvChannel(awTvChannelInfo);
        } else {
            updateDtvChannel(awTvChannelInfo);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v2 */
    /* JADX WARN: Type inference failed for: r0v3, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r0v5 */
    /* JADX WARN: Type inference failed for: r3v1 */
    /* JADX WARN: Type inference failed for: r3v10 */
    /* JADX WARN: Type inference failed for: r3v2 */
    /* JADX WARN: Type inference failed for: r3v3, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r3v4 */
    /* JADX WARN: Type inference failed for: r3v6 */
    /* JADX WARN: Type inference failed for: r3v7 */
    /* JADX WARN: Type inference failed for: r3v8 */
    /* JADX WARN: Type inference failed for: r3v9 */
    public void updateOrInsertDtvChannelWithNumber(AwTvChannelInfo awTvChannelInfo, String str) throws Throwable {
        ?? r0;
        int i;
        String str2;
        String[] strArr = {"_id", "service_id", "original_network_id", "transport_stream_id", "display_number", "display_name", "internal_provider_data"};
        int i2 = -1;
        ?? r3 = 0;
        r3 = 0;
        r3 = 0;
        try {
            try {
                Cursor cursorQuery = this.mContentResolver.query(TvContract.buildChannelsUriForInput(awTvChannelInfo.getInputId()), strArr, null, null);
                while (true) {
                    i = 0;
                    if (cursorQuery == null) {
                        break;
                    }
                    try {
                        if (!cursorQuery.moveToNext()) {
                            break;
                        }
                        int columnIndex = cursorQuery.getColumnIndex("internal_provider_data");
                        if (awTvChannelInfo.isSameChannel(new AwTvChannelInfo.Builder().setServiceId(cursorQuery.getInt(findPosition(strArr, "service_id"))).setDisplayName(cursorQuery.getString(findPosition(strArr, "display_name"))).setFrequency(columnIndex >= 0 ? Integer.parseInt(AwTvUtils.jsonToMap(cursorQuery.getString(columnIndex)).get(AwTvChannelInfo.KEY_FREQUENCY)) : 0).build())) {
                            Log.d(TAG, "updateOrInsertDtvChannelWithNumber found");
                            i = 1;
                            i2 = cursorQuery.getInt(findPosition(strArr, "_id"));
                            awTvChannelInfo.setDisplayNumber(cursorQuery.getString(findPosition(strArr, "display_number")));
                            break;
                        }
                    } catch (Exception e) {
                        e = e;
                        r3 = cursorQuery;
                        e.printStackTrace();
                        if (r3 != 0) {
                            r3.close();
                        }
                    } catch (Throwable th) {
                        th = th;
                        r0 = cursorQuery;
                        if (r0 != 0) {
                            r0.close();
                        }
                        throw th;
                    }
                }
                if (i != 0) {
                    Log.d(TAG, "updateOrInsertDtvChannelWithNumber update rowId:" + i2);
                    this.mContentResolver.update(TvContract.buildChannelUri((long) i2), buildDtvChannelData(awTvChannelInfo), null, null);
                } else {
                    StringBuilder sb = new StringBuilder();
                    str2 = "updateOrInsertDtvChannelWithNumber insert id:";
                    sb.append("updateOrInsertDtvChannelWithNumber insert id:");
                    sb.append(str);
                    Log.d(TAG, sb.toString());
                    insertDtvChannel(awTvChannelInfo, str);
                }
                if (cursorQuery != null) {
                    r3 = str2;
                    cursorQuery.close();
                }
            } catch (Throwable th2) {
                th = th2;
                r0 = r3;
            }
        } catch (Exception e2) {
            e = e2;
        }
        r3 = str2;
    }

    public void insertAtvChannel(AwTvChannelInfo awTvChannelInfo, String str) {
        Log.d(TAG, "insertAtvChannel uri " + TvContract.buildChannelsUriForInput(awTvChannelInfo.getInputId()));
        awTvChannelInfo.setDisplayNumber(str);
        Log.d(TAG, "insertAtvChannel uri produce " + this.mContentResolver.insert(TvContract.Channels.CONTENT_URI, buildAtvChannelData(awTvChannelInfo)));
        Log.d(TAG, "Insert ATV CH: [freq:" + awTvChannelInfo.getFrequency() + "][name:" + awTvChannelInfo.getDisplayName() + "][num:" + awTvChannelInfo.getDisplayNumber() + "]");
    }

    private ContentValues buildAtvChannelData(AwTvChannelInfo awTvChannelInfo) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("input_id", awTvChannelInfo.getInputId());
        contentValues.put("display_number", awTvChannelInfo.getDisplayNumber());
        contentValues.put("display_name", awTvChannelInfo.getDisplayName());
        contentValues.put("type", awTvChannelInfo.getType());
        contentValues.put("browsable", Integer.valueOf(awTvChannelInfo.isBrowsable() ? 1 : 0));
        contentValues.put("locked", Integer.valueOf(awTvChannelInfo.isLocked() ? 1 : 0));
        contentValues.put("service_type", awTvChannelInfo.getServiceType());
        HashMap map = new HashMap();
        map.put(AwTvChannelInfo.KEY_VFMT, String.valueOf(awTvChannelInfo.getVfmt()));
        map.put(AwTvChannelInfo.KEY_FREQUENCY, String.valueOf(awTvChannelInfo.getFrequency()));
        map.put(AwTvChannelInfo.KEY_ORIGFREQUENCY, String.valueOf(awTvChannelInfo.getOrgFrequency()));
        map.put(AwTvChannelInfo.KEY_VIDEO_STD, String.valueOf(awTvChannelInfo.getVideoStd()));
        map.put(AwTvChannelInfo.KEY_AUDIO_STD, String.valueOf(awTvChannelInfo.getAudioStd()));
        map.put(AwTvChannelInfo.KEY_IS_AUTO_STD, String.valueOf(awTvChannelInfo.getIsAutoStd()));
        map.put(AwTvChannelInfo.KEY_FINE_TUNE, String.valueOf(awTvChannelInfo.getFineTune()));
        map.put(AwTvChannelInfo.KEY_AUDIO_COMPENSATION, String.valueOf(awTvChannelInfo.getAudioCompensation()));
        map.put(AwTvChannelInfo.KEY_PROGRAM_NUMBER, String.valueOf(awTvChannelInfo.getNumber()));
        map.put(AwTvChannelInfo.KEY_CHANNEL_NO, String.valueOf(awTvChannelInfo.getChannelNo()));
        map.put(AwTvChannelInfo.KEY_CHANNEL_FAVOURITE, String.valueOf(awTvChannelInfo.getFavourite()));
        map.put(AwTvChannelInfo.KEY_CHANNEL_FAVOURITE_GROUP, String.valueOf(awTvChannelInfo.getFavgroup()));
        map.put(AwTvChannelInfo.KEY_CHANNEL_SKIP, String.valueOf(awTvChannelInfo.getSkip()));
        map.put(AwTvChannelInfo.KEY_AUDIO_SYS, String.valueOf(awTvChannelInfo.getAudioSys()));
        map.put(AwTvChannelInfo.KEY_AUDIO_MTS, String.valueOf(awTvChannelInfo.getAudioMts()));
        map.put(AwTvChannelInfo.KEY_AUDIO_OUT_MTS, String.valueOf(awTvChannelInfo.getAudioOutMts()));
        map.put(AwTvChannelInfo.KEY_AUDIO_MANUAL_OUT_MTS, String.valueOf(awTvChannelInfo.getAudioManualOutMts()));
        map.put(AwTvChannelInfo.KEY_COUNTRY, String.valueOf(awTvChannelInfo.getCountry()));
        contentValues.put("internal_provider_data", AwTvUtils.mapToJson(map));
        return contentValues;
    }

    /* JADX WARN: Code duplicated, block: B:28:0x0063  */
    /* JADX WARN: Code duplicated, block: B:34:0x007c  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v0, types: [java.lang.String[]] */
    /* JADX WARN: Type inference failed for: r10v1 */
    /* JADX WARN: Type inference failed for: r10v4, types: [android.database.Cursor] */
    public ArrayList<AwTvChannelInfo> getChannelList(String str, String[] strArr, String str2, String[] strArr2) throws Throwable {
        Cursor cursorQuery;
        Exception e;
        ArrayList<AwTvChannelInfo> arrayList = new ArrayList<>();
        try {
            try {
                cursorQuery = this.mContentResolver.query(TvContract.buildChannelsUriForInput(str), strArr, str2, strArr2, null);
                if (cursorQuery != null) {
                    try {
                        if (cursorQuery.getCount() != 0) {
                            while (cursorQuery.moveToNext()) {
                                arrayList.add(AwTvChannelInfo.fromCommonCursor(cursorQuery));
                            }
                            if (cursorQuery != null) {
                            }
                            if (arrayList.size() > 0 && arrayList.get(0).isAnalogChannel()) {
                                Collections.sort(arrayList, new SortDisplayNumComparator());
                            }
                            return arrayList;
                        }
                    } catch (Exception e2) {
                        e = e2;
                        Log.d(TAG, "Content provider query: " + e.getStackTrace());
                        if (cursorQuery != null) {
                        }
                        if (arrayList.size() > 0) {
                            Collections.sort(arrayList, new SortDisplayNumComparator());
                        }
                        return arrayList;
                    }
                    cursorQuery.close();
                    if (arrayList.size() > 0) {
                        Collections.sort(arrayList, new SortDisplayNumComparator());
                    }
                    return arrayList;
                }
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
                return arrayList;
            } catch (Throwable th) {
                th = th;
                if (strArr != 0) {
                    strArr.close();
                }
                throw th;
            }
        } catch (Exception e3) {
            cursorQuery = null;
            e = e3;
        } catch (Throwable th2) {
            th = th2;
            strArr = 0;
            if (strArr != 0) {
                strArr.close();
            }
            throw th;
        }
    }

    public ArrayList<AwTvChannelInfo> getChannelList(String str, int i, String[] strArr, String str2, String[] strArr2) throws Throwable {
        ArrayList<AwTvChannelInfo> channelList = getChannelList(str, strArr, str2, strArr2);
        ArrayList<AwTvChannelInfo> arrayList = new ArrayList<>();
        for (AwTvChannelInfo awTvChannelInfo : channelList) {
            if (awTvChannelInfo.getFrequency() == i) {
                arrayList.add(awTvChannelInfo);
            }
        }
        return arrayList;
    }

    public ArrayList<AwTvChannelInfo> getChannelListWithQueryMap(String str, String[] strArr, String str2, String[] strArr2, HashMap<String, Integer> map) throws Throwable {
        Cursor cursorQuery;
        ArrayList<AwTvChannelInfo> arrayList = new ArrayList<>();
        Cursor cursor = null;
        try {
            try {
                cursorQuery = this.mContentResolver.query(TvContract.buildChannelsUriForInput(str), strArr, str2, strArr2, null);
                if (cursorQuery != null) {
                    try {
                        if (cursorQuery.getCount() != 0) {
                            while (cursorQuery.moveToNext()) {
                                boolean z = true;
                                if (map != null && !map.isEmpty()) {
                                    Map<String, String> mapJsonToMap = AwTvUtils.jsonToMap(cursorQuery.getString(cursorQuery.getColumnIndex("internal_provider_data")));
                                    for (String str3 : map.keySet()) {
                                        Log.d(TAG, "key = " + str3 + "----- value =" + map.get(str3));
                                        if (Integer.parseInt(mapJsonToMap.get(str3)) != map.get(str3).intValue()) {
                                            z = false;
                                            break;
                                        }
                                    }
                                }
                                if (z) {
                                    arrayList.add(AwTvChannelInfo.fromCommonCursor(cursorQuery));
                                }
                            }
                            if (cursorQuery != null) {
                                cursorQuery.close();
                            }
                        }
                    } catch (Exception e) {
                        e = e;
                        cursor = cursorQuery;
                        Log.d(TAG, "Content provider query: " + e.getStackTrace());
                        if (cursor != null) {
                            cursor.close();
                        }
                    } catch (Throwable th) {
                        th = th;
                        if (cursorQuery != null) {
                            cursorQuery.close();
                        }
                        throw th;
                    }
                    if (arrayList.size() > 0 && arrayList.get(0).isAnalogChannel()) {
                        Collections.sort(arrayList, new SortDisplayNumComparator());
                    }
                    return arrayList;
                }
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
                return arrayList;
            } catch (Throwable th2) {
                th = th2;
                cursorQuery = cursor;
            }
        } catch (Exception e2) {
            e = e2;
        }
    }

    public AwTvChannelInfo getChannelList(String str, int i, int i2, String[] strArr, String str2, String[] strArr2) throws Throwable {
        ArrayList<AwTvChannelInfo> channelList = getChannelList(str, strArr, str2, strArr2);
        ArrayList arrayList = new ArrayList();
        for (AwTvChannelInfo awTvChannelInfo : channelList) {
            if (awTvChannelInfo.getFrequency() == i && awTvChannelInfo.getServiceId() == i2) {
                arrayList.add(awTvChannelInfo);
            }
        }
        if (arrayList.size() <= 0) {
            Log.w(TAG, "not found channel for freq=" + i + " serviceId=" + i2);
            return null;
        }
        if (arrayList.size() > 1) {
            Log.w(TAG, "more than one channel for freq=" + i + " serviceId=" + i2);
        }
        return (AwTvChannelInfo) arrayList.get(0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r9v0, types: [com.softwinner.tv.module.AwTvDataBaseHelper] */
    /* JADX WARN: Type inference failed for: r9v1, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r9v2 */
    public AwTvChannelInfo getChannelInfo(Uri uri) throws Throwable {
        Cursor cursorQuery;
        int iMatchsWhich = uri != null ? AwTvUtils.matchsWhich(uri) : -1;
        AwTvChannelInfo awTvChannelInfoFromCommonCursor = null;
        if (iMatchsWhich != -1) {
            try {
                if (iMatchsWhich != 1) {
                    try {
                        cursorQuery = this.mContentResolver.query(uri, AwTvChannelInfo.COMMON_PROJECTION, null, null, null);
                        if (cursorQuery != null) {
                            try {
                                if (cursorQuery.moveToNext()) {
                                    awTvChannelInfoFromCommonCursor = AwTvChannelInfo.fromCommonCursor(cursorQuery);
                                }
                            } catch (Exception e) {
                                e = e;
                                Log.e(TAG, "Failed to get channel info from TvProvider.", e);
                                if (cursorQuery != null) {
                                }
                                return awTvChannelInfoFromCommonCursor;
                            }
                        }
                        if (cursorQuery != null) {
                            cursorQuery.close();
                        }
                    } catch (Exception e2) {
                        e = e2;
                        cursorQuery = null;
                    } catch (Throwable th) {
                        th = th;
                        this = 0;
                        if (this != 0) {
                            this.close();
                        }
                        throw th;
                    }
                    return awTvChannelInfoFromCommonCursor;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }
        return null;
    }

    public List<AwTvProgram> getPrograms(long j, long j2, long j3) {
        return getPrograms(TvContract.buildProgramsUriForChannel(j, j2, j3));
    }

    public List<AwTvProgram> getPrograms(long j) {
        return getPrograms(TvContract.buildProgramsUriForChannel(j));
    }

    /* JADX WARN: Code duplicated, block: B:29:0x005b  */
    public List<AwTvProgram> getPrograms(Uri uri) throws Throwable {
        Cursor cursorQuery;
        Exception e;
        ArrayList arrayList = new ArrayList();
        try {
            cursorQuery = this.mContentResolver.query(uri, null, null, null, "start_time_utc_millis ASC");
            if (cursorQuery != null) {
                try {
                    try {
                        if (cursorQuery.getCount() != 0) {
                            while (cursorQuery.moveToNext()) {
                                arrayList.add(AwTvProgram.fromCursor(cursorQuery));
                            }
                            if (cursorQuery != null) {
                            }
                            return arrayList;
                        }
                    } catch (Exception e2) {
                        e = e2;
                        Log.w(TAG, "Unable to get programs for " + uri, e);
                        if (cursorQuery != null) {
                        }
                        return arrayList;
                    }
                    cursorQuery.close();
                    return arrayList;
                } catch (Throwable th) {
                    th = th;
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                    throw th;
                }
            }
            if (cursorQuery != null) {
                cursorQuery.close();
            }
            return arrayList;
        } catch (Exception e3) {
            e = e3;
            cursorQuery = null;
        } catch (Throwable th2) {
            th = th2;
            cursorQuery = null;
            if (cursorQuery != null) {
                cursorQuery.close();
            }
            throw th;
        }
    }

    public void insertPrograms(long j, List<AwTvProgram> list) {
        Log.d(TAG, "insertPrograms ");
        for (AwTvProgram awTvProgram : list) {
            this.mContentResolver.insert(TvContract.Programs.CONTENT_URI, awTvProgram.toContentValues());
            Log.d(TAG, "Insert Program : [id:" + awTvProgram.getId() + "][channelId:" + awTvProgram.getChannelId() + "][title:" + awTvProgram.getTitle() + "][start:" + awTvProgram.getStartTimeUtcMillis() + "][end:" + awTvProgram.getEndTimeUtcMillis() + "]");
        }
    }

    public AwTvProgram getProgram(Uri uri, long j) throws Throwable {
        Uri uriBuildProgramsUriForChannel = TvContract.buildProgramsUriForChannel(uri);
        Log.d(TAG, "getProgram uri=" + uriBuildProgramsUriForChannel);
        List<AwTvProgram> programs = getPrograms(uriBuildProgramsUriForChannel);
        int i = 0;
        AwTvProgram awTvProgram = null;
        while (i < programs.size()) {
            awTvProgram = programs.get(i);
            if (j >= awTvProgram.getStartTimeUtcMillis() && j < awTvProgram.getEndTimeUtcMillis()) {
                break;
            }
            i++;
        }
        if (i == programs.size()) {
            return null;
        }
        return awTvProgram;
    }

    public int deleteProgram(AwTvChannelInfo awTvChannelInfo) {
        return deleteProgram(Long.valueOf(awTvChannelInfo.getId()));
    }

    public int deleteProgram(Long l) {
        int iDelete = this.mContentResolver.delete(TvContract.Programs.CONTENT_URI, "channel_id=?", new String[]{String.valueOf(l)});
        if (iDelete > 0) {
            Log.d(TAG, "Deleted " + iDelete + " programs");
        }
        return iDelete;
    }

    /* JADX WARN: Code duplicated, block: B:12:0x0080 A[Catch: all -> 0x007b, Exception -> 0x007d, TRY_LEAVE, TryCatch #4 {Exception -> 0x007d, all -> 0x007b, blocks: (B:5:0x002b, B:7:0x0031, B:12:0x0080), top: B:29:0x002b }] */
    /* JADX WARN: Code duplicated, block: B:14:0x00ba  */
    /* JADX WARN: Instruction removed from duplicated block: B:12:0x0080, please report this as an issue */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0 */
    /* JADX WARN: Type inference failed for: r0v1 */
    /* JADX WARN: Type inference failed for: r0v2, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r0v3 */
    /* JADX WARN: Type inference failed for: r0v4 */
    /* JADX WARN: Type inference failed for: r0v5 */
    /* JADX WARN: Type inference failed for: r0v7 */
    /* JADX WARN: Type inference failed for: r0v8 */
    /* JADX WARN: Type inference failed for: r0v9 */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v1, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r1v4 */
    public void updateLastUri(String str, Uri uri) throws Throwable {
        ?? r1;
        ?? r0 = 0;
        r0 = 0;
        r0 = 0;
        try {
            try {
                Cursor cursorQuery = this.mContentResolver.query(CONTENT_LAST_TUNE_URI, new String[]{COLUMN_LAST_TUNE_INPUT_KEY, COLUMN_LAST_TUNE_URI}, "input_key = '" + str + "'", null, null);
                if (cursorQuery != null) {
                    try {
                        if (cursorQuery.moveToNext()) {
                            ContentValues contentValues = new ContentValues();
                            contentValues.put(COLUMN_LAST_TUNE_URI, uri.toString());
                            Log.d(TAG, "updateLastUri id:" + str + " value:" + contentValues);
                            this.mContentResolver.update(CONTENT_LAST_TUNE_URI, contentValues, "input_key = '" + str + "'", null);
                        } else {
                            ContentValues contentValues2 = new ContentValues();
                            contentValues2.put(COLUMN_LAST_TUNE_INPUT_KEY, str);
                            contentValues2.put(COLUMN_LAST_TUNE_URI, uri.toString());
                            Log.d(TAG, "insertLastUri id:" + str + " value:" + contentValues2);
                            this.mContentResolver.insert(CONTENT_LAST_TUNE_URI, contentValues2);
                            r0 = contentValues2;
                        }
                        if (cursorQuery != null) {
                            cursorQuery.close();
                        }
                    } catch (Exception e) {
                        e = e;
                        r0 = cursorQuery;
                        Log.e(TAG, "Failed to update last uri from TvProvider.", e);
                        if (r0 != 0) {
                            r0.close();
                        }
                    } catch (Throwable th) {
                        th = th;
                        r1 = cursorQuery;
                        if (r1 != 0) {
                            r1.close();
                        }
                        throw th;
                    }
                } else {
                    ContentValues contentValues3 = new ContentValues();
                    contentValues3.put(COLUMN_LAST_TUNE_INPUT_KEY, str);
                    contentValues3.put(COLUMN_LAST_TUNE_URI, uri.toString());
                    Log.d(TAG, "insertLastUri id:" + str + " value:" + contentValues3);
                    this.mContentResolver.insert(CONTENT_LAST_TUNE_URI, contentValues3);
                    r0 = contentValues3;
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                r1 = r0;
            }
        } catch (Exception e2) {
            e = e2;
        }
    }

    /* JADX WARN: Code duplicated, block: B:15:0x0074 A[Catch: all -> 0x006f, Exception -> 0x0071, TRY_LEAVE, TryCatch #4 {Exception -> 0x0071, all -> 0x006f, blocks: (B:5:0x002d, B:7:0x0033, B:9:0x0047, B:10:0x004c, B:15:0x0074), top: B:32:0x002d }] */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0 */
    /* JADX WARN: Type inference failed for: r0v1 */
    /* JADX WARN: Type inference failed for: r0v13 */
    /* JADX WARN: Type inference failed for: r0v15 */
    /* JADX WARN: Type inference failed for: r0v16 */
    /* JADX WARN: Type inference failed for: r0v17 */
    /* JADX WARN: Type inference failed for: r0v2, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r0v3 */
    /* JADX WARN: Type inference failed for: r0v4 */
    /* JADX WARN: Type inference failed for: r10v1 */
    /* JADX WARN: Type inference failed for: r10v2, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r10v6 */
    public long getCurrentChannelId(String str) throws Throwable {
        ?? r10;
        ?? r0 = 0;
        r0 = 0;
        long id = -1;
        try {
            try {
                Cursor cursorQuery = this.mContentResolver.query(CONTENT_LAST_TUNE_URI, new String[]{COLUMN_LAST_TUNE_INPUT_KEY, COLUMN_LAST_TUNE_URI}, "input_key = '" + str + "'", null, null);
                if (cursorQuery != null) {
                    try {
                        if (cursorQuery.moveToNext()) {
                            Uri uri = Uri.parse(cursorQuery.getString(cursorQuery.getColumnIndex(COLUMN_LAST_TUNE_URI)));
                            AwTvChannelInfo channelInfo = getChannelInfo(uri);
                            id = channelInfo != null ? channelInfo.getId() : -1L;
                            String str2 = "getCurrentChannelId  uri = :" + uri.toString() + "  channelid = " + id;
                            Log.d(TAG, str2);
                            r0 = str2;
                        } else {
                            Log.e(TAG, "Do not find any channel record.");
                            r0 = "Do not find any channel record.";
                        }
                    } catch (Exception e) {
                        e = e;
                        r0 = cursorQuery;
                        Log.e(TAG, "Failed to update last uri from TvProvider.", e);
                        if (r0 != 0) {
                            r0.close();
                        }
                    } catch (Throwable th) {
                        th = th;
                        r10 = cursorQuery;
                        if (r10 != 0) {
                            r10.close();
                        }
                        throw th;
                    }
                } else {
                    Log.e(TAG, "Do not find any channel record.");
                    r0 = "Do not find any channel record.";
                }
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
            } catch (Throwable th2) {
                th = th2;
                r10 = r0;
            }
        } catch (Exception e2) {
            e = e2;
        }
        return id;
    }

    private int findPosition(String[] strArr, String str) {
        for (int i = 0; i < strArr.length; i++) {
            if (strArr[i].equals(str)) {
                return i;
            }
        }
        return -1;
    }

    public void insertAtvChannel(AwTvChannelInfo awTvChannelInfo, int i) {
        insertAtvChannel(awTvChannelInfo, Integer.toString(i));
    }

    public ArrayList<AwTvChannelInfo> getATVChannelList(String str) throws Throwable {
        ArrayList<AwTvChannelInfo> arrayList = new ArrayList<>();
        ArrayList<AwTvChannelInfo> channelList = getChannelList(str, null, null, null);
        for (int i = 0; i <= channelList.size() - 1; i++) {
            AwTvChannelInfo awTvChannelInfo = channelList.get(i);
            if (awTvChannelInfo.isAnalogChannel()) {
                arrayList.add(awTvChannelInfo);
            }
        }
        return arrayList;
    }

    /* JADX WARN: Code duplicated, block: B:31:0x0127  */
    /* JADX WARN: Code duplicated, block: B:34:0x0142 A[LOOP:1: B:32:0x013c->B:34:0x0142, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:44:0x015c A[EDGE_INSN: B:44:0x015c->B:35:0x015c BREAK  A[LOOP:1: B:32:0x013c->B:34:0x0142], SYNTHETIC] */
    public int insertAtvChannelWithOrder(AwTvChannelInfo awTvChannelInfo) throws Throwable {
        int number;
        ArrayList<AwTvChannelInfo> aTVChannelList = getATVChannelList(awTvChannelInfo.getInputId());
        int size = 1;
        if (aTVChannelList.size() <= 0 || awTvChannelInfo.getFrequency() > aTVChannelList.get(aTVChannelList.size() - 1).getFrequency()) {
            size = 1 + aTVChannelList.size();
        } else {
            int i = 0;
            while (i < aTVChannelList.size()) {
                if (awTvChannelInfo.getFrequency() == aTVChannelList.get(i).getFrequency()) {
                    Log.d(TAG, "ATV CH[freq:" + awTvChannelInfo.getFrequency() + "] already exist");
                    if (awTvChannelInfo.getVideoStd() != aTVChannelList.get(i).getVideoStd() || awTvChannelInfo.getAudioStd() != aTVChannelList.get(i).getAudioStd() || awTvChannelInfo.getVfmt() != aTVChannelList.get(i).getVfmt()) {
                        Log.d(TAG, "Update ATV CH[freq:" + awTvChannelInfo.getFrequency() + "] video or audio std.");
                        aTVChannelList.get(i).setVideoStd(awTvChannelInfo.getVideoStd());
                        aTVChannelList.get(i).setAudioStd(awTvChannelInfo.getAudioStd());
                        aTVChannelList.get(i).setVfmt(awTvChannelInfo.getVfmt());
                        updateAtvChannel(aTVChannelList.get(i));
                    }
                    return aTVChannelList.size() + 1;
                }
                if (awTvChannelInfo.getFrequency() >= aTVChannelList.get(i).getFrequency()) {
                    i++;
                } else if (i != 0 || aTVChannelList.get(i).getNumber() <= 1) {
                    if (i > 0) {
                        int i2 = i - 1;
                        if (aTVChannelList.get(i).getNumber() - aTVChannelList.get(i2).getNumber() > 1) {
                            size = 1 + aTVChannelList.get(i2).getNumber();
                        } else {
                            int number2 = aTVChannelList.get(i).getNumber();
                            number = aTVChannelList.get(i).getNumber();
                            while (true) {
                                number++;
                                if (i < aTVChannelList.size()) {
                                    break;
                                }
                                aTVChannelList.get(i).setDisplayNumber(String.valueOf(number));
                                updateAtvChannel(aTVChannelList.get(i));
                                i++;
                            }
                            size = number2;
                        }
                    } else {
                        int number3 = aTVChannelList.get(i).getNumber();
                        number = aTVChannelList.get(i).getNumber();
                        while (true) {
                            number++;
                            if (i < aTVChannelList.size()) {
                                break;
                                break;
                            }
                            aTVChannelList.get(i).setDisplayNumber(String.valueOf(number));
                            updateAtvChannel(aTVChannelList.get(i));
                            i++;
                        }
                        size = number3;
                    }
                }
            }
            size = -1;
        }
        insertAtvChannel(awTvChannelInfo, size);
        return size;
    }

    /* JADX WARN: Code duplicated, block: B:44:0x012c  */
    /* JADX WARN: Code duplicated, block: B:45:0x012f  */
    /* JADX WARN: Code duplicated, block: B:55:0x00f0 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:59:? A[LOOP:0: B:5:0x0020->B:59:?, LOOP_END, SYNTHETIC] */
    private int updateAtvChannel(AwTvChannelInfo awTvChannelInfo) throws Throwable {
        Cursor cursorQuery;
        int count;
        String str;
        String[] strArr = {"_id", "type", "internal_provider_data"};
        int i = 0;
        try {
            cursorQuery = this.mContentResolver.query(TvContract.buildChannelsUriForInput(awTvChannelInfo.getInputId()), strArr, null, null, null);
            boolean z = false;
            while (cursorQuery != null) {
                try {
                    if (!cursorQuery.moveToNext()) {
                        break;
                    }
                    long j = cursorQuery.getLong(findPosition(strArr, "_id"));
                    Log.d(TAG, "updateAtvChannel rowID = " + j);
                    if (awTvChannelInfo.getId() == -1) {
                        Map<String, String> mapJsonToMap = AwTvUtils.jsonToMap(cursorQuery.getString(findPosition(strArr, "internal_provider_data")));
                        String string = cursorQuery.getString(findPosition(strArr, "type"));
                        if (Integer.parseInt(mapJsonToMap.get(AwTvChannelInfo.KEY_FREQUENCY)) == awTvChannelInfo.getFrequency()) {
                            if (string.equals(awTvChannelInfo.getType())) {
                                z = true;
                            } else {
                                Log.d(TAG, "atv standard type has changed from" + string + " to" + awTvChannelInfo.getType());
                                Uri uriBuildChannelUri = TvContract.buildChannelUri(j);
                                this.mContentResolver.delete(uriBuildChannelUri, "_id!=-1 and type='" + string + "'", null);
                                insertAtvChannel(awTvChannelInfo, awTvChannelInfo.getDisplayNumber());
                            }
                        }
                        if (Integer.parseInt(mapJsonToMap.get(AwTvChannelInfo.KEY_PROGRAM_NUMBER)) == awTvChannelInfo.getNumber()) {
                            z = true;
                        }
                        if (z) {
                            this.mContentResolver.update(TvContract.buildChannelUri(j), buildAtvChannelData(awTvChannelInfo), null, null);
                        }
                    } else {
                        if (j == awTvChannelInfo.getId()) {
                            z = true;
                        }
                        if (z) {
                            this.mContentResolver.update(TvContract.buildChannelUri(j), buildAtvChannelData(awTvChannelInfo), null, null);
                        }
                    }
                    i = -1;
                    break;
                } catch (Exception unused) {
                    count = i;
                    if (cursorQuery != null) {
                    }
                    StringBuilder sb = new StringBuilder();
                    sb.append("update ");
                    if (count == -1) {
                        str = "found";
                    } else {
                        str = "notfound";
                    }
                    sb.append(str);
                    sb.append(" ATV CH: [_id:");
                    sb.append(awTvChannelInfo.getId());
                    sb.append("][freq:");
                    sb.append(awTvChannelInfo.getFrequency());
                    sb.append("][name:");
                    sb.append(awTvChannelInfo.getDisplayName());
                    sb.append("][num:");
                    sb.append(awTvChannelInfo.getDisplayNumber());
                    sb.append("]");
                    Log.d(TAG, sb.toString());
                    return count;
                } catch (Throwable th) {
                    th = th;
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                    throw th;
                }
            }
            count = i != -1 ? cursorQuery.getCount() : i;
            if (cursorQuery != null) {
                cursorQuery.close();
            }
        } catch (Exception unused2) {
            count = 0;
            cursorQuery = null;
        } catch (Throwable th2) {
            th = th2;
            cursorQuery = null;
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append("update ");
        if (count == -1) {
            str = "found";
        } else {
            str = "notfound";
        }
        sb2.append(str);
        sb2.append(" ATV CH: [_id:");
        sb2.append(awTvChannelInfo.getId());
        sb2.append("][freq:");
        sb2.append(awTvChannelInfo.getFrequency());
        sb2.append("][name:");
        sb2.append(awTvChannelInfo.getDisplayName());
        sb2.append("][num:");
        sb2.append(awTvChannelInfo.getDisplayNumber());
        sb2.append("]");
        Log.d(TAG, sb2.toString());
        return count;
    }

    public int getChannelNumberByFreq(String str, int i) throws Throwable {
        ArrayList<AwTvChannelInfo> aTVChannelList = getATVChannelList(str);
        for (int i2 = 0; i2 < aTVChannelList.size(); i2++) {
            if (i == aTVChannelList.get(i2).getFrequency()) {
                return Integer.parseInt(aTVChannelList.get(i2).getDisplayNumber());
            }
        }
        return 1;
    }

    public void updateOrInsertAtvChannelWithNumber(AwTvChannelInfo awTvChannelInfo) {
        if (updateAtvChannel(awTvChannelInfo) != -1) {
            insertAtvChannel(awTvChannelInfo, awTvChannelInfo.getDisplayNumber());
        }
    }

    public void swapChannel(AwTvChannelInfo awTvChannelInfo, AwTvChannelInfo awTvChannelInfo2) {
        if (awTvChannelInfo == null || awTvChannelInfo2 == null || awTvChannelInfo.getNumber() == awTvChannelInfo2.getNumber()) {
            return;
        }
        ContentValues contentValues = new ContentValues();
        Uri uriBuildChannelUri = TvContract.buildChannelUri(awTvChannelInfo.getId());
        this.mContentResolver.update(uriBuildChannelUri, buildAtvChannelData(awTvChannelInfo2), null, null);
        contentValues.put("display_number", awTvChannelInfo.getDisplayNumber());
        this.mContentResolver.update(uriBuildChannelUri, contentValues, null, null);
        Uri uriBuildChannelUri2 = TvContract.buildChannelUri(awTvChannelInfo2.getId());
        this.mContentResolver.update(uriBuildChannelUri2, buildAtvChannelData(awTvChannelInfo), null, null);
        contentValues.put("display_number", awTvChannelInfo2.getDisplayNumber());
        this.mContentResolver.update(uriBuildChannelUri2, contentValues, null, null);
    }

    public void moveChannel(AwTvChannelInfo awTvChannelInfo, AwTvChannelInfo awTvChannelInfo2) throws Throwable {
        Cursor cursorQuery;
        if (awTvChannelInfo == null || awTvChannelInfo2 == null || awTvChannelInfo.getNumber() == awTvChannelInfo2.getNumber()) {
            return;
        }
        String[] strArr = {"_id", "display_number"};
        Cursor cursor = null;
        try {
            try {
                cursorQuery = this.mContentResolver.query(TvContract.buildChannelsUriForInput(awTvChannelInfo.getInputId()), strArr, "service_type=?", new String[]{awTvChannelInfo.getServiceType()}, null);
                while (cursorQuery != null) {
                    try {
                        if (!cursorQuery.moveToNext()) {
                            break;
                        }
                        long j = cursorQuery.getLong(findPosition(strArr, "_id"));
                        int i = Integer.parseInt(cursorQuery.getString(findPosition(strArr, "display_number")));
                        Uri uriBuildChannelUri = TvContract.buildChannelUri(j);
                        ContentValues contentValues = new ContentValues();
                        if (awTvChannelInfo2.getNumber() < awTvChannelInfo.getNumber()) {
                            if (i >= awTvChannelInfo2.getNumber() && i < awTvChannelInfo.getNumber()) {
                                contentValues.put("display_number", String.valueOf(i + 1));
                                this.mContentResolver.update(uriBuildChannelUri, contentValues, null, null);
                            }
                        } else if (awTvChannelInfo2.getNumber() > awTvChannelInfo.getNumber() && i > awTvChannelInfo.getNumber() && i <= awTvChannelInfo2.getNumber()) {
                            contentValues.put("display_number", String.valueOf(i - 1));
                            this.mContentResolver.update(uriBuildChannelUri, contentValues, null, null);
                        }
                        if (i == awTvChannelInfo.getNumber()) {
                            contentValues.put("display_number", Integer.valueOf(awTvChannelInfo2.getNumber()));
                            this.mContentResolver.update(uriBuildChannelUri, contentValues, null, null);
                        }
                    } catch (Exception e) {
                        e = e;
                        cursor = cursorQuery;
                        e.printStackTrace();
                        if (cursor != null) {
                            cursor.close();
                        }
                    } catch (Throwable th) {
                        th = th;
                        if (cursorQuery != null) {
                            cursorQuery.close();
                        }
                        throw th;
                    }
                }
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
            } catch (Throwable th2) {
                th = th2;
                cursorQuery = cursor;
            }
        } catch (Exception e2) {
            e = e2;
        }
    }

    public class SortFreqComparator implements Comparator<AwTvChannelInfo> {
        public SortFreqComparator() {
        }

        @Override // java.util.Comparator
        public int compare(AwTvChannelInfo awTvChannelInfo, AwTvChannelInfo awTvChannelInfo2) {
            if (awTvChannelInfo.getDisplayNumber() == null) {
                return -1;
            }
            return awTvChannelInfo.getFrequency() - awTvChannelInfo2.getFrequency();
        }
    }

    public class SortDisplayNumComparator implements Comparator<AwTvChannelInfo> {
        public SortDisplayNumComparator() {
        }

        @Override // java.util.Comparator
        public int compare(AwTvChannelInfo awTvChannelInfo, AwTvChannelInfo awTvChannelInfo2) {
            if (awTvChannelInfo.getDisplayNumber() == null) {
                return -1;
            }
            return awTvChannelInfo.getNumber() - awTvChannelInfo2.getNumber();
        }
    }
}
