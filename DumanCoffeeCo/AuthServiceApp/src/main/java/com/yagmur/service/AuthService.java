package com.yagmur.service;


import com.yagmur.dto.request.AuthSaveRequestDto;
import com.yagmur.dto.request.LoginRequestDto;
import com.yagmur.entity.Auth;
import com.yagmur.exception.AuthManagerException;
import com.yagmur.exception.ErrorType;
import com.yagmur.mapper.AuthMapper;
import com.yagmur.repository.AuthRepository;
import com.yagmur.utility.ERole;
import com.yagmur.utility.JwtTokenManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthRepository authRepository;
    private final JwtTokenManager jwtTokenManager;

    public Auth register (AuthSaveRequestDto authSaveRequestDto){
        Auth auth = authRepository.save(AuthMapper.INSTANCE.fromAuthSaveRequestDtoToUser(authSaveRequestDto));
        return auth;
    }

    public String login (LoginRequestDto loginRequestDto){
        Auth auth = authRepository.findByEmailAndPassword(loginRequestDto.getEmail(), loginRequestDto.getPassword())
                .orElseThrow(() -> new AuthManagerException(ErrorType.USER_NOT_FOUND));
        String token = jwtTokenManager.createToken(auth.getId(), auth.getRole()).get();
        if (token.isEmpty()) {
            throw new AuthManagerException(ErrorType.TOKEN_NOT_FOUND);
        }
        return token;
    }

    public ERole getRoleFromToken(String token) {
        Optional<String> role = jwtTokenManager.getRoleFromToken(token);
        if (role.isEmpty()) {
            throw new AuthManagerException(ErrorType.INVALID_TOKEN);
        }

        // role string bilgisini ERole'a çeviriyoruz
        return ERole.valueOf(role.get());
    }



}
