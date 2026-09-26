package com.hs.p.common.http;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import com.hs.p.common.utils.LOG;
import com.hs.p.common.utils.ObjUtils;
import com.hs.p.common.utils.TextUtils;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SNIHostName;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/* JADX INFO: loaded from: /Users/ruben/projector-dump/downloads/plugin.jar */
public class HTTPHelper {
    public static final String CHARSET_UTF8 = "UTF-8";
    public static final String CONTENT_TYPE_BINARY = "application/octet-stream";
    public static final String CONTENT_TYPE_JSON = "application/json";
    public static final String HEADER_CHARSET = "Charset";
    public static final String HEADER_CONTENT_LENGTH = "Content-Length";
    public static final String HEADER_CONTENT_RANGE = "Content-Range";
    public static final String HEADER_CONTENT_TYPE = "Content-Type";
    public static final String HEADER_LOCATION = "Location";
    public static final String HEADER_RANGE = "Range";
    public static final String HEADER_USER_AGENT = "User-Agent";
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
    private String mSniHostname = null;
    private String mHttpDnsIP = null;

    private static class BreakpointRange {
        public final long mLength;
        public final long mOffset;
        public final long mTotal;

        public BreakpointRange(long j, long j2, long j3) {
            this.mOffset = j;
            this.mLength = j2;
            this.mTotal = j3;
        }
    }

    private static class HttpContext {
        private String ID;
        private String mRealRequestUrl;
        private byte[] mResponseBody;
        private String mTargetHost;

