package com.yagmur.service;

import com.yagmur.dto.request.CategorySaveRequestDto;
import com.yagmur.dto.request.CategoryUpdateRequestDto;
import com.yagmur.entity.Category;
import com.yagmur.mapper.CategoryMapper;
import com.yagmur.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    // Kategori Kaydetme
    public Category saveCategory(CategorySaveRequestDto categorySaveRequestDto) {
        return categoryRepository.save(CategoryMapper.INSTANCE.fromCategorySaveRequestDtoToCategory(categorySaveRequestDto));
    }

    // Tüm Kategorileri Getirme
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    // ID'ye Göre Kategori Getirme
    public Optional<Category> getCategoryById(String id) {
        return categoryRepository.findById(id);
    }

    // Kategori Güncelleme
    public Category updateCategory(CategoryUpdateRequestDto categoryUpdateRequestDto) {
        Category category = categoryRepository.findById(categoryUpdateRequestDto.getId())
                .orElseThrow(() -> new RuntimeException("Kategori bulunamadı"));

        category.setName(categoryUpdateRequestDto.getName());
        category.setDescription(categoryUpdateRequestDto.getDescription());
        return categoryRepository.save(category);
    }

    // Kategori Silme
    public void deleteCategory(String id) {
        categoryRepository.deleteById(id);
    }


}
