package com.rk_itvui.settings.datetime;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import com.android.settingslib.accessibility.AccessibilityUtils;
import com.ashd.settings.R;
import com.rk_itvui.settings.BuildConfig;
import com.rk_itvui.settings.FullScreenPreferenceActivity;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.TimeZone;

/* JADX INFO: loaded from: classes.dex */
public class DateTimeSetting extends FullScreenPreferenceActivity implements AdapterView.OnItemClickListener {
    private static final int DATE_DIALOG_ID = 1;
    private static final int TIME_DIALOG_ID = 3;
    private static final int TryTimes = 5;
    private static final String[] date_format_sring = {"2013-12-31", "31-12-2013", "12-31-2013"};
    private int current_date_day;
    private int current_date_month;
    private int current_date_year;
    private int current_time_hour;
    private int current_time_minute;
    private String current_timezone;
    private boolean is24HourFormat;
    private boolean isAutoDateTime;
    private int[] return_time;
    String strTimeFormat;
    long sysTime;
    ArrayList<HashMap<String, Object>> listItem = new ArrayList<>();
    HashMap<String, Object> map_DateTimeAuto = new HashMap<>();
    HashMap<String, Object> map_DateSet = new HashMap<>();
    HashMap<String, Object> map_TimeSet = new HashMap<>();
    HashMap<String, Object> map_TimeZoneSet = new HashMap<>();
    HashMap<String, Object> map_24HourFormat = new HashMap<>();
    HashMap<String, Object> map_DateFormat = new HashMap<>();
    RemindViewAdapter listItemAdapter = null;
    ListView list = null;
    Calendar calendar = null;
    private String date_format = null;
    private char date_format_click_time = 0;
    private boolean isListNotReady = true;
    private TextView tvTime = null;
    private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() { // from class: com.rk_itvui.settings.datetime.DateTimeSetting.1
        @Override // android.app.DatePickerDialog.OnDateSetListener
        public void onDateSet(DatePicker datePicker, int i, int i2, int i3) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(1, i);
            calendar.set(2, i2);
            calendar.set(5, i3);
            ((AlarmManager) DateTimeSetting.this.getSystemService("alarm")).setTime(calendar.getTimeInMillis());
            DateTimeSetting.this.current_date_year = calendar.get(1);
            DateTimeSetting.this.current_date_month = calendar.get(2);
            DateTimeSetting.this.current_date_day = calendar.get(5);
            DateTimeSetting.this.return_time[0] = DateTimeSetting.this.current_date_year;
            DateTimeSetting.this.return_time[1] = DateTimeSetting.this.current_date_month;
            DateTimeSetting.this.return_time[2] = DateTimeSetting.this.current_date_day;
            DateTimeSetting.this.map_DateSet.put("DateTimeStatus", Integer.toString(DateTimeSetting.this.current_date_year) + "-" + Integer.toString(DateTimeSetting.this.current_date_month + 1) + "-" + Integer.toString(DateTimeSetting.this.current_date_day));
            DateTimeSetting.this.listItemAdapter.notifyDataSetChanged();
            DateTimeSetting.this.calendar.set(DateTimeSetting.this.current_date_year, DateTimeSetting.this.current_date_month, DateTimeSetting.this.current_date_day);
        }
    };
    private TimePickerDialog.OnTimeSetListener mTimeSetListener = new TimePickerDialog.OnTimeSetListener() { // from class: com.rk_itvui.settings.datetime.DateTimeSetting.2
        @Override // android.app.TimePickerDialog.OnTimeSetListener
        public void onTimeSet(TimePicker timePicker, int i, int i2) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(11, i);
            calendar.set(12, i2);
            ((AlarmManager) DateTimeSetting.this.getSystemService("alarm")).setTime(calendar.getTimeInMillis());
            DateTimeSetting.this.current_time_hour = calendar.get(11);
            DateTimeSetting.this.current_time_minute = calendar.get(12);
            DateTimeSetting.this.return_time[3] = DateTimeSetting.this.current_time_hour;
            DateTimeSetting.this.return_time[4] = DateTimeSetting.this.current_time_minute;
            DateTimeSetting.this.UpdateTimeDispaly(DateTimeSetting.this.is24HourFormat);
            DateTimeSetting.this.listItemAdapter.notifyDataSetChanged();
            DateTimeSetting.this.calendar.set(1, 2, 5, DateTimeSetting.this.current_time_hour, DateTimeSetting.this.current_time_minute, 0);
        }
    };
    private Handler mHandler = new Handler() { // from class: com.rk_itvui.settings.datetime.DateTimeSetting.3
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            switch (message.what) {
                case 1:
                    Calendar calendar = Calendar.getInstance();
                    switch (DateTimeSetting.this.date_format_click_time) {
                        case 0:
                            if (DateTimeSetting.this.is24HourFormat) {
                                DateTimeSetting.this.sysTime = System.currentTimeMillis();
                                DateTimeSetting.this.tvTime.setText(DateFormat.format("yyyy-MM-dd HH:mm:ss", DateTimeSetting.this.sysTime));
                            } else {
                                DateTimeSetting.this.sysTime = System.currentTimeMillis();
                                DateTimeSetting.this.tvTime.setText(DateFormat.format("yyyy-MM-dd hh:mm:ss aaa", DateTimeSetting.this.sysTime));
                            }
                            break;
                        case 1:
                            if (DateTimeSetting.this.is24HourFormat) {
                                DateTimeSetting.this.sysTime = System.currentTimeMillis();
                                DateTimeSetting.this.tvTime.setText(DateFormat.format("dd-MM-yyyy HH:mm:ss", DateTimeSetting.this.sysTime));
                            } else {
                                DateTimeSetting.this.sysTime = System.currentTimeMillis();
                                DateTimeSetting.this.tvTime.setText(DateFormat.format("dd-MM-yyyy hh:mm:ss aaa", DateTimeSetting.this.sysTime));
                            }
                            break;
                        case 2:
                            if (DateTimeSetting.this.is24HourFormat) {
                                DateTimeSetting.this.sysTime = System.currentTimeMillis();
                                DateTimeSetting.this.tvTime.setText(DateFormat.format("MM-dd-yyyy HH:mm:ss", DateTimeSetting.this.sysTime));
                            } else {
                                DateTimeSetting.this.sysTime = System.currentTimeMillis();
                                DateTimeSetting.this.tvTime.setText(DateFormat.format("MM-dd-yyyy hh:mm:ss aaa", DateTimeSetting.this.sysTime));
                            }
                            break;
                    }
                    DateTimeSetting.this.current_date_year = calendar.get(1);
                    DateTimeSetting.this.current_date_month = calendar.get(2);
                    DateTimeSetting.this.current_date_day = calendar.get(5);
                    DateTimeSetting.this.current_time_hour = calendar.get(11);
                    DateTimeSetting.this.current_time_minute = calendar.get(12);
                    DateTimeSetting.this.UpdateTimeDispaly(DateTimeSetting.this.is24HourFormat);
                    DateTimeSetting.this.listItemAdapter.notifyDataSetChanged();
                    break;
                case 2:
                    DateTimeSetting.this.current_date_year = DateTimeSetting.this.calendar.get(1);
                    DateTimeSetting.this.current_date_month = DateTimeSetting.this.calendar.get(2);
                    DateTimeSetting.this.current_date_day = DateTimeSetting.this.calendar.get(5);
                    DateTimeSetting.this.current_time_hour = DateTimeSetting.this.calendar.get(11);
                    DateTimeSetting.this.current_time_minute = DateTimeSetting.this.calendar.get(12);
                    DateTimeSetting.this.UpdateTimeDispaly(DateTimeSetting.this.is24HourFormat);
                    DateTimeSetting.this.listItemAdapter.notifyDataSetChanged();
                    break;
                case 3:
                    if (DateTimeSetting.this.isListNotReady) {
                        DateTimeSetting.this.isListNotReady = false;
                        DateTimeSetting.this.list.getChildAt(4);
                        DateTimeSetting.this.list.getChildAt(5);
                        DateTimeSetting.this.UpdateTimeDispaly(DateTimeSetting.this.is24HourFormat);
                        DateTimeSetting.this.listItemAdapter.notifyDataSetChanged();
                        boolean unused = DateTimeSetting.this.isAutoDateTime;
                        switch (DateTimeSetting.this.date_format_click_time) {
                            case 0:
                                if (DateTimeSetting.this.is24HourFormat) {
                                    DateTimeSetting.this.sysTime = System.currentTimeMillis();
                                    DateTimeSetting.this.tvTime.setText(DateFormat.format("yyyy-MM-dd HH:mm:ss", DateTimeSetting.this.sysTime));
                                } else {
                                    DateTimeSetting.this.sysTime = System.currentTimeMillis();
                                    DateTimeSetting.this.tvTime.setText(DateFormat.format("yyyy-MM-dd hh:mm:ss aaa", DateTimeSetting.this.sysTime));
                                }
                                break;
                            case 1:
                                if (DateTimeSetting.this.is24HourFormat) {
                                    DateTimeSetting.this.sysTime = System.currentTimeMillis();
                                    DateTimeSetting.this.tvTime.setText(DateFormat.format("dd-MM-yyyy HH:mm:ss", DateTimeSetting.this.sysTime));
                                } else {
                                    DateTimeSetting.this.sysTime = System.currentTimeMillis();
                                    DateTimeSetting.this.tvTime.setText(DateFormat.format("dd-MM-yyyy hh:mm:ss aaa", DateTimeSetting.this.sysTime));
                                }
                                break;
                            case 2:
                                if (DateTimeSetting.this.is24HourFormat) {
                                    DateTimeSetting.this.sysTime = System.currentTimeMillis();
                                    DateTimeSetting.this.tvTime.setText(DateFormat.format("MM-dd-yyyy HH:mm:ss", DateTimeSetting.this.sysTime));
                                } else {
                                    DateTimeSetting.this.sysTime = System.currentTimeMillis();
                                    DateTimeSetting.this.tvTime.setText(DateFormat.format("MM-dd-yyyy hh:mm:ss aaa", DateTimeSetting.this.sysTime));
                                }
                                break;
                        }
                    }
                    break;
                case 4:
                    if (DateTimeSetting.this.isAutoDateTime && !DateTimeSetting.this.GetTimeFromNetTryTimes(5)) {
                        DateTimeSetting.this.current_date_year = DateTimeSetting.this.calendar.get(1);
                        DateTimeSetting.this.current_date_month = DateTimeSetting.this.calendar.get(2);
                        DateTimeSetting.this.current_date_day = DateTimeSetting.this.calendar.get(5);
                        DateTimeSetting.this.current_time_hour = DateTimeSetting.this.calendar.get(11);
                        DateTimeSetting.this.current_time_minute = DateTimeSetting.this.calendar.get(12);
                    }
                    DateTimeSetting.this.UpdateTimeDispaly(DateTimeSetting.this.is24HourFormat);
                    DateTimeSetting.this.listItemAdapter.notifyDataSetChanged();
                    break;
                case 5:
                    DateTimeSetting.this.current_date_year = DateTimeSetting.this.return_time[0];
                    DateTimeSetting.this.current_date_month = DateTimeSetting.this.return_time[1];
                    DateTimeSetting.this.current_date_day = DateTimeSetting.this.return_time[2];
                    DateTimeSetting.this.current_time_hour = DateTimeSetting.this.return_time[3];
                    DateTimeSetting.this.current_time_minute = DateTimeSetting.this.return_time[4];
                    DateTimeSetting.this.UpdateTimeDispaly(DateTimeSetting.this.is24HourFormat);
                    DateTimeSetting.this.listItemAdapter.notifyDataSetChanged();
                    DateTimeSetting.this.calendar.set(DateTimeSetting.this.current_date_year, DateTimeSetting.this.current_date_month, DateTimeSetting.this.current_date_day, DateTimeSetting.this.current_time_hour, DateTimeSetting.this.current_time_minute);
                    ((AlarmManager) DateTimeSetting.this.getSystemService("alarm")).setTime(DateTimeSetting.this.calendar.getTimeInMillis());
                    break;
            }
        }
    };

    @Override // com.rk_itvui.settings.FullScreenPreferenceActivity, android.preference.PreferenceActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.date_time_setting);
        if (BuildConfig.FLAVOR.contains(BuildConfig.FLAVOR)) {
            TextView textView = (TextView) findViewById(R.id.app_text);
            TextView textView2 = (TextView) findViewById(R.id.settings_line);
            textView.setCompoundDrawablesWithIntrinsicBounds(getDrawable(R.drawable.icon_ashd2), (Drawable) null, (Drawable) null, (Drawable) null);
            textView2.setBackgroundResource(R.drawable.settings_head_line_ashd2);
        }
        addListView();
        this.tvTime = (TextView) findViewById(R.id.mytime);
        new TimeThread().start();
        new UpdateThread().start();
        new BindListThread().start();
    }

    public void addListView() {
        ContentResolver contentResolver = getContentResolver();
        this.calendar = Calendar.getInstance();
        this.isAutoDateTime = getAutoState();
        this.date_format = getDateFormat();
        if (!this.isAutoDateTime || !GetTimeFromNetTryTimes(5)) {
            this.current_date_year = this.calendar.get(1);
            this.current_date_month = this.calendar.get(2);
            this.current_date_day = this.calendar.get(5);
            this.current_time_hour = this.calendar.get(11);
            this.current_time_minute = this.calendar.get(12);
        }
        this.return_time = new int[5];
        this.return_time[0] = this.current_date_year;
        this.return_time[1] = this.current_date_month;
        this.return_time[2] = this.current_date_day;
        this.return_time[3] = this.current_time_hour;
        this.return_time[4] = this.current_time_minute;
        this.current_timezone = getTimeZoneText();
        this.is24HourFormat = this.calendar.isLenient();
        this.map_DateTimeAuto.put("DateTimeItem", getString(R.string.date_time_auto));
        this.map_DateTimeAuto.put("DateTimeStatus", getString(this.isAutoDateTime ? R.string.auto_time : R.string.not_auto_time));
        this.map_DateSet.put("DateTimeItem", getString(R.string.date_time_set_date));
        this.map_TimeSet.put("DateTimeItem", getString(R.string.date_time_set_time));
        this.map_TimeZoneSet.put("DateTimeItem", getString(R.string.date_time_set_timezone));
        this.map_TimeZoneSet.put("DateTimeStatus", this.current_timezone);
        this.map_24HourFormat.put("DateTimeItem", getString(R.string.date_time_24hour));
        try {
            this.strTimeFormat = Settings.System.getString(contentResolver, "time_12_24");
            if (this.strTimeFormat.equals("24")) {
                Log.i("activity", "24");
                this.is24HourFormat = true;
                UpdateTimeDispaly(true);
            } else if (this.strTimeFormat.equals("12")) {
                this.is24HourFormat = false;
                UpdateTimeDispaly(false);
            }
        } catch (Exception unused) {
            Settings.System.putString(getContentResolver(), "time_12_24", "24");
            this.map_24HourFormat.put("DateTimeStatus", getString(R.string.hour24));
        }
        if (this.date_format != null) {
            switch (this.date_format) {
                case "yyyy-MM-dd":
                    this.date_format_click_time = (char) 0;
                    this.map_DateSet.put("DateTimeStatus", this.current_date_year + "-" + (this.current_date_month + 1) + "-" + this.current_date_day);
                    break;
                case "dd-MM-yyyy":
                    this.date_format_click_time = (char) 1;
                    this.map_DateSet.put("DateTimeStatus", this.current_date_day + "-" + (this.current_date_month + 1) + "-" + this.current_date_year);
                    break;
                case "MM-dd-yyyy":
                    this.date_format_click_time = (char) 2;
                    this.map_DateSet.put("DateTimeStatus", (this.current_date_month + 1) + "-" + this.current_date_day + "-" + this.current_date_year);
                    break;
            }
        } else {
            this.date_format = "yyyy-MM-dd";
            this.date_format_click_time = (char) 0;
            this.map_DateSet.put("DateTimeStatus", this.current_date_year + "-" + (this.current_date_month + 1) + "-" + this.current_date_day);
        }
        this.map_DateFormat.put("DateTimeItem", getString(R.string.choose_date_format));
        this.map_DateFormat.put("DateTimeStatus", date_format_sring[this.date_format_click_time]);
        this.listItem.add(this.map_TimeZoneSet);
        this.listItem.add(this.map_24HourFormat);
        this.listItem.add(this.map_DateFormat);
        this.listItem.add(this.map_DateTimeAuto);
        this.listItem.add(this.map_DateSet);
        this.listItem.add(this.map_TimeSet);
        this.list = (ListView) findViewById(R.id.datetime_list);
        this.listItemAdapter = new RemindViewAdapter(this, this.listItem, R.layout.date_time_item, new String[]{"DateTimeItem", "DateTimeStatus"}, new int[]{R.id.DateTimeItem, R.id.DateTimeStatus});
        this.list.setAdapter((ListAdapter) this.listItemAdapter);
        this.list.setOnItemClickListener(this);
    }

    @Override // android.app.Activity
    public void onResume() {
        Log.d("DateTimeSetting", "=========Activity:onResume");
        this.current_date_year = this.calendar.get(1);
        this.current_date_month = this.calendar.get(2);
        this.current_date_day = this.calendar.get(5);
        this.current_time_hour = this.calendar.get(11);
        this.current_time_minute = this.calendar.get(12);
        this.current_timezone = getTimeZoneText();
        Log.d("DateTimeSetting", this.current_timezone);
        this.map_TimeZoneSet.put("DateTimeStatus", this.current_timezone);
        UpdateTimeDispaly(this.is24HourFormat);
        this.listItemAdapter.notifyDataSetChanged();
        Message message = new Message();
        message.what = 4;
        this.mHandler.sendMessage(message);
        super.onResume();
    }

    @Override // android.preference.PreferenceActivity, android.app.ListActivity, android.app.Activity
    protected void onDestroy() {
        Log.d("DateTimeSetting", "=============================Activity:onDestroy");
        super.onDestroy();
    }

    @Override // android.app.Activity
    protected void onPause() {
        Log.d("DateTimeSetting", "=============================Activity:onPause");
        super.onPause();
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        switch (i) {
            case 0:
                startActivityForResult(new Intent(this, (Class<?>) TimeZoneAlterDialogActivity.class), 0);
                break;
            case 1:
                if (this.is24HourFormat) {
                    this.is24HourFormat = false;
                    UpdateTimeDispaly(false);
                } else {
                    UpdateTimeDispaly(true);
                    this.is24HourFormat = true;
                }
                this.listItemAdapter.notifyDataSetChanged();
                break;
            case 2:
                char c = (char) (this.date_format_click_time + 1);
                this.date_format_click_time = c;
                if (c > 2) {
                    this.date_format_click_time = (char) 0;
                }
                switch (this.date_format_click_time) {
                    case 0:
                        this.date_format = "yyyy-MM-dd";
                        break;
                    case 1:
                        this.date_format = "dd-MM-yyyy";
                        break;
                    case 2:
                        this.date_format = "MM-dd-yyyy";
                        break;
                }
                setDateFormat(this.date_format);
                this.map_DateFormat.put("DateTimeStatus", date_format_sring[this.date_format_click_time]);
                UpdateTimeDispaly(this.is24HourFormat);
                this.listItemAdapter.notifyDataSetChanged();
                break;
            case 3:
                this.list.getChildAt(4);
                this.list.getChildAt(5);
                if (this.isAutoDateTime) {
                    this.isAutoDateTime = false;
                    this.map_DateTimeAuto.put("DateTimeStatus", getString(R.string.not_auto_time));
                    Message message = new Message();
                    message.what = 5;
                    this.mHandler.sendMessage(message);
                    Log.d("smj", "=====================" + Integer.toString(this.current_date_year) + "-" + (this.current_date_month + 1) + "-" + this.current_date_day + Integer.toString(this.current_time_hour) + Integer.toString(this.current_time_minute));
                } else {
                    this.isAutoDateTime = true;
                    this.map_DateTimeAuto.put("DateTimeStatus", getString(R.string.auto_time));
                    if (!GetTimeFromNetTryTimes(5)) {
                        this.current_date_year = this.calendar.get(1);
                        this.current_date_month = this.calendar.get(2);
                        this.current_date_day = this.calendar.get(5);
                        this.current_time_hour = this.calendar.get(11);
                        this.current_time_minute = this.calendar.get(12);
                    }
                    UpdateTimeDispaly(this.is24HourFormat);
                    Log.d("smj", "=====================" + Integer.toString(this.current_date_year) + "-" + Integer.toString(this.current_date_month + 1) + "-" + Integer.toString(this.current_date_day) + Integer.toString(this.current_time_hour) + Integer.toString(this.current_time_minute));
                }
                this.listItemAdapter.notifyDataSetChanged();
                Settings.Global.putInt(getContentResolver(), "auto_time", this.isAutoDateTime ? 1 : 0);
                break;
            case 4:
                if (!this.isAutoDateTime) {
                    Calendar calendar = Calendar.getInstance();
                    DatePickerDialog datePickerDialog = new DatePickerDialog(this, this.mDateSetListener, calendar.get(1), calendar.get(2), calendar.get(5));
                    datePickerDialog.show();
                    datePickerDialog.updateDate(calendar.get(1), calendar.get(2), calendar.get(5));
                }
                break;
            case 5:
                if (!this.isAutoDateTime) {
                    Calendar calendar2 = Calendar.getInstance();
                    TimePickerDialog timePickerDialog = new TimePickerDialog(this, this.mTimeSetListener, calendar2.get(11), calendar2.get(12), DateFormat.is24HourFormat(this));
                    timePickerDialog.setTitle(getResources().getString(R.string.date_time_changeTime_text));
                    timePickerDialog.show();
                    timePickerDialog.updateTime(calendar2.get(11), calendar2.get(12));
                }
                break;
        }
    }

    @Override // android.preference.PreferenceActivity, android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        UpdateTimeDispaly(this.is24HourFormat);
        this.listItemAdapter.notifyDataSetChanged();
    }

    private CharSequence getPeroidText() {
        Settings.System.putString(getContentResolver(), "time_12_24", "24");
        String str = Calendar.getInstance().get(11) >= 12 ? "PM" : "AM";
        Settings.System.putString(getContentResolver(), "time_12_24", this.is24HourFormat ? "24" : "12");
        return str;
    }

    private String getTimeZoneText() {
        TimeZone timeZone = Calendar.getInstance().getTimeZone();
        boolean zInDaylightTime = timeZone.inDaylightTime(new Date());
        StringBuilder sb = new StringBuilder();
        sb.append(formatOffset(timeZone.getRawOffset() + (zInDaylightTime ? timeZone.getDSTSavings() : 0)));
        sb.append(", ");
        sb.append(timeZone.getDisplayName(zInDaylightTime, 1));
        return sb.toString();
    }

    private char[] formatOffset(int i) {
        int i2 = (i / 1000) / 60;
        char[] cArr = new char[9];
        cArr[0] = 'G';
        cArr[1] = 'M';
        cArr[2] = 'T';
        if (i2 < 0) {
            cArr[3] = '-';
            i2 = -i2;
        } else {
            cArr[3] = '+';
        }
        int i3 = i2 / 60;
        int i4 = i2 % 60;
        cArr[4] = (char) ((i3 / 10) + 48);
        cArr[5] = (char) ((i3 % 10) + 48);
        cArr[6] = AccessibilityUtils.ENABLED_ACCESSIBILITY_SERVICES_SEPARATOR;
        cArr[7] = (char) ((i4 / 10) + 48);
        cArr[8] = (char) ((i4 % 10) + 48);
        return cArr;
    }

    @Override // android.app.Activity
    protected void onPrepareDialog(int i, Dialog dialog) {
        if (i == 1) {
            ((DatePickerDialog) dialog).updateDate(1, 2, 5);
            this.calendar.set(1, 2, 5);
        } else {
            if (i != 3) {
                return;
            }
            ((TimePickerDialog) dialog).updateTime(11, 12);
            this.calendar.set(1, 2, 5, 11, 12, 0);
        }
    }

    private String getDateFormat() {
        return Settings.System.getString(getContentResolver(), "date_format");
    }

    private void setDateFormat(String str) {
        if (str.length() == 0) {
            str = null;
        }
        Settings.System.putString(getContentResolver(), "date_format", str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void UpdateTimeDispaly(boolean z) {
        switch (this.date_format_click_time) {
            case 0:
                this.map_DateSet.put("DateTimeStatus", Integer.toString(this.current_date_year) + "-" + Integer.toString(this.current_date_month + 1) + "-" + Integer.toString(this.current_date_day));
                break;
            case 1:
                this.map_DateSet.put("DateTimeStatus", Integer.toString(this.current_date_day) + "-" + Integer.toString(this.current_date_month + 1) + "-" + Integer.toString(this.current_date_year));
                break;
            case 2:
                this.map_DateSet.put("DateTimeStatus", Integer.toString(this.current_date_month + 1) + "-" + Integer.toString(this.current_date_day) + "-" + Integer.toString(this.current_date_year));
                break;
        }
        if (z) {
            Settings.System.putString(getContentResolver(), "time_12_24", "24");
            this.map_24HourFormat.put("DateTimeStatus", getString(R.string.hour24));
            if (this.current_time_minute < 10) {
                this.map_TimeSet.put("DateTimeStatus", Integer.toString(this.current_time_hour) + ":0" + Integer.toString(this.current_time_minute));
                return;
            }
            this.map_TimeSet.put("DateTimeStatus", Integer.toString(this.current_time_hour) + ":" + Integer.toString(this.current_time_minute));
            return;
        }
        Settings.System.putString(getContentResolver(), "time_12_24", "12");
        this.map_24HourFormat.put("DateTimeStatus", getString(R.string.hour12));
        if (this.current_time_minute < 10) {
            HashMap<String, Object> map = this.map_TimeSet;
            StringBuilder sb = new StringBuilder();
            sb.append((Object) getPeroidText());
            sb.append("  ");
            sb.append(Integer.toString(this.current_time_hour > 12 ? this.current_time_hour - 12 : this.current_time_hour));
            sb.append(":0");
            sb.append(Integer.toString(this.current_time_minute));
            map.put("DateTimeStatus", sb.toString());
            return;
        }
        HashMap<String, Object> map2 = this.map_TimeSet;
        StringBuilder sb2 = new StringBuilder();
        sb2.append((Object) getPeroidText());
        sb2.append("  ");
        sb2.append(Integer.toString(this.current_time_hour > 12 ? this.current_time_hour - 12 : this.current_time_hour));
        sb2.append(":");
        sb2.append(Integer.toString(this.current_time_minute));
        map2.put("DateTimeStatus", sb2.toString());
    }

    private boolean getAutoState() {
        try {
            if (Build.VERSION.SDK_INT > 16) {
                return Settings.Global.getInt(getContentResolver(), "auto_time") > 0;
            }
            return Settings.System.getInt(getContentResolver(), "auto_time") > 0;
        } catch (Settings.SettingNotFoundException unused) {
            return true;
        }
    }

    protected boolean TryGetTimeFromNET() {
        try {
            URLConnection uRLConnectionOpenConnection = new URL("http://www.baidu.com").openConnection();
            uRLConnectionOpenConnection.connect();
            Date date = new Date(uRLConnectionOpenConnection.getDate());
            this.current_date_year = date.getYear();
            this.current_date_month = date.getMonth();
            this.current_date_day = date.getDay();
            this.current_time_hour = date.getHours();
            this.current_time_minute = date.getMinutes();
            this.calendar.set(this.current_date_year, this.current_date_month, this.current_date_day, this.current_time_hour, this.current_time_minute);
            ((AlarmManager) getSystemService("alarm")).setTime(this.calendar.getTimeInMillis());
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean GetTimeFromNetTryTimes(int i) {
        for (int i2 = 0; i2 < i; i2++) {
            if (TryGetTimeFromNET()) {
                return true;
            }
        }
        return false;
    }

    class TimeThread extends Thread {
        TimeThread() {
        }

        /* JADX INFO: Infinite loop detected, blocks: 9, insns: 0 */
        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            while (true) {
                try {
                    Thread.sleep(1000L);
                    Message message = new Message();
                    message.what = 1;
                    DateTimeSetting.this.mHandler.sendMessage(message);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class UpdateThread extends Thread {
        UpdateThread() {
        }

        /* JADX INFO: Infinite loop detected, blocks: 9, insns: 0 */
        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            while (true) {
                try {
                    Thread.sleep(60000L);
                    Message message = new Message();
                    message.what = 2;
                    DateTimeSetting.this.mHandler.sendMessage(message);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class BindListThread extends Thread {
        BindListThread() {
        }

        /* JADX INFO: Infinite loop detected, blocks: 9, insns: 0 */
        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            while (true) {
                try {
                    Thread.sleep(500L);
                    Message message = new Message();
                    message.what = 3;
                    DateTimeSetting.this.mHandler.sendMessage(message);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (keyEvent.getAction() == 1) {
            return super.dispatchKeyEvent(keyEvent);
        }
        int selectedItemPosition = this.list.getSelectedItemPosition();
        if (keyEvent.getKeyCode() == 19) {
            if (selectedItemPosition == 0) {
                this.list.setSelection(this.list.getChildCount() - 1);
                return true;
            }
        } else if (keyEvent.getKeyCode() == 20 && selectedItemPosition == this.list.getChildCount() - 1) {
            this.list.setSelection(0);
            return true;
        }
        return super.dispatchKeyEvent(keyEvent);
    }
}
