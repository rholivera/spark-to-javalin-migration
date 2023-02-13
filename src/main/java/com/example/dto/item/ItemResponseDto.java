package com.example.dto.item;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemResponseDto {
    private String id;
    private String title;
    private Double price;
    @JsonProperty("seller_id")
    private Long sellerId;
    @JsonProperty("category_id")
    private String categoryId;
    @JsonProperty(value = "currency_id")
    private String currencyId;
}
