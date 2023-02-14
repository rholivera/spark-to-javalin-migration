package com.example.controller;

import com.example.exception.ApiException;
import com.example.service.impl.ItemService;
import com.example.service.interfaces.IItemService;
import io.javalin.http.Context;

public class ItemController {

    private IItemService itemService;

    public ItemController() {
        this.itemService = new ItemService();
    }

    public void getItemById(Context ctx) throws ApiException {
        String itemId = ctx.pathParam("itemId");
        String response = itemService.getItemById(itemId);
        ctx.json(response);
    }
}
