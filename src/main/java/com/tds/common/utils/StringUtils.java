package com.tds.common.utils;

import com.tds.common.core.text.StrFormatter;

public class StringUtils extends org.apache.commons.lang3.StringUtils {

    private static final String NULLSTR = "";
    private static final char SEPARATOR = '_';

    public static String format(String template, Object... params) {
        if (isEmpty(params) || isEmpty(template)) {
            return template;
        }
        return StrFormatter.format(template, params);
    }

    public static String substring(final String str, int start, int end) {
        if (str == null) {
            return NULLSTR;
        }
        if (end < 0) {
            end = str.length() + end;
        }
        if (start > 0) {
            start = str.length() + start;
        }
        if (end > str.length()) {
            end = str.length();

        }
        if (start > end) {
            return NULLSTR;
        }
        if (start < 0) {
            start = 0;
        }
        if (end < 0) {
            end = 0;
        }
        return str.substring(start, end);

    }

    public static boolean isEmpty(Object[] objects) {
        return isNull(objects) || (objects.length == 0);
    }

    public static boolean isNull(Object object) {
        return object == null;
    }

    public static boolean isEmpty(String str) {
        return isNull(str) || NULLSTR.equals(str.trim());
    }

    public static boolean isNotNull(Object object) {
        return !isNull(object);
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }


    public static String toUnderScoreCase(String str) {
        if (str == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        boolean preCharIsUpperCase = true;
        boolean curreCharIsUpperCase = true;
        boolean nextCharIsUpperCase = true;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (i > 0) {
                preCharIsUpperCase = Character.isUpperCase(str.charAt(i - 1));
            } else {
                preCharIsUpperCase = false;
            }
            curreCharIsUpperCase = Character.isUpperCase(c);
            if (i < (str.length() - 1)) {
                nextCharIsUpperCase = Character.isUpperCase(str.charAt(i + 1));
            }
            if (preCharIsUpperCase && curreCharIsUpperCase && !nextCharIsUpperCase) {
                sb.append(SEPARATOR);
            } else if ((i != 0 && curreCharIsUpperCase && !nextCharIsUpperCase)) {
                sb.append(SEPARATOR);
            }
            sb.append(Character.toLowerCase(c));
        }
        return sb.toString();
    }

    public static String convertToCamelCase(String name) {
        StringBuilder result = new StringBuilder();
        if (name == null || name.isEmpty()) {
            return "";
        } else if (!name.contains("_")) {
            return name.substring(0, 1).toUpperCase() + name.substring(1);
        }
        String[] camels = name.split("_");
        for (String camel : camels) {
            if (camel.isEmpty()) {
                continue;
            }
            result.append(camel.substring(0, 1).toUpperCase());
            result.append(camel.substring(1).toLowerCase());
        }
        return result.toString();
    }

    public static String toCamelCase(String s) {
        if (s == null) {
            return null;
        }
        s = s.toLowerCase();
        StringBuilder sb = new StringBuilder(s.length());
        boolean upperCase = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == SEPARATOR) {
                upperCase = true;
            }else if (upperCase){
                sb.append(Character.toUpperCase(c));
                upperCase=false;
            }else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
    @SuppressWarnings("unchecked")
    public static <T> T cast(Object obj){
        return (T) obj;
    }
}
