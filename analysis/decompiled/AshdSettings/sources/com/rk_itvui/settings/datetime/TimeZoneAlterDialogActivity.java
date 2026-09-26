package com.rk_itvui.settings.datetime;

import android.R;
import android.app.AlarmManager;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import com.android.settingslib.accessibility.AccessibilityUtils;
import com.rk_itvui.settings.FullScreenAlertActivity;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes.dex */
public class TimeZoneAlterDialogActivity extends FullScreenAlertActivity {
    private static final int HOURS_1 = 3600000;
    private static final int HOURS_24 = 86400000;
    private static final String KEY_DISPLAYNAME = "name";
    private static final String KEY_GMT = "gmt";
    private static final String KEY_ID = "id";
    private static final String KEY_OFFSET = "offset";
    private static final String XMLTAG_TIMEZONE = "timezone";
    public static boolean isChangedZone;
    private SimpleAdapter mAlphabeticalAdapter;
    private int mDefault;
    private boolean mSortedByTimezone;
    private SimpleAdapter mTimezoneSortedAdapter;
    private ListView mListView = null;
    private AdapterView.OnItemClickListener mListItemClister = new AdapterView.OnItemClickListener() { // from class: com.rk_itvui.settings.datetime.TimeZoneAlterDialogActivity.1
        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            ((AlarmManager) TimeZoneAlterDialogActivity.this.getSystemService("alarm")).setTimeZone((String) ((Map) TimeZoneAlterDialogActivity.this.mListView.getItemAtPosition(i)).get(TimeZoneAlterDialogActivity.KEY_ID));
            TimeZoneAlterDialogActivity.this.setResult(-1);
            TimeZoneAlterDialogActivity.this.finish();
            TimeZoneAlterDialogActivity.isChangedZone = true;
        }
    };

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.rk_itvui.settings.FullScreenAlertActivity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        String[] strArr = {KEY_DISPLAYNAME, KEY_GMT};
        int[] iArr = {R.id.text1, R.id.text2};
        MyComparator myComparator = new MyComparator(KEY_OFFSET);
        List<HashMap> zones = getZones();
        Collections.sort(zones, myComparator);
        this.mTimezoneSortedAdapter = new SimpleAdapter(this, zones, com.ashd.settings.R.layout.my_simple_list_item_2, strArr, iArr);
        ArrayList arrayList = new ArrayList(zones);
        myComparator.setSortingKey(KEY_DISPLAYNAME);
        Collections.sort(arrayList, myComparator);
        this.mAlphabeticalAdapter = new SimpleAdapter(this, arrayList, com.ashd.settings.R.layout.my_simple_list_item_2, strArr, iArr);
        setSorting(true);
        this.mAlert.setView(this.mListView, -5, -5, -5, -5);
        setupAlert();
        hideSystemUI();
    }

    private void setSorting(boolean z) {
        this.mListView = (ListView) ((LayoutInflater) getSystemService("layout_inflater")).inflate(com.ashd.settings.R.layout.listview, (ViewGroup) null);
        this.mListView.setAdapter((ListAdapter) (z ? this.mTimezoneSortedAdapter : this.mAlphabeticalAdapter));
        this.mListView.setSelection(this.mDefault);
        this.mListView.setOnItemClickListener(this.mListItemClister);
        this.mSortedByTimezone = z;
    }

    protected void addItem(List<HashMap> list, String str, String str2, long j) {
        HashMap map = new HashMap();
        map.put(KEY_ID, str);
        map.put(KEY_DISPLAYNAME, str2);
        int offset = TimeZone.getTimeZone(str).getOffset(j);
        int iAbs = Math.abs(offset);
        StringBuilder sb = new StringBuilder();
        sb.append("GMT");
        if (offset < 0) {
            sb.append('-');
        } else {
            sb.append('+');
        }
        sb.append(iAbs / HOURS_1);
        sb.append(AccessibilityUtils.ENABLED_ACCESSIBILITY_SERVICES_SEPARATOR);
        int i = (iAbs / 60000) % 60;
        if (i < 10) {
            sb.append('0');
        }
        sb.append(i);
        map.put(KEY_GMT, sb.toString());
        map.put(KEY_OFFSET, Integer.valueOf(offset));
        if (str.equals(TimeZone.getDefault().getID())) {
            this.mDefault = list.size();
        }
        Log.e("TimeZone", map.toString());
        list.add(map);
    }

    private List<HashMap> getZones() {
        ArrayList arrayList = new ArrayList();
        long timeInMillis = Calendar.getInstance().getTimeInMillis();
        try {
            XmlResourceParser xml = getResources().getXml(com.ashd.settings.R.xml.timezones);
            while (xml.next() != 2) {
            }
            xml.next();
            while (xml.getEventType() != 3) {
                while (xml.getEventType() != 2) {
                    if (xml.getEventType() == 1) {
                        return arrayList;
                    }
                    xml.next();
                }
                if (xml.getName().equals(XMLTAG_TIMEZONE)) {
                    addItem(arrayList, xml.getAttributeValue(0), xml.nextText(), timeInMillis);
                }
                while (xml.getEventType() != 3) {
                    xml.next();
                }
                xml.next();
            }
            xml.close();
        } catch (IOException unused) {
            LOGD("Unable to read timezones.xml file");
        } catch (XmlPullParserException unused2) {
            LOGD("Ill-formatted timezones.xml file");
        }
        return arrayList;
    }

    private static class MyComparator implements Comparator<HashMap> {
        private String mSortingKey;

        public MyComparator(String str) {
            this.mSortingKey = str;
        }

        public void setSortingKey(String str) {
            this.mSortingKey = str;
        }

        @Override // java.util.Comparator
        public int compare(HashMap map, HashMap map2) {
            Object obj = map.get(this.mSortingKey);
            Object obj2 = map2.get(this.mSortingKey);
            if (!isComparable(obj)) {
                return isComparable(obj2) ? 1 : 0;
            }
            if (isComparable(obj2)) {
                return ((Comparable) obj).compareTo(obj2);
            }
            return -1;
        }

        private boolean isComparable(Object obj) {
            return obj != null && (obj instanceof Comparable);
        }
    }

    private void LOGD(String str) {
        Log.d("TimeZoneAlterDialogActivity", str);
    }
}
