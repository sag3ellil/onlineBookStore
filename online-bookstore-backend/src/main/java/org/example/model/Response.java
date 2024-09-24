package org.example.model;

import lombok.Data;

@Data
public class Response {
    private String error;
    private Integer code;
    private Object data;
}
