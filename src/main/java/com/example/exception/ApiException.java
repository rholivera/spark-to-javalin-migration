package com.example.exception;

import com.mercadolibre.json.JsonUtils;
import org.apache.http.HttpStatus;

import java.util.HashMap;

public class ApiException extends Exception {

    private static final long serialVersionUID = 1L;

    private final String code;
    private final String description;
    private Integer statusCode = HttpStatus.SC_INTERNAL_SERVER_ERROR;


    public ApiException(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public ApiException(String code, String description, Integer statusCode) {
        this.code = code;
        this.description = description;
        this.statusCode = statusCode;
    }

    public ApiException(String code, String description, Integer statusCode, Throwable cause) {
        super(cause);
        this.code = code;
        this.description = description;
        this.statusCode = statusCode;
    }

    public ApiException(String code, String description, Throwable cause) {
        super(cause);
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return this.code;
    }

    public String getDescription() {
        return this.description;
    }

    public Integer getStatusCode() {
        return this.statusCode;
    }

    public String toJson() {
        try {
            HashMap map = new HashMap();
            map.put("error", this.code);
            map.put("message", this.description);
            map.put("status", this.statusCode);
            return JsonUtils.INSTANCE.toJsonString(map);
        } catch (Exception ignored) {
            return "{" + "\"error\": " + "\"" + this.code + "\", " + "\"message\": " + "\"" + this.description + "\", " + "\"status\": " +
                    this.statusCode + "}";
        }
    }

}
