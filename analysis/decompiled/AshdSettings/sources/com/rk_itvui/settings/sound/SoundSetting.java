package com.rk_itvui.settings.sound;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import com.ashd.settings.R;
import com.rk_itvui.settings.BuildConfig;
import com.rk_itvui.settings.FullScreenPreferenceActivity;
import com.rk_itvui.settings.Utils;
import java.util.ArrayList;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public class SoundSetting extends FullScreenPreferenceActivity implements AdapterView.OnItemClickListener {
    SimpleAdapter listItemAdapter;
    private AudioManager mAudioManager;
    ArrayList<HashMap<String, Object>> listItem = new ArrayList<>();
    HashMap<String, Object> map_VolumeSetting = new HashMap<>();
    HashMap<String, Object> map_PressVoice = new HashMap<>();
    private final boolean is3229 = Utils.checkChipType().equals("rk3229");

    @Override // com.rk_itvui.settings.FullScreenPreferenceActivity, android.preference.PreferenceActivity, android.app.Activity
    @RequiresApi(api = 23)
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.sound_setting_new);
        addListView();
        if (BuildConfig.FLAVOR.contains(BuildConfig.FLAVOR)) {
            TextView textView = (TextView) findViewById(R.id.app_text);
            TextView textView2 = (TextView) findViewById(R.id.settings_line);
            textView.setCompoundDrawablesWithIntrinsicBounds(getDrawable(R.drawable.icon_ashd2), (Drawable) null, (Drawable) null, (Drawable) null);
            textView2.setBackgroundResource(R.drawable.settings_head_line_ashd2);
        }
    }

    @RequiresApi(api = 23)
    public void addListView() {
        this.mAudioManager = (AudioManager) getSystemService(AudioManager.class);
        this.map_VolumeSetting.put("SoundSettingIcon", Integer.valueOf(R.drawable.volume_icon));
        this.map_VolumeSetting.put("SoundSettingItem", getString(R.string.volume_setting));
        this.map_PressVoice.put("SoundSettingIcon", Integer.valueOf(R.drawable.sound_device_icon));
        this.map_PressVoice.put("SoundSettingItem", getString(R.string.press_voice));
        if (getSoundEffectsEnabled()) {
            this.map_PressVoice.put("SoundSettingStatus", getString(R.string.PressVioceStatus_open));
        } else {
            this.map_PressVoice.put("SoundSettingStatus", getString(R.string.PressVioceStatus_close));
        }
        if (!this.is3229) {
            this.listItem.add(this.map_VolumeSetting);
        }
        this.listItem.add(this.map_PressVoice);
        ListView listView = (ListView) findViewById(R.id.language_list);
        this.listItemAdapter = new SimpleAdapter(this, this.listItem, R.layout.sound_item, new String[]{"SoundSettingIcon", "SoundSettingItem", "SoundSettingStatus"}, new int[]{R.id.SoundSettingIcon, R.id.SoundSettingItem, R.id.SoundSettingStatus});
        listView.setAdapter((ListAdapter) this.listItemAdapter);
        listView.setOnItemClickListener(this);
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        switch (i) {
            case 0:
                if (this.is3229) {
                    setSoundEffects();
                    this.listItemAdapter.notifyDataSetChanged();
                } else {
                    startActivity(new Intent(this, (Class<?>) VolumeSetting.class));
                }
                break;
            case 1:
                setSoundEffects();
                this.listItemAdapter.notifyDataSetChanged();
                break;
        }
    }

    @Override // android.app.Activity
    public void onResume() {
        this.listItemAdapter.notifyDataSetChanged();
        super.onResume();
    }

    private void setSoundEffects() {
        setSoundEffectsEnabled(getSoundEffectsEnabled());
        if (getSoundEffectsEnabled()) {
            this.map_PressVoice.put("SoundSettingStatus", getString(R.string.PressVioceStatus_open));
        } else {
            this.map_PressVoice.put("SoundSettingStatus", getString(R.string.PressVioceStatus_close));
        }
    }

    private boolean getSoundEffectsEnabled() {
        return getSoundEffectsEnabled(getContentResolver());
    }

    public static boolean getSoundEffectsEnabled(ContentResolver contentResolver) {
        return Settings.System.getInt(contentResolver, "sound_effects_enabled", 1) != 0;
    }

    private void setSoundEffectsEnabled(boolean z) {
        if (z) {
            this.mAudioManager.unloadSoundEffects();
        } else {
            this.mAudioManager.loadSoundEffects();
        }
        this.mAudioManager.playSoundEffect(9);
        Settings.System.putInt(getContentResolver(), "sound_effects_enabled", !z ? 1 : 0);
    }
}
