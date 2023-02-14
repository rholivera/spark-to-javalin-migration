package com.example.service.interfaces;


import com.example.exception.ApiException;

public interface IItemService {

    String getItemById(String itemId) throws ApiException;
}
