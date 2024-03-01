package com.example.demo.dto;

import lombok.Data;

@Data
public class BookDto {
    private String name;
    private String description;
    private Long categoryId;
}
