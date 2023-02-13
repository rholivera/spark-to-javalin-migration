package com.example.service.interfaces;

import com.example.dto.item.ItemResponseDto;

public interface IItemService {

    ItemResponseDto getItemById(String itemId);
}
