package com.yagmur.controller;

import com.yagmur.dto.request.CategorySaveRequestDto;
import com.yagmur.dto.request.CategoryUpdateRequestDto;
import com.yagmur.dto.response.BasicResponse;
import com.yagmur.entity.Category;
import com.yagmur.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.yagmur.constans.RestApiUrls.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(CATEGORY)
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping(SAVE)
    @CrossOrigin("*")
    public ResponseEntity<BasicResponse<Object>> saveCategory(@RequestBody CategorySaveRequestDto categorySaveRequestDto) {
        return ResponseEntity.ok(BasicResponse.builder()
                .status(200)
                .message("Kategori Eklendi.")
                .data(categoryService.saveCategory(categorySaveRequestDto))
                .build());
    }

    @GetMapping(GET_ALL)
    @CrossOrigin("*")
    public ResponseEntity<BasicResponse<List<Category>>> getAllCategories() {
        return ResponseEntity.ok(BasicResponse.<List<Category>>builder()
                .status(200)
                .message("Kategoriler Listelendi.")
                .data(categoryService.getAllCategories())
                .build());
    }

    @GetMapping(GET_BY_ID)
    @CrossOrigin("*")
    public ResponseEntity<BasicResponse<Object>> getCategoryById(@PathVariable String id) {
        return ResponseEntity.ok(BasicResponse.builder()
                .status(200)
                .message("Kategori Getirildi.")
                .data(categoryService.getCategoryById(id))
                .build());
    }

    @PutMapping(UPDATE)
    @CrossOrigin("*")
    public ResponseEntity<BasicResponse<Object>> updateCategory(@RequestBody CategoryUpdateRequestDto categoryUpdateRequestDto) {
        return ResponseEntity.ok(BasicResponse.builder()
                .status(200)
                .message("Kategori Güncellendi.")
                .data(categoryService.updateCategory(categoryUpdateRequestDto))
                .build());
    }

    @DeleteMapping(DELETE_BY_ID)
    @CrossOrigin("*")
    public ResponseEntity<BasicResponse<Object>> deleteCategory(@PathVariable String id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok(BasicResponse.builder()
                .status(200)
                .message("Kategori Silindi.")
                .build());
    }



}