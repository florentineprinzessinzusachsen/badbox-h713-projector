package com.hs.p.common.http;

/* JADX INFO: loaded from: /Users/ruben/projector-dump/downloads/plugin.jar */
public class HTTPResult {
    public String ID;
    public HTTPError error;
    public InternalError internalError;
    public String realRequestUrl;
    public byte[] responseBody;
    public String targetHost;

    public boolean codeEquals(int i) {
        HTTPError hTTPError = this.error;
        return hTTPError != null && hTTPError.codeEquals(i);
    }
}
