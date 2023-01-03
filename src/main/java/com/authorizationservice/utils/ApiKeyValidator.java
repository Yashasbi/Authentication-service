package com.authorizationservice.utils;

import com.authorizationservice.configs.ApiKeyConfigs;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicBoolean;

@Component
public class ApiKeyValidator {

    public boolean validateApiKey(String inputApiKey) {
        return ApiKeyConfigs.apiKeyMapping.containsKey(inputApiKey);
    }
}
