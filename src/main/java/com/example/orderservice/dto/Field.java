package com.example.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor // 생성자
public class Field {
    private String type;
    private boolean optional;
    private String field;
}
