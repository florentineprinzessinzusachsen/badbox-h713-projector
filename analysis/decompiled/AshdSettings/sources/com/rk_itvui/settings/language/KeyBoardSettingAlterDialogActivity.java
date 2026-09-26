package com.rk_itvui.settings.language;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.android.internal.app.AlertActivity;
import com.android.settingslib.accessibility.AccessibilityUtils;
import com.ashd.settings.R;
import com.rk_itvui.settings.developer.SettingMacroDefine;
import com.rk_itvui.settings.dialog.AlterDialogListViewAdapter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class KeyBoardSettingAlterDialogActivity extends AlertActivity {
    private static Handler mHandler;
    private List<InputMethodInfo> mInputMethodList;
    private String mLastInputMethodId;
    private String mLastTickedInputMethodId;
    private boolean mHaveHardKeyboard = false;
    private ListView mListView = null;
    private AlterDialogListViewAdapter mAdapter = null;
    private ArrayList<String> mArrayList = new ArrayList<>();
    private int mSelection = -1;
    private int mID = -1;
    private String[] HideInputMethod = {"com.peasun.aispeech", "com.google.android.voicesearch"};
    private AdapterView.OnItemClickListener mListItemClister = new AdapterView.OnItemClickListener() { // from class: com.rk_itvui.settings.language.KeyBoardSettingAlterDialogActivity.1
        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            KeyBoardSettingAlterDialogActivity.this.mLastInputMethodId = ((InputMethodInfo) KeyBoardSettingAlterDialogActivity.this.mInputMethodList.get(i)).getId();
            KeyBoardSettingAlterDialogActivity.this.mAdapter.setSelection(i);
            KeyBoardSettingAlterDialogActivity.this.mAdapter.notifyDataSetChanged();
            if (KeyBoardSettingAlterDialogActivity.mHandler != null && KeyBoardSettingAlterDialogActivity.this.mID != -1) {
                CharSequence charSequenceLoadLabel = ((InputMethodInfo) KeyBoardSettingAlterDialogActivity.this.mInputMethodList.get(i)).loadLabel(KeyBoardSettingAlterDialogActivity.this.getPackageManager());
                Message message = new Message();
                message.what = 5;
                message.arg1 = KeyBoardSettingAlterDialogActivity.this.mID;
                message.obj = charSequenceLoadLabel.toString();
                KeyBoardSettingAlterDialogActivity.mHandler.sendMessage(message);
            }
            KeyBoardSettingAlterDialogActivity.this.finish();
        }
    };

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mID = getIntent().getIntExtra(SettingMacroDefine.ID, -1);
        onCreateIMM();
        createListView();
        this.mAlert.setView(this.mListView, 0, 0, 0, 0);
        setupAlert();
    }

    public static void setHandler(Handler handler) {
        mHandler = handler;
    }

    private boolean isSystemIme(InputMethodInfo inputMethodInfo) {
        return (inputMethodInfo.getServiceInfo().applicationInfo.flags & 1) != 0;
    }

    private void onCreateIMM() {
        this.mLastInputMethodId = Settings.Secure.getString(getContentResolver(), "default_input_method");
        this.mInputMethodList = ((InputMethodManager) getSystemService("input_method")).getInputMethodList();
        if ((this.mInputMethodList == null ? 0 : this.mInputMethodList.size()) > 0) {
            Iterator<InputMethodInfo> it = this.mInputMethodList.iterator();
            while (it.hasNext()) {
                String id = it.next().getId();
                LOGD("prefKey= " + id);
                String[] strArr = this.HideInputMethod;
                int length = strArr.length;
                for (int i = 0; i < length; i++) {
                    if (id.contains(strArr[i])) {
                        it.remove();
                        break;
                    }
                }
            }
        }
        int size = this.mInputMethodList == null ? 0 : this.mInputMethodList.size();
        LOGD("N=" + size);
        for (int i2 = 0; i2 < size; i2++) {
            InputMethodInfo inputMethodInfo = this.mInputMethodList.get(i2);
            String id2 = inputMethodInfo.getId();
            CharSequence charSequenceLoadLabel = inputMethodInfo.loadLabel(getPackageManager());
            isSystemIme(inputMethodInfo);
            getResources().getConfiguration();
            this.mHaveHardKeyboard = true;
            if (this.mHaveHardKeyboard || size > 1) {
                LOGD("onCreateIMM,label = " + charSequenceLoadLabel.toString());
                this.mArrayList.add(charSequenceLoadLabel.toString());
                if (id2 != null && this.mLastInputMethodId != null && id2.equals(this.mLastInputMethodId)) {
                    this.mSelection = i2;
                }
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void createListView() {
        this.mListView = (ListView) ((LayoutInflater) getSystemService("layout_inflater")).inflate(R.layout.list_view_kerboard, (ViewGroup) null);
        this.mAdapter = new AlterDialogListViewAdapter(this, this.mArrayList);
        this.mAdapter.setSelection(this.mSelection);
        this.mListView.setAdapter((ListAdapter) this.mAdapter);
        this.mListView.setOnItemClickListener(this.mListItemClister);
    }

    protected void onPause() {
        super.onPause();
        StringBuilder sb = new StringBuilder(256);
        StringBuilder sb2 = new StringBuilder(256);
        int size = this.mInputMethodList.size();
        int i = -1;
        for (int i2 = 0; i2 < size; i2++) {
            InputMethodInfo inputMethodInfo = this.mInputMethodList.get(i2);
            String id = inputMethodInfo.getId();
            boolean zEquals = id.equals(this.mLastInputMethodId);
            boolean zIsSystemIme = isSystemIme(inputMethodInfo);
            LOGD("id = " + id + ",mLastInputMethodId = " + this.mLastInputMethodId);
            if (((size == 1 || zIsSystemIme) && !this.mHaveHardKeyboard) || zEquals) {
                if (sb.length() > 0) {
                    sb.append(AccessibilityUtils.ENABLED_ACCESSIBILITY_SERVICES_SEPARATOR);
                }
                sb.append(id);
                if (i < 0) {
                    i = i2;
                }
            }
            if (zIsSystemIme && this.mHaveHardKeyboard && !zEquals) {
                if (sb2.length() > 0) {
                    sb2.append(":");
                }
                sb2.append(id);
            }
        }
        if (this.mLastInputMethodId == null || "".equals(this.mLastInputMethodId)) {
            if (i >= 0) {
                this.mLastInputMethodId = this.mInputMethodList.get(i).getId();
            } else {
                this.mLastInputMethodId = null;
            }
        }
        LOGD("onPause,Settings.Secure.ENABLED_INPUT_METHODS, builder.toString() = " + sb.toString());
        LOGD("onPause,Settings.Secure.DISABLED_SYSTEM_INPUT_METHODS, disabledSysImes.toString() = " + sb2.toString());
        StringBuilder sb3 = new StringBuilder();
        sb3.append("onPause,Settings.Secure.DEFAULT_INPUT_METHOD, mLastInputMethodId = ");
        sb3.append(this.mLastInputMethodId != null ? this.mLastInputMethodId : "");
        LOGD(sb3.toString());
        Settings.Secure.putString(getContentResolver(), "enabled_input_methods", sb.toString());
        Settings.Secure.putString(getContentResolver(), "disabled_system_input_methods", sb2.toString());
        Settings.Secure.putString(getContentResolver(), "default_input_method", this.mLastInputMethodId != null ? this.mLastInputMethodId : "");
    }

    public void LOGD(String str) {
        Log.d("KeyBoardSettingDialog", str);
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (keyEvent.getAction() == 1) {
            return super.dispatchKeyEvent(keyEvent);
        }
        int selectedItemPosition = this.mListView.getSelectedItemPosition();
        if (keyEvent.getKeyCode() == 19) {
            if (selectedItemPosition == 0) {
                this.mListView.setSelection(this.mListView.getChildCount() - 1);
                return true;
            }
        } else if (keyEvent.getKeyCode() == 20 && selectedItemPosition == this.mListView.getChildCount() - 1) {
            this.mListView.setSelection(0);
            return true;
        }
        return super.dispatchKeyEvent(keyEvent);
    }
}
