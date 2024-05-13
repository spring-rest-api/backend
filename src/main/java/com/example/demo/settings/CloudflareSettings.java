package com.example.demo.settings;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CloudflareSettings {

    @Value("${cloudflare.account.id}")
    private String accountId;

    @Value("${cloudflare.api.key}")
    private String apiKey;


    public String getAccountId() {
        return accountId;
    }

    public String getApiKey() {
        return apiKey;
    }

}
