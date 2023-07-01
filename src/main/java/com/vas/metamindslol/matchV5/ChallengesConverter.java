package com.vas.metamindslol.matchV5;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Map;

import static com.vas.metamindslol.GsonInstance.gson;

@Converter
public class ChallengesConverter implements
        AttributeConverter<Map<String, Object>, String> {

    @Override
    public String convertToDatabaseColumn(Map<String, Object> map) {
        return gson.toJson(map);
    }

    @Override
    public Map<String, Object> convertToEntityAttribute(String s) {
        Map map = gson.fromJson(s,Map.class);
        return map;
    }
}