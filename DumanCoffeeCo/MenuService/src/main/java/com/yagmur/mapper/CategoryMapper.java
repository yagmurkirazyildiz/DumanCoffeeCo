package com.yagmur.mapper;

import com.yagmur.dto.request.CategorySaveRequestDto;
import com.yagmur.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;


@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    Category fromCategorySaveRequestDtoToCategory(CategorySaveRequestDto dto);

    CategorySaveRequestDto fromCategoryToCategorySaveRequestDto(Category category);

}

