package com.example.controller;

import com.example.dto.item.ItemResponseDto;
import com.example.service.impl.ItemService;
import com.example.service.interfaces.IItemService;
import io.javalin.http.Context;

public class ItemController {

    private final IItemService itemService;

    public ItemController() {
        this.itemService = new ItemService();
    }


    public void handleGetItemById(Context ctx) {
        String itemId = ctx.pathParam("itemId");
        ItemResponseDto response = itemService.getItemById(itemId);
        ctx.json(response);
    }
}
