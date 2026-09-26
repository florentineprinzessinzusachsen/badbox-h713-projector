package com.umeng.commonsdk.statistics.proto;

import com.umeng.commonsdk.proguard.aa;
import com.umeng.commonsdk.proguard.ac;
import com.umeng.commonsdk.proguard.ad;
import com.umeng.commonsdk.proguard.ai;
import com.umeng.commonsdk.proguard.aj;
import com.umeng.commonsdk.proguard.al;
import com.umeng.commonsdk.proguard.an;
import com.umeng.commonsdk.proguard.ao;
import com.umeng.commonsdk.proguard.aq;
import com.umeng.commonsdk.proguard.ar;
import com.umeng.commonsdk.proguard.as;
import com.umeng.commonsdk.proguard.at;
import com.umeng.commonsdk.proguard.au;
import com.umeng.commonsdk.proguard.g;
import com.umeng.commonsdk.proguard.j;
import com.umeng.commonsdk.proguard.p;
import com.umeng.commonsdk.proguard.q;
import com.umeng.commonsdk.proguard.v;
import com.umeng.commonsdk.proguard.w;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class Response implements j<Response, e>, Serializable, Cloneable {
    private static final int __RESP_CODE_ISSET_ID = 0;
    public static final Map<e, v> metaDataMap;
    private static final long serialVersionUID = -4549277923241195391L;
    private byte __isset_bitfield;
    public com.umeng.commonsdk.statistics.proto.d imprint;
    public String msg;
    private e[] optionals;
    public int resp_code;
    private static final an STRUCT_DESC = new an("Response");
    private static final ad RESP_CODE_FIELD_DESC = new ad("resp_code", (byte) 8, 1);
    private static final ad MSG_FIELD_DESC = new ad("msg", (byte) 11, 2);
    private static final ad IMPRINT_FIELD_DESC = new ad(com.umeng.commonsdk.proguard.e.U, (byte) 12, 3);
    private static final Map<Class<? extends aq>, ar> schemes = new HashMap();

    private static class a extends as<Response> {
        private a() {
        }

        @Override // com.umeng.commonsdk.proguard.aq
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void b(ai aiVar, Response response) throws aj {
            aiVar.j();
            while (true) {
                ad adVarL = aiVar.l();
                byte b2 = adVarL.f3907b;
                if (b2 == 0) {
                    break;
                }
                short s = adVarL.f3908c;
                if (s != 1) {
                    if (s != 2) {
                        if (s != 3) {
                            al.a(aiVar, b2);
                        } else if (b2 == 12) {
                            response.imprint = new com.umeng.commonsdk.statistics.proto.d();
                            response.imprint.read(aiVar);
                            response.setImprintIsSet(true);
                        } else {
                            al.a(aiVar, b2);
                        }
                    } else if (b2 == 11) {
                        response.msg = aiVar.z();
                        response.setMsgIsSet(true);
                    } else {
                        al.a(aiVar, b2);
                    }
                } else if (b2 == 8) {
                    response.resp_code = aiVar.w();
                    response.setResp_codeIsSet(true);
                } else {
                    al.a(aiVar, b2);
                }
                aiVar.m();
            }
            aiVar.k();
            if (response.isSetResp_code()) {
                response.validate();
                return;
            }
            throw new aj("Required field 'resp_code' was not found in serialized data! Struct: " + toString());
        }

        @Override // com.umeng.commonsdk.proguard.aq
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(ai aiVar, Response response) throws aj {
            response.validate();
            aiVar.a(Response.STRUCT_DESC);
            aiVar.a(Response.RESP_CODE_FIELD_DESC);
            aiVar.a(response.resp_code);
            aiVar.c();
            if (response.msg != null && response.isSetMsg()) {
                aiVar.a(Response.MSG_FIELD_DESC);
                aiVar.a(response.msg);
                aiVar.c();
            }
            if (response.imprint != null && response.isSetImprint()) {
                aiVar.a(Response.IMPRINT_FIELD_DESC);
                response.imprint.write(aiVar);
                aiVar.c();
            }
            aiVar.d();
            aiVar.b();
        }
    }

    private static class b implements ar {
        private b() {
        }

        @Override // com.umeng.commonsdk.proguard.ar
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public a b() {
            return new a();
        }
    }

    private static class c extends at<Response> {
        private c() {
        }

        @Override // com.umeng.commonsdk.proguard.aq
        public void a(ai aiVar, Response response) {
            ao aoVar = (ao) aiVar;
            aoVar.a(response.resp_code);
            BitSet bitSet = new BitSet();
            if (response.isSetMsg()) {
                bitSet.set(0);
            }
            if (response.isSetImprint()) {
                bitSet.set(1);
            }
            aoVar.a(bitSet, 2);
            if (response.isSetMsg()) {
                aoVar.a(response.msg);
            }
            if (response.isSetImprint()) {
                response.imprint.write(aoVar);
            }
        }

        @Override // com.umeng.commonsdk.proguard.aq
        public void b(ai aiVar, Response response) {
            ao aoVar = (ao) aiVar;
            response.resp_code = aoVar.w();
            response.setResp_codeIsSet(true);
            BitSet bitSetB = aoVar.b(2);
            if (bitSetB.get(0)) {
                response.msg = aoVar.z();
                response.setMsgIsSet(true);
            }
            if (bitSetB.get(1)) {
                response.imprint = new com.umeng.commonsdk.statistics.proto.d();
                response.imprint.read(aoVar);
                response.setImprintIsSet(true);
            }
        }
    }

    private static class d implements ar {
        private d() {
        }

        @Override // com.umeng.commonsdk.proguard.ar
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public c b() {
            return new c();
        }
    }

    static {
        schemes.put(as.class, new b());
        schemes.put(at.class, new d());
        EnumMap enumMap = new EnumMap(e.class);
        enumMap.put(e.RESP_CODE, new v("resp_code", (byte) 1, new w((byte) 8)));
        enumMap.put(e.MSG, new v("msg", (byte) 2, new w((byte) 11)));
        enumMap.put(e.IMPRINT, new v(com.umeng.commonsdk.proguard.e.U, (byte) 2, new aa((byte) 12, com.umeng.commonsdk.statistics.proto.d.class)));
        metaDataMap = Collections.unmodifiableMap(enumMap);
        v.a(Response.class, metaDataMap);
    }

    public Response() {
        this.__isset_bitfield = (byte) 0;
        this.optionals = new e[]{e.MSG, e.IMPRINT};
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException {
        try {
            this.__isset_bitfield = (byte) 0;
            read(new ac(new au(objectInputStream)));
        } catch (p e2) {
            throw new IOException(e2.getMessage());
        }
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        try {
            write(new ac(new au(objectOutputStream)));
        } catch (p e2) {
            throw new IOException(e2.getMessage());
        }
    }

    @Override // com.umeng.commonsdk.proguard.j
    public void clear() {
        setResp_codeIsSet(false);
        this.resp_code = 0;
        this.msg = null;
        this.imprint = null;
    }

    public com.umeng.commonsdk.statistics.proto.d getImprint() {
        return this.imprint;
    }

    public String getMsg() {
        return this.msg;
    }

    public int getResp_code() {
        return this.resp_code;
    }

    public boolean isSetImprint() {
        return this.imprint != null;
    }

    public boolean isSetMsg() {
        return this.msg != null;
    }

    public boolean isSetResp_code() {
        return g.a(this.__isset_bitfield, 0);
    }

    @Override // com.umeng.commonsdk.proguard.j
    public void read(ai aiVar) {
        schemes.get(aiVar.D()).b().b(aiVar, this);
    }

    public Response setImprint(com.umeng.commonsdk.statistics.proto.d dVar) {
        this.imprint = dVar;
        return this;
    }

    public void setImprintIsSet(boolean z) {
        if (z) {
            return;
        }
        this.imprint = null;
    }

    public Response setMsg(String str) {
        this.msg = str;
        return this;
    }

    public void setMsgIsSet(boolean z) {
        if (z) {
            return;
        }
        this.msg = null;
    }

    public Response setResp_code(int i) {
        this.resp_code = i;
        setResp_codeIsSet(true);
        return this;
    }

    public void setResp_codeIsSet(boolean z) {
        this.__isset_bitfield = g.a(this.__isset_bitfield, 0, z);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Response(");
        sb.append("resp_code:");
        sb.append(this.resp_code);
        if (isSetMsg()) {
            sb.append(", ");
            sb.append("msg:");
            String str = this.msg;
            if (str == null) {
                sb.append("null");
            } else {
                sb.append(str);
            }
        }
        if (isSetImprint()) {
            sb.append(", ");
            sb.append("imprint:");
            com.umeng.commonsdk.statistics.proto.d dVar = this.imprint;
            if (dVar == null) {
                sb.append("null");
            } else {
                sb.append(dVar);
            }
        }
        sb.append(")");
        return sb.toString();
    }

    public void unsetImprint() {
        this.imprint = null;
    }

    public void unsetMsg() {
        this.msg = null;
    }

    public void unsetResp_code() {
        this.__isset_bitfield = g.b(this.__isset_bitfield, 0);
    }

    public void validate() throws aj {
        com.umeng.commonsdk.statistics.proto.d dVar = this.imprint;
        if (dVar != null) {
            dVar.l();
        }
    }

    @Override // com.umeng.commonsdk.proguard.j
    public void write(ai aiVar) {
        schemes.get(aiVar.D()).b().a(aiVar, this);
    }

    public enum e implements q {
        RESP_CODE(1, "resp_code"),
        MSG(2, "msg"),
        IMPRINT(3, com.umeng.commonsdk.proguard.e.U);


        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private static final Map<String, e> f4167d = new HashMap();

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final short f4168e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private final String f4169f;

        static {
            for (e eVar : EnumSet.allOf(e.class)) {
                f4167d.put(eVar.b(), eVar);
            }
        }

        e(short s, String str) {
            this.f4168e = s;
            this.f4169f = str;
        }

        public static e a(int i) {
            if (i == 1) {
                return RESP_CODE;
            }
            if (i == 2) {
                return MSG;
            }
            if (i != 3) {
                return null;
            }
            return IMPRINT;
        }

        public static e b(int i) {
            e eVarA = a(i);
            if (eVarA != null) {
                return eVarA;
            }
            throw new IllegalArgumentException("Field " + i + " doesn't exist!");
        }

        @Override // com.umeng.commonsdk.proguard.q
        public String b() {
            return this.f4169f;
        }

        public static e a(String str) {
            return f4167d.get(str);
        }

        @Override // com.umeng.commonsdk.proguard.q
        public short a() {
            return this.f4168e;
        }
    }

    @Override // com.umeng.commonsdk.proguard.j
    public Response deepCopy() {
        return new Response(this);
    }

    @Override // com.umeng.commonsdk.proguard.j
    public e fieldForId(int i) {
        return e.a(i);
    }

    public Response(int i) {
        this();
        this.resp_code = i;
        setResp_codeIsSet(true);
    }

    public Response(Response response) {
        this.__isset_bitfield = (byte) 0;
        this.optionals = new e[]{e.MSG, e.IMPRINT};
        this.__isset_bitfield = response.__isset_bitfield;
        this.resp_code = response.resp_code;
        if (response.isSetMsg()) {
            this.msg = response.msg;
        }
        if (response.isSetImprint()) {
            this.imprint = new com.umeng.commonsdk.statistics.proto.d(response.imprint);
        }
    }
}
