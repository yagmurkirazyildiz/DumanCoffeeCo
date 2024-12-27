package com.yagmur.mapper;

import com.yagmur.dto.request.AuthSaveRequestDto;
import com.yagmur.entity.Auth;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AuthMapper {

    AuthMapper INSTANCE = Mappers.getMapper(AuthMapper.class);


     Auth fromAuthSaveRequestDtoToUser(final AuthSaveRequestDto dto);


}
