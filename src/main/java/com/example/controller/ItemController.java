package com.example.controller;


import com.example.dto.item.ItemResponseDto;
import com.example.service.impl.ItemService;
import io.javalin.http.Context;

import javax.inject.Inject;

public class ItemController {

    @Inject
    private ItemService itemService;

    public void handleGetItemById(Context ctx) {
        String itemId = ctx.pathParam("itemId");
        ItemResponseDto response = itemService.getItemById(itemId);
        ctx.json(response);
    }
}
