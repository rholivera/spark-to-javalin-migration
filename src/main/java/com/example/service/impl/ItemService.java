package com.example.service.impl;

import com.example.dto.item.ItemResponseDto;
import com.example.service.interfaces.IItemService;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import com.google.gson.Gson;

import java.io.IOException;

public class ItemService implements IItemService {
    private OkHttpClient httpClient;
    private final String ITEMS_API_URL = "https://api.mercadolibre.com/items/";

    public ItemService(){
        this.httpClient = new OkHttpClient();
    }

    public ItemResponseDto getItemById(String itemId) {
        Request request = new Request.Builder()
                .url(ITEMS_API_URL.concat(itemId))
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            if (response.isSuccessful()) {
                String responseBody = response.body().string();
                Gson gson = new Gson();
                return gson.fromJson(responseBody, ItemResponseDto.class);
            } else {
                throw new RuntimeException("Failed to get response from external API");
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to get response from external API", e);
        }
    }
}
