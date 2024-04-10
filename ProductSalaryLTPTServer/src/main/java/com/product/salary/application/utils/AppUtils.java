package com.product.salary.application.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.product.salary.application.utils.adapter.LocalDateTypeAdapter;

import java.time.LocalDate;
import java.util.Map;

public class AppUtils {
    public static final Gson GSON = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter()).create();

    /**
     * Convert map to object
     * @param map
     * @param clazz
     * @return
     * @param <T>
     */
    public static <T> T convert(Map<String, Object> map, Class<T> clazz) {
        Gson gson = new Gson();
        String json = gson.toJson(map);
        return GSON.fromJson(json, clazz);
    }
}
