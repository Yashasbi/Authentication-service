package com.authorizationservice.configs;

import com.authorizationservice.constants.Constants;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ApiKeyConfigs {

    public static Map<String, String> apiKeyMapping = new HashMap<>() {{
        put(Constants.apiKey, Constants.clientId);
    }};

}
