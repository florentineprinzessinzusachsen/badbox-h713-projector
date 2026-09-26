package com.hs.p.dx;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.AssetManager;
import android.content.res.Resources;

/* JADX INFO: loaded from: /Users/ruben/projector-dump/downloads/plugin.jar */
public class PluginContext extends ContextWrapper {
    private AssetManager assetManager;
    private ClassLoader classLoader;
    private Resources resources;
    private Resources.Theme theme;

    public PluginContext(Context context) {
        super(context);
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Context getApplicationContext() {
        return this;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public AssetManager getAssets() {
        return this.assetManager;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public ClassLoader getClassLoader() {
        return this.classLoader;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Resources getResources() {
        return this.resources;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Resources.Theme getTheme() {
        return this.theme;
    }

    public PluginContext(Context context, Resources resources, AssetManager assetManager, Resources.Theme theme, ClassLoader classLoader) {
        super(context);
        this.resources = resources;
        this.assetManager = assetManager;
        this.theme = theme;
        this.classLoader = classLoader;
    }
}
