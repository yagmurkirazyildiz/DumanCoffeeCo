package com.yagmur.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductSaveRequestDto {
    @Schema(type = "string", format = "binary", description = "Yüklenecek dosya")
    private MultipartFile file;

    @Schema(description = "Ürün adı", example = "Yeni Ürün")
    private String name;

    @Schema(description = "Ürün açıklaması", example = "Bu bir açıklamadır")
    private String description;

    @Schema(description = "Ürün fiyatı", example = "99.99")
    private Double price;

    @Schema(description = "Kategori ID'si", example = "12345")
    private String categoryId;

    @Schema(description = "Ürün resim URL'si", example = "http://example.com/image.jpg")
    private String imageUrl;  // Add this field
}