        private HttpContext() {
            this.ID = "" + HTTPHelper.gCounter.getAndIncrement();
            this.mRealRequestUrl = null;
            this.mResponseBody = null;
            this.mTargetHost = null;
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

    private HTTPHelper(Context context) {
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

    private void addBrokenRequestHeader(Map<String, String> map, long j, long j2) {
        String str;
        if (j > 0) {
            if (j2 > 0) {
                str = "bytes=" + j + "-" + ((j2 + j) - 1);
            } else {
                str = "bytes=" + j + "-";
            }
            map.put(HEADER_RANGE, str);
        }
    }

    private Map<String, String> buildRequestHeaders(String str, Map<String, String> map, byte[] bArr) {
        HashMap map2 = new HashMap();
        if (TextUtils.equalsIgnoreCase(str, METHOD_POST)) {
            map2.put(HEADER_CHARSET, CHARSET_UTF8);
            map2.put(HEADER_CONTENT_TYPE, CONTENT_TYPE_BINARY);
            if (bArr != null) {
                map2.put(HEADER_CONTENT_LENGTH, String.valueOf(bArr.length));
            }
        }
        map2.putAll(map);
        return map2;
    }

    private String buildRequestUrl(String str, String str2, String str3, String str4, Map<String, String> map) {
        LinkedHashMap linkedHashMap = new LinkedHashMap(map);
        linkedHashMap.put(P_REQUEST_TIME, "" + System.currentTimeMillis());
        return buildRequestUrl(str2, str3, str4, linkedHashMap);
    }

    private boolean checkRetryForHosts(InternalError internalError) {
        return !internalError.codeEquals(0);
    }

    private boolean checkRetryForProxy(InternalError internalError) {
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

    private HttpURLConnection createConnection(ProxyManager.ProxyNode proxyNode, String str, String str2, Map<String, String> map) throws Exception {
        HttpURLConnection httpURLConnection;
        String str3 = this.mHttpDnsIP;
        if (str3 == null || str3.isEmpty() || !str2.toLowerCase().startsWith("https")) {
            httpURLConnection = getHttpURLConnection(str2, proxyNode);
        } else {
            httpURLConnection = getHttpURLConnectionWithHttpDns(str2, proxyNode, this.mHttpDnsIP);
            LOG.d(TAG, "Using HTTPDNS IP: " + this.mHttpDnsIP + " for URL: " + str2);
        }
        httpURLConnection.setRequestMethod(str);
        httpURLConnection.setConnectTimeout(this.mTimeout);
        httpURLConnection.setReadTimeout(this.mTimeout);
        httpURLConnection.setDoInput(true);
        setRequestHeaders(httpURLConnection, map);
        return httpURLConnection;
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

    private SSLSocketFactory createSNISSLSocketFactory(final String str) throws Exception {
        TrustManager[] trustManagerArr = {new NoneX509TrustManager()};
        SSLContext sSLContext = SSLContext.getInstance("TLSv1.2");
        sSLContext.init(null, trustManagerArr, new SecureRandom());
        final SSLSocketFactory socketFactory = sSLContext.getSocketFactory();
        return new SSLSocketFactory() { // from class: com.hs.p.common.http.HTTPHelper.2
            private void enableSNI(SSLSocket sSLSocket, String str2) {
                if (sSLSocket == null || str2 == null || str2.isEmpty()) {
                    return;
                }
                LOG.d(HTTPHelper.TAG, "enableSNI: socket class = " + sSLSocket.getClass().getName());
                try {
                    LOG.d(HTTPHelper.TAG, "Supported protocols: " + Arrays.toString(sSLSocket.getSupportedProtocols()));
                    sSLSocket.setEnabledProtocols(new String[]{"TLSv1.2", "TLSv1.3"});
                    for (Class<?> superclass = sSLSocket.getClass(); superclass != null; superclass = superclass.getSuperclass()) {
                        try {
                            Method declaredMethod = superclass.getDeclaredMethod("setHostname", String.class);
                            declaredMethod.setAccessible(true);
                            declaredMethod.invoke(sSLSocket, str2);
                            LOG.d(HTTPHelper.TAG, "Set SNI via " + superclass.getSimpleName() + ".setHostname: " + str2);
                            return;
                        } catch (NoSuchMethodException unused) {
                        }
                    }
                    if (Build.VERSION.SDK_INT >= 24) {
                        SSLParameters sSLParameters = sSLSocket.getSSLParameters();
                        ArrayList arrayList = new ArrayList();
                        arrayList.add(new SNIHostName(str2));
                        sSLParameters.setServerNames(arrayList);
                        sSLSocket.setSSLParameters(sSLParameters);
                        LOG.d(HTTPHelper.TAG, "Set SNI via SSLParameters: " + str2);
                    }
                } catch (Exception e) {
                    LOG.w(HTTPHelper.TAG, "Error setting SNI: " + e.getMessage(), e);
                }
            }

            @Override // javax.net.SocketFactory
            public Socket createSocket(String str2, int i) throws IOException {
                SSLSocket sSLSocket = (SSLSocket) socketFactory.createSocket(str2, i);
                enableSNI(sSLSocket, str);
                return sSLSocket;
            }

            @Override // javax.net.ssl.SSLSocketFactory
            public String[] getDefaultCipherSuites() {
                return socketFactory.getDefaultCipherSuites();
            }

            @Override // javax.net.ssl.SSLSocketFactory
            public String[] getSupportedCipherSuites() {
                return socketFactory.getSupportedCipherSuites();
            }

            @Override // javax.net.SocketFactory
            public Socket createSocket(String str2, int i, InetAddress inetAddress, int i2) throws IOException {
                SSLSocket sSLSocket = (SSLSocket) socketFactory.createSocket(str2, i, inetAddress, i2);
                enableSNI(sSLSocket, str);
                return sSLSocket;
            }

            @Override // javax.net.SocketFactory
            public Socket createSocket(InetAddress inetAddress, int i) throws IOException {
                SSLSocket sSLSocket = (SSLSocket) socketFactory.createSocket(inetAddress, i);
                enableSNI(sSLSocket, str);
                return sSLSocket;
            }

            @Override // javax.net.SocketFactory
            public Socket createSocket(InetAddress inetAddress, int i, InetAddress inetAddress2, int i2) throws IOException {
                SSLSocket sSLSocket = (SSLSocket) socketFactory.createSocket(inetAddress, i, inetAddress2, i2);
                enableSNI(sSLSocket, str);
                return sSLSocket;
            }

            @Override // javax.net.ssl.SSLSocketFactory
            public Socket createSocket(Socket socket, String str2, int i, boolean z) throws IOException {
                String str3 = str;
                String str4 = (str3 == null || str3.isEmpty()) ? str2 : str;
                LOG.d(HTTPHelper.TAG, "createSocket with SNI hostname: " + str4 + " (original host: " + str2 + ")");
                SSLSocket sSLSocket = (SSLSocket) socketFactory.createSocket(socket, str4, i, z);
                enableSNI(sSLSocket, str);
                return sSLSocket;
            }
        };
    }

    private InternalError downloadBreakpoint(HttpContext httpContext, ProxyManager.ProxyNode proxyNode, String str, Map<String, String> map, long j, OnBlockListener onBlockListener) throws Exception {
        long jCurrentTimeMillis;
        InternalError internalError;
        try {
            try {
                jCurrentTimeMillis = System.currentTimeMillis();
                try {
                    addBrokenRequestHeader(map, j, -1L);
                    HttpResource httpResourceSendRequestRedirect = sendRequestRedirect(httpContext, proxyNode, METHOD_GET, str, map, null);
                    long jCurrentTimeMillis2 = System.currentTimeMillis() - jCurrentTimeMillis;
                    long jCurrentTimeMillis3 = System.currentTimeMillis();
                    if (isRequestOK(httpResourceSendRequestRedirect.mResponseCode)) {
                        BreakpointRange responseBreakpointRange = parseResponseBreakpointRange(httpResourceSendRequestRedirect.mConnection);
                        if (onBlockListener.onStart(httpContext.mRealRequestUrl, responseBreakpointRange.mOffset, responseBreakpointRange.mLength, responseBreakpointRange.mTotal)) {
                            internalError = readDataAndCallback(httpResourceSendRequestRedirect, responseBreakpointRange.mLength, onBlockListener);
                            long jCurrentTimeMillis4 = System.currentTimeMillis() - jCurrentTimeMillis3;
                            if (internalError.codeEquals(0)) {
                                I("[NO:" + httpContext.ID + "] download done: target=" + httpContext.mTargetHost + ", resp=" + jCurrentTimeMillis2 + ", recv=" + jCurrentTimeMillis4 + ", (" + httpResourceSendRequestRedirect.mResponseCode + " " + httpResourceSendRequestRedirect.mResponseMessage + ")");
                            } else {
                                E("[NO:" + httpContext.ID + "] download failed: target: " + httpContext.mTargetHost + ", " + internalError);
                            }
                        } else {
                            E("[NO:" + httpContext.ID + "] download failed: target=" + httpContext.mTargetHost + ", e=callback failed");
                            internalError = new InternalError(InternalError.ECLIENTCALLBACK, "callback error");
                        }
                    } else {
                        E("[NO:" + httpContext.ID + "] download failed: target=" + httpContext.mTargetHost + ", resp=" + jCurrentTimeMillis2 + "\n" + printHeaders(map) + "\n" + httpResourceSendRequestRedirect.mResponseCode + " " + httpResourceSendRequestRedirect.mResponseMessage + "\n" + printHeaders(getResponseHeaders(httpResourceSendRequestRedirect)));
                        int i = httpResourceSendRequestRedirect.mResponseCode + InternalError.EHTTPSTATUSOFFSET;
                        StringBuilder sb = new StringBuilder();
                        sb.append(httpResourceSendRequestRedirect.mResponseCode);
                        sb.append(" ");
                        sb.append(httpResourceSendRequestRedirect.mResponseMessage);
                        internalError = new InternalError(i, sb.toString());
                    }
                    closeResource(httpResourceSendRequestRedirect);
                    return internalError;
                } catch (IOException e) {
                    e = e;
                    long jCurrentTimeMillis5 = System.currentTimeMillis() - jCurrentTimeMillis;
                    InternalError internalError2 = getInternalError(e);
                    E("[NO:" + httpContext.ID + "] download failed: respMillis=" + jCurrentTimeMillis5 + ", e=" + e, e);
                    closeResource(null);
                    return internalError2;
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

    private InternalError downloadRequest(HttpContext httpContext, String str, Map<String, String> map, long j, OnBlockListener onBlockListener) throws Exception {
        ProxyManager.ProxyNode node;
        Map<String, String> mapBuildRequestHeaders = buildRequestHeaders(METHOD_GET, map, null);
        InternalError internalErrorDownloadBreakpoint = downloadBreakpoint(httpContext, null, str, mapBuildRequestHeaders, j, onBlockListener);
        if (!checkRetryForProxy(internalErrorDownloadBreakpoint) || (node = ProxyManager.getNode(this.mContext)) == null) {
            return internalErrorDownloadBreakpoint;
        }
        I("[NO:" + httpContext.ID + "] download failed, continue: proxy=" + node + ", e=" + internalErrorDownloadBreakpoint);
        return downloadBreakpoint(httpContext, node, str, mapBuildRequestHeaders, j, onBlockListener);
    }

    private HTTPError fromInternalError(InternalError internalError) {
        if (internalError == null) {
            return HTTPError.fail(100);
        }
        if (internalError.codeEquals(0)) {
            return HTTPError.ok();
        }
        if (internalError.codeEquals(100) || internalError.codeEquals(InternalError.EBROKENPROTOCOL) || internalError.codeEquals(InternalError.ECLIENTCALLBACK)) {
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

    public static HTTPHelper get(Context context) {
        return new HTTPHelper(context);
    }

    private long getContentLength(HttpURLConnection httpURLConnection) {
        String headerField = httpURLConnection.getHeaderField(HEADER_CONTENT_LENGTH);
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

    private HttpURLConnection getHttpURLConnectionWithHttpDns(String str, ProxyManager.ProxyNode proxyNode, final String str2) throws Exception {
        URL url = new URL(str);
        final String host = url.getHost();
        if (url.getPort() != -1) {
            url.getPort();
        }
        HttpsURLConnection httpsURLConnection = (HttpsURLConnection) (proxyNode != null ? url.openConnection(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyNode.getProxy(), proxyNode.getPort()))) : url.openConnection());
        TrustManager[] trustManagerArr = {new NoneX509TrustManager()};
        SSLContext sSLContext = SSLContext.getInstance("TLSv1.2");
        sSLContext.init(null, trustManagerArr, new SecureRandom());
        final SSLSocketFactory socketFactory = sSLContext.getSocketFactory();
        httpsURLConnection.setSSLSocketFactory(new SSLSocketFactory() { // from class: com.hs.p.common.http.HTTPHelper.1
            @Override // javax.net.SocketFactory
            public Socket createSocket(String str3, int i) throws IOException {
                LOG.d(HTTPHelper.TAG, "HTTPDNS createSocket: connecting to IP " + str2 + ":" + i + " with SNI " + host);
                Socket socket = new Socket();
                socket.connect(new InetSocketAddress(str2, i), HTTPHelper.this.mTimeout);
                return socketFactory.createSocket(socket, host, i, true);
            }

            @Override // javax.net.ssl.SSLSocketFactory
            public String[] getDefaultCipherSuites() {
                return socketFactory.getDefaultCipherSuites();
            }

            @Override // javax.net.ssl.SSLSocketFactory
            public String[] getSupportedCipherSuites() {
                return socketFactory.getSupportedCipherSuites();
            }

            @Override // javax.net.SocketFactory
            public Socket createSocket(String str3, int i, InetAddress inetAddress, int i2) throws IOException {
                LOG.d(HTTPHelper.TAG, "HTTPDNS createSocket with local: connecting to IP " + str2 + ":" + i);
                Socket socket = new Socket();
                socket.bind(new InetSocketAddress(inetAddress, i2));
                socket.connect(new InetSocketAddress(str2, i), HTTPHelper.this.mTimeout);
                return socketFactory.createSocket(socket, host, i, true);
            }

            @Override // javax.net.SocketFactory
            public Socket createSocket(InetAddress inetAddress, int i) throws IOException {
                LOG.d(HTTPHelper.TAG, "HTTPDNS createSocket by InetAddress: " + str2 + ":" + i);
                Socket socket = new Socket();
                socket.connect(new InetSocketAddress(str2, i), HTTPHelper.this.mTimeout);
                return socketFactory.createSocket(socket, host, i, true);
            }

            @Override // javax.net.SocketFactory
            public Socket createSocket(InetAddress inetAddress, int i, InetAddress inetAddress2, int i2) throws IOException {
                LOG.d(HTTPHelper.TAG, "HTTPDNS createSocket by InetAddress with local: " + str2 + ":" + i);
                Socket socket = new Socket();
                socket.bind(new InetSocketAddress(inetAddress2, i2));
                socket.connect(new InetSocketAddress(str2, i), HTTPHelper.this.mTimeout);
                return socketFactory.createSocket(socket, host, i, true);
            }

            @Override // javax.net.ssl.SSLSocketFactory
            public Socket createSocket(Socket socket, String str3, int i, boolean z) throws IOException {
                LOG.d(HTTPHelper.TAG, "HTTPDNS createSocket: host=" + str3 + " (will use for SNI)");
                return socketFactory.createSocket(socket, str3, i, z);
            }
        });
        httpsURLConnection.setHostnameVerifier(new NoneHostnameVerifier());
        httpsURLConnection.setConnectTimeout(this.mTimeout);
        httpsURLConnection.setReadTimeout(this.mTimeout);
        httpsURLConnection.setInstanceFollowRedirects(false);
        return httpsURLConnection;
    }

    private InternalError getInternalError(Exception exc) {
        if (exc instanceof ConnectException) {
            return new InternalError(InternalError.ECONNECT, exc.getMessage());
        }
        if (exc instanceof SocketTimeoutException) {
            return new InternalError(InternalError.ETIMEOUT, exc.getMessage());
        }
        if (exc instanceof UnknownHostException) {
            return new InternalError(InternalError.EUNKNOWNHOST, exc.getMessage());
        }
        if (exc instanceof IOException) {
            return TextUtils.contains(exc.getMessage(), "ECONNRESET") ? new InternalError(InternalError.ECONNRESET, exc.getMessage()) : new InternalError(InternalError.EIOEXCEPTION, exc.getMessage());
        }
        return new InternalError(100, exc.getMessage());
    }

    private String getLocation(HttpURLConnection httpURLConnection) {
        String headerField = httpURLConnection.getHeaderField(HEADER_LOCATION);
        return headerField != null ? headerField : "";
    }

    private Map<String, List<String>> getResponseHeaders(HttpResource httpResource) {
        try {
            return httpResource.mConnection.getHeaderFields();
        } catch (Throwable unused) {
            return new HashMap();
        }
    }

    private boolean isRequestOK(int i) {
        return i < 300 && i >= 200;
    }

    private boolean isRequestRedirect(int i) {
        return 301 == i || 302 == i;
    }

    private HTTPResult newHTTPResult(HttpContext httpContext, int i, String str) {
        return newHTTPResult(httpContext, HTTPError.fail(i, str), (InternalError) null);
    }

    private BreakpointRange parseResponseBreakpointRange(HttpURLConnection httpURLConnection) throws Exception {
        String headerField = httpURLConnection.getHeaderField(HEADER_CONTENT_RANGE);
        if (TextUtils.empty(headerField)) {
            long contentLength = getContentLength(httpURLConnection);
            return new BreakpointRange(0L, contentLength, contentLength);
        }
        try {
            String strReplaceAll = headerField.replaceAll(" ", "");
            long j = Long.parseLong(strReplaceAll.substring(strReplaceAll.indexOf("bytes") + 5, strReplaceAll.indexOf("-")));
            return new BreakpointRange(j, (Long.parseLong(strReplaceAll.substring(strReplaceAll.indexOf("-") + 1, strReplaceAll.indexOf("/"))) - j) + 1, Long.parseLong(strReplaceAll.substring(strReplaceAll.indexOf("/") + 1)));
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

    private String printResponseBody(HttpResource httpResource) {
        try {
            return subResponseBody(readBodyAsByteArray(httpResource), 512);
        } catch (Throwable th) {
            return "exception: " + th;
        }
    }

    private byte[] readBodyAsByteArray(HttpResource httpResource) throws IOException {
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

    private InternalError readDataAndCallback(HttpResource httpResource, long j, OnBlockListener onBlockListener) throws Exception {
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

    private HTTPResult requestWithHosts(HttpContext httpContext, String str, byte[] bArr) {
        String string;
        I("[NO:" + httpContext.ID + "] http: method=" + str + ", hosts=" + this.mHosts + ", path=" + this.mPath);
        if (ObjUtils.empty(this.mHosts)) {
            string = "empty hosts";
        } else if (ObjUtils.empty(this.mPath)) {
            string = "empty path";
        } else {
            try {
                return newHTTPResult(httpContext, requestWithHosts(httpContext, str, this.mProtocol, this.mHosts, this.mPath, this.mQueryParameters, this.mRequestHeaders, bArr));
            } catch (Throwable th) {
                E("[NO:" + httpContext.ID + "] http failed: e=" + th, th);
                StringBuilder sb = new StringBuilder();
                sb.append("");
                sb.append(th);
                string = sb.toString();
            }
        }
        return newHTTPResult(httpContext, 100, string);
    }

    private InternalError requestWithUrl(HttpContext httpContext, String str, String str2, Map<String, String> map, byte[] bArr) throws Exception {
        ProxyManager.ProxyNode node;
        Map<String, String> mapBuildRequestHeaders = buildRequestHeaders(str, map, bArr);
        InternalError internalErrorSendRequest = sendRequest(httpContext, null, str, str2, mapBuildRequestHeaders, bArr);
        if (!checkRetryForProxy(internalErrorSendRequest) || (node = ProxyManager.getNode(this.mContext)) == null) {
            return internalErrorSendRequest;
        }
        I("[NO:" + httpContext.ID + "] http failed, continue: proxy=" + node + ", e=" + internalErrorSendRequest);
        return sendRequest(httpContext, node, str, str2, mapBuildRequestHeaders, bArr);
    }

    private InternalError sendRequest(HttpContext httpContext, ProxyManager.ProxyNode proxyNode, String str, String str2, Map<String, String> map, byte[] bArr) throws Exception {
        long jCurrentTimeMillis;
        InternalError internalError;
        HttpResource httpResourceSendRequestRedirect = null;
        try {
            try {
                jCurrentTimeMillis = System.currentTimeMillis();
                try {
                    httpResourceSendRequestRedirect = sendRequestRedirect(httpContext, proxyNode, str, str2, map, bArr);
                    setRemoteAddress(httpContext, httpResourceSendRequestRedirect);
                    long jCurrentTimeMillis2 = System.currentTimeMillis() - jCurrentTimeMillis;
                    jCurrentTimeMillis = System.currentTimeMillis();
                    if (isRequestOK(httpResourceSendRequestRedirect.mResponseCode)) {
                        httpContext.mResponseBody = readBodyAsByteArray(httpResourceSendRequestRedirect);
                        long jCurrentTimeMillis3 = System.currentTimeMillis() - jCurrentTimeMillis;
                        internalError = new InternalError(0);
                        I("[NO:" + httpContext.ID + "] http done: target=" + httpContext.mTargetHost + ", resp=" + jCurrentTimeMillis2 + ", recv=" + jCurrentTimeMillis3 + ", length=" + getContentLength(httpResourceSendRequestRedirect.mConnection) + ", " + httpResourceSendRequestRedirect.mResponseCode + " " + httpResourceSendRequestRedirect.mResponseMessage);
                    } else {
                        InternalError internalError2 = new InternalError(httpResourceSendRequestRedirect.mResponseCode + InternalError.EHTTPSTATUSOFFSET, httpResourceSendRequestRedirect.mResponseCode + " " + httpResourceSendRequestRedirect.mResponseMessage);
                        E("[NO:" + httpContext.ID + "] http failed: target=" + httpContext.mTargetHost + ", resp=" + jCurrentTimeMillis2 + "\n" + printHeaders(map) + "\n" + httpResourceSendRequestRedirect.mResponseCode + " " + httpResourceSendRequestRedirect.mResponseMessage + "\n" + printHeaders(getResponseHeaders(httpResourceSendRequestRedirect)) + "\n\n" + printResponseBody(httpResourceSendRequestRedirect));
                        internalError = internalError2;
                    }
                } catch (IOException e) {
                    e = e;
                    long jCurrentTimeMillis4 = System.currentTimeMillis() - jCurrentTimeMillis;
                    internalError = getInternalError(e);
                    E("[NO:" + httpContext.ID + "] http failed: resp=" + jCurrentTimeMillis4 + ", e=" + e, e);
                }
            } finally {
                closeResource(httpResourceSendRequestRedirect);
            }
        } catch (IOException e2) {
            e = e2;
            jCurrentTimeMillis = 0;
        }
        return internalError;
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

    private HttpResource sendRequestRedirect(HttpContext httpContext, ProxyManager.ProxyNode proxyNode, String str, String str2, Map<String, String> map, byte[] bArr) throws Exception {
        httpContext.mRealRequestUrl = str2;
        I("[NO:" + httpContext.ID + "] http request: url=" + httpContext.mRealRequestUrl);
        int i = -1;
        int i2 = 0;
        HttpURLConnection httpURLConnection = null;
        OutputStream outputStreamSendRequestBody = null;
        String str3 = null;
        while (i2 < 5) {
            HttpURLConnection httpURLConnectionCreateConnection = createConnection(proxyNode, str, httpContext.mRealRequestUrl, map);
            if (TextUtils.equalsIgnoreCase(str, METHOD_POST)) {
                httpURLConnectionCreateConnection.setDoOutput(true);
                outputStreamSendRequestBody = sendRequestBody(httpURLConnectionCreateConnection, bArr);
            }
            int responseCode = httpURLConnectionCreateConnection.getResponseCode();
            String responseMessage = httpURLConnectionCreateConnection.getResponseMessage();
            if (isRequestRedirect(responseCode)) {
                String location = getLocation(httpURLConnectionCreateConnection);
                if (TextUtils.empty(location)) {
                    I("[NO:" + httpContext.ID + "] http redirect: " + responseCode + " " + responseMessage + ", but empty location");
                } else {
                    close(outputStreamSendRequestBody);
                    close(httpURLConnectionCreateConnection);
                    I("[NO:" + httpContext.ID + "] http redirect: " + responseCode + " " + responseMessage + ", location=" + location);
                    httpContext.mRealRequestUrl = location;
                    i2++;
                    httpURLConnection = httpURLConnectionCreateConnection;
                    i = responseCode;
                    str3 = responseMessage;
                }
            }
            httpURLConnection = httpURLConnectionCreateConnection;
            i = responseCode;
            str3 = responseMessage;
        }
        HttpResource httpResource = new HttpResource();
        httpResource.mConnection = httpURLConnection;
        httpResource.mOutput = outputStreamSendRequestBody;
        httpResource.mResponseCode = i;
        httpResource.mResponseMessage = str3;
        httpResource.mInput = isRequestOK(i) ? httpURLConnection.getInputStream() : httpURLConnection.getErrorStream();
        return httpResource;
    }

    private void setRemoteAddress(HttpContext httpContext, HttpResource httpResource) {
        httpContext.mTargetHost = "";
    }

    private void setRequestHeaders(HttpURLConnection httpURLConnection, Map<String, String> map) {
        for (String str : map.keySet()) {
            httpURLConnection.setRequestProperty(str, map.get(str));
        }
    }

    private String subResponseBody(byte[] bArr, int i) {
        if (bArr == null) {
            return "";
        }
        try {
            if (bArr.length <= i) {
                return new String(bArr, 0, i, StandardCharsets.UTF_8);
            }
            return new String(bArr, 0, i, StandardCharsets.UTF_8) + " ...too much(" + bArr.length + ")";
        } catch (Throwable th) {
            return "exception: " + th;
        }
    }

    public HTTPError download(String str, long j) {
        String string;
        HttpContext httpContext = new HttpContext();
        I("[NO:" + httpContext.ID + "] download: url=" + str + ", offset=" + j);
        if (ObjUtils.empty(str)) {
            string = "empty url";
        } else {
            try {
                return fromInternalError(downloadRequest(httpContext, str, this.mRequestHeaders, j, this.mOnBlockListener));
            } catch (Throwable th) {
                E("[NO:" + httpContext.ID + "] download failed", th);
                StringBuilder sb = new StringBuilder();
                sb.append("");
                sb.append(th);
                string = sb.toString();
            }
        }
        return HTTPError.fail(100, string);
    }

    public HTTPResult post() {
        return requestWithHosts(new HttpContext(), METHOD_POST, this.mRequestBody);
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

    public synchronized void setHttpDnsIP(String str) {
        this.mHttpDnsIP = str;
    }

    public synchronized void setLogEnabled(boolean z) {
        this.mLogEnabled = z;
    }

    public synchronized void setPath(String str) {
        this.mPath = str;
    }

    public synchronized void setProtocol(String str) {
        this.mProtocol = str;
    }

    public synchronized void setRequestBody(byte[] bArr) {
        this.mRequestBody = bArr;
    }

    public synchronized void setSniHostname(String str) {
        this.mSniHostname = str;
    }

    public synchronized void setTimeout(int i) {
        if (i > 0) {
            this.mTimeout = i;
        }
    }

    public synchronized void setUserAgent(String str) {
        if (!TextUtils.empty(str)) {
            this.mRequestHeaders.put(HEADER_USER_AGENT, str);
        }
    }

    private void E(String str, Throwable th) {
        if (this.mLogEnabled) {
            LOG.e(TAG, str, th);
        }
    }

    private String buildRequestUrl(String str, String str2, String str3, Map<String, String> map) {
        if (!str2.startsWith("http")) {
            str2 = str + str2;
        }
        Uri.Builder builderBuildUpon = Uri.parse(str2).buildUpon();
        builderBuildUpon.path("").appendEncodedPath(str3);
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

    private HTTPResult newHTTPResult(HttpContext httpContext, HTTPError hTTPError, InternalError internalError) {
        HTTPResult hTTPResult = new HTTPResult();
        hTTPResult.ID = httpContext.ID;
        hTTPResult.realRequestUrl = httpContext.mRealRequestUrl;
        hTTPResult.responseBody = httpContext.mResponseBody;
        hTTPResult.targetHost = httpContext.mTargetHost;
        hTTPResult.error = hTTPError;
        hTTPResult.internalError = internalError;
        return hTTPResult;
    }

    private InternalError requestWithHosts(HttpContext httpContext, String str, String str2, List<String> list, String str3, Map<String, String> map, Map<String, String> map2, byte[] bArr) throws Exception {
        InternalError internalErrorRequestWithUrl = null;
        for (String str4 : list) {
            httpContext.mTargetHost = str4;
            internalErrorRequestWithUrl = requestWithUrl(httpContext, str, buildRequestUrl(httpContext.ID, str2, str4, str3, map), map2, bArr);
            if (!checkRetryForHosts(internalErrorRequestWithUrl)) {
                break;
            }
            I("[NO:" + httpContext.ID + "] http failed, continue: host=" + str4 + ", e=" + internalErrorRequestWithUrl);
        }
        return internalErrorRequestWithUrl;
    }

    public HTTPResult get() {
        return requestWithHosts(new HttpContext(), METHOD_GET, null);
    }

    private HTTPResult newHTTPResult(HttpContext httpContext, InternalError internalError) {
        return newHTTPResult(httpContext, fromInternalError(internalError), internalError);
    }

    public HTTPResult get(String str) {
        String string;
        HttpContext httpContext = new HttpContext();
        I("[NO:" + httpContext.ID + "] http(GET): url=" + str);
        if (ObjUtils.empty(str)) {
            string = "empty url";
        } else {
            try {
                return newHTTPResult(httpContext, requestWithUrl(httpContext, METHOD_GET, str, this.mRequestHeaders, null));
            } catch (Throwable th) {
                E("[NO:" + httpContext.ID + "] http(GET) failed", th);
                StringBuilder sb = new StringBuilder();
                sb.append("");
                sb.append(th);
                string = sb.toString();
            }
        }
        return newHTTPResult(httpContext, 100, string);
    }
}
