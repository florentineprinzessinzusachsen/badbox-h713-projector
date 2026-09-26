package com.android.settingslib.system;

import android.content.Context;
import android.content.res.Resources;
import android.hardware.display.DisplayManager;
import android.os.Handler;
import android.os.Looper;
import android.support.v14.preference.SwitchPreference;
import android.util.AttributeSet;
import android.view.Display;
import com.android.settingslib.R;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class ColorModePreference extends SwitchPreference implements DisplayManager.DisplayListener {
    private int mCurrentIndex;
    private ArrayList<ColorModeDescription> mDescriptions;
    private Display mDisplay;
    private DisplayManager mDisplayManager;

    @Override // android.hardware.display.DisplayManager.DisplayListener
    public void onDisplayRemoved(int i) {
    }

    public ColorModePreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mDisplayManager = (DisplayManager) getContext().getSystemService(DisplayManager.class);
    }

    public int getColorModeCount() {
        return this.mDescriptions.size();
    }

    public void startListening() {
        this.mDisplayManager.registerDisplayListener(this, new Handler(Looper.getMainLooper()));
    }

    public void stopListening() {
        this.mDisplayManager.unregisterDisplayListener(this);
    }

    @Override // android.hardware.display.DisplayManager.DisplayListener
    public void onDisplayAdded(int i) {
        if (i == 0) {
            updateCurrentAndSupported();
        }
    }

    @Override // android.hardware.display.DisplayManager.DisplayListener
    public void onDisplayChanged(int i) {
        if (i == 0) {
            updateCurrentAndSupported();
        }
    }

    public void updateCurrentAndSupported() {
        this.mDisplay = this.mDisplayManager.getDisplay(0);
        this.mDescriptions = new ArrayList<>();
        Resources resources = getContext().getResources();
        int[] intArray = resources.getIntArray(R.array.color_mode_ids);
        String[] stringArray = resources.getStringArray(R.array.color_mode_names);
        String[] stringArray2 = resources.getStringArray(R.array.color_mode_descriptions);
        for (int i = 0; i < intArray.length; i++) {
            if (intArray[i] != -1 && i != 1) {
                ColorModeDescription colorModeDescription = new ColorModeDescription();
                colorModeDescription.colorMode = intArray[i];
                colorModeDescription.title = stringArray[i];
                colorModeDescription.summary = stringArray2[i];
                this.mDescriptions.add(colorModeDescription);
            }
        }
        int colorMode = this.mDisplay.getColorMode();
        this.mCurrentIndex = -1;
        for (int i2 = 0; i2 < this.mDescriptions.size(); i2++) {
            if (this.mDescriptions.get(i2).colorMode == colorMode) {
                this.mCurrentIndex = i2;
                break;
            }
        }
        setChecked(this.mCurrentIndex == 1);
    }

    @Override // android.support.v7.preference.Preference
    protected boolean persistBoolean(boolean z) {
        if (this.mDescriptions.size() != 2) {
            return true;
        }
        ColorModeDescription colorModeDescription = this.mDescriptions.get(z ? 1 : 0);
        this.mDisplay.requestColorMode(colorModeDescription.colorMode);
        this.mCurrentIndex = this.mDescriptions.indexOf(colorModeDescription);
        return true;
    }

    private static class ColorModeDescription {
        private int colorMode;
        private String summary;
        private String title;

        private ColorModeDescription() {
        }
    }
}
