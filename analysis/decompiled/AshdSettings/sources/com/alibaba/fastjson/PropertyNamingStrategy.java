package com.alibaba.fastjson;

/* JADX INFO: loaded from: classes.dex */
public enum PropertyNamingStrategy {
    CamelCase,
    PascalCase,
    SnakeCase,
    KebabCase;

    public String translate(String str) {
        int i = 0;
        switch (this) {
            case SnakeCase:
                StringBuilder sb = new StringBuilder();
                while (i < str.length()) {
                    char cCharAt = str.charAt(i);
                    if (cCharAt >= 'A' && cCharAt <= 'Z') {
                        char c = (char) (cCharAt + ' ');
                        if (i > 0) {
                            sb.append('_');
                        }
                        sb.append(c);
                    } else {
                        sb.append(cCharAt);
                    }
                    i++;
                }
                return sb.toString();
            case KebabCase:
                StringBuilder sb2 = new StringBuilder();
                while (i < str.length()) {
                    char cCharAt2 = str.charAt(i);
                    if (cCharAt2 >= 'A' && cCharAt2 <= 'Z') {
                        char c2 = (char) (cCharAt2 + ' ');
                        if (i > 0) {
                            sb2.append('-');
                        }
                        sb2.append(c2);
                    } else {
                        sb2.append(cCharAt2);
                    }
                    i++;
                }
                return sb2.toString();
            case PascalCase:
                char cCharAt3 = str.charAt(0);
                if (cCharAt3 < 'a' || cCharAt3 > 'z') {
                    return str;
                }
                char[] charArray = str.toCharArray();
                charArray[0] = (char) (charArray[0] - ' ');
                return new String(charArray);
            case CamelCase:
                char cCharAt4 = str.charAt(0);
                if (cCharAt4 < 'A' || cCharAt4 > 'Z') {
                    return str;
                }
                char[] charArray2 = str.toCharArray();
                charArray2[0] = (char) (charArray2[0] + ' ');
                return new String(charArray2);
            default:
                return str;
        }
    }
}
