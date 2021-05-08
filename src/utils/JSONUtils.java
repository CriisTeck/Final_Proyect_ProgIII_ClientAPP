package utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import models.Notification;

import java.lang.reflect.Type;
import java.util.Comparator;

public class JSONUtils {
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static Object objectFromJSON(String readUTF, Class<?> typeClass) {
        return gson.fromJson(readUTF, typeClass);
    }

    public static Object objectFromJSON(String readUTF, Type typeClass) {
        return gson.fromJson(readUTF, typeClass);
    }

    public static String requestToJSON(String requestCode, String title) {
        return gson.toJson(new String[]{requestCode, title}, String[].class);
    }

    public static String objectToJSON(Object userd, Class<?> userClass) {
        return gson.toJson(userd, userClass);
    }

    public static Object userListFromJSON(String readUTF, Type typeClass) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Comparator.class, new ComparatorCreatorr(Comparator.comparingInt(Notification::getId)));
        return gsonBuilder.setPrettyPrinting().create().fromJson(readUTF, typeClass);
    }
}
