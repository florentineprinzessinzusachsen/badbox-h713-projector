package com.speed.net;

import j2.i;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class ApiResponse<T> {
    private final int code;
    private final T data;
    private final String msg;
    private final T result;

    public ApiResponse(int i4, T t3, T t4, String str) {
        i.e(str, "msg");
        this.code = i4;
        this.data = t3;
        this.result = t4;
        this.msg = str;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ ApiResponse copy$default(ApiResponse apiResponse, int i4, Object obj, Object obj2, String str, int i5, Object obj3) {
        if ((i5 & 1) != 0) {
            i4 = apiResponse.code;
        }
        if ((i5 & 2) != 0) {
            obj = apiResponse.data;
        }
        if ((i5 & 4) != 0) {
            obj2 = apiResponse.result;
        }
        if ((i5 & 8) != 0) {
            str = apiResponse.msg;
        }
        return apiResponse.copy(i4, obj, obj2, str);
    }

    public final int component1() {
        return this.code;
    }

    public final T component2() {
        return this.data;
    }

    public final T component3() {
        return this.result;
    }

    public final String component4() {
        return this.msg;
    }

    public final ApiResponse<T> copy(int i4, T t3, T t4, String str) {
        i.e(str, "msg");
        return new ApiResponse<>(i4, t3, t4, str);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ApiResponse)) {
            return false;
        }
        ApiResponse apiResponse = (ApiResponse) obj;
        return this.code == apiResponse.code && i.a(this.data, apiResponse.data) && i.a(this.result, apiResponse.result) && i.a(this.msg, apiResponse.msg);
    }

    public final int getCode() {
        return this.code;
    }

    public final T getData() {
        return this.data;
    }

    public final String getErrorMessage() {
        return this.msg;
    }

    public final String getMsg() {
        return this.msg;
    }

    public final T getResult() {
        return this.result;
    }

    public final T getValidData() {
        T t3 = this.data;
        return t3 == null ? this.result : t3;
    }

    public int hashCode() {
        int i4 = this.code * 31;
        T t3 = this.data;
        int iHashCode = (i4 + (t3 == null ? 0 : t3.hashCode())) * 31;
        T t4 = this.result;
        return this.msg.hashCode() + ((iHashCode + (t4 != null ? t4.hashCode() : 0)) * 31);
    }

    public final boolean isSuccess() {
        return this.code == 200;
    }

    public String toString() {
        return "ApiResponse(code=" + this.code + ", data=" + this.data + ", result=" + this.result + ", msg=" + this.msg + ")";
    }
}
