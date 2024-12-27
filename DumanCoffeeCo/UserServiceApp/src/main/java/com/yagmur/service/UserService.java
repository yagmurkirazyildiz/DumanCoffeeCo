package com.yagmur.service;


import com.yagmur.dto.request.UserSaveRequestDto;
import com.yagmur.dto.response.UserSaveResponseDto;
import com.yagmur.entity.User;
import com.yagmur.mapper.UserMapper;
import com.yagmur.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserSaveResponseDto saveUser (UserSaveRequestDto userSaveRequestDto){
        User user = userRepository.save(UserMapper.INSTANCE.fromUserSaveRequestDtoToUser(userSaveRequestDto));
        return UserMapper.INSTANCE.fromUserToUserSaveResponseDto(user);
    }

}
