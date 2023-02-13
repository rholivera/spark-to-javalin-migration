package com.example.unit;

import com.example.dto.item.ItemResponseDto;
import com.example.builder.ItemResponseDtoBuilder;
import com.example.service.impl.ItemService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ItemServiceTest {

    @Mock
    private ItemService itemService;

    @Test
    void whenGetItemByIdThenItemResponseDtoShouldBeReturned() {
        ItemResponseDto expected = ItemResponseDtoBuilder.getItemResponseDto();
        String itemId = expected.getId();

        when(itemService.getItemById(itemId)).thenReturn(expected);

        ItemResponseDto result = itemService.getItemById(itemId);

        assertEquals(itemId, result.getId());
        verify(itemService, times(1)).getItemById(itemId);
    }
}
