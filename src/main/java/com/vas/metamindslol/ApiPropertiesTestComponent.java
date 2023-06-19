package com.vas.metamindslol;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ApiPropertiesTestComponent {
    private final ApiProperties apiProperties;

    @Autowired
    public ApiPropertiesTestComponent(ApiProperties apiProperties) {
        this.apiProperties = apiProperties;
    }

    public void printApiKey() {
        String apiKey = apiProperties.getApiKey();
        System.out.println("API Key: " + apiKey);
    }

    public String getApiKey() {
        return apiProperties.getApiKey();
    }
}