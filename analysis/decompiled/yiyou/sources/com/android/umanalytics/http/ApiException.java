package com.android.umanalytics.http;

/* JADX INFO: loaded from: classes.dex */
public class ApiException extends RuntimeException {
    public static final int ACCOUNT_NOT_ACTIVE = 410;
    public static final int ALREADY_BIND_ACCOUNT = 107;
    public static final int DEVICE_ACTIVATED = 116;
    public static final int DEVICE_UNABLE = 113;
    public static final int DEVICE_USER_LOGIN_OTHER = 114;
    public static final int DISABLE_REQUEST_ID = 100003;
    public static final int DISABLE_TOKEN = 409;
    public static final int ERROR_CHECK_CODE = 105;
    public static final int ERROR_DECODE = 48;
    public static final int ERROR_GET_URL = -1;
    public static final int ERROR_SYSTEM = 100099;
    public static final int ERROR_USERNAME_OR_PASSWROD = 102;
    public static final int EXCEPTION_DATA_FORMAT = 100001;
    public static final int EXCEPTION_TIME_OUT = 100002;
    public static final int EXCEPTION_WITHDRAW_MONEY = 111;
    public static final int FAILURE = 400;
    public static final int FAILURE_ATTESTATION = 100000;
    public static final int FAILURE_REGISTER_USER_EXIST = 106;
    public static final int GIFT_VERSION_LATEST = 108;
    public static final int INVITE_CODE_FILL = 46;
    public static final int LOGOUT = 115;
    public static final int MSM_EXCEED_LIMIT = 103;
    public static final int MUST_PARAMETER_NULL = 100005;
    public static final int NO_GET_VERCODE_BY_LIMIT = 1;
    public static final int PROBATION_PERIOD_PASSED = 412;
    public static final int REQUEST_TOO_OFTEN = 100004;
    public static final String TEXT_ACCOUNT_NOT_ACTIVE = "账号未激活";
    public static final String TEXT_DISABLE_TOKEN = "token失效,请重新登录";
    public static final String TEXT_ERROR_GET_URL = "获取播放地址失败";
    public static final String TEXT_PROBATION_PERIOD_PASSED = "试用期已过，请激活后使用";
    public static final int UNVERIFIED = 151;
    public static final int USER_NOT_EXIST = 104;
    public static final int WAIT_AUTH = 152;
    public static final int WITHDRAW_BEYOND_LIMIT = 109;

    public ApiException(int i) {
        this(getApiExceptionMessage(i, "未知错误"));
    }

    private static String getApiExceptionMessage(int i, String str) {
        if (i == -1) {
            return TEXT_ERROR_GET_URL;
        }
        if (i == 1) {
            return str;
        }
        if (i == 46) {
            return "已填写过邀请码";
        }
        if (i == 48) {
            return "解密失败";
        }
        if (i == 111) {
            return "提现金额小于0元";
        }
        if (i == 400) {
            return "失败";
        }
        if (i == 412) {
            return TEXT_PROBATION_PERIOD_PASSED;
        }
        if (i == 100099) {
            return "系统错误";
        }
        if (i == 151) {
            return "未认证";
        }
        if (i == 152) {
            return "待认证";
        }
        if (i == 409) {
            return TEXT_DISABLE_TOKEN;
        }
        if (i == 410) {
            return TEXT_ACCOUNT_NOT_ACTIVE;
        }
        switch (i) {
            case 102:
                return "用户名或密码错误";
            case 103:
                return "每天发送验证码不能超过30条";
            case 104:
                return "用户不存在";
            case 105:
                return "验证码错误";
            case 106:
                return "注册失败，该用户已存在";
            case 107:
                return "该手机号已经绑定过账号";
            case 108:
                return "礼物已是最新版本";
            case 109:
                return "提现金额超过当天额度";
            default:
                switch (i) {
                    case 113:
                        return "设备被封";
                    case 114:
                        return "用户已在另一台设备登录";
                    case 115:
                        return "退出登录";
                    case 116:
                        return "设备已激活";
                    default:
                        switch (i) {
                            case FAILURE_ATTESTATION /* 100000 */:
                                return "验签失败";
                            case EXCEPTION_DATA_FORMAT /* 100001 */:
                                return "数据格式异常";
                            case EXCEPTION_TIME_OUT /* 100002 */:
                                return "校验时间戳超时";
                            case DISABLE_REQUEST_ID /* 100003 */:
                                return "request_id无效";
                            case REQUEST_TOO_OFTEN /* 100004 */:
                                return "请求过于频繁";
                            case MUST_PARAMETER_NULL /* 100005 */:
                                return "必填参数为空";
                            default:
                                return "未知错误";
                        }
                }
        }
    }

    public ApiException(int i, String str) {
        this(getApiExceptionMessage(i, str));
    }

    public ApiException(String str) {
        super(str);
    }
}
