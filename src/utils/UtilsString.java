package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UtilsString {
    public static String capitalize(String str) {
        StringBuffer strbf = new StringBuffer();
        Matcher match = Pattern.compile("([a-z])([a-z]*)", Pattern.CASE_INSENSITIVE).matcher(str);
        while (match.find()) {
            match.appendReplacement(strbf, match.group(1).toUpperCase() + match.group(2).toLowerCase());
        }
        return match.appendTail(strbf).toString();
    }
}
