package com.example.builder;

import com.example.dto.item.ItemResponseDto;

public class ItemResponseDtoBuilder {

    public static ItemResponseDto getItemResponseDto() {
        return ItemResponseDto.builder()
                .id("MLA12345")
                .price(10000.00)
                .title("Mochila Impermeable anti robo")
                .sellerId(729755460L)
                .build();
    }
}
