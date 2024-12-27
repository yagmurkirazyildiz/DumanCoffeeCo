package com.yagmur.mapper;

import com.yagmur.dto.request.ProductSaveRequestDto;
import com.yagmur.entity.Products;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)

public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);
   Products fromProductSaveRequestDtoToProducts(final ProductSaveRequestDto dto);

}
