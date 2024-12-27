package com.yagmur.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductUpdateRequestDto {
    private String id; // Güncellenecek ürünün ID'si
    private String name;
    private String description;
    private Double price;
    private String imageUrl;
    private String categoryId; // Yeni kategori ID'si (gerekirse)
}
