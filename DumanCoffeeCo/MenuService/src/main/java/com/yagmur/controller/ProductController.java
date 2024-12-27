package com.yagmur.controller;

import com.yagmur.dto.request.ProductSaveRequestDto;
import com.yagmur.dto.request.ProductUpdateRequestDto;
import com.yagmur.dto.response.BasicResponse;
import com.yagmur.entity.Products;
import com.yagmur.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.yagmur.constans.RestApiUrls.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(PRODUCT)
@Tag(name = "Products", description = "Ürün yönetimi API'leri")
public class ProductController {

    private final ProductService productService;

    @PostMapping(SAVE)
    @CrossOrigin("*")
    public ResponseEntity<BasicResponse<Object>> saveProduct(@RequestBody ProductSaveRequestDto productSaveRequestDto) {
        return ResponseEntity.ok(BasicResponse.builder()
                .status(200)
                .message("Ürün Eklendi.")
                .data(productService.saveProduct(productSaveRequestDto))
                .build());
    }

    @PostMapping("/upload")
    @CrossOrigin("*")
    public ResponseEntity<?> uploadProductWithFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("price") Double price,
            @RequestParam("categoryId") String categoryId
    ) {
        try {
            Products savedProduct = productService.saveProductWithFile(file, name, description, price, categoryId);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Bir hata oluştu: " + e.getMessage());
        }
    }

    @GetMapping(GET_ALL)
    @CrossOrigin("*")
    public ResponseEntity<BasicResponse<List<Products>>> getAllProducts() {
        return ResponseEntity.ok(BasicResponse.<List<Products>>builder()
                .status(200)
                .message("Ürünler listelendi.")
                .data(productService.getAllProducts())
                .build());
    }

    // ID'ye Göre Ürün Getirme
    @GetMapping(GET_BY_ID)
    @CrossOrigin("*")
    public ResponseEntity<BasicResponse<Object>> getProductById(@PathVariable String id) {
        return ResponseEntity.ok(BasicResponse.builder()
                .status(200)
                .message("Ürün bulundu.")
                .data(productService.getProductById(id))
                .build());
    }

    // Update - Ürün Güncelleme
    @PutMapping(UPDATE)
    @CrossOrigin("*")
    public ResponseEntity<BasicResponse<Object>> updateProduct(@RequestBody ProductUpdateRequestDto productUpdateRequestDto) {
        return ResponseEntity.ok(BasicResponse.builder()
                .status(200)
                .message("Ürün güncellendi.")
                .data(productService.updateProduct(productUpdateRequestDto))
                .build());
    }

    // Delete - Ürün Silme
    @DeleteMapping(DELETE_BY_ID)
    public ResponseEntity<BasicResponse<String>> deleteProduct(@PathVariable String id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok(BasicResponse.<String>builder()
                .status(200)
                .message("Ürün silindi.")
                .data("Ürün başarıyla silindi.")
                .build());
    }

    @GetMapping("/category/{categoryName}")
    @CrossOrigin("*")
    public ResponseEntity<List<Products>> getProductsByCategory(@PathVariable String categoryName) {
        List<Products> products = productService.getProductsByCategoryName(categoryName);
        return ResponseEntity.ok(products);
    }

}
