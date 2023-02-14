package com.example.service.impl;

import com.example.exception.ApiException;
import com.example.service.interfaces.IItemService;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.http.HttpStatus;

import java.io.IOException;


public class ItemService implements IItemService {
    private OkHttpClient httpClient;
    private static final String ITEMS_API_URL = "https://api.mercadolibre.com/items/";

    public ItemService(){
        this.httpClient = new OkHttpClient();
    }

    public String getItemById(String itemId) throws ApiException {

        if (itemId.matches("^[a-zA-Z]{3}\\d+")){
            Request request = new Request.Builder()
                    .url(ITEMS_API_URL.concat(itemId.toUpperCase()))
                    .build();

            try (Response response = httpClient.newCall(request).execute()) {
                if (response.isSuccessful()) {
                    return response.body().string();
                } else {
                    throw new RuntimeException("Failed to get response from external API");
                }
            } catch (IOException e) {
                throw new RuntimeException("Failed to get response from external API", e);
            }
        } else {
            throw new ApiException("400", "Bad request", HttpStatus.SC_BAD_REQUEST);
        }
    }

}
