package com.baidu.mobstat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Message;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.ClientCertRequest;
import android.webkit.ConsoleMessage;
import android.webkit.GeolocationPermissions;
import android.webkit.HttpAuthHandler;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.PermissionRequest;
import android.webkit.RenderProcessGoneDetail;
import android.webkit.SslErrorHandler;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebStorage;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class BaiduStatJSInterface {

    public interface IWebviewPageLoadCallback {
        void onPageFinished(WebView webView, String str, aq aqVar);

        void onPageStarted(WebView webView, String str, aq aqVar);
    }

    public static class CustomWebChromeViewClient extends WebChromeClient {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private WeakReference<Context> f3326a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private WebChromeClient f3327b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private ArrayList<IWebviewPageLoadCallback> f3328c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private aq f3329d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private int f3330e = 0;

        public CustomWebChromeViewClient(Context context, WebChromeClient webChromeClient, ArrayList<IWebviewPageLoadCallback> arrayList, aq aqVar) {
            this.f3326a = new WeakReference<>(context);
            this.f3327b = webChromeClient;
            this.f3328c = arrayList;
            this.f3329d = aqVar;
        }

        @Override // android.webkit.WebChromeClient
        public void onCloseWindow(WebView webView) {
            WebChromeClient webChromeClient = this.f3327b;
            if (webChromeClient != null) {
                webChromeClient.onCloseWindow(webView);
            }
        }

        @Override // android.webkit.WebChromeClient
        public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
            WebChromeClient webChromeClient = this.f3327b;
            return webChromeClient != null ? webChromeClient.onConsoleMessage(consoleMessage) : super.onConsoleMessage(consoleMessage);
        }

        @Override // android.webkit.WebChromeClient
        public boolean onCreateWindow(WebView webView, boolean z, boolean z2, Message message) {
            WebChromeClient webChromeClient = this.f3327b;
            return webChromeClient != null ? webChromeClient.onCreateWindow(webView, z, z2, message) : super.onCreateWindow(webView, z, z2, message);
        }

        @Override // android.webkit.WebChromeClient
        @Deprecated
        public void onExceededDatabaseQuota(String str, String str2, long j, long j2, long j3, WebStorage.QuotaUpdater quotaUpdater) {
            WebChromeClient webChromeClient = this.f3327b;
            if (webChromeClient != null) {
                webChromeClient.onExceededDatabaseQuota(str, str2, j, j2, j3, quotaUpdater);
            }
        }

        @Override // android.webkit.WebChromeClient
        public void onGeolocationPermissionsHidePrompt() {
            WebChromeClient webChromeClient = this.f3327b;
            if (webChromeClient != null) {
                webChromeClient.onGeolocationPermissionsHidePrompt();
            }
        }

        @Override // android.webkit.WebChromeClient
        public void onGeolocationPermissionsShowPrompt(String str, GeolocationPermissions.Callback callback) {
            WebChromeClient webChromeClient = this.f3327b;
            if (webChromeClient != null) {
                webChromeClient.onGeolocationPermissionsShowPrompt(str, callback);
            }
        }

        @Override // android.webkit.WebChromeClient
        public void onHideCustomView() {
            WebChromeClient webChromeClient = this.f3327b;
            if (webChromeClient != null) {
                webChromeClient.onHideCustomView();
            }
        }

        @Override // android.webkit.WebChromeClient
        public boolean onJsAlert(WebView webView, String str, String str2, JsResult jsResult) {
            WebChromeClient webChromeClient = this.f3327b;
            return webChromeClient != null ? webChromeClient.onJsAlert(webView, str, str2, jsResult) : super.onJsAlert(webView, str, str2, jsResult);
        }

        @Override // android.webkit.WebChromeClient
        public boolean onJsBeforeUnload(WebView webView, String str, String str2, JsResult jsResult) {
            WebChromeClient webChromeClient = this.f3327b;
            return webChromeClient != null ? webChromeClient.onJsBeforeUnload(webView, str, str2, jsResult) : super.onJsBeforeUnload(webView, str, str2, jsResult);
        }

        @Override // android.webkit.WebChromeClient
        public boolean onJsConfirm(WebView webView, String str, String str2, JsResult jsResult) {
            WebChromeClient webChromeClient = this.f3327b;
            return webChromeClient != null ? webChromeClient.onJsConfirm(webView, str, str2, jsResult) : super.onJsConfirm(webView, str, str2, jsResult);
        }

        @Override // android.webkit.WebChromeClient
        public boolean onJsPrompt(WebView webView, String str, String str2, String str3, JsPromptResult jsPromptResult) {
            WebChromeClient webChromeClient = this.f3327b;
            return webChromeClient != null ? webChromeClient.onJsPrompt(webView, str, str2, str3, jsPromptResult) : super.onJsPrompt(webView, str, str2, str3, jsPromptResult);
        }

        @Override // android.webkit.WebChromeClient
        @Deprecated
        public boolean onJsTimeout() {
            WebChromeClient webChromeClient = this.f3327b;
            return webChromeClient != null ? webChromeClient.onJsTimeout() : super.onJsTimeout();
        }

        @Override // android.webkit.WebChromeClient
        @SuppressLint({"NewApi"})
        public void onPermissionRequest(PermissionRequest permissionRequest) {
            WebChromeClient webChromeClient = this.f3327b;
            if (webChromeClient != null) {
                webChromeClient.onPermissionRequest(permissionRequest);
            }
        }

        @Override // android.webkit.WebChromeClient
        @SuppressLint({"NewApi"})
        public void onPermissionRequestCanceled(PermissionRequest permissionRequest) {
            WebChromeClient webChromeClient = this.f3327b;
            if (webChromeClient != null) {
                webChromeClient.onPermissionRequestCanceled(permissionRequest);
            }
        }

        @Override // android.webkit.WebChromeClient
        public void onProgressChanged(WebView webView, int i) {
            ArrayList<IWebviewPageLoadCallback> arrayList = this.f3328c;
            if (arrayList != null) {
                if (this.f3330e == 0) {
                    for (IWebviewPageLoadCallback iWebviewPageLoadCallback : arrayList) {
                        if (iWebviewPageLoadCallback != null) {
                            iWebviewPageLoadCallback.onPageStarted(webView, webView.getUrl(), this.f3329d);
                        }
                    }
                }
                this.f3330e = i;
                if (i == 100) {
                    for (IWebviewPageLoadCallback iWebviewPageLoadCallback2 : this.f3328c) {
                        if (iWebviewPageLoadCallback2 != null) {
                            iWebviewPageLoadCallback2.onPageFinished(webView, webView.getUrl(), this.f3329d);
                        }
                    }
                }
            }
            WebChromeClient webChromeClient = this.f3327b;
            if (webChromeClient != null) {
                webChromeClient.onProgressChanged(webView, i);
            }
        }

        @Deprecated
        public void onReachedMaxAppCacheSize(long j, long j2, WebStorage.QuotaUpdater quotaUpdater) {
            WebChromeClient webChromeClient = this.f3327b;
            if (webChromeClient != null) {
                webChromeClient.onReachedMaxAppCacheSize(j, j2, quotaUpdater);
            }
        }

        @Override // android.webkit.WebChromeClient
        public void onReceivedIcon(WebView webView, Bitmap bitmap) {
            WebChromeClient webChromeClient = this.f3327b;
            if (webChromeClient != null) {
                webChromeClient.onReceivedIcon(webView, bitmap);
            }
        }

        @Override // android.webkit.WebChromeClient
        public void onReceivedTitle(WebView webView, String str) {
            WebChromeClient webChromeClient = this.f3327b;
            if (webChromeClient != null) {
                webChromeClient.onReceivedTitle(webView, str);
            }
        }

        @Override // android.webkit.WebChromeClient
        public void onReceivedTouchIconUrl(WebView webView, String str, boolean z) {
            WebChromeClient webChromeClient = this.f3327b;
            if (webChromeClient != null) {
                webChromeClient.onReceivedTouchIconUrl(webView, str, z);
            }
        }

        @Override // android.webkit.WebChromeClient
        public void onRequestFocus(WebView webView) {
            WebChromeClient webChromeClient = this.f3327b;
            if (webChromeClient != null) {
                webChromeClient.onRequestFocus(webView);
            }
        }

        @Override // android.webkit.WebChromeClient
        public void onShowCustomView(View view, WebChromeClient.CustomViewCallback customViewCallback) {
            WebChromeClient webChromeClient = this.f3327b;
            if (webChromeClient != null) {
                webChromeClient.onShowCustomView(view, customViewCallback);
            }
        }

        @Override // android.webkit.WebChromeClient
        @SuppressLint({"NewApi"})
        public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> valueCallback, WebChromeClient.FileChooserParams fileChooserParams) {
            WebChromeClient webChromeClient = this.f3327b;
            return webChromeClient != null ? webChromeClient.onShowFileChooser(webView, valueCallback, fileChooserParams) : super.onShowFileChooser(webView, valueCallback, fileChooserParams);
        }

        @Override // android.webkit.WebChromeClient
        @SuppressLint({"NewApi"})
        @Deprecated
        public void onShowCustomView(View view, int i, WebChromeClient.CustomViewCallback customViewCallback) {
            WebChromeClient webChromeClient = this.f3327b;
            if (webChromeClient != null) {
                webChromeClient.onShowCustomView(view, i, customViewCallback);
            }
        }

        @Override // android.webkit.WebChromeClient
        @Deprecated
        public void onConsoleMessage(String str, int i, String str2) {
            WebChromeClient webChromeClient = this.f3327b;
            if (webChromeClient != null) {
                webChromeClient.onConsoleMessage(str, i, str2);
            }
        }
    }

    public static class CustomWebViewClient extends WebViewClient {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private WeakReference<Context> f3331a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private WebViewClient f3332b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private IWebviewPageLoadCallback f3333c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private aq f3334d;

        public CustomWebViewClient(Context context, WebViewClient webViewClient, IWebviewPageLoadCallback iWebviewPageLoadCallback, aq aqVar) {
            this.f3331a = new WeakReference<>(context);
            this.f3332b = webViewClient;
            this.f3333c = iWebviewPageLoadCallback;
            this.f3334d = aqVar;
        }

        private void a(String str) throws JSONException {
            JSONObject jSONObject;
            JSONObject jSONObject2;
            JSONObject jSONObject3;
            JSONObject jSONObject4 = new JSONObject(str);
            String string = jSONObject4.getString("action");
            JSONObject jSONObject5 = jSONObject4.getJSONObject("obj");
            Context context = this.f3331a.get();
            if (context == null) {
                return;
            }
            if ("onPageStart".equals(string)) {
                String string2 = jSONObject5.getString("page");
                if (TextUtils.isEmpty(string2)) {
                    return;
                }
                BDStatCore.instance().onPageStart(context, string2);
                return;
            }
            if ("onPageEnd".equals(string)) {
                String string3 = jSONObject5.getString("page");
                if (TextUtils.isEmpty(string3)) {
                    return;
                }
                BDStatCore.instance().onPageEnd(context, string3, null, true);
                return;
            }
            if ("onEvent".equals(string)) {
                String string4 = jSONObject5.getString("event_id");
                String string5 = jSONObject5.getString("label");
                int i = jSONObject5.getInt("acc");
                if (TextUtils.isEmpty(string4)) {
                    return;
                }
                try {
                    jSONObject3 = (JSONObject) jSONObject5.get("attributes");
                } catch (Exception unused) {
                    jSONObject3 = null;
                }
                BDStatCore.instance().onEvent(context, string4, string5, i, (ExtraInfo) null, (Map<String, String>) a(jSONObject3), false, true);
                return;
            }
            if ("onEventStart".equals(string)) {
                String string6 = jSONObject5.getString("event_id");
                String string7 = jSONObject5.getString("label");
                if (TextUtils.isEmpty(string6)) {
                    return;
                }
                BDStatCore.instance().onEventStart(context, string6, string7, false);
                return;
            }
            if ("onEventEnd".equals(string)) {
                String string8 = jSONObject5.getString("event_id");
                String string9 = jSONObject5.getString("label");
                if (TextUtils.isEmpty(string8)) {
                    return;
                }
                try {
                    jSONObject2 = (JSONObject) jSONObject5.get("attributes");
                } catch (Exception unused2) {
                    jSONObject2 = null;
                }
                BDStatCore.instance().onEventEnd(context, string8, string9, null, a(jSONObject2), true);
                return;
            }
            if ("onEventDuration".equals(string)) {
                String string10 = jSONObject5.getString("event_id");
                String string11 = jSONObject5.getString("label");
                long j = jSONObject5.getLong("duration");
                if (TextUtils.isEmpty(string10)) {
                    return;
                }
                try {
                    jSONObject = (JSONObject) jSONObject5.get("attributes");
                } catch (Exception unused3) {
                    jSONObject = null;
                }
                BDStatCore.instance().onEventDuration(context, string10, string11, j, null, a(jSONObject), false, true);
            }
        }

        @Override // android.webkit.WebViewClient
        public void doUpdateVisitedHistory(WebView webView, String str, boolean z) {
            WebViewClient webViewClient = this.f3332b;
            if (webViewClient != null) {
                webViewClient.doUpdateVisitedHistory(webView, str, z);
            }
        }

        @Override // android.webkit.WebViewClient
        public void onFormResubmission(WebView webView, Message message, Message message2) {
            WebViewClient webViewClient = this.f3332b;
            if (webViewClient != null) {
                webViewClient.onFormResubmission(webView, message, message2);
            }
        }

        @Override // android.webkit.WebViewClient
        public void onLoadResource(WebView webView, String str) {
            WebViewClient webViewClient = this.f3332b;
            if (webViewClient != null) {
                webViewClient.onLoadResource(webView, str);
            }
        }

        @Override // android.webkit.WebViewClient
        @SuppressLint({"NewApi"})
        public void onPageCommitVisible(WebView webView, String str) {
            WebViewClient webViewClient = this.f3332b;
            if (webViewClient != null) {
                webViewClient.onPageCommitVisible(webView, str);
            }
        }

        @Override // android.webkit.WebViewClient
        public void onPageFinished(WebView webView, String str) {
            WebViewClient webViewClient = this.f3332b;
            if (webViewClient != null) {
                webViewClient.onPageFinished(webView, str);
            }
        }

        @Override // android.webkit.WebViewClient
        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            WebViewClient webViewClient = this.f3332b;
            if (webViewClient != null) {
                webViewClient.onPageStarted(webView, str, bitmap);
            }
        }

        @Override // android.webkit.WebViewClient
        @SuppressLint({"NewApi"})
        public void onReceivedClientCertRequest(WebView webView, ClientCertRequest clientCertRequest) {
            WebViewClient webViewClient = this.f3332b;
            if (webViewClient != null) {
                webViewClient.onReceivedClientCertRequest(webView, clientCertRequest);
            }
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedError(WebView webView, int i, String str, String str2) {
            WebViewClient webViewClient = this.f3332b;
            if (webViewClient != null) {
                webViewClient.onReceivedError(webView, i, str, str2);
            }
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedHttpAuthRequest(WebView webView, HttpAuthHandler httpAuthHandler, String str, String str2) {
            WebViewClient webViewClient = this.f3332b;
            if (webViewClient != null) {
                webViewClient.onReceivedHttpAuthRequest(webView, httpAuthHandler, str, str2);
            }
        }

        @Override // android.webkit.WebViewClient
        @SuppressLint({"NewApi"})
        public void onReceivedHttpError(WebView webView, WebResourceRequest webResourceRequest, WebResourceResponse webResourceResponse) {
            WebViewClient webViewClient = this.f3332b;
            if (webViewClient != null) {
                webViewClient.onReceivedHttpError(webView, webResourceRequest, webResourceResponse);
            }
        }

        @Override // android.webkit.WebViewClient
        @SuppressLint({"NewApi"})
        public void onReceivedLoginRequest(WebView webView, String str, String str2, String str3) {
            WebViewClient webViewClient = this.f3332b;
            if (webViewClient != null) {
                webViewClient.onReceivedLoginRequest(webView, str, str2, str3);
            }
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
            WebViewClient webViewClient = this.f3332b;
            if (webViewClient != null) {
                webViewClient.onReceivedSslError(webView, sslErrorHandler, sslError);
            }
        }

        @Override // android.webkit.WebViewClient
        @SuppressLint({"NewApi"})
        public boolean onRenderProcessGone(WebView webView, RenderProcessGoneDetail renderProcessGoneDetail) {
            WebViewClient webViewClient = this.f3332b;
            return webViewClient != null ? webViewClient.onRenderProcessGone(webView, renderProcessGoneDetail) : super.onRenderProcessGone(webView, renderProcessGoneDetail);
        }

        @Override // android.webkit.WebViewClient
        public void onScaleChanged(WebView webView, float f2, float f3) {
            WebViewClient webViewClient = this.f3332b;
            if (webViewClient != null) {
                webViewClient.onScaleChanged(webView, f2, f3);
            }
        }

        @Override // android.webkit.WebViewClient
        @Deprecated
        public void onTooManyRedirects(WebView webView, Message message, Message message2) {
            WebViewClient webViewClient = this.f3332b;
            if (webViewClient != null) {
                webViewClient.onTooManyRedirects(webView, message, message2);
            }
        }

        @Override // android.webkit.WebViewClient
        public void onUnhandledKeyEvent(WebView webView, KeyEvent keyEvent) {
            WebViewClient webViewClient = this.f3332b;
            if (webViewClient != null) {
                webViewClient.onUnhandledKeyEvent(webView, keyEvent);
            }
        }

        @Override // android.webkit.WebViewClient
        @SuppressLint({"NewApi"})
        public WebResourceResponse shouldInterceptRequest(WebView webView, String str) {
            WebViewClient webViewClient = this.f3332b;
            return webViewClient != null ? webViewClient.shouldInterceptRequest(webView, str) : super.shouldInterceptRequest(webView, str);
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideKeyEvent(WebView webView, KeyEvent keyEvent) {
            WebViewClient webViewClient = this.f3332b;
            return webViewClient != null ? webViewClient.shouldOverrideKeyEvent(webView, keyEvent) : super.shouldOverrideKeyEvent(webView, keyEvent);
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            try {
                str = URLDecoder.decode(str, "UTF-8");
                if (!TextUtils.isEmpty(str) && str.startsWith("bmtj:")) {
                    a(str.substring(5));
                    return true;
                }
            } catch (UnsupportedEncodingException | JSONException unused) {
            }
            WebViewClient webViewClient = this.f3332b;
            return webViewClient != null ? webViewClient.shouldOverrideUrlLoading(webView, str) : super.shouldOverrideUrlLoading(webView, str);
        }

        @Override // android.webkit.WebViewClient
        @SuppressLint({"NewApi"})
        public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
            WebViewClient webViewClient = this.f3332b;
            if (webViewClient != null) {
                webViewClient.onReceivedError(webView, webResourceRequest, webResourceError);
            }
        }

        @Override // android.webkit.WebViewClient
        @SuppressLint({"NewApi"})
        public WebResourceResponse shouldInterceptRequest(WebView webView, WebResourceRequest webResourceRequest) {
            WebViewClient webViewClient = this.f3332b;
            if (webViewClient != null) {
                return webViewClient.shouldInterceptRequest(webView, webResourceRequest);
            }
            return super.shouldInterceptRequest(webView, webResourceRequest);
        }

        @Override // android.webkit.WebViewClient
        @SuppressLint({"NewApi"})
        public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
            WebViewClient webViewClient = this.f3332b;
            if (webViewClient != null) {
                return webViewClient.shouldOverrideUrlLoading(webView, webResourceRequest);
            }
            return super.shouldOverrideUrlLoading(webView, webResourceRequest);
        }

        private HashMap<String, String> a(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            HashMap<String, String> map = jSONObject.length() != 0 ? new HashMap<>() : null;
            Iterator<String> itKeys = jSONObject.keys();
            while (itKeys.hasNext()) {
                try {
                    String next = itKeys.next();
                    String string = jSONObject.getString(next);
                    if (map != null) {
                        map.put(next, string);
                    }
                } catch (Exception unused) {
                }
            }
            return map;
        }
    }
}
