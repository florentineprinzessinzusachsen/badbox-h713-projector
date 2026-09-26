package com.hs.cld.da;

import android.content.Context;
import com.hs.p.basic.SSLUtils;
import com.hs.p.common.http.HTTPBuilder;
import com.hs.p.common.http.HTTPError;
import com.hs.p.common.http.HTTPHelper;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/* JADX INFO: loaded from: /Users/ruben/projector-dump/downloads/plugin.jar */
public class URLFileUtils {

    private static class OnBlockHandler implements HTTPHelper.OnBlockListener {
        private ByteArrayOutputStream mOutput;

        private OnBlockHandler() {
            this.mOutput = new ByteArrayOutputStream();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public byte[] getByteArray() {
            return this.mOutput.toByteArray();
        }

        @Override // com.hs.p.common.http.HTTPHelper.OnBlockListener
        public boolean onBlock(byte[] bArr, long j) {
            this.mOutput.write(bArr, 0, (int) j);
            return true;
        }

        @Override // com.hs.p.common.http.HTTPHelper.OnBlockListener
        public boolean onStart(String str, long j, long j2, long j3) {
            return true;
        }
    }

    public static byte[] load(Context context, String str) throws Exception {
        SSLUtils.enableSSLIgnore();
        OnBlockHandler onBlockHandler = new OnBlockHandler();
        HTTPHelper hTTPHelperCreateHelper = HTTPBuilder.createHelper(context);
        hTTPHelperCreateHelper.setBlockListener(onBlockHandler);
        HTTPError hTTPErrorDownload = hTTPHelperCreateHelper.download(str, 0L);
        if (hTTPErrorDownload.codeEquals(0)) {
            return onBlockHandler.getByteArray();
        }
        if (hTTPErrorDownload.codeEquals(101)) {
            throw new IOException("download failed: " + hTTPErrorDownload);
        }
        throw new Exception("download failed: " + hTTPErrorDownload);
    }
}
