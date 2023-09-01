package com.vas.metamindslol;

import com.google.gson.Gson;
import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
public class GsonInstance {
    private final Gson gson;
    private static GsonInstance instance;

    private GsonInstance() {
        gson = new Gson();
    }

    public static GsonInstance getInstance() {
        if (instance == null) {
            instance = new GsonInstance();
        }
        return instance;
    }

    public Gson getGson() {
        return gson;
    }
}
