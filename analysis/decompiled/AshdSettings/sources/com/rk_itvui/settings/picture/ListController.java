package com.rk_itvui.settings.picture;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import com.ashd.settings.R;
import com.rk_itvui.settings.picture.model.SettingItem;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class ListController implements AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener {
    protected static String TAG = "ListController";
    protected CallBackListener callBackListener;
    protected ListController mChild;
    protected Context mContext;
    protected int mCurrentItemPos;
    protected Button mCurrentLeftButton;
    protected TextView mCurrentNameTextView;
    protected Button mCurrentRightButton;
    protected TextView mCurrentTextView;
    protected boolean mDefaultInit;
    public SettingItem mFocusedSetting;
    protected ListController mParent;
    int mSelectedPosition;
    protected ArrayList<SettingItem> mSettings;
    protected boolean mValueAdjustable;
    protected ListAdapter myAdapter;

    public interface CallBackListener {
        void updateListController(ListController listController);

        void updateListPosition(int i);

        void updateTvView(boolean z, String str, String str2, String str3);
    }

    protected int getSettingIntValue(String str) {
        return 0;
    }

    protected String getSettingValue(String str) {
        return ConfigManager.DEFAULT_VALUE;
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    protected void onPause() {
    }

    protected void onResume() {
    }

    protected void specialInit(SettingItem settingItem) {
    }

    protected int updateSettingValue(String str, int i) {
        return 0;
    }

    public ListController() {
        this.mCurrentItemPos = 0;
        this.mDefaultInit = false;
        this.mValueAdjustable = true;
        this.mSelectedPosition = 0;
    }

    public ListController(Context context) {
        this.mCurrentItemPos = 0;
        this.mDefaultInit = false;
        this.mValueAdjustable = true;
        this.mSelectedPosition = 0;
        this.mContext = context;
        this.mSettings = initSettings();
    }

    public void init(Context context, ListAdapter listAdapter, ListController listController) {
        this.mContext = context;
        this.myAdapter = listAdapter;
        this.mParent = listController;
        this.mSettings = initSettings();
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
        Log.d(TAG, "onItemSelected pos:" + i);
        this.mFocusedSetting = this.mSettings.get(i);
        this.mCurrentItemPos = i;
        if (view == null) {
            Log.e(TAG, "view is null, error selected");
            return;
        }
        if (this.mCurrentNameTextView != null) {
            this.mCurrentNameTextView.setTextColor(Color.parseColor("#FFFFFF"));
        }
        if (this.mCurrentTextView != null) {
            this.mCurrentTextView.setTextColor(Color.parseColor("#FFFFFF"));
        }
        this.mCurrentNameTextView = (TextView) view.findViewById(R.id.nameText);
        this.mCurrentTextView = (TextView) view.findViewById(R.id.valueText);
        this.mCurrentLeftButton = (Button) view.findViewById(R.id.leftButton);
        this.mCurrentRightButton = (Button) view.findViewById(R.id.rightButton);
        this.mCurrentNameTextView.setTextColor(Color.parseColor("#1E74FE"));
        this.mCurrentTextView.setTextColor(Color.parseColor("#1E74FE"));
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        Log.d(TAG, "onItemClick pos:" + i);
        this.mFocusedSetting = this.mSettings.get(i);
        this.mCurrentItemPos = i;
        this.mCurrentTextView = (TextView) view.findViewById(R.id.valueText);
        this.mCurrentLeftButton = (Button) view.findViewById(R.id.leftButton);
        this.mCurrentRightButton = (Button) view.findViewById(R.id.rightButton);
        if (this.mFocusedSetting.getType() == 2) {
            String className = this.mFocusedSetting.getClassName();
            Log.d(TAG, "onItemClick TYPE_SUB_MENU loading " + className);
            try {
                this.mChild = (ListController) Class.forName(className).newInstance();
                this.mChild.init(this.mContext, this.myAdapter, this);
                this.mChild.setCallBackListener(this.callBackListener);
                this.callBackListener.updateListController(this.mChild);
                this.callBackListener.updateListPosition(0);
                onPause();
                this.mChild.onResume();
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (this.mFocusedSetting == null) {
            Log.w(TAG, "onKey without onItemSelected/onItemClick");
            return false;
        }
        if (this.mFocusedSetting.getType() == 3) {
            switch (i) {
                case 21:
                    if (keyEvent.getRepeatCount() > 0) {
                        valueSettingDown();
                        return true;
                    }
                    break;
                case 22:
                    if (keyEvent.getRepeatCount() > 0) {
                        valueSettingUp();
                        return true;
                    }
                    break;
            }
        }
        return false;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:31:0x00eb A[RETURN] */
    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (i == 4 && this.mParent != null) {
            Log.d(TAG, "onItemClick KEYCODE_BACK");
            this.callBackListener.updateListController(this.mParent);
            this.callBackListener.updateListPosition(this.mParent.getLastPosition());
            onPause();
            this.mParent.onResume();
            return true;
        }
        if (this.mFocusedSetting == null) {
            Log.w(TAG, "onKey without onItemSelected/onItemClick");
            return false;
        }
        switch (this.mFocusedSetting.getType()) {
            case 3:
                switch (i) {
                    case 21:
                        valueSettingDown();
                        return true;
                    case 22:
                        valueSettingUp();
                        return true;
                    default:
                        return false;
                }
            case 4:
                switch (i) {
                    case 21:
                        if (this.mFocusedSetting.setValue(Math.max(this.mFocusedSetting.getIntValue() - 1, this.mFocusedSetting.getMinValue()))) {
                            this.mCurrentTextView.setText(this.mFocusedSetting.getLevelValue());
                            this.mCurrentLeftButton.setEnabled(!this.mFocusedSetting.isMin());
                            this.mCurrentRightButton.setEnabled(!this.mFocusedSetting.isMax());
                            updateSettingValue(this.mFocusedSetting.getKey(), this.mFocusedSetting.getIntValue());
                        }
                        return true;
                    case 22:
                        if (this.mFocusedSetting.setValue(Math.min(this.mFocusedSetting.getIntValue() + 1, this.mFocusedSetting.getMaxValue()))) {
                            this.mCurrentTextView.setText(this.mFocusedSetting.getLevelValue());
                            this.mCurrentLeftButton.setEnabled(!this.mFocusedSetting.isMin());
                            this.mCurrentRightButton.setEnabled(!this.mFocusedSetting.isMax());
                            updateSettingValue(this.mFocusedSetting.getKey(), this.mFocusedSetting.getIntValue());
                        }
                        return true;
                    default:
                        return false;
                }
            default:
                return false;
        }
    }

    private void valueSettingDown() {
        int valueStep = this.mFocusedSetting.getValueStep();
        if (this.mFocusedSetting.setValue(Math.max(this.mFocusedSetting.getIntValue() - valueStep, this.mFocusedSetting.getMinValue()))) {
            updateSettingValue(this.mFocusedSetting.getKey(), this.mFocusedSetting.getIntValue());
            if (this.mValueAdjustable) {
                this.mCurrentTextView.setText(new Integer(this.mFocusedSetting.getIntValue()).toString());
            } else {
                this.mFocusedSetting.setValue(this.mFocusedSetting.getIntValue() == this.mFocusedSetting.getMaxValue() ? this.mFocusedSetting.getMaxValue() : valueStep + this.mFocusedSetting.getIntValue());
            }
        }
    }

    private void valueSettingUp() {
        int valueStep = this.mFocusedSetting.getValueStep();
        if (this.mFocusedSetting.setValue(Math.min(this.mFocusedSetting.getIntValue() + valueStep, this.mFocusedSetting.getMaxValue()))) {
            updateSettingValue(this.mFocusedSetting.getKey(), this.mFocusedSetting.getIntValue());
            if (this.mValueAdjustable) {
                this.mCurrentTextView.setText(Integer.toString(this.mFocusedSetting.getIntValue()));
            } else {
                this.mFocusedSetting.setValue(this.mFocusedSetting.getIntValue() == this.mFocusedSetting.getMaxValue() ? this.mFocusedSetting.getMaxValue() : this.mFocusedSetting.getIntValue() - valueStep);
            }
        }
    }

    protected ArrayList<SettingItem> initSettings() {
        ArrayList<SettingItem> arrayList = new ArrayList<>();
        if (ConfigManager.getInstance().getInitState() && !this.mDefaultInit) {
            SettingItem settingItem = ConfigManager.getInstance().getSettingMap().get(getClass().getSimpleName());
            if (settingItem != null) {
                arrayList.addAll(settingItem.getChildList());
            }
            for (SettingItem settingItem2 : arrayList) {
                settingItem2.setTitle(ConfigManager.getResString(this.mContext, settingItem2.getFieldTitle()));
                if (settingItem2.getType() == 4) {
                    settingItem2.setLevel(ConfigManager.getResArrayString(this.mContext, settingItem2.getFieldLevel()));
                }
                switch (settingItem2.getType()) {
                    case 3:
                    case 4:
                        settingItem2.setValue(getSettingIntValue(settingItem2.getKey()));
                        break;
                    case 5:
                        settingItem2.setStringValue(getSettingValue(settingItem2.getKey()));
                        break;
                }
                specialInit(settingItem2);
            }
            return arrayList;
        }
        return defaultInitSettings();
    }

    protected ArrayList<SettingItem> defaultInitSettings() {
        return new ArrayList<>();
    }

    public ArrayList<SettingItem> getSettings() {
        return this.mSettings;
    }

    public void setAdapter(ListAdapter listAdapter) {
        this.myAdapter = listAdapter;
    }

    public int getLastPosition() {
        return this.mCurrentItemPos;
    }

    public void setCallBackListener(CallBackListener callBackListener) {
        this.callBackListener = callBackListener;
    }
}
