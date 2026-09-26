package com.hs.p.basic;

import android.content.Context;
import android.content.Intent;

/* JADX INFO: loaded from: /Users/ruben/projector-dump/downloads/plugin.jar */
public interface Processor {
    String myId();

    void process(Context context, Intent intent);
}
