package com.yagmur.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryUpdateRequestDto {
    private String id; // Güncellenecek kategorinin ID'si
    private String name;
    private String description;
}
