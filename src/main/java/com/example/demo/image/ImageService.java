package com.example.demo.image;

import com.example.demo.settings.CloudflareSettings;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

import java.awt.*;

@Service
public class ImageService {




    private final RestClient restClient;
    private final CloudflareSettings cloudflareSettings;

    @Autowired
    public ImageService(CloudflareSettings cloudflareSettings) {

        this.cloudflareSettings = cloudflareSettings;

        this.restClient = RestClient.builder()
                .baseUrl("https://api.cloudflare.com/client/v4/accounts/" +
                        cloudflareSettings.getAccountId() +
                        "/images")
                .defaultHeader("Authorization", "Bearer " + cloudflareSettings.getApiKey())
                .build();
    }

    public String getNewImageUrl() {

        ResponseEntity<String> response =  restClient.post()
                .uri("/v2/direct_upload")
                .retrieve()
                .toEntity(String.class);


        ObjectMapper mapper = new ObjectMapper();

        try {
            JsonNode jsonNode = mapper.readTree(response.getBody());
            return jsonNode.get("result").get("uploadURL").toString();

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
