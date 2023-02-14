package com.example.builder;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class ItemBuilder {


    public static String getItemResponse() {
        ObjectMapper mapper = new ObjectMapper();

        try {
            File file = new File("src/test/java/com/example/item.json");
            return mapper.writeValueAsString(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}