package com.yagmur.service;

import com.yagmur.dto.request.ProductSaveRequestDto;
import com.yagmur.dto.request.ProductUpdateRequestDto;
import com.yagmur.entity.Category;
import com.yagmur.entity.Products;
import com.yagmur.repository.CategoryRepository;
import com.yagmur.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final FileStorageService fileStorageService;

    public Products saveProduct(ProductSaveRequestDto productSaveRequestDto) {

        Category category = categoryRepository.findById(productSaveRequestDto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Kategori bulunamadı"));

        Products product = Products.builder()
                .imageUrl(productSaveRequestDto.getImageUrl())
                .name(productSaveRequestDto.getName())
                .description(productSaveRequestDto.getDescription())
                .price(productSaveRequestDto.getPrice())
                .categoryId(category.getId()) // Kategoriyi ata
                .build();
        return productRepository.save(product);
    }

    public Products saveProductWithFile(
            MultipartFile file,
            String name,
            String description,
            Double price,
            String categoryId
    ) throws IOException {
        // Kategoriyi kontrol et
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Kategori bulunamadı"));

        // Dosyayı kaydet ve URL'yi al
        String fileUrl = fileStorageService.storeFile(file);
        System.out.println("fileUrl" +  fileUrl);
        // Ürünü oluştur ve kaydet
        Products product = Products.builder()
                .name(name)
                .description(description)
                .price(price)
                .imageUrl(fileUrl) // Dosya URL'si buraya ekleniyor
                .categoryId(category.getId()) // Kategori ID
                .build();

        return productRepository.save(product);
    }

    // Tüm Ürünleri Getirme
    public List<Products> getAllProducts() {
        System.out.println("productRepository"+productRepository);

        return productRepository.findAll();
    }

    // ID'ye Göre Ürün Getirme
    public Optional<Products> getProductById(String id) {
        return productRepository.findById(id);
    }

    // Ürün Güncelleme
    public Products updateProduct(ProductUpdateRequestDto productUpdateRequestDto) {
        Products product = productRepository.findById(productUpdateRequestDto.getId())
                .orElseThrow(() -> new RuntimeException("Ürün bulunamadı"));

        product.setName(productUpdateRequestDto.getName());
        product.setDescription(productUpdateRequestDto.getDescription());
        product.setPrice(productUpdateRequestDto.getPrice());
        product.setImageUrl(productUpdateRequestDto.getImageUrl());

        if (productUpdateRequestDto.getCategoryId() != null) {
            Category category = categoryRepository.findById(productUpdateRequestDto.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Kategori bulunamadı"));
            product.setCategoryId(category.getId());
        }

        return productRepository.save(product);
    }

    // Ürün Silme
    public void deleteProduct(String id) {
        productRepository.deleteById(id);
    }

    public List<Products> getProductsByCategoryName(String categoryName) {
        Category category = categoryRepository.findByNameIgnoreCase(categoryName)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        return productRepository.findByCategoryId(category.getId());
    }

}
