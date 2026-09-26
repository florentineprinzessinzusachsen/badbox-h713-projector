package com.hs.p.common.http;

import android.content.Context;
import android.net.Uri;
import com.hs.p.common.utils.LOG;
import com.hs.p.common.utils.ObjUtils;
import com.hs.p.common.utils.TextUtils;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/* JADX INFO: loaded from: /Users/ruben/projector-dump/downloads/plugin.jar */
public class HTTPHelper2 {
    private static final String CHARSET_UTF8 = "UTF-8";
    private static final String CONTENT_TYPE_BINARY = "application/octet-stream";
    private static final String HEADER_CHARSET = "Charset";
    private static final String HEADER_CONTENT_LENGTH = "Content-Length";
    private static final String HEADER_CONTENT_RANGE = "Content-Range";
    private static final String HEADER_CONTENT_TYPE = "Content-Type";
    private static final String HEADER_RANGE = "Range";
    private static final String HEADER_USER_AGENT = "User-Agent";
    private static final String METHOD_GET = "GET";
    private static final String METHOD_POST = "POST";
    private static final String P_REQUEST_TIME = "t";
    private static final String P_SYN = "syn";
    private static final String TAG = "HTTPHelper";
    private static AtomicLong gCounter = new AtomicLong(1);
    private final Context mContext;
    private int mTimeout = 30000;
    private boolean mLogEnabled = true;
    private String mProtocol = "https://";
    private List<String> mHosts = null;
    private String mPath = null;
    private Map<String, String> mQueryParameters = new LinkedHashMap();
    private Map<String, String> mRequestHeaders = new HashMap();
    private byte[] mRequestBody = null;
    private OnBlockListener mOnBlockListener = null;
    private HttpContext mHttpContext = null;

    private static class BrokenRange {
        public final long mBodyLength;
        public final long mOffset;
        public final long mTotal;

        public BrokenRange(long j, long j2, long j3) {
            this.mOffset = j;
            this.mBodyLength = j2;
            this.mTotal = j3;
        }
    }

    private static class HttpContext {
        private String ID;
        private String mRealRequestUrl;
        private String mRequestUrl;
        private byte[] mResponseBody;
        private String mTargetHost;

        private HttpContext() {
            this.ID = "" + HTTPHelper2.gCounter.getAndIncrement();
            this.mRequestUrl = null;
            this.mRealRequestUrl = null;
            this.mResponseBody = null;
            this.mTargetHost = null;
        }

        public String toString() {
            return "HttpContext(" + this.ID + ")";
        }
    }

    private static class HttpResource {
        private HttpURLConnection mConnection;
        private InputStream mInput;
        private OutputStream mOutput;
        private int mResponseCode;
        private String mResponseMessage;

        private HttpResource() {
            this.mInput = null;
            this.mOutput = null;
            this.mConnection = null;
            this.mResponseCode = -1;
            this.mResponseMessage = null;
        }
    }

    private static class NoneHostnameVerifier implements HostnameVerifier {
        private NoneHostnameVerifier() {
        }

        @Override // javax.net.ssl.HostnameVerifier
        public boolean verify(String str, SSLSession sSLSession) {
            return true;
        }
    }

    private static class NoneX509TrustManager implements X509TrustManager {
        private NoneX509TrustManager() {
        }

        @Override // javax.net.ssl.X509TrustManager
        public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) {
        }

