package com.example.unit;

import com.example.builder.ItemBuilder;
import com.example.exception.ApiException;
import com.example.service.interfaces.IItemService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class ItemServiceTest {

    @Mock
    private IItemService itemService;

    @Test
    void whenGetItemByIdThenItemShouldBeReturned() throws ApiException {
        String expected = ItemBuilder.getItemResponse();
        String itemId = "MLA912141455";

        when(itemService.getItemById(itemId)).thenReturn(expected);

        String result = itemService.getItemById(itemId);

        assertEquals(expected, result);
        verify(itemService, times(1)).getItemById(itemId);
    }

    @Test
    void whenGetItemByIdWithInvalidIdThenApiExceptionShouldBeReturned() throws ApiException {
        String itemId = "asd912141455";

        when(itemService.getItemById(itemId)).thenThrow(ApiException.class);

        assertThrows(ApiException.class, ()-> itemService.getItemById(itemId));
        verify(itemService, times(1)).getItemById(itemId);
    }
}
