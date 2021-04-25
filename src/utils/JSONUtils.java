package utils;

import com.google.gson.Gson;
import models.User;

public class JSONUtils {
    private static final Gson gson = new Gson();

    public static Object objectFromJSON(String readUTF, Class<?> typeClass) {
        return gson.fromJson(readUTF, typeClass);
    }

    public static String requestToJSON(String requestCode, String title){
        return gson.toJson(new String[]{requestCode,title},String[].class);
    }

    public static String objectToJSON(User userd, Class<?> userClass) {
        return gson.toJson(userd,userClass);
    }
}