        @Override // javax.net.ssl.X509TrustManager
        public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) {
        }

        @Override // javax.net.ssl.X509TrustManager
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }
    }

    public interface OnBlockListener {
        boolean onBlock(byte[] bArr, long j);

        boolean onStart(String str, long j, long j2, long j3);
    }

    static {
        try {
            SSLContext sSLContext = SSLContext.getInstance("SSL");
            sSLContext.init(null, new TrustManager[]{new NoneX509TrustManager()}, new SecureRandom());
            HttpsURLConnection.setDefaultHostnameVerifier(new NoneHostnameVerifier());
            HttpsURLConnection.setDefaultSSLSocketFactory(sSLContext.getSocketFactory());
        } catch (Throwable th) {
            LOG.w(TAG, "init https factory failed: " + th);
        }
    }

    private HTTPHelper2(Context context) {
        this.mContext = context;
    }

    private void E(String str) {
        if (this.mLogEnabled) {
            LOG.e(TAG, str);
        }
    }

    private void I(String str) {
        if (this.mLogEnabled) {
            LOG.i(TAG, str);
        }
    }

    private boolean REDIRECT(int i) {
        return 301 == i || 302 == i;
    }

    private boolean SUCCESS(int i) {
        return i < 300 && i >= 200;
    }

    private Map<String, String> buildBrokenRequestHeaders(long j, long j2) {
        String str;
        Map<String, String> mapBuildRequestHeaders = buildRequestHeaders(METHOD_GET, null);
        if (j > 0) {
            if (j2 > 0) {
                str = "bytes=" + j + "-" + ((j2 + j) - 1);
            } else {
                str = "bytes=" + j + "-";
            }
            mapBuildRequestHeaders.put("Range", str);
        }
        return mapBuildRequestHeaders;
    }

    private Map<String, String> buildRequestHeaders(String str, byte[] bArr) {
        HashMap map = new HashMap();
        if (TextUtils.equalsIgnoreCase(str, METHOD_POST)) {
            map.put("Charset", "UTF-8");
            map.put("Content-Type", "application/octet-stream");
            if (bArr != null) {
                map.put("Content-Length", String.valueOf(bArr.length));
            }
        }
        map.putAll(this.mRequestHeaders);
        return map;
    }

    private String buildRequestUrl(HttpContext httpContext, String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap(this.mQueryParameters);
        linkedHashMap.put(P_REQUEST_TIME, "" + System.currentTimeMillis());
        linkedHashMap.put(P_SYN, httpContext.ID);
        return buildRequestUrl(str, this.mPath, linkedHashMap);
    }

    private boolean canRetryForHosts(InternalError internalError) {
        return internalError.codeEquals(InternalError.EUNKNOWNHOST) || internalError.codeEquals(InternalError.ECONNRESET) || internalError.codeEquals(InternalError.ECONNECT) || (internalError.getCode() >= 1400 && internalError.getCode() < 1500);
    }

    private boolean canRetryForProxy(InternalError internalError) {
        return internalError.codeEquals(InternalError.ECONNECT) || internalError.codeEquals(InternalError.ETIMEOUT);
    }

    private static void close(HttpURLConnection httpURLConnection) {
        if (httpURLConnection != null) {
            try {
                httpURLConnection.disconnect();
            } catch (Throwable unused) {
            }
        }
    }

    private void closeResource(HttpResource httpResource) {
        if (httpResource != null) {
            close(httpResource.mOutput);
            close(httpResource.mInput);
            close(httpResource.mConnection);
            httpResource.mOutput = null;
            httpResource.mInput = null;
            httpResource.mConnection = null;
        }
    }

    private static HttpURLConnection createHttpURLConnection(String str, InetSocketAddress inetSocketAddress) throws Exception {
        URLConnection uRLConnectionOpenConnection;
        SSLSocketFactory socketFactory;
        URLConnection uRLConnectionOpenConnection2;
        HttpURLConnection httpURLConnection;
        if (inetSocketAddress != null) {
            Proxy proxy = new Proxy(Proxy.Type.HTTP, inetSocketAddress);
            URL url = new URL(str);
            if (str.toLowerCase().startsWith("https")) {
                TrustManager[] trustManagerArr = {new NoneX509TrustManager()};
                SSLContext sSLContext = SSLContext.getInstance("SSL");
                sSLContext.init(null, trustManagerArr, new SecureRandom());
                socketFactory = sSLContext.getSocketFactory();
                uRLConnectionOpenConnection2 = url.openConnection(proxy);
                HttpsURLConnection httpsURLConnection = (HttpsURLConnection) uRLConnectionOpenConnection2;
                httpsURLConnection.setSSLSocketFactory(socketFactory);
                httpsURLConnection.connect();
                httpURLConnection = httpsURLConnection;
            } else {
                uRLConnectionOpenConnection = url.openConnection(proxy);
                httpURLConnection = (HttpURLConnection) uRLConnectionOpenConnection;
            }
        } else {
            URL url2 = new URL(str);
            if (str.toLowerCase().startsWith("https")) {
                TrustManager[] trustManagerArr2 = {new NoneX509TrustManager()};
                SSLContext sSLContext2 = SSLContext.getInstance("SSL");
                sSLContext2.init(null, trustManagerArr2, new SecureRandom());
                socketFactory = sSLContext2.getSocketFactory();
                uRLConnectionOpenConnection2 = url2.openConnection();
                HttpsURLConnection httpsURLConnection2 = (HttpsURLConnection) uRLConnectionOpenConnection2;
                httpsURLConnection2.setSSLSocketFactory(socketFactory);
                httpsURLConnection2.connect();
                httpURLConnection = httpsURLConnection2;
            } else {
                uRLConnectionOpenConnection = url2.openConnection();
                httpURLConnection = (HttpURLConnection) uRLConnectionOpenConnection;
            }
        }
        httpURLConnection.setConnectTimeout(30000);
        httpURLConnection.setReadTimeout(30000);
        httpURLConnection.setInstanceFollowRedirects(false);
        return httpURLConnection;
    }

    private InternalError doRecvAndCallback(HttpResource httpResource, long j, OnBlockListener onBlockListener) throws Exception {
        byte[] bArr = new byte[65536];
        InputStream inputStream = httpResource.mInput;
        long j2 = 0;
        while (true) {
            int i = inputStream.read(bArr);
            if (-1 == i) {
                if (j > 0 && j2 != j) {
                    return new InternalError(InternalError.EDATARECEIVE, "length mismatch");
                }
                return new InternalError(0);
            }
            if (i > 0) {
                long j3 = i;
                j2 += j3;
                if (!onBlockListener.onBlock(bArr, j3)) {
                    return new InternalError(InternalError.ECLIENTCALLBACK, "callback error");
                }
            }
        }
    }

    private InternalError downloadBreakpoint(HttpContext httpContext, ProxyManager.ProxyNode proxyNode, long j, OnBlockListener onBlockListener) throws Exception {
        long jCurrentTimeMillis;
        InternalError internalError;
        try {
            try {
                jCurrentTimeMillis = System.currentTimeMillis();
                try {
                    Map<String, String> mapBuildBrokenRequestHeaders = buildBrokenRequestHeaders(j, -1L);
                    HttpResource httpResourceSendRequest = sendRequest(httpContext, proxyNode, METHOD_GET, mapBuildBrokenRequestHeaders, null);
                    long jCurrentTimeMillis2 = System.currentTimeMillis() - jCurrentTimeMillis;
                    long jCurrentTimeMillis3 = System.currentTimeMillis();
                    if (SUCCESS(httpResourceSendRequest.mResponseCode)) {
                        BrokenRange responseBrokenRange = parseResponseBrokenRange(httpResourceSendRequest.mConnection);
                        if (onBlockListener.onStart(httpContext.mRealRequestUrl, responseBrokenRange.mOffset, responseBrokenRange.mBodyLength, responseBrokenRange.mTotal)) {
                            internalError = doRecvAndCallback(httpResourceSendRequest, responseBrokenRange.mBodyLength, onBlockListener);
                            long jCurrentTimeMillis4 = System.currentTimeMillis() - jCurrentTimeMillis3;
                            if (internalError.codeEquals(0)) {
                                I("[NO:" + httpContext.ID + "] download done: target: " + httpContext.mTargetHost + ", respMillis: " + jCurrentTimeMillis2 + ", recvMillis: " + jCurrentTimeMillis4 + ", (" + httpResourceSendRequest.mResponseCode + " " + httpResourceSendRequest.mResponseMessage + ")");
                            } else {
                                E("[NO:" + httpContext.ID + "] download failed: target: " + httpContext.mTargetHost + ", " + internalError);
                            }
                        } else {
                            E("[NO:" + httpContext.ID + "] download: target: " + httpContext.mTargetHost + ", callback failed");
                            internalError = new InternalError(InternalError.ECLIENTCALLBACK, "callback error");
                        }
                    } else {
                        E("[NO:" + httpContext.ID + "] download failed: target: " + httpContext.mTargetHost + ", respMillis: " + jCurrentTimeMillis2 + "\n" + printHeaders(mapBuildBrokenRequestHeaders) + "\n" + httpResourceSendRequest.mResponseCode + " " + httpResourceSendRequest.mResponseMessage + "\n" + printHeaders(getResponseHeaders(httpResourceSendRequest)));
                        int i = httpResourceSendRequest.mResponseCode + InternalError.EHTTPSTATUSOFFSET;
                        StringBuilder sb = new StringBuilder();
                        sb.append(httpResourceSendRequest.mResponseCode);
                        sb.append(" ");
                        sb.append(httpResourceSendRequest.mResponseMessage);
                        internalError = new InternalError(i, sb.toString());
                    }
                    closeResource(httpResourceSendRequest);
                    return internalError;
                } catch (IOException e) {
                    e = e;
                    long jCurrentTimeMillis5 = System.currentTimeMillis() - jCurrentTimeMillis;
                    InternalError internalErrorExceptionToError = exceptionToError(e);
                    E("[NO:" + httpContext.ID + "] download failed: respMillis: " + jCurrentTimeMillis5 + ", exception: ", e);
                    closeResource(null);
                    return internalErrorExceptionToError;
                }
            } catch (Throwable th) {
                closeResource(null);
                throw th;
            }
        } catch (IOException e2) {
            e = e2;
            jCurrentTimeMillis = 0;
        }
    }

    private InternalError downloadForProxy(HttpContext httpContext, String str, long j, OnBlockListener onBlockListener) throws Exception {
        ProxyManager.ProxyNode node;
        httpContext.mRequestUrl = str;
        InternalError internalErrorDownloadBreakpoint = downloadBreakpoint(httpContext, null, j, onBlockListener);
        if (!canRetryForProxy(internalErrorDownloadBreakpoint) || (node = ProxyManager.getNode(this.mContext)) == null) {
            return internalErrorDownloadBreakpoint;
        }
        I("[NO:" + httpContext.ID + "] download proxy: " + node + ", continue: " + internalErrorDownloadBreakpoint);
        httpContext.mRequestUrl = str;
        return downloadBreakpoint(httpContext, node, j, onBlockListener);
    }

    private InternalError exceptionToError(Exception exc) {
        if (exc instanceof ConnectException) {
            return new InternalError(InternalError.ECONNECT, exc.getMessage());
        }
        if (exc instanceof SocketTimeoutException) {
            return new InternalError(InternalError.ETIMEOUT, exc.getMessage());
        }
        if (exc instanceof UnknownHostException) {
            return new InternalError(InternalError.EUNKNOWNHOST, exc.getMessage());
        }
        return exc instanceof IOException ? new InternalError(getIOCode((IOException) exc), exc.getMessage()) : new InternalError(100, exc.getMessage());
    }

    private HTTPError fromInternalError(InternalError internalError) {
        if (internalError == null) {
            return HTTPError.fail(100);
        }
        if (internalError.codeEquals(0)) {
            return HTTPError.ok();
        }
        if (internalError.codeEquals(100) || internalError.codeEquals(101) || internalError.codeEquals(InternalError.EBROKENPROTOCOL) || internalError.codeEquals(InternalError.ECLIENTCALLBACK)) {
            return HTTPError.fail(100, "" + internalError);
        }
        if (internalError.codeEquals(InternalError.EDATARECEIVE) || internalError.codeEquals(InternalError.EIOEXCEPTION) || internalError.codeEquals(InternalError.EUNKNOWNHOST) || internalError.codeEquals(InternalError.ECONNRESET) || internalError.codeEquals(InternalError.ECONNECT) || internalError.codeEquals(InternalError.ETIMEOUT)) {
            return HTTPError.fail(101, "" + internalError);
        }
        if (internalError.getCode() < 1000 || internalError.getCode() - InternalError.EHTTPSTATUSOFFSET < 500) {
            return HTTPError.fail(100, "" + internalError);
        }
        return HTTPError.fail(101, "" + internalError);
    }

    private byte[] getBodyAsByteArray(HttpResource httpResource) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        InputStream inputStream = httpResource.mInput;
        byte[] bArr = new byte[8092];
        while (true) {
            int i = inputStream.read(bArr);
            if (-1 == i) {
                return byteArrayOutputStream.toByteArray();
            }
            if (i > 0) {
                byteArrayOutputStream.write(bArr, 0, i);
            }
        }
    }

    private String getBodyAsString(HttpResource httpResource) {
        try {
            return printResponseBody(getBodyAsByteArray(httpResource));
        } catch (Throwable th) {
            return "exception: " + th;
        }
    }

    private long getContentLength(HttpURLConnection httpURLConnection) {
        String headerField = httpURLConnection.getHeaderField("Content-Length");
        try {
            if (TextUtils.empty(headerField)) {
                return -1L;
            }
            return Long.parseLong(headerField);
        } catch (Throwable unused) {
            return -1L;
        }
    }

    private static HttpURLConnection getHttpURLConnection(String str, ProxyManager.ProxyNode proxyNode) throws Exception {
        return proxyNode == null ? createHttpURLConnection(str, null) : createHttpURLConnection(str, new InetSocketAddress(proxyNode.getProxy(), proxyNode.getPort()));
    }

    private int getIOCode(IOException iOException) {
        return TextUtils.contains(iOException.getMessage(), "ECONNRESET") ? InternalError.ECONNRESET : InternalError.EIOEXCEPTION;
    }

    private String getLocation(HttpURLConnection httpURLConnection) {
        String headerField = httpURLConnection.getHeaderField(HTTPHelper.HEADER_LOCATION);
        return headerField != null ? headerField : "";
    }

    private Map<String, List<String>> getResponseHeaders(HttpResource httpResource) {
        try {
            return httpResource.mConnection.getHeaderFields();
        } catch (Throwable unused) {
            return new HashMap();
        }
    }

    private BrokenRange parseResponseBrokenRange(HttpURLConnection httpURLConnection) throws Exception {
        String headerField = httpURLConnection.getHeaderField("Content-Range");
        if (TextUtils.empty(headerField)) {
            long contentLength = getContentLength(httpURLConnection);
            return new BrokenRange(0L, contentLength, contentLength);
        }
        try {
            String strReplaceAll = headerField.replaceAll(" ", "");
            long j = Long.parseLong(strReplaceAll.substring(strReplaceAll.indexOf("bytes") + 5, strReplaceAll.indexOf("-")));
            return new BrokenRange(j, (Long.parseLong(strReplaceAll.substring(strReplaceAll.indexOf("-") + 1, strReplaceAll.indexOf("/"))) - j) + 1, Long.parseLong(strReplaceAll.substring(strReplaceAll.indexOf("/") + 1)));
        } catch (Exception e) {
            throw new Exception("parse range(" + headerField + ") failed: " + e);
        }
    }

    private <T> String printHeaders(Map<String, T> map) {
        StringBuilder sb = new StringBuilder();
        boolean z = true;
        for (Map.Entry<String, T> entry : map.entrySet()) {
            if (z) {
                z = false;
            } else {
                sb.append("\n");
            }
            sb.append(entry.getKey());
            sb.append(": ");
            sb.append(entry.getValue());
        }
        return sb.toString();
    }

    private String printResponseBody(byte[] bArr) {
        if (bArr == null) {
            return "";
        }
        try {
            if (bArr.length <= 512) {
                return new String(bArr, 0, 512, StandardCharsets.UTF_8);
            }
            return new String(bArr, 0, 512, StandardCharsets.UTF_8) + " ...too much(" + bArr.length + ")";
        } catch (Throwable th) {
            return "exception: " + th;
        }
    }

    private InternalError requestWithHost(HttpContext httpContext, String str, String str2, byte[] bArr) throws Exception {
        ProxyManager.ProxyNode node;
        httpContext.mRequestUrl = buildRequestUrl(httpContext, str);
        InternalError internalErrorSendRequestAndGetResponse = sendRequestAndGetResponse(httpContext, null, str2, bArr);
        if (!canRetryForProxy(internalErrorSendRequestAndGetResponse) || (node = ProxyManager.getNode(this.mContext)) == null) {
            return internalErrorSendRequestAndGetResponse;
        }
        I("[NO:" + httpContext.ID + "] http proxy: " + node + ", continue: " + internalErrorSendRequestAndGetResponse);
        httpContext.mRequestUrl = buildRequestUrl(httpContext, str);
        return sendRequestAndGetResponse(httpContext, node, str2, bArr);
    }

    private InternalError requestWithHosts(HttpContext httpContext, String str, byte[] bArr) throws Exception {
        if (ObjUtils.empty(this.mHosts)) {
            return new InternalError(101, "empty hosts");
        }
        InternalError internalError = null;
        for (String str2 : this.mHosts) {
            httpContext.mTargetHost = str2;
            InternalError internalErrorRequestWithHost = requestWithHost(httpContext, str2, str, bArr);
            if (!canRetryForHosts(internalErrorRequestWithHost)) {
                return internalErrorRequestWithHost;
            }
            I("[NO:" + httpContext.ID + "][" + str2 + "] http failed: " + internalErrorRequestWithHost + ", continue ...");
            internalError = internalErrorRequestWithHost;
        }
        return internalError;
    }

    private InternalError requestWithUrl(HttpContext httpContext, String str, String str2, byte[] bArr) throws Exception {
        ProxyManager.ProxyNode node;
        httpContext.mRequestUrl = str;
        InternalError internalErrorSendRequestAndGetResponse = sendRequestAndGetResponse(httpContext, null, str2, bArr);
        if (!canRetryForProxy(internalErrorSendRequestAndGetResponse) || (node = ProxyManager.getNode(this.mContext)) == null) {
            return internalErrorSendRequestAndGetResponse;
        }
        I("[NO:" + httpContext.ID + "] http proxy: " + node + ", continue: " + internalErrorSendRequestAndGetResponse);
        httpContext.mRequestUrl = str;
        return sendRequestAndGetResponse(httpContext, node, str2, bArr);
    }

    private HttpResource sendRequest(HttpContext httpContext, ProxyManager.ProxyNode proxyNode, String str, Map<String, String> map, byte[] bArr) throws Exception {
        httpContext.mRealRequestUrl = httpContext.mRequestUrl;
        I("[NO:" + httpContext.ID + "] http request: url=" + httpContext.mRealRequestUrl);
        int i = -1;
        int i2 = 0;
        HttpURLConnection httpURLConnection = null;
        OutputStream outputStreamSendRequestBody = null;
        String str2 = null;
        while (i2 < 5) {
            HttpURLConnection httpURLConnection2 = getHttpURLConnection(httpContext.mRealRequestUrl, proxyNode);
            httpURLConnection2.setRequestMethod(str);
            httpURLConnection2.setConnectTimeout(this.mTimeout);
            httpURLConnection2.setReadTimeout(this.mTimeout);
            httpURLConnection2.setDoInput(true);
            setRequestHeaders(httpURLConnection2, map);
            if (TextUtils.equalsIgnoreCase(str, METHOD_POST)) {
                httpURLConnection2.setDoOutput(true);
                outputStreamSendRequestBody = sendRequestBody(httpURLConnection2, bArr);
            }
            int responseCode = httpURLConnection2.getResponseCode();
            String responseMessage = httpURLConnection2.getResponseMessage();
            if (REDIRECT(responseCode)) {
                String location = getLocation(httpURLConnection2);
                if (TextUtils.empty(location)) {
                    I("[NO:" + httpContext.ID + "] http redirect: " + responseCode + " " + responseMessage + ", but empty location");
                } else {
                    close(outputStreamSendRequestBody);
                    close(httpURLConnection2);
                    I("[NO:" + httpContext.ID + "] http redirect: " + responseCode + " " + responseMessage + ", location=" + location);
                    httpContext.mRealRequestUrl = location;
                    i2++;
                    httpURLConnection = httpURLConnection2;
                    i = responseCode;
                    str2 = responseMessage;
                }
            }
            httpURLConnection = httpURLConnection2;
            i = responseCode;
            str2 = responseMessage;
        }
        HttpResource httpResource = new HttpResource();
        httpResource.mConnection = httpURLConnection;
        httpResource.mOutput = outputStreamSendRequestBody;
        httpResource.mResponseCode = i;
        httpResource.mResponseMessage = str2;
        httpResource.mInput = SUCCESS(i) ? httpURLConnection.getInputStream() : httpURLConnection.getErrorStream();
        return httpResource;
    }

    private InternalError sendRequestAndGetResponse(HttpContext httpContext, ProxyManager.ProxyNode proxyNode, String str, byte[] bArr) throws Exception {
        long jCurrentTimeMillis;
        InternalError internalError;
        try {
            try {
                jCurrentTimeMillis = System.currentTimeMillis();
                try {
                    Map<String, String> mapBuildRequestHeaders = buildRequestHeaders(str, bArr);
                    HttpResource httpResourceSendRequest = sendRequest(httpContext, proxyNode, str, mapBuildRequestHeaders, bArr);
                    setRemoteAddress(httpContext, httpResourceSendRequest);
                    long jCurrentTimeMillis2 = System.currentTimeMillis() - jCurrentTimeMillis;
                    long jCurrentTimeMillis3 = System.currentTimeMillis();
                    if (SUCCESS(httpResourceSendRequest.mResponseCode)) {
                        httpContext.mResponseBody = getBodyAsByteArray(httpResourceSendRequest);
                        long jCurrentTimeMillis4 = System.currentTimeMillis() - jCurrentTimeMillis3;
                        internalError = new InternalError(0);
                        I("[NO:" + httpContext.ID + "] http done: target: " + httpContext.mTargetHost + ", respMillis: " + jCurrentTimeMillis2 + ", recvMillis: " + jCurrentTimeMillis4 + ", length: " + getContentLength(httpResourceSendRequest.mConnection) + ", " + httpResourceSendRequest.mResponseCode + " " + httpResourceSendRequest.mResponseMessage);
                    } else {
                        InternalError internalError2 = new InternalError(httpResourceSendRequest.mResponseCode + InternalError.EHTTPSTATUSOFFSET, httpResourceSendRequest.mResponseCode + " " + httpResourceSendRequest.mResponseMessage);
                        E("[NO:" + httpContext.ID + "] http failed: target: " + httpContext.mTargetHost + ", respMillis: " + jCurrentTimeMillis2 + "\n" + printHeaders(mapBuildRequestHeaders) + "\n" + httpResourceSendRequest.mResponseCode + " " + httpResourceSendRequest.mResponseMessage + "\n" + printHeaders(getResponseHeaders(httpResourceSendRequest)) + "\n\n" + getBodyAsString(httpResourceSendRequest));
                        internalError = internalError2;
                    }
                    closeResource(httpResourceSendRequest);
                    return internalError;
                } catch (IOException e) {
                    e = e;
                    long jCurrentTimeMillis5 = System.currentTimeMillis() - jCurrentTimeMillis;
                    InternalError internalErrorExceptionToError = exceptionToError(e);
                    E("[NO:" + httpContext.ID + "] http failed: resp=" + jCurrentTimeMillis5 + ", e=" + e, e);
                    closeResource(null);
                    return internalErrorExceptionToError;
                }
            } catch (Throwable th) {
                closeResource(null);
                throw th;
            }
        } catch (IOException e2) {
            e = e2;
            jCurrentTimeMillis = 0;
        }
    }

    private OutputStream sendRequestBody(HttpURLConnection httpURLConnection, byte[] bArr) throws IOException {
        if (TextUtils.empty(bArr)) {
            return null;
        }
        OutputStream outputStream = httpURLConnection.getOutputStream();
        outputStream.write(bArr);
        outputStream.flush();
        return outputStream;
    }

    private void setRemoteAddress(HttpContext httpContext, HttpResource httpResource) {
    }

    private void setRequestHeaders(HttpURLConnection httpURLConnection, Map<String, String> map) {
        for (String str : map.keySet()) {
            httpURLConnection.setRequestProperty(str, map.get(str));
        }
    }

    public synchronized HTTPError download(String str, long j) {
        this.mHttpContext = new HttpContext();
        I("[NO:" + this.mHttpContext.ID + "] download: url: " + str + ", offset=" + j);
        try {
        } catch (Throwable th) {
            E("[NO:" + this.mHttpContext.ID + "] download failed", th);
            return HTTPError.fail(100, "" + th);
        }
        return fromInternalError(downloadForProxy(this.mHttpContext, str, j, this.mOnBlockListener));
    }

    public HTTPError get() {
        this.mHttpContext = new HttpContext();
        I("[NO:" + this.mHttpContext.ID + "] http(GET) hosts: " + this.mHosts + ", path=" + this.mPath);
        try {
            return fromInternalError(requestWithHosts(this.mHttpContext, METHOD_GET, null));
        } catch (Throwable th) {
            E("[NO:" + this.mHttpContext.ID + "] http(GET) failed", th);
            return HTTPError.fail(100, "" + th);
        }
    }

    public String getRealRequestUrl() {
        HttpContext httpContext = this.mHttpContext;
        return httpContext != null ? httpContext.mRealRequestUrl : "";
    }

    public String getRequestUrl() {
        HttpContext httpContext = this.mHttpContext;
        return httpContext != null ? httpContext.mRequestUrl : "";
    }

    public byte[] getResponseBody() {
        HttpContext httpContext = this.mHttpContext;
        return httpContext != null ? httpContext.mResponseBody : new byte[0];
    }

    public String getTargetHost() {
        HttpContext httpContext = this.mHttpContext;
        return httpContext != null ? httpContext.mTargetHost : "";
    }

    public HTTPError post() {
        this.mHttpContext = new HttpContext();
        I("[NO:" + this.mHttpContext.ID + "] http(POST) hosts: " + this.mHosts + ", path=" + this.mPath);
        try {
            return fromInternalError(requestWithHosts(this.mHttpContext, METHOD_POST, this.mRequestBody));
        } catch (Throwable th) {
            E("[NO:" + this.mHttpContext.ID + "] http(s)(POST) failed", th);
            return HTTPError.fail(100, "" + th);
        }
    }

    public synchronized void putQueryParameter(String str, String str2) {
        if (!TextUtils.empty(str) && !TextUtils.empty(str2)) {
            this.mQueryParameters.put(str, str2);
        }
    }

    public synchronized void putQueryParameters(Map<String, String> map) {
        this.mQueryParameters.putAll(map);
    }

    public synchronized void putRequestHeader(String str, String str2) {
        if (!TextUtils.empty(str) && !TextUtils.empty(str2)) {
            this.mRequestHeaders.put(str, str2);
        }
    }

    public synchronized void setBlockListener(OnBlockListener onBlockListener) {
        this.mOnBlockListener = onBlockListener;
    }

    public synchronized void setHosts(List<String> list) {
        this.mHosts = list;
    }

    public void setLogEnabled(boolean z) {
        this.mLogEnabled = z;
    }

    public synchronized void setPath(String str) {
        this.mPath = str;
    }

    public void setProtocol(String str) {
        this.mProtocol = str;
    }

    public synchronized void setRequestBody(byte[] bArr) {
        this.mRequestBody = bArr;
    }

    public void setTimeout(int i) {
        if (i > 0) {
            this.mTimeout = i;
        }
    }

    public synchronized void setUserAgent(String str) {
        if (!TextUtils.empty(str)) {
            this.mRequestHeaders.put("User-Agent", str);
        }
    }

    private void E(String str, Throwable th) {
        if (this.mLogEnabled) {
            LOG.e(TAG, str, th);
        }
    }

    private String buildRequestUrl(String str, String str2, Map<String, String> map) {
        if (!str.startsWith("http")) {
            str = this.mProtocol + str;
        }
        Uri.Builder builderBuildUpon = Uri.parse(str).buildUpon();
        builderBuildUpon.path("").appendEncodedPath(str2);
        for (Map.Entry<String, String> entry : map.entrySet()) {
            builderBuildUpon.appendQueryParameter(entry.getKey(), entry.getValue());
        }
        return builderBuildUpon.build().toString();
    }

    private static void close(Closeable... closeableArr) {
        if (closeableArr != null) {
            for (Closeable closeable : closeableArr) {
                if (closeable != null) {
                    try {
                        closeable.close();
                    } catch (Throwable unused) {
                    }
                }
            }
        }
    }

    public HTTPError get(String str) {
        this.mHttpContext = new HttpContext();
        I("[NO:" + this.mHttpContext.ID + "] http(GET) url: " + str);
        try {
            return fromInternalError(requestWithUrl(this.mHttpContext, str, METHOD_GET, null));
        } catch (Throwable th) {
            E("[NO:" + this.mHttpContext.ID + "] http(GET) failed", th);
            return HTTPError.fail(100, "" + th);
        }
    }

    public static HTTPHelper2 get(Context context) {
        return new HTTPHelper2(context);
    }
}
