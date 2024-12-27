package com.yagmur.mapper;

import com.yagmur.dto.request.UserSaveRequestDto;
import com.yagmur.dto.response.UserSaveResponseDto;
import com.yagmur.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);


     User fromUserSaveRequestDtoToUser(final UserSaveRequestDto dto);

     UserSaveResponseDto fromUserToUserSaveResponseDto(final User user);

}
