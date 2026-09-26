package retrofit2;

import d.a0;
import d.b0;
import d.q;
import d.s;
import d.t;
import d.v;
import d.w;
import e.c;
import e.d;

/* JADX INFO: loaded from: classes.dex */
final class RequestBuilder {
    private static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    private static final String PATH_SEGMENT_ALWAYS_ENCODE_SET = " \"<>^`{}|\\?#";
    private final t baseUrl;
    private b0 body;
    private v contentType;
    private q.a formBuilder;
    private final boolean hasBody;
    private final String method;
    private w.a multipartBuilder;
    private String relativeUrl;
    private final a0.a requestBuilder = new a0.a();
    private t.a urlBuilder;

    private static class ContentTypeOverridingRequestBody extends b0 {
        private final v contentType;
        private final b0 delegate;

        ContentTypeOverridingRequestBody(b0 b0Var, v vVar) {
            this.delegate = b0Var;
            this.contentType = vVar;
        }

        @Override // d.b0
        public long contentLength() {
            return this.delegate.contentLength();
        }

        @Override // d.b0
        public v contentType() {
            return this.contentType;
        }

        @Override // d.b0
        public void writeTo(d dVar) {
            this.delegate.writeTo(dVar);
        }
    }

    RequestBuilder(String str, t tVar, String str2, s sVar, v vVar, boolean z, boolean z2, boolean z3) {
        this.method = str;
        this.baseUrl = tVar;
        this.relativeUrl = str2;
        this.contentType = vVar;
        this.hasBody = z;
        if (sVar != null) {
            this.requestBuilder.a(sVar);
        }
        if (z2) {
            this.formBuilder = new q.a();
        } else if (z3) {
            this.multipartBuilder = new w.a();
            this.multipartBuilder.a(w.f4683f);
        }
    }

    private static String canonicalizeForPath(String str, boolean z) {
        int length = str.length();
        int iCharCount = 0;
        while (iCharCount < length) {
            int iCodePointAt = str.codePointAt(iCharCount);
            if (iCodePointAt < 32 || iCodePointAt >= 127 || PATH_SEGMENT_ALWAYS_ENCODE_SET.indexOf(iCodePointAt) != -1 || (!z && (iCodePointAt == 47 || iCodePointAt == 37))) {
                c cVar = new c();
                cVar.a(str, 0, iCharCount);
                canonicalizeForPath(cVar, str, iCharCount, length, z);
                return cVar.o();
            }
            iCharCount += Character.charCount(iCodePointAt);
        }
        return str;
    }

    void addFormField(String str, String str2, boolean z) {
        if (z) {
            this.formBuilder.b(str, str2);
        } else {
            this.formBuilder.a(str, str2);
        }
    }

    void addHeader(String str, String str2) {
        if (!"Content-Type".equalsIgnoreCase(str)) {
            this.requestBuilder.a(str, str2);
            return;
        }
        v vVarB = v.b(str2);
        if (vVarB != null) {
            this.contentType = vVarB;
            return;
        }
        throw new IllegalArgumentException("Malformed content type: " + str2);
    }

    void addPart(s sVar, b0 b0Var) {
        this.multipartBuilder.a(sVar, b0Var);
    }

    void addPathParam(String str, String str2, boolean z) {
        String str3 = this.relativeUrl;
        if (str3 == null) {
            throw new AssertionError();
        }
        this.relativeUrl = str3.replace("{" + str + "}", canonicalizeForPath(str2, z));
    }

    void addQueryParam(String str, String str2, boolean z) {
        String str3 = this.relativeUrl;
        if (str3 != null) {
            this.urlBuilder = this.baseUrl.a(str3);
            if (this.urlBuilder == null) {
                throw new IllegalArgumentException("Malformed URL. Base: " + this.baseUrl + ", Relative: " + this.relativeUrl);
            }
            this.relativeUrl = null;
        }
        if (z) {
            this.urlBuilder.a(str, str2);
        } else {
            this.urlBuilder.b(str, str2);
        }
    }

    a0 build() {
        t tVarB;
        t.a aVar = this.urlBuilder;
        if (aVar != null) {
            tVarB = aVar.a();
        } else {
            tVarB = this.baseUrl.b(this.relativeUrl);
            if (tVarB == null) {
                throw new IllegalArgumentException("Malformed URL. Base: " + this.baseUrl + ", Relative: " + this.relativeUrl);
            }
        }
        b0 contentTypeOverridingRequestBody = this.body;
        if (contentTypeOverridingRequestBody == null) {
            q.a aVar2 = this.formBuilder;
            if (aVar2 != null) {
                contentTypeOverridingRequestBody = aVar2.a();
            } else {
                w.a aVar3 = this.multipartBuilder;
                if (aVar3 != null) {
                    contentTypeOverridingRequestBody = aVar3.a();
                } else if (this.hasBody) {
                    contentTypeOverridingRequestBody = b0.create((v) null, new byte[0]);
                }
            }
        }
        v vVar = this.contentType;
        if (vVar != null) {
            if (contentTypeOverridingRequestBody != null) {
                contentTypeOverridingRequestBody = new ContentTypeOverridingRequestBody(contentTypeOverridingRequestBody, vVar);
            } else {
                this.requestBuilder.a("Content-Type", vVar.toString());
            }
        }
        a0.a aVar4 = this.requestBuilder;
        aVar4.a(tVarB);
        aVar4.a(this.method, contentTypeOverridingRequestBody);
        return aVar4.a();
    }

    void setBody(b0 b0Var) {
        this.body = b0Var;
    }

    void setRelativeUrl(Object obj) {
        this.relativeUrl = obj.toString();
    }

    void addPart(w.b bVar) {
        this.multipartBuilder.a(bVar);
    }

    private static void canonicalizeForPath(c cVar, String str, int i, int i2, boolean z) {
        c cVar2 = null;
        while (i < i2) {
            int iCodePointAt = str.codePointAt(i);
            if (!z || (iCodePointAt != 9 && iCodePointAt != 10 && iCodePointAt != 12 && iCodePointAt != 13)) {
                if (iCodePointAt >= 32 && iCodePointAt < 127 && PATH_SEGMENT_ALWAYS_ENCODE_SET.indexOf(iCodePointAt) == -1 && (z || (iCodePointAt != 47 && iCodePointAt != 37))) {
                    cVar.c(iCodePointAt);
                } else {
                    if (cVar2 == null) {
                        cVar2 = new c();
                    }
                    cVar2.c(iCodePointAt);
                    while (!cVar2.j()) {
                        int i3 = cVar2.readByte() & 255;
                        cVar.writeByte(37);
                        cVar.writeByte((int) HEX_DIGITS[(i3 >> 4) & 15]);
                        cVar.writeByte((int) HEX_DIGITS[i3 & 15]);
                    }
                }
            }
            i += Character.charCount(iCodePointAt);
        }
    }
}
