package com.softwinner.tv.common;

import java.util.Locale;
import java.util.Objects;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class AwTvMultilingualText {
    private static final Locale HK_LOCAL = new Locale("zh", "HK");
    private static final int SPLIT_CHAR = 124;
    private static final String TAG = "TvMultilingualText";

    private class MultilingualText {
        protected String language;
        protected String text;

        public MultilingualText(String str) {
            if (str != null && str.length() >= 3) {
                this.language = str.substring(0, 3);
                if (this.language.equalsIgnoreCase("xxx")) {
                    this.language = "eng";
                }
                if (str.length() > 3) {
                    this.text = str.substring(3, str.length());
                    return;
                }
                return;
            }
            this.language = "";
        }

        public MultilingualText() {
        }

        public String getLangage() {
            return this.language;
        }

        public String getText() {
            return this.text;
        }
    }

    private class MultilingualTextJ extends MultilingualText {
        public MultilingualTextJ(JSONObject jSONObject) {
            super();
            parse(jSONObject);
        }

        public MultilingualTextJ(String str) {
            super();
            if (str == null || str.length() < 8) {
                return;
            }
            try {
                parse(new JSONObject(str));
            } catch (JSONException e) {
                throw new RuntimeException("Json parse fail: [" + str + "]", e);
            }
        }

        public void parse(JSONObject jSONObject) {
            if (jSONObject != null) {
                this.language = jSONObject.optString("lng");
                this.text = jSONObject.optString("txt");
                if (this.language.equalsIgnoreCase("xxx")) {
                    this.language = "eng";
                    return;
                }
                return;
            }
            this.language = "";
        }
    }

    /* JADX WARN: Code duplicated, block: B:17:0x0037  */
    /* JADX WARN: Code duplicated, block: B:18:0x0052  */
    public static String getText(String str, String str2) {
        boolean z;
        String string;
        String[] strArrSplit;
        MultilingualText multilingualText;
        if (str == null || str2 == null || str.isEmpty()) {
            return null;
        }
        if (str2.equalsIgnoreCase("local")) {
            str2 = getLocalLang();
        } else {
            z = str2.equalsIgnoreCase("first");
            if (str.contains("|")) {
                StringBuilder sb = new StringBuilder();
                sb.append("\\");
                sb.append("|");
                string = sb.toString();
            } else {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("\\");
                sb2.append("|");
                string = sb2.toString();
            }
            strArrSplit = str.split(string);
            for (int i = 0; strArrSplit != null && i < strArrSplit.length; i++) {
                AwTvMultilingualText awTvMultilingualText = new AwTvMultilingualText();
                Objects.requireNonNull(awTvMultilingualText);
                multilingualText = awTvMultilingualText.new MultilingualText(strArrSplit[i]);
                if (!z || multilingualText.getLangage().equalsIgnoreCase(str2)) {
                    return multilingualText.getText();
                }
            }
            return null;
        }
        if (str.contains("|")) {
            StringBuilder sb3 = new StringBuilder();
            sb3.append("\\");
            sb3.append("|");
            string = sb3.toString();
        } else {
            StringBuilder sb4 = new StringBuilder();
            sb4.append("\\");
            sb4.append("|");
            string = sb4.toString();
        }
        strArrSplit = str.split(string);
        while (strArrSplit != null) {
            AwTvMultilingualText awTvMultilingualText2 = new AwTvMultilingualText();
            Objects.requireNonNull(awTvMultilingualText2);
            multilingualText = awTvMultilingualText2.new MultilingualText(strArrSplit[i]);
            if (z) {
            }
            return multilingualText.getText();
        }
        return null;
    }

    public static String getText(String str) {
        String text = null;
        if ("local first" == 0 || "local first".isEmpty()) {
            return null;
        }
        String[] strArrSplit = "local first".split(" ");
        if (strArrSplit != null && strArrSplit.length > 0) {
            for (int i = 0; i < strArrSplit.length && ((text = getText(str, strArrSplit[i])) == null || text.isEmpty()); i++) {
            }
        }
        return text;
    }

    public static String getText(String str, String[] strArr) {
        String[] strArr2 = {"local", "first"};
        if (strArr == null || strArr.length == 0) {
            strArr = strArr2;
        }
        String text = null;
        if (strArr != null && strArr.length > 0) {
            for (int i = 0; i < strArr.length && ((text = getText(str, strArr[i])) == null || text.isEmpty()); i++) {
            }
        }
        return text;
    }

    public static String getLocalLang() {
        Locale locale = Locale.getDefault();
        if (locale.equals(Locale.SIMPLIFIED_CHINESE)) {
            return "chs";
        }
        return (locale.equals(Locale.TRADITIONAL_CHINESE) || locale.equals(HK_LOCAL)) ? "chi" : Locale.getDefault().getISO3Language();
    }

    /* JADX WARN: Code duplicated, block: B:18:0x002f  */
    public static String getTextJ(String str, String str2) {
        boolean z;
        JSONArray jSONArray;
        MultilingualTextJ multilingualTextJ;
        if (str == null || str2 == null || str.isEmpty()) {
            return null;
        }
        try {
            if (str2.equalsIgnoreCase("local")) {
                str2 = getLocalLang();
            } else {
                z = str2.equalsIgnoreCase("first");
                jSONArray = new JSONArray(str);
                for (int i = 0; i < jSONArray.length(); i++) {
                    JSONObject jSONObjectOptJSONObject = jSONArray.optJSONObject(i);
                    AwTvMultilingualText awTvMultilingualText = new AwTvMultilingualText();
                    Objects.requireNonNull(awTvMultilingualText);
                    multilingualTextJ = awTvMultilingualText.new MultilingualTextJ(jSONObjectOptJSONObject);
                    if (!z || multilingualTextJ.getLangage().equalsIgnoreCase(str2)) {
                        return multilingualTextJ.getText();
                    }
                }
                return null;
            }
            jSONArray = new JSONArray(str);
            while (i < jSONArray.length()) {
                JSONObject jSONObjectOptJSONObject2 = jSONArray.optJSONObject(i);
                AwTvMultilingualText awTvMultilingualText2 = new AwTvMultilingualText();
                Objects.requireNonNull(awTvMultilingualText2);
                multilingualTextJ = awTvMultilingualText2.new MultilingualTextJ(jSONObjectOptJSONObject2);
                if (z) {
                }
                return multilingualTextJ.getText();
            }
            return null;
        } catch (JSONException e) {
            throw new RuntimeException("Json parse fail: [" + str + "]", e);
        }
    }

    public static String getTextJ(String str, String[] strArr) {
        String[] strArr2 = {"local", "first"};
        if (strArr == null || strArr.length == 0) {
            strArr = strArr2;
        }
        String textJ = null;
        if (strArr != null && strArr.length > 0) {
            for (int i = 0; i < strArr.length && ((textJ = getTextJ(str, strArr[i])) == null || textJ.isEmpty()); i++) {
            }
        }
        return textJ;
    }

    public static String getTextJ(String str) {
        return getTextJ(str, new String[0]);
    }
}
